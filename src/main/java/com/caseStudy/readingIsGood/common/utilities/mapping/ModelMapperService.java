package com.caseStudy.readingIsGood.common.utilities.mapping;

import java.util.List;

public interface ModelMapperService {
    <D> D forResponseMap(Object source, Class<D> destinationType);
    <S, D> List<D> forResponseMapList(List<S> source, Class<D> destinationType);
    <D> D forRequestMap(Object source, Class<D> destinationType);
}
