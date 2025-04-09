package org.dishdiary.domain.reviews;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.dishdiary.domain.users.User;
import org.dishdiary.enums.FoodTypeEnum;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "cpf")
    private User usuario;

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

    @Column(nullable = false)
    private LocalDateTime dataCadastro;
}
