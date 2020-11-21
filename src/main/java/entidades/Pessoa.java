package entidades;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
}
