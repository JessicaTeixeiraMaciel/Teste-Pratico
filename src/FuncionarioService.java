import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FuncionarioService {
    public static void aumentarSalario (Funcionario funcionario, Double porcentagemAumento){
        BigDecimal salarioAtual = funcionario.getSalario();
        BigDecimal novoSalario = (salarioAtual.multiply(BigDecimal.valueOf(porcentagemAumento))).add(salarioAtual);
        funcionario.setSalario(novoSalario);
    }

    public static void imprimirLista(List<Funcionario> lista){

        for (Funcionario funcionario : lista){

            LocalDate dataNascimento = funcionario.getDataNascimento();
            DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataNascimentoFormatada = dataNascimento.format(formatarData);

            var salario = new BigDecimal(String.valueOf(funcionario.getSalario()));
            NumberFormat formatarSalario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            System.out.println(
                    "- Nome: " + funcionario.getNome() + ", Data de Nascimento: " +  dataNascimentoFormatada + ", Salário: " + formatarSalario.format(salario) + ", Função: " + funcionario.getFuncao());
        }
    }

    public static List<Funcionario> agruparPorFuncao(List<Funcionario> lista, String funcao){
        List<Funcionario> funcionarioPorFuncao = new ArrayList<>();
        for (Funcionario funcionario : lista) {
            if(funcionario.getFuncao().equals(funcao)){
                funcionarioPorFuncao.add(funcionario);
            }
        }
        return funcionarioPorFuncao;
    }

    public static List<Funcionario> ordernarListaPorNome (List<Funcionario> lista){
        List<String> nomesEmOrdem = new ArrayList<>();
        List<Funcionario> funcionarioEmOrdem = new ArrayList<>();


        for (Funcionario funcionario : lista) {
            nomesEmOrdem.add(funcionario.getNome());
        }
        Collections.sort(nomesEmOrdem);

        int i = 0;
        while (i < lista.size()) {
            for (Funcionario funcionario : lista){
                if (Objects.equals(funcionario.getNome(), nomesEmOrdem.get(i))) {
                    funcionarioEmOrdem.add(funcionario);
                }
            }
            i++;
        }


        return funcionarioEmOrdem;
    }

    public static void aniversarianteDoMes(List<Funcionario> lista, int mes){
        List<String> aniversariantes = new ArrayList<>();
        for (Funcionario funcionario : lista){
            if (funcionario.getDataNascimento().getMonth().getValue() == mes){
                aniversariantes.add(funcionario.getDataNascimento().getDayOfMonth() + "/" + mes + ": " + funcionario.getNome());
                Collections.sort(aniversariantes);
            }
        }
        System.out.println("\nAniversariante(s) do mês " + mes);

        if (aniversariantes.size() == 0){
            System.out.println("- Não há aniversariantes no mês " + mes);
        } else{
            for (String aniversariante : aniversariantes) {
                System.out.println("- " + aniversariante);
            }
        }
    }
    public static void funcionarioMaisVelho(List<Funcionario> lista){

        List<LocalDate> idades = new ArrayList<>();
        for (Funcionario funcionario : lista) {
            idades.add(funcionario.getDataNascimento());
        }

        for (Funcionario funcionario : lista) {
            int idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
            if (funcionario.getDataNascimento().equals(Collections.min(idades))){
                System.out.println("- Nome: " + funcionario.getNome() + ", Idade: " + idade + " anos");
                break;
            }
        }
    }


    public static void somarSalarios (List<Funcionario> lista){
        BigDecimal somaDosSalarios = BigDecimal.valueOf(0.0);
        for (Funcionario funcionario : lista){
            somaDosSalarios = somaDosSalarios.add(funcionario.getSalario());
        }
        NumberFormat formatarSalario = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println(formatarSalario.format(somaDosSalarios));
    }

    public static void quantidadeDeSalarioMinimos(List<Funcionario> lista, BigDecimal salarioMinimo){

        for (Funcionario funcionario : lista){
            BigDecimal calcSalarioMinimos =  funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_UP);
            System.out.printf("- " + funcionario.getNome() + ": %.1f salários mínimos\n",calcSalarioMinimos);
        }
    }

    public static void separador(){
        System.out.println("\n-------------------------------------------------------------------------------------------------");
    }
}
