package com.nahum.commons.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nahum.commons.mappers.CommonsMapper;

public class CommonsServiceImpl<D , E, 
	M extends CommonsMapper<D, E, R> , R extends JpaRepository<E, Long>> 
	implements CommonsService<D> {
	
	@Autowired
	protected R reporitory;
	
	@Autowired
	protected M mapper;

	@Override
	public List<D> listar() {
		return null;
	}

	@Override
	public Optional<D> obtenerPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public D insertar(D dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public D editar(D dto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public D eliminar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
