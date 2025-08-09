package org.jline.builtins;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public interface Source {
   String getName();

   InputStream read() throws IOException;

   Long lines();

   public static class URLSource implements Source {
      final URL url;
      final String name;

      public URLSource(URL url, String name) {
         this.url = (URL)Objects.requireNonNull(url);
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public InputStream read() throws IOException {
         return this.url.openStream();
      }

      public Long lines() {
         Long out = null;

         try {
            Stream<String> lines = Files.lines((new File(this.url.toURI())).toPath());

            try {
               out = lines.count();
            } catch (Throwable var6) {
               if (lines != null) {
                  try {
                     lines.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (lines != null) {
               lines.close();
            }
         } catch (Exception var7) {
         }

         return out;
      }
   }

   public static class PathSource implements Source {
      final Path path;
      final String name;

      public PathSource(File file, String name) {
         this(((File)Objects.requireNonNull(file)).toPath(), name);
      }

      public PathSource(Path path, String name) {
         this.path = (Path)Objects.requireNonNull(path);
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public InputStream read() throws IOException {
         return Files.newInputStream(this.path);
      }

      public Long lines() {
         Long out = null;

         try {
            Stream<String> lines = Files.lines(this.path);

            try {
               out = lines.count();
            } catch (Throwable var6) {
               if (lines != null) {
                  try {
                     lines.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (lines != null) {
               lines.close();
            }
         } catch (Exception var7) {
         }

         return out;
      }
   }

   public static class InputStreamSource implements Source {
      final InputStream in;
      final String name;

      public InputStreamSource(InputStream in, boolean close, String name) {
         Objects.requireNonNull(in);
         if (close) {
            this.in = in;
         } else {
            this.in = new FilterInputStream(in) {
               public void close() throws IOException {
               }
            };
         }

         if (this.in.markSupported()) {
            this.in.mark(Integer.MAX_VALUE);
         }

         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public InputStream read() throws IOException {
         if (this.in.markSupported()) {
            this.in.reset();
         }

         return this.in;
      }

      public Long lines() {
         return null;
      }
   }

   public static class StdInSource extends InputStreamSource {
      public StdInSource() {
         this(System.in);
      }

      public StdInSource(InputStream in) {
         super(in, false, (String)null);
      }
   }

   public static class ResourceSource implements Source {
      final String resource;
      final String name;

      public ResourceSource(String resource) {
         this(resource, resource);
      }

      public ResourceSource(String resource, String name) {
         this.resource = (String)Objects.requireNonNull(resource);
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public InputStream read() throws IOException {
         return this.getClass().getResourceAsStream(this.resource);
      }

      public Long lines() {
         return null;
      }
   }
}
