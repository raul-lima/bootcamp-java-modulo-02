package br.com.alura.carteira.modelo;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@ToString(exclude = "senha")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String login;
    private String senha;
}
