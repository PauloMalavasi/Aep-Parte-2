package ph.proj.aep.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ph.proj.aep.enums.PrioridadeChamadoEnum;
import ph.proj.aep.enums.StatusChamadoEnum;
import ph.proj.aep.enums.TipoChamadoEnum;

import java.time.LocalDateTime;

@Entity
public class ChamadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int protocolo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 500, message = "Endereço deve ter no máximo 500 caracteres")
    private String enderco;

    private String bairro;

    @NotNull(message = "Tipo do chamado é obrigatório")
    private TipoChamadoEnum tipo;

    @NotNull(message = "Prioridade é obrigatória")
    private PrioridadeChamadoEnum prioridade;

    private StatusChamadoEnum status;
    private LocalDateTime dataCadastro;
    private Boolean anonimo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @NotNull(message = "Categoria é obrigatória")
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;


    public ChamadoModel() {
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnderco() {
        return enderco;
    }

    public void setEnderco(String enderco) {
        this.enderco = enderco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public TipoChamadoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoChamadoEnum tipo) {
        this.tipo = tipo;
    }

    public PrioridadeChamadoEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeChamadoEnum prioridade) {
        this.prioridade = prioridade;
    }

    public StatusChamadoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusChamadoEnum status) {
        this.status = status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(Boolean anonimo) {
        this.anonimo = anonimo;
    }
}
