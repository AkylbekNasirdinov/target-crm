package kg.manas.crm.controller;

import kg.manas.crm.entity.Offer;
import kg.manas.crm.entity.UserServices;
import kg.manas.crm.job.OfferConstructor;
import kg.manas.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final OfferConstructor offerConstructor;

    @GetMapping("/purchases/get-all")
    public List<UserServices> getAll() {
        return userService.getAllPurchases();
    }

    @PostMapping("/purchases/save")
    public UserServices purchase(Long serviceId, Long userId) {
        return userService.purchase(serviceId, userId);
    }

    @PostMapping("/offers/construct")
    public void construct() {
        offerConstructor.constructOffers();
    }
}
