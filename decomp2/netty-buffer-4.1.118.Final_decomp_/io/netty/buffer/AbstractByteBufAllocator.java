package io.netty.buffer;

import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;

public abstract class AbstractByteBufAllocator implements ByteBufAllocator {
   static final int DEFAULT_INITIAL_CAPACITY = 256;
   static final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;
   static final int DEFAULT_MAX_COMPONENTS = 16;
   static final int CALCULATE_THRESHOLD = 4194304;
   private final boolean directByDefault;
   private final ByteBuf emptyBuf;

   protected static ByteBuf toLeakAwareBuffer(ByteBuf buf) {
      switch (ResourceLeakDetector.getLevel()) {
         case SIMPLE:
            ResourceLeakTracker<ByteBuf> leak = AbstractByteBuf.leakDetector.track(buf);
            if (leak != null) {
               buf = new SimpleLeakAwareByteBuf(buf, leak);
            }
            break;
         case ADVANCED:
         case PARANOID:
            ResourceLeakTracker<ByteBuf> leak = AbstractByteBuf.leakDetector.track(buf);
            if (leak != null) {
               buf = new AdvancedLeakAwareByteBuf(buf, leak);
            }
      }

      return buf;
   }

   protected static CompositeByteBuf toLeakAwareBuffer(CompositeByteBuf buf) {
      switch (ResourceLeakDetector.getLevel()) {
         case SIMPLE:
            ResourceLeakTracker<ByteBuf> leak = AbstractByteBuf.leakDetector.track(buf);
            if (leak != null) {
               buf = new SimpleLeakAwareCompositeByteBuf(buf, leak);
            }
            break;
         case ADVANCED:
         case PARANOID:
            ResourceLeakTracker<ByteBuf> leak = AbstractByteBuf.leakDetector.track(buf);
            if (leak != null) {
               buf = new AdvancedLeakAwareCompositeByteBuf(buf, leak);
            }
      }

      return buf;
   }

   protected AbstractByteBufAllocator() {
      this(false);
   }

   protected AbstractByteBufAllocator(boolean preferDirect) {
      this.directByDefault = preferDirect && PlatformDependent.hasUnsafe();
      this.emptyBuf = new EmptyByteBuf(this);
   }

   public ByteBuf buffer() {
      return this.directByDefault ? this.directBuffer() : this.heapBuffer();
   }

   public ByteBuf buffer(int initialCapacity) {
      return this.directByDefault ? this.directBuffer(initialCapacity) : this.heapBuffer(initialCapacity);
   }

   public ByteBuf buffer(int initialCapacity, int maxCapacity) {
      return this.directByDefault ? this.directBuffer(initialCapacity, maxCapacity) : this.heapBuffer(initialCapacity, maxCapacity);
   }

   public ByteBuf ioBuffer() {
      return !PlatformDependent.hasUnsafe() && !this.isDirectBufferPooled() ? this.heapBuffer(256) : this.directBuffer(256);
   }

   public ByteBuf ioBuffer(int initialCapacity) {
      return !PlatformDependent.hasUnsafe() && !this.isDirectBufferPooled() ? this.heapBuffer(initialCapacity) : this.directBuffer(initialCapacity);
   }

   public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
      return !PlatformDependent.hasUnsafe() && !this.isDirectBufferPooled() ? this.heapBuffer(initialCapacity, maxCapacity) : this.directBuffer(initialCapacity, maxCapacity);
   }

   public ByteBuf heapBuffer() {
      return this.heapBuffer(256, Integer.MAX_VALUE);
   }

   public ByteBuf heapBuffer(int initialCapacity) {
      return this.heapBuffer(initialCapacity, Integer.MAX_VALUE);
   }

   public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
      if (initialCapacity == 0 && maxCapacity == 0) {
         return this.emptyBuf;
      } else {
         validate(initialCapacity, maxCapacity);
         return this.newHeapBuffer(initialCapacity, maxCapacity);
      }
   }

   public ByteBuf directBuffer() {
      return this.directBuffer(256, Integer.MAX_VALUE);
   }

   public ByteBuf directBuffer(int initialCapacity) {
      return this.directBuffer(initialCapacity, Integer.MAX_VALUE);
   }

   public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
      if (initialCapacity == 0 && maxCapacity == 0) {
         return this.emptyBuf;
      } else {
         validate(initialCapacity, maxCapacity);
         return this.newDirectBuffer(initialCapacity, maxCapacity);
      }
   }

   public CompositeByteBuf compositeBuffer() {
      return this.directByDefault ? this.compositeDirectBuffer() : this.compositeHeapBuffer();
   }

   public CompositeByteBuf compositeBuffer(int maxNumComponents) {
      return this.directByDefault ? this.compositeDirectBuffer(maxNumComponents) : this.compositeHeapBuffer(maxNumComponents);
   }

   public CompositeByteBuf compositeHeapBuffer() {
      return this.compositeHeapBuffer(16);
   }

   public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
      return toLeakAwareBuffer(new CompositeByteBuf(this, false, maxNumComponents));
   }

   public CompositeByteBuf compositeDirectBuffer() {
      return this.compositeDirectBuffer(16);
   }

   public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
      return toLeakAwareBuffer(new CompositeByteBuf(this, true, maxNumComponents));
   }

   private static void validate(int initialCapacity, int maxCapacity) {
      ObjectUtil.checkPositiveOrZero(initialCapacity, "initialCapacity");
      if (initialCapacity > maxCapacity) {
         throw new IllegalArgumentException(String.format("initialCapacity: %d (expected: not greater than maxCapacity(%d)", initialCapacity, maxCapacity));
      }
   }

   protected abstract ByteBuf newHeapBuffer(int var1, int var2);

   protected abstract ByteBuf newDirectBuffer(int var1, int var2);

   public String toString() {
      return StringUtil.simpleClassName(this) + "(directByDefault: " + this.directByDefault + ')';
   }

   public int calculateNewCapacity(int minNewCapacity, int maxCapacity) {
      ObjectUtil.checkPositiveOrZero(minNewCapacity, "minNewCapacity");
      if (minNewCapacity > maxCapacity) {
         throw new IllegalArgumentException(String.format("minNewCapacity: %d (expected: not greater than maxCapacity(%d)", minNewCapacity, maxCapacity));
      } else {
         int threshold = 4194304;
         if (minNewCapacity == 4194304) {
            return 4194304;
         } else if (minNewCapacity > 4194304) {
            int newCapacity = minNewCapacity / 4194304 * 4194304;
            if (newCapacity > maxCapacity - 4194304) {
               newCapacity = maxCapacity;
            } else {
               newCapacity += 4194304;
            }

            return newCapacity;
         } else {
            int newCapacity = MathUtil.findNextPositivePowerOfTwo(Math.max(minNewCapacity, 64));
            return Math.min(newCapacity, maxCapacity);
         }
      }
   }

   static {
      ResourceLeakDetector.addExclusions(AbstractByteBufAllocator.class, new String[]{"toLeakAwareBuffer"});
   }
}
