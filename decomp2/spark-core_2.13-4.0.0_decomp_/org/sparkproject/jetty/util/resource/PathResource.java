package org.sparkproject.jetty.util.resource;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;

public class PathResource extends Resource {
   private static final Logger LOG = LoggerFactory.getLogger(PathResource.class);
   private static final LinkOption[] NO_FOLLOW_LINKS;
   private static final LinkOption[] FOLLOW_LINKS;
   private final Path path;
   private final Path alias;
   private final URI uri;
   private final boolean belongsToDefaultFileSystem;

   private Path checkAliasPath() {
      Path abs = this.path;
      if (!URIUtil.equalsIgnoreEncodings(this.uri, this.path.toUri())) {
         try {
            return Paths.get(this.uri).toRealPath(FOLLOW_LINKS);
         } catch (IOException ignored) {
            LOG.trace("IGNORED", ignored);
         }
      }

      if (!abs.isAbsolute()) {
         abs = this.path.toAbsolutePath();
      }

      Path normal = this.path.normalize();
      if (!isSameName(abs, normal)) {
         return normal;
      } else {
         try {
            if (Files.isSymbolicLink(this.path)) {
               return this.path.getParent().resolve(Files.readSymbolicLink(this.path));
            }

            if (Files.exists(this.path, new LinkOption[0])) {
               Path real = abs.toRealPath(FOLLOW_LINKS);
               if (!isSameName(abs, real)) {
                  return real;
               }
            }
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
         } catch (Exception e) {
            LOG.warn("bad alias ({} {}) for {}", new Object[]{e.getClass().getName(), e.getMessage(), this.path});
         }

         return null;
      }
   }

   public static boolean isSameName(Path pathA, Path pathB) {
      int aCount = pathA.getNameCount();
      int bCount = pathB.getNameCount();
      if (aCount != bCount) {
         return false;
      } else {
         int i = bCount;

         while(i-- > 0) {
            if (!pathA.getName(i).toString().equals(pathB.getName(i).toString())) {
               return false;
            }
         }

         return true;
      }
   }

   public PathResource(File file) {
      this(file.toPath());
   }

   public PathResource(Path path) {
      Path absPath = path;

      try {
         absPath = path.toRealPath(NO_FOLLOW_LINKS);
      } catch (IOException | IOError e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to get real/canonical path for {}", path, e);
         }
      }

      this.path = absPath;
      this.assertValidPath(path);
      this.uri = this.path.toUri();
      this.alias = this.checkAliasPath();
      this.belongsToDefaultFileSystem = this.path.getFileSystem() == FileSystems.getDefault();
   }

   private PathResource(PathResource parent, String childPath) {
      this.path = parent.path.getFileSystem().getPath(parent.path.toString(), childPath);
      if (this.isDirectory() && !childPath.endsWith("/")) {
         childPath = childPath + "/";
      }

      this.uri = URIUtil.addPath(parent.uri, childPath);
      this.alias = this.checkAliasPath();
      this.belongsToDefaultFileSystem = this.path.getFileSystem() == FileSystems.getDefault();
   }

   public PathResource(URI uri) throws IOException {
      if (!uri.isAbsolute()) {
         throw new IllegalArgumentException("not an absolute uri");
      } else if (!uri.getScheme().equalsIgnoreCase("file")) {
         throw new IllegalArgumentException("not file: scheme");
      } else {
         Path path;
         try {
            path = Paths.get(uri);
         } catch (IllegalArgumentException e) {
            throw e;
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
            throw new IOException("Unable to build Path from: " + String.valueOf(uri), e);
         }

         this.path = path.toAbsolutePath();
         this.uri = path.toUri();
         this.alias = this.checkAliasPath();
         this.belongsToDefaultFileSystem = this.path.getFileSystem() == FileSystems.getDefault();
      }
   }

   public PathResource(URL url) throws IOException, URISyntaxException {
      this(url.toURI());
   }

   public boolean isSame(Resource resource) {
      try {
         if (resource instanceof PathResource) {
            Path path = ((PathResource)resource).getPath();
            return Files.isSameFile(this.getPath(), path);
         }
      } catch (IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("ignored", e);
         }
      }

      return false;
   }

   public Resource addPath(String subPath) throws IOException {
      if (URIUtil.canonicalPath(subPath) == null) {
         throw new MalformedURLException(subPath);
      } else {
         return "/".equals(subPath) ? this : new PathResource(this, subPath);
      }
   }

   private void assertValidPath(Path path) {
      String str = path.toString();
      int idx = StringUtil.indexOfControlChars(str);
      if (idx >= 0) {
         throw new InvalidPathException(str, "Invalid Character at index " + idx);
      }
   }

   public void close() {
   }

   public boolean delete() throws SecurityException {
      try {
         return Files.deleteIfExists(this.path);
      } catch (IOException e) {
         LOG.trace("IGNORED", e);
         return false;
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         PathResource other = (PathResource)obj;
         if (this.path == null) {
            if (other.path != null) {
               return false;
            }
         } else if (!this.path.equals(other.path)) {
            return false;
         }

         return true;
      }
   }

   public boolean exists() {
      return Files.exists(this.path, NO_FOLLOW_LINKS);
   }

   public File getFile() throws IOException {
      return !this.belongsToDefaultFileSystem ? null : this.path.toFile();
   }

   public Path getPath() {
      return this.path;
   }

   public InputStream getInputStream() throws IOException {
      return Files.newInputStream(this.path, StandardOpenOption.READ);
   }

   public String getName() {
      return this.path.toAbsolutePath().toString();
   }

   public ReadableByteChannel getReadableByteChannel() throws IOException {
      return this.newSeekableByteChannel();
   }

   public SeekableByteChannel newSeekableByteChannel() throws IOException {
      return Files.newByteChannel(this.path, StandardOpenOption.READ);
   }

   public URI getURI() {
      return this.uri;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.path == null ? 0 : this.path.hashCode());
      return result;
   }

   public boolean isContainedIn(Resource r) {
      try {
         PathResource pr = (PathResource)PathResource.class.cast(r);
         return this.path.startsWith(pr.getPath());
      } catch (ClassCastException var3) {
         return false;
      }
   }

   public boolean isDirectory() {
      return Files.isDirectory(this.path, FOLLOW_LINKS);
   }

   public long lastModified() {
      try {
         FileTime ft = Files.getLastModifiedTime(this.path, FOLLOW_LINKS);
         return ft.toMillis();
      } catch (IOException e) {
         LOG.trace("IGNORED", e);
         return 0L;
      }
   }

   public long length() {
      try {
         return Files.size(this.path);
      } catch (IOException var2) {
         return 0L;
      }
   }

   public boolean isAlias() {
      return this.alias != null;
   }

   public Path getAliasPath() {
      return this.alias;
   }

   public URI getAlias() {
      return this.alias == null ? null : this.alias.toUri();
   }

   public String[] list() {
      try {
         DirectoryStream<Path> dir = Files.newDirectoryStream(this.path);

         String[] var11;
         try {
            List<String> entries = new ArrayList();

            for(Path entry : dir) {
               String name = entry.getFileName().toString();
               if (Files.isDirectory(entry, new LinkOption[0])) {
                  name = name + "/";
               }

               entries.add(name);
            }

            int size = entries.size();
            var11 = (String[])entries.toArray(new String[size]);
         } catch (Throwable var7) {
            if (dir != null) {
               try {
                  dir.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (dir != null) {
            dir.close();
         }

         return var11;
      } catch (DirectoryIteratorException e) {
         LOG.debug("Directory list failure", e);
      } catch (IOException e) {
         LOG.debug("Directory list access failure", e);
      }

      return null;
   }

   public boolean renameTo(Resource dest) throws SecurityException {
      if (dest instanceof PathResource) {
         PathResource destRes = (PathResource)dest;

         try {
            Path result = Files.move(this.path, destRes.path);
            return Files.exists(result, NO_FOLLOW_LINKS);
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
            return false;
         }
      } else {
         return false;
      }
   }

   public void copyTo(File destination) throws IOException {
      if (this.isDirectory()) {
         IO.copyDir(this.path.toFile(), destination);
      } else {
         Files.copy(this.path, destination.toPath());
      }

   }

   public String toString() {
      return this.uri.toASCIIString();
   }

   static {
      NO_FOLLOW_LINKS = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
      FOLLOW_LINKS = new LinkOption[0];
   }
}
