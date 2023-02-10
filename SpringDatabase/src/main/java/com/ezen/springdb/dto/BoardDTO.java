package com.ezen.springdb.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {

	private Integer board_id;
	private String writer_id;
	private String writer_pw;
	private String write_title;
	private String write_content;
	private Date write_date;
	private Integer write_view;
	private Integer write_reconmmand;
	private Integer write_not_reconmmand;
}
