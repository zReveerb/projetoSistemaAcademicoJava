package turma;
import listarInfo.*;
import Aluno.*;
import java.util.*;
import funcionario.*;
public class Turma implements ListarInfo {
    private String nomeDisciplina;
    private int creditos;
    private Professor professor;
    private String codigoDisciplina;
    private ArrayList<Alunos> alunos = new ArrayList<>();
    private String sala;

    public Turma(String nomeDisciplina, int horasCadeira, Professor professor, String codigo, String sala){

        this.nomeDisciplina = nomeDisciplina;
        this.creditos = horasCadeira/15;
        this.professor = professor;
        this.codigoDisciplina = codigo;
        this.sala = sala;

    }
    public ArrayList<Alunos> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Alunos> alunos) {
        this.alunos = alunos;
    }
    public void addAlunos(Alunos aluno){
        this.alunos.add(aluno);
    }

    Comparator<Alunos> compara = new Comparator<Alunos>() {
        public int compare(Alunos t1, Alunos t2) {

            if(t1.getNome().compareToIgnoreCase(t2.getNome()) >= 1){
                return 1;
            }
            if(t1.getNome().compareToIgnoreCase(t2.getNome()) == 0){
                return 0;
            }
            else{
                return -1;
            }

        }
    };

    public void listarInfo(){
        System.out.println("\nCódigo: "+this.codigoDisciplina +"| Disciplina: "+this.nomeDisciplina+"| Professor: "+professor.getNome());
        System.out.println("+--------------------------------------------------+-------+-------+-------+-------+-------+--------+------------+");
        System.out.println("|Nome                                              |Unid. 1|Unid. 2|Unid. 3| Final | Media | Faltas |  Situação  |");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        alunos.sort(compara);
        for(int i = 0; i < alunos.size(); i++){
            int letras = 50 - alunos.get(i).getNome().length();

            System.out.printf("| %s",  alunos.get(i).getNome());

            for (int j = letras; j > 0; j--) {
                System.out.print(" ");
            }

            ArrayList<Boletim> boletins = alunos.get(i).getBoletins();

            for(Boletim lista: boletins){
                if((lista.getNomeDisciplina().equals(this.nomeDisciplina))){
                    lista.listarInfo();
                }
            }
        }
    }
    public int getCreditos(){
        return creditos;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }
}
