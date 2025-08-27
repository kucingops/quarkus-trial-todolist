package org.trial.dto;

import lombok.Data;

@Data
public class TodoSaveRequest {
    private Long id;           // null => insert, non-null => update
    private String description;
    private String actor;      // who performs save (set createdBy/updatedBy)
}
