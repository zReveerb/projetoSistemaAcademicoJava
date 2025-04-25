package Aluno;

import turma.Turma;

import java.util.ArrayList;

public class Alunos {
    private String nome;
    private String email;
    private String matricula;
    private ArrayList<Turma> turmas = new ArrayList<>();
    private double CRA;
    private ArrayList<Boletim> boletins = new ArrayList<>();

    public Alunos(String nome, String email, String matricula ) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;

    }

    public double calcularCRA() {
        double cumpridos = 0, soma = 0;
        for (Turma turminha : turmas) {
            for (int i = 0; i < boletins.size(); i++) {
                if (turminha.getNomeDisciplina().equals(boletins.get(i).getNomeDisciplina())) {
                    soma += turminha.getCreditos() * boletins.get(i).getMediaFinal();
                    cumpridos += turminha.getCreditos();
                }
            }
            this.CRA = Math.round((100*(soma/cumpridos)))/100.0;

        }
        return this.CRA;
    }
    public void mostrarHistorico(){
        System.out.println("\nNome: "+this.nome+" |Matrícula: "+this.matricula);
        System.out.println("+-----------------------------------------+-------+-------+-------+-------+-------+--------+-----------+");
        System.out.println("|                  Disciplina             |Unid. 1|Unid. 2|Unid. 3| Final | Media | Faltas | Situação  |");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (Turma turminha : turmas) {
            for (int i = 0; i < boletins.size(); i++) {
                if (turminha.getNomeDisciplina().equals(boletins.get(i).getNomeDisciplina())) {
                    boletins.get(i).listarInfoDisciplina();
                }
            }
        }
    }
    public void criarBoletim(String nome, double nota1, double nota2, double nota3, int faltas, double notaFim){
        boletins.add(new Boletim(nome, nota1,nota2,nota3,faltas,notaFim));
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArrayList<Turma> turmas) {
        this.turmas = turmas;
    }
    public void addTurmas(Turma turma){
        this.turmas.add(turma);
    }
    public ArrayList<Boletim> getBoletins(){
        return this.boletins;
    }

    public String getNome() {
        return nome;
    }
    public void printaNome(){
        int letras = 50 - this.nome.length();
        System.out.printf("| %s", this.nome);
        for (int i = letras; i > 0; i--) {
            System.out.printf(" ");
        }
    }

    public String getMatricula() {
        return matricula;
    }
}
