/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.CargoHorarioEspecial;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Interfaces.IControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaCargo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author bruno e thiago
 */
public class ControladorCargo implements IControladorCargo {

    private ArrayList<Cargo> cargos;
    private TelaCargo telaCargo;

    public ControladorCargo() {
        this.cargos = new ArrayList<>();
        this.telaCargo = new TelaCargo(this);
    }

    /**
     * Exibe o menu principal do CRUD do cargo.
     */
    public void exibeMenuCargo() {
        telaCargo.exibeMenuCargo();
        controlaMenuCargo();
    }

    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu principal do cargo. Caso aperte 1: cadastra um cargo.
     * Caso aperte 2: edita um cargo. Caso aperte 3: lista todos os
     * cargos. Caso aperte 4: deleta um cargo. Caso aperte 5: volta
     * ao menu principal do sistema. Caso aperte outra tecla: apresenta uma
     * mensagem de opcao inexistente e pede que o usuario digite outra vez a
     * opcao que deseja selecionar.
     */
    public void controlaMenuCargo() {

        int opcao = this.telaCargo.pedeOpcao();

        switch (opcao) {
            case 1:
                incluiCargo();
                exibeMenuCargo();
                break;
            case 2:
                editaCargo();
                break;
            case 3:
                pedeListaCargos();
                break;
            case 4:
                menuDeletarCargo();
                break;
            case 5:
                ControladorPrincipal.getInstance().exibeMenuPrincipal();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                exibeMenuCargo();
                break;
        }
    }

    public void voltaMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

    /**
     * Pede ao usuario todos os atributos para cadastrar um cargo e caso o
     * cargo não esteja cadastrado (codigo não foi cadastrada ainda),
     * cadastra o cargo.
	 * 
	 * @return Cargo cadastrado
     */
    public Cargo incluiCargo() {
        this.telaCargo.mensagemNovoCargo();
        String nome = pedeNome();
        int codigo = verificaCodigoInserido();
        int tipoCargo = this.telaCargo.pedeTipoCargo();
        SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATADOR_HORA);
        try {
            Date horaInicioManha = formatador.parse(Constantes.HORA_INICIO_MANHA_COMERCIAL);
            Date horaFimManha = formatador.parse(Constantes.HORA_FIM_MANHA_COMERCIAL);
            Date horaInicioTarde = formatador.parse(Constantes.HORA_INICIO_TARDE_COMERCIAL);
            Date horaFimTarde = formatador.parse(Constantes.HORA_FIM_TARDE_COMERCIAL);
            Cargo cargo = new Cargo(codigo, nome, false, true, horaInicioManha, horaFimManha, horaInicioTarde, horaFimTarde);
            switch(tipoCargo){
                case 1:
                    cargo.setEhGerencial(true);
                    this.cargos.add(cargo);
                    this.telaCargo.mensagemCargoCadastrado();
                    break;
                case 2:
                    this.cargos.add(cargo);
                    this.telaCargo.mensagemCargoCadastrado();
                    break;
                case 3:
                    cargo = criaCargoEspecial(nome, codigo, formatador);
                    this.telaCargo.mensagemCargoCadastrado();
                    break;
                case 4:
                    cargo = criaCargoComum(nome, codigo, formatador);
                    this.telaCargo.mensagemCargoCadastrado();
                    break;
                case 5:
                    cargo.setTemAcessoAoFinanceiro(false);
                    this.cargos.add(cargo);
                    this.telaCargo.mensagemCargoCadastrado();
                    break;
                default:
                    this.telaCargo.exibeOpcaoInexistente();
                    incluiCargo();
                    break;
            }
            return cargo;
        } catch (ParseException e) {
            telaCargo.exibeErroConstantesFormatador();
            ControladorPrincipal.getInstance().exibeMenuPrincipal();
        }
        return null;
    }


    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o cargo. Caso o usuario aperte 1: deleta o cargo.
     * Caso o usuario aperte 2: volta para o menu principal do cargo. Caso o
     * usuario aperte outra tecla qualquer: apresenta a mensagem de opcao
     * inexistente e exibe o menu de deletar um cargo novamente
     *
     * @param cargo a ser deletado
     */
    public void controlaMenuDeletarCargo(Cargo cargo) {
        int opcao = this.telaCargo.pedeOpcao();
        switch (opcao) {
            case 1:
                deletaCargo(cargo);
                exibeMenuCargo();
                break;
            case 2:
                exibeMenuCargo();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                controlaMenuDeletarCargo(cargo);
                break;
        }
    }

    /**
     * Pede qual cargo o usuario deseja deletar. Exibe o menu de deletar
     * cargo e chama quem controla o menu.
     */
    public void menuDeletarCargo() {
        this.telaCargo.mensagemDeletaCargo();
        Cargo cargo = pedeCargo();
        this.telaCargo.exibeMensagemCargoSelecionado();
        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerencial(), cargo.temAcessoAoFinanceiro());
        this.telaCargo.exibeMenuConfirmacaoDeletarCargo();
        controlaMenuDeletarCargo(cargo);
    }

    /**
     * Caso o cargo exista na lista de cargos ele é deletado da
     * mesma
     *
     * @param cargo que vai ser deletado
     */
    public void deletaCargo(Cargo cargo) {
        Cargo cargoIndefinido = encontraCargoIndefinido();
        if (cargo != null) {
            ControladorPrincipal.getInstance().deletaCargosFuncionarios(cargo, cargoIndefinido);
            cargos.remove(cargo);
            this.telaCargo.mensagemCargoDeletadoSucesso();                
        }
    }

    /**
     * Verifica se o codigo inserido pelo usuario ja foi cadastrado
     * anteriormente
     *
     * @return codigo caso não tenha sido cadastrado antes, caso contrario
     * pede para que o usuario insira um novo codigo
     */
    public int verificaCodigoInserido() {
        int codigo = this.telaCargo.pedeCodigo();
        for (Cargo cargoCadastrado : this.cargos) {
            if (cargoCadastrado.getCodigo() == codigo) {
                this.telaCargo.mensagemErroCodigoJaCadastrada();
                verificaCodigoInserido();
            }
        }
        return codigo;
    }
    
    /**
     * Pede para o usuário inserir um nome para o cargo, chama o método
     * para verificar se o nome é valido e, caso seja, retorna o nome.
     *
     * @return nome do cargo validado e inserido pelo usuário
     */
    public String pedeNome() {
        String nome = this.telaCargo.pedeNome();
        nome = verificaNome(nome);
        return nome;
    }
    
    /**
     * Verifica se a string nome é composta de pelo menos 3 caracteres e somente
     * de letras ou espaços. Caso não seja exibe uma mensagem ao usuário e chama
     * o pedeNome novamente.
     *
     * @param nome que o usuário inseriu por meio do teclado.
     */
    public String verificaNome(String nome) {
        if (nome.length() > 2) {
            for (int i = 0; i < nome.length(); i++) {
                char letraAnalisada = nome.charAt(i);
                if (!Character.isLetter(letraAnalisada)) {
                    if (!Character.isSpace(letraAnalisada)) {
                        this.telaCargo.mensagemNomeInvalidoLetras();
                        nome = pedeNome();
                    }
                }
            }
        } else {
            this.telaCargo.mensagemNomeInvalidoTamanho();
            nome = pedeNome();
        }
        return nome;
    }
    
    /**
     * Pede qual cargo o usuario deseja editar. Exibe o menu de editar
     * cargo e chama quem controla o menu.
     */
    public void editaCargo() {
        this.telaCargo.mensagemEditaCargo();
        Cargo cargo = pedeCargo();
        this.telaCargo.exibeMensagemCargoSelecionado();
        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerencial(), cargo.temAcessoAoFinanceiro());
        menuEditaCargo(cargo);
    }
    
    /**
     * Método responsavel pelo menu de editar o cargo, onde chama o metodo
     * da tela para exibir o menu propriamente e depois chama o método
     * responsavel pelo controle das opcoes possiveis
     *
     * @param cargo a ser editado
     */
    public void menuEditaCargo(Cargo cargo) {
        this.telaCargo.exibeMenuEditaCargo();
        controlaMenuEditaCargo(cargo);
    }
    
    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o cargo. Caso aperte 1: altera o nome do
     * cargo. Caso aperte 2: altera o codigo do cargo. Caso aperte 3: altera 
     * a função gerencial do cargo. Caso aperte 4: altera a permissão de acesso 
     * ao financeiro do cargo. Caso aperte 5: volta ao menu principal. Caso 
     * aperte qualquer outra tecla: exibe mensagem de opcao invalida e pede 
     * para que insira uma nova opcao.
     *
     * @param cargo a ser editado.
     */
    public void controlaMenuEditaCargo(Cargo cargo) {
        int opcao = this.telaCargo.pedeOpcao();
        
        switch (opcao) {
            case 1:
                String nome = this.telaCargo.pedeNome();
                cargo.setNome(nome);
                this.telaCargo.mensagemNomeEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 2:
                int codigo = this.telaCargo.pedeCodigo();
                cargo.setCodigo(codigo);
                this.telaCargo.mensagemCodigoEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 3: 
                int opcaoEhGerencial = this.telaCargo.pedeSeEhGerencial();
                atualizaEhGerencial(opcaoEhGerencial, cargo);
                this.telaCargo.mensagemGerencialEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 4:
                int opcaoTemAcessoAoFinanceiro = this.telaCargo.pedeTemAcessoAoFinanceiro();
                atualizaTemAcessoAoFinanceiro(opcaoTemAcessoAoFinanceiro, cargo);
                this.telaCargo.mensagemAcessoEditadoSucesso();
                menuEditaCargo(cargo);
                break;
            case 5:
                if(cargo instanceof CargoHorarioEspecial) {
                        atualizaHorariosCargoHorarioEspecial(cargo);
                        telaCargo.mensagemHorariosEditadosSucesso();
                } else if(cargo.temAcessoAoFinanceiro()) {
                        atualizaHorariosCargo(cargo);
                        telaCargo.mensagemHorariosEditadosSucesso();
                } else {
                        telaCargo.exibeEditaHorariosCargoSemAcesso();
                        controlaMenuEditaCargo(cargo);
                }
            case 6:
                exibeMenuCargo();
                break;
            default:
                this.telaCargo.opcaoInexistente();
                editaCargo();
                break;
        }
    }
    
    /**
     * Chama a função listaCargos() e em seguida retorna ao menu cargo 
     */
    public void pedeListaCargos() {
        listaCargos();
        exibeMenuCargo();
    }
    
    @Override
    public void listaCargos() {
        this.telaCargo.mensagemListaCargos();
        for (Cargo cargoCadastrado : cargos) {
            int codigo = cargoCadastrado.getCodigo();
            String nome = cargoCadastrado.getNome();
            boolean ehGerencial = cargoCadastrado.ehGerencial();
            boolean temAcessoAoFinanceiro = cargoCadastrado.temAcessoAoFinanceiro();
            this.telaCargo.exibeCargo(codigo, nome, ehGerencial, temAcessoAoFinanceiro);
        }
    }

    @Override
    public Cargo encontraCargoPorCodigo(int codigo) {
        for(Cargo cargoLista : this.cargos) {
            if(cargoLista.getCodigo() == codigo) return cargoLista;
        }
        return null;
    }

    /**
     * Pede inicialemente qual o codigo do cargo que o usuario esta se
     * referindo. Com o codigo inserido pelo usuario, encontra o cargo
     * em questão.
     *
     * @return cargo que o usuario esta se referindo
     */
    public Cargo pedeCargo() {
        int codigo = this.telaCargo.pedeCodigo();
        Cargo cargo = encontraCargoPorCodigo(codigo);
        if (cargo == null) {
            this.telaCargo.mensagemCargoNaoEncontrado();
            cargo = pedeCargo();
        }
        return cargo;
    }
    
    /**
     * Pede ao usuario todos os horarios de inicio e fim para instanciar um novo
     * cargo especial e caso algum horario tenha sido inserido em formato errado
     * acusa erro e volta a pedir para inserir
     */
    public CargoHorarioEspecial criaCargoEspecial(String nome, int codigo, SimpleDateFormat formatador) {
        try {
            Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
            Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
            Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
            Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
            Date horaInicioEspecial = formatador.parse(telaCargo.pedeHoraInicioEspecial());
            Date horaFimEspecial = formatador.parse(telaCargo.pedeHoraFimEspecial());
//			Date horaInicioManha = pedeHora(Constantes.DIGITE_HORA_INICIO_MANHA, formatador);
//			Date horaFimManha = pedeHora(Constantes.DIGITE_HORA_FIM_MANHA, formatador);
//			Date horaInicioTarde = pedeHora(Constantes.DIGITE_HORA_INICIO_TARDE, formatador);
//			Date horaFimTarde = pedeHora(Constantes.DIGITE_HORA_FIM_TARDE, formatador);
//			Date horaInicioEspecial = pedeHora(Constantes.DIGITE_HORA_INICIO_ESPECIAL, formatador);
//			Date horaFimEspecial = pedeHora(Constantes.DIGITE_HORA_FIM_ESPECIAL, formatador);
            CargoHorarioEspecial cargo = new CargoHorarioEspecial(codigo, nome, horaInicioManha, horaFimManha,
			horaInicioTarde, horaFimTarde, horaInicioEspecial, horaFimEspecial);
            this.cargos.add(cargo);
            return cargo;
        } catch (ParseException e) {
            this.telaCargo.exibeHoraInseridaFormatoIncorreto();
            criaCargoEspecial(nome, codigo, formatador);
        }
        return null;
    }
    
    /**
     * Atualiza a função gerencial do cargo com base na opcao que o usuario
     * selecionar. Caso aperte 1: determina que o cargo tem função gerencial.
     * Caso aperte 2: determina que o cargo não tem função gerencial. Caso 
     * aperte qualquer outra tecla: exibe mensagem
     * de opcao inexistente e volta para o menu edita cargo.
     *
     * @param opcaoEhGerencial a ser selecionada pelo usuario
     * @param cargo a ser editado
     */
    public void atualizaEhGerencial(int opcaoEhGerencial, Cargo cargo) {
        switch(opcaoEhGerencial) {
            case 1: 
                cargo.setEhGerencial(true);
                cargo.setTemAcessoAoFinanceiro(true);
                break;
            case 2: 
                cargo.setEhGerencial(false);
                break;
            default:
                this.telaCargo.opcaoInexistente();
                menuEditaCargo(cargo);
                break;
        }
    }
    
    /**
     * Atualiza a permissão de acesso do cargo com base na opcao que o usuario
     * selecionar. Caso aperte 1: determina que o cargo tem permissão de acesso.
     * Caso aperte 2: determina que o cargo não tem permissão de acesso. Caso 
     * aperte qualquer outra tecla: exibe mensagem
     * de opcao inexistente e volta para o menu edita cargo.
     *
     * @param opcaoTemAcessoAoFinanceiro a ser selecionada pelo usuario
     * @param cargo a ser editado
     */
    public void atualizaTemAcessoAoFinanceiro(int opcaoTemAcessoAoFinanceiro, Cargo cargo) {
        switch(opcaoTemAcessoAoFinanceiro) {
            case 1: 
                cargo.setTemAcessoAoFinanceiro(true);
                break;
            case 2: 
                cargo.setTemAcessoAoFinanceiro(false);
                break;
            default:
                this.telaCargo.opcaoInexistente();
                menuEditaCargo(cargo);
                break;
        }
    }

    /**
     * Instancia cargo indefinido que não tera função gerencial, nem acesso
     * ao financeiro
     */
    public void criaCargoPadrao() {
        Date dataIndefinida = new Date();
        Cargo cargo = new Cargo(0, Constantes.CARGO_INDEFINIDO, false, false,
		dataIndefinida, dataIndefinida, dataIndefinida, dataIndefinida);
        this.cargos.add(cargo);
    }

    /**
     * Chama a função que encontra cargo no array de cargos, nessa caso
     * especifico o cargo indefinido, e retorna esse cargo
     * @return cargo
     */
    public Cargo encontraCargoIndefinido() {
        return encontraCargoPorCodigo(0);
    }

    /**
     * Pede ao usuario todos os horarios de inicio e fim para instanciar um novo
     * cargo comum e caso algum horario tenha sido inserido em formato errado
     * acusa erro e volta a pedir para inserir
     */
    public Cargo criaCargoComum(String nome, int codigo, SimpleDateFormat formatador) {
        try {
        Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
        Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
        Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
        Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
//			Date horaInicioManha = pedeHora(Constantes.DIGITE_HORA_INICIO_MANHA, formatador);
//			Date horaFimManha = pedeHora(Constantes.DIGITE_HORA_FIM_MANHA, formatador);
//			Date horaInicioTarde = pedeHora(Constantes.DIGITE_HORA_INICIO_TARDE, formatador);
//			Date horaFimTarde = pedeHora(Constantes.DIGITE_HORA_FIM_TARDE, formatador);
        Cargo cargo = new Cargo(codigo, nome, false, true, horaInicioManha, horaFimManha,
                            horaInicioTarde, horaFimTarde);
        this.cargos.add(cargo);
        return cargo;
        } catch (ParseException e) {
            telaCargo.exibeHoraInseridaFormatoIncorreto();
            criaCargoComum(nome, codigo, formatador);
        }
        return null;
    }
    
    /**
     * Atualiza os horarios de acesso do cargo especial com base na opcao que o usuario
     * escolher. No caso de hora inserida em formato incorreto exibe mensagem:
     * FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado
     *
     * @param cargo a ser editado horarios
     */
    public void atualizaHorariosCargoHorarioEspecial(Cargo cargo) {
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            try {
                    Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
                    Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
                    Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
                    Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
                    Date horaInicioEspecial = formatador.parse(telaCargo.pedeHoraInicioEspecial());
                    Date horaFimEspecial = formatador.parse(telaCargo.pedeHoraFimEspecial());
                    ((CargoHorarioEspecial) cargo).setHoraInicioManha(horaInicioManha);
                    ((CargoHorarioEspecial) cargo).setHoraFimManha(horaFimManha);
                    ((CargoHorarioEspecial) cargo).setHoraInicioTarde(horaInicioTarde);
                    ((CargoHorarioEspecial) cargo).setHoraFimTarde(horaFimTarde);
                    ((CargoHorarioEspecial) cargo).setHoraInicioEspecial(horaInicioEspecial);
                    ((CargoHorarioEspecial) cargo).setHoraFimEspecial(horaFimEspecial);
            } catch (ParseException e) {
                    telaCargo.exibeHoraInseridaFormatoIncorreto();
                    atualizaHorariosCargoHorarioEspecial(cargo);
            }
//		Date horaInicioManha = pedeHora(Constantes.DIGITE_HORA_INICIO_MANHA, formatador);
//		Date horaFimManha = pedeHora(Constantes.DIGITE_HORA_FIM_MANHA, formatador);
//		Date horaInicioTarde = pedeHora(Constantes.DIGITE_HORA_INICIO_TARDE, formatador);
//		Date horaFimTarde = pedeHora(Constantes.DIGITE_HORA_FIM_TARDE, formatador);
//		Date horaInicioEspecial = pedeHora(Constantes.DIGITE_HORA_INICIO_ESPECIAL, formatador);
//		Date horaFimEspecial = pedeHora(Constantes.DIGITE_HORA_FIM_ESPECIAL, formatador);

    }

//	public Date pedeHora(String constante, SimpleDateFormat formatador) {
//		try {
//			return formatador.parse(telaCargo.pedeHora(constante));
//		} catch (ParseException e) {
//			telaCargo.exibeHoraInseridaFormatoIncorreto();
//			pedeHora(constante, formatador);
//		}
//		return null;
//	}
    
    /**
     * Atualiza os horarios de acesso do cargo com base na opcao que o usuario
     * escolher. No caso de hora inserida em formato incorreto exibe mensagem:
     * FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado
     *
     * @param cargo a ser editado horarios
     */
    public void atualizaHorariosCargo(Cargo cargo) {
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            try {
                    Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
                    Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
                    Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
                    Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
                    Date horaInicioEspecial = formatador.parse(telaCargo.pedeHoraInicioEspecial());
                    Date horaFimEspecial = formatador.parse(telaCargo.pedeHoraFimEspecial());
                    cargo.setHoraInicioManha(horaInicioManha);
                    cargo.setHoraFimManha(horaFimManha);
                    cargo.setHoraInicioTarde(horaInicioTarde);
                    cargo.setHoraFimTarde(horaFimTarde);
            } catch (ParseException e) {
                    telaCargo.exibeHoraInseridaFormatoIncorreto();
                    atualizaHorariosCargo(cargo);
            }
//		Date horaInicioManha = pedeHora(Constantes.DIGITE_HORA_INICIO_MANHA, formatador);
//		Date horaFimManha = pedeHora(Constantes.DIGITE_HORA_FIM_MANHA, formatador);
//		Date horaInicioTarde = pedeHora(Constantes.DIGITE_HORA_INICIO_TARDE, formatador);
//		Date horaFimTarde = pedeHora(Constantes.DIGITE_HORA_FIM_TARDE, formatador);
    }

}
