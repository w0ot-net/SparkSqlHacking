package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.OrcUtils;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.FileTail;
import org.apache.orc.OrcProto.Footer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class OrcTail {
   private static final Logger LOG = LoggerFactory.getLogger(OrcTail.class);
   private final OrcProto.FileTail fileTail;
   private final BufferChunk serializedTail;
   private final TypeDescription schema;
   private final long fileModificationTime;
   private final Reader reader;

   public OrcTail(OrcProto.FileTail fileTail, ByteBuffer serializedTail) throws IOException {
      this(fileTail, serializedTail, -1L);
   }

   public OrcTail(OrcProto.FileTail fileTail, ByteBuffer serializedTail, long fileModificationTime) throws IOException {
      this(fileTail, new BufferChunk(serializedTail, getStripeStatisticsOffset(fileTail)), fileModificationTime);
   }

   public OrcTail(OrcProto.FileTail fileTail, BufferChunk serializedTail, long fileModificationTime) throws IOException {
      this(fileTail, serializedTail, fileModificationTime, (Reader)null);
   }

   public OrcTail(OrcProto.FileTail fileTail, BufferChunk serializedTail, long fileModificationTime, Reader reader) throws IOException {
      this.fileTail = fileTail;
      this.serializedTail = serializedTail;
      this.fileModificationTime = fileModificationTime;
      List<OrcProto.Type> types = this.getTypes();
      OrcUtils.isValidTypeTree(types, 0);
      this.schema = OrcUtils.convertTypeFromProtobuf(types, 0);
      this.reader = reader;
   }

   public ByteBuffer getSerializedTail() {
      if (this.serializedTail.next == null) {
         return this.serializedTail.getData();
      } else {
         int len = 0;

         for(BufferChunk chunk = this.serializedTail; chunk != null; chunk = (BufferChunk)chunk.next) {
            len += chunk.getLength();
         }

         ByteBuffer result = ByteBuffer.allocate(len);

         for(BufferChunk chunk = this.serializedTail; chunk != null; chunk = (BufferChunk)chunk.next) {
            ByteBuffer tmp = chunk.getData();
            result.put(tmp.array(), tmp.arrayOffset() + tmp.position(), tmp.remaining());
         }

         result.flip();
         return result;
      }
   }

   public BufferChunk getTailBuffer() {
      return this.serializedTail;
   }

   public long getFileModificationTime() {
      return this.fileModificationTime;
   }

   public OrcProto.Footer getFooter() {
      return this.fileTail.getFooter();
   }

   public OrcProto.PostScript getPostScript() {
      return this.fileTail.getPostscript();
   }

   public OrcFile.WriterVersion getWriterVersion() {
      OrcProto.PostScript ps = this.fileTail.getPostscript();
      OrcProto.Footer footer = this.fileTail.getFooter();
      OrcFile.WriterImplementation writer = OrcFile.WriterImplementation.from(footer.getWriter());
      return OrcFile.WriterVersion.from(writer, ps.getWriterVersion());
   }

   public List getStripes() {
      return OrcUtils.convertProtoStripesToStripes(this.getFooter().getStripesList());
   }

   public CompressionKind getCompressionKind() {
      return CompressionKind.valueOf(this.fileTail.getPostscript().getCompression().name());
   }

   public int getCompressionBufferSize() {
      OrcProto.PostScript postScript = this.fileTail.getPostscript();
      return ReaderImpl.getCompressionBlockSize(postScript);
   }

   public int getMetadataSize() {
      return (int)this.getPostScript().getMetadataLength();
   }

   public List getTypes() {
      return this.getFooter().getTypesList();
   }

   public TypeDescription getSchema() {
      return this.schema;
   }

   public OrcProto.FileTail getFileTail() {
      return this.fileTail;
   }

   static long getMetadataOffset(OrcProto.FileTail tail) {
      OrcProto.PostScript ps = tail.getPostscript();
      return tail.getFileLength() - 1L - tail.getPostscriptLength() - ps.getFooterLength() - ps.getMetadataLength();
   }

   static long getStripeStatisticsOffset(OrcProto.FileTail tail) {
      OrcProto.PostScript ps = tail.getPostscript();
      return getMetadataOffset(tail) - ps.getStripeStatisticsLength();
   }

   public long getMetadataOffset() {
      return getMetadataOffset(this.fileTail);
   }

   public long getStripeStatisticsOffset() {
      return getStripeStatisticsOffset(this.fileTail);
   }

   public long getFileLength() {
      return this.fileTail.getFileLength();
   }

   public OrcProto.FileTail getMinimalFileTail() {
      OrcProto.FileTail.Builder fileTailBuilder = FileTail.newBuilder(this.fileTail);
      OrcProto.Footer.Builder footerBuilder = Footer.newBuilder(this.fileTail.getFooter());
      footerBuilder.clearStatistics();
      fileTailBuilder.setFooter(footerBuilder.build());
      return fileTailBuilder.build();
   }

   /** @deprecated */
   public List getStripeStatistics() throws IOException {
      if (this.reader == null) {
         LOG.warn("Please use Reader.getStripeStatistics or give `Reader` to OrcTail constructor.");
         return new ArrayList();
      } else {
         return this.reader.getStripeStatistics();
      }
   }
}
