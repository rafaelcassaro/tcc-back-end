package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "fotos")
public class Fotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeFoto;
    private String caminhoImagem;



    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "post_moradia_id")
    private PostMoradia postMoradia;

    public Fotos(){}

    public Fotos(Long id, String caminhoImagem, String nomeFoto) {
        this.id = id;
        this.caminhoImagem = caminhoImagem;
        this.nomeFoto = nomeFoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }



    public PostMoradia getPostMoradia() {
        return postMoradia;
    }

    public void setPostMoradia(PostMoradia postMoradia) {
        this.postMoradia = postMoradia;
    }
}
