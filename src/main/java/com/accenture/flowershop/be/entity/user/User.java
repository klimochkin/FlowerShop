package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;



@XmlRootElement
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", allocationSize = 1)
    @Column(name = "user_id")
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private BigDecimal account;

    @Column
    private String tel;

    @Column
    private Integer discount ;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userRole_id")
    private UserRole userRole;


    //==========================================================================================


    public User(){
    }

    public User( String firstName, String lastName, String address, BigDecimal account, String tel, Integer discount, String username, String password, String email, UserRole userRole) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.account = account;
        this.tel = tel;
        this.discount = discount;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAccount() {
        return account;
    }

    @XmlElement
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    @XmlElement
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDiscount() {
        return discount;
    }

    @XmlElement
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername() {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @XmlElement
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
