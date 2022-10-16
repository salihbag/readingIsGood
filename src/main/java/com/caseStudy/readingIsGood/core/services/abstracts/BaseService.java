package com.caseStudy.readingIsGood.core.services.abstracts;

import com.caseStudy.readingIsGood.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
public abstract class BaseService {

    @Autowired
    protected ModelMapperService modelMapperService;

    public <D> D forResponseMap(Object source, Class<D> destination) {
        return modelMapperService.forResponseMap(source, destination);
    }
    public <D> D forRequestMap(Object source, Class<D> destination) {
        return modelMapperService.forRequestMap(source, destination);
    }
    public <S, D> List<D> forResponseMapList(List<S> source, Class<D> destination) {
        return modelMapperService.forResponseMapList(source, destination);
    }

}
