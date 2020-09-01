/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JESSI
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department ,String>{
    
    @Modifying
    @Query(value = "UPDATE department SET Quota= ?1 WHERE Id= ?2 ", nativeQuery = true)
    void ubahquota(Integer quota, String id);
    
    
    @Query(value = "SELECT * FROM department WHERE Code= 'All' OR Code=?1 ", nativeQuery = true)
    List<Department> getgroupcode(String code);
    
    
}
