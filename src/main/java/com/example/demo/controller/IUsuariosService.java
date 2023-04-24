package com.example.demo.controller;

import java.util.List;

import com.example.dto.Usuario;

public interface IUsuariosService {
	
	public List<Usuario> consulta();
	public boolean editar(Usuario usuarioEditado);
	public boolean guardar(Usuario usuario);
	public boolean borrar(String id);
}