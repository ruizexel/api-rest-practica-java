package com.ferreteria.ferreteria.repository;

import com.ferreteria.ferreteria.modells.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
