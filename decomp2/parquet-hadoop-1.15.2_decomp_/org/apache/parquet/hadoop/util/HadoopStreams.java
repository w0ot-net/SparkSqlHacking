package org.apache.parquet.hadoop.util;

import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;
import org.apache.hadoop.fs.ByteBufferReadable;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.parquet.io.PositionOutputStream;
import org.apache.parquet.io.SeekableInputStream;
import org.apache.parquet.util.DynMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HadoopStreams {
   private static final Logger LOG = LoggerFactory.getLogger(HadoopStreams.class);
   private static final DynMethods.UnboundMethod hasCapabilitiesMethod = (new DynMethods.Builder("hasCapabilities")).impl(FSDataInputStream.class, "hasCapabilities", new Class[]{String.class}).orNoop().build();

   public static SeekableInputStream wrap(FSDataInputStream stream) {
      Objects.requireNonNull(stream, "Cannot wrap a null input stream");
      Boolean hasCapabilitiesResult = isWrappedStreamByteBufferReadable(stream);
      if (hasCapabilitiesResult != null) {
         return (SeekableInputStream)(hasCapabilitiesResult ? new H2SeekableInputStream(stream) : new H1SeekableInputStream(stream));
      } else {
         return (SeekableInputStream)unwrapByteBufferReadableLegacy(stream).apply(stream);
      }
   }

   private static Function unwrapByteBufferReadableLegacy(FSDataInputStream stream) {
      InputStream wrapped = stream.getWrappedStream();
      if (wrapped instanceof FSDataInputStream) {
         LOG.debug("Checking on wrapped stream {} of {} whether is ByteBufferReadable", wrapped, stream);
         return unwrapByteBufferReadableLegacy((FSDataInputStream)wrapped);
      } else {
         return stream.getWrappedStream() instanceof ByteBufferReadable ? H2SeekableInputStream::new : H1SeekableInputStream::new;
      }
   }

   private static Boolean isWrappedStreamByteBufferReadable(FSDataInputStream stream) {
      if (hasCapabilitiesMethod.isNoop()) {
         return null;
      } else {
         boolean isByteBufferReadable = (Boolean)hasCapabilitiesMethod.invoke(stream, new Object[]{"in:readbytebuffer"});
         if (isByteBufferReadable) {
            return true;
         } else {
            InputStream wrapped = stream.getWrappedStream();
            if (wrapped instanceof FSDataInputStream) {
               LOG.debug("Checking on wrapped stream {} of {} whether is ByteBufferReadable", wrapped, stream);
               return isWrappedStreamByteBufferReadable((FSDataInputStream)wrapped);
            } else {
               return wrapped instanceof ByteBufferReadable;
            }
         }
      }
   }

   public static PositionOutputStream wrap(FSDataOutputStream stream) {
      Objects.requireNonNull(stream, "Cannot wrap a null output stream");
      return new HadoopPositionOutputStream(stream);
   }
}
