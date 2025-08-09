package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;

public class IsPersistent extends IsXXX {
   public static IsPersistent getInstance(ClassEnhancer enhancer) {
      return new IsPersistent(enhancer, enhancer.getNamer().getIsPersistentMethodName(), 17, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsPersistent(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   protected String getStateManagerIsMethod() {
      return "isPersistent";
   }
}
