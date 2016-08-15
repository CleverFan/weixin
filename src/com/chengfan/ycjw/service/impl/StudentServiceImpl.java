package com.chengfan.ycjw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.chengfan.ycjw.mapper.StudentMapper;
import com.chengfan.ycjw.po.Student;
import com.chengfan.ycjw.service.StudentService;

public class StudentServiceImpl implements StudentService {

	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public int insertStudent(Student student) {
		return studentMapper.insert(student);
	}

	@Override
	public Student findStudentByWeixinname(String weixinname) {
		return studentMapper.selectByPrimaryKey(weixinname);
	}

	@Override
	public int deleteStudentById(String FromUserName) {
		return studentMapper.deleteByPrimaryKey(FromUserName);
	}

}
