package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Acreedor;
import com.backinsolvencia.repository.AcreedorRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcreedorService {

    private AcreedorRepository acreedorRepository;


    public List<Acreedor> getAllAcreedor() {
        List<Acreedor> acreedores = (List<Acreedor>) acreedorRepository.findAll();
        if (acreedores.isEmpty()) return null;
        return acreedores;
    }
    public Acreedor getAcreedorById(long id) {
        Optional<Acreedor> acreedor = acreedorRepository.findById(id);
        if (acreedor.isPresent()) return acreedor.get();
        return null;
    }

    public Acreedor addAcreedor(Acreedor acreedor) {
        if(acreedor.getNombre().isEmpty())  throw new ExceptionsInsolvencia("El nombre no puede estar vacio");
        return acreedorRepository.save(acreedor);
    }
    public Acreedor updateAcreedor(Acreedor acreedor) {
        if( acreedor.getNombre().isEmpty())  throw new ExceptionsInsolvencia("El nombre no puede estar vacio");
        Optional<Acreedor> acreedorUpdate = acreedorRepository.findById(acreedor.getId());
        if(acreedorUpdate.isPresent()){
            acreedorUpdate.get().setNombre(acreedor.getNombre());
            return acreedorRepository.save(acreedorUpdate.get());
        }else {
            throw new ExceptionsInsolvencia("El Acreedor no existe");
        }

    }

    public String deleteAcreedor(long id) {
        Optional<Acreedor> acreedor = acreedorRepository.findById(id);
        if (acreedor.isPresent()) {
            acreedorRepository.delete(acreedor.get());
            return "el acreedor "+ id + " ha sido eliminado";
        }else {
            throw new ExceptionsInsolvencia("el acreedor no existe");
        }
    }
}
