package com.js.summary.factory;

import com.js.summary.service.PriceComputeService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceComputeFactory {
    private static Map<String, PriceComputeService> map = new ConcurrentHashMap<>();

    public static PriceComputeService getPriceComputeService(String key){
        return map.get(key);
    }
    public static void register(String key, PriceComputeService priceComputeService){
        map.put(key, priceComputeService);
    }
}
