package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import trabalho.conclusao.curso.tcc.entities.enums.GeneroMoradia;


@Entity
@Table(name = "detalhes_moradia")
public class DetalhesMoradia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean quarto;
    private boolean garagem;
    private Integer moradores;
    private boolean pets;

    private Integer generoMoradia;


    @JsonIgnore
    @OneToOne(mappedBy = "detalhesMoradia", cascade = CascadeType.ALL)
    private PostMoradia postMoradia;

    public  DetalhesMoradia(){}

    public DetalhesMoradia(Long id, boolean quarto, boolean garagem, Integer moradores, boolean pets, Integer generoMoradia) {
        this.id = id;
        this.quarto = quarto;
        this.garagem = garagem;
        this.moradores = moradores;
        this.pets = pets;
        this.generoMoradia = generoMoradia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuarto() {
        if(quarto ==true){
            return "individual";
        }
        else{
            return "compartilhado";
        }
    }

    public void setQuarto(boolean quarto) {
        this.quarto = quarto;
    }

    public String getGaragem() {
        if(garagem ==true){
            return "tem garagem";
        }
        else{
            return "não tem garagem";
        }

    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    public Integer getMoradores() {
        return moradores;
    }

    public void setMoradores(Integer moradores) {
        this.moradores = moradores;
    }

    public String getPets() {
        if(pets ==true){
            return "possui";
        }
        else{
            return "não possui";
        }

    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public GeneroMoradia getGeneroMoradia() {
        return GeneroMoradia.valueOf(generoMoradia);
    }

    public void setGeneroMoradia(GeneroMoradia generoMoradia) {
        if(generoMoradia != null){
            this.generoMoradia = generoMoradia.getCode();
        }
    }

    public PostMoradia getPostMoradia() {
        return postMoradia;
    }

    public void setPostMoradia(PostMoradia postMoradia) {
        this.postMoradia = postMoradia;
    }
}
