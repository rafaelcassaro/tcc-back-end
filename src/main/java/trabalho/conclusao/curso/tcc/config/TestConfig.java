package trabalho.conclusao.curso.tcc.config;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import trabalho.conclusao.curso.tcc.entities.*;
import trabalho.conclusao.curso.tcc.entities.enums.PlanoStatus;
import trabalho.conclusao.curso.tcc.repositories.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository userRep;

    @Autowired
    private PostRepository postRep;

    @Autowired
    private PostMoradiaRepository postMoradiaRep;

    @Autowired
    private DetalhesMoradiaRepository detalhesMoradiaRep;

    @Autowired
    private PlanosRepository planosRep;

    @Autowired
    private ContratosRepository contratoRep;




    //Long id, String nome, String email, Integer celular, String senha, String link1, String link2, String link3) {

        @Override
    public void run(String... args) throws Exception {
            Date data = new Date();


            Usuario u1 = new Usuario(null, "Rafael", "rafael@gmail.com", 92929292, "Senha", "Link1", "Link2", "Link3");
            Usuario u2 = new Usuario(null, "Merlo", "Merlo@gmail.com", 10000000, "password", "www.link1.com.br", "www.link2.com.br", "www.link3.com.br");
            Post post1 = new Post(null, 10, "Coment", "Pontal", "SP",data,14180000);
            Post post2 = new Post(null, 15, "cmoentario", "sertaozinho", "SP",data,564251748);
            PostMoradia pMoradia1 = new PostMoradia(null, "Casa", "endereco", 000,299.21,"proximo a casa x");
            Planos plano1 = new Planos(null, "Standard", "descricao standard");
            Planos plano2 = new Planos(null,"Black", "descricao Black");
            Planos plano3 = new Planos(null,"Premium", "descricao Premium");
            Contratos contrato = new Contratos(null, data,data, PlanoStatus.ATIVO);
            Contratos contrato2 = new Contratos(null, data,data, PlanoStatus.FINALIZADO);
            Feedback feedback1 = new Feedback(null, "comentario");
            DetalhesMoradia detalhesMoradia1 = new DetalhesMoradia(null, "Compartilhado", 2, 5, 2);

            //contratoRep.save(contrato);


            //userRep.save(u1);


            //u1.addPlano(plano1);



            // ADICIONAR POSTMORADIA AO POST
            post1.setPostMoradia(pMoradia1);
            u1.addFeedback(feedback1);
            u1.addPost(post1);
            pMoradia1.setDetalhes(detalhesMoradia1);

            //contrato.setPlano(plano1);
            //contrato.setUsuario(u1);
            //  ADD CONTRATOS
            u1.setContratos(contrato);
            plano1.addContrato(contrato);
            u1.setContratos(contrato2);
            plano3.addContrato(contrato2);



            userRep.save(u1);
            userRep.save(u2);
            postRep.save(post1);
            postRep.save(post2);
            postMoradiaRep.save(pMoradia1);
            detalhesMoradiaRep.save(detalhesMoradia1);
            planosRep.saveAll(Arrays.asList(plano1,plano2,plano3));


            //Contratos test = contratoRep.getById(1L);
            //test.setInicioContrato(contrato.getInicioContrato());
            //test.setPlanoStatus(contrato.getPlanoStatus());
            //contratoRep.save(test);

            contratoRep.save(contrato);
            contratoRep.save(contrato2);






    }


}
