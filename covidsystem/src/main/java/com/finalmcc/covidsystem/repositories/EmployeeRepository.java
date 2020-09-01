/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JESSI
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee ,String>{
    
    @Modifying
    @Query(value = "SELECT * FROM employee WHERE Department=:id", nativeQuery = true)
    List<Employee> empdept(@Param("id") String id);
    
    @Query(value = "SELECT COUNT(Id) FROM employee WHERE Department=?1", nativeQuery = true)
    int countdept(String id);
    
    @Query(value = "SELECT COUNT(Id) FROM employee", nativeQuery = true)
    int countemp();
}
