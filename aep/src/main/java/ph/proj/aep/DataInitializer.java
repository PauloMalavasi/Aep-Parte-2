package ph.proj.aep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ph.proj.aep.models.CategoriaModel;
import ph.proj.aep.repository.CategoriaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) {
        // Crias as categorias
        if (categoriaRepository.count() == 0) {
            String[] categorias = {
                "Iluminação pública",
                "Buraco na via",
                "Limpeza urbana",
                "Saneamento",
                "Segurança",
                "Saúde",
                "Transporte público",
                "Outros"
            };

            for (String nome : categorias) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setNome(nome);
                categoriaRepository.save(categoria);
            }

            //Teste
            System.out.println("Categorias padrão inseridas com sucesso.");
        }
    }
}
