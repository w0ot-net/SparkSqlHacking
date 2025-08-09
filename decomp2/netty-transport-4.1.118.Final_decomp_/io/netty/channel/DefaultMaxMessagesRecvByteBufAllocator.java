package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;

public abstract class DefaultMaxMessagesRecvByteBufAllocator implements MaxMessagesRecvByteBufAllocator {
   private final boolean ignoreBytesRead;
   private volatile int maxMessagesPerRead;
   private volatile boolean respectMaybeMoreData;

   public DefaultMaxMessagesRecvByteBufAllocator() {
      this(1);
   }

   public DefaultMaxMessagesRecvByteBufAllocator(int maxMessagesPerRead) {
      this(maxMessagesPerRead, false);
   }

   DefaultMaxMessagesRecvByteBufAllocator(int maxMessagesPerRead, boolean ignoreBytesRead) {
      this.respectMaybeMoreData = true;
      this.ignoreBytesRead = ignoreBytesRead;
      this.maxMessagesPerRead(maxMessagesPerRead);
   }

   public int maxMessagesPerRead() {
      return this.maxMessagesPerRead;
   }

   public MaxMessagesRecvByteBufAllocator maxMessagesPerRead(int maxMessagesPerRead) {
      ObjectUtil.checkPositive(maxMessagesPerRead, "maxMessagesPerRead");
      this.maxMessagesPerRead = maxMessagesPerRead;
      return this;
   }

   public DefaultMaxMessagesRecvByteBufAllocator respectMaybeMoreData(boolean respectMaybeMoreData) {
      this.respectMaybeMoreData = respectMaybeMoreData;
      return this;
   }

   public final boolean respectMaybeMoreData() {
      return this.respectMaybeMoreData;
   }

   public abstract class MaxMessageHandle implements RecvByteBufAllocator.ExtendedHandle {
      private ChannelConfig config;
      private int maxMessagePerRead;
      private int totalMessages;
      private int totalBytesRead;
      private int attemptedBytesRead;
      private int lastBytesRead;
      private final boolean respectMaybeMoreData;
      private final UncheckedBooleanSupplier defaultMaybeMoreSupplier;

      public MaxMessageHandle() {
         this.respectMaybeMoreData = DefaultMaxMessagesRecvByteBufAllocator.this.respectMaybeMoreData;
         this.defaultMaybeMoreSupplier = new UncheckedBooleanSupplier() {
            public boolean get() {
               return MaxMessageHandle.this.attemptedBytesRead == MaxMessageHandle.this.lastBytesRead;
            }
         };
      }

      public void reset(ChannelConfig config) {
         this.config = config;
         this.maxMessagePerRead = DefaultMaxMessagesRecvByteBufAllocator.this.maxMessagesPerRead();
         this.totalMessages = this.totalBytesRead = 0;
      }

      public ByteBuf allocate(ByteBufAllocator alloc) {
         return alloc.ioBuffer(this.guess());
      }

      public final void incMessagesRead(int amt) {
         this.totalMessages += amt;
      }

      public void lastBytesRead(int bytes) {
         this.lastBytesRead = bytes;
         if (bytes > 0) {
            this.totalBytesRead += bytes;
         }

      }

      public final int lastBytesRead() {
         return this.lastBytesRead;
      }

      public boolean continueReading() {
         return this.continueReading(this.defaultMaybeMoreSupplier);
      }

      public boolean continueReading(UncheckedBooleanSupplier maybeMoreDataSupplier) {
         return this.config.isAutoRead() && (!this.respectMaybeMoreData || maybeMoreDataSupplier.get()) && this.totalMessages < this.maxMessagePerRead && (DefaultMaxMessagesRecvByteBufAllocator.this.ignoreBytesRead || this.totalBytesRead > 0);
      }

      public void readComplete() {
      }

      public int attemptedBytesRead() {
         return this.attemptedBytesRead;
      }

      public void attemptedBytesRead(int bytes) {
         this.attemptedBytesRead = bytes;
      }

      protected final int totalBytesRead() {
         return this.totalBytesRead < 0 ? Integer.MAX_VALUE : this.totalBytesRead;
      }
   }
}
