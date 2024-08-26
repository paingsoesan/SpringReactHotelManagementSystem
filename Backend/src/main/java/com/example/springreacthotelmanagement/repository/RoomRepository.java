package com.example.springreacthotelmanagement.repository;

import com.example.springreacthotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("select distinct r.roomType from Room r")
    List<String> findDistinctRoomTypes();

    @Query("select r from Room r where r.roomType like %:roomType% and r.id not in " +
            "(select bk.room.id from Booking bk where " +
            "(bk.checkInDate <= :checkOutDate) and (bk.checkOutDate >= :checkInDate))")
    List<Room> findAvailableRoomsByDatesAndTypes(LocalDate checkInDate, LocalDate checkOutDate, String roomType);


    @Query("select r from Room r where r.id not in (select b.room.id from Booking b)")
    List<Room> getAllAvailableRooms();

}
