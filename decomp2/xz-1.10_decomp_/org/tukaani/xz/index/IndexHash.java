package org.tukaani.xz.index;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.XZIOException;
import org.tukaani.xz.check.CRC32;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.check.SHA256;
import org.tukaani.xz.common.DecoderUtil;

public class IndexHash extends IndexBase {
   private Check hash;

   public IndexHash() {
      super(new CorruptedInputException());

      try {
         this.hash = new SHA256();
      } catch (NoSuchAlgorithmException var2) {
         this.hash = new CRC32();
      }

   }

   public void add(long unpaddedSize, long uncompressedSize) throws XZIOException {
      super.add(unpaddedSize, uncompressedSize);
      ByteBuffer buf = ByteBuffer.allocate(16);
      buf.putLong(unpaddedSize);
      buf.putLong(uncompressedSize);
      this.hash.update(buf.array());
   }

   public void validate(InputStream in) throws IOException {
      java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
      crc32.update(0);
      CheckedInputStream inChecked = new CheckedInputStream(in, crc32);
      long storedRecordCount = DecoderUtil.decodeVLI(inChecked);
      if (storedRecordCount != this.recordCount) {
         throw new CorruptedInputException("XZ Block Header or the start of XZ Index is corrupt");
      } else {
         IndexHash stored = new IndexHash();
         long i = 0L;

         while(true) {
            if (i < this.recordCount) {
               long unpaddedSize = DecoderUtil.decodeVLI(inChecked);
               long uncompressedSize = DecoderUtil.decodeVLI(inChecked);

               try {
                  stored.add(unpaddedSize, uncompressedSize);
               } catch (XZIOException var14) {
                  throw new CorruptedInputException("XZ Index is corrupt");
               }

               if (stored.blocksSum <= this.blocksSum && stored.uncompressedSum <= this.uncompressedSum && stored.indexListSize <= this.indexListSize) {
                  ++i;
                  continue;
               }

               throw new CorruptedInputException("XZ Index is corrupt");
            }

            if (stored.blocksSum == this.blocksSum && stored.uncompressedSum == this.uncompressedSum && stored.indexListSize == this.indexListSize && Arrays.equals(stored.hash.finish(), this.hash.finish())) {
               DataInputStream inData = new DataInputStream(inChecked);

               for(int i = this.getIndexPaddingSize(); i > 0; --i) {
                  if (inData.readUnsignedByte() != 0) {
                     throw new CorruptedInputException("XZ Index is corrupt");
                  }
               }

               long value = crc32.getValue();

               for(int i = 0; i < 4; ++i) {
                  if ((value >>> i * 8 & 255L) != (long)inData.readUnsignedByte()) {
                     throw new CorruptedInputException("XZ Index is corrupt");
                  }
               }

               return;
            }

            throw new CorruptedInputException("XZ Index is corrupt");
         }
      }
   }
}
