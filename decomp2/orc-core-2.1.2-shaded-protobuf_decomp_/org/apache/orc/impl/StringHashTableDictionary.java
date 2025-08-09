package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.Text;

public class StringHashTableDictionary implements Dictionary {
   private final DynamicByteArray byteArray;
   private final DynamicIntArray keyOffsets;
   private DynamicIntArray[] hashBuckets;
   private int capacity;
   private int threshold;
   private float loadFactor;
   private static float DEFAULT_LOAD_FACTOR = 0.75F;
   private static final int BUCKET_SIZE = 8;
   private static final int MAX_ARRAY_SIZE = 2147483639;

   public StringHashTableDictionary(int initialCapacity) {
      this(initialCapacity, DEFAULT_LOAD_FACTOR);
   }

   public StringHashTableDictionary(int initialCapacity, float loadFactor) {
      this.byteArray = new DynamicByteArray();
      this.capacity = initialCapacity;
      this.loadFactor = loadFactor;
      this.keyOffsets = new DynamicIntArray(initialCapacity);
      this.initializeHashBuckets();
      this.threshold = (int)Math.min((float)initialCapacity * loadFactor, (float)Integer.MAX_VALUE);
   }

   private void initializeHashBuckets() {
      DynamicIntArray[] newBuckets = this.hashBuckets == null ? new DynamicIntArray[this.capacity] : this.hashBuckets;

      for(int i = 0; i < this.capacity; ++i) {
         newBuckets[i] = this.createBucket();
      }

      this.hashBuckets = newBuckets;
   }

   private DynamicIntArray createBucket() {
      return new DynamicIntArray(8);
   }

   public void visit(Dictionary.Visitor visitor) throws IOException {
      this.traverse(visitor, new VisitorContextImpl(this.byteArray, this.keyOffsets));
   }

   private void traverse(Dictionary.Visitor visitor, VisitorContextImpl context) throws IOException {
      for(DynamicIntArray intArray : this.hashBuckets) {
         for(int i = 0; i < intArray.size(); ++i) {
            context.setPosition(intArray.get(i));
            visitor.visit(context);
         }
      }

   }

   public void clear() {
      this.byteArray.clear();
      this.keyOffsets.clear();
      this.initializeHashBuckets();
   }

   public void getText(Text result, int positionInKeyOffset) {
      DictionaryUtils.getTextInternal(result, positionInKeyOffset, this.keyOffsets, this.byteArray);
   }

   public ByteBuffer getText(int positionInKeyOffset) {
      return DictionaryUtils.getTextInternal(positionInKeyOffset, this.keyOffsets, this.byteArray);
   }

   public int writeTo(OutputStream out, int position) throws IOException {
      return DictionaryUtils.writeToTextInternal(out, position, this.keyOffsets, this.byteArray);
   }

   public int add(Text text) {
      return this.add(text.getBytes(), 0, text.getLength());
   }

   public int add(byte[] bytes, int offset, int length) {
      this.resizeIfNeeded();
      int index = this.getIndex(bytes, offset, length);
      DynamicIntArray candidateArray = this.hashBuckets[index];

      for(int i = 0; i < candidateArray.size(); ++i) {
         int candidateIndex = candidateArray.get(i);
         if (DictionaryUtils.equalsInternal(bytes, offset, length, candidateIndex, this.keyOffsets, this.byteArray)) {
            return candidateIndex;
         }
      }

      int currIdx = this.keyOffsets.size();
      this.keyOffsets.add(this.byteArray.add(bytes, offset, length));
      candidateArray.add(currIdx);
      return currIdx;
   }

   private void resizeIfNeeded() {
      if (this.keyOffsets.size() >= this.threshold) {
         int oldCapacity = this.capacity;
         int newCapacity = (oldCapacity << 1) + 1;
         this.capacity = newCapacity;
         this.doResize(newCapacity, oldCapacity);
         this.threshold = (int)Math.min((float)newCapacity * this.loadFactor, (float)Integer.MAX_VALUE);
      }

   }

   public int size() {
      return this.keyOffsets.size();
   }

   int getIndex(Text text) {
      return this.getIndex(text.getBytes(), 0, text.getLength());
   }

   int getIndex(byte[] bytes, int offset, int length) {
      int hash = 1;

      for(int i = offset; i < offset + length; ++i) {
         hash = 31 * hash + bytes[i];
      }

      return Math.floorMod(hash, this.capacity);
   }

   private void doResize(int newCapacity, int oldCapacity) {
      DynamicIntArray[] resizedHashBuckets = new DynamicIntArray[newCapacity];

      for(int i = 0; i < newCapacity; ++i) {
         resizedHashBuckets[i] = this.createBucket();
      }

      for(int i = 0; i < oldCapacity; ++i) {
         DynamicIntArray oldBucket = this.hashBuckets[i];

         for(int j = 0; j < oldBucket.size(); ++j) {
            int offset = oldBucket.get(j);
            ByteBuffer text = this.getText(offset);
            resizedHashBuckets[this.getIndex(text.array(), text.position(), text.remaining())].add(oldBucket.get(j));
         }
      }

      this.hashBuckets = resizedHashBuckets;
   }

   public long getSizeInBytes() {
      long bucketTotalSize = 0L;

      for(DynamicIntArray dynamicIntArray : this.hashBuckets) {
         bucketTotalSize += (long)dynamicIntArray.size();
      }

      return this.byteArray.getSizeInBytes() + (long)this.keyOffsets.getSizeInBytes() + bucketTotalSize;
   }
}
