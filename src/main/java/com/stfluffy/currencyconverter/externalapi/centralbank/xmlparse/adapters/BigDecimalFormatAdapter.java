package com.stfluffy.currencyconverter.externalapi.centralbank.xmlparse.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalFormatAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String s) {
        return new BigDecimal(s.replace(",", "."));
    }

    @Override
    public String marshal(BigDecimal bigDecimal) {
        return bigDecimal.toString().replace(".", ",");
    }
}
