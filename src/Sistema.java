import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sistema {
    DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatarSalario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public void deletarFuncionario(String funcionario, List<Funcionario> lista){
        lista.removeIf(x->x.getNome().equals(funcionario));
        System.out.println(funcionario + " foi removido(a) da lista de funcionários");
    }
    public void imprimirLista(List<Funcionario> lista) {

        for (Funcionario funcionario : lista) {

            LocalDate dataNascimento = funcionario.getDataNascimento();
            String dataNascimentoFormatada = dataNascimento.format(formatarData);
            var salario = new BigDecimal(String.valueOf(funcionario.getSalario()));

            System.out.println(
                    "- Nome: " + funcionario.getNome() + ", Data de Nascimento: " + dataNascimentoFormatada + ", Salário: " + formatarSalario.format(salario) + ", Função: " + funcionario.getFuncao());
        }
    }

    public void aumentarSalario(Funcionario funcionario, Double porcentagemAumento) {
        BigDecimal salarioAtual = funcionario.getSalario();
        BigDecimal novoSalario = (salarioAtual.multiply(BigDecimal.valueOf(porcentagemAumento))).add(salarioAtual);
        funcionario.setSalario(novoSalario);
    }

    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao (List<Funcionario> lista) {
        List<String> listaDeFuncoes = new ArrayList<>(Sistema.pesquisarFuncoes(lista));
        Map<String, List<Funcionario>> mapa = new HashMap<>();
        for (String funcao : listaDeFuncoes) {
            List<Funcionario> listaDeFuncionariosComAMesmaFuncao = Sistema.filtrarFuncionariosPorFuncao(lista, funcao);
            mapa.put(funcao,  listaDeFuncionariosComAMesmaFuncao);
        }
        return mapa;
    }

    public static Collection<String> pesquisarFuncoes(List<Funcionario> lista) {
        Set<String> listaDeFuncoes = new HashSet<>();
        for (Funcionario funcionario : lista) {
            listaDeFuncoes.add(funcionario.getFuncao());
        }
        return listaDeFuncoes;
    }

    public static List<Funcionario> filtrarFuncionariosPorFuncao (List<Funcionario> lista, String funcao) {

        List<Funcionario> funcionariosPorFuncao = new ArrayList<>();
        for (Funcionario funcionario : lista) {
            if (funcionario.getFuncao().equals(funcao)) {
                funcionariosPorFuncao.add(funcionario);
            }
        }
        return funcionariosPorFuncao;
    }

    public void filtrarAniversariantesDoMes(List<Funcionario> lista, int mes) {
        List<String> listaDeAniversariantes = new ArrayList<>();
        for (Funcionario funcionario : lista) {
            if (funcionario.getDataNascimento().getMonth().getValue() == mes) {
                listaDeAniversariantes.add(funcionario.getDataNascimento().getDayOfMonth() + "/" + mes + ": " + funcionario.getNome());
                Collections.sort(listaDeAniversariantes);
            }
        }
        System.out.println("\nAniversariante(s) do mês " + mes);

        if (listaDeAniversariantes.size() == 0) {
            System.out.println("- Não há aniversariantes no mês " + mes);
        } else {
            for (String aniversariante : listaDeAniversariantes) {
                System.out.println("- " + aniversariante);
            }
        }
    }

    public void pesquisarFuncionarioMaisVelho(List<Funcionario> lista) {

        String nomeDoMaisVelho = null;
        int idadeDoMaisVelho = 0;

        for (Funcionario funcionario : lista) {
            int idadeFuncionario = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
            if (idadeFuncionario > idadeDoMaisVelho) {
                idadeDoMaisVelho = idadeFuncionario;
                nomeDoMaisVelho = funcionario.getNome();
            }
        }
        System.out.println("- Nome: " + nomeDoMaisVelho + ", Idade: " + idadeDoMaisVelho + " anos");
    }

    public List<Funcionario> nomesEmOrdemCrescente (List<Funcionario> lista) {
        List<String> nomesEmOrdem = new ArrayList<>();
        List<Funcionario> funcionarioEmOrdem = new ArrayList<>();

        for (Funcionario funcionario : lista) {
            nomesEmOrdem.add(funcionario.getNome());
        }
        Collections.sort(nomesEmOrdem);

        for (int i = 0; i < lista.size(); i++) {
            for (Funcionario funcionario : lista) {
                if (Objects.equals(funcionario.getNome(), nomesEmOrdem.get(i))) {
                    funcionarioEmOrdem.add(funcionario);
                }
            }
        }
        return funcionarioEmOrdem;
    }

    public void somarSalarios(List<Funcionario> lista) {
        BigDecimal somaDosSalarios = BigDecimal.valueOf(0.0);
        for (Funcionario funcionario : lista) {
            somaDosSalarios = somaDosSalarios.add(funcionario.getSalario());
        }
        System.out.println(formatarSalario.format(somaDosSalarios));
    }

    public void calcularQuantidadeDeSalarioMinimos(List<Funcionario> lista, BigDecimal salarioMinimo) {

        for (Funcionario funcionario : lista) {
            BigDecimal calcSalarioMinimos = funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_UP);
            System.out.printf("- " + funcionario.getNome() + ": %.1f salários mínimos\n", calcSalarioMinimos);
        }
    }

    public void titulo(String texto){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_PURPLE = "\u001B[35m";
        System.out.println(ANSI_PURPLE + texto + ANSI_RESET);
    }

    public void separador() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GRAY = "\u001B[37m";
        System.out.println(ANSI_GRAY + "\n-------------------------------------------------------------------------------------------------" + ANSI_RESET);
    }
}
