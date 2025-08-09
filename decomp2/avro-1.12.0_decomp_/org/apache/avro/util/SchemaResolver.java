package org.apache.avro.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;

public final class SchemaResolver {
   private static final String UR_SCHEMA_ATTR = "org.apache.avro.idl.unresolved.name";
   private static final String UR_SCHEMA_NAME = "UnresolvedSchema";
   private static final String UR_SCHEMA_NS = "org.apache.avro.compiler";
   private static final AtomicInteger COUNTER = new AtomicInteger();

   private SchemaResolver() {
   }

   public static Schema unresolvedSchema(final String name) {
      Schema schema = Schema.createRecord("UnresolvedSchema_" + COUNTER.getAndIncrement(), "unresolved schema", "org.apache.avro.compiler", false, Collections.emptyList());
      schema.addProp("org.apache.avro.idl.unresolved.name", name);
      return schema;
   }

   public static boolean isUnresolvedSchema(final Schema schema) {
      return schema.getType() == Schema.Type.RECORD && schema.getProp("org.apache.avro.idl.unresolved.name") != null && schema.getName() != null && schema.getName().startsWith("UnresolvedSchema") && "org.apache.avro.compiler".equals(schema.getNamespace());
   }

   public static String getUnresolvedSchemaName(final Schema schema) {
      if (!isUnresolvedSchema(schema)) {
         throw new IllegalArgumentException("Not a unresolved schema: " + String.valueOf(schema));
      } else {
         return schema.getProp("org.apache.avro.idl.unresolved.name");
      }
   }

   public static boolean isFullyResolvedSchema(final Schema schema) {
      return isUnresolvedSchema(schema) ? false : (Boolean)Schemas.visit(schema, new IsResolvedSchemaVisitor());
   }

   public static final class IsResolvedSchemaVisitor implements SchemaVisitor {
      boolean hasUnresolvedParts = false;

      IsResolvedSchemaVisitor() {
      }

      public SchemaVisitor.SchemaVisitorAction visitTerminal(Schema terminal) {
         this.hasUnresolvedParts = SchemaResolver.isUnresolvedSchema(terminal);
         return this.hasUnresolvedParts ? SchemaVisitor.SchemaVisitorAction.TERMINATE : SchemaVisitor.SchemaVisitorAction.CONTINUE;
      }

      public SchemaVisitor.SchemaVisitorAction visitNonTerminal(Schema nonTerminal) {
         this.hasUnresolvedParts = SchemaResolver.isUnresolvedSchema(nonTerminal);
         if (this.hasUnresolvedParts) {
            return SchemaVisitor.SchemaVisitorAction.TERMINATE;
         } else {
            return nonTerminal.getType() == Schema.Type.RECORD && !nonTerminal.hasFields() ? SchemaVisitor.SchemaVisitorAction.SKIP_SUBTREE : SchemaVisitor.SchemaVisitorAction.CONTINUE;
         }
      }

      public SchemaVisitor.SchemaVisitorAction afterVisitNonTerminal(Schema nonTerminal) {
         return SchemaVisitor.SchemaVisitorAction.CONTINUE;
      }

      public Boolean get() {
         return !this.hasUnresolvedParts;
      }
   }

   public static final class ResolvingVisitor implements SchemaVisitor {
      private static final Set CONTAINER_SCHEMA_TYPES;
      private static final Set NAMED_SCHEMA_TYPES;
      private final Function symbolTable;
      private final IdentityHashMap replace = new IdentityHashMap();

      public ResolvingVisitor(final Function symbolTable) {
         this.symbolTable = symbolTable;
      }

      public SchemaVisitor.SchemaVisitorAction visitTerminal(final Schema terminal) {
         Schema.Type type = terminal.getType();
         if (CONTAINER_SCHEMA_TYPES.contains(type)) {
            if (!this.replace.containsKey(terminal)) {
               throw new IllegalStateException("Schema " + String.valueOf(terminal) + " must be already processed");
            }
         } else {
            this.replace.put(terminal, terminal);
         }

         return SchemaVisitor.SchemaVisitorAction.CONTINUE;
      }

      public SchemaVisitor.SchemaVisitorAction visitNonTerminal(final Schema nt) {
         Schema.Type type = nt.getType();
         if (type == Schema.Type.RECORD && !this.replace.containsKey(nt)) {
            if (SchemaResolver.isUnresolvedSchema(nt)) {
               String unresolvedSchemaName = SchemaResolver.getUnresolvedSchemaName(nt);
               Schema resSchema = (Schema)this.symbolTable.apply(unresolvedSchemaName);
               if (resSchema == null) {
                  throw new AvroTypeException("Undefined schema: " + unresolvedSchemaName);
               }

               Schema replacement = (Schema)this.replace.computeIfAbsent(resSchema, (schema) -> {
                  Schemas.visit(schema, this);
                  return (Schema)this.replace.get(schema);
               });
               this.replace.put(nt, replacement);
            } else {
               this.replace.put(nt, Schema.createRecord(nt.getName(), nt.getDoc(), nt.getNamespace(), nt.isError()));
            }
         }

         return SchemaVisitor.SchemaVisitorAction.CONTINUE;
      }

      public void copyProperties(final Schema first, final Schema second) {
         Optional.ofNullable(first.getLogicalType()).ifPresent((logicalType) -> logicalType.addToSchema(second));
         if (NAMED_SCHEMA_TYPES.contains(first.getType())) {
            Set var10000 = first.getAliases();
            Objects.requireNonNull(second);
            var10000.forEach(second::addAlias);
         }

         Map var3 = first.getObjectProps();
         Objects.requireNonNull(second);
         var3.forEach(second::addProp);
      }

      public SchemaVisitor.SchemaVisitorAction afterVisitNonTerminal(final Schema nt) {
         Schema.Type type = nt.getType();
         Schema newSchema;
         switch (type) {
            case RECORD:
               if (!SchemaResolver.isUnresolvedSchema(nt)) {
                  newSchema = (Schema)this.replace.get(nt);
                  if (!newSchema.hasFields()) {
                     List<Schema.Field> fields = nt.getFields();
                     List<Schema.Field> newFields = new ArrayList(fields.size());

                     for(Schema.Field field : fields) {
                        newFields.add(new Schema.Field(field, (Schema)this.replace.get(field.schema())));
                     }

                     newSchema.setFields(newFields);
                     this.copyProperties(nt, newSchema);
                  }
               }

               return SchemaVisitor.SchemaVisitorAction.CONTINUE;
            case UNION:
               List<Schema> types = nt.getTypes();
               List<Schema> newTypes = new ArrayList(types.size());

               for(Schema sch : types) {
                  newTypes.add((Schema)Objects.requireNonNull((Schema)this.replace.get(sch)));
               }

               newSchema = Schema.createUnion(newTypes);
               break;
            case ARRAY:
               newSchema = Schema.createArray((Schema)Objects.requireNonNull((Schema)this.replace.get(nt.getElementType())));
               break;
            case MAP:
               newSchema = Schema.createMap((Schema)Objects.requireNonNull((Schema)this.replace.get(nt.getValueType())));
               break;
            default:
               String var10002 = String.valueOf(type);
               throw new IllegalStateException("Illegal type " + var10002 + ", schema " + String.valueOf(nt));
         }

         this.copyProperties(nt, newSchema);
         this.replace.put(nt, newSchema);
         return SchemaVisitor.SchemaVisitorAction.CONTINUE;
      }

      public Void get() {
         return null;
      }

      public Schema getResolved(Schema schema) {
         return (Schema)Objects.requireNonNull((Schema)this.replace.get(schema), () -> "Unknown schema: " + schema.getFullName() + ". Was it resolved before?");
      }

      public String toString() {
         String var10000 = String.valueOf(this.symbolTable);
         return "ResolvingVisitor{symbolTable=" + var10000 + ", replace=" + String.valueOf(this.replace) + "}";
      }

      static {
         CONTAINER_SCHEMA_TYPES = EnumSet.of(Schema.Type.RECORD, Schema.Type.ARRAY, Schema.Type.MAP, Schema.Type.UNION);
         NAMED_SCHEMA_TYPES = EnumSet.of(Schema.Type.RECORD, Schema.Type.ENUM, Schema.Type.FIXED);
      }
   }
}
