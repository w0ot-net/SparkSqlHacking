package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.AnnotatedElement;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.VersionUtil;
import org.sparkproject.jpmml.model.annotations.Since;

public class ExtendedVersionRangeFinder extends VersionRangeFinder {
   private String libraryMinimum = null;
   private String libraryMaximum = null;

   public void reset() {
      super.reset();
      this.libraryMinimum = null;
      this.libraryMaximum = null;
   }

   public void updateMinimum(PMMLObject object, AnnotatedElement element, Version minimum) {
      Since since = (Since)element.getAnnotation(Since.class);
      if (since != null) {
         String libraryMinimum = since.value();
         if (this.libraryMinimum == null || VersionUtil.compare(libraryMinimum, this.libraryMinimum) < 0) {
            this.libraryMinimum = libraryMinimum;
         }
      } else {
         super.updateMinimum(object, element, minimum);
      }

   }

   public void updateMaximum(PMMLObject object, AnnotatedElement element, Version maximum) {
      Since since = (Since)element.getAnnotation(Since.class);
      if (since != null) {
         String libraryMaximum = since.value();
         if (this.libraryMaximum == null || VersionUtil.compare(libraryMaximum, this.libraryMaximum) > 0) {
            this.libraryMaximum = libraryMaximum;
         }
      } else {
         super.updateMaximum(object, element, maximum);
      }

   }

   public String getLibraryMinimum() {
      return this.libraryMinimum;
   }

   public String getLibraryMaximum() {
      return this.libraryMaximum;
   }
}
