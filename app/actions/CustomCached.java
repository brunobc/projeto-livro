package actions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import play.mvc.With;

@With(CachedAction.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCached {
    /**
     * The cache key to store the result in
     */
    String key();

    /**
     * The duration the action should be cached for.  Defaults to 0.
     */
    int duration() default 0;
}

