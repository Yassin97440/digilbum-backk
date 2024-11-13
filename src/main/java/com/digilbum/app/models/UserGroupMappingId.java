package com.digilbum.app.models;

import com.digilbum.app.security.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserGroupMappingId implements Serializable {
    private static final long serialVersionUID = 5913799549091878042L;
    //    @MapsId("user_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //    @MapsId("group_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        UserGroupMappingId entity = (UserGroupMappingId) o;
//        return Objects.equals(this.groupId, entity.groupId) &&
//                Objects.equals(this.userId, entity.userId);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(group.getId(), user.getId());
    }

}