package ph.proj.aep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ph.proj.aep.models.CategoriaModel;
import ph.proj.aep.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaModel> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    public CategoriaModel criarCategoria(@RequestBody CategoriaModel categoria) {
        return categoriaService.criarCategoria(categoria);
    }
}
