package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.function.Function;

public interface WindowDao extends JpaRepository<WindowEntity, Long>, WindowDaoCustom {

    @Query("select m from WindowEntity m where m.name=:name")
    WindowEntity findByName(@Param("name") String name);

    @Query("select m from WindowEntity m where m.windowStatus=:windowStatus")
    List<WindowEntity> findByWindowStatus(@Param("windowStatus") SensorEntity windowStatus);

    @Modifying
    @Query("delete from WindowEntity m where m.room.id = ?1")
    void deleteWindowsByRoomId(Long roomId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE SensorEntity m " +
            "SET m.value = :windowValue " +
            "WHERE m.id IN (SELECT w.windowStatus.id FROM WindowEntity w WHERE w.room.id = :roomId)")
    void updateWindowStatusByRoomId(@Param("roomId") Long roomId, @Param("windowValue") Double windowValue);


}
