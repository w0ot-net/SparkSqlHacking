package org.apache.orc.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.orc.Reader;
import org.apache.orc.TypeDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaEvolution {
   private final TypeDescription[] readerFileTypes;
   private final boolean[] readerIncluded;
   private final int readerColumnOffset;
   private final boolean[] fileIncluded;
   private final TypeDescription fileSchema;
   private final TypeDescription readerSchema;
   private boolean hasConversion;
   private boolean isOnlyImplicitConversion;
   private final boolean isAcid;
   final boolean isSchemaEvolutionCaseAware;
   private final boolean includeAcidColumns;
   private final boolean[] ppdSafeConversion;
   private final boolean positionalColumns;
   private static final Logger LOG = LoggerFactory.getLogger(SchemaEvolution.class);
   private static final Pattern missingMetadataPattern = Pattern.compile("_col\\d+");
   private static final List acidEventFieldNames = new ArrayList();

   public SchemaEvolution(TypeDescription fileSchema, TypeDescription readerSchema, Reader.Options options) {
      boolean allowMissingMetadata = options.getTolerateMissingSchema();
      boolean[] includedCols = options.getInclude();
      this.isSchemaEvolutionCaseAware = options.getIsSchemaEvolutionCaseAware();
      this.readerIncluded = includedCols == null ? null : Arrays.copyOf(includedCols, includedCols.length);
      this.fileIncluded = new boolean[fileSchema.getMaximumId() + 1];
      this.hasConversion = false;
      this.isOnlyImplicitConversion = true;
      this.fileSchema = fileSchema;
      readerSchema = readerSchema == null ? this.fileSchema : readerSchema;
      this.isAcid = checkAcidSchema(fileSchema);
      boolean readerSchemaIsAcid = checkAcidSchema(readerSchema);
      this.includeAcidColumns = options.getIncludeAcidColumns();
      this.readerColumnOffset = this.isAcid && !readerSchemaIsAcid ? acidEventFieldNames.size() : 0;
      if (this.isAcid && !readerSchemaIsAcid) {
         this.readerSchema = createEventSchema(readerSchema);
      } else {
         this.readerSchema = readerSchema;
      }

      if (this.readerIncluded != null && this.readerIncluded.length + this.readerColumnOffset != this.readerSchema.getMaximumId() + 1) {
         String var10002 = this.readerSchema.toJson();
         throw new IllegalArgumentException("Include vector the wrong length: " + var10002 + " with include length " + this.readerIncluded.length);
      } else {
         this.readerFileTypes = new TypeDescription[this.readerSchema.getMaximumId() + 1];
         int positionalLevels = 0;
         if (options.getForcePositionalEvolution()) {
            positionalLevels = this.isAcid ? 2 : options.getPositionalEvolutionLevel();
         } else if (!this.hasColumnNames(this.isAcid ? getBaseRow(fileSchema) : fileSchema) && !this.fileSchema.equals(this.readerSchema)) {
            if (!allowMissingMetadata) {
               throw new RuntimeException("Found that schema metadata is missing from file. This is likely caused by a writer earlier than HIVE-4243. Will not try to reconcile schemas");
            }

            Logger var10000 = LOG;
            String var10001 = String.valueOf(this.fileSchema);
            var10000.warn("Column names are missing from this file. This is caused by a writer earlier than HIVE-4243. The reader will reconcile schemas based on index. File type: " + var10001 + ", reader type: " + String.valueOf(this.readerSchema));
            positionalLevels = this.isAcid ? 2 : options.getPositionalEvolutionLevel();
         }

         this.buildConversion(fileSchema, this.readerSchema, positionalLevels);
         this.positionalColumns = options.getForcePositionalEvolution();
         this.ppdSafeConversion = this.populatePpdSafeConversion();
      }
   }

   /** @deprecated */
   @Deprecated
   public SchemaEvolution(TypeDescription fileSchema, boolean[] readerIncluded) {
      this(fileSchema, (TypeDescription)null, (boolean[])readerIncluded);
   }

   /** @deprecated */
   @Deprecated
   public SchemaEvolution(TypeDescription fileSchema, TypeDescription readerSchema, boolean[] readerIncluded) {
      this(fileSchema, readerSchema, (new Reader.Options(new Configuration())).include(readerIncluded));
   }

   private boolean hasColumnNames(TypeDescription fileSchema) {
      if (fileSchema.getCategory() != TypeDescription.Category.STRUCT) {
         return true;
      } else {
         for(String fieldName : fileSchema.getFieldNames()) {
            if (!missingMetadataPattern.matcher(fieldName).matches()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isSchemaEvolutionCaseAware() {
      return this.isSchemaEvolutionCaseAware;
   }

   public TypeDescription getReaderSchema() {
      return this.readerSchema;
   }

   public TypeDescription getReaderBaseSchema() {
      return this.isAcid ? getBaseRow(this.readerSchema) : this.readerSchema;
   }

   boolean isAcid() {
      return this.isAcid;
   }

   public boolean hasConversion() {
      return this.hasConversion;
   }

   public boolean isOnlyImplicitConversion() {
      return this.isOnlyImplicitConversion;
   }

   public TypeDescription getFileSchema() {
      return this.fileSchema;
   }

   public TypeDescription getFileType(TypeDescription readerType) {
      return this.getFileType(readerType.getId());
   }

   public TypeDescription getFileType(int id) {
      return this.readerFileTypes[id];
   }

   public boolean[] getReaderIncluded() {
      return this.readerIncluded;
   }

   public boolean[] getFileIncluded() {
      return this.fileIncluded;
   }

   public boolean getPositionalColumns() {
      return this.positionalColumns;
   }

   private boolean typesAreImplicitConversion(TypeDescription fileType, TypeDescription readerType) {
      switch (fileType.getCategory()) {
         case BYTE:
            if (readerType.getCategory().equals(TypeDescription.Category.SHORT) || readerType.getCategory().equals(TypeDescription.Category.INT) || readerType.getCategory().equals(TypeDescription.Category.LONG)) {
               return true;
            }
            break;
         case SHORT:
            if (readerType.getCategory().equals(TypeDescription.Category.INT) || readerType.getCategory().equals(TypeDescription.Category.LONG)) {
               return true;
            }
            break;
         case INT:
            if (readerType.getCategory().equals(TypeDescription.Category.LONG)) {
               return true;
            }
            break;
         case FLOAT:
            if (readerType.getCategory().equals(TypeDescription.Category.DOUBLE)) {
               return true;
            }
            break;
         case CHAR:
         case VARCHAR:
            if (readerType.getCategory().equals(TypeDescription.Category.STRING)) {
               return true;
            }

            if (readerType.getCategory().equals(TypeDescription.Category.CHAR) || readerType.getCategory().equals(TypeDescription.Category.VARCHAR)) {
               return fileType.getMaxLength() <= readerType.getMaxLength();
            }
      }

      return false;
   }

   public boolean isPPDSafeConversion(int fileColId) {
      if (!this.hasConversion()) {
         return true;
      } else {
         return fileColId >= 0 && fileColId < this.ppdSafeConversion.length && this.ppdSafeConversion[fileColId];
      }
   }

   private boolean[] populatePpdSafeConversion() {
      if (this.fileSchema != null && this.readerSchema != null && this.readerFileTypes != null) {
         boolean[] result = new boolean[this.fileSchema.getMaximumId() + 1];
         boolean safePpd = this.validatePPDConversion(this.fileSchema, this.readerSchema);
         result[this.fileSchema.getId()] = safePpd;
         return this.populatePpdSafeConversionForChildren(result, this.readerSchema.getChildren());
      } else {
         return null;
      }
   }

   private boolean[] populatePpdSafeConversionForChildren(boolean[] ppdSafeConversion, List children) {
      if (children != null) {
         for(TypeDescription child : children) {
            TypeDescription fileType = this.getFileType(child.getId());
            boolean safePpd = this.validatePPDConversion(fileType, child);
            if (fileType != null) {
               ppdSafeConversion[fileType.getId()] = safePpd;
            }

            this.populatePpdSafeConversionForChildren(ppdSafeConversion, child.getChildren());
         }
      }

      return ppdSafeConversion;
   }

   private boolean validatePPDConversion(TypeDescription fileType, TypeDescription readerType) {
      if (fileType == null) {
         return false;
      } else {
         if (fileType.getCategory().isPrimitive()) {
            if (fileType.getCategory().equals(readerType.getCategory())) {
               return fileType.getCategory() != TypeDescription.Category.DECIMAL || fileType.equals(readerType);
            }

            switch (fileType.getCategory()) {
               case BYTE:
                  if (readerType.getCategory().equals(TypeDescription.Category.SHORT) || readerType.getCategory().equals(TypeDescription.Category.INT) || readerType.getCategory().equals(TypeDescription.Category.LONG)) {
                     return true;
                  }
                  break;
               case SHORT:
                  if (readerType.getCategory().equals(TypeDescription.Category.INT) || readerType.getCategory().equals(TypeDescription.Category.LONG)) {
                     return true;
                  }
                  break;
               case INT:
                  if (readerType.getCategory().equals(TypeDescription.Category.LONG)) {
                     return true;
                  }
               case FLOAT:
               case CHAR:
               default:
                  break;
               case VARCHAR:
                  if (readerType.getCategory().equals(TypeDescription.Category.STRING)) {
                     return true;
                  }
                  break;
               case STRING:
                  if (readerType.getCategory().equals(TypeDescription.Category.VARCHAR)) {
                     return true;
                  }
            }
         }

         return false;
      }
   }

   public boolean includeReaderColumn(int readerId) {
      if (readerId == 0) {
         return true;
      } else if (this.isAcid) {
         if (readerId < this.readerColumnOffset) {
            return this.includeAcidColumns;
         } else {
            return this.readerIncluded == null || this.readerIncluded[readerId - this.readerColumnOffset];
         }
      } else {
         return this.readerIncluded == null || this.readerIncluded[readerId];
      }
   }

   void buildConversion(TypeDescription fileType, TypeDescription readerType, int positionalLevels) {
      if (this.includeReaderColumn(readerType.getId())) {
         boolean isOk = true;
         if (fileType.getCategory() == readerType.getCategory()) {
            switch (readerType.getCategory()) {
               case BYTE:
               case SHORT:
               case INT:
               case FLOAT:
               case STRING:
               case BOOLEAN:
               case LONG:
               case DOUBLE:
               case TIMESTAMP:
               case TIMESTAMP_INSTANT:
               case BINARY:
               case DATE:
                  break;
               case CHAR:
               case VARCHAR:
                  if (fileType.getMaxLength() != readerType.getMaxLength()) {
                     this.hasConversion = true;
                     if (!this.typesAreImplicitConversion(fileType, readerType)) {
                        this.isOnlyImplicitConversion = false;
                     }
                  }
                  break;
               case DECIMAL:
                  if (fileType.getPrecision() != readerType.getPrecision() || fileType.getScale() != readerType.getScale()) {
                     this.hasConversion = true;
                     this.isOnlyImplicitConversion = false;
                  }
                  break;
               case UNION:
               case MAP:
               case LIST:
                  List<TypeDescription> fileChildren = fileType.getChildren();
                  List<TypeDescription> readerChildren = readerType.getChildren();
                  if (fileChildren.size() == readerChildren.size()) {
                     for(int i = 0; i < fileChildren.size(); ++i) {
                        this.buildConversion((TypeDescription)fileChildren.get(i), (TypeDescription)readerChildren.get(i), positionalLevels - 1);
                     }
                  } else {
                     isOk = false;
                  }
                  break;
               case STRUCT:
                  List<TypeDescription> readerChildren = readerType.getChildren();
                  List<TypeDescription> fileChildren = fileType.getChildren();
                  if (fileChildren.size() != readerChildren.size()) {
                     this.hasConversion = true;
                     this.isOnlyImplicitConversion = false;
                  }

                  if (positionalLevels <= 0) {
                     List<String> readerFieldNames = readerType.getFieldNames();
                     List<String> fileFieldNames = fileType.getFieldNames();
                     Map<String, TypeDescription> fileTypesIdx;
                     if (this.isSchemaEvolutionCaseAware) {
                        fileTypesIdx = new HashMap();
                     } else {
                        fileTypesIdx = new CaseInsensitiveMap();
                     }

                     for(int i = 0; i < fileFieldNames.size(); ++i) {
                        String fileFieldName = (String)fileFieldNames.get(i);
                        fileTypesIdx.put(fileFieldName, (TypeDescription)fileChildren.get(i));
                     }

                     for(int i = 0; i < readerFieldNames.size(); ++i) {
                        String readerFieldName = (String)readerFieldNames.get(i);
                        TypeDescription readerField = (TypeDescription)readerChildren.get(i);
                        TypeDescription fileField = (TypeDescription)fileTypesIdx.get(readerFieldName);
                        if (fileField != null) {
                           this.buildConversion(fileField, readerField, 0);
                        }
                     }
                  } else {
                     int jointSize = Math.min(fileChildren.size(), readerChildren.size());

                     for(int i = 0; i < jointSize; ++i) {
                        this.buildConversion((TypeDescription)fileChildren.get(i), (TypeDescription)readerChildren.get(i), positionalLevels - 1);
                     }
                  }
                  break;
               default:
                  throw new IllegalArgumentException("Unknown type " + String.valueOf(readerType));
            }
         } else {
            isOk = ConvertTreeReaderFactory.canConvert(fileType, readerType);
            this.hasConversion = true;
            if (!this.typesAreImplicitConversion(fileType, readerType)) {
               this.isOnlyImplicitConversion = false;
            }
         }

         if (isOk) {
            this.readerFileTypes[readerType.getId()] = fileType;
            this.fileIncluded[fileType.getId()] = true;
         } else {
            throw new IllegalEvolutionException(String.format("ORC does not support type conversion from file type %s (%d) to reader type %s (%d)", fileType, fileType.getId(), readerType, readerType.getId()));
         }
      }
   }

   public static boolean checkAcidSchema(TypeDescription type) {
      if (type.getCategory().equals(TypeDescription.Category.STRUCT)) {
         List<String> rootFields = type.getFieldNames();
         if (rootFields.size() != acidEventFieldNames.size()) {
            return false;
         } else {
            for(int i = 0; i < rootFields.size(); ++i) {
               if (!((String)acidEventFieldNames.get(i)).equalsIgnoreCase((String)rootFields.get(i))) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static TypeDescription createEventSchema(TypeDescription typeDescr) {
      TypeDescription result = TypeDescription.createStruct().addField("operation", TypeDescription.createInt()).addField("originalTransaction", TypeDescription.createLong()).addField("bucket", TypeDescription.createInt()).addField("rowId", TypeDescription.createLong()).addField("currentTransaction", TypeDescription.createLong()).addField("row", typeDescr.clone());
      return result;
   }

   public static TypeDescription getBaseRow(TypeDescription typeDescription) {
      int ACID_ROW_OFFSET = 5;
      return (TypeDescription)typeDescription.getChildren().get(5);
   }

   static {
      acidEventFieldNames.add("operation");
      acidEventFieldNames.add("originalTransaction");
      acidEventFieldNames.add("bucket");
      acidEventFieldNames.add("rowId");
      acidEventFieldNames.add("currentTransaction");
      acidEventFieldNames.add("row");
   }

   public static class IllegalEvolutionException extends RuntimeException {
      public IllegalEvolutionException(String msg) {
         super(msg);
      }
   }

   private static class CaseInsensitiveMap extends HashMap {
      public Object put(String key, Object value) {
         return super.put(key.toLowerCase(), value);
      }

      public Object get(Object key) {
         return this.get((String)key);
      }

      public Object get(String key) {
         return super.get(key.toLowerCase());
      }
   }
}
