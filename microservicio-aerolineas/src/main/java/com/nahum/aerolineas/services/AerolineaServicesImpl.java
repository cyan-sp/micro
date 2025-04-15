package com.nahum.aerolineas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nahum.aerolineas.AerolineaMapper;
import com.nahum.aerolineas.dto.AerolineaDTO;
import com.nahum.aerolineas.models.repository.AerolineaRepository;
import com.nahum.commons.models.entitites.Aerolinea;
import com.nahum.commons.services.CommonsServiceImpl;

@Service
public class AerolineaServicesImpl extends CommonsServiceImpl<AerolineaDTO, Aerolinea, 
	AerolineaMapper, AerolineaRepository>
	implements AerolineaService {

	@Override
	public List<AerolineaDTO> listar() {
		List<AerolineaDTO> lista = new ArrayList<>();
		reporitory.findAll().forEach(linea -> {
			lista.add(mapper.entityToDTO(linea));
		});
		return lista;
	}

	@Override
	public Optional<AerolineaDTO> obtenerPorId(Long id) {
		Optional<Aerolinea> optAerolinea = reporitory.findById(id);
		if (optAerolinea.isPresent()) {
			return Optional.of(mapper.entityToDTO(optAerolinea.get()));
		}
		return Optional.empty();
	}

	@Override
	public AerolineaDTO insertar(AerolineaDTO dto) {
		Aerolinea aerolinea = mapper.dtoToEntity(dto);
		reporitory.save(aerolinea);
		return dto;
	}

	@Override
	public AerolineaDTO editar(AerolineaDTO dto, Long id) {
		Optional<Aerolinea> opt = reporitory.findById(id);
		if (opt.isPresent()) {
			Aerolinea aerolinea = opt.get();
			aerolinea.setNombre(dto.getNombre());
			aerolinea.setIata(dto.getIata());
			aerolinea.setPais(dto.getPais());
			aerolinea.setEstatus(dto.getEstatus());
			aerolinea.setFechaFundacion(dto.getFechaFundacion());
			return mapper.entityToDTO(aerolinea);
		}
		return null;
	}

	@Override
	public AerolineaDTO eliminar(Long id) {
		Optional<Aerolinea> opt = reporitory.findById(id);
		if (opt.isPresent()) {
			AerolineaDTO dto = mapper.entityToDTO(opt.get());
			reporitory.deleteById(id);
			return dto;
		}
		return null;
	}

	
	
}
