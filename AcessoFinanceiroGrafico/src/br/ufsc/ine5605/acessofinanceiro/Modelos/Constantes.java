/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

/**
 *
 * Define as Strings constantes utilizadas no projeto.
 *
 * @author thiagobrezinski
 */
public class Constantes {

    //COMUM
    public static final String COMUM_BOTAO_VOLTAR = "Voltar";
    public static final String COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL = "Voltar ao Menu Principal";
    public static final String COMUM_DATA = "Data";
    public static final String COMUM_NOME = "Nome";
    public static final String COMUM_MATRICULA = "Matrícula";
    public static final String COMUM_DATA_NASCIMENTO = "Data de Nascimento";
    public static final String COMUM_TELEFONE = "Telefone";
    public static final String COMUM_SALARIO = "Salário";
    public static final String COMUM_CARGO = "Cargo";
    public static final String COMUM_CODIGO = "Código";
    public static final String COMUM_OQUE_DESEJA_FAZER = "O que você deseja fazer?";
    public static final String COMUM_BOTAO_CANCELAR = "Cancelar";
    public static final String COMUM_BOTAO_ALTERAR = "Alterar";
    public static final String COMUM_BOTAO_CADASTRAR = "Cadastrar";
    public static final String COMUM_BOTAO_EDITAR = "Editar";
    public static final String COMUM_SIM = "Sim";
    public static final String COMUM_NAO = "Não";
    public static final String COMUM_VAZIO = "";

    //MENU PRINCIPAL
    public static final String PRINCIPAL_TITULO = "AcessFIN - Controle como nenhum outro!";
    public static final String PRINCIPAL_SUBTITULO = "Versão 12.0.1.3";
    public static final String PRINCIPAL_MENU_ACESSO = "Acessar o Financeiro";
    public static final String PRINCIPAL_MENU_FUNCIONARIOS = "Gerenciar Funcionários";
    public static final String PRINCIPAL_MENU_CARGOS = "Gerenciar Cargos";
    public static final String PRINCIPAL_MENU_DATA = "Gerenciar Data do Sistema";
    public static final String PRINCIPAL_MENU_RELATORIO = "Emitir Relatórios";
    public static final String PRINCIPAL_MENU_SAIR = "Sair";
    public static final String PRINCIPAL_MENU_BEM_VINDO = "Bem vindo ao melhor controle de acessos da galáxia!";

    //ACESSO AO FINANCEIRO
    public static final String ACESSO_FINANCEIRO = "Acesso ao Financeiro";
    public static final String ACESSO_INSIRA_MATRICULA = "Insira a matrícula de acesso: ";
    public static final String ACESSO_MATRICULA_INVALIDA = "Matrícula inválida";
    public static final String ACESSO_BOTAO_ACESSAR = "Acessar";
    public static final String ACESSO_NEGADO_TITULO = "Acesso Negado";
    public static final String ACESSO_PERMITIDO = "Financeiro acessado com sucesso!";
    public static final String ACESSO_PERMITIDO_TITULO = "Acesso Permitido";

    //REGISTRO
    public static final String REGISTRO_TITULO = "Relatório de Acessos Negados";
    public static final String REGISTRO_NUMERO = "Número";
    public static final String REGISTRO_MOTIVO = "Motivo";
    public static final String REGISTRO_BOTAO_FILTRAR = "Filtrar";
    public static final String REGISTRO_LABEL_FILTRO_MOTIVO = "Filtrar por motivo: ";
    public static final String REGISTRO_LABEL_FILTRO_MATRICULA = "Filtrar por matrícula: ";
    public static final String REGISTRO_FILTRO_TODOS = "Todos";
    public static final String REGISTRO_FILTRO_VAZIO = "";
    public static final String REGISTRO_FILTRO_MATRICULA_INVALIDA = "A matrícula deve conter somente números";
    public static final String REGISTRO_FILTRO_INVALIDO = "Filtro inválido";

    //FUNCIONARIO
    public static final String GERENCIAR_FUNCIONARIO_TITULO = "Gerenciar Funcionários";
    public static final String GERENCIAR_FUNCIONARIO_CADASTRAR = "Cadastrar Funcionário";
    public static final String GERENCIAR_FUNCIONARIO_EDITAR = "Editar Funcinário";
    public static final String GERENCIAR_FUNCIONARIO_LISTAR = "Listar Funcionário";
    public static final String GERENCIAR_FUNCIONARIO_DELETAR = "Deletar Funcionário";
    public static final String GERENCIAR_FUNCIONARIO_NOME = "Nome: ";
    public static final String GERENCIAR_FUNCIONARIO_MATRICULA = "Matrícula: ";
    public static final String GERENCIAR_FUNCIONARIO_DATA_NASCIMENTO = "Data de Nascimento: ";
    public static final String GERENCIAR_FUNCIONARIO_TELEFONE = "Telefone: ";
    public static final String GERENCIAR_FUNCIONARIO_SALARIO = "Salario: ";
    public static final String GERENCIAR_FUNCIONARIO_CARGO = "Cargo: ";
    public static final String GERENCIAR_FUNCIONARIO_TITULO_DELETADO_SUCESSO = "Funcionário deletado com sucesso!";
    public static final String GERENCIAR_FUNCIONARIO_NADA_SELECIONADO = "Nenhum funcionário selecionado.";
    public static final String GERENCIAR_FUNCIONARIO_TITULO_CADASTRADO_SUCESSO = "Funcionário cadastrado com sucesso!";
    public static final String GERENCIAR_FUNCIONARIO_DELETADO_SUCESSO = "Funcionário deletado com sucesso!";
    public static final String GERENCIAR_FUNCIONARIO_EDITADO_SUCESSO = "Funcionário editado com sucesso!";
    public static final String GERENCIAR_FUNCIONARIO_MATRICULA_JA_EXISTENTE = "O funcionário não cadastrado. Matrícula ja existente.";
    public static final String GERENCIAR_FUNCIONARIO_CONFIRMACAO_DELETAR = "Você deseja mesmo excluir o funcionário?";
    public static final String GERENCIAR_FUNCIONARIO_ERRO_NOME = "Erro: o nome deve possuir mais de 3 letras e não deve possuir números.";
    public static final String GERENCIAR_FUNCIONARIO_DATA_NASCIMENTO_INVALIDA = "Data de Nascimento inválida. Utilize o formato correto.";
    public static final String GERENCIAR_FUNCIONARIO_FORMATO_DATA_NASCIMENTO = "*Insira uma data de nascimento no formato dd-MM-yyyy";

    //CARGO
    public static final String CARGO_TITULO = "Gerenciar Cargo";
    public static final String CARGO_NOME = "Nome: ";
    public static final String CARGO_CODIGO = "Codigo: ";
    public static final String CARGO_CADASTRAR = "Cadastrar Cargo";
    public static final String CARGO_CADASTRADO_SUCESSO = "Cargo cadastrado com sucesso!";
    public static final String CARGO_EDITAR = "Editar Cargo";
    public static final String CARGO_EDITADO_SUCESSO = "Cargo editado com sucesso!";
    public static final String CARGO_DELETAR = "Deletar Cargo";
    public static final String CARGO_EH_GERENCIAL = "É Gerencial";
    public static final String CARGO_TEM_ACESSO = "Tem Acesso";
    public static final String CARGO_INICIO_MANHA = "Hora Inicio Manhã";
    public static final String CARGO_FIM_MANHA = "Hora Fim Manhã";
    public static final String CARGO_INICIO_TARDE = "Hora Inicio Tarde";
    public static final String CARGO_FIM_TARDE = "Hora Fim Tarde";
    public static final String CARGO_FIM_ESPECIAL = "Hora Fim Especial";
    public static final String CARGO_INICIO_ESPECIAL = "Hora Inicio Especial";
    public static final String CARGO_DELETADO_SUCESSO = "Cargo deletado com sucesso!";
    public static final String CARGO_NADA_SELECIONADO = "Selecione um cargo.";
    public static final String CARGO_TIPO_GERENCIAL = "Gerencial";
    public static final String CARGO_TIPO_COMERCIAL = "Comercial";
    public static final String CARGO_TIPO_ESPECIAL = "Especial";
    public static final String CARGO_TIPO_COMUM = "Comum";
    public static final String CARGO_TIPO_SEM_ACESSO = "Sem acesso";
    public static final String CARGO_TIPO = "Tipo do Cargo: ";
    public static final String CARGO_CODIGO_JA_CADASTRADO = "Código já cadastrado.";
    public static final String CARGO_NOME_INVALIDO_LETRAS = "O nome deve conter somente letras.";
    public static final String CARGO_CODIGO_INVALIDO_LETRAS = "O código deve conter somente números.";
    public static final String CARGO_CODIGO_INVALIDO_TAMANHO = "O código deve conter no máximo 9 caracteres.";
    public static final String CARGO_NOME_INVALIDO_TAMANHO = "O nome deve conter mais de 3 caracteres.";
    public static final String CARGO_FORMATO_HORA = "*Formato utilizado: HH:mm";
	public static final String CARGO_HORA_INICIO_MANHA_COMERCIAL = "08:00";
    public static final String CARGO_HORA_FIM_MANHA_COMERCIAL = "12:00";
    public static final String CARGO_HORA_INICIO_TARDE_COMERCIAL = "14:00";
    public static final String CARGO_HORA_FIM_TARDE_COMERCIAL = "18:00";
	public static final String CARGO_INDEFINIDO = "Cargo Indefinido";

    //DATA SISTEMA
    public static final String DATA_SISTEMA_TITULO = "Data e Hora do Sistema";
    public static final String DATA_SISTEMA_ATUAL = "Data e Hora do Sistema: ";
    public static final String DATA_SISTEMA_BOTAO_ALTERAR = "Alterar Data/Hora do Sistema";
    public static final String DATA_SISTEMA_NOVO = "Nova data/hora do sistema: ";
    public static final String DATA_SISTEMA_SUCESSO = "Data e Hora do sistema atualizadas com sucesso!";
    public static final String DATA_SISTEMA_FORMATO_INCORRETO = "Data e hora não atualizadas, formato incorreto.";
    public static final String DATA_SISTEMA_FORMATO = "*Formato utilizado: dd-MM-yyyy HH:mm";

    //MOTIVO
    public static final String MOTIVO_MATRICULA_INEXISTENTE = "Matrícula inexistente";
    public static final String MOTIVO_CARGO_SEM_ACESSO = "Cargo sem acesso";
    public static final String MOTIVO_HORARIO_NAO_PERMITIDO = "Horário não permitido";
    public static final String MOTIVO_ACESSO_BLOQUEADO = "Acesso bloqueado";

    //FORMATADOR
    public static final String FORMATADOR_ERRO_CONSTANTE = "Erro na constante do formatador";
	public static final String FORMATADOR_MEIA_NOITE = "00:00";
	public static final String FORMATADOR_HORA = "HH:mm";

    //EXCEPTION
    public static final String EXCEPTION_MATRICULA_INEXISTENTE = "Matricula inexistente";

}
