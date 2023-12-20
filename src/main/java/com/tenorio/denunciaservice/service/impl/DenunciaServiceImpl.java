package com.tenorio.denunciaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tenorio.denunciaservice.entity.Denuncia;
import com.tenorio.denunciaservice.repository.DenunciaRepository;
import com.tenorio.denunciaservice.service.DenunciaService;

import jakarta.transaction.Transactional;

@Service
public class DenunciaServiceImpl implements DenunciaService {
    @Autowired
    private DenunciaRepository repository;

    @Override
    @Transactional
    public List<Denuncia> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Denuncia> findByTitulo(String titulo, Pageable page) {
        try {
            return repository.findByTituloContaining(titulo, page);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Denuncia findById(int id) {
        try {
            Denuncia registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Denuncia save(Denuncia denuncia) {
        try {
            // Establece cualquier lógica adicional necesaria antes de guardar
            Denuncia registro = repository.save(denuncia);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Denuncia update(Denuncia denuncia) {
        try {
            Denuncia registro = repository.findById(denuncia.getId()).orElseThrow();
            // Actualiza las propiedades necesarias
            registro.setTitulo(denuncia.getTitulo());
            registro.setDescripcion(denuncia.getDescripcion());
            // Guarda los cambios
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            // Realiza cualquier lógica adicional antes de eliminar
            repository.deleteById(id);
        } catch (Exception e) {
            // Maneja cualquier excepción que pueda ocurrir al eliminar
        }
    }
}
