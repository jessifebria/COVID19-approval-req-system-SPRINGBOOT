/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.Question;
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
public interface QuestionRepository extends JpaRepository<Question ,Integer> {
    
    @Modifying
    @Query(value = "SELECT * FROM question WHERE Isactive='True'", nativeQuery = true)
    List<Question> findactive();
    
    @Modifying
    @Query(value = "SELECT * FROM question WHERE Section='Covid' AND Isactive='True'", nativeQuery = true)
    List<Question> findcovidactive();
    
     @Modifying
    @Query(value = "SELECT * FROM question WHERE Section='Urgency' AND Isactive='True'", nativeQuery = true)
    List<Question> findurgencyactive();
    
     @Modifying
    @Query(value = "SELECT * FROM question WHERE Isactive='False'", nativeQuery = true)
    List<Question> findnonactive();
    
    @Modifying
    @Query(value = "UPDATE question SET Pic=:user, Isactive='False' WHERE Id=:id", nativeQuery = true)
    void ubahkenonaktif(@Param("user") String user,@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE question SET Pic=:user, Isactive='True' WHERE Id=:id", nativeQuery = true)
    void ubahkeaktif(@Param("user") String user,@Param("id") Integer id);
}

