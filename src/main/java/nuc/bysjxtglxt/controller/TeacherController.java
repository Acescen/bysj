package nuc.bysjxtglxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.service.TeacherService;
import nuc.bysjxtglxt.util.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.controller
 * @Description:
 * @Date: Created in 21:27 2019-12-21
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查看我发布的题目
     * @param request
     * @param any
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public BysjResponse topicList(QueryRequest request, String any) {
        BysjResponse bysjResponse = new BysjResponse();

        IPage<NucTopic> list = teacherService.findMyTopicByAny(any, request);

        bysjResponse.put("total", list.getTotal()).data(list.getRecords());

        return bysjResponse;

    }
}
