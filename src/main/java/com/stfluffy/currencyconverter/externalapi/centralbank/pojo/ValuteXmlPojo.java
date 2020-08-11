package com.stfluffy.currencyconverter.externalapi.centralbank.pojo;

import com.stfluffy.currencyconverter.externalapi.centralbank.xmlparse.adapters.BigDecimalFormatAdapter;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@ToString
@Getter
public class ValuteXmlPojo {

    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private BigDecimal value;

    @XmlElement(name = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(value = BigDecimalFormatAdapter.class)
    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
