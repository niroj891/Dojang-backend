package com.dojang.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SendMessageRequest {
	
	private Integer chatId;
	private Integer userId;
	private String content;
	private String image;

}
