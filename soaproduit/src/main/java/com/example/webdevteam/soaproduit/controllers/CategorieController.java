package com.example.webdevteam.soaproduit.controllers;

import com.example.webdevteam.soaproduit.models.CategorieModel;
import com.example.webdevteam.soaproduit.models.ProductModel;
import com.example.webdevteam.soaproduit.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")

public class CategorieController {

    @Autowired
    private CategorieService cService;

    @GetMapping
    public ResponseEntity<List<CategorieModel>> getAllCategories() {
        // Votre logique pour obtenir les catégories
        List<CategorieModel> categories = cService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
   

    @GetMapping("/{id}")
    public CategorieModel getCategoryById(@PathVariable Long id) {
        return cService.getCategoryById(id);
    }

    @PostMapping
    public CategorieModel addCategory(@RequestBody CategorieModel categorie) {
        return cService.addCategory(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        cService.deleteCategoryById(id);
    }

    @GetMapping("/{id}/products")
    public List<ProductModel> getCategoryProducts(@PathVariable Long id) {
        return cService.getCategoryProducts(id);
    }
    
    @RequestMapping(value = "/api/categories")
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }


}
