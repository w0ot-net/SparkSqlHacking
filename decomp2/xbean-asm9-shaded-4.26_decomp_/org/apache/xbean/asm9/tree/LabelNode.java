package org.apache.xbean.asm9.tree;

import java.util.Map;
import org.apache.xbean.asm9.Label;
import org.apache.xbean.asm9.MethodVisitor;

public class LabelNode extends AbstractInsnNode {
   private Label value;

   public LabelNode() {
      super(-1);
   }

   public LabelNode(Label label) {
      super(-1);
      this.value = label;
   }

   public int getType() {
      return 8;
   }

   public Label getLabel() {
      if (this.value == null) {
         this.value = new Label();
      }

      return this.value;
   }

   public void accept(MethodVisitor methodVisitor) {
      methodVisitor.visitLabel(this.getLabel());
   }

   public AbstractInsnNode clone(Map clonedLabels) {
      return (AbstractInsnNode)clonedLabels.get(this);
   }

   public void resetLabel() {
      this.value = null;
   }
}
