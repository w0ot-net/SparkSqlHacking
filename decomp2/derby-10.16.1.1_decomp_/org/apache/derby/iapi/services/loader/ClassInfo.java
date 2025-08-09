package org.apache.derby.iapi.services.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassInfo implements InstanceGetter {
   private static final Class[] noParameters = new Class[0];
   private static final Object[] noArguments = new Object[0];
   private final Class clazz;
   private boolean useConstructor = true;
   private Constructor noArgConstructor;

   public ClassInfo(Class var1) {
      this.clazz = var1;
   }

   public final String getClassName() {
      return this.clazz.getName();
   }

   public final Class getClassObject() {
      return this.clazz;
   }

   public Object getNewInstance() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
      if (!this.useConstructor) {
         return this.clazz.getConstructor().newInstance();
      } else {
         if (this.noArgConstructor == null) {
            try {
               this.noArgConstructor = this.clazz.getConstructor(noParameters);
            } catch (NoSuchMethodException var3) {
               this.useConstructor = false;
               return this.getNewInstance();
            }
         }

         try {
            return this.noArgConstructor.newInstance(noArguments);
         } catch (IllegalArgumentException var2) {
            return null;
         }
      }
   }
}
