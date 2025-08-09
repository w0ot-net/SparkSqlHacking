package scala.collection.immutable;

import scala.reflect.ClassTag$;

public final class SetNode$ {
   public static final SetNode$ MODULE$ = new SetNode$();
   private static final BitmapIndexedSetNode EmptySetNode;

   static {
      EmptySetNode = new BitmapIndexedSetNode(0, 0, ClassTag$.MODULE$.Any().newArray(0), new int[0], 0, 0);
   }

   private final BitmapIndexedSetNode EmptySetNode() {
      return EmptySetNode;
   }

   public BitmapIndexedSetNode empty() {
      return this.EmptySetNode();
   }

   public final int TupleLength() {
      return 1;
   }

   private SetNode$() {
   }
}
