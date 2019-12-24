package nuc.bysjxtglxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nuc.bysjxtglxt.domain.NucTopic;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: jurui 
 * @Email: acescen@foxmail.com 
 *@ProjectName:    bysjxtglxt
 *@Package:    nuc.bysjxtglxt.mapper
 *@Description:
 *@Date: Created in 9:45 2019-12-17
 */
    
public interface NucTopicMapper extends BaseMapper<NucTopic> {
    IPage<NucTopic> findNucTopicListByAny(Page<NucTopic> page, @Param("any") String any);

    IPage<NucTopic> findMyTopicByAny(Page<NucTopic> page,  @Param("any")String any,  @Param("userId")Long userId);

    NucTopic findmyTopicById(@Param("topicId") String topicId);

    boolean updateTopicStatus(Long id);

    Integer getSelCount();

    Integer getNoSelCount();
}