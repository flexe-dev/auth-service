package com.flexe.authservice.entity.relationships;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CreationRelationship<T> {

    private Long id;
    private Date createdAt;
    private T root;

    public CreationRelationship(){
    }

}