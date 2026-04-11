package main.com.library.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Publisher entity
 * Represents a book publisher with all relevant information
 */
public class PublisherDTO {
    private int publisherId;
    private String publisherName;
    private String publisherCode;
    private String publisherAddress;
    private String publisherEmail;
    private String publisherPhone;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;
    private String isDeleted;
    // Constructors
    public PublisherDTO() {}
    
    public PublisherDTO(int publisherId, String publisherName, String publisherCode, 
                       String publisherAddress, String publisherEmail, String publisherPhone,
                       String isDeleted) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.publisherCode = publisherCode;
        this.publisherAddress = publisherAddress;
        this.publisherEmail = publisherEmail;
        this.publisherPhone = publisherPhone;
        this.isDeleted = isDeleted;
    }

    
    // Getters and Setters
    public int getPublisherId() {
        return publisherId;
    }
    
    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
    
    public String getPublisherName() {
        return publisherName;
    }
    
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    
    public String getPublisherCode() {
        return publisherCode;
    }
    
    public void setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode;
    }
    
    public String getPublisherAddress() {
        return publisherAddress;
    }
    
    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }
    
    public String getPublisherEmail() {
        return publisherEmail;
    }
    
    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }
    
    public String getPublisherPhone() {
        return publisherPhone;
    }
    
    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }
    

    public String getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "PublisherDTO{" +
                "publisherId=" + publisherId +
                ", publisherName='" + publisherName + '\'' +
                ", publisherCode='" + publisherCode + '\'' +
                ", publisherAddress='" + publisherAddress + '\'' +
                ", publisherEmail='" + publisherEmail + '\'' +
                ", publisherPhone='" + publisherPhone + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate=" + updatedDate +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
