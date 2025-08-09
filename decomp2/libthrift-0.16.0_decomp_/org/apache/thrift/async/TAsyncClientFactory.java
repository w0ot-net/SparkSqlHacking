package org.apache.thrift.async;

import org.apache.thrift.transport.TNonblockingTransport;

public interface TAsyncClientFactory {
   TAsyncClient getAsyncClient(TNonblockingTransport var1);
}
