package com.yilan.awesome.service;

import com.yilan.awesome.base.PageResultVO;
import com.yilan.awesome.domain.criteria.ZooCriteria;
import com.yilan.awesome.domain.entity.Animal;
import com.yilan.awesome.domain.entity.Zoo;

import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
public interface ZooService {
    List<Zoo> getAll();

    Zoo getById(Long id);

    PageResultVO<Zoo> getByPage(ZooCriteria criteria);

    Zoo save(Zoo zoo);

    List<Zoo> saveBatch(List<Zoo> list);

    Zoo create(Zoo zoo);

    Zoo update(Zoo zoo);

    Boolean deleteById(Long id);

    List<Animal> getAllAnimals(Long zooId);

    Boolean deleteAnimal(Long zooId, Long animalId);
}
