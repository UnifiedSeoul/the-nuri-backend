package com.nuri.backend.dto;


import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean result;
    private T data;
    private String token;
    private String message;
}
