package com.nahum.commons.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nahum.commons.services.CommonsService;

import jakarta.validation.Valid;

public class CommonsController<D, S extends CommonsService<D>> {
	
	@Autowired
	protected S service;

	public CommonsController(S service) {
		this.service = service;
	}
	@GetMapping
	public ResponseEntity<List<D>> getAll() {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<D> getById(@PathVariable Long id){
		Optional<D> dto = service.obtenerPorId(id);
		if (dto.isPresent()) {
			return ResponseEntity.ok(dto.get());
		}
		return ResponseEntity.notFound().build();
		
	}
	@PostMapping
	public ResponseEntity<D> post(@Valid @RequestBody D dto , Binding result ){
		return ResponseEntity.ok(service.insertar(dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<D> delete(@PathVariable Long id){
		D dto = service.eliminar(id);
		if (dto != null) {
			return ResponseEntity.ok(dto);
		}
		return ResponseEntity.notFound().build();
	}
	
	protected ResponseEntity<?> validate(BindingResult result) {
		Map<String,Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(error -> {
			errores.put(error.getField(), 
					"Campo " + error.getField() + " " + error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().build();
	}
	

}
