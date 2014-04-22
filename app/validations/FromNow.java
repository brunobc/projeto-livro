package validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value={ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=FromNowValidator.class)
@play.data.Form.Display(name="validations.FromNow")
public @interface FromNow {

	 String message() default "error.frowNow";
	
	 Class<?>[] groups() default { };

     Class<? extends Payload>[] payload() default { };
	
	
}
