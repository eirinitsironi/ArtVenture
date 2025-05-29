package backend;

import java.util.LinkedHashMap;
import java.util.Map;

public class Discount {
    
    public static Map<String, Integer> getAvailableDiscounts(int totalPoints) {
        Map<String, Integer> discounts = new LinkedHashMap<>();
        if (totalPoints >= 50) discounts.put("5% discount", 50);
        if (totalPoints >= 150) discounts.put("15% discount", 150);
        if (totalPoints >= 300) discounts.put("30% discount", 300);
        return discounts;
    }
}

