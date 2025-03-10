package com.dojang.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="comment_tbl")
public class Comment  implements Serializable{
	
	private static final long serialVersionUID = 87654123456789L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private int id;
	
	@Column(name="comment_text")
	private String text;
	
	@Column(name="post_date")
	private LocalDate postedDate;
	
	private User user;
	
	

}
