package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ParameterizedTypeImpl implements ParameterizedType {
   private final Type rawType;
   private final Type[] actualTypeArguments;

   public ParameterizedTypeImpl(Type rawType, Type... actualTypeArguments) {
      this.rawType = rawType;
      this.actualTypeArguments = actualTypeArguments;
   }

   public Type[] getActualTypeArguments() {
      return this.actualTypeArguments;
   }

   public Type getRawType() {
      return this.rawType;
   }

   public Type getOwnerType() {
      return null;
   }

   public int hashCode() {
      int retVal = Arrays.hashCode(this.actualTypeArguments);
      return this.rawType == null ? retVal : retVal ^ this.rawType.hashCode();
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (!(o instanceof ParameterizedType)) {
         return false;
      } else {
         ParameterizedType other = (ParameterizedType)o;
         if (!this.rawType.equals(other.getRawType())) {
            return false;
         } else {
            Type[] otherActuals = other.getActualTypeArguments();
            if (otherActuals.length != this.actualTypeArguments.length) {
               return false;
            } else {
               for(int lcv = 0; lcv < otherActuals.length; ++lcv) {
                  if (!this.actualTypeArguments[lcv].equals(otherActuals[lcv])) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   public String toString() {
      return Pretty.pType(this);
   }
}
