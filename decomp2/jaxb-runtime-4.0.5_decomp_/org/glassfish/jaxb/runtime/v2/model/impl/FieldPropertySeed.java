package org.glassfish.jaxb.runtime.v2.model.impl;

import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.Location;

class FieldPropertySeed implements PropertySeed {
   protected final Object field;
   private ClassInfoImpl parent;

   FieldPropertySeed(ClassInfoImpl classInfo, Object field) {
      this.parent = classInfo;
      this.field = field;
   }

   public Annotation readAnnotation(Class a) {
      return this.parent.reader().getFieldAnnotation(a, this.field, this);
   }

   public boolean hasAnnotation(Class annotationType) {
      return this.parent.reader().hasFieldAnnotation(annotationType, this.field);
   }

   public String getName() {
      return this.parent.nav().getFieldName(this.field);
   }

   public Object getRawType() {
      return this.parent.nav().getFieldType(this.field);
   }

   public Locatable getUpstream() {
      return this.parent;
   }

   public Location getLocation() {
      return this.parent.nav().getFieldLocation(this.field);
   }
}
