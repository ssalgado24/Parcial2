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
public class TablaSensorDAO {
    private static final EntityManagerFactory 
            emf= Persistence.createEntityManagerFactory("Parcial2PU");
    
    public void crear (TablaSensor object){
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
    
    public boolean eliminar (TablaSensor object){
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
    
    public TablaSensor leer (TablaSensor par){
        EntityManager em= emf.createEntityManager();
        TablaSensor s= null;
        Query q= em.createQuery("SELECT u FROM Sensor u " + 
                " WHERE u.idsensor LIKE :idsensor" + 
                " AND u.ubicacion LIKE :ubicacion " + 
                " AND u.tipo LIKE :tipo ")
                .setParameter("idsensor", par.getIdsensor())
                .setParameter("ubicacion", par.getUbicacion())
                .setParameter("tipo", par.getTipo());
        try {
            s = (TablaSensor) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            s = (TablaSensor) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return s;
        }
    }
    
    public boolean actualizar (TablaSensor object, TablaSensor nuevoObjeto){
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret= false;
        try {
            object= leer(object);
            object.setIdsensor(nuevoObjeto.getIdsensor());
            object.setUbicacion(nuevoObjeto.getUbicacion());
            object.setTipo(nuevoObjeto.getTipo());
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
