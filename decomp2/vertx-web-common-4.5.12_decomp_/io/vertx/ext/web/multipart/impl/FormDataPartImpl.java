package io.vertx.ext.web.multipart.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.multipart.FormDataPart;

public class FormDataPartImpl implements FormDataPart {
   private final String name;
   private final String value;
   private final String filename;
   private final String mediaType;
   private final String pathname;
   private final Buffer content;
   private final Boolean text;

   public FormDataPartImpl(String name, String value) {
      if (name == null) {
         throw new NullPointerException();
      } else if (value == null) {
         throw new NullPointerException();
      } else {
         this.name = name;
         this.value = value;
         this.filename = null;
         this.pathname = null;
         this.content = null;
         this.mediaType = null;
         this.text = null;
      }
   }

   public FormDataPartImpl(String name, String filename, String pathname, String mediaType, boolean text) {
      if (name == null) {
         throw new NullPointerException();
      } else if (filename == null) {
         throw new NullPointerException();
      } else if (pathname == null) {
         throw new NullPointerException();
      } else if (mediaType == null) {
         throw new NullPointerException();
      } else {
         this.name = name;
         this.value = null;
         this.filename = filename;
         this.pathname = pathname;
         this.content = null;
         this.mediaType = mediaType;
         this.text = text;
      }
   }

   public FormDataPartImpl(String name, String filename, Buffer content, String mediaType, boolean text) {
      if (name == null) {
         throw new NullPointerException();
      } else if (filename == null) {
         throw new NullPointerException();
      } else if (content == null) {
         throw new NullPointerException();
      } else if (mediaType == null) {
         throw new NullPointerException();
      } else {
         this.name = name;
         this.value = null;
         this.filename = filename;
         this.pathname = null;
         this.content = content;
         this.mediaType = mediaType;
         this.text = text;
      }
   }

   public String name() {
      return this.name;
   }

   public boolean isAttribute() {
      return this.value != null;
   }

   public boolean isFileUpload() {
      return this.value == null;
   }

   public String value() {
      return this.value;
   }

   public String filename() {
      return this.filename;
   }

   public String pathname() {
      return this.pathname;
   }

   public Buffer content() {
      return this.content;
   }

   public String mediaType() {
      return this.mediaType;
   }

   public Boolean isText() {
      return this.text;
   }
}
