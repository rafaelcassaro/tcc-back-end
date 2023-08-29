package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "detalhes_moradia")
public class DetalhesMoradia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quarto;
    private Integer garagem;
    private Integer moradores;
    private Integer pets;

    @JsonIgnore
    @OneToOne(mappedBy = "detalhesMoradia", cascade = CascadeType.ALL)
    private PostMoradia postMoradia;

    public  DetalhesMoradia(){}
    public DetalhesMoradia(Long id, String quarto, Integer garagem, Integer moradores, Integer pets) {
        this.id = id;
        this.quarto = quarto;
        this.garagem = garagem;
        this.moradores = moradores;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public Integer getGaragem() {
        return garagem;
    }

    public void setGaragem(Integer garagem) {
        this.garagem = garagem;
    }

    public Integer getMoradores() {
        return moradores;
    }

    public void setMoradores(Integer moradores) {
        this.moradores = moradores;
    }

    public Integer getPets() {
        return pets;
    }

    public void setPets(Integer pets) {
        this.pets = pets;
    }

    public PostMoradia getPostMoradia() {
        return postMoradia;
    }

    public void setPostMoradia(PostMoradia postMoradia) {
        this.postMoradia = postMoradia;
    }
}
