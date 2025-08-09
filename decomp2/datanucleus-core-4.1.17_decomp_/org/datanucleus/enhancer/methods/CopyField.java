package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.PropertyMetaData;

public class CopyField extends ClassMethod {
   public static CopyField getInstance(ClassEnhancer enhancer) {
      return new CopyField(enhancer, enhancer.getNamer().getCopyFieldMethodName(), 20, (Object)null, new Class[]{enhancer.getClassBeingEnhanced(), Integer.TYPE}, new String[]{"obj", "index"});
   }

   public CopyField(ClassEnhancer enhancer, String name, int access, Object returnType, Object[] argTypes, String[] argNames) {
      super(enhancer, name, access, returnType, argTypes, argNames);
   }

   public void execute() {
      AbstractMemberMetaData[] fields = this.enhancer.getClassMetaData().getManagedMembers();
      String pcSuperclassName = this.enhancer.getClassMetaData().getPersistableSuperclass();
      this.visitor.visitCode();
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      if (pcSuperclassName != null) {
         Class supercls = this.enhancer.getClassLoaderResolver().classForName(pcSuperclassName);
         String superclsDescriptor = Type.getDescriptor(supercls);
         if (fields.length > 0) {
            this.visitor.visitVarInsn(21, 2);
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
               this.visitor.visitVarInsn(25, 1);
               if (fields[i] instanceof PropertyMetaData) {
                  this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetMethodPrefixMethodName() + fields[i].getName(), "()" + Type.getDescriptor(fields[i].getType()));
                  this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getSetMethodPrefixMethodName() + fields[i].getName(), "(" + Type.getDescriptor(fields[i].getType()) + ")V");
               } else {
                  this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
                  this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
               }

               this.visitor.visitJumpInsn(167, endSwitchLabel);
            }

            this.visitor.visitLabel(defaultLabel);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(25, 1);
            this.visitor.visitVarInsn(21, 2);
            this.visitor.visitMethodInsn(183, pcSuperclassName.replace('.', '/'), this.getNamer().getCopyFieldMethodName(), "(" + superclsDescriptor + "I)V");
            this.visitor.visitLabel(endSwitchLabel);
            this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
            this.visitor.visitInsn(177);
         } else {
            this.visitor.visitVarInsn(25, 0);
            this.visitor.visitVarInsn(25, 1);
            this.visitor.visitVarInsn(21, 2);
            this.visitor.visitMethodInsn(183, pcSuperclassName.replace('.', '/'), this.getNamer().getCopyFieldMethodName(), "(" + superclsDescriptor + "I)V");
            this.visitor.visitInsn(177);
         }
      } else if (fields.length > 0) {
         this.visitor.visitVarInsn(21, 2);
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
            this.visitor.visitVarInsn(25, 1);
            if (fields[i] instanceof PropertyMetaData) {
               this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getGetMethodPrefixMethodName() + fields[i].getName(), "()" + Type.getDescriptor(fields[i].getType()));
               this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getSetMethodPrefixMethodName() + fields[i].getName(), "(" + Type.getDescriptor(fields[i].getType()) + ")V");
            } else {
               this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
               this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), fields[i].getName(), Type.getDescriptor(fields[i].getType()));
            }

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
         this.visitor.visitVarInsn(21, 2);
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(I)Ljava/lang/StringBuffer;");
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;");
         this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitInsn(191);
         this.visitor.visitLabel(endSwitchLabel);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
         this.visitor.visitInsn(177);
      } else {
         this.visitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
         this.visitor.visitInsn(89);
         this.visitor.visitTypeInsn(187, "java/lang/StringBuffer");
         this.visitor.visitInsn(89);
         this.visitor.visitLdcInsn("out of field index :");
         this.visitor.visitMethodInsn(183, "java/lang/StringBuffer", "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitVarInsn(21, 2);
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(I)Ljava/lang/StringBuffer;");
         this.visitor.visitMethodInsn(182, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;");
         this.visitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
         this.visitor.visitInsn(191);
      }

      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable("this", this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitLocalVariable(this.argNames[0], this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 1);
      this.visitor.visitLocalVariable(this.argNames[1], "I", (String)null, startLabel, endLabel, 2);
      if (pcSuperclassName != null) {
         this.visitor.visitMaxs(3, 3);
      } else {
         this.visitor.visitMaxs(5, 3);
      }

      this.visitor.visitEnd();
   }
}
