package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;

public class SetViaMediate extends ClassMethod {
   protected AbstractMemberMetaData fmd;

   public SetViaMediate(ClassEnhancer enhancer, AbstractMemberMetaData fmd) {
      super(enhancer, enhancer.getNamer().getSetMethodPrefixMethodName() + fmd.getName(), (fmd.isPublic() ? 1 : 0) | (fmd.isProtected() ? 4 : 0) | (fmd.isPrivate() ? 2 : 0) | 8, (Object)null, (Object[])null, (String[])null);
      this.argTypes = new Class[]{this.getClassEnhancer().getClassBeingEnhanced(), fmd.getType()};
      this.argNames = new String[]{"objPC", "val"};
      this.fmd = fmd;
   }

   public void execute() {
      this.visitor.visitCode();
      String fieldTypeDesc = Type.getDescriptor(this.fmd.getType());
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
      Label l1 = new Label();
      this.visitor.visitJumpInsn(199, l1);
      this.visitor.visitVarInsn(25, 0);
      EnhanceUtils.addLoadForType(this.visitor, this.fmd.getType(), 1);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      Label l3 = new Label();
      this.visitor.visitJumpInsn(167, l3);
      this.visitor.visitLabel(l1);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getStateManagerFieldName(), "L" + this.getNamer().getStateManagerAsmClassName() + ";");
      this.visitor.visitVarInsn(25, 0);
      EnhanceUtils.addBIPUSHToMethod(this.visitor, this.fmd.getFieldId());
      if (this.enhancer.getClassMetaData().getPersistableSuperclass() != null) {
         this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
         this.visitor.visitInsn(96);
      }

      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      EnhanceUtils.addLoadForType(this.visitor, this.fmd.getType(), 1);
      String dnMethodName = "set" + EnhanceUtils.getTypeNameForPersistableMethod(this.fmd.getType()) + "Field";
      String argTypeDesc = fieldTypeDesc;
      if (dnMethodName.equals("setObjectField")) {
         argTypeDesc = EnhanceUtils.CD_Object;
      }

      this.visitor.visitMethodInsn(185, this.getNamer().getStateManagerAsmClassName(), dnMethodName, "(L" + this.getNamer().getPersistableAsmClassName() + ";I" + argTypeDesc + argTypeDesc + ")V");
      this.visitor.visitLabel(l3);
      this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      if (this.enhancer.getClassMetaData().isDetachable()) {
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitMethodInsn(182, this.getClassEnhancer().getASMClassName(), this.getNamer().getIsDetachedMethodName(), "()Z");
         Label l6 = new Label();
         this.visitor.visitJumpInsn(153, l6);
         this.visitor.visitVarInsn(25, 0);
         this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.getNamer().getDetachedStateFieldName(), "[Ljava/lang/Object;");
         this.visitor.visitInsn(6);
         this.visitor.visitInsn(50);
         this.visitor.visitTypeInsn(192, "java/util/BitSet");
         EnhanceUtils.addBIPUSHToMethod(this.visitor, this.fmd.getFieldId());
         if (this.enhancer.getClassMetaData().getPersistableSuperclass() != null) {
            this.visitor.visitFieldInsn(178, this.getClassEnhancer().getASMClassName(), this.getNamer().getInheritedFieldCountFieldName(), "I");
            this.visitor.visitInsn(96);
         }

         this.visitor.visitMethodInsn(182, "java/util/BitSet", "set", "(I)V");
         this.visitor.visitLabel(l6);
         this.visitor.visitFrame(3, 0, (Object[])null, 0, (Object[])null);
      }

      this.visitor.visitInsn(177);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable(this.argNames[0], this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitLocalVariable(this.argNames[1], fieldTypeDesc, (String)null, startLabel, endLabel, 1);
      this.visitor.visitMaxs(5, 2);
      this.visitor.visitEnd();
   }
}
