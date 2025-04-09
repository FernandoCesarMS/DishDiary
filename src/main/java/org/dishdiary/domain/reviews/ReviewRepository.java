package org.dishdiary.domain.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews r WHERE r.usuario = :cpf ORDER BY r.data_cadastro DESC", nativeQuery = true)
    List<Review> findByCpf(String cpf);

    @Query("SELECT r FROM Review r WHERE r.tipoPrato = :tipoPrato AND r.usuario.cpf <> :cpf ORDER BY r.dataCadastro DESC")
    List<Review> findByFoodType(@Param("tipoPrato") String tipoPrato, @Param("cpf") String cpf);

    @Query("SELECT r FROM Review r WHERE r.estabelecimento = :establishment AND r.usuario.cpf <> :cpf ORDER BY r.dataCadastro DESC")
    List<Review> findByEstablishment(@Param("establishment") String establishment, @Param("cpf") String cpf);

    @Query("SELECT r FROM Review r WHERE r.usuario.cpf <> :cpf ORDER BY r.dataCadastro DESC")
    List<Review> findAll(@Param("cpf") String cpf);

    @Query("SELECT r FROM Review r WHERE LOWER(r.usuario.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND r.usuario.cpf <> :cpf ORDER BY r.dataCadastro DESC")
    List<Review> findByCustomerName(@Param("nome") String nome, @Param("cpf") String cpf);

}
