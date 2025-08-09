package org.apache.thrift.server;

class Invocation implements Runnable {
   private final AbstractNonblockingServer.FrameBuffer frameBuffer;

   public Invocation(AbstractNonblockingServer.FrameBuffer frameBuffer) {
      this.frameBuffer = frameBuffer;
   }

   public void run() {
      this.frameBuffer.invoke();
   }
}
