package com.zust.pro.service.Course;

import com.zust.pro.vo.Course;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.temper.teh_output;

import java.util.List;

/**
 * @author 86178
 */
public interface CourseService {
    /**
     * @param  course
     * @return boolean
     * 管理员添加课程
     * **/
    public boolean AddCourse(Course course);

    /**
     * @param  Tclass
     * @return List
     * 管理员添加课程
     * **/
    public List<Course> FindCourseByClass(String Tclass);

    /**根据id删除course
     * @param id
     * @return boolean
     * **/
    public boolean delCourse(int id);

    /**根据课程id找到course
     * @param id
     * @return Course
     * **/
    public Course FindCourseById(int id);


    /**找到所有的课程
     * @return Course
     * **/
    List<Course> FindCourses();
    /**根据老师id查找老师所教课程
     * @param teacher_Id
     * @return List
     */
    List<Course> FindCourseBytehId(int teacher_Id);

    /**
     * 根据老师id查找学生和课程信息
     * @param teacher_Id
     * @return List
     */
    List<teh_output> findStu_CouByTeacherId(int teacher_Id);

    /**
     * 查看学生id是否选修了这门课
     * @param course_id
     * @param stu_id
     * @return boolean
     * **/
    boolean isFindThisCourse(int stu_id,int course_id);

}
