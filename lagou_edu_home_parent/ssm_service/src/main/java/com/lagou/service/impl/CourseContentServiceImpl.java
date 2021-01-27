package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContenService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    // 根据课程id查询章节和课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    // 回显课程信息
    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    // 保存章节
    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    // 修改章节
    @Override
    public void updateSection(CourseSection courseSection) {
        // 补全信息
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);
    }

    // 修改章节状态
    @Override
    public void updateSectionStatus(int id,int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(courseSection);
    }

    // 增加课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseLesson.setIsDel(0);

        courseContentMapper.saveLesson(courseLesson);
    }
}
