package org.apache.xbean.asm9;

public abstract class RecordComponentVisitor {
   protected final int api;
   protected RecordComponentVisitor delegate;

   protected RecordComponentVisitor(int api) {
      this(api, (RecordComponentVisitor)null);
   }

   protected RecordComponentVisitor(int api, RecordComponentVisitor recordComponentVisitor) {
      if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
         throw new IllegalArgumentException(stringConcat$0(api));
      } else {
         if (api == 17432576) {
            Constants.checkAsmExperimental(this);
         }

         this.api = api;
         this.delegate = recordComponentVisitor;
      }
   }

   // $FF: synthetic method
   private static String stringConcat$0(int var0) {
      return "Unsupported api " + var0;
   }

   public RecordComponentVisitor getDelegate() {
      return this.delegate;
   }

   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
      return this.delegate != null ? this.delegate.visitAnnotation(descriptor, visible) : null;
   }

   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
      return this.delegate != null ? this.delegate.visitTypeAnnotation(typeRef, typePath, descriptor, visible) : null;
   }

   public void visitAttribute(Attribute attribute) {
      if (this.delegate != null) {
         this.delegate.visitAttribute(attribute);
      }

   }

   public void visitEnd() {
      if (this.delegate != null) {
         this.delegate.visitEnd();
      }

   }
}
