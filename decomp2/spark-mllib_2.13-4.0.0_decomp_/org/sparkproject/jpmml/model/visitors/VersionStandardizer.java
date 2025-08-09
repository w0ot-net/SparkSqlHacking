package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;

public class VersionStandardizer extends VersionInspector {
   public void handleAdded(PMMLObject object, AnnotatedElement element, Added added) {
      Version version = added.value();
      if (!version.isStandard() && !(element instanceof Class)) {
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
}
