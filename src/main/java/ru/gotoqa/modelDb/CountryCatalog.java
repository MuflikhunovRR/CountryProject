package ru.gotoqa.modelDb;

public class CountryCatalog {

    private Integer id;
    private String COUNTRY;
    private String CAPITAL;
    private String LANGUAGE;
    private String COUNTRY_CODE;
    private String ISO_CODES2;
    private String isoCodes3;
    private Integer POPULATION;
    private Integer AREA_KM2;
    private String GDP_$USD;
    private String CURRENCY_CODE;
    private String CURRENCY_NAME;
    private String PHONE_CODE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getCAPITAL() {
        return CAPITAL;
    }

    public void setCAPITAL(String CAPITAL) {
        this.CAPITAL = CAPITAL;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
    }

    public String getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    public void setCOUNTRY_CODE(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public String getISO_CODES2() {
        return ISO_CODES2;
    }

    public void setISO_CODES2(String ISO_CODES2) {
        this.ISO_CODES2 = ISO_CODES2;
    }

    public String getIsoCodes3() {
        return isoCodes3;
    }

    public void setIsoCodes3(String isoCodes3) {
        this.isoCodes3 = isoCodes3;
    }

    public Integer getPOPULATION() {
        return POPULATION;
    }

    public void setPOPULATION(Integer POPULATION) {
        this.POPULATION = POPULATION;
    }

    public Integer getAREA_KM2() {
        return AREA_KM2;
    }

    public void setAREA_KM2(Integer AREA_KM2) {
        this.AREA_KM2 = AREA_KM2;
    }

    public String getGDP_$USD() {
        return GDP_$USD;
    }

    public void setGDP_$USD(String GDP_$USD) {
        this.GDP_$USD = GDP_$USD;
    }

    public String getCURRENCY_CODE() {
        return CURRENCY_CODE;
    }

    public void setCURRENCY_CODE(String CURRENCY_CODE) {
        this.CURRENCY_CODE = CURRENCY_CODE;
    }

    public String getCURRENCY_NAME() {
        return CURRENCY_NAME;
    }

    public void setCURRENCY_NAME(String CURRENCY_NAME) {
        this.CURRENCY_NAME = CURRENCY_NAME;
    }

    public String getPHONE_CODE() {
        return PHONE_CODE;
    }

    public void setPHONE_CODE(String PHONE_CODE) {
        this.PHONE_CODE = PHONE_CODE;
    }

    @Override
    public String toString() {
        return "CountryCatalog{" +
                "id=" + id +
                ", COUNTRY='" + COUNTRY + '\'' +
                ", CAPITAL='" + CAPITAL + '\'' +
                ", LANGUAGE='" + LANGUAGE + '\'' +
                ", COUNTRY_CODE='" + COUNTRY_CODE + '\'' +
                ", ISO_CODES2='" + ISO_CODES2 + '\'' +
                ", isoCodes3='" + isoCodes3 + '\'' +
                ", POPULATION=" + POPULATION +
                ", AREA_KM2=" + AREA_KM2 +
                ", GDP_$USD='" + GDP_$USD + '\'' +
                ", CURRENCY_CODE='" + CURRENCY_CODE + '\'' +
                ", CURRENCY_NAME='" + CURRENCY_NAME + '\'' +
                ", PHONE_CODE='" + PHONE_CODE + '\'' +
                '}';
    }

    public CountryCatalog() {
    }

}
