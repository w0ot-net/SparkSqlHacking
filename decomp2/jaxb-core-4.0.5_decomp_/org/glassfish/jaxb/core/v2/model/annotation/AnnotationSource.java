package org.glassfish.jaxb.core.v2.model.annotation;

import java.lang.annotation.Annotation;

public interface AnnotationSource {
   Annotation readAnnotation(Class var1);

   boolean hasAnnotation(Class var1);
}
