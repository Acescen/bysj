package nuc.bysjxtglxt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nuc.bysjxtglxt.domain.NucRecord;
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
    
public interface NucRecordMapper extends BaseMapper<NucRecord> {


    Integer findByUserId(@Param("userId") Long userId);

    IPage<NucTopic> findMySlelect(Page<NucTopic> page, String any,@Param("userId") Long userId);

    boolean deleteSel(Long id);

}