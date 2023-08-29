package trabalho.conclusao.curso.tcc.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "fotos")
public class Fotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String test;

    public Fotos(Long id, String test) {
        this.id = id;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
