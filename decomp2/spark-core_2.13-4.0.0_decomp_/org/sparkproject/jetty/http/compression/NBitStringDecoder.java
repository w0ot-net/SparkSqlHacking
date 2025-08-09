package org.sparkproject.jetty.http.compression;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.CharsetStringBuilder;

public class NBitStringDecoder {
   private final NBitIntegerDecoder _integerDecoder;
   private final HuffmanDecoder _huffmanBuilder;
   private final CharsetStringBuilder.Iso88591StringBuilder _builder;
   private boolean _huffman;
   private int _count;
   private int _length;
   private int _prefix;
   private State _state;

   public NBitStringDecoder() {
      this._state = NBitStringDecoder.State.PARSING;
      this._integerDecoder = new NBitIntegerDecoder();
      this._huffmanBuilder = new HuffmanDecoder();
      this._builder = new CharsetStringBuilder.Iso88591StringBuilder();
   }

   public void setPrefix(int prefix) {
      if (this._state != NBitStringDecoder.State.PARSING) {
         throw new IllegalStateException();
      } else {
         this._prefix = prefix;
      }
   }

   public String decode(ByteBuffer buffer) throws EncodingException {
      while(true) {
         switch (this._state.ordinal()) {
            case 0:
               byte firstByte = buffer.get(buffer.position());
               this._huffman = (128 >>> 8 - this._prefix & firstByte) != 0;
               this._state = NBitStringDecoder.State.LENGTH;
               this._integerDecoder.setPrefix(this._prefix - 1);
               break;
            case 1:
               this._length = this._integerDecoder.decodeInt(buffer);
               if (this._length < 0) {
                  return null;
               }

               this._state = NBitStringDecoder.State.VALUE;
               this._huffmanBuilder.setLength(this._length);
               break;
            case 2:
               String value = this._huffman ? this._huffmanBuilder.decode(buffer) : this.stringDecode(buffer);
               if (value != null) {
                  this.reset();
               }

               return value;
            default:
               throw new IllegalStateException(this._state.name());
         }
      }
   }

   private String stringDecode(ByteBuffer buffer) {
      while(this._count < this._length) {
         if (!buffer.hasRemaining()) {
            return null;
         }

         this._builder.append(buffer.get());
         ++this._count;
      }

      return this._builder.build();
   }

   public void reset() {
      this._state = NBitStringDecoder.State.PARSING;
      this._integerDecoder.reset();
      this._huffmanBuilder.reset();
      this._builder.reset();
      this._prefix = 0;
      this._count = 0;
      this._length = 0;
      this._huffman = false;
   }

   private static enum State {
      PARSING,
      LENGTH,
      VALUE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{PARSING, LENGTH, VALUE};
      }
   }
}
