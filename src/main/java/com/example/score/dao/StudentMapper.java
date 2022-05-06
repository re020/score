package com.example.score.dao;

import com.example.score.entity.AllScore;
import com.example.score.entity.ClassTeacher;
import com.example.score.entity.Student;
import com.example.score.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 通过班级id 学年 学期 获取教师
     * @param classId
     * @param acaYear
     * @param seme
     * @return
     */
    List<ClassTeacher> selectTe(@Param("classId") int classId,@Param("acaYear") String acaYear,@Param("seme") int seme );

    /**
     * 通过教师id获取教师姓名
     * @param teId
     * @return
     */
    Teacher selectTeName(int teId);

    /**
     * 获取学生用户密码
     * @param stuNum
     * @return
     */
    String getStuPassWd(String stuNum);

    /**
     * 通过学号获取班级
     * @param stuNum
     * @return
     */
    Integer getClassIdByStuId(String stuNum);

    AllScore getByStuNum(String stuNum);

    Student getStudentByUsername(@Param("sno") String username);
}
