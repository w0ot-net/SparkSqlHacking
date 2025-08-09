package org.sparkproject.jpmml.model.visitors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.List;
import org.sparkproject.dmg.pmml.Interval;
import org.sparkproject.dmg.pmml.InvalidValueTreatmentMethod;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.PMMLAttributes;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.ReflectionUtil;

public class MissingMarkupInspector extends MarkupInspector {
   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         Object value = ReflectionUtil.getFieldValue(field, object);
         if (value instanceof List) {
            List<?> collection = (List)value;
            if (collection.isEmpty()) {
               value = null;
            }
         }

         if (value == null) {
            XmlAttribute attribute = (XmlAttribute)field.getAnnotation(XmlAttribute.class);
            if (attribute != null && attribute.required()) {
               this.report(new MissingAttributeException(object, field));
            }

            XmlElement element = (XmlElement)field.getAnnotation(XmlElement.class);
            if (element != null && element.required()) {
               this.report(new MissingElementException(object, field));
            }
         }
      }

      return super.visit(object);
   }

   public VisitorAction visit(Interval interval) {
      Number leftMargin = interval.getLeftMargin();
      Number rightMargin = interval.getRightMargin();
      if (leftMargin == null && rightMargin == null) {
         this.report(new MissingAttributeException(interval, PMMLAttributes.INTERVAL_LEFTMARGIN));
         this.report(new MissingAttributeException(interval, PMMLAttributes.INTERVAL_RIGHTMARGIN));
      }

      return super.visit(interval);
   }

   public VisitorAction visit(MiningField miningField) {
      InvalidValueTreatmentMethod invalidValueTreatmentMethod = miningField.getInvalidValueTreatment();
      Object invalidValueReplacement = miningField.getInvalidValueReplacement();
      switch (invalidValueTreatmentMethod) {
         case AS_VALUE:
            if (invalidValueReplacement == null) {
               this.report(new MissingAttributeException(miningField, PMMLAttributes.MININGFIELD_INVALIDVALUEREPLACEMENT));
            }
         default:
            return super.visit(miningField);
      }
   }

   public VisitorAction visit(SimplePredicate simplePredicate) {
      SimplePredicate.Operator operator = simplePredicate.getOperator();
      if (operator != null) {
         switch (operator) {
            case EQUAL:
            case NOT_EQUAL:
            case LESS_THAN:
            case LESS_OR_EQUAL:
            case GREATER_OR_EQUAL:
            case GREATER_THAN:
               if (!simplePredicate.hasValue()) {
                  this.report(new MissingAttributeException(simplePredicate, PMMLAttributes.SIMPLEPREDICATE_VALUE));
               }
         }
      }

      return super.visit(simplePredicate);
   }
}
