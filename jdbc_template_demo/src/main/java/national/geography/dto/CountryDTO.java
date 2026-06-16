package main.java.national.geography.dto;

import java.util.Date;

public class CountryDTO {
    private  int countryId;
    private String countryName;
    private String countryCode;
    private byte[] countryMapImage;
    private String countryFlag;
    private byte[] countryFlagImage;
    private String countryCurrency;
    private String countryLanguage;
    private String countryContinent;
    private String countryCapital;
    private int countryPopulation;
    private double countryArea;
    private String countryTimeZone;
    private String countryCallingCode;
    private Date independenceDay;
    private Date republicDay;
    private String nationalSymbol;
    private String nationalAnimal;
    private String firstPresident;
    private String firstPrimeMinister;
    private String currentPresident;
    private String currentPrimeMinister;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public byte[] getCountryMapImage() {
        return countryMapImage;
    }

    public void setCountryMapImage(byte[] countryMapImage) {
        this.countryMapImage = countryMapImage;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public byte[] getCountryFlagImage() {
        return countryFlagImage;
    }

    public void setCountryFlagImage(byte[] countryFlagImage) {
        this.countryFlagImage = countryFlagImage;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(String countryCurrency) {
        this.countryCurrency = countryCurrency;
    }

    public String getCountryLanguage() {
        return countryLanguage;
    }

    public void setCountryLanguage(String countryLanguage) {
        this.countryLanguage = countryLanguage;
    }

    public String getCountryContinent() {
        return countryContinent;
    }

    public void setCountryContinent(String countryContinent) {
        this.countryContinent = countryContinent;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public double getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(double countryArea) {
        this.countryArea = countryArea;
    }

    public String getCountryTimeZone() {
        return countryTimeZone;
    }

    public void setCountryTimeZone(String countryTimeZone) {
        this.countryTimeZone = countryTimeZone;
    }

    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    public void setCountryCallingCode(String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    public Date getIndependenceDay() {
        return independenceDay;
    }

    public void setIndependenceDay(Date independenceDay) {
        this.independenceDay = independenceDay;
    }

    public Date getRepublicDay() {
        return republicDay;
    }

    public void setRepublicDay(Date republicDay) {
        this.republicDay = republicDay;
    }

    public String getNationalSymbol() {
        return nationalSymbol;
    }

    public void setNationalSymbol(String nationalSymbol) {
        this.nationalSymbol = nationalSymbol;
    }

    public String getNationalAnimal() {
        return nationalAnimal;
    }

    public void setNationalAnimal(String nationalAnimal) {
        this.nationalAnimal = nationalAnimal;
    }

    public String getFirstPresident() {
        return firstPresident;
    }

    public void setFirstPresident(String firstPresident) {
        this.firstPresident = firstPresident;
    }

    public String getFirstPrimeMinister() {
        return firstPrimeMinister;
    }

    public void setFirstPrimeMinister(String firstPrimeMinister) {
        this.firstPrimeMinister = firstPrimeMinister;
    }

    public String getCurrentPresident() {
        return currentPresident;
    }

    public void setCurrentPresident(String currentPresident) {
        this.currentPresident = currentPresident;
    }

    public String getCurrentPrimeMinister() {
        return currentPrimeMinister;
    }

    public void setCurrentPrimeMinister(String currentPrimeMinister) {
        this.currentPrimeMinister = currentPrimeMinister;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "countryContinent='" + countryContinent + '\'' +
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                ", countryCurrency='" + countryCurrency + '\'' +
                ", countryLanguage='" + countryLanguage + '\'' +
                ", countryCapital='" + countryCapital + '\'' +
                ", countryPopulation=" + countryPopulation +
                ", countryArea=" + countryArea +
                ", countryTimeZone='" + countryTimeZone + '\'' +
                ", countryCallingCode='" + countryCallingCode + '\'' +
                ", independenceDay=" + independenceDay +
                ", republicDay=" + republicDay +
                ", nationalSymbol='" + nationalSymbol + '\'' +
                ", nationalAnimal='" + nationalAnimal + '\'' +
                ", firstPresident='" + firstPresident + '\'' +
                ", firstPrimeMinister='" + firstPrimeMinister + '\'' +
                ", currentPresident='" + currentPresident + '\'' +
                ", currentPrimeMinister='" + currentPrimeMinister + '\'' +
                '}';
    }
}
