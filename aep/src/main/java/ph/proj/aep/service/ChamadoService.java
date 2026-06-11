package ph.proj.aep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.proj.aep.enums.StatusChamadoEnum;
import ph.proj.aep.models.CategoriaModel;
import ph.proj.aep.models.ChamadoModel;
import ph.proj.aep.repository.CategoriaRepository;
import ph.proj.aep.repository.ChamadoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    public ChamadoRepository chamadoRepository;

    @Autowired
    public CategoriaRepository categoriaRepository;

    // Cria o novo chamado com status inicial PENDENTE e gera o protocolo automaticamente
    public ChamadoModel criarChamado(ChamadoModel chamado) {
        if (chamado.getCategoria() == null || chamado.getCategoria().getId() == null) {
            throw new RuntimeException("Categoria é obrigatória");
        }
        CategoriaModel categoria = categoriaRepository.findById(chamado.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        chamado.setCategoria(categoria);

        chamado.setDataCadastro(LocalDateTime.now());
        chamado.setStatus(StatusChamadoEnum.PENDENTE);

        // Salva o chamado para obter o ID gerado pelo banco
        ChamadoModel salvo = chamadoRepository.save(chamado);

        // Usa o ID como número de protocolo
        salvo.setProtocolo(salvo.getId().intValue());
        return chamadoRepository.save(salvo);
    }

    // Busca chamado pelo ID
    public Optional<ChamadoModel> consultarPorProtocolo(Long id) {
        return chamadoRepository.findById(id);
    }

    // Busca chamado pelo número de protocolo (id do banco)
    public Optional<ChamadoModel> consultarPorProtocoloNumero(int protocolo) {
        return chamadoRepository.findByProtocolo(protocolo);
    }


    public List<ChamadoModel> listarChamados() {
        return chamadoRepository.findAll();
    }

    // Atualiza apenas o status, sem alterar a data de cadastro
    public ChamadoModel atualizarStatus(Long id, StatusChamadoEnum novoStatus) {
        ChamadoModel chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        chamado.setStatus(novoStatus);
        return chamadoRepository.save(chamado);
    }

    public void deletarChamadoPorId(Long id) {
        chamadoRepository.deleteById(id);
    }
}
