package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.MathUtil;
import java.util.Map;

final class HpackEncoder {
   static final int NOT_FOUND = -1;
   static final int HUFF_CODE_THRESHOLD = 512;
   private final NameEntry[] nameEntries;
   private final NameValueEntry[] nameValueEntries;
   private final NameValueEntry head;
   private NameValueEntry latest;
   private final HpackHuffmanEncoder hpackHuffmanEncoder;
   private final byte hashMask;
   private final boolean ignoreMaxHeaderListSize;
   private final int huffCodeThreshold;
   private long size;
   private long maxHeaderTableSize;
   private long maxHeaderListSize;

   HpackEncoder() {
      this(false);
   }

   HpackEncoder(boolean ignoreMaxHeaderListSize) {
      this(ignoreMaxHeaderListSize, 64, 512);
   }

   HpackEncoder(boolean ignoreMaxHeaderListSize, int arraySizeHint, int huffCodeThreshold) {
      this.head = new NameValueEntry(-1, AsciiString.EMPTY_STRING, AsciiString.EMPTY_STRING, Integer.MAX_VALUE, (NameValueEntry)null);
      this.latest = this.head;
      this.hpackHuffmanEncoder = new HpackHuffmanEncoder();
      this.ignoreMaxHeaderListSize = ignoreMaxHeaderListSize;
      this.maxHeaderTableSize = 4096L;
      this.maxHeaderListSize = 4294967295L;
      this.nameEntries = new NameEntry[MathUtil.findNextPositivePowerOfTwo(Math.max(2, Math.min(arraySizeHint, 128)))];
      this.nameValueEntries = new NameValueEntry[this.nameEntries.length];
      this.hashMask = (byte)(this.nameEntries.length - 1);
      this.huffCodeThreshold = huffCodeThreshold;
   }

   public void encodeHeaders(int streamId, ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
      if (this.ignoreMaxHeaderListSize) {
         this.encodeHeadersIgnoreMaxHeaderListSize(out, headers, sensitivityDetector);
      } else {
         this.encodeHeadersEnforceMaxHeaderListSize(streamId, out, headers, sensitivityDetector);
      }

   }

   private void encodeHeadersEnforceMaxHeaderListSize(int streamId, ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
      long headerSize = 0L;

      for(Map.Entry header : headers) {
         CharSequence name = (CharSequence)header.getKey();
         CharSequence value = (CharSequence)header.getValue();
         headerSize += HpackHeaderField.sizeOf(name, value);
         if (headerSize > this.maxHeaderListSize) {
            Http2CodecUtil.headerListSizeExceeded(streamId, this.maxHeaderListSize, false);
         }
      }

      this.encodeHeadersIgnoreMaxHeaderListSize(out, headers, sensitivityDetector);
   }

   private void encodeHeadersIgnoreMaxHeaderListSize(ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
      for(Map.Entry header : headers) {
         CharSequence name = (CharSequence)header.getKey();
         CharSequence value = (CharSequence)header.getValue();
         this.encodeHeader(out, name, value, sensitivityDetector.isSensitive(name, value), HpackHeaderField.sizeOf(name, value));
      }

   }

   private void encodeHeader(ByteBuf out, CharSequence name, CharSequence value, boolean sensitive, long headerSize) {
      if (sensitive) {
         int nameIndex = this.getNameIndex(name);
         this.encodeLiteral(out, name, value, HpackUtil.IndexType.NEVER, nameIndex);
      } else if (this.maxHeaderTableSize == 0L) {
         int staticTableIndex = HpackStaticTable.getIndexInsensitive(name, value);
         if (staticTableIndex == -1) {
            int nameIndex = HpackStaticTable.getIndex(name);
            this.encodeLiteral(out, name, value, HpackUtil.IndexType.NONE, nameIndex);
         } else {
            encodeInteger(out, 128, 7, staticTableIndex);
         }

      } else if (headerSize > this.maxHeaderTableSize) {
         int nameIndex = this.getNameIndex(name);
         this.encodeLiteral(out, name, value, HpackUtil.IndexType.NONE, nameIndex);
      } else {
         int nameHash = AsciiString.hashCode(name);
         int valueHash = AsciiString.hashCode(value);
         NameValueEntry headerField = this.getEntryInsensitive(name, nameHash, value, valueHash);
         if (headerField != null) {
            encodeInteger(out, 128, 7, this.getIndexPlusOffset(headerField.counter));
         } else {
            int staticTableIndex = HpackStaticTable.getIndexInsensitive(name, value);
            if (staticTableIndex != -1) {
               encodeInteger(out, 128, 7, staticTableIndex);
            } else {
               this.ensureCapacity(headerSize);
               this.encodeAndAddEntries(out, name, nameHash, value, valueHash);
               this.size += headerSize;
            }
         }

      }
   }

   private void encodeAndAddEntries(ByteBuf out, CharSequence name, int nameHash, CharSequence value, int valueHash) {
      int staticTableIndex = HpackStaticTable.getIndex(name);
      int nextCounter = this.latestCounter() - 1;
      if (staticTableIndex == -1) {
         NameEntry e = this.getEntry(name, nameHash);
         if (e == null) {
            this.encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, -1);
            this.addNameEntry(name, nameHash, nextCounter);
            this.addNameValueEntry(name, value, nameHash, valueHash, nextCounter);
         } else {
            this.encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, this.getIndexPlusOffset(e.counter));
            this.addNameValueEntry(e.name, value, nameHash, valueHash, nextCounter);
            e.counter = nextCounter;
         }
      } else {
         this.encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, staticTableIndex);
         this.addNameValueEntry(HpackStaticTable.getEntry(staticTableIndex).name, value, nameHash, valueHash, nextCounter);
      }

   }

   public void setMaxHeaderTableSize(ByteBuf out, long maxHeaderTableSize) throws Http2Exception {
      if (maxHeaderTableSize >= 0L && maxHeaderTableSize <= 4294967295L) {
         if (this.maxHeaderTableSize != maxHeaderTableSize) {
            this.maxHeaderTableSize = maxHeaderTableSize;
            this.ensureCapacity(0L);
            encodeInteger(out, 32, 5, maxHeaderTableSize);
         }
      } else {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", 0L, 4294967295L, maxHeaderTableSize);
      }
   }

   public long getMaxHeaderTableSize() {
      return this.maxHeaderTableSize;
   }

   public void setMaxHeaderListSize(long maxHeaderListSize) throws Http2Exception {
      if (maxHeaderListSize >= 0L && maxHeaderListSize <= 4294967295L) {
         this.maxHeaderListSize = maxHeaderListSize;
      } else {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", 0L, 4294967295L, maxHeaderListSize);
      }
   }

   public long getMaxHeaderListSize() {
      return this.maxHeaderListSize;
   }

   private static void encodeInteger(ByteBuf out, int mask, int n, int i) {
      encodeInteger(out, mask, n, (long)i);
   }

   private static void encodeInteger(ByteBuf out, int mask, int n, long i) {
      assert n >= 0 && n <= 8 : "N: " + n;

      int nbits = 255 >>> 8 - n;
      if (i < (long)nbits) {
         out.writeByte((int)((long)mask | i));
      } else {
         out.writeByte(mask | nbits);

         long length;
         for(length = i - (long)nbits; (length & -128L) != 0L; length >>>= 7) {
            out.writeByte((int)(length & 127L | 128L));
         }

         out.writeByte((int)length);
      }

   }

   private void encodeStringLiteral(ByteBuf out, CharSequence string) {
      int huffmanLength;
      if (string.length() >= this.huffCodeThreshold && (huffmanLength = this.hpackHuffmanEncoder.getEncodedLength(string)) < string.length()) {
         encodeInteger(out, 128, 7, huffmanLength);
         this.hpackHuffmanEncoder.encode(out, string);
      } else {
         encodeInteger(out, 0, 7, string.length());
         if (string instanceof AsciiString) {
            AsciiString asciiString = (AsciiString)string;
            out.writeBytes(asciiString.array(), asciiString.arrayOffset(), asciiString.length());
         } else {
            out.writeCharSequence(string, CharsetUtil.ISO_8859_1);
         }
      }

   }

   private void encodeLiteral(ByteBuf out, CharSequence name, CharSequence value, HpackUtil.IndexType indexType, int nameIndex) {
      boolean nameIndexValid = nameIndex != -1;
      switch (indexType) {
         case INCREMENTAL:
            encodeInteger(out, 64, 6, nameIndexValid ? nameIndex : 0);
            break;
         case NONE:
            encodeInteger(out, 0, 4, nameIndexValid ? nameIndex : 0);
            break;
         case NEVER:
            encodeInteger(out, 16, 4, nameIndexValid ? nameIndex : 0);
            break;
         default:
            throw new Error("should not reach here");
      }

      if (!nameIndexValid) {
         this.encodeStringLiteral(out, name);
      }

      this.encodeStringLiteral(out, value);
   }

   private int getNameIndex(CharSequence name) {
      int index = HpackStaticTable.getIndex(name);
      if (index != -1) {
         return index;
      } else {
         NameEntry e = this.getEntry(name, AsciiString.hashCode(name));
         return e == null ? -1 : this.getIndexPlusOffset(e.counter);
      }
   }

   private void ensureCapacity(long headerSize) {
      while(this.maxHeaderTableSize - this.size < headerSize) {
         this.remove();
      }

   }

   int length() {
      return this.isEmpty() ? 0 : this.getIndex(this.head.after.counter);
   }

   long size() {
      return this.size;
   }

   HpackHeaderField getHeaderField(int index) {
      NameValueEntry entry;
      for(entry = this.head; index++ < this.length(); entry = entry.after) {
      }

      return entry;
   }

   private NameValueEntry getEntryInsensitive(CharSequence name, int nameHash, CharSequence value, int valueHash) {
      int h = hash(nameHash, valueHash);

      for(NameValueEntry e = this.nameValueEntries[this.bucket(h)]; e != null; e = e.next) {
         if (e.hash == h && HpackUtil.equalsVariableTime(value, e.value) && HpackUtil.equalsVariableTime(name, e.name)) {
            return e;
         }
      }

      return null;
   }

   private NameEntry getEntry(CharSequence name, int nameHash) {
      for(NameEntry e = this.nameEntries[this.bucket(nameHash)]; e != null; e = e.next) {
         if (e.hash == nameHash && HpackUtil.equalsConstantTime(name, e.name) != 0) {
            return e;
         }
      }

      return null;
   }

   private int getIndexPlusOffset(int counter) {
      return this.getIndex(counter) + HpackStaticTable.length;
   }

   private int getIndex(int counter) {
      return counter - this.latestCounter() + 1;
   }

   private int latestCounter() {
      return this.latest.counter;
   }

   private void addNameEntry(CharSequence name, int nameHash, int nextCounter) {
      int bucket = this.bucket(nameHash);
      this.nameEntries[bucket] = new NameEntry(nameHash, name, nextCounter, this.nameEntries[bucket]);
   }

   private void addNameValueEntry(CharSequence name, CharSequence value, int nameHash, int valueHash, int nextCounter) {
      int hash = hash(nameHash, valueHash);
      int bucket = this.bucket(hash);
      NameValueEntry e = new NameValueEntry(hash, name, value, nextCounter, this.nameValueEntries[bucket]);
      this.nameValueEntries[bucket] = e;
      this.latest.after = e;
      this.latest = e;
   }

   private void remove() {
      NameValueEntry eldest = this.head.after;
      this.removeNameValueEntry(eldest);
      this.removeNameEntryMatchingCounter(eldest.name, eldest.counter);
      this.head.after = eldest.after;
      eldest.unlink();
      this.size -= (long)eldest.size();
      if (this.isEmpty()) {
         this.latest = this.head;
      }

   }

   private boolean isEmpty() {
      return this.size == 0L;
   }

   private void removeNameValueEntry(NameValueEntry eldest) {
      int bucket = this.bucket(eldest.hash);
      NameValueEntry e = this.nameValueEntries[bucket];
      if (e == eldest) {
         this.nameValueEntries[bucket] = eldest.next;
      } else {
         while(e.next != eldest) {
            e = e.next;
         }

         e.next = eldest.next;
      }

   }

   private void removeNameEntryMatchingCounter(CharSequence name, int counter) {
      int hash = AsciiString.hashCode(name);
      int bucket = this.bucket(hash);
      NameEntry e = this.nameEntries[bucket];
      if (e != null) {
         if (counter == e.counter) {
            this.nameEntries[bucket] = e.next;
            e.unlink();
         } else {
            NameEntry prev = e;

            for(NameEntry var7 = e.next; var7 != null; var7 = var7.next) {
               if (counter == var7.counter) {
                  prev.next = var7.next;
                  var7.unlink();
                  break;
               }

               prev = var7;
            }
         }

      }
   }

   private int bucket(int h) {
      return h & this.hashMask;
   }

   private static int hash(int nameHash, int valueHash) {
      return 31 * nameHash + valueHash;
   }

   private static final class NameEntry {
      NameEntry next;
      final CharSequence name;
      final int hash;
      int counter;

      NameEntry(int hash, CharSequence name, int counter, NameEntry next) {
         this.hash = hash;
         this.name = name;
         this.counter = counter;
         this.next = next;
      }

      void unlink() {
         this.next = null;
      }
   }

   private static final class NameValueEntry extends HpackHeaderField {
      NameValueEntry after;
      NameValueEntry next;
      final int hash;
      final int counter;

      NameValueEntry(int hash, CharSequence name, CharSequence value, int counter, NameValueEntry next) {
         super(name, value);
         this.next = next;
         this.hash = hash;
         this.counter = counter;
      }

      void unlink() {
         this.after = null;
         this.next = null;
      }
   }
}
