package ph.proj.aep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ph.proj.aep.models.UsuarioModel;
import ph.proj.aep.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
        public UsuarioService usuarioService;

    @PostMapping
    public UsuarioModel criarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.criarUsuario(usuario);

    }

    @GetMapping
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioModel> consultarUsuarioId(@PathVariable Long id) {
        return usuarioService.consultarUsuarioId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuarioId(id);
    }

    @PutMapping("/{id}")
    public UsuarioModel atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }


}
