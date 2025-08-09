package org.apache.thrift.transport.sasl;

import org.apache.thrift.transport.TTransportException;

public interface SaslPeer {
   byte[] evaluate(byte[] var1) throws TSaslNegotiationException;

   boolean isAuthenticated();

   boolean isDataProtected();

   byte[] wrap(byte[] var1, int var2, int var3) throws TTransportException;

   default byte[] wrap(byte[] data) throws TTransportException {
      return this.wrap(data, 0, data.length);
   }

   byte[] unwrap(byte[] var1, int var2, int var3) throws TTransportException;

   default byte[] unwrap(byte[] data) throws TTransportException {
      return this.unwrap(data, 0, data.length);
   }

   void dispose();
}
