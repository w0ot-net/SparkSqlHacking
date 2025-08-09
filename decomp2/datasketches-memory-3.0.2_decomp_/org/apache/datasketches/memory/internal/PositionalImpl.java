package org.apache.datasketches.memory.internal;

import org.apache.datasketches.memory.BufferPositionInvariantsException;
import org.apache.datasketches.memory.Positional;

public abstract class PositionalImpl extends ResourceImpl implements Positional {
   private long capacity;
   private long start = 0L;
   private long pos = 0L;
   private long end;

   PositionalImpl(long capacityBytes) {
      this.capacity = this.end = capacityBytes;
   }

   public final PositionalImpl incrementPosition(long increment) {
      this.incrementAndCheckPositionForRead(this.pos, increment);
      return this;
   }

   public final long getEnd() {
      return this.end;
   }

   public final long getPosition() {
      return this.pos;
   }

   public final long getStart() {
      return this.start;
   }

   public final long getRemaining() {
      return this.end - this.pos;
   }

   public final boolean hasRemaining() {
      return this.end - this.pos > 0L;
   }

   public final PositionalImpl resetPosition() {
      this.pos = this.start;
      return this;
   }

   public final PositionalImpl setPosition(long position) {
      return this.setStartPositionEnd(this.start, position, this.end);
   }

   public final PositionalImpl setStartPositionEnd(long start, long position, long end) {
      this.checkValid();
      checkInvariants(start, position, end, this.capacity);
      this.start = start;
      this.end = end;
      this.pos = position;
      return this;
   }

   final void incrementAndCheckPositionForRead(long position, long increment) {
      this.checkValid();
      long newPos = position + increment;
      checkInvariants(this.start, newPos, this.end, this.capacity);
      this.pos = newPos;
   }

   final void incrementAndCheckPositionForWrite(long position, long increment) {
      this.checkNotReadOnly();
      this.incrementAndCheckPositionForRead(position, increment);
   }

   static final void checkInvariants(long start, long pos, long end, long cap) {
      if ((start | pos | end | cap | pos - start | end - pos | cap - end) < 0L) {
         throw new BufferPositionInvariantsException("Violation of Invariants: start: " + start + " <= pos: " + pos + " <= end: " + end + " <= cap: " + cap + "; (pos - start): " + (pos - start) + ", (end - pos): " + (end - pos) + ", (cap - end): " + (cap - end));
      }
   }
}
