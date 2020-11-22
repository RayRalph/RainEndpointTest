package io.swagger.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.data.dao.IndividualDao;
import io.swagger.data.persistance.IndividualRepository;
import io.swagger.model.Individual;
import io.swagger.model.IndividualCreate;
import io.swagger.model.IndividualUpdate;
import io.swagger.service.IndividualService;
import io.swagger.utils.JsonString;
import io.swagger.utils.PatchComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
public class IndividualApiController implements IndividualApi {
    private static final Logger log = LoggerFactory.getLogger(IndividualApiController.class);
    private static final String ACCEPT = "Accept";
    private static final String NOT_FOUND_MSG = "Unable to find an Individual with the id of: [%s]";
    private static final String REQUIRED_FIELD_EMPTY_MSG = "Required field [%s] has not been provided";
    private static final String JSON = "application/json";
    private static final String SERIALIZE_ERROR = "Couldn't serialize response for content type application/json";

    @Autowired
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private JsonString jsonString;
    @Autowired
    private final IndividualRepository individualRepository;
    @Autowired
    PatchComparator comparator;
    @Autowired
    private final IndividualService individualService;

    @org.springframework.beans.factory.annotation.Autowired
    public IndividualApiController(ObjectMapper objectMapper, HttpServletRequest request, IndividualRepository individualRepository, IndividualService individualService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.individualRepository = individualRepository;
        this.individualService = individualService;
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }


    @RequestMapping(value = "/individual/{id}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.POST)
    ResponseEntity<Void> createDatabase() {
        individualService.databaseCreate();
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<Individual> createIndividual(@ApiParam(value = "The Individual to be created", required = true) @Valid @RequestBody IndividualCreate individual) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            if (null == individual.getId() || individual.getId().isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "id"));
            }

            if (null == individual.getFullName() || individual.getFullName().isEmpty()) {
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), String.format(REQUIRED_FIELD_EMPTY_MSG, "fullName"));
            }
            try {
                IndividualDao entity = IndividualCreate.mapModelToEntity(individual);
                individualRepository.save(entity);
                String json = jsonString.toJsonString(entity);
                return new ResponseEntity<Individual>(objectMapper.readValue(json, Individual.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }
    @RequestMapping(value = "/individual/{id}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String id) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            IndividualDao individual = individualRepository.findByIdString(id);
            if (null == individual) {
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, id));
            }
            individualRepository.deleteByIdString(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
    @RequestMapping(value = "/individual",
            produces = { "application/json;charset=utf-8" },
            method = RequestMethod.GET)
    public ResponseEntity<List<Individual>> listIndividual(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String
                                                                   fields, @ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer
                                                                   offset, @ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer
                                                                   limit) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                List<Individual> individualDtos = new ArrayList();
                List<IndividualDao> individuals = new ArrayList();
                StringBuilder sb = new StringBuilder();
                if(null == limit){
                    limit = 0;
                }
                if(null == offset){
                    offset = 0;
                }
                if(null != fields){
                    individuals = individualService.listIndividualWithFields(fields, limit, offset);
                    individuals.stream().forEach(ind ->{
                        String map = jsonString.toParameterizedJsonString(ind);
                        sb.append(map).append(", ");
                    });
                }else {
                    if(limit > 0){
                        individuals = individualService.listIndividualWithLimits(limit, offset);
                    } else {
                        individuals = individualRepository.findAll();
                    }
                    individuals.stream().forEach(ind ->{
                        sb.append(jsonString.toJsonString(ind)).append(", ");
                    });
                }
                String values = sb.toString();
                return new ResponseEntity<List<Individual>>(objectMapper.readValue(String.format("[ %s ]",
                        values.substring(0, values.lastIndexOf(","))), List.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<List<Individual>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Individual>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/individual/{id}",
            produces = { "application/json;charset=utf-8" },
            method = RequestMethod.PATCH)
    public ResponseEntity<Individual> patchIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String id, @ApiParam(value = "The Individual to be updated", required = true) @Valid @RequestBody IndividualUpdate
            individual) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                IndividualDao dao = individualRepository.findByIdString(id);
                if (null == dao) {
                    throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, id));
                }
                IndividualCreate dto = comparator.compareForPatch(IndividualCreate.mapEntityToModel(dao), individual);
                IndividualDao entity = IndividualCreate.mapModelToEntity(dto);
                individualRepository.save(entity);
                return new ResponseEntity<Individual>(objectMapper.readValue(jsonString.toJsonString(entity), Individual.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }
    @RequestMapping(value = "/individual/{id}",
            produces = { "application/json;charset=utf-8" },
            method = RequestMethod.GET)
    public ResponseEntity<Individual> retrieveIndividual(@ApiParam(value = "Identifier of the Individual", required = true) @PathVariable("id") String
                                                                 id, @ApiParam(value = "Comma-separated properties to provide in response") @Valid @RequestParam(value = "fields", required = false) String
                                                                 fields) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(JSON)) {
            try {
                String msg = getJsonMessage(id, fields);
                return new ResponseEntity<Individual>(objectMapper.readValue(msg, Individual.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(SERIALIZE_ERROR, e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

    private String getJsonMessage(String id,String fields){
        IndividualDao individual = null;
        if (null != fields) {
            individual = individualService.retrieveIndividualWithFields(id, fields);
            if (null == individual) {
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, id));
            }
            return jsonString.toParameterizedJsonString(individual);
        } else {
            individual = individualRepository.findByIdString(id);
            if (null == individual) {
                throw new NotFoundException(HttpStatus.NOT_FOUND.value(), String.format(NOT_FOUND_MSG, id));
            }
            return jsonString.toJsonString(individual);
        }

    }
}
