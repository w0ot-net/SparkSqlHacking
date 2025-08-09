package org.apache.hadoop.hive.thrift.client;

import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import org.apache.hadoop.hive.thrift.TFilterTransport;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TUGIAssumingTransport extends TFilterTransport {
   protected UserGroupInformation ugi;

   public TUGIAssumingTransport(TTransport wrapped, UserGroupInformation ugi) {
      super(wrapped);
      this.ugi = ugi;
   }

   public void open() throws TTransportException {
      try {
         this.ugi.doAs(new PrivilegedExceptionAction() {
            public Void run() {
               try {
                  TUGIAssumingTransport.this.wrapped.open();
                  return null;
               } catch (TTransportException tte) {
                  throw new RuntimeException(tte);
               }
            }
         });
      } catch (IOException ioe) {
         throw new RuntimeException("Received an ioe we never threw!", ioe);
      } catch (InterruptedException ie) {
         throw new RuntimeException("Received an ie we never threw!", ie);
      } catch (RuntimeException rte) {
         if (rte.getCause() instanceof TTransportException) {
            throw (TTransportException)rte.getCause();
         } else {
            throw rte;
         }
      }
   }
}
