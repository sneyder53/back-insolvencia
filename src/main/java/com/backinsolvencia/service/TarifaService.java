package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
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

    public List<Tarifa>  getAllTarifas(){
        List<Tarifa> tarifas = (List<Tarifa>) tarifaRepository.findAll();
        if (tarifas.isEmpty()) {
            return null;
        }
        return  tarifas;
    }

    public Tarifa getTarifaById(Long id){
        Optional<Tarifa> tarifa = tarifaRepository.findById(id);
        if (tarifa.isPresent()) {
            return tarifa.get();
        }
        return null;
    }

    public Tarifa  addTarifa(Tarifa tarifa){
        if (validaCampos(tarifa)) throw new ExceptionsInsolvencia("Los campos no pueden estar vacios o negativos");
        return tarifaRepository.save(tarifa);
    }

    public Tarifa updateTarifa(Tarifa tarifa){
        if (validaCampos(tarifa)) throw new ExceptionsInsolvencia("Los campos no pueden estar vacios o negativos");
        Tarifa tarifaUpdate = tarifaRepository.findById(tarifa.getId()).get();
        tarifaUpdate.setRangoMenor(tarifa.getRangoMenor());
        tarifaUpdate.setRangoMayor(tarifa.getRangoMayor());
        tarifaUpdate.setHonorarios(tarifa.getHonorarios());
        tarifaUpdate.setNumeroCuotas(tarifa.getNumeroCuotas());
        tarifaUpdate.setPorcentaje(tarifa.getPorcentaje());
        return tarifaRepository.save(tarifaUpdate);

    }

    public String deleteTarifa(Long id) {
            Optional<Tarifa> tarifa = tarifaRepository.findById(id);
            if(tarifa.isPresent()) {
                tarifaRepository.delete(tarifa.get());
                return "la tarifa con id: " + id + " eliminada";
            }else{
                throw new ExceptionsInsolvencia("La tarifa no existe");
            }

    }

    private boolean validaCampos(Tarifa tarifa) {
        if (tarifa.getRangoMenor() == null || tarifa.getRangoMenor() < 0 || tarifa.getRangoMayor() == null || tarifa.getRangoMayor() < 0 ||
                tarifa.getHonorarios() == null || tarifa.getHonorarios() < 0 || tarifa.getNumeroCuotas() < 0 ||
                tarifa.getPorcentaje() == null || tarifa.getPorcentaje() < 0 ) {
            return true;
        }
        return false;
    }
}
