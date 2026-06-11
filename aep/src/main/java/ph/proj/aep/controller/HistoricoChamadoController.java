package ph.proj.aep.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ph.proj.aep.models.HistoricoChamadoModel;
import ph.proj.aep.service.HistoricoChamadoService;

import java.util.List;

@RestController
@RequestMapping("historicos")
public class HistoricoChamadoController {

    @Autowired
    public HistoricoChamadoService historicoChamadoService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoChamadoModel registrarHistorico(@PathVariable Long id, @Valid @RequestBody HistoricoChamadoModel historicoChamado) {
        return historicoChamadoService.registrarHistorico(id, historicoChamado);
    }

    @GetMapping
    public List<HistoricoChamadoModel> listarHistoricoChamado() {
        return historicoChamadoService.listarHistoricoChamado();
    }

    @GetMapping("/chamado/{chamadoId}")
    public List<HistoricoChamadoModel> listarPorChamado(@PathVariable Long chamadoId) {
        return historicoChamadoService.listarPorChamado(chamadoId);
    }

}
