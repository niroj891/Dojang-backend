package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="chat_tbl")
public class Chat implements Serializable {
	
	private static final long serialVersionUID = 87773663635534667L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chat_id")
	private int chatId;
	
	private String chatName;
	private String chatImage;
	private Boolean isGroup;
	
	@ManyToOne
	private User createdBy;
	
	private LocalDate timestamp;
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	@OneToMany( mappedBy= "chat")
	private List<Message> message = new ArrayList<>();

}
