package org.sparkproject.jetty.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import org.sparkproject.jetty.util.resource.Resource;

public interface HttpContent {
   HttpField getContentType();

   String getContentTypeValue();

   String getCharacterEncoding();

   MimeTypes.Type getMimeType();

   HttpField getContentEncoding();

   String getContentEncodingValue();

   HttpField getContentLength();

   long getContentLengthValue();

   HttpField getLastModified();

   String getLastModifiedValue();

   HttpField getETag();

   String getETagValue();

   ByteBuffer getIndirectBuffer();

   ByteBuffer getDirectBuffer();

   Resource getResource();

   InputStream getInputStream() throws IOException;

   ReadableByteChannel getReadableByteChannel() throws IOException;

   void release();

   Map getPrecompressedContents();

   public interface ContentFactory {
      HttpContent getContent(String var1, int var2) throws IOException;
   }
}
