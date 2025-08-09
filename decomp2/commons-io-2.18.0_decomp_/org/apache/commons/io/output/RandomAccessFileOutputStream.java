package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.apache.commons.io.build.AbstractStreamBuilder;

public final class RandomAccessFileOutputStream extends OutputStream {
   private final RandomAccessFile randomAccessFile;

   public static Builder builder() {
      return new Builder();
   }

   private RandomAccessFileOutputStream(RandomAccessFile randomAccessFile) {
      this.randomAccessFile = (RandomAccessFile)Objects.requireNonNull(randomAccessFile);
   }

   public void close() throws IOException {
      this.randomAccessFile.close();
      super.close();
   }

   public void flush() throws IOException {
      this.randomAccessFile.getChannel().force(true);
      super.flush();
   }

   public void write(int b) throws IOException {
      this.randomAccessFile.write(b);
   }

   public static final class Builder extends AbstractStreamBuilder {
      private Builder() {
         this.setOpenOptions(new OpenOption[]{StandardOpenOption.WRITE});
      }

      public RandomAccessFileOutputStream get() throws IOException {
         return new RandomAccessFileOutputStream(this.getRandomAccessFile());
      }
   }
}
