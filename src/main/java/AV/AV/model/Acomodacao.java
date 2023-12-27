package AV.AV.model;

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


@Entity(name="acomodacao")
@Table(name="acomodacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Acomodacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String localizacao;
    private int numero_registro;
    private int quantidade_quartos_disponiveis;
    
    @ManyToOne
    @JoinColumn(name = "anfitriao_id") //Chave estrangeira
    private Anfitriao anfitriao;




    /*ID, nome, localização, número de registro, quantidade de quartos
disponíveis, anfitrião.*/
}
