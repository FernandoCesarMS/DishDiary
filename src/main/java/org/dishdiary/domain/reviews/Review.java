package org.dishdiary.domain.reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "usuario", referencedColumnName="cpf")
    private String usuario;

    @Column(nullable = false, length = 255)
    private String estabelecimento;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private String prato;

    @Column(nullable = false)
    private String tipoPrato;

    @Column(nullable = false)
    private Integer nota;
}
