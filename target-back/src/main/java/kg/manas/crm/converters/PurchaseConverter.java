package kg.manas.crm.converters;

import kg.manas.crm.entities.Purchase;
import kg.manas.crm.models.PurchaseModel;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConverter extends Converter<Purchase, PurchaseModel>{
    public PurchaseConverter(Reflections reflections) {
        super(Purchase.class, PurchaseModel.class, reflections);
    }
}
