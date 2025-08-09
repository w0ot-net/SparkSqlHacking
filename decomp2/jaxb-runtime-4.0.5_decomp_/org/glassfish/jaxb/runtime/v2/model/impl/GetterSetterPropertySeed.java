package org.glassfish.jaxb.runtime.v2.model.impl;

import java.beans.Introspector;
import java.lang.annotation.Annotation;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.Location;

class GetterSetterPropertySeed implements PropertySeed {
   protected final Object getter;
   protected final Object setter;
   private ClassInfoImpl parent;

   GetterSetterPropertySeed(ClassInfoImpl parent, Object getter, Object setter) {
      this.parent = parent;
      this.getter = getter;
      this.setter = setter;
      if (getter == null && setter == null) {
         throw new IllegalArgumentException();
      }
   }

   public Object getRawType() {
      return this.getter != null ? this.parent.nav().getReturnType(this.getter) : this.parent.nav().getMethodParameters(this.setter)[0];
   }

   public Annotation readAnnotation(Class annotation) {
      return this.parent.reader().getMethodAnnotation(annotation, this.getter, this.setter, this);
   }

   public boolean hasAnnotation(Class annotationType) {
      return this.parent.reader().hasMethodAnnotation(annotationType, this.getName(), this.getter, this.setter, this);
   }

   public String getName() {
      return this.getter != null ? this.getName(this.getter) : this.getName(this.setter);
   }

   private String getName(Object m) {
      String seed = this.parent.nav().getMethodName(m);
      String lseed = seed.toLowerCase();
      if (!lseed.startsWith("get") && !lseed.startsWith("set")) {
         return lseed.startsWith("is") ? camelize(seed.substring(2)) : seed;
      } else {
         return camelize(seed.substring(3));
      }
   }

   private static String camelize(String s) {
      return Introspector.decapitalize(s);
   }

   public Locatable getUpstream() {
      return this.parent;
   }

   public Location getLocation() {
      return this.getter != null ? this.parent.nav().getMethodLocation(this.getter) : this.parent.nav().getMethodLocation(this.setter);
   }
}
