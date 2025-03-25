package gestaodecontatos.gestaodecontatos.service;

import gestaodecontatos.gestaodecontatos.controller.ClienteDTO;
import gestaodecontatos.gestaodecontatos.entity.Cliente;
import gestaodecontatos.gestaodecontatos.repository.ClienteRepository;
import gestaodecontatos.gestaodecontatos.repository.ContatoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContatoRepository contatoRepository;

    public ClienteService(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        this.clienteRepository = clienteRepository;
        this.contatoRepository = contatoRepository;
    }

    // Lista CLientes
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Mostra um cliente pelo ID
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    // Busca um cliente pelo NOME ou CPF
    @Transactional(readOnly = true)
    public List<Cliente> findByNomeOrCpf(String nome, String cpf) {
        return clienteRepository.findByNomeOrCpf(nome, cpf);
    }

    // Cadastrar novo cliente
    @Transactional
    public Cliente save(ClienteDTO clienteDTO) {
        // Verifica se o CPF já existe no banco
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        // Converte ClienteDTO para Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEndereco(clienteDTO.getEndereco());

        // Salva o cliente
        return clienteRepository.save(cliente);
    }


    // Atualiza cliente
    @Transactional
    public Cliente update(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = findById(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEndereco(clienteDTO.getEndereco());
        return clienteRepository.save(cliente);
    }

    // Deletar cliente
    @Transactional
    public Boolean deleteById(Long id) {
        Cliente cliente = findById(id);
        if (cliente == null) {
            return false;
        }else{
            contatoRepository.deleteAll(contatoRepository.findByClienteId(id));
            clienteRepository.deleteById(id);
            return true;
        }
    }
}
