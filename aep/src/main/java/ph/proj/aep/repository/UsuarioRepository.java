package ph.proj.aep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.proj.aep.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}
