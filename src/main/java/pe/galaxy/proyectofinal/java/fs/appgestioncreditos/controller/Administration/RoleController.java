package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.RoleEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IRoleService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.*;

@Slf4j
@RestController
@RequestMapping(API_ROL)
public class RoleController extends GenericController {


    private final IRoleService roleService;

    public RoleController(final IRoleService roleService) {

        this.roleService = roleService;
    }

    @GetMapping("/find-roles")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<RoleEntity> roles = roleService.findAllStatus();
            if (roles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(roles);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<RoleEntity> roles = roleService.findAll();
            if (roles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(roles);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<RoleEntity> optRole = roleService.findByIdAndStatus(id);
            if (optRole.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optRole.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoleEntity roleEntity, BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            roleEntity.setIdRole(id);
            RoleEntity oRoleEntity=roleService.update(roleEntity);
            if (isNull(oRoleEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oRoleEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            RoleEntity roleEntity= new RoleEntity();
            roleEntity.setIdRole(id);
            if (!isNull(roleService.delete(roleEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated RoleEntity roleEntity, BindingResult result) {
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            RoleEntity oRoleEntity = roleService.save(roleEntity);
            if (isNull(oRoleEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oRoleEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
