package org.apache.thrift.transport.sasl;

import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public interface FrameHeaderReader {
   int payloadSize();

   byte[] toBytes();

   boolean isComplete();

   void clear();

   boolean read(TTransport var1) throws TSaslNegotiationException, TTransportException;
}
