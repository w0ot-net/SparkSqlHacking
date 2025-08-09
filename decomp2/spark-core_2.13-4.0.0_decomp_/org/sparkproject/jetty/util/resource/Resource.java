package org.sparkproject.jetty.util.resource;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.UrlEncoded;

public abstract class Resource implements ResourceFactory, Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(Resource.class);
   public static boolean __defaultUseCaches = true;
   volatile Object _associate;

   public static void setDefaultUseCaches(boolean useCaches) {
      __defaultUseCaches = useCaches;
   }

   public static boolean getDefaultUseCaches() {
      return __defaultUseCaches;
   }

   public static Resource resolveAlias(Resource resource) {
      if (!resource.isAlias()) {
         return resource;
      } else {
         try {
            File file = resource.getFile();
            if (file != null) {
               return newResource(file.toPath().toRealPath());
            }
         } catch (IOException e) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("resolve alias failed", e);
            }
         }

         return resource;
      }
   }

   public static Resource newResource(URI uri) throws MalformedURLException {
      return newResource(uri.toURL());
   }

   public static Resource newResource(URL url) {
      return newResource(url, __defaultUseCaches);
   }

   static Resource newResource(URL url, boolean useCaches) {
      if (url == null) {
         return null;
      } else {
         String urlString = url.toExternalForm();
         if (urlString.startsWith("file:")) {
            try {
               return new PathResource(url);
            } catch (Exception var4) {
               if (LOG.isDebugEnabled()) {
                  LOG.warn("Bad PathResource: {}", url, var4);
               } else {
                  LOG.warn("Bad PathResource: {} {}", url, var4.toString());
               }

               return new BadResource(url, var4.toString());
            }
         } else if (urlString.startsWith("jar:file:")) {
            return new JarFileResource(url, useCaches);
         } else {
            return (Resource)(urlString.startsWith("jar:") ? new JarResource(url, useCaches) : new URLResource(url, (URLConnection)null, useCaches));
         }
      }
   }

   public static Resource newResource(String resource) throws IOException {
      return newResource(resource, __defaultUseCaches);
   }

   public static Resource newResource(String resource, boolean useCaches) throws IOException {
      URL url;
      try {
         url = new URL(resource);
      } catch (MalformedURLException e) {
         if (!resource.startsWith("ftp:") && !resource.startsWith("file:") && !resource.startsWith("jar:")) {
            return new PathResource(Paths.get(resource));
         }

         LOG.warn("Bad Resource: {}", resource);
         throw e;
      }

      return newResource(url, useCaches);
   }

   public static Resource newResource(File file) {
      return new PathResource(file.toPath());
   }

   public static Resource newResource(Path path) {
      return new PathResource(path);
   }

   public static Resource newSystemResource(String resource) throws IOException {
      URL url = null;
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      if (loader != null) {
         try {
            url = loader.getResource(resource);
            if (url == null && resource.startsWith("/")) {
               url = loader.getResource(resource.substring(1));
            }
         } catch (IllegalArgumentException e) {
            LOG.trace("IGNORED", e);
            url = null;
         }
      }

      if (url == null) {
         loader = Resource.class.getClassLoader();
         if (loader != null) {
            url = loader.getResource(resource);
            if (url == null && resource.startsWith("/")) {
               url = loader.getResource(resource.substring(1));
            }
         }
      }

      if (url == null) {
         url = ClassLoader.getSystemResource(resource);
         if (url == null && resource.startsWith("/")) {
            url = ClassLoader.getSystemResource(resource.substring(1));
         }
      }

      return url == null ? null : newResource(url);
   }

   public static Resource newClassPathResource(String resource) {
      return newClassPathResource(resource, true, false);
   }

   public static Resource newClassPathResource(String name, boolean useCaches, boolean checkParents) {
      URL url = Resource.class.getResource(name);
      if (url == null) {
         url = Loader.getResource(name);
      }

      return url == null ? null : newResource(url, useCaches);
   }

   public static boolean isContainedIn(Resource r, Resource containingResource) throws MalformedURLException {
      return r.isContainedIn(containingResource);
   }

   public abstract boolean isContainedIn(Resource var1) throws MalformedURLException;

   public boolean isSame(Resource resource) {
      return this.equals(resource);
   }

   public abstract void close();

   public abstract boolean exists();

   public abstract boolean isDirectory();

   public abstract long lastModified();

   public abstract long length();

   public abstract URI getURI();

   public abstract File getFile() throws IOException;

   public abstract String getName();

   public abstract InputStream getInputStream() throws IOException;

   public abstract ReadableByteChannel getReadableByteChannel() throws IOException;

   public abstract boolean delete() throws SecurityException;

   public abstract boolean renameTo(Resource var1) throws SecurityException;

   public abstract String[] list();

   public abstract Resource addPath(String var1) throws IOException, MalformedURLException;

   public Resource getResource(String path) throws IOException {
      return this.addPath(path);
   }

   public Object getAssociate() {
      return this._associate;
   }

   public void setAssociate(Object o) {
      this._associate = o;
   }

   public boolean isAlias() {
      return this.getAlias() != null;
   }

   public URI getAlias() {
      return null;
   }

   public String getListHTML(String base, boolean parent, String query) throws IOException {
      base = URIUtil.canonicalPath(base);
      if (base != null && this.isDirectory()) {
         String[] rawListing = this.list();
         if (rawListing == null) {
            return null;
         } else {
            boolean sortOrderAscending = true;
            String sortColumn = "N";
            if (query != null) {
               MultiMap<String> params = new MultiMap();
               UrlEncoded.decodeUtf8To(query, 0, query.length(), params);
               String paramO = params.getString("O");
               String paramC = params.getString("C");
               if (StringUtil.isNotBlank(paramO)) {
                  if (paramO.equals("A")) {
                     sortOrderAscending = true;
                  } else if (paramO.equals("D")) {
                     sortOrderAscending = false;
                  }
               }

               if (StringUtil.isNotBlank(paramC) && (paramC.equals("N") || paramC.equals("M") || paramC.equals("S"))) {
                  sortColumn = paramC;
               }
            }

            List<Resource> items = new ArrayList();

            for(String l : rawListing) {
               Resource item = this.addPath(l);
               items.add(item);
            }

            if (sortColumn.equals("M")) {
               items.sort(ResourceCollators.byLastModified(sortOrderAscending));
            } else if (sortColumn.equals("S")) {
               items.sort(ResourceCollators.bySize(sortOrderAscending));
            } else {
               items.sort(ResourceCollators.byName(sortOrderAscending));
            }

            String decodedBase = URIUtil.decodePath(base);
            String title = "Directory: " + deTag(decodedBase);
            StringBuilder buf = new StringBuilder(4096);
            buf.append("<!DOCTYPE html>\n");
            buf.append("<html lang=\"en\">\n");
            buf.append("<head>\n");
            buf.append("<meta charset=\"utf-8\">\n");
            buf.append("<link href=\"jetty-dir.css\" rel=\"stylesheet\" />\n");
            buf.append("<title>");
            buf.append(title);
            buf.append("</title>\n");
            buf.append("</head>\n");
            buf.append("<body>\n");
            buf.append("<h1 class=\"title\">").append(title).append("</h1>\n");
            String ARROW_DOWN = "&nbsp; &#8681;";
            String ARROW_UP = "&nbsp; &#8679;";
            buf.append("<table class=\"listing\">\n");
            buf.append("<thead>\n");
            String arrow = "";
            String order = "A";
            if (sortColumn.equals("N")) {
               if (sortOrderAscending) {
                  order = "D";
                  arrow = "&nbsp; &#8679;";
               } else {
                  order = "A";
                  arrow = "&nbsp; &#8681;";
               }
            }

            buf.append("<tr><th class=\"name\"><a href=\"?C=N&O=").append(order).append("\">");
            buf.append("Name").append(arrow);
            buf.append("</a></th>");
            arrow = "";
            order = "A";
            if (sortColumn.equals("M")) {
               if (sortOrderAscending) {
                  order = "D";
                  arrow = "&nbsp; &#8679;";
               } else {
                  order = "A";
                  arrow = "&nbsp; &#8681;";
               }
            }

            buf.append("<th class=\"lastmodified\"><a href=\"?C=M&O=").append(order).append("\">");
            buf.append("Last Modified").append(arrow);
            buf.append("</a></th>");
            arrow = "";
            order = "A";
            if (sortColumn.equals("S")) {
               if (sortOrderAscending) {
                  order = "D";
                  arrow = "&nbsp; &#8679;";
               } else {
                  order = "A";
                  arrow = "&nbsp; &#8681;";
               }
            }

            buf.append("<th class=\"size\"><a href=\"?C=S&O=").append(order).append("\">");
            buf.append("Size").append(arrow);
            buf.append("</a></th></tr>\n");
            buf.append("</thead>\n");
            buf.append("<tbody>\n");
            String encodedBase = hrefEncodeURI(base);
            if (parent) {
               buf.append("<tr><td class=\"name\"><a href=\"");
               buf.append(URIUtil.addPaths(encodedBase, "../"));
               buf.append("\">Parent Directory</a></td>");
               buf.append("<td class=\"lastmodified\">-</td>");
               buf.append("<td>-</td>");
               buf.append("</tr>\n");
            }

            DateFormat dfmt = DateFormat.getDateTimeInstance(2, 2);

            for(Resource item : items) {
               String name = item.getFileName();
               if (!StringUtil.isBlank(name)) {
                  if (item.isDirectory() && !name.endsWith("/")) {
                     name = name + "/";
                  }

                  buf.append("<tr><td class=\"name\"><a href=\"");
                  String path = URIUtil.addEncodedPaths(encodedBase, URIUtil.encodePath(name));
                  buf.append(path);
                  buf.append("\">");
                  buf.append(deTag(name));
                  buf.append("&nbsp;");
                  buf.append("</a></td>");
                  buf.append("<td class=\"lastmodified\">");
                  long lastModified = item.lastModified();
                  if (lastModified > 0L) {
                     buf.append(dfmt.format(new Date(item.lastModified())));
                  }

                  buf.append("&nbsp;</td>");
                  buf.append("<td class=\"size\">");
                  long length = item.length();
                  if (length >= 0L) {
                     buf.append(String.format("%,d bytes", item.length()));
                  }

                  buf.append("&nbsp;</td></tr>\n");
               }
            }

            buf.append("</tbody>\n");
            buf.append("</table>\n");
            buf.append("</body></html>\n");
            return buf.toString();
         }
      } else {
         return null;
      }
   }

   private String getFileName() {
      try {
         File file = this.getFile();
         if (file != null) {
            return file.getName();
         }
      } catch (Throwable var5) {
      }

      try {
         String rawName = this.getName();
         int idx = rawName.lastIndexOf(47);
         if (idx == rawName.length() - 1) {
            idx = rawName.lastIndexOf(47, idx - 1);
         }

         String encodedFileName;
         if (idx >= 0) {
            encodedFileName = rawName.substring(idx + 1);
         } else {
            encodedFileName = rawName;
         }

         return UrlEncoded.decodeString(encodedFileName, 0, encodedFileName.length(), StandardCharsets.UTF_8);
      } catch (Throwable var4) {
         return null;
      }
   }

   private static String hrefEncodeURI(String param0) {
      // $FF: Couldn't be decompiled
   }

   private static String deTag(String raw) {
      return StringUtil.sanitizeXmlString(raw);
   }

   public void copyTo(File destination) throws IOException {
      if (destination.exists()) {
         throw new IllegalArgumentException(String.valueOf(destination) + " exists");
      } else {
         File src = this.getFile();
         if (src != null) {
            Files.copy(src.toPath(), destination.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
         } else {
            InputStream in = this.getInputStream();

            try {
               OutputStream out = new FileOutputStream(destination);

               try {
                  IO.copy(in, out);
               } catch (Throwable var9) {
                  try {
                     out.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }

                  throw var9;
               }

               out.close();
            } catch (Throwable var10) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var7) {
                     var10.addSuppressed(var7);
                  }
               }

               throw var10;
            }

            if (in != null) {
               in.close();
            }

         }
      }
   }

   public String getWeakETag() {
      return this.getWeakETag("");
   }

   public String getWeakETag(String suffix) {
      StringBuilder b = new StringBuilder(32);
      b.append("W/\"");
      String name = this.getName();
      int length = name.length();
      long lhash = 0L;

      for(int i = 0; i < length; ++i) {
         lhash = 31L * lhash + (long)name.charAt(i);
      }

      Base64.Encoder encoder = Base64.getEncoder().withoutPadding();
      b.append(encoder.encodeToString(longToBytes(this.lastModified() ^ lhash)));
      b.append(encoder.encodeToString(longToBytes(this.length() ^ lhash)));
      b.append(suffix);
      b.append('"');
      return b.toString();
   }

   private static byte[] longToBytes(long value) {
      byte[] result = new byte[8];

      for(int i = 7; i >= 0; --i) {
         result[i] = (byte)((int)(value & 255L));
         value >>= 8;
      }

      return result;
   }

   public Collection getAllResources() {
      try {
         ArrayList<Resource> deep = new ArrayList();
         String[] list = this.list();
         if (list != null) {
            for(String i : list) {
               Resource r = this.addPath(i);
               if (r.isDirectory()) {
                  deep.addAll(r.getAllResources());
               } else {
                  deep.add(r);
               }
            }
         }

         return deep;
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   public static URL toURL(File file) throws MalformedURLException {
      return file.toURI().toURL();
   }

   public static List fromList(String resources, boolean globDirs) throws IOException {
      return fromList(resources, globDirs, Resource::newResource);
   }

   public static List fromList(String resources, boolean globDirs, ResourceFactory resourceFactory) throws IOException {
      if (StringUtil.isBlank(resources)) {
         return Collections.emptyList();
      } else {
         List<Resource> returnedResources = new ArrayList();
         StringTokenizer tokenizer = new StringTokenizer(resources, ",;");

         while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.endsWith("/*") && !token.endsWith("\\*")) {
               returnedResources.add(resourceFactory.getResource(token));
            } else {
               String dir = token.substring(0, token.length() - 2);
               Resource dirResource = resourceFactory.getResource(dir);
               if (dirResource.exists() && dirResource.isDirectory()) {
                  String[] entries = dirResource.list();
                  if (entries != null) {
                     Arrays.sort(entries);

                     for(String entry : entries) {
                        try {
                           Resource resource = dirResource.addPath(entry);
                           if (!resource.isDirectory()) {
                              returnedResources.add(resource);
                           } else if (globDirs) {
                              returnedResources.add(resource);
                           }
                        } catch (Exception ex) {
                           LOG.warn("Bad glob [{}] entry: {}", new Object[]{token, entry, ex});
                        }
                     }
                  }
               }
            }
         }

         return returnedResources;
      }
   }
}
