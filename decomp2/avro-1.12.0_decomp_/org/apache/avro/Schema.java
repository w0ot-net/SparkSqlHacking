package org.apache.avro;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.NullNode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.avro.path.TracingAvroTypeException;
import org.apache.avro.util.internal.Accessor;
import org.apache.avro.util.internal.JacksonUtils;
import org.apache.avro.util.internal.ThreadLocalWithInitial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Schema extends JsonProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   static final JsonFactory FACTORY = new JsonFactory();
   static final Logger LOG = LoggerFactory.getLogger(Schema.class);
   static final ObjectMapper MAPPER;
   private static final int NO_HASHCODE = Integer.MIN_VALUE;
   private final Type type;
   private LogicalType logicalType = null;
   private static final Set SCHEMA_RESERVED;
   private static final Set ENUM_RESERVED;
   int hashCode = Integer.MIN_VALUE;
   private static final Set FIELD_RESERVED;
   private static final ThreadLocal SEEN_EQUALS;
   private static final ThreadLocal SEEN_HASHCODE;
   static final Map PRIMITIVES;
   private static final ThreadLocal VALIDATE_NAMES;
   private static final ThreadLocal VALIDATE_DEFAULTS;

   protected Object writeReplace() {
      SerializableSchema ss = new SerializableSchema();
      ss.schemaString = this.toString();
      return ss;
   }

   Schema(Type type) {
      super(type == Schema.Type.ENUM ? ENUM_RESERVED : SCHEMA_RESERVED);
      this.type = type;
   }

   public static Schema create(Type type) {
      switch (type.ordinal()) {
         case 6:
            return new StringSchema();
         case 7:
            return new BytesSchema();
         case 8:
            return new IntSchema();
         case 9:
            return new LongSchema();
         case 10:
            return new FloatSchema();
         case 11:
            return new DoubleSchema();
         case 12:
            return new BooleanSchema();
         case 13:
            return new NullSchema();
         default:
            throw new AvroRuntimeException("Can't create a: " + String.valueOf(type));
      }
   }

   public void addProp(String name, String value) {
      super.addProp(name, value);
      this.hashCode = Integer.MIN_VALUE;
   }

   public void addProp(String name, Object value) {
      super.addProp(name, value);
      this.hashCode = Integer.MIN_VALUE;
   }

   public LogicalType getLogicalType() {
      return this.logicalType;
   }

   void setLogicalType(LogicalType logicalType) {
      this.logicalType = logicalType;
   }

   /** @deprecated */
   @Deprecated
   public static Schema createRecord(List fields) {
      Schema result = createRecord((String)null, (String)null, (String)null, false);
      result.setFields(fields);
      return result;
   }

   public static Schema createRecord(String name, String doc, String namespace, boolean isError) {
      return new RecordSchema(new Name(name, namespace), doc, isError);
   }

   public static Schema createRecord(String name, String doc, String namespace, boolean isError, List fields) {
      return new RecordSchema(new Name(name, namespace), doc, isError, fields);
   }

   public static Schema createEnum(String name, String doc, String namespace, List values) {
      return new EnumSchema(new Name(name, namespace), doc, new LockableArrayList(values), (String)null);
   }

   public static Schema createEnum(String name, String doc, String namespace, List values, String enumDefault) {
      return new EnumSchema(new Name(name, namespace), doc, new LockableArrayList(values), enumDefault);
   }

   public static Schema createArray(Schema elementType) {
      return new ArraySchema(elementType);
   }

   public static Schema createMap(Schema valueType) {
      return new MapSchema(valueType);
   }

   public static Schema createUnion(List types) {
      return new UnionSchema(new LockableArrayList(types));
   }

   public static Schema createUnion(Schema... types) {
      return createUnion((List)(new LockableArrayList(types)));
   }

   public static Schema createFixed(String name, String doc, String space, int size) {
      return new FixedSchema(new Name(name, space), doc, size);
   }

   public Type getType() {
      return this.type;
   }

   public Field getField(String fieldName) {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public List getFields() {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public boolean hasFields() {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public void setFields(List fields) {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public List getEnumSymbols() {
      throw new AvroRuntimeException("Not an enum: " + String.valueOf(this));
   }

   public String getEnumDefault() {
      throw new AvroRuntimeException("Not an enum: " + String.valueOf(this));
   }

   public int getEnumOrdinal(String symbol) {
      throw new AvroRuntimeException("Not an enum: " + String.valueOf(this));
   }

   public boolean hasEnumSymbol(String symbol) {
      throw new AvroRuntimeException("Not an enum: " + String.valueOf(this));
   }

   public String getName() {
      return this.type.name;
   }

   public String getDoc() {
      return null;
   }

   public String getNamespace() {
      throw new AvroRuntimeException("Not a named type: " + String.valueOf(this));
   }

   public String getFullName() {
      return this.getName();
   }

   public void addAlias(String alias) {
      throw new AvroRuntimeException("Not a named type: " + String.valueOf(this));
   }

   public void addAlias(String alias, String space) {
      throw new AvroRuntimeException("Not a named type: " + String.valueOf(this));
   }

   public Set getAliases() {
      throw new AvroRuntimeException("Not a named type: " + String.valueOf(this));
   }

   public boolean isError() {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public Schema getElementType() {
      throw new AvroRuntimeException("Not an array: " + String.valueOf(this));
   }

   public Schema getValueType() {
      throw new AvroRuntimeException("Not a map: " + String.valueOf(this));
   }

   public List getTypes() {
      throw new AvroRuntimeException("Not a union: " + String.valueOf(this));
   }

   public Integer getIndexNamed(String name) {
      throw new AvroRuntimeException("Not a union: " + String.valueOf(this));
   }

   public int getFixedSize() {
      throw new AvroRuntimeException("Not fixed: " + String.valueOf(this));
   }

   public String toString() {
      return this.toString(false);
   }

   /** @deprecated */
   @Deprecated
   public String toString(boolean pretty) {
      return this.toString((Set)(new HashSet()), pretty);
   }

   /** @deprecated */
   @Deprecated
   public String toString(Collection referencedSchemas, boolean pretty) {
      Set<String> knownNames = new HashSet();
      if (referencedSchemas != null) {
         for(Schema s : referencedSchemas) {
            knownNames.add(s.getFullName());
         }
      }

      return this.toString(knownNames, pretty);
   }

   /** @deprecated */
   @Deprecated
   String toString(Set knownNames, boolean pretty) {
      try {
         StringWriter writer = new StringWriter();
         JsonGenerator gen = FACTORY.createGenerator(writer);
         if (pretty) {
            gen.useDefaultPrettyPrinter();
         }

         this.toJson(knownNames, (String)null, gen);
         gen.flush();
         return writer.toString();
      } catch (IOException e) {
         throw new AvroRuntimeException(e);
      }
   }

   /** @deprecated */
   @Deprecated
   void toJson(Set knownNames, String namespace, JsonGenerator gen) throws IOException {
      if (!this.hasProps()) {
         gen.writeString(this.getName());
      } else {
         gen.writeStartObject();
         gen.writeStringField("type", this.getName());
         this.writeProps(gen);
         gen.writeEndObject();
      }

   }

   /** @deprecated */
   @Deprecated
   void fieldsToJson(Set knownNames, String namespace, JsonGenerator gen) throws IOException {
      throw new AvroRuntimeException("Not a record: " + String.valueOf(this));
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Schema)) {
         return false;
      } else {
         Schema that = (Schema)o;
         if (this.type != that.type) {
            return false;
         } else {
            return this.equalCachedHash(that) && this.propsEqual(that);
         }
      }
   }

   public final int hashCode() {
      if (this.hashCode == Integer.MIN_VALUE) {
         this.hashCode = this.computeHash();
      }

      return this.hashCode;
   }

   int computeHash() {
      return this.getType().hashCode() + this.propsHashCode();
   }

   final boolean equalCachedHash(Schema other) {
      return this.hashCode == other.hashCode || this.hashCode == Integer.MIN_VALUE || other.hashCode == Integer.MIN_VALUE;
   }

   public boolean isUnion() {
      return this instanceof UnionSchema;
   }

   public boolean isNullable() {
      if (!this.isUnion()) {
         return this.getType().equals(Schema.Type.NULL);
      } else {
         for(Schema schema : this.getTypes()) {
            if (schema.isNullable()) {
               return true;
            }
         }

         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Schema parse(File file) throws IOException {
      return (new Parser()).parse(file);
   }

   /** @deprecated */
   @Deprecated
   public static Schema parse(InputStream in) throws IOException {
      return (new Parser()).parse(in);
   }

   /** @deprecated */
   @Deprecated
   public static Schema parse(String jsonSchema) {
      return (new Parser()).parse(jsonSchema);
   }

   /** @deprecated */
   @Deprecated
   public static Schema parse(String jsonSchema, boolean validate) {
      NameValidator validator = validate ? NameValidator.UTF_VALIDATOR : NameValidator.NO_VALIDATION;
      return (new Parser(validator)).parse(jsonSchema);
   }

   private static String validateName(String name) {
      NameValidator.Result result = ((NameValidator)VALIDATE_NAMES.get()).validate(name);
      if (!result.isOK()) {
         throw new SchemaParseException(result.getErrors());
      } else {
         return name;
      }
   }

   /** @deprecated */
   @Deprecated
   public static void setNameValidator(final NameValidator validator) {
      VALIDATE_NAMES.set(validator);
   }

   /** @deprecated */
   @Deprecated
   public static NameValidator getNameValidator() {
      return (NameValidator)VALIDATE_NAMES.get();
   }

   private static JsonNode validateDefault(String fieldName, Schema schema, JsonNode defaultValue) {
      if ((Boolean)VALIDATE_DEFAULTS.get() && defaultValue != null && !schema.isValidDefault(defaultValue)) {
         String message = "Invalid default for field " + fieldName + ": " + String.valueOf(defaultValue) + " not a " + String.valueOf(schema);
         throw new AvroTypeException(message);
      } else {
         return defaultValue;
      }
   }

   /** @deprecated */
   @Deprecated
   public static void setValidateDefaults(boolean validateDefaults) {
      VALIDATE_DEFAULTS.set(validateDefaults);
   }

   /** @deprecated */
   @Deprecated
   public static boolean getValidateDefaults() {
      return (Boolean)VALIDATE_DEFAULTS.get();
   }

   public boolean isValidDefault(JsonNode jsonValue) {
      return isValidDefault(this, jsonValue);
   }

   private static boolean isValidDefault(Schema schema, JsonNode defaultValue) {
      if (defaultValue == null) {
         return false;
      } else {
         switch (schema.getType().ordinal()) {
            case 0:
               if (!defaultValue.isObject()) {
                  return false;
               } else {
                  for(Field field : schema.getFields()) {
                     if (!isValidValue(field.schema(), defaultValue.has(field.name()) ? defaultValue.get(field.name()) : field.defaultValue())) {
                        return false;
                     }
                  }

                  return true;
               }
            case 1:
            case 5:
            case 6:
            case 7:
               return defaultValue.isTextual();
            case 2:
               if (!defaultValue.isArray()) {
                  return false;
               } else {
                  for(JsonNode element : defaultValue) {
                     if (!isValidDefault(schema.getElementType(), element)) {
                        return false;
                     }
                  }

                  return true;
               }
            case 3:
               if (!defaultValue.isObject()) {
                  return false;
               } else {
                  for(JsonNode value : defaultValue) {
                     if (!isValidDefault(schema.getValueType(), value)) {
                        return false;
                     }
                  }

                  return true;
               }
            case 4:
               return schema.getTypes().stream().anyMatch((s) -> isValidValue(s, defaultValue));
            case 8:
               return defaultValue.isIntegralNumber() && defaultValue.canConvertToInt();
            case 9:
               return defaultValue.isIntegralNumber() && defaultValue.canConvertToLong();
            case 10:
            case 11:
               return defaultValue.isNumber();
            case 12:
               return defaultValue.isBoolean();
            case 13:
               return defaultValue.isNull();
            default:
               return false;
         }
      }
   }

   private static boolean isValidValue(Schema schema, JsonNode value) {
      if (value == null) {
         return false;
      } else if (schema.isUnion()) {
         for(Schema sub : schema.getTypes()) {
            if (isValidDefault(sub, value)) {
               return true;
            }
         }

         return false;
      } else {
         return isValidDefault(schema, value);
      }
   }

   static Schema parse(JsonNode schema, ParseContext context, String currentNameSpace) {
      if (schema == null) {
         throw new SchemaParseException("Cannot parse <null> schema");
      } else if (schema.isTextual()) {
         return context.find(schema.textValue(), currentNameSpace);
      } else if (schema.isObject()) {
         String type = getRequiredText(schema, "type", "No type");
         boolean isTypeError = "error".equals(type);
         if (PRIMITIVES.containsKey(type)) {
            return parsePrimitive(schema, type);
         } else if (!"record".equals(type) && !isTypeError) {
            if ("enum".equals(type)) {
               return parseEnum(schema, context, currentNameSpace);
            } else if (type.equals("array")) {
               return parseArray(schema, context, currentNameSpace);
            } else if (type.equals("map")) {
               return parseMap(schema, context, currentNameSpace);
            } else {
               return "fixed".equals(type) ? parseFixed(schema, context, currentNameSpace) : context.find(type, currentNameSpace);
            }
         } else {
            return parseRecord(schema, context, currentNameSpace, isTypeError);
         }
      } else if (schema.isArray()) {
         return parseUnion(schema, context, currentNameSpace);
      } else {
         throw new SchemaParseException("Schema not yet supported: " + String.valueOf(schema));
      }
   }

   private static Schema parsePrimitive(JsonNode schema, String type) {
      Schema result = create((Type)PRIMITIVES.get(type));
      parsePropertiesAndLogicalType(schema, result, SCHEMA_RESERVED);
      return result;
   }

   private static Schema parseRecord(JsonNode schema, ParseContext context, String currentNameSpace, boolean isTypeError) {
      Name name = parseName(schema, currentNameSpace);
      String doc = parseDoc(schema);
      Schema result = new RecordSchema(name, doc, isTypeError);
      context.put(result);
      JsonNode fieldsNode = schema.get("fields");
      if (fieldsNode != null && fieldsNode.isArray()) {
         List<Field> fields = new ArrayList();

         for(JsonNode field : fieldsNode) {
            Field f = parseField(field, context, name.space);
            fields.add(f);
            if (f.schema().getLogicalType() == null && getOptionalText(field, "logicalType") != null) {
               LOG.warn("Ignored the {}.{}.logicalType property (\"{}\"). It should probably be nested inside the \"type\" for the field.", new Object[]{name, f.name(), getOptionalText(field, "logicalType")});
            }
         }

         result.setFields(fields);
         parsePropertiesAndLogicalType(schema, result, SCHEMA_RESERVED);
         parseAliases(schema, result);
         return result;
      } else {
         throw new SchemaParseException("Record has no fields: " + String.valueOf(schema));
      }
   }

   private static Field parseField(JsonNode field, ParseContext context, String namespace) {
      String fieldName = getRequiredText(field, "name", "No field name");
      String fieldDoc = parseDoc(field);
      JsonNode fieldTypeNode = field.get("type");
      if (fieldTypeNode == null) {
         throw new SchemaParseException("No field type: " + String.valueOf(field));
      } else {
         Schema fieldSchema = parse(fieldTypeNode, context, namespace);
         Field.Order order = Schema.Field.Order.ASCENDING;
         JsonNode orderNode = field.get("order");
         if (orderNode != null) {
            order = Schema.Field.Order.valueOf(orderNode.textValue().toUpperCase(Locale.ENGLISH));
         }

         JsonNode defaultValue = field.get("default");
         if (defaultValue != null && (Schema.Type.FLOAT.equals(fieldSchema.getType()) || Schema.Type.DOUBLE.equals(fieldSchema.getType())) && defaultValue.isTextual()) {
            defaultValue = new DoubleNode(Double.parseDouble(defaultValue.textValue()));
         }

         Field f = new Field(fieldName, fieldSchema, fieldDoc, defaultValue, true, order);
         parseProperties(field, f, FIELD_RESERVED);
         f.aliases = parseAliases(field);
         return f;
      }
   }

   private static Schema parseEnum(JsonNode schema, ParseContext context, String currentNameSpace) {
      Name name = parseName(schema, currentNameSpace);
      String doc = parseDoc(schema);
      JsonNode symbolsNode = schema.get("symbols");
      if (symbolsNode != null && symbolsNode.isArray()) {
         LockableArrayList<String> symbols = new LockableArrayList(symbolsNode.size());

         for(JsonNode n : symbolsNode) {
            symbols.add(n.textValue());
         }

         JsonNode enumDefault = schema.get("default");
         String defaultSymbol = null;
         if (enumDefault != null) {
            defaultSymbol = enumDefault.textValue();
         }

         Schema result = new EnumSchema(name, doc, symbols, defaultSymbol);
         context.put(result);
         parsePropertiesAndLogicalType(schema, result, ENUM_RESERVED);
         parseAliases(schema, result);
         return result;
      } else {
         throw new SchemaParseException("Enum has no symbols: " + String.valueOf(schema));
      }
   }

   private static Schema parseArray(JsonNode schema, ParseContext context, String currentNameSpace) {
      JsonNode itemsNode = schema.get("items");
      if (itemsNode == null) {
         throw new SchemaParseException("Array has no items type: " + String.valueOf(schema));
      } else {
         Schema result = new ArraySchema(parse(itemsNode, context, currentNameSpace));
         parsePropertiesAndLogicalType(schema, result, SCHEMA_RESERVED);
         return result;
      }
   }

   private static Schema parseMap(JsonNode schema, ParseContext context, String currentNameSpace) {
      JsonNode valuesNode = schema.get("values");
      if (valuesNode == null) {
         throw new SchemaParseException("Map has no values type: " + String.valueOf(schema));
      } else {
         Schema result = new MapSchema(parse(valuesNode, context, currentNameSpace));
         parsePropertiesAndLogicalType(schema, result, SCHEMA_RESERVED);
         return result;
      }
   }

   private static Schema parseFixed(JsonNode schema, ParseContext context, String currentNameSpace) {
      Name name = parseName(schema, currentNameSpace);
      String doc = parseDoc(schema);
      JsonNode sizeNode = schema.get("size");
      if (sizeNode != null && sizeNode.isInt()) {
         Schema result = new FixedSchema(name, doc, sizeNode.intValue());
         context.put(result);
         parsePropertiesAndLogicalType(schema, result, SCHEMA_RESERVED);
         parseAliases(schema, result);
         return result;
      } else {
         throw new SchemaParseException("Invalid or no size: " + String.valueOf(schema));
      }
   }

   private static UnionSchema parseUnion(JsonNode schema, ParseContext context, String currentNameSpace) {
      LockableArrayList<Schema> types = new LockableArrayList(schema.size());

      for(JsonNode typeNode : schema) {
         types.add(parse(typeNode, context, currentNameSpace));
      }

      return new UnionSchema(types);
   }

   private static void parsePropertiesAndLogicalType(JsonNode jsonNode, Schema result, Set propertiesToSkip) {
      parseProperties(jsonNode, result, propertiesToSkip);
      result.logicalType = LogicalTypes.fromSchemaIgnoreInvalid(result);
   }

   private static void parseProperties(JsonNode schema, JsonProperties result, Set propertiesToSkip) {
      schema.fieldNames().forEachRemaining((prop) -> {
         if (!propertiesToSkip.contains(prop)) {
            result.addProp(prop, (Object)schema.get(prop));
         }

      });
   }

   private static Name parseName(JsonNode schema, String currentNameSpace) {
      String space = getOptionalText(schema, "namespace");
      if (space == null) {
         space = currentNameSpace;
      }

      return new Name(getRequiredText(schema, "name", "No name in schema"), space);
   }

   private static String parseDoc(JsonNode schema) {
      return getOptionalText(schema, "doc");
   }

   private static void parseAliases(JsonNode schema, Schema result) {
      Set<String> aliases = parseAliases(schema);
      if (aliases != null) {
         for(String alias : aliases) {
            result.addAlias(alias);
         }
      }

   }

   static Set parseAliases(JsonNode node) {
      JsonNode aliasesNode = node.get("aliases");
      if (aliasesNode == null) {
         return null;
      } else if (!aliasesNode.isArray()) {
         throw new SchemaParseException("aliases not an array: " + String.valueOf(node));
      } else {
         Set<String> aliases = new LinkedHashSet();

         for(JsonNode aliasNode : aliasesNode) {
            if (!aliasNode.isTextual()) {
               throw new SchemaParseException("alias not a string: " + String.valueOf(aliasNode));
            }

            aliases.add(aliasNode.textValue());
         }

         return aliases;
      }
   }

   private static String getRequiredText(JsonNode container, String key, String error) {
      String out = getOptionalText(container, key);
      if (null == out) {
         throw new SchemaParseException(error + ": " + String.valueOf(container));
      } else {
         return out;
      }
   }

   private static String getOptionalText(JsonNode container, String key) {
      JsonNode jsonNode = container.get(key);
      return jsonNode != null ? jsonNode.textValue() : null;
   }

   static JsonNode parseJson(String s) {
      try {
         return (JsonNode)MAPPER.readTree(FACTORY.createParser(s));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static Object parseJsonToObject(String s) {
      return JacksonUtils.toObject(parseJson(s));
   }

   public static Schema applyAliases(Schema writer, Schema reader) {
      if (writer.equals(reader)) {
         return writer;
      } else {
         Map<Schema, Schema> seen = new IdentityHashMap(1);
         Map<Name, Name> aliases = new HashMap(1);
         Map<Name, Map<String, String>> fieldAliases = new HashMap(1);
         getAliases(reader, seen, aliases, fieldAliases);
         if (aliases.isEmpty() && fieldAliases.isEmpty()) {
            return writer;
         } else {
            seen.clear();
            return applyAliases(writer, seen, aliases, fieldAliases);
         }
      }
   }

   private static Schema applyAliases(Schema s, Map seen, Map aliases, Map fieldAliases) {
      Name name = s instanceof NamedSchema ? ((NamedSchema)s).name : null;
      Schema result = s;
      switch (s.getType().ordinal()) {
         case 0:
            if (seen.containsKey(s)) {
               return (Schema)seen.get(s);
            }

            if (aliases.containsKey(name)) {
               name = (Name)aliases.get(name);
            }

            result = createRecord(name.full, s.getDoc(), (String)null, s.isError());
            seen.put(s, result);
            List<Field> newFields = new ArrayList();

            for(Field f : s.getFields()) {
               Schema fSchema = applyAliases(f.schema, seen, aliases, fieldAliases);
               String fName = getFieldAlias(name, f.name, fieldAliases);
               Field newF = new Field(fName, fSchema, f.doc, f.defaultValue, true, f.order);
               newF.putAll(f);
               newFields.add(newF);
            }

            result.setFields(newFields);
            break;
         case 1:
            if (aliases.containsKey(name)) {
               result = createEnum(((Name)aliases.get(name)).full, s.getDoc(), (String)null, s.getEnumSymbols(), s.getEnumDefault());
            }
            break;
         case 2:
            Schema e = applyAliases(s.getElementType(), seen, aliases, fieldAliases);
            if (!e.equals(s.getElementType())) {
               result = createArray(e);
            }
            break;
         case 3:
            Schema v = applyAliases(s.getValueType(), seen, aliases, fieldAliases);
            if (!v.equals(s.getValueType())) {
               result = createMap(v);
            }
            break;
         case 4:
            List<Schema> types = new ArrayList();

            for(Schema branch : s.getTypes()) {
               types.add(applyAliases(branch, seen, aliases, fieldAliases));
            }

            result = createUnion(types);
            break;
         case 5:
            if (aliases.containsKey(name)) {
               result = createFixed(((Name)aliases.get(name)).full, s.getDoc(), (String)null, s.getFixedSize());
            }
      }

      if (!result.equals(s)) {
         result.putAll(s);
      }

      return result;
   }

   private static void getAliases(Schema schema, Map seen, Map aliases, Map fieldAliases) {
      if (schema instanceof NamedSchema) {
         NamedSchema namedSchema = (NamedSchema)schema;
         if (namedSchema.aliases != null) {
            for(Name alias : namedSchema.aliases) {
               aliases.put(alias, namedSchema.name);
            }
         }
      }

      switch (schema.getType().ordinal()) {
         case 0:
            if (seen.containsKey(schema)) {
               return;
            }

            seen.put(schema, schema);
            RecordSchema record = (RecordSchema)schema;

            for(Field field : schema.getFields()) {
               if (field.aliases != null) {
                  for(String fieldAlias : field.aliases) {
                     Map<String, String> recordAliases = (Map)fieldAliases.computeIfAbsent(record.name, (k) -> new HashMap());
                     recordAliases.put(fieldAlias, field.name);
                  }
               }

               getAliases(field.schema, seen, aliases, fieldAliases);
            }

            if (record.aliases != null && fieldAliases.containsKey(record.name)) {
               for(Name recordAlias : record.aliases) {
                  fieldAliases.put(recordAlias, (Map)fieldAliases.get(record.name));
               }
            }
         case 1:
         default:
            break;
         case 2:
            getAliases(schema.getElementType(), seen, aliases, fieldAliases);
            break;
         case 3:
            getAliases(schema.getValueType(), seen, aliases, fieldAliases);
            break;
         case 4:
            for(Schema s : schema.getTypes()) {
               getAliases(s, seen, aliases, fieldAliases);
            }
      }

   }

   private static String getFieldAlias(Name record, String field, Map fieldAliases) {
      Map<String, String> recordAliases = (Map)fieldAliases.get(record);
      if (recordAliases == null) {
         return field;
      } else {
         String alias = (String)recordAliases.get(field);
         return alias == null ? field : alias;
      }
   }

   static {
      MAPPER = new ObjectMapper(FACTORY);
      FACTORY.enable(Feature.ALLOW_COMMENTS);
      FACTORY.setCodec(MAPPER);
      SCHEMA_RESERVED = new HashSet(Arrays.asList("doc", "fields", "items", "name", "namespace", "size", "symbols", "values", "type", "aliases"));
      ENUM_RESERVED = new HashSet(SCHEMA_RESERVED);
      ENUM_RESERVED.add("default");
      FIELD_RESERVED = Collections.unmodifiableSet(new HashSet(Arrays.asList("default", "doc", "name", "order", "type", "aliases")));
      SEEN_EQUALS = ThreadLocalWithInitial.of(HashSet::new);
      SEEN_HASHCODE = ThreadLocalWithInitial.of(IdentityHashMap::new);
      PRIMITIVES = new HashMap();
      PRIMITIVES.put("string", Schema.Type.STRING);
      PRIMITIVES.put("bytes", Schema.Type.BYTES);
      PRIMITIVES.put("int", Schema.Type.INT);
      PRIMITIVES.put("long", Schema.Type.LONG);
      PRIMITIVES.put("float", Schema.Type.FLOAT);
      PRIMITIVES.put("double", Schema.Type.DOUBLE);
      PRIMITIVES.put("boolean", Schema.Type.BOOLEAN);
      PRIMITIVES.put("null", Schema.Type.NULL);
      VALIDATE_NAMES = ThreadLocalWithInitial.of(() -> NameValidator.UTF_VALIDATOR);
      VALIDATE_DEFAULTS = ThreadLocalWithInitial.of(() -> true);
   }

   private static final class SerializableSchema implements Serializable {
      private static final long serialVersionUID = 1L;
      private String schemaString;

      private Object readResolve() {
         return (new Parser()).parse(this.schemaString);
      }
   }

   public static enum Type {
      RECORD,
      ENUM,
      ARRAY,
      MAP,
      UNION,
      FIXED,
      STRING,
      BYTES,
      INT,
      LONG,
      FLOAT,
      DOUBLE,
      BOOLEAN,
      NULL;

      private final String name;

      private Type() {
         this.name = this.name().toLowerCase(Locale.ENGLISH);
      }

      public String getName() {
         return this.name;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT, LONG, FLOAT, DOUBLE, BOOLEAN, NULL};
      }
   }

   public static class Field extends JsonProperties {
      public static final Object NULL_DEFAULT_VALUE;
      private final String name;
      private int position;
      private final Schema schema;
      private final String doc;
      private final JsonNode defaultValue;
      private final Order order;
      private Set aliases;

      Field(String name, Schema schema, String doc, JsonNode defaultValue, boolean validateDefault, Order order) {
         super(Schema.FIELD_RESERVED);
         this.position = -1;
         this.name = Schema.validateName(name);
         this.schema = (Schema)Objects.requireNonNull(schema, "schema is required and cannot be null");
         this.doc = doc;
         this.defaultValue = validateDefault ? Schema.validateDefault(name, schema, defaultValue) : defaultValue;
         this.order = (Order)Objects.requireNonNull(order, "Order cannot be null");
      }

      public Field(Field field, Schema schema) {
         this(field.name, schema, field.doc, field.defaultValue, true, field.order);
         this.putAll(field);
         if (field.aliases != null) {
            this.aliases = new LinkedHashSet(field.aliases);
         }

      }

      public Field(String name, Schema schema) {
         this(name, schema, (String)null, (JsonNode)null, true, Schema.Field.Order.ASCENDING);
      }

      public Field(String name, Schema schema, String doc) {
         this(name, schema, doc, (JsonNode)null, true, Schema.Field.Order.ASCENDING);
      }

      public Field(String name, Schema schema, String doc, Object defaultValue) {
         this(name, schema, doc, (JsonNode)(defaultValue == NULL_DEFAULT_VALUE ? NullNode.getInstance() : JacksonUtils.toJsonNode(defaultValue)), true, Schema.Field.Order.ASCENDING);
      }

      public Field(String name, Schema schema, String doc, Object defaultValue, Order order) {
         this(name, schema, doc, (JsonNode)(defaultValue == NULL_DEFAULT_VALUE ? NullNode.getInstance() : JacksonUtils.toJsonNode(defaultValue)), true, (Order)Objects.requireNonNull(order));
      }

      public String name() {
         return this.name;
      }

      public int pos() {
         return this.position;
      }

      public Schema schema() {
         return this.schema;
      }

      public String doc() {
         return this.doc;
      }

      public boolean hasDefaultValue() {
         return this.defaultValue != null;
      }

      JsonNode defaultValue() {
         return this.defaultValue;
      }

      public Object defaultVal() {
         return JacksonUtils.toObject(this.defaultValue, this.schema);
      }

      public Order order() {
         return this.order;
      }

      public void addAlias(String alias) {
         if (this.aliases == null) {
            this.aliases = new LinkedHashSet();
         }

         this.aliases.add(alias);
      }

      public Set aliases() {
         return this.aliases == null ? Collections.emptySet() : Collections.unmodifiableSet(this.aliases);
      }

      public boolean equals(Object other) {
         if (other == this) {
            return true;
         } else if (!(other instanceof Field)) {
            return false;
         } else {
            Field that = (Field)other;
            return this.name.equals(that.name) && this.schema.equals(that.schema) && this.defaultValueEquals(that.defaultValue) && this.order == that.order && this.propsEqual(that);
         }
      }

      public int hashCode() {
         return this.name.hashCode() + this.schema.computeHash();
      }

      private boolean defaultValueEquals(JsonNode thatDefaultValue) {
         if (this.defaultValue == null) {
            return thatDefaultValue == null;
         } else if (thatDefaultValue == null) {
            return false;
         } else {
            return Double.isNaN(this.defaultValue.doubleValue()) ? Double.isNaN(thatDefaultValue.doubleValue()) : this.defaultValue.equals(thatDefaultValue);
         }
      }

      public String toString() {
         String var10000 = this.name;
         return var10000 + " type:" + String.valueOf(this.schema.type) + " pos:" + this.position;
      }

      static {
         Accessor.setAccessor(new Accessor.FieldAccessor() {
            protected JsonNode defaultValue(Field field) {
               return field.defaultValue();
            }

            protected Field createField(String name, Schema schema, String doc, JsonNode defaultValue) {
               return new Field(name, schema, doc, defaultValue, true, Schema.Field.Order.ASCENDING);
            }

            protected Field createField(String name, Schema schema, String doc, JsonNode defaultValue, boolean validate, Order order) {
               return new Field(name, schema, doc, defaultValue, validate, order);
            }
         });
         NULL_DEFAULT_VALUE = new Object();
      }

      public static enum Order {
         ASCENDING,
         DESCENDING,
         IGNORE;

         private final String name;

         private Order() {
            this.name = this.name().toLowerCase(Locale.ENGLISH);
         }

         // $FF: synthetic method
         private static Order[] $values() {
            return new Order[]{ASCENDING, DESCENDING, IGNORE};
         }
      }
   }

   static class Name {
      private final String name;
      private final String space;
      private final String full;

      public Name(String name, String space) {
         if (name == null) {
            this.name = this.space = this.full = null;
         } else {
            int lastDot = name.lastIndexOf(46);
            if (lastDot < 0) {
               this.name = Schema.validateName(name);
            } else {
               space = name.substring(0, lastDot);
               this.name = Schema.validateName(name.substring(lastDot + 1));
            }

            if ("".equals(space)) {
               space = null;
            }

            this.space = space;
            this.full = this.space == null ? this.name : this.space + "." + this.name;
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Name)) {
            return false;
         } else {
            Name that = (Name)o;
            return Objects.equals(this.full, that.full);
         }
      }

      public int hashCode() {
         return this.full == null ? 0 : this.full.hashCode();
      }

      public String toString() {
         return this.full;
      }

      public void writeName(String currentNamespace, JsonGenerator gen) throws IOException {
         if (this.name != null) {
            gen.writeStringField("name", this.name);
         }

         if (this.space != null) {
            if (!this.space.equals(currentNamespace)) {
               gen.writeStringField("namespace", this.space);
            }
         } else if (currentNamespace != null) {
            gen.writeStringField("namespace", "");
         }

      }

      public String getQualified(String defaultSpace) {
         return this.shouldWriteFull(defaultSpace) ? this.full : this.name;
      }

      private boolean shouldWriteFull(String defaultSpace) {
         if (this.space != null && this.space.equals(defaultSpace)) {
            for(Type schemaType : Schema.Type.values()) {
               if (schemaType.name.equals(this.name)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }
   }

   private abstract static class NamedSchema extends Schema {
      final Name name;
      final String doc;
      Set aliases;

      public NamedSchema(Type type, Name name, String doc) {
         super(type);
         this.name = name;
         this.doc = doc;
         if (PRIMITIVES.containsKey(name.full)) {
            throw new AvroTypeException("Schemas may not be named after primitives: " + name.full);
         }
      }

      public String getName() {
         return this.name.name;
      }

      public String getDoc() {
         return this.doc;
      }

      public String getNamespace() {
         return this.name.space;
      }

      public String getFullName() {
         return this.name.full;
      }

      public void addAlias(String alias) {
         this.addAlias(alias, (String)null);
      }

      public void addAlias(String name, String space) {
         if (this.aliases == null) {
            this.aliases = new LinkedHashSet();
         }

         if (space == null) {
            space = this.name.space;
         }

         this.aliases.add(new Name(name, space));
      }

      public Set getAliases() {
         Set<String> result = new LinkedHashSet();
         if (this.aliases != null) {
            for(Name alias : this.aliases) {
               if (alias.space == null && this.name.space != null) {
                  result.add("." + alias.name);
               } else {
                  result.add(alias.full);
               }
            }
         }

         return result;
      }

      public boolean writeNameRef(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         if (this.name.name != null && !knownNames.add(this.name.full)) {
            gen.writeString(this.name.getQualified(currentNamespace));
            return true;
         } else {
            return false;
         }
      }

      public void writeName(String currentNamespace, JsonGenerator gen) throws IOException {
         this.name.writeName(currentNamespace, gen);
      }

      public boolean equalNames(NamedSchema that) {
         return this.name.equals(that.name);
      }

      int computeHash() {
         return super.computeHash() + this.name.hashCode();
      }

      public void aliasesToJson(JsonGenerator gen) throws IOException {
         if (this.aliases != null && !this.aliases.isEmpty()) {
            gen.writeFieldName("aliases");
            gen.writeStartArray();

            for(Name alias : this.aliases) {
               gen.writeString(alias.getQualified(this.name.space));
            }

            gen.writeEndArray();
         }
      }
   }

   public static class SeenPair {
      private final Object s1;
      private final Object s2;

      public SeenPair(Object s1, Object s2) {
         this.s1 = s1;
         this.s2 = s2;
      }

      public boolean equals(Object o) {
         if (!(o instanceof SeenPair)) {
            return false;
         } else {
            return this.s1 == ((SeenPair)o).s1 && this.s2 == ((SeenPair)o).s2;
         }
      }

      public int hashCode() {
         return System.identityHashCode(this.s1) + System.identityHashCode(this.s2);
      }
   }

   private static class RecordSchema extends NamedSchema {
      private List fields;
      private Map fieldMap;
      private final boolean isError;

      public RecordSchema(Name name, String doc, boolean isError) {
         super(Schema.Type.RECORD, name, doc);
         this.isError = isError;
      }

      public RecordSchema(Name name, String doc, boolean isError, List fields) {
         super(Schema.Type.RECORD, name, doc);
         this.isError = isError;
         this.setFields(fields);
      }

      public boolean isError() {
         return this.isError;
      }

      public Field getField(String fieldName) {
         if (this.fieldMap == null) {
            throw new AvroRuntimeException("Schema fields not set yet");
         } else {
            return (Field)this.fieldMap.get(fieldName);
         }
      }

      public List getFields() {
         if (this.fields == null) {
            throw new AvroRuntimeException("Schema fields not set yet");
         } else {
            return this.fields;
         }
      }

      public boolean hasFields() {
         return this.fields != null;
      }

      public void setFields(List fields) {
         if (this.fields != null) {
            throw new AvroRuntimeException("Fields are already set");
         } else {
            int i = 0;
            this.fieldMap = new HashMap(Math.multiplyExact(2, fields.size()));
            LockableArrayList<Field> ff = new LockableArrayList(fields.size());

            for(Field f : fields) {
               if (f.position != -1) {
                  throw new AvroRuntimeException("Field already used: " + String.valueOf(f));
               }

               f.position = i++;
               Field existingField = (Field)this.fieldMap.put(f.name(), f);
               if (existingField != null) {
                  throw new AvroRuntimeException(String.format("Duplicate field %s in record %s: %s and %s.", f.name(), this.name, f, existingField));
               }

               ff.add(f);
            }

            this.fields = ff.lock();
            this.hashCode = Integer.MIN_VALUE;
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof RecordSchema)) {
            return false;
         } else {
            RecordSchema that = (RecordSchema)o;
            if (!this.equalCachedHash(that)) {
               return false;
            } else if (!this.equalNames(that)) {
               return false;
            } else if (!this.propsEqual(that)) {
               return false;
            } else {
               Set<SeenPair> seen = (Set)Schema.SEEN_EQUALS.get();
               SeenPair here = new SeenPair(this, o);
               if (seen.contains(here)) {
                  return true;
               } else {
                  boolean first = seen.isEmpty();

                  boolean var6;
                  try {
                     seen.add(here);
                     var6 = Objects.equals(this.fields, that.fields);
                  } finally {
                     if (first) {
                        seen.clear();
                     }

                  }

                  return var6;
               }
            }
         }
      }

      int computeHash() {
         Map<Schema, Schema> seen = (Map)Schema.SEEN_HASHCODE.get();
         if (seen.containsKey(this)) {
            return 0;
         } else {
            boolean first = seen.isEmpty();

            int var3;
            try {
               seen.put(this, this);
               var3 = super.computeHash() + this.fields.hashCode();
            } finally {
               if (first) {
                  seen.clear();
               }

            }

            return var3;
         }
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         if (!this.writeNameRef(knownNames, currentNamespace, gen)) {
            gen.writeStartObject();
            gen.writeStringField("type", this.isError ? "error" : "record");
            this.writeName(currentNamespace, gen);
            if (this.getDoc() != null) {
               gen.writeStringField("doc", this.getDoc());
            }

            if (this.fields != null) {
               gen.writeFieldName("fields");
               this.fieldsToJson(knownNames, this.name.space, gen);
            }

            this.writeProps(gen);
            this.aliasesToJson(gen);
            gen.writeEndObject();
         }
      }

      /** @deprecated */
      @Deprecated
      void fieldsToJson(Set knownNames, String namespace, JsonGenerator gen) throws IOException {
         gen.writeStartArray();

         for(Field f : this.fields) {
            gen.writeStartObject();
            gen.writeStringField("name", f.name());
            gen.writeFieldName("type");
            f.schema().toJson(knownNames, namespace, gen);
            if (f.doc() != null) {
               gen.writeStringField("doc", f.doc());
            }

            if (f.hasDefaultValue()) {
               gen.writeFieldName("default");
               gen.writeTree(f.defaultValue());
            }

            if (f.order() != Schema.Field.Order.ASCENDING) {
               gen.writeStringField("order", f.order().name);
            }

            if (f.aliases != null && !f.aliases.isEmpty()) {
               gen.writeFieldName("aliases");
               gen.writeStartArray();

               for(String alias : f.aliases) {
                  gen.writeString(alias);
               }

               gen.writeEndArray();
            }

            f.writeProps(gen);
            gen.writeEndObject();
         }

         gen.writeEndArray();
      }
   }

   private static class EnumSchema extends NamedSchema {
      private final List symbols;
      private final Map ordinals;
      private final String enumDefault;

      public EnumSchema(Name name, String doc, LockableArrayList symbols, String enumDefault) {
         super(Schema.Type.ENUM, name, doc);
         this.symbols = symbols.lock();
         this.ordinals = new HashMap(Math.multiplyExact(2, symbols.size()));
         this.enumDefault = enumDefault;
         int i = 0;

         for(String symbol : symbols) {
            if (this.ordinals.put(Schema.validateName(symbol), i++) != null) {
               throw new SchemaParseException("Duplicate enum symbol: " + symbol);
            }
         }

         if (enumDefault != null && !symbols.contains(enumDefault)) {
            throw new SchemaParseException("The Enum Default: " + enumDefault + " is not in the enum symbol set: " + String.valueOf(symbols));
         }
      }

      public List getEnumSymbols() {
         return this.symbols;
      }

      public boolean hasEnumSymbol(String symbol) {
         return this.ordinals.containsKey(symbol);
      }

      public int getEnumOrdinal(String symbol) {
         Integer ordinal = (Integer)this.ordinals.get(symbol);
         if (ordinal == null) {
            throw new TracingAvroTypeException(new AvroTypeException("enum value '" + symbol + "' is not in the enum symbol set: " + String.valueOf(this.symbols)));
         } else {
            return ordinal;
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof EnumSchema)) {
            return false;
         } else {
            EnumSchema that = (EnumSchema)o;
            return this.equalCachedHash(that) && this.equalNames(that) && this.symbols.equals(that.symbols) && this.propsEqual(that);
         }
      }

      public String getEnumDefault() {
         return this.enumDefault;
      }

      int computeHash() {
         return super.computeHash() + this.symbols.hashCode();
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         if (!this.writeNameRef(knownNames, currentNamespace, gen)) {
            gen.writeStartObject();
            gen.writeStringField("type", "enum");
            this.writeName(currentNamespace, gen);
            if (this.getDoc() != null) {
               gen.writeStringField("doc", this.getDoc());
            }

            gen.writeArrayFieldStart("symbols");

            for(String symbol : this.symbols) {
               gen.writeString(symbol);
            }

            gen.writeEndArray();
            if (this.getEnumDefault() != null) {
               gen.writeStringField("default", this.getEnumDefault());
            }

            this.writeProps(gen);
            this.aliasesToJson(gen);
            gen.writeEndObject();
         }
      }
   }

   private static class ArraySchema extends Schema {
      private final Schema elementType;

      public ArraySchema(Schema elementType) {
         super(Schema.Type.ARRAY);
         this.elementType = elementType;
      }

      public Schema getElementType() {
         return this.elementType;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof ArraySchema)) {
            return false;
         } else {
            ArraySchema that = (ArraySchema)o;
            return this.equalCachedHash(that) && this.elementType.equals(that.elementType) && this.propsEqual(that);
         }
      }

      int computeHash() {
         return super.computeHash() + this.elementType.computeHash();
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String namespace, JsonGenerator gen) throws IOException {
         gen.writeStartObject();
         gen.writeStringField("type", "array");
         gen.writeFieldName("items");
         this.elementType.toJson(knownNames, namespace, gen);
         this.writeProps(gen);
         gen.writeEndObject();
      }
   }

   private static class MapSchema extends Schema {
      private final Schema valueType;

      public MapSchema(Schema valueType) {
         super(Schema.Type.MAP);
         this.valueType = valueType;
      }

      public Schema getValueType() {
         return this.valueType;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof MapSchema)) {
            return false;
         } else {
            MapSchema that = (MapSchema)o;
            return this.equalCachedHash(that) && this.valueType.equals(that.valueType) && this.propsEqual(that);
         }
      }

      int computeHash() {
         return super.computeHash() + this.valueType.computeHash();
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         gen.writeStartObject();
         gen.writeStringField("type", "map");
         gen.writeFieldName("values");
         this.valueType.toJson(knownNames, currentNamespace, gen);
         this.writeProps(gen);
         gen.writeEndObject();
      }
   }

   private static class UnionSchema extends Schema {
      private final List types;
      private final Map indexByName;

      public UnionSchema(LockableArrayList types) {
         super(Schema.Type.UNION);
         this.indexByName = new HashMap(Math.multiplyExact(2, types.size()));
         this.types = types.lock();
         int index = 0;

         for(Schema type : types) {
            if (type.getType() == Schema.Type.UNION) {
               throw new AvroRuntimeException("Nested union: " + String.valueOf(this));
            }

            String name = type.getFullName();
            if (name == null) {
               throw new AvroRuntimeException("Nameless in union:" + String.valueOf(this));
            }

            if (this.indexByName.put(name, index++) != null) {
               throw new AvroRuntimeException("Duplicate in union:" + name);
            }
         }

      }

      public boolean isValidDefault(JsonNode jsonValue) {
         return this.types.stream().anyMatch((s) -> s.isValidDefault(jsonValue));
      }

      public List getTypes() {
         return this.types;
      }

      public Integer getIndexNamed(String name) {
         return (Integer)this.indexByName.get(name);
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof UnionSchema)) {
            return false;
         } else {
            UnionSchema that = (UnionSchema)o;
            return this.equalCachedHash(that) && this.types.equals(that.types) && this.propsEqual(that);
         }
      }

      int computeHash() {
         int hash = super.computeHash();

         for(Schema type : this.types) {
            hash += type.computeHash();
         }

         return hash;
      }

      public void addProp(String name, String value) {
         throw new AvroRuntimeException("Can't set properties on a union: " + String.valueOf(this));
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         gen.writeStartArray();

         for(Schema type : this.types) {
            type.toJson(knownNames, currentNamespace, gen);
         }

         gen.writeEndArray();
      }

      public String getName() {
         String var10000 = super.getName();
         return var10000 + (String)this.getTypes().stream().map(Schema::getName).collect(Collectors.joining(", ", "[", "]"));
      }
   }

   private static class FixedSchema extends NamedSchema {
      private final int size;

      public FixedSchema(Name name, String doc, int size) {
         super(Schema.Type.FIXED, name, doc);
         SystemLimitException.checkMaxBytesLength((long)size);
         this.size = size;
      }

      public int getFixedSize() {
         return this.size;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof FixedSchema)) {
            return false;
         } else {
            FixedSchema that = (FixedSchema)o;
            return this.equalCachedHash(that) && this.equalNames(that) && this.size == that.size && this.propsEqual(that);
         }
      }

      int computeHash() {
         return super.computeHash() + this.size;
      }

      /** @deprecated */
      @Deprecated
      void toJson(Set knownNames, String currentNamespace, JsonGenerator gen) throws IOException {
         if (!this.writeNameRef(knownNames, currentNamespace, gen)) {
            gen.writeStartObject();
            gen.writeStringField("type", "fixed");
            this.writeName(currentNamespace, gen);
            if (this.getDoc() != null) {
               gen.writeStringField("doc", this.getDoc());
            }

            gen.writeNumberField("size", this.size);
            this.writeProps(gen);
            this.aliasesToJson(gen);
            gen.writeEndObject();
         }
      }
   }

   private static class StringSchema extends Schema {
      public StringSchema() {
         super(Schema.Type.STRING);
      }
   }

   private static class BytesSchema extends Schema {
      public BytesSchema() {
         super(Schema.Type.BYTES);
      }
   }

   private static class IntSchema extends Schema {
      public IntSchema() {
         super(Schema.Type.INT);
      }
   }

   private static class LongSchema extends Schema {
      public LongSchema() {
         super(Schema.Type.LONG);
      }
   }

   private static class FloatSchema extends Schema {
      public FloatSchema() {
         super(Schema.Type.FLOAT);
      }
   }

   private static class DoubleSchema extends Schema {
      public DoubleSchema() {
         super(Schema.Type.DOUBLE);
      }
   }

   private static class BooleanSchema extends Schema {
      public BooleanSchema() {
         super(Schema.Type.BOOLEAN);
      }
   }

   private static class NullSchema extends Schema {
      public NullSchema() {
         super(Schema.Type.NULL);
      }
   }

   public static class Parser {
      final ParseContext context;
      private final NameValidator validate;
      private boolean validateDefaults;

      public Parser() {
         this(NameValidator.UTF_VALIDATOR);
      }

      public Parser(final NameValidator validate) {
         this.validateDefaults = true;
         this.validate = validate != null ? validate : NameValidator.NO_VALIDATION;
         this.context = new ParseContext(this.validate);
      }

      public Parser(final ParseContext context) {
         this.validateDefaults = true;
         this.validate = context.nameValidator;
         this.context = context;
      }

      /** @deprecated */
      @Deprecated
      public Parser addTypes(Map types) {
         return this.addTypes((Iterable)types.values());
      }

      public Parser addTypes(Iterable types) {
         for(Schema s : types) {
            this.context.put(s);
         }

         return this;
      }

      public Map getTypes() {
         return this.context.typesByName();
      }

      public Parser setValidateDefaults(boolean validateDefaults) {
         this.validateDefaults = validateDefaults;
         return this;
      }

      public boolean getValidateDefaults() {
         return this.validateDefaults;
      }

      public Schema parse(File file) throws IOException {
         return this.parse(Schema.FACTORY.createParser(file), false, true);
      }

      public Schema parse(InputStream in) throws IOException {
         JsonParser parser = Schema.FACTORY.createParser(in).disable(Feature.AUTO_CLOSE_SOURCE);
         return this.parse(parser, true, true);
      }

      public Schema parse(String s, String... more) {
         StringBuilder b = new StringBuilder(s);

         for(String part : more) {
            b.append(part);
         }

         return this.parse(b.toString());
      }

      public Schema parse(String s) {
         try {
            return this.parse(Schema.FACTORY.createParser(s), false, true);
         } catch (IOException e) {
            throw new SchemaParseException(e);
         }
      }

      public Schema parseInternal(String s) {
         try {
            return this.parse(Schema.FACTORY.createParser(s), false, false);
         } catch (IOException e) {
            throw new SchemaParseException(e);
         }
      }

      private Schema parse(JsonParser parser, boolean allowDanglingContent, boolean resolveSchema) throws IOException {
         NameValidator saved = (NameValidator)Schema.VALIDATE_NAMES.get();
         boolean savedValidateDefaults = (Boolean)Schema.VALIDATE_DEFAULTS.get();

         Schema var17;
         try {
            Schema.VALIDATE_NAMES.set(this.validate);
            Schema.VALIDATE_DEFAULTS.set(this.validateDefaults);
            JsonNode jsonNode = (JsonNode)Schema.MAPPER.readTree(parser);
            Schema schema = Schema.parse(jsonNode, this.context, (String)null);
            if (resolveSchema) {
               this.context.commit();
               schema = this.context.resolve(schema);
            }

            if (!allowDanglingContent) {
               StringWriter danglingWriter = new StringWriter();
               int numCharsReleased = parser.releaseBuffered(danglingWriter);
               String dangling;
               if (numCharsReleased == -1) {
                  ByteArrayOutputStream danglingOutputStream = new ByteArrayOutputStream();
                  parser.releaseBuffered(danglingOutputStream);
                  dangling = (new String(danglingOutputStream.toByteArray(), StandardCharsets.UTF_8)).trim();
               } else {
                  dangling = danglingWriter.toString().trim();
               }

               if (!dangling.isEmpty()) {
                  throw new SchemaParseException("dangling content after end of schema: " + dangling);
               }
            }

            var17 = schema;
         } catch (JsonParseException e) {
            throw new SchemaParseException(e);
         } finally {
            parser.close();
            Schema.VALIDATE_NAMES.set(saved);
            Schema.VALIDATE_DEFAULTS.set(savedValidateDefaults);
         }

         return var17;
      }
   }

   static class Names extends LinkedHashMap {
      private static final long serialVersionUID = 1L;
      private String space;

      public Names() {
      }

      public Names(String space) {
         this.space = space;
      }

      public String space() {
         return this.space;
      }

      public void space(String space) {
         this.space = space;
      }

      public Schema get(String o) {
         Type primitive = (Type)Schema.PRIMITIVES.get(o);
         if (primitive != null) {
            return Schema.create(primitive);
         } else {
            Name name = new Name(o, this.space);
            if (!this.containsKey(name)) {
               name = new Name(o, "");
            }

            return (Schema)super.get(name);
         }
      }

      public boolean contains(Schema schema) {
         return this.get(((NamedSchema)schema).name) != null;
      }

      public void add(Schema schema) {
         this.put(((NamedSchema)schema).name, schema);
      }

      public Schema put(Name name, Schema schema) {
         if (this.containsKey(name)) {
            Schema other = (Schema)super.get(name);
            if (!Objects.equals(other, schema)) {
               throw new SchemaParseException("Can't redefine: " + String.valueOf(name));
            } else {
               return schema;
            }
         } else {
            return (Schema)super.put(name, schema);
         }
      }
   }

   static class LockableArrayList extends ArrayList {
      private static final long serialVersionUID = 1L;
      private boolean locked = false;

      public LockableArrayList() {
      }

      public LockableArrayList(int size) {
         super(size);
      }

      public LockableArrayList(List types) {
         super(types);
      }

      @SafeVarargs
      public LockableArrayList(Object... types) {
         super(types.length);
         Collections.addAll(this, types);
      }

      public List lock() {
         this.locked = true;
         return this;
      }

      private void ensureUnlocked() {
         if (this.locked) {
            throw new IllegalStateException();
         }
      }

      public boolean add(Object e) {
         this.ensureUnlocked();
         return super.add(e);
      }

      public boolean remove(Object o) {
         this.ensureUnlocked();
         return super.remove(o);
      }

      public Object remove(int index) {
         this.ensureUnlocked();
         return super.remove(index);
      }

      public boolean addAll(Collection c) {
         this.ensureUnlocked();
         return super.addAll(c);
      }

      public boolean addAll(int index, Collection c) {
         this.ensureUnlocked();
         return super.addAll(index, c);
      }

      public boolean removeAll(Collection c) {
         this.ensureUnlocked();
         return super.removeAll(c);
      }

      public boolean retainAll(Collection c) {
         this.ensureUnlocked();
         return super.retainAll(c);
      }

      public void clear() {
         this.ensureUnlocked();
         super.clear();
      }
   }
}
