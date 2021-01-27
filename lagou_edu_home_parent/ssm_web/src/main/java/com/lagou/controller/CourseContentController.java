package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {


    @Autowired
    private CourseContenService courseContenService;

    /*
        根据课程id查询课程信息
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContenService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true, 200, "章节及课时响应成功", list);
    }

    /*
        回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContenService.findCourseByCourseId(courseId);
        return new ResponseResult(true, 200, "查询课程信息成功", course);
    }

    /*
        新增或修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setState(200);
        // 判断是否携带了章节id
        if(courseSection.getId()==null) {
            courseContenService.saveSection(courseSection);
            responseResult.setMessage("新增章节成功");
        } else {
            courseContenService.updateSection(courseSection);
            responseResult.setMessage("修改章节成功");
        }
        return responseResult;
    }

    /*
        章节状态管理
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        courseContenService.updateSectionStatus(id,status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true,200,"修改章节状态成功", map);
    }

    /*
        新增课时信息
     */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson) {
        courseContenService.saveLesson(courseLesson);
        return new ResponseResult(true,200,"新增课时成功", null);
    }

}
