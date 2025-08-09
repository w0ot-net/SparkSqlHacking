package org.datanucleus.metadata.annotations;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;

public interface MemberAnnotationHandler {
   void processMemberAnnotation(AnnotationObject var1, AbstractMemberMetaData var2, ClassLoaderResolver var3);
}
