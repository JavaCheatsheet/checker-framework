package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.QualifierArgument;

/**
 * Indicates that the given expressions evaluate to a value that is a key in all the given maps, if
 * the method returns the given result (either true or false).
 *
 * <p>As an example, consider the following method in {@code java.util.Map}:
 *
 * <pre>
 *   &#64;EnsuresNonEmptyIf(result=true, expression="key", map="this")
 *   public boolean containsKey(String key) { ... }
 * </pre>
 *
 * If an invocation {@code m.containsKey(k)} returns true, then the type of {@code k} can be
 * inferred to be {@code @NonEmpty("m")}.
 *
 * @see NonEmpty
 * @see EnsuresNonEmpty
 * @checker_framework.manual #map-key-checker Map Key Checker
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@ConditionalPostconditionAnnotation(qualifier = NonEmpty.class)
@InheritedAnnotation
public @interface EnsuresNonEmptyIf {
    /** The value the method must return, in order for the postcondition to hold. */
    boolean result();

    /**
     * Java expressions that are keys in the given maps after the method returns the given result.
     *
     * @checker_framework.manual #java-expressions-as-arguments Syntax of Java expressions
     */
    String[] expression();

    /**
     * Java expressions that are maps, each of which contains each of the expressions' value after
     * the method returns the given result.
     *
     * @checker_framework.manual #java-expressions-as-arguments Syntax of Java expressions
     */
    @JavaExpression
    @QualifierArgument("value")
    String[] map();
}