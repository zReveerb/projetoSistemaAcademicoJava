package funcionario;
import listarInfo.*;
import java.time.LocalDate;

public class auxiliarAdministrativo extends Funcionario implements ListarInfo {
    private String setor;
    public auxiliarAdministrativo(String cpf, String nome, String ID, String email, LocalDate data_admissao, double salario, String telefone, String setor) {
        super(cpf, nome, ID, email, data_admissao, salario, telefone);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }
    public void mudaSetor(String setor) {
        this.setor = setor;
    }

    public void aumentaSalario(double aumento) {
        setSalario(this.getSalario()*(1+(aumento/100.0)));
    }

    @Override
    public void listarInfo() {
        System.out.println("|-------------------------------------------------------|");
        System.out.println("\nNome: "+nome);
        System.out.println("Setor: "+setor);
        System.out.println("E-mail: "+email);
        System.out.println("Telefone: "+telefone);
        System.out.println("Data de admissão: "+data_admissao);
        System.out.printf("Salário: R$%.2f\n", getSalario());

    }
}
