package main.com.library.dto;

import java.util.Date;

public class AuthorDTO {
    private int authorId;
    private String authorName;
    private String authorCode;
    private String authorAddress;
    private Date authorDob;
    private String authorEmail;
    private String authorEducation;
    private String authorUniversity;
    private String isDeleted;

    public AuthorDTO() {}

    public AuthorDTO(int authorId, String authorName, String authorEmail) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
    }

    public AuthorDTO(int authorId, String authorName, String authorCode, String authorAddress, 
                    Date authorDob, String authorEmail, String authorEducation, 
                    String authorUniversity, String isDeleted) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorCode = authorCode;
        this.authorAddress = authorAddress;
        this.authorDob = authorDob;
        this.authorEmail = authorEmail;
        this.authorEducation = authorEducation;
        this.authorUniversity = authorUniversity;
        this.isDeleted = isDeleted;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public Date getAuthorDob() {
        return authorDob;
    }

    public void setAuthorDob(Date authorDob) {
        this.authorDob = authorDob;
    }

    public String getAuthorEducation() {
        return authorEducation;
    }

    public void setAuthorEducation(String authorEducation) {
        this.authorEducation = authorEducation;
    }

    public String getAuthorUniversity() {
        return authorUniversity;
    }

    public void setAuthorUniversity(String authorUniversity) {
        this.authorUniversity = authorUniversity;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorCode='" + authorCode + '\'' +
                ", authorAddress='" + authorAddress + '\'' +
                ", authorDob=" + authorDob +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorEducation='" + authorEducation + '\'' +
                ", authorUniversity='" + authorUniversity + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
