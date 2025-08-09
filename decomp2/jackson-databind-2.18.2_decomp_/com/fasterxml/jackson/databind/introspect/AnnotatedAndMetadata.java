package com.fasterxml.jackson.databind.introspect;

public class AnnotatedAndMetadata {
   public final Annotated annotated;
   public final Object metadata;

   public AnnotatedAndMetadata(Annotated ann, Object md) {
      this.annotated = ann;
      this.metadata = md;
   }

   public static AnnotatedAndMetadata of(Annotated ann, Object md) {
      return new AnnotatedAndMetadata(ann, md);
   }
}
