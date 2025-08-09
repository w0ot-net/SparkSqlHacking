package scala.collection.mutable;

import java.io.Serializable;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class ArrayBuilder$ implements Serializable {
   public static final ArrayBuilder$ MODULE$ = new ArrayBuilder$();

   public ArrayBuilder make(final ClassTag evidence$1) {
      Class var2 = evidence$1.runtimeClass();
      Class var10000 = Byte.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofByte();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofByte();
      }

      var10000 = Short.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofShort();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofShort();
      }

      var10000 = Character.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofChar();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofChar();
      }

      var10000 = Integer.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofInt();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofInt();
      }

      var10000 = Long.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofLong();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofLong();
      }

      var10000 = Float.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofFloat();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofFloat();
      }

      var10000 = Double.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofDouble();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofDouble();
      }

      var10000 = Boolean.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofBoolean();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofBoolean();
      }

      var10000 = Void.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofUnit();
         }
      } else if (var10000.equals(var2)) {
         return new ArrayBuilder.ofUnit();
      }

      return new ArrayBuilder.ofRef(evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayBuilder$.class);
   }

   private ArrayBuilder$() {
   }
}
