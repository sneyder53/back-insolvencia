package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Acreedor;
import com.backinsolvencia.models.Producto;
import com.backinsolvencia.repository.AcreedorRepository;
import com.backinsolvencia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private AcreedorRepository acreedorService;

    public List<Producto> findAllProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    public Producto findProductoById(long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }
    public List<Producto> findProductoByAcreedor(long id) {
        Optional<Acreedor> acreedor = acreedorService.findById(id);
        if (acreedor.isPresent()) {
            return productoRepository.findByAcreedor(acreedor.get());
        }else {
            throw new ExceptionsInsolvencia("El Acreedor no existe");
        }
    }

    public Producto addProducto(Producto producto) {
        Optional<Acreedor> acreedor = acreedorService.findById(producto.getAcreedor().getId());
        if (acreedor.isPresent()) {
            producto.setAcreedor(acreedor.get());
            if(producto.getNombre() == null || producto.getNombre().equals("")) throw new ExceptionsInsolvencia("El nombre del producto no puede ser vacio");
            return productoRepository.save(producto);
        }else {
            throw new ExceptionsInsolvencia("El Acreedor no existe");
        }
    }

    public Producto updateProducto(Producto producto) {
        Optional<Producto> productoOptional = productoRepository.findById(producto.getId());
        Optional<Acreedor> acreedorOptional = acreedorService.findById(producto.getAcreedor().getId());
        if (acreedorOptional.isPresent()) {
            if (productoOptional.isPresent()) {
                productoOptional.get().setNombre(producto.getNombre());
                return productoRepository.save(productoOptional.get());
            }else {
                throw new ExceptionsInsolvencia("El producto no existe");
            }
        }else {
            throw new ExceptionsInsolvencia("El Acreedor no existe");
        }

    }

    public String deleteProducto(long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            productoRepository.delete(productoOptional.get());
            return "El producto " +id+ " ha sido eliminado";
        }else {
            throw new ExceptionsInsolvencia("El producto no existe");
        }

    }
}
