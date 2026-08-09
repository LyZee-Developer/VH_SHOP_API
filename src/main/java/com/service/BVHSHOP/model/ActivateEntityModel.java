package com.service.BVHSHOP.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class ActivateEntityModel extends BaseModel {
    private Boolean isActivate = true;
}
