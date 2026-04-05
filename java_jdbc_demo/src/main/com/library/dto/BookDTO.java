package main.com.library.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDTO {
    // Primary Key
    private int bookId;
    
    // Basic Book Information
    private String bookTitle;
    private String isbn;
    private String genre;
    private int pages;
    private String language;
    private BigDecimal price;
    private LocalDate publicationDate;
    
    // Foreign Keys
    private Integer authorId; //1000
    private Integer publisherId;//2000
    
    // Audit Fields
    private Integer createdByAdmin;
    private Integer updatedByAdmin;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String isDeleted;
    
    // Additional Fields for Joins (optional)
    private String authorName;
    private String publisherName;
    
    // Constructors
    public BookDTO() {
        this.isDeleted = "N"; // Default value
    }
    
    public BookDTO(String bookTitle, String isbn, Integer authorId, Integer publisherId, 
                   LocalDate publicationDate, BigDecimal price, String genre, int pages, String language) {
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.publicationDate = publicationDate;
        this.price = price;
        this.genre = genre;
        this.pages = pages;
        this.language = language;
        this.isDeleted = "N";
    }
    
    // Getters and Setters
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getPages() {
        return pages;
    }
    
    public void setPages(int pages) {
        this.pages = pages;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    
    public Integer getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    
    public Integer getPublisherId() {
        return publisherId;
    }
    
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }
    
    public Integer getCreatedByAdmin() {
        return createdByAdmin;
    }
    
    public void setCreatedByAdmin(Integer createdByAdmin) {
        this.createdByAdmin = createdByAdmin;
    }
    
    public Integer getUpdatedByAdmin() {
        return updatedByAdmin;
    }
    
    public void setUpdatedByAdmin(Integer updatedByAdmin) {
        this.updatedByAdmin = updatedByAdmin;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public String getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public String getPublisherName() {
        return publisherName;
    }
    
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    
    // Utility Methods
    public boolean isDeleted() {
        return "Y".equalsIgnoreCase(this.isDeleted);
    }
    
    public void markAsDeleted() {
        this.isDeleted = "Y";
    }
    
    public void markAsActive() {
        this.isDeleted = "N";
    }
    
    // Validation Methods
    public boolean isValid() {
        return bookTitle != null && !bookTitle.trim().isEmpty() &&
               isbn != null && !isbn.trim().isEmpty() &&
               authorId != null && authorId > 0 &&
               publisherId != null && publisherId > 0 &&
               price != null && price.compareTo(BigDecimal.ZERO) >= 0 &&
               pages > 0 &&
               language != null && !language.trim().isEmpty();
    }
    
    // toString method
    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authorId=" + authorId +
                ", publisherId=" + publisherId +
                ", publicationDate=" + publicationDate +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                ", language='" + language + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                (authorName != null ? ", authorName='" + authorName + '\'' : "") +
                (publisherName != null ? ", publisherName='" + publisherName + '\'' : "") +
                '}';
    }
    
    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        BookDTO bookDTO = (BookDTO) o;
        
        return bookId == bookDTO.bookId &&
               pages == bookDTO.pages &&
               (bookTitle != null ? bookTitle.equals(bookDTO.bookTitle) : bookDTO.bookTitle == null) &&
               (isbn != null ? isbn.equals(bookDTO.isbn) : bookDTO.isbn == null) &&
               (genre != null ? genre.equals(bookDTO.genre) : bookDTO.genre == null) &&
               (language != null ? language.equals(bookDTO.language) : bookDTO.language == null) &&
               (price != null ? price.equals(bookDTO.price) : bookDTO.price == null) &&
               (publicationDate != null ? publicationDate.equals(bookDTO.publicationDate) : bookDTO.publicationDate == null);
    }
    
    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + pages;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}
