package org.datanucleus.store.rdbms.identifier;

import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.schema.naming.NamingCase;

public class DN2IdentifierFactory extends DNIdentifierFactory {
   public DN2IdentifierFactory(DatastoreAdapter dba, ClassLoaderResolver clr, Map props) {
      super(dba, clr, props);
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
            baseName = "ELEMENT";
         } else if (fieldRole == FieldRole.ROLE_ARRAY_ELEMENT) {
            baseName = "ELEMENT";
         } else if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            baseName = "KEY";
         } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
            baseName = "VALUE";
         } else {
            baseName = "UNKNOWN";
         }

         return this.newColumnIdentifier(baseName);
      }
   }

   public DatastoreIdentifier newForeignKeyFieldIdentifier(AbstractMemberMetaData ownerFmd, DatastoreIdentifier destinationId, boolean embedded, FieldRole fieldRole) {
      if (fieldRole == FieldRole.ROLE_OWNER) {
         return this.newColumnIdentifier(ownerFmd.getName() + "." + destinationId.getName(), embedded, fieldRole, false);
      } else if (fieldRole == FieldRole.ROLE_INDEX) {
         return this.newColumnIdentifier(ownerFmd.getName(), embedded, fieldRole, false);
      } else {
         throw (new NucleusException("Column role " + fieldRole + " not supported by this method")).setFatal();
      }
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
      String name = "IDX";
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(name);
      if (identifier == null) {
         identifier = new ColumnIdentifier(this, name);
         this.columns.put(name, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newAdapterIndexFieldIdentifier() {
      return this.newIndexFieldIdentifier((AbstractMemberMetaData)null);
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
}
