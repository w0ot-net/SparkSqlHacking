package io.vertx.ext.web.multipart.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.multipart.MultipartForm;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultipartFormImpl implements MultipartForm {
   private Charset charset;
   private final List parts;

   public MultipartFormImpl() {
      this.charset = StandardCharsets.UTF_8;
      this.parts = new ArrayList();
   }

   public MultipartForm setCharset(String charset) {
      return this.setCharset(charset != null ? Charset.forName(charset) : null);
   }

   public MultipartForm setCharset(Charset charset) {
      this.charset = charset;
      return this;
   }

   public Charset getCharset() {
      return this.charset;
   }

   public MultipartForm attribute(String name, String value) {
      this.parts.add(new FormDataPartImpl(name, value));
      return this;
   }

   public MultipartForm textFileUpload(String name, String filename, String pathname, String mediaType) {
      this.parts.add(new FormDataPartImpl(name, filename, pathname, mediaType, true));
      return this;
   }

   public MultipartForm textFileUpload(String name, String filename, Buffer content, String mediaType) {
      this.parts.add(new FormDataPartImpl(name, filename, content, mediaType, true));
      return this;
   }

   public MultipartForm binaryFileUpload(String name, String filename, String pathname, String mediaType) {
      this.parts.add(new FormDataPartImpl(name, filename, pathname, mediaType, false));
      return this;
   }

   public MultipartForm binaryFileUpload(String name, String filename, Buffer content, String mediaType) {
      this.parts.add(new FormDataPartImpl(name, filename, content, mediaType, false));
      return this;
   }

   public Iterator iterator() {
      return this.parts.iterator();
   }
}
