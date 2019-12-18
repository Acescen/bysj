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
     * 用户角色
     */
    public static final String ADMIN = "0";
    public static final String TEACHER = "1";
    public static final String STUDENT = "2";
    /**
     * 状态
     */
    public static final String LOCK = "0";
    public static final String AVAILABLE = "1";
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
    private String userRole="2";

    /**
     * status
     */
    @TableField(value = "status")
    //默认可用
    private String status="1";

    /**
     * note
     */
    @TableField(value = "note")
    private String note;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PHONE_NUM = "phone_num";

    public static final String COL_PASSWORD = "password";

    public static final String COL_STU_OR_WOR_ID = "stu_or_wor_id";

    public static final String COL_NAME = "name";

    public static final String COL_USER_ROLE = "user_role";

    public static final String COL_STATUS = "status";

    public static final String COL_NOTE = "note";

    public static final String COL_CREATE_TIME = "create_time";


}