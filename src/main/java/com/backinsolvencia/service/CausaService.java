package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Causa;
import com.backinsolvencia.repository.CausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CausaService {

    @Autowired
    private CausaRepository causaRepository;

    public List<Causa> findAllCausa() {
        return (List<Causa>) causaRepository.findAll();
    }

    public Causa findCausaById(long id) {
        return causaRepository.findById(id).orElse(null);
    }

    public Causa addCausa(Causa causa) {
        if (causa.getNombre() == null || causa.getNombre().equals("")) throw new ExceptionsInsolvencia("El nombre del causa no puede ser vacio");
        return causaRepository.save(causa);
    }

    public Causa updateCausa(Causa causa) {
        if (causa.getNombre() == null || causa.getNombre().equals("")) throw new ExceptionsInsolvencia("El nombre del causa no puede ser vacio");
        Optional<Causa> causaUpdate = causaRepository.findById(causa.getId());
        if (causaUpdate.isPresent()) {
            causaUpdate.get().setNombre(causa.getNombre());
            return causaRepository.save(causaUpdate.get());
        } else {
            throw new ExceptionsInsolvencia("No existe el causa con el id " + causa.getId());
        }
    }

    public String deleteCausaById(long id) {
        Optional<Causa> causa = causaRepository.findById(id);
        if (causa.isPresent()) {
            causaRepository.deleteById(id);
            return "la Causa "+ id + " eliminado";
        } else {
            throw new ExceptionsInsolvencia("No existe la causa con el id " + id);
        }
    }
}
