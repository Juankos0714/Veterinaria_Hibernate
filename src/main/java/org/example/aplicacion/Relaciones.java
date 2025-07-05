package org.example.aplicacion;


import org.example.dao.MascotaDAO;
import org.example.vistas.VentanaConsultarLista;
import org.example.vistas.VentanaConsultarMascota;
import org.example.vistas.VentanaPrincipal;
import org.example.vistas.VentanaRegistrarMascota;

public class Relaciones {
public Relaciones() {
	VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
	VentanaRegistrarMascota ventanaRegistrar = new VentanaRegistrarMascota();
	VentanaConsultarLista ventanaconsultarLista = new VentanaConsultarLista();
	VentanaConsultarMascota ventanaConsultar = new VentanaConsultarMascota();
	
	

	 MascotaDAO miMascotaDAO = new MascotaDAO();
     Coordinador miCoordinador = new Coordinador();
     
     
     
     ventanaPrincipal.setMiCoordinador(miCoordinador);
     ventanaRegistrar.setMiCoordinador(miCoordinador);
     ventanaConsultar.setMiCoordinador(miCoordinador);
     ventanaconsultarLista.setMiCoordinador(miCoordinador);
     
     miMascotaDAO.setMiCoordinador(miCoordinador);
     
     
     miCoordinador.setVentanaPrincipal(ventanaPrincipal);
     miCoordinador.setVentanaRegistrarMascota(ventanaRegistrar);
     miCoordinador.setVentanaConsultarLista(ventanaconsultarLista);
     miCoordinador.setVentanaConsultarMascota(ventanaConsultar);
     
     
     miCoordinador.setMascotaDAO(miMascotaDAO);
     
     
     miCoordinador.mostrarVentanaPrincipal();;
}
}
