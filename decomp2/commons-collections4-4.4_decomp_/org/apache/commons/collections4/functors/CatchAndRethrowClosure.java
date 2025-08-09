package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

public abstract class CatchAndRethrowClosure implements Closure {
   public void execute(Object input) {
      try {
         this.executeAndThrow(input);
      } catch (RuntimeException ex) {
         throw ex;
      } catch (Throwable t) {
         throw new FunctorException(t);
      }
   }

   protected abstract void executeAndThrow(Object var1) throws Throwable;
}
