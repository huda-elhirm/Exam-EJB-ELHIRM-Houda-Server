package org.example;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class CDUsersImpl implements CDUsers{


    @PersistenceContext(unitName = "gestion_cine_ejb")
    private EntityManager em;

    public List<CD> listAvailableCDs() {
        return em.createQuery("SELECT c FROM CD c WHERE c.isAvailable = true", CD.class).getResultList();
    }

    public List<CD> listBorrowedCDs(Long userId) {
        return em.createQuery("SELECT c FROM CD c WHERE c.borrowedBy.id = :userId", CD.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void borrowCD(Long userId, Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            Users user = em.find(Users.class, userId);
            cd.setBorrowedBy(user);
            cd.setAvailable(false);
            em.merge(cd);
        }
    }

    public void returnCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && !cd.isAvailable()) {
            cd.setBorrowedBy(null);
            cd.setAvailable(true);
            em.merge(cd);
        }
    }

}
