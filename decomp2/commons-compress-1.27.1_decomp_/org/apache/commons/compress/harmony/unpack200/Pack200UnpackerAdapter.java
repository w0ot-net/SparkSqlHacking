package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Adapter;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.lang3.reflect.FieldUtils;

public class Pack200UnpackerAdapter extends Pack200Adapter implements Pack200.Unpacker {
   static BoundedInputStream newBoundedInputStream(File file) throws IOException {
      return newBoundedInputStream(file.toPath());
   }

   private static BoundedInputStream newBoundedInputStream(FileInputStream fileInputStream) throws IOException {
      return newBoundedInputStream(readPathString(fileInputStream));
   }

   static BoundedInputStream newBoundedInputStream(InputStream inputStream) throws IOException {
      if (inputStream instanceof BoundedInputStream) {
         return (BoundedInputStream)inputStream;
      } else if (inputStream instanceof CloseShieldInputStream) {
         return newBoundedInputStream((InputStream)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get());
      } else if (inputStream instanceof FilterInputStream) {
         return newBoundedInputStream(unwrap((FilterInputStream)inputStream));
      } else {
         return inputStream instanceof FileInputStream ? newBoundedInputStream((FileInputStream)inputStream) : newBoundedInputStream((InputStream)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get());
      }
   }

   static BoundedInputStream newBoundedInputStream(Path path) throws IOException {
      return ((BoundedInputStream.Builder)((BoundedInputStream.Builder)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(new BufferedInputStream(Files.newInputStream(path)))).setMaxCount(Files.size(path))).setPropagateClose(false)).get();
   }

   static BoundedInputStream newBoundedInputStream(String first, String... more) throws IOException {
      return newBoundedInputStream(Paths.get(first, more));
   }

   static BoundedInputStream newBoundedInputStream(URL url) throws IOException, URISyntaxException {
      return newBoundedInputStream(Paths.get(url.toURI()));
   }

   private static Object readField(Object object, String fieldName) {
      try {
         return FieldUtils.readField(object, fieldName, true);
      } catch (IllegalAccessException var3) {
         return null;
      }
   }

   static String readPathString(FileInputStream fis) {
      return (String)readField(fis, "path");
   }

   static InputStream unwrap(FilterInputStream filterInputStream) {
      return (InputStream)readField(filterInputStream, "in");
   }

   static InputStream unwrap(InputStream inputStream) {
      return inputStream instanceof FilterInputStream ? unwrap((FilterInputStream)inputStream) : inputStream;
   }

   public void unpack(File file, JarOutputStream out) throws IOException {
      if (file != null && out != null) {
         long size = file.length();
         int bufferSize = size > 0L && size < 8192L ? (int)size : 8192;
         InputStream in = new BufferedInputStream(Files.newInputStream(file.toPath()), bufferSize);

         try {
            this.unpack(in, out);
         } catch (Throwable var10) {
            try {
               in.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         in.close();
      } else {
         throw new IllegalArgumentException("Must specify both input and output streams");
      }
   }

   public void unpack(InputStream in, JarOutputStream out) throws IOException {
      if (in != null && out != null) {
         this.completed((double)0.0F);

         try {
            (new Archive(in, out)).unpack();
         } catch (Pack200Exception e) {
            throw new IOException("Failed to unpack Jar:" + e);
         }

         this.completed((double)1.0F);
      } else {
         throw new IllegalArgumentException("Must specify both input and output streams");
      }
   }
}
