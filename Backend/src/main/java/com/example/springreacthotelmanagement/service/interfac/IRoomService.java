package com.example.springreacthotelmanagement.service.interfac;

import com.example.springreacthotelmanagement.dto.Response;
import com.example.springreacthotelmanagement.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    Response addNewRoom( String roomType, BigDecimal roomPrice, String description);

    List<String> getALlRoomTypes();

    Response getAllRooms();

    Response deleteRoom(Long roomId);

    Response updateRoom(Long roomId,String description, String roomType,BigDecimal roomPrice);

    Response getRoomById(Long roomId);

    Response getAvailableRoomsByDateAndType(LocalDate checkInDate,LocalDate checkOutDate, String roomType);

    Response getAllAvailableRooms();
}
