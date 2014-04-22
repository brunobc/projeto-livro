package validations;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.constraintvalidators.FutureValidatorForCalendar;

public class FromNowValidator implements ConstraintValidator<FromNow,Calendar> {
	
	private FutureValidatorForCalendar futureValidator = new FutureValidatorForCalendar();

	@Override
	public boolean isValid(Calendar data,
			ConstraintValidatorContext constraintValidatorContext) {
		if(data==null) return true;
		Calendar hoje = Calendar.getInstance();
		return mesmoDia(hoje, data)
				|| futureValidator.isValid(data, constraintValidatorContext);
	}

	private boolean mesmoDia(Calendar hoje, Calendar data) {
		return hoje.get(Calendar.DAY_OF_MONTH) == data
				.get(Calendar.DAY_OF_MONTH)
				&& hoje.get(Calendar.MONTH) == data.get(Calendar.MONTH)
				&& hoje.get(Calendar.YEAR) == data.get(Calendar.YEAR);
	}

	@Override
	public void initialize(FromNow fromNow) {
		
	}
}
