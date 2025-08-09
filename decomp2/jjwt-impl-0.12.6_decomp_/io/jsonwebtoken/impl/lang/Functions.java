package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public final class Functions {
   private Functions() {
   }

   public static Function identity() {
      return new Function() {
         public Object apply(Object t) {
            return t;
         }
      };
   }

   public static Function wrapFmt(CheckedFunction fn, Class exClass, String msg) {
      return new PropagatingExceptionFunction(fn, exClass, new FormattedStringFunction(msg));
   }

   public static Function wrap(Function fn, Class exClass, String fmt, Object... args) {
      return new PropagatingExceptionFunction(new DelegatingCheckedFunction(fn), exClass, new FormattedStringSupplier(fmt, args));
   }

   public static Function andThen(final Function before, final Function after) {
      Assert.notNull(before, "Before function cannot be null.");
      Assert.notNull(after, "After function cannot be null.");
      return new Function() {
         public Object apply(Object t) {
            V result = (V)before.apply(t);
            return after.apply(result);
         }
      };
   }

   @SafeVarargs
   public static Function firstResult(final Function... fns) {
      Assert.notEmpty(fns, "Function list cannot be null or empty.");
      return new Function() {
         public Object apply(Object t) {
            for(Function fn : fns) {
               Assert.notNull(fn, "Function cannot be null.");
               R result = (R)fn.apply(t);
               if (result != null) {
                  return result;
               }
            }

            return null;
         }
      };
   }
}
