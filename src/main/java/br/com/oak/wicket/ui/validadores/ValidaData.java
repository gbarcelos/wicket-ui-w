package br.com.oak.wicket.ui.validadores;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.joda.time.DateTime;

public class ValidaData<Date> implements IValidator<Date> {

	private static final long serialVersionUID = -7918277297179050243L;

	private int anoMin;

	private int anoMax;

	public ValidaData() {
	}

	public ValidaData(int ano, boolean anoInicial) {
		if (anoInicial) {
			this.anoMin = ano;
		} else {
			this.anoMax = ano;
		}
	}

	public ValidaData(int anoMin, int anoMax) {
		this.anoMin = anoMin;
		this.anoMax = anoMax;
	}

	@Override
	public void validate(IValidatable<Date> validatable) {
		if ((validatable.getValue() != null)
				&& !isDataValida(validatable.getValue())) {
			error(validatable, "invalido");
		}
	}

	protected boolean isDataValida(Date data) {
		boolean retorno = true;
		DateTime dt = new DateTime(data);
		Integer ano = dt.getYear();
		retorno = isMinMax(retorno, ano);
		retorno = chechaMesDia(retorno, dt, ano);
		return retorno;
	}

	private boolean chechaMesDia(boolean retorno, DateTime dt, Integer ano) {
		int mesTemp;
		Integer mes = dt.getMonthOfYear();
		Integer dia = dt.getDayOfMonth();
		mesTemp = checaMes(mes);
		retorno = checaMes(mesTemp, retorno, ano, dia);
		return retorno;
	}

	private boolean isMinMax(boolean retorno, Integer ano) {
		if ((anoMin > 0) && (ano < anoMin)) {
			retorno = false;
		}

		if ((anoMax > 0) && (ano > anoMax)) {
			retorno = false;
		}
		return retorno;
	}

	private boolean checaMes(int mesTemp, boolean retorno, Integer ano,
			Integer dia) {
		int diaTemp;
		Integer mes;
		if (mesTemp != -1) {

			mes = mesTemp;
			diaTemp = checaDia(ano, mes, dia);
			if (diaTemp == -1) {
				retorno = false;
			}
		} else {
			retorno = false;
		}
		return retorno;
	}

	private int checaMes(int mesTemp) {

		if ((mesTemp > 0) && (mesTemp <= 12)) {
			return mesTemp;
		} else {
			return -1;
		}
	}

	private int checaDia(Integer ano, Integer mes, Integer diaTemp) {
		int ultimoDiaMes[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		if ((diaTemp > 0) && (diaTemp <= ultimoDiaMes[mes])) {
			return diaTemp;
		} else if ((mes == 2)
				&& (diaTemp == 29)
				&& (((ano % 400) == 0) || (((ano % 4) == 0) && ((ano % 100) != 0)))) {
			return diaTemp;
		} else {
			return -1;
		}
	}

	private void error(IValidatable<Date> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addMessageKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}
}