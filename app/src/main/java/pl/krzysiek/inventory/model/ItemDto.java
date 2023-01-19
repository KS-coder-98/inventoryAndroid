package pl.krzysiek.inventory.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class ItemDto {
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("purchaseDate")
    private String purchaseDate;
    @SerializedName("faxNumber")
    private String faxNumber;
    @SerializedName("purchasePrice")
    private Double purchasePrice;
    @SerializedName("amountOfAnnualDepreciation")
    private Double amountOfAnnualDepreciation;
    @SerializedName("currencyValue")
    private Double currencyValue;
    @SerializedName("description")
    private String description;
    @SerializedName("location")
    private String location;
    @SerializedName("classification")
    private FixedAssetClassification classification;
    @SerializedName("barCodeNumber")
    private String barCodeNumber;

    public ItemDto(Long id, String name, String purchaseDate, String faxNumber, Double purchasePrice, Double amountOfAnnualDepreciation, Double currencyValue, String description, String location, FixedAssetClassification classification, String barCodeNumber) {
        this.id = id;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.faxNumber = faxNumber;
        this.purchasePrice = purchasePrice;
        this.amountOfAnnualDepreciation = amountOfAnnualDepreciation;
        this.currencyValue = currencyValue;
        this.description = description;
        this.location = location;
        this.classification = classification;
        this.barCodeNumber = barCodeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getAmountOfAnnualDepreciation() {
        return amountOfAnnualDepreciation;
    }

    public void setAmountOfAnnualDepreciation(Double amountOfAnnualDepreciation) {
        this.amountOfAnnualDepreciation = amountOfAnnualDepreciation;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public FixedAssetClassification getClassification() {
        return classification;
    }

    public void setClassification(FixedAssetClassification classification) {
        this.classification = classification;
    }

    public String getBarCodeNumber() {
        return barCodeNumber;
    }

    public void setBarCodeNumber(String barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
    }


}
