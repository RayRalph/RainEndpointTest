package io.swagger.data.persistance;

import io.swagger.data.dao.IndividualDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IndividualRepository extends JpaRepository<IndividualDao, Integer> {

    @Query(value = "FROM IndividualDao i where i.id = :id")
    IndividualDao findByIdString(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM IndividualDao i where i.id = :id")
    void deleteByIdString(@Param("id") String id);


}
