/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.GroupCode;
import com.finalmcc.covidsystem.entities.GroupCodePK;
import java.util.Date;
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
public interface GroupCodeRepository extends JpaRepository<GroupCode, GroupCodePK> {

    @Query(value = "SELECT Startdate FROM group_code WHERE Day='Monday' AND Isactive='True'", nativeQuery = true)
    Date nextgroupdate();

    @Query(value = "SELECT COUNT(*) FROM group_code WHERE Code='A'", nativeQuery = true)
    int counta();

    @Query(value = "SELECT COUNT(*) FROM group_code WHERE Code='B'", nativeQuery = true)
    int countb();

    @Modifying
    @Query(value = "UPDATE group_code SET Isactive='False' WHERE Isactive='True' AND Startdate=:date", nativeQuery = true)
    void setfalse(@Param("date") Date date);

    @Query(value = "SELECT * FROM group_code ORDER BY StartDate, FIELD(Day,'Monday','Tuesday','Wednesday','Thursday','Friday') ", nativeQuery = true)
    List<GroupCode> getall();

    @Query(value = "SELECT Code FROM group_code WHERE Isactive='True' AND Day=:day", nativeQuery = true)
    String getdaygroup(@Param("day") String day);

    
    

}
