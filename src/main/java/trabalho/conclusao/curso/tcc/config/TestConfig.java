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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        String u1EncryptedPassword = new BCryptPasswordEncoder().encode("a");
        String u2EncryptedPassword = new BCryptPasswordEncoder().encode("b");
        String comentario = "Procuro alguem para divir um ap próximo do lugar x, com valor de xxxx, que aceita pets xxxxx";


        Usuario u1 = new Usuario(null, "Rafael Cassaro Costa", "a", "16992129161", u1EncryptedPassword, "Cristiano", "Cristiano", "Cristiano", "imgperfil.jpg", "src\\main\\resources\\static\\images\\image-perfil\\imgperfil.jpg");
        Usuario u2 = new Usuario(null, "Merlo", "b", "10000000", u2EncryptedPassword, "neymarjr", "neymarjr", "neymarjr","imgperfil2.png", "src\\main\\resources\\static\\images\\image-perfil\\imgperfil2.png");

        Post post1 = new Post(null, 10, comentario, "Pontal", "SP", data, "89010025");
        Post post2 = new Post(null, 15, comentario, "Sertaozinho", "SP", data, "89010025");
        Post post3 = new Post(null, 15, comentario, "Ribeirão Preto", "SP", data, "89010025");
        Post post4 = new Post(null, 15, comentario, "Santa Catarina", "SC", data, "89010025");

        Post post5 = new Post(null, 10, comentario, "Pontal", "SP", data, "89010025");
        Post post6 = new Post(null, 15, comentario, "Sertaozinho", "SP", data, "89010025");
        Post post7 = new Post(null, 15, comentario, "Ribeirão Preto", "SP", data, "89010025");
        Post post8 = new Post(null, 15, comentario, "Santa Catarina", "SC", data, "89010025");

        PostMoradia pMoradia1 = new PostMoradia(null, true, "U1 Rua Genoveva Onófre Barban", 679, 550.00);
        PostMoradia pMoradia2 = new PostMoradia(null, false, "U1 Rua Genoveva Onófre Barban", 2455, 800.00);
        PostMoradia pMoradia3 = new PostMoradia(null, true, "U2 Rua Genoveva Onófre Barban", 213, 599.21);
        PostMoradia pMoradia4 = new PostMoradia(null, false, "U2 Rua Genoveva Onófre Barban", 23, 2299.21);
        Planos plano1 = new Planos(null, "Standard", "descricao standard");
        Planos plano2 = new Planos(null, "Black", "descricao Black");
        Planos plano3 = new Planos(null, "Premium", "descricao Premium");
        Contratos contrato = new Contratos(null, data, data, PlanoStatus.ATIVO);
        Contratos contrato2 = new Contratos(null, data, data, PlanoStatus.FINALIZADO);
        Feedback feedback1 = new Feedback(null, "comentario");
        DetalhesMoradia detalhesMoradia1 = new DetalhesMoradia(null, true, true, 12, true, 1);
        DetalhesMoradia detalhesMoradia2 = new DetalhesMoradia(null, false, false, 2, false, 2);
        DetalhesMoradia detalhesMoradia3 = new DetalhesMoradia(null, false, true, 4, true, 3);
        DetalhesMoradia detalhesMoradia4 = new DetalhesMoradia(null, true, false, 5, true, 1);

       // Fotos foto1 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\Imagem1.jpg", "Imagem1.jpg");
       // Fotos foto2 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\1000000034.jpg", "1000000034.jpg");

        Fotos foto1 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa1.jpg", "casa1.jpg");
        Fotos foto2 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa2.jpg", "casa2.jpg");
        Fotos foto3 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa3.jpg", "casa3.jpg");
        Fotos foto4 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa4.jpg", "casa4.jpg");
        Fotos foto5 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa5.jpg", "casa5.jpg");
        Fotos foto6 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa6.jpg", "casa6.jpg");
        Fotos foto7 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa7.jpg", "casa7.jpg");
        Fotos foto8 = new Fotos(null,"src\\main\\resources\\static\\images\\image-moradias\\casa8.jpg", "casa8.jpg");


        pMoradia1.addFoto(foto1);
        pMoradia1.addFoto(foto2);
        pMoradia2.addFoto(foto3);
        pMoradia2.addFoto(foto4);
        pMoradia3.addFoto(foto5);
        pMoradia3.addFoto(foto6);
        pMoradia4.addFoto(foto7);
        pMoradia4.addFoto(foto8);

        //contratoRep.save(contrato);


        //userRep.save(u1);


        //u1.addPlano(plano1);


        // ADICIONAR POSTMORADIA AO POST
        post1.setPostMoradia(pMoradia1);
        post2.setPostMoradia(pMoradia2);
        post3.setPostMoradia(pMoradia3);
        post4.setPostMoradia(pMoradia4);
        u1.addFeedback(feedback1);
        u1.addPost(post1);
        u1.addPost(post2);
        u2.addPost(post3);
        u2.addPost(post4);
        u1.addPost(post5);
        u1.addPost(post6);
        u2.addPost(post7);
        u2.addPost(post8);
        pMoradia1.setDetalhes(detalhesMoradia1);
        pMoradia2.setDetalhes(detalhesMoradia3);
        pMoradia3.setDetalhes(detalhesMoradia2);
        pMoradia4.setDetalhes(detalhesMoradia4);

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
        postRep.save(post3);
        postRep.save(post4);
        postMoradiaRep.save(pMoradia1);
        postMoradiaRep.save(pMoradia2);
        postMoradiaRep.save(pMoradia3);
        postMoradiaRep.save(pMoradia4);

        detalhesMoradiaRep.save(detalhesMoradia1);
        detalhesMoradiaRep.save(detalhesMoradia2);
        detalhesMoradiaRep.save(detalhesMoradia3);
        detalhesMoradiaRep.save(detalhesMoradia4);

        planosRep.saveAll(Arrays.asList(plano1, plano2, plano3));


        //Contratos test = contratoRep.getById(1L);
        //test.setInicioContrato(contrato.getInicioContrato());
        //test.setPlanoStatus(contrato.getPlanoStatus());
        //contratoRep.save(test);

        contratoRep.save(contrato);
        contratoRep.save(contrato2);


    }


}
