package scala.collection.immutable;

import scala.reflect.ClassTag$;

public final class MapNode$ {
   public static final MapNode$ MODULE$ = new MapNode$();
   private static final BitmapIndexedMapNode EmptyMapNode;

   static {
      EmptyMapNode = new BitmapIndexedMapNode(0, 0, ClassTag$.MODULE$.Any().newArray(0), new int[0], 0, 0);
   }

   private final BitmapIndexedMapNode EmptyMapNode() {
      return EmptyMapNode;
   }

   public BitmapIndexedMapNode empty() {
      return this.EmptyMapNode();
   }

   public final int TupleLength() {
      return 2;
   }

   private MapNode$() {
   }
}
