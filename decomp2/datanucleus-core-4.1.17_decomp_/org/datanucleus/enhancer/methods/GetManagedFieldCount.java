package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.ClassMetaData;

public class GetManagedFieldCount extends ClassMethod {
   public static GetManagedFieldCount getInstance(ClassEnhancer enhancer) {
      return new GetManagedFieldCount(enhancer, enhancer.getNamer().getGetManagedFieldCountMethodName(), 12, Integer.TYPE, (Object[])null, (String[])null);
   }

   public GetManagedFieldCount(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      ClassMetaData cmd = this.enhancer.getClassMetaData();
      String persistableSuperclass = cmd.getPersistableSuperclass();
      this.visitor.visitCode();
      if (persistableSuperclass != null && persistableSuperclass.length() > 0) {
         EnhanceUtils.addBIPUSHToMethod(this.visitor, cmd.getNoOfManagedMembers());
         this.visitor.visitMethodInsn(184, persistableSuperclass.replace('.', '/'), this.methodName, "()I");
         this.visitor.visitInsn(96);
         this.visitor.visitInsn(172);
         this.visitor.visitMaxs(2, 0);
      } else {
         EnhanceUtils.addBIPUSHToMethod(this.visitor, cmd.getNoOfManagedMembers());
         this.visitor.visitInsn(172);
         this.visitor.visitMaxs(1, 0);
      }

      this.visitor.visitEnd();
   }
}
