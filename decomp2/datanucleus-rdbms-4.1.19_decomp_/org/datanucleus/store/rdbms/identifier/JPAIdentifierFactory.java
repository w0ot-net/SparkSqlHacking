package org.datanucleus.store.rdbms.identifier;

import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.schema.naming.NamingCase;

public class JPAIdentifierFactory extends AbstractIdentifierFactory {
   public JPAIdentifierFactory(DatastoreAdapter dba, ClassLoaderResolver clr, Map props) {
      super(dba, clr, props);
   }

   public DatastoreIdentifier newTableIdentifier(AbstractMemberMetaData mmd) {
      String identifierName = null;
      String schemaName = null;
      String catalogName = null;
      AbstractMemberMetaData[] relatedMmds = null;
      if (mmd.getColumnMetaData().length > 0 && mmd.getColumnMetaData()[0].getName() != null) {
         identifierName = mmd.getColumnMetaData()[0].getName();
      } else if (mmd.hasContainer()) {
         if (mmd.getTable() != null) {
            String specifiedName = mmd.getTable();
            String[] parts = this.getIdentifierNamePartsFromName(specifiedName);
            if (parts != null) {
               catalogName = parts[0];
               schemaName = parts[1];
               identifierName = parts[2];
            }

            if (catalogName == null) {
               catalogName = mmd.getCatalog();
            }

            if (schemaName == null) {
               schemaName = mmd.getSchema();
            }
         } else {
            relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
            if (relatedMmds != null && relatedMmds[0].getTable() != null) {
               String specifiedName = relatedMmds[0].getTable();
               String[] parts = this.getIdentifierNamePartsFromName(specifiedName);
               if (parts != null) {
                  catalogName = parts[0];
                  schemaName = parts[1];
                  identifierName = parts[2];
               }

               if (catalogName == null) {
                  catalogName = relatedMmds[0].getCatalog();
               }

               if (schemaName == null) {
                  schemaName = relatedMmds[0].getSchema();
               }
            }
         }
      }

      if (schemaName == null && catalogName == null) {
         if (mmd.getParent() instanceof AbstractClassMetaData) {
            AbstractClassMetaData ownerCmd = (AbstractClassMetaData)mmd.getParent();
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
         if (mmd.getRelationType(this.clr) == RelationType.NONE) {
            identifierName = mmd.getAbstractClassMetaData().getEntityName() + this.getWordSeparator() + mmd.getName();
         } else {
            String ownerClass = mmd.getClassName(false);
            String otherClass = mmd.getTypeName();
            if (mmd.hasCollection()) {
               otherClass = mmd.getCollection().getElementType();
            } else if (mmd.hasArray()) {
               otherClass = mmd.getArray().getElementType();
            } else if (mmd.hasMap()) {
               otherClass = mmd.getMap().getValueType();
            }

            if (mmd.hasCollection() && relatedMmds != null && relatedMmds[0].hasCollection() && mmd.getMappedBy() != null) {
               ownerClass = relatedMmds[0].getClassName(false);
               otherClass = relatedMmds[0].getCollection().getElementType();
            }

            otherClass = otherClass.substring(otherClass.lastIndexOf(46) + 1);
            identifierName = ownerClass + this.getWordSeparator() + otherClass;
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

      if (catalogName != null) {
         catalogName = this.getIdentifierInAdapterCase(catalogName);
      }

      if (schemaName != null) {
         schemaName = this.getIdentifierInAdapterCase(schemaName);
      }

      if (identifierName == null) {
         String unique_name = cmd.getFullClassName().substring(cmd.getFullClassName().lastIndexOf(46) + 1);
         identifierName = unique_name;
      }

      DatastoreIdentifier identifier = this.newTableIdentifier(identifierName, catalogName, schemaName);
      return identifier;
   }

   public DatastoreIdentifier newReferenceFieldIdentifier(AbstractMemberMetaData refMetaData, AbstractClassMetaData implMetaData, DatastoreIdentifier implIdentifier, boolean embedded, FieldRole fieldRole) {
      String key = "[" + refMetaData.getFullFieldName() + "][" + implMetaData.getFullClassName() + "][" + implIdentifier.getName() + "]";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.references.get(key);
      if (identifier == null) {
         String referenceName = refMetaData.getName();
         String implementationName = implMetaData.getFullClassName();
         int dot = implementationName.lastIndexOf(46);
         if (dot > -1) {
            implementationName = implementationName.substring(dot + 1);
         }

         String name = referenceName + "." + implementationName + "." + implIdentifier.getName();
         String datastoreID = this.generateIdentifierNameForJavaName(name);
         String baseID = this.truncate(datastoreID, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN));
         identifier = new ColumnIdentifier(this, baseID);
         this.references.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newJoinTableFieldIdentifier(AbstractMemberMetaData ownerFmd, AbstractMemberMetaData relatedFmd, DatastoreIdentifier destinationId, boolean embedded, FieldRole fieldRole) {
      DatastoreIdentifier identifier = null;
      if (relatedFmd != null) {
         if (fieldRole == FieldRole.ROLE_OWNER) {
            identifier = this.newColumnIdentifier(relatedFmd.getName() + this.getWordSeparator() + destinationId.getName());
         } else if (fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT && fieldRole != FieldRole.ROLE_ARRAY_ELEMENT && fieldRole != FieldRole.ROLE_MAP_KEY && fieldRole != FieldRole.ROLE_MAP_VALUE) {
            identifier = this.newColumnIdentifier(destinationId.getName(), embedded, fieldRole, false);
         } else if (destinationId != null) {
            identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + destinationId.getName());
         } else if (fieldRole != FieldRole.ROLE_ARRAY_ELEMENT && fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT) {
            if (fieldRole == FieldRole.ROLE_MAP_KEY) {
               identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "KEY");
            } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
               identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "VALUE");
            }
         } else {
            identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "ELEMENT");
         }
      } else if (fieldRole == FieldRole.ROLE_OWNER) {
         identifier = this.newColumnIdentifier(ownerFmd.getClassName(false) + this.getWordSeparator() + destinationId.getName());
      } else if (fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT && fieldRole != FieldRole.ROLE_ARRAY_ELEMENT && fieldRole != FieldRole.ROLE_MAP_KEY && fieldRole != FieldRole.ROLE_MAP_VALUE) {
         identifier = this.newColumnIdentifier(destinationId.getName(), embedded, fieldRole, false);
      } else if (destinationId != null) {
         identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + destinationId.getName());
      } else if (fieldRole != FieldRole.ROLE_ARRAY_ELEMENT && fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT) {
         if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "KEY");
         } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
            identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "VALUE");
         }
      } else {
         identifier = this.newColumnIdentifier(ownerFmd.getName() + this.getWordSeparator() + "ELEMENT");
      }

      return identifier;
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
         return this.newColumnIdentifier(ownerFmd.getName() + ".IDX", embedded, fieldRole, false);
      } else {
         throw (new NucleusException("Column role " + fieldRole + " not supported by this method")).setFatal();
      }
   }

   public DatastoreIdentifier newDiscriminatorFieldIdentifier() {
      String name = "DTYPE";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newVersionFieldIdentifier() {
      String name = "VERSION";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newIndexFieldIdentifier(AbstractMemberMetaData mmd) {
      String name = mmd.getName() + this.getWordSeparator() + "ORDER";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newAdapterIndexFieldIdentifier() {
      String name = "IDX";
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

         for(int i = 0; i < javaName.length(); ++i) {
            char c = javaName.charAt(i);
            if (c >= 'A' && c <= 'Z' && this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
               s.append(c);
            } else if (c < 'A' || c > 'Z' || this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
               if (c < 'a' || c > 'z' || this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
                  if (c >= 'a' && c <= 'z' && this.namingCase != NamingCase.MIXED_CASE && this.namingCase != NamingCase.MIXED_CASE_QUOTED) {
                     s.append((char)(c - 32));
                  } else if ((c < '0' || c > '9') && c != '_') {
                     if (c == '.') {
                        s.append(this.getWordSeparator());
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
      String suffix = "";
      if (role == FieldRole.ROLE_NONE) {
         suffix = !embedded ? "_ID" : "";
      }

      return suffix;
   }
}
