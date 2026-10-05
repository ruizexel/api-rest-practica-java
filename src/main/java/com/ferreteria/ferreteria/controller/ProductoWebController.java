package com.ferreteria.ferreteria.controller;

import com.ferreteria.ferreteria.modells.Producto;
import com.ferreteria.ferreteria.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final IProductoService productoService;
    public ProductoWebController(IProductoService productoService){
        this.productoService = productoService;
    }

    // Get oara traer la lista de productos
    @GetMapping
    public String traerProductos(Model model){
        model.addAttribute(
                "productos", productoService.traerProductos()
        );

        return "lista";
    }

    // Get para traer la pagina/vista del formulario
    @GetMapping("/nuevo")
    public String mostrarFormulario (Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("titulo", "Registrar Producto");

        return "formulario";
    }

    // Post para guardar el formulario

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto, Model model){
        Producto resultado;

        if(producto.getCodProducto() == null){
            resultado = productoService.crearProducto(producto);
        }else{
            resultado = productoService.editarProducto(
                    producto.getCodProducto(),
                    producto
            );
        }

        if(resultado == null){
            model.addAttribute("producto", producto);
            model.addAttribute(
                    "titulo",
                    producto.getCodProducto() == null
                    ? "Registrar producto" :
                       "editar producto"
            );
            model.addAttribute(
                    "error",
                    "Revisa los datos. Nombre, marca y categoria son obligatorios." +
                            "El precio deber ser mayor a cero y el stock no puede ser negativo-"
            );
            return "formulario";
        }
        return "redirect:/productos";
    }

    @GetMapping("/editar/{codProd}")
    public String mostrarFormulario(@PathVariable Long codProd, Model model){
        Producto producto = productoService.buscarProducto(codProd);

        if(producto == null){
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar producto");

        return "formulario";
    }


    @PostMapping("/eliminar/{codProd}")
    public String eliminarProducto(@PathVariable Long codProd){

        productoService.eliminarProducto(codProd);

        return "redirect:/productos";
    }
}
