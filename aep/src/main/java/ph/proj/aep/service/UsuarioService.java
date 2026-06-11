package ph.proj.aep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.proj.aep.enums.UsuarioEnum;
import ph.proj.aep.models.UsuarioModel;
import ph.proj.aep.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;


    public UsuarioModel criarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> consultarUsuarioId(Long id){
        return usuarioRepository.findById(id);
    }

    public void deletarUsuarioId(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioModel atualizarUsuario(Long id, UsuarioModel usuarioAtualizado) {
        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setUsuarioEnum(usuarioAtualizado.getUsuarioEnum());

        return usuarioRepository.save(usuario);
    }







}
