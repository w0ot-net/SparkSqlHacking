package io.netty.handler.codec.http2;

public interface StreamByteDistributor {
   void updateStreamableBytes(StreamState var1);

   void updateDependencyTree(int var1, int var2, short var3, boolean var4);

   boolean distribute(int var1, Writer var2) throws Http2Exception;

   public interface StreamState {
      Http2Stream stream();

      long pendingBytes();

      boolean hasFrame();

      int windowSize();
   }

   public interface Writer {
      void write(Http2Stream var1, int var2);
   }
}
