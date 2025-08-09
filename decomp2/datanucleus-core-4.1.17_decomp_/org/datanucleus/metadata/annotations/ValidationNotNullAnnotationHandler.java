package org.datanucleus.metadata.annotations;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;

public class ValidationNotNullAnnotationHandler implements MemberAnnotationHandler {
   public void processMemberAnnotation(AnnotationObject annotation, AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      ColumnMetaData[] colmds = mmd.getColumnMetaData();
      if (colmds != null && colmds.length != 0) {
         if (colmds[0].getAllowsNull() == null) {
            colmds[0].setAllowsNull(false);
         }

      } else {
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setAllowsNull(false);
         mmd.addColumn(colmd);
      }
   }
}
