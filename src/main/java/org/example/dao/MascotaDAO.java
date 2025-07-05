package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.aplicacion.Coordinador;
import org.example.aplicacion.JPAUtil;
import org.example.entidades.Mascota;

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    Coordinador miCoordinador;
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public String registrarMascota(Mascota miMascota) {

        entityManager.getTransaction().begin();
        entityManager.persist(miMascota);
        entityManager.getTransaction().commit();

        String resp = "Mascota Registrada!";

        return resp;
    }

    public Mascota consultarMascota(Long idMascota) {

        Mascota miMascota = entityManager.find(Mascota.class, idMascota);

        if (miMascota != null) {
            return miMascota;
        } else {
            return null;
        }

    }

    public List<Mascota> consultarListaMascotas() {

        List<Mascota> listaMascotas = new ArrayList<Mascota>();
        Query query = entityManager.createQuery("SELECT m FROM Mascota m");
        listaMascotas = query.getResultList();

        return listaMascotas;
    }

    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {

        List<Mascota> listaMascotas = new ArrayList<Mascota>();
        String sentencia = "SELECT m FROM Mascota m WHERE m.sexo= :sexoMascota";
        Query query = entityManager.createQuery(sentencia);
        query.setParameter("sexoMascota", sexo);
        listaMascotas = query.getResultList();

        return listaMascotas;
    }

    public String actualizarMascota(Mascota miMascota) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        String resp;
        try {
            em.getTransaction().begin();

            Mascota mascotaExistente = em.find(Mascota.class, miMascota.getIdMascota());
            if (mascotaExistente == null) {
                resp = "No existe mascota con el ID: " + miMascota.getIdMascota();
            } else {

                mascotaExistente.setNombre(miMascota.getNombre());
                mascotaExistente.setRaza(miMascota.getRaza());
                mascotaExistente.setColorMascota(miMascota.getColorMascota());
                mascotaExistente.setSexo(miMascota.getSexo());

                em.getTransaction().commit();
                resp = "se actualizo la mascota";
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            resp = "Error al actualizar la mascota: " + e.getMessage();
        } finally {
            em.close();
        }
        return resp;
    }

    public String eliminarMascota(Mascota miMascota) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        String resp;
        try {
            em.getTransaction().begin();

            Mascota mascotaExistente = em.find(Mascota.class, miMascota.getIdMascota());
            if (mascotaExistente == null) {
                resp = "No haymascota con ese id " + miMascota.getIdMascota();
            } else {
                em.remove(mascotaExistente);
                em.getTransaction().commit();
                resp = "se elimino la mascota";
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            resp = "no se puedo eliminar la mascotita" + e.getMessage();
        } finally {
            em.close();
        }
        return resp;
    }

    public void close() {
        entityManager.close();
        JPAUtil.shutdown();
    }

    public void setMiCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;

    }

}
