package com.slepnev.stockphoto.service;

import com.slepnev.stockphoto.dao.PhotographerDao;
import com.slepnev.stockphoto.dto.CreatePhotographerDTO;
import com.slepnev.stockphoto.dto.PhotographerDTO;
import com.slepnev.stockphoto.exception.ValidationException;
import com.slepnev.stockphoto.mapper.CreatePhotographerMapper;
import com.slepnev.stockphoto.validator.CreatePhotographerValidator;

import java.util.List;

public class PhotographerService {

    private static final PhotographerService INSTANCE = new PhotographerService();

    private final CreatePhotographerValidator createPhotographerValidator = CreatePhotographerValidator.getInstance();
    private final PhotographerDao photographerDao = PhotographerDao.getInstance();
    private final CreatePhotographerMapper createPhotographerMapper = CreatePhotographerMapper.getInstance();

    private PhotographerService() {
    }

    public List<PhotographerDTO> findAll() {
        return photographerDao.findAll().stream()
                .map(photographer -> new PhotographerDTO(
                                photographer.getId(),
                                photographer.getPassword(),
                                photographer.getPhoneNumber(),
                                photographer.getSocialNetwork()
                        )
                ).toList();
    }

    public Integer create(CreatePhotographerDTO createPhotographerDTO) {
        var validationResult = createPhotographerValidator.isValid(createPhotographerDTO);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var photographerEntity = createPhotographerMapper.mapFrom(createPhotographerDTO);
        photographerDao.save(photographerEntity);
        return photographerEntity.getId();
    }

    public static PhotographerService getInstance() {
        return INSTANCE;
    }
}