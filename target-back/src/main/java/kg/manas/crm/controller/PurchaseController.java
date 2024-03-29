package kg.manas.crm.controller;

import kg.manas.crm.job.OfferConstructor;
import kg.manas.crm.models.PurchaseModel;
import kg.manas.crm.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping("/get-all")
    public List<PurchaseModel> getAll() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping("/save")
    public PurchaseModel purchase(Long serviceId, Long userId) {
        return purchaseService.purchase(serviceId, userId);
    }

}
