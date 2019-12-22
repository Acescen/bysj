package nuc.bysjxtglxt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.domain
 * @Description:
 * @Date: Created in 11:13 2019-12-22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "nuc_record")
public class NucRecord {

    /**
     * topicId
     */
    @TableId(value = "topic_id")
    private Long topicId;

    /**
     * userId
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
