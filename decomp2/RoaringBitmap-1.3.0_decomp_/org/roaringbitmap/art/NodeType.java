package org.roaringbitmap.art;

public enum NodeType {
   NODE4,
   NODE16,
   NODE48,
   NODE256,
   LEAF_NODE,
   DUMMY_ROOT;

   // $FF: synthetic method
   private static NodeType[] $values() {
      return new NodeType[]{NODE4, NODE16, NODE48, NODE256, LEAF_NODE, DUMMY_ROOT};
   }
}
