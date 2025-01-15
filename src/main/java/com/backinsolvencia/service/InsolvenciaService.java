package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.*;
import com.backinsolvencia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InsolvenciaService {

    @Autowired
    private InsolvenciaRepository insolvenciaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CausaRepository causaRepository;

    @Autowired
    private InsolvenciaProductoRepository insolvenciaProductoRepository;

    @Autowired
    private ProductoRepository ProductoRepository;

    public List<Insolvencia> findAllInsolvencia() {
        List<Insolvencia> insolvencias = (List<Insolvencia>) insolvenciaRepository.findAll();
        if (insolvencias.isEmpty()) return null;
        return insolvencias;
    }

    public Insolvencia findInsolvenciaById(long id) {
        return insolvenciaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Insolvencia addInsolvencia(Insolvencia insolvencia) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(insolvencia.getCliente().getId());
        if (cliente.isPresent()) {
            insolvencia.setCliente(cliente.get());
            List<Causa> causas = new ArrayList<>();
            for (Causa causa : insolvencia.getCausas()) {
                Optional<Causa> causanew = causaRepository.findById(causa.getId());
                if (causanew.isEmpty()) {
                    throw new ExceptionsInsolvencia("No existe la causa con el id " + causa.getId());
                }
                causas.add(causanew.get());
            }
            insolvencia.setCausas(causas);
            if (causas != null) {
                if (!insolvencia.getInsolvenciaProductos().isEmpty()) {
                    Insolvencia savedInsolvencia = insolvenciaRepository.save(insolvencia);
                    for (InsolvenciaProducto insolvenciaProducto : insolvencia.getInsolvenciaProductos()) {
                        insolvenciaProducto.setInsolvenciaId(savedInsolvencia);
                        insolvenciaProducto.setProductoId(ProductoRepository.findById(insolvenciaProducto.getProductoId().getId()).get());
                        insolvenciaProductoRepository.save(insolvenciaProducto);

                    }
                    savedInsolvencia.setInsolvenciaProductos(insolvencia.getInsolvenciaProductos());
                    return savedInsolvencia;
                } else {
                    throw new ExceptionsInsolvencia("No se encontraron productos");
                }
            } else {
                throw new ExceptionsInsolvencia("No existen las causas " + insolvencia.getCausas());
            }
        } else {
            throw new ExceptionsInsolvencia("No existe el cliente con el id " + insolvencia.getCliente().getId());
        }
    }

    public Insolvencia updateInsolvencia(Insolvencia insolvencia) {

        Optional<Insolvencia> insolvenciaUpdate = insolvenciaRepository.findById(insolvencia.getId());
        if (insolvenciaUpdate.isPresent()) {
            Optional<Cliente> cliente = clienteRepository.findById(insolvencia.getCliente().getId());
            if (cliente.isPresent()) {
                List<Causa> causas = insolvencia.getCausas();
                for (Causa causa : causas) {
                    Optional<Causa> causaExiste = causaRepository.findById(causa.getId());
                    if (!causaExiste.isPresent()) {
                        throw new ExceptionsInsolvencia("No existe la causa con el id " + causa.getId());
                    }
                    System.out.println(causaExiste.get().toString());
                }
                if (causas != null) {
                    List<InsolvenciaProducto> InsolvenciaProductos = insolvencia.getInsolvenciaProductos();
                    for (InsolvenciaProducto insolvenciaProducto : InsolvenciaProductos) {
                        insolvenciaProductoRepository.save(insolvenciaProducto);
                    }
                    if (InsolvenciaProductos != null) {
                        insolvenciaUpdate.get().setInsolvenciaProductos(InsolvenciaProductos);
                        insolvenciaUpdate.get().setCliente( cliente.get() );
                        insolvenciaUpdate.get().setCausas( causas );
                        insolvenciaUpdate.get().setEstado( insolvencia.getEstado());
                        return insolvenciaRepository.save(insolvenciaUpdate.get());
                    } else {
                        throw new ExceptionsInsolvencia("No se encontraron productos");
                    }

                } else {
                    throw new ExceptionsInsolvencia("No existe las causas con el id " + insolvencia.getCausas());
                }
            }else {
                throw new ExceptionsInsolvencia("No existe el cliente con el id " + insolvencia.getCliente().getId());
            }
        } else {
            throw new ExceptionsInsolvencia("No existe la insolvencia con el id " + insolvencia.getId());
        }

    }

    public String deleteInsolvenciaById(long id) {
        Optional<Insolvencia> insolvencia = insolvenciaRepository.findById(id);
        if (insolvencia.isPresent()) {
            insolvenciaRepository.deleteById(id);
            return "la insolvencia "+ id + " eliminada";
        } else {
            throw new ExceptionsInsolvencia("No existe la insolvencia con el id " + id);
        }
    }

    public Insolvencia cambiarEstadoInsolvencia (Insolvencia insolvencia){
        Optional<Insolvencia> insolvenciaUpdate = insolvenciaRepository.findById(insolvencia.getId());
        if (insolvenciaUpdate.isPresent()) {
            insolvenciaUpdate.get().setEstado(insolvencia.getEstado());
            return insolvenciaRepository.save(insolvenciaUpdate.get());
        } else {
            throw new ExceptionsInsolvencia("No existe la insolvencia con el id " + insolvencia.getId());
        }
    }
}
