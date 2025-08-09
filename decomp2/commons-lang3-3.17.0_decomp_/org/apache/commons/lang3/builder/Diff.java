package org.apache.commons.lang3.builder;

import java.lang.reflect.Type;
import java.util.Objects;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

public abstract class Diff extends Pair {
   private static final long serialVersionUID = 1L;
   private final Type type;
   private final String fieldName;

   protected Diff(String fieldName) {
      this.fieldName = (String)Objects.requireNonNull(fieldName);
      this.type = (Type)ObjectUtils.defaultIfNull((Type)TypeUtils.getTypeArguments(this.getClass(), Diff.class).get(Diff.class.getTypeParameters()[0]), Object.class);
   }

   Diff(String fieldName, Type type) {
      this.fieldName = (String)Objects.requireNonNull(fieldName);
      this.type = (Type)Objects.requireNonNull(type);
   }

   public final String getFieldName() {
      return this.fieldName;
   }

   /** @deprecated */
   @Deprecated
   public final Type getType() {
      return this.type;
   }

   public final Object setValue(Object value) {
      throw new UnsupportedOperationException("Cannot alter Diff object.");
   }

   public final String toString() {
      return String.format("[%s: %s, %s]", this.fieldName, this.getLeft(), this.getRight());
   }
}
