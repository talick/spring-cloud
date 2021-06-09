package com.github.talick.photoappusersapi.service.impl;

import com.github.talick.photoappusersapi.data.AlbumServiceClient;
import com.github.talick.photoappusersapi.data.UserEntity;
import com.github.talick.photoappusersapi.repository.UsersRepository;
import com.github.talick.photoappusersapi.service.UsersService;
import com.github.talick.photoappusersapi.shared.UserDto;
import com.github.talick.photoappusersapi.ui.model.AlbumResponseModel;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //    private final RestTemplate restTemplate;
    private final AlbumServiceClient albumServiceClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UsersServiceImpl(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AlbumServiceClient albumServiceClient) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.albumServiceClient = albumServiceClient;
    }

    @Override
    public UserDto createUser(UserDto user) {
        user.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity = usersRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);
        if (userEntity == null) throw new UsernameNotFoundException(username);
        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailByEmail(String email) {

        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUsedId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

/*        String albumsUrl = String.format(environment.getProperty("albums.url"), userId);

        ResponseEntity<List<AlbumResponseModel>> albumListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {

        });
        List<AlbumResponseModel> albums = albumListResponse.getBody();*/

        List<AlbumResponseModel> albums = albumServiceClient.getAlbums(userId);
        userDto.setAlbums(albums);
        return userDto;
    }
}
