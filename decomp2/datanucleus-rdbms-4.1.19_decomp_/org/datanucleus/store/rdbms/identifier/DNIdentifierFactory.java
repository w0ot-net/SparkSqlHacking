package org.datanucleus.store.rdbms.identifier;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.schema.naming.NamingCase;

public class DNIdentifierFactory extends AbstractIdentifierFactory {
   protected String tablePrefix = null;
   protected String tableSuffix = null;

   public DNIdentifierFactory(DatastoreAdapter dba, ClassLoaderResolver clr, Map props) {
      super(dba, clr, props);
      if (props.containsKey("WordSeparator")) {
         this.wordSeparator = (String)props.get("WordSeparator");
      }

      this.tablePrefix = (String)props.get("TablePrefix");
      this.tableSuffix = (String)props.get("TableSuffix");
   }

   public DatastoreIdentifier newTableIdentifier(AbstractMemberMetaData fmd) {
      String identifierName = null;
      String schemaName = null;
      String catalogName = null;
      AbstractMemberMetaData[] relatedMmds = null;
      if (fmd.getColumnMetaData().length > 0 && fmd.getColumnMetaData()[0].getName() != null) {
         identifierName = fmd.getColumnMetaData()[0].getName();
      } else if (fmd.hasContainer()) {
         if (fmd.getTable() != null) {
            String specifiedName = fmd.getTable();
            String[] parts = this.getIdentifierNamePartsFromName(specifiedName);
            if (parts != null) {
               catalogName = parts[0];
               schemaName = parts[1];
               identifierName = parts[2];
            }

            if (catalogName == null) {
               catalogName = fmd.getCatalog();
            }

            if (schemaName == null) {
               schemaName = fmd.getSchema();
            }
         } else {
            relatedMmds = fmd.getRelatedMemberMetaData(this.clr);
            if (relatedMmds != null && relatedMmds[0].getTable() != null) {
               String specifiedName = relatedMmds[0].getTable();
               String[] parts = this.getIdentifierNamePartsFromName(specifiedName);
               if (parts != null) {
                  catalogName = parts[0];
                  schemaName = parts[1];
                  identifierName = parts[2];
               }

               if (catalogName == null) {
                  catalogName = fmd.getCatalog();
               }

               if (schemaName == null) {
                  schemaName = fmd.getSchema();
               }
            }
         }
      }

      if (schemaName == null && catalogName == null) {
         if (fmd.getParent() instanceof AbstractClassMetaData) {
            AbstractClassMetaData ownerCmd = (AbstractClassMetaData)fmd.getParent();
            if (this.dba.supportsOption("CatalogInTableDefinition")) {
               catalogName = ownerCmd.getCatalog();
            }

            if (this.dba.supportsOption("SchemaInTableDefinition")) {
               schemaName = ownerCmd.getSchema();
            }
         }

         if (schemaName == null && catalogName == null) {
            if (this.dba.supportsOption("CatalogInTableDefinition")) {
               catalogName = this.defaultCatalogName;
            }

            if (this.dba.supportsOption("SchemaInTableDefinition")) {
               schemaName = this.defaultSchemaName;
            }
         }
      }

      if (catalogName != null) {
         catalogName = this.getIdentifierInAdapterCase(catalogName);
      }

      if (schemaName != null) {
         schemaName = this.getIdentifierInAdapterCase(schemaName);
      }

      if (identifierName == null) {
         String fieldNameBasis = fmd.getFullFieldName();
         if (relatedMmds != null && relatedMmds[0].getMappedBy() != null) {
            fieldNameBasis = relatedMmds[0].getFullFieldName();
         }

         ArrayList name_parts = new ArrayList();
         StringTokenizer tokens = new StringTokenizer(fieldNameBasis, ".");

         while(tokens.hasMoreTokens()) {
            name_parts.add(tokens.nextToken());
         }

         ListIterator li = name_parts.listIterator(name_parts.size());
         String unique_name = (String)li.previous();
         String full_name = (li.hasPrevious() ? li.previous() + this.getWordSeparator() : "") + unique_name;
         identifierName = "";
         if (this.tablePrefix != null && this.tablePrefix.length() > 0) {
            identifierName = this.tablePrefix;
         }

         identifierName = identifierName + full_name;
         if (this.tableSuffix != null && this.tableSuffix.length() > 0) {
            identifierName = identifierName + this.tableSuffix;
         }
      }

      DatastoreIdentifier identifier = this.newTableIdentifier(identifierName, catalogName, schemaName);
      return identifier;
   }

   public DatastoreIdentifier newTableIdentifier(AbstractClassMetaData cmd) {
      String identifierName = null;
      String schemaName = null;
      String catalogName = null;
      String specifiedName = cmd.getTable();
      String[] parts = this.getIdentifierNamePartsFromName(specifiedName);
      if (parts != null) {
         catalogName = parts[0];
         schemaName = parts[1];
         identifierName = parts[2];
      }

      if (schemaName == null && catalogName == null) {
         if (this.dba.supportsOption("CatalogInTableDefinition")) {
            catalogName = cmd.getCatalog();
         }

         if (this.dba.supportsOption("SchemaInTableDefinition")) {
            schemaName = cmd.getSchema();
         }

         if (schemaName == null && catalogName == null) {
            if (this.dba.supportsOption("CatalogInTableDefinition")) {
               catalogName = this.defaultCatalogName;
            }

            if (this.dba.supportsOption("SchemaInTableDefinition")) {
               schemaName = this.defaultSchemaName;
            }
         }
      }

      if (identifierName == null) {
         String unique_name = cmd.getFullClassName().substring(cmd.getFullClassName().lastIndexOf(46) + 1);
         identifierName = "";
         if (this.tablePrefix != null && this.tablePrefix.length() > 0) {
            identifierName = this.tablePrefix;
         }

         identifierName = identifierName + unique_name;
         if (this.tableSuffix != null && this.tableSuffix.length() > 0) {
            identifierName = identifierName + this.tableSuffix;
         }
      }

      if (catalogName != null) {
         catalogName = this.getIdentifierInAdapterCase(catalogName);
      }

      if (schemaName != null) {
         schemaName = this.getIdentifierInAdapterCase(schemaName);
      }

      DatastoreIdentifier identifier = this.newTableIdentifier(identifierName, catalogName, schemaName);
      return identifier;
   }

   public DatastoreIdentifier newReferenceFieldIdentifier(AbstractMemberMetaData refMetaData, AbstractClassMetaData implMetaData, DatastoreIdentifier implIdentifier, boolean embedded, FieldRole fieldRole) {
      DatastoreIdentifier identifier = null;
      String key = "[" + refMetaData.getFullFieldName() + "][" + implMetaData.getFullClassName() + "][" + implIdentifier.getName() + "]";
      identifier = (DatastoreIdentifier)this.references.get(key);
      if (identifier == null) {
         String referenceName = refMetaData.getName();
         String implementationName = implMetaData.getFullClassName();
         int dot = implementationName.lastIndexOf(46);
         if (dot > -1) {
            implementationName = implementationName.substring(dot + 1);
         }

         String name = referenceName + "." + implementationName + "." + implIdentifier.getName();
         String suffix = this.getColumnIdentifierSuffix(fieldRole, embedded);
         String datastoreID = this.generateIdentifierNameForJavaName(name);
         String baseID = this.truncate(datastoreID, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN) - suffix.length());
         identifier = new ColumnIdentifier(this, baseID + suffix);
         this.references.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newJoinTableFieldIdentifier(AbstractMemberMetaData ownerFmd, AbstractMemberMetaData relatedFmd, DatastoreIdentifier destinationId, boolean embedded, FieldRole fieldRole) {
      if (destinationId != null) {
         RelationType relType = ownerFmd.getRelationType(this.clr);
         if (relType == RelationType.MANY_TO_MANY_BI && ownerFmd.hasCollection() && ownerFmd.getMappedBy() != null) {
            if (fieldRole == FieldRole.ROLE_COLLECTION_ELEMENT) {
               fieldRole = FieldRole.ROLE_OWNER;
            } else if (fieldRole == FieldRole.ROLE_OWNER) {
               fieldRole = FieldRole.ROLE_COLLECTION_ELEMENT;
            }
         }

         return this.newColumnIdentifier(destinationId.getName(), embedded, fieldRole, false);
      } else {
         String baseName = null;
         if (fieldRole == FieldRole.ROLE_COLLECTION_ELEMENT) {
            String elementType = ownerFmd.getCollection().getElementType();
            baseName = elementType.substring(elementType.lastIndexOf(46) + 1);
         } else if (fieldRole == FieldRole.ROLE_ARRAY_ELEMENT) {
            String elementType = ownerFmd.getArray().getElementType();
            baseName = elementType.substring(elementType.lastIndexOf(46) + 1);
         } else if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            String keyType = ownerFmd.getMap().getKeyType();
            baseName = keyType.substring(keyType.lastIndexOf(46) + 1);
         } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
            String valueType = ownerFmd.getMap().getValueType();
            baseName = valueType.substring(valueType.lastIndexOf(46) + 1);
         } else {
            baseName = "UNKNOWN";
         }

         return this.newColumnIdentifier(baseName, embedded, fieldRole, false);
      }
   }

   public DatastoreIdentifier newForeignKeyFieldIdentifier(AbstractMemberMetaData ownerFmd, AbstractMemberMetaData relatedFmd, DatastoreIdentifier destinationId, boolean embedded, FieldRole fieldRole) {
      if (relatedFmd != null) {
         if (fieldRole == FieldRole.ROLE_OWNER) {
            return this.newColumnIdentifier(relatedFmd.getName() + "." + destinationId.getName(), embedded, fieldRole, false);
         } else if (fieldRole == FieldRole.ROLE_INDEX) {
            return this.newColumnIdentifier(relatedFmd.getName() + "." + destinationId.getName(), embedded, fieldRole, false);
         } else {
            throw (new NucleusException("Column role " + fieldRole + " not supported by this method")).setFatal();
         }
      } else if (fieldRole == FieldRole.ROLE_OWNER) {
         return this.newColumnIdentifier(ownerFmd.getName() + "." + destinationId.getName(), embedded, fieldRole, false);
      } else if (fieldRole == FieldRole.ROLE_INDEX) {
         return this.newColumnIdentifier(ownerFmd.getName() + ".INTEGER", embedded, fieldRole, false);
      } else {
         throw (new NucleusException("Column role " + fieldRole + " not supported by this method")).setFatal();
      }
   }

   public DatastoreIdentifier newDiscriminatorFieldIdentifier() {
      String name = "DISCRIMINATOR";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newVersionFieldIdentifier() {
      String name = "OPT_VERSION";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newAdapterIndexFieldIdentifier() {
      String name = "ADPT_PK_IDX";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newIndexFieldIdentifier(AbstractMemberMetaData mmd) {
      String name = "INTEGER_IDX";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public String generateIdentifierNameForJavaName(String javaName) {
      if (javaName == null) {
         return null;
      } else {
         StringBuilder s = new StringBuilder();
         char prev = 0;

         for(int i = 0; i < javaName.length(); ++i) {
            char c = javaName.charAt(i);
            if (c >= 'A' && c <= 'Z' && this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
               if (prev >= 'a' && prev <= 'z') {
                  s.append(this.wordSeparator);
               }

               s.append(c);
            } else if (c < 'A' || c > 'Z' || this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
               if (c < 'a' || c > 'z' || this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
                  if (c >= 'a' && c <= 'z' && this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
                     s.append((char)(c - 32));
                  } else if ((c < '0' || c > '9') && c != '_') {
                     if (c == '.') {
                        s.append(this.wordSeparator);
                     } else {
                        String cval = "000" + Integer.toHexString(c);
                        s.append(cval.substring(cval.length() - (c > 255 ? 4 : 2)));
                     }
                  } else {
                     s.append(c);
                  }
               } else {
                  s.append(c);
               }
            } else {
               s.append(c);
            }

            prev = c;
         }

         while(s.length() > 0 && s.charAt(0) == '_') {
            s.deleteCharAt(0);
         }

         if (s.length() == 0) {
            throw new IllegalArgumentException("Illegal Java identifier: " + javaName);
         } else {
            return s.toString();
         }
      }
   }

   protected String getColumnIdentifierSuffix(FieldRole role, boolean embedded) {
      String suffix;
      if (role == FieldRole.ROLE_OWNER) {
         suffix = !embedded ? "_OID" : "_OWN";
      } else if (role != FieldRole.ROLE_FIELD && role != FieldRole.ROLE_COLLECTION_ELEMENT && role != FieldRole.ROLE_ARRAY_ELEMENT) {
         if (role == FieldRole.ROLE_MAP_KEY) {
            suffix = !embedded ? "_KID" : "_KEY";
         } else if (role == FieldRole.ROLE_MAP_VALUE) {
            suffix = !embedded ? "_VID" : "_VAL";
         } else if (role == FieldRole.ROLE_INDEX) {
            suffix = !embedded ? "_XID" : "_IDX";
         } else {
            suffix = !embedded ? "_ID" : "";
         }
      } else {
         suffix = !embedded ? "_EID" : "_ELE";
      }

      return suffix;
   }
}
