package com.eazybytes.jobportal.repository;

import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
