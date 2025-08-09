package org.glassfish.jaxb.runtime.v2.model.annotation;

import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.ErrorHandler;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

public abstract class AbstractInlineAnnotationReaderImpl implements AnnotationReader {
   private ErrorHandler errorHandler;

   protected AbstractInlineAnnotationReaderImpl() {
   }

   public void setErrorHandler(ErrorHandler errorHandler) {
      if (errorHandler == null) {
         throw new IllegalArgumentException();
      } else {
         this.errorHandler = errorHandler;
      }
   }

   public final ErrorHandler getErrorHandler() {
      assert this.errorHandler != null : "error handler must be set before use";

      return this.errorHandler;
   }

   public final Annotation getMethodAnnotation(Class annotation, Object getter, Object setter, Locatable srcPos) {
      A a1 = (A)(getter == null ? null : this.getMethodAnnotation(annotation, getter, srcPos));
      A a2 = (A)(setter == null ? null : this.getMethodAnnotation(annotation, setter, srcPos));
      if (a1 == null) {
         return a2;
      } else if (a2 == null) {
         return a1;
      } else {
         this.getErrorHandler().error(new IllegalAnnotationException(Messages.DUPLICATE_ANNOTATIONS.format(annotation.getName(), this.fullName(getter), this.fullName(setter)), a1, a2));
         return a1;
      }
   }

   public boolean hasMethodAnnotation(Class annotation, String propertyName, Object getter, Object setter, Locatable srcPos) {
      boolean x = getter != null && this.hasMethodAnnotation(annotation, getter);
      boolean y = setter != null && this.hasMethodAnnotation(annotation, setter);
      if (x && y) {
         this.getMethodAnnotation(annotation, getter, setter, srcPos);
      }

      return x || y;
   }

   protected abstract String fullName(Object var1);
}
