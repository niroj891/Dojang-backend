package com.dojang.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String gender;
	private String image;

}
