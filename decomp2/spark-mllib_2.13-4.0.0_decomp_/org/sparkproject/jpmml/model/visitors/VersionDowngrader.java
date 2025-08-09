package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Objects;
import org.sparkproject.dmg.pmml.Apply;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.PMMLAttributes;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.TargetValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.time_series.TrendExpoSmooth;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.UnsupportedAttributeException;
import org.sparkproject.jpmml.model.UnsupportedElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;

public class VersionDowngrader extends VersionInspector {
   private Version version = null;

   public VersionDowngrader(Version version) {
      this.version = (Version)Objects.requireNonNull(version);
      if (!version.isStandard()) {
         throw new IllegalArgumentException();
      }
   }

   public void handleAdded(PMMLObject object, AnnotatedElement element, Added added) {
      Version version = added.value();
      if (version.isStandard() && version.compareTo(this.version) > 0 && !(element instanceof Class)) {
         if (!(element instanceof Field)) {
            throw new IllegalArgumentException();
         }

         Field field = (Field)element;
         if (added.removable()) {
            ReflectionUtil.setFieldValue(field, object, (Object)null);
         }
      }

   }

   public void handleRemoved(PMMLObject object, AnnotatedElement element, Removed removed) {
   }

   public void handleOptional(PMMLObject object, AnnotatedElement element, Optional optional) {
   }

   public void handleRequired(PMMLObject object, AnnotatedElement element, Required required) {
   }

   public VisitorAction visit(Apply apply) {
      Object defaultValue = apply.getDefaultValue();
      if (defaultValue != null && this.version.compareTo(Version.PMML_4_1) == 0) {
         Object mapMissingTo = apply.getMapMissingTo();
         if (mapMissingTo != null) {
            throw new UnsupportedAttributeException(apply, PMMLAttributes.APPLY_DEFAULTVALUE, defaultValue);
         }

         apply.setDefaultValue((Object)null).setMapMissingTo(defaultValue);
      }

      return super.visit(apply);
   }

   public VisitorAction visit(MiningField miningField) {
      MiningField.UsageType usageType = miningField.getUsageType();
      switch (usageType) {
         case TARGET:
            if (this.version.compareTo(Version.PMML_4_2) < 0) {
               miningField.setUsageType(MiningField.UsageType.PREDICTED);
            }
         default:
            return super.visit((MiningField)miningField);
      }
   }

   public VisitorAction visit(PMML pmml) {
      pmml.setVersion(this.version.getVersion());
      return super.visit((PMML)pmml);
   }

   public VisitorAction visit(TargetValue targetValue) {
      String displayValue = targetValue.getDisplayValue();
      if (displayValue != null && this.version.compareTo(Version.PMML_3_2) <= 0) {
         throw new UnsupportedAttributeException(targetValue, PMMLAttributes.TARGETVALUE_DISPLAYVALUE, displayValue);
      } else {
         return super.visit((TargetValue)targetValue);
      }
   }

   public VisitorAction visit(TrendExpoSmooth trendExpoSmooth) {
      if (this.version.compareTo(Version.PMML_4_0) == 0) {
         throw new UnsupportedElementException(trendExpoSmooth);
      } else {
         return super.visit((TrendExpoSmooth)trendExpoSmooth);
      }
   }
}
