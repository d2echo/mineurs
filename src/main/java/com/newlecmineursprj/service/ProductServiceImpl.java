package com.newlecmineursprj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public List<ProductView> getList() {
        return repository.findAll(null,null);
    }
    @Override
    public List<ProductView> getList(String searchMethod, String searchKeyword) {
        return repository.findAll(searchMethod,searchKeyword);
    }
    @Override
    public void reg(Product product) {
        repository.reg(product);
    }
    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }
    @Override
    public void edit(Product product) {
        repository.updateProductById(product);
    }

    @Override
    public void deleteAllById(List<Long> deleteId) {
        repository.deleteAll(deleteId);
    }

    @Override
    public String saveProductImg(MultipartFile img, String realPath) {
        String fileName = img.getOriginalFilename();
        if (img != null && !img.isEmpty()) {

            File pathFile = new File(realPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(realPath + File.separator + fileName);
            try {
                img.transferTo(file);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Failed File Upload";
    }
}
