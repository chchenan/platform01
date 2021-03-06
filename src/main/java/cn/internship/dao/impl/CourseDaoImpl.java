package cn.internship.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import cn.internship.dao.CourseDao;
import cn.internship.entity.Course;

/**
 * 课程持久层操作
 * @author dreamlate
 */
public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao{

	//获得所有课程
	@Override
	public List<Course> getAll() {
		String hql = "from Course";
		return (List<Course>) getHibernateTemplate().find(hql);
	}


	//根据主键获得指定课程
	@Override
	public Course get(Integer id) {
		return getHibernateTemplate().get(Course.class, id);
	}


	//获得某个学生的所有必修课程
	@Override
	public List<Course> getBySno(Integer studentId) {
//		String sql = "SELECT * FROM course c WHERE c.courseId IN ( SELECT sc.courseId FROM student_course sc WHERE sc.studentId =:studentId";
		String hql = "select s.courses from Student s where s.studentId = :studentId";
		String[] params = new String[]{"studentId"};
		Integer[] values = new Integer[]{studentId};
		List<Course> list = (List<Course>) getHibernateTemplate().findByNamedParam(hql, params, values);
		return list;
	}


	//获得某个教师的所有教授课程
	@Override
	public List<Course> getByTno(Integer teacherId) {
		String hql = "select t.courses from Teacher t where t.teacherId = :teacherId";
		String[] params = new String[]{"teacherId"};
		Integer[] values = new Integer[]{teacherId};
		List<Course> list = (List<Course>) getHibernateTemplate().findByNamedParam(hql, params, values);
		return list;
	}


	//获得成绩
	@Override
	public Integer getCourseScore(Integer studentId,Integer courseId) {
		//执行原生的sql语句
		Integer score = getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery("select score from student_course where courseId=? and studentId=?");
//				query.addScalar("score");
				query.setInteger(0, courseId);
				query.setInteger(1, studentId);
				List<Integer> list = query.list();
				Integer s = list.size()==0?null:list.get(0);
				return s;
			}
		});
		return score;
	}

	//设置成绩
	@Override
	public Integer setCourseScore(Integer studentId,Integer courseId,Integer score) {
		//执行原生的sql语句
		Integer result = getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery("update student_course set score =? where courseId=? and studentId=?");
//					query.addScalar("score");
				query.setInteger(0, score);
				query.setInteger(1, courseId);
				query.setInteger(2, studentId);
				return query.executeUpdate();
			}
		});
		return result;
	}
	

	//添加课程记录
	@Override
	public void addCourse(Course course) {
		getHibernateTemplate().save(course);
	}


	//删除课程记录
	@Override
	public void deleteCourse(Integer id) {
		getHibernateTemplate().delete(get(id));
	}

	//修改课程信息
	@Override
	public void updateCourse(Course course) {
		getHibernateTemplate().update(course);
	}



}
