package nuc.bysjxtglxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.authentication.NucUserEncry;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.service.NucTopicService;
import nuc.bysjxtglxt.service.NucUserService;
import nuc.bysjxtglxt.util.QueryRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.controller
 * @Description:
 * @Date: Created in 17:47 2019-12-17
 */
@Controller
@RequestMapping("/nucTopic")
public class NucTopicController {

    @Autowired
    private NucTopicService nucTopicService;

    /**
     * 添加题目
     *
     * @param nucTopic
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public BysjResponse add(@Valid NucTopic nucTopic) {
        try {
//获取当前登录用户信息
            NucUser user = (NucUser) SecurityUtils.getSubject().getPrincipal();
            nucTopic.setUserId(user.getUserId());
            nucTopic.setUserName(user.getName());
            nucTopic.setPhoneNum(user.getPhoneNum());
            nucTopic.setCreateTime(new Date());
            nucTopic.setStatus("未被选择");

            nucTopicService.save(nucTopic);
            return new BysjResponse().success();
        } catch (Exception e) {
            return new BysjResponse().fail().message("添加失败，请联系管理员");
        }
    }

    /**
     * 查看题目列表
     *
     * @param request
     * @param any
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public BysjResponse topicList(QueryRequest request, String any) {
        BysjResponse bysjResponse = new BysjResponse();

        IPage<NucTopic> list = nucTopicService.findNucTopicListByAny(any, request);

        bysjResponse.put("total", list.getTotal()).data(list.getRecords());

        return bysjResponse;

    }


    /**
     * 修改题目
     *
     * @param nucTopic
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public BysjResponse updateTopic(@Valid NucTopic nucTopic) {

        System.out.println(nucTopic);
        //保存
        boolean b = nucTopicService.updateById(nucTopic);
        BysjResponse bysjResponse = new BysjResponse();
        if (b) {
            bysjResponse.success();
        } else {
            bysjResponse.fail();
        }
        return bysjResponse;
    }


    /**
     * 删除题目
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public BysjResponse deleteTopic(String id) {
        boolean b = nucTopicService.removeById(id);
        BysjResponse bysjResponse = new BysjResponse();
        if (b) {
            bysjResponse.success();
        } else {
            bysjResponse.fail();
        }
        return bysjResponse;
    }
}
