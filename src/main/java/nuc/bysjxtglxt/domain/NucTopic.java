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
    private String title;

    /**
     * introduction
     */
    @TableField(value = "introduction")
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
     * createTime
     */
    @TableField(value = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_TOPIC_ID = "topic_id";

    public static final String COL_TITLE = "title";

    public static final String COL_INTRODUCTION = "introduction";

    public static final String COL_DETAILS = "details";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CREATE_TIME = "create_time";
}