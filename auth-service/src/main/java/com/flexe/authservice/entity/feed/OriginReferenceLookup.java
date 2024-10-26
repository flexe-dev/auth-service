package com.flexe.authservice.entity.feed;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OriginReferenceLookup {

    private OriginReferenceKey key;
    private Boolean isActive = true;
    private Date referenceDate;

    public OriginReferenceLookup() {
    }

}
