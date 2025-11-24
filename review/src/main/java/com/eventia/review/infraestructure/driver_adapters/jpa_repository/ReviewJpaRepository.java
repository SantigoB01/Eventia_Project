package com.eventia.review.infraestructure.driver_adapters.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewData, Long> {

    List<ReviewData> findByIdServicio(Long idServicio);



    List<ReviewData> findByIdCliente(Long idCliente);

}
