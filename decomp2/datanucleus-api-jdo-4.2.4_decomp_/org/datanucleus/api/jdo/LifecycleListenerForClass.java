package org.datanucleus.api.jdo;

import javax.jdo.listener.InstanceLifecycleListener;

public class LifecycleListenerForClass {
   private final Class[] classes;
   private final InstanceLifecycleListener listener;

   public LifecycleListenerForClass(InstanceLifecycleListener listener, Class[] classes) {
      this.classes = classes;
      this.listener = listener;
   }

   public InstanceLifecycleListener getListener() {
      return this.listener;
   }

   public Class[] getClasses() {
      return this.classes;
   }

   public boolean forClass(Class cls) {
      if (this.classes == null) {
         return true;
      } else {
         for(int i = 0; i < this.classes.length; ++i) {
            if (this.classes[i].isAssignableFrom(cls)) {
               return true;
            }
         }

         return false;
      }
   }

   LifecycleListenerForClass mergeClasses(Class[] extraClasses) {
      if (this.classes == null) {
         return this;
      } else if (extraClasses == null) {
         return new LifecycleListenerForClass(this.listener, (Class[])null);
      } else {
         Class[] allClasses = new Class[this.classes.length + extraClasses.length];
         System.arraycopy(this.classes, 0, allClasses, 0, this.classes.length);
         System.arraycopy(extraClasses, 0, allClasses, this.classes.length, extraClasses.length);
         return new LifecycleListenerForClass(this.listener, allClasses);
      }
   }

   static Class[] canonicaliseClasses(Class[] classes) {
      if (classes == null) {
         return null;
      } else {
         int count = 0;

         for(Class c : classes) {
            if (c == null) {
               ++count;
            }
         }

         Class[] result = new Class[classes.length - count];
         int pos = 0;

         for(Class c : classes) {
            if (c != null) {
               result[pos++] = c;
            }
         }

         return result;
      }
   }
}
