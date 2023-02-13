package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.ProductEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.IProductService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_PRODUCT;

@Slf4j
@RestController
@RequestMapping(API_PRODUCT)
public class ProductController extends GenericController {


    private final IProductService productService;

    public ProductController(final IProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/find-productos")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<ProductEntity> products = productService.findAllStatus();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<ProductEntity> products = productService.findAll();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<ProductEntity> optProduct = productService.findByIdAndStatus(id);
            if (optProduct.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optProduct.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductEntity productEntity,BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            productEntity.setIdProduct(id);
            ProductEntity oProductEntity=productService.update(productEntity);
            if (isNull(oProductEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oProductEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setIdProduct(id);
            if (!isNull(productService.delete(productEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated ProductEntity productEntity, BindingResult result) {
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            ProductEntity oProductEntity = productService.save(productEntity);
            if (isNull(oProductEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oProductEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
