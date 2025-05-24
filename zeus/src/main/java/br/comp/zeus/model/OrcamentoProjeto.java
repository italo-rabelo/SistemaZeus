package br.comp.zeus.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class OrcamentoProjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numeroOrcamento;
    private String descricaoProjeto;
    private String cliente;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    private double valorEstimado;
    private double custosPrevistos;
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento status;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDate.now();
        status = StatusOrcamento.EM_ANALISE;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatusOrcamento getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamento status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getCustosPrevistos() {
        return custosPrevistos;
    }

    public void setCustosPrevistos(double custosPrevistos) {
        this.custosPrevistos = custosPrevistos;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public String getNumeroOrcamento() {
        return numeroOrcamento;
    }

    public void setNumeroOrcamento(String numeroOrcamento) {
        this.numeroOrcamento = numeroOrcamento;
    }
}
