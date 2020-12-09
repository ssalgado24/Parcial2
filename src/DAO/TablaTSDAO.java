/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Entidad.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author Samuel
 */
public class TablaTSDAO {
    private static final EntityManagerFactory 
            emf= Persistence.createEntityManagerFactory("Parcial2PU");
    
    public void crear (TablaTS object){
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean eliminar (TablaTS object){
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret= false;
        try {
            em.remove(object);
            em.getTransaction().commit();
            ret=true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }      
    }
    
    public TablaTS leer (TablaTS par){
        EntityManager em= emf.createEntityManager();
        TablaTS s= null;
        Query q= em.createQuery("SELECT u FROM TipoSensor u " + 
                " WHERE u.tipo LIKE :tipo" + 
                " AND u.nombre LIKE :nombre " + 
                " AND u.minimo LIKE :minimo " +
                " AND u.maximo LIKE :maximo ")
                .setParameter("tipo", par.getTipo())
                .setParameter("nombre", par.getNombre())
                .setParameter("minimo", par.getMinimo())
                .setParameter("maximo", par.getMaximo());
        try {
            s = (TablaTS) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            s = (TablaTS) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return s;
        }
    }
    
    public boolean actualizar (TablaTS object, TablaTS nuevoObjeto){
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret= false;
        try {
            object= leer(object);
            object.setTipo(nuevoObjeto.getTipo());
            object.setNombre(nuevoObjeto.getNombre());
            object.setMinimo(nuevoObjeto.getMinimo());
            object.setMaximo(nuevoObjeto.getMaximo());
            em.merge(object);
            em.getTransaction().commit();
            ret=true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        } 
    }
}
