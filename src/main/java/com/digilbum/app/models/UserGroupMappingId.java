package com.digilbum.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserGroupMappingId implements Serializable {
    private static final long serialVersionUID = 5913799549091878042L;
    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "GroupId", nullable = false)
    private Integer groupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserGroupMappingId entity = (UserGroupMappingId) o;
        return Objects.equals(this.groupId, entity.groupId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId);
    }

}