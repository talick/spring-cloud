package com.github.talick.photoappusersapi.shared;

import com.github.talick.photoappusersapi.ui.model.AlbumResponseModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1039447109761163732L;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;
}
