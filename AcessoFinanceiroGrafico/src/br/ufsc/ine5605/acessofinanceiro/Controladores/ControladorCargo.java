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
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaCadastroCargo;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaCargo;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaEditarCargo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author bruno e thiago
 */
public class ControladorCargo implements IControladorCargo {

    private static ControladorCargo controladorCargo;
    private CargoDAO cargoDAO;
    private TelaCargo telaCargo;
    private TelaEditarCargo telaEditarCargo;
    private TelaCadastroCargo telaCadastroCargo;

    private ControladorCargo() {
        this.telaCargo = new TelaCargo(this);
        this.cargoDAO = new CargoDAO();
        this.telaEditarCargo = new TelaEditarCargo(this);
        this.telaCadastroCargo = new TelaCadastroCargo(this);
    }

    public static ControladorCargo getInstance() {
        if (controladorCargo == null) controladorCargo = new ControladorCargo();
        return controladorCargo;
    }
    
    /**
     * Volta ao menu principal
     */
    public void voltarMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

    /**
     * Exibe o menu principal do CRUD do cargo.
     */
    public void exibeMenuCargo() {
        telaCargo.exibeMenuCargo();
    }

    public void exibeCadastraFuncionario() {
        this.telaCadastroCargo.exibeMenuCadastroCargo();
    }
    
    public ArrayList<Cargo> getListaCargos() {
        return this.cargoDAO.getList();
    }

    public void voltaMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }
    
    public void updateCargos() {
        this.telaCargo.updateData();
    }

    /**
     * Pede ao usuario todos os atributos para cadastrar um cargo e caso o cargo
     * não esteja cadastrado (codigo não foi cadastrada ainda), cadastra o
     * cargo.
     *
     * @return Cargo cadastrado
     */
    public Cargo incluiCargo(
			int codigo, String nome, String tipoCargo, String inputHoraInicioManha,
			String inputHoraFimManha, String inputHoraInicioTarde, String inputHoraFimTarde,
			String inputHoraInicioEspecial, String inputHoraFimEspecial, boolean editando
	) {
		if(inputHoraInicioManha.equals(Constantes.COMUM_VAZIO)) inputHoraInicioManha = Constantes.FORMATADOR_MEIA_NOITE;
		if(inputHoraFimManha.equals(Constantes.COMUM_VAZIO)) inputHoraFimManha = Constantes.FORMATADOR_MEIA_NOITE;
		if(inputHoraInicioTarde.equals(Constantes.COMUM_VAZIO)) inputHoraInicioTarde = Constantes.FORMATADOR_MEIA_NOITE;
		if(inputHoraFimTarde.equals(Constantes.COMUM_VAZIO)) inputHoraFimTarde = Constantes.FORMATADOR_MEIA_NOITE;
		if(inputHoraInicioEspecial.equals(Constantes.COMUM_VAZIO)) inputHoraInicioEspecial = Constantes.FORMATADOR_MEIA_NOITE;
		if(inputHoraFimEspecial.equals(Constantes.COMUM_VAZIO)) inputHoraFimEspecial = Constantes.FORMATADOR_MEIA_NOITE;
        if(verificaNome(nome)) {
			if(verificaCodigoInserido(codigo)) {
				SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATADOR_HORA);
				try {
					Date horaInicioManha = formatador.parse(inputHoraInicioManha);
					Date horaFimManha = formatador.parse(inputHoraFimManha);
					Date horaInicioTarde = formatador.parse(inputHoraInicioTarde);
					Date horaFimTarde = formatador.parse(inputHoraFimTarde);
					Date horaInicioEspecial = formatador.parse(inputHoraInicioEspecial);
					Date horaFimEspecial = formatador.parse(inputHoraFimEspecial);
					Cargo cargo = new Cargo(codigo, nome, false, true, horaInicioManha, horaFimManha, horaInicioTarde, horaFimTarde);
					switch (tipoCargo) {
						case Constantes.CARGO_TIPO_GERENCIAL:
							cargo.setEhGerencial(true);
							this.cargoDAO.put(cargo);
							if(!editando) this.telaCargo.mensagemCargoCadastrado();
							break;
						case Constantes.CARGO_TIPO_COMERCIAL:
							cargo.setHoraInicioManha(formatador.parse(Constantes.CARGO_HORA_INICIO_MANHA_COMERCIAL));
							cargo.setHoraFimManha(formatador.parse(Constantes.CARGO_HORA_FIM_MANHA_COMERCIAL));
							cargo.setHoraInicioTarde(formatador.parse(Constantes.CARGO_HORA_INICIO_TARDE_COMERCIAL));
							cargo.setHoraFimTarde(formatador.parse(Constantes.CARGO_HORA_FIM_TARDE_COMERCIAL));
							this.cargoDAO.put(cargo);
							if(!editando) this.telaCargo.mensagemCargoCadastrado();
							break;
						case Constantes.CARGO_TIPO_ESPECIAL:
							cargo = criaCargoEspecial(
											nome, codigo, horaInicioManha, horaFimManha,
											horaInicioTarde, horaFimTarde, horaInicioEspecial,
											horaFimEspecial
											);
							if(!editando) this.telaCargo.mensagemCargoCadastrado();
							break;
						case Constantes.CARGO_TIPO_COMUM:
							cargo = criaCargoComum(nome, codigo, horaInicioManha, horaFimManha,
											horaInicioTarde, horaFimTarde);
							if(!editando) this.telaCargo.mensagemCargoCadastrado();
							break;
						case Constantes.CARGO_TIPO_SEM_ACESSO:
							cargo.setTemAcessoAoFinanceiro(false);
							this.cargoDAO.put(cargo);
							if(!editando) this.telaCargo.mensagemCargoCadastrado();
							break;
					}
					return cargo;
				} catch (ParseException e) {
					telaCargo.exibeErroConstantesFormatador();
					ControladorPrincipal.getInstance().exibeMenuPrincipal();
				}
				return null;
			}
		}
		return null;
    }
	
    /**
     * Caso o cargo exista na lista de cargos ele é deletado da mesma
     *
     * @param indexSelecionado do cargo selecionado que vai ser deletado
     */
    public void deletaCargoSelecionado(int indexSelecionado) {
        if (indexSelecionado != -1) {
            ArrayList<Integer> codigos = this.cargoDAO.getCodigos();
            int codigo = codigos.get(indexSelecionado);
            Cargo cargo = this.cargoDAO.get(codigo);
            this.cargoDAO.remove(cargo);
            cargo = null;
            this.telaCargo.exibeCargoDeletadoComSucesso();
        } else {
            this.telaCargo.exibeCargoNaoSelecionado();
        }
        
//        Cargo cargoIndefinido = encontraCargoIndefinido();
//        if (cargo != null) {
//            ControladorPrincipal.getInstance().deletaCargosFuncionarios(cargo, cargoIndefinido);
//            cargoDAO.remove(cargo.getCodigo());
//            this.telaCargo.mensagemCargoDeletadoSucesso();
//        }
    }

    /**
     * Verifica se o codigo inserido pelo usuario ja foi cadastrado
     * anteriormente
     *
     * @return codigo caso não tenha sido cadastrado antes, caso contrario pede
     * para que o usuario insira um novo codigo
     */
    public boolean verificaCodigoInserido(int codigo) {
//		for (int i = 0; i < codigo.length(); i++) {
//			char letraAnalisada = codigo.charAt(i);
//			if (!Character.isLetter(letraAnalisada)) {
//				if (!Character.isSpace(letraAnalisada)) {
//					this.telaCargo.mensagemNomeInvalidoLetras();
//					return false;
//				}
//			}
//		}
        for (Cargo cargoCadastrado : this.cargoDAO.getList()) {
            if (cargoCadastrado.getCodigo() == codigo) {
                this.telaCargo.mensagemErroCodigoJaCadastrado();
                return false;
            }
        }
        return true;
    }

	public boolean verificaCodigo(String codigo) {
		if(codigo.length() > 9) {
			telaCargo.mensagemCodigoInvalidoTamanho();
			return false;
		}
		for (int i = 0; i < codigo.length(); i++) {
			char letraAnalisada = codigo.charAt(i);
			if (Character.isLetter(letraAnalisada) ||
					Character.isSpace(letraAnalisada)) {
				this.telaCargo.mensagemCodigoInvalidoLetras();
				return false;
			}
		}
        for (Cargo cargoCadastrado : this.cargoDAO.getList()) {
            if (cargoCadastrado.getCodigo() == Integer.parseInt(codigo)) {
                this.telaCargo.mensagemErroCodigoJaCadastrado();
                return false;
            }
        }
        return true;
	}
	
    /**
     * Verifica se a string nome é composta de pelo menos 3 caracteres e somente
     * de letras ou espaços. Caso não seja exibe uma mensagem ao usuário e chama
     * o pedeNome novamente.
     *
     * @param nome que o usuário inseriu por meio do teclado.
     */
    public boolean verificaNome(String nome) {
        if (nome.length() > 2) {
            for (int i = 0; i < nome.length(); i++) {
                char letraAnalisada = nome.charAt(i);
                if (!Character.isLetter(letraAnalisada)) {
                    if (!Character.isSpace(letraAnalisada)) {
                        this.telaCargo.mensagemNomeInvalidoLetras();
                        return false;
                    }
                }
            }
        } else {
            this.telaCargo.mensagemNomeInvalidoTamanho();
            return false;
        }
        return true;
    }
    
    public void exibeCadastraCargo() {
        this.telaCadastroCargo.exibeMenuCadastroCargo();
    }
    
    public void exibeEditarCargoSelecionado(int indexSelecionado) {
        if (indexSelecionado != -1) {
            ArrayList<Integer> codigos = this.cargoDAO.getCodigos();
            int codigo = codigos.get(indexSelecionado);
            Cargo cargo = this.cargoDAO.get(codigo);
            this.telaEditarCargo.exibeMenuEditaCargo(cargo);
        } else {
            this.telaCargo.exibeCargoNaoSelecionado();
        }
    }
	
    public void editaCargo(int codigoAntigo, int codigo, String nome, String tipoCargo, String inputHoraInicioManha,
			String inputHoraFimManha, String inputHoraInicioTarde, String inputHoraFimTarde,
			String inputHoraInicioEspecial, String inputHoraFimEspecial) {
        
        boolean nomeValido = verificaNome(nome);
		boolean codigoValido = true;
		if(codigoAntigo != codigo)codigoValido = verificaCodigoInserido(codigo);
        if (nomeValido) {
            if (codigoAntigo != codigo) {
                if (codigoValido) {
                    Cargo cargoNaoEditado = this.cargoDAO.get(codigoAntigo);
                    this.cargoDAO.remove(cargoNaoEditado);
                    cargoNaoEditado = null;
                    Cargo cargoEditado = incluiCargo(codigo, nome, tipoCargo, 
                                inputHoraInicioManha, inputHoraFimManha, inputHoraInicioTarde, 
                                inputHoraFimTarde, inputHoraInicioEspecial, inputHoraFimEspecial,
								true
					);
                    cargoDAO.put(cargoEditado);
                    this.telaEditarCargo.exibeCargoEditadoComSucesso();
                    this.telaCargo.updateData();
                } else {
                    this.telaCargo.mensagemErroCodigoJaCadastrado();
                }
            } else {
                Cargo cargoNaoEditado = this.cargoDAO.get(codigoAntigo);
                this.cargoDAO.remove(cargoNaoEditado);
                cargoNaoEditado = null;
                Cargo cargoEditado = incluiCargo(codigoAntigo, nome, tipoCargo, 
                                inputHoraInicioManha, inputHoraFimManha, inputHoraInicioTarde, 
                                inputHoraFimTarde, inputHoraInicioEspecial, inputHoraFimEspecial,
								true
				);
                cargoDAO.put(cargoEditado);
                this.telaEditarCargo.exibeCargoEditadoComSucesso();
                this.telaCargo.updateData();
            }
        } else {
            this.telaCargo.mensagemNomeInvalidoLetras();
        }
        
    }
        
    @Override
    public Cargo encontraCargoPorCodigo(int codigo) {
        for (Cargo cargoLista : this.cargoDAO.getList()) {
            if (cargoLista.getCodigo() == codigo) {
                return cargoLista;
            }
        }
        return null;
    }
    
    /**
     * Pede ao usuario todos os horarios de inicio e fim para instanciar um novo
     * cargo especial e caso algum horario tenha sido inserido em formato errado
     * acusa erro e volta a pedir para inserir
     */
    public CargoHorarioEspecial criaCargoEspecial(
			String nome, int codigo, Date horaInicioManha,
			Date horaFimManha, Date horaInicioTarde, Date horaFimTarde,
			Date horaInicioEspecial, Date horaFimEspecial
	) {
        CargoHorarioEspecial cargo = new CargoHorarioEspecial(
                        codigo, nome, horaInicioManha, horaFimManha,
                        horaInicioTarde, horaFimTarde, horaInicioEspecial,
                        horaFimEspecial
        );
        this.cargoDAO.put(cargo);
        return cargo;
    }
    
    /**
     * Instancia cargo indefinido que não tera função gerencial, nem acesso ao
     * financeiro
     */
    public void criaCargoPadrao() {
        Date dataIndefinida = new Date();
        Cargo cargo = new Cargo(0, Constantes.CARGO_INDEFINIDO, false, false,
                dataIndefinida, dataIndefinida, dataIndefinida, dataIndefinida);
        this.cargoDAO.put(cargo);
    }

    /**
     * Chama a função que encontra cargo no array de cargos, nessa caso
     * especifico o cargo indefinido, e retorna esse cargo
     *
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
    public Cargo criaCargoComum(String nome, int codigo, Date horaInicioManha,
		Date horaFimManha, Date horaInicioTarde, Date horaFimTarde) {
		Cargo cargo = new Cargo(
				codigo, nome, false, true, horaInicioManha, horaFimManha,
				horaInicioTarde, horaFimTarde
		);
		this.cargoDAO.put(cargo);
		return cargo;
    }
    
    
    
    
	
//    /**
//     * Pede qual cargo o usuario deseja editar. Exibe o menu de editar cargo e
//     * chama quem controla o menu.
//     */
//    public void editaCargo() {
//        this.telaCargo.mensagemEditaCargo();
//        Cargo cargo = pedeCargo();
//        this.telaCargo.exibeMensagemCargoSelecionado();
//        this.telaCargo.exibeCargo(cargo.getCodigo(), cargo.getNome(), cargo.ehGerencial(), cargo.temAcessoAoFinanceiro());
//        menuEditaCargo(cargo);
//    }
//
//    /**
//     * Método responsavel pelo menu de editar o cargo, onde chama o metodo da
//     * tela para exibir o menu propriamente e depois chama o método responsavel
//     * pelo controle das opcoes possiveis
//     *
//     * @param cargo a ser editado
//     */
//    public void menuEditaCargo(Cargo cargo) {
//        this.telaCargo.exibeMenuEditaCargo();
//        controlaMenuEditaCargo(cargo);
//    }
//
//    /**
//     * Controla o que o sistema faz com base na opcao que o usuario selecionar
//     * no menu para deletar o cargo. Caso aperte 1: altera o nome do cargo. Caso
//     * aperte 2: altera o codigo do cargo. Caso aperte 3: altera a função
//     * gerencial do cargo. Caso aperte 4: altera a permissão de acesso ao
//     * financeiro do cargo. Caso aperte 5: volta ao menu principal. Caso aperte
//     * qualquer outra tecla: exibe mensagem de opcao invalida e pede para que
//     * insira uma nova opcao.
//     *
//     * @param cargo a ser editado.
//     */
//    public void controlaMenuEditaCargo(Cargo cargo) {
//        int opcao = this.telaCargo.pedeOpcao();
//
//        switch (opcao) {
//            case 1:
//                String nome = this.telaCargo.pedeNome();
//                cargo.setNome(nome);
//                this.telaCargo.mensagemNomeEditadoSucesso();
//                menuEditaCargo(cargo);
//                break;
//            case 2:
//                int codigo = this.telaCargo.pedeCodigo();
//                cargo.setCodigo(codigo);
//                this.telaCargo.mensagemCodigoEditadoSucesso();
//                menuEditaCargo(cargo);
//                break;
//            case 3:
//                int opcaoEhGerencial = this.telaCargo.pedeSeEhGerencial();
//                atualizaEhGerencial(opcaoEhGerencial, cargo);
//                this.telaCargo.mensagemGerencialEditadoSucesso();
//                menuEditaCargo(cargo);
//                break;
//            case 4:
//                int opcaoTemAcessoAoFinanceiro = this.telaCargo.pedeTemAcessoAoFinanceiro();
//                atualizaTemAcessoAoFinanceiro(opcaoTemAcessoAoFinanceiro, cargo);
//                this.telaCargo.mensagemAcessoEditadoSucesso();
//                menuEditaCargo(cargo);
//                break;
//            case 5:
//                if (cargo instanceof CargoHorarioEspecial) {
//                    atualizaHorariosCargoHorarioEspecial(cargo);
//                    telaCargo.mensagemHorariosEditadosSucesso();
//                } else if (cargo.temAcessoAoFinanceiro()) {
//                    atualizaHorariosCargo(cargo);
//                    telaCargo.mensagemHorariosEditadosSucesso();
//                } else {
//                    telaCargo.exibeEditaHorariosCargoSemAcesso();
//                    controlaMenuEditaCargo(cargo);
//                }
//            case 6:
//                exibeMenuCargo();
//                break;
//            default:
//                this.telaCargo.opcaoInexistente();
//                editaCargo();
//                break;
//        }
//    }

//    /**
//     * Chama a função listaCargos() e em seguida retorna ao menu cargo
//     */
//    public void pedeListaCargos() {
//        listaCargos();
//        exibeMenuCargo();
//    }
//
//    @Override
//    public void listaCargos() {
//        this.telaCargo.mensagemListaCargos();
//        for (Cargo cargoCadastrado : cargoDAO.getList()) {
//            int codigo = cargoCadastrado.getCodigo();
//            String nome = cargoCadastrado.getNome();
//            boolean ehGerencial = cargoCadastrado.ehGerencial();
//            boolean temAcessoAoFinanceiro = cargoCadastrado.temAcessoAoFinanceiro();
//            this.telaCargo.exibeCargo(codigo, nome, ehGerencial, temAcessoAoFinanceiro);
//        }
//    }

    

//    /**
//     * Pede inicialemente qual o codigo do cargo que o usuario esta se
//     * referindo. Com o codigo inserido pelo usuario, encontra o cargo em
//     * questão.
//     *
//     * @return cargo que o usuario esta se referindo
//     */
//    public Cargo pedeCargo() {
//        int codigo = this.telaCargo.pedeCodigo();
//        Cargo cargo = encontraCargoPorCodigo(codigo);
//        if (cargo == null) {
//            this.telaCargo.mensagemCargoNaoEncontrado();
//            cargo = pedeCargo();
//        }
//        return cargo;
//    }

    

//    /**
//     * Atualiza a função gerencial do cargo com base na opcao que o usuario
//     * selecionar. Caso aperte 1: determina que o cargo tem função gerencial.
//     * Caso aperte 2: determina que o cargo não tem função gerencial. Caso
//     * aperte qualquer outra tecla: exibe mensagem de opcao inexistente e volta
//     * para o menu edita cargo.
//     *
//     * @param opcaoEhGerencial a ser selecionada pelo usuario
//     * @param cargo a ser editado
//     */
//    public void atualizaEhGerencial(int opcaoEhGerencial, Cargo cargo) {
//        switch (opcaoEhGerencial) {
//            case 1:
//                cargo.setEhGerencial(true);
//                cargo.setTemAcessoAoFinanceiro(true);
//                break;
//            case 2:
//                cargo.setEhGerencial(false);
//                break;
//            default:
//                this.telaCargo.opcaoInexistente();
//                menuEditaCargo(cargo);
//                break;
//        }
//    }

//    /**
//     * Atualiza a permissão de acesso do cargo com base na opcao que o usuario
//     * selecionar. Caso aperte 1: determina que o cargo tem permissão de acesso.
//     * Caso aperte 2: determina que o cargo não tem permissão de acesso. Caso
//     * aperte qualquer outra tecla: exibe mensagem de opcao inexistente e volta
//     * para o menu edita cargo.
//     *
//     * @param opcaoTemAcessoAoFinanceiro a ser selecionada pelo usuario
//     * @param cargo a ser editado
//     */
//    public void atualizaTemAcessoAoFinanceiro(int opcaoTemAcessoAoFinanceiro, Cargo cargo) {
//        switch (opcaoTemAcessoAoFinanceiro) {
//            case 1:
//                cargo.setTemAcessoAoFinanceiro(true);
//                break;
//            case 2:
//                cargo.setTemAcessoAoFinanceiro(false);
//                break;
//            default:
//                this.telaCargo.opcaoInexistente();
//                menuEditaCargo(cargo);
//                break;
//        }
//    }

//    /**
//     * Atualiza os horarios de acesso do cargo especial com base na opcao que o
//     * usuario escolher. No caso de hora inserida em formato incorreto exibe
//     * mensagem: FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado
//     *
//     * @param cargo a ser editado horarios
//     */
//    public void atualizaHorariosCargoHorarioEspecial(Cargo cargo) {
//        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
//        try {
//            Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
//            Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
//            Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
//            Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
//            Date horaInicioEspecial = formatador.parse(telaCargo.pedeHoraInicioEspecial());
//            Date horaFimEspecial = formatador.parse(telaCargo.pedeHoraFimEspecial());
//            ((CargoHorarioEspecial) cargo).setHoraInicioManha(horaInicioManha);
//            ((CargoHorarioEspecial) cargo).setHoraFimManha(horaFimManha);
//            ((CargoHorarioEspecial) cargo).setHoraInicioTarde(horaInicioTarde);
//            ((CargoHorarioEspecial) cargo).setHoraFimTarde(horaFimTarde);
//            ((CargoHorarioEspecial) cargo).setHoraInicioEspecial(horaInicioEspecial);
//            ((CargoHorarioEspecial) cargo).setHoraFimEspecial(horaFimEspecial);
//        } catch (ParseException e) {
//            telaCargo.exibeHoraInseridaFormatoIncorreto();
//            atualizaHorariosCargoHorarioEspecial(cargo);
//        }

    }

//    /**
//     * Atualiza os horarios de acesso do cargo com base na opcao que o usuario
//     * escolher. No caso de hora inserida em formato incorreto exibe mensagem:
//     * FORMATO INSERIDO INVÁLIDO, utilize o formato solicitado
//     *
//     * @param cargo a ser editado horarios
//     */
//    public void atualizaHorariosCargo(Cargo cargo) {
//        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
//        try {
//            Date horaInicioManha = formatador.parse(telaCargo.pedeHoraInicioManha());
//            Date horaFimManha = formatador.parse(telaCargo.pedeHoraFimManha());
//            Date horaInicioTarde = formatador.parse(telaCargo.pedeHoraInicioTarde());
//            Date horaFimTarde = formatador.parse(telaCargo.pedeHoraFimTarde());
//            Date horaInicioEspecial = formatador.parse(telaCargo.pedeHoraInicioEspecial());
//            Date horaFimEspecial = formatador.parse(telaCargo.pedeHoraFimEspecial());
//            cargo.setHoraInicioManha(horaInicioManha);
//            cargo.setHoraFimManha(horaFimManha);
//            cargo.setHoraInicioTarde(horaInicioTarde);
//            cargo.setHoraFimTarde(horaFimTarde);
//        } catch (ParseException e) {
//            telaCargo.exibeHoraInseridaFormatoIncorreto();
//            atualizaHorariosCargo(cargo);
//        }
//    }
