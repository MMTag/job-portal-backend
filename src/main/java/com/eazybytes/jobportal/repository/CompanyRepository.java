package com.eazybytes.jobportal.repository;

import com.eazybytes.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Query("SELECT DISTINCT c FROM Company c JOIN FETCH c.jobs j WHERE j.status = :status")
    List<Company> findAllWithJobsByStatus(@Param("status") String status);

    //Native queries give the ease of using SQL directly but do not fix N+1 issue, nor do they return correct result without a few
    //additional annotations in entity classes like @SQLRestriction
    @Query(value = "SELECT DISTINCT c.* FROM companies c JOIN jobs j ON c.id = j.company_id WHERE j.status = ?", nativeQuery = true)
    List<Company> findAllWithJobsByStatusNative(@Param("status") String status);
}