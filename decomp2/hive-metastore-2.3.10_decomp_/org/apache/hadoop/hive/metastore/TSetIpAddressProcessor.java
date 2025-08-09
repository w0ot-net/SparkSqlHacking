package org.apache.hadoop.hive.metastore;

import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TSetIpAddressProcessor extends ThriftHiveMetastore.Processor {
   public TSetIpAddressProcessor(ThriftHiveMetastore.Iface iface) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      super(iface);
   }

   public void process(TProtocol in, TProtocol out) throws TException {
      this.setIpAddress(in);
      super.process(in, out);
   }

   protected void setIpAddress(TProtocol in) {
      TTransport transport = in.getTransport();
      if (transport instanceof TSocket) {
         this.setIpAddress(((TSocket)transport).getSocket());
      }
   }

   protected void setIpAddress(Socket inSocket) {
      HiveMetaStore.HMSHandler.setThreadLocalIpAddress(inSocket.getInetAddress().getHostAddress());
   }
}
