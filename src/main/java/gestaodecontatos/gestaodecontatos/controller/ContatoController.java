package gestaodecontatos.gestaodecontatos.controller;

import gestaodecontatos.gestaodecontatos.entity.Contato;
import gestaodecontatos.gestaodecontatos.service.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Contato>> findByClienteId(@PathVariable Long clienteId) {
        List<Contato> contato = contatoService.findByClienteId(clienteId);
        return ResponseEntity.ok().body(contato);
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Contato> save(@PathVariable Long clienteId, @RequestBody Contato contato) {
        Contato salvarContato = contatoService.save(clienteId, contato);
        return ResponseEntity.ok().body(salvarContato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
        Contato contatoAtualizado = contatoService.update(id, contato);
        return ResponseEntity.ok().body(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Boolean flag = contatoService.deleteById(id);
        return ResponseEntity.ok().body(flag);
    }

}
