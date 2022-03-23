package fr.univlille.da2i.hubert.etu.tricount.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity extends UserEntity {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @ColumnDefault("'USER'")
    private String role = "USER";

    @Column(name = "enabled", nullable = false)
    @ColumnDefault("'true'")
    private boolean enabled = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity user;

    @Override
    public String toString() {
        return "AccountEntity{" +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }

}