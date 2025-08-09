package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class ProvideField extends ClassMethod {
   public static ProvideField getInstance(ClassEnhancer enhancer) {
      return new ProvideField(enhancer, enhancer.getNamer().getProvideFieldMethodName(), 1, (Object)null, new Class[]{Integer.TYPE}, new String[]{"index"});
   }

   public ProvideField(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      AbstractMemberMetaData[] fields = this.enhancer.getClassMetaData().getManagedMembers();
      String pcSuperclassName = this.enhancer.getClassMetaData().getPersistableSuperclass();
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      if (pcSuperclassName != null) {
         if (fields.length > 0) {
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
            Label l1 = new Label();
            this.visitor.visitJumpInsn(199, l1);
            this.visitor.visitTypeInsn(187, "java/lang/IllegalStateException");
            this.visitor.visitInsn(89);
            this.visitor.visitLdcInsn("state manager is null");
            this.visitor.visitMethodInsn(183, "java/lang/IllegalStateException", "<init>", "(Ljava/lang/String;)V");
            this.visitor.visitInsn(191);
            this.visitor.visitLabel(l1);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitVarInsn(21, 1);
            this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
            this.visitor.visitInsn(100);
            Label[] fieldOptions = new Label[fields.length];

            for(int i = 0; i < fields.length; ++i) {
               fieldOptions[i] = new Label();
            }

            Label defaultLabel = new Label();
            Label endSwitchLabel = new Label();
            this.visitor.visitTableSwitchInsn(0, fields.length - 1, defaultLabel, fieldOptions);

            for(int i = 0; i < fields.length; ++i) {
               this.visitor.visitLabel(fieldOptions[i]);
               this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
               this.visitor.visitVarInsn(25, 0);
               this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
               this.visitor.visitVarInsn(25, 0);
               this.visitor.visitVarInsn(21, 1);
               this.visitor.visitVarInsn(25, 0);
               if (fields[i] instanceof PropertyMetaData) {
                  this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetMethodPrefixMethodName() + fields[i].getName(), "()" + Type.getDescriptor(fields[i].getType()));
               } else {
                  this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
               }

               this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "provided" + EnhanceUtils.getTypeNameForPersistableMethod(fields[i].getType()) + "Field", "(" + this.getNamer().getPersistableDescriptor() + "I" + EnhanceUtils.getTypeDescriptorForEnhanceMethod(fields[i].getType()) + ")V");
               this.visitor.visitJumpInsn(167, endSwitchLabel);
            }

            this.visitor.visitLabel(defaultLabel);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(21, 1);
            this.visitor.visitMethodInsn(183, pcSuperclassName.replace('.', '/'), this.getNamer().getProvideFieldMethodName(), "(I)V");
            this.visitor.visitLabel(endSwitchLabel);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitInsn(177);
            Label endLabel = new Label();
            this.visitor.visitLabel(endLabel);
            this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
            this.visitor.visitLocalVariable(this.argNames[0], "I", (String)null, startLabel, endLabel, 1);
            this.visitor.visitMaxs(4, 2);
         } else {
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(21, 1);
            this.visitor.visitMethodInsn(183, pcSuperclassName.replace('.', '/'), this.getNamer().getProvideFieldMethodName(), "(I)V");
            this.visitor.visitInsn(177);
            Label endLabel = new Label();
            this.visitor.visitLabel(endLabel);
            this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
            this.visitor.visitLocalVariable(this.argNames[0], "I", (String)null, startLabel, endLabel, 1);
            this.visitor.visitMaxs(2, 2);
         }
      } else if (fields.length > 0) {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
         Label l1 = new Label();
         this.visitor.visitJumpInsn(199, l1);
         this.visitor.visitTypeInsn(187, "java/lang/IllegalStateException");
         this.visitor.visitInsn(89);
         this.visitor.visitLdcInsn("state manager is null");
         this.visitor.visitMethodInsn(183, "java/lang/IllegalStateException", "<init>", "(" + EnhanceUtils.CD_String + ")V");
         this.visitor.visitInsn(191);
         this.visitor.visitLabel(l1);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitVarInsn(21, 1);
         Label[] fieldOptions = new Label[fields.length];

         for(int i = 0; i < fields.length; ++i) {
            fieldOptions[i] = new Label();
         }

         Label defaultLabel = new Label();
         Label endSwitchLabel = new Label();
         this.visitor.visitTableSwitchInsn(0, fields.length - 1, defaultLabel, fieldOptions);

         for(int i = 0; i < fields.length; ++i) {
            this.visitor.visitLabel(fieldOptions[i]);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), this.getNamer().getStateManagerDescriptor());
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(21, 1);
            this.visitor.visitVarInsn(25, 0);
            if (fields[i] instanceof PropertyMetaData) {
               this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetMethodPrefixMethodName() + fields[i].getName(), "()" + Type.getDescriptor(fields[i].getType()));
            } else {
               this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
            }

            this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), "provided" + EnhanceUtils.getTypeNameForPersistableMethod(fields[i].getType()) + "Field", "(" + this.getNamer().getPersistableDescriptor() + "I" + EnhanceUtils.getTypeDescriptorForEnhanceMethod(fields[i].getType()) + ")V");
            this.visitor.visitJumpInsn(167, endSwitchLabel);
         }

         this.visitor.visitLabel(defaultLabel);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
         this.visitor.visitInsn(89);
         this.visitor.visitTypeInsn(187, "java/lang/StringBuffer");
         this.visitor.visitInsn(89);
         this.visitor.visitLdcInsn("out of field index :");
         this.visitor.visitMethodInsn(183, "java/lang/StringBuffer", "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitVarInsn(21, 1);
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(I)Ljava/lang/StringBuffer;");
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;");
         this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitInsn(191);
         this.visitor.visitLabel(endSwitchLabel);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitInsn(177);
         Label l7 = new Label();
         this.visitor.visitLabel(l7);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, l7, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "I", (String)null, startLabel, l7, 1);
         this.visitor.visitMaxs(5, 2);
      } else {
         this.visitor.visitInsn(177);
         Label endLabel = new Label();
         this.visitor.visitLabel(endLabel);
         this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
         this.visitor.visitLocalVariable(this.argNames[0], "I", (String)null, startLabel, endLabel, 1);
         this.visitor.visitMaxs(0, 2);
      }

      this.visitor.visitEnd();
   }
}
