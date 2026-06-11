package ph.proj.aep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.proj.aep.models.ChamadoModel;

import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<ChamadoModel, Long> {

    Optional<ChamadoModel> findByProtocolo(int protocolo);
}
