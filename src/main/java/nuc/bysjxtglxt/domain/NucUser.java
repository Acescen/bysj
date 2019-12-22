package nuc.bysjxtglxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.pojo
 * @Description:
 * @Date: Created in 9:45 2019-12-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "nuc_user")
public class NucUser implements Serializable {


    /**
     * userId
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * phoneNum
     */
    @TableField(value = "phone_num")
    @NotNull(message = "{required}")
    private String phoneNum;

    /**
     * password
     */
    @TableField(value = "password")
    @NotNull(message = "{required}")
    private String password;

    /**
     * stuOrWorId
     */
    @TableField(value = "stu_or_wor_id")
    @NotNull(message = "{required}")
    private String stuOrWorId;

    /**
     * name
     */
    @TableField(value = "name")
    @NotNull(message = "{required}")
    private String name;

    /**
     * userRole
     */
    @TableField(value = "user_role")
    //默认为学生角色
    private String userRole="学生";

    /**
     * status
     */
    @TableField(value = "status")
    //默认可用
    private String status="可用";

    /**
     * note
     */
    @TableField(value = "note")
    private String note;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private static final long serialVersionUID = 1L;


}