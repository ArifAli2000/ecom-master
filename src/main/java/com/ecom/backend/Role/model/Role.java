package com.ecom.backend.role.model;

import com.ecom.backend.user.model.User;
import com.ecom.backend.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {
//    @Id
//    @Column(name = "Role_Id", nullable = false)
//    private  String roleId;
    @Column(name = "Role_Name",nullable = false)
    private  String roleName;

    @ManyToMany(mappedBy = "role")
    Set<User> user = new HashSet<>();
}
