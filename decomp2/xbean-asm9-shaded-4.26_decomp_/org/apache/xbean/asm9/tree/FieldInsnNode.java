package org.apache.xbean.asm9.tree;

import java.util.Map;
import org.apache.xbean.asm9.MethodVisitor;

public class FieldInsnNode extends AbstractInsnNode {
   public String owner;
   public String name;
   public String desc;

   public FieldInsnNode(int opcode, String owner, String name, String descriptor) {
      super(opcode);
      this.owner = owner;
      this.name = name;
      this.desc = descriptor;
   }

   public void setOpcode(int opcode) {
      this.opcode = opcode;
   }

   public int getType() {
      return 4;
   }

   public void accept(MethodVisitor methodVisitor) {
      methodVisitor.visitFieldInsn(this.opcode, this.owner, this.name, this.desc);
      this.acceptAnnotations(methodVisitor);
   }

   public AbstractInsnNode clone(Map clonedLabels) {
      return (new FieldInsnNode(this.opcode, this.owner, this.name, this.desc)).cloneAnnotations(this);
   }
}
