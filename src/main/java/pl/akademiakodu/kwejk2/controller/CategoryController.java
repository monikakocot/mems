package pl.akademiakodu.kwejk2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.kwejk2.model.Category;
import pl.akademiakodu.kwejk2.repository.CategoryRepository;


@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/categories/new")
    public String add(){
        return "categories/add";
    }

    @PostMapping("/categories")
    public String getCategories(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String index(ModelMap modelMap){
        modelMap.put("categories",categoryRepository.findAll());
        return "categories/index";
    }

}
