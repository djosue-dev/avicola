package com.example.Apicola.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    List<Cliente> findAllByEstadoTrue();
}
