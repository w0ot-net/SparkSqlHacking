package shaded.parquet.com.fasterxml.jackson.databind.ext;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DOMSerializer extends StdSerializer {
   protected final TransformerFactory transformerFactory;

   public DOMSerializer() {
      super(Node.class);

      try {
         this.transformerFactory = TransformerFactory.newInstance();
         this.transformerFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
         setTransformerFactoryAttribute(this.transformerFactory, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
         setTransformerFactoryAttribute(this.transformerFactory, "http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
      } catch (Exception e) {
         throw new IllegalStateException("Could not instantiate `TransformerFactory`: " + e.getMessage(), e);
      }
   }

   public void serialize(Node value, JsonGenerator g, SerializerProvider provider) throws IOException {
      try {
         Transformer transformer = this.transformerFactory.newTransformer();
         transformer.setOutputProperty("omit-xml-declaration", "yes");
         transformer.setOutputProperty("indent", "no");
         StreamResult result = new StreamResult(new StringWriter());
         transformer.transform(new DOMSource(value), result);
         g.writeString(result.getWriter().toString());
      } catch (TransformerConfigurationException e) {
         throw new IllegalStateException("Could not create XML Transformer for writing DOM `Node` value: " + e.getMessage(), e);
      } catch (TransformerException e) {
         provider.reportMappingProblem(e, "DOM `Node` value serialization failed: %s", e.getMessage());
      }

   }

   /** @deprecated */
   @Deprecated
   public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
      return this.createSchemaNode("string", true);
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      if (visitor != null) {
         visitor.expectAnyFormat(typeHint);
      }

   }

   private static void setTransformerFactoryAttribute(TransformerFactory transformerFactory, String name, Object value) {
      try {
         transformerFactory.setAttribute(name, value);
      } catch (Exception var4) {
         System.err.println("[DOMSerializer] Failed to set TransformerFactory attribute: " + name);
      }

   }
}
