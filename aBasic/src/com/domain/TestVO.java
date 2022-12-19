package com.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//롬복
@Data
// 인자 있는 생성자
@AllArgsConstructor
// 인자 없는 생성자
@NoArgsConstructor
public class TestVO {
	
		private String name;
		private int age;

}
