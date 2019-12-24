package nuc.bysjxtglxt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nuc.bysjxtglxt.util.QueryRequest;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.mapper.NucUserMapper;
import nuc.bysjxtglxt.service.NucUserService;

import java.util.List;

/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.service.impl
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
@Service
public class NucUserServiceImpl extends ServiceImpl<NucUserMapper, NucUser> implements NucUserService{

    @Override
    public NucUser findNucUserByPhoneNum(String phoneNum) {
        return baseMapper.findNucUserByPhoneNum(phoneNum);
    }

    @Override
    public NucUser findNucUserByStuOrWorId(String stuOrWorId) {
        return baseMapper.findNucUserByStuOrWorId(stuOrWorId);

    }

    @Override
    public IPage<NucUser> findNucUserListByAny(String any, QueryRequest request) {
        Page<NucUser> page = new Page<>(request.getPageNum(), request.getPageSize());


        return baseMapper.findNucUserListByAny(page,any);
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
