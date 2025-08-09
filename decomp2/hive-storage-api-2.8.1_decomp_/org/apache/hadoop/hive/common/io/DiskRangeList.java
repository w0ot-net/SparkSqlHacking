package org.apache.hadoop.hive.common.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskRangeList extends DiskRange {
   private static final Logger LOG = LoggerFactory.getLogger(DiskRangeList.class);
   public DiskRangeList prev;
   public DiskRangeList next;

   public DiskRangeList(long offset, long end) {
      super(offset, end);
   }

   public DiskRangeList replaceSelfWith(DiskRangeList other) {
      this.checkArg(other);
      other.prev = this.prev;
      other.next = this.next;
      if (this.prev != null) {
         checkOrder(this.prev, other, this);
         this.prev.next = other;
      }

      if (this.next != null) {
         checkOrder(other, this.next, this);
         this.next.prev = other;
      }

      this.next = this.prev = null;
      return other;
   }

   private static final void checkOrder(DiskRangeList prev, DiskRangeList next, DiskRangeList ref) {
      if (prev.getEnd() > next.getOffset()) {
         assertInvalidOrder(ref.prev == null ? ref : ref.prev, prev, next);
      }
   }

   private static final void assertInvalidOrder(DiskRangeList ref, DiskRangeList prev, DiskRangeList next) {
      String error = "Elements not in order " + prev + " and " + next + "; trying to insert into " + stringifyDiskRanges(ref);
      LOG.error(error);
      throw new AssertionError(error);
   }

   public static final String stringifyDiskRanges(DiskRangeList range) {
      StringBuilder buffer = new StringBuilder();
      buffer.append("[");

      for(boolean isFirst = true; range != null; range = range.next) {
         if (!isFirst) {
            buffer.append(", {");
         } else {
            buffer.append("{");
         }

         isFirst = false;
         buffer.append(range.toString());
         buffer.append("}");
      }

      buffer.append("]");
      return buffer.toString();
   }

   private void checkArg(DiskRangeList other) {
      if (other == this) {
         throw new AssertionError("Inserting self into the list [" + other + "]");
      } else if (other.prev != null || other.next != null) {
         throw new AssertionError("[" + other + "] is part of another list; prev [" + other.prev + "], next [" + other.next + "]");
      }
   }

   public DiskRangeList insertPartBefore(DiskRangeList other) {
      this.checkArg(other);
      if (other.end <= this.offset || other.end > this.end) {
         assertInvalidOrder(this.prev == null ? this : this.prev, other, this);
      }

      this.offset = other.end;
      other.prev = this.prev;
      other.next = this;
      if (this.prev != null) {
         checkOrder(this.prev, other, this.prev);
         this.prev.next = other;
      }

      this.prev = other;
      return other;
   }

   public DiskRangeList insertAfter(DiskRangeList other) {
      this.checkArg(other);
      checkOrder(this, other, this);
      return this.insertAfterInternal(other);
   }

   private DiskRangeList insertAfterInternal(DiskRangeList other) {
      other.next = this.next;
      other.prev = this;
      if (this.next != null) {
         checkOrder(other, this.next, this);
         this.next.prev = other;
      }

      this.next = other;
      return other;
   }

   public DiskRangeList insertPartAfter(DiskRangeList other) {
      if (other.offset > this.end || other.offset <= this.offset || other.end <= this.offset) {
         assertInvalidOrder(this.prev == null ? this : this.prev, this, other);
      }

      this.end = other.offset;
      return this.insertAfter(other);
   }

   public void removeAfter() {
      DiskRangeList other = this.next;
      if (this == other) {
         throw new AssertionError("Invalid duplicate [" + other + "]");
      } else {
         this.next = other.next;
         if (this.next != null) {
            this.next.prev = this;
         }

         other.next = other.prev = null;
      }
   }

   public void removeSelf() {
      if (this.prev != this && this.next != this) {
         if (this.prev != null) {
            this.prev.next = this.next;
         }

         if (this.next != null) {
            this.next.prev = this.prev;
         }

         this.next = this.prev = null;
      } else {
         throw new AssertionError("Invalid duplicate [" + this + "]");
      }
   }

   public final DiskRangeList split(long cOffset) {
      DiskRangeList right = this.insertAfterInternal((DiskRangeList)this.sliceAndShift(cOffset, this.end, 0L));
      DiskRangeList left = this.replaceSelfWith((DiskRangeList)this.sliceAndShift(this.offset, cOffset, 0L));
      checkOrder(left, right, left);
      return left;
   }

   public boolean hasContiguousNext() {
      return this.next != null && this.end == this.next.offset;
   }

   public int listSize() {
      int result = 1;

      for(DiskRangeList current = this.next; current != null; current = current.next) {
         ++result;
      }

      return result;
   }

   public long getTotalLength() {
      long totalLength = (long)this.getLength();

      for(DiskRangeList current = this.next; current != null; current = current.next) {
         totalLength += (long)current.getLength();
      }

      return totalLength;
   }

   public DiskRangeList[] listToArray() {
      DiskRangeList[] result = new DiskRangeList[this.listSize()];
      int i = 0;

      for(DiskRangeList current = this.next; current != null; current = current.next) {
         result[i] = current;
         ++i;
      }

      return result;
   }

   public int hashCode() {
      return super.hashCode();
   }

   public boolean equals(Object other) {
      return super.equals(other);
   }

   public void setEnd(long newEnd) {
      assert newEnd >= this.offset;

      assert this.next == null || this.next.offset >= newEnd;

      this.end = newEnd;
      if (this.next != null) {
         checkOrder(this, this.next, this);
      }

   }

   public static class CreateHelper {
      private DiskRangeList tail = null;
      private DiskRangeList head;

      public DiskRangeList getTail() {
         return this.tail;
      }

      public void addOrMerge(long offset, long end, boolean doMerge, boolean doLogNew) {
         if (!doMerge || this.tail == null || !this.tail.merge(offset, end)) {
            if (doLogNew) {
               DiskRangeList.LOG.debug("Creating new range; last range (which can include some previous adds) was " + this.tail);
            }

            DiskRangeList node = new DiskRangeList(offset, end);
            if (this.tail == null) {
               this.head = this.tail = node;
            } else {
               this.tail = this.tail.insertAfter(node);
            }

         }
      }

      public DiskRangeList get() {
         return this.head;
      }

      public DiskRangeList extract() {
         DiskRangeList result = this.head;
         this.head = null;
         return result;
      }
   }

   public static class MutateHelper extends DiskRangeList {
      public MutateHelper(DiskRangeList head) {
         super(-1L, -1L);

         assert head != null;

         assert head.prev == null;

         this.next = head;
         head.prev = this;
      }

      public DiskRangeList get() {
         return this.next;
      }

      public DiskRangeList extract() {
         DiskRangeList result = this.next;

         assert result != null;

         this.next = result.prev = null;
         return result;
      }
   }
}
