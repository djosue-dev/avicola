package com.example.Apicola.services;

import com.example.Apicola.domain.cliente.Cliente;
import com.example.Apicola.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guarda(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public Cliente obtener(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }


}
