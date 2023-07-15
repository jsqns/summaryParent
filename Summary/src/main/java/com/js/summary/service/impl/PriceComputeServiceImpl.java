package com.js.summary.service.impl;


import com.js.summary.factory.PriceComputeFactory;
import com.js.summary.service.PriceComputeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceComputeServiceImpl implements PriceComputeService, InitializingBean {
    @Override
    public BigDecimal compute(BigDecimal bigDecimal) {
        return new BigDecimal(1);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PriceComputeFactory.register("key",this);
    }
}
