package com.example.todoappspring;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
public class TodoAppController {

@Autowired
ItemRepository itemRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("items",itemRepository.findAll());
        return "index";
    }
    @GetMapping("/{item}")
    public String oneItem(@PathVariable Long item, Model model){
        Item itemFromDb=itemRepository.getOne(item);
        model.addAttribute("item",itemFromDb);
        return "single";
    }
    @PostMapping("/")
    public String postItem(String name, String description){
        itemRepository.save(new Item(name,description,0));
        return "redirect:/";
    }
}