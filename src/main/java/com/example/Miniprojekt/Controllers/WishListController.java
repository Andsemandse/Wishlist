package com.example.Miniprojekt.Controllers;


import com.example.Miniprojekt.Model.Users;
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
    String returnValue;
    @GetMapping("")
    public String landingSite() {
        return "landing-site";
    }
    @GetMapping("showusers")
    public String showUsers(Model model){
        model.addAttribute("users",repository.getListOfUsers());
        return "userlist";
    }

    @GetMapping("showlistoflists/{id}")
    public String showListOfLists(Model model, @PathVariable String id){
        model.addAttribute("listoflists",repository.getListOfLists( Integer.parseInt(id)));//Lists with a specific user id
        returnValue = id;
        return "list_of_lists";
    }

    @GetMapping("showwishlist/{id}")
    public String showWishes(Model model, @PathVariable int id){
        model.addAttribute("list",repository.getWishList(id));
        model.addAttribute("return-value",returnValue);
        return "show_list";
    }

    //Write-methods
    @GetMapping("adduser") //Todo finish this method
    public String addUser(Model model){
        model.addAttribute("users",new Users());
        return "add_user";
    }
    @PostMapping("add-user")
    public String addUser(@ModelAttribute("users")Users users){
        repository.addUser(users);
        return "redirect: userlist";
    }

    @GetMapping("/create-wish")
    public String showCreateWishForm(Model model) {
        model.addAttribute("wish", new Wish());
        return "add_wish";
    }


    @PostMapping("/add-wish")
    public String addWish(@ModelAttribute("wish") Wish wish) {
        repository.addWish(wish);
        // Redirect to the wishlist page
        return "redirect: show_list";
    }
    //Return Back

    @GetMapping("/edit-wish")
    public String editWish(Model model) {
        model.addAttribute("wish",new Wish());
        // update the wish table with new values

        return "redirect:/wish-list";
    }

    @PostMapping("/edit-wish")
    public String editWish(@ModelAttribute("wish") Wish wish ){
        repository.editWish(wish);
        return "userlist"; //?
    }

}
