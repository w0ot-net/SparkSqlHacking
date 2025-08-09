package org.snakeyaml.engine.v2.api;

import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import org.snakeyaml.engine.v2.composer.Composer;
import org.snakeyaml.engine.v2.constructor.BaseConstructor;
import org.snakeyaml.engine.v2.constructor.StandardConstructor;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.parser.ParserImpl;
import org.snakeyaml.engine.v2.scanner.StreamReader;

public class Load {
   private final LoadSettings settings;
   private final BaseConstructor constructor;

   public Load(LoadSettings settings) {
      this(settings, new StandardConstructor(settings));
   }

   public Load(LoadSettings settings, BaseConstructor constructor) {
      Objects.requireNonNull(settings, "LoadSettings cannot be null");
      Objects.requireNonNull(constructor, "BaseConstructor cannot be null");
      this.settings = settings;
      this.constructor = constructor;
   }

   private Composer createComposer(StreamReader streamReader) {
      return new Composer(this.settings, new ParserImpl(this.settings, streamReader));
   }

   protected Composer createComposer(InputStream yamlStream) {
      return this.createComposer(new StreamReader(this.settings, new YamlUnicodeReader(yamlStream)));
   }

   protected Composer createComposer(String yaml) {
      return this.createComposer(new StreamReader(this.settings, yaml));
   }

   protected Composer createComposer(Reader yamlReader) {
      return this.createComposer(new StreamReader(this.settings, yamlReader));
   }

   protected Object loadOne(Composer composer) {
      Optional<Node> nodeOptional = composer.getSingleNode();
      return this.constructor.constructSingleDocument(nodeOptional);
   }

   public Object loadFromInputStream(InputStream yamlStream) {
      Objects.requireNonNull(yamlStream, "InputStream cannot be null");
      return this.loadOne(this.createComposer(yamlStream));
   }

   public Object loadFromReader(Reader yamlReader) {
      Objects.requireNonNull(yamlReader, "Reader cannot be null");
      return this.loadOne(this.createComposer(yamlReader));
   }

   public Object loadFromString(String yaml) {
      Objects.requireNonNull(yaml, "String cannot be null");
      return this.loadOne(this.createComposer(yaml));
   }

   private Iterable loadAll(Composer composer) {
      Iterator<Object> result = new YamlIterator(composer, this.constructor);
      return new YamlIterable(result);
   }

   public Iterable loadAllFromInputStream(InputStream yamlStream) {
      Objects.requireNonNull(yamlStream, "InputStream cannot be null");
      Composer composer = this.createComposer(new StreamReader(this.settings, new YamlUnicodeReader(yamlStream)));
      return this.loadAll(composer);
   }

   public Iterable loadAllFromReader(Reader yamlReader) {
      Objects.requireNonNull(yamlReader, "Reader cannot be null");
      Composer composer = this.createComposer(new StreamReader(this.settings, yamlReader));
      return this.loadAll(composer);
   }

   public Iterable loadAllFromString(String yaml) {
      Objects.requireNonNull(yaml, "String cannot be null");
      Composer composer = this.createComposer(new StreamReader(this.settings, yaml));
      return this.loadAll(composer);
   }

   private static class YamlIterable implements Iterable {
      private final Iterator iterator;

      public YamlIterable(Iterator iterator) {
         this.iterator = iterator;
      }

      public Iterator iterator() {
         return this.iterator;
      }
   }

   private static class YamlIterator implements Iterator {
      private final Composer composer;
      private final BaseConstructor constructor;
      private boolean composerInitiated = false;

      public YamlIterator(Composer composer, BaseConstructor constructor) {
         this.composer = composer;
         this.constructor = constructor;
      }

      public boolean hasNext() {
         this.composerInitiated = true;
         return this.composer.hasNext();
      }

      public Object next() {
         if (!this.composerInitiated) {
            this.hasNext();
         }

         Node node = this.composer.next();
         return this.constructor.constructSingleDocument(Optional.of(node));
      }

      public void remove() {
         throw new UnsupportedOperationException("Removing is not supported.");
      }
   }
}
