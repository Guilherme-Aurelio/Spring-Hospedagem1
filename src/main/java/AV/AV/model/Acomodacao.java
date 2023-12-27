package AV.AV.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String localizacao;
    
    @Min(value = 1, message = "Número de registro deve ser maior que zero")
    private int numero_registro;

    @Min(value = 0, message = "Quantidade de quartos disponíveis não pode ser negativa")
    private int quantidade_quartos_disponiveis;
    
    @ManyToOne
    @JoinColumn(name = "anfitriao_id") //Chave estrangeira
    private Anfitriao anfitriao;




    /*ID, nome, localização, número de registro, quantidade de quartos
disponíveis, anfitrião.*/
}
