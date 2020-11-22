package io.swagger.service;

import io.swagger.data.dao.IndividualDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IndividualService {


    @Autowired
    JdbcTemplate template;

    public void databaseCreate(){
        template.execute("CREATE SCHEMA IF NOT EXISTS individual_schema AUTHORIZATION sa; \n" +
                "CREATE TABLE IF NOT EXISTS individual_schema.INDIVIDUAL(\n" +
                "  ID                    VARCHAR(20) NOT NULL PRIMARY KEY,\n" +
                "  ARISTOCRATIC_TITLE    VARCHAR(50),\n" +
                "  BIRTH_DATE            TIMESTAMP,\n" +
                "  DEATH_DATE            TIMESTAMP,\n" +
                "  COUNTRY_OF_BIRTH      VARCHAR(50),\n" +
                "  FAMILY_NAME           VARCHAR(50),\n" +
                "  FULL_NAME             VARCHAR(50),\n" +
                "  GENDER                VARCHAR(50),\n" +
                "  HREF                  VARCHAR(50),\n" +
                "  BASE_TYPE             VARCHAR(50),\n" +
                "  SCHEMA_LOCATION       VARCHAR(50),\n" +
                "  TYPE                  VARCHAR(50));");
    }

    public IndividualDao retrieveIndividualWithFields(String id, String fields){
        String sql = String.format("SELECT %s FROM individual_schema.INDIVIDUAL i where i.id = %s", selectFields(fields), id);
        RowMapper<IndividualDao> rm = BeanPropertyRowMapper.newInstance(IndividualDao.class);
        return template.query(sql, rm).get(0);
    }

    public List<IndividualDao> listIndividualWithLimits(int limit, int offset){
        String sql = String.format("SELECT * FROM individual_schema.INDIVIDUAL i LIMIT %d OFFSET %d", limit, offset);
        RowMapper<IndividualDao> rm = BeanPropertyRowMapper.newInstance(IndividualDao.class);
        return this.template.query(sql, rm);
    }

    public List<IndividualDao> listIndividualWithFields(String fields, int limit, int offset){
        StringBuilder sql = new StringBuilder(String.format("SELECT %s FROM individual_schema.INDIVIDUAL i", selectFields(fields)));
        if(limit > 0){
            sql.append(String.format(" LIMIT %d OFFSET %d", limit, offset));
        }
        RowMapper<IndividualDao> rm = BeanPropertyRowMapper.newInstance(IndividualDao.class);
        return this.template.query(sql.toString(), rm);
    }

    private String selectFields(String fields){
        List<String> fieldsList = Arrays.asList(fields.split("[,]"));
        StringBuilder sb = new StringBuilder();
        fieldsList.stream().forEach(field -> sb.append("i.").append(field.trim()).append(", "));
        String select = sb.toString();
        return select.substring(0, select.lastIndexOf(","));
    }
}
