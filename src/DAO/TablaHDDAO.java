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
public class TablaHDDAO {
     private static final EntityManagerFactory 
            emf= Persistence.createEntityManagerFactory("Parcial2PU");
    
    public void crear (TablaHD object){
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
    
    public boolean eliminar (TablaHD object){
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
}
