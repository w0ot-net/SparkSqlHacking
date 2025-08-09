package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;

public class IsNew extends IsXXX {
   public static IsNew getInstance(ClassEnhancer enhancer) {
      return new IsNew(enhancer, enhancer.getNamer().getIsNewMethodName(), 17, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsNew(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   protected String getStateManagerIsMethod() {
      return "isNew";
   }
}
