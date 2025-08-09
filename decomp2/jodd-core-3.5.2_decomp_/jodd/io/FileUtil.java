package jodd.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import jodd.JoddCore;
import jodd.util.SystemUtil;
import jodd.util.URLDecoder;

public class FileUtil {
   private static final String MSG_NOT_A_DIRECTORY = "Not a directory: ";
   private static final String MSG_CANT_CREATE = "Can't create: ";
   private static final String MSG_NOT_FOUND = "Not found: ";
   private static final String MSG_NOT_A_FILE = "Not a file: ";
   private static final String MSG_ALREADY_EXISTS = "Already exists: ";
   private static final String MSG_UNABLE_TO_DELETE = "Unable to delete: ";

   private static File file(String fileName) {
      return new File(fileName);
   }

   private static File file(File parent, String fileName) {
      return new File(parent, fileName);
   }

   public static boolean equals(String file1, String file2) {
      return equals(file(file1), file(file2));
   }

   public static boolean equals(File file1, File file2) {
      try {
         file1 = file1.getCanonicalFile();
         file2 = file2.getCanonicalFile();
      } catch (IOException var3) {
         return false;
      }

      return file1.equals(file2);
   }

   public static File toFile(URL url) {
      String fileName = toFileName(url);
      return fileName == null ? null : file(fileName);
   }

   public static URL toURL(File file) throws MalformedURLException {
      return file.toURI().toURL();
   }

   public static String toFileName(URL url) {
      if (url != null && url.getProtocol().equals("file")) {
         String filename = url.getFile().replace('/', File.separatorChar);
         return URLDecoder.decode(filename, JoddCore.encoding);
      } else {
         return null;
      }
   }

   public static void mkdirs(String dirs) throws IOException {
      mkdirs(file(dirs));
   }

   public static void mkdirs(File dirs) throws IOException {
      if (dirs.exists()) {
         if (!dirs.isDirectory()) {
            throw new IOException("Not a directory: " + dirs);
         }
      } else if (!dirs.mkdirs()) {
         throw new IOException("Can't create: " + dirs);
      }
   }

   public static void mkdir(String dir) throws IOException {
      mkdir(file(dir));
   }

   public static void mkdir(File dir) throws IOException {
      if (dir.exists()) {
         if (!dir.isDirectory()) {
            throw new IOException("Not a directory: " + dir);
         }
      } else if (!dir.mkdir()) {
         throw new IOException("Can't create: " + dir);
      }
   }

   public static void touch(String file) throws IOException {
      touch(file(file));
   }

   public static void touch(File file) throws IOException {
      if (!file.exists()) {
         StreamUtil.close((OutputStream)(new FileOutputStream(file)));
      }

      file.setLastModified(System.currentTimeMillis());
   }

   public static FileUtilParams cloneParams() {
      try {
         return JoddCore.fileUtilParams.clone();
      } catch (CloneNotSupportedException var1) {
         return null;
      }
   }

   public static FileUtilParams params() {
      return new FileUtilParams();
   }

   public static void copyFile(String src, String dest) throws IOException {
      copyFile(file(src), file(dest), JoddCore.fileUtilParams);
   }

   public static void copyFile(String src, String dest, FileUtilParams params) throws IOException {
      copyFile(file(src), file(dest), params);
   }

   public static void copyFile(File src, File dest) throws IOException {
      copyFile(src, dest, JoddCore.fileUtilParams);
   }

   public static void copyFile(File src, File dest, FileUtilParams params) throws IOException {
      checkFileCopy(src, dest, params);
      doCopyFile(src, dest, params);
   }

   private static void checkFileCopy(File src, File dest, FileUtilParams params) throws IOException {
      if (!src.exists()) {
         throw new FileNotFoundException("Not found: " + src);
      } else if (!src.isFile()) {
         throw new IOException("Not a file: " + src);
      } else if (equals(src, dest)) {
         throw new IOException("Files '" + src + "' and '" + dest + "' are equal");
      } else {
         File destParent = dest.getParentFile();
         if (destParent != null && !destParent.exists()) {
            if (!params.createDirs) {
               throw new IOException("Not found: " + destParent);
            }

            if (!destParent.mkdirs()) {
               throw new IOException("Can't create: " + destParent);
            }
         }

      }
   }

   private static void doCopyFile(File src, File dest, FileUtilParams params) throws IOException {
      if (dest.exists()) {
         if (dest.isDirectory()) {
            throw new IOException("Destination '" + dest + "' is a directory");
         }

         if (!params.overwrite) {
            throw new IOException("Already exists: " + dest);
         }
      }

      FileInputStream input = new FileInputStream(src);

      try {
         FileOutputStream output = new FileOutputStream(dest);

         try {
            StreamUtil.copy((InputStream)input, (OutputStream)output);
         } finally {
            StreamUtil.close((OutputStream)output);
         }
      } finally {
         StreamUtil.close((InputStream)input);
      }

      if (src.length() != dest.length()) {
         throw new IOException("Copy file failed of '" + src + "' to '" + dest + "' due to different sizes");
      } else {
         if (params.preserveDate) {
            dest.setLastModified(src.lastModified());
         }

      }
   }

   public static File copyFileToDir(String src, String destDir) throws IOException {
      return copyFileToDir(file(src), file(destDir), JoddCore.fileUtilParams);
   }

   public static File copyFileToDir(String src, String destDir, FileUtilParams params) throws IOException {
      return copyFileToDir(file(src), file(destDir), params);
   }

   public static File copyFileToDir(File src, File destDir) throws IOException {
      return copyFileToDir(src, destDir, JoddCore.fileUtilParams);
   }

   public static File copyFileToDir(File src, File destDir, FileUtilParams params) throws IOException {
      if (destDir.exists() && !destDir.isDirectory()) {
         throw new IOException("Not a directory: " + destDir);
      } else {
         File dest = file(destDir, src.getName());
         copyFile(src, dest, params);
         return dest;
      }
   }

   public static void copyDir(String srcDir, String destDir) throws IOException {
      copyDir(file(srcDir), file(destDir), JoddCore.fileUtilParams);
   }

   public static void copyDir(String srcDir, String destDir, FileUtilParams params) throws IOException {
      copyDir(file(srcDir), file(destDir), params);
   }

   public static void copyDir(File srcDir, File destDir) throws IOException {
      copyDir(srcDir, destDir, JoddCore.fileUtilParams);
   }

   public static void copyDir(File srcDir, File destDir, FileUtilParams params) throws IOException {
      checkDirCopy(srcDir, destDir);
      doCopyDirectory(srcDir, destDir, params);
   }

   private static void checkDirCopy(File srcDir, File destDir) throws IOException {
      if (!srcDir.exists()) {
         throw new FileNotFoundException("Not found: " + srcDir);
      } else if (!srcDir.isDirectory()) {
         throw new IOException("Not a directory: " + srcDir);
      } else if (equals(srcDir, destDir)) {
         throw new IOException("Source '" + srcDir + "' and destination '" + destDir + "' are equal");
      }
   }

   private static void doCopyDirectory(File srcDir, File destDir, FileUtilParams params) throws IOException {
      if (destDir.exists()) {
         if (!destDir.isDirectory()) {
            throw new IOException("Not a directory: " + destDir);
         }
      } else {
         if (!params.createDirs) {
            throw new IOException("Not found: " + destDir);
         }

         if (!destDir.mkdirs()) {
            throw new IOException("Can't create: " + destDir);
         }

         if (params.preserveDate) {
            destDir.setLastModified(srcDir.lastModified());
         }
      }

      File[] files = srcDir.listFiles();
      if (files == null) {
         throw new IOException("Failed to list contents of: " + srcDir);
      } else {
         IOException exception = null;

         for(File file : files) {
            File destFile = file(destDir, file.getName());

            try {
               if (file.isDirectory()) {
                  if (params.recursive) {
                     doCopyDirectory(file, destFile, params);
                  }
               } else {
                  doCopyFile(file, destFile, params);
               }
            } catch (IOException ioex) {
               if (!params.continueOnError) {
                  throw ioex;
               }

               exception = ioex;
            }
         }

         if (exception != null) {
            throw exception;
         }
      }
   }

   public static void moveFile(String src, String dest) throws IOException {
      moveFile(file(src), file(dest), JoddCore.fileUtilParams);
   }

   public static void moveFile(String src, String dest, FileUtilParams params) throws IOException {
      moveFile(file(src), file(dest), params);
   }

   public static void moveFile(File src, File dest) throws IOException {
      moveFile(src, dest, JoddCore.fileUtilParams);
   }

   public static void moveFile(File src, File dest, FileUtilParams params) throws IOException {
      checkFileCopy(src, dest, params);
      doMoveFile(src, dest, params);
   }

   private static void doMoveFile(File src, File dest, FileUtilParams params) throws IOException {
      if (dest.exists()) {
         if (!dest.isFile()) {
            throw new IOException("Not a file: " + dest);
         }

         if (!params.overwrite) {
            throw new IOException("Already exists: " + dest);
         }

         dest.delete();
      }

      boolean rename = src.renameTo(dest);
      if (!rename) {
         doCopyFile(src, dest, params);
         src.delete();
      }

   }

   public static void moveFileToDir(String src, String destDir) throws IOException {
      moveFileToDir(file(src), file(destDir), JoddCore.fileUtilParams);
   }

   public static void moveFileToDir(String src, String destDir, FileUtilParams params) throws IOException {
      moveFileToDir(file(src), file(destDir), params);
   }

   public static void moveFileToDir(File src, File destDir) throws IOException {
      moveFileToDir(src, destDir, JoddCore.fileUtilParams);
   }

   public static void moveFileToDir(File src, File destDir, FileUtilParams params) throws IOException {
      if (destDir.exists() && !destDir.isDirectory()) {
         throw new IOException("Not a directory: " + destDir);
      } else {
         moveFile(src, file(destDir, src.getName()), params);
      }
   }

   public static void moveDir(String srcDir, String destDir) throws IOException {
      moveDir(file(srcDir), file(destDir));
   }

   public static void moveDir(File srcDir, File destDir) throws IOException {
      checkDirCopy(srcDir, destDir);
      doMoveDirectory(srcDir, destDir);
   }

   private static void doMoveDirectory(File src, File dest) throws IOException {
      if (dest.exists()) {
         if (!dest.isDirectory()) {
            throw new IOException("Not a directory: " + dest);
         }

         dest = file(dest, dest.getName());
         dest.mkdir();
      }

      boolean rename = src.renameTo(dest);
      if (!rename) {
         doCopyDirectory(src, dest, params());
         deleteDir(src);
      }

   }

   public static void deleteFile(String dest) throws IOException {
      deleteFile(file(dest));
   }

   public static void deleteFile(File dest) throws IOException {
      if (!dest.exists()) {
         throw new FileNotFoundException("Not found: " + dest);
      } else if (!dest.isFile()) {
         throw new IOException("Not a file: " + dest);
      } else if (!dest.delete()) {
         throw new IOException("Unable to delete: " + dest);
      }
   }

   public static void deleteDir(String dest) throws IOException {
      deleteDir(file(dest), JoddCore.fileUtilParams);
   }

   public static void deleteDir(String dest, FileUtilParams params) throws IOException {
      deleteDir(file(dest), params);
   }

   public static void deleteDir(File dest) throws IOException {
      deleteDir(dest, JoddCore.fileUtilParams);
   }

   public static void deleteDir(File dest, FileUtilParams params) throws IOException {
      cleanDir(dest, params);
      if (!dest.delete()) {
         throw new IOException("Unable to delete: " + dest);
      }
   }

   public static void cleanDir(String dest) throws IOException {
      cleanDir(file(dest), JoddCore.fileUtilParams);
   }

   public static void cleanDir(String dest, FileUtilParams params) throws IOException {
      cleanDir(file(dest), params);
   }

   public static void cleanDir(File dest) throws IOException {
      cleanDir(dest, JoddCore.fileUtilParams);
   }

   public static void cleanDir(File dest, FileUtilParams params) throws IOException {
      if (!dest.exists()) {
         throw new FileNotFoundException("Not found: " + dest);
      } else if (!dest.isDirectory()) {
         throw new IOException("Not a directory: " + dest);
      } else {
         File[] files = dest.listFiles();
         if (files == null) {
            throw new IOException("Failed to list contents of: " + dest);
         } else {
            IOException exception = null;

            for(File file : files) {
               try {
                  if (file.isDirectory()) {
                     if (params.recursive) {
                        deleteDir(file, params);
                     }
                  } else {
                     file.delete();
                  }
               } catch (IOException ioex) {
                  if (!params.continueOnError) {
                     throw ioex;
                  }

                  exception = ioex;
               }
            }

            if (exception != null) {
               throw exception;
            }
         }
      }
   }

   public static char[] readUTFChars(String fileName) throws IOException {
      return readUTFChars(file(fileName));
   }

   public static char[] readUTFChars(File file) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         long len = file.length();
         if (len >= 2147483647L) {
            len = 2147483647L;
         }

         UnicodeInputStream in = null;

         char[] var6;
         try {
            in = new UnicodeInputStream(new FileInputStream(file), (String)null);
            FastCharArrayWriter fastCharArrayWriter = new FastCharArrayWriter((int)len);
            String encoding = in.getDetectedEncoding();
            if (encoding == null) {
               encoding = "UTF-8";
            }

            StreamUtil.copy((InputStream)in, (Writer)fastCharArrayWriter, encoding);
            var6 = fastCharArrayWriter.toCharArray();
         } finally {
            StreamUtil.close((InputStream)in);
         }

         return var6;
      }
   }

   public static char[] readChars(String fileName) throws IOException {
      return readChars(file(fileName), JoddCore.fileUtilParams.encoding);
   }

   public static char[] readChars(File file) throws IOException {
      return readChars(file, JoddCore.fileUtilParams.encoding);
   }

   public static char[] readChars(String fileName, String encoding) throws IOException {
      return readChars(file(fileName), encoding);
   }

   public static char[] readChars(File file, String encoding) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         long len = file.length();
         if (len >= 2147483647L) {
            len = 2147483647L;
         }

         InputStream in = null;

         char[] var6;
         try {
            in = new FileInputStream(file);
            if (encoding.startsWith("UTF")) {
               in = new UnicodeInputStream(in, encoding);
            }

            FastCharArrayWriter fastCharArrayWriter = new FastCharArrayWriter((int)len);
            StreamUtil.copy((InputStream)in, (Writer)fastCharArrayWriter, encoding);
            var6 = fastCharArrayWriter.toCharArray();
         } finally {
            StreamUtil.close(in);
         }

         return var6;
      }
   }

   public static void writeChars(File dest, char[] data) throws IOException {
      outChars(dest, data, JoddCore.encoding, false);
   }

   public static void writeChars(String dest, char[] data) throws IOException {
      outChars(file(dest), data, JoddCore.encoding, false);
   }

   public static void writeChars(File dest, char[] data, String encoding) throws IOException {
      outChars(dest, data, encoding, false);
   }

   public static void writeChars(String dest, char[] data, String encoding) throws IOException {
      outChars(file(dest), data, encoding, false);
   }

   protected static void outChars(File dest, char[] data, String encoding, boolean append) throws IOException {
      if (dest.exists() && !dest.isFile()) {
         throw new IOException("Not a file: " + dest);
      } else {
         Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest, append), encoding));

         try {
            out.write(data);
         } finally {
            StreamUtil.close(out);
         }

      }
   }

   public static String readUTFString(String fileName) throws IOException {
      return readUTFString(file(fileName));
   }

   public static String readUTFString(File file) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         long len = file.length();
         if (len >= 2147483647L) {
            len = 2147483647L;
         }

         UnicodeInputStream in = null;

         String var6;
         try {
            in = new UnicodeInputStream(new FileInputStream(file), (String)null);
            FastCharArrayWriter out = new FastCharArrayWriter((int)len);
            String encoding = in.getDetectedEncoding();
            if (encoding == null) {
               encoding = "UTF-8";
            }

            StreamUtil.copy((InputStream)in, (Writer)out, encoding);
            var6 = out.toString();
         } finally {
            StreamUtil.close((InputStream)in);
         }

         return var6;
      }
   }

   public static String readUTFString(InputStream inputStream) throws IOException {
      UnicodeInputStream in = null;

      String var4;
      try {
         in = new UnicodeInputStream(inputStream, (String)null);
         FastCharArrayWriter out = new FastCharArrayWriter();
         String encoding = in.getDetectedEncoding();
         if (encoding == null) {
            encoding = "UTF-8";
         }

         StreamUtil.copy((InputStream)in, (Writer)out, encoding);
         var4 = out.toString();
      } finally {
         StreamUtil.close((InputStream)in);
      }

      return var4;
   }

   public static String readString(String source) throws IOException {
      return readString(file(source), JoddCore.fileUtilParams.encoding);
   }

   public static String readString(String source, String encoding) throws IOException {
      return readString(file(source), encoding);
   }

   public static String readString(File source) throws IOException {
      return readString(source, JoddCore.fileUtilParams.encoding);
   }

   public static String readString(File file, String encoding) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         long len = file.length();
         if (len >= 2147483647L) {
            len = 2147483647L;
         }

         InputStream in = null;

         String var6;
         try {
            in = new FileInputStream(file);
            if (encoding.startsWith("UTF")) {
               in = new UnicodeInputStream(in, encoding);
            }

            FastCharArrayWriter out = new FastCharArrayWriter((int)len);
            StreamUtil.copy((InputStream)in, (Writer)out, encoding);
            var6 = out.toString();
         } finally {
            StreamUtil.close(in);
         }

         return var6;
      }
   }

   public static void writeString(String dest, String data) throws IOException {
      outString(file(dest), data, JoddCore.fileUtilParams.encoding, false);
   }

   public static void writeString(String dest, String data, String encoding) throws IOException {
      outString(file(dest), data, encoding, false);
   }

   public static void writeString(File dest, String data) throws IOException {
      outString(dest, data, JoddCore.fileUtilParams.encoding, false);
   }

   public static void writeString(File dest, String data, String encoding) throws IOException {
      outString(dest, data, encoding, false);
   }

   public static void appendString(String dest, String data) throws IOException {
      outString(file(dest), data, JoddCore.fileUtilParams.encoding, true);
   }

   public static void appendString(String dest, String data, String encoding) throws IOException {
      outString(file(dest), data, encoding, true);
   }

   public static void appendString(File dest, String data) throws IOException {
      outString(dest, data, JoddCore.fileUtilParams.encoding, true);
   }

   public static void appendString(File dest, String data, String encoding) throws IOException {
      outString(dest, data, encoding, true);
   }

   protected static void outString(File dest, String data, String encoding, boolean append) throws IOException {
      if (dest.exists() && !dest.isFile()) {
         throw new IOException("Not a file: " + dest);
      } else {
         FileOutputStream out = null;

         try {
            out = new FileOutputStream(dest, append);
            out.write(data.getBytes(encoding));
         } finally {
            StreamUtil.close((OutputStream)out);
         }

      }
   }

   public static void writeStream(File dest, InputStream in) throws IOException {
      FileOutputStream out = null;

      try {
         out = new FileOutputStream(dest);
         StreamUtil.copy((InputStream)in, (OutputStream)out);
      } finally {
         StreamUtil.close((OutputStream)out);
      }

   }

   public static void writeStream(String dest, InputStream in) throws IOException {
      FileOutputStream out = null;

      try {
         out = new FileOutputStream(dest);
         StreamUtil.copy((InputStream)in, (OutputStream)out);
      } finally {
         StreamUtil.close((OutputStream)out);
      }

   }

   public static String[] readLines(String source) throws IOException {
      return readLines(file(source), JoddCore.fileUtilParams.encoding);
   }

   public static String[] readLines(String source, String encoding) throws IOException {
      return readLines(file(source), encoding);
   }

   public static String[] readLines(File source) throws IOException {
      return readLines(source, JoddCore.fileUtilParams.encoding);
   }

   public static String[] readLines(File file, String encoding) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         List<String> list = new ArrayList();
         InputStream in = null;

         try {
            in = new FileInputStream(file);
            if (encoding.startsWith("UTF")) {
               in = new UnicodeInputStream(in, encoding);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));

            String strLine;
            while((strLine = br.readLine()) != null) {
               list.add(strLine);
            }
         } finally {
            StreamUtil.close(in);
         }

         return (String[])list.toArray(new String[list.size()]);
      }
   }

   public static byte[] readBytes(String file) throws IOException {
      return readBytes(file(file));
   }

   public static byte[] readBytes(File file) throws IOException {
      if (!file.exists()) {
         throw new FileNotFoundException("Not found: " + file);
      } else if (!file.isFile()) {
         throw new IOException("Not a file: " + file);
      } else {
         long len = file.length();
         if (len >= 2147483647L) {
            throw new IOException("File is larger then max array size");
         } else {
            byte[] bytes = new byte[(int)len];
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.readFully(bytes);
            randomAccessFile.close();
            return bytes;
         }
      }
   }

   public static void writeBytes(String dest, byte[] data) throws IOException {
      outBytes(file(dest), data, 0, data.length, false);
   }

   public static void writeBytes(String dest, byte[] data, int off, int len) throws IOException {
      outBytes(file(dest), data, off, len, false);
   }

   public static void writeBytes(File dest, byte[] data) throws IOException {
      outBytes(dest, data, 0, data.length, false);
   }

   public static void writeBytes(File dest, byte[] data, int off, int len) throws IOException {
      outBytes(dest, data, off, len, false);
   }

   public static void appendBytes(String dest, byte[] data) throws IOException {
      outBytes(file(dest), data, 0, data.length, true);
   }

   public static void appendBytes(String dest, byte[] data, int off, int len) throws IOException {
      outBytes(file(dest), data, off, len, true);
   }

   public static void appendBytes(File dest, byte[] data) throws IOException {
      outBytes(dest, data, 0, data.length, true);
   }

   public static void appendBytes(File dest, byte[] data, int off, int len) throws IOException {
      outBytes(dest, data, off, len, true);
   }

   protected static void outBytes(File dest, byte[] data, int off, int len, boolean append) throws IOException {
      if (dest.exists() && !dest.isFile()) {
         throw new IOException("Not a file: " + dest);
      } else {
         FileOutputStream out = null;

         try {
            out = new FileOutputStream(dest, append);
            out.write(data, off, len);
         } finally {
            StreamUtil.close((OutputStream)out);
         }

      }
   }

   public static boolean compare(String file1, String file2) throws IOException {
      return compare(file(file1), file(file2));
   }

   public static boolean compare(File file1, File file2) throws IOException {
      boolean file1Exists = file1.exists();
      if (file1Exists != file2.exists()) {
         return false;
      } else if (!file1Exists) {
         return true;
      } else if (file1.isFile() && file2.isFile()) {
         if (file1.length() != file2.length()) {
            return false;
         } else if (equals(file1, file2)) {
            return true;
         } else {
            InputStream input1 = null;
            InputStream input2 = null;

            boolean var5;
            try {
               input1 = new FileInputStream(file1);
               input2 = new FileInputStream(file2);
               var5 = StreamUtil.compare(input1, input2);
            } finally {
               StreamUtil.close(input1);
               StreamUtil.close(input2);
            }

            return var5;
         }
      } else {
         throw new IOException("Only files can be compared");
      }
   }

   public static boolean isNewer(String file, String reference) {
      return isNewer(file(file), file(reference));
   }

   public static boolean isNewer(File file, File reference) {
      if (!reference.exists()) {
         throw new IllegalArgumentException("Reference file not found: " + reference);
      } else {
         return isNewer(file, reference.lastModified());
      }
   }

   public static boolean isOlder(String file, String reference) {
      return isOlder(file(file), file(reference));
   }

   public static boolean isOlder(File file, File reference) {
      if (!reference.exists()) {
         throw new IllegalArgumentException("Reference file not found: " + reference);
      } else {
         return isOlder(file, reference.lastModified());
      }
   }

   public static boolean isNewer(File file, long timeMillis) {
      if (!file.exists()) {
         return false;
      } else {
         return file.lastModified() > timeMillis;
      }
   }

   public static boolean isNewer(String file, long timeMillis) {
      return isNewer(file(file), timeMillis);
   }

   public static boolean isOlder(File file, long timeMillis) {
      if (!file.exists()) {
         return false;
      } else {
         return file.lastModified() < timeMillis;
      }
   }

   public static boolean isOlder(String file, long timeMillis) {
      return isOlder(file(file), timeMillis);
   }

   public static void copy(String src, String dest) throws IOException {
      copy(file(src), file(dest), JoddCore.fileUtilParams);
   }

   public static void copy(String src, String dest, FileUtilParams params) throws IOException {
      copy(file(src), file(dest), params);
   }

   public static void copy(File src, File dest) throws IOException {
      copy(src, dest, JoddCore.fileUtilParams);
   }

   public static void copy(File src, File dest, FileUtilParams params) throws IOException {
      if (src.isDirectory()) {
         copyDir(src, dest, params);
      } else if (dest.isDirectory()) {
         copyFileToDir(src, dest, params);
      } else {
         copyFile(src, dest, params);
      }
   }

   public static void move(String src, String dest) throws IOException {
      move(file(src), file(dest), JoddCore.fileUtilParams);
   }

   public static void move(String src, String dest, FileUtilParams params) throws IOException {
      move(file(src), file(dest), params);
   }

   public static void move(File src, File dest) throws IOException {
      move(src, dest, JoddCore.fileUtilParams);
   }

   public static void move(File src, File dest, FileUtilParams params) throws IOException {
      if (src.isDirectory()) {
         moveDir(src, dest);
      } else if (dest.isDirectory()) {
         moveFileToDir(src, dest, params);
      } else {
         moveFile(src, dest, params);
      }
   }

   public static void delete(String dest) throws IOException {
      delete(file(dest), JoddCore.fileUtilParams);
   }

   public static void delete(String dest, FileUtilParams params) throws IOException {
      delete(file(dest), params);
   }

   public static void delete(File dest) throws IOException {
      delete(dest, JoddCore.fileUtilParams);
   }

   public static void delete(File dest, FileUtilParams params) throws IOException {
      if (dest.isDirectory()) {
         deleteDir(dest, params);
      } else {
         deleteFile(dest);
      }
   }

   public static boolean isAncestor(File ancestor, File file, boolean strict) {
      for(File parent = strict ? getParentFile(file) : file; parent != null; parent = getParentFile(parent)) {
         if (parent.equals(ancestor)) {
            return true;
         }
      }

      return false;
   }

   public static File getParentFile(File file) {
      int skipCount = 0;
      File parentFile = file;

      while(true) {
         parentFile = parentFile.getParentFile();
         if (parentFile == null) {
            return null;
         }

         if (!".".equals(parentFile.getName())) {
            if ("..".equals(parentFile.getName())) {
               ++skipCount;
            } else {
               if (skipCount <= 0) {
                  return parentFile;
               }

               --skipCount;
            }
         }
      }
   }

   public static boolean isFilePathAcceptable(File file, FileFilter fileFilter) {
      while(fileFilter == null || fileFilter.accept(file)) {
         file = file.getParentFile();
         if (file == null) {
            return true;
         }
      }

      return false;
   }

   public static File createTempDirectory() throws IOException {
      return createTempDirectory(JoddCore.tempFilePrefix, (String)null, (File)null);
   }

   public static File createTempDirectory(String prefix, String suffix) throws IOException {
      return createTempDirectory(prefix, suffix, (File)null);
   }

   public static File createTempDirectory(String prefix, String suffix, File tempDir) throws IOException {
      File file = createTempFile(prefix, suffix, tempDir);
      file.delete();
      file.mkdir();
      return file;
   }

   public static File createTempFile() throws IOException {
      return createTempFile(JoddCore.tempFilePrefix, (String)null, (File)null, true);
   }

   public static File createTempFile(String prefix, String suffix, File tempDir, boolean create) throws IOException {
      File file = createTempFile(prefix, suffix, tempDir);
      file.delete();
      if (create) {
         file.createNewFile();
      }

      return file;
   }

   public static File createTempFile(String prefix, String suffix, File dir) throws IOException {
      int exceptionsCount = 0;

      while(true) {
         try {
            return File.createTempFile(prefix, suffix, dir).getCanonicalFile();
         } catch (IOException ioex) {
            ++exceptionsCount;
            if (exceptionsCount >= 50) {
               throw ioex;
            }
         }
      }
   }

   public static boolean isSymlink(File file) throws IOException {
      if (SystemUtil.isHostWindows()) {
         return false;
      } else {
         File fileInCanonicalDir;
         if (file.getParent() == null) {
            fileInCanonicalDir = file;
         } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
         }

         return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
      }
   }
}
