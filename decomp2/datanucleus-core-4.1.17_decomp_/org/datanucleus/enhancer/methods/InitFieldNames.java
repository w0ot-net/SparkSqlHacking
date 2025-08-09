package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;

public class InitFieldNames extends ClassMethod {
   public static InitFieldNames getInstance(ClassEnhancer enhancer) {
      return new InitFieldNames(enhancer, enhancer.getNamer().getFieldNamesInitMethodName(), 26, String[].class, (Object[])null, (String[])null);
   }

   public InitFieldNames(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      AbstractMemberMetaData[] fields = this.enhancer.getClassMetaData().getManagedMembers();
      this.visitor.visitCode();
      if (fields != null && fields.length > 0) {
         EnhanceUtils.addBIPUSHToMethod(this.visitor, fields.length);
         this.visitor.visitTypeInsn(189, "java/lang/String");

         for(int i = 0; i < fields.length; ++i) {
            this.visitor.visitInsn(89);
            EnhanceUtils.addBIPUSHToMethod(this.visitor, i);
            this.visitor.visitLdcInsn(fields[i].getName());
            this.visitor.visitInsn(83);
         }

         this.visitor.visitInsn(176);
         this.visitor.visitMaxs(4, 0);
      } else {
         this.visitor.visitInsn(3);
         this.visitor.visitTypeInsn(189, "java/lang/String");
         this.visitor.visitInsn(176);
         this.visitor.visitMaxs(1, 0);
      }

      this.visitor.visitEnd();
   }
}
