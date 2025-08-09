package org.glassfish.jaxb.core.v2.model.annotation;

import com.sun.istack.Nullable;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.core.ErrorHandler;

public interface AnnotationReader {
   void setErrorHandler(ErrorHandler var1);

   Annotation getFieldAnnotation(Class var1, Object var2, Locatable var3);

   boolean hasFieldAnnotation(Class var1, Object var2);

   boolean hasClassAnnotation(Object var1, Class var2);

   Annotation[] getAllFieldAnnotations(Object var1, Locatable var2);

   Annotation getMethodAnnotation(Class var1, Object var2, Object var3, Locatable var4);

   boolean hasMethodAnnotation(Class var1, String var2, Object var3, Object var4, Locatable var5);

   Annotation[] getAllMethodAnnotations(Object var1, Locatable var2);

   Annotation getMethodAnnotation(Class var1, Object var2, Locatable var3);

   boolean hasMethodAnnotation(Class var1, Object var2);

   @Nullable
   Annotation getMethodParameterAnnotation(Class var1, Object var2, int var3, Locatable var4);

   @Nullable
   Annotation getClassAnnotation(Class var1, Object var2, Locatable var3);

   @Nullable
   Annotation getPackageAnnotation(Class var1, Object var2, Locatable var3);

   Object getClassValue(Annotation var1, String var2);

   Object[] getClassArrayValue(Annotation var1, String var2);
}
