package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.dto.Usuario;



@SpringBootApplication
//@PropertySource("file:${HOME}/PracticaBD/applicationPracticaBD.properties" )
public class PracticaBdApplication implements CommandLineRunner{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(PracticaBdApplication.class, args);
	}

	@Override
	public void run(String...  args) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM usuarios";
		List<Usuario> usuarios=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Usuario.class));
		usuarios.forEach(System.out::println);
	}

}
