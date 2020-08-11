package com.stfluffy.currencyconverter.externalapi.centralbank.pojo;

import com.stfluffy.currencyconverter.externalapi.centralbank.xmlparse.adapters.DateFormatAdapter;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "ValCurs")
@Getter
@ToString
public class ValCursXmlPojo {

    private LocalDate localDate;
    private List<ValuteXmlPojo> valuteList;

    @XmlAttribute(name = "Date")
    @XmlJavaTypeAdapter(value = DateFormatAdapter.class)
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @XmlElement(name = "Valute")
    public void setValuteList(List<ValuteXmlPojo> valuteList) {
        this.valuteList = valuteList;
    }
}
