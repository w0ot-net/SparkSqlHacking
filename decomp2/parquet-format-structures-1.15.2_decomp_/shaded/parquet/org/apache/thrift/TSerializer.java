package shaded.parquet.org.apache.thrift;

import java.io.ByteArrayOutputStream;
import shaded.parquet.org.apache.thrift.protocol.TBinaryProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolFactory;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;
import shaded.parquet.org.apache.thrift.transport.TTransportException;

public class TSerializer {
   private final ByteArrayOutputStream baos_;
   private final TProtocol protocol_;

   public TSerializer() throws TTransportException {
      this(new TBinaryProtocol.Factory());
   }

   public TSerializer(TProtocolFactory protocolFactory) throws TTransportException {
      this.baos_ = new ByteArrayOutputStream();
      TIOStreamTransport transport_ = new TIOStreamTransport(new TConfiguration(), this.baos_);
      this.protocol_ = protocolFactory.getProtocol(transport_);
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
