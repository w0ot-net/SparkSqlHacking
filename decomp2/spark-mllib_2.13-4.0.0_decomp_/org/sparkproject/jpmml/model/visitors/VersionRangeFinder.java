package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.AnnotatedElement;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;

public class VersionRangeFinder extends VersionInspector implements Resettable {
   private Version minimum = Version.getMinimum();
   private Version maximum = Version.getMaximum();

   public void reset() {
      this.minimum = Version.getMinimum();
      this.maximum = Version.getMaximum();
   }

   public void handleAdded(PMMLObject object, AnnotatedElement element, Added added) {
      this.updateMinimum(object, element, added.value());
   }

   public void handleRemoved(PMMLObject object, AnnotatedElement element, Removed removed) {
      this.updateMaximum(object, element, removed.value());
   }

   public void handleOptional(PMMLObject object, AnnotatedElement element, Optional optional) {
      this.updateMinimum(object, element, optional.value());
   }

   public void handleRequired(PMMLObject object, AnnotatedElement element, Required required) {
      this.updateMaximum(object, element, required.value().previous());
   }

   public Version getMinimum() {
      return this.minimum;
   }

   public void updateMinimum(PMMLObject object, AnnotatedElement element, Version minimum) {
      if (minimum != null && minimum.compareTo(this.minimum) > 0) {
         this.minimum = minimum;
      }

   }

   public Version getMaximum() {
      return this.maximum;
   }

   public void updateMaximum(PMMLObject object, AnnotatedElement element, Version maximum) {
      if (maximum != null && maximum.compareTo(this.maximum) < 0) {
         this.maximum = maximum;
      }

   }
}
