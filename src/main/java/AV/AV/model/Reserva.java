package AV.AV.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name="reserva")
@Table(name="reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*Atributos: ID, data de início, data de fim, acomodação reservada, hóspede que
realizou a reserva. */
    @Column(name = "data_inicio") 
    private String dataInicio;
    @Column(name = "data_fim")
    private String dataFim;
    /*falta acomodacao e hospede*/


    @ManyToOne
    @JoinColumn(name="acomodacao_id")
    private Acomodacao acomodacao;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

}
