package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import trabalho.conclusao.curso.tcc.entities.enums.PlanoStatus;


import java.io.Serializable;
import java.util.*;

@Entity
@Table(name ="usuario")
public class Usuario  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String celular;
    private String senha;
    private String link1;
    private String link2;
    private String link3;
    //private Image fotoPerfil;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Post> postagens;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Feedback> feedbacks;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Contratos> contratos;


    public Usuario(){}


    public Usuario(Long id, String nome, String email, String celular, String senha, String link1, String link2, String link3) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
        this.link1 = link1;
        this.link2 = link2;
        this.link3 = link3;
        //this.fotoPerfil = fotoPerfil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLink1() {
        return link1;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public String getLink2() {
        return link2;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public String getLink3() {
        return link3;
    }

    public void setLink3(String link3) {
        this.link3 = link3;
    }

    public List<Post> getPostagens() {
        return postagens;
    }

    public void addPost(Post tempPost){
        if ((postagens == null)){
            postagens = new ArrayList<>();
        }

        postagens.add(tempPost);
        tempPost.setUsuario(this);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback tempFeedback){
        if ((feedbacks == null)){
            feedbacks = new ArrayList<>();
        }

        feedbacks.add(tempFeedback);
        tempFeedback.setUsuario(this);
    }

    public List<Contratos> getContratos() {
        return contratos;
    }

    public void setContratos(Contratos tempContratos) {
        if ((contratos == null)){
            contratos = new ArrayList<>();
        }
        contratos.add(tempContratos);
        tempContratos.setUsuario(this);
    }
}
