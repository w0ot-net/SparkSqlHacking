package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.util.ClassUtils;

public class NewObjectIdInstance1 extends ClassMethod {
   public static NewObjectIdInstance1 getInstance(ClassEnhancer enhancer) {
      return new NewObjectIdInstance1(enhancer, enhancer.getNamer().getNewObjectIdInstanceMethodName(), 1, Object.class, (Object[])null, (String[])null);
   }

   public NewObjectIdInstance1(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      ClassMetaData cmd = this.enhancer.getClassMetaData();
      if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         if (!cmd.isInstantiable()) {
            this.visitor.visitTypeInsn(187, this.getClassEnhancer().getNamer().getFatalInternalExceptionAsmClassName());
            this.visitor.visitInsn(89);
            this.visitor.visitLdcInsn("This class has no identity");
            this.visitor.visitMethodInsn(183, this.getClassEnhancer().getNamer().getFatalInternalExceptionAsmClassName(), "<init>", "(Ljava/lang/String;)V");
            this.visitor.visitInsn(191);
            Label endLabel = new Label();
            this.visitor.visitLabel(endLabel);
            this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
            this.visitor.visitMaxs(3, 1);
         } else {
            String objectIdClass = cmd.getObjectidClass();
            int[] pkFieldNums = cmd.getPKMemberPositions();
            if (IdentityUtils.isSingleFieldIdentityClass(objectIdClass)) {
               String ACN_objectIdClass = objectIdClass.replace('.', '/');
               AbstractMemberMetaData fmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[0]);
               this.visitor.visitTypeInsn(187, ACN_objectIdClass);
               this.visitor.visitInsn(89);
               this.visitor.visitVarInsn(25, 0);
               this.visitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
               this.visitor.visitVarInsn(25, 0);
               if (fmd instanceof PropertyMetaData) {
                  this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetMethodPrefixMethodName() + fmd.getName(), "()" + Type.getDescriptor(fmd.getType()));
               } else {
                  this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), fmd.getName(), Type.getDescriptor(fmd.getType()));
               }

               Class primitiveType = ClassUtils.getPrimitiveTypeForType(fmd.getType());
               if (primitiveType != null) {
                  this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "(Ljava/lang/Class;" + Type.getDescriptor(fmd.getType()) + ")V");
               } else {
                  this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "(Ljava/lang/Class;" + this.getNamer().getTypeDescriptorForSingleFieldIdentityGetKey(objectIdClass) + ")V");
               }

               this.visitor.visitInsn(176);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitMaxs(4, 1);
            } else {
               String ACN_objectIdClass = objectIdClass.replace('.', '/');
               this.visitor.visitTypeInsn(187, ACN_objectIdClass);
               this.visitor.visitInsn(89);
               this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "()V");
               this.visitor.visitInsn(176);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitMaxs(2, 1);
            }
         }
      } else {
         this.visitor.visitInsn(1);
         this.visitor.visitInsn(176);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitMaxs(1, 1);
      }

      this.visitor.visitEnd();
   }
}
