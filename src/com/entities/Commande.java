package com.entities;

public class Commande {
	
	private int id_cmd;
	private int id_client;
	private String produit;
	private int nombre;
	private int prix;
	
	public Commande(int id_cmd, int id_client, String produit, int nombre, int prix) {
		this.id_cmd = id_cmd;
		this.id_client = id_client;
		this.produit = produit;
		this.nombre = nombre;
		this.prix = prix;
	}
	
	public int getId_cmd() {
		return id_cmd;
	}


	public int getId_client() {
		return id_client;
	}


	public String getProduit() {
		return produit;
	}


	public int getNombre() {
		return nombre;
	}


	public int getPrix() {
		return prix;
	}


	public void setId_cmd(int id_cmd) {
		this.id_cmd = id_cmd;
	}


	public void setId_client(int id_client) {
		this.id_client = id_client;
	}


	public void setProduit(String produit) {
		this.produit = produit;
	}


	public void setNombre(int nombre) {
		this.nombre = nombre;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	@Override
	public String toString() {
		return "Commande [id_cmd=" + id_cmd + ", id_client=" + id_client + ", produit=" + produit + ", nombre=" + nombre
				+ ", prix=" + prix + "]";
	}



}
