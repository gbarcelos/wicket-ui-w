package br.com.oak.wicket.ui.data;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import br.com.oak.wicket.ui.mascara.Mascara;
import br.com.oak.wicket.ui.validadores.ValidaData;

public class CampoData<Date> extends TextField<Date> {

	private static final long serialVersionUID = -5156729950165167278L;

	private int anoMin;

	private int anoMax;

	public CampoData(String id) {
		super(id);
		this.add(new Mascara("99/99/9999"));
		add(new ValidaData<Date>());
	}

	@Override
	public void validate() {

		String tmp = recuperaValorTemporario();

		boolean required = tmp != null && tmp.equals("__/__/____");

		if (required && isRequired()) {
			addError("converterDateRequired");

		} else if (required && !isRequired()) {
			addError("converterDate");

		} else {
			validacaoNaoConverter();
		}
	}

	private String recuperaValorTemporario() {
		String[] value = getInputAsArray();
		return value != null && value.length > 0 ? value[0] : null;
	}

	private void addError(String erro) {
		error(new ValidationError().addMessageKey(erro));
	}

	private void validacaoNaoConverter() {
		validateRequired();
		if (isValid()) {
			convertInput();
			if (isValid()) {
				if (isRequired() && getConvertedInput() == null
						&& isInputNullable()) {
					reportRequiredError();
				} else {
					validaData();
				}
			}
		}
	}

	private void reportRequiredError() {
		error(new ValidationError().addMessageKey("Required"));
	}

	private void validaData() {
		boolean isNull = getConvertedInput() == null;
		IValidator<Date> validator = null;
		try {
			if (!isNull || validator instanceof INullAcceptingValidator<?>) {
				isDataVazia();
			}
		} catch (Exception e) {
			error(new ValidationError().addMessageKey("DataInvalida"));
		}

	}

	private void isDataVazia() {
		if (StringUtils.isBlank(getValue())
				|| StringUtils.isNotBlank(getValue())
				&& !isDataValida(getValue())) {

			error(new ValidationError().addMessageKey("DataInvalida"));

			setConvertedInput(null);
			setModelObject(null);

		} else if (StringUtils.isBlank(getValue())
				|| StringUtils.isNotBlank(getValue())
				&& !isDataAnoInvalido(getValue())) {

			error(new ValidationError().addMessageKey("DataAnoInvalida"));

			setConvertedInput(null);
			setModelObject(null);
		}
	}

	protected boolean isDataValida(String data) {
		boolean retorno = true;
		Integer ano = Integer.parseInt(data.trim().substring(6, 10));
		Integer mes = Integer.parseInt(data.trim().substring(3, 5));
		Integer dia = Integer.parseInt(data.trim().substring(0, 2));
		retorno = checaMes(ano, retorno, mes, dia);
		return retorno;
	}

	public boolean isDataAnoInvalido(String data) {
		boolean retorno = true;
		Integer ano = Integer.parseInt(data.trim().substring(6, 10));
		retorno = verificaAnoMinMax(ano, retorno);
		return retorno;

	}

	private boolean checaMes(Integer ano, boolean retorno, Integer mes,
			Integer dia) {
		int mesTemp;
		int diaTemp;
		mesTemp = checaMes(mes);
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

	private boolean verificaAnoMinMax(Integer ano, boolean retorno) {
		if (anoMin > 0 && ano < anoMin) {
			retorno = false;
		}
		if (anoMax > 0 && ano > anoMax) {
			retorno = false;
		}
		return retorno;
	}

	private int checaMes(int mesTemp) {

		if (mesTemp > 0 && mesTemp <= 12) {
			return mesTemp;
		} else {
			return -1;
		}
	}

	private int checaDia(Integer ano, Integer mes, Integer diaTemp) {
		int ultimoDiaMes[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		if (diaTemp > 0 && diaTemp <= ultimoDiaMes[mes]) {
			return diaTemp;
		} else if (mes == 2 && diaTemp == 29
				&& (ano % 400 == 0 || ano % 4 == 0 && ano % 100 != 0)) {
			return diaTemp;
		} else {
			return -1;
		}
	}
}