package nuc.bysjxtglxt.controller;

import nuc.bysjxtglxt.domain.BysjResponse;
import nuc.bysjxtglxt.domain.NucUser;
import nuc.bysjxtglxt.service.NucTopicService;
import nuc.bysjxtglxt.service.NucUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.controller
 * @Description:
 * @Date: Created in 19:52 2019-12-16
 */
@Controller
public class LoginController {
    @Autowired
    private NucUserService nucUserService;

    @Autowired
    private NucTopicService nucTopicService;

    @PostMapping("/login")
    @ResponseBody
    public BysjResponse login(NucUser nucUser)  {

        System.out.println("待检测账户" + nucUser);
        BysjResponse bysjResponse = new BysjResponse();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(nucUser.getStuOrWorId(), nucUser.getPassword());

        try {
            //登录
            subject.login(token);
            NucUser resUser = nucUserService.findNucUserByStuOrWorId(nucUser.getStuOrWorId());
            //去除密码
            resUser.setPassword("");
            // 将用户的角色和权限发送到前台
            return bysjResponse.success().put("user", resUser);
        } catch (IncorrectCredentialsException e) {
            return bysjResponse.fail().message("账号或密码错误！");
        } catch (LockedAccountException e) {
            return bysjResponse.fail().message("登录失败，该用户已被冻结！");
        } catch (AuthenticationException e) {
            //用户不存在
            return bysjResponse.fail().message("账号或密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bysjResponse.fail().message("登录失败！");
    }

    /**
     * 获取首页数据
     * @return
     */
    @RequestMapping("/indexData")
    @ResponseBody
    public BysjResponse index() {
        BysjResponse bysjResponse = new BysjResponse();
        Integer selectTopic = nucTopicService.getSelCount();
        Integer noSelTopic = nucTopicService.getNoSelCount();
        Integer selectStudent = nucUserService.getSelCount();
        Integer noSelStudent = nucUserService.getNoSelCount();
        bysjResponse.success().put("selectTopic", selectTopic).put("noSelTopic", noSelTopic).put("selectStudent", selectStudent).put("noSelStudent", noSelStudent);
        return bysjResponse;
    }
}
