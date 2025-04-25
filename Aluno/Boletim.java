package Aluno;
import listarInfo.*;
public class Boletim implements ListarInfo {

    private String nomeDisciplina;
    private double nota1;
    private double nota2;
    private double nota3;
    private int faltas;
    private boolean aprovado;
    private double mediaFinal;
    private double notaFim;

    public void calculaAprovacao() {
        calcularFinal();
        this.aprovado = (((nota1 + nota2 + nota3) / 3.0 >= 7) || this.mediaFinal >= 5) && this.faltas <= 15;
    }

    public void setNomeDisciplina(String nome) {
        this.nomeDisciplina = nome;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public Boletim(String nome, double nota1, double nota2, double nota3, int faltas, double notaFinal) {
        this.nomeDisciplina = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.faltas = faltas;
        this.notaFim = notaFinal;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public double getMediaFinal() {
        return this.mediaFinal;
    }
    public void calcularFinal(){
        if(this.notaFim != -1){
            this.mediaFinal = ((notaFim*4)+((nota1+nota2+nota3)/3)*6)/10;
        }
        else if(faltas <= 15){
            this.mediaFinal = (nota1+nota2+nota3)/3.0;
        }
        else{
            this.mediaFinal = 0.0;
        }

    }

    public void listarInfoDisciplina() {
        calculaAprovacao();
        calcularFinal();

        int letras = 40 - this.nomeDisciplina.length();
        System.out.printf("|  %s", this.nomeDisciplina);

        for (int i = letras; i > 0; i--) {
            System.out.print(" ");
        }
        lista();
    }
    public void listarInfo() {
        calculaAprovacao();
        calcularFinal();
        lista();
    }
    public String getNomeDisciplina(){
        return this.nomeDisciplina;
    }
    public void lista(){
        String situacao;
        if (aprovado) {
            situacao = "Aprovado";
        } else {
            situacao = "Reprovado";
        }
        if (notaFim == -1) {

            if (faltas >= 10) {
                System.out.printf("|  %.1f |  %.1f |  %.1f  |  ---  |  %.1f  |   %d   | %s  |\n", this.nota1, this.nota2, this.nota3, this.mediaFinal, this.faltas, situacao);
            } else {
                System.out.printf("|  %.1f |  %.1f |  %.1f  |  ---  |  %.1f   |  %d    | %s  |\n", this.nota1, this.nota2, this.nota3, this.mediaFinal, this.faltas, situacao);
            }
        }
        else{
            if (faltas >= 10) {
                System.out.printf("|  %.1f |  %.1f |  %.1f  |  %.1f  |  %.1f  |  %d   | %s  |\n", this.nota1, this.nota2, this.nota3,this.notaFim, this.mediaFinal, this.faltas, situacao);
            } else {
                System.out.printf("|  %.1f |  %.1f |  %.1f  |  %.1f  |  %.1f  |  %d    | %s  |\n", this.nota1, this.nota2, this.nota3, this.notaFim, this.mediaFinal, this.faltas, situacao);
            }
        }
    }
}
