/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.RapidResult;
import java.util.Collection;
import java.util.List;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
public interface RapidResultRepository extends JpaRepository<RapidResult ,Integer>{
    
    @Modifying
    @Query(value = "SELECT * FROM rapid_result WHERE Isactive='True'", nativeQuery = true)
    List<RapidResult> findactive();
    
     @Modifying
    @Query(value = "SELECT * FROM rapid_result WHERE Isactive='False'", nativeQuery = true)
    List<RapidResult> findexpired();
    
    @Modifying
    @Query(value = "SELECT * FROM rapid_result WHERE Isactive='True' AND Result='Reaktif'", nativeQuery = true)
    List<RapidResult> findreaktif();
    
    @Modifying
    @Query(value = "SELECT * FROM rapid_result WHERE Isactive='True' AND Result='Positif'", nativeQuery = true)
    List<RapidResult> findpositif();
    
     @Modifying
    @Query(value = "SELECT * FROM rapid_result WHERE Result='Positif'", nativeQuery = true)
    List<RapidResult> hispositif();
    
    @Modifying
    @Query(value = "UPDATE rapid_result SET Isactive='False' WHERE Isactive='True'", nativeQuery = true)
    void changetofalse();
 
    @Query(value = "SELECT COUNT(*) FROM rapid_result WHERE Isactive='True' AND Employee=:emp", nativeQuery = true)
    int countemptrue(@Param("emp") String emp);
    
    @Query(value = "SELECT COUNT(*) FROM rapid_result WHERE Isactive='True'", nativeQuery = true)
    int countrapidtrue();
    
}
