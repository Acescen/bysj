package nuc.bysjxtglxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucTopic;
import nuc.bysjxtglxt.service.StudentService;
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
 * @Date: Created in 10:52 2019-12-22
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping("select")
    @ResponseBody
    public BysjResponse select(String topicId) {
        return studentService.select(topicId);
    }

    /**
     * 查看我选择的题目
     * @param request
     * @param any
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public BysjResponse topicList(QueryRequest request, String any) {
        BysjResponse bysjResponse = new BysjResponse();

        IPage<NucTopic> list = studentService.findMyTopicByAny(any, request);

        bysjResponse.put("total", list.getTotal()).data(list.getRecords());

        return bysjResponse;

    }

    /**
     *  删除选课
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public BysjResponse deleteTopic() {
        boolean b = studentService.deleteSel();
        BysjResponse bysjResponse = new BysjResponse();
        if (b) {
            bysjResponse.success();
        } else {
            bysjResponse.fail();
        }
        return bysjResponse;
    }

}

