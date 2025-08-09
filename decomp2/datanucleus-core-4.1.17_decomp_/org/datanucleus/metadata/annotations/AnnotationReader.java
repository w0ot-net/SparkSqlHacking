package org.datanucleus.metadata.annotations;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.PackageMetaData;

public interface AnnotationReader {
   String[] getSupportedAnnotationPackages();

   AbstractClassMetaData getMetaDataForClass(Class var1, PackageMetaData var2, ClassLoaderResolver var3);
}
