package com.backinsolvencia.service;

import com.backinsolvencia.models.Tarifa;
import com.backinsolvencia.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaService {
    @Autowired
    TarifaRepository tarifaRepository;

    public List<Tarifa> getAllTarifas(){
        return  (List<Tarifa>) tarifaRepository.findAll();
    }

    public Tarifa getTarifaById(Long id){
        Optional<Tarifa> tarifa = tarifaRepository.findById(id);
        return tarifa.orElse(null);
    }

    public Tarifa addTarifa(Tarifa tarifa){
        return tarifaRepository.save(tarifa);
    }

    public Tarifa updateTarifa(Tarifa tarifa){
        Tarifa tarifaUpdate = tarifaRepository.findById(tarifa.getId()).get();
        tarifaUpdate.setRangoMenor(tarifa.getRangoMenor());
        tarifaUpdate.setRangoMayor(tarifa.getRangoMayor());
        tarifaUpdate.setHonorarios(tarifa.getHonorarios());
        tarifaUpdate.setNumeroCuotas(tarifa.getNumeroCuotas());
        tarifaUpdate.setPorcentaje(tarifa.getPorcentaje());

        return tarifaRepository.save(tarifaUpdate);
    }

    public void deleteTarifa(Long id){
        try {
            tarifaRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
