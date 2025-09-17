package com.example.tp1singleviewapp.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.tp1singleviewapp.model.User;

public class UserViewModel extends BaseObservable {

    private User user;
    @Bindable
    public String getName() {
        return user.getName();
    }

}
