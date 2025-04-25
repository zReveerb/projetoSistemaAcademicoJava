package funcionario;
import listarInfo.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Professor extends Funcionario implements ListarInfo {
    private int QuantPesq;
    private ArrayList<String> pesquisasMinistradas = new ArrayList<>();
    private String departamento;

    private String sala;
    private ArrayList<String> turmasLecionadas = new ArrayList<>();

    public Professor(String cpf, String nome, String ID, String email, LocalDate data_admissao, double salario, String telefone) {
        super(cpf, nome, ID, email, data_admissao, salario, telefone);
    }

    public void aumentaSalario(){
        setSalario(getSalario()*(1+QuantPesq/10.0));
    }
    public void listarInfo(){
        System.out.println("|-------------------------------------------------------|");
        System.out.println("\nNome: "+nome);
        System.out.println("Departamento: "+departamento);
        System.out.println("Sala: "+sala);
        System.out.println("Turmas lecionadas: "+turmasLecionadas);
        System.out.printf("Salário: R$%.2f\n", getSalario());
        System.out.println("Data de Admissão: "+data_admissao);
        System.out.println("Email: "+email);
        System.out.println("Telefone: "+telefone);
        System.out.println("Pesquisas Ministradas: "+pesquisasMinistradas);

    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    public void addPesquisasMinistradas(String pesq){
        pesquisasMinistradas.add(pesq);
    }
    public void addTurmasLecionadas(String turma){
        turmasLecionadas.add(turma);
    }

    public void setQuantPesq(int quantPesq) {
        QuantPesq = quantPesq;
    }
    public int getQuantPesq(){
        return QuantPesq;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

