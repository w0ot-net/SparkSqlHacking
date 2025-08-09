package org.glassfish.jersey.message.internal;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Singleton
public final class SourceProvider {
   @Produces({"application/xml", "text/xml", "*/*"})
   @Consumes({"application/xml", "text/xml", "*/*"})
   @Singleton
   public static final class StreamSourceReader implements MessageBodyReader {
      public boolean isReadable(Class t, Type gt, Annotation[] as, MediaType mediaType) {
         return StreamSource.class == t || Source.class == t;
      }

      public StreamSource readFrom(Class t, Type gt, Annotation[] as, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException {
         return new StreamSource(entityStream);
      }
   }

   @Produces({"application/xml", "text/xml", "*/*"})
   @Consumes({"application/xml", "text/xml", "*/*"})
   @Singleton
   public static final class SaxSourceReader implements MessageBodyReader {
      private final Provider spf;

      @Inject
      public SaxSourceReader(@Context Provider spf) {
         this.spf = spf;
      }

      public boolean isReadable(Class t, Type gt, Annotation[] as, MediaType mediaType) {
         return SAXSource.class == t;
      }

      public SAXSource readFrom(Class t, Type gt, Annotation[] as, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException {
         try {
            return new SAXSource(((SAXParserFactory)this.spf.get()).newSAXParser().getXMLReader(), new InputSource(entityStream));
         } catch (SAXParseException ex) {
            throw new BadRequestException(ex);
         } catch (SAXException ex) {
            throw new InternalServerErrorException(ex);
         } catch (ParserConfigurationException ex) {
            throw new InternalServerErrorException(ex);
         }
      }
   }

   @Produces({"application/xml", "text/xml", "*/*"})
   @Consumes({"application/xml", "text/xml", "*/*"})
   @Singleton
   public static final class DomSourceReader implements MessageBodyReader {
      private final Provider dbf;

      @Inject
      public DomSourceReader(@Context Provider dbf) {
         this.dbf = dbf;
      }

      public boolean isReadable(Class t, Type gt, Annotation[] as, MediaType mediaType) {
         return DOMSource.class == t;
      }

      public DOMSource readFrom(Class t, Type gt, Annotation[] as, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException {
         try {
            Document d = ((DocumentBuilderFactory)this.dbf.get()).newDocumentBuilder().parse(entityStream);
            return new DOMSource(d);
         } catch (SAXParseException ex) {
            throw new BadRequestException(ex);
         } catch (SAXException ex) {
            throw new InternalServerErrorException(ex);
         } catch (ParserConfigurationException ex) {
            throw new InternalServerErrorException(ex);
         }
      }
   }

   @Produces({"application/xml", "text/xml", "*/*"})
   @Consumes({"application/xml", "text/xml", "*/*"})
   @Singleton
   public static final class SourceWriter implements MessageBodyWriter {
      private final Provider saxParserFactory;
      private final Provider transformerFactory;

      @Inject
      public SourceWriter(@Context Provider spf, @Context Provider tf) {
         this.saxParserFactory = spf;
         this.transformerFactory = tf;
      }

      public boolean isWriteable(Class t, Type gt, Annotation[] as, MediaType mediaType) {
         return Source.class.isAssignableFrom(t);
      }

      public long getSize(Source o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
         return -1L;
      }

      public void writeTo(Source source, Class t, Type gt, Annotation[] as, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException {
         try {
            if (source instanceof StreamSource) {
               StreamSource stream = (StreamSource)source;
               InputSource inputStream = new InputSource(stream.getInputStream());
               inputStream.setCharacterStream(inputStream.getCharacterStream());
               inputStream.setPublicId(stream.getPublicId());
               inputStream.setSystemId(source.getSystemId());
               source = new SAXSource(((SAXParserFactory)this.saxParserFactory.get()).newSAXParser().getXMLReader(), inputStream);
            }

            StreamResult sr = new StreamResult(entityStream);
            ((TransformerFactory)this.transformerFactory.get()).newTransformer().transform(source, sr);
         } catch (SAXException ex) {
            throw new InternalServerErrorException(ex);
         } catch (ParserConfigurationException ex) {
            throw new InternalServerErrorException(ex);
         } catch (TransformerException ex) {
            throw new InternalServerErrorException(ex);
         }
      }
   }
}
