package com.services.ms.student.app.infrastructure.adapters.input.restapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {
    @NotBlank(message = "Field firstname can't be empty or null.")
    private String firstname;
    @NotBlank(message = "Field lastname can't be empty or null.")
    private String lastname;
    @NotNull(message = "Field age can't be null.")
    private Integer age;
    @NotBlank(message = "Field address can't be empty or null.")
    private String address;

}
