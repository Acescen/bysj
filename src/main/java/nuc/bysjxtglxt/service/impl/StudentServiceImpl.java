package nuc.bysjxtglxt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucRecord;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.mapper.NucRecordMapper;
import nuc.bysjxtglxt.mapper.NucTopicMapper;
import nuc.bysjxtglxt.service.StudentService;
import nuc.bysjxtglxt.service.TeacherService;
import nuc.bysjxtglxt.util.QueryRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.service.impl
 * @Description:
 * @Date: Created in 9:45 2019-12-17
 */

@Service
public class StudentServiceImpl extends ServiceImpl<NucRecordMapper, NucRecord> implements StudentService {
    @Autowired
    private NucTopicMapper topicMapper;


    @Override
    public IPage<NucTopic> findMyTopicByAny(String any, QueryRequest request) {
        //获取当前登录用户信息
        NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();


        Page<NucTopic> page = new Page<>(request.getPageNum(), request.getPageSize());

        return baseMapper.findMySlelect(page, any, user.getUserId());
    }


    @Override
    @Transactional
    public BysjResponse select(String topicId) {
        BysjResponse bysjResponse = new BysjResponse();
        //获取当前登录用户信息
        NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();

        //首先判断该用户是否已选择题目
        Integer count = baseMapper.findByUserId(user.getUserId());

        if (count != null && count > 0) {
            bysjResponse.fail().message("请到我的选题页面，先删除已经选择的题目！");
        } else{
            System.out.println("该同学尚未选择题目");
            //判断题目是否存在
            NucTopic nucTopic = topicMapper.findmyTopicById(topicId);

            if (nucTopic != null) {
                //判断题目是否被选择
                if ("未被选择".equals(nucTopic.getStatus())) {
                    //将题目状态改为选择
                    nucTopic.setStatus("已被选");
                    topicMapper.updateById(nucTopic);
                    //记录到选题表
                    NucRecord nucRecord = new NucRecord(nucTopic.getTopicId(), user.getUserId(), new Date());
                    int insert = baseMapper.insert(nucRecord);

                    if (insert > 0) {
                        bysjResponse.success().message("成功选取题目，请到我的选题界面查看！");
                    } else {
                        bysjResponse.fail().message("未知异常，选择失败！");

                    }
                } else {
                    bysjResponse.fail().message("该毕业设计题目已经被抢走了");
                }
            } else {
                bysjResponse.fail().message("该毕业设计题目不存在！");
            }
        }


        return bysjResponse;
    }

    @Override
    @Transactional
    public boolean deleteSel() {

        //获取当前登录用户信息
        NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();

        //将题目表中的题目状态更改为未被选择
        boolean upTop =topicMapper.updateTopicStatus(user.getUserId());

        //删除选题记录表中的数据
        boolean upRec =baseMapper.deleteSel(user.getUserId());

        if (upRec && upTop) {
            return true;
        } else {
            return false;
        }
    }
}
