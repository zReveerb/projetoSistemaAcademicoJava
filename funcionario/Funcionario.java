package funcionario;

import java.time.LocalDate;

public abstract class Funcionario {
    protected String Cpf;
    protected String nome;
    protected String ID;
    protected String email;
    protected LocalDate data_admissao;
    private double Salario;
    protected String telefone;

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double salario) {
        Salario = salario;
    }

    public Funcionario(String cpf, String nome, String ID, String email, LocalDate data_admissao, double salario, String telefone){
        this.Cpf = cpf;
        this.Salario = salario;
        this.data_admissao = data_admissao;
        this.nome = nome;
        this.ID = ID;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

}
