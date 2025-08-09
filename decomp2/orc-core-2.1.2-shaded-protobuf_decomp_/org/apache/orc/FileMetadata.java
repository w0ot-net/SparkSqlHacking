package org.apache.orc;

import java.util.List;

/** @deprecated */
public interface FileMetadata {
   boolean isOriginalFormat();

   List getStripes();

   CompressionKind getCompressionKind();

   int getCompressionBufferSize();

   int getRowIndexStride();

   int getColumnCount();

   int getFlattenedColumnCount();

   Object getFileKey();

   List getVersionList();

   int getMetadataSize();

   int getWriterImplementation();

   int getWriterVersionNum();

   List getTypes();

   List getStripeStats();

   long getContentLength();

   long getNumberOfRows();

   List getFileStats();
}
