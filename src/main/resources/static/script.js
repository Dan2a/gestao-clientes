const API_URL = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", () => {
    carregarClientes();
    document.getElementById("formCliente").addEventListener("submit", salvarCliente);
    document.getElementById("btnPesquisarCliente").addEventListener("click", pesquisarClientes);
    document.getElementById("addContato").addEventListener("click", adicionarCampoContato);
    document.getElementById("pesquisaCliente").addEventListener("input", sugerirClientes);
    document.getElementById("pesquisaCliente").addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            document.getElementById("btnPesquisarCliente").click();
        }
    });
});

async function carregarClientes() {
    const response = await fetch(`${API_URL}/clientes/listar`);
    const clientes = await response.json();
    atualizarTabela(clientes);
}

function atualizarTabela(clientes) {
    const tabela = document.getElementById("clientesTabela");
    tabela.innerHTML = "";
    clientes.forEach(cliente => {
        const linha = document.createElement("tr");

        const formatarData = (dataISO) => {
            if (!dataISO) return "";
            const [ano, mes, dia] = dataISO.split("-");
            return `${dia}/${mes}/${ano}`;
        };

        linha.innerHTML = `
            <td>${cliente.nome}</td>
            <td>${cliente.cpf}</td>
            <td>${formatarData(cliente.dataNascimento)}</td>
            <td>${cliente.endereco}</td>
            <td class="acao"><button onclick="editarCliente(${cliente.id})"><i class='bx bx-edit' ></button></td>
            <td class="acao"><button onclick="excluirCliente(${cliente.id})"><i class='bx bx-trash'></button></td>
        `;
        tabela.appendChild(linha);
    });
}

async function salvarCliente(event) {
    event.preventDefault();

    const cliente = {
        id: document.getElementById("idCliente").value || null,
        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value,
        dataNascimento: document.getElementById("dataNascimento").value,
        endereco: document.getElementById("endereco").value,
        contatos: obterContatos()
    };

    const metodo = cliente.id ? "PUT" : "POST";
    const url = cliente.id ? `${API_URL}/clientes/${cliente.id}` : `${API_URL}/clientes/salvar`;

    try {
        const response = await fetch(url, {
            method: metodo,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente)
        });

        if (!response.ok) {
            const erroTexto = await response.text();
            console.error("Erro da API:", erroTexto);
            throw new Error('Erro ao salvar cliente');
        }

        alert("Cliente salvo com sucesso!");
        fecharModal();
        carregarClientes();
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao salvar cliente. Tente novamente.');
    }
}

function obterContatos() {
    const contatos = [];
    const contatoElements = document.querySelectorAll("#contatosContainer div");

    contatoElements.forEach(div => {
        const tipo = div.querySelector("select").value;
        const valor = div.querySelector("input[name='valorContato']").value;
        const observacao = div.querySelector("input[name='observacao']").value;

        if (valor) {
            contatos.push({ tipo, valor, observacao });
        }
    });

    return contatos;
}

function adicionarCampoContato(tipo = '', valor = '', observacao = '', id = null) {
    const container = document.getElementById("contatosContainer");
    const div = document.createElement("div");
    div.classList.add("contatoItem");

    div.innerHTML = `
        <select>
            <option value="TELEFONE" ${tipo === "TELEFONE" ? 'selected' : ''}>Telefone</option>
            <option value="EMAIL" ${tipo === "EMAIL" ? 'selected' : ''}>E-mail</option>
        </select>
        <input type="text" name="valorContato" placeholder="Digite o contato" value="${valor}" required>
        <input type="text" name="observacao" class="observacao" placeholder="Observação (opcional)" value="${observacao}">
        <button type="button" onclick="removerContato(${id})">
            <i class='bx bx-x'></i>
        </button>
    `;

    div.setAttribute("data-id", id);

    container.appendChild(div);
}

async function removerContato(id) {
    console.log("ID do contato para remoção:", id);
    try {
        const response = await fetch(`${API_URL}/contatos/${id}`, { method: "DELETE" });

        const contatoItem = document.querySelector(`[data-id='${id}']`);
        if (contatoItem) {
            contatoItem.remove();
        };
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao excluir contato. Tente novamente.');
    }
}

async function editarCliente(id) {
    const response = await fetch(`${API_URL}/clientes/${id}`);
    const cliente = await response.json();
    console.log(cliente);

    document.getElementById("nome").value = cliente.nome;
    document.getElementById("cpf").value = cliente.cpf;
    document.getElementById("dataNascimento").value = cliente.dataNascimento;
    document.getElementById("endereco").value = cliente.endereco;
    document.getElementById("idCliente").value = cliente.id;

    const contatos = cliente.contatos || [];
    console.log("Contatos recebidos:", contatos);

    const contatosContainer = document.getElementById("contatosContainer");
    contatosContainer.innerHTML = '';

    contatos.forEach(contato => {
        adicionarCampoContato(contato.tipo, contato.valor, contato.observacao, contato.id);
    });

    abrirModal();
    document.getElementById("modalClienteTitle").textContent = "Editar Cliente";
}

async function excluirCliente(id) {
    if (confirm("Tem certeza que deseja excluir este cliente?")) {
        await fetch(`${API_URL}/clientes/${id}`, { method: "DELETE" });
        carregarClientes();
    }
}

async function pesquisarClientes() {
    const termo = document.getElementById("pesquisaCliente").value;

    if(termo.length === 0 ){
        carregarClientes();
        return;
    }

    try{
        const response = await fetch(`${API_URL}/clientes/buscar?nome=${termo}&cpf=${termo}`);
        const clientes = await response.json();
        atualizarTabela(clientes);
    }catch(error){
        console.error("Erro ao pesquisar cliente:", error);
        alert("Erro ao pesquisar cliente.");
    }
}

async function sugerirClientes() {
    const termo = document.getElementById("pesquisaCliente").value.trim();

    if (termo.length === 0) {
        document.getElementById("sugestoes").innerHTML = '';
        return;
    }

    try {
        let response = await fetch(`${API_URL}/clientes/buscar?nome=${termo}&cpf=${termo}`);
        let clientes = await response.json();

        let sugestoesList = document.getElementById("sugestoes");
        sugestoesList.innerHTML = '';

        clientes.forEach((cliente, index) => {
            let li = document.createElement("li");
            li.textContent = cliente.nome;
            li.onclick = function() {
                document.getElementById("pesquisaCliente").value = cliente.nome; 
                document.getElementById("sugestoes").innerHTML = '';
            };
            sugestoesList.appendChild(li);
        });
    } catch (error) {
        console.error("Erro ao buscar clientes:", error);
    }
}

function abrirModal() {
    document.getElementById("modalClienteTitle").textContent = "Adicionar Cliente";
    document.getElementById("modalCliente").style.display = "flex";
}

function fecharModal() {
    document.getElementById("modalCliente").style.display = "none";
    document.getElementById("formCliente").reset();
    document.getElementById("idCliente").value = "";

    const contatosContainer = document.getElementById("contatosContainer");
    contatosContainer.innerHTML = '';
}

window.addEventListener('click', function(event) {
    let modal = document.getElementById("modalCliente");
    if (event.target === modal) {
        fecharModal();
    }
});

