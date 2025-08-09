package org.apache.thrift;

import org.apache.thrift.server.AbstractNonblockingServer;

public interface TAsyncProcessor {
   void process(AbstractNonblockingServer.AsyncFrameBuffer var1) throws TException;
}
