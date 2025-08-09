package jodd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import jodd.util.StringUtil;
import jodd.util.Wildcard;

public class ZipUtil {
   public static final String ZIP_EXT = ".zip";
   public static final String GZIP_EXT = ".gz";
   public static final String ZLIB_EXT = ".zlib";

   public static void zlib(String file) throws IOException {
      zlib(new File(file));
   }

   public static void zlib(File file) throws IOException {
      if (file.isDirectory()) {
         throw new IOException("Can't zlib folder");
      } else {
         FileInputStream fis = new FileInputStream(file);
         Deflater deflater = new Deflater(9);
         DeflaterOutputStream dos = new DeflaterOutputStream(new FileOutputStream(file.getAbsolutePath() + ".zlib"), deflater);

         try {
            StreamUtil.copy((InputStream)fis, (OutputStream)dos);
         } finally {
            StreamUtil.close((OutputStream)dos);
            StreamUtil.close((InputStream)fis);
         }

      }
   }

   public static void gzip(String fileName) throws IOException {
      gzip(new File(fileName));
   }

   public static void gzip(File file) throws IOException {
      if (file.isDirectory()) {
         throw new IOException("Can't gzip folder");
      } else {
         FileInputStream fis = new FileInputStream(file);
         GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(file.getAbsolutePath() + ".gz"));

         try {
            StreamUtil.copy((InputStream)fis, (OutputStream)gzos);
         } finally {
            StreamUtil.close((OutputStream)gzos);
            StreamUtil.close((InputStream)fis);
         }

      }
   }

   public static void ungzip(String file) throws IOException {
      ungzip(new File(file));
   }

   public static void ungzip(File file) throws IOException {
      String outFileName = FileNameUtil.removeExtension(file.getAbsolutePath());
      File out = new File(outFileName);
      out.createNewFile();
      FileOutputStream fos = new FileOutputStream(out);
      GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(file));

      try {
         StreamUtil.copy((InputStream)gzis, (OutputStream)fos);
      } finally {
         StreamUtil.close((OutputStream)fos);
         StreamUtil.close((InputStream)gzis);
      }

   }

   public static void zip(String file) throws IOException {
      zip(new File(file));
   }

   public static void zip(File file) throws IOException {
      String zipFile = file.getAbsolutePath() + ".zip";
      ZipOutputStream zos = null;

      try {
         zos = createZip(zipFile);
         addToZip(zos).file(file).recursive().add();
      } finally {
         StreamUtil.close((OutputStream)zos);
      }

   }

   public static void unzip(String zipFile, String destDir, String... patterns) throws IOException {
      unzip(new File(zipFile), new File(destDir), patterns);
   }

   public static void unzip(File zipFile, File destDir, String... patterns) throws IOException {
      ZipFile zip = new ZipFile(zipFile);
      Enumeration zipEntries = zip.entries();

      while(zipEntries.hasMoreElements()) {
         ZipEntry entry = (ZipEntry)zipEntries.nextElement();
         String entryName = entry.getName();
         if (patterns == null || patterns.length <= 0 || Wildcard.matchPathOne(entryName, patterns) != -1) {
            File file = destDir != null ? new File(destDir, entryName) : new File(entryName);
            if (entry.isDirectory()) {
               if (!file.mkdirs() && !file.isDirectory()) {
                  throw new IOException("Failed to create directory: " + file);
               }
            } else {
               File parent = file.getParentFile();
               if (parent != null && !parent.exists() && !parent.mkdirs() && !file.isDirectory()) {
                  throw new IOException("Failed to create directory: " + parent);
               }

               InputStream in = zip.getInputStream(entry);
               OutputStream out = null;

               try {
                  out = new FileOutputStream(file);
                  StreamUtil.copy(in, out);
               } finally {
                  StreamUtil.close(out);
                  StreamUtil.close(in);
               }
            }
         }
      }

      close(zip);
   }

   public static ZipOutputStream createZip(String zipFile) throws FileNotFoundException {
      return createZip(new File(zipFile));
   }

   public static ZipOutputStream createZip(File zip) throws FileNotFoundException {
      return new ZipOutputStream(new FileOutputStream(zip));
   }

   public static AddToZip addToZip(ZipOutputStream zos) {
      return new AddToZip(zos);
   }

   public static void addToZip(ZipOutputStream zos, File file, String path, String comment, boolean recursive) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException(file.toString());
      } else {
         if (path == null) {
            path = file.getName();
         }

         while(path.length() != 0 && path.charAt(0) == '/') {
            path = path.substring(1);
         }

         boolean isDir = file.isDirectory();
         if (isDir && !StringUtil.endsWithChar(path, '/')) {
            path = path + '/';
         }

         ZipEntry zipEntry = new ZipEntry(path);
         zipEntry.setTime(file.lastModified());
         if (comment != null) {
            zipEntry.setComment(comment);
         }

         if (isDir) {
            zipEntry.setSize(0L);
            zipEntry.setCrc(0L);
         }

         zos.putNextEntry(zipEntry);
         if (!isDir) {
            InputStream is = new FileInputStream(file);

            try {
               StreamUtil.copy((InputStream)is, (OutputStream)zos);
            } finally {
               StreamUtil.close(is);
            }
         }

         zos.closeEntry();
         if (recursive && file.isDirectory()) {
            boolean noRelativePath = StringUtil.isEmpty(path);
            File[] children = file.listFiles();
            if (children != null && children.length != 0) {
               for(File child : children) {
                  String childRelativePath = (noRelativePath ? "" : path) + child.getName();
                  addToZip(zos, child, childRelativePath, comment, recursive);
               }
            }
         }

      }
   }

   public static void close(ZipFile zipFile) {
      if (zipFile != null) {
         try {
            zipFile.close();
         } catch (IOException var2) {
         }
      }

   }

   public static class AddToZip {
      private final ZipOutputStream zos;
      private File file;
      private String path;
      private String comment;
      private boolean recursive;

      private AddToZip(ZipOutputStream zos) {
         this.recursive = true;
         this.zos = zos;
      }

      public AddToZip file(File file) {
         this.file = file;
         return this;
      }

      public AddToZip file(String fileName) {
         this.file = new File(fileName);
         return this;
      }

      public AddToZip file(String parent, String child) {
         this.file = new File(parent, child);
         return this;
      }

      public AddToZip path(String path) {
         this.path = path;
         return this;
      }

      public AddToZip comment(String comment) {
         this.comment = comment;
         return this;
      }

      public AddToZip recursive() {
         this.recursive = true;
         return this;
      }

      public void add() throws IOException {
         ZipUtil.addToZip(this.zos, this.file, this.path, this.comment, this.recursive);
      }
   }
}
