package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.util.List;

final class StreamingAeadHelper implements StreamingAead {
   private final List allPrimitives;
   private final StreamingAead primary;

   public StreamingAeadHelper(List allPrimitives, StreamingAead primary) throws GeneralSecurityException {
      this.allPrimitives = allPrimitives;
      this.primary = primary;
   }

   public WritableByteChannel newEncryptingChannel(WritableByteChannel ciphertextDestination, byte[] associatedData) throws GeneralSecurityException, IOException {
      return this.primary.newEncryptingChannel(ciphertextDestination, associatedData);
   }

   public ReadableByteChannel newDecryptingChannel(ReadableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
      return new ReadableByteChannelDecrypter(this.allPrimitives, ciphertextChannel, associatedData);
   }

   public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
      return new SeekableByteChannelDecrypter(this.allPrimitives, ciphertextChannel, associatedData);
   }

   public InputStream newDecryptingStream(InputStream ciphertextStream, byte[] associatedData) throws GeneralSecurityException, IOException {
      return new InputStreamDecrypter(this.allPrimitives, ciphertextStream, associatedData);
   }

   public OutputStream newEncryptingStream(OutputStream ciphertext, byte[] associatedData) throws GeneralSecurityException, IOException {
      return this.primary.newEncryptingStream(ciphertext, associatedData);
   }
}
