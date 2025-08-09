package shaded.parquet.com.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;

public abstract class Annotated {
   protected Annotated() {
   }

   public abstract Annotation getAnnotation(Class var1);

   public abstract boolean hasAnnotation(Class var1);

   public abstract boolean hasOneOf(Class[] var1);

   public abstract AnnotatedElement getAnnotated();

   protected abstract int getModifiers();

   public boolean isPublic() {
      return Modifier.isPublic(this.getModifiers());
   }

   public boolean isStatic() {
      return Modifier.isStatic(this.getModifiers());
   }

   public abstract String getName();

   public abstract JavaType getType();

   public abstract Class getRawType();

   /** @deprecated */
   @Deprecated
   public abstract Iterable annotations();

   public abstract boolean equals(Object var1);

   public abstract int hashCode();

   public abstract String toString();
}
