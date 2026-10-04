package com.ferreteria.ferreteria.service;

import com.ferreteria.ferreteria.modells.Producto;
import com.ferreteria.ferreteria.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements  IProductoService{

   private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crear(Producto producto) {

        // validación de que el producto no sea null
        if(producto == null){
            return  null;
        }
        // Id se genera automaticamente en la BD y con esto la devolvemos junto con el producto
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(Long codigoProd, Producto producto) {
        // buscar si existe producto
        Producto buscarProduct = buscarProducto(codigoProd);
        if(buscarProduct == null){
            return  null;
        }
        return productoRepository.save(producto);
    }

    @Override
    public boolean delete(Long codProducto) {
        return false;
    }
}
