package br.nikolastrapp.receba.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RecebaGenericException {

    private String title;
    private String detail;
    private String type;
    private Integer status;
    private LocalDateTime instant;

}
