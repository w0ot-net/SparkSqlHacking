package org.glassfish.jersey.internal.inject;

public final class CustomAnnotationLiteral extends AnnotationLiteral implements Custom {
   public static final Custom INSTANCE = new CustomAnnotationLiteral();

   private CustomAnnotationLiteral() {
   }
}
