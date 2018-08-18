package com.chen.demo.services.imp;

import com.chen.demo.commons.BeanUtil;
import com.chen.demo.commons.IdWorker;
import com.chen.demo.mappers.BaseMapper;
import com.chen.demo.models.BaseModel;
import com.chen.demo.services.BaseService;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImp<E extends BaseModel> implements BaseService<E> {

    private BaseMapper<E> baseMapper;

    @Override
    public boolean add(E e) throws Exception{
        e.setId(IdWorker.getFlowIdWorkerInstance().nextId());
        Long time = System.currentTimeMillis();
        e.setCreateTime(time);
        e.setUpdateTime(time);
        e.setDeleted(false);
        e.setEnable(true);
        Map<String, Object> map = BeanUtil.objectToMap(e);
        return baseMapper.insert(map) > 0;
    }

    @Override
    public boolean update(E e) throws Exception{
        e.setUpdateTime(System.currentTimeMillis());
        e.setDeleted(null);
        e.setCreateTime(null);
        Map<String, Object> map = BeanUtil.objectToMap(e);
        map.remove("id");
        return baseMapper.update(map, e.getId()) > 0;
    }

    @Override
    public boolean delete(Long id){
        return baseMapper.delete(id) > 0;
    }

    @Override
    public E findByID(Long id){
        return baseMapper.findByID(id);
    }

    @Override
    public List<E> fetchAll(){
        return baseMapper.fetchAll();
    }

    public void setBaseMapper(BaseMapper<E> baseMapper) {
        this.baseMapper = baseMapper;
    }
}
