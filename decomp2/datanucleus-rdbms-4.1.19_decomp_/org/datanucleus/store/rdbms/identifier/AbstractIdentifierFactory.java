package org.datanucleus.store.rdbms.identifier;

import java.util.Map;
import java.util.WeakHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.exceptions.TooManyForeignKeysException;
import org.datanucleus.store.rdbms.exceptions.TooManyIndicesException;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.schema.naming.NamingCase;
import org.datanucleus.store.schema.naming.NamingFactory;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractIdentifierFactory implements IdentifierFactory {
   public static final int CASE_PRESERVE = 1;
   public static final int CASE_UPPER = 2;
   public static final int CASE_LOWER = 3;
   private static final int HASH_LENGTH = 4;
   private static final int HASH_RANGE = calculateHashMax();
   protected NamingFactory namingFactory;
   protected DatastoreAdapter dba;
   protected ClassLoaderResolver clr;
   protected NamingCase namingCase;
   protected String quoteString;
   protected String wordSeparator = "_";
   protected Map tables = new WeakHashMap();
   protected Map columns = new WeakHashMap();
   protected Map foreignkeys = new WeakHashMap();
   protected Map indexes = new WeakHashMap();
   protected Map candidates = new WeakHashMap();
   protected Map primarykeys = new WeakHashMap();
   protected Map sequences = new WeakHashMap();
   protected Map references = new WeakHashMap();
   protected String defaultCatalogName = null;
   protected String defaultSchemaName = null;

   private static final int calculateHashMax() {
      int hm = 1;

      for(int i = 0; i < 4; ++i) {
         hm *= 36;
      }

      return hm;
   }

   public AbstractIdentifierFactory(DatastoreAdapter dba, ClassLoaderResolver clr, Map props) {
      this.dba = dba;
      this.clr = clr;
      this.quoteString = dba.getIdentifierQuoteString();
      int userIdentifierCase = 2;
      if (props.containsKey("RequiredCase")) {
         String requiredCase = (String)props.get("RequiredCase");
         if (requiredCase.equalsIgnoreCase("UPPERCASE")) {
            userIdentifierCase = 2;
         } else if (requiredCase.equalsIgnoreCase("lowercase")) {
            userIdentifierCase = 3;
         } else if (requiredCase.equalsIgnoreCase("MixedCase")) {
            userIdentifierCase = 1;
         }
      }

      if (userIdentifierCase == 2) {
         if (dba.supportsOption("UpperCaseIdentifiers")) {
            this.namingCase = NamingCase.UPPER_CASE;
         } else if (dba.supportsOption("UpperCaseQuotedIdentifiers")) {
            this.namingCase = NamingCase.UPPER_CASE_QUOTED;
         } else if (!dba.supportsOption("MixedCaseIdentifiers") && !dba.supportsOption("MixedCaseSensitiveIdentifiers")) {
            if (!dba.supportsOption("MixedCaseQuotedIdentifiers") && !dba.supportsOption("MixedCaseQuotedSensitiveIdentifiers")) {
               if (dba.supportsOption("LowerCaseIdentifiers")) {
                  this.namingCase = NamingCase.LOWER_CASE;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"UPPERCASE", "LOWERCASE"}));
               } else {
                  if (!dba.supportsOption("LowerCaseQuotedIdentifiers")) {
                     throw (new NucleusUserException(Localiser.msg("039002", new Object[]{"UPPERCASE"}))).setFatal();
                  }

                  this.namingCase = NamingCase.LOWER_CASE_QUOTED;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"UPPERCASE", "LOWERCASEQUOTED"}));
               }
            } else {
               this.namingCase = NamingCase.UPPER_CASE_QUOTED;
            }
         } else {
            this.namingCase = NamingCase.UPPER_CASE;
         }
      } else if (userIdentifierCase == 3) {
         if (dba.supportsOption("LowerCaseIdentifiers")) {
            this.namingCase = NamingCase.LOWER_CASE;
         } else if (dba.supportsOption("LowerCaseQuotedIdentifiers")) {
            this.namingCase = NamingCase.LOWER_CASE_QUOTED;
         } else if (!dba.supportsOption("MixedCaseIdentifiers") && !dba.supportsOption("MixedCaseSensitiveIdentifiers")) {
            if (!dba.supportsOption("MixedCaseQuotedIdentifiers") && !dba.supportsOption("MixedCaseQuotedSensitiveIdentifiers")) {
               if (dba.supportsOption("UpperCaseIdentifiers")) {
                  this.namingCase = NamingCase.UPPER_CASE;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"LOWERCASE", "UPPERCASE"}));
               } else {
                  if (!dba.supportsOption("UpperCaseQuotedIdentifiers")) {
                     throw (new NucleusUserException(Localiser.msg("039002", new Object[]{"LOWERCASE"}))).setFatal();
                  }

                  this.namingCase = NamingCase.UPPER_CASE_QUOTED;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"LOWERCASE", "UPPERCASEQUOTED"}));
               }
            } else {
               this.namingCase = NamingCase.LOWER_CASE_QUOTED;
            }
         } else {
            this.namingCase = NamingCase.LOWER_CASE;
         }
      } else {
         if (userIdentifierCase != 1) {
            throw (new NucleusUserException(Localiser.msg("039000", (long)userIdentifierCase))).setFatal();
         }

         if (!dba.supportsOption("MixedCaseIdentifiers") && !dba.supportsOption("MixedCaseSensitiveIdentifiers")) {
            if (!dba.supportsOption("MixedCaseQuotedIdentifiers") && !dba.supportsOption("MixedCaseQuotedSensitiveIdentifiers")) {
               if (dba.supportsOption("LowerCaseIdentifiers")) {
                  this.namingCase = NamingCase.LOWER_CASE;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"MIXEDCASE", "LOWERCASE"}));
               } else if (dba.supportsOption("LowerCaseQuotedIdentifiers")) {
                  this.namingCase = NamingCase.LOWER_CASE_QUOTED;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"MIXEDCASE", "LOWERCASEQUOTED"}));
               } else if (dba.supportsOption("UpperCaseIdentifiers")) {
                  this.namingCase = NamingCase.UPPER_CASE;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"MIXEDCASE", "UPPERCASE"}));
               } else {
                  if (!dba.supportsOption("UpperCaseQuotedIdentifiers")) {
                     throw (new NucleusUserException(Localiser.msg("039002", new Object[]{"MIXEDCASE"}))).setFatal();
                  }

                  this.namingCase = NamingCase.UPPER_CASE_QUOTED;
                  NucleusLogger.PERSISTENCE.warn(Localiser.msg("039001", new Object[]{"MIXEDCASE", "UPPERCASEQUOTED"}));
               }
            } else {
               this.namingCase = NamingCase.MIXED_CASE_QUOTED;
            }
         } else {
            this.namingCase = NamingCase.MIXED_CASE;
         }
      }

      if (props.containsKey("DefaultCatalog")) {
         this.defaultCatalogName = this.getIdentifierInAdapterCase((String)props.get("DefaultCatalog"));
      }

      if (props.containsKey("DefaultSchema")) {
         this.defaultSchemaName = this.getIdentifierInAdapterCase((String)props.get("DefaultSchema"));
      }

      if (props.containsKey("NamingFactory")) {
         this.namingFactory = (NamingFactory)props.get("NamingFactory");
      }

   }

   public DatastoreAdapter getDatastoreAdapter() {
      return this.dba;
   }

   public NamingCase getNamingCase() {
      return this.namingCase;
   }

   public String getWordSeparator() {
      return this.wordSeparator;
   }

   protected String truncate(String identifier, int length) {
      if (length < 0) {
         return identifier;
      } else if (identifier.length() > length) {
         if (length < 4) {
            throw new IllegalArgumentException("The length argument (=" + length + ") is less than HASH_LENGTH(=" + 4 + ")!");
         } else {
            int tailIndex = length - 4;
            int tailHash = identifier.hashCode();
            if (tailHash < 0) {
               tailHash *= -1;
            }

            tailHash %= HASH_RANGE;
            String suffix = Integer.toString(tailHash, 36);
            if (suffix.length() > 4) {
               throw new IllegalStateException("Calculated hash \"" + suffix + "\" has more characters than defined by HASH_LENGTH (=" + 4 + ")! This should never happen!");
            } else {
               if (suffix.length() < 4) {
                  StringBuilder sb = new StringBuilder(4);
                  sb.append(suffix);

                  while(sb.length() < 4) {
                     sb.insert(0, '0');
                  }

                  suffix = sb.toString();
               }

               return identifier.substring(0, tailIndex) + suffix;
            }
         }
      } else {
         return identifier;
      }
   }

   public String getIdentifierInAdapterCase(String identifier) {
      if (identifier == null) {
         return null;
      } else {
         StringBuilder id = new StringBuilder();
         if ((this.namingCase == NamingCase.LOWER_CASE_QUOTED || this.namingCase == NamingCase.MIXED_CASE_QUOTED || this.namingCase == NamingCase.UPPER_CASE_QUOTED) && !identifier.startsWith(this.quoteString)) {
            id.append(this.quoteString);
         }

         if (this.namingCase != NamingCase.LOWER_CASE && this.namingCase != NamingCase.LOWER_CASE_QUOTED) {
            if (this.namingCase != NamingCase.UPPER_CASE && this.namingCase != NamingCase.UPPER_CASE_QUOTED) {
               id.append(identifier);
            } else {
               id.append(identifier.toUpperCase());
            }
         } else {
            id.append(identifier.toLowerCase());
         }

         if ((this.namingCase == NamingCase.LOWER_CASE_QUOTED || this.namingCase == NamingCase.MIXED_CASE_QUOTED || this.namingCase == NamingCase.UPPER_CASE_QUOTED) && !identifier.endsWith(this.quoteString)) {
            id.append(this.quoteString);
         }

         return id.toString();
      }
   }

   public DatastoreIdentifier newIdentifier(IdentifierType identifierType, String name) {
      DatastoreIdentifier identifier = null;
      String key = name.replace(this.quoteString, "");
      if (identifierType == IdentifierType.TABLE) {
         identifier = (DatastoreIdentifier)this.tables.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new TableIdentifier(this, sqlIdentifier);
            this.setCatalogSchemaForTable((TableIdentifier)identifier);
            this.tables.put(key, identifier);
         }
      } else if (identifierType == IdentifierType.COLUMN) {
         identifier = (DatastoreIdentifier)this.columns.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new ColumnIdentifier(this, sqlIdentifier);
            this.columns.put(key, identifier);
         }
      } else if (identifierType == IdentifierType.FOREIGN_KEY) {
         identifier = (DatastoreIdentifier)this.foreignkeys.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new ForeignKeyIdentifier(this, sqlIdentifier);
            this.foreignkeys.put(key, identifier);
         }
      } else if (identifierType == IdentifierType.INDEX) {
         identifier = (DatastoreIdentifier)this.indexes.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new IndexIdentifier(this, sqlIdentifier);
            this.indexes.put(key, identifier);
         }
      } else if (identifierType == IdentifierType.CANDIDATE_KEY) {
         identifier = (DatastoreIdentifier)this.candidates.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new CandidateKeyIdentifier(this, sqlIdentifier);
            this.candidates.put(key, identifier);
         }
      } else if (identifierType == IdentifierType.PRIMARY_KEY) {
         identifier = (DatastoreIdentifier)this.primarykeys.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new PrimaryKeyIdentifier(this, sqlIdentifier);
            this.primarykeys.put(key, identifier);
         }
      } else {
         if (identifierType != IdentifierType.SEQUENCE) {
            throw (new NucleusException("identifier type " + identifierType + " not supported by this factory method")).setFatal();
         }

         identifier = (DatastoreIdentifier)this.sequences.get(key);
         if (identifier == null) {
            String sqlIdentifier = this.generateIdentifierNameForJavaName(key);
            sqlIdentifier = this.truncate(sqlIdentifier, this.dba.getDatastoreIdentifierMaxLength(identifierType));
            identifier = new SequenceIdentifier(this, sqlIdentifier);
            this.sequences.put(key, identifier);
         }
      }

      return identifier;
   }

   public DatastoreIdentifier newIdentifier(DatastoreIdentifier identifier, String suffix) {
      String newId = identifier.getName() + this.getWordSeparator() + suffix;
      if (identifier instanceof TableIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.TABLE));
         TableIdentifier tableIdentifier = new TableIdentifier(this, newId);
         this.setCatalogSchemaForTable(tableIdentifier);
         return tableIdentifier;
      } else if (identifier instanceof ColumnIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN));
         return new ColumnIdentifier(this, newId);
      } else if (identifier instanceof ForeignKeyIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.FOREIGN_KEY));
         return new ForeignKeyIdentifier(this, newId);
      } else if (identifier instanceof IndexIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.INDEX));
         return new IndexIdentifier(this, newId);
      } else if (identifier instanceof CandidateKeyIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.CANDIDATE_KEY));
         return new CandidateKeyIdentifier(this, newId);
      } else if (identifier instanceof PrimaryKeyIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.PRIMARY_KEY));
         return new PrimaryKeyIdentifier(this, newId);
      } else if (identifier instanceof SequenceIdentifier) {
         newId = this.truncate(newId, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.SEQUENCE));
         return new SequenceIdentifier(this, newId);
      } else {
         return null;
      }
   }

   public DatastoreIdentifier newTableIdentifier(String identifierName) {
      String key = identifierName.replace(this.quoteString, "");
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.tables.get(key);
      if (identifier == null) {
         String baseID = this.truncate(key, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.TABLE));
         identifier = new TableIdentifier(this, baseID);
         this.setCatalogSchemaForTable((TableIdentifier)identifier);
         this.tables.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newTableIdentifier(String identifierName, String catalogName, String schemaName) {
      String tableName = identifierName.replace(this.quoteString, "");
      String key = (StringUtils.isWhitespace(catalogName) ? "" : catalogName + ".") + (StringUtils.isWhitespace(schemaName) ? "" : schemaName + ".") + tableName;
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.tables.get(key);
      if (identifier == null) {
         String baseID = this.truncate(tableName, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.TABLE));
         identifier = new TableIdentifier(this, baseID);
         if (catalogName == null && schemaName == null) {
            this.setCatalogSchemaForTable((TableIdentifier)identifier);
         } else {
            if (catalogName != null) {
               identifier.setCatalogName(catalogName);
            }

            if (schemaName != null) {
               identifier.setSchemaName(schemaName);
            }
         }

         this.tables.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newColumnIdentifier(String identifierName) {
      String key = identifierName.replace(this.quoteString, "");
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.columns.get(key);
      if (identifier == null) {
         String baseID = this.truncate(key, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN));
         identifier = new ColumnIdentifier(this, baseID);
         this.columns.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newColumnIdentifier(String javaName, boolean embedded, FieldRole fieldRole, boolean custom) {
      DatastoreIdentifier identifier = null;
      String key = "[" + (javaName == null ? "" : javaName) + "][" + embedded + "][" + fieldRole;
      identifier = (DatastoreIdentifier)this.columns.get(key);
      if (identifier == null) {
         if (custom) {
            String baseID = this.truncate(javaName, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN));
            identifier = new ColumnIdentifier(this, baseID);
         } else {
            String suffix = this.getColumnIdentifierSuffix(fieldRole, embedded);
            String datastoreID = this.generateIdentifierNameForJavaName(javaName);
            String baseID = this.truncate(datastoreID, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.COLUMN) - suffix.length());
            identifier = new ColumnIdentifier(this, baseID + suffix);
         }

         this.columns.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newSequenceIdentifier(String sequenceName) {
      DatastoreIdentifier identifier = (DatastoreIdentifier)this.sequences.get(sequenceName);
      if (identifier == null) {
         String baseID = this.truncate(sequenceName, this.dba.getDatastoreIdentifierMaxLength(IdentifierType.SEQUENCE));
         identifier = new ColumnIdentifier(this, baseID);
         this.sequences.put(sequenceName, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newPrimaryKeyIdentifier(Table table) {
      DatastoreIdentifier identifier = null;
      String key = table.getIdentifier().toString();
      identifier = (DatastoreIdentifier)this.primarykeys.get(key);
      if (identifier == null) {
         String suffix = this.getWordSeparator() + "PK";
         int maxLength = this.dba.getDatastoreIdentifierMaxLength(IdentifierType.PRIMARY_KEY);
         String baseID = this.truncate(table.getIdentifier().getName(), maxLength - suffix.length());
         identifier = new PrimaryKeyIdentifier(this, baseID + suffix);
         this.primarykeys.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newCandidateKeyIdentifier(Table table, int seq) {
      DatastoreIdentifier identifier = null;
      String key = "[" + table.getIdentifier().toString() + "][" + seq + "]";
      identifier = (DatastoreIdentifier)this.candidates.get(key);
      if (identifier == null) {
         String suffix = this.getWordSeparator() + "U" + seq;
         int maxLength = this.dba.getDatastoreIdentifierMaxLength(IdentifierType.CANDIDATE_KEY);
         String baseID = this.truncate(table.getIdentifier().getName(), maxLength - suffix.length());
         identifier = new CandidateKeyIdentifier(this, baseID + suffix);
         this.candidates.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newForeignKeyIdentifier(Table table, int seq) {
      DatastoreIdentifier identifier = null;
      String key = "[" + table.getIdentifier().toString() + "][" + seq + "]";
      identifier = (DatastoreIdentifier)this.foreignkeys.get(key);
      if (identifier == null) {
         String suffix = this.getWordSeparator() + "FK";
         if (seq < 10) {
            suffix = suffix + "" + (char)(48 + seq);
         } else {
            if (seq >= this.dba.getMaxForeignKeys()) {
               throw new TooManyForeignKeysException(this.dba, table.toString());
            }

            suffix = suffix + Integer.toHexString(65 + seq);
         }

         int maxLength = this.dba.getDatastoreIdentifierMaxLength(IdentifierType.FOREIGN_KEY);
         String baseID = this.truncate(table.getIdentifier().getName(), maxLength - suffix.length());
         identifier = new ForeignKeyIdentifier(this, baseID + suffix);
         this.foreignkeys.put(key, identifier);
      }

      return identifier;
   }

   public DatastoreIdentifier newIndexIdentifier(Table table, boolean isUnique, int seq) {
      DatastoreIdentifier identifier = null;
      String key = "[" + table.getIdentifier().toString() + "][" + isUnique + "][" + seq + "]";
      identifier = (DatastoreIdentifier)this.indexes.get(key);
      if (identifier == null) {
         String suffix = this.getWordSeparator() + (isUnique ? "U" : "N");
         if (seq >= this.dba.getMaxIndexes()) {
            throw new TooManyIndicesException(this.dba, table.toString());
         }

         suffix = suffix + String.valueOf(48 + seq);
         int maxLength = this.dba.getDatastoreIdentifierMaxLength(IdentifierType.INDEX);
         String baseID = this.truncate(table.getIdentifier().getName(), maxLength - suffix.length());
         identifier = new IndexIdentifier(this, baseID + suffix);
         this.indexes.put(key, identifier);
      }

      return identifier;
   }

   protected abstract String getColumnIdentifierSuffix(FieldRole var1, boolean var2);

   protected abstract String generateIdentifierNameForJavaName(String var1);

   protected void setCatalogSchemaForTable(TableIdentifier identifier) {
      String catalogName = identifier.getCatalogName();
      String schemaName = identifier.getSchemaName();
      if (schemaName == null && catalogName == null) {
         if (this.dba.supportsOption("CatalogInTableDefinition")) {
            identifier.setCatalogName(this.defaultCatalogName);
         }

         if (this.dba.supportsOption("SchemaInTableDefinition")) {
            identifier.setSchemaName(this.defaultSchemaName);
         }
      }

   }

   protected String[] getIdentifierNamePartsFromName(String name) {
      if (name != null) {
         String[] names = new String[3];
         if (name.indexOf(46) < 0) {
            names[0] = null;
            names[1] = null;
            names[2] = name;
         } else {
            String[] specifiedNameParts = StringUtils.split(name, ".");
            int currentPartIndex = specifiedNameParts.length - 1;
            names[2] = specifiedNameParts[currentPartIndex--];
            if (this.dba.supportsOption("SchemaInTableDefinition") && currentPartIndex >= 0) {
               names[1] = specifiedNameParts[currentPartIndex--];
            }

            if (this.dba.supportsOption("CatalogInTableDefinition") && currentPartIndex >= 0) {
               names[0] = specifiedNameParts[currentPartIndex--];
            }
         }

         return names;
      } else {
         return null;
      }
   }
}
