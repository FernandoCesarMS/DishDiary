package org.dishdiary.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindAllReviewsResponse {
    private String usuario;
    private String estabelecimento;
    private String mensagem;
    private String prato;
    private String tipoPrato;
    private Integer nota;
}