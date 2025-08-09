package scala.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class StructuralCallSite$ {
   public static final StructuralCallSite$ MODULE$ = new StructuralCallSite$();

   public CallSite bootstrap(final MethodHandles.Lookup lookup, final String invokedName, final MethodType invokedType, final MethodType reflectiveCallType) {
      StructuralCallSite structuralCallSite = new StructuralCallSite(reflectiveCallType);
      return new ConstantCallSite(MethodHandles.constant(StructuralCallSite.class, structuralCallSite));
   }

   private StructuralCallSite$() {
   }
}
