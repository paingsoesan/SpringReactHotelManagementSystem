package com.example.springreacthotelmanagement.utils;

import com.example.springreacthotelmanagement.dto.BookingDto;
import com.example.springreacthotelmanagement.dto.RoomDto;
import com.example.springreacthotelmanagement.dto.UserDto;
import com.example.springreacthotelmanagement.entity.Booking;
import com.example.springreacthotelmanagement.entity.Room;
import com.example.springreacthotelmanagement.entity.User;


import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <length; i++){
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static UserDto mapUserEntityToUserDTO(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhonNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static RoomDto mapRoomEntityToRoomDTO(Room room){
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setRoomPrice(room.getRoomPrice());
//        roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDto.setRoomDescription(room.getRoomDescription());
        return roomDto;
    }

    public static BookingDto mapBookingsEntityToBookingsDTO(Booking booking){
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setNumOfAdults(booking.getNumOfAdults());
        bookingDto.setNumOfChildren(booking.getNumOfChildren());
        bookingDto.setTotalNumberOfGuest(booking.getTotalNumberOfGuest());
        bookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        return bookingDto;
    }

    public static RoomDto mapRoomEntityToRoomDTOPlusBookings(Room room){
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setRoomPrice(room.getRoomPrice());
//        roomDto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDto.setRoomDescription(room.getRoomDescription());

        if(room.getBookings() != null){
            roomDto.setBookings(room.getBookings()
                    .stream().map(Utils::mapBookingsEntityToBookingsDTO).collect(Collectors.toList()));
        }

        return roomDto;
    }



    //user entity nae room entity ka relationship ma chake htr buu
    //user ka ny room ko lo chin yin booking.getRoom ka ny yuu ya ml
    public static UserDto mapUserEntityToUserDTOPlusUserBookingAndRooms(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhonNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());

        if(!user.getBookings().isEmpty()){
            userDto.setBookings(user.getBookings().stream()
                    .map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking,false))
                    .collect(Collectors.toList()));
        }
        return userDto;
    }

    //this method is to get room entity for mapUserEntityToUserDTOPlusUserBookingAndRooms method
    //mapUserEntityToUserDTOPlusUserBookingAndRooms cant get room entity because user and room have no relationship
    public static BookingDto mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setNumOfAdults(booking.getNumOfAdults());
        bookingDto.setNumOfChildren(booking.getNumOfChildren());
        bookingDto.setTotalNumberOfGuest(booking.getTotalNumberOfGuest());
        bookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        if (mapUser) {
            bookingDto.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
        }
        if (booking.getRoom() != null){
            RoomDto roomDto = new RoomDto();

            roomDto.setId(booking.getRoom().getId());
            roomDto.setRoomType(booking.getRoom().getRoomType());
            roomDto.setRoomPrice(booking.getRoom().getRoomPrice());
//            roomDto.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDto.setRoomDescription(booking.getRoom().getRoomDescription());
            bookingDto.setRoom(roomDto);
        }
        return bookingDto;
    }

    public static List<UserDto> mapUserListEntityToUserListDto(List<User> userList){
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }

    public static List<RoomDto> mapRoomListEntityToRoomListDto(List<Room> roomList){
        return roomList.stream().map(Utils::mapRoomEntityToRoomDTO).collect(Collectors.toList());
    }

    public static List<BookingDto> mapBookingListEntityToBookingListDto(List<Booking> bookingList){
        return bookingList.stream().map(Utils::mapBookingsEntityToBookingsDTO).collect(Collectors.toList());
    }

}
