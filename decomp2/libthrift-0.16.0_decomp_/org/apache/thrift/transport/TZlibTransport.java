package org.apache.thrift.transport;

import java.util.Objects;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.thrift.TConfiguration;

public class TZlibTransport extends TIOStreamTransport {
   private TTransport transport_;

   public TZlibTransport(TTransport transport) throws TTransportException {
      this(transport, 9);
   }

   public TZlibTransport(TTransport transport, int compressionLevel) throws TTransportException {
      super(Objects.isNull(transport.getConfiguration()) ? new TConfiguration() : transport.getConfiguration());
      this.transport_ = null;
      this.transport_ = transport;
      this.inputStream_ = new InflaterInputStream(new TTransportInputStream(this.transport_), new Inflater());
      this.outputStream_ = new DeflaterOutputStream(new TTransportOutputStream(this.transport_), new Deflater(compressionLevel, false), true);
   }

   public boolean isOpen() {
      return this.transport_.isOpen();
   }

   public void open() throws TTransportException {
      this.transport_.open();
   }

   public void close() {
      super.close();
      if (this.transport_.isOpen()) {
         this.transport_.close();
      }

   }

   public static class Factory extends TTransportFactory {
      public TTransport getTransport(TTransport base) throws TTransportException {
         return new TZlibTransport(base);
      }
   }
}
