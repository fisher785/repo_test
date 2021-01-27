package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 条件查询所有课程信息
    @Override
    public List<Course> findAllCourse(CourseVo courseVo) {
        return courseMapper.findAllCourse(courseVo);
    }

    // 添加课程及讲师信息
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);

        // 补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        course.setIsDel(0);

        // 保存课程
        courseMapper.saveCourse(course);

        // 获取新插入的id值
        int id = course.getId();

        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        // 保存讲师信息
        courseMapper.saveTeacher(teacher);

    }

    /*
        根据id查询课程信息
     */
    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);

        Date date = new Date();
        course.setUpdateTime(date);
        course.setIsDel(0);

        // 更新课程信息
        courseMapper.updateCourse(course);

        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);

        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        // 更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int id, int status) {

        // 1.封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }


}
