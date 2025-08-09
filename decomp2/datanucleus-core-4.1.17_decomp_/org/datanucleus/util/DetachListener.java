package org.datanucleus.util;

import org.datanucleus.exceptions.NucleusUserException;

public abstract class DetachListener {
   private static DetachListener instance;

   public static DetachListener getInstance() {
      if (instance == null) {
         synchronized(DetachListener.class) {
            if (instance == null) {
               instance = new DetachListener() {
                  public void undetachedFieldAccess(Object instance, String fieldName) {
                     throw new NucleusUserException("You have just attempted to access field \"" + fieldName + "\" yet this field was not detached when you detached the object. Either dont access this field, or detach it when detaching the object.");
                  }
               };
            }
         }
      }

      return instance;
   }

   public static void setInstance(DetachListener instance) {
      synchronized(DetachListener.class) {
         DetachListener.instance = instance;
      }
   }

   public abstract void undetachedFieldAccess(Object var1, String var2);
}
