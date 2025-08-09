package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.roaringbitmap.longlong.LongUtils;

public class LeafNode extends Node {
   private long key;
   long containerIdx;
   public static final int LEAF_NODE_KEY_LENGTH_IN_BYTES = 6;

   public LeafNode(byte[] key, long containerIdx) {
      super(NodeType.LEAF_NODE, 0);
      byte[] bytes = new byte[8];
      System.arraycopy(key, 0, bytes, 0, 6);
      this.key = LongUtils.fromBDBytes(bytes);
      this.containerIdx = containerIdx;
   }

   public LeafNode(long key, long containerIdx) {
      super(NodeType.LEAF_NODE, 0);
      this.key = key;
      this.containerIdx = containerIdx;
   }

   public void serializeNodeBody(DataOutput dataOutput) throws IOException {
      byte[] keyBytes = LongUtils.highPart(this.key);
      dataOutput.write(keyBytes);
      dataOutput.writeLong(Long.reverseBytes(this.containerIdx));
   }

   public void serializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      byte[] keyBytes = LongUtils.highPart(this.key);
      byteBuffer.put(keyBytes);
      byteBuffer.putLong(this.containerIdx);
   }

   public void deserializeNodeBody(DataInput dataInput) throws IOException {
      byte[] longBytes = new byte[8];
      dataInput.readFully(longBytes, 0, 6);
      this.key = LongUtils.fromBDBytes(longBytes);
      this.containerIdx = Long.reverseBytes(dataInput.readLong());
   }

   public void deserializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      byte[] bytes = new byte[8];
      byteBuffer.get(bytes, 0, 6);
      this.key = LongUtils.fromBDBytes(bytes);
      this.containerIdx = byteBuffer.getLong();
   }

   public int serializeNodeBodySizeInBytes() {
      return 14;
   }

   public int getChildPos(byte k) {
      throw new UnsupportedOperationException();
   }

   public SearchResult getNearestChildPos(byte key) {
      throw new UnsupportedOperationException();
   }

   public byte getChildKey(int pos) {
      throw new UnsupportedOperationException();
   }

   public Node getChild(int pos) {
      throw new UnsupportedOperationException();
   }

   public void replaceNode(int pos, Node freshOne) {
      throw new UnsupportedOperationException();
   }

   public int getMinPos() {
      throw new UnsupportedOperationException();
   }

   public int getNextLargerPos(int pos) {
      throw new UnsupportedOperationException();
   }

   public int getMaxPos() {
      throw new UnsupportedOperationException();
   }

   public int getNextSmallerPos(int pos) {
      throw new UnsupportedOperationException();
   }

   public Node remove(int pos) {
      throw new UnsupportedOperationException();
   }

   public void replaceChildren(Node[] children) {
      throw new UnsupportedOperationException();
   }

   public long getContainerIdx() {
      return this.containerIdx;
   }

   public byte[] getKeyBytes() {
      return LongUtils.highPart(this.key);
   }

   public long getKey() {
      return this.key >>> 16;
   }
}
