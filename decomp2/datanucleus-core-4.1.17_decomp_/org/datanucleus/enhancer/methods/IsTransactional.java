package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;

public class IsTransactional extends IsXXX {
   public static IsTransactional getInstance(ClassEnhancer enhancer) {
      return new IsTransactional(enhancer, enhancer.getNamer().getIsTransactionalMethodName(), 17, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsTransactional(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   protected String getStateManagerIsMethod() {
      return "isTransactional";
   }
}
