package org.apache.xbean.asm9.shade.commons;

import org.apache.xbean.asm9.AnnotationVisitor;
import org.apache.xbean.asm9.Attribute;
import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.FieldVisitor;
import org.apache.xbean.asm9.Handle;
import org.apache.xbean.asm9.Label;
import org.apache.xbean.asm9.MethodVisitor;
import org.apache.xbean.asm9.TypePath;

public class EmptyVisitor extends ClassVisitor {
   protected final AnnotationVisitor av;
   protected final FieldVisitor fv;
   protected final MethodVisitor mv;

   public EmptyVisitor() {
      super(AsmConstants.ASM_VERSION);
      this.av = new AnnotationVisitor(AsmConstants.ASM_VERSION) {
         public void visit(String name, Object value) {
            EmptyVisitor.this.visit(name, value);
         }

         public void visitEnum(String name, String desc, String value) {
            EmptyVisitor.this.visitEnum(name, desc, value);
         }

         public AnnotationVisitor visitAnnotation(String name, String desc) {
            return EmptyVisitor.this.visitAnnotation(name, desc);
         }

         public AnnotationVisitor visitArray(String name) {
            return EmptyVisitor.this.visitArray(name);
         }

         public void visitEnd() {
            EmptyVisitor.this.visitEnd();
         }
      };
      this.fv = new FieldVisitor(AsmConstants.ASM_VERSION) {
         public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return EmptyVisitor.this.visitAnnotation(desc, visible);
         }

         public void visitAttribute(Attribute attribute) {
            EmptyVisitor.this.visitAttribute(attribute);
         }

         public void visitEnd() {
            EmptyVisitor.this.visitEnd();
         }
      };
      this.mv = new MethodVisitor(AsmConstants.ASM_VERSION) {
         public AnnotationVisitor visitAnnotationDefault() {
            return EmptyVisitor.this.visitAnnotationDefault();
         }

         public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return EmptyVisitor.this.visitAnnotation(desc, visible);
         }

         public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
            return EmptyVisitor.this.visitMethodParameterAnnotation(parameter, desc, visible);
         }

         public void visitAttribute(Attribute attribute) {
            EmptyVisitor.this.visitAttribute(attribute);
         }

         public void visitCode() {
            EmptyVisitor.this.visitCode();
         }

         public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
            EmptyVisitor.this.visitFrame(type, nLocal, local, nStack, stack);
         }

         public void visitInsn(int opcode) {
            EmptyVisitor.this.visitInsn(opcode);
         }

         public void visitJumpInsn(int i, Label label) {
            EmptyVisitor.this.visitJumpInsn(i, label);
         }

         public void visitLabel(Label label) {
            EmptyVisitor.this.visitLabel(label);
         }

         public void visitLdcInsn(Object cst) {
            EmptyVisitor.this.visitLdcInsn(cst);
         }

         public void visitIincInsn(int var, int increment) {
            EmptyVisitor.this.visitIincInsn(var, increment);
         }

         public void visitTableSwitchInsn(int i, int i2, Label label, Label... labels) {
            EmptyVisitor.this.visitTableSwitchInsn(i, i2, label, labels);
         }

         public void visitLookupSwitchInsn(Label label, int[] ints, Label[] labels) {
            EmptyVisitor.this.visitLookupSwitchInsn(label, ints, labels);
         }

         public void visitMultiANewArrayInsn(String desc, int dims) {
            EmptyVisitor.this.visitMultiANewArrayInsn(desc, dims);
         }

         public void visitTryCatchBlock(Label label, Label label2, Label label3, String s) {
            EmptyVisitor.this.visitTryCatchBlock(label, label2, label3, s);
         }

         public void visitLocalVariable(String s, String s2, String s3, Label label, Label label2, int i) {
            EmptyVisitor.this.visitLocalVariable(s, s2, s3, label, label2, i);
         }

         public void visitLineNumber(int i, Label label) {
            EmptyVisitor.this.visitLineNumber(i, label);
         }

         public void visitMaxs(int maxStack, int maxLocals) {
            EmptyVisitor.this.visitMaxs(maxStack, maxLocals);
         }

         public void visitEnd() {
            EmptyVisitor.this.visitEnd();
         }

         public void visitIntInsn(int opcode, int operand) {
            EmptyVisitor.this.visitIntInsn(opcode, operand);
         }

         public void visitVarInsn(int opcode, int var) {
            EmptyVisitor.this.visitVarInsn(opcode, var);
         }

         public void visitTypeInsn(int opcode, String type) {
            EmptyVisitor.this.visitTypeInsn(opcode, type);
         }

         public void visitFieldInsn(int opcode, String owner, String name, String desc) {
            EmptyVisitor.this.visitFieldInsn(opcode, owner, name, desc);
         }

         public void visitMethodInsn(int opcode, String owner, String name, String desc) {
            EmptyVisitor.this.visitMethodInsn(opcode, owner, name, desc);
         }

         public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
            EmptyVisitor.this.visitMethodInsn(opcode, owner, name, desc);
         }

         public void visitInvokeDynamicInsn(String s, String s2, Handle handle, Object... objects) {
            EmptyVisitor.this.visitInvokeDynamicInsn(s, s2, handle, objects);
         }
      };
   }

   protected AnnotationVisitor visitAnnotationDefault() {
      return this.av;
   }

   protected AnnotationVisitor visitArray(String name) {
      return this.av;
   }

   protected AnnotationVisitor visitAnnotation(String name, String desc) {
      return this.av;
   }

   protected void visitEnum(String name, String desc, String value) {
   }

   protected void visit(String name, Object value) {
   }

   protected void visitVarInsn(int opcode, int var) {
   }

   protected void visitTypeInsn(int opcode, String type) {
   }

   protected void visitFieldInsn(int opcode, String owner, String name, String desc) {
   }

   protected void visitMethodInsn(int opcode, String owner, String name, String desc) {
   }

   protected void visitInvokeDynamicInsn(String s, String s2, Handle handle, Object[] objects) {
   }

   protected void visitIntInsn(int opcode, int operand) {
   }

   protected void visitJumpInsn(int i, Label label) {
   }

   protected void visitLabel(Label label) {
   }

   protected void visitLdcInsn(Object cst) {
   }

   protected void visitIincInsn(int var, int increment) {
   }

   protected void visitTableSwitchInsn(int i, int i2, Label label, Label[] labels) {
   }

   protected void visitLookupSwitchInsn(Label label, int[] ints, Label[] labels) {
   }

   protected void visitMultiANewArrayInsn(String desc, int dims) {
   }

   protected void visitTryCatchBlock(Label label, Label label2, Label label3, String s) {
   }

   protected void visitLocalVariable(String s, String s2, String s3, Label label, Label label2, int i) {
   }

   protected void visitLineNumber(int i, Label label) {
   }

   protected void visitMaxs(int maxStack, int maxLocals) {
   }

   protected void visitInsn(int opcode) {
   }

   protected void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
   }

   protected void visitCode() {
   }

   protected AnnotationVisitor visitMethodParameterAnnotation(int parameter, String desc, boolean visible) {
      return this.av;
   }

   protected AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
      return this.av;
   }

   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
      if (this.cv != null) {
         this.cv.visit(version, access, name, signature, superName, interfaces);
      }

   }

   public void visitSource(String source, String debug) {
      if (this.cv != null) {
         this.cv.visitSource(source, debug);
      }

   }

   public void visitOuterClass(String owner, String name, String desc) {
      if (this.cv != null) {
         this.cv.visitOuterClass(owner, name, desc);
      }

   }

   public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
      return this.av;
   }

   public void visitAttribute(Attribute attr) {
      if (this.cv != null) {
         this.cv.visitAttribute(attr);
      }

   }

   public void visitInnerClass(String name, String outerName, String innerName, int access) {
      if (this.cv != null) {
         this.cv.visitInnerClass(name, outerName, innerName, access);
      }

   }

   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
      return this.fv;
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
      return this.mv;
   }

   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
      return this.av;
   }

   public void visitEnd() {
      if (this.cv != null) {
         this.cv.visitEnd();
      }

   }

   public AnnotationVisitor annotationVisitor() {
      return this.av;
   }

   public FieldVisitor fieldVisitor() {
      return this.fv;
   }

   public MethodVisitor methodVisitor() {
      return this.mv;
   }
}
