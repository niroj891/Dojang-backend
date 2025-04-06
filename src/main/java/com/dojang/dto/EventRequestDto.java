package com.dojang.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Getter
@Setter
public class EventRequestDto {

    private String title;

    private String description;

    private MultipartFile imageFile;

    private String location;

    private Date endDate;

}
