package org.apache.avro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;
import org.apache.avro.util.UtfTextUtils;

public class SchemaParser {
   private final ParseContext parseContext = new ParseContext();
   private final Collection formattedSchemaParsers = new ArrayList();

   public SchemaParser() {
      for(FormattedSchemaParser formattedSchemaParser : ServiceLoader.load(FormattedSchemaParser.class)) {
         this.formattedSchemaParsers.add(formattedSchemaParser);
      }

      this.formattedSchemaParsers.add(new JsonSchemaParser());
   }

   public ParseResult parse(File file) throws IOException, SchemaParseException {
      return this.parse((File)file, (Charset)null);
   }

   public ParseResult parse(File file, Charset charset) throws IOException, SchemaParseException {
      return this.parse(file.toPath(), charset);
   }

   public ParseResult parse(Path file) throws IOException, SchemaParseException {
      return this.parse((Path)file, (Charset)null);
   }

   public ParseResult parse(Path file, Charset charset) throws IOException, SchemaParseException {
      URI inputDir = file.getParent().toUri();
      InputStream stream = Files.newInputStream(file);

      ParseResult var6;
      try {
         String formattedSchema = UtfTextUtils.readAllBytes(stream, charset);
         var6 = this.parse((URI)inputDir, (CharSequence)formattedSchema);
      } catch (Throwable var8) {
         if (stream != null) {
            try {
               stream.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }
         }

         throw var8;
      }

      if (stream != null) {
         stream.close();
      }

      return var6;
   }

   public ParseResult parse(URI location, Charset charset) throws IOException, SchemaParseException {
      InputStream stream = location.toURL().openStream();

      ParseResult var5;
      try {
         String formattedSchema = UtfTextUtils.readAllBytes(stream, charset);
         var5 = this.parse((URI)location, (CharSequence)formattedSchema);
      } catch (Throwable var7) {
         if (stream != null) {
            try {
               stream.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }
         }

         throw var7;
      }

      if (stream != null) {
         stream.close();
      }

      return var5;
   }

   public ParseResult parse(InputStream in) throws IOException, SchemaParseException {
      return this.parse((InputStream)in, (Charset)null);
   }

   public ParseResult parse(InputStream in, Charset charset) throws IOException, SchemaParseException {
      return this.parse((CharSequence)UtfTextUtils.readAllBytes(in, charset));
   }

   public ParseResult parse(Reader in) throws IOException, SchemaParseException {
      return this.parse((CharSequence)UtfTextUtils.readAllChars(in));
   }

   public ParseResult parse(CharSequence text) throws SchemaParseException {
      try {
         return this.parse((URI)null, (CharSequence)text);
      } catch (IOException e) {
         throw new AvroRuntimeException("Could not read schema", e);
      }
   }

   private ParseResult parse(URI baseUri, CharSequence formattedSchema) throws IOException, SchemaParseException {
      List<SchemaParseException> parseExceptions = new ArrayList();

      for(FormattedSchemaParser formattedSchemaParser : this.formattedSchemaParsers) {
         try {
            Schema schema = formattedSchemaParser.parse(this.parseContext, baseUri, formattedSchema);
            if (this.parseContext.hasNewSchemas() || schema != null) {
               return this.parseContext.commit(schema);
            }
         } catch (SchemaParseException e) {
            this.parseContext.rollback();
            parseExceptions.add(e);
         }
      }

      if (parseExceptions.size() == 1) {
         throw (SchemaParseException)parseExceptions.get(0);
      } else {
         SchemaParseException parseException = new SchemaParseException("Could not parse the schema (the suppressed exceptions tell why).");
         Objects.requireNonNull(parseException);
         parseExceptions.forEach(parseException::addSuppressed);
         throw parseException;
      }
   }

   public List getParsedNamedSchemas() {
      return this.parseContext.resolveAllSchemas();
   }

   /** @deprecated */
   @Deprecated
   public Schema resolve(ParseResult result) {
      return result.mainSchema();
   }

   public interface ParseResult {
      Schema mainSchema();

      List parsedNamedSchemas();
   }
}
