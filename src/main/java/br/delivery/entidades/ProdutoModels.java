package br.delivery.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.Link;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoModels implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProduto;
    private String nome;
    private BigDecimal valor;
    
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public UUID getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(UUID idProduto) {
        this.idProduto = idProduto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



}
