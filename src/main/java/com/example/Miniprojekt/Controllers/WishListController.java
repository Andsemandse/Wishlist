package com.example.Miniprojekt.Controllers;


import com.example.Miniprojekt.Model.Wish;
import com.example.Miniprojekt.Repository.InterfaceRepository;
import com.example.Miniprojekt.Repository.WishListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("wishlist")
@Controller
public class WishListController {

    InterfaceRepository repository = new WishListRepository();
    @GetMapping("showusers")
    public String showUsers(Model model){
        model.addAttribute("users",repository.getListOfUsers());
        return "userlist";
    }

    @GetMapping("showlists/{id}")
    public String showListOfLists(Model model, @PathVariable int id){
        model.addAttribute("listoflists",repository.getListOfLists(id));//Lists with a specific user id
        return "list_of_lists";
    }

    @GetMapping("showwishlist/{id}")
    public String showWishes(Model model, @PathVariable int id){
        model.addAttribute("list",repository.getWishList(id));

        return "show_list";
    }

    //Write-methods
    @GetMapping("adduser") //Todo finish this method
    public String addUser(Model model){

        return "add_user";
    }

    @GetMapping("/create-wish")
    public String showCreateWishForm(Model model) {
        model.addAttribute("wish", new Wish());
        return "create-wish-form";
    }

    @PostMapping("/add-wish")
    public String addWish(@ModelAttribute("wish") Wish wish) {


        // Redirect to the wishlist page
        return "redirect:/wishlist";
    }





}
