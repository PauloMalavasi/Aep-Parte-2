package ph.proj.aep.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ph.proj.aep.enums.StatusChamadoEnum;
import ph.proj.aep.models.ChamadoModel;
import ph.proj.aep.service.ChamadoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chamados")
public class ChamadoController {

    @Autowired
    public ChamadoService chamadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChamadoModel criarChamado(@Valid @RequestBody ChamadoModel chamado) {
        return chamadoService.criarChamado(chamado);
    }

    @GetMapping("/protocolo/{protocolo}")
    public Optional<ChamadoModel> consultarPorProtocolo(@PathVariable int protocolo) {
        return chamadoService.consultarPorProtocoloNumero(protocolo);
    }

    @GetMapping("/{id}")
    public Optional<ChamadoModel> consultarPorId(@PathVariable Long id) {
        return chamadoService.consultarPorProtocolo(id);
    }

    @GetMapping
    public List<ChamadoModel> consultarPorChamado() {
        return chamadoService.listarChamados();
    }

    @DeleteMapping("/{id}")
    public void deletarPorChamado(@PathVariable Long id) {
        chamadoService.deletarChamadoPorId(id);
    }

    @PutMapping("/{id}")
    public ChamadoModel atualizarStatus(@PathVariable Long id, @RequestBody StatusChamadoEnum novoStatus) {
        return chamadoService.atualizarStatus(id, novoStatus);
    }



}
