package com.digilbum.app.repositorys;

import com.digilbum.app.models.Picture;
import org.springframework.data.repository.CrudRepository;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
}