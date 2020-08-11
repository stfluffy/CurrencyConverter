package com.stfluffy.currencyconverter.externalapi.centralbank.xmlparse;

import com.stfluffy.currencyconverter.dto.CentralBankExchRateDto;
import com.stfluffy.currencyconverter.dto.CurrencyDto;
import com.stfluffy.currencyconverter.exception.ParseCentralBankApiException;
import com.stfluffy.currencyconverter.externalapi.centralbank.pojo.ValCursXmlPojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UnmarshalXml {

    private final String DAILY_VAL_CURS = "http://www.cbr.ru/scripts/XML_daily.asp";
    private final String DATE_QUERY_PARAM = "?date_req=";

    /**
     *
     * @return CentralBankExchRateDto с данными полученными из api цбрф
     * @throws Exception
     */
    public CentralBankExchRateDto getCentralBankExchRatesDto() throws Exception {
        return convertToExchangeRatesDto(unmarshalXml(builderUrl()));
    }

    /**
     * Парсит xml из api цбрф
     * @param url на api цбрф
     * @return ValCursXmlPojo с данными после парсинга
     * @throws Exception
     */
    private ValCursXmlPojo unmarshalXml(URL url) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ValCursXmlPojo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ValCursXmlPojo valCursXmlPojo = (ValCursXmlPojo) jaxbUnmarshaller.unmarshal(url);

        if (Objects.nonNull(valCursXmlPojo)) {
            if (!valCursXmlPojo.getValuteList().isEmpty() && valCursXmlPojo.getLocalDate() != null) {
                return valCursXmlPojo;
            }
        }

        throw new ParseCentralBankApiException();
    }

    /**
     * Конвертирует данные из valCursXmlPojo в CentralBankExchRateDto
     * @param valCursXmlPojo класс после парсинга
     * @return CentralBankExchRateDto
     */
    private CentralBankExchRateDto convertToExchangeRatesDto(ValCursXmlPojo valCursXmlPojo) {
        List<CurrencyDto> currencyDtos = valCursXmlPojo.getValuteList().stream()
                .map(valute -> CurrencyDto.builder()
                        .numCode(valute.getNumCode())
                        .charCode(valute.getCharCode())
                        .denomination(valute.getNominal())
                        .name(valute.getName())
                        .value(valute.getValue())
                        .build())
                .collect(Collectors.toList());

        currencyDtos.add(CurrencyDto.builder()
                .numCode("643")
                .charCode("RUB")
                .name("Российский рубль")
                .denomination(1)
                .value(BigDecimal.ONE)
                .build());

        return CentralBankExchRateDto.builder()
                .currencies(currencyDtos)
                .localDate(LocalDate.now())
                .build();
    }

    /**
     * Составляет url с датой на сегодня
     * @return Url с акутальным курсом на сегодня
     * @throws MalformedURLException
     */
    private URL builderUrl() throws MalformedURLException {
        String url = DAILY_VAL_CURS + DATE_QUERY_PARAM
                + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new URL(url);
    }
}
