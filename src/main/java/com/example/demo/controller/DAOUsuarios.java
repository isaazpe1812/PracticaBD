package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.utils.Encriptar;
import com.example.dto.Usuario;

@Component
public class DAOUsuarios implements IUsuariosService{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Usuario> consulta() {
		String sql="SELECT * FROM usuarios";
		List<Usuario> usuarios=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Usuario.class));
		usuarios.forEach(System.out::println);
		return usuarios;
	}

	@Override
	public boolean editar(Usuario usuarioEditado) {
		LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fechaHoraCon = LocalDateTime.of(hoy, ahora);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String text = fechaHoraCon.format(formatter);
		// TODO Auto-generated method stub
		String sql="UPDATE USUARIOS SET TXT_NOMBRE='"+usuarioEditado.getTxt_Nombre()+"',"+"TXT_APE_MAT='"+usuarioEditado.getTxt_Ape_Mat()+"',"+"TEXT_TEL='"+usuarioEditado.getText_Tel()+"',"+"TXT_CORREO='"+Encriptar.aesEncryptStr(usuarioEditado.getTxt_Correo(), Encriptar.pkey)+"',"+"TXT_APE_PAT='"+usuarioEditado.getTxt_Ape_Pat()+"',"+"FECHA_ULT_CON="+"to_date('"+text+"', 'dd-MM-yyyy HH24:mi:ss')"+","+"FECHA_CARG="+"to_date('"+hoy+"', 'yyyy-MM-dd')"+"WHERE ID_USR_APK='"+usuarioEditado.getId_Usr_Apk()+"'";
		int res=jdbcTemplate.update(sql);
		return true;
	}

	@Override
	public boolean guardar(Usuario usuarioEditado) {
		// TODO Auto-generated method stub
		LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fechaHoraCon = LocalDateTime.of(hoy, ahora);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String text = fechaHoraCon.format(formatter);
		// TODO Auto-generated method stub
		String sql="INSERT INTO USUARIOS (ID_USR_APK,TXT_NOMBRE,TXT_PAS,TXT_APE_MAT,TEXT_TEL,TXT_CORREO,TXT_APE_PAT,FECHA_ULT_CON,FECHA_CARG) VALUES ("+"'"+usuarioEditado.getId_Usr_Apk()+"','"+usuarioEditado.getTxt_Nombre()+"','"+Encriptar.aesEncryptStr(usuarioEditado.getTxt_Pas(), Encriptar.pkey)+"','"+usuarioEditado.getTxt_Ape_Mat()+"','"+usuarioEditado.getText_Tel()+"','"+Encriptar.aesEncryptStr(usuarioEditado.getTxt_Correo(), Encriptar.pkey)+"','"+usuarioEditado.getTxt_Ape_Pat()+"',"+"to_date('"+text+"', 'dd-MM-yyyy HH24:mi:ss')"+","+"to_date('"+hoy+"', 'yyyy-MM-dd')"+")";
		int res=jdbcTemplate.update(sql);
		return (res==1)?true:false;
	}

	@Override
	public boolean borrar(String id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM USUARIOS WHERE ID_USR_APK='"+id+"'";
		int res=jdbcTemplate.update(sql);
		return (res==1)?true:false;
	}

}
