package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程列表查询
     */

    List<Course> findAllCourse(CourseVo courseVo);

    /*
        新增课程及讲师信息
     */
    void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
        根据id查询课程信息
     */
    CourseVo findCourseById(Integer id);

    /*
        修改课程信息
     */
    void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
        课程状态管理
     */
    void updateCourseStatus(int id, int status);
}
