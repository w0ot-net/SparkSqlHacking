package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;

public class IsDeleted extends IsXXX {
   public static IsDeleted getInstance(ClassEnhancer enhancer) {
      return new IsDeleted(enhancer, enhancer.getNamer().getIsDeletedMethodName(), 17, Boolean.TYPE, (Object[])null, (String[])null);
   }

   public IsDeleted(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   protected String getStateManagerIsMethod() {
      return "isDeleted";
   }
}
