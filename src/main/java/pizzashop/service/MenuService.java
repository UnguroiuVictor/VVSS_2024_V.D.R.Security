package pizzashop.service;
import pizzashop.model.MenuDataModel;
import pizzashop.repository.MenuRepository;

import java.util.List;

public class MenuService {

    public MenuService(MenuRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    private MenuRepository menuRepo;

    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

}