package nuc.bysjxtglxt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.mapper.NucTopicMapper;
import nuc.bysjxtglxt.service.NucTopicService;
import nuc.bysjxtglxt.service.TeacherService;
import nuc.bysjxtglxt.util.QueryRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.service.impl
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
@Service
public class TeacherServiceImpl extends ServiceImpl<NucTopicMapper, NucTopic> implements TeacherService {



    @Override
    public IPage<NucTopic> findMyTopicByAny(String any, QueryRequest request) {
        //获取当前登录用户信息
        NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();


        Page<NucTopic> page = new Page<>(request.getPageNum(), request.getPageSize());

        return baseMapper.findMyTopicByAny(page,any,user.getUserId());
    }
}
