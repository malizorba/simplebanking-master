package com.eteration.simplebanking.model.Mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper getMapperRequest();

    ModelMapper getMapperResponse();
}
