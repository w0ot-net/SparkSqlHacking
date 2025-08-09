package org.sparkproject.guava.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import org.sparkproject.guava.annotations.GwtCompatible;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Nonnull
@GwtCompatible
@interface ElementTypesAreNonnullByDefault {
}
