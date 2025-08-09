package org.roaringbitmap.art;

public class ForwardShuttle extends AbstractShuttle {
   ForwardShuttle(Art art, Containers containers) {
      super(art, containers);
   }

   protected boolean currentBeforeHigh(long current, long high) {
      return current < high;
   }

   protected int visitedNodeNextPosition(Node node, int pos) {
      return node.getNextLargerPos(pos);
   }

   protected int boundaryNodePosition(Node node, boolean inRunDirection) {
      return inRunDirection ? node.getMaxPos() : node.getMinPos();
   }

   protected boolean prefixMismatchIsInRunDirection(byte nodeValue, byte highValue) {
      return Byte.toUnsignedInt(nodeValue) < Byte.toUnsignedInt(highValue);
   }

   protected int searchMissNextPosition(SearchResult result) {
      return result.getNextLargerPos();
   }
}
