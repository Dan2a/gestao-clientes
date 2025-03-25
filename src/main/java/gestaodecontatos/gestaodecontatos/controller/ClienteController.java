package gestaodecontatos.gestaodecontatos.controller;

import gestaodecontatos.gestaodecontatos.entity.Cliente;
import gestaodecontatos.gestaodecontatos.entity.Contato;
import gestaodecontatos.gestaodecontatos.service.ClienteService;
import gestaodecontatos.gestaodecontatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ContatoService contatoService;

    public ClienteController(ClienteService clienteService, ContatoService contatoService) {
        this.clienteService = clienteService;
        this.contatoService = contatoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> cliente = clienteService.findAll();
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> findByNomeOrCpf(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf) {
        List<Cliente> cliente = clienteService.findByNomeOrCpf(nome, cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente novoCliente = clienteService.save(clienteDTO);

        // Salva os contatos associados
        if (clienteDTO.getContatos() != null) {
            for (ContatoDTO contatoDTO : clienteDTO.getContatos()) {
                Contato contato = new Contato();
                contato.setTipo(contatoDTO.getTipo());
                contato.setValor(contatoDTO.getValor());
                contato.setObservacao(contatoDTO.getObservacao());
                contato.setCliente(novoCliente);
                contatoService.save(novoCliente.getId(), contato);
            }
        }

        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteAtualizado = clienteService.update(id, clienteDTO);

        if (clienteDTO.getContatos() != null) {
            contatoService.deleteByClienteId(id);

            for (ContatoDTO contatoDTO : clienteDTO.getContatos()) {
                Contato contato = new Contato();
                contato.setTipo(contatoDTO.getTipo());
                contato.setValor(contatoDTO.getValor());
                contato.setObservacao(contatoDTO.getObservacao());
                contato.setCliente(clienteAtualizado);
                contatoService.save(id, contato);
            }
        }

        return ResponseEntity.ok(clienteAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Boolean flag = clienteService.deleteById(id);
        return ResponseEntity.ok().body(flag);
    }
}
