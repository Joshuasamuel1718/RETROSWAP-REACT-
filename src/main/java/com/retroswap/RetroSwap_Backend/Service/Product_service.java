package com.retroswap.RetroSwap_Backend.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.retroswap.RetroSwap_Backend.Model.Product;
import com.retroswap.RetroSwap_Backend.Model.ProductRequest;
import com.retroswap.RetroSwap_Backend.Model.User;
import com.retroswap.RetroSwap_Backend.Repository.Product_repo;
import com.retroswap.RetroSwap_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class Product_service {
    @Autowired
    Product_repo productRepo;
    @Autowired
    UserRepo userRepo;
    public List<Product> getproducts()
    {
        return  productRepo.findAll();
    }
    public Product getProductById(long id)
    {

        return productRepo.findById(id).orElse(null);
    }
    @Autowired
    private Cloudinary cloudinary;
    public void addProduct(ProductRequest request, MultipartFile file) throws IOException {


        User user = userRepo.findByEmail(getEmail());
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
        );
        String imageUrl = (String) uploadResult.get("secure_url");
        Product product = new Product();

        product.setUser(user);
        product.setDetails(request.getDetails());
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setImage(imageUrl);
        product.setColor(request.getColor());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());

        productRepo.save(product);

    }

    private String getEmail()
    {

        return  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    public void deleteProduct(long id) {
        productRepo.deleteById(id);
    }

    public Product updateProduct(long id,Product product) {
        Product product1=productRepo.findById(id).orElse(null);
        if(product1==null) {
            return null;
        }
        product.setId(id);
        productRepo.save(product);
        return product1;
    }
}
