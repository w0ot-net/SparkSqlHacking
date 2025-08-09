package io.jsonwebtoken.impl.lang;

abstract class ReflectionFunction implements Function {
   public static final String ERR_MSG = "Reflection operation failed. This is likely due to an internal implementation programming error.  Please report this to the JJWT development team.  Cause: ";

   protected abstract boolean supports(Object var1);

   protected abstract Object invoke(Object var1) throws Throwable;

   public final Object apply(Object input) {
      if (this.supports(input)) {
         try {
            return this.invoke(input);
         } catch (Throwable throwable) {
            String msg = "Reflection operation failed. This is likely due to an internal implementation programming error.  Please report this to the JJWT development team.  Cause: " + throwable.getMessage();
            throw new IllegalStateException(msg, throwable);
         }
      } else {
         return null;
      }
   }
}
