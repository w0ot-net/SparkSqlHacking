package org.apache.xbean.asm9.commons;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.xbean.asm9.Label;
import org.apache.xbean.asm9.MethodVisitor;
import org.apache.xbean.asm9.Opcodes;
import org.apache.xbean.asm9.tree.AbstractInsnNode;
import org.apache.xbean.asm9.tree.InsnList;
import org.apache.xbean.asm9.tree.InsnNode;
import org.apache.xbean.asm9.tree.JumpInsnNode;
import org.apache.xbean.asm9.tree.LabelNode;
import org.apache.xbean.asm9.tree.LocalVariableNode;
import org.apache.xbean.asm9.tree.MethodNode;
import org.apache.xbean.asm9.tree.TryCatchBlockNode;

public class JSRInlinerAdapter extends MethodNode implements Opcodes {
   private final BitSet mainSubroutineInsns;
   private final Map subroutinesInsns;
   final BitSet sharedSubroutineInsns;

   public JSRInlinerAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
      this(589824, methodVisitor, access, name, descriptor, signature, exceptions);
      if (this.getClass() != JSRInlinerAdapter.class) {
         throw new IllegalStateException();
      }
   }

   protected JSRInlinerAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
      super(api, access, name, descriptor, signature, exceptions);
      this.mainSubroutineInsns = new BitSet();
      this.subroutinesInsns = new HashMap();
      this.sharedSubroutineInsns = new BitSet();
      this.mv = methodVisitor;
   }

   public void visitJumpInsn(int opcode, Label label) {
      super.visitJumpInsn(opcode, label);
      LabelNode labelNode = ((JumpInsnNode)this.instructions.getLast()).label;
      if (opcode == 168 && !this.subroutinesInsns.containsKey(labelNode)) {
         this.subroutinesInsns.put(labelNode, new BitSet());
      }

   }

   public void visitEnd() {
      if (!this.subroutinesInsns.isEmpty()) {
         this.findSubroutinesInsns();
         this.emitCode();
      }

      if (this.mv != null) {
         this.accept(this.mv);
      }

   }

   private void findSubroutinesInsns() {
      BitSet visitedInsns = new BitSet();
      this.findSubroutineInsns(0, this.mainSubroutineInsns, visitedInsns);

      for(Map.Entry entry : this.subroutinesInsns.entrySet()) {
         LabelNode jsrLabelNode = (LabelNode)entry.getKey();
         BitSet subroutineInsns = (BitSet)entry.getValue();
         this.findSubroutineInsns(this.instructions.indexOf(jsrLabelNode), subroutineInsns, visitedInsns);
      }

   }

   private void findSubroutineInsns(int startInsnIndex, BitSet subroutineInsns, BitSet visitedInsns) {
      this.findReachableInsns(startInsnIndex, subroutineInsns, visitedInsns);

      boolean applicableHandlerFound;
      do {
         applicableHandlerFound = false;

         for(TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
            int handlerIndex = this.instructions.indexOf(tryCatchBlockNode.handler);
            if (!subroutineInsns.get(handlerIndex)) {
               int startIndex = this.instructions.indexOf(tryCatchBlockNode.start);
               int endIndex = this.instructions.indexOf(tryCatchBlockNode.end);
               int firstSubroutineInsnAfterTryCatchStart = subroutineInsns.nextSetBit(startIndex);
               if (firstSubroutineInsnAfterTryCatchStart >= startIndex && firstSubroutineInsnAfterTryCatchStart < endIndex) {
                  this.findReachableInsns(handlerIndex, subroutineInsns, visitedInsns);
                  applicableHandlerFound = true;
               }
            }
         }
      } while(applicableHandlerFound);

   }

   private void findReachableInsns(int param1, BitSet param2, BitSet param3) {
      // $FF: Couldn't be decompiled
   }

   private void emitCode() {
      LinkedList<Instantiation> worklist = new LinkedList();
      worklist.add(new Instantiation((Instantiation)null, this.mainSubroutineInsns));
      InsnList newInstructions = new InsnList();
      List<TryCatchBlockNode> newTryCatchBlocks = new ArrayList();
      List<LocalVariableNode> newLocalVariables = new ArrayList();

      while(!worklist.isEmpty()) {
         Instantiation instantiation = (Instantiation)worklist.removeFirst();
         this.emitInstantiation(instantiation, worklist, newInstructions, newTryCatchBlocks, newLocalVariables);
      }

      this.instructions = newInstructions;
      this.tryCatchBlocks = newTryCatchBlocks;
      this.localVariables = newLocalVariables;
   }

   private void emitInstantiation(Instantiation instantiation, List worklist, InsnList newInstructions, List newTryCatchBlocks, List newLocalVariables) {
      LabelNode previousLabelNode = null;

      for(int i = 0; i < this.instructions.size(); ++i) {
         AbstractInsnNode insnNode = this.instructions.get(i);
         if (insnNode.getType() == 8) {
            LabelNode labelNode = (LabelNode)insnNode;
            LabelNode clonedLabelNode = instantiation.getClonedLabel(labelNode);
            if (clonedLabelNode != previousLabelNode) {
               newInstructions.add((AbstractInsnNode)clonedLabelNode);
               previousLabelNode = clonedLabelNode;
            }
         } else if (instantiation.findOwner(i) == instantiation) {
            if (insnNode.getOpcode() != 169) {
               if (insnNode.getOpcode() == 168) {
                  LabelNode jsrLabelNode = ((JumpInsnNode)insnNode).label;
                  BitSet subroutineInsns = (BitSet)this.subroutinesInsns.get(jsrLabelNode);
                  Instantiation newInstantiation = new Instantiation(instantiation, subroutineInsns);
                  LabelNode clonedJsrLabelNode = newInstantiation.getClonedLabelForJumpInsn(jsrLabelNode);
                  newInstructions.add((AbstractInsnNode)(new InsnNode(1)));
                  newInstructions.add((AbstractInsnNode)(new JumpInsnNode(167, clonedJsrLabelNode)));
                  newInstructions.add((AbstractInsnNode)newInstantiation.returnLabel);
                  worklist.add(newInstantiation);
               } else {
                  newInstructions.add(insnNode.clone(instantiation));
               }
            } else {
               LabelNode retLabel = null;

               for(Instantiation retLabelOwner = instantiation; retLabelOwner != null; retLabelOwner = retLabelOwner.parent) {
                  if (retLabelOwner.subroutineInsns.get(i)) {
                     retLabel = retLabelOwner.returnLabel;
                  }
               }

               if (retLabel == null) {
                  throw new IllegalArgumentException(stringConcat$0(i));
               }

               newInstructions.add((AbstractInsnNode)(new JumpInsnNode(167, retLabel)));
            }
         }
      }

      for(TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
         LabelNode start = instantiation.getClonedLabel(tryCatchBlockNode.start);
         LabelNode end = instantiation.getClonedLabel(tryCatchBlockNode.end);
         if (start != end) {
            LabelNode handler = instantiation.getClonedLabelForJumpInsn(tryCatchBlockNode.handler);
            if (start == null || end == null || handler == null) {
               throw new AssertionError("Internal error!");
            }

            newTryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, tryCatchBlockNode.type));
         }
      }

      for(LocalVariableNode localVariableNode : this.localVariables) {
         LabelNode start = instantiation.getClonedLabel(localVariableNode.start);
         LabelNode end = instantiation.getClonedLabel(localVariableNode.end);
         if (start != end) {
            newLocalVariables.add(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, start, end, localVariableNode.index));
         }
      }

   }

   // $FF: synthetic method
   private static String stringConcat$0(int var0) {
      return "Instruction #" + var0 + " is a RET not owned by any subroutine";
   }

   private final class Instantiation extends AbstractMap {
      final Instantiation parent;
      final BitSet subroutineInsns;
      final Map clonedLabels;
      final LabelNode returnLabel;

      Instantiation(Instantiation parent, BitSet subroutineInsns) {
         for(Instantiation instantiation = parent; instantiation != null; instantiation = instantiation.parent) {
            if (instantiation.subroutineInsns == subroutineInsns) {
               throw new IllegalArgumentException(stringConcat$0(subroutineInsns));
            }
         }

         this.parent = parent;
         this.subroutineInsns = subroutineInsns;
         this.returnLabel = parent == null ? null : new LabelNode();
         this.clonedLabels = new HashMap();
         LabelNode clonedLabelNode = null;

         for(int insnIndex = 0; insnIndex < JSRInlinerAdapter.this.instructions.size(); ++insnIndex) {
            AbstractInsnNode insnNode = JSRInlinerAdapter.this.instructions.get(insnIndex);
            if (insnNode.getType() == 8) {
               LabelNode labelNode = (LabelNode)insnNode;
               if (clonedLabelNode == null) {
                  clonedLabelNode = new LabelNode();
               }

               this.clonedLabels.put(labelNode, clonedLabelNode);
            } else if (this.findOwner(insnIndex) == this) {
               clonedLabelNode = null;
            }
         }

      }

      // $FF: synthetic method
      private static String stringConcat$0(BitSet var0) {
         return "Recursive invocation of " + var0;
      }

      Instantiation findOwner(int insnIndex) {
         if (!this.subroutineInsns.get(insnIndex)) {
            return null;
         } else if (!JSRInlinerAdapter.this.sharedSubroutineInsns.get(insnIndex)) {
            return this;
         } else {
            Instantiation owner = this;

            for(Instantiation instantiation = this.parent; instantiation != null; instantiation = instantiation.parent) {
               if (instantiation.subroutineInsns.get(insnIndex)) {
                  owner = instantiation;
               }
            }

            return owner;
         }
      }

      LabelNode getClonedLabelForJumpInsn(LabelNode labelNode) {
         return (LabelNode)this.findOwner(JSRInlinerAdapter.this.instructions.indexOf(labelNode)).clonedLabels.get(labelNode);
      }

      LabelNode getClonedLabel(LabelNode labelNode) {
         return (LabelNode)this.clonedLabels.get(labelNode);
      }

      public Set entrySet() {
         throw new UnsupportedOperationException();
      }

      public LabelNode get(Object key) {
         return this.getClonedLabelForJumpInsn((LabelNode)key);
      }

      public boolean equals(Object other) {
         throw new UnsupportedOperationException();
      }

      public int hashCode() {
         throw new UnsupportedOperationException();
      }
   }
}
