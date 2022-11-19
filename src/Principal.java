import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

class Principal {
    public static void main(String[] args) {

        //Todo: Inserir todos os funcionários, na mesma ordem e informações da tabela.
        List<Funcionario> funcionarioList = new ArrayList<>();

        funcionarioList.add(new Funcionario("Maria", LocalDate.of(2020,10,18),BigDecimal.valueOf(2009.44),"Operador"));
        funcionarioList.add(new Funcionario("João", LocalDate.of(1990,5,12),BigDecimal.valueOf(2284.38),"Operador"));
        funcionarioList.add(new Funcionario("Caio", LocalDate.of(1961,5,2),BigDecimal.valueOf(9836.14),"Coordenador"));
        funcionarioList.add(new Funcionario("Miguel", LocalDate.of(1988,10,14),BigDecimal.valueOf(19119.88),"Diretor"));
        funcionarioList.add(new Funcionario("Alice", LocalDate.of(1995,1,5),BigDecimal.valueOf(2234.68),"Recepcionista"));
        funcionarioList.add(new Funcionario("Heitor", LocalDate.of(1999,11,19),BigDecimal.valueOf(1582.72),"Operador"));
        funcionarioList.add(new Funcionario("Arthur", LocalDate.of(1993,3,31),BigDecimal.valueOf(4071.84),"Contador"));
        funcionarioList.add(new Funcionario("Laura", LocalDate.of(1994,7,8),BigDecimal.valueOf(3017.45),"Gerente"));
        funcionarioList.add(new Funcionario("Heloísa", LocalDate.of(2003,5,24),BigDecimal.valueOf(1606.85),"Eletricista"));
        funcionarioList.add(new Funcionario("Helena", LocalDate.of(1996,9,2),BigDecimal.valueOf(2799.93),"Gerente"));

        //Todo: Remover o funcionário “João” da lista.
        funcionarioList.removeIf(x->x.getNome().equals("João"));

        // Todo: Imprimir todos os funcionários com todas suas informações, sendo que: formato data dd/mm/aaaa; valor numérico formatado com separador de milhar como ponto e decimal como vírgula.
        System.out.println("1) Lista de Funcionários\n");
        FuncionarioService.imprimirLista(funcionarioList);
        FuncionarioService.separador();

        //Todo: Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        for (Funcionario funcionario : funcionarioList) {
            FuncionarioService.aumentarSalario(funcionario, 0.1);
        }
        System.out.println("2) Lista de Funcionários Atualizada\n");
        FuncionarioService.imprimirLista(funcionarioList);
        FuncionarioService.separador();

        //Todo: Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Set<String> pegarFuncoes = new HashSet<>();
        for (Funcionario funcionario : funcionarioList) {
            pegarFuncoes.add(funcionario.getFuncao());
        }
        List<String> listaDeFuncoes = new ArrayList<>(pegarFuncoes);
        Map<String, List<Funcionario>> mapa = new HashMap<>();

        for (int i = 0; i < pegarFuncoes.size(); i++){
            List<Funcionario> lista = FuncionarioService.agruparPorFuncao(funcionarioList,listaDeFuncoes.get(i));
            mapa.put(listaDeFuncoes.get(i),lista);
        }

        //Todo: Imprimir os funcionários, agrupados por função.
        System.out.println("3) Lista de funcionários por função");
        for (String nomeDaFuncao : listaDeFuncoes) {
            System.out.println("\nLista de " + nomeDaFuncao);
            FuncionarioService.imprimirLista(mapa.get(nomeDaFuncao));
        }
        FuncionarioService.separador();

        //Todo: Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("4) Lista de Aniversariantes");
        FuncionarioService.aniversarianteDoMes(funcionarioList,10);
        FuncionarioService.aniversarianteDoMes(funcionarioList,12);
        FuncionarioService.separador();

        //Todo: Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("5) Funcionário mais velho");
        FuncionarioService.funcionarioMaisVelho(funcionarioList);
        FuncionarioService.separador();

        //Todo: Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("6) Lista de funcionários em ordem alfabética\n");
        FuncionarioService.imprimirLista(FuncionarioService.ordernarListaPorNome(funcionarioList));
        FuncionarioService.separador();


        //Todo: Imprimir o total dos salários dos funcionários.
        System.out.println("7) Total dos salários dos funcionários");
        FuncionarioService.somarSalarios(funcionarioList);
        FuncionarioService.separador();

        //Todo: Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("8) Quantidade aproximada de salários minimos por funcionário\n");
        FuncionarioService.quantidadeDeSalarioMinimos(funcionarioList, BigDecimal.valueOf(1212.00));

    }


}