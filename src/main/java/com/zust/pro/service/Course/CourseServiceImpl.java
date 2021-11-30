package com.zust.pro.service.Course;

import com.zust.pro.dao.course.CourseDao;
import com.zust.pro.dao.course.CourseDaoImpl;
import com.zust.pro.dao.user.UserDao;
import com.zust.pro.service.user.UserService;
import com.zust.pro.service.user.UserServiceImpl;
import com.zust.pro.vo.Course;
import com.zust.pro.vo.Student;
import com.zust.pro.vo.temper.teh_output;

import java.util.List;

/**
 * @author 86178
 */
public class CourseServiceImpl implements CourseService{
    CourseDao courseDao = new CourseDaoImpl();
    @Override
    public boolean AddCourse(Course course) {
        boolean b = courseDao.addCourse(course);
        return b;
    }

    @Override
    public List<Course> FindCourseByClass(String Tclass) {
        List<Course> allCourseFromClass = courseDao.findAllCourseFromClass(Tclass);
        return allCourseFromClass;
    }

    @Override
    public boolean delCourse(int id) {
        Course courseByCourseId = courseDao.findCourseByCourseId(id);
        boolean b = courseDao.delCourse(courseByCourseId);
        return b;
    }

    @Override
    public Course FindCourseById(int id) {
        Course courseByCourseId = courseDao.findCourseByCourseId(id);
        return courseByCourseId;
    }

    @Override
    public List<Course> FindCourses() {
        List<Course> courses = courseDao.findCourses();
        return courses;
    }

    @Override
    public List<Course> FindCourseBytehId(int teacher_Id) {
        List<Course> courseList = courseDao.findAllCourseFromTeacher(teacher_Id);
        return courseList;
    }


    @Override
    public List<teh_output> findStu_CouByTeacherId(int teacher_Id) {
        List<teh_output> teh_outputList = courseDao.findStu_CouByTeacherId(teacher_Id);
        return teh_outputList;
    }

    @Override
    public boolean isFindThisCourse(int stu_id, int course_id) {
        boolean flag = false;
        UserService userService = new UserServiceImpl();
        Student studentById = userService.getStudentById(stu_id);
        List<Course> allCourseFromClass = courseDao.findAllCourseFromClass(studentById.getStu_Class());
        for (Course course : allCourseFromClass) {
            if (course_id == course_id) {
                flag = true;
            }
        }
        return flag;
    }

}
