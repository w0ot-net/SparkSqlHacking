package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;

public class ArrayListTrimmer extends AbstractVisitor {
   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         Object value = ReflectionUtil.getFieldValue(field, object);
         if (value instanceof ArrayList) {
            ArrayList<?> list = (ArrayList)value;
            list.trimToSize();
         }
      }

      return super.visit(object);
   }
}
