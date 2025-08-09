package org.datanucleus.enhancer.methods;

import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;

public class InitFieldFlags extends ClassMethod {
   public static InitFieldFlags getInstance(ClassEnhancer enhancer) {
      return new InitFieldFlags(enhancer, enhancer.getNamer().getFieldFlagsInitMethodName(), 26, byte[].class, (Object[])null, (String[])null);
   }

   public InitFieldFlags(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      AbstractMemberMetaData[] fields = this.enhancer.getClassMetaData().getManagedMembers();
      this.visitor.visitCode();
      if (fields != null && fields.length > 0) {
         EnhanceUtils.addBIPUSHToMethod(this.visitor, fields.length);
         this.visitor.visitIntInsn(188, 8);

         for(int i = 0; i < fields.length; ++i) {
            this.visitor.visitInsn(89);
            EnhanceUtils.addBIPUSHToMethod(this.visitor, i);
            EnhanceUtils.addBIPUSHToMethod(this.visitor, fields[i].getPersistenceFlags());
            this.visitor.visitInsn(84);
         }

         this.visitor.visitInsn(176);
         this.visitor.visitMaxs(4, 0);
      } else {
         this.visitor.visitInsn(3);
         this.visitor.visitIntInsn(188, 8);
         this.visitor.visitInsn(176);
         this.visitor.visitMaxs(1, 0);
      }

      this.visitor.visitEnd();
   }
}
