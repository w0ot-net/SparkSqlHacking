package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;

public class SetNormal extends ClassMethod {
   protected AbstractMemberMetaData fmd;

   public SetNormal(ClassEnhancer enhancer, AbstractMemberMetaData fmd) {
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
      EnhanceUtils.addLoadForType(this.visitor, this.fmd.getType(), 1);
      this.visitor.visitFieldInsn(181, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      this.visitor.visitInsn(177);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable(this.argNames[0], this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitLocalVariable(this.argNames[1], fieldTypeDesc, (String)null, startLabel, endLabel, 1);
      this.visitor.visitMaxs(2, 2);
      this.visitor.visitEnd();
   }
}
