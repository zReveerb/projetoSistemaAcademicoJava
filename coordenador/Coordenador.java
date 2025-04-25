package coordenador;

import Aluno.Alunos;
import funcionario.Professor;
import funcionario.auxiliarAdministrativo;
import turma.Turma;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Coordenador {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bem vindo usuário! Este é um sistema de cadastro e visualização acadêmica da UFPB!");
        ArrayList<Professor> professors = new ArrayList<>();
        ArrayList<auxiliarAdministrativo> auxiliares = new ArrayList<>();
        ArrayList<Turma> turmas = new ArrayList<>();
        ArrayList<Alunos> alunos = new ArrayList<>();
        int contaPROF = 0;
        boolean loop = true;
        try {
            while (loop) {
                String escolha;
                escolha = JOptionPane.showInputDialog(null, "Digite:\n" +
                        "'Professor' para acessar o menu de professores\n" +
                        "'Auxiliar' para acessar o menu de auxiliares\n" +
                        "'Alunos' para acessar o menu de alunos\n" +
                        "'Turmas' para acessar o menu de turmas\n" +
                        "'Sair' para sair do sistema\n");
                switch (escolha.toLowerCase()) {
                    case "sair":
                        JOptionPane.showMessageDialog(null, "Obrigado pela preferência!");
                        loop = false;
                        break;
                    case "professor":
                        String escolhaPROF = JOptionPane.showInputDialog(null, "Digite:\n" +
                                "'Criar' para inserir um novo professor ao sistema\n" +
                                "'Listar' para listar as informações públicas dos professores\n" +
                                "'Nome' para acessar um professor em específico");
                        switch (escolhaPROF.toLowerCase()) {
                            case "criar":
                                String nome = JOptionPane.showInputDialog(null, "Digite o nome completo do(a) professor(a):");
                                String telefone = JOptionPane.showInputDialog(null, "Digite o telefone(a) do(a) professor(a): " + nome);
                                String email = JOptionPane.showInputDialog(null, "Digite o email do(a) professor(a): " + nome);
                                double salario;
                                try {
                                    salario = Double.parseDouble(JOptionPane.showInputDialog(null,
                                            "Digite o salário do(a) professor(a) " + nome));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                    continue;
                                }
                                String ID = JOptionPane.showInputDialog(null, "Digite o ID do(a) professor(a): " + nome);
                                String CPF = JOptionPane.showInputDialog(null, "Digite o CPF do(a) professor(a): " + nome);
                                String dataAdmissao = JOptionPane.showInputDialog("Digite a data de admissão (dd/MM/yyyy) do professor(a): " + nome);


                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate data = null;
                                try {
                                    data = LocalDate.parse(dataAdmissao, formato);
                                } catch (DateTimeParseException e) {
                                    JOptionPane.showMessageDialog(null, "Formato de data inválido!");
                                }
                                if (data != null) {
                                    professors.add(new Professor(CPF, nome, ID, email, data, salario, telefone));
                                    contaPROF++;
                                } else {
                                    continue;
                                }
                                while (true) {
                                    String disciplinas = JOptionPane.showInputDialog("Digite a disciplina ministrada: ");
                                    professors.get(contaPROF - 1).addTurmasLecionadas((disciplinas));
                                    String ha = JOptionPane.showInputDialog("Há mais alguma? (sim/não) ");
                                    if (ha.equalsIgnoreCase("sim")) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                                professors.get(contaPROF - 1).setDepartamento(JOptionPane.showInputDialog(null, "Digite o departamento do(a): " + nome));
                                professors.get(contaPROF - 1).setSala(JOptionPane.showInputDialog(null, "Digite a sala do(a): " + nome));
                                String profPESQUISA = JOptionPane.showInputDialog(null, nome + " está envolvido(a) em alguma pesquisa? (sim/não)");
                                if (profPESQUISA.equalsIgnoreCase("sim")) {
                                    int pesquisas;
                                    try {
                                        pesquisas = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas pesquisas?"));
                                        professors.get(contaPROF - 1).setQuantPesq(pesquisas);
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Digite um número!");
                                    }

                                    professors.get(contaPROF - 1).aumentaSalario();
                                    JOptionPane.showMessageDialog(null, "Seu salário irá aumentar pela quantidade de pesquisas em 10% por cada uma!");
                                    for (int i = 0; i < professors.get(contaPROF - 1).getQuantPesq(); i++) {
                                        professors.get(contaPROF - 1).addPesquisasMinistradas((JOptionPane.showInputDialog(null, "Digite o título de sua pesquisa em que está envolvido(a)")));
                                    }
                                }
                                break;
                            case "listar":
                                for (Professor prof : professors) {
                                    prof.listarInfo();
                                }
                                break;
                            case "nome":
                                String nomeProf = JOptionPane.showInputDialog(null, "Digite o nome do(a) professor(a): ");
                                boolean achouProf = false;
                                for (Professor professor : professors) {
                                    if (professor.getNome().equalsIgnoreCase(nomeProf)) {
                                        achouProf = true;
                                        int lista_ou_nao;
                                        try {
                                            lista_ou_nao = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                    "Digite:\n1 - Digitar novo salário\n2 - Listar informações"));
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite apenas 1 ou 2.");
                                            continue;
                                        }
                                        if (lista_ou_nao == 1) {
                                            double salarioNovo;
                                            try {
                                                salarioNovo = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Digite o novo salario do(a) professor(a) "));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                continue;
                                            }
                                            professor.setSalario(salarioNovo);
                                        } else if (lista_ou_nao == 2) {
                                            professor.listarInfo();
                                        } else {
                                            System.out.println("ESCOLHA INVÁLIDA!");
                                        }
                                    }
                                    if (!(achouProf)) {
                                        JOptionPane.showMessageDialog(null, "Nenhum professor com esse nome no sistema!");
                                        continue;
                                    }
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Nenhuma opção compatível.");
                        }
                        break;
                    case "auxiliar":
                        String escolhaAux = JOptionPane.showInputDialog(null, "Digite:\n" +
                                "'Criar' para inserir um novo auxiliar ao sistema\n" +
                                "'Listar' para listar as informações públicas dos auxiliares administrativos\n" +
                                "'Nome' para acessar um auxiliar em específico");
                        switch (escolhaAux.toLowerCase()) {
                            case "criar":
                                String nome = JOptionPane.showInputDialog(null, "Digite o nome completo do(a) auxiliar:");
                                String setor = JOptionPane.showInputDialog(null, "Digite o setor do(a) auxiliar: " + nome);

                                String telefone = JOptionPane.showInputDialog(null, "Digite o telefone(a) do(a) auxiliar: " + nome);
                                String email = JOptionPane.showInputDialog(null, "Digite o email do(a) auxiliar: " + nome);
                                double salario;
                                try {
                                    salario = Double.parseDouble(JOptionPane.showInputDialog(null,
                                            "Digite o salário do(a) auxiliar " + nome));
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                    continue;
                                }

                                String ID = JOptionPane.showInputDialog(null, "Digite o ID do(a) auxiliar: " + nome);
                                String CPF = JOptionPane.showInputDialog(null, "Digite o CPF do(a) auxiliar: " + nome);
                                String dataAdmissao = JOptionPane.showInputDialog("Digite a data de admissão (dd/MM/yyyy) do(a) auxiliar: " + nome);


                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate data = null;
                                try {
                                    data = LocalDate.parse(dataAdmissao, formato);
                                } catch (DateTimeParseException e) {
                                    JOptionPane.showMessageDialog(null, "Formato de data inválido!");
                                }
                                if (data != null) {
                                    auxiliares.add(new auxiliarAdministrativo(CPF, nome, ID, email, data, salario, telefone, setor));

                                } else {
                                    continue;
                                }
                                break;
                            case "listar":
                                for (auxiliarAdministrativo aux : auxiliares) {
                                    aux.listarInfo();
                                }
                                break;
                            case "nome":
                                String nomeAux = JOptionPane.showInputDialog(null, "Digite o nome do(a) auxiliar: ");
                                boolean achouAux = false;
                                for (auxiliarAdministrativo auxiliar : auxiliares) {
                                    if (auxiliar.getNome().equalsIgnoreCase(nomeAux)) {
                                        achouAux = true;
                                        int lista_ou_nao;
                                        try {
                                            lista_ou_nao = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                    "Digite:\n1 - Aumentar salário\n2 - Listar informações\n3 - Mudar setor"));
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite apenas 1, 2 ou 3.");
                                            continue;
                                        }
                                        if (lista_ou_nao == 1) {
                                            double aumento;
                                            try {
                                                aumento = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Digite o percentual de aumento: "));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                continue;
                                            }
                                            auxiliar.aumentaSalario(aumento);
                                        } else if (lista_ou_nao == 2) {
                                            auxiliar.listarInfo();
                                        } else if (lista_ou_nao == 3) {
                                            String setorNovo = JOptionPane.showInputDialog("Digite o novo setor de: " + auxiliar.getNome());
                                            auxiliar.mudaSetor(setorNovo);
                                        } else {
                                            System.out.println("ESCOLHA INVÁLIDA!");
                                        }
                                    }
                                }
                                if (!(achouAux)) {
                                    JOptionPane.showMessageDialog(null, "Nenhum auxiliar com esse nome no sistema!");
                                    continue;
                                }
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Nenhuma opção compatível.");
                        }
                        break;

                    case "turmas":
                        String escolhaTurma = JOptionPane.showInputDialog(null, "Digite:\n" +
                                "'Criar' para inserir um nova turma ao sistema\n" +
                                "'Listar' para listar todas as informações das turmas presentes no sistema\n" +
                                "'Disciplina' para acessar uma disciplina em específico");
                        switch (escolhaTurma.toLowerCase()) {
                            case "criar":
                                String profTurma = JOptionPane.showInputDialog(null, "Digite o nome do professor da turma (precisa estar presente no sistema anteriormente): ");
                                boolean nomeValido = false;
                                for (Professor professor : professors) {
                                    if (professor.getNome().equalsIgnoreCase(profTurma)) {
                                        nomeValido = true;
                                        String disciplina = JOptionPane.showInputDialog(null, "Digite o nome da disciplina que desejas criar: ");
                                        int horas;
                                        try {
                                            horas = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantas horas essa cadeira apresenta? "));
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números inteiros!");
                                            continue;

                                        }

                                        String codigo = JOptionPane.showInputDialog(null, "Digite o codigo da disciplina que desejas criar: ");
                                        String sala = JOptionPane.showInputDialog(null, "Qual a sala que irão ocorrer as aulas?");
                                        turmas.add(new Turma(disciplina, horas, professor, codigo, sala));

                                    }
                                }
                                if (!(nomeValido)) {
                                    System.out.println("Professor não encontrado no sistema!");
                                }
                                break;
                            case "listar":
                                for (Turma turminha : turmas) {

                                    turminha.listarInfo();
                                    System.out.println("");
                                }
                                break;
                            case "disciplina":
                                String dialogo = JOptionPane.showInputDialog(null, "Digite o nome da disciplina a qual desejas acessar: ");
                                boolean turmaPresente = false;
                                for (Turma turminha : turmas) {
                                    if (turminha.getNomeDisciplina().equalsIgnoreCase(dialogo)) {
                                        turmaPresente = true;
                                        String escolhaDisciplina = JOptionPane.showInputDialog(null, "Digite:\n" +
                                                "'Criar' para adicionar um novo aluno a turma\n " +
                                                "'Adicionar' para adicionar um aluno já existente na turma\n" +
                                                "'Listar' para listar informações de somente essa turma em específico");

                                        switch (escolhaDisciplina.toLowerCase()) {
                                            case "listar":
                                                turminha.listarInfo();
                                                break;
                                            case "criar":
                                                String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno: (Máx 40 caracteres) ");
                                                double nota1, nota2, nota3, notaFim = -1;
                                                int faltas;
                                                try {
                                                    nota1 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                            "Digite a primeira nota (0 a 10) "));
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                    continue;
                                                }
                                                try {
                                                    nota2 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                            "Digite a segunda nota (0 a 10)"));
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                    continue;
                                                }
                                                try {
                                                    nota3 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                            "Digite a terceira nota (0 a 10)"));
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                    continue;
                                                }
                                                try {
                                                    faltas = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                            "Digite a quantidadade de faltas: "));
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números inteiros!");
                                                    continue;
                                                }
                                                if ((nota1 + nota2 + nota3) / 3.0 < 7 && (nota1 + nota2 + nota3) / 3.0 >= 4 && faltas <= 15) {
                                                    try {
                                                        notaFim = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                "O aluno foi para final! Digite a nota tirada por ele na prova final: (0 a 10) "));
                                                    } catch (NumberFormatException e) {
                                                        JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                        continue;
                                                    }
                                                }

                                                String email = JOptionPane.showInputDialog(null, "Digite o email de " + nomeAluno);
                                                String matricula = JOptionPane.showInputDialog(null, "Digite a matricula de " + nomeAluno);
                                                Alunos novoAluno = new Alunos(nomeAluno, email, matricula);
                                                novoAluno.criarBoletim(turminha.getNomeDisciplina(), nota1, nota2, nota3, faltas, notaFim); //Se a nota fim for negativa por ‘default’, significa que o aluno passou direto, caso não seja, então o aluno foi para final
                                                novoAluno.addTurmas(turminha);
                                                turminha.addAlunos(novoAluno);
                                                alunos.add(novoAluno);
                                                break;

                                            case "adicionar":
                                                String nomeAlunoExistente = JOptionPane.showInputDialog(null, "Digite o nome do aluno ou a sua matrícula:");
                                                boolean existeAluno = false;
                                                for (Alunos alunos1 : alunos) {
                                                    if (alunos1.getNome().equalsIgnoreCase(nomeAlunoExistente) || alunos1.getMatricula().equalsIgnoreCase(nomeAlunoExistente)) {
                                                        existeAluno = true;
                                                        JOptionPane.showMessageDialog(null, "Aluno adicionado!");
                                                        double notaPrimeira, notaSegunda, notaTerceira, notaFinal = -1;
                                                        int faltasDoAluno;
                                                        try {
                                                            notaPrimeira = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                    "Digite a primeira nota (0 a 10) "));
                                                        } catch (NumberFormatException e) {
                                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                            continue;
                                                        }
                                                        try {
                                                            notaSegunda = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                    "Digite a segunda nota (0 a 10)"));
                                                        } catch (NumberFormatException e) {
                                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                            continue;
                                                        }
                                                        try {
                                                            notaTerceira = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                    "Digite a terceira nota (0 a 10)"));
                                                        } catch (NumberFormatException e) {
                                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                            continue;
                                                        }
                                                        try {
                                                            faltasDoAluno = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                    "Digite a quantidadade de faltas: "));
                                                        } catch (NumberFormatException e) {
                                                            JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números inteiros!");
                                                            continue;
                                                        }
                                                        if ((notaPrimeira + notaSegunda + notaTerceira) / 3.0 < 7 && (notaPrimeira + notaSegunda + notaTerceira) / 3.0 >= 4 && faltasDoAluno <= 15) {
                                                            try {
                                                                notaFinal = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                                        "O aluno foi para final! Digite a nota tirada por ele na prova final: (0 a 10) "));
                                                            } catch (NumberFormatException e) {
                                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                                continue;
                                                            }
                                                        }
                                                        alunos1.criarBoletim(turminha.getNomeDisciplina(), notaPrimeira, notaSegunda, notaTerceira, faltasDoAluno, notaFinal);
                                                        turminha.addAlunos(alunos1);
                                                        alunos1.addTurmas(turminha);
                                                    }
                                                }
                                                if (!(existeAluno)) {
                                                    JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
                                                }
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null, "Nenhuma opção compatível.");
                                                break;
                                        }
                                    }
                                }
                                if (!turmaPresente) {
                                    JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                                }
                        }
                        break;
                    case "alunos":
                        String escolhaAluno = JOptionPane.showInputDialog(null, "Digite:\n" +
                                "'Criar' para inserir um novo(a) aluno(a) ao sistema\n" +
                                "'Listar' para listar todas as informações dos(as) alunos(as) presentes no sistema\n" +
                                "'Nome' para acessar um(a) aluno(a) em específico");

                        switch (escolhaAluno.toLowerCase()) {
                            case "criar":
                                String nomeAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno: (Max 40 caracteres) ");
                                String email = JOptionPane.showInputDialog(null, "Digite o email de " + nomeAluno);
                                String matricula = JOptionPane.showInputDialog(null, "Digite a matricula de " + nomeAluno);

                                String escolhaDoAluno = JOptionPane.showInputDialog(null, "Desejas adicionar " + nomeAluno + " em uma turma já existente? (sim/não)");
                                if (escolhaDoAluno.equalsIgnoreCase("sim")) {
                                    String turmaDoAluno = JOptionPane.showInputDialog(null, "Digite o código da disciplina ou o nome");
                                    boolean turmaEncontrada = false;
                                    for (Turma turminha : turmas) {
                                        if (turminha.getNomeDisciplina().equalsIgnoreCase(turmaDoAluno) || turminha.getCodigoDisciplina().equalsIgnoreCase(turmaDoAluno)) {
                                            turmaEncontrada = true;
                                            JOptionPane.showMessageDialog(null, "Aluno adicionado!");
                                            double nota1, nota2, nota3, notaFim = -1;
                                            int faltas;
                                            try {
                                                nota1 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Digite a primeira nota (0 a 10) "));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                continue;
                                            }
                                            try {
                                                nota2 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Digite a segunda nota (0 a 10)"));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                continue;
                                            }
                                            try {
                                                nota3 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                        "Digite a terceira nota (0 a 10)"));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                continue;
                                            }
                                            try {
                                                faltas = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                        "Digite a quantidadade de faltas: "));
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números inteiros!");
                                                continue;
                                            }
                                            if ((nota1 + nota2 + nota3) / 3.0 < 7 && (nota1 + nota2 + nota3) / 3.0 >= 4 && faltas <= 15) {
                                                try {
                                                    notaFim = Double.parseDouble(JOptionPane.showInputDialog(null,
                                                            "O aluno foi para final! Digite a nota tirada por ele na prova final: (0 a 10) "));
                                                } catch (NumberFormatException e) {
                                                    JOptionPane.showMessageDialog(null, "Entrada inválida! Digite números!");
                                                    continue;
                                                }
                                            }
                                            Alunos novoAluno = new Alunos(nomeAluno, email, matricula);
                                            novoAluno.criarBoletim(turminha.getNomeDisciplina(), nota1, nota2, nota3, faltas, notaFim);
                                            novoAluno.addTurmas(turminha);
                                            alunos.add(novoAluno);
                                            turminha.addAlunos(novoAluno);

                                        }
                                    }
                                    if (!(turmaEncontrada)) {
                                        JOptionPane.showMessageDialog(null, "Turma não encontrada!");
                                    }

                                } else {

                                    alunos.add(new Alunos(nomeAluno, email, matricula));
                                }
                                break;
                            case "listar":
                                for (Alunos alunos1 : alunos) {
                                    alunos1.mostrarHistorico();
                                }
                                break;

                            case "nome":

                                String nomeDoAluno = JOptionPane.showInputDialog(null, "Digite o nome ou matrícula do(a) aluno que desejas acessar as informações!)");
                                boolean alunoEncontrado = false;

                                for (Alunos alunos1 : alunos) {
                                    if (alunos1.getNome().equalsIgnoreCase(nomeDoAluno) || alunos1.getMatricula().equalsIgnoreCase(nomeDoAluno)) {
                                        alunoEncontrado = true;
                                        String escolhaComAluno = JOptionPane.showInputDialog(null, "Digite: " +
                                                "CRA - Para exibir o CRA de " + nomeDoAluno +
                                                "\nExibir - Para exibir todo o histórico de " + nomeDoAluno);

                                        switch (escolhaComAluno.toLowerCase()) {
                                            case "cra":
                                                JOptionPane.showMessageDialog(null, "O CRA de " + nomeDoAluno + " é " + alunos1.calcularCRA());
                                                break;

                                            case "exibir":
                                                alunos1.mostrarHistorico();
                                        }
                                    }
                                }
                                if (!alunoEncontrado) {
                                    JOptionPane.showMessageDialog(null, "Aluno não encontrado no sistema!");
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opção inválida");

                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida");
                }

            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Obrigado pela preferência!");

        }

    }
}
