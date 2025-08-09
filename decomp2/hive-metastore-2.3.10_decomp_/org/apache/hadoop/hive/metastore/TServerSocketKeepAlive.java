package org.apache.hadoop.hive.metastore;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class TServerSocketKeepAlive extends TServerSocket {
   public TServerSocketKeepAlive(TServerSocket serverSocket) throws TTransportException {
      super(serverSocket.getServerSocket());
   }
}
