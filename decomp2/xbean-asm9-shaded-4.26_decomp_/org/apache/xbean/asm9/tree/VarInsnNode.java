package org.apache.xbean.asm9.tree;

import java.util.Map;
import org.apache.xbean.asm9.MethodVisitor;

public class VarInsnNode extends AbstractInsnNode {
   public int var;

   public VarInsnNode(int opcode, int varIndex) {
      super(opcode);
      this.var = varIndex;
   }

   public void setOpcode(int opcode) {
      this.opcode = opcode;
   }

   public int getType() {
      return 2;
   }

   public void accept(MethodVisitor methodVisitor) {
      methodVisitor.visitVarInsn(this.opcode, this.var);
      this.acceptAnnotations(methodVisitor);
   }

   public AbstractInsnNode clone(Map clonedLabels) {
      return (new VarInsnNode(this.opcode, this.var)).cloneAnnotations(this);
   }
}
