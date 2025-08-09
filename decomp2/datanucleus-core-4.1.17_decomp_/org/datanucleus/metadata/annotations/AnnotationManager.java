package org.datanucleus.metadata.annotations;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.PackageMetaData;

public interface AnnotationManager {
   AbstractClassMetaData getMetaDataForClass(Class var1, PackageMetaData var2, ClassLoaderResolver var3);

   boolean getClassAnnotationHasHandler(String var1);

   boolean getMemberAnnotationHasHandler(String var1);

   ClassAnnotationHandler getHandlerForClassAnnotation(String var1);

   MemberAnnotationHandler getHandlerForMemberAnnotation(String var1);
}
