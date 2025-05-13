package com.persistence;

import com.entities.Sales;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sales, String> {

    List<Sales> findAllbySalesId(String id);
}
