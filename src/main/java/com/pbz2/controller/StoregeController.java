package com.pbz2.controller;

import com.pbz2.entity.Inventory;
import com.pbz2.entity.Storege;
import com.pbz2.repos.InventoryRepo;
import com.pbz2.repos.StoregeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/storage")
public class StoregeController {
    @Autowired
    StoregeRepo storegeRepo;

    @Autowired
    InventoryRepo inventoryRepo;


    @GetMapping("/show")
    public String showStorage(Model model){
        Iterable<Storege> storeges = storegeRepo.findAll();
        model.addAttribute("storages", storeges);
        return "showStoreges";
    }

    @GetMapping("/{id}")
    public String indexStorage(@PathVariable("id") Long id, Model model){
        Storege storege = storegeRepo.findById(id).orElse(new Storege());
        model.addAttribute("storage", storege);
        return "storageByIndex";
    }


    @GetMapping("/new")
    public String newStorage(@ModelAttribute("storage") Storege storege){
        return "newStorage";
    }

    @PostMapping("/create")
    public String createStorage(@ModelAttribute("storege") Storege storege){
        storegeRepo.save(storege);
        return "redirect:/storage/show";
    }

    @PostMapping("/{id}")
    public String deleteStorage(@PathVariable("id") Long id){
        storegeRepo.deleteById(id);
        return "redirect:/storage/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Storege storege = storegeRepo.findById(id).orElse(new Storege());
        model.addAttribute("storage", storege);
        return "editStorage";
    }

    @PostMapping("/{id}/update")
    public String update(@RequestParam String name,
                         @RequestParam String number,
                         @RequestParam Long id) {

        Storege storege = storegeRepo.findById(id).orElse(new Storege());
        storege.setName(name);
        storege.setNumber(number);
        storegeRepo.save(storege);
        return "redirect:/storage/show";
    }
}
