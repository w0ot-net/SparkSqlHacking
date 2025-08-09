package org.apache.xbean.asm9.tree;

import java.util.List;
import org.apache.xbean.asm9.AnnotationVisitor;
import org.apache.xbean.asm9.Attribute;
import org.apache.xbean.asm9.ClassVisitor;
import org.apache.xbean.asm9.RecordComponentVisitor;
import org.apache.xbean.asm9.TypePath;

public class RecordComponentNode extends RecordComponentVisitor {
   public String name;
   public String descriptor;
   public String signature;
   public List visibleAnnotations;
   public List invisibleAnnotations;
   public List visibleTypeAnnotations;
   public List invisibleTypeAnnotations;
   public List attrs;

   public RecordComponentNode(String name, String descriptor, String signature) {
      this(589824, name, descriptor, signature);
      if (this.getClass() != RecordComponentNode.class) {
         throw new IllegalStateException();
      }
   }

   public RecordComponentNode(int api, String name, String descriptor, String signature) {
      super(api);
      this.name = name;
      this.descriptor = descriptor;
      this.signature = signature;
   }

   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
      AnnotationNode annotation = new AnnotationNode(descriptor);
      if (visible) {
         this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
      } else {
         this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
      }

      return annotation;
   }

   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
      TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
      if (visible) {
         this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
      } else {
         this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
      }

      return typeAnnotation;
   }

   public void visitAttribute(Attribute attribute) {
      this.attrs = Util.add(this.attrs, attribute);
   }

   public void visitEnd() {
   }

   public void check(int api) {
      if (api < 524288) {
         throw new UnsupportedClassVersionException();
      }
   }

   public void accept(ClassVisitor classVisitor) {
      RecordComponentVisitor recordComponentVisitor = classVisitor.visitRecordComponent(this.name, this.descriptor, this.signature);
      if (recordComponentVisitor != null) {
         if (this.visibleAnnotations != null) {
            int i = 0;

            for(int n = this.visibleAnnotations.size(); i < n; ++i) {
               AnnotationNode annotation = (AnnotationNode)this.visibleAnnotations.get(i);
               annotation.accept(recordComponentVisitor.visitAnnotation(annotation.desc, true));
            }
         }

         if (this.invisibleAnnotations != null) {
            int i = 0;

            for(int n = this.invisibleAnnotations.size(); i < n; ++i) {
               AnnotationNode annotation = (AnnotationNode)this.invisibleAnnotations.get(i);
               annotation.accept(recordComponentVisitor.visitAnnotation(annotation.desc, false));
            }
         }

         if (this.visibleTypeAnnotations != null) {
            int i = 0;

            for(int n = this.visibleTypeAnnotations.size(); i < n; ++i) {
               TypeAnnotationNode typeAnnotation = (TypeAnnotationNode)this.visibleTypeAnnotations.get(i);
               typeAnnotation.accept(recordComponentVisitor.visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
            }
         }

         if (this.invisibleTypeAnnotations != null) {
            int i = 0;

            for(int n = this.invisibleTypeAnnotations.size(); i < n; ++i) {
               TypeAnnotationNode typeAnnotation = (TypeAnnotationNode)this.invisibleTypeAnnotations.get(i);
               typeAnnotation.accept(recordComponentVisitor.visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
            }
         }

         if (this.attrs != null) {
            int i = 0;

            for(int n = this.attrs.size(); i < n; ++i) {
               recordComponentVisitor.visitAttribute((Attribute)this.attrs.get(i));
            }
         }

         recordComponentVisitor.visitEnd();
      }
   }
}
