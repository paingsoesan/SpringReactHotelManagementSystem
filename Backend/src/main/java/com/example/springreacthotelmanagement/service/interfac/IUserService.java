package com.example.springreacthotelmanagement.service.interfac;


import com.example.springreacthotelmanagement.dto.LoginRequest;
import com.example.springreacthotelmanagement.dto.Response;
import com.example.springreacthotelmanagement.entity.User;

public interface IUserService {

    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String  userId);

    Response getMyInfo(String email);

}
