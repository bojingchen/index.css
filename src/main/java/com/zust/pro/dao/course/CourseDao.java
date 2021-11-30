package com.zust.pro.dao.course;

import com.zust.pro.vo.Course;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.temper.teh_output;

import java.util.List;

/**
 * @author 86178
 */
public interface CourseDao {
    /**
     * 添加课程信息
     * @param course
     * @return boolean
     * **/
    public boolean addCourse(Course course);

    /**
     * 删除课程信息
     * @param course
     * @return boolean
     * **/
    public boolean delCourse(Course course);

    /**
     * 查找学了这个课程的所有学生
     * 通过查看课程的班级，然后到学生表里面找这个班级的学生
     * @param courseId
     * @return List
     * **/
    public List<Student> findAllStudentByCourse(int courseId);

    /**
     * 查找老师教了哪些课程
     * @param teacherId
     * @return List
     * **/
    public List<Course> findAllCourseFromTeacher(int teacherId);

    /**
     * 查看这个班级应该学哪些课程
     * @param className
     * @return List
     * **/
    public List<Course> findAllCourseFromClass(String className);

    /***
     * 管理员查看课程信息
     * @return List
     * */
    public List<Course> findCourses();

    /**
     * 根据课程id查看课程
     * @param course_id
     * @return course
     * **/
    public Course findCourseByCourseId(int course_id);

    /**
     * 根据老师id查看老师所教的学生
     * @param teacher_Id
     * @return List
     */
    public List<Student> findStudentByTeacherId(int teacher_Id);

    /**
     * 根据老师id查看老师所教的学生和课程
     * @param teacher_Id
     * @return List
     */
    public List<teh_output> findStu_CouByTeacherId(int teacher_Id);

    /**
     * 找到没有在week周请假的课程id的学生们
     * @param course_id
     * @param week
     * @return List
     * **/
    public List<Student> findStudentsNotLeave(int course_id, String week);


}
