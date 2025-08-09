package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;

public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
   private static final long serialVersionUID = -547733176983104172L;
   private final byte[] magicNumbers;
   private final long byteOffset;

   public MagicNumberFileFilter(byte[] magicNumber) {
      this(magicNumber, 0L);
   }

   public MagicNumberFileFilter(byte[] magicNumbers, long offset) {
      Objects.requireNonNull(magicNumbers, "magicNumbers");
      if (magicNumbers.length == 0) {
         throw new IllegalArgumentException("The magic number must contain at least one byte");
      } else if (offset < 0L) {
         throw new IllegalArgumentException("The offset cannot be negative");
      } else {
         this.magicNumbers = (byte[])(([B)magicNumbers).clone();
         this.byteOffset = offset;
      }
   }

   public MagicNumberFileFilter(String magicNumber) {
      this(magicNumber, 0L);
   }

   public MagicNumberFileFilter(String magicNumber, long offset) {
      Objects.requireNonNull(magicNumber, "magicNumber");
      if (magicNumber.isEmpty()) {
         throw new IllegalArgumentException("The magic number must contain at least one byte");
      } else if (offset < 0L) {
         throw new IllegalArgumentException("The offset cannot be negative");
      } else {
         this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
         this.byteOffset = offset;
      }
   }

   public boolean accept(File file) {
      if (file != null && file.isFile() && file.canRead()) {
         try {
            return (Boolean)RandomAccessFileMode.READ_ONLY.apply(file.toPath(), (raf) -> Arrays.equals(this.magicNumbers, RandomAccessFiles.read(raf, this.byteOffset, this.magicNumbers.length)));
         } catch (IOException var3) {
         }
      }

      return false;
   }

   public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
      if (file != null && Files.isRegularFile(file, new LinkOption[0]) && Files.isReadable(file)) {
         try {
            FileChannel fileChannel = FileChannel.open(file);

            FileVisitResult var10;
            label57: {
               try {
                  ByteBuffer byteBuffer = ByteBuffer.allocate(this.magicNumbers.length);
                  fileChannel.position(this.byteOffset);
                  int read = fileChannel.read(byteBuffer);
                  if (read == this.magicNumbers.length) {
                     var10 = this.toFileVisitResult(Arrays.equals(this.magicNumbers, byteBuffer.array()));
                     break label57;
                  }

                  var10 = FileVisitResult.TERMINATE;
               } catch (Throwable var8) {
                  if (fileChannel != null) {
                     try {
                        fileChannel.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }
                  }

                  throw var8;
               }

               if (fileChannel != null) {
                  fileChannel.close();
               }

               return var10;
            }

            if (fileChannel != null) {
               fileChannel.close();
            }

            return var10;
         } catch (IOException var9) {
         }
      }

      return FileVisitResult.TERMINATE;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder(super.toString());
      builder.append("(");
      builder.append(new String(this.magicNumbers, Charset.defaultCharset()));
      builder.append(",");
      builder.append(this.byteOffset);
      builder.append(")");
      return builder.toString();
   }
}
