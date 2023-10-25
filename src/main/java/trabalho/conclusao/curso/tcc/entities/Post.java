package trabalho.conclusao.curso.tcc.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qntdDenuncia;
    private String comentario;
    private String cidade;
    private String estado;

   //G @JsonFormat(pattern="dd MMM yyyy")
    private Date dataPost;
    private String cep;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_moradia_id")
    private PostMoradia postMoradia;




    public Post(){}

    public Post(Long id, Integer qntdDenuncia, String comentario, String cidade, String estado, Date dataPost, String cep) {
        this.id = id;
        this.qntdDenuncia = qntdDenuncia;
        this.comentario = comentario;
        this.cidade = cidade;
        this.estado = estado;
        this.dataPost = dataPost;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQntdDenuncia() {
        return qntdDenuncia;
    }

    public void setQntdDenuncia(Integer qntdDenuncia) {
        this.qntdDenuncia = qntdDenuncia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataPost() {
        return dataPost;
    }

    public void setDataPost(Date dataPost) {
        this.dataPost = dataPost;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PostMoradia getPostMoradia() {
        return postMoradia;
    }

    public void setPostMoradia(PostMoradia postMoradia) {
        this.postMoradia = postMoradia;
    }
}