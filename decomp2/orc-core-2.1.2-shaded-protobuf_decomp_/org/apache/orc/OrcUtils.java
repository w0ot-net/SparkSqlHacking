package org.apache.orc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.orc.OrcProto.StringPair;
import org.apache.orc.OrcProto.Type;
import org.apache.orc.OrcProto.Type.Kind;
import org.apache.orc.impl.ParserUtils;
import org.apache.orc.impl.ReaderImpl;
import org.apache.orc.impl.SchemaEvolution;

public class OrcUtils {
   public static boolean[] includeColumns(String selectedColumns, TypeDescription schema) {
      int numFlattenedCols = schema.getMaximumId();
      boolean[] results = new boolean[numFlattenedCols + 1];
      if ("*".equals(selectedColumns)) {
         Arrays.fill(results, true);
         return results;
      } else {
         TypeDescription baseSchema = SchemaEvolution.checkAcidSchema(schema) ? SchemaEvolution.getBaseRow(schema) : schema;
         if (selectedColumns != null && baseSchema.getCategory() == TypeDescription.Category.STRUCT) {
            for(String columnName : selectedColumns.split(",")) {
               TypeDescription column = findColumn(baseSchema, columnName.trim());
               if (column != null) {
                  for(int i = column.getId(); i <= column.getMaximumId(); ++i) {
                     results[i] = true;
                  }
               }
            }
         }

         return results;
      }
   }

   private static TypeDescription findColumn(TypeDescription schema, String column) {
      TypeDescription result = schema;
      String[] columnMatcher = column.split("\\.");
      int index = 0;

      while(index < columnMatcher.length && result.getCategory() == TypeDescription.Category.STRUCT) {
         String columnName = columnMatcher[index];
         int prevIndex = index;
         List<TypeDescription> fields = result.getChildren();
         List<String> fieldNames = result.getFieldNames();

         for(int i = 0; i < fields.size(); ++i) {
            if (columnName.equalsIgnoreCase((String)fieldNames.get(i))) {
               result = (TypeDescription)fields.get(i);
               ++index;
               break;
            }
         }

         if (prevIndex == index) {
            return null;
         }
      }

      return result;
   }

   public static List getOrcTypes(TypeDescription typeDescr) {
      List<OrcProto.Type> result = new ArrayList();
      appendOrcTypes(result, typeDescr);
      return result;
   }

   private static void appendOrcTypes(List result, TypeDescription typeDescr) {
      OrcProto.Type.Builder type = Type.newBuilder();
      List<TypeDescription> children = typeDescr.getChildren();

      for(String key : typeDescr.getAttributeNames()) {
         type.addAttributes(StringPair.newBuilder().setKey(key).setValue(typeDescr.getAttributeValue(key)).build());
      }

      switch (typeDescr.getCategory()) {
         case BOOLEAN:
            type.setKind(Kind.BOOLEAN);
            break;
         case BYTE:
            type.setKind(Kind.BYTE);
            break;
         case SHORT:
            type.setKind(Kind.SHORT);
            break;
         case INT:
            type.setKind(Kind.INT);
            break;
         case LONG:
            type.setKind(Kind.LONG);
            break;
         case FLOAT:
            type.setKind(Kind.FLOAT);
            break;
         case DOUBLE:
            type.setKind(Kind.DOUBLE);
            break;
         case STRING:
            type.setKind(Kind.STRING);
            break;
         case CHAR:
            type.setKind(Kind.CHAR);
            type.setMaximumLength(typeDescr.getMaxLength());
            break;
         case VARCHAR:
            type.setKind(Kind.VARCHAR);
            type.setMaximumLength(typeDescr.getMaxLength());
            break;
         case BINARY:
            type.setKind(Kind.BINARY);
            break;
         case TIMESTAMP:
            type.setKind(Kind.TIMESTAMP);
            break;
         case TIMESTAMP_INSTANT:
            type.setKind(Kind.TIMESTAMP_INSTANT);
            break;
         case DATE:
            type.setKind(Kind.DATE);
            break;
         case DECIMAL:
            type.setKind(Kind.DECIMAL);
            type.setPrecision(typeDescr.getPrecision());
            type.setScale(typeDescr.getScale());
            break;
         case LIST:
            type.setKind(Kind.LIST);
            type.addSubtypes(((TypeDescription)children.get(0)).getId());
            break;
         case MAP:
            type.setKind(Kind.MAP);

            for(TypeDescription t : children) {
               type.addSubtypes(t.getId());
            }
            break;
         case STRUCT:
            type.setKind(Kind.STRUCT);

            for(TypeDescription t : children) {
               type.addSubtypes(t.getId());
            }

            for(String field : typeDescr.getFieldNames()) {
               type.addFieldNames(field);
            }
            break;
         case UNION:
            type.setKind(Kind.UNION);

            for(TypeDescription t : children) {
               type.addSubtypes(t.getId());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown category: " + String.valueOf(typeDescr.getCategory()));
      }

      result.add(type.build());
      if (children != null) {
         for(TypeDescription child : children) {
            appendOrcTypes(result, child);
         }
      }

   }

   public static int isValidTypeTree(List types, int root) throws IOException {
      if (root >= 0 && root < types.size()) {
         OrcProto.Type rootType = (OrcProto.Type)types.get(root);
         int current = root + 1;
         List<Integer> children = rootType.getSubtypesList();
         if (!rootType.hasKind()) {
            throw new IOException("Type " + root + " has an unknown kind.");
         } else {
            switch (rootType.getKind()) {
               case LIST:
                  if (children == null || children.size() != 1) {
                     throw new IOException("Wrong number of type children in list " + root);
                  }
                  break;
               case MAP:
                  if (children == null || children.size() != 2) {
                     throw new IOException("Wrong number of type children in map " + root);
                  }
               case UNION:
               case STRUCT:
                  break;
               default:
                  if (children != null && children.size() != 0) {
                     throw new IOException("Type children under primitive type " + root);
                  }
            }

            if (children != null) {
               for(int child : children) {
                  if (child != current) {
                     throw new IOException("Unexpected child type id " + child + " when " + current + " was expected.");
                  }

                  current = isValidTypeTree(types, current);
               }
            }

            return current;
         }
      } else {
         throw new IOException("Illegal type id " + root + ". The valid range is 0 to " + (types.size() - 1));
      }
   }

   public static TypeDescription convertTypeFromProtobuf(List types, int rootColumn) throws FileFormatException {
      OrcProto.Type type = (OrcProto.Type)types.get(rootColumn);
      TypeDescription result;
      switch (type.getKind()) {
         case LIST:
            if (type.getSubtypesCount() != 1) {
               throw new FileFormatException("LIST type should contain exactly one subtype but has " + type.getSubtypesCount());
            }

            result = TypeDescription.createList(convertTypeFromProtobuf(types, type.getSubtypes(0)));
            break;
         case MAP:
            if (type.getSubtypesCount() != 2) {
               throw new FileFormatException("MAP type should contain exactly two subtypes but has " + type.getSubtypesCount());
            }

            result = TypeDescription.createMap(convertTypeFromProtobuf(types, type.getSubtypes(0)), convertTypeFromProtobuf(types, type.getSubtypes(1)));
            break;
         case UNION:
            if (type.getSubtypesCount() == 0) {
               throw new FileFormatException("UNION type should contain at least one subtype but has none");
            }

            result = TypeDescription.createUnion();

            for(int f = 0; f < type.getSubtypesCount(); ++f) {
               result.addUnionChild(convertTypeFromProtobuf(types, type.getSubtypes(f)));
            }
            break;
         case STRUCT:
            result = TypeDescription.createStruct();

            for(int f = 0; f < type.getSubtypesCount(); ++f) {
               String name = type.getFieldNames(f);
               name = name.startsWith("`") ? name : "`" + name + "`";
               String fieldName = ParserUtils.parseName(new ParserUtils.StringPosition(name));
               result.addField(fieldName, convertTypeFromProtobuf(types, type.getSubtypes(f)));
            }
            break;
         case BOOLEAN:
            result = TypeDescription.createBoolean();
            break;
         case BYTE:
            result = TypeDescription.createByte();
            break;
         case SHORT:
            result = TypeDescription.createShort();
            break;
         case INT:
            result = TypeDescription.createInt();
            break;
         case LONG:
            result = TypeDescription.createLong();
            break;
         case FLOAT:
            result = TypeDescription.createFloat();
            break;
         case DOUBLE:
            result = TypeDescription.createDouble();
            break;
         case STRING:
            result = TypeDescription.createString();
            break;
         case CHAR:
         case VARCHAR:
            result = type.getKind() == Kind.CHAR ? TypeDescription.createChar() : TypeDescription.createVarchar();
            if (type.hasMaximumLength()) {
               result.withMaxLength(type.getMaximumLength());
            }
            break;
         case BINARY:
            result = TypeDescription.createBinary();
            break;
         case TIMESTAMP:
            result = TypeDescription.createTimestamp();
            break;
         case TIMESTAMP_INSTANT:
            result = TypeDescription.createTimestampInstant();
            break;
         case DATE:
            result = TypeDescription.createDate();
            break;
         case DECIMAL:
            result = TypeDescription.createDecimal();
            if (type.hasScale()) {
               result.withScale(type.getScale());
            }

            if (type.hasPrecision()) {
               result.withPrecision(type.getPrecision());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown ORC type " + String.valueOf(type.getKind()));
      }

      for(int i = 0; i < type.getAttributesCount(); ++i) {
         OrcProto.StringPair pair = type.getAttributes(i);
         result.setAttribute(pair.getKey(), pair.getValue());
      }

      return result;
   }

   public static List convertProtoStripesToStripes(List stripes) {
      List<StripeInformation> result = new ArrayList(stripes.size());
      long previousStripeId = 0L;
      byte[][] previousKeys = null;
      long stripeId = 0L;

      for(OrcProto.StripeInformation stripeProto : stripes) {
         ReaderImpl.StripeInformationImpl stripe = new ReaderImpl.StripeInformationImpl(stripeProto, stripeId++, previousStripeId, previousKeys);
         result.add(stripe);
         previousStripeId = stripe.getEncryptionStripeId();
         previousKeys = stripe.getEncryptedLocalKeys();
      }

      return result;
   }

   public static String getSoftwareVersion(int writer, String version) {
      String base;
      switch (writer) {
         case 0 -> base = "ORC Java";
         case 1 -> base = "ORC C++";
         case 2 -> base = "Presto";
         case 3 -> base = "Scritchley Go";
         case 4 -> base = "Trino";
         case 5 -> base = "CUDF";
         default -> base = String.format("Unknown(%d)", writer);
      }

      return version == null ? base : base + " " + version;
   }

   public static String getOrcVersion() {
      Class<OrcFile> cls = OrcFile.class;

      try {
         label57: {
            InputStream is = cls.getResourceAsStream("/META-INF/maven/org.apache.orc/orc-core/pom.properties");

            String var4;
            label50: {
               try {
                  if (is != null) {
                     Properties p = new Properties();
                     p.load(is);
                     String version = p.getProperty("version", (String)null);
                     if (version != null) {
                        var4 = version;
                        break label50;
                     }
                  }
               } catch (Throwable var6) {
                  if (is != null) {
                     try {
                        is.close();
                     } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                     }
                  }

                  throw var6;
               }

               if (is != null) {
                  is.close();
               }
               break label57;
            }

            if (is != null) {
               is.close();
            }

            return var4;
         }
      } catch (IOException var7) {
      }

      Package aPackage = cls.getPackage();
      if (aPackage != null) {
         String version = aPackage.getImplementationVersion();
         if (version != null) {
            return version;
         }
      }

      return "unknown";
   }
}
