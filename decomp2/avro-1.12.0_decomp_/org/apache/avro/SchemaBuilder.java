package org.apache.avro;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.internal.JacksonUtils;

public class SchemaBuilder {
   private static final Schema NULL_SCHEMA;

   private SchemaBuilder() {
   }

   public static TypeBuilder builder() {
      return new TypeBuilder(new SchemaCompletion(), new NameContext());
   }

   public static TypeBuilder builder(String namespace) {
      return new TypeBuilder(new SchemaCompletion(), (new NameContext()).namespace(namespace));
   }

   public static RecordBuilder record(String name) {
      return builder().record(name);
   }

   public static EnumBuilder enumeration(String name) {
      return builder().enumeration(name);
   }

   public static FixedBuilder fixed(String name) {
      return builder().fixed(name);
   }

   public static ArrayBuilder array() {
      return builder().array();
   }

   public static MapBuilder map() {
      return builder().map();
   }

   public static BaseTypeBuilder unionOf() {
      return builder().unionOf();
   }

   public static BaseTypeBuilder nullable() {
      return builder().nullable();
   }

   private static JsonNode toJsonNode(Object o) {
      try {
         String s;
         if (o instanceof ByteBuffer) {
            ByteBuffer bytes = (ByteBuffer)o;
            ((Buffer)bytes).mark();
            byte[] data = new byte[bytes.remaining()];
            bytes.get(data);
            ((Buffer)bytes).reset();
            s = new String(data, StandardCharsets.ISO_8859_1);
            char[] quoted = JsonStringEncoder.getInstance().quoteAsString(s);
            String var10000 = new String(quoted);
            s = "\"" + var10000 + "\"";
         } else if (o instanceof byte[]) {
            s = new String((byte[])o, StandardCharsets.ISO_8859_1);
            char[] quoted = JsonStringEncoder.getInstance().quoteAsString(s);
            String var9 = new String(quoted);
            s = "\"" + var9 + "\"";
         } else {
            s = GenericData.get().toString(o);
         }

         return (new ObjectMapper()).readTree(s);
      } catch (IOException e) {
         throw new SchemaBuilderException(e);
      }
   }

   static {
      NULL_SCHEMA = Schema.create(Schema.Type.NULL);
   }

   public abstract static class PropBuilder {
      private Map props = null;

      protected PropBuilder() {
      }

      public final PropBuilder prop(String name, String val) {
         return this.prop(name, (JsonNode)TextNode.valueOf(val));
      }

      public final PropBuilder prop(String name, Object value) {
         return this.prop(name, JacksonUtils.toJsonNode(value));
      }

      final PropBuilder prop(String name, JsonNode val) {
         if (!this.hasProps()) {
            this.props = new HashMap();
         }

         this.props.put(name, val);
         return this.self();
      }

      private boolean hasProps() {
         return this.props != null;
      }

      final JsonProperties addPropsTo(JsonProperties jsonable) {
         if (this.hasProps()) {
            for(Map.Entry prop : this.props.entrySet()) {
               jsonable.addProp((String)prop.getKey(), prop.getValue());
            }
         }

         return jsonable;
      }

      protected abstract PropBuilder self();
   }

   public abstract static class NamedBuilder extends PropBuilder {
      private final String name;
      private final NameContext names;
      private String doc;
      private String[] aliases;

      protected NamedBuilder(NameContext names, String name) {
         this.name = (String)Objects.requireNonNull(name, "Type must have a name");
         this.names = names;
      }

      public final NamedBuilder doc(String doc) {
         this.doc = doc;
         return (NamedBuilder)this.self();
      }

      public final NamedBuilder aliases(String... aliases) {
         this.aliases = aliases;
         return (NamedBuilder)this.self();
      }

      final String doc() {
         return this.doc;
      }

      final String name() {
         return this.name;
      }

      final NameContext names() {
         return this.names;
      }

      final Schema addAliasesTo(Schema schema) {
         if (null != this.aliases) {
            for(String alias : this.aliases) {
               schema.addAlias(alias);
            }
         }

         return schema;
      }

      final Schema.Field addAliasesTo(Schema.Field field) {
         if (null != this.aliases) {
            for(String alias : this.aliases) {
               field.addAlias(alias);
            }
         }

         return field;
      }
   }

   public abstract static class NamespacedBuilder extends NamedBuilder {
      private final Completion context;
      private String namespace;

      protected NamespacedBuilder(Completion context, NameContext names, String name) {
         super(names, name);
         this.context = context;
      }

      public final NamespacedBuilder namespace(String namespace) {
         this.namespace = namespace;
         return (NamespacedBuilder)this.self();
      }

      final String space() {
         return null == this.namespace ? this.names().namespace : this.namespace;
      }

      final Schema completeSchema(Schema schema) {
         this.addPropsTo(schema);
         this.addAliasesTo(schema);
         this.names().put(schema);
         return schema;
      }

      final Completion context() {
         return this.context;
      }
   }

   private abstract static class PrimitiveBuilder extends PropBuilder {
      private final Completion context;
      private final Schema immutable;

      protected PrimitiveBuilder(Completion context, NameContext names, Schema.Type type) {
         this.context = context;
         this.immutable = names.getFullname(type.getName());
      }

      private Object end() {
         Schema schema = this.immutable;
         if (super.hasProps()) {
            schema = Schema.create(this.immutable.getType());
            this.addPropsTo(schema);
         }

         return this.context.complete(schema);
      }
   }

   public static final class BooleanBuilder extends PrimitiveBuilder {
      private BooleanBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.BOOLEAN);
      }

      private static BooleanBuilder create(Completion context, NameContext names) {
         return new BooleanBuilder(context, names);
      }

      protected BooleanBuilder self() {
         return this;
      }

      public Object endBoolean() {
         return super.end();
      }
   }

   public static final class IntBuilder extends PrimitiveBuilder {
      private IntBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.INT);
      }

      private static IntBuilder create(Completion context, NameContext names) {
         return new IntBuilder(context, names);
      }

      protected IntBuilder self() {
         return this;
      }

      public Object endInt() {
         return super.end();
      }
   }

   public static final class LongBuilder extends PrimitiveBuilder {
      private LongBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.LONG);
      }

      private static LongBuilder create(Completion context, NameContext names) {
         return new LongBuilder(context, names);
      }

      protected LongBuilder self() {
         return this;
      }

      public Object endLong() {
         return super.end();
      }
   }

   public static final class FloatBuilder extends PrimitiveBuilder {
      private FloatBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.FLOAT);
      }

      private static FloatBuilder create(Completion context, NameContext names) {
         return new FloatBuilder(context, names);
      }

      protected FloatBuilder self() {
         return this;
      }

      public Object endFloat() {
         return super.end();
      }
   }

   public static final class DoubleBuilder extends PrimitiveBuilder {
      private DoubleBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.DOUBLE);
      }

      private static DoubleBuilder create(Completion context, NameContext names) {
         return new DoubleBuilder(context, names);
      }

      protected DoubleBuilder self() {
         return this;
      }

      public Object endDouble() {
         return super.end();
      }
   }

   public static final class StringBldr extends PrimitiveBuilder {
      private StringBldr(Completion context, NameContext names) {
         super(context, names, Schema.Type.STRING);
      }

      private static StringBldr create(Completion context, NameContext names) {
         return new StringBldr(context, names);
      }

      protected StringBldr self() {
         return this;
      }

      public Object endString() {
         return super.end();
      }
   }

   public static final class BytesBuilder extends PrimitiveBuilder {
      private BytesBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.BYTES);
      }

      private static BytesBuilder create(Completion context, NameContext names) {
         return new BytesBuilder(context, names);
      }

      protected BytesBuilder self() {
         return this;
      }

      public Object endBytes() {
         return super.end();
      }
   }

   public static final class NullBuilder extends PrimitiveBuilder {
      private NullBuilder(Completion context, NameContext names) {
         super(context, names, Schema.Type.NULL);
      }

      private static NullBuilder create(Completion context, NameContext names) {
         return new NullBuilder(context, names);
      }

      protected NullBuilder self() {
         return this;
      }

      public Object endNull() {
         return super.end();
      }
   }

   public static final class FixedBuilder extends NamespacedBuilder {
      private FixedBuilder(Completion context, NameContext names, String name) {
         super(context, names, name);
      }

      private static FixedBuilder create(Completion context, NameContext names, String name) {
         return new FixedBuilder(context, names, name);
      }

      protected FixedBuilder self() {
         return this;
      }

      public Object size(int size) {
         Schema schema = Schema.createFixed(this.name(), super.doc(), this.space(), size);
         this.completeSchema(schema);
         return this.context().complete(schema);
      }
   }

   public static final class EnumBuilder extends NamespacedBuilder {
      private String enumDefault = null;

      private EnumBuilder(Completion context, NameContext names, String name) {
         super(context, names, name);
      }

      private static EnumBuilder create(Completion context, NameContext names, String name) {
         return new EnumBuilder(context, names, name);
      }

      protected EnumBuilder self() {
         return this;
      }

      public Object symbols(String... symbols) {
         Schema schema = Schema.createEnum(this.name(), this.doc(), this.space(), Arrays.asList(symbols), this.enumDefault);
         this.completeSchema(schema);
         return this.context().complete(schema);
      }

      public EnumBuilder defaultSymbol(String enumDefault) {
         this.enumDefault = enumDefault;
         return this.self();
      }
   }

   public static final class MapBuilder extends PropBuilder {
      private final Completion context;
      private final NameContext names;

      private MapBuilder(Completion context, NameContext names) {
         this.context = context;
         this.names = names;
      }

      private static MapBuilder create(Completion context, NameContext names) {
         return new MapBuilder(context, names);
      }

      protected MapBuilder self() {
         return this;
      }

      public TypeBuilder values() {
         return new TypeBuilder(new MapCompletion(this, this.context), this.names);
      }

      public Object values(Schema valueSchema) {
         return (new MapCompletion(this, this.context)).complete(valueSchema);
      }
   }

   public static final class ArrayBuilder extends PropBuilder {
      private final Completion context;
      private final NameContext names;

      public ArrayBuilder(Completion context, NameContext names) {
         this.context = context;
         this.names = names;
      }

      private static ArrayBuilder create(Completion context, NameContext names) {
         return new ArrayBuilder(context, names);
      }

      protected ArrayBuilder self() {
         return this;
      }

      public TypeBuilder items() {
         return new TypeBuilder(new ArrayCompletion(this, this.context), this.names);
      }

      public Object items(Schema itemsSchema) {
         return (new ArrayCompletion(this, this.context)).complete(itemsSchema);
      }
   }

   private static class NameContext {
      private static final Set PRIMITIVES = new HashSet();
      private final HashMap schemas;
      private final String namespace;

      private NameContext() {
         this.schemas = new HashMap();
         this.namespace = null;
         this.schemas.put("null", Schema.create(Schema.Type.NULL));
         this.schemas.put("boolean", Schema.create(Schema.Type.BOOLEAN));
         this.schemas.put("int", Schema.create(Schema.Type.INT));
         this.schemas.put("long", Schema.create(Schema.Type.LONG));
         this.schemas.put("float", Schema.create(Schema.Type.FLOAT));
         this.schemas.put("double", Schema.create(Schema.Type.DOUBLE));
         this.schemas.put("bytes", Schema.create(Schema.Type.BYTES));
         this.schemas.put("string", Schema.create(Schema.Type.STRING));
      }

      private NameContext(HashMap schemas, String namespace) {
         this.schemas = schemas;
         this.namespace = "".equals(namespace) ? null : namespace;
      }

      private NameContext namespace(String namespace) {
         return new NameContext(this.schemas, namespace);
      }

      private Schema get(String name, String namespace) {
         return this.getFullname(this.resolveName(name, namespace));
      }

      private Schema getFullname(String fullName) {
         Schema schema = (Schema)this.schemas.get(fullName);
         if (schema == null) {
            throw new SchemaParseException("Undefined name: " + fullName);
         } else {
            return schema;
         }
      }

      private void put(Schema schema) {
         String fullName = schema.getFullName();
         if (this.schemas.containsKey(fullName)) {
            throw new SchemaParseException("Can't redefine: " + fullName);
         } else {
            this.schemas.put(fullName, schema);
         }
      }

      private String resolveName(String name, String space) {
         if (PRIMITIVES.contains(name) && space == null) {
            return name;
         } else {
            int lastDot = name.lastIndexOf(46);
            if (lastDot < 0) {
               if (space == null) {
                  space = this.namespace;
               }

               if (space != null && !"".equals(space)) {
                  return space + "." + name;
               }
            }

            return name;
         }
      }

      static {
         PRIMITIVES.add("null");
         PRIMITIVES.add("boolean");
         PRIMITIVES.add("int");
         PRIMITIVES.add("long");
         PRIMITIVES.add("float");
         PRIMITIVES.add("double");
         PRIMITIVES.add("bytes");
         PRIMITIVES.add("string");
      }
   }

   public static class BaseTypeBuilder {
      private final Completion context;
      private final NameContext names;

      private BaseTypeBuilder(Completion context, NameContext names) {
         this.context = context;
         this.names = names;
      }

      public final Object type(Schema schema) {
         return this.context.complete(schema);
      }

      public final Object type(String name) {
         return this.type(name, (String)null);
      }

      public final Object type(String name, String namespace) {
         return this.type(this.names.get(name, namespace));
      }

      public final Object booleanType() {
         return this.booleanBuilder().endBoolean();
      }

      public final BooleanBuilder booleanBuilder() {
         return SchemaBuilder.BooleanBuilder.create(this.context, this.names);
      }

      public final Object intType() {
         return this.intBuilder().endInt();
      }

      public final IntBuilder intBuilder() {
         return SchemaBuilder.IntBuilder.create(this.context, this.names);
      }

      public final Object longType() {
         return this.longBuilder().endLong();
      }

      public final LongBuilder longBuilder() {
         return SchemaBuilder.LongBuilder.create(this.context, this.names);
      }

      public final Object floatType() {
         return this.floatBuilder().endFloat();
      }

      public final FloatBuilder floatBuilder() {
         return SchemaBuilder.FloatBuilder.create(this.context, this.names);
      }

      public final Object doubleType() {
         return this.doubleBuilder().endDouble();
      }

      public final DoubleBuilder doubleBuilder() {
         return SchemaBuilder.DoubleBuilder.create(this.context, this.names);
      }

      public final Object stringType() {
         return this.stringBuilder().endString();
      }

      public final StringBldr stringBuilder() {
         return SchemaBuilder.StringBldr.create(this.context, this.names);
      }

      public final Object bytesType() {
         return this.bytesBuilder().endBytes();
      }

      public final BytesBuilder bytesBuilder() {
         return SchemaBuilder.BytesBuilder.create(this.context, this.names);
      }

      public final Object nullType() {
         return this.nullBuilder().endNull();
      }

      public final NullBuilder nullBuilder() {
         return SchemaBuilder.NullBuilder.create(this.context, this.names);
      }

      public final MapBuilder map() {
         return SchemaBuilder.MapBuilder.create(this.context, this.names);
      }

      public final ArrayBuilder array() {
         return SchemaBuilder.ArrayBuilder.create(this.context, this.names);
      }

      public final FixedBuilder fixed(String name) {
         return SchemaBuilder.FixedBuilder.create(this.context, this.names, name);
      }

      public final EnumBuilder enumeration(String name) {
         return SchemaBuilder.EnumBuilder.create(this.context, this.names, name);
      }

      public final RecordBuilder record(String name) {
         return SchemaBuilder.RecordBuilder.create(this.context, this.names, name);
      }

      protected BaseTypeBuilder unionOf() {
         return SchemaBuilder.UnionBuilder.create(this.context, this.names);
      }

      protected BaseTypeBuilder nullable() {
         return new BaseTypeBuilder(new NullableCompletion(this.context), this.names);
      }
   }

   public static final class TypeBuilder extends BaseTypeBuilder {
      private TypeBuilder(Completion context, NameContext names) {
         super(context, names);
      }

      public BaseTypeBuilder unionOf() {
         return super.unionOf();
      }

      public BaseTypeBuilder nullable() {
         return super.nullable();
      }
   }

   private static final class UnionBuilder extends BaseTypeBuilder {
      private UnionBuilder(Completion context, NameContext names) {
         this(context, names, Collections.emptyList());
      }

      private static UnionBuilder create(Completion context, NameContext names) {
         return new UnionBuilder(context, names);
      }

      private UnionBuilder(Completion context, NameContext names, List schemas) {
         super(new UnionCompletion(context, names, schemas), names);
      }
   }

   public static class BaseFieldTypeBuilder {
      protected final FieldBuilder bldr;
      protected final NameContext names;
      private final CompletionWrapper wrapper;

      protected BaseFieldTypeBuilder(FieldBuilder bldr, CompletionWrapper wrapper) {
         this.bldr = bldr;
         this.names = bldr.names();
         this.wrapper = wrapper;
      }

      public final BooleanDefault booleanType() {
         return (BooleanDefault)this.booleanBuilder().endBoolean();
      }

      public final BooleanBuilder booleanBuilder() {
         return SchemaBuilder.BooleanBuilder.create(this.wrap(new BooleanDefault(this.bldr)), this.names);
      }

      public final IntDefault intType() {
         return (IntDefault)this.intBuilder().endInt();
      }

      public final IntBuilder intBuilder() {
         return SchemaBuilder.IntBuilder.create(this.wrap(new IntDefault(this.bldr)), this.names);
      }

      public final LongDefault longType() {
         return (LongDefault)this.longBuilder().endLong();
      }

      public final LongBuilder longBuilder() {
         return SchemaBuilder.LongBuilder.create(this.wrap(new LongDefault(this.bldr)), this.names);
      }

      public final FloatDefault floatType() {
         return (FloatDefault)this.floatBuilder().endFloat();
      }

      public final FloatBuilder floatBuilder() {
         return SchemaBuilder.FloatBuilder.create(this.wrap(new FloatDefault(this.bldr)), this.names);
      }

      public final DoubleDefault doubleType() {
         return (DoubleDefault)this.doubleBuilder().endDouble();
      }

      public final DoubleBuilder doubleBuilder() {
         return SchemaBuilder.DoubleBuilder.create(this.wrap(new DoubleDefault(this.bldr)), this.names);
      }

      public final StringDefault stringType() {
         return (StringDefault)this.stringBuilder().endString();
      }

      public final StringBldr stringBuilder() {
         return SchemaBuilder.StringBldr.create(this.wrap(new StringDefault(this.bldr)), this.names);
      }

      public final BytesDefault bytesType() {
         return (BytesDefault)this.bytesBuilder().endBytes();
      }

      public final BytesBuilder bytesBuilder() {
         return SchemaBuilder.BytesBuilder.create(this.wrap(new BytesDefault(this.bldr)), this.names);
      }

      public final NullDefault nullType() {
         return (NullDefault)this.nullBuilder().endNull();
      }

      public final NullBuilder nullBuilder() {
         return SchemaBuilder.NullBuilder.create(this.wrap(new NullDefault(this.bldr)), this.names);
      }

      public final MapBuilder map() {
         return SchemaBuilder.MapBuilder.create(this.wrap(new MapDefault(this.bldr)), this.names);
      }

      public final ArrayBuilder array() {
         return SchemaBuilder.ArrayBuilder.create(this.wrap(new ArrayDefault(this.bldr)), this.names);
      }

      public final FixedBuilder fixed(String name) {
         return SchemaBuilder.FixedBuilder.create(this.wrap(new FixedDefault(this.bldr)), this.names, name);
      }

      public final EnumBuilder enumeration(String name) {
         return SchemaBuilder.EnumBuilder.create(this.wrap(new EnumDefault(this.bldr)), this.names, name);
      }

      public final RecordBuilder record(String name) {
         return SchemaBuilder.RecordBuilder.create(this.wrap(new RecordDefault(this.bldr)), this.names, name);
      }

      private Completion wrap(Completion completion) {
         return this.wrapper != null ? this.wrapper.wrap(completion) : completion;
      }
   }

   public static final class FieldTypeBuilder extends BaseFieldTypeBuilder {
      private FieldTypeBuilder(FieldBuilder bldr) {
         super(bldr, (CompletionWrapper)null);
      }

      public UnionFieldTypeBuilder unionOf() {
         return new UnionFieldTypeBuilder(this.bldr);
      }

      public BaseFieldTypeBuilder nullable() {
         return new BaseFieldTypeBuilder(this.bldr, new NullableCompletionWrapper());
      }

      public BaseTypeBuilder optional() {
         return new BaseTypeBuilder(new OptionalCompletion(this.bldr), this.names);
      }
   }

   public static final class UnionFieldTypeBuilder {
      private final FieldBuilder bldr;
      private final NameContext names;

      private UnionFieldTypeBuilder(FieldBuilder bldr) {
         this.bldr = bldr;
         this.names = bldr.names();
      }

      public UnionAccumulator booleanType() {
         return (UnionAccumulator)this.booleanBuilder().endBoolean();
      }

      public BooleanBuilder booleanBuilder() {
         return SchemaBuilder.BooleanBuilder.create(this.completion(new BooleanDefault(this.bldr)), this.names);
      }

      public UnionAccumulator intType() {
         return (UnionAccumulator)this.intBuilder().endInt();
      }

      public IntBuilder intBuilder() {
         return SchemaBuilder.IntBuilder.create(this.completion(new IntDefault(this.bldr)), this.names);
      }

      public UnionAccumulator longType() {
         return (UnionAccumulator)this.longBuilder().endLong();
      }

      public LongBuilder longBuilder() {
         return SchemaBuilder.LongBuilder.create(this.completion(new LongDefault(this.bldr)), this.names);
      }

      public UnionAccumulator floatType() {
         return (UnionAccumulator)this.floatBuilder().endFloat();
      }

      public FloatBuilder floatBuilder() {
         return SchemaBuilder.FloatBuilder.create(this.completion(new FloatDefault(this.bldr)), this.names);
      }

      public UnionAccumulator doubleType() {
         return (UnionAccumulator)this.doubleBuilder().endDouble();
      }

      public DoubleBuilder doubleBuilder() {
         return SchemaBuilder.DoubleBuilder.create(this.completion(new DoubleDefault(this.bldr)), this.names);
      }

      public UnionAccumulator stringType() {
         return (UnionAccumulator)this.stringBuilder().endString();
      }

      public StringBldr stringBuilder() {
         return SchemaBuilder.StringBldr.create(this.completion(new StringDefault(this.bldr)), this.names);
      }

      public UnionAccumulator bytesType() {
         return (UnionAccumulator)this.bytesBuilder().endBytes();
      }

      public BytesBuilder bytesBuilder() {
         return SchemaBuilder.BytesBuilder.create(this.completion(new BytesDefault(this.bldr)), this.names);
      }

      public UnionAccumulator nullType() {
         return (UnionAccumulator)this.nullBuilder().endNull();
      }

      public NullBuilder nullBuilder() {
         return SchemaBuilder.NullBuilder.create(this.completion(new NullDefault(this.bldr)), this.names);
      }

      public MapBuilder map() {
         return SchemaBuilder.MapBuilder.create(this.completion(new MapDefault(this.bldr)), this.names);
      }

      public ArrayBuilder array() {
         return SchemaBuilder.ArrayBuilder.create(this.completion(new ArrayDefault(this.bldr)), this.names);
      }

      public FixedBuilder fixed(String name) {
         return SchemaBuilder.FixedBuilder.create(this.completion(new FixedDefault(this.bldr)), this.names, name);
      }

      public EnumBuilder enumeration(String name) {
         return SchemaBuilder.EnumBuilder.create(this.completion(new EnumDefault(this.bldr)), this.names, name);
      }

      public RecordBuilder record(String name) {
         return SchemaBuilder.RecordBuilder.create(this.completion(new RecordDefault(this.bldr)), this.names, name);
      }

      private UnionCompletion completion(Completion context) {
         return new UnionCompletion(context, this.names, Collections.emptyList());
      }
   }

   public static final class RecordBuilder extends NamespacedBuilder {
      private RecordBuilder(Completion context, NameContext names, String name) {
         super(context, names, name);
      }

      private static RecordBuilder create(Completion context, NameContext names, String name) {
         return new RecordBuilder(context, names, name);
      }

      protected RecordBuilder self() {
         return this;
      }

      public FieldAssembler fields() {
         Schema record = Schema.createRecord(this.name(), this.doc(), this.space(), false);
         this.completeSchema(record);
         return new FieldAssembler(this.context(), this.names().namespace(record.getNamespace()), record);
      }
   }

   public static final class FieldAssembler {
      private final List fields = new ArrayList();
      private final Completion context;
      private final NameContext names;
      private final Schema record;

      private FieldAssembler(Completion context, NameContext names, Schema record) {
         this.context = context;
         this.names = names;
         this.record = record;
      }

      public FieldBuilder name(String fieldName) {
         return new FieldBuilder(this, this.names, fieldName);
      }

      public FieldAssembler requiredBoolean(String fieldName) {
         return this.name(fieldName).type().booleanType().noDefault();
      }

      public FieldAssembler optionalBoolean(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().booleanType();
      }

      public FieldAssembler nullableBoolean(String fieldName, boolean defaultVal) {
         return this.name(fieldName).type().nullable().booleanType().booleanDefault(defaultVal);
      }

      public FieldAssembler requiredInt(String fieldName) {
         return this.name(fieldName).type().intType().noDefault();
      }

      public FieldAssembler optionalInt(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().intType();
      }

      public FieldAssembler nullableInt(String fieldName, int defaultVal) {
         return this.name(fieldName).type().nullable().intType().intDefault(defaultVal);
      }

      public FieldAssembler requiredLong(String fieldName) {
         return this.name(fieldName).type().longType().noDefault();
      }

      public FieldAssembler optionalLong(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().longType();
      }

      public FieldAssembler nullableLong(String fieldName, long defaultVal) {
         return this.name(fieldName).type().nullable().longType().longDefault(defaultVal);
      }

      public FieldAssembler requiredFloat(String fieldName) {
         return this.name(fieldName).type().floatType().noDefault();
      }

      public FieldAssembler optionalFloat(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().floatType();
      }

      public FieldAssembler nullableFloat(String fieldName, float defaultVal) {
         return this.name(fieldName).type().nullable().floatType().floatDefault(defaultVal);
      }

      public FieldAssembler requiredDouble(String fieldName) {
         return this.name(fieldName).type().doubleType().noDefault();
      }

      public FieldAssembler optionalDouble(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().doubleType();
      }

      public FieldAssembler nullableDouble(String fieldName, double defaultVal) {
         return this.name(fieldName).type().nullable().doubleType().doubleDefault(defaultVal);
      }

      public FieldAssembler requiredString(String fieldName) {
         return this.name(fieldName).type().stringType().noDefault();
      }

      public FieldAssembler optionalString(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().stringType();
      }

      public FieldAssembler nullableString(String fieldName, String defaultVal) {
         return this.name(fieldName).type().nullable().stringType().stringDefault(defaultVal);
      }

      public FieldAssembler requiredBytes(String fieldName) {
         return this.name(fieldName).type().bytesType().noDefault();
      }

      public FieldAssembler optionalBytes(String fieldName) {
         return (FieldAssembler)this.name(fieldName).type().optional().bytesType();
      }

      public FieldAssembler nullableBytes(String fieldName, byte[] defaultVal) {
         return this.name(fieldName).type().nullable().bytesType().bytesDefault(defaultVal);
      }

      public Object endRecord() {
         this.record.setFields(this.fields);
         return this.context.complete(this.record);
      }

      private FieldAssembler addField(Schema.Field field) {
         this.fields.add(field);
         return this;
      }
   }

   public static final class FieldBuilder extends NamedBuilder {
      private final FieldAssembler fields;
      private Schema.Field.Order order;
      private boolean validatingDefaults;

      private FieldBuilder(FieldAssembler fields, NameContext names, String name) {
         super(names, name);
         this.order = Schema.Field.Order.ASCENDING;
         this.validatingDefaults = true;
         this.fields = fields;
      }

      public FieldBuilder orderAscending() {
         this.order = Schema.Field.Order.ASCENDING;
         return this.self();
      }

      public FieldBuilder orderDescending() {
         this.order = Schema.Field.Order.DESCENDING;
         return this.self();
      }

      public FieldBuilder orderIgnore() {
         this.order = Schema.Field.Order.IGNORE;
         return this.self();
      }

      public FieldBuilder validatingDefaults() {
         this.validatingDefaults = true;
         return this.self();
      }

      public FieldBuilder notValidatingDefaults() {
         this.validatingDefaults = false;
         return this.self();
      }

      public FieldTypeBuilder type() {
         return new FieldTypeBuilder(this);
      }

      public GenericDefault type(Schema type) {
         return new GenericDefault(this, type);
      }

      public GenericDefault type(String name) {
         return this.type(name, (String)null);
      }

      public GenericDefault type(String name, String namespace) {
         Schema schema = this.names().get(name, namespace);
         return this.type(schema);
      }

      private FieldAssembler completeField(Schema schema, Object defaultVal) {
         JsonNode defaultNode = (JsonNode)(defaultVal == null ? NullNode.getInstance() : SchemaBuilder.toJsonNode(defaultVal));
         return this.completeField(schema, defaultNode);
      }

      private FieldAssembler completeField(Schema schema) {
         return this.completeField(schema, (JsonNode)null);
      }

      private FieldAssembler completeField(Schema schema, JsonNode defaultVal) {
         Schema.Field field = new Schema.Field(this.name(), schema, this.doc(), defaultVal, this.validatingDefaults, this.order);
         this.addPropsTo(field);
         this.addAliasesTo(field);
         return this.fields.addField(field);
      }

      protected FieldBuilder self() {
         return this;
      }
   }

   public abstract static class FieldDefault extends Completion {
      private final FieldBuilder field;
      private Schema schema;

      FieldDefault(FieldBuilder field) {
         this.field = field;
      }

      public final FieldAssembler noDefault() {
         return this.field.completeField(this.schema);
      }

      private FieldAssembler usingDefault(Object defaultVal) {
         return this.field.completeField(this.schema, defaultVal);
      }

      final FieldDefault complete(Schema schema) {
         this.schema = schema;
         return this.self();
      }

      abstract FieldDefault self();
   }

   public static class BooleanDefault extends FieldDefault {
      private BooleanDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler booleanDefault(boolean defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final BooleanDefault self() {
         return this;
      }
   }

   public static class IntDefault extends FieldDefault {
      private IntDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler intDefault(int defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final IntDefault self() {
         return this;
      }
   }

   public static class LongDefault extends FieldDefault {
      private LongDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler longDefault(long defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final LongDefault self() {
         return this;
      }
   }

   public static class FloatDefault extends FieldDefault {
      private FloatDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler floatDefault(float defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final FloatDefault self() {
         return this;
      }
   }

   public static class DoubleDefault extends FieldDefault {
      private DoubleDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler doubleDefault(double defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final DoubleDefault self() {
         return this;
      }
   }

   public static class StringDefault extends FieldDefault {
      private StringDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler stringDefault(String defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final StringDefault self() {
         return this;
      }
   }

   public static class BytesDefault extends FieldDefault {
      private BytesDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler bytesDefault(byte[] defaultVal) {
         return super.usingDefault(ByteBuffer.wrap(defaultVal));
      }

      public final FieldAssembler bytesDefault(ByteBuffer defaultVal) {
         return super.usingDefault(defaultVal);
      }

      public final FieldAssembler bytesDefault(String defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final BytesDefault self() {
         return this;
      }
   }

   public static class NullDefault extends FieldDefault {
      private NullDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler nullDefault() {
         return super.usingDefault((Object)null);
      }

      final NullDefault self() {
         return this;
      }
   }

   public static class MapDefault extends FieldDefault {
      private MapDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler mapDefault(Map defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final MapDefault self() {
         return this;
      }
   }

   public static class ArrayDefault extends FieldDefault {
      private ArrayDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler arrayDefault(List defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final ArrayDefault self() {
         return this;
      }
   }

   public static class FixedDefault extends FieldDefault {
      private FixedDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler fixedDefault(byte[] defaultVal) {
         return super.usingDefault(ByteBuffer.wrap(defaultVal));
      }

      public final FieldAssembler fixedDefault(ByteBuffer defaultVal) {
         return super.usingDefault(defaultVal);
      }

      public final FieldAssembler fixedDefault(String defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final FixedDefault self() {
         return this;
      }
   }

   public static class EnumDefault extends FieldDefault {
      private EnumDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler enumDefault(String defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final EnumDefault self() {
         return this;
      }
   }

   public static class RecordDefault extends FieldDefault {
      private RecordDefault(FieldBuilder field) {
         super(field);
      }

      public final FieldAssembler recordDefault(GenericRecord defaultVal) {
         return super.usingDefault(defaultVal);
      }

      final RecordDefault self() {
         return this;
      }
   }

   public static final class GenericDefault {
      private final FieldBuilder field;
      private final Schema schema;

      private GenericDefault(FieldBuilder field, Schema schema) {
         this.field = field;
         this.schema = schema;
      }

      public FieldAssembler noDefault() {
         return this.field.completeField(this.schema);
      }

      public FieldAssembler withDefault(Object defaultVal) {
         return this.field.completeField(this.schema, defaultVal);
      }
   }

   private abstract static class Completion {
      abstract Object complete(Schema schema);
   }

   private static class SchemaCompletion extends Completion {
      protected Schema complete(Schema schema) {
         return schema;
      }
   }

   private static class NullableCompletion extends Completion {
      private final Completion context;

      private NullableCompletion(Completion context) {
         this.context = context;
      }

      protected Object complete(Schema schema) {
         Schema nullable = Schema.createUnion(Arrays.asList(schema, SchemaBuilder.NULL_SCHEMA));
         return this.context.complete(nullable);
      }
   }

   private static class OptionalCompletion extends Completion {
      private final FieldBuilder bldr;

      public OptionalCompletion(FieldBuilder bldr) {
         this.bldr = bldr;
      }

      protected FieldAssembler complete(Schema schema) {
         Schema optional = Schema.createUnion(Arrays.asList(SchemaBuilder.NULL_SCHEMA, schema));
         return this.bldr.completeField(optional, (Object)null);
      }
   }

   private abstract static class CompletionWrapper {
      abstract Completion wrap(Completion completion);
   }

   private static final class NullableCompletionWrapper extends CompletionWrapper {
      Completion wrap(Completion completion) {
         return new NullableCompletion(completion);
      }
   }

   private abstract static class NestedCompletion extends Completion {
      private final Completion context;
      private final PropBuilder assembler;

      private NestedCompletion(PropBuilder assembler, Completion context) {
         this.context = context;
         this.assembler = assembler;
      }

      protected final Object complete(Schema schema) {
         Schema outer = this.outerSchema(schema);
         this.assembler.addPropsTo(outer);
         return this.context.complete(outer);
      }

      protected abstract Schema outerSchema(Schema inner);
   }

   private static class MapCompletion extends NestedCompletion {
      private MapCompletion(MapBuilder assembler, Completion context) {
         super(assembler, context);
      }

      protected Schema outerSchema(Schema inner) {
         return Schema.createMap(inner);
      }
   }

   private static class ArrayCompletion extends NestedCompletion {
      private ArrayCompletion(ArrayBuilder assembler, Completion context) {
         super(assembler, context);
      }

      protected Schema outerSchema(Schema inner) {
         return Schema.createArray(inner);
      }
   }

   private static class UnionCompletion extends Completion {
      private final Completion context;
      private final NameContext names;
      private final List schemas;

      private UnionCompletion(Completion context, NameContext names, List schemas) {
         this.context = context;
         this.names = names;
         this.schemas = schemas;
      }

      protected UnionAccumulator complete(Schema schema) {
         List<Schema> updated = new ArrayList(this.schemas);
         updated.add(schema);
         return new UnionAccumulator(this.context, this.names, updated);
      }
   }

   public static final class UnionAccumulator {
      private final Completion context;
      private final NameContext names;
      private final List schemas;

      private UnionAccumulator(Completion context, NameContext names, List schemas) {
         this.context = context;
         this.names = names;
         this.schemas = schemas;
      }

      public BaseTypeBuilder and() {
         return new UnionBuilder(this.context, this.names, this.schemas);
      }

      public Object endUnion() {
         Schema schema = Schema.createUnion(this.schemas);
         return this.context.complete(schema);
      }
   }
}
