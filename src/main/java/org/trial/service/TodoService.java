package org.trial.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.trial.dto.TodoResponse;
import org.trial.dto.TodoSaveRequest;
import org.trial.model.Todo;
import org.trial.repository.TodoRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoRepository repo;

    @Transactional
    public TodoResponse save(TodoSaveRequest req) {
        if (req == null || req.getDescription() == null || req.getDescription().isBlank()) {
            throw new IllegalArgumentException("description is required");
        }

        Todo entity;
        if (req.getId() == null) {
            // INSERT
            entity = Todo.builder()
                    .description(req.getDescription().trim())
                    .createdBy(req.getActor())
                    .updatedBy(req.getActor())
                    .createdAt(OffsetDateTime.now())
                    .build();
            repo.persist(entity);
        } else {
            // UPDATE
            entity = repo.findById(req.getId());
            if (entity == null || entity.getDeletedAt() != null) {
                throw new IllegalStateException("Todo not found or already deleted");
            }
            entity.setDescription(req.getDescription().trim());
            entity.setUpdatedBy(req.getActor());
            // updatedAt handled by @PreUpdate on flush/commit
            entity = repo.merge(entity);
        }
        return TodoResponse.of(entity);
    }

    @Transactional
    public void softDelete(Long id, String actor) {
        Todo entity = repo.findById(id);
        if (entity == null || entity.getDeletedAt() != null) {
            throw new IllegalStateException("Todo not found or already deleted");
        }
        entity.setDeletedAt(OffsetDateTime.now());
        entity.setUpdatedBy(actor);
        entity.setUpdatedAt(OffsetDateTime.now());
        repo.merge(entity);
    }

    @Transactional
    public List<TodoResponse> listActive() {
        List<Todo> rows = repo.listActive();
        List<TodoResponse> out = new ArrayList<>(rows.size());
        for (int i = 0; i < rows.size(); i++) {
            out.add(TodoResponse.of(rows.get(i)));
        }
        return out;
    }
}
