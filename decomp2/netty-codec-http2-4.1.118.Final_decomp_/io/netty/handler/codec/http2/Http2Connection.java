package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;

public interface Http2Connection {
   Future close(Promise var1);

   PropertyKey newKey();

   void addListener(Listener var1);

   void removeListener(Listener var1);

   Http2Stream stream(int var1);

   boolean streamMayHaveExisted(int var1);

   Http2Stream connectionStream();

   int numActiveStreams();

   Http2Stream forEachActiveStream(Http2StreamVisitor var1) throws Http2Exception;

   boolean isServer();

   Endpoint local();

   Endpoint remote();

   boolean goAwayReceived();

   void goAwayReceived(int var1, long var2, ByteBuf var4) throws Http2Exception;

   boolean goAwaySent();

   boolean goAwaySent(int var1, long var2, ByteBuf var4) throws Http2Exception;

   public interface Endpoint {
      int incrementAndGetNextStreamId();

      boolean isValidStreamId(int var1);

      boolean mayHaveCreatedStream(int var1);

      boolean created(Http2Stream var1);

      boolean canOpenStream();

      Http2Stream createStream(int var1, boolean var2) throws Http2Exception;

      Http2Stream reservePushStream(int var1, Http2Stream var2) throws Http2Exception;

      boolean isServer();

      void allowPushTo(boolean var1);

      boolean allowPushTo();

      int numActiveStreams();

      int maxActiveStreams();

      void maxActiveStreams(int var1);

      int lastStreamCreated();

      int lastStreamKnownByPeer();

      Http2FlowController flowController();

      void flowController(Http2FlowController var1);

      Endpoint opposite();
   }

   public interface Listener {
      void onStreamAdded(Http2Stream var1);

      void onStreamActive(Http2Stream var1);

      void onStreamHalfClosed(Http2Stream var1);

      void onStreamClosed(Http2Stream var1);

      void onStreamRemoved(Http2Stream var1);

      void onGoAwaySent(int var1, long var2, ByteBuf var4);

      void onGoAwayReceived(int var1, long var2, ByteBuf var4);
   }

   public interface PropertyKey {
   }
}
