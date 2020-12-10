package com.pbz2.controller;


import com.pbz2.entity.ExpenceBill;
import com.pbz2.entity.IncommingBill;
import com.pbz2.entity.Inventory;
import com.pbz2.entity.Storege;
import com.pbz2.repos.ExpenceBillRepo;
import com.pbz2.repos.IncommingBillRepo;
import com.pbz2.repos.InventoryRepo;
import com.pbz2.repos.StoregeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/incommingbill")
public class IncommingBillController {
    @Autowired
    IncommingBillRepo incommingBillRepo;
    @Autowired
    StoregeRepo storegeRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    @GetMapping("/show")
    public String showBill(Model model) {
        Iterable<IncommingBill> incommingBills = incommingBillRepo.findAll();
        model.addAttribute("inbills", incommingBills);
        return "showInBills";
    }

    @GetMapping("/{id}")
    public String indexBill(@PathVariable("id") Long id, Model model) {
        IncommingBill incommingBill = incommingBillRepo.findById(id).orElse(new IncommingBill());
        model.addAttribute("bill", incommingBill);
        return "inBillByIndex";
    }


    @GetMapping("/new")
    public String newBill(@ModelAttribute("bill") ExpenceBill expenceBill) {
        return "newInBill";
    }

    @PostMapping("/create")
    public String createStorage(@RequestParam String date,
                                @RequestParam Long id,
                                @RequestParam String idInv,
                                @RequestParam Integer amountInv) {
        IncommingBill incommingBill = new IncommingBill();
        incommingBill.setDate(date);

        Storege storege = storegeRepo.findById(id).orElse(new Storege());

        incommingBill.setStorege(storege);

        System.out.println("!!!!!!!" + idInv);
        Inventory inventory = new Inventory();
        inventory.setName(idInv);
        inventory.setAmount(amountInv);
        inventoryRepo.save(inventory);

        incommingBill.setInventories(inventory);
        incommingBillRepo.save(incommingBill);

        return "redirect:/incommingbill/show";
    }

    @PostMapping("/{id}")
    public String deleteStorage(@PathVariable("id") Long id){
        incommingBillRepo.deleteById(id);
        return "redirect:/incommingbill/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        IncommingBill incommingBill = incommingBillRepo.findById(id).orElse(new IncommingBill());
        model.addAttribute("bill", incommingBill);
        return "editInBill";
    }

    @PostMapping("/{id}/update")
    public String update(@RequestParam String date,
                         @RequestParam Long id,
                         @RequestParam String idInv,
                         @RequestParam Integer amountInv,
                         @RequestParam Long stId) {

        IncommingBill incommingBill = incommingBillRepo.findById(id).orElse(new IncommingBill());
        incommingBill.setDate(date);

        Storege storege = storegeRepo.findById(id).orElse(new Storege());

        incommingBill.setStorege(storege);

        System.out.println("!!!!!!!" + idInv);
        Inventory inventory = new Inventory();
        inventory.setName(idInv);
        inventory.setAmount(amountInv);
        inventoryRepo.save(inventory);

        incommingBill.setInventories(inventory);
        incommingBillRepo.save(incommingBill);
        return "redirect:/incommingbill/show";
    }
}
