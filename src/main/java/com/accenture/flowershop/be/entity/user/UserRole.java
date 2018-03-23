package com.accenture.flowershop.be.entity.user;

import org.springframework.stereotype.Component;
import javax.persistence.*;



@Entity(name = "UserRole")
@Table(name = "UserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_userRole")
    @SequenceGenerator(name = "seq_userRole", sequenceName = "seq_userRole", allocationSize = 1)
    @Column(name = "userRole_id")
    private int userTypeId;

    @Column(name = "type")
    private String type;

    public UserRole(){
    }

    public UserRole(int userTypeId, String type) {
        this.userTypeId = userTypeId;
        this.type = type;
    }

    public int getUserType_id() {
        return userTypeId;
    }

    public void setUserType_id(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
