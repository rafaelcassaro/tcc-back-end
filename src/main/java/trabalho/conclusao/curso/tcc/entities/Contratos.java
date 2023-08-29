package trabalho.conclusao.curso.tcc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import trabalho.conclusao.curso.tcc.entities.enums.PlanoStatus;

import java.util.Date;

@Entity
@Table(name = "contratos")
public class Contratos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date inicioContrato;
    private Date terminoContrato;
    private Integer planoStatus;

    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "plano_id")
    private Planos plano;

    public Contratos(){}

    public Contratos(Long id, Date inicioContrato, Date terminoContrato, PlanoStatus planoStatus) {
        this.id = id;
        this.inicioContrato = inicioContrato;
        this.terminoContrato = terminoContrato;
        setPlanoStatus(planoStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Date inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Date getTerminoContrato() {
        return terminoContrato;
    }

    public void setTerminoContrato(Date terminoContrato) {
        this.terminoContrato = terminoContrato;
    }

    public PlanoStatus getPlanoStatus() {
        return PlanoStatus.valueOf(planoStatus);
    }

    public void setPlanoStatus(PlanoStatus planoStatus) {
        if(planoStatus != null) {
            this.planoStatus = planoStatus.getCode();
        }
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Planos getPlano() {
        return plano;
    }

    public void setPlano(Planos plano) {
        this.plano = plano;
    }
}
