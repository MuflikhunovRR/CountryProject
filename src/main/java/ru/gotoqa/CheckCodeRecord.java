package ru.gotoqa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.gotoqa.modelDb.CountryCatalog;
import ru.gotoqa.models.*;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Muflikhunov Roman
 */

public class CheckCodeRecord {
    private static NamedParameterJdbcTemplate nquSql;

    @Test
    public void test1() throws IOException {
        //START. Soap request
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-config.xml"});
        CountryInfoService bean = context.getBean(CountryInfoService.class);
        ArrayOftCountryCodeAndName arrayOftCountryCodeAndName = bean.getCountryInfoServiceSoap().listOfCountryNamesByCode();
        List<TCountryCodeAndName> tCountryCodeAndName = arrayOftCountryCodeAndName.getTCountryCodeAndName();
        //STOP. Soap request
        int update1 = 0;
        for (TCountryCodeAndName codeAndName : tCountryCodeAndName){
            String isoCode = codeAndName.getSISOCode();
            String countryName = codeAndName.getSName();

            String capitalCity = bean.getCountryInfoServiceSoap().capitalCity(isoCode);
            //Get Country Currency
            TCurrency currency = bean.getCountryInfoServiceSoap().countryCurrency(isoCode);
            String currencyCode = currency.getSISOCode();
            String currencyName = currency.getSName();
            //Get Country Phone Code
            String phoneCode = bean.getCountryInfoServiceSoap().countryIntPhoneCode(isoCode);
            //Get Country language
            TCountryInfo countryLanguageList = bean.getCountryInfoServiceSoap().fullCountryInfo(isoCode);
            List<TLanguage> tLanguage = countryLanguageList.getLanguages().getTLanguage();
            String countryLanguage = null;
            for (TLanguage oftLanguage : tLanguage){
                countryLanguage = oftLanguage.getSName();
            }

            //START. Record ISOCode and Country name in db
            ClassPathXmlApplicationContext contextdb = new ClassPathXmlApplicationContext("db.xml");
            nquSql = new NamedParameterJdbcTemplate(contextdb.getBean(DataSource.class));
            HashMap<String, Object> param = new HashMap<>();
            param.put("isoCode", isoCode);
            param.put("countryName", countryName);
            param.put("capitalCity", capitalCity);
            param.put("countryLanguage", countryLanguage);
            param.put("currencyCode", currencyCode);
            param.put("currencyName", currencyName);
            param.put("phoneCode", phoneCode);
            String insertSql = "INSERT INTO COUNTRY_CATALOG  VALUES (id, :countryName, :capitalCity, :countryLanguage, NULL, :isoCode, NULL, NULL, NULL, NULL, :currencyCode, :currencyName, :phoneCode);";
            // execute insert + count number of records / records processed by the executed query
            nquSql.update(insertSql, param);
            update1 ++;
            //STOP. Record in db
        }
        System.out.println(update1 + " records inserted.");

        //START. Parsing web page
        //https://countrycode.org/
        //https://try.jsoup.org/
        Document doc = Jsoup.parse(new File("D:\\JAVA\\Java_SRC\\CountryCodeProject\\src\\main\\resources\\countrycode.html"), "utf-8");
        Elements inputElements = doc.select("div.visible-md > table > tbody > tr");
        int update2 = 0;
        for (Element inputElement : inputElements) {
            String country = inputElement.select("td:nth-child(1)").text();
            String countryCode = inputElement.select("td:nth-child(2)").text();
            String isoCode2 = inputElement.select("td:nth-child(3)").text().substring(0,2);
            String isoCode3 = inputElement.select("td:nth-child(3)").text().substring(5,8);
            String population = inputElement.select("td:nth-child(4)").text().replace(" ", "");
            String areaSq = inputElement.select("td:nth-child(5)").text().replace(" ", "");
            String gpd = inputElement.select("td:nth-child(6)").text();

            HashMap<String, Object> param = new HashMap<>();
            param.put("ISO_CODES2", isoCode2);
            List<CountryCatalog> newDatasetAirs = nquSql.query("SELECT * FROM COUNTRY_CATALOG WHERE ISO_CODES2 = :ISO_CODES2",
                    param,
                    new BeanPropertyRowMapper<>(CountryCatalog.class));
            System.out.println(newDatasetAirs);

            if (newDatasetAirs.size() != 0){
                HashMap<String, Object> param2 = new HashMap<>();
                param2.put("isoCode2", isoCode2);
                param2.put("isoCode3", isoCode3);
                param2.put("countryCode", countryCode);
                param2.put("population", population);
                param2.put("areaSq", areaSq);
                param2.put("gpd", gpd);
                String updateSql = "UPDATE COUNTRY_CATALOG SET COUNTRY_CODE = :countryCode, ISO_CODES3 = :isoCode3, POPULATION = :population, AREA_KM2 = :areaSq, GDP_$USD = :gpd WHERE ISO_CODES2 = :isoCode2";
                nquSql.update(updateSql, param2);
            }

            update2 ++;
        }
        System.out.println("Count on page: " +update2);

    }

}
