package io.swagger.utils;

import io.swagger.model.IndividualUpdate;
import io.swagger.model.IndividualCreate;
import org.springframework.stereotype.Component;

@Component
public class PatchComparator {
    public IndividualCreate compareForPatch(IndividualCreate dto, IndividualUpdate update){
        dto.setAristocraticTitle((null != update.getAristocraticTitle())?update.getAristocraticTitle():dto.getAristocraticTitle());
        dto.setBaseType((null != update.getBaseType())?update.getBaseType():dto.getBaseType());
        dto.setBirthDate((null != update.getBirthDate())?update.getBirthDate():dto.getBirthDate());
        dto.setCountryOfBirth((null != update.getCountryOfBirth())?update.getCountryOfBirth():dto.getCountryOfBirth());
        dto.setDeathDate((null != update.getDeathDate())?update.getDeathDate():dto.getDeathDate());
        dto.setFamilyName((null != update.getFamilyName())?update.getFamilyName():dto.getFamilyName());
        dto.setFullName((null != update.getFullName())?update.getFullName():dto.getFullName());
        dto.setGender((null != update.getGender())?update.getGender():dto.getGender());
        dto.setHref((null != update.getHref())?update.getHref():dto.getHref());
        dto.setSchemaLocation((null != update.getSchemaLocation())?update.getSchemaLocation():dto.getSchemaLocation());
        dto.setType((null != update.getType())?update.getType():dto.getType());
        return dto;
    }
}
