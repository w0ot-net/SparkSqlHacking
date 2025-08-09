package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.MethodParameter;

public class MethodParameterImpl implements MethodParameter {
   private final int index;
   private final Object value;

   public MethodParameterImpl(int index, Object value) {
      this.index = index;
      this.value = value;
   }

   public int getParameterPosition() {
      return this.index;
   }

   public Object getParameterValue() {
      return this.value;
   }

   public String toString() {
      int var10000 = this.index;
      return "MethodParamterImpl(" + var10000 + "," + this.value + "," + System.identityHashCode(this) + ")";
   }
}
