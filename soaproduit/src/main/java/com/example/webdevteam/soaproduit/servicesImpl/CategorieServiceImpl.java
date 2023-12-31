package com.example.webdevteam.soaproduit.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webdevteam.soaproduit.models.CategorieModel;
import com.example.webdevteam.soaproduit.models.ProductModel;
import com.example.webdevteam.soaproduit.repositories.CategorieRepository;
import com.example.webdevteam.soaproduit.repositories.ProductRepository;
import com.example.webdevteam.soaproduit.services.CategorieService;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository cRepo;

    @Autowired
    private ProductRepository pRepo;

    @Override
    public List<CategorieModel> getAllCategories() {
        return cRepo.findAll();
    }

    @Override
    public CategorieModel getCategoryById(Long categorieId) {
        Optional<CategorieModel> c = cRepo.findById(categorieId);
        return c.isPresent() ? c.get() : null;
    }

    @Override
    public CategorieModel addCategory(CategorieModel c) {
        return cRepo.save(c);
    }

    @Override
    public void deleteCategoryById(Long categorieId) {
        cRepo.deleteById(categorieId);
    }

    @Override
    public List<ProductModel> getCategoryProducts(Long categorieId) {
        return cRepo.findById(categorieId).map(CategorieModel::getProducts).orElse(null);
    }
}
