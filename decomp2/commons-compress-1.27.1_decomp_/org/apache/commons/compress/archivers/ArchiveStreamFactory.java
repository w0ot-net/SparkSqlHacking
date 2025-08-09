package org.apache.commons.compress.archivers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.util.Collections;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Sets;

public class ArchiveStreamFactory implements ArchiveStreamProvider {
   private static final int TAR_HEADER_SIZE = 512;
   private static final int TAR_TEST_ENTRY_COUNT = 10;
   private static final int DUMP_SIGNATURE_SIZE = 32;
   private static final int SIGNATURE_SIZE = 12;
   public static final ArchiveStreamFactory DEFAULT = new ArchiveStreamFactory();
   public static final String APK = "apk";
   public static final String XAPK = "xapk";
   public static final String APKS = "apks";
   public static final String APKM = "apkm";
   public static final String AR = "ar";
   public static final String ARJ = "arj";
   public static final String CPIO = "cpio";
   public static final String DUMP = "dump";
   public static final String JAR = "jar";
   public static final String TAR = "tar";
   public static final String ZIP = "zip";
   public static final String SEVEN_Z = "7z";
   private volatile String entryEncoding;
   private SortedMap archiveInputStreamProviders;
   private SortedMap archiveOutputStreamProviders;

   private static Iterable archiveStreamProviderIterable() {
      return ServiceLoader.load(ArchiveStreamProvider.class, ClassLoader.getSystemClassLoader());
   }

   public static String detect(InputStream in) throws ArchiveException {
      if (in == null) {
         throw new IllegalArgumentException("Stream must not be null.");
      } else if (!in.markSupported()) {
         throw new IllegalArgumentException("Mark is not supported.");
      } else {
         byte[] signature = new byte[12];
         in.mark(signature.length);
         int signatureLength = -1;

         try {
            signatureLength = IOUtils.readFully(in, signature);
            in.reset();
         } catch (IOException e) {
            throw new ArchiveException("IOException while reading signature.", e);
         }

         if (ZipArchiveInputStream.matches(signature, signatureLength)) {
            return "zip";
         } else if (JarArchiveInputStream.matches(signature, signatureLength)) {
            return "jar";
         } else if (ArArchiveInputStream.matches(signature, signatureLength)) {
            return "ar";
         } else if (CpioArchiveInputStream.matches(signature, signatureLength)) {
            return "cpio";
         } else if (ArjArchiveInputStream.matches(signature, signatureLength)) {
            return "arj";
         } else if (SevenZFile.matches(signature, signatureLength)) {
            return "7z";
         } else {
            byte[] dumpsig = new byte[32];
            in.mark(dumpsig.length);

            try {
               signatureLength = IOUtils.readFully(in, dumpsig);
               in.reset();
            } catch (IOException e) {
               throw new ArchiveException("IOException while reading dump signature", e);
            }

            if (DumpArchiveInputStream.matches(dumpsig, signatureLength)) {
               return "dump";
            } else {
               byte[] tarHeader = new byte[512];
               in.mark(tarHeader.length);

               try {
                  signatureLength = IOUtils.readFully(in, tarHeader);
                  in.reset();
               } catch (IOException e) {
                  throw new ArchiveException("IOException while reading tar signature", e);
               }

               if (TarArchiveInputStream.matches(tarHeader, signatureLength)) {
                  return "tar";
               } else {
                  if (signatureLength >= 512) {
                     try {
                        TarArchiveInputStream inputStream = new TarArchiveInputStream(new ByteArrayInputStream(tarHeader));

                        String var8;
                        label156: {
                           try {
                              TarArchiveEntry entry = inputStream.getNextEntry();

                              int count;
                              for(count = 0; entry != null && entry.isDirectory() && entry.isCheckSumOK() && count++ < 10; entry = inputStream.getNextEntry()) {
                              }

                              if (entry != null && entry.isCheckSumOK() && !entry.isDirectory() && entry.getSize() > 0L || count > 0) {
                                 var8 = "tar";
                                 break label156;
                              }
                           } catch (Throwable var13) {
                              try {
                                 inputStream.close();
                              } catch (Throwable var9) {
                                 var13.addSuppressed(var9);
                              }

                              throw var13;
                           }

                           inputStream.close();
                           throw new ArchiveException("No Archiver found for the stream signature");
                        }

                        inputStream.close();
                        return var8;
                     } catch (Exception var14) {
                     }
                  }

                  throw new ArchiveException("No Archiver found for the stream signature");
               }
            }
         }
      }
   }

   public static SortedMap findAvailableArchiveInputStreamProviders() {
      return (SortedMap)AccessController.doPrivileged(() -> {
         TreeMap<String, ArchiveStreamProvider> map = new TreeMap();
         putAll(DEFAULT.getInputStreamArchiveNames(), DEFAULT, map);
         archiveStreamProviderIterable().forEach((provider) -> putAll(provider.getInputStreamArchiveNames(), provider, map));
         return map;
      });
   }

   public static SortedMap findAvailableArchiveOutputStreamProviders() {
      return (SortedMap)AccessController.doPrivileged(() -> {
         TreeMap<String, ArchiveStreamProvider> map = new TreeMap();
         putAll(DEFAULT.getOutputStreamArchiveNames(), DEFAULT, map);
         archiveStreamProviderIterable().forEach((provider) -> putAll(provider.getOutputStreamArchiveNames(), provider, map));
         return map;
      });
   }

   static void putAll(Set names, ArchiveStreamProvider provider, TreeMap map) {
      names.forEach((name) -> map.put(toKey(name), provider));
   }

   private static String toKey(String name) {
      return name.toUpperCase(Locale.ROOT);
   }

   public ArchiveStreamFactory() {
      this((String)null);
   }

   public ArchiveStreamFactory(String entryEncoding) {
      this.entryEncoding = entryEncoding;
   }

   public ArchiveInputStream createArchiveInputStream(InputStream in) throws ArchiveException {
      return this.createArchiveInputStream(detect(in), in);
   }

   public ArchiveInputStream createArchiveInputStream(String archiverName, InputStream in) throws ArchiveException {
      return this.createArchiveInputStream(archiverName, in, this.entryEncoding);
   }

   public ArchiveInputStream createArchiveInputStream(String archiverName, InputStream in, String actualEncoding) throws ArchiveException {
      if (archiverName == null) {
         throw new IllegalArgumentException("Archiver name must not be null.");
      } else if (in == null) {
         throw new IllegalArgumentException("InputStream must not be null.");
      } else if ("ar".equalsIgnoreCase(archiverName)) {
         return new ArArchiveInputStream(in);
      } else if ("arj".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new ArjArchiveInputStream(in, actualEncoding) : new ArjArchiveInputStream(in);
      } else if ("zip".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new ZipArchiveInputStream(in, actualEncoding) : new ZipArchiveInputStream(in);
      } else if ("tar".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new TarArchiveInputStream(in, actualEncoding) : new TarArchiveInputStream(in);
      } else if (!"jar".equalsIgnoreCase(archiverName) && !"apk".equalsIgnoreCase(archiverName)) {
         if ("cpio".equalsIgnoreCase(archiverName)) {
            return actualEncoding != null ? new CpioArchiveInputStream(in, actualEncoding) : new CpioArchiveInputStream(in);
         } else if ("dump".equalsIgnoreCase(archiverName)) {
            return actualEncoding != null ? new DumpArchiveInputStream(in, actualEncoding) : new DumpArchiveInputStream(in);
         } else if ("7z".equalsIgnoreCase(archiverName)) {
            throw new StreamingNotSupportedException("7z");
         } else {
            ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider)this.getArchiveInputStreamProviders().get(toKey(archiverName));
            if (archiveStreamProvider != null) {
               return archiveStreamProvider.createArchiveInputStream(archiverName, in, actualEncoding);
            } else {
               throw new ArchiveException("Archiver: " + archiverName + " not found.");
            }
         }
      } else {
         return actualEncoding != null ? new JarArchiveInputStream(in, actualEncoding) : new JarArchiveInputStream(in);
      }
   }

   public ArchiveOutputStream createArchiveOutputStream(String archiverName, OutputStream out) throws ArchiveException {
      return this.createArchiveOutputStream(archiverName, out, this.entryEncoding);
   }

   public ArchiveOutputStream createArchiveOutputStream(String archiverName, OutputStream out, String actualEncoding) throws ArchiveException {
      if (archiverName == null) {
         throw new IllegalArgumentException("Archiver name must not be null.");
      } else if (out == null) {
         throw new IllegalArgumentException("OutputStream must not be null.");
      } else if ("ar".equalsIgnoreCase(archiverName)) {
         return new ArArchiveOutputStream(out);
      } else if ("zip".equalsIgnoreCase(archiverName)) {
         ZipArchiveOutputStream zip = new ZipArchiveOutputStream(out);
         if (actualEncoding != null) {
            zip.setEncoding(actualEncoding);
         }

         return zip;
      } else if ("tar".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new TarArchiveOutputStream(out, actualEncoding) : new TarArchiveOutputStream(out);
      } else if ("jar".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new JarArchiveOutputStream(out, actualEncoding) : new JarArchiveOutputStream(out);
      } else if ("cpio".equalsIgnoreCase(archiverName)) {
         return actualEncoding != null ? new CpioArchiveOutputStream(out, actualEncoding) : new CpioArchiveOutputStream(out);
      } else if ("7z".equalsIgnoreCase(archiverName)) {
         throw new StreamingNotSupportedException("7z");
      } else {
         ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider)this.getArchiveOutputStreamProviders().get(toKey(archiverName));
         if (archiveStreamProvider != null) {
            return archiveStreamProvider.createArchiveOutputStream(archiverName, out, actualEncoding);
         } else {
            throw new ArchiveException("Archiver: " + archiverName + " not found.");
         }
      }
   }

   public SortedMap getArchiveInputStreamProviders() {
      if (this.archiveInputStreamProviders == null) {
         this.archiveInputStreamProviders = Collections.unmodifiableSortedMap(findAvailableArchiveInputStreamProviders());
      }

      return this.archiveInputStreamProviders;
   }

   public SortedMap getArchiveOutputStreamProviders() {
      if (this.archiveOutputStreamProviders == null) {
         this.archiveOutputStreamProviders = Collections.unmodifiableSortedMap(findAvailableArchiveOutputStreamProviders());
      }

      return this.archiveOutputStreamProviders;
   }

   public String getEntryEncoding() {
      return this.entryEncoding;
   }

   public Set getInputStreamArchiveNames() {
      return Sets.newHashSet("ar", "arj", "zip", "tar", "jar", "cpio", "dump", "7z");
   }

   public Set getOutputStreamArchiveNames() {
      return Sets.newHashSet("ar", "zip", "tar", "jar", "cpio", "7z");
   }

   /** @deprecated */
   @Deprecated
   public void setEntryEncoding(String entryEncoding) {
      this.entryEncoding = entryEncoding;
   }
}
