package com.js.summary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.js.summary.typeHandler.ListTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName summary
 */
@TableName(value ="summary",resultMap = "BaseResultMap")
@Data
public class Summary implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "summary",typeHandler = ListTypeHandler.class)
    @ApiModelProperty(value = "summary")
    private List<Long> summary;

    private Integer version;
    private Integer testVersion;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}