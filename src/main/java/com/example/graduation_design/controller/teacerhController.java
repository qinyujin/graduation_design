package com.example.graduation_design.controller;

import com.example.graduation_design.component.requestComponent;
import com.example.graduation_design.component.updateSuitable;
import com.example.graduation_design.entity.*;
import com.example.graduation_design.service.StudentService;
import com.example.graduation_design.service.TeacherService;
import com.example.graduation_design.service.UserService;
import com.example.graduation_design.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher/")
@Slf4j
public class teacerhController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private requestComponent  requestComponent;
    @Autowired
    private updateSuitable updateSuitable;

    @GetMapping("index")
    public Map index(){
        int tid=requestComponent.getUid();
        Teacher t=teacherService.getTeacherById(tid);
        log.debug("id:{}", t.getId());
        return Map.of("teacher", t, "students", t.getStudents(), "courses", t.getCourses());
    }

    /**
     * 添加老师
     * @param user
     * @return
     */
    @PostMapping("addTeacher")
    public Map postTeacher(@RequestBody User user) {
        User u=userService.getUserByNum(user.getNum());
        Teacher t=new Teacher();
     if(u==null){
         user.setPassword(encoder.encode(user.getPassword()));
         t.setUser(user);
         teacherService.addTeacher(t);
     }
     else throw new ResponseStatusException(HttpStatus.FORBIDDEN,"该老师已经存在!" );
     return Map.of("Teacher",t);
    }

    /**
     * 添加学生
     * @return
     */
    @PostMapping("postStu")
    public Map postStudent(@RequestBody User user){
        User u=userService.getUserByNum(user.getNum());
        Student t=new Student();
        if(u==null){
            user.setPassword(encoder.encode(user.getPassword()));
            t.setUser(user);
            studentService.addStudent(t);
        }
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN,"该学生已经存在!" );
        return Map.of("Teacher",t);
    }

    /**
     * 添加毕设方向
     * @param d
     * @return
     */
    @PostMapping("addDirection")
    public  Map addDirection(@RequestBody directions d){
        teacherService.addDirections(d);
        return Map.of("添加的方向", d);
    }

    /**
     * 全参更新学生
     * @return
     */
    @PatchMapping("patchStu/{sid}")
    public Map patchStu(@RequestBody Student student,@PathVariable int sid){
        studentService.updateStu(sid,student);
        return Map.of("students",student);
    }

    /**
     * 更新方向
     * @param d
     * @return
     */
    @PatchMapping("updateDirection")
    public String updateDirection(@RequestBody directions d){
        if(teacherService.getDirectionById(d.getId())!=null){
        teacherService.updateDirection(d.getId(),d.getDetail());
        return d.getDetail();}
        else  return "未找到该方向";
    }

    /**
     * 创建课程
     * @param course
     * @return
     */
    @PostMapping("addCourses")
    public Map addCourses(@RequestBody Course course){
        course.setTeacher(teacherService.getTeacherByNum("1001"));
        if(course.getWeight()>=1)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"权重应该在0-1之间");
        courseService.addCourse(course);
        return Map.of("course",course);
    }


    /**
     * 设置权重
     * @param course
     * @return
     */
    @PostMapping("setWeight")
    public Map setCourseWeightAndLowest(@RequestBody Course course){
        courseService.setWeight(course.getLowestScore(),course.getWeight(), course.getId());
        updateSuitable.update();
        return Map.of("sucees","yes");
    }

    /**
     * 添加学生成绩单
     * @param sc
     * @param cid
     * @param sid
     * @return
     */
    @PostMapping("addStuScore/course/{cid}/student/{sid}")
    public selectedCourses addStuScore(@RequestBody selectedCourses sc,@PathVariable int cid,@PathVariable int sid){
        Student s= studentService.getStudentById(sid);
        if(s==null)throw new  ResponseStatusException(HttpStatus.BAD_REQUEST,"学生不存在");
        courseService.addStu(cid, sc.getScore(), sid);
        return sc;
    }

    /**
     * 修改学生课程成绩
     * @param sc
     * @param cid
     * @param sid
     * @return
     */
    @PatchMapping("updateStuScore/course/{cid}/student/{sid}")
    public Map updateStuScore(@RequestBody selectedCourses sc, @PathVariable int cid,@PathVariable int sid){
        Course c=courseService.getCourseById(cid);
        courseService.updateCourseStu(cid, sid, sc.getScore());
        return Map.of(c.getCourseName()+"课程分数改为：",sc.getScore());
    }

    /**
     * 添加选中学生
     * @param user
     * @param tid
     * @return
     */
    @PostMapping("{tid}/addStu")
    public String addStu(@RequestBody User user,@PathVariable int tid){
        User u=userService.getUserById(user.getId());
        teacherService.addTeacherStu(tid, user.getId());
        return  u.getName();
    }

    /**
     * 设置指导学生数
     * @param teacher
     * @return
     */
    @PatchMapping("patchAmount")
    public int setAmount(@RequestBody Teacher teacher){
        teacherService.updateTeacher(requestComponent.getUid(), teacher.getTotalStudents());
        return  teacher.getTotalStudents();
    }



}