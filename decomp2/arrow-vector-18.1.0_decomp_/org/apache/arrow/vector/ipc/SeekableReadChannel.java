package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

public class SeekableReadChannel extends ReadChannel {
   private final SeekableByteChannel in;

   public SeekableReadChannel(SeekableByteChannel in) {
      super(in);
      this.in = in;
   }

   public void setPosition(long position) throws IOException {
      this.in.position(position);
   }

   public long size() throws IOException {
      return this.in.size();
   }
}
