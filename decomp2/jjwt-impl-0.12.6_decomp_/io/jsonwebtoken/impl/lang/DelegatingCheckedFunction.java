package io.jsonwebtoken.impl.lang;

public class DelegatingCheckedFunction implements CheckedFunction {
   final Function delegate;

   public DelegatingCheckedFunction(Function delegate) {
      this.delegate = delegate;
   }

   public Object apply(Object t) throws Exception {
      return this.delegate.apply(t);
   }
}
