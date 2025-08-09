package org.apache.orc;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.impl.BufferChunkList;
import org.apache.orc.impl.InStream;

public interface DataReader extends AutoCloseable, Cloneable {
   void open() throws IOException;

   OrcProto.StripeFooter readStripeFooter(StripeInformation var1) throws IOException;

   BufferChunkList readFileData(BufferChunkList var1, boolean var2) throws IOException;

   boolean isTrackingDiskRanges();

   /** @deprecated */
   @Deprecated
   void releaseBuffer(ByteBuffer var1);

   void releaseAllBuffers();

   DataReader clone();

   void close() throws IOException;

   InStream.StreamOptions getCompressionOptions();
}
