package com.chengfan.ycjw.service;

import com.chengfan.ycjw.po.Student;



public interface StudentService {
	/**
	 * @param student
	 * @return
	 */
	int insertStudent(Student student);
	
	/**
	 * @param weixinname
	 * @return
	 */
	Student findStudentByWeixinname(String weixinname);
	
	/**
	 * @param FromUserName
	 * @return
	 */
	int deleteStudentById(String FromUserName);
}
