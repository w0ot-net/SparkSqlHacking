package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.util.ClassUtils;

public class NewObjectIdInstance2 extends ClassMethod {
   public static NewObjectIdInstance2 getInstance(ClassEnhancer enhancer) {
      return new NewObjectIdInstance2(enhancer, enhancer.getNamer().getNewObjectIdInstanceMethodName(), 1, Object.class, new Class[]{Object.class}, new String[]{"key"});
   }

   public NewObjectIdInstance2(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
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
            this.visitor.visitLocalVariable("key", "Ljava/lang/Object;", (String)null, startLabel, endLabel, 1);
            this.visitor.visitMaxs(3, 2);
         } else {
            String objectIdClass = cmd.getObjectidClass();
            int[] pkFieldNums = cmd.getPKMemberPositions();
            if (IdentityUtils.isSingleFieldIdentityClass(objectIdClass)) {
               String ACN_objectIdClass = objectIdClass.replace('.', '/');
               AbstractMemberMetaData fmd = this.enhancer.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[0]);
               this.visitor.visitVarInsn(25, 1);
               Label l1 = new Label();
               this.visitor.visitJumpInsn(199, l1);
               this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
               this.visitor.visitInsn(89);
               this.visitor.visitLdcInsn("key is null");
               this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(191);
               this.visitor.visitLabel(l1);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(193, "java/lang/String");
               Label l3 = new Label();
               this.visitor.visitJumpInsn(154, l3);
               this.visitor.visitTypeInsn(187, ACN_objectIdClass);
               this.visitor.visitInsn(89);
               this.visitor.visitVarInsn(25, 0);
               this.visitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
               this.visitor.visitVarInsn(25, 1);
               String objectTypeInConstructor = EnhanceUtils.getASMClassNameForSingleFieldIdentityConstructor(fmd.getType());
               Class primitiveType = ClassUtils.getPrimitiveTypeForType(fmd.getType());
               if (primitiveType != null) {
                  objectTypeInConstructor = fmd.getTypeName().replace('.', '/');
               }

               if (!objectIdClass.equals(this.getNamer().getObjectIdentityClass().getName()) || primitiveType != null) {
                  this.visitor.visitTypeInsn(192, objectTypeInConstructor);
               }

               this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "(Ljava/lang/Class;L" + objectTypeInConstructor + ";)V");
               this.visitor.visitInsn(176);
               this.visitor.visitLabel(l3);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitTypeInsn(187, ACN_objectIdClass);
               this.visitor.visitInsn(89);
               this.visitor.visitVarInsn(25, 0);
               this.visitor.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(192, "java/lang/String");
               this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "(Ljava/lang/Class;Ljava/lang/String;)V");
               this.visitor.visitInsn(176);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitLocalVariable("key", EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 1);
               this.visitor.visitMaxs(4, 2);
            } else {
               String ACN_objectIdClass = objectIdClass.replace('.', '/');
               this.visitor.visitTypeInsn(187, ACN_objectIdClass);
               this.visitor.visitInsn(89);
               this.visitor.visitVarInsn(25, 1);
               this.visitor.visitTypeInsn(192, "java/lang/String");
               this.visitor.visitMethodInsn(183, ACN_objectIdClass, "<init>", "(Ljava/lang/String;)V");
               this.visitor.visitInsn(176);
               Label endLabel = new Label();
               this.visitor.visitLabel(endLabel);
               this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
               this.visitor.visitLocalVariable(this.argNames[0], EnhanceUtils.CD_Object, (String)null, startLabel, endLabel, 1);
               this.visitor.visitMaxs(3, 2);
               this.visitor.visitEnd();
            }
         }
      } else {
         this.visitor.visitInsn(1);
         this.visitor.visitInsn(176);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "Ljava/lang/Object;", (String)null, startLabel, endLabel, 1);
         this.visitor.visitMaxs(1, 2);
      }

      this.visitor.visitEnd();
   }
}
