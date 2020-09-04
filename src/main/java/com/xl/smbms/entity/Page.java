package com.xl.smbms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page {

	private int start;
	private int size = 5;
	private int total;
	private int totalPage;
	private int current;
	private int first;
	private int previous;
	private int next;
	private int last;

}
