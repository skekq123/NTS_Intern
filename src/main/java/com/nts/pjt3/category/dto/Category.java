package com.nts.pjt3.category.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * DB 연결을 위한 Category 클래스
 * 
 * @author 고영빈
 * 
 */
@Getter
@Setter
public class Category {
	private long id;
	private String name;
	
	@Override
	public String toString() {
		return "id = " + id + ", " + "name = " + name;
	}
}
