package nuc.bysjxtglxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.util.QueryRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.mapper
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
public interface NucUserMapper extends BaseMapper<NucUser> {
    NucUser findNucUserByPhoneNum(String phoneNum);

    NucUser findNucUserByStuOrWorId(String stuOrWorId);

    IPage<NucUser> findNucUserListByAny(Page page, @Param("any") String any);

    Integer getSelCount();

    Integer getNoSelCount();
}