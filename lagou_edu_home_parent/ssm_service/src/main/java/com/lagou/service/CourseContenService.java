package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContenService {

    /*
        根据课程id查询课程内容（章节+课时信息）
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        回显章节对应的课时信息
     */
    Course findCourseByCourseId(Integer courseId);

    /*
        新增章节信息
    */
    void saveSection(CourseSection courseSection);

    /*
        更新章节信息
     */
    void updateSection(CourseSection courseSection);

    /*
        更新章节状态
     */
    void updateSectionStatus(int id,int status);

    /*
        新增章节信息
     */
    void saveLesson(CourseLesson courseLesson);
}
