package com.backinsolvencia.service;

import com.backinsolvencia.exception.ExceptionsInsolvencia;
import com.backinsolvencia.models.Cliente;
import com.backinsolvencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAllClientes() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        if (clientes.isEmpty()) return null;
        return clientes;
    }

    public Cliente findClienteById(long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) return cliente.get();
        return null;
    }

    public Cliente addCliente(Cliente cliente) throws Exception {
        if(cliente.getNombres().isEmpty() || cliente.getApellidos().isEmpty() || cliente.getIdentificacion().isEmpty() ||
            cliente.getEmail().isEmpty()) throw new ExceptionsInsolvencia("Los campos no pueden ser vacio");
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception("la identificacion o el email ya existen "+e.getMessage());
        }

    }
    public Cliente updateCliente(Cliente cliente) throws Exception {
        if(cliente.getNombres().isEmpty() || cliente.getApellidos().isEmpty() || cliente.getIdentificacion().isEmpty() ||
                cliente.getEmail().isEmpty()) throw new ExceptionsInsolvencia("Los campos no pueden ser vacio");
        Optional<Cliente> clienteUpdate = clienteRepository.findById(cliente.getId());
        if (clienteUpdate.isPresent()) {
            clienteUpdate.get().setNombres(cliente.getNombres());
            clienteUpdate.get().setApellidos(cliente.getApellidos());
            clienteUpdate.get().setIdentificacion(cliente.getIdentificacion());
            clienteUpdate.get().setEmail(cliente.getEmail());
            clienteUpdate.get().setDireccion(cliente.getDireccion());
            clienteUpdate.get().setTelefono(cliente.getTelefono());
            try {
                return clienteRepository.save(clienteUpdate.get());
            }catch (Exception e) {
                throw new Exception("la identificacion o el email ya existen "+e.getMessage());
            }

        }else {
            throw new ExceptionsInsolvencia("No se encontro el cliente");
        }
    }

    public String deleteClienteById(long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return "Cliente "+id+" eliminado";
        }else {
            throw new ExceptionsInsolvencia("No se encontro el cliente");
        }
    }
}