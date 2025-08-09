package org.sparkproject.jetty.server.resource;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpContent;

public class HttpContentRangeWriter {
   private static final Logger LOG = LoggerFactory.getLogger(HttpContentRangeWriter.class);

   public static RangeWriter newRangeWriter(HttpContent content) {
      Objects.requireNonNull(content, "HttpContent");
      ByteBuffer buffer = content.getDirectBuffer();
      if (buffer == null) {
         buffer = content.getIndirectBuffer();
      }

      if (buffer != null) {
         return new ByteBufferRangeWriter(buffer);
      } else {
         try {
            ReadableByteChannel channel = content.getReadableByteChannel();
            if (channel != null) {
               if (channel instanceof SeekableByteChannel) {
                  SeekableByteChannel seekableByteChannel = (SeekableByteChannel)channel;
                  return new SeekableByteChannelRangeWriter(seekableByteChannel, () -> (SeekableByteChannel)content.getReadableByteChannel());
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("Skipping non-SeekableByteChannel option {} from content {}", channel, content);
               }

               channel.close();
            }
         } catch (IOException e) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Skipping ReadableByteChannel option", e);
            }
         }

         return new InputStreamRangeWriter(() -> content.getInputStream());
      }
   }
}
