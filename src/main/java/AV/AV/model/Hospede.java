package AV.AV.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/*import org.springframework.format.annotation.DateTimeFormat;*/

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name="hospede")
@Table(name="hospede")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class Hospede {
    /*ID, nome, data de registro, lista de reservas. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    /*@DateTimeFormat*/
    private LocalDateTime data_registro; /*(pattern="dd/MM/yyyy")*/

    @OneToMany(mappedBy = "hospede", fetch = FetchType.EAGER)
    private List<Reserva> reservas;

    /*falta reservas */
}
