package org.apache.avro;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Protocol extends JsonProperties {
   public static final long VERSION = 1L;
   private static final Set MESSAGE_RESERVED = Collections.unmodifiableSet(new HashSet(Arrays.asList("doc", "response", "request", "errors", "one-way")));
   private static final Set FIELD_RESERVED = Collections.unmodifiableSet(new HashSet(Arrays.asList("name", "type", "doc", "default", "aliases")));
   private String name;
   private String namespace;
   private String doc;
   private ParseContext context;
   private final Map messages;
   private byte[] md5;
   public static final Schema SYSTEM_ERROR;
   public static final Schema SYSTEM_ERRORS;
   private static final Set PROTOCOL_RESERVED;

   private Protocol() {
      super(PROTOCOL_RESERVED);
      this.context = new ParseContext();
      this.messages = new LinkedHashMap();
   }

   public Protocol(Protocol p) {
      this(p.getName(), p.getDoc(), p.getNamespace());
      this.putAll(p);
   }

   public Protocol(String name, String doc, String namespace) {
      super(PROTOCOL_RESERVED);
      this.context = new ParseContext();
      this.messages = new LinkedHashMap();
      this.setName(name, namespace);
      this.doc = doc;
   }

   public Protocol(String name, String namespace) {
      this(name, (String)null, namespace);
   }

   private void setName(String name, String namespace) {
      int lastDot = name.lastIndexOf(46);
      if (lastDot < 0) {
         this.name = name;
         this.namespace = namespace;
      } else {
         this.name = name.substring(lastDot + 1);
         this.namespace = name.substring(0, lastDot);
      }

      if (this.namespace != null && this.namespace.isEmpty()) {
         this.namespace = null;
      }

   }

   public String getName() {
      return this.name;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public String getDoc() {
      return this.doc;
   }

   public Collection getTypes() {
      return this.context.resolveAllSchemas();
   }

   /** @deprecated */
   @Deprecated
   public Collection getUnresolvedTypes() {
      return this.context.typesByName().values();
   }

   public Schema getType(String name) {
      Schema namedSchema = null;
      if (!name.contains(".")) {
         namedSchema = this.context.getNamedSchema(this.namespace + "." + name);
      }

      return namedSchema != null ? namedSchema : this.context.getNamedSchema(name);
   }

   public void setTypes(Collection newTypes) {
      this.context = new ParseContext();

      for(Schema s : newTypes) {
         this.context.put(s);
      }

      this.context.commit();
   }

   public Map getMessages() {
      return this.messages;
   }

   /** @deprecated */
   @Deprecated
   public Message createMessage(String name, String doc, Schema request) {
      return new Message(name, doc, Collections.emptyMap(), request);
   }

   public Message createMessage(Message m, Schema request) {
      return new Message(m.name, m.doc, m, request);
   }

   public Message createMessage(String name, String doc, JsonProperties propMap, Schema request) {
      return new Message(name, doc, propMap, request);
   }

   public Message createMessage(String name, String doc, Map propMap, Schema request) {
      return new Message(name, doc, propMap, request);
   }

   /** @deprecated */
   @Deprecated
   public Message createMessage(String name, String doc, Schema request, Schema response, Schema errors) {
      return new TwoWayMessage(name, doc, new LinkedHashMap(), request, response, errors);
   }

   public Message createMessage(Message m, Schema request, Schema response, Schema errors) {
      return new TwoWayMessage(m.getName(), m.getDoc(), m, request, response, errors);
   }

   public Message createMessage(String name, String doc, JsonProperties propMap, Schema request, Schema response, Schema errors) {
      return new TwoWayMessage(name, doc, propMap, request, response, errors);
   }

   public Message createMessage(String name, String doc, Map propMap, Schema request, Schema response, Schema errors) {
      return new TwoWayMessage(name, doc, propMap, request, response, errors);
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Protocol)) {
         return false;
      } else {
         Protocol that = (Protocol)o;
         return Objects.equals(this.name, that.name) && Objects.equals(this.namespace, that.namespace) && Objects.equals(this.context.resolveAllSchemas(), that.context.resolveAllSchemas()) && Objects.equals(this.messages, that.messages) && this.propsEqual(that);
      }
   }

   public int hashCode() {
      return 31 * Objects.hash(new Object[]{this.name, this.namespace, this.context, this.messages}) + this.propsHashCode();
   }

   public String toString() {
      return this.toString(false);
   }

   public String toString(boolean pretty) {
      try {
         StringWriter writer = new StringWriter();
         JsonGenerator gen = Schema.FACTORY.createGenerator(writer);
         if (pretty) {
            gen.useDefaultPrettyPrinter();
         }

         this.toJson(gen);
         gen.flush();
         return writer.toString();
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   void toJson(JsonGenerator gen) throws IOException {
      gen.writeStartObject();
      gen.writeStringField("protocol", this.name);
      if (this.namespace != null) {
         gen.writeStringField("namespace", this.namespace);
      }

      if (this.doc != null) {
         gen.writeStringField("doc", this.doc);
      }

      this.writeProps(gen);
      gen.writeArrayFieldStart("types");
      Set<String> knownNames = new HashSet();

      for(Schema type : this.context.resolveAllSchemas()) {
         if (!knownNames.contains(type.getFullName())) {
            type.toJson(knownNames, this.namespace, gen);
         }
      }

      gen.writeEndArray();
      gen.writeObjectFieldStart("messages");

      for(Map.Entry e : this.messages.entrySet()) {
         gen.writeFieldName((String)e.getKey());
         ((Message)e.getValue()).toJson(knownNames, gen);
      }

      gen.writeEndObject();
      gen.writeEndObject();
   }

   public byte[] getMD5() {
      if (this.md5 == null) {
         try {
            this.md5 = MessageDigest.getInstance("MD5").digest(this.toString().getBytes(StandardCharsets.UTF_8));
         } catch (Exception e) {
            throw new AvroRuntimeException(e);
         }
      }

      return this.md5;
   }

   public static Protocol parse(File file) throws IOException {
      JsonParser jsonParser = Schema.FACTORY.createParser(file);

      Protocol var2;
      try {
         var2 = parse(jsonParser);
      } catch (Throwable var5) {
         if (jsonParser != null) {
            try {
               jsonParser.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }
         }

         throw var5;
      }

      if (jsonParser != null) {
         jsonParser.close();
      }

      return var2;
   }

   public static Protocol parse(InputStream stream) throws IOException {
      return parse(Schema.FACTORY.createParser(stream));
   }

   public static Protocol parse(String string, String... more) {
      StringBuilder b = new StringBuilder(string);

      for(String part : more) {
         b.append(part);
      }

      return parse(b.toString());
   }

   public static Protocol parse(String string) {
      try {
         return parse(Schema.FACTORY.createParser(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8))));
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   private static Protocol parse(JsonParser parser) {
      try {
         Protocol protocol = new Protocol();
         protocol.parse((JsonNode)Schema.MAPPER.readTree(parser));
         return protocol;
      } catch (IOException e) {
         throw new SchemaParseException(e);
      }
   }

   private void parse(JsonNode json) {
      this.parseNameAndNamespace(json);
      this.parseTypes(json);
      this.parseMessages(json);
      this.parseDoc(json);
      this.parseProps(json);
      this.context.commit();
      this.context.resolveAllSchemas();
      this.resolveMessageSchemata();
   }

   private void resolveMessageSchemata() {
      for(Map.Entry entry : this.messages.entrySet()) {
         Message oldValue = (Message)entry.getValue();
         Message newValue;
         if (oldValue.isOneWay()) {
            newValue = this.createMessage((String)oldValue.getName(), (String)oldValue.getDoc(), (JsonProperties)oldValue, this.context.resolve(oldValue.getRequest()));
         } else {
            Schema request = this.context.resolve(oldValue.getRequest());
            Schema response = this.context.resolve(oldValue.getResponse());
            Schema errors = this.context.resolve(oldValue.getErrors());
            newValue = this.createMessage(oldValue.getName(), oldValue.getDoc(), (JsonProperties)oldValue, request, response, errors);
         }

         entry.setValue(newValue);
      }

   }

   private void parseNameAndNamespace(JsonNode json) {
      JsonNode nameNode = json.get("protocol");
      if (nameNode == null) {
         throw new SchemaParseException("No protocol name specified: " + String.valueOf(json));
      } else {
         JsonNode namespaceNode = json.get("namespace");
         String namespace = namespaceNode == null ? null : namespaceNode.textValue();
         this.setName(nameNode.textValue(), namespace);
      }
   }

   private void parseDoc(JsonNode json) {
      this.doc = this.parseDocNode(json);
   }

   private String parseDocNode(JsonNode json) {
      JsonNode nameNode = json.get("doc");
      return nameNode == null ? null : nameNode.textValue();
   }

   private void parseTypes(JsonNode json) {
      JsonNode defs = json.get("types");
      if (defs != null) {
         if (!defs.isArray()) {
            throw new SchemaParseException("Types not an array: " + String.valueOf(defs));
         } else {
            for(JsonNode type : defs) {
               if (!type.isObject()) {
                  throw new SchemaParseException("Type not an object: " + String.valueOf(type));
               }

               Schema.parse(type, this.context, this.namespace);
            }

         }
      }
   }

   private void parseProps(JsonNode json) {
      Iterator<String> i = json.fieldNames();

      while(i.hasNext()) {
         String p = (String)i.next();
         if (!PROTOCOL_RESERVED.contains(p)) {
            this.addProp(p, json.get(p));
         }
      }

   }

   private void parseMessages(JsonNode json) {
      JsonNode defs = json.get("messages");
      if (defs != null) {
         Iterator<String> i = defs.fieldNames();

         while(i.hasNext()) {
            String prop = (String)i.next();
            this.messages.put(prop, this.parseMessage(prop, defs.get(prop)));
         }

      }
   }

   private Message parseMessage(String messageName, JsonNode json) {
      String doc = this.parseDocNode(json);
      Map<String, JsonNode> mProps = new LinkedHashMap();
      Iterator<String> i = json.fieldNames();

      while(i.hasNext()) {
         String p = (String)i.next();
         if (!MESSAGE_RESERVED.contains(p)) {
            mProps.put(p, json.get(p));
         }
      }

      JsonNode requestNode = json.get("request");
      if (requestNode != null && requestNode.isArray()) {
         List<Schema.Field> fields = new ArrayList();

         for(JsonNode field : requestNode) {
            JsonNode fieldNameNode = field.get("name");
            if (fieldNameNode == null) {
               throw new SchemaParseException("No param name: " + String.valueOf(field));
            }

            JsonNode fieldTypeNode = field.get("type");
            if (fieldTypeNode == null) {
               throw new SchemaParseException("No param type: " + String.valueOf(field));
            }

            String name = fieldNameNode.textValue();
            String fieldDoc = null;
            JsonNode fieldDocNode = field.get("doc");
            if (fieldDocNode != null) {
               fieldDoc = fieldDocNode.textValue();
            }

            Schema.Field newField = new Schema.Field(name, Schema.parse(fieldTypeNode, this.context, this.namespace), fieldDoc, field.get("default"), true, Schema.Field.Order.ASCENDING);
            Set<String> aliases = Schema.parseAliases(field);
            if (aliases != null) {
               for(String alias : aliases) {
                  newField.addAlias(alias);
               }
            }

            Iterator<String> i = field.fieldNames();

            while(i.hasNext()) {
               String prop = (String)i.next();
               if (!FIELD_RESERVED.contains(prop)) {
                  newField.addProp(prop, field.get(prop));
               }
            }

            fields.add(newField);
         }

         Schema request = Schema.createRecord((String)null, (String)null, (String)null, false, fields);
         boolean oneWay = false;
         JsonNode oneWayNode = json.get("one-way");
         if (oneWayNode != null) {
            if (!oneWayNode.isBoolean()) {
               throw new SchemaParseException("one-way must be boolean: " + String.valueOf(json));
            }

            oneWay = oneWayNode.booleanValue();
         }

         JsonNode responseNode = json.get("response");
         if (!oneWay && responseNode == null) {
            throw new SchemaParseException("No response specified: " + String.valueOf(json));
         } else {
            JsonNode decls = json.get("errors");
            if (oneWay) {
               if (decls != null) {
                  throw new SchemaParseException("one-way can't have errors: " + String.valueOf(json));
               } else if (responseNode != null && Schema.parse(responseNode, this.context, this.namespace).getType() != Schema.Type.NULL) {
                  throw new SchemaParseException("One way response must be null: " + String.valueOf(json));
               } else {
                  return new Message(messageName, doc, mProps, request);
               }
            } else {
               Schema response = Schema.parse(responseNode, this.context, this.namespace);
               List<Schema> errs = new ArrayList();
               errs.add(SYSTEM_ERROR);
               if (decls != null) {
                  if (!decls.isArray()) {
                     throw new SchemaParseException("Errors not an array: " + String.valueOf(json));
                  }

                  for(JsonNode decl : decls) {
                     String name = decl.textValue();
                     Schema schema = this.context.find(name, this.namespace);
                     if (schema == null) {
                        throw new SchemaParseException("Undefined error: " + name);
                     }

                     if (!schema.isError()) {
                        throw new SchemaParseException("Not an error: " + name);
                     }

                     errs.add(schema);
                  }
               }

               return new TwoWayMessage(messageName, doc, mProps, request, response, Schema.createUnion(errs));
            }
         }
      } else {
         throw new SchemaParseException("No request specified: " + String.valueOf(json));
      }
   }

   public static void main(String[] args) throws Exception {
      System.out.println(parse(new File(args[0])));
   }

   static {
      SYSTEM_ERROR = Schema.create(Schema.Type.STRING);
      SYSTEM_ERRORS = Schema.createUnion(Collections.singletonList(SYSTEM_ERROR));
      PROTOCOL_RESERVED = Collections.unmodifiableSet(new HashSet(Arrays.asList("namespace", "protocol", "doc", "messages", "types", "errors")));
   }

   public class Message extends JsonProperties {
      private final String name;
      private final String doc;
      private final Schema request;

      private Message(String name, String doc, JsonProperties propMap, Schema request) {
         super(Protocol.MESSAGE_RESERVED);
         this.name = name;
         this.doc = doc;
         this.request = request;
         if (propMap != null) {
            this.addAllProps(propMap);
         }

      }

      private Message(String name, String doc, Map propMap, Schema request) {
         super(Protocol.MESSAGE_RESERVED, propMap);
         this.name = name;
         this.doc = doc;
         this.request = request;
      }

      public String getName() {
         return this.name;
      }

      public Schema getRequest() {
         return this.request;
      }

      public Schema getResponse() {
         return Schema.create(Schema.Type.NULL);
      }

      public Schema getErrors() {
         return Schema.createUnion(Collections.emptyList());
      }

      public boolean isOneWay() {
         return true;
      }

      public String toString() {
         try {
            StringWriter writer = new StringWriter();
            JsonGenerator gen = Schema.FACTORY.createGenerator(writer);
            this.toJson(new HashSet(), gen);
            gen.flush();
            return writer.toString();
         } catch (IOException e) {
            throw new AvroRuntimeException(e);
         }
      }

      void toJson(Set knownNames, JsonGenerator gen) throws IOException {
         gen.writeStartObject();
         if (this.doc != null) {
            gen.writeStringField("doc", this.doc);
         }

         this.writeProps(gen);
         gen.writeFieldName("request");
         this.request.fieldsToJson(knownNames, Protocol.this.namespace, gen);
         this.toJson1(knownNames, gen);
         gen.writeEndObject();
      }

      void toJson1(Set knownNames, JsonGenerator gen) throws IOException {
         gen.writeStringField("response", "null");
         gen.writeBooleanField("one-way", true);
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Message)) {
            return false;
         } else {
            Message that = (Message)o;
            return this.name.equals(that.name) && this.request.equals(that.request) && this.propsEqual(that);
         }
      }

      public int hashCode() {
         return this.name.hashCode() + this.request.hashCode() + this.propsHashCode();
      }

      public String getDoc() {
         return this.doc;
      }
   }

   private final class TwoWayMessage extends Message {
      private final Schema response;
      private final Schema errors;

      private TwoWayMessage(String name, String doc, Map propMap, Schema request, Schema response, Schema errors) {
         super(name, doc, (Map)propMap, request);
         this.response = response;
         this.errors = errors;
      }

      private TwoWayMessage(String name, String doc, JsonProperties propMap, Schema request, Schema response, Schema errors) {
         super(name, doc, (JsonProperties)propMap, request);
         this.response = response;
         this.errors = errors;
      }

      public Schema getResponse() {
         return this.response;
      }

      public Schema getErrors() {
         return this.errors;
      }

      public boolean isOneWay() {
         return false;
      }

      public boolean equals(Object o) {
         if (!super.equals(o)) {
            return false;
         } else if (!(o instanceof TwoWayMessage)) {
            return false;
         } else {
            TwoWayMessage that = (TwoWayMessage)o;
            return this.response.equals(that.response) && this.errors.equals(that.errors);
         }
      }

      public int hashCode() {
         return super.hashCode() + this.response.hashCode() + this.errors.hashCode();
      }

      void toJson1(Set knownNames, JsonGenerator gen) throws IOException {
         gen.writeFieldName("response");
         this.response.toJson(knownNames, Protocol.this.namespace, gen);
         List<Schema> errs = this.errors.getTypes();
         if (errs.size() > 1) {
            Schema union = Schema.createUnion(errs.subList(1, errs.size()));
            gen.writeFieldName("errors");
            union.toJson(knownNames, Protocol.this.namespace, gen);
         }

      }
   }
}
