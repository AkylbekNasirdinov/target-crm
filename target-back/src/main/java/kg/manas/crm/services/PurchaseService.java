package kg.manas.crm.services;

import kg.manas.crm.models.PurchaseModel;

import java.util.List;

public interface PurchaseService {
    PurchaseModel purchase(Long serviceId, Long customerId);
    List<PurchaseModel> getAllPurchases();
}
