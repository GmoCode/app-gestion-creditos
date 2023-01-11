package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IUserService;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_USER;

@Slf4j
@RestController
@RequestMapping(API_USER)
public class UserController extends GenericController {


    private final IUserService userService;

    public UserController(final IUserService userService) {

        this.userService = userService;
    }

    @GetMapping("/find-usuarios")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<UserEntity> users = userService.findAllStatus();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<UserEntity> users = userService.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<UserEntity> optUser = userService.findByIdAndStatus(id);
            if (optUser.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optUser.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserEntity userEntity){
        try {
            userEntity.setIdUser(id);
            UserEntity oUserEntity=userService.update(userEntity);
            if (isNull(oUserEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oUserEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            UserEntity userEntity= new UserEntity();
            userEntity.setIdUser(id);
            if (!isNull(userService.delete(userEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated UserEntity userEntity, BindingResult result) {
        if (result.hasErrors()) {

            return getErrors(result);
        }
        try {
            UserEntity oUserEntity = userService.save(userEntity);
            if (isNull(oUserEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oUserEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
