package io.swagger.utils;

import io.swagger.data.dao.IndividualDao;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class JsonString {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public String toJsonString(IndividualDao ind) {
        DateTimeParser birthTimeParser = new DateTimeParser();
        DateTimeParser deathTimeParser = new DateTimeParser();
        String value = String.format("{  \"aristocraticTitle\" : \"%s\",  \"gender\" : \"%s\",  " +
                        "\"@baseType\" : \"%s\",  \"countryOfBirth\" : \"%s\",  \"@type\" : \"%s\",  " +
                        "\"familyName\" : \"%s\",  \"deathDate\" : \"%s\",  \"fullName\" : \"%s\", " +
                        " \"id\" : \"%s\",  \"href\" : \"%s\",  \"@schemaLocation\" : \"%s\",  \"birthDate\" : \"%s\"}",
                ind.getAristocraticTitle(), ind.getGender(), ind.getBaseType(), ind.getCountryOfBirth(), ind.getType(),
                ind.getFamilyName(), deathTimeParser.parseFromSqlTimeStamp(ind.getDeathDate()), ind.getFullName(),
                ind.getId(), ind.getHref(), ind.getSchemaLocation(), birthTimeParser.parseFromSqlTimeStamp(ind.getBirthDate()));
        return value;
    }

    public String toParameterizedJsonString(IndividualDao ind) {
        DateTimeParser birthTimeParser = new DateTimeParser();
        DateTimeParser deathTimeParser = new DateTimeParser();
        StringBuilder sb = new StringBuilder("{");
        if(null != ind.getAristocraticTitle()){
            sb.append("  \"aristocraticTitle\" : \"").append(ind.getAristocraticTitle()).append("\", ");
        }
        if(null != ind.getGender()){
            sb.append("  \"gender\" : \"").append(ind.getGender()).append("\", ");
        }
        if(null != ind.getBaseType()){
            sb.append("  \"@baseType\" : \"").append(ind.getBaseType()).append("\", ");
        }
        if(null != ind.getCountryOfBirth()){
            sb.append("  \"countryOfBirth\" : \"").append(ind.getCountryOfBirth()).append("\", ");
        }
        if(null != ind.getType()){
            sb.append("  \"@type\" : \"").append(ind.getType()).append("\", ");
        }
        if(null != ind.getFamilyName()){
            sb.append("  \"familyName\" : \"").append(ind.getFamilyName()).append("\", ");
        }
        if(null != ind.getDeathDate()){
            sb.append("  \"deathDate\" : \"").append(deathTimeParser.parseFromSqlTimeStamp(ind.getDeathDate())).append("\", ");
        }
        if(null != ind.getFullName()){
            sb.append("  \"fullName\" : \"").append(ind.getFullName()).append("\", ");
        }
        if(null != ind.getId()){
            sb.append("  \"id\" : \"").append(ind.getId()).append("\", ");
        }
        if(null != ind.getHref()){
            sb.append("  \"href\" : \"").append(ind.getHref()).append("\", ");
        }
        if(null != ind.getSchemaLocation()){
            sb.append("  \"@schemaLocation\" : \"").append(ind.getSchemaLocation()).append("\", ");
        }
        if(null != ind.getBirthDate()){
            sb.append("  \"aristocraticTitle\" : \"").append(birthTimeParser.parseFromSqlTimeStamp(ind.getBirthDate())).append("\", ");
        }
        String str = sb.toString();
        str = str.substring(0, str.lastIndexOf(","));
        return str.concat(" }");
    }

}
