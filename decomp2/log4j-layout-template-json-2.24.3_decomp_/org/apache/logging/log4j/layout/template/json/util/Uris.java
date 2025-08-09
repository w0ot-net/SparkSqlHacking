package org.apache.logging.log4j.layout.template.json.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.Strings;

public final class Uris {
   private static final Logger LOGGER = StatusLogger.getLogger();

   private Uris() {
   }

   public static String readUri(final String spec, final Charset charset) {
      Objects.requireNonNull(spec, "spec");
      Objects.requireNonNull(charset, "charset");

      try {
         URI uri = new URI(spec);
         return unsafeReadUri(uri, charset);
      } catch (Exception error) {
         throw new RuntimeException("failed reading URI: " + spec, error);
      }
   }

   public static String readUri(final URI uri, final Charset charset) {
      Objects.requireNonNull(uri, "uri");
      Objects.requireNonNull(charset, "charset");

      try {
         return unsafeReadUri(uri, charset);
      } catch (Exception error) {
         throw new RuntimeException("failed reading URI: " + uri, error);
      }
   }

   private static String unsafeReadUri(final URI uri, final Charset charset) throws Exception {
      switch (Strings.toRootLowerCase(uri.getScheme())) {
         case "classpath":
            return readClassPathUri(uri, charset);
         case "file":
            return readFileUri(uri, charset);
         default:
            throw new IllegalArgumentException("unknown scheme in URI: " + uri);
      }
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The uri parameter comes from aconfiguration file."
   )
   private static String readFileUri(final URI uri, final Charset charset) throws IOException {
      Path path = Paths.get(uri);
      BufferedReader fileReader = Files.newBufferedReader(path, charset);

      String var4;
      try {
         var4 = consumeReader(fileReader);
      } catch (Throwable var7) {
         if (fileReader != null) {
            try {
               fileReader.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (fileReader != null) {
         fileReader.close();
      }

      return var4;
   }

   @SuppressFBWarnings(
      value = {"URLCONNECTION_SSRF_FD"},
      justification = "The uri parameter comes fro a configuration file."
   )
   private static String readClassPathUri(final URI uri, final Charset charset) throws IOException {
      String spec = uri.toString();
      String path = spec.substring("classpath:".length());
      List<URL> resources = new ArrayList(LoaderUtil.findResources(path));
      if (resources.isEmpty()) {
         String message = String.format("could not locate classpath resource (path=%s)", path);
         throw new RuntimeException(message);
      } else {
         URL resource = (URL)resources.get(0);
         if (resources.size() > 1) {
            String message = String.format("for URI %s found %d resources, using the first one: %s", uri, resources.size(), resource);
            LOGGER.warn(message);
         }

         InputStream inputStream = resource.openStream();

         String var9;
         try {
            InputStreamReader reader = new InputStreamReader(inputStream, charset);

            try {
               BufferedReader bufferedReader = new BufferedReader(reader);

               try {
                  var9 = consumeReader(bufferedReader);
               } catch (Throwable var14) {
                  try {
                     bufferedReader.close();
                  } catch (Throwable var13) {
                     var14.addSuppressed(var13);
                  }

                  throw var14;
               }

               bufferedReader.close();
            } catch (Throwable var15) {
               try {
                  reader.close();
               } catch (Throwable var12) {
                  var15.addSuppressed(var12);
               }

               throw var15;
            }

            reader.close();
         } catch (Throwable var16) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Throwable var11) {
                  var16.addSuppressed(var11);
               }
            }

            throw var16;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         return var9;
      }
   }

   private static String consumeReader(final BufferedReader reader) throws IOException {
      StringBuilder builder = new StringBuilder();

      String line;
      while((line = reader.readLine()) != null) {
         builder.append(line);
      }

      return builder.toString();
   }
}
