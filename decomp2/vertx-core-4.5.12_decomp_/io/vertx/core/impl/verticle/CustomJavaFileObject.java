package io.vertx.core.impl.verticle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;

public class CustomJavaFileObject implements JavaFileObject {
   private final String binaryName;
   private final JavaFileObject.Kind kind;
   private final URI uri;

   protected CustomJavaFileObject(URI uri, JavaFileObject.Kind kind, String binaryName) {
      this.uri = uri;
      this.kind = kind;
      this.binaryName = binaryName;
   }

   public String binaryName() {
      return this.binaryName;
   }

   public InputStream openInputStream() throws IOException {
      return this.uri.toURL().openStream();
   }

   public JavaFileObject.Kind getKind() {
      return this.kind;
   }

   public NestingKind getNestingKind() {
      return null;
   }

   public URI toUri() {
      return this.uri;
   }

   public String getName() {
      return this.toUri().getPath();
   }

   public OutputStream openOutputStream() throws IOException {
      throw new UnsupportedOperationException();
   }

   public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
      throw new UnsupportedOperationException();
   }

   public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
      throw new UnsupportedOperationException();
   }

   public Writer openWriter() throws IOException {
      return new OutputStreamWriter(this.openOutputStream());
   }

   public long getLastModified() {
      return 0L;
   }

   public boolean delete() {
      return false;
   }

   public boolean isNameCompatible(String simpleName, JavaFileObject.Kind kind) {
      String name = simpleName + kind.extension;
      return (name.equals(this.toUri().getPath()) || this.toUri().getPath().endsWith('/' + name)) && kind.equals(this.getKind());
   }

   public Modifier getAccessLevel() {
      return null;
   }

   public String toString() {
      return this.getClass().getName() + '[' + this.toUri() + ']';
   }
}
