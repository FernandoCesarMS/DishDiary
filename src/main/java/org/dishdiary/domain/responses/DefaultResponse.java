package org.dishdiary.domain.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DefaultResponse {
    private String message;
    private Object data;
}
