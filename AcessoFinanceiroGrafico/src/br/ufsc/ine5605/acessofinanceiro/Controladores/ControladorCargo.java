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
import excecoes.CodigoInexistenteException;
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
        this.telaCargo = new TelaCargo();
        this.cargoDAO = new CargoDAO();
        this.telaEditarCargo = new TelaEditarCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
    }

    public static ControladorCargo getInstance() {
        if (controladorCargo == null) controladorCargo = new ControladorCargo();
        return controladorCargo;
    }
    
    /**
     * Volta ao menu principal.
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

	/**
	 * Exibe tela de cadastro de cargo.
	 */
    public void exibeCadastraFuncionario() {
        this.telaCadastroCargo.exibeMenuCadastroCargo();
    }
    
	/**
	 * Solicita lista de cargos persistidos.
	 * @return ArrayList<Cargo> arrayList com os cargos persistidos
	 */
    public ArrayList<Cargo> getListaCargos() {
        return this.cargoDAO.getList();
    }

	/**
	 * Regressa ao menu principal.
	 */
    public void voltaMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }
    
	/**
	 * Solicita que a telaCargo faca update.
	 */
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
     * Caso o cargo exista na lista de cargos ele é deletado da mesma.
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
    }

    /**
     * Verifica se o codigo inserido pelo usuario ja foi cadastrado
     * anteriormente.
     *
     * @return codigo caso não tenha sido cadastrado antes, caso contrario pede
     * para que o usuario insira um novo codigo
     */
    public boolean verificaCodigoInserido(int codigo) {
        for (Cargo cargoCadastrado : this.cargoDAO.getList()) {
            if (cargoCadastrado.getCodigo() == codigo) {
                this.telaCargo.mensagemErroCodigoJaCadastrado();
                return false;
            }
        }
        return true;
    }

	/**
	 * Verifica se o codigo eh valido conferindo o tamanho, existencia de letras
	 * e existencia de codigo igual.
	 * @param codigo codigo verificado
	 * @return true se o codigo for valido
	 */
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
    
	/**
	 * Exibe tela de cadastro de cargo.
	 */
    public void exibeCadastraCargo() {
        this.telaCadastroCargo.exibeMenuCadastroCargo();
    }
    
	/**
	 * Exibe tela de edicao de cargo.
	 * @param indexSelecionado index da linha selecionada da tabela
	 */
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
	
	/**
	 * Realiza a edicao do cargo.
	 * 
	 * @param codigoAntigo codigo anterior do cargo editado
	 * @param codigo codigo novo do cargo editado
	 * @param nome nome do cargo editado
	 * @param tipoCargo tipo do cargo editado
	 * @param inputHoraInicioManha hora de inicio do turno matutino do cargo editado
	 * @param inputHoraFimManha hora de fim do turno matutino do cargo editado
	 * @param inputHoraInicioTarde hora de inicio do turno vespertino do cargo editado
	 * @param inputHoraFimTarde hora de fim do turno vespertino do cargo editado
	 * @param inputHoraInicioEspecial hora de inicio do turno especial do cargo editado
	 * @param inputHoraFimEspecial hora de fim do turno especial do cargo editado
	 */
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
    public Cargo encontraCargoPorCodigo(int codigo) throws CodigoInexistenteException {
        for (Cargo cargoLista : this.cargoDAO.getList()) {
            if (cargoLista.getCodigo() == codigo) {
                return cargoLista;
            }
        }
        throw new CodigoInexistenteException();
    }
    
    /**
     * Pede ao usuario todos os horarios de inicio e fim para instanciar um novo
     * cargo especial e caso algum horario tenha sido inserido em formato errado
     * acusa erro e volta a pedir para inserir.
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
     * financeiro.
     */
    public void criaCargoPadrao() {
        Date dataIndefinida = new Date();
        Cargo cargo = new Cargo(0, Constantes.CARGO_INDEFINIDO, false, false,
                dataIndefinida, dataIndefinida, dataIndefinida, dataIndefinida);
        this.cargoDAO.put(cargo);
    }

    /**
     * Chama a função que encontra cargo no array de cargos, nessa caso
     * especifico o cargo indefinido, e retorna esse cargo.
     *
     * @return cargo
     */
    public Cargo encontraCargoIndefinido() {
		Cargo cargo = null;
		try {
			cargo = encontraCargoPorCodigo(0);
		} catch (CodigoInexistenteException e) {
			System.out.println(e.getMessage());
		}
		return cargo;
    }

    /**
     * Pede ao usuario todos os horarios de inicio e fim para instanciar um novo
     * cargo comum e caso algum horario tenha sido inserido em formato errado
     * acusa erro e volta a pedir para inserir.
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
    
}