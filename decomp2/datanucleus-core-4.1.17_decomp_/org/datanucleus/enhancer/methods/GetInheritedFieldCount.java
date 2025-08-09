package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.metadata.ClassMetaData;

public class GetInheritedFieldCount extends ClassMethod {
   public static GetInheritedFieldCount getInstance(ClassEnhancer enhancer) {
      return new GetInheritedFieldCount(enhancer, enhancer.getNamer().getGetInheritedFieldCountMethodName(), 12, Integer.TYPE, (Object[])null, (String[])null);
   }

   public GetInheritedFieldCount(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      ClassMetaData cmd = this.enhancer.getClassMetaData();
      String persistableSuperclass = cmd.getPersistableSuperclass();
      this.visitor.visitCode();
      if (persistableSuperclass != null && persistableSuperclass.length() > 0) {
         this.visitor.visitMethodInsn(184, persistableSuperclass.replace('.', '/'), this.getNamer().getGetManagedFieldCountMethodName(), "()I");
         this.visitor.visitInsn(172);
         this.visitor.visitMaxs(1, 0);
      } else {
         this.visitor.visitInsn(3);
         this.visitor.visitInsn(172);
         this.visitor.visitMaxs(1, 0);
      }

      this.visitor.visitEnd();
   }
}
