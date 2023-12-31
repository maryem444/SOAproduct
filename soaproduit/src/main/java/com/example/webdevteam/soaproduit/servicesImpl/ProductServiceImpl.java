package com.example.webdevteam.soaproduit.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webdevteam.soaproduit.models.CategorieModel;
import com.example.webdevteam.soaproduit.models.ProductModel;
import com.example.webdevteam.soaproduit.repositories.CategorieRepository;
import com.example.webdevteam.soaproduit.repositories.ProductRepository;
import com.example.webdevteam.soaproduit.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired   // annotation qui permet de créer une instance et l'injecter dans cette classe  
	//   signifie que vous pouvez utiliser mRepo pour interagir avec la base de données sans avoir à créer manuellement une instance de MatchRepository
	private ProductRepository pRepo;
	@Autowired   // annotation qui permet de créer une instance et l'injecter dans cette classe  
	//   signifie que vous pouvez utiliser mRepo pour interagir avec la base de données sans avoir à créer manuellement une instance de MatchRepository
	private CategorieRepository cRepo;
	
	
	@Override
	public List<ProductModel> getAllProducts() {
		return pRepo.findAll();
	}

	@Override
	public ProductModel getProductById(Long id) {
		// TODO Auto-generated method stub
		Optional<ProductModel> p = pRepo.findById(id);
		return p.isPresent() ? p.get() : null;     //? si   : sinon
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		pRepo.deleteById(id);
		
	}

	@Override
	public ProductModel addProduct(ProductModel p) {
	    // Check if the category in ProductModel is not null
	    if (p.getCategorie() != null) {
	        // Retrieve the category by ID
	        Optional<CategorieModel> categoryOptional = this.cRepo.findById(p.getCategorie().getCategorieId());

	        if (categoryOptional.isPresent()) {
	            // If the category exists, set it in the ProductModel
	            CategorieModel category = categoryOptional.get();
	            p.setCategorie(category);

	            // Add the product to the category's list of products
	            category.addProduct(p);

	            // Save the category (this will cascade to save the product as well)
	            this.cRepo.save(category);

	            return pRepo.save(p);
	        } else {
	            // Handle the case when the category with the given ID is not found
	            System.err.println("Category not found for ID: " + p.getCategorie().getCategorieId());
	        }
	    } else {
	        // Handle the case when the category in ProductModel is null
	        System.err.println("Product category cannot be null");
	    }
	    return null;
	}



	@Override
	public ProductModel updateProduct(ProductModel p) {
		// TODO Auto-generated method stub
		return pRepo.save(p);
	}


}
