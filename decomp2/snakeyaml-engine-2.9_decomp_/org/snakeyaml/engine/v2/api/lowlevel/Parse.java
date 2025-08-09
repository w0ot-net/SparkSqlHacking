package org.snakeyaml.engine.v2.api.lowlevel;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Objects;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.api.YamlUnicodeReader;
import org.snakeyaml.engine.v2.parser.ParserImpl;
import org.snakeyaml.engine.v2.scanner.StreamReader;

public class Parse {
   private final LoadSettings settings;

   public Parse(LoadSettings settings) {
      Objects.requireNonNull(settings, "LoadSettings cannot be null");
      this.settings = settings;
   }

   public Iterable parseInputStream(InputStream yaml) {
      Objects.requireNonNull(yaml, "InputStream cannot be null");
      return () -> new ParserImpl(this.settings, new StreamReader(this.settings, new YamlUnicodeReader(yaml)));
   }

   public Iterable parseReader(Reader yaml) {
      Objects.requireNonNull(yaml, "Reader cannot be null");
      return () -> new ParserImpl(this.settings, new StreamReader(this.settings, yaml));
   }

   public Iterable parseString(final String yaml) {
      Objects.requireNonNull(yaml, "String cannot be null");
      return new Iterable() {
         public Iterator iterator() {
            return new ParserImpl(Parse.this.settings, new StreamReader(Parse.this.settings, new StringReader(yaml)));
         }
      };
   }
}
