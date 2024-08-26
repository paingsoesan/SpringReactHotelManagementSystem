package com.example.springreacthotelmanagement.service.impl;

import com.example.springreacthotelmanagement.dto.Response;
import com.example.springreacthotelmanagement.dto.RoomDto;
import com.example.springreacthotelmanagement.entity.Room;
import com.example.springreacthotelmanagement.exception.OurException;
import com.example.springreacthotelmanagement.repository.BookingRepository;
import com.example.springreacthotelmanagement.repository.RoomRepository;
import com.example.springreacthotelmanagement.service.interfac.IRoomService;
import com.example.springreacthotelmanagement.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

//    @Autowired
//    private AwsS3Service awsS3Service;

    @Override
    public Response addNewRoom( String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();

        try{
//            String imageUrl = awsS3Service.saveImageToS3(photo);
            Room room = new Room();

//            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);
            Room savedRoom = roomRepository.save(room);
            RoomDto roomDto = Utils.mapRoomEntityToRoomDTO(savedRoom);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomDto);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getALlRoomTypes() {
         return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response getAllRooms() {
        Response response = new Response();

        try{
            List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<RoomDto> roomDtoList = Utils.mapRoomListEntityToRoomListDto(roomList);

            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomDtoList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {
        Response response = new Response();

        try{
            roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
            roomRepository.deleteById(roomId);
            response.setStatusCode(200);
            response.setMessage("Delete Successful");
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateRoom(Long roomId,String description, String roomType, BigDecimal roomPrice) {
        Response response = new Response();

        try{
//            String imageUrl = null;
//            if(photo != null && !photo.isEmpty()){
//                imageUrl = awsS3ervice.saveImageToS3(photo);
//            }
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
            if(roomType != null) room.setRoomType(roomType );
            if(roomPrice != null) room.setRoomPrice(roomPrice );
            if(description != null) room.setRoomDescription(description );
//            if(imageUrl != null) room.setRoomType(imageUrl );

            Room updatedRoom = roomRepository.save(room);
            RoomDto roomDto = Utils.mapRoomEntityToRoomDTO(updatedRoom);

            response.setStatusCode(200);
            response.setMessage("Delete Successful");
            response.setRoom(roomDto);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getRoomById(Long roomId) {
        Response response = new Response();
        try{
           Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room Not Found"));
           RoomDto roomDto = Utils.mapRoomEntityToRoomDTOPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("Delete Successful");
            response.setRoom(roomDto);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
          Response response = new Response();
        try{
            List<Room> roomList = roomRepository.findAvailableRoomsByDatesAndTypes(checkInDate,checkOutDate,roomType);
            List<RoomDto> roomDtoList = Utils.mapRoomListEntityToRoomListDto(roomList);
            response.setStatusCode(200);
            response.setMessage("Delete Successful");
            response.setRoomList(roomDtoList);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableRooms() {
        Response response = new Response();
        try{
            List<Room> roomList = roomRepository.getAllAvailableRooms();
            List<RoomDto> roomDtoList = Utils.mapRoomListEntityToRoomListDto(roomList);
            response.setStatusCode(200);
            response.setMessage("Delete Successful");
            response.setRoomList(roomDtoList);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room" + e.getMessage());
        }
        return response;
    }
}

