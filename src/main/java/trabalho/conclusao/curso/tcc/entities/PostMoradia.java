package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "post_moradia")
public class PostMoradia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean tipoResidencia;
    private String endereco;
    private Integer numCasa;
    private Double valorAluguel;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalhes_moradia_id")
    private DetalhesMoradia detalhesMoradia;

    public PostMoradia(){}

    public PostMoradia(Long id, boolean tipoResidencia, String endereco, Integer numCasa, Double valorAluguel) {
        this.id = id;
        this.tipoResidencia = tipoResidencia;
        this.endereco = endereco;
        this.numCasa = numCasa;
        this.valorAluguel = valorAluguel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoResidencia() {
        if(tipoResidencia == true){
            return "Casa";
        }
        else {
            return "Apartamento";
        }

    }

    public void setTipoResidencia(boolean tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(Integer numCasa) {
        this.numCasa = numCasa;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }


    public DetalhesMoradia getDetalhes() {
        return detalhesMoradia;
    }

    public void setDetalhes(DetalhesMoradia detalhes) {
        this.detalhesMoradia = detalhes;
    }
}
