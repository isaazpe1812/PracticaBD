package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DelatedByIdRequest;
import com.example.dto.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;




@RestController
@RequestMapping(path = "api/usuario")
public class ControllerUsuarios {
	
	
	@Autowired
	DAOUsuarios usuarioFacade;
	
	@PostMapping("/actualizar")
	public  String actualizar(HttpServletRequest req) throws SQLException, JsonSyntaxException, JsonIOException, IOException {
		Gson s=new Gson();
		Usuario n=s.fromJson(req.getReader(), Usuario.class);
		System.out.println("Body: {}"+n.toString());
		System.out.println("Body de GSON>>>"+ n.getTxt_Nombre());
		boolean res=usuarioFacade.editar(n);
		return (res== true)?("{\"Mensaje\":\"Actualizacion Exitosa\"}"):("{\"Mensaje\":\"Error al Actualizar\"}");
		
	}
	
	@GetMapping("/consulta")
	public List<Usuario> consultaUsuario() throws SQLException {
		 return usuarioFacade.consulta();
	}
	
	
	@PostMapping(path="/alta")
	public String alta(HttpServletRequest req) throws IOException {
		
		Gson s=new Gson();
		Usuario n=s.fromJson(req.getReader(), Usuario.class);
		System.out.println("Body: {}"+n.toString());
		System.out.println("Body de GSON>>>"+ n.getTxt_Nombre());
		boolean res=usuarioFacade.guardar(n);
		return (res== true)?("{\"Mensaje\":\"Alta Exitosa\"}"):("{\"Mensaje\":\"Error en el Alta\"}");
	       
	}
	
	@PostMapping(path="/eliminar")
	public String borrar(HttpServletRequest req) {
		Gson s=new Gson();
		DelatedByIdRequest n;
		boolean res=false;
		try {
			n = s.fromJson(req.getReader(), DelatedByIdRequest.class);
			System.out.println("Body: {}"+n.toString());
			System.out.println("Body de GSON>>>"+ n.getId_Usr_Apk());
			res=usuarioFacade.borrar(String.valueOf(n.getId_Usr_Apk()));
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (res== true)?("{\"Mensaje\":\"Alta Exitosa\"}"):("{\"Mensaje\":\"Error en el Alta\"}");
	}
	
}
