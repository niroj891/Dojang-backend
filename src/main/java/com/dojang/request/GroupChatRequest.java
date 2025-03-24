package com.dojang.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupChatRequest {
	private List<Integer>userIds;
	private String chatName;
	private String chatImage;

}
