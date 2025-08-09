package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.glassfish.hk2.api.AnnotationLiteral;
import org.glassfish.hk2.api.Unqualified;

public class UnqualifiedImpl extends AnnotationLiteral implements Unqualified {
   private static final long serialVersionUID = 7982327982416740739L;
   private final Class[] value;

   public UnqualifiedImpl(Class... value) {
      this.value = (Class[])Arrays.copyOf(value, value.length);
   }

   public Class[] value() {
      return (Class[])Arrays.copyOf(this.value, this.value.length);
   }

   public String toString() {
      String var10000 = Arrays.toString(this.value);
      return "UnqualifiedImpl(" + var10000 + "," + System.identityHashCode(this) + ")";
   }
}
