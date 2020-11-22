package io.swagger.data.dao;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(schema = "individual_schema", name= "INDIVIDUAL")
@JsonPropertyOrder("id, href, aristocraticTitle, birthDate, countryOfBirth, deathDate, familyName, fullName, gender")
public class IndividualDao {
    @Id
    @NotNull
    @Column(name = "id")
    private String id = null;

    @Column(name = "href")
    private String href = null;

    @Column(name = "aristocraticTitle")
    private String aristocraticTitle = null;

    @Column(name = "birthDate")
    private Timestamp birthDate = null;

    @Column(name = "countryOfBirth")
    private String countryOfBirth = null;

    @Column(name = "deathDate")
    private Timestamp deathDate = null;

    @Column(name = "familyName")
    private String familyName = null;

    @Column(name = "fullName")
    private String fullName = null;

    @Column(name = "gender")
    private String gender = null;

    @Column(name = "baseType")
    private String baseType = null;

    @Column(name = "schemaLocation")
    private String schemaLocation = null;

    @Column(name = "type")
    private String type = null;


    public IndividualDao() {
    }

    public IndividualDao(String id, String aristocraticTitle, Date birthDate, String countryOfBirth, Date deathDate, String familyName, String fullName, String gender, String href) {
    }

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonGetter("href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    @JsonGetter("aristocraticTitle")
    public String getAristocraticTitle() {
        return aristocraticTitle;
    }

    public void setAristocraticTitle(String aristocraticTitle) {
        this.aristocraticTitle = aristocraticTitle;
    }
    @JsonGetter("birthDate")
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }
    @JsonGetter("countryOfBirth")
    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }
    @JsonGetter("deathDate")
    public Timestamp getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Timestamp deathDate) {
        this.deathDate = deathDate;
    }
    @JsonGetter("familyName")
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    @JsonGetter("fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @JsonGetter("gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @JsonGetter("baseType")
    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    @JsonGetter("schemaLocation")
    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }
    @JsonGetter("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
