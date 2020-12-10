package com.pbz2.controller;

import com.pbz2.entity.ExpenceBill;
import com.pbz2.entity.Inventory;
import com.pbz2.entity.Storege;
import com.pbz2.repos.ExpenceBillRepo;
import com.pbz2.repos.InventoryRepo;
import com.pbz2.repos.StoregeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/expencebill")
public class ExpenceBillController {

    @Autowired
    ExpenceBillRepo expenceBillRepo;
    @Autowired
    StoregeRepo storegeRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    @GetMapping("/show")
    public String showBill(Model model) {
        Iterable<ExpenceBill> expenceBills = expenceBillRepo.findAll();
        model.addAttribute("ebills", expenceBills);
        return "showExBills";
    }

    @GetMapping("/{id}")
    public String indexBill(@PathVariable("id") Long id, Model model) {
        ExpenceBill expenceBill = expenceBillRepo.findById(id).orElse(new ExpenceBill());
        model.addAttribute("bill", expenceBill);
        return "exBillByIndex";
    }


    @GetMapping("/new")
    public String newBill(@ModelAttribute("bill") ExpenceBill expenceBill) {
        return "newExBill";
    }

    @PostMapping("/create")
    public String createStorage(@RequestParam String date,
                                @RequestParam Long id,
                                @RequestParam String idInv,
                                @RequestParam Integer amountInv) {
        ExpenceBill expenceBill = new ExpenceBill();
        expenceBill.setDate(date);

        Storege storege = storegeRepo.findById(id).orElse(new Storege());

        expenceBill.setStorege(storege);

        System.out.println("!!!!!!!" + idInv);
        Inventory inventory = new Inventory();
        inventory.setName(idInv);
        inventory.setAmount(amountInv);
        inventory.setStorege(storege);
        inventoryRepo.save(inventory);

        expenceBill.setInventories(inventory);
        expenceBillRepo.save(expenceBill);

        return "redirect:/expencebill/show";
    }

    @PostMapping("/{id}")
    public String deleteStorage(@PathVariable("id") Long id){
        expenceBillRepo.deleteById(id);
        return "redirect:/expencebill/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        ExpenceBill expenceBill = expenceBillRepo.findById(id).orElse(new ExpenceBill());
        model.addAttribute("bill", expenceBill);
        return "editExBill";
    }

    @PostMapping("/{id}/update")
    public String update(@RequestParam String date,
                         @RequestParam Long id,
                         @RequestParam String idInv,
                         @RequestParam Integer amountInv,
                         @RequestParam Long stId) {

        ExpenceBill expenceBill = expenceBillRepo.findById(id).orElse(new ExpenceBill());
        expenceBill.setDate(date);

        Storege storege = storegeRepo.findById(id).orElse(new Storege());

        expenceBill.setStorege(storege);

        System.out.println("!!!!!!!" + idInv);
        Inventory inventory = new Inventory();
        inventory.setName(idInv);
        inventory.setAmount(amountInv);
        inventoryRepo.save(inventory);

        expenceBill.setInventories(inventory);
        expenceBillRepo.save(expenceBill);
        return "redirect:/expencebill/show";
    }
}
