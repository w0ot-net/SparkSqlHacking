package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ReflectionDiffBuilder implements org.apache.commons.lang3.builder.Builder {
   private final DiffBuilder diffBuilder;
   private String[] excludeFieldNames;

   public static Builder builder() {
      return new Builder();
   }

   private static String[] toExcludeFieldNames(String[] excludeFieldNames) {
      return excludeFieldNames == null ? ArrayUtils.EMPTY_STRING_ARRAY : (String[])ArraySorter.sort((Object[])ReflectionToStringBuilder.toNoNullStringArray((Object[])excludeFieldNames));
   }

   private ReflectionDiffBuilder(DiffBuilder diffBuilder, String[] excludeFieldNames) {
      this.diffBuilder = diffBuilder;
      this.excludeFieldNames = excludeFieldNames;
   }

   /** @deprecated */
   @Deprecated
   public ReflectionDiffBuilder(Object left, Object right, ToStringStyle style) {
      this(DiffBuilder.builder().setLeft(left).setRight(right).setStyle(style).build(), (String[])null);
   }

   private boolean accept(Field field) {
      if (field.getName().indexOf(36) != -1) {
         return false;
      } else if (Modifier.isTransient(field.getModifiers())) {
         return false;
      } else if (Modifier.isStatic(field.getModifiers())) {
         return false;
      } else if (this.excludeFieldNames != null && Arrays.binarySearch(this.excludeFieldNames, field.getName()) >= 0) {
         return false;
      } else {
         return !field.isAnnotationPresent(DiffExclude.class);
      }
   }

   private void appendFields(Class clazz) {
      for(Field field : FieldUtils.getAllFields(clazz)) {
         if (this.accept(field)) {
            try {
               this.diffBuilder.append(field.getName(), this.readField(field, this.getLeft()), this.readField(field, this.getRight()));
            } catch (IllegalAccessException e) {
               throw new IllegalArgumentException("Unexpected IllegalAccessException: " + e.getMessage(), e);
            }
         }
      }

   }

   public DiffResult build() {
      if (this.getLeft().equals(this.getRight())) {
         return this.diffBuilder.build();
      } else {
         this.appendFields(this.getLeft().getClass());
         return this.diffBuilder.build();
      }
   }

   public String[] getExcludeFieldNames() {
      return (String[])this.excludeFieldNames.clone();
   }

   private Object getLeft() {
      return this.diffBuilder.getLeft();
   }

   private Object getRight() {
      return this.diffBuilder.getRight();
   }

   private Object readField(Field field, Object target) throws IllegalAccessException {
      return FieldUtils.readField(field, target, true);
   }

   /** @deprecated */
   @Deprecated
   public ReflectionDiffBuilder setExcludeFieldNames(String... excludeFieldNames) {
      this.excludeFieldNames = toExcludeFieldNames(excludeFieldNames);
      return this;
   }

   public static final class Builder {
      private String[] excludeFieldNames;
      private DiffBuilder diffBuilder;

      public Builder() {
         this.excludeFieldNames = ArrayUtils.EMPTY_STRING_ARRAY;
      }

      public ReflectionDiffBuilder build() {
         return new ReflectionDiffBuilder(this.diffBuilder, this.excludeFieldNames);
      }

      public Builder setDiffBuilder(DiffBuilder diffBuilder) {
         this.diffBuilder = diffBuilder;
         return this;
      }

      public Builder setExcludeFieldNames(String... excludeFieldNames) {
         this.excludeFieldNames = ReflectionDiffBuilder.toExcludeFieldNames(excludeFieldNames);
         return this;
      }
   }
}
