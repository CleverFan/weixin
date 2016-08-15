package com.chengfan.ycjw.po;

/**
 * 课程表po
 * @author ChengFan
 *
 */
public class StudentTable {
	//					
	/**
	 * 课程名称
	 */
	private String className;
	/**
	 * 开课学院
	 */
	private String classCollege;
	/**
	 * 学分
	 */
	private String credit;
	/**
	 * 学时
	 */
	private String studyHour;
	/**
	 * 上课时间地点
	 */
	private String location;
	/**
	 * 课程类型
	 */
	private String classStyle;
	
	//默认构造方法
	public StudentTable(){
		
	}
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassCollege() {
		return classCollege;
	}
	public void setClassCollege(String classCollege) {
		this.classCollege = classCollege;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getStudyHour() {
		return studyHour;
	}
	public void setStudyHour(String studyHour) {
		this.studyHour = studyHour;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClassStyle() {
		return classStyle;
	}
	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}
	
	
}
