package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.EncodingException;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteBase64UrlStreamEncoder implements Encoder {
   private final Encoder delegate;

   public ByteBase64UrlStreamEncoder(Encoder delegate) {
      this.delegate = (Encoder)Assert.notNull(delegate, "delegate cannot be null.");
   }

   public OutputStream encode(OutputStream outputStream) throws EncodingException {
      Assert.notNull(outputStream, "outputStream cannot be null.");
      return new TranslatingOutputStream(outputStream, this.delegate);
   }

   private static class TranslatingOutputStream extends FilteredOutputStream {
      private final OutputStream dst;
      private final Encoder delegate;

      public TranslatingOutputStream(OutputStream dst, Encoder delegate) {
         super(new ByteArrayOutputStream());
         this.dst = dst;
         this.delegate = delegate;
      }

      public void close() throws IOException {
         byte[] data = ((ByteArrayOutputStream)this.out).toByteArray();
         String s = (String)this.delegate.encode(data);
         this.dst.write(Strings.utf8(s));
         this.dst.flush();
         this.dst.close();
      }
   }
}
