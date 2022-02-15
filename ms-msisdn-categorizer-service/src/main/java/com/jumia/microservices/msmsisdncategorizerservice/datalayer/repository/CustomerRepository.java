package com.jumia.microservices.msmsisdncategorizerservice.datalayer.repository;

import com.jumia.microservices.msmsisdncategorizerservice.datalayer.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    List<CustomerModel> findAllByOrderByIdAsc();
}
