package br.com.dzvolve.minhasfinancas.entities;

import br.com.dzvolve.minhasfinancas.enums.StatusRelease;
import br.com.dzvolve.minhasfinancas.enums.TypeRelease;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@FlywayDataSource
@Table(name = "ENTRY")
@Getter
public class Entry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private TypeRelease type;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusRelease status;

    @Column(name = "date_register")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateRegister;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
