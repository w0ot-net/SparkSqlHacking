package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public final class ArrayOfDoublesSketches {
   public static ArrayOfDoublesSketch heapifySketch(Memory srcMem) {
      return heapifySketch(srcMem, 9001L);
   }

   public static ArrayOfDoublesSketch heapifySketch(Memory srcMem, long seed) {
      return ArrayOfDoublesSketch.heapify(srcMem, seed);
   }

   public static ArrayOfDoublesUpdatableSketch heapifyUpdatableSketch(Memory srcMem) {
      return heapifyUpdatableSketch(srcMem, 9001L);
   }

   public static ArrayOfDoublesUpdatableSketch heapifyUpdatableSketch(Memory srcMem, long seed) {
      return ArrayOfDoublesUpdatableSketch.heapify(srcMem, seed);
   }

   public static ArrayOfDoublesSketch wrapSketch(Memory srcMem) {
      return wrapSketch(srcMem, 9001L);
   }

   public static ArrayOfDoublesSketch wrapSketch(Memory srcMem, long seed) {
      return ArrayOfDoublesSketch.wrap(srcMem, seed);
   }

   public static ArrayOfDoublesUpdatableSketch wrapUpdatableSketch(WritableMemory srcMem) {
      return wrapUpdatableSketch(srcMem, 9001L);
   }

   public static ArrayOfDoublesUpdatableSketch wrapUpdatableSketch(WritableMemory srcMem, long seed) {
      return ArrayOfDoublesUpdatableSketch.wrap(srcMem, seed);
   }

   public static ArrayOfDoublesUnion heapifyUnion(Memory srcMem) {
      return heapifyUnion(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion heapifyUnion(Memory srcMem, long seed) {
      return ArrayOfDoublesUnion.heapify(srcMem, seed);
   }

   public static ArrayOfDoublesUnion wrapUnion(Memory srcMem) {
      return wrapUnion(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion wrapUnion(Memory srcMem, long seed) {
      return ArrayOfDoublesUnion.wrap(srcMem, seed);
   }

   public static ArrayOfDoublesUnion wrapUnion(WritableMemory srcMem) {
      return wrapUnion(srcMem, 9001L);
   }

   public static ArrayOfDoublesUnion wrapUnion(WritableMemory srcMem, long seed) {
      return ArrayOfDoublesUnion.wrap(srcMem, seed);
   }
}
