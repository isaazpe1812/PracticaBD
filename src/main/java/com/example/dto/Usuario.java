package com.example.dto;

import java.io.Serializable;

import com.example.demo.utils.Encriptar;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Id_Usr_Apk;
	private String Txt_Nombre;
	private String Txt_Pas;
	private String Txt_Ape_Pat;
	private String Txt_Ape_Mat;
	private String Fecha_Ult_Con;
	private String Fecha_Carg;
	private String Text_Tel;
	private String Txt_Correo;
	public String getId_Usr_Apk() {
		return Id_Usr_Apk;
	}
	public void setId_Usr_Apk(String id_Usr_Apk) {
		this.Id_Usr_Apk = id_Usr_Apk;
	}
	public String getTxt_Nombre() {
		return Txt_Nombre;
	}
	public void setTxt_Nombre(String txt_Nombre) {
		this.Txt_Nombre = txt_Nombre;
	}
	public String getTxt_Pas() {
		return Txt_Pas;
	}
	public void setTxt_Pas(String txt_Pas) {
		this.Txt_Pas = Encriptar.aesEncryptStr(txt_Pas, Encriptar.pkey);
	}
	public String getTxt_Ape_Pat() {
		return Txt_Ape_Pat;
	}
	public void setTxt_Ape_Pat(String txt_Ape_Pat) {
		this.Txt_Ape_Pat = txt_Ape_Pat;
	}
	public String getTxt_Ape_Mat() {
		return Txt_Ape_Mat;
	}
	public void setTxt_Ape_Mat(String txt_Ape_Mat) {
		this.Txt_Ape_Mat = txt_Ape_Mat;
	}
	public String getFecha_Ult_Con() {
		return Fecha_Ult_Con;
	}
	public void setFecha_Ult_Con(String fecha_Ult_Con) {
		this.Fecha_Ult_Con = fecha_Ult_Con;
	}
	public String getFecha_Carg() {
		return Fecha_Carg;
	}
	public void setFecha_Carg(String fecha_Carg) {
		this.Fecha_Carg = fecha_Carg;
	}
	public String getText_Tel() {
		return Text_Tel;
	}
	public void setText_Tel(String text_Tel) {
		this.Text_Tel = text_Tel;
	}
	public String getTxt_Correo() {
		return Txt_Correo;
	}
	public void setTxt_Correo(String txt_Correo) {
		this.Txt_Correo = Encriptar.aesEncryptStr(txt_Correo, Encriptar.pkey);
	}
	@Override
	public String toString() {
		return "Usuario [Id_Usr_Apk=" + Id_Usr_Apk + ", Txt_Nombre=" + Txt_Nombre + ", Txt_Pas=" + Txt_Pas
				+ ", Txt_Ape_Pat=" + Txt_Ape_Pat + ", Txt_Ape_Mat=" + Txt_Ape_Mat + ", Fecha_Ult_Con=" + Fecha_Ult_Con
				+ ", Fecha_Carg=" + Fecha_Carg + ", Text_Tel=" + Text_Tel + ", Txt_Correo=" + Txt_Correo + "]";
	}
	
	

}
