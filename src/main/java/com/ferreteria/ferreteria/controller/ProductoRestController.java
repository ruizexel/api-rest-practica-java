package com.ferreteria.ferreteria.controller;

import com.ferreteria.ferreteria.modells.Producto;
import com.ferreteria.ferreteria.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final IProductoService productoService;
    public ProductoRestController(IProductoService productoService){
        this.productoService = productoService;
    }

    // READ
    @GetMapping
    public List<Producto> traerProductos(){
        return productoService.traerProductos();
    }

    // READ de producto especifico
    @GetMapping("/{codProd}")
    public ResponseEntity<?> buscarProducto(@PathVariable Long codProd){
        Producto prod = productoService.buscarProducto(codProd);
        if(prod == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el producto con ese codigo");
        }

        return ResponseEntity.ok(prod);

    }

    // Create
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        Producto productoCreado = productoService.crearProducto(producto);

        if(productoCreado == null){
            return ResponseEntity.badRequest().body("Los datos del producto no son válidos");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    // UPDATE

    @PutMapping("/{codProd}")
    public ResponseEntity<?> editarProducto(@PathVariable Long codProd, @RequestBody Producto prodModificar){
        Producto prodEditado = productoService.editarProducto(codProd, prodModificar);

        if(prodEditado == null){
            return ResponseEntity.badRequest().body("No fue posible editar el producto");
        }

        return ResponseEntity.ok(prodEditado);
    }

    // DELETE

    @DeleteMapping("/{codProd}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long codProd){
        boolean eliminado = productoService.eliminarProducto(codProd);

        // validación

        if(eliminado == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro un producto con el codigo: " + codProd);
        }

        return ResponseEntity.ok("Producto eliminado correctamente");
    }

}
