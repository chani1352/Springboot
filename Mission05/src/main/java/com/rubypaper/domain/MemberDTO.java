package com.rubypaper.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;

}
