package com.example.springreacthotelmanagement.service.interfac;

import com.example.springreacthotelmanagement.dto.Response;
import com.example.springreacthotelmanagement.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomid, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancleBooking(Long bookingId);
}
