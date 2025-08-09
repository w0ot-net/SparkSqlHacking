package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

public class Utf8Old extends Utf8 {
   private static final ThreadLocal CACHE = new ThreadLocal() {
      protected Cache initialValue() {
         return new Cache();
      }
   };

   public int encodedLength(CharSequence in) {
      Cache cache = (Cache)CACHE.get();
      int estimated = (int)((float)in.length() * cache.encoder.maxBytesPerChar());
      if (cache.lastOutput == null || cache.lastOutput.capacity() < estimated) {
         cache.lastOutput = ByteBuffer.allocate(Math.max(128, estimated));
      }

      cache.lastOutput.clear();
      cache.lastInput = in;
      CharBuffer wrap = in instanceof CharBuffer ? (CharBuffer)in : CharBuffer.wrap(in);
      CoderResult result = cache.encoder.encode(wrap, cache.lastOutput, true);
      if (result.isError()) {
         try {
            result.throwException();
         } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("bad character encoding", e);
         }
      }

      cache.lastOutput.flip();
      return cache.lastOutput.remaining();
   }

   public void encodeUtf8(CharSequence in, ByteBuffer out) {
      Cache cache = (Cache)CACHE.get();
      if (cache.lastInput != in) {
         this.encodedLength(in);
      }

      out.put(cache.lastOutput);
   }

   public String decodeUtf8(ByteBuffer buffer, int offset, int length) {
      CharsetDecoder decoder = ((Cache)CACHE.get()).decoder;
      decoder.reset();
      buffer = buffer.duplicate();
      buffer.position(offset);
      buffer.limit(offset + length);

      try {
         CharBuffer result = decoder.decode(buffer);
         return result.toString();
      } catch (CharacterCodingException e) {
         throw new IllegalArgumentException("Bad encoding", e);
      }
   }

   private static class Cache {
      final CharsetEncoder encoder;
      final CharsetDecoder decoder;
      CharSequence lastInput = null;
      ByteBuffer lastOutput = null;

      Cache() {
         this.encoder = StandardCharsets.UTF_8.newEncoder();
         this.decoder = StandardCharsets.UTF_8.newDecoder();
      }
   }
}
