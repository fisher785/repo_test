package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /*
        对条件课程列表查询
     */
    List<Course> findAllCourse(CourseVo courseVo);

    /*
        新增课程信息
     */
    void saveCourse(Course course);

    /*
        新增讲师信息
     */
    void saveTeacher(Teacher teacher);

    /*
        根据id查询课程信息及关联的讲师信息
     */
    CourseVo findCourseById(Integer id);

    /*
        更新课程信息
     */
    void updateCourse(Course course);

    /*
        更新讲师信息
     */
    void updateTeacher(Teacher teacher);

    /*
        课程状态管理
     */
    void updateCourseStatus(Course course);
}
