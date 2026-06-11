package ph.proj.aep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.proj.aep.models.CategoriaModel;

public interface CategoriaRepository extends JpaRepository <CategoriaModel, Long> {
}
