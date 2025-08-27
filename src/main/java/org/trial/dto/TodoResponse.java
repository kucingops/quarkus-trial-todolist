package org.trial.dto;

import lombok.Data;
import org.trial.model.Todo;

import java.time.OffsetDateTime;

@Data
public class TodoResponse {
    private Long id;
    private String description;
    private String createdBy;
    private String updatedBy;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    // helper mapper
    public static TodoResponse of(Todo t) {
        TodoResponse r = new TodoResponse();
        r.setId(t.getId());
        r.setDescription(t.getDescription());
        r.setCreatedBy(t.getCreatedBy());
        r.setUpdatedBy(t.getUpdatedBy());
        r.setCreatedAt(t.getCreatedAt());
        r.setUpdatedAt(t.getUpdatedAt());
        return r;
    }
}
