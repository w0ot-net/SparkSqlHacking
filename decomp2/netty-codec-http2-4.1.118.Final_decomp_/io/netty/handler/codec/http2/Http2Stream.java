package io.netty.handler.codec.http2;

public interface Http2Stream {
   int id();

   State state();

   Http2Stream open(boolean var1) throws Http2Exception;

   Http2Stream close();

   Http2Stream closeLocalSide();

   Http2Stream closeRemoteSide();

   boolean isResetSent();

   Http2Stream resetSent();

   Object setProperty(Http2Connection.PropertyKey var1, Object var2);

   Object getProperty(Http2Connection.PropertyKey var1);

   Object removeProperty(Http2Connection.PropertyKey var1);

   Http2Stream headersSent(boolean var1);

   boolean isHeadersSent();

   boolean isTrailersSent();

   Http2Stream headersReceived(boolean var1);

   boolean isHeadersReceived();

   boolean isTrailersReceived();

   Http2Stream pushPromiseSent();

   boolean isPushPromiseSent();

   public static enum State {
      IDLE(false, false),
      RESERVED_LOCAL(false, false),
      RESERVED_REMOTE(false, false),
      OPEN(true, true),
      HALF_CLOSED_LOCAL(false, true),
      HALF_CLOSED_REMOTE(true, false),
      CLOSED(false, false);

      private final boolean localSideOpen;
      private final boolean remoteSideOpen;

      private State(boolean localSideOpen, boolean remoteSideOpen) {
         this.localSideOpen = localSideOpen;
         this.remoteSideOpen = remoteSideOpen;
      }

      public boolean localSideOpen() {
         return this.localSideOpen;
      }

      public boolean remoteSideOpen() {
         return this.remoteSideOpen;
      }
   }
}
