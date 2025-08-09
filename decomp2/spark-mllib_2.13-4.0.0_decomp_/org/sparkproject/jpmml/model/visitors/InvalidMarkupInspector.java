package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import org.sparkproject.dmg.pmml.Apply;
import org.sparkproject.dmg.pmml.Expression;
import org.sparkproject.dmg.pmml.Interval;
import org.sparkproject.dmg.pmml.InvalidValueTreatmentMethod;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.MissingValueTreatmentMethod;
import org.sparkproject.dmg.pmml.OutputField;
import org.sparkproject.dmg.pmml.PMMLAttributes;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.ResultFeature;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.TargetValue;
import org.sparkproject.dmg.pmml.TextIndexNormalization;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.InvalidAttributeException;
import org.sparkproject.jpmml.model.InvalidElementException;
import org.sparkproject.jpmml.model.MisplacedAttributeException;
import org.sparkproject.jpmml.model.MisplacedElementException;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.annotations.CollectionSize;

public class InvalidMarkupInspector extends MarkupInspector {
   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         CollectionSize collectionSize = (CollectionSize)field.getAnnotation(CollectionSize.class);
         if (collectionSize != null) {
            Field collectionField = ReflectionUtil.getField(object.getClass(), collectionSize.value());
            Object sizeValue = ReflectionUtil.getFieldValue(field, object);
            Object collectionValue = ReflectionUtil.getFieldValue(collectionField, object);
            if (sizeValue != null) {
               int size = (Integer)sizeValue;
               List<?> collection = (List)collectionValue;
               CollectionSize.Operator operator = collectionSize.operator();
               boolean valid;
               switch (operator) {
                  case EQUAL:
                  case GREATER_OR_EQUAL:
                     valid = operator.check(size, collection != null ? collection : Collections.emptyList());
                     break;
                  default:
                     valid = true;
               }

               if (!valid) {
                  this.report(new InvalidAttributeException(object, field, sizeValue));
               }
            }
         }
      }

      return super.visit(object);
   }

   public VisitorAction visit(Apply apply) {
      InvalidValueTreatmentMethod invalidValueTreatmentMethod = apply.getInvalidValueTreatment();
      switch (invalidValueTreatmentMethod) {
         case AS_VALUE:
            this.report(new InvalidAttributeException(apply, invalidValueTreatmentMethod));
         default:
            return super.visit(apply);
      }
   }

   public VisitorAction visit(Interval interval) {
      Number leftMargin = interval.getLeftMargin();
      Number rightMargin = interval.getRightMargin();
      if (leftMargin != null && rightMargin != null && Double.compare(leftMargin.doubleValue(), rightMargin.doubleValue()) > 0) {
         this.report(new InvalidElementException(interval));
      }

      return super.visit(interval);
   }

   public VisitorAction visit(MiningField miningField) {
      InvalidValueTreatmentMethod invalidValueTreatmentMethod = miningField.getInvalidValueTreatment();
      Object invalidValueReplacement = miningField.getInvalidValueReplacement();
      switch (invalidValueTreatmentMethod) {
         case AS_IS:
            if (invalidValueReplacement != null) {
            }
            break;
         case AS_MISSING:
         case RETURN_INVALID:
            if (invalidValueReplacement != null) {
               throw new MisplacedAttributeException(miningField, PMMLAttributes.MININGFIELD_INVALIDVALUEREPLACEMENT, invalidValueReplacement);
            }
      }

      MissingValueTreatmentMethod missingValueTreatmentMethod = miningField.getMissingValueTreatment();
      Object missingValueReplacement = miningField.getMissingValueReplacement();
      if (missingValueTreatmentMethod == null) {
         missingValueTreatmentMethod = MissingValueTreatmentMethod.AS_IS;
      }

      switch (missingValueTreatmentMethod) {
         case RETURN_INVALID:
            if (missingValueReplacement != null) {
               this.report(new MisplacedAttributeException(miningField, PMMLAttributes.MININGFIELD_MISSINGVALUEREPLACEMENT, missingValueReplacement));
            }
         default:
            Number lowValue = miningField.getLowValue();
            Number highValue = miningField.getHighValue();
            if (lowValue != null && highValue != null && Double.compare(lowValue.doubleValue(), highValue.doubleValue()) > 0) {
               throw new InvalidElementException(miningField);
            } else {
               return super.visit(miningField);
            }
      }
   }

   public VisitorAction visit(OutputField outputField) {
      ResultFeature resultFeature = outputField.getResultFeature();
      String targetFieldName = outputField.getTargetField();
      if (targetFieldName != null) {
         switch (resultFeature) {
            case TRANSFORMED_VALUE:
            case DECISION:
               Expression expression = outputField.getExpression();
               if (expression != null) {
                  this.report(new MisplacedElementException(expression));
               }
            case WARNING:
               this.report(new MisplacedAttributeException(outputField, PMMLAttributes.OUTPUTFIELD_TARGETFIELD, targetFieldName));
         }
      }

      return super.visit(outputField);
   }

   public VisitorAction visit(SimplePredicate simplePredicate) {
      SimplePredicate.Operator operator = simplePredicate.getOperator();
      if (operator != null) {
         switch (operator) {
            case IS_MISSING:
            case IS_NOT_MISSING:
               if (simplePredicate.hasValue()) {
                  this.report(new MisplacedAttributeException(simplePredicate, PMMLAttributes.SIMPLEPREDICATE_VALUE, simplePredicate.getValue()));
               }
         }
      }

      return super.visit(simplePredicate);
   }

   public VisitorAction visit(TargetValue targetValue) {
      Number defaultValue = targetValue.getDefaultValue();
      Object value = targetValue.getValue();
      Number priorProbability = targetValue.getPriorProbability();
      if (defaultValue != null && (value != null || priorProbability != null)) {
         this.report(new InvalidElementException(targetValue));
      }

      return super.visit(targetValue);
   }

   public VisitorAction visit(TextIndexNormalization textIndexNormalization) {
      String wordRE = textIndexNormalization.getWordRE();
      String wordSeparatorCharacterRE = textIndexNormalization.getWordSeparatorCharacterRE();
      if (wordRE != null && wordSeparatorCharacterRE != null) {
         this.report(new InvalidElementException(textIndexNormalization));
      }

      return super.visit(textIndexNormalization);
   }
}
