package org.dishdiary.domain.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateUserRequest {
    private String cpf;
    private String senha;
}
