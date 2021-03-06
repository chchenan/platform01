package cn.internship.service.impl;

import java.util.List;

import cn.internship.dao.WeeklyReportDao;
import cn.internship.dao.impl.WeeklyReportDaoImpl;
import cn.internship.entity.WeeklyReport;
import cn.internship.service.WeeklyReportService;;
public class WeeklyReportServiceImpl implements WeeklyReportService{

	private WeeklyReportDao weeklyReportDao;
	
	//通过主键id获取一条周报
	@Override
	public WeeklyReport getWeeklyReport(Integer id) {
		return weeklyReportDao.get(id);		
	}

	//通过主键id删除一条周报
	@Override
	public void deleteWeeklyReport(Integer id) {
		weeklyReportDao.delete(id);
	}

	//更新一条周报
	@Override
	public void updateWeeklyReport(WeeklyReport weeklyReport) {
		weeklyReportDao.update(weeklyReport);
	}

	//保存一条周报
	@Override
	public void saveWeeklyReport(WeeklyReport weeklyReport) {
		weeklyReportDao.save(weeklyReport);
	}

	//根据当前学生学号获取所有的周报
	@Override
	public List<WeeklyReport> getAllWeeklyReport(String sno) {
		return weeklyReportDao.getAll(sno);
	}

	public WeeklyReportDao getWeeklyReportDao() {
		return weeklyReportDao;
	}

	public void setWeeklyReportDao(WeeklyReportDao weeklyReportDao) {
		this.weeklyReportDao = weeklyReportDao;
	}

	
}
