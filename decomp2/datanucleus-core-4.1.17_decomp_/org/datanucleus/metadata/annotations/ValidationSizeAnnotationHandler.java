package org.datanucleus.metadata.annotations;

import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;

public class ValidationSizeAnnotationHandler implements MemberAnnotationHandler {
   public void processMemberAnnotation(AnnotationObject annotation, AbstractMemberMetaData mmd, ClassLoaderResolver clr) {
      Map<String, Object> annotationValues = annotation.getNameValueMap();
      int max = (Integer)annotationValues.get("max");
      ColumnMetaData[] colmds = mmd.getColumnMetaData();
      if (colmds != null && colmds.length != 0) {
         if (colmds[0].getLength() == null) {
            colmds[0].setLength(max);
         }

      } else {
         ColumnMetaData colmd = new ColumnMetaData();
         colmd.setLength(max);
         mmd.addColumn(colmd);
      }
   }
}
