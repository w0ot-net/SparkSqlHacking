package org.apache.thrift;

import java.io.ByteArrayOutputStream;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransportException;

public class TSerializer {
   private final ByteArrayOutputStream baos_;
   private final TIOStreamTransport transport_;
   private TProtocol protocol_;

   public TSerializer() throws TTransportException {
      this(new TBinaryProtocol.Factory());
   }

   public TSerializer(TProtocolFactory protocolFactory) throws TTransportException {
      this.baos_ = new ByteArrayOutputStream();
      this.transport_ = new TIOStreamTransport(new TConfiguration(), this.baos_);
      this.protocol_ = protocolFactory.getProtocol(this.transport_);
   }

   public byte[] serialize(TBase base) throws TException {
      this.baos_.reset();
      base.write(this.protocol_);
      return this.baos_.toByteArray();
   }

   public String toString(TBase base) throws TException {
      return new String(this.serialize(base));
   }
}
