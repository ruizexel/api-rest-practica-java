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
    public Producto crearProducto(Producto producto) {

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

        // Validar
        if(buscarProduct == null){
            return  null;
        }

        // actualizamos los datos del producto

        buscarProduct.setNombre(producto.getNombre());
        buscarProduct.setMarca(producto.getMarca());
        buscarProduct.setCategoria(producto.getCategoria());
        buscarProduct.setPrecio(producto.getPrecio());
        buscarProduct.setStock(producto.getStock());
        buscarProduct.setDescripcion(producto.getDescripcion());

        return productoRepository.save(buscarProduct);
    }

    @Override
    public boolean eliminarProducto(Long codProducto) {

        // buscar si existe
        Producto eliminarProducto = buscarProducto(codProducto);

        // validar si existe
        if(eliminarProducto == null){
            return false;
        }

        productoRepository.delete(eliminarProducto);
        return true;
    }
}
