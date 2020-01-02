package nuc.bysjxtglxt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import nuc.bysjxtglxt.authentication.NucUserEncry;
import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.service.NucUserService;
import nuc.bysjxtglxt.util.QueryRequest;
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
@RequestMapping("/nucUser")
public class NucUserController {

    @Autowired
    private NucUserService nucUserService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/regist")
    @ResponseBody
    public BysjResponse regist(@Valid NucUser user) {
        try {
            //先判断该学号或者工号是否注册
            NucUser result = nucUserService.findNucUserByStuOrWorId(user.getStuOrWorId());
            if (result != null) {
                return new BysjResponse().fail().message("该账号已注册");
            }
            //将用户密码加密
            user = NucUserEncry.encry(user);
            //设置用户注册时间
            user.setCreateTime(new Date());
            nucUserService.save(user);

            return new BysjResponse().success();
        } catch (Exception e) {
            return new BysjResponse().fail().message("注册失败，请联系管理员");
        }
    }

    /**
     * 查看用户列表
     * @param request
     * @param any
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public BysjResponse userList(QueryRequest request, String any) {
        BysjResponse bysjResponse = new BysjResponse();

        IPage<NucUser> list = nucUserService.findNucUserListByAny(any, request);

        bysjResponse.put("total", list.getTotal()).data(list.getRecords());


        return bysjResponse;

    }


    /**
     * 修改用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public BysjResponse updateUser(@Valid NucUser user) {
        if ("".equals(user.getPassword())) {
            user.setPassword(null);
        } else {
            //将密码加密
            user = NucUserEncry.encry(user);
            //9865fbbcbba15ab9ecc344b854f35747
        }
        //保存
        boolean b = nucUserService.updateById(user);
        BysjResponse bysjResponse = new BysjResponse();
        if (b){
            bysjResponse.success();
        }else {
            bysjResponse.fail();
        }
        return bysjResponse;
    }

    /**
     *  删除用户
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public BysjResponse deleteUser(String id) {
        boolean b = nucUserService.removeById(id);
        BysjResponse bysjResponse = new BysjResponse();
        if (b){
            bysjResponse.success();
        }else {
            bysjResponse.fail();
        }
        return bysjResponse;
    }
}
