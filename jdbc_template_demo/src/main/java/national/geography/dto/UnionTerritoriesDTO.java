package main.java.national.geography.dto;

import java.util.Date;

public class UnionTerritoriesDTO {
    private int unionTerritoryId;
    private String unionTerritoryName;
    private String unionTerritoryCode;
    private byte[] unionTerritoryMapImage;
    private String unionTerritoryCapital;
    private String primaryLanguage;
    private String secondaryLanguage;
    private int population;
    private int area;
    private Date formationDay;
    private String firstGovernor;
    private String currentGovernor;
    private String firstChiefMinister;
    private String currentChiefMinister;
    private int numberOfDistricts;
    private String majorCities;
    private String countryId;

    public String getUnionTerritoryCode() {
        return unionTerritoryCode;
    }

    public void setUnionTerritoryCode(String unionTerritoryCode) {
        this.unionTerritoryCode = unionTerritoryCode;
    }

    public int getUnionTerritoryId() {
        return unionTerritoryId;
    }

    public void setUnionTerritoryId(int unionTerritoryId) {
        this.unionTerritoryId = unionTerritoryId;
    }

    public String getUnionTerritoryName() {
        return unionTerritoryName;
    }

    public void setUnionTerritoryName(String unionTerritoryName) {
        this.unionTerritoryName = unionTerritoryName;
    }

    public byte[] getUnionTerritoryMapImage() {
        return unionTerritoryMapImage;
    }

    public void setUnionTerritoryMapImage(byte[] unionTerritoryMapImage) {
        this.unionTerritoryMapImage = unionTerritoryMapImage;
    }

    public String getUnionTerritoryCapital() {
        return unionTerritoryCapital;
    }

    public void setUnionTerritoryCapital(String unionTerritoryCapital) {
        this.unionTerritoryCapital = unionTerritoryCapital;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(String secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Date getFormationDay() {
        return formationDay;
    }

    public void setFormationDay(Date formationDay) {
        this.formationDay = formationDay;
    }

    public String getFirstGovernor() {
        return firstGovernor;
    }

    public void setFirstGovernor(String firstGovernor) {
        this.firstGovernor = firstGovernor;
    }

    public String getCurrentGovernor() {
        return currentGovernor;
    }

    public void setCurrentGovernor(String currentGovernor) {
        this.currentGovernor = currentGovernor;
    }

    public String getFirstChiefMinister() {
        return firstChiefMinister;
    }

    public void setFirstChiefMinister(String firstChiefMinister) {
        this.firstChiefMinister = firstChiefMinister;
    }

    public String getCurrentChiefMinister() {
        return currentChiefMinister;
    }

    public void setCurrentChiefMinister(String currentChiefMinister) {
        this.currentChiefMinister = currentChiefMinister;
    }

    public int getNumberOfDistricts() {
        return numberOfDistricts;
    }

    public void setNumberOfDistricts(int numberOfDistricts) {
        this.numberOfDistricts = numberOfDistricts;
    }

    public String getMajorCities() {
        return majorCities;
    }

    public void setMajorCities(String majorCities) {
        this.majorCities = majorCities;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "UnionTerritoriesDTO{" +
                "unionTerritoryId=" + unionTerritoryId +
                ", unionTerritoryName='" + unionTerritoryName + '\'' +
                ", unionTerritoryCode='" + unionTerritoryCode + '\'' +
                ", unionTerritoryCapital='" + unionTerritoryCapital + '\'' +
                ", primaryLanguage='" + primaryLanguage + '\'' +
                ", secondaryLanguage='" + secondaryLanguage + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", formationDay=" + formationDay +
                ", firstGovernor='" + firstGovernor + '\'' +
                ", currentGovernor='" + currentGovernor + '\'' +
                ", firstChiefMinister='" + firstChiefMinister + '\'' +
                ", currentChiefMinister='" + currentChiefMinister + '\'' +
                ", numberOfDistricts=" + numberOfDistricts +
                ", majorCities='" + majorCities + '\'' +
                ", countryId='" + countryId + '\'' +
                '}';
    }
}
