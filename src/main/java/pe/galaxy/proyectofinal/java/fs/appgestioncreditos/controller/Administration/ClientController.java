package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ClientEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IClientService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_CLIENT;

@Slf4j
@RestController
@RequestMapping(API_CLIENT)
public class ClientController extends GenericController {


    private final IClientService clientService;

    public ClientController(final IClientService clientService) {

        this.clientService = clientService;
    }

    @GetMapping("/find-clientes")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<ClientEntity> clients = clientService.findAllStatus();
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

            List<ClientEntity> clients = clientService.findAll();
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

            Optional<ClientEntity> optClient = clientService.findByIdAndStatus(id);
            if (optClient.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optClient.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientEntity clientEntity, BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            clientEntity.setIdClient(id);
            ClientEntity oClientEntity=clientService.update(clientEntity);
            if (isNull(oClientEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oClientEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            ClientEntity clientEntity= new ClientEntity();
            clientEntity.setIdClient(id);
            if (!isNull(clientService.delete(clientEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated ClientEntity clientEntity, BindingResult result) {
        if (result.hasErrors()) {

            return getErrors(result);
        }
        try {
            ClientEntity oClientEntity = clientService.save(clientEntity);
            if (isNull(oClientEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oClientEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
