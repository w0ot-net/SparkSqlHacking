package scala.xml;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import org.xml.sax.InputSource;

public final class Source$ {
   public static final Source$ MODULE$ = new Source$();

   public InputSource fromFile(final String name) {
      return this.fromFile(new File(name));
   }

   public InputSource fromFile(final File file) {
      return this.fromUrl(file.toURI().toURL());
   }

   public InputSource fromUrl(final URL url) {
      return this.fromSysId(url.toString());
   }

   public InputSource fromSysId(final String sysID) {
      return new InputSource(sysID);
   }

   public InputSource fromFile(final FileDescriptor fd) {
      return this.fromInputStream(new FileInputStream(fd));
   }

   public InputSource fromInputStream(final InputStream is) {
      return new InputSource(is);
   }

   public InputSource fromString(final String string) {
      return this.fromReader(new StringReader(string));
   }

   public InputSource fromReader(final Reader reader) {
      return new InputSource(reader);
   }

   private Source$() {
   }
}
