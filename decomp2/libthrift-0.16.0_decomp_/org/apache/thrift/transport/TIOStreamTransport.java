package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import org.apache.thrift.TConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TIOStreamTransport extends TEndpointTransport {
   private static final Logger LOGGER = LoggerFactory.getLogger(TIOStreamTransport.class.getName());
   protected InputStream inputStream_ = null;
   protected OutputStream outputStream_ = null;

   protected TIOStreamTransport(TConfiguration config) throws TTransportException {
      super(config);
   }

   protected TIOStreamTransport() throws TTransportException {
      super(new TConfiguration());
   }

   public TIOStreamTransport(TConfiguration config, InputStream is) throws TTransportException {
      super(config);
      this.inputStream_ = is;
   }

   public TIOStreamTransport(InputStream is) throws TTransportException {
      super(new TConfiguration());
      this.inputStream_ = is;
   }

   public TIOStreamTransport(TConfiguration config, OutputStream os) throws TTransportException {
      super(config);
      this.outputStream_ = os;
   }

   public TIOStreamTransport(OutputStream os) throws TTransportException {
      super(new TConfiguration());
      this.outputStream_ = os;
   }

   public TIOStreamTransport(TConfiguration config, InputStream is, OutputStream os) throws TTransportException {
      super(config);
      this.inputStream_ = is;
      this.outputStream_ = os;
   }

   public TIOStreamTransport(InputStream is, OutputStream os) throws TTransportException {
      super(new TConfiguration());
      this.inputStream_ = is;
      this.outputStream_ = os;
   }

   public boolean isOpen() {
      return this.inputStream_ != null || this.outputStream_ != null;
   }

   public void open() throws TTransportException {
   }

   public void close() {
      try {
         if (this.inputStream_ != null) {
            try {
               this.inputStream_.close();
            } catch (IOException iox) {
               LOGGER.warn("Error closing input stream.", iox);
            }
         }

         if (this.outputStream_ != null) {
            try {
               this.outputStream_.close();
            } catch (IOException iox) {
               LOGGER.warn("Error closing output stream.", iox);
            }
         }
      } finally {
         this.inputStream_ = null;
         this.outputStream_ = null;
      }

   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if (this.inputStream_ == null) {
         throw new TTransportException(1, "Cannot read from null inputStream");
      } else {
         int bytesRead;
         try {
            bytesRead = this.inputStream_.read(buf, off, len);
         } catch (SocketTimeoutException ste) {
            throw new TTransportException(3, ste);
         } catch (IOException iox) {
            throw new TTransportException(0, iox);
         }

         if (bytesRead < 0) {
            throw new TTransportException(4, "Socket is closed by peer.");
         } else {
            return bytesRead;
         }
      }
   }

   public void write(byte[] buf, int off, int len) throws TTransportException {
      if (this.outputStream_ == null) {
         throw new TTransportException(1, "Cannot write to null outputStream");
      } else {
         try {
            this.outputStream_.write(buf, off, len);
         } catch (IOException iox) {
            throw new TTransportException(0, iox);
         }
      }
   }

   public void flush() throws TTransportException {
      if (this.outputStream_ == null) {
         throw new TTransportException(1, "Cannot flush null outputStream");
      } else {
         try {
            this.outputStream_.flush();
            this.resetConsumedMessageSize(-1L);
         } catch (IOException iox) {
            throw new TTransportException(0, iox);
         }
      }
   }
}
