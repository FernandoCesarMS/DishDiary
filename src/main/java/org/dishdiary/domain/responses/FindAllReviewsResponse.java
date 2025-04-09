package org.dishdiary.domain.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FindAllReviewsResponse {
    private String usuario;
    private String estabelecimento;
    private String mensagem;
    private String prato;
    private String tipoPrato;
    private Integer nota;
    private String dataCadastro;
}