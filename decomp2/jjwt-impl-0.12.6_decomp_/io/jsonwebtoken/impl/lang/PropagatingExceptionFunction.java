package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Supplier;
import java.lang.reflect.Constructor;

public class PropagatingExceptionFunction implements Function {
   private final CheckedFunction function;
   private final Function msgFunction;
   private final Class clazz;

   public PropagatingExceptionFunction(Function f, Class exceptionClass, String msg) {
      this((CheckedFunction)(new DelegatingCheckedFunction(f)), exceptionClass, (Function)(new ConstantFunction(msg)));
   }

   public PropagatingExceptionFunction(CheckedFunction f, Class exceptionClass, String msg) {
      this((CheckedFunction)f, exceptionClass, (Function)(new ConstantFunction(msg)));
   }

   public PropagatingExceptionFunction(CheckedFunction fn, Class exceptionClass, Supplier msgSupplier) {
      // $FF: Couldn't be decompiled
   }

   public PropagatingExceptionFunction(CheckedFunction f, Class exceptionClass, Function msgFunction) {
      this.clazz = (Class)Assert.notNull(exceptionClass, "Exception class cannot be null.");
      this.msgFunction = (Function)Assert.notNull(msgFunction, "msgFunction cannot be null.");
      this.function = (CheckedFunction)Assert.notNull(f, "Function cannot be null");
   }

   public Object apply(Object t) {
      try {
         return this.function.apply(t);
      } catch (Exception e) {
         if (this.clazz.isAssignableFrom(e.getClass())) {
            throw (RuntimeException)this.clazz.cast(e);
         } else {
            String msg = (String)this.msgFunction.apply(t);
            if (!msg.endsWith(".")) {
               msg = msg + ".";
            }

            msg = msg + " Cause: " + e.getMessage();
            Class<RuntimeException> clazzz = this.clazz;
            Constructor<RuntimeException> ctor = Classes.getConstructor(clazzz, new Class[]{String.class, Throwable.class});
            throw (RuntimeException)Classes.instantiate(ctor, new Object[]{msg, e});
         }
      }
   }
}
