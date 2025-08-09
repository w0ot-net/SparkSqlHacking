package org.datanucleus.store.valuegenerator;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import org.datanucleus.util.NucleusLogger;

class AUID implements Comparable {
   private static final int VERSION_RANDOM_NODE = 3;
   private static final int VARIANT_NCS = 0;
   private static final int VARIANT_DCE = 32768;
   private static final int VARIANT_MICROSOFT = 49152;
   private static final int VARIANT_RESERVED = 57344;
   private static final int CS_MASK_NCS = 32767;
   private static final int CS_MASK_DCE = 16383;
   private static final int CS_MASK_MICROSOFT = 8191;
   private static final int CS_MASK_RESERVED = 8191;
   private static final long MAXIMUM_ENTROPIC_TIME_MS = 5000L;
   private static final long TIME_SCALE = 10000L;
   private static final long UTC_OFFSET = (new GregorianCalendar()).getGregorianChange().getTime() * 10000L;
   private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
   private static State auidState = null;
   private long firstHalf;
   private long secondHalf;

   public AUID() {
      this.makeUnique(0, false);
   }

   protected AUID(int securityAttributes) {
      this.makeUnique(securityAttributes, true);
   }

   protected AUID(long time, int version, int clockSeq, int variant, long node) {
      this.packFirstHalf(time, version);
      this.packSecondHalf(clockSeq, variant, node);
   }

   protected AUID(long timeLow, long timeMid, long timeHiAndVersion, int clockSeqHiAndVariant, int clockSeqLow, long node) {
      this.packDCEFieldsFirstHalf(timeLow, timeMid, timeHiAndVersion);
      this.packDCEFieldsSecondHalf(clockSeqHiAndVariant, clockSeqLow, node);
   }

   protected AUID(AUID auid) {
      this.firstHalf = auid.firstHalf;
      this.secondHalf = auid.secondHalf;
   }

   public AUID(String auid) {
      try {
         this.firstHalf = this.parseFirstHalf(auid.subSequence(0, 18));
         this.secondHalf = this.parseSecondHalf(auid.subSequence(18, 36));
      } catch (IndexOutOfBoundsException var3) {
         throw new NumberFormatException();
      } catch (NumberFormatException var4) {
         throw new NumberFormatException();
      }
   }

   public AUID(CharSequence auid) {
      try {
         this.firstHalf = this.parseFirstHalf(auid.subSequence(0, 18));
         this.secondHalf = this.parseSecondHalf(auid.subSequence(18, 36));
      } catch (IndexOutOfBoundsException var3) {
         throw new NumberFormatException();
      } catch (NumberFormatException var4) {
         throw new NumberFormatException();
      }
   }

   public AUID(byte[] bytes) {
      this(bytes, 0);
   }

   public AUID(byte[] bytes, int offset) {
      long timeLow = getOctets(4, bytes, 0 + offset, true);
      long timeMid = getOctets(2, bytes, 4 + offset, true);
      long timeHAV = getOctets(2, bytes, 6 + offset, true);
      int csHAV = (int)getOctets(1, bytes, 8 + offset, true);
      int csLow = (int)getOctets(1, bytes, 9 + offset, true);
      long node = getOctets(6, bytes, 10 + offset, true);
      this.packDCEFieldsFirstHalf(timeLow, timeMid, timeHAV);
      this.packDCEFieldsSecondHalf(csHAV, csLow, node);
   }

   public static AUID parse(String auid) {
      return new AUID(auid);
   }

   public static AUID parse(CharSequence auid) {
      return new AUID(auid);
   }

   protected int identifyVariant(int clockSeqAndVariant) {
      if ((clockSeqAndVariant & Short.MIN_VALUE) == 0) {
         return 0;
      } else if ((clockSeqAndVariant & -16384) == 32768) {
         return 32768;
      } else if ((clockSeqAndVariant & -8192) == 49152) {
         return 0;
      } else if ((clockSeqAndVariant & -8192) == 57344) {
         return 57344;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public int getClockSeqMaskForVariant(int variant) {
      switch (variant) {
         case 0:
            return 32767;
         case 32768:
            return 16383;
         case 49152:
            return 8191;
         case 57344:
            return 8191;
         default:
            throw new IllegalArgumentException();
      }
   }

   protected long getCurrentTime() {
      return System.currentTimeMillis() * 10000L - UTC_OFFSET;
   }

   protected State loadState(State state) {
      State loadInto = state;
      if (state == null) {
         if (auidState == null) {
            loadInto = auidState = new State();
         }

         state = auidState;
      }

      if (loadInto != null) {
         if (loadInto.getRandom() == null) {
            loadInto.setRandom(new Random(entropicSeed(32, System.currentTimeMillis())));
         }

         loadInto.setLastTime(this.getCurrentTime());
         loadInto.setAdjustTime(0L);
         loadInto.setClockSequence(loadInto.getRandom().nextInt());
         loadInto.setNode(loadInto.getRandom().nextLong() & 281474976710655L);
         loadInto.setVersion(3);
         loadInto.setVariant(32768);
         loadInto.setIncludeSecurityAttributes(false);
      }

      return state;
   }

   protected void saveState(State state) {
   }

   protected byte[] getBytes(byte[] dst, int dstBegin, boolean bigendian) {
      if (dst == null) {
         dst = new byte[16];
         dstBegin = 0;
      }

      putOctets(this.getTimeLow(), 4, dst, dstBegin, bigendian);
      putOctets(this.getTimeMid(), 2, dst, dstBegin + 4, bigendian);
      putOctets(this.getTimeHighAndVersion(), 2, dst, dstBegin + 6, bigendian);
      putOctets((long)this.getClockSeqHighAndVariant(), 1, dst, dstBegin + 8, bigendian);
      putOctets((long)this.getClockSeqLow(), 1, dst, dstBegin + 9, bigendian);
      putOctets(this.getNode(), 6, dst, dstBegin + 10, bigendian);
      return dst;
   }

   public byte[] getBytes(byte[] dst, int dstBegin) {
      return this.getBytes(dst, dstBegin, true);
   }

   public StringBuilder toStringBuilder(StringBuilder sb) {
      if (sb == null) {
         sb = new StringBuilder();
      }

      this.toHex(sb, this.getTimeLow(), 8);
      sb.append('-');
      this.toHex(sb, this.getTimeMid(), 4);
      sb.append('-');
      this.toHex(sb, this.getTimeHighAndVersion(), 4);
      sb.append('-');
      this.toHex(sb, (long)this.getClockSeqAndVariant(), 4);
      sb.append('-');
      this.toHex(sb, this.getNode(), 12);
      return sb;
   }

   private void packFirstHalf(long time, int version) {
      this.firstHalf = (long)version << 60 | time & 1152921504606846975L;
   }

   private void packDCEFieldsFirstHalf(long timeLow, long timeMid, long timeHiAndVersion) {
      this.firstHalf = timeHiAndVersion << 48 | timeMid << 32 | timeLow;
   }

   private void packSecondHalf(int clockSeq, int variant, long node) {
      int csMasked = clockSeq & this.getClockSeqMaskForVariant(variant);
      int csLow = csMasked & 255;
      int csHigh = (variant | csMasked) >>> 8;
      this.secondHalf = node << 16 | (long)(csLow << 8) | (long)csHigh;
   }

   private void packDCEFieldsSecondHalf(int clockSeqHiAndVariant, int clockSeqLow, long node) {
      this.secondHalf = node << 16 | (long)(clockSeqLow << 8) | (long)clockSeqHiAndVariant;
   }

   private void makeUnique(int securityAttributes, boolean hasSecurityAttributes) {
      synchronized(AUID.class) {
         State state = this.loadState((State)null);
         long now = this.getCurrentTime();
         if (now < state.getLastTime()) {
            state.setClockSequence(state.getClockSequence() + 1);
            state.setAdjustTime(0L);
            state.setLastTime(now);
         } else if (now != state.getLastTime()) {
            if (now < state.getLastTime() + state.getAdjustTime()) {
               throw new IllegalStateException("Clock overrun occured.");
            }

            state.setAdjustTime(0L);
            state.setLastTime(now);
         }

         now += state.incrementAdjustTime();
         if (state.getIncludeSecurityAttributes()) {
            if (!hasSecurityAttributes) {
               throw new IllegalArgumentException("Required to include security attributes as declared in state.");
            }

            now = now & -4294967296L | (long)securityAttributes;
         } else if (hasSecurityAttributes) {
            throw new IllegalArgumentException("Cannot include security attributes if not declared in state.");
         }

         this.packFirstHalf(now, state.getVersion());
         this.packSecondHalf(state.getClockSequence(), state.getVariant(), state.getNode());
         this.saveState(state);
      }
   }

   private void toHex(StringBuilder result, long value, int nibbles) {
      if (nibbles > 0) {
         this.toHex(result, value >>> 4, nibbles - 1);
         result.append(HEX_CHARS[(int)value & 15]);
      }

   }

   private long parseNibble(char c) {
      switch (c) {
         case '0':
            return 0L;
         case '1':
            return 1L;
         case '2':
            return 2L;
         case '3':
            return 3L;
         case '4':
            return 4L;
         case '5':
            return 5L;
         case '6':
            return 6L;
         case '7':
            return 7L;
         case '8':
            return 8L;
         case '9':
            return 9L;
         case ':':
         case ';':
         case '<':
         case '=':
         case '>':
         case '?':
         case '@':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         default:
            throw new NumberFormatException();
         case 'A':
         case 'a':
            return 10L;
         case 'B':
         case 'b':
            return 11L;
         case 'C':
         case 'c':
            return 12L;
         case 'D':
         case 'd':
            return 13L;
         case 'E':
         case 'e':
            return 14L;
         case 'F':
         case 'f':
            return 15L;
      }
   }

   private void parseHyphen(char c) {
      if (c != '-') {
         throw new NumberFormatException();
      }
   }

   private long parseHex(CharSequence cs) {
      long retval = 0L;

      for(int i = 0; i < cs.length(); ++i) {
         retval = (retval << 4) + this.parseNibble(cs.charAt(i));
      }

      return retval;
   }

   private long parseFirstHalf(CharSequence charSequence) {
      long timeLow = this.parseHex(charSequence.subSequence(0, 8));
      this.parseHyphen(charSequence.charAt(8));
      long timeMid = this.parseHex(charSequence.subSequence(9, 13));
      this.parseHyphen(charSequence.charAt(13));
      long timeHi = this.parseHex(charSequence.subSequence(14, 18));
      return timeHi << 48 | timeMid << 32 | timeLow;
   }

   private long parseSecondHalf(CharSequence charSequence) {
      this.parseHyphen(charSequence.charAt(0));
      long clockSeq = this.parseHex(charSequence.subSequence(1, 5));
      this.parseHyphen(charSequence.charAt(5));
      long node = this.parseHex(charSequence.subSequence(6, 18));
      return node << 16 | (clockSeq & 255L) << 8 | clockSeq >>> 8;
   }

   protected static final long getOctets(int octets, byte[] bytes, int begin, boolean bigendian) {
      if (octets > 1) {
         return bigendian ? ((long)bytes[begin] & 255L) << 8 * (octets - 1) | getOctets(octets - 1, bytes, begin + 1, bigendian) : getOctets(octets - 1, bytes, begin, bigendian) | ((long)bytes[begin + octets - 1] & 255L) << 8 * (octets - 1);
      } else {
         return (long)bytes[begin] & 255L;
      }
   }

   protected static final void putOctets(long value, int octets, byte[] dst, int dstBegin, boolean bigendian) {
      if (bigendian) {
         if (octets > 1) {
            putOctets(value >>> 8, octets - 1, dst, dstBegin, bigendian);
         }

         dst[dstBegin + octets - 1] = (byte)((int)(value & 255L));
      } else {
         dst[dstBegin] = (byte)((int)(value & 255L));
         if (octets > 1) {
            putOctets(value >>> 8, octets - 1, dst, dstBegin + 1, bigendian);
         }
      }

   }

   public final long getTimeLow() {
      return this.firstHalf & -1L;
   }

   public final long getTimeMid() {
      return this.firstHalf >>> 32 & 65535L;
   }

   public final long getTimeHigh() {
      return this.firstHalf >>> 48 & 4095L;
   }

   public final long getTimeHighAndVersion() {
      return this.firstHalf >>> 48;
   }

   public final long getTime() {
      return this.firstHalf & 1152921504606846975L;
   }

   public final Date getDate() {
      return new Date((this.getTime() + UTC_OFFSET) / 10000L);
   }

   public final long getNanos() {
      return (this.getTime() + UTC_OFFSET) % 10000L;
   }

   public final int getVersion() {
      return (int)(this.firstHalf >>> 60);
   }

   public final int getClockSeqHighAndVariant() {
      return (int)(this.secondHalf & 255L);
   }

   public final int getClockSeqLow() {
      return (int)(this.secondHalf >>> 8 & 255L);
   }

   public final int getClockSeqAndVariant() {
      return this.getClockSeqHighAndVariant() << 8 | this.getClockSeqLow();
   }

   public final int getClockSeq() {
      int csv = this.getClockSeqAndVariant();
      return csv & this.getClockSeqMaskForVariant(this.identifyVariant(csv));
   }

   public final int getVariant() {
      return this.identifyVariant(this.getClockSeqAndVariant());
   }

   public final long getNode() {
      return this.secondHalf >>> 16;
   }

   public final byte[] getBytes() {
      return this.getBytes((byte[])null, 0);
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof AUID)) {
         return false;
      } else {
         AUID other = (AUID)obj;
         return this.firstHalf == other.firstHalf && this.secondHalf == other.secondHalf;
      }
   }

   public int hashCode() {
      return (int)(this.firstHalf ^ this.firstHalf >>> 32 ^ this.secondHalf ^ this.secondHalf >>> 32);
   }

   public String toString() {
      return this.toStringBuilder((StringBuilder)null).toString();
   }

   public int compareTo(Object o) {
      AUID other = (AUID)o;
      long cmp = this.getTimeLow() - other.getTimeLow();
      if (cmp != 0L) {
         cmp = this.getTimeMid() - other.getTimeMid();
         if (cmp != 0L) {
            cmp = this.getTimeHighAndVersion() - other.getTimeHighAndVersion();
            if (cmp != 0L) {
               cmp = (long)(this.getClockSeqHighAndVariant() - other.getClockSeqHighAndVariant());
               if (cmp != 0L) {
                  cmp = (long)(this.getClockSeqLow() - other.getClockSeqLow());
                  if (cmp != 0L) {
                     cmp = this.getNode() - other.getNode();
                  }
               }
            }
         }
      }

      return cmp == 0L ? 0 : (cmp < 0L ? -1 : 1);
   }

   private static long entropicSeed(int bits, long initialSeed) {
      if (bits > 63) {
         bits = 63;
      } else if (bits < 1) {
         bits = 1;
      }

      final long startTime = System.currentTimeMillis();
      final int[] counters = new int[bits + 1];
      final Random[] randoms = new Random[bits];
      final Thread[] threads = new Thread[bits];
      final int endvalue = bits * 128;
      final int lastindex = bits;
      Random random = new Random(initialSeed);

      for(final int i = 0; i < bits; ++i) {
         long nextSeed = random.nextLong();
         randoms[i] = new Random(nextSeed);
         threads[i] = new Thread() {
            public void run() {
               try {
                  while(counters[lastindex] < endvalue) {
                     long value = randoms[i].nextLong();
                     int loop = (int)(value & 255L) + 16;

                     for(int a = 0; a < loop; ++a) {
                        randoms[i].nextLong();
                        if (System.currentTimeMillis() - startTime > 5000L) {
                           break;
                        }
                     }

                     int var10002 = counters[i]++;
                     if (System.currentTimeMillis() - startTime > 5000L) {
                        break;
                     }
                  }
               } catch (Throwable t) {
                  NucleusLogger.VALUEGENERATION.error(t);
                  counters[i] = endvalue;
               } finally {
                  threads[i] = null;
               }

            }
         };
         threads[i].start();
      }

      for(int i = 0; i < bits; ++i) {
         while(counters[i] < bits) {
            Thread.yield();
            if (System.currentTimeMillis() - startTime > 5000L) {
               break;
            }
         }
      }

      while(counters[lastindex] < endvalue) {
         Thread.yield();
         int sum = 0;

         for(int i = 0; i < bits; ++i) {
            sum += counters[i];
         }

         counters[lastindex] = sum;
         if (System.currentTimeMillis() - startTime > 5000L) {
            break;
         }
      }

      for(int i = 0; i < bits; ++i) {
         while(threads[i] != null) {
            Thread.yield();
         }
      }

      long seed = 0L;

      for(int i = 0; i < bits; ++i) {
         seed += randoms[i].nextLong();
      }

      return seed;
   }

   protected static class State {
      private long lastTime;
      private long adjustTime;
      private int clockSequence;
      private long node;
      private int version;
      private int variant;
      private Random random;
      private boolean includeSecurityAttributes;

      public void setLastTime(long lastTime) {
         this.lastTime = lastTime;
      }

      public long getLastTime() {
         return this.lastTime;
      }

      public void setAdjustTime(long adjustTime) {
         this.adjustTime = adjustTime;
      }

      public long getAdjustTime() {
         return this.adjustTime;
      }

      public long incrementAdjustTime() {
         return (long)(this.adjustTime++);
      }

      public void setClockSequence(int clockSequence) {
         this.clockSequence = clockSequence;
      }

      public int getClockSequence() {
         return this.clockSequence;
      }

      public void setNode(long node) {
         this.node = node;
      }

      public long getNode() {
         return this.node;
      }

      public void setVersion(int version) {
         this.version = version;
      }

      public int getVersion() {
         return this.version;
      }

      public void setVariant(int variant) {
         this.variant = variant;
      }

      public int getVariant() {
         return this.variant;
      }

      public void setRandom(Random random) {
         this.random = random;
      }

      public Random getRandom() {
         return this.random;
      }

      public void setIncludeSecurityAttributes(boolean includeSecurityAttributes) {
         this.includeSecurityAttributes = includeSecurityAttributes;
      }

      public boolean getIncludeSecurityAttributes() {
         return this.includeSecurityAttributes;
      }
   }
}
