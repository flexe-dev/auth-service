package com.flexe.authservice.entity.relationships;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InteractionRelationship<T> {

    private Long id;
    private Date timestamp;
    private T root;

    public InteractionRelationship(){}

    @Override
    public int hashCode() {
        return root.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InteractionRelationship<?> that = (InteractionRelationship<?>) obj;
        return root.equals(that.root);
    }
}

