package org.sparkproject.jpmml.model;

import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;

public class PMMLObjectKey {
   private PMMLObject object = null;

   public PMMLObjectKey(PMMLObject object) {
      this.setObject(object);
   }

   public int hashCode() {
      return ReflectionUtil.hashCode(this.getObject());
   }

   public boolean equals(Object object) {
      if (object instanceof PMMLObjectKey) {
         PMMLObjectKey that = (PMMLObjectKey)object;
         return ReflectionUtil.equals(this.getObject(), that.getObject());
      } else {
         return false;
      }
   }

   public PMMLObject getObject() {
      return this.object;
   }

   private void setObject(PMMLObject object) {
      this.object = (PMMLObject)Objects.requireNonNull(object);
   }
}
