package br.com.gabrielmontoni.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel modeloUsuario){
        var user = this.userRepository.findByUsername(modeloUsuario.getUsername());

        if(user != null){
            System.out.println("Usuário já existe! ");
            //Mensagem de erro e status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var senhaCriptografada = BCrypt.withDefaults().hashToString(12,
         modeloUsuario.getPassword().toCharArray());

         modeloUsuario.setPassword(senhaCriptografada);

        var userCreated = this.userRepository.save(modeloUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
