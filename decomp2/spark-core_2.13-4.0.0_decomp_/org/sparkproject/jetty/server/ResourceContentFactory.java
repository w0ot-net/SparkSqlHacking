package org.sparkproject.jetty.server;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.ResourceHttpContent;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.resource.ResourceFactory;

public class ResourceContentFactory implements HttpContent.ContentFactory {
   private final ResourceFactory _factory;
   private final MimeTypes _mimeTypes;
   private final CompressedContentFormat[] _precompressedFormats;

   public ResourceContentFactory(ResourceFactory factory, MimeTypes mimeTypes, CompressedContentFormat[] precompressedFormats) {
      this._factory = factory;
      this._mimeTypes = mimeTypes;
      this._precompressedFormats = precompressedFormats;
   }

   public HttpContent getContent(String pathInContext, int maxBufferSize) throws IOException {
      try {
         Resource resource = this._factory.getResource(pathInContext);
         return this.load(pathInContext, resource, maxBufferSize);
      } catch (Throwable t) {
         InvalidPathException saferException = new InvalidPathException(pathInContext, "Invalid PathInContext");
         saferException.initCause(t);
         throw saferException;
      }
   }

   private HttpContent load(String pathInContext, Resource resource, int maxBufferSize) throws IOException {
      if (resource != null && resource.exists()) {
         if (resource.isDirectory()) {
            return new ResourceHttpContent(resource, this._mimeTypes.getMimeByExtension(resource.toString()), maxBufferSize);
         } else {
            String mt = this._mimeTypes.getMimeByExtension(pathInContext);
            if (this._precompressedFormats.length > 0) {
               Map<CompressedContentFormat, HttpContent> compressedContents = new HashMap(this._precompressedFormats.length);

               for(CompressedContentFormat format : this._precompressedFormats) {
                  String compressedPathInContext = pathInContext + format.getExtension();
                  Resource compressedResource = this._factory.getResource(compressedPathInContext);
                  if (compressedResource != null && compressedResource.exists() && compressedResource.lastModified() >= resource.lastModified() && compressedResource.length() < resource.length()) {
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

   public String toString() {
      String var10000 = String.valueOf(this._factory);
      return "ResourceContentFactory[" + var10000 + "]@" + this.hashCode();
   }
}
