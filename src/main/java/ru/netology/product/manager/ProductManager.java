package ru.netology.product.manager;

import ru.netology.product.data.Product;
import ru.netology.product.repository.Repository;

public class ProductManager {
    private Repository repository;

    public ProductManager(Repository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.add(product);
    }

    public Product[] findAll() {
        return repository.findAll();
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
