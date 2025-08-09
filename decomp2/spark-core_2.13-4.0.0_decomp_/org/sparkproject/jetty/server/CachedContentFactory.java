package org.sparkproject.jetty.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.DateGenerator;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.http.PrecompressedHttpContent;
import org.sparkproject.jetty.http.ResourceHttpContent;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.resource.ResourceFactory;

public class CachedContentFactory implements HttpContent.ContentFactory {
   private static final Logger LOG = LoggerFactory.getLogger(CachedContentFactory.class);
   private static final Map NO_PRECOMPRESSED = Collections.unmodifiableMap(Collections.emptyMap());
   private final ConcurrentMap _cache;
   private final AtomicInteger _cachedSize;
   private final AtomicInteger _cachedFiles;
   private final ResourceFactory _factory;
   private final CachedContentFactory _parent;
   private final MimeTypes _mimeTypes;
   private final boolean _etags;
   private final CompressedContentFormat[] _precompressedFormats;
   private final boolean _useFileMappedBuffer;
   private int _maxCachedFileSize = 134217728;
   private int _maxCachedFiles = 2048;
   private int _maxCacheSize = 268435456;

   public CachedContentFactory(CachedContentFactory parent, ResourceFactory factory, MimeTypes mimeTypes, boolean useFileMappedBuffer, boolean etags, CompressedContentFormat[] precompressedFormats) {
      this._factory = factory;
      this._cache = new ConcurrentHashMap();
      this._cachedSize = new AtomicInteger();
      this._cachedFiles = new AtomicInteger();
      this._mimeTypes = mimeTypes;
      this._parent = parent;
      this._useFileMappedBuffer = useFileMappedBuffer;
      this._etags = etags;
      this._precompressedFormats = precompressedFormats;
   }

   public int getCachedSize() {
      return this._cachedSize.get();
   }

   public int getCachedFiles() {
      return this._cachedFiles.get();
   }

   public int getMaxCachedFileSize() {
      return this._maxCachedFileSize;
   }

   public void setMaxCachedFileSize(int maxCachedFileSize) {
      this._maxCachedFileSize = maxCachedFileSize;
      this.shrinkCache();
   }

   public int getMaxCacheSize() {
      return this._maxCacheSize;
   }

   public void setMaxCacheSize(int maxCacheSize) {
      this._maxCacheSize = maxCacheSize;
      this.shrinkCache();
   }

   public int getMaxCachedFiles() {
      return this._maxCachedFiles;
   }

   public void setMaxCachedFiles(int maxCachedFiles) {
      this._maxCachedFiles = maxCachedFiles;
      this.shrinkCache();
   }

   public boolean isUseFileMappedBuffer() {
      return this._useFileMappedBuffer;
   }

   public void flushCache() {
      label19:
      while(true) {
         if (this._cache.size() > 0) {
            Iterator var1 = this._cache.keySet().iterator();

            while(true) {
               if (!var1.hasNext()) {
                  continue label19;
               }

               String path = (String)var1.next();
               CachedHttpContent content = (CachedHttpContent)this._cache.remove(path);
               if (content != null) {
                  content.invalidate();
               }
            }
         }

         return;
      }
   }

   public HttpContent getContent(String pathInContext, int maxBufferSize) throws IOException {
      CachedHttpContent content = (CachedHttpContent)this._cache.get(pathInContext);
      if (content != null && content.isValid()) {
         return content;
      } else {
         Resource resource = this._factory.getResource(pathInContext);
         HttpContent loaded = this.load(pathInContext, resource, maxBufferSize);
         if (loaded != null) {
            return loaded;
         } else {
            if (this._parent != null) {
               HttpContent httpContent = this._parent.getContent(pathInContext, maxBufferSize);
               if (httpContent != null) {
                  return httpContent;
               }
            }

            return null;
         }
      }
   }

   protected boolean isCacheable(Resource resource) {
      if (this._maxCachedFiles <= 0) {
         return false;
      } else {
         long len = resource.length();
         return len > 0L && (this._useFileMappedBuffer || len < (long)this._maxCachedFileSize && len < (long)this._maxCacheSize);
      }
   }

   private HttpContent load(String pathInContext, Resource resource, int maxBufferSize) throws IOException {
      if (resource != null && resource.exists()) {
         if (resource.isDirectory()) {
            return new ResourceHttpContent(resource, this._mimeTypes.getMimeByExtension(resource.toString()), this.getMaxCachedFileSize());
         } else if (this.isCacheable(resource)) {
            CachedHttpContent content;
            if (this._precompressedFormats.length > 0) {
               Map<CompressedContentFormat, CachedHttpContent> precompresssedContents = new HashMap(this._precompressedFormats.length);

               for(CompressedContentFormat format : this._precompressedFormats) {
                  String compressedPathInContext = pathInContext + format.getExtension();
                  CachedHttpContent compressedContent = (CachedHttpContent)this._cache.get(compressedPathInContext);
                  if (compressedContent == null || compressedContent.isValid()) {
                     compressedContent = null;
                     Resource compressedResource = this._factory.getResource(compressedPathInContext);
                     if (compressedResource.exists() && compressedResource.lastModified() >= resource.lastModified() && compressedResource.length() < resource.length()) {
                        compressedContent = new CachedHttpContent(compressedPathInContext, compressedResource, (Map)null);
                        CachedHttpContent added = (CachedHttpContent)this._cache.putIfAbsent(compressedPathInContext, compressedContent);
                        if (added != null) {
                           compressedContent.invalidate();
                           compressedContent = added;
                        }
                     }
                  }

                  if (compressedContent != null) {
                     precompresssedContents.put(format, compressedContent);
                  }
               }

               content = new CachedHttpContent(pathInContext, resource, precompresssedContents);
            } else {
               content = new CachedHttpContent(pathInContext, resource, (Map)null);
            }

            CachedHttpContent added = (CachedHttpContent)this._cache.putIfAbsent(pathInContext, content);
            if (added != null) {
               content.invalidate();
               content = added;
            }

            return content;
         } else {
            String mt = this._mimeTypes.getMimeByExtension(pathInContext);
            if (this._precompressedFormats.length > 0) {
               Map<CompressedContentFormat, HttpContent> compressedContents = new HashMap();

               for(CompressedContentFormat format : this._precompressedFormats) {
                  String compressedPathInContext = pathInContext + format.getExtension();
                  CachedHttpContent compressedContent = (CachedHttpContent)this._cache.get(compressedPathInContext);
                  if (compressedContent != null && compressedContent.isValid() && compressedContent.getResource().lastModified() >= resource.lastModified()) {
                     compressedContents.put(format, compressedContent);
                  }

                  Resource compressedResource = this._factory.getResource(compressedPathInContext);
                  if (compressedResource.exists() && compressedResource.lastModified() >= resource.lastModified() && compressedResource.length() < resource.length()) {
                     compressedContents.put(format, new ResourceHttpContent(compressedResource, this._mimeTypes.getMimeByExtension(compressedPathInContext), maxBufferSize));
                  }
               }

               if (!compressedContents.isEmpty()) {
                  return new ResourceHttpContent(resource, mt, maxBufferSize, compressedContents);
               }
            }

            return new ResourceHttpContent(resource, mt, maxBufferSize);
         }
      } else {
         return null;
      }
   }

   private void shrinkCache() {
      while(this._cache.size() > 0 && (this._cachedFiles.get() > this._maxCachedFiles || this._cachedSize.get() > this._maxCacheSize)) {
         SortedSet<CachedHttpContent> sorted = new TreeSet((c1, c2) -> {
            if (c1._lastAccessed < c2._lastAccessed) {
               return -1;
            } else if (c1._lastAccessed > c2._lastAccessed) {
               return 1;
            } else {
               return c1._contentLengthValue < c2._contentLengthValue ? -1 : c1._key.compareTo(c2._key);
            }
         });
         sorted.addAll(this._cache.values());

         for(CachedHttpContent content : sorted) {
            if (this._cachedFiles.get() <= this._maxCachedFiles && this._cachedSize.get() <= this._maxCacheSize) {
               break;
            }

            if (content == this._cache.remove(content.getKey())) {
               content.invalidate();
            }
         }
      }

   }

   protected ByteBuffer getIndirectBuffer(Resource resource) {
      try {
         return BufferUtil.toBuffer(resource, false);
      } catch (IllegalArgumentException | IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to get Indirect Buffer for {}", resource, e);
         }

         return null;
      }
   }

   protected ByteBuffer getMappedBuffer(Resource resource) {
      try {
         if (this._useFileMappedBuffer && resource.getFile() != null && resource.length() < 2147483647L) {
            return BufferUtil.toMappedBuffer(resource.getFile());
         }
      } catch (IllegalArgumentException | IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to get Mapped Buffer for {}", resource, e);
         }
      }

      return null;
   }

   protected ByteBuffer getDirectBuffer(Resource resource) {
      try {
         return BufferUtil.toBuffer(resource, true);
      } catch (IllegalArgumentException | IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to get Direct Buffer for {}", resource, e);
         }

         return null;
      }
   }

   public String toString() {
      String var10000 = String.valueOf(this._parent);
      return "ResourceCache[" + var10000 + "," + String.valueOf(this._factory) + "]@" + this.hashCode();
   }

   public class CachedHttpContent implements HttpContent {
      private final String _key;
      private final Resource _resource;
      private final long _contentLengthValue;
      private final HttpField _contentType;
      private final String _characterEncoding;
      private final MimeTypes.Type _mimeType;
      private final HttpField _contentLength;
      private final HttpField _lastModified;
      private final long _lastModifiedValue;
      private final HttpField _etag;
      private final Map _precompressed;
      private final AtomicReference _indirectBuffer = new AtomicReference();
      private final AtomicReference _directBuffer = new AtomicReference();
      private final AtomicReference _mappedBuffer = new AtomicReference();
      private volatile long _lastAccessed;

      CachedHttpContent(String pathInContext, Resource resource, Map precompressedResources) {
         this._key = pathInContext;
         this._resource = resource;
         String contentType = CachedContentFactory.this._mimeTypes.getMimeByExtension(this._resource.toString());
         this._contentType = contentType == null ? null : new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, contentType);
         this._characterEncoding = this._contentType == null ? null : MimeTypes.getCharsetFromContentType(contentType);
         this._mimeType = this._contentType == null ? null : (MimeTypes.Type)MimeTypes.CACHE.get(MimeTypes.getContentTypeWithoutCharset(contentType));
         boolean exists = resource.exists();
         this._lastModifiedValue = exists ? resource.lastModified() : -1L;
         this._lastModified = this._lastModifiedValue == -1L ? null : new PreEncodedHttpField(HttpHeader.LAST_MODIFIED, DateGenerator.formatDate(this._lastModifiedValue));
         this._contentLengthValue = exists ? resource.length() : 0L;
         this._contentLength = new PreEncodedHttpField(HttpHeader.CONTENT_LENGTH, Long.toString(this._contentLengthValue));
         if (CachedContentFactory.this._cachedFiles.incrementAndGet() > CachedContentFactory.this._maxCachedFiles) {
            CachedContentFactory.this.shrinkCache();
         }

         this._lastAccessed = System.currentTimeMillis();
         this._etag = CachedContentFactory.this._etags ? new PreEncodedHttpField(HttpHeader.ETAG, resource.getWeakETag()) : null;
         if (precompressedResources != null) {
            this._precompressed = new HashMap(precompressedResources.size());

            for(Map.Entry entry : precompressedResources.entrySet()) {
               this._precompressed.put((CompressedContentFormat)entry.getKey(), CachedContentFactory.this.new CachedPrecompressedHttpContent(this, (CachedHttpContent)entry.getValue(), (CompressedContentFormat)entry.getKey()));
            }
         } else {
            this._precompressed = CachedContentFactory.NO_PRECOMPRESSED;
         }

      }

      public String getKey() {
         return this._key;
      }

      public boolean isCached() {
         return this._key != null;
      }

      public Resource getResource() {
         return this._resource;
      }

      public HttpField getETag() {
         return this._etag;
      }

      public String getETagValue() {
         return this._etag.getValue();
      }

      boolean isValid() {
         if (this._lastModifiedValue == this._resource.lastModified() && this._contentLengthValue == this._resource.length()) {
            this._lastAccessed = System.currentTimeMillis();
            return true;
         } else {
            if (this == CachedContentFactory.this._cache.remove(this._key)) {
               this.invalidate();
            }

            return false;
         }
      }

      protected void invalidate() {
         ByteBuffer indirect = (ByteBuffer)this._indirectBuffer.getAndSet((Object)null);
         if (indirect != null) {
            CachedContentFactory.this._cachedSize.addAndGet(-BufferUtil.length(indirect));
         }

         ByteBuffer direct = (ByteBuffer)this._directBuffer.getAndSet((Object)null);
         if (direct != null) {
            CachedContentFactory.this._cachedSize.addAndGet(-BufferUtil.length(direct));
         }

         this._mappedBuffer.getAndSet((Object)null);
         CachedContentFactory.this._cachedFiles.decrementAndGet();
         this._resource.close();
      }

      public HttpField getLastModified() {
         return this._lastModified;
      }

      public String getLastModifiedValue() {
         return this._lastModified == null ? null : this._lastModified.getValue();
      }

      public HttpField getContentType() {
         return this._contentType;
      }

      public String getContentTypeValue() {
         return this._contentType == null ? null : this._contentType.getValue();
      }

      public HttpField getContentEncoding() {
         return null;
      }

      public String getContentEncodingValue() {
         return null;
      }

      public String getCharacterEncoding() {
         return this._characterEncoding;
      }

      public MimeTypes.Type getMimeType() {
         return this._mimeType;
      }

      public void release() {
      }

      public ByteBuffer getIndirectBuffer() {
         if (this._resource.length() > (long)CachedContentFactory.this._maxCachedFileSize) {
            return null;
         } else {
            ByteBuffer buffer = (ByteBuffer)this._indirectBuffer.get();
            if (buffer == null) {
               ByteBuffer buffer2 = CachedContentFactory.this.getIndirectBuffer(this._resource);
               if (buffer2 == null) {
                  if (CachedContentFactory.LOG.isDebugEnabled()) {
                     CachedContentFactory.LOG.debug("Could not load indirect buffer from {}", this);
                  }

                  return null;
               }

               if (this._indirectBuffer.compareAndSet((Object)null, buffer2)) {
                  buffer = buffer2;
                  if (CachedContentFactory.this._cachedSize.addAndGet(BufferUtil.length(buffer2)) > CachedContentFactory.this._maxCacheSize) {
                     CachedContentFactory.this.shrinkCache();
                  }
               } else {
                  buffer = (ByteBuffer)this._indirectBuffer.get();
               }
            }

            return buffer == null ? null : buffer.asReadOnlyBuffer();
         }
      }

      public ByteBuffer getDirectBuffer() {
         ByteBuffer buffer = (ByteBuffer)this._mappedBuffer.get();
         if (buffer == null) {
            buffer = (ByteBuffer)this._directBuffer.get();
         }

         if (buffer == null) {
            ByteBuffer mapped = CachedContentFactory.this.getMappedBuffer(this._resource);
            if (mapped != null) {
               if (this._mappedBuffer.compareAndSet((Object)null, mapped)) {
                  buffer = mapped;
               } else {
                  buffer = (ByteBuffer)this._mappedBuffer.get();
               }
            } else if (this._resource.length() < (long)CachedContentFactory.this._maxCachedFileSize) {
               ByteBuffer direct = CachedContentFactory.this.getDirectBuffer(this._resource);
               if (direct != null) {
                  if (this._directBuffer.compareAndSet((Object)null, direct)) {
                     buffer = direct;
                     if (CachedContentFactory.this._cachedSize.addAndGet(BufferUtil.length(direct)) > CachedContentFactory.this._maxCacheSize) {
                        CachedContentFactory.this.shrinkCache();
                     }
                  } else {
                     buffer = (ByteBuffer)this._directBuffer.get();
                  }
               } else if (CachedContentFactory.LOG.isDebugEnabled()) {
                  CachedContentFactory.LOG.debug("Could not load{}", this);
               }
            }
         }

         return buffer == null ? null : buffer.asReadOnlyBuffer();
      }

      public HttpField getContentLength() {
         return this._contentLength;
      }

      public long getContentLengthValue() {
         return this._contentLengthValue;
      }

      public InputStream getInputStream() throws IOException {
         ByteBuffer indirect = this.getIndirectBuffer();
         return (InputStream)(indirect != null && indirect.hasArray() ? new ByteArrayInputStream(indirect.array(), indirect.arrayOffset() + indirect.position(), indirect.remaining()) : this._resource.getInputStream());
      }

      public ReadableByteChannel getReadableByteChannel() throws IOException {
         return this._resource.getReadableByteChannel();
      }

      public String toString() {
         return String.format("CachedContent@%x{r=%s,e=%b,lm=%s,ct=%s,c=%d}", this.hashCode(), this._resource, this._resource.exists(), this._lastModified, this._contentType, this._precompressed.size());
      }

      public Map getPrecompressedContents() {
         if (this._precompressed.size() == 0) {
            return null;
         } else {
            Map<CompressedContentFormat, CachedPrecompressedHttpContent> ret = this._precompressed;

            for(Map.Entry entry : this._precompressed.entrySet()) {
               if (!((CachedPrecompressedHttpContent)entry.getValue()).isValid()) {
                  if (ret == this._precompressed) {
                     ret = new HashMap(this._precompressed);
                  }

                  ret.remove(entry.getKey());
               }
            }

            return ret;
         }
      }
   }

   public class CachedPrecompressedHttpContent extends PrecompressedHttpContent {
      private final CachedHttpContent _content;
      private final CachedHttpContent _precompressedContent;
      private final HttpField _etag;

      CachedPrecompressedHttpContent(CachedHttpContent content, CachedHttpContent precompressedContent, CompressedContentFormat format) {
         super(content, precompressedContent, format);
         this._content = content;
         this._precompressedContent = precompressedContent;
         this._etag = CachedContentFactory.this._etags ? new PreEncodedHttpField(HttpHeader.ETAG, this._content.getResource().getWeakETag(format.getEtagSuffix())) : null;
      }

      public boolean isValid() {
         return this._precompressedContent.isValid() && this._content.isValid() && this._content.getResource().lastModified() <= this._precompressedContent.getResource().lastModified();
      }

      public HttpField getETag() {
         return this._etag != null ? this._etag : super.getETag();
      }

      public String getETagValue() {
         return this._etag != null ? this._etag.getValue() : super.getETagValue();
      }

      public String toString() {
         return "Cached" + super.toString();
      }
   }
}
