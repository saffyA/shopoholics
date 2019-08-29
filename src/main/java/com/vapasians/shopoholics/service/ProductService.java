package com.vapasians.shopoholics.service;

import com.vapasians.shopoholics.model.Category;
import com.vapasians.shopoholics.model.Product;
import com.vapasians.shopoholics.repository.CategoryDao;
import com.vapasians.shopoholics.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private CategoryDao categoryDao;


    @Autowired
    private ProductDao productDao;



    public HashMap<String , Set<Product>> findAll()
    {
        HashMap<String, Set<Product> > map = new HashMap<>();
        List<Category> categories = categoryDao.findAllByOrderByCategoryIdAsc();
       for(Category category : categories)
       {

           Set<Product> productSet = category.getProductSet();
           map.put( category.getCategoryName(), productSet);

       }
       return map;
    }
    public void saveProduct(Product product) {
        product.setAvailable( true );
        productDao.save( product );

    }

    public void markProductUnavailable(int pid) {
        Optional<Product> productOrNull = productDao.findById(pid);
        System.out.println(productOrNull.get());
        if(productOrNull.isPresent())
        {
            productOrNull.get().setAvailable(false);
            productDao.save(productOrNull.get());
        }
    }

    public float getPriceOfProducts(List<Product> productsInUserCart) {
        float totalPrice = productsInUserCart.stream().map(product -> product.getPrice()).reduce((float)0,(a,b) -> a+b);
        return totalPrice;
    }
}
