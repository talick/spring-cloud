package com.github.talick.photoappalbums.service;

import com.github.talick.photoappalbums.data.AlbumEntity;

import java.util.List;

public interface AlbumsService {

    List<AlbumEntity> getAlbums(String userId);
}
