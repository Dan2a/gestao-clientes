package gestaodecontatos.gestaodecontatos.service;

import gestaodecontatos.gestaodecontatos.entity.Cliente;
import gestaodecontatos.gestaodecontatos.entity.Contato;
import gestaodecontatos.gestaodecontatos.repository.ClienteRepository;
import gestaodecontatos.gestaodecontatos.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ClienteRepository clienteRepository;

    public ContatoService(ContatoRepository contatoRepository, ClienteRepository clienteRepository) {
        this.contatoRepository = contatoRepository;
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os contatos de um cliente
    @Transactional(readOnly = true)
    public List<Contato> findByClienteId(Long clienteId) {
        return contatoRepository.findByClienteId(clienteId);
    }

    // Cadastrar contao para cliente
    @Transactional
    public Contato save(Long clienteId, Contato contato) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        contato.setCliente(cliente);
        return contatoRepository.save(contato);
    }

    // Atualizar contato
    @Transactional
    public Contato update(Long id, Contato contatoAtualizado) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado!"));

        contato.setTipo(contatoAtualizado.getTipo());
        contato.setValor(contatoAtualizado.getValor());
        contato.setObservacao(contatoAtualizado.getObservacao());

        return contatoRepository.save(contato);
    }

    // Excluir contato
    @Transactional
    public Boolean deleteById(Long id) {
        Optional<Contato> contato = contatoRepository.findById(id);

        if (contato.isPresent()) {
            contatoRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public void deleteByClienteId(Long clienteId) {
        List<Contato> contatos = contatoRepository.findByClienteId(clienteId);
        contatoRepository.deleteAll(contatos);
    }


}
