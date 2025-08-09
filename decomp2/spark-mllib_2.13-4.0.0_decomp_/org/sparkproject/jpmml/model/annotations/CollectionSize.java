package org.sparkproject.jpmml.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CollectionSize {
   String value();

   Operator operator() default CollectionSize.Operator.EQUAL;

   public static enum Operator {
      EQUAL {
         public boolean check(int size, Collection collection) {
            return size == collection.size();
         }
      },
      GREATER_OR_EQUAL {
         public boolean check(int size, Collection collection) {
            return size >= collection.size();
         }
      };

      public abstract boolean check(int var1, Collection var2);
   }
}
