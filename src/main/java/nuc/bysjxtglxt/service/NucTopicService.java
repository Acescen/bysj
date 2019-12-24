package nuc.bysjxtglxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.domain.NucTopic;
import com.baomidou.mybatisplus.extension.service.IService;
import nuc.bysjxtglxt.util.QueryRequest;

/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.service
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
public interface NucTopicService extends IService<NucTopic>{


        IPage<NucTopic> findNucTopicListByAny(String any, QueryRequest request);

    Integer getSelCount();

    Integer getNoSelCount();

}
