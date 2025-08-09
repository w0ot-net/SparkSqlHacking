package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

final class Http2MaxRstFrameDecoder extends DecoratingHttp2ConnectionDecoder {
   private final int maxRstFramesPerWindow;
   private final int secondsPerWindow;

   Http2MaxRstFrameDecoder(Http2ConnectionDecoder delegate, int maxRstFramesPerWindow, int secondsPerWindow) {
      super(delegate);
      this.maxRstFramesPerWindow = ObjectUtil.checkPositive(maxRstFramesPerWindow, "maxRstFramesPerWindow");
      this.secondsPerWindow = ObjectUtil.checkPositive(secondsPerWindow, "secondsPerWindow");
   }

   public void frameListener(Http2FrameListener listener) {
      if (listener != null) {
         super.frameListener(new Http2MaxRstFrameListener(listener, this.maxRstFramesPerWindow, this.secondsPerWindow));
      } else {
         super.frameListener((Http2FrameListener)null);
      }

   }

   public Http2FrameListener frameListener() {
      Http2FrameListener frameListener = this.frameListener0();
      return frameListener instanceof Http2MaxRstFrameListener ? ((Http2MaxRstFrameListener)frameListener).listener : frameListener;
   }

   Http2FrameListener frameListener0() {
      return super.frameListener();
   }
}
