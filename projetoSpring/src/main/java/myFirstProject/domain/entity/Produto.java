package myFirstProject.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data //==> Equivale as anotações Getter Setter To String e EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id" )
    private Integer id;

    @Column(name ="descricao" )
    private String descricao;

    @Column(name ="preco_unitario" )
    private BigDecimal preco;

}
