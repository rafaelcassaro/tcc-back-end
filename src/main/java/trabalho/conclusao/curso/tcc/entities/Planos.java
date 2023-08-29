package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "planos")
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "plano",cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Contratos> contrato;
    public Planos(){}

    public Planos(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setContrato(List<Contratos> contrato) {
        this.contrato = contrato;
    }

    public List<Contratos> getContrato() {
        return contrato;
    }

    public void addContrato(Contratos tempContrato) {
        if ((contrato == null)){
            contrato = new ArrayList<>();
        }
        contrato.add(tempContrato);
        tempContrato.setPlano(this);
    }
}

