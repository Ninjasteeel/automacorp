package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;

public interface RoomDaoCustom {
    RoomEntity findById(Long id);
}
