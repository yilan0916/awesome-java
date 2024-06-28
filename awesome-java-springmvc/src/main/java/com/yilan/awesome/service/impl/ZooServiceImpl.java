package com.yilan.awesome.service.impl;

import com.yilan.awesome.domain.criteria.ZooCriteria;
import com.yilan.awesome.domain.entity.Animal;
import com.yilan.awesome.domain.entity.Zoo;
import com.yilan.awesome.base.PageResultVO;
import com.yilan.awesome.service.ZooService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Service
public class ZooServiceImpl implements ZooService {
    @Override
    public List<Zoo> getAll() {
        return null;
    }

    @Override
    public Zoo getById(Long id) {
        return null;
    }

    @Override
    public PageResultVO<Zoo> getByPage(ZooCriteria criteria) {
        return null;
    }

    @Override
    public Zoo save(Zoo zoo) {
        return null;
    }

    @Override
    public List<Zoo> saveBatch(List<Zoo> list) {
        return null;
    }

    @Override
    public Zoo create(Zoo zoo) {
        return null;
    }

    @Override
    public Zoo update(Zoo zoo) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    @Override
    public List<Animal> getAllAnimals(Long zooId) {
        return null;
    }

    @Override
    public Boolean deleteAnimal(Long zooId, Long animalId) {
        return null;
    }
}
