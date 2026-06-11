package ph.proj.aep.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ph.proj.aep.enums.StatusChamadoEnum;

import java.time.LocalDateTime;

@Entity
public class HistoricoChamadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome do responsável é obrigatório")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_chamado")
    private ChamadoModel chamado;

    @NotNull(message = "Status é obrigatório")
    private StatusChamadoEnum status;

    private String comentario;
    private LocalDateTime data;


    public HistoricoChamadoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ChamadoModel getChamado() {
        return chamado;
    }

    public void setChamado(ChamadoModel chamado) {
        this.chamado = chamado;
    }

    public StatusChamadoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusChamadoEnum status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
