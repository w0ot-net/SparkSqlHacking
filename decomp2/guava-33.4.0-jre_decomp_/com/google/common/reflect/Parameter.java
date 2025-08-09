package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
public final class Parameter implements AnnotatedElement {
   private final Invokable declaration;
   private final int position;
   private final TypeToken type;
   private final ImmutableList annotations;
   private final @Nullable Object annotatedType;

   Parameter(Invokable declaration, int position, TypeToken type, Annotation[] annotations, @Nullable Object annotatedType) {
      this.declaration = declaration;
      this.position = position;
      this.type = type;
      this.annotations = ImmutableList.copyOf((Object[])annotations);
      this.annotatedType = annotatedType;
   }

   public TypeToken getType() {
      return this.type;
   }

   public Invokable getDeclaringInvokable() {
      return this.declaration;
   }

   public boolean isAnnotationPresent(Class annotationType) {
      return this.getAnnotation(annotationType) != null;
   }

   @CheckForNull
   public Annotation getAnnotation(Class annotationType) {
      Preconditions.checkNotNull(annotationType);

      for(Annotation annotation : this.annotations) {
         if (annotationType.isInstance(annotation)) {
            return (Annotation)annotationType.cast(annotation);
         }
      }

      return null;
   }

   public Annotation[] getAnnotations() {
      return this.getDeclaredAnnotations();
   }

   public Annotation[] getAnnotationsByType(Class annotationType) {
      return this.getDeclaredAnnotationsByType(annotationType);
   }

   public Annotation[] getDeclaredAnnotations() {
      return (Annotation[])this.annotations.toArray(new Annotation[0]);
   }

   @CheckForNull
   public Annotation getDeclaredAnnotation(Class annotationType) {
      Preconditions.checkNotNull(annotationType);
      return (Annotation)FluentIterable.from((Iterable)this.annotations).filter(annotationType).first().orNull();
   }

   public Annotation[] getDeclaredAnnotationsByType(Class annotationType) {
      A[] result = (A[])((Annotation[])FluentIterable.from((Iterable)this.annotations).filter(annotationType).toArray(annotationType));
      return result;
   }

   public AnnotatedType getAnnotatedType() {
      return (AnnotatedType)Objects.requireNonNull((AnnotatedType)this.annotatedType);
   }

   public boolean equals(@CheckForNull Object obj) {
      if (!(obj instanceof Parameter)) {
         return false;
      } else {
         Parameter that = (Parameter)obj;
         return this.position == that.position && this.declaration.equals(that.declaration);
      }
   }

   public int hashCode() {
      return this.position;
   }

   public String toString() {
      return this.type + " arg" + this.position;
   }
}
