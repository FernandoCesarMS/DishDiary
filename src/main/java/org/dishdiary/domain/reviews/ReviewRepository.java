package org.dishdiary.domain.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "SELECT * FROM reviews r WHERE r.usuario = :cpf",
            nativeQuery = true)
    List<Review> findByCpf(String cpf);

    List<Review> findByTipoPrato(String tipoPrato);
}
