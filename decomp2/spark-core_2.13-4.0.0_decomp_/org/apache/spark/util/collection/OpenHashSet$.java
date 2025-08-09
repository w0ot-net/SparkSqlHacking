package org.apache.spark.util.collection;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class OpenHashSet$ implements Serializable {
   public static final OpenHashSet$ MODULE$ = new OpenHashSet$();
   private static final int MAX_CAPACITY = 1073741824;
   private static final int INVALID_POS = -1;
   private static final int NONEXISTENCE_MASK = Integer.MIN_VALUE;
   private static final int POSITION_MASK = Integer.MAX_VALUE;
   private static final Function1 org$apache$spark$util$collection$OpenHashSet$$grow = (JFunction1.mcVI.sp)(newSize) -> MODULE$.grow1(newSize);
   private static final Function2 org$apache$spark$util$collection$OpenHashSet$$move = (JFunction2.mcVII.sp)(oldPos, newPos) -> MODULE$.move1(oldPos, newPos);

   public int MAX_CAPACITY() {
      return MAX_CAPACITY;
   }

   public int INVALID_POS() {
      return INVALID_POS;
   }

   public int NONEXISTENCE_MASK() {
      return NONEXISTENCE_MASK;
   }

   public int POSITION_MASK() {
      return POSITION_MASK;
   }

   private void grow1(final int newSize) {
   }

   private void move1(final int oldPos, final int newPos) {
   }

   public Function1 org$apache$spark$util$collection$OpenHashSet$$grow() {
      return org$apache$spark$util$collection$OpenHashSet$$grow;
   }

   public Function2 org$apache$spark$util$collection$OpenHashSet$$move() {
      return org$apache$spark$util$collection$OpenHashSet$$move;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OpenHashSet$.class);
   }

   private OpenHashSet$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
