package com.example.Miniprojekt.Controllers;


import com.example.Miniprojekt.Repository.InterfaceRepository;
import com.example.Miniprojekt.Repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("wishlist")
@Controller
public class WishListController {

    InterfaceRepository repository = new WishListRepository();

    @GetMapping("showlist/{id}")
    public String showWishes(Model model, @PathVariable int id){
        model.addAttribute("list",repository.getWishList(id));

        return "show_list";
    }
}
