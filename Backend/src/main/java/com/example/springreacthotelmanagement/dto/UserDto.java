package com.example.springreacthotelmanagement.dto;

import com.example.springreacthotelmanagement.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private String phonNumber;
    private String role;
    private List<BookingDto> bookings = new ArrayList<>();
}
