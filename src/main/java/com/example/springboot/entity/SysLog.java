package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@TableName("sys_log")
@ApiModel(value = "SysLog对象", description = "")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("操作人员")
      private String username;

      @ApiModelProperty("操作")
      private String operation;

      @ApiModelProperty("方法名")
      private String method;

      @ApiModelProperty("参数")
      private String params;

      @ApiModelProperty("ip地址")
      private String ip;

      @ApiModelProperty("操作时间")
      private Date createDate;

      @ApiModelProperty("是否删除")
      private Boolean isDelete;

}
