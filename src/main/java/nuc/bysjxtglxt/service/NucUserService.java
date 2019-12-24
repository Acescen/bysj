package nuc.bysjxtglxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.domain.NucUser;
import com.baomidou.mybatisplus.extension.service.IService;
import nuc.bysjxtglxt.util.QueryRequest;

import java.util.List;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.service
 * @Description:
 * @Date: Created in 9:45 2019-12-17
 */

public interface NucUserService extends IService<NucUser> {


    NucUser findNucUserByPhoneNum(String phoneNum);

    NucUser findNucUserByStuOrWorId(String stuOrWorId);

    IPage<NucUser> findNucUserListByAny(String any, QueryRequest request);

    Integer getSelCount();

    Integer getNoSelCount();

}
