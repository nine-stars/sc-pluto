package com.iyb.ak.constants;

import lombok.Getter;

@Getter
public enum DeleteFlagEnum {
	
	YES("1"),
	NO("0");

	String code;

	private DeleteFlagEnum(String code) {
		this.code = code;
	}
}
