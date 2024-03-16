package app.controller;

import app.entity.Product;
import app.service.ProductService;
import app.utils.AppStarter;
import app.utils.Constants;
import app.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductController {

    @Autowired
    AppView menuView;
    @Autowired
    ProductCreateView createView;
    @Autowired
    ProductReadView readView;
    @Autowired
    ProductReadByIdView readByIdView;
    @Autowired
    ProductUpdateView updateView;
    @Autowired
    ProductDeleteView deleteView;
    @Autowired
    ProductService service;


    public void getOption() {
        int option = Integer.parseInt(menuView.getOption());
        switch (option) {
            case 1 -> create();
            case 2 -> getAll();
            case 3 -> getById();
            case 4 -> update();
            case 5 -> delete();
            case 0 -> menuView.getOutput(Constants.APP_CLOSE_MSG);
        }
    }

    public void create() {
        String[] data = createView.getData();
        Product product = new Product(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]));
        createView.getOutput(service.create(product));
        AppStarter.startApp();
    }

    public void getAll() {
        readView.getOutput(service.getAll());
        AppStarter.startApp();
    }

    public void getById() {
        readByIdView.getOutput(service
                .getById(readByIdView.getData()));
        AppStarter.startApp();
    }

    public void update() {
        Map<String, String> data = updateView.getData();
        Product product = new Product(Long.parseLong(data.get("id")), data.get("name"),
                Double.parseDouble(data.get("quota")), Double.parseDouble(data.get("price")));
        updateView.getOutput(service.update(product));
        AppStarter.startApp();
    }

    public void delete() {
        deleteView.getOutput(service
                .delete(deleteView.getData()));
        AppStarter.startApp();
    }
}
