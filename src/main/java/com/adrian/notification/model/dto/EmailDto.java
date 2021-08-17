package com.adrian.notification.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class EmailDto {

    @NotBlank
    private String to;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
