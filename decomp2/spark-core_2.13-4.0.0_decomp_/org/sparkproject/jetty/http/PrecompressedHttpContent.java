package org.sparkproject.jetty.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import org.sparkproject.jetty.util.resource.Resource;

public class PrecompressedHttpContent implements HttpContent {
   private final HttpContent _content;
   private final HttpContent _precompressedContent;
   private final CompressedContentFormat _format;

   public PrecompressedHttpContent(HttpContent content, HttpContent precompressedContent, CompressedContentFormat format) {
      this._content = content;
      this._precompressedContent = precompressedContent;
      this._format = format;
      if (this._precompressedContent == null || this._format == null) {
         throw new NullPointerException("Missing compressed content and/or format");
      }
   }

   public int hashCode() {
      return this._content.hashCode();
   }

   public boolean equals(Object obj) {
      return this._content.equals(obj);
   }

   public Resource getResource() {
      return this._content.getResource();
   }

   public HttpField getETag() {
      return new HttpField(HttpHeader.ETAG, this.getETagValue());
   }

   public String getETagValue() {
      return this._content.getResource().getWeakETag(this._format.getEtagSuffix());
   }

   public HttpField getLastModified() {
      return this._content.getLastModified();
   }

   public String getLastModifiedValue() {
      return this._content.getLastModifiedValue();
   }

   public HttpField getContentType() {
      return this._content.getContentType();
   }

   public String getContentTypeValue() {
      return this._content.getContentTypeValue();
   }

   public HttpField getContentEncoding() {
      return this._format.getContentEncoding();
   }

   public String getContentEncodingValue() {
      return this._format.getContentEncoding().getValue();
   }

   public String getCharacterEncoding() {
      return this._content.getCharacterEncoding();
   }

   public MimeTypes.Type getMimeType() {
      return this._content.getMimeType();
   }

   public void release() {
      this._content.release();
   }

   public ByteBuffer getIndirectBuffer() {
      return this._precompressedContent.getIndirectBuffer();
   }

   public ByteBuffer getDirectBuffer() {
      return this._precompressedContent.getDirectBuffer();
   }

   public HttpField getContentLength() {
      return this._precompressedContent.getContentLength();
   }

   public long getContentLengthValue() {
      return this._precompressedContent.getContentLengthValue();
   }

   public InputStream getInputStream() throws IOException {
      return this._precompressedContent.getInputStream();
   }

   public ReadableByteChannel getReadableByteChannel() throws IOException {
      return this._precompressedContent.getReadableByteChannel();
   }

   public String toString() {
      return String.format("%s@%x{e=%s,r=%s|%s,lm=%s|%s,ct=%s}", this.getClass().getSimpleName(), this.hashCode(), this._format, this._content.getResource(), this._precompressedContent.getResource(), this._content.getResource().lastModified(), this._precompressedContent.getResource().lastModified(), this.getContentType());
   }

   public Map getPrecompressedContents() {
      return null;
   }
}
