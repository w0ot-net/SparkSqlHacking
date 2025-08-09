package org.sparkproject.guava.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
public abstract class TypeParameter extends TypeCapture {
   final TypeVariable typeVariable;

   protected TypeParameter() {
      Type type = this.capture();
      Preconditions.checkArgument(type instanceof TypeVariable, "%s should be a type variable.", (Object)type);
      this.typeVariable = (TypeVariable)type;
   }

   public final int hashCode() {
      return this.typeVariable.hashCode();
   }

   public final boolean equals(@CheckForNull Object o) {
      if (o instanceof TypeParameter) {
         TypeParameter<?> that = (TypeParameter)o;
         return this.typeVariable.equals(that.typeVariable);
      } else {
         return false;
      }
   }

   public String toString() {
      return this.typeVariable.toString();
   }
}
