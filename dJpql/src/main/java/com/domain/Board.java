package com.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="BOARD")
public class Board {
	@Id
	//멤버변수
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	
	private int cnt;
}
