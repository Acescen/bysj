package nuc.bysjxtglxt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.util.QueryRequest;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.bysjxtglxt.mapper.NucTopicMapper;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.service.NucTopicService;
/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.service.impl
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
@Service
public class NucTopicServiceImpl extends ServiceImpl<NucTopicMapper, NucTopic> implements NucTopicService{

    @Override
    public IPage<NucTopic> findNucTopicListByAny(String any, QueryRequest request) {
        Page<NucTopic> page = new Page<>(request.getPageNum(), request.getPageSize());

        return baseMapper.findNucTopicListByAny(page,any);
    }

    @Override
    public Integer getSelCount() {
        return baseMapper.getSelCount();
    }

    @Override
    public Integer getNoSelCount() {
        return baseMapper.getNoSelCount();
    }
}
