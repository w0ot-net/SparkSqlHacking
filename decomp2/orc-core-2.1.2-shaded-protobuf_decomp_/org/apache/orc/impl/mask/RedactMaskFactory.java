package org.apache.orc.impl.mask;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.io.Text;
import org.apache.orc.DataMask;
import org.apache.orc.TypeDescription;

public class RedactMaskFactory extends MaskFactory {
   private static final int UNMASKED_CHAR = "_".codePointAt(0);
   private static final int UNMASKED_DATE = -1;
   private static final int DEFAULT_LETTER_UPPER = "X".codePointAt(0);
   private static final int DEFAULT_LETTER_LOWER = "x".codePointAt(0);
   private static final int DEFAULT_NUMBER_DIGIT = 9;
   private static final int DEFAULT_NUMBER_DIGIT_CP = Integer.toString(9).codePointAt(0);
   private static final int DEFAULT_SYMBOL = "$".codePointAt(0);
   private static final int DEFAULT_PUNCTUATION = ".".codePointAt(0);
   private static final int DEFAULT_SEPARATOR;
   private static final int DEFAULT_LETTER_OTHER;
   private static final int DEFAULT_MARK;
   private static final int DEFAULT_NUMBER_OTHER;
   private static final int DEFAULT_OTHER;
   private final int UPPER_REPLACEMENT;
   private final int LOWER_REPLACEMENT;
   private final int OTHER_LETTER_REPLACEMENT;
   private final int MARK_REPLACEMENT;
   private final int DIGIT_CP_REPLACEMENT;
   private final int OTHER_NUMBER_REPLACEMENT;
   private final int SYMBOL_REPLACEMENT;
   private final int PUNCTUATION_REPLACEMENT;
   private final int SEPARATOR_REPLACEMENT;
   private final int OTHER_REPLACEMENT;
   private final int DIGIT_REPLACEMENT;
   private final int YEAR_REPLACEMENT;
   private final int MONTH_REPLACEMENT;
   private final int DATE_REPLACEMENT;
   private final int HOUR_REPLACEMENT;
   private final int MINUTE_REPLACEMENT;
   private final int SECOND_REPLACEMENT;
   private final boolean maskDate;
   private final boolean maskTimestamp;
   private final SortedMap unmaskIndexRanges = new TreeMap();
   private static final double[] DOUBLE_POWER_10;
   private final Calendar scratch = Calendar.getInstance();
   private static final long MILLIS_PER_DAY;
   private final Calendar utcScratch = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
   static final long OVERFLOW_REPLACEMENT = 111111111111111111L;

   public RedactMaskFactory(String... params) {
      ByteBuffer param = params.length < 1 ? ByteBuffer.allocate(0) : ByteBuffer.wrap(params[0].getBytes(StandardCharsets.UTF_8));
      this.UPPER_REPLACEMENT = getNextCodepoint(param, DEFAULT_LETTER_UPPER);
      this.LOWER_REPLACEMENT = getNextCodepoint(param, DEFAULT_LETTER_LOWER);
      this.DIGIT_CP_REPLACEMENT = getNextCodepoint(param, DEFAULT_NUMBER_DIGIT_CP);
      this.DIGIT_REPLACEMENT = getReplacementDigit(this.DIGIT_CP_REPLACEMENT);
      this.SYMBOL_REPLACEMENT = getNextCodepoint(param, DEFAULT_SYMBOL);
      this.PUNCTUATION_REPLACEMENT = getNextCodepoint(param, DEFAULT_PUNCTUATION);
      this.SEPARATOR_REPLACEMENT = getNextCodepoint(param, DEFAULT_SEPARATOR);
      this.OTHER_LETTER_REPLACEMENT = getNextCodepoint(param, DEFAULT_LETTER_OTHER);
      this.MARK_REPLACEMENT = getNextCodepoint(param, DEFAULT_MARK);
      this.OTHER_NUMBER_REPLACEMENT = getNextCodepoint(param, DEFAULT_NUMBER_OTHER);
      this.OTHER_REPLACEMENT = getNextCodepoint(param, DEFAULT_OTHER);
      String[] timeParams;
      if (params.length >= 2 && !params[1].isBlank()) {
         timeParams = params[1].split("\\W+");
      } else {
         timeParams = null;
      }

      this.YEAR_REPLACEMENT = getDateParam(timeParams, 0, -1, 4000);
      this.MONTH_REPLACEMENT = getDateParam(timeParams, 1, 1, 12);
      this.DATE_REPLACEMENT = getDateParam(timeParams, 2, 1, 31);
      this.HOUR_REPLACEMENT = getDateParam(timeParams, 3, 0, 23);
      this.MINUTE_REPLACEMENT = getDateParam(timeParams, 4, 0, 59);
      this.SECOND_REPLACEMENT = getDateParam(timeParams, 5, 0, 59);
      this.maskDate = this.YEAR_REPLACEMENT != -1 || this.MONTH_REPLACEMENT != -1 || this.DATE_REPLACEMENT != -1;
      this.maskTimestamp = this.maskDate || this.HOUR_REPLACEMENT != -1 || this.MINUTE_REPLACEMENT != -1 || this.SECOND_REPLACEMENT != -1;
      if (params.length >= 3 && !params[2].isBlank()) {
         String[] unmaskIndexes = params[2].split(",");

         for(int i = 0; i < unmaskIndexes.length; ++i) {
            String[] pair = unmaskIndexes[i].trim().split(":");
            this.unmaskIndexRanges.put(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
         }
      }

   }

   protected DataMask buildBooleanMask(TypeDescription schema) {
      return (DataMask)(this.DIGIT_CP_REPLACEMENT == UNMASKED_CHAR ? new LongIdentity() : new BooleanRedactConverter());
   }

   protected DataMask buildLongMask(TypeDescription schema) {
      return (DataMask)(this.DIGIT_CP_REPLACEMENT == UNMASKED_CHAR ? new LongIdentity() : new LongRedactConverter(schema.getCategory()));
   }

   protected DataMask buildDecimalMask(TypeDescription schema) {
      return (DataMask)(this.DIGIT_CP_REPLACEMENT == UNMASKED_CHAR ? new DecimalIdentity() : new DecimalRedactConverter());
   }

   protected DataMask buildDoubleMask(TypeDescription schema) {
      return (DataMask)(this.DIGIT_CP_REPLACEMENT == UNMASKED_CHAR ? new DoubleIdentity() : new DoubleRedactConverter());
   }

   protected DataMask buildStringMask(TypeDescription schema) {
      return new StringConverter();
   }

   protected DataMask buildDateMask(TypeDescription schema) {
      return (DataMask)(this.maskDate ? new DateRedactConverter() : new LongIdentity());
   }

   protected DataMask buildTimestampMask(TypeDescription schema) {
      return (DataMask)(this.maskTimestamp ? new TimestampRedactConverter() : new TimestampIdentity());
   }

   protected DataMask buildBinaryMask(TypeDescription schema) {
      return new NullifyMask();
   }

   static int getNextCodepoint(ByteBuffer param, int defaultValue) {
      return param.remaining() == 0 ? defaultValue : Text.bytesToCodePoint(param);
   }

   static int getReplacementDigit(int digitCodePoint) {
      int dig = Character.getNumericValue(digitCodePoint);
      return dig >= 0 && dig <= 9 ? dig : 9;
   }

   static int getDateParam(String[] dateParams, int posn, int myDefault, int max) {
      if (dateParams != null && posn < dateParams.length) {
         if (dateParams[posn].codePointAt(0) == UNMASKED_CHAR) {
            return -1;
         } else {
            int result = Integer.parseInt(dateParams[posn]);
            if (result >= -1 && result <= max) {
               return result;
            } else {
               throw new IllegalArgumentException("Invalid date parameter " + posn + " of " + dateParams[posn] + " greater than " + max);
            }
         }
      } else {
         return myDefault;
      }
   }

   public long maskLong(long value) {
      if (!this.unmaskIndexRanges.isEmpty()) {
         return this.maskLongWithUnmasking(value);
      } else if (this.DIGIT_REPLACEMENT == 0) {
         return 0L;
      } else {
         long base;
         if (value >= 0L) {
            base = 1L;
         } else {
            base = -1L;
            if (value == Long.MIN_VALUE) {
               value = Long.MAX_VALUE;
            } else {
               value = -value;
            }
         }

         if (value < 100000000L) {
            if (value < 10000L) {
               if (value < 100L) {
                  if (value < 10L) {
                     base *= 1L;
                  } else {
                     base *= 11L;
                  }
               } else if (value < 1000L) {
                  base *= 111L;
               } else {
                  base *= 1111L;
               }
            } else if (value < 1000000L) {
               if (value < 100000L) {
                  base *= 11111L;
               } else {
                  base *= 111111L;
               }
            } else if (value < 10000000L) {
               base *= 1111111L;
            } else {
               base *= 11111111L;
            }
         } else if (value < 10000000000000000L) {
            if (value < 1000000000000L) {
               if (value < 10000000000L) {
                  if (value < 1000000000L) {
                     base *= 111111111L;
                  } else {
                     base *= 1111111111L;
                  }
               } else if (value < 100000000000L) {
                  base *= 11111111111L;
               } else {
                  base *= 111111111111L;
               }
            } else if (value < 100000000000000L) {
               if (value < 10000000000000L) {
                  base *= 1111111111111L;
               } else {
                  base *= 11111111111111L;
               }
            } else if (value < 1000000000000000L) {
               base *= 111111111111111L;
            } else {
               base *= 1111111111111111L;
            }
         } else if (value < 100000000000000000L) {
            base *= 11111111111111111L;
         } else if (value >= 1000000000000000000L && this.DIGIT_REPLACEMENT != 9) {
            base *= 1111111111111111111L;
         } else {
            base *= 111111111111111111L;
         }

         return (long)this.DIGIT_REPLACEMENT * base;
      }
   }

   public double maskDouble(double value) {
      if (!this.unmaskIndexRanges.isEmpty()) {
         return this.maskDoubleWIthUnmasking(value);
      } else if (value != (double)0.0F && this.DIGIT_REPLACEMENT != 0) {
         double base;
         if (value > (double)0.0F) {
            base = 1.11111;
         } else {
            base = -1.11111;
            value = -value;
         }

         int posn = Arrays.binarySearch(DOUBLE_POWER_10, value);
         if (posn < -DOUBLE_POWER_10.length - 2) {
            posn = DOUBLE_POWER_10.length - 1;
         } else if (posn == -1) {
            posn = 0;
         } else if (posn < 0) {
            posn = -posn - 2;
         }

         return (double)this.DIGIT_REPLACEMENT * base * DOUBLE_POWER_10[posn];
      } else {
         return (double)this.DIGIT_REPLACEMENT * 1.11111;
      }
   }

   long maskTime(long millis) {
      this.scratch.setTimeInMillis(millis);
      if (this.YEAR_REPLACEMENT != -1) {
         this.scratch.set(1, this.YEAR_REPLACEMENT);
      }

      if (this.MONTH_REPLACEMENT != -1) {
         this.scratch.set(2, this.MONTH_REPLACEMENT - 1);
      }

      if (this.DATE_REPLACEMENT != -1) {
         this.scratch.set(5, this.DATE_REPLACEMENT);
      }

      if (this.HOUR_REPLACEMENT != -1) {
         if (this.HOUR_REPLACEMENT >= 12) {
            this.scratch.set(10, this.HOUR_REPLACEMENT - 12);
            this.scratch.set(9, 1);
         } else {
            this.scratch.set(10, this.HOUR_REPLACEMENT);
            this.scratch.set(9, 0);
         }
      }

      if (this.MINUTE_REPLACEMENT != -1) {
         this.scratch.set(12, this.MINUTE_REPLACEMENT);
      }

      if (this.SECOND_REPLACEMENT != -1) {
         this.scratch.set(13, this.SECOND_REPLACEMENT);
         this.scratch.set(14, 0);
      }

      return this.scratch.getTimeInMillis();
   }

   int maskDate(int daysSinceEpoch) {
      this.utcScratch.setTimeInMillis((long)daysSinceEpoch * MILLIS_PER_DAY);
      if (this.YEAR_REPLACEMENT != -1) {
         this.utcScratch.set(1, this.YEAR_REPLACEMENT);
      }

      if (this.MONTH_REPLACEMENT != -1) {
         this.utcScratch.set(2, this.MONTH_REPLACEMENT - 1);
      }

      if (this.DATE_REPLACEMENT != -1) {
         this.utcScratch.set(5, this.DATE_REPLACEMENT);
      }

      return (int)(this.utcScratch.getTimeInMillis() / MILLIS_PER_DAY);
   }

   HiveDecimalWritable maskDecimal(HiveDecimalWritable source) {
      return new HiveDecimalWritable(this.maskNumericString(source.toString()));
   }

   int getReplacement(int codepoint) {
      switch (Character.getType(codepoint)) {
         case 1:
            return this.UPPER_REPLACEMENT;
         case 2:
            return this.LOWER_REPLACEMENT;
         case 3:
         case 4:
         case 5:
            return this.OTHER_LETTER_REPLACEMENT;
         case 6:
         case 7:
         case 8:
            return this.MARK_REPLACEMENT;
         case 9:
            return this.DIGIT_CP_REPLACEMENT;
         case 10:
         case 11:
            return this.OTHER_NUMBER_REPLACEMENT;
         case 12:
         case 13:
         case 14:
            return this.SEPARATOR_REPLACEMENT;
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         default:
            return this.OTHER_REPLACEMENT;
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
            return this.PUNCTUATION_REPLACEMENT;
         case 25:
         case 26:
         case 27:
         case 28:
            return this.SYMBOL_REPLACEMENT;
      }
   }

   static int getCodepointLength(int codepoint) {
      if (codepoint < 0) {
         throw new IllegalArgumentException("Illegal codepoint " + codepoint);
      } else if (codepoint < 128) {
         return 1;
      } else if (codepoint < 2047) {
         return 2;
      } else if (codepoint < 65535) {
         return 3;
      } else if (codepoint < 1114111) {
         return 4;
      } else {
         throw new IllegalArgumentException("Illegal codepoint " + codepoint);
      }
   }

   static void writeCodepoint(int codepoint, byte[] buffer, int offset, int length) {
      switch (length) {
         case 1:
            buffer[offset] = (byte)codepoint;
            break;
         case 2:
            buffer[offset] = (byte)(192 | codepoint >> 6);
            buffer[offset + 1] = (byte)(128 | codepoint & 63);
            break;
         case 3:
            buffer[offset] = (byte)(224 | codepoint >> 12);
            buffer[offset + 1] = (byte)(128 | codepoint >> 6 & 63);
            buffer[offset + 2] = (byte)(128 | codepoint & 63);
            break;
         case 4:
            buffer[offset] = (byte)(240 | codepoint >> 18);
            buffer[offset + 1] = (byte)(128 | codepoint >> 12 & 63);
            buffer[offset + 2] = (byte)(128 | codepoint >> 6 & 63);
            buffer[offset + 3] = (byte)(128 | codepoint & 63);
            break;
         default:
            throw new IllegalArgumentException("Invalid length for codepoint " + codepoint + " = " + length);
      }

   }

   void maskString(BytesColumnVector source, int row, BytesColumnVector target) {
      int expectedBytes = source.length[row];
      ByteBuffer sourceBytes = ByteBuffer.wrap(source.vector[row], source.start[row], source.length[row]);
      target.ensureValPreallocated(expectedBytes);
      byte[] outputBuffer = target.getValPreallocatedBytes();
      int outputOffset = target.getValPreallocatedStart();
      int outputStart = outputOffset;

      int len;
      for(int index = 0; sourceBytes.remaining() > 0; outputOffset += len) {
         int cp = Text.bytesToCodePoint(sourceBytes);
         int replacement = this.getReplacement(cp);
         if (replacement == UNMASKED_CHAR || this.isIndexInUnmaskRange(index, source.length[row])) {
            replacement = cp;
         }

         ++index;
         len = getCodepointLength(replacement);
         if (len + outputOffset > outputBuffer.length) {
            int currentOutputStart = outputStart;
            int currentOutputLength = outputOffset - outputStart;
            expectedBytes = currentOutputLength + len + sourceBytes.remaining() * 4;
            target.ensureValPreallocated(expectedBytes);
            byte[] oldBuffer = outputBuffer;
            outputBuffer = target.getValPreallocatedBytes();
            outputOffset = target.getValPreallocatedStart();
            outputStart = outputOffset;
            System.arraycopy(oldBuffer, currentOutputStart, outputBuffer, outputOffset, currentOutputLength);
            outputOffset += currentOutputLength;
         }

         writeCodepoint(replacement, outputBuffer, outputOffset, len);
      }

      target.setValPreallocated(row, outputOffset - outputStart);
   }

   long maskLongWithUnmasking(long value) throws IndexOutOfBoundsException {
      try {
         return Long.parseLong(this.maskNumericString(Long.toString(value)));
      } catch (NumberFormatException var4) {
         return 111111111111111111L * (long)this.DIGIT_REPLACEMENT;
      }
   }

   double maskDoubleWIthUnmasking(double value) {
      try {
         return Double.parseDouble(this.maskNumericString(Double.toString(value)));
      } catch (NumberFormatException var4) {
         return (double)(111111111111111111L * (long)this.DIGIT_REPLACEMENT);
      }
   }

   String maskNumericString(String value) {
      StringBuilder result = new StringBuilder();
      int length = value.codePointCount(0, value.length());

      for(int c = 0; c < length; ++c) {
         int cp = value.codePointAt(c);
         if (!this.isIndexInUnmaskRange(c, length) && Character.getType(cp) == 9) {
            result.appendCodePoint(this.DIGIT_CP_REPLACEMENT);
         } else {
            result.appendCodePoint(cp);
         }
      }

      return result.toString();
   }

   private boolean isIndexInUnmaskRange(int index, int length) {
      for(Map.Entry pair : this.unmaskIndexRanges.entrySet()) {
         int start;
         if ((Integer)pair.getKey() >= 0) {
            start = (Integer)pair.getKey();
         } else {
            start = length + (Integer)pair.getKey();
         }

         int end;
         if ((Integer)pair.getValue() >= 0) {
            end = (Integer)pair.getValue();
         } else {
            end = length + (Integer)pair.getValue();
         }

         if (index >= start && index <= end) {
            return true;
         }
      }

      return false;
   }

   static {
      DEFAULT_SEPARATOR = UNMASKED_CHAR;
      DEFAULT_LETTER_OTHER = "ª".codePointAt(0);
      DEFAULT_MARK = "ः".codePointAt(0);
      DEFAULT_NUMBER_OTHER = "²".codePointAt(0);
      DEFAULT_OTHER = "\u06dd".codePointAt(0);
      DOUBLE_POWER_10 = new double[]{1.0E-308, 1.0E-307, 1.0E-306, 1.0E-305, 1.0E-304, 1.0E-303, 1.0E-302, 1.0E-301, 1.0E-300, 1.0E-299, 1.0E-298, 1.0E-297, 1.0E-296, 1.0E-295, 1.0E-294, 1.0E-293, 1.0E-292, 1.0E-291, 1.0E-290, 1.0E-289, 1.0E-288, 1.0E-287, 1.0E-286, 1.0E-285, 1.0E-284, 1.0E-283, 1.0E-282, 1.0E-281, 1.0E-280, 1.0E-279, 1.0E-278, 1.0E-277, 1.0E-276, 1.0E-275, 1.0E-274, 1.0E-273, 1.0E-272, 1.0E-271, 1.0E-270, 1.0E-269, 1.0E-268, 1.0E-267, 1.0E-266, 1.0E-265, 1.0E-264, 1.0E-263, 1.0E-262, 1.0E-261, 1.0E-260, 1.0E-259, 1.0E-258, 1.0E-257, 1.0E-256, 1.0E-255, 1.0E-254, 1.0E-253, 1.0E-252, 1.0E-251, 1.0E-250, 1.0E-249, 1.0E-248, 1.0E-247, 1.0E-246, 1.0E-245, 1.0E-244, 1.0E-243, 1.0E-242, 1.0E-241, 1.0E-240, 1.0E-239, 1.0E-238, 1.0E-237, 1.0E-236, 1.0E-235, 1.0E-234, 1.0E-233, 1.0E-232, 1.0E-231, 1.0E-230, 1.0E-229, 1.0E-228, 1.0E-227, 1.0E-226, 1.0E-225, 1.0E-224, 1.0E-223, 1.0E-222, 1.0E-221, 1.0E-220, 1.0E-219, 1.0E-218, 1.0E-217, 1.0E-216, 1.0E-215, 1.0E-214, 1.0E-213, 1.0E-212, 1.0E-211, 1.0E-210, 1.0E-209, 1.0E-208, 1.0E-207, 1.0E-206, 1.0E-205, 1.0E-204, 1.0E-203, 1.0E-202, 1.0E-201, 1.0E-200, 1.0E-199, 1.0E-198, 1.0E-197, 1.0E-196, 1.0E-195, 1.0E-194, 1.0E-193, 1.0E-192, 1.0E-191, 1.0E-190, 1.0E-189, 1.0E-188, 1.0E-187, 1.0E-186, 1.0E-185, 1.0E-184, 1.0E-183, 1.0E-182, 1.0E-181, 1.0E-180, 1.0E-179, 1.0E-178, 1.0E-177, 1.0E-176, 1.0E-175, 1.0E-174, 1.0E-173, 1.0E-172, 1.0E-171, 1.0E-170, 1.0E-169, 1.0E-168, 1.0E-167, 1.0E-166, 1.0E-165, 1.0E-164, 1.0E-163, 1.0E-162, 1.0E-161, 1.0E-160, 1.0E-159, 1.0E-158, 1.0E-157, 1.0E-156, 1.0E-155, 1.0E-154, 1.0E-153, 1.0E-152, 1.0E-151, 1.0E-150, 1.0E-149, 1.0E-148, 1.0E-147, 1.0E-146, 1.0E-145, 1.0E-144, 1.0E-143, 1.0E-142, 1.0E-141, 1.0E-140, 1.0E-139, 1.0E-138, 1.0E-137, 1.0E-136, 1.0E-135, 1.0E-134, 1.0E-133, 1.0E-132, 1.0E-131, 1.0E-130, 1.0E-129, 1.0E-128, 1.0E-127, 1.0E-126, 1.0E-125, 1.0E-124, 1.0E-123, 1.0E-122, 1.0E-121, 1.0E-120, 1.0E-119, 1.0E-118, 1.0E-117, 1.0E-116, 1.0E-115, 1.0E-114, 1.0E-113, 1.0E-112, 1.0E-111, 1.0E-110, 1.0E-109, 1.0E-108, 1.0E-107, 1.0E-106, 1.0E-105, 1.0E-104, 1.0E-103, 1.0E-102, 1.0E-101, 1.0E-100, 1.0E-99, 1.0E-98, 1.0E-97, 1.0E-96, 1.0E-95, 1.0E-94, 1.0E-93, 1.0E-92, 1.0E-91, 1.0E-90, 1.0E-89, 1.0E-88, 1.0E-87, 1.0E-86, 1.0E-85, 1.0E-84, 1.0E-83, 1.0E-82, 1.0E-81, 1.0E-80, 1.0E-79, 1.0E-78, 1.0E-77, 1.0E-76, 1.0E-75, 1.0E-74, 1.0E-73, 1.0E-72, 1.0E-71, 1.0E-70, 1.0E-69, 1.0E-68, 1.0E-67, 1.0E-66, 1.0E-65, 1.0E-64, 1.0E-63, 1.0E-62, 1.0E-61, 1.0E-60, 1.0E-59, 1.0E-58, 1.0E-57, 1.0E-56, 1.0E-55, 1.0E-54, 1.0E-53, 1.0E-52, 1.0E-51, 1.0E-50, 1.0E-49, 1.0E-48, 1.0E-47, 1.0E-46, 1.0E-45, 1.0E-44, 1.0E-43, 1.0E-42, 1.0E-41, 1.0E-40, 1.0E-39, 1.0E-38, 1.0E-37, 1.0E-36, 1.0E-35, 1.0E-34, 1.0E-33, 1.0E-32, 1.0E-31, 1.0E-30, 1.0E-29, 1.0E-28, 1.0E-27, 1.0E-26, 1.0E-25, 1.0E-24, 1.0E-23, 1.0E-22, 1.0E-21, 1.0E-20, 1.0E-19, 1.0E-18, 1.0E-17, 1.0E-16, 1.0E-15, 1.0E-14, 1.0E-13, 1.0E-12, 1.0E-11, 1.0E-10, 1.0E-9, 1.0E-8, 1.0E-7, 1.0E-6, 1.0E-5, 1.0E-4, 0.001, 0.01, 0.1, (double)1.0F, (double)10.0F, (double)100.0F, (double)1000.0F, (double)10000.0F, (double)100000.0F, (double)1000000.0F, (double)1.0E7F, (double)1.0E8F, (double)1.0E9F, (double)1.0E10F, 1.0E11, 1.0E12, 1.0E13, 1.0E14, 1.0E15, 1.0E16, 1.0E17, 1.0E18, 1.0E19, 1.0E20, 1.0E21, 1.0E22, 9.999999999999999E22, 1.0E24, 1.0E25, 1.0E26, 1.0E27, 1.0E28, 1.0E29, 1.0E30, 1.0E31, 1.0E32, 1.0E33, 1.0E34, 1.0E35, 1.0E36, 1.0E37, 1.0E38, 1.0E39, 1.0E40, 1.0E41, 1.0E42, 1.0E43, 1.0E44, 1.0E45, 1.0E46, 1.0E47, 1.0E48, 1.0E49, 1.0E50, 1.0E51, 1.0E52, 1.0E53, 1.0E54, 1.0E55, 1.0E56, 1.0E57, 1.0E58, 1.0E59, 1.0E60, 1.0E61, 1.0E62, 1.0E63, 1.0E64, 1.0E65, 1.0E66, 1.0E67, 1.0E68, 1.0E69, 1.0E70, 1.0E71, 1.0E72, 1.0E73, 1.0E74, 1.0E75, 1.0E76, 1.0E77, 1.0E78, 1.0E79, 1.0E80, 1.0E81, 1.0E82, 1.0E83, 1.0E84, 1.0E85, 1.0E86, 1.0E87, 1.0E88, 1.0E89, 1.0E90, 1.0E91, 1.0E92, 1.0E93, 1.0E94, 1.0E95, 1.0E96, 1.0E97, 1.0E98, 1.0E99, 1.0E100, 1.0E101, 1.0E102, 1.0E103, 1.0E104, 1.0E105, 1.0E106, 1.0E107, 1.0E108, 1.0E109, 1.0E110, 1.0E111, 1.0E112, 1.0E113, 1.0E114, 1.0E115, 1.0E116, 1.0E117, 1.0E118, 1.0E119, 1.0E120, 1.0E121, 1.0E122, 1.0E123, 1.0E124, 1.0E125, 1.0E126, 1.0E127, 1.0E128, 1.0E129, 1.0E130, 1.0E131, 1.0E132, 1.0E133, 1.0E134, 1.0E135, 1.0E136, 1.0E137, 1.0E138, 1.0E139, 1.0E140, 1.0E141, 1.0E142, 1.0E143, 1.0E144, 1.0E145, 1.0E146, 1.0E147, 1.0E148, 1.0E149, 1.0E150, 1.0E151, 1.0E152, 1.0E153, 1.0E154, 1.0E155, 1.0E156, 1.0E157, 1.0E158, 1.0E159, 1.0E160, 1.0E161, 1.0E162, 1.0E163, 1.0E164, 1.0E165, 1.0E166, 1.0E167, 1.0E168, 1.0E169, 1.0E170, 1.0E171, 1.0E172, 1.0E173, 1.0E174, 1.0E175, 1.0E176, 1.0E177, 1.0E178, 1.0E179, 1.0E180, 1.0E181, 1.0E182, 1.0E183, 1.0E184, 1.0E185, 1.0E186, 1.0E187, 1.0E188, 1.0E189, 1.0E190, 1.0E191, 1.0E192, 1.0E193, 1.0E194, 1.0E195, 1.0E196, 1.0E197, 1.0E198, 1.0E199, 1.0E200, 1.0E201, 1.0E202, 1.0E203, 1.0E204, 1.0E205, 1.0E206, 1.0E207, 1.0E208, 1.0E209, 1.0E210, 1.0E211, 1.0E212, 1.0E213, 1.0E214, 1.0E215, 1.0E216, 1.0E217, 1.0E218, 1.0E219, 1.0E220, 1.0E221, 1.0E222, 1.0E223, 1.0E224, 1.0E225, 1.0E226, 1.0E227, 1.0E228, 1.0E229, 1.0E230, 1.0E231, 1.0E232, 1.0E233, 1.0E234, 1.0E235, 1.0E236, 1.0E237, 1.0E238, 1.0E239, 1.0E240, 1.0E241, 1.0E242, 1.0E243, 1.0E244, 1.0E245, 1.0E246, 1.0E247, 1.0E248, 1.0E249, 1.0E250, 1.0E251, 1.0E252, 1.0E253, 1.0E254, 1.0E255, 1.0E256, 1.0E257, 1.0E258, 1.0E259, 1.0E260, 1.0E261, 1.0E262, 1.0E263, 1.0E264, 1.0E265, 1.0E266, 1.0E267, 1.0E268, 1.0E269, 1.0E270, 1.0E271, 1.0E272, 1.0E273, 1.0E274, 1.0E275, 1.0E276, 1.0E277, 1.0E278, 1.0E279, 1.0E280, 1.0E281, 1.0E282, 1.0E283, 1.0E284, 1.0E285, 1.0E286, 1.0E287, 1.0E288, 1.0E289, 1.0E290, 1.0E291, 1.0E292, 1.0E293, 1.0E294, 1.0E295, 1.0E296, 1.0E297, 1.0E298, 1.0E299, 1.0E300, 1.0E301, 1.0E302, 1.0E303, 1.0E304, 1.0E305, 1.0E306, 1.0E307};
      MILLIS_PER_DAY = TimeUnit.DAYS.toMillis(1L);
   }

   class LongRedactConverter implements DataMask {
      final long mask;

      LongRedactConverter(TypeDescription.Category category) {
         switch (category) {
            case BYTE:
               this.mask = 255L;
               break;
            case SHORT:
               this.mask = 65535L;
               break;
            case INT:
               this.mask = -1L;
               break;
            case LONG:
            default:
               this.mask = -1L;
         }

      }

      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         LongColumnVector target = (LongColumnVector)masked;
         LongColumnVector source = (LongColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.vector[0] = RedactMaskFactory.this.maskLong(source.vector[0]) & this.mask;
            target.isNull[0] = source.isNull[0];
         } else {
            for(int r = start; r < start + length; ++r) {
               target.vector[r] = RedactMaskFactory.this.maskLong(source.vector[r]) & this.mask;
               target.isNull[r] = source.isNull[r];
            }
         }

      }
   }

   class BooleanRedactConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         LongColumnVector target = (LongColumnVector)masked;
         LongColumnVector source = (LongColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.vector[0] = RedactMaskFactory.this.DIGIT_REPLACEMENT == 0 ? 0L : 1L;
            target.isNull[0] = source.isNull[0];
         } else {
            for(int r = start; r < start + length; ++r) {
               target.vector[r] = RedactMaskFactory.this.DIGIT_REPLACEMENT == 0 ? 0L : 1L;
               target.isNull[r] = source.isNull[r];
            }
         }

      }
   }

   class DoubleRedactConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         DoubleColumnVector target = (DoubleColumnVector)masked;
         DoubleColumnVector source = (DoubleColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.vector[0] = RedactMaskFactory.this.maskDouble(source.vector[0]);
            target.isNull[0] = source.isNull[0];
         } else {
            for(int r = start; r < start + length; ++r) {
               target.vector[r] = RedactMaskFactory.this.maskDouble(source.vector[r]);
               target.isNull[r] = source.isNull[r];
            }
         }

      }
   }

   class StringConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         BytesColumnVector target = (BytesColumnVector)masked;
         BytesColumnVector source = (BytesColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               RedactMaskFactory.this.maskString(source, 0, target);
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  RedactMaskFactory.this.maskString(source, r, target);
               }
            }
         }

      }
   }

   class DecimalRedactConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         DecimalColumnVector target = (DecimalColumnVector)masked;
         DecimalColumnVector source = (DecimalColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         target.scale = source.scale;
         target.precision = source.precision;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               target.vector[0].set(RedactMaskFactory.this.maskDecimal(source.vector[0]));
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  target.vector[r].set(RedactMaskFactory.this.maskDecimal(source.vector[r]));
               }
            }
         }

      }
   }

   class TimestampRedactConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         TimestampColumnVector target = (TimestampColumnVector)masked;
         TimestampColumnVector source = (TimestampColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               target.time[0] = RedactMaskFactory.this.maskTime(source.time[0]);
               target.nanos[0] = 0;
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  target.time[r] = RedactMaskFactory.this.maskTime(source.time[r]);
                  target.nanos[r] = 0;
               }
            }
         }

      }
   }

   class DateRedactConverter implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         LongColumnVector target = (LongColumnVector)masked;
         LongColumnVector source = (LongColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               target.vector[0] = (long)RedactMaskFactory.this.maskDate((int)source.vector[0]);
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  target.vector[r] = (long)RedactMaskFactory.this.maskDate((int)source.vector[r]);
               }
            }
         }

      }
   }
}
