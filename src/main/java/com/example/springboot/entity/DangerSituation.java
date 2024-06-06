package com.example.springboot.entity;
//yzy
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("danger_situation")
public class DangerSituation {
    @TableId(type= IdType.AUTO)
    private Integer dangerId;
    private String createTime;
    private String address;
    private String camera;
    private String imgUrl;
    private Integer appealState;
    private String solvePerson;
    private Boolean isSolved;
    private String solveTime;
    private String appealContent;
    private String appellant;
    private String staffNumber;
    private Integer alarmId;
}
