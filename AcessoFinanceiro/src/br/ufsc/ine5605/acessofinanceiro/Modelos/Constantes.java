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

    // COMUM
    public static final String INSIRA_MATRICULA = "Digite a matrícula: ";
    public static final String MATRICULA_INVALIDA = "------------ MATRÍCULA INVÁLIDA: a matrícula deve conter somente e ate 9 numeros ------------";
    public static final String OPCAO_INVALIDA = "------------ OPÇÃO INVÁLIDA: por favor digite somente um numero --";
    public static final String OPCAO_INEXISTENTE = "------------ OPÇÃO SELECIONADA NÃO EXISTE: confira as opções do menu e tente novamente ------------";
    public static final String INSIRA_OPCAO = " Digite a opção: ";
    public static final String MATRICULA_JA_CADASTRADA = "------------ MATRÍCULA INVÁLIDA: a matricula inserida ja foi cadastrada ------------";
    public static final String CODIGO_JA_CADASTRADO = "------------ CÓDIGO INVALIDO: o código inserido ja foi cadastrado ------------";
    public static final String MATRICULA_INEXISTENTE = "------------ MATRÍCULA INEXISTENTE: a matrícula inserida nao pertence a nenhum funcionario ------------";
    public static final String TENTAR_NOVAMENTE = " 1. Tentar novamente";
    public static final String VOLTAR_MENU_PRINCIPAL_1 = " 1. Voltar ao menu principal";
    public static final String VOLTAR_MENU_PRINCIPAL_2 = " 2. Voltar ao menu principal";
    public static final String VOLTAR_MENU_PRINCIPAL_3 = " 3.  Voltar ao menu principal";
    public static final String VOLTAR_MENU_PRINCIPAL_4 = " 4.  Voltar ao menu principal";
    public static final String VOLTAR_MENU_PRINCIPAL_5 = " 5. Voltar ao menu principal";
    public static final String NOME_INVALIDO_LETRAS = "------------ NOME INVÁLIDO: o nome deve conter somente letras. ------------";
    public static final String NOME_INVALIDO_TAMANHO = "------------ NOME INVÁLIDO: o nome deve conter no mínimo 3 caracteres. ------------";
    public static final String DATA_NASCIMENTO_INVALIDA = "------------ DATA DE NASCIMENTO INVÁLIDA: a data deve ser inserida no formato dd/mm/yyyy ------------";
    public static final String SIM = " 1. Sim ";
    public static final String NAO = " 2. Não";
    public static final String NOVA_TENTATIVA_FILTRO_MATRICULA = "Tentar novamente filtrar por matrícula";
    public static final String NOVA_TENTATIVA_FILTRO_MOTIVO = "Tentar novamente filtrar por motivo";
    public static final String MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO = "------------ FUNCIONÁRIO NÃO ENCONTRADO: digite uma matrícula existente. ------------";
    public static final String MENSAGEM_CARGO_NAO_ENCONTRADO = "------------ CARGO NÃO ENCONTRADO: digite um código existente. ------------";
    public static final String VOLTAR_2 = " 2. Voltar";
    public static final String VOLTAR_5 = " 5. Voltar";
    public static final String CARGO_INDEFINIDO = " Cargo Indefinido ";
    public static final String CODIGO_INVALIDO = "------------ CÓDIGO INVÁLIDO: o código deve conter somente e ate 9 números. Tente novamente. ------------";
    public static final String SALARIO_INVALIDO = "------------ SALÁRIO INVÁLIDO: o salário deve conter somente e ate 9 numeros. Tente novamente. ------------";
    public static final String TELEFONE_INVALIDO = "------------ TELEFONE INVÁLIDO: o telefone deve conter somente e ate 9 números. Tente novamente. ------------";
    public static final String CARGO_NAO_ENCONTRADO = "------------ O sistema não encontrou nenhum cargo com este codigo. Tente novamente. ------------";
    public static final String FUNCIONARIO_DELETADO_SUCESSO = "FUNCIONÁRIO DELETADO COM SUCESSO!";
    public static final String FUNCIONARIO_CADASTRADO_SUCESSO = "FUNCIONÁRIO CADASTRADO COM SUCESSO!";
    public static final String FORMATADOR_HORA = "HH:mm";
    public static final String FORMATADOR_MEIA_NOITE = "00:00";

    // ACESSO AO FINANCEIRO
    public static final String ACESSO_FINANCEIRO = "+-+-+-+-+-+-+-+ Acesso ao financeiro +-+-+-+-+-+-+-+";
    public static final String ACESSO_MATRICULA_INEXISTENTE = "------------ MATRÍCULA INSERIDA NÃO EXISTE, não foi possivel acessar o financeio. ------------";
    public static final String ACESSO_PERMITIDO = "------------ ACESSO AO FINANCEIRO REALIZADO COM SUCESSO! ------------";
    public static final String ACESSO_NEGADO_MATRICULA_INEXISTENTE = "------------ ACESSO NEGADO: matricula inexistente. ------------";
    public static final String ACESSO_NEGADO_HORARIO_NAO_PERMITIDO = "------------ ACESSO NEGADO: horario nao permitido. ------------";
    public static final String ACESSO_NEGADO_CARGO_SEM_ACESSO = "------------ ACESSO NEGADO: cargo sem acesso. ------------";
    public static final String ACESSO_NEGADO_ACESSO_BLOQUEADO = "------------ ACESSO NEGADO: acesso bloqueado. ------------";

    // RELATORIO
    public static final String RELATORIO_ACESSO = "+-+-+-+-+-+-+-+ Emitir relatórios de acesso negado +-+-+-+-+-+-+-+";
    public static final String RELATORIO_ESCOLHA_FILTRO = " Escolha o filtro: ";
    public static final String RELATORIO_FILTRO_MOTIVO = " 1. Por motivo";
    public static final String RELATORIO_FILTRO_MATRICULA = " 2. Por matrícula";
    public static final String RELATORIO_FILTRO_ESCOLHA_MOTIVO = " Escolha o motivo: ";
    public static final String RELATORIO_FILTRO_MOTIVO_MATRICULA_INEXISTENTE = " 1. Matrícula inexistente";
    public static final String RELATORIO_FILTRO_MOTIVO_CARGO_SEM_ACESSO = " 2. Cargo sem acesso";
    public static final String RELATORIO_FILTRO_MOTIVO_HORARIO_NAO_PERMITIDO = " 3. Horário nao permitido";
    public static final String RELATORIO_FILTRO_MOTIVO_ACESSO_BLOQUEADO = " 4. Acesso bloqueado";
    public static final String RELATORIO_ACESSO_MATRICULA = " Relatórios de acesso negado filtrados por matrícula: ";
    public static final String RELATORIO_ACESSO_MOTIVO_MATRICULA_INEXISTENTE = " Relatórios de acesso negado filtrados por motivo de matrícula inexistente:";
    public static final String RELATORIO_ACESSO_MOTIVO_ACESSO_BLOQUEADO = "Relatórios de acesso negado filtrados por motivo de acesso bloqueado:";
    public static final String RELATORIO_ACESSO_MOTIVO_CARGO_SEM_ACESSO = "Relatórios de acesso negado filtrados por motivo de cargo sem acesso:";
    public static final String RELATORIO_ACESSO_MOTIVO_HORARIO_NAO_PERMITIDO = "Relatórios de acesso negado filtrados por motivo de horário não permitido:";
    public static final String RELATORIO_REGISTRO_CABECALHO = " ==== Registro ";
    public static final String RELATORIO_REGISTRO_DATA = " Data: ";
    public static final String RELATORIO_REGISTRO_MOTIVO = " Motivo: ";
    public static final String RELATORIO_REGISTRO_MATRICULA = " Matrícula: ";
    public static final String RELATORIO_REGISTRO_NENHUM_ENCONTRADO = " ------------ Nenhum registro encontrado. -----------";

    //MENU GERENCIAR FUNCIONARIO
    public static final String GERENCIAR_FUNCIONARIO = " +-+-+-+-+-+-+-+ Gerenciar Funcionários +-+-+-+-+-+-+-+";
    public static final String CADASTRAR_FUNCIONARIO = " 1. Cadastrar Funcionário ";
    public static final String EDITAR_FUNCIONARIO = " 2. Editar Funcionário ";
    public static final String LISTAR_FUNCIONARIOS = " 3. Listar Funcionários ";
    public static final String DELETAR_FUNCIONARIO = " 4. Deletar Funcionário ";
    public static final String VOLTAR_AO_MENU_PRINCIPAL = " 5. Voltar ao menu principal ";

    //CADASTRAR FUNCIONARIO
    public static final String MENSAGEM_NOVO_USUARIO = "+-+-+-+-+-+-+-+ Novo Funcionário +-+-+-+-+-+-+-+";
    public static final String DIGITE_NOME = " Digite o nome do funcionário:";
    public static final String DIGITE_MATRICULA = " Digite a matrícula do funcionário:";
    public static final String DIGITE_DATA_NASCIMENTO = " Digite a data de nascimento do funcionário no formato dd/mm/yyyy:";
    public static final String DIGITE_TELEFONE = " Digite o telefone do funcionário:";
    public static final String DIGITE_SALARIO = " Digite o salário do funcionário:";
    public static final String DIGITE_CODIGO = " Digite o código do cargo que deseja atribuir ao funcionário: ";
    public static final String EXIBE_DATA_CADASTRADA = " Data de nascimento cadastrada: ";
    public static final String CONFIRMA_DATA_CADASTRADA = " Você confirma a data de nascimento cadastrada?";

    //EDITAR FUNCIONARIO
    public static final String TITULO_EDITAR_FUNCIONARIO = "+-+-+-+-+-+-+-+ Editar Funcionário +-+-+-+-+-+-+-+";
    public static final String FUNCIONARIO_SELECIONADO = " Funcionário selecionado: ";
    public static final String ALTERAR_NOME = " 1. Alterar nome";
    public static final String ALTERAR_MATRICULA = " 2. Alterar matrícula ";
    public static final String ALTERAR_DATA_NASCIMENTO = " 3. Alterar a data de nascimento ";
    public static final String ALTERAR_TELEFONE = " 4. Alterar o telefone ";
    public static final String ALTERAR_SALARIO = " 5. Alterar o salário ";
    public static final String ALTERAR_CARGO = " 6. Alterar o cargo ";
    public static final String OPCAO_VOLTAR = " 7. Voltar ";
    public static final String CARGO_EDITADO_SUCESSO = " CARGO EDITADO COM SUCESSO!";
    public static final String SALARIO_EDITADO_SUCESSO = " SALÁRIO EDITADO COM SUCESSO!";
    public static final String TELEFONE_EDITADO_SUCESSO = " TELEFONE EDITADO COM SUCESSO!";
    public static final String DATA_NASCIMENTO_EDITADA_SUCESSO = " DATA DE NASCIMENTO EDITADA COM SUCESSO!";
    public static final String MATRICULA_EDITADA_SUCESSO = " MATRÍCULA EDITADA COM SUCESSO!";
    public static final String NOME_EDITADO_SUCESSO = " NOME EDITADO COM SUCESSO!";
    public static final String HORARIOS_EDITADOS_SUCESSO = "HORÁRIOS DE ACESSO EDITADOS COM SUCESSO!";
    public static final String EDITA_HORARIOS_CARGO_SEM_ACESSO = "------------ Não é possível editar os horários de acesso: o cargo não possui acesso. ------------";

    // CARGO
    public static final String HORA_INICIO_MANHA_COMERCIAL = " 08:00";
    public static final String HORA_FIM_MANHA_COMERCIAL = " 12:00";
    public static final String HORA_INICIO_TARDE_COMERCIAL = " 14:00";
    public static final String HORA_FIM_TARDE_COMERCIAL = " 18:00";
    public static final String ERRO_INTERNO_CONSTANTES_FORMATADOR = "------------ ERRO INTERNO: as constantes usadas para formatação dos horários de acesso dos cargos estão inválidas. ------------";

//SELECIONAR OU CRIAR CARGO NO CADASTRO
    public static final String O_QUE_DESEJA_FAZER = " Digite a opção correspondente ao que você deseja fazer:";
    public static final String USAR_CARGO_EXISTENTE = " 1. Usar cargo ja existente ";
    public static final String CRIAR_CARGO_PARA_FUNCIONARIO = " 2. Criar cargo ";
    public static final String CODIGO_DO_CARGO = " Código do cargo: ";
    public static final String NOME_DO_CARGO = " Nome do cargo: ";

    //LISTAR
    public static final String LISTA_DE_FUNCIONARIOS = "+-+-+-+-+-+-+-+ Lista de Funcionários +-+-+-+-+-+-+-+";
    public static final String MATRICULA = " Matrícula: ";
    public static final String NOME = " Nome : ";
    public static final String DATA_NASCIMENTO = " Data de Nascimento: ";
    public static final String TELEFONE = " Telefone: ";
    public static final String SALARIO = " Salário: ";
    public static final String CARGO = " Cargo: ";

    //DELETAR FUNCIONARIO
    public static final String TITULO_DELETAR_FUNCIONARIO = "+-+-+-+-+-+-+-+ Deletar Funcionário +-+-+-+-+-+-+-+";
    public static final String TEM_CERTEZA_EXCLUIR = " Tem certeza que deseja excluir o funcionário selecionado? Caso selecione 'não' você voltará ao menu de funcionários. ";

    //MENU GERENCIAR CARGO  @BRUNO
    public static final String GERENCIAR_CARGO = " +-+-+-+-+-+-+-+ Gerenciar Cargo +-+-+-+-+-+-+-+";
    public static final String CADASTRAR_CARGO = " 1. Cadastrar Cargo ";
    public static final String EDITAR_CARGO = " 2. Editar Cargo ";
    public static final String LISTAR_CARGOS = " 3. Listar Cargos ";
    public static final String DELETAR_CARGO = " 4. Deletar Cargo ";

    //CADASTRA CARGO    @BRUNO
    public static final String MENSAGEM_NOVO_CARGO = "+-+-+-+-+-+-+-+ Novo Cargo +-+-+-+-+-+-+-+";
    public static final String DIGITE_NOME_CARGO = " Digite um nome para o cargo:";
    public static final String ESCOLHA_TIPO_CARGO = " Escolha o tipo do cargo:";
    public static final String TIPO_CARGO_GERENCIAL = " 1. Cargo gerencial";
    public static final String TIPO_CARGO_COMERCIAL = " 2. Cargo comercial";
    public static final String TIPO_CARGO_ESPECIAL = " 3. Cargo especial";
    public static final String TIPO_CARGO_COMUM = " 4. Cargo comum";
    public static final String TIPO_CARGO_SEM_ACESSO = " 5. Cargo sem acesso";
    public static final String DIGITE_CODIGO_CARGO = " Digite o código do cargo: ";
    public static final String DIGITE_HORA_INICIO_MANHA = " Digite a hora de início do turno matutino no formato HH:mm : ";
    public static final String DIGITE_HORA_FIM_MANHA = " Digite a hora de fim do turno matutino no formato HH:mm : ";
    public static final String DIGITE_HORA_INICIO_TARDE = " Digite a hora de início do turno vespertino no formato HH:mm : ";
    public static final String DIGITE_HORA_FIM_TARDE = " Digite a hora de fim do turno vespertino no formato HH:mm : ";
    public static final String DIGITE_HORA_INICIO_ESPECIAL = " Digite a hora de início do turno especial no formato HH:mm : ";
    public static final String DIGITE_HORA_FIM_ESPECIAL = " Digite a hora de fim do turno especial no formato HH:mm : ";
    public static final String HORA_INSERIDA_FORMATO_INCORRETO = "------------ FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado. ------------";
    public static final String CARGO_CADASTRADO_SUCESSO = " CARGO CADASTRADO COM SUCESSO!";

    //EDITAR CARGO   @BRUNO
    public static final String TITULO_EDITAR_CARGO = "+-+-+-+-+-+-+-+ Editar Cargo +-+-+-+-+-+-+-+";
    public static final String CARGO_SELECIONADO = " Cargo selecionado: ";
    public static final String ALTERAR_NOME_CARGO = " 1. Alterar nome do cargo";
    public static final String ALTERAR_CODIGO_CARGO = " 2. Alterar código do cargo";
    public static final String ALTERAR_EH_GERENCIAL = " 3. Alterar função gerencial";
    public static final String ALTERAR_TEM_ACESSO = " 4. Alterar permissão de acesso";
    public static final String ALTERAR_HORARIOS_ACESSO = " 5. Alterar horários de acesso";
    public static final String OPCAO_VOLTAR_CARGO = " 6. Voltar ";
    public static final String CODIGO_EDITADO_SUCESSO = " CÓDIGO EDITADO COM SUCESSO! ";
    public static final String EH_GERENCIAL_EDITADO_SUCESSO = " FUNÇÃO GERENCIAL EDITADA COM SUCESSO!";
    public static final String TEM_ACESSO_EDITADO_SUCESSO = " PERMISSÃO DE ACESSO EDITADA COM SUCESSO!";
    public static final String TORNAR_CARGO_GERENCIAL = " Deseja torná-lo um cargo gerencial? ";
    public static final String ESCOLHA_TEM_ACESSO = " Deseja torná-lo um cargo com acesso permitido ao financeiro? ";

    //LISTAR CARGOS
    public static final String LISTA_DE_CARGOS = "+-+-+-+-+-+-+-+ Lista de Cargos +-+-+-+-+-+-+-+";
    public static final String CODIGO = " Código: ";
    public static final String NOME_CARGO = " Nome: ";
    public static final String EH_GERENTE = " Cargo Gerencial: ";
    public static final String TEM_ACESSO_AO_FINANCEIRO = " Tem Acesso ao Financeiro: ";

    //DELETAR CARGO
    public static final String TITULO_DELETAR_CARGO = "+-+-+-+-+-+-+-+ Deletar Cargo +-+-+-+-+-+-+-+";
    public static final String CARGO_DELETADO_SUCESSO = "CARGO DELETADO COM SUCESSO!";
    public static final String CONFIRMACAO_EXCLUSAO_CARGO = " Tem certeza que deseja excluir o cargo? Caso selecione 'não' você voltará ao menu de cargos. ";

    //DATA DO SISTEMA
    public static final String TITULO_MENU_DATA_HORA_SISTEMA = "+-+-+-+-+-+-+-+ Menu de data e hora do sistema +-+-+-+-+-+-+-+";
    public static final String HORA_ATUAL_DO_SISTEMA = "Data e hora atuais do sistema: ";
    public static final String ALTERAR_DATA_E_HORA = " 1. Alterar data e hora do sistema";
    public static final String DATA_VOLTAR_MENU_PRINCIPAL = " 2. Voltar ao menu principal";
    public static final String INSIRA_DATA_SISTEMA = "Digite a nova data e hora do sistema no seguinte formato: dd-MM-yyyy HH:mm ";
    public static final String DATA_HORA_SISTEMA_INVALIDOS = "------------ FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado. ------------";
    public static final String CONFIRMA_ALTERACAO_DATA_HORA = "Você confirma a alteração de data e hora do sistema?";
    public static final String DATA_HORA_ATUALIZADOS_SUCESSO = "DATA E HORA DO SISTEMA ATUALIZADAS COM SUCESSO!";
    public static final String DATA_HORA_NAO_ATUALIZADOS = "------------ Atualização de data e hora do sistema abortada. ------------";

    //MENU PRINCIPAL
    public static final String MENU_PRINCIPAL = "+-+-+-+-+-+-+-+ Menu Principal +-+-+-+-+-+-+-+";
    public static final String ACESSO_AO_FINANCEIRO = " 1. Acesso à Porta do Financeiro";
    public static final String GERENCIAR_FUNCIONARIOS = " 2. Gerenciar Funcionários";
    public static final String GERENCIAR_CARGOS = " 3. Gerenciar Cargos";
    public static final String GERENCIAR_DATA = " 4. Gerenciar data do sistema";
    public static final String EMITIR_RELATORIO = " 5. Emitir Relatorio de Acessos Negados";

}
