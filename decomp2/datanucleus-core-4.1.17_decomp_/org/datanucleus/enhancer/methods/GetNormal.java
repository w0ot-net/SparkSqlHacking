package org.datanucleus.enhancer.methods;

import org.datanucleus.asm.Label;
import org.datanucleus.asm.Type;
import org.datanucleus.enhancer.ClassEnhancer;
import org.datanucleus.enhancer.ClassMethod;
import org.datanucleus.enhancer.EnhanceUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;

public class GetNormal extends ClassMethod {
   protected AbstractMemberMetaData fmd;

   public GetNormal(ClassEnhancer enhancer, AbstractMemberMetaData fmd) {
      super(enhancer, enhancer.getNamer().getGetMethodPrefixMethodName() + fmd.getName(), (fmd.isPublic() ? 1 : 0) | (fmd.isProtected() ? 4 : 0) | (fmd.isPrivate() ? 2 : 0) | 8, fmd.getType(), (Object[])null, (String[])null);
      this.argTypes = new Class[]{this.getClassEnhancer().getClassBeingEnhanced()};
      this.argNames = new String[]{"objPC"};
      this.fmd = fmd;
   }

   public void execute() {
      this.visitor.visitCode();
      String fieldTypeDesc = Type.getDescriptor(this.fmd.getType());
      Label startLabel = new Label();
      this.visitor.visitLabel(startLabel);
      this.visitor.visitVarInsn(25, 0);
      this.visitor.visitFieldInsn(180, this.getClassEnhancer().getASMClassName(), this.fmd.getName(), fieldTypeDesc);
      EnhanceUtils.addReturnForType(this.visitor, (Class)this.returnType);
      Label endLabel = new Label();
      this.visitor.visitLabel(endLabel);
      this.visitor.visitLocalVariable(this.argNames[0], this.getClassEnhancer().getClassDescriptor(), (String)null, startLabel, endLabel, 0);
      this.visitor.visitMaxs(1, 1);
      this.visitor.visitEnd();
   }
}
