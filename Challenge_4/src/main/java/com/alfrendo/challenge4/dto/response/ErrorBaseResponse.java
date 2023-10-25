package com.alfrendo.challenge4.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorBaseResponse <T> {

    private T message;

}
