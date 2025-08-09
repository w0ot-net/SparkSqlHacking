package org.apache.hadoop.hive.thrift;

import com.google.common.collect.MapMaker;
import java.net.Socket;
import java.util.concurrent.ConcurrentMap;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportFactory;

public class TUGIContainingTransport extends TFilterTransport {
   private UserGroupInformation ugi;

   public TUGIContainingTransport(TTransport wrapped) {
      super(wrapped);
   }

   public UserGroupInformation getClientUGI() {
      return this.ugi;
   }

   public void setClientUGI(UserGroupInformation ugi) {
      this.ugi = ugi;
   }

   public Socket getSocket() {
      return this.wrapped instanceof TSocket ? ((TSocket)this.wrapped).getSocket() : null;
   }

   public static class Factory extends TTransportFactory {
      private static final ConcurrentMap transMap = (new MapMaker()).weakKeys().weakValues().makeMap();

      public TUGIContainingTransport getTransport(TTransport trans) {
         TUGIContainingTransport tugiTrans = (TUGIContainingTransport)transMap.get(trans);
         if (tugiTrans == null) {
            tugiTrans = new TUGIContainingTransport(trans);
            TUGIContainingTransport prev = (TUGIContainingTransport)transMap.putIfAbsent(trans, tugiTrans);
            if (prev != null) {
               return prev;
            }
         }

         return tugiTrans;
      }
   }
}
