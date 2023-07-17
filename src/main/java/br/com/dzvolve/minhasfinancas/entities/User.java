package br.com.dzvolve.minhasfinancas.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@FlywayDataSource
@Table(name = "USERS")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_register")
    private LocalDate dateRegister;

    public User(String name, String email) {
    }
}
