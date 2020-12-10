package com.pbz2.controller;

import com.pbz2.entity.Inventory;
import com.pbz2.repos.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryRepo inventoryRepo;

    @GetMapping("/show")
    public String showInventory(Model model){
        Iterable<Inventory> inventories = inventoryRepo.findAll();
        model.addAttribute("inventories", inventories);
        return "showInventories";
    }

    @GetMapping("/{id}")
    public String indexInventory(@PathVariable("id") Long id, Model model){
        Inventory inventory = inventoryRepo.findById(id).orElse(new Inventory());
        model.addAttribute("inventory", inventory);
        return "inventoryByIndex";
    }


    @GetMapping("/new")
    public String newInventory(@ModelAttribute("inventory") Inventory inventory){
        return "newInventory";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("inventory") Inventory inventory){
        inventoryRepo.save(inventory);
        return "redirect:/inventory/show";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        inventoryRepo.deleteById(id);
        return "redirect:/inventory/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Inventory inventory = inventoryRepo.findById(id).orElse(new Inventory());
        System.out.println("get" + inventory.getId());
        model.addAttribute("inventory", inventory);
        return "editInventory";
    }

    @PostMapping("/{id}/update")
    public String update(@RequestParam String name,
            @RequestParam Integer amount,
            @RequestParam Long id) {
        System.out.println("post" + " " + name + " " + id);
        Inventory updateInventory = inventoryRepo.findById(id).orElse(new Inventory());
        updateInventory.setName(name);
        updateInventory.setAmount(amount);
        inventoryRepo.save(updateInventory);
        return "redirect:/inventory/show";
    }
}
