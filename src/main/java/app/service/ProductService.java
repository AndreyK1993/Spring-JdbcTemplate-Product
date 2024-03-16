package app.service;

import app.entity.Product;
import app.exceptions.ProductDataException;
import app.repository.impl.ProductRepository;
import app.utils.Constants;
import app.utils.IdValidator;
import app.utils.PriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ProductService {

    @Autowired
    Product product;
    @Autowired
    ProductRepository repository;

    Map<String, String> errors = new HashMap<>();

    public String create(Product product) {
        validateData(product);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repository.create(product)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String getAll() {
        Optional<List<Product>> optional = repository.fetchAll();
        if (optional.isPresent()) {
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            List<Product> list = optional.get();
            list.forEach((contact) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(contact.toString())
            );
            return stringBuilder.toString();
        } else return Constants.DATA_ABSENT_MSG;
    }

    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        Optional<Product> optional = repository.fetchById(Long.parseLong(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Product product = optional.get();
            return product.toString();
        }
    }

    public String update(Product product) {
        validateData(product);
        validateId(String.valueOf(product.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs",
                        errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repository.update(product)) {
            return Constants.DATA_UPDATE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        product.setId(Long.parseLong(id));
        if (repository.delete(product)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(Product product) {
        if (product.getName().isEmpty())
            errors.put("first name", Constants.INPUT_REQ_MSG);
        if (product.getQuota().isNaN())
            errors.put("last name", Constants.INPUT_REQ_MSG);
        if (PriceValidator.isPriceValid(product.getPrice()))
            errors.put("phone", Constants.PRICE_ERR_MSG);
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}
