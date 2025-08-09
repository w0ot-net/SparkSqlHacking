package org.sparkproject.guava.hash;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.When;
import org.sparkproject.guava.annotations.GwtCompatible;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Nonnull(
   when = When.UNKNOWN
)
@GwtCompatible
@TypeQualifierNickname
@interface ParametricNullness {
}
