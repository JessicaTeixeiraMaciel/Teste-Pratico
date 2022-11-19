import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

class Principal {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        List<Funcionario> listaDeFuncionarios = criarListaDeFuncionarios();

        listaDeFuncionarios.removeIf(x->x.getNome().equals("João"));

        System.out.println("1) Lista de funcionários\n");
        sistema.imprimirLista(listaDeFuncionarios);
        sistema.separador();

        System.out.println("2) Lista de funcionários com salários atualizados\n");
        for (Funcionario funcionario : listaDeFuncionarios) {
          sistema.aumentarSalario(funcionario,0.1);
        }
        sistema.imprimirLista(listaDeFuncionarios);
        sistema.separador();

        sistema.agruparFuncionariosPorFuncao(listaDeFuncionarios);

        System.out.println("3) Lista de funcionários agrupados por função");
        for (String nomeDaFuncao : Sistema.pesquisarFuncoes(listaDeFuncionarios)) {
            System.out.println("\nLista de " + nomeDaFuncao);
            sistema.imprimirLista(sistema.agruparFuncionariosPorFuncao(listaDeFuncionarios).get(nomeDaFuncao));
        }
        sistema.separador();

        System.out.println("4) Lista de Aniversariantes");
        sistema.filtrarAniversariantesDoMes(listaDeFuncionarios,10);
        sistema.filtrarAniversariantesDoMes(listaDeFuncionarios,12);
        sistema.separador();

        System.out.println("5) Funcionário mais velho");
        sistema.pesquisarFuncionarioMaisVelho(listaDeFuncionarios);
        sistema.separador();

        System.out.println("6) Lista de funcionários em ordem alfabética\n");
        sistema.imprimirLista(sistema.nomesEmOrdemCrescente(listaDeFuncionarios));
        sistema.separador();

        System.out.println("7) Total dos salários dos funcionários");
        sistema.somarSalarios(listaDeFuncionarios);
        sistema.separador();

        System.out.println("8) Quantidade de salários mínimos por funcionário\n");
        sistema.calcularQuantidadeDeSalarioMinimos(listaDeFuncionarios, BigDecimal.valueOf(1212.00));
    }

    private static List<Funcionario> criarListaDeFuncionarios(){

        List<Funcionario> funcionarioList = new ArrayList<>();
        funcionarioList.add(new Funcionario("Maria", LocalDate.of(2000,10,18),BigDecimal.valueOf(2009.44),"Operador"));
        funcionarioList.add(new Funcionario("João", LocalDate.of(1990,5,12),BigDecimal.valueOf(2284.38),"Operador"));
        funcionarioList.add(new Funcionario("Caio", LocalDate.of(1961,5,2),BigDecimal.valueOf(9836.14),"Coordenador"));
        funcionarioList.add(new Funcionario("Miguel", LocalDate.of(1988,10,14),BigDecimal.valueOf(19119.88),"Diretor"));
        funcionarioList.add(new Funcionario("Alice", LocalDate.of(1995,1,5),BigDecimal.valueOf(2234.68),"Recepcionista"));
        funcionarioList.add(new Funcionario("Heitor", LocalDate.of(1999,11,19),BigDecimal.valueOf(1582.72),"Operador"));
        funcionarioList.add(new Funcionario("Arthur", LocalDate.of(1993,3,31),BigDecimal.valueOf(4071.84),"Contador"));
        funcionarioList.add(new Funcionario("Laura", LocalDate.of(1994,7,8),BigDecimal.valueOf(3017.45),"Gerente"));
        funcionarioList.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24),BigDecimal.valueOf(1606.85),"Eletricista"));
        funcionarioList.add(new Funcionario("Helena", LocalDate.of(1996,9,2),BigDecimal.valueOf(2799.93),"Gerente"));

        return funcionarioList;
    }
}