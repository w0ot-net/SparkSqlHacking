package org.apache.logging.log4j.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

@PerformanceSensitive({"allocation"})
public class Unbox {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final int BITS_PER_INT = 32;
   private static final int RINGBUFFER_MIN_SIZE = 32;
   private static final int RINGBUFFER_SIZE = calculateRingBufferSize("log4j.unbox.ringbuffer.size");
   private static final int MASK;
   private static ThreadLocal threadLocalState;
   private static WebSafeState webSafeState;

   private Unbox() {
   }

   private static int calculateRingBufferSize(final String propertyName) {
      String userPreferredRBSize = PropertiesUtil.getProperties().getStringProperty(propertyName, String.valueOf(32));

      try {
         int size = Integer.parseInt(userPreferredRBSize.trim());
         if (size < 32) {
            size = 32;
            LOGGER.warn((String)"Invalid {} {}, using minimum size {}.", (Object)propertyName, userPreferredRBSize, 32);
         }

         return ceilingNextPowerOfTwo(size);
      } catch (Exception var3) {
         LOGGER.warn((String)"Invalid {} {}, using default size {}.", (Object)propertyName, userPreferredRBSize, 32);
         return 32;
      }
   }

   private static int ceilingNextPowerOfTwo(final int x) {
      return 1 << 32 - Integer.numberOfLeadingZeros(x - 1);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final float value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final double value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final short value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final int value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final char value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final long value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final byte value) {
      return getSB().append(value);
   }

   @PerformanceSensitive({"allocation"})
   public static StringBuilder box(final boolean value) {
      return getSB().append(value);
   }

   private static State getState() {
      State state = (State)threadLocalState.get();
      if (state == null) {
         state = new State();
         threadLocalState.set(state);
      }

      return state;
   }

   private static StringBuilder getSB() {
      return Constants.ENABLE_THREADLOCALS ? getState().getStringBuilder() : webSafeState.getStringBuilder();
   }

   static int getRingbufferSize() {
      return RINGBUFFER_SIZE;
   }

   static {
      MASK = RINGBUFFER_SIZE - 1;
      threadLocalState = new ThreadLocal();
      webSafeState = new WebSafeState();
   }

   private static class WebSafeState {
      private final ThreadLocal ringBuffer;
      private final ThreadLocal current;

      private WebSafeState() {
         this.ringBuffer = new ThreadLocal();
         this.current = new ThreadLocal();
      }

      public StringBuilder getStringBuilder() {
         StringBuilder[] array = (StringBuilder[])this.ringBuffer.get();
         if (array == null) {
            array = new StringBuilder[Unbox.RINGBUFFER_SIZE];

            for(int i = 0; i < array.length; ++i) {
               array[i] = new StringBuilder(21);
            }

            this.ringBuffer.set(array);
            this.current.set(new int[1]);
         }

         int[] index = (int[])this.current.get();
         int var10001 = Unbox.MASK;
         int var10005 = index[0];
         int var10002 = index[0];
         index[0] = var10005 + 1;
         StringBuilder result = array[var10001 & var10002];
         result.setLength(0);
         return result;
      }
   }

   private static class State {
      private final StringBuilder[] ringBuffer;
      private int current;

      State() {
         this.ringBuffer = new StringBuilder[Unbox.RINGBUFFER_SIZE];

         for(int i = 0; i < this.ringBuffer.length; ++i) {
            this.ringBuffer[i] = new StringBuilder(21);
         }

      }

      public StringBuilder getStringBuilder() {
         StringBuilder result = this.ringBuffer[Unbox.MASK & this.current++];
         result.setLength(0);
         return result;
      }
   }
}
