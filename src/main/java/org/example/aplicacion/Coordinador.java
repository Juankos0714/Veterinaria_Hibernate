package org.example.aplicacion;



import org.example.dao.MascotaDAO;
import org.example.entidades.Mascota;
import org.example.vistas.VentanaConsultarLista;
import org.example.vistas.VentanaConsultarMascota;
import org.example.vistas.VentanaPrincipal;
import org.example.vistas.VentanaRegistrarMascota;

import java.util.List;

public class Coordinador {
	private VentanaPrincipal ventanaPrincipal;
	private VentanaRegistrarMascota ventanaRegistrarMascota;
	private VentanaConsultarMascota ventanaConsultarMascota;
	private VentanaConsultarLista ventanaConsultarLista;
	private MascotaDAO mascotaDAO;

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public void setVentanaRegistrarMascota(VentanaRegistrarMascota ventanaRegistrarMascota) {
		this.ventanaRegistrarMascota = ventanaRegistrarMascota;
	}

	public void setVentanaConsultarMascota(VentanaConsultarMascota ventanaConsultarMascota) {
		this.ventanaConsultarMascota = ventanaConsultarMascota;
	}

	public void setVentanaConsultarLista(VentanaConsultarLista ventanaConsultarLista) {
		this.ventanaConsultarLista = ventanaConsultarLista;
	}

	public void setMascotaDAO(MascotaDAO mascotaDAO) {
		this.mascotaDAO = mascotaDAO;
	}

	public void mostrarVentanaRegistrar() {
		ventanaRegistrarMascota.setVisible(true);
	}

	public void mostrarVentanaConsultarLista() {
		ventanaConsultarLista.setVisible(true);
	}

	public void mostrarVentanaConsultar() {
		ventanaConsultarMascota.setVisible(true);
	}

	public void mostrarVentanaPrincipal() {
		ventanaPrincipal.setVisible(true);
	}

	public String registrarMascota(Mascota mascota) {
		return mascotaDAO.registrarMascota(mascota);

	}
	public Mascota consultarMascota(Long idMascota) {
		return mascotaDAO.consultarMascota(idMascota);
	}
	public List<Mascota> consultarListaMascotas(Long idMascota) {
		return mascotaDAO.consultarListaMascotas();
	}
	public List<Mascota> consultarListaMascotasSexo(String sexo) {
		return mascotaDAO.consultarListaMascotasPorSexo(sexo);
	}
	public String eliminarMascota(Mascota mascota) {
		return mascotaDAO.eliminarMascota(mascota);
	}
	public String ActualizarMascota(Mascota mascota) {
		return mascotaDAO.actualizarMascota(mascota);
	}

	

}
