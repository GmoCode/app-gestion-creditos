package pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Administration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.controller.Generic.GenericController;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.entity.administration.CategoryEntity;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.service.Administration.ICategoryService;
import pe.galaxy.proyectofinal.java.fs.appgestioncreditos.util.WebUtil;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static pe.galaxy.proyectofinal.java.fs.appgestioncreditos.commons.GlobalConstant.API_CATEGORY;

@Slf4j
@RestController
@RequestMapping(API_CATEGORY)
public class CategoryController extends GenericController {


    private final ICategoryService CategoryService;

    public CategoryController(final ICategoryService CategoryService) {

        this.CategoryService = CategoryService;
    }

    @GetMapping("/find-categorias")
    public ResponseEntity<?> findAllStatus() {
        try {

            List<CategoryEntity> categories = CategoryService.findAllStatus();
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {

            List<CategoryEntity> categories = CategoryService.findAll();
            if (categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByIdAndStatus(@PathVariable Long id) {
        try {

            Optional<CategoryEntity> optCategories = CategoryService.findByIdAndStatus(id);
            if (optCategories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(optCategories.get());
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryEntity CategoryEntity,BindingResult result){
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            CategoryEntity.setIdCategory(id);
            CategoryEntity oCategoryEntity=CategoryService.update(CategoryEntity);
            if (isNull(oCategoryEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(oCategoryEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            CategoryEntity CategoryEntity = new CategoryEntity();
            CategoryEntity.setIdCategory(id);
            if (!isNull(CategoryService.delete(CategoryEntity))) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encuentra el ID: " + id);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  @Validated CategoryEntity CategoryEntity, BindingResult result) {
        if (result.hasErrors()) {

            return WebUtil.getErrors(result);
        }
        try {
            CategoryEntity oCategoryEntity = CategoryService.save(CategoryEntity);
            if (isNull(oCategoryEntity)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(oCategoryEntity);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
