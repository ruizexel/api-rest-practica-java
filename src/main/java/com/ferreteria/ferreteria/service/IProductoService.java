package com.ferreteria.ferreteria.service;

import com.ferreteria.ferreteria.modells.Producto;

import java.util.List;

public interface IProductoService {

    // Metódos del CRUD

    // READ
    List<Producto> traerProductos();

    Producto buscarProducto(Long id);

    // CREATE

    Producto crear(Producto producto);

    // UPDATE
    Producto editarProducto(Long codigoProd, Producto producto);

    // DELETE

    boolean delete(Long codProducto);
}
