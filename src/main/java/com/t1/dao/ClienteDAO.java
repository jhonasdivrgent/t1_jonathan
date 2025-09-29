package com.t1.dao;

import com.t1.model.Cliente;
import com.t1.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {

    public void guardar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Cliente buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public List<Cliente> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente); // merge actualiza el objeto en BD
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    

    }

    public void eliminar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.getTransaction().begin();
                em.remove(cliente);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
