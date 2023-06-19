package kg.manas.crm.controller;

import kg.manas.crm.entities.Offer;
import kg.manas.crm.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {
    private final OfferRepository offerRepository;


    @GetMapping
    List<Offer> fetchOffers() {
        return offerRepository.findAll();
    }
}
