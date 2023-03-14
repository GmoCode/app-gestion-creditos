package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.seguridad.UserEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IVendorService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_CLIENT;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_VENDOR;

@Slf4j
@RestController
@RequestMapping(API_VENDOR)
public class VendorController extends GenericController {


    private final IVendorService vendorService;

    public VendorController(final IVendorService vendorService) {

        this.vendorService = vendorService;
    }

    @GetMapping("/find-vendedores")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<UserEntity> clients = vendorService.findAllStatus();
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clients);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<UserEntity> clients = vendorService.findAll();
            if (clients.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clients);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<UserEntity> optVendor = vendorService.findByIdAndStatus(id);
            if (optVendor.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optVendor.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserEntity userEntity, BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            userEntity.setIdUser(id);
            UserEntity oUserEntity=vendorService.update(userEntity);
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
            if (!isNull(vendorService.delete(userEntity))) {
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
            UserEntity oUserEntity = vendorService.save(userEntity);
            if (isNull(oUserEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oUserEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
