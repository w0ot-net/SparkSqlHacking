package org.apache.avro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.avro.util.SchemaResolver;
import org.apache.avro.util.Schemas;

public class ParseContext {
   private static final Map PRIMITIVES = new HashMap();
   private static final Set NAMED_SCHEMA_TYPES;
   private final Map oldSchemas;
   private final Map newSchemas;
   final NameValidator nameValidator;
   private SchemaResolver.ResolvingVisitor resolvingVisitor;

   public ParseContext() {
      this(NameValidator.UTF_VALIDATOR);
   }

   public ParseContext(NameValidator nameValidator) {
      this((NameValidator)Objects.requireNonNull(nameValidator), new LinkedHashMap(), new LinkedHashMap());
   }

   private ParseContext(NameValidator nameValidator, Map oldSchemas, Map newSchemas) {
      this.nameValidator = nameValidator;
      this.oldSchemas = oldSchemas;
      this.newSchemas = newSchemas;
      this.resolvingVisitor = null;
   }

   public boolean contains(String name) {
      return PRIMITIVES.containsKey(name) || this.oldSchemas.containsKey(name) || this.newSchemas.containsKey(name);
   }

   public Schema find(String name, String namespace) {
      Schema.Type type = (Schema.Type)PRIMITIVES.get(name);
      if (type != null) {
         return Schema.create(type);
      } else {
         String fullName = this.fullName(name, namespace);
         Schema schema = this.getNamedSchema(fullName);
         if (schema == null) {
            schema = this.getNamedSchema(name);
         }

         return schema != null ? schema : SchemaResolver.unresolvedSchema(fullName);
      }
   }

   private String fullName(String name, String namespace) {
      return namespace != null && name.lastIndexOf(46) < 0 ? namespace + "." + name : name;
   }

   public Schema getNamedSchema(String fullName) {
      Schema schema = (Schema)this.oldSchemas.get(fullName);
      if (schema == null) {
         schema = (Schema)this.newSchemas.get(fullName);
      }

      return schema;
   }

   public void put(Schema schema) {
      if (!NAMED_SCHEMA_TYPES.contains(schema.getType())) {
         throw new AvroTypeException("You can only put a named schema into the context");
      } else {
         String fullName = this.requireValidFullName(schema.getFullName());
         Schema alreadyKnownSchema = (Schema)this.oldSchemas.get(fullName);
         if (alreadyKnownSchema != null) {
            if (!schema.equals(alreadyKnownSchema)) {
               throw new SchemaParseException("Can't redefine: " + fullName);
            }
         } else {
            this.resolvingVisitor = null;
            Schema previouslyAddedSchema = (Schema)this.newSchemas.putIfAbsent(fullName, schema);
            if (previouslyAddedSchema != null && !previouslyAddedSchema.equals(schema)) {
               throw new SchemaParseException("Can't redefine: " + fullName);
            }
         }

      }
   }

   private String requireValidFullName(String fullName) {
      String[] names = fullName.split("\\.");

      for(int i = 0; i < names.length - 1; ++i) {
         this.validateName(names[i], "Namespace part");
      }

      this.validateName(names[names.length - 1], "Name");
      return fullName;
   }

   private void validateName(String name, String typeOfName) {
      NameValidator.Result result = this.nameValidator.validate(name);
      if (!result.isOK()) {
         throw new SchemaParseException(typeOfName + " \"" + name + "\" is invalid: " + result.getErrors());
      }
   }

   public boolean hasNewSchemas() {
      return !this.newSchemas.isEmpty();
   }

   public void commit() {
      this.oldSchemas.putAll(this.newSchemas);
      this.newSchemas.clear();
   }

   public SchemaParser.ParseResult commit(final Schema mainSchema) {
      final Collection<Schema> parsedNamedSchemas = this.newSchemas.values();
      SchemaParser.ParseResult parseResult = new SchemaParser.ParseResult() {
         public Schema mainSchema() {
            return mainSchema == null ? null : ParseContext.this.resolve(mainSchema);
         }

         public List parsedNamedSchemas() {
            return (List)parsedNamedSchemas.stream().map(ParseContext.this::resolve).collect(Collectors.toList());
         }
      };
      this.commit();
      return parseResult;
   }

   public void rollback() {
      this.newSchemas.clear();
   }

   public List resolveAllSchemas() {
      this.ensureSchemasAreResolved();
      return new ArrayList(this.oldSchemas.values());
   }

   private void ensureSchemasAreResolved() {
      if (this.hasNewSchemas()) {
         throw new IllegalStateException("Schemas cannot be resolved unless the ParseContext is committed.");
      } else {
         if (this.resolvingVisitor == null) {
            NameValidator saved = Schema.getNameValidator();

            try {
               Schema.setNameValidator(this.nameValidator);
               Map var10002 = this.oldSchemas;
               Objects.requireNonNull(var10002);
               SchemaResolver.ResolvingVisitor visitor = new SchemaResolver.ResolvingVisitor(var10002::get);
               this.oldSchemas.values().forEach((schema) -> Schemas.visit(schema, visitor));

               for(Map.Entry entry : this.oldSchemas.entrySet()) {
                  entry.setValue(visitor.getResolved((Schema)entry.getValue()));
               }

               this.resolvingVisitor = visitor;
            } finally {
               Schema.setNameValidator(saved);
            }
         }

      }
   }

   public Schema resolve(Schema schema) {
      this.ensureSchemasAreResolved();
      if (NAMED_SCHEMA_TYPES.contains(schema.getType()) && schema.getFullName() != null) {
         return (Schema)Objects.requireNonNull((Schema)this.oldSchemas.get(schema.getFullName()), () -> "Unknown schema: " + schema.getFullName());
      } else {
         Schemas.visit(schema, this.resolvingVisitor);
         return this.resolvingVisitor.getResolved(schema);
      }
   }

   public Map typesByName() {
      LinkedHashMap<String, Schema> result = new LinkedHashMap();
      result.putAll(this.oldSchemas);
      result.putAll(this.newSchemas);
      return result;
   }

   static {
      PRIMITIVES.put("string", Schema.Type.STRING);
      PRIMITIVES.put("bytes", Schema.Type.BYTES);
      PRIMITIVES.put("int", Schema.Type.INT);
      PRIMITIVES.put("long", Schema.Type.LONG);
      PRIMITIVES.put("float", Schema.Type.FLOAT);
      PRIMITIVES.put("double", Schema.Type.DOUBLE);
      PRIMITIVES.put("boolean", Schema.Type.BOOLEAN);
      PRIMITIVES.put("null", Schema.Type.NULL);
      NAMED_SCHEMA_TYPES = EnumSet.of(Schema.Type.RECORD, Schema.Type.ENUM, Schema.Type.FIXED);
   }
}
