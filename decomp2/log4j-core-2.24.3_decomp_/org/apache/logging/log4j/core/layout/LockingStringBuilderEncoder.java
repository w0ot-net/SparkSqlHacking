package org.apache.logging.log4j.core.layout;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Objects;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.status.StatusLogger;

public class LockingStringBuilderEncoder implements Encoder {
   private final Charset charset;
   private final CharsetEncoder charsetEncoder;
   private final CharBuffer cachedCharBuffer;

   public LockingStringBuilderEncoder(final Charset charset) {
      this(charset, Constants.ENCODER_CHAR_BUFFER_SIZE);
   }

   public LockingStringBuilderEncoder(final Charset charset, final int charBufferSize) {
      this.charset = (Charset)Objects.requireNonNull(charset, "charset");
      this.charsetEncoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
      this.cachedCharBuffer = CharBuffer.wrap(new char[charBufferSize]);
   }

   private CharBuffer getCharBuffer() {
      return this.cachedCharBuffer;
   }

   public void encode(final StringBuilder source, final ByteBufferDestination destination) {
      try {
         synchronized(destination) {
            TextEncoderHelper.encodeText(this.charsetEncoder, this.cachedCharBuffer, destination.getByteBuffer(), source, destination);
         }
      } catch (Exception ex) {
         this.logEncodeTextException(ex, source, destination);
         TextEncoderHelper.encodeTextFallBack(this.charset, source, destination);
      }

   }

   private void logEncodeTextException(final Exception ex, final StringBuilder text, final ByteBufferDestination destination) {
      StatusLogger.getLogger().error("Recovering from LockingStringBuilderEncoder.encode('{}') error", text, ex);
   }
}
