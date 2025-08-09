package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class Partially {
   private Partially() {
   }

   @Retention(RetentionPolicy.CLASS)
   @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
   @Documented
   @interface GwtIncompatible {
      String value();
   }
}
