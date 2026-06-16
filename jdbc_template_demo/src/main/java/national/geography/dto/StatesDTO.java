package main.java.national.geography.dto;

import java.util.Date;

public class StatesDTO {
    private int stateId;
    private String stateName;
    private String stateCode;
    private byte[] stateMapImage;
    private String stateCapital;
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
    private int countryId;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public byte[] getStateMapImage() {
        return stateMapImage;
    }

    public void setStateMapImage(byte[] stateMapImage) {
        this.stateMapImage = stateMapImage;
    }

    public String getStateCapital() {
        return stateCapital;
    }

    public void setStateCapital(String stateCapital) {
        this.stateCapital = stateCapital;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "StatesDTO{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", stateCapital='" + stateCapital + '\'' +
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
