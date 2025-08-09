package shaded.parquet.com.fasterxml.jackson.databind.ext;

import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.Annotated;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;
import shaded.parquet.com.fasterxml.jackson.databind.util.ExceptionUtil;

public abstract class Java7Support {
   private static final Java7Support IMPL;

   public static Java7Support instance() {
      return IMPL;
   }

   public abstract Boolean findTransient(Annotated var1);

   public abstract Boolean hasCreatorAnnotation(Annotated var1);

   public abstract PropertyName findConstructorName(AnnotatedParameter var1);

   static {
      Java7Support impl = null;

      try {
         Class<?> cls = Class.forName("shaded.parquet.com.fasterxml.jackson.databind.ext.Java7SupportImpl");
         impl = (Java7Support)ClassUtil.createInstance(cls, false);
      } catch (IllegalAccessError var2) {
      } catch (Throwable t) {
         ExceptionUtil.rethrowIfFatal(t);
      }

      IMPL = impl;
   }
}
