package org.trial.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.trial.model.Todo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoRepository {

    @PersistenceContext
    EntityManager em;

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public void persist(Todo t) {
        em.persist(t);
    }

    public Todo merge(Todo t) {
        return em.merge(t);
    }

    public List<Todo> listActive() {
        // No streams (explicit loop)
        List<Todo> entities = em.createQuery(
                        "SELECT t FROM Todos t WHERE t.deletedAt IS NULL ORDER BY t.createdAt ASC", Todo.class)
                .getResultList();
        List<Todo> out = new ArrayList<>(entities.size());
        for (int i = 0; i < entities.size(); i++) {
            out.add(entities.get(i));
        }
        return out;
    }
}
