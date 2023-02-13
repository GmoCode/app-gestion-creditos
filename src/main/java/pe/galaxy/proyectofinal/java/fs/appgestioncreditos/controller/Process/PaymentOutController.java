package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;

import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.process.PaymentOutEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Process.IPaymentOutService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_PAYMENT;

@Slf4j
@RestController
@RequestMapping(API_PAYMENT)
public class PaymentOutController extends GenericController {


    private final IPaymentOutService paymentOutService;

    public PaymentOutController(final IPaymentOutService paymentOutService) {

        this.paymentOutService = paymentOutService;
    }

    @GetMapping("/find-desembolsos")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<PaymentOutEntity> paymentOuts = paymentOutService.findAllStatus();
            if (paymentOuts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(paymentOuts);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<PaymentOutEntity> paymentOuts = paymentOutService.findAll();
            if (paymentOuts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(paymentOuts);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<PaymentOutEntity> optPaymentOut = paymentOutService.findByIdAndStatus(id);
            if (optPaymentOut.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optPaymentOut.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PaymentOutEntity paymentOutEntity,BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            paymentOutEntity.setIdPaymentOut(id);
            PaymentOutEntity oPaymentOutEntity=paymentOutService.update(paymentOutEntity);
            if (isNull(oPaymentOutEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oPaymentOutEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            PaymentOutEntity paymentOutEntity= new PaymentOutEntity();
            paymentOutEntity.setIdPaymentOut(id);
            if (!isNull(paymentOutService.delete(paymentOutEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated PaymentOutEntity paymentOutEntity, BindingResult result) {
        if (result.hasErrors()) {

            return getErrors(result);
        }
        try {
            PaymentOutEntity oPaymentOutEntity = paymentOutService.save(paymentOutEntity);
            if (isNull(oPaymentOutEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oPaymentOutEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
