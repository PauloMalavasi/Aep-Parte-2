package ph.proj.aep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.proj.aep.models.ChamadoModel;
import ph.proj.aep.models.HistoricoChamadoModel;
import ph.proj.aep.repository.ChamadoRepository;
import ph.proj.aep.repository.HistoricoChamadoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoChamadoService {

    @Autowired
    public HistoricoChamadoRepository historicoChamadoRepository;

    @Autowired
    public ChamadoRepository chamadoRepository;


    public HistoricoChamadoModel registrarHistorico(Long id, HistoricoChamadoModel historico) {
        ChamadoModel chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        // Vincula o chamado e registra a data/hora automaticamente
        historico.setChamado(chamado);
        historico.setData(LocalDateTime.now());

        return historicoChamadoRepository.save(historico);
    }


    public List<HistoricoChamadoModel> listarHistoricoChamado() {
        return historicoChamadoRepository.findAll();
    }


    public List<HistoricoChamadoModel> listarPorChamado(Long chamadoId) {
        return historicoChamadoRepository.findByChamadoId(chamadoId);
    }
}
