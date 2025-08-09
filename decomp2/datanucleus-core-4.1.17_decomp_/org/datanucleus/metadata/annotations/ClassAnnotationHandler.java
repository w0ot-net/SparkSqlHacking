package org.datanucleus.metadata.annotations;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;

public interface ClassAnnotationHandler {
   void processClassAnnotation(AnnotationObject var1, AbstractClassMetaData var2, ClassLoaderResolver var3);
}
