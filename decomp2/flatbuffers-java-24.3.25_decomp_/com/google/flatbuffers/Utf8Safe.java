package com.google.flatbuffers;

import java.nio.ByteBuffer;

public final class Utf8Safe extends Utf8 {
   private static int computeEncodedLength(CharSequence sequence) {
      int utf16Length = sequence.length();
      int utf8Length = utf16Length;

      int i;
      for(i = 0; i < utf16Length && sequence.charAt(i) < 128; ++i) {
      }

      while(i < utf16Length) {
         char c = sequence.charAt(i);
         if (c >= 2048) {
            utf8Length += encodedLengthGeneral(sequence, i);
            break;
         }

         utf8Length += 127 - c >>> 31;
         ++i;
      }

      if (utf8Length < utf16Length) {
         throw new IllegalArgumentException("UTF-8 length does not fit in int: " + ((long)utf8Length + 4294967296L));
      } else {
         return utf8Length;
      }
   }

   private static int encodedLengthGeneral(CharSequence sequence, int start) {
      int utf16Length = sequence.length();
      int utf8Length = 0;

      for(int i = start; i < utf16Length; ++i) {
         char c = sequence.charAt(i);
         if (c < 2048) {
            utf8Length += 127 - c >>> 31;
         } else {
            utf8Length += 2;
            if ('\ud800' <= c && c <= '\udfff') {
               int cp = Character.codePointAt(sequence, i);
               if (cp < 65536) {
                  throw new UnpairedSurrogateException(i, utf16Length);
               }

               ++i;
            }
         }
      }

      return utf8Length;
   }

   public static String decodeUtf8Array(byte[] bytes, int index, int size) {
      if ((index | size | bytes.length - index - size) < 0) {
         throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", bytes.length, index, size));
      } else {
         int offset = index;
         int limit = index + size;
         char[] resultArr = new char[size];
         int resultPos = 0;

         while(offset < limit) {
            byte b = bytes[offset];
            if (!Utf8.DecodeUtil.isOneByte(b)) {
               break;
            }

            ++offset;
            Utf8.DecodeUtil.handleOneByte(b, resultArr, resultPos++);
         }

         while(offset < limit) {
            byte byte1 = bytes[offset++];
            if (Utf8.DecodeUtil.isOneByte(byte1)) {
               Utf8.DecodeUtil.handleOneByte(byte1, resultArr, resultPos++);

               while(offset < limit) {
                  byte b = bytes[offset];
                  if (!Utf8.DecodeUtil.isOneByte(b)) {
                     break;
                  }

                  ++offset;
                  Utf8.DecodeUtil.handleOneByte(b, resultArr, resultPos++);
               }
            } else if (Utf8.DecodeUtil.isTwoBytes(byte1)) {
               if (offset >= limit) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleTwoBytes(byte1, bytes[offset++], resultArr, resultPos++);
            } else if (Utf8.DecodeUtil.isThreeBytes(byte1)) {
               if (offset >= limit - 1) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleThreeBytes(byte1, bytes[offset++], bytes[offset++], resultArr, resultPos++);
            } else {
               if (offset >= limit - 2) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleFourBytes(byte1, bytes[offset++], bytes[offset++], bytes[offset++], resultArr, resultPos++);
               ++resultPos;
            }
         }

         return new String(resultArr, 0, resultPos);
      }
   }

   public static String decodeUtf8Buffer(ByteBuffer buffer, int offset, int length) {
      if ((offset | length | buffer.limit() - offset - length) < 0) {
         throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", buffer.limit(), offset, length));
      } else {
         int limit = offset + length;
         char[] resultArr = new char[length];
         int resultPos = 0;

         while(offset < limit) {
            byte b = buffer.get(offset);
            if (!Utf8.DecodeUtil.isOneByte(b)) {
               break;
            }

            ++offset;
            Utf8.DecodeUtil.handleOneByte(b, resultArr, resultPos++);
         }

         while(offset < limit) {
            byte byte1 = buffer.get(offset++);
            if (Utf8.DecodeUtil.isOneByte(byte1)) {
               Utf8.DecodeUtil.handleOneByte(byte1, resultArr, resultPos++);

               while(offset < limit) {
                  byte b = buffer.get(offset);
                  if (!Utf8.DecodeUtil.isOneByte(b)) {
                     break;
                  }

                  ++offset;
                  Utf8.DecodeUtil.handleOneByte(b, resultArr, resultPos++);
               }
            } else if (Utf8.DecodeUtil.isTwoBytes(byte1)) {
               if (offset >= limit) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleTwoBytes(byte1, buffer.get(offset++), resultArr, resultPos++);
            } else if (Utf8.DecodeUtil.isThreeBytes(byte1)) {
               if (offset >= limit - 1) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleThreeBytes(byte1, buffer.get(offset++), buffer.get(offset++), resultArr, resultPos++);
            } else {
               if (offset >= limit - 2) {
                  throw new IllegalArgumentException("Invalid UTF-8");
               }

               Utf8.DecodeUtil.handleFourBytes(byte1, buffer.get(offset++), buffer.get(offset++), buffer.get(offset++), resultArr, resultPos++);
               ++resultPos;
            }
         }

         return new String(resultArr, 0, resultPos);
      }
   }

   public int encodedLength(CharSequence in) {
      return computeEncodedLength(in);
   }

   public String decodeUtf8(ByteBuffer buffer, int offset, int length) throws IllegalArgumentException {
      return buffer.hasArray() ? decodeUtf8Array(buffer.array(), buffer.arrayOffset() + offset, length) : decodeUtf8Buffer(buffer, offset, length);
   }

   private static void encodeUtf8Buffer(CharSequence in, ByteBuffer out) {
      int inLength = in.length();
      int outIx = out.position();
      int inIx = 0;

      try {
         char c;
         while(inIx < inLength && (c = in.charAt(inIx)) < 128) {
            out.put(outIx + inIx, (byte)c);
            ++inIx;
         }

         if (inIx == inLength) {
            out.position(outIx + inIx);
         } else {
            outIx += inIx;

            while(true) {
               if (inIx >= inLength) {
                  out.position(outIx);
                  return;
               }

               c = in.charAt(inIx);
               if (c < 128) {
                  out.put(outIx, (byte)c);
               } else if (c < 2048) {
                  out.put(outIx++, (byte)(192 | c >>> 6));
                  out.put(outIx, (byte)(128 | 63 & c));
               } else if (c >= '\ud800' && '\udfff' >= c) {
                  if (inIx + 1 == inLength) {
                     break;
                  }

                  ++inIx;
                  char low;
                  if (!Character.isSurrogatePair(c, low = in.charAt(inIx))) {
                     break;
                  }

                  int codePoint = Character.toCodePoint(c, low);
                  out.put(outIx++, (byte)(240 | codePoint >>> 18));
                  out.put(outIx++, (byte)(128 | 63 & codePoint >>> 12));
                  out.put(outIx++, (byte)(128 | 63 & codePoint >>> 6));
                  out.put(outIx, (byte)(128 | 63 & codePoint));
               } else {
                  out.put(outIx++, (byte)(224 | c >>> 12));
                  out.put(outIx++, (byte)(128 | 63 & c >>> 6));
                  out.put(outIx, (byte)(128 | 63 & c));
               }

               ++inIx;
               ++outIx;
            }

            throw new UnpairedSurrogateException(inIx, inLength);
         }
      } catch (IndexOutOfBoundsException var8) {
         int badWriteIndex = out.position() + Math.max(inIx, outIx - out.position() + 1);
         throw new ArrayIndexOutOfBoundsException("Failed writing " + in.charAt(inIx) + " at index " + badWriteIndex);
      }
   }

   private static int encodeUtf8Array(CharSequence in, byte[] out, int offset, int length) {
      int utf16Length = in.length();
      int j = offset;
      int i = 0;

      int limit;
      char c;
      for(limit = offset + length; i < utf16Length && i + j < limit && (c = in.charAt(i)) < 128; ++i) {
         out[j + i] = (byte)c;
      }

      if (i == utf16Length) {
         return j + utf16Length;
      } else {
         j += i;

         while(true) {
            if (i >= utf16Length) {
               return j;
            }

            c = in.charAt(i);
            if (c < 128 && j < limit) {
               out[j++] = (byte)c;
            } else if (c < 2048 && j <= limit - 2) {
               out[j++] = (byte)(960 | c >>> 6);
               out[j++] = (byte)(128 | 63 & c);
            } else if ((c < '\ud800' || '\udfff' < c) && j <= limit - 3) {
               out[j++] = (byte)(480 | c >>> 12);
               out[j++] = (byte)(128 | 63 & c >>> 6);
               out[j++] = (byte)(128 | 63 & c);
            } else {
               if (j > limit - 4) {
                  if ('\ud800' > c || c > '\udfff' || i + 1 != in.length() && Character.isSurrogatePair(c, in.charAt(i + 1))) {
                     throw new ArrayIndexOutOfBoundsException("Failed writing " + c + " at index " + j);
                  }

                  throw new UnpairedSurrogateException(i, utf16Length);
               }

               if (i + 1 == in.length()) {
                  break;
               }

               ++i;
               char low;
               if (!Character.isSurrogatePair(c, low = in.charAt(i))) {
                  break;
               }

               int codePoint = Character.toCodePoint(c, low);
               out[j++] = (byte)(240 | codePoint >>> 18);
               out[j++] = (byte)(128 | 63 & codePoint >>> 12);
               out[j++] = (byte)(128 | 63 & codePoint >>> 6);
               out[j++] = (byte)(128 | 63 & codePoint);
            }

            ++i;
         }

         throw new UnpairedSurrogateException(i - 1, utf16Length);
      }
   }

   public void encodeUtf8(CharSequence in, ByteBuffer out) {
      if (out.hasArray()) {
         int start = out.arrayOffset();
         int end = encodeUtf8Array(in, out.array(), start + out.position(), out.remaining());
         out.position(end - start);
      } else {
         encodeUtf8Buffer(in, out);
      }

   }

   static class UnpairedSurrogateException extends IllegalArgumentException {
      UnpairedSurrogateException(int index, int length) {
         super("Unpaired surrogate at index " + index + " of " + length);
      }
   }
}
