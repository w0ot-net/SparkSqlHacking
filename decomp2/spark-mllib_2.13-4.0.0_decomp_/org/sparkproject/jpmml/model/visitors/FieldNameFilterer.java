package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.OutputField;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ResultFeature;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;

public abstract class FieldNameFilterer extends AbstractVisitor {
   public abstract String filter(String var1);

   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         if (ReflectionUtil.isFieldName(field)) {
            String name = (String)ReflectionUtil.getFieldValue(field, object);
            name = this.filter(name);
            ReflectionUtil.setFieldValue(field, object, name);
         }
      }

      return super.visit(object);
   }

   public VisitorAction visit(OutputField outputField) {
      ResultFeature resultFeature = outputField.getResultFeature();
      switch (resultFeature) {
         case TRANSFORMED_VALUE:
         case DECISION:
            String segmentId = outputField.getSegmentId();
            if (segmentId != null) {
               Object value = outputField.getValue();
               if (value instanceof String) {
                  value = this.filter((String)value);
               }

               outputField.setValue(value);
            }
         default:
            return super.visit(outputField);
      }
   }
}
