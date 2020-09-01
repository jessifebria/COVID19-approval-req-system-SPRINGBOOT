/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalmcc.covidsystem.repositories;

import com.finalmcc.covidsystem.entities.Account;
import com.finalmcc.covidsystem.entities.RequestQuota;
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
public interface RequestQuotaRepository extends JpaRepository<RequestQuota, Integer> {

    @Modifying
    @Query(value = "SELECT * FROM request_quota WHERE Status='Pending'", nativeQuery = true)
    List<RequestQuota> findpending();

    @Modifying
    @Query(value = "SELECT * FROM request_quota WHERE Status='Accept'", nativeQuery = true)
    List<RequestQuota> findacc();

    @Modifying
    @Query(value = "SELECT * FROM request_quota WHERE Status='Reject'", nativeQuery = true)
    List<RequestQuota> findreject();

    @Modifying
    @Query(value = "UPDATE request_quota SET Pic= ?1, Status='Accept', Picnote= ?2" + " WHERE Id= ?3 ", nativeQuery = true)
    void acc(String user, String text, Integer id);

    @Modifying
    @Query(value = "UPDATE request_quota SET Pic= ?1, Status='Reject', Picnote= ?2" + " WHERE Id= ?3 ", nativeQuery = true)
    void rej(String user, String text, Integer id);

    @Modifying
    @Query(value = "INSERT INTO `final`.`request_quota` (`Quota`, `Manajernote`, `Manajer`, `Pic`, `Picnote`, `Status`) VALUES (?1, ?2, ?3, 'E0001', '', 'Pending')", nativeQuery = true)
    void savereq(Integer quota, String note, String manajer);

    @Modifying
    @Query(value = "SELECT * FROM request_quota WHERE Manajer=?1", nativeQuery = true)
    List<RequestQuota> findbydept(String manajer);

    @Query(value = "SELECT COUNT(Id) FROM request_quota WHERE Status='Pending' AND Manajer=?1", nativeQuery = true)
    int countactive(String manajer);

}
