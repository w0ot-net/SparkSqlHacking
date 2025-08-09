package org.glassfish.jaxb.runtime.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.namespace.QName;

public final class TypeReference {
   public final QName tagName;
   public final Type type;
   public final Annotation[] annotations;

   public TypeReference(QName tagName, Type type, Annotation... annotations) {
      if (tagName != null && type != null && annotations != null) {
         this.tagName = new QName(tagName.getNamespaceURI().intern(), tagName.getLocalPart().intern(), tagName.getPrefix());
         this.type = type;
         this.annotations = annotations;
      } else {
         String nullArgs = "";
         if (tagName == null) {
            nullArgs = "tagName";
         }

         if (type == null) {
            nullArgs = nullArgs + (nullArgs.length() > 0 ? ", type" : "type");
         }

         if (annotations == null) {
            nullArgs = nullArgs + (nullArgs.length() > 0 ? ", annotations" : "annotations");
         }

         Messages.ARGUMENT_CANT_BE_NULL.format(nullArgs);
         throw new IllegalArgumentException(Messages.ARGUMENT_CANT_BE_NULL.format(nullArgs));
      }
   }

   public Annotation get(Class annotationType) {
      for(Annotation a : this.annotations) {
         if (a.annotationType() == annotationType) {
            return (Annotation)annotationType.cast(a);
         }
      }

      return null;
   }

   public TypeReference toItemType() {
      Type base = (Type)Utils.REFLECTION_NAVIGATOR.getBaseClass(this.type, Collection.class);
      return base == null ? this : new TypeReference(this.tagName, (Type)Utils.REFLECTION_NAVIGATOR.getTypeArgument(base, 0), new Annotation[0]);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         TypeReference that = (TypeReference)o;
         if (!Arrays.equals(this.annotations, that.annotations)) {
            return false;
         } else {
            return !this.tagName.equals(that.tagName) ? false : this.type.equals(that.type);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.tagName.hashCode();
      result = 31 * result + this.type.hashCode();
      result = 31 * result + Arrays.hashCode(this.annotations);
      return result;
   }
}
