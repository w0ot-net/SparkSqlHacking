package org.apache.ivy.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.GZIPInputStream;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.util.url.TimeoutConstrainedURLHandler;
import org.apache.ivy.util.url.URLHandler;
import org.apache.ivy.util.url.URLHandlerRegistry;

public final class FileUtil {
   private static final int BUFFER_SIZE = 65536;
   private static final byte[] EMPTY_BUFFER = new byte[0];

   private FileUtil() {
   }

   public static boolean symlink(File target, File link, boolean overwrite) throws IOException {
      if (target.isFile()) {
         if (!prepareCopy(target, link, overwrite)) {
            return false;
         }
      } else {
         if (Files.isSymbolicLink(link.toPath()) && overwrite) {
            Message.verbose("Un-linking existing symbolic link " + link + " during symlink creation, since overwrite=true");
            Files.delete(link.toPath());
         }

         if (link.getParentFile() != null) {
            link.getParentFile().mkdirs();
         }
      }

      Files.createSymbolicLink(link.toPath(), target.getAbsoluteFile().toPath());
      return true;
   }

   public static boolean copy(File src, File dest, CopyProgressListener l) throws IOException {
      return copy(src, dest, l, false);
   }

   public static boolean prepareCopy(File src, File dest, boolean overwrite) throws IOException {
      if (src.isDirectory()) {
         if (dest.exists()) {
            if (!dest.isDirectory()) {
               throw new IOException("impossible to copy: destination is not a directory: " + dest);
            }
         } else {
            dest.mkdirs();
         }

         return true;
      } else {
         if (dest.exists()) {
            boolean unlinkSymlinkIfOverwrite = true;
            if (!dest.isFile()) {
               throw new IOException("impossible to copy: destination is not a file: " + dest);
            }

            if (!overwrite) {
               Message.verbose(dest + " already exists, nothing done");
               return false;
            }

            if (Files.isSymbolicLink(dest.toPath())) {
               dest.delete();
            } else if (!dest.canWrite()) {
               dest.delete();
            }
         }

         if (dest.getParentFile() != null) {
            dest.getParentFile().mkdirs();
         }

         return true;
      }
   }

   public static boolean copy(File src, File dest, CopyProgressListener l, boolean overwrite) throws IOException {
      if (!prepareCopy(src, dest, overwrite)) {
         return false;
      } else if (src.isDirectory()) {
         return deepCopy(src, dest, l, overwrite);
      } else {
         try {
            if (Files.isSameFile(src.toPath(), dest.toPath())) {
               Message.verbose("Skipping copy of file " + src + " to " + dest + " since they are the same file");
               return overwrite;
            }
         } catch (NoSuchFileException var8) {
         } catch (IOException ioe) {
            Message.verbose("Could not determine if " + src + " and dest " + dest + " are the same file", ioe);
         }

         copy((InputStream)(new FileInputStream(src)), (File)dest, l);
         long srcLen = src.length();
         long destLen = dest.length();
         if (srcLen != destLen) {
            dest.delete();
            throw new IOException("size of source file " + src.toString() + "(" + srcLen + ") differs from size of dest file " + dest.toString() + "(" + destLen + ") - please retry");
         } else {
            dest.setLastModified(src.lastModified());
            return true;
         }
      }
   }

   public static boolean deepCopy(File src, File dest, CopyProgressListener l, boolean overwrite) throws IOException {
      List<File> existingChild = Collections.emptyList();
      if (dest.exists()) {
         if (!dest.isDirectory()) {
            dest.delete();
            dest.mkdirs();
            dest.setLastModified(src.lastModified());
         } else {
            File[] children = dest.listFiles();
            if (children != null) {
               existingChild = new ArrayList(Arrays.asList(children));
            }
         }
      } else {
         dest.mkdirs();
         dest.setLastModified(src.lastModified());
      }

      File[] toCopy = src.listFiles();
      if (toCopy != null) {
         for(File cf : toCopy) {
            File childDest = new File(dest, cf.getName());
            if (!existingChild.isEmpty()) {
               existingChild.remove(childDest);
            }

            if (cf.isDirectory()) {
               deepCopy(cf, childDest, l, overwrite);
            } else {
               copy(cf, childDest, l, overwrite);
            }
         }
      }

      for(File child : existingChild) {
         forceDelete(child);
      }

      return true;
   }

   public static void copy(URL src, File dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      URLHandler handler = URLHandlerRegistry.getDefault();
      if (handler instanceof TimeoutConstrainedURLHandler) {
         ((TimeoutConstrainedURLHandler)handler).download(src, dest, listener, timeoutConstraint);
      } else {
         handler.download(src, dest, listener);
      }
   }

   public static void copy(File src, URL dest, CopyProgressListener listener, TimeoutConstraint timeoutConstraint) throws IOException {
      URLHandler handler = URLHandlerRegistry.getDefault();
      if (handler instanceof TimeoutConstrainedURLHandler) {
         ((TimeoutConstrainedURLHandler)handler).upload(src, dest, listener, timeoutConstraint);
      } else {
         handler.upload(src, dest, listener);
      }
   }

   public static void copy(InputStream src, File dest, CopyProgressListener l) throws IOException {
      if (dest.getParentFile() != null) {
         dest.getParentFile().mkdirs();
      }

      copy((InputStream)src, (OutputStream)(new FileOutputStream(dest)), l);
   }

   public static void copy(InputStream src, OutputStream dest, CopyProgressListener l) throws IOException {
      copy(src, dest, l, true);
   }

   public static void copy(InputStream src, OutputStream dest, CopyProgressListener l, boolean autoClose) throws IOException {
      CopyProgressEvent evt = null;
      if (l != null) {
         evt = new CopyProgressEvent();
      }

      try {
         byte[] buffer = new byte[65536];
         long total = 0L;
         if (l != null) {
            l.start(evt);
         }

         int c;
         while((c = src.read(buffer)) != -1) {
            if (Thread.currentThread().isInterrupted()) {
               throw new IOException("transfer interrupted");
            }

            dest.write(buffer, 0, c);
            total += (long)c;
            if (l != null) {
               l.progress(evt.update(buffer, c, total));
            }
         }

         if (l != null) {
            evt.update(EMPTY_BUFFER, 0, total);
         }

         try {
            dest.flush();
         } catch (IOException var20) {
         }

         if (autoClose) {
            src.close();
            dest.close();
         }
      } finally {
         if (autoClose) {
            try {
               src.close();
            } catch (IOException var19) {
            }

            try {
               dest.close();
            } catch (IOException var18) {
            }
         }

      }

      if (l != null) {
         l.end(evt);
      }

   }

   public static String readEntirely(BufferedReader in) throws IOException {
      String var3;
      try {
         StringBuilder buf = new StringBuilder();

         for(String line = in.readLine(); line != null; line = in.readLine()) {
            buf.append(line).append("\n");
         }

         var3 = buf.toString();
      } finally {
         in.close();
      }

      return var3;
   }

   public static String readEntirely(File f) throws IOException {
      return readEntirely((InputStream)(new FileInputStream(f)));
   }

   public static String readEntirely(InputStream is) throws IOException {
      String var4;
      try {
         StringBuilder sb = new StringBuilder();
         byte[] buffer = new byte[65536];

         int c;
         while((c = is.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, c));
         }

         var4 = sb.toString();
      } finally {
         is.close();
      }

      return var4;
   }

   public static String concat(String dir, String file) {
      return dir + "/" + file;
   }

   public static boolean forceDelete(File file) {
      if (!file.exists()) {
         return true;
      } else {
         if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
               for(File df : files) {
                  if (!forceDelete(df)) {
                     return false;
                  }
               }
            }
         }

         return file.delete();
      }
   }

   public static List getPathFiles(File root, File file) {
      List<File> ret;
      for(ret = new ArrayList(); file != null && !file.getAbsolutePath().equals(root.getAbsolutePath()); file = file.getParentFile()) {
         ret.add(file);
      }

      if (root != null) {
         ret.add(root);
      }

      Collections.reverse(ret);
      return ret;
   }

   public static Collection listAll(File dir, Collection ignore) {
      return listAll(dir, new ArrayList(), ignore);
   }

   private static Collection listAll(File file, Collection list, Collection ignore) {
      if (ignore.contains(file.getName())) {
         return list;
      } else {
         if (file.exists()) {
            list.add(file);
         }

         if (file.isDirectory()) {
            File[] files = file.listFiles();

            for(File lf : files) {
               listAll(lf, list, ignore);
            }
         }

         return list;
      }
   }

   public static File resolveFile(File file, String filename) {
      File result = new File(filename);
      if (!result.isAbsolute()) {
         result = new File(file, filename);
      }

      return normalize(result.getPath());
   }

   public static File normalize(String path) {
      Stack<String> s = new Stack();
      DissectedPath dissectedPath = dissect(path);
      s.push(dissectedPath.root);
      StringTokenizer tok = new StringTokenizer(dissectedPath.remainingPath, File.separator);

      while(tok.hasMoreTokens()) {
         String thisToken = tok.nextToken();
         if (!".".equals(thisToken)) {
            if ("..".equals(thisToken)) {
               if (s.size() < 2) {
                  return new File(path);
               }

               s.pop();
            } else {
               s.push(thisToken);
            }
         }
      }

      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < s.size(); ++i) {
         if (i > 1) {
            sb.append(File.separatorChar);
         }

         sb.append((String)s.elementAt(i));
      }

      return new File(sb.toString());
   }

   private static DissectedPath dissect(String path) {
      char sep = File.separatorChar;
      String pathToDissect = path.replace('/', sep).replace('\\', sep).trim();
      File[] filesystemRoots = File.listRoots();
      if (filesystemRoots != null) {
         for(File filesystemRoot : filesystemRoots) {
            if (pathToDissect.startsWith(filesystemRoot.getPath())) {
               String root = filesystemRoot.getPath();
               String rest = pathToDissect.substring(root.length());
               StringBuilder sbPath = new StringBuilder();

               for(int i = 0; i < rest.length(); ++i) {
                  char currentChar = rest.charAt(i);
                  if (i == 0) {
                     sbPath.append(currentChar);
                  } else {
                     char previousChar = rest.charAt(i - 1);
                     if (currentChar != sep || previousChar != sep) {
                        sbPath.append(currentChar);
                     }
                  }
               }

               return new DissectedPath(root, sbPath.toString());
            }
         }
      }

      if (pathToDissect.length() > 1 && pathToDissect.charAt(1) == sep) {
         int nextsep = pathToDissect.indexOf(sep, 2);
         nextsep = pathToDissect.indexOf(sep, nextsep + 1);
         String root = nextsep > 2 ? pathToDissect.substring(0, nextsep + 1) : pathToDissect;
         String rest = pathToDissect.substring(root.length());
         return new DissectedPath(root, rest);
      } else {
         return new DissectedPath(File.separator, pathToDissect);
      }
   }

   public static boolean isLeadingPath(File leading, File path) {
      String l = normalize(leading.getAbsolutePath()).getAbsolutePath();
      String p = normalize(path.getAbsolutePath()).getAbsolutePath();
      if (l.equals(p)) {
         return true;
      } else {
         if (!l.endsWith(File.separator)) {
            l = l + File.separator;
         }

         String up = File.separator + ".." + File.separator;
         return !l.contains(up) && !p.contains(up) && !(p + File.separator).contains(up) ? p.startsWith(l) : false;
      }
   }

   public static boolean isLeadingPath(File leading, File path, boolean resolveSymlinks) throws IOException {
      if (!resolveSymlinks) {
         return isLeadingPath(leading, path);
      } else {
         File l = leading.getCanonicalFile();
         File p = path.getCanonicalFile();

         while(!l.equals(p)) {
            p = p.getParentFile();
            if (p == null) {
               return false;
            }
         }

         return true;
      }
   }

   public static long getFileLength(File file) {
      long l = 0L;
      if (file.isDirectory()) {
         File[] files = file.listFiles();
         if (files != null) {
            for(File gf : files) {
               l += getFileLength(gf);
            }
         }
      } else {
         l = file.length();
      }

      return l;
   }

   public static InputStream unwrapPack200(InputStream packed) throws IOException {
      BufferedInputStream buffered = new BufferedInputStream(packed);
      buffered.mark(4);
      byte[] magic = new byte[4];
      buffered.read(magic, 0, 4);
      buffered.reset();
      InputStream in = buffered;
      if (magic[0] == 31 && magic[1] == -117 && magic[2] == 8) {
         in = new GZIPInputStream(buffered);
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      JarOutputStream jar = new JarOutputStream(baos);
      Pack200.newUnpacker().unpack(new UncloseInputStream(in), jar);
      jar.close();
      return new ByteArrayInputStream(baos.toByteArray());
   }

   private static final class UncloseInputStream extends InputStream {
      private InputStream wrapped;

      public UncloseInputStream(InputStream wrapped) {
         this.wrapped = wrapped;
      }

      public void close() throws IOException {
      }

      public int read() throws IOException {
         return this.wrapped.read();
      }

      public int hashCode() {
         return this.wrapped.hashCode();
      }

      public int read(byte[] b) throws IOException {
         return this.wrapped.read(b);
      }

      public boolean equals(Object obj) {
         return this.wrapped.equals(obj);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         return this.wrapped.read(b, off, len);
      }

      public long skip(long n) throws IOException {
         return this.wrapped.skip(n);
      }

      public String toString() {
         return this.wrapped.toString();
      }

      public int available() throws IOException {
         return this.wrapped.available();
      }

      public void mark(int readlimit) {
         this.wrapped.mark(readlimit);
      }

      public void reset() throws IOException {
         this.wrapped.reset();
      }

      public boolean markSupported() {
         return this.wrapped.markSupported();
      }
   }

   private static final class DissectedPath {
      private final String root;
      private final String remainingPath;

      private DissectedPath(String root, String remainingPath) {
         this.root = root;
         this.remainingPath = remainingPath;
      }

      public String toString() {
         return "Dissected Path [root=" + this.root + ", remainingPath=" + this.remainingPath + "]";
      }
   }
}
