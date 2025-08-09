package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RuntimeVisibleorInvisibleParameterAnnotationsAttribute extends AnnotationsAttribute {
   private final int numParameters;
   private final ParameterAnnotation[] parameterAnnotations;

   public RuntimeVisibleorInvisibleParameterAnnotationsAttribute(CPUTF8 name, ParameterAnnotation[] parameterAnnotations) {
      super(name);
      this.numParameters = parameterAnnotations.length;
      this.parameterAnnotations = parameterAnnotations;
   }

   protected int getLength() {
      int length = 1;

      for(int i = 0; i < this.numParameters; ++i) {
         length += this.parameterAnnotations[i].getLength();
      }

      return length;
   }

   protected ClassFileEntry[] getNestedClassFileEntries() {
      List<Object> nested = new ArrayList();
      nested.add(this.attributeName);

      for(ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
         nested.addAll(parameterAnnotation.getClassFileEntries());
      }

      return (ClassFileEntry[])nested.toArray(NONE);
   }

   protected void resolve(ClassConstantPool pool) {
      super.resolve(pool);

      for(ParameterAnnotation parameterAnnotation : this.parameterAnnotations) {
         parameterAnnotation.resolve(pool);
      }

   }

   public String toString() {
      return this.attributeName.underlyingString() + ": " + this.numParameters + " parameter annotations";
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeByte(this.numParameters);

      for(int i = 0; i < this.numParameters; ++i) {
         this.parameterAnnotations[i].writeBody(dos);
      }

   }

   public static class ParameterAnnotation {
      private final AnnotationsAttribute.Annotation[] annotations;
      private final int numAnnotations;

      public ParameterAnnotation(AnnotationsAttribute.Annotation[] annotations) {
         this.numAnnotations = annotations.length;
         this.annotations = annotations;
      }

      public List getClassFileEntries() {
         List<Object> nested = new ArrayList();

         for(AnnotationsAttribute.Annotation annotation : this.annotations) {
            nested.addAll(annotation.getClassFileEntries());
         }

         return nested;
      }

      public int getLength() {
         int length = 2;

         for(AnnotationsAttribute.Annotation annotation : this.annotations) {
            length += annotation.getLength();
         }

         return length;
      }

      public void resolve(ClassConstantPool pool) {
         for(AnnotationsAttribute.Annotation annotation : this.annotations) {
            annotation.resolve(pool);
         }

      }

      public void writeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.numAnnotations);

         for(AnnotationsAttribute.Annotation annotation : this.annotations) {
            annotation.writeBody(dos);
         }

      }
   }
}
