package ph.proj.aep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.proj.aep.models.HistoricoChamadoModel;

import java.util.List;

public interface HistoricoChamadoRepository extends JpaRepository<HistoricoChamadoModel, Long> {

    List<HistoricoChamadoModel> findByChamadoId(Long chamadoId);
}
