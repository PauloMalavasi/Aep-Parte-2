package ph.proj.aep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.proj.aep.models.CategoriaModel;
import ph.proj.aep.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;


    public CategoriaModel criarCategoria(CategoriaModel categoriaModel){
        return categoriaRepository.save(categoriaModel);
    }

    public List<CategoriaModel> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public void deletarCategoria(CategoriaModel categoriaModel){
        categoriaRepository.delete(categoriaModel);
    }

    public CategoriaModel atualizarCategoria(CategoriaModel categoriaAtualizada, Long id){
        CategoriaModel categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        categoria.setNome(categoriaAtualizada.getNome());

        return categoriaRepository.save(categoria);
    }

}
