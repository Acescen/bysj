package nuc.bysjxtglxt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucRecord;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.util.QueryRequest;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.service
 * @Description:
 * @Date: Created in 9:45 2019-12-17
 */

public interface StudentService extends IService<NucRecord> {


    IPage<NucTopic> findMyTopicByAny(String any, QueryRequest request);

    BysjResponse select(String topicId);

    boolean deleteSel();

}
