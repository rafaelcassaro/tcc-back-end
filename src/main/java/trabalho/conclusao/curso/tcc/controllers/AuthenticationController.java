package trabalho.conclusao.curso.tcc.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trabalho.conclusao.curso.tcc.entities.dtos.AuthenticationDTO;
import trabalho.conclusao.curso.tcc.entities.dtos.LoginResponseDTO;
import trabalho.conclusao.curso.tcc.entities.dtos.RegisterDTO;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.repositories.UsuarioRepository;
import trabalho.conclusao.curso.tcc.security.TokenService;
import trabalho.conclusao.curso.tcc.services.UsuarioService;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        var userId = ((Usuario) auth.getPrincipal()).getId();



        return ResponseEntity.ok(new LoginResponseDTO(token, userId));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody  RegisterDTO data){

        if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = new Usuario(null,data.nome(), data.email(), data.celular(), encryptedPassword, data.link1(), data.link2(), data.link3());

        this.repository.save(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
        //return ResponseEntity.ok().build();
    }



}
