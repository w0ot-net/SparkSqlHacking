package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.Text;

public class StringRedBlackTree extends RedBlackTree implements Dictionary {
   private final DynamicByteArray byteArray = new DynamicByteArray();
   private final DynamicIntArray keyOffsets;
   private final Text newKey = new Text();

   public StringRedBlackTree(int initialCapacity) {
      super(initialCapacity);
      this.keyOffsets = new DynamicIntArray(initialCapacity);
   }

   public int add(String value) {
      this.newKey.set(value);
      return this.addNewKey();
   }

   private int addNewKey() {
      if (this.add()) {
         int len = this.newKey.getLength();
         this.keyOffsets.add(this.byteArray.add(this.newKey.getBytes(), 0, len));
      }

      return this.lastAdd;
   }

   public int add(Text value) {
      this.newKey.set(value);
      return this.addNewKey();
   }

   public int add(byte[] bytes, int offset, int length) {
      this.newKey.set(bytes, offset, length);
      return this.addNewKey();
   }

   protected int compareValue(int position) {
      int start = this.keyOffsets.get(position);
      int end;
      if (position + 1 == this.keyOffsets.size()) {
         end = this.byteArray.size();
      } else {
         end = this.keyOffsets.get(position + 1);
      }

      return this.byteArray.compare(this.newKey.getBytes(), 0, this.newKey.getLength(), start, end - start);
   }

   private void recurse(int node, Dictionary.Visitor visitor, VisitorContextImpl context) throws IOException {
      if (node != -1) {
         this.recurse(this.getLeft(node), visitor, context);
         context.setPosition(node);
         visitor.visit(context);
         this.recurse(this.getRight(node), visitor, context);
      }

   }

   public void visit(Dictionary.Visitor visitor) throws IOException {
      this.recurse(this.root, visitor, new VisitorContextImpl(this.byteArray, this.keyOffsets));
   }

   public void clear() {
      super.clear();
      this.byteArray.clear();
      this.keyOffsets.clear();
   }

   public void getText(Text result, int originalPosition) {
      DictionaryUtils.getTextInternal(result, originalPosition, this.keyOffsets, this.byteArray);
   }

   public ByteBuffer getText(int positionInKeyOffset) {
      return DictionaryUtils.getTextInternal(positionInKeyOffset, this.keyOffsets, this.byteArray);
   }

   public int writeTo(OutputStream out, int position) throws IOException {
      return DictionaryUtils.writeToTextInternal(out, position, this.keyOffsets, this.byteArray);
   }

   public int getCharacterSize() {
      return this.byteArray.size();
   }

   public long getSizeInBytes() {
      return this.byteArray.getSizeInBytes() + (long)this.keyOffsets.getSizeInBytes() + super.getSizeInBytes();
   }
}
