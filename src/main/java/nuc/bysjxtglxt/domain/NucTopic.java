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
@TableName(value = "nuc_topic")
public class NucTopic implements Serializable {
    /**
     * topicId
     */
    @TableId(value = "topic_id", type = IdType.AUTO)
    private Long topicId;

    /**
     * title
     */
    @TableField(value = "title")
    @NotNull(message = "{required}")
    private String title;

    /**
     * introduction
     */
    @TableField(value = "introduction")
    @NotNull(message = "{required}")
    private String introduction;

    /**
     * details
     */
    @TableField(value = "details")
    private String details;

    /**
     * userId
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * userName
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * phoneNum
     */
    @TableField(value = "phone_num")
    private String phoneNum;

    /**
     * status
     */
    @TableField(value = "status")
    private String status;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private static final long serialVersionUID = 1L;


}