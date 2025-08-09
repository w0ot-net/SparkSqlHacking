package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;

public class InitPersistableSuperclass extends ClassMethod {
   public static InitPersistableSuperclass getInstance(ClassEnhancer enhancer) {
      return new InitPersistableSuperclass(enhancer, enhancer.getNamer().getPersistableSuperclassInitMethodName(), 10, Class.class, (Object[])null, (String[])null);
   }

   public InitPersistableSuperclass(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      String pcSuperclassName = this.enhancer.getClassMetaData().getPersistableSuperclass();
      if (pcSuperclassName != null) {
         this.visitor.visitLdcInsn(pcSuperclassName);
         this.visitor.visitMethodInsn(184, this.getClassEnhancer().getASMClassName(), this.getNamer().getLoadClassMethodName(), "(Ljava/lang/String;)Ljava/lang/Class;");
      } else {
         this.visitor.visitInsn(1);
      }

      this.visitor.visitInsn(176);
      this.visitor.visitMaxs(1, 0);
      this.visitor.visitEnd();
   }
}
