package org.datanucleus.store.schema.naming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ConstraintMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.util.StringUtils;

public abstract class AbstractNamingFactory implements NamingFactory {
   protected Set reservedWords = new HashSet();
   protected String wordSeparator = "_";
   protected String quoteString = "\"";
   protected NamingCase namingCase;
   protected NucleusContext nucCtx;
   protected ClassLoaderResolver clr;
   Map maxLengthByComponent;
   private static final int TRUNCATE_HASH_LENGTH = 4;
   private static final int TRUNCATE_HASH_RANGE = calculateHashMax();

   public AbstractNamingFactory(NucleusContext nucCtx) {
      this.namingCase = NamingCase.MIXED_CASE;
      this.maxLengthByComponent = new HashMap();
      this.nucCtx = nucCtx;
      this.clr = nucCtx.getClassLoaderResolver((ClassLoader)null);
   }

   public NamingFactory setReservedKeywords(Set keywords) {
      if (keywords != null) {
         this.reservedWords.addAll(keywords);
      }

      return this;
   }

   public NamingFactory setQuoteString(String quote) {
      if (quote != null) {
         this.quoteString = quote;
      }

      return this;
   }

   public NamingFactory setWordSeparator(String sep) {
      if (sep != null) {
         this.wordSeparator = sep;
      }

      return this;
   }

   public NamingFactory setNamingCase(NamingCase nameCase) {
      if (nameCase != null) {
         this.namingCase = nameCase;
      }

      return this;
   }

   public NamingFactory setMaximumLength(SchemaComponent cmpt, int max) {
      this.maxLengthByComponent.put(cmpt, max);
      return this;
   }

   protected int getMaximumLengthForComponent(SchemaComponent cmpt) {
      return this.maxLengthByComponent.containsKey(cmpt) ? (Integer)this.maxLengthByComponent.get(cmpt) : -1;
   }

   public String getTableName(AbstractClassMetaData cmd) {
      String name = null;
      if (cmd.getTable() != null) {
         name = cmd.getTable();
      }

      if (name == null) {
         name = cmd.getName();
      }

      return this.prepareIdentifierNameForUse(name, SchemaComponent.TABLE);
   }

   public String getColumnName(AbstractMemberMetaData mmd, ColumnType type) {
      return this.getColumnName(mmd, type, 0);
   }

   public String getColumnName(List mmds, int colPosition) {
      EmbeddedMetaData embmd = null;
      AbstractMemberMetaData rootMmd = (AbstractMemberMetaData)mmds.get(0);
      if (!rootMmd.hasCollection() && !rootMmd.hasArray()) {
         embmd = ((AbstractMemberMetaData)mmds.get(0)).getEmbeddedMetaData();
      } else if (rootMmd.getElementMetaData() != null) {
         embmd = rootMmd.getElementMetaData().getEmbeddedMetaData();
      }

      if (embmd != null && mmds.size() > 1) {
         boolean checked = false;
         int mmdNo = 1;

         while(!checked) {
            AbstractMemberMetaData[] embMmds = embmd.getMemberMetaData();
            if (embMmds == null || embMmds.length == 0) {
               break;
            }

            boolean checkedEmbmd = false;
            boolean foundEmbmd = false;

            for(int i = 0; i < embMmds.length; ++i) {
               if (embMmds[i].getFullFieldName().equals(((AbstractMemberMetaData)mmds.get(mmdNo)).getFullFieldName())) {
                  foundEmbmd = true;
                  if (mmds.size() == mmdNo + 1) {
                     checked = true;
                     ColumnMetaData[] colmds = embMmds[i].getColumnMetaData();
                     if (colmds != null && colmds.length > colPosition && !StringUtils.isWhitespace(colmds[colPosition].getName())) {
                        String colName = colmds[colPosition].getName();
                        return this.prepareIdentifierNameForUse(colName, SchemaComponent.COLUMN);
                     }
                  } else {
                     checkedEmbmd = true;
                     ++mmdNo;
                     embmd = null;
                     if (!embMmds[i].hasCollection() && !embMmds[i].hasArray()) {
                        embmd = embMmds[i].getEmbeddedMetaData();
                     } else if (embMmds[i].getElementMetaData() != null) {
                        embmd = embMmds[i].getElementMetaData().getEmbeddedMetaData();
                     }

                     if (embmd == null) {
                        checked = true;
                     }
                  }
               }

               if (checked || checkedEmbmd) {
                  break;
               }
            }

            if (!foundEmbmd) {
               checked = true;
            }
         }
      }

      if (mmds.size() >= 1) {
         AbstractMemberMetaData lastMmd = (AbstractMemberMetaData)mmds.get(mmds.size() - 1);
         ColumnMetaData[] colmds = lastMmd.getColumnMetaData();
         if (colmds != null && colmds.length > colPosition && !StringUtils.isWhitespace(colmds[colPosition].getName())) {
            String colName = colmds[colPosition].getName();
            return this.prepareIdentifierNameForUse(colName, SchemaComponent.COLUMN);
         }
      }

      StringBuilder str = new StringBuilder(((AbstractMemberMetaData)mmds.get(0)).getName());

      for(int i = 1; i < mmds.size(); ++i) {
         str.append(this.wordSeparator);
         str.append(((AbstractMemberMetaData)mmds.get(i)).getName());
      }

      return this.prepareIdentifierNameForUse(str.toString(), SchemaComponent.COLUMN);
   }

   public String getConstraintName(AbstractClassMetaData cmd, ConstraintMetaData cnstrmd, int position) {
      if (cnstrmd != null && !StringUtils.isWhitespace(cnstrmd.getName())) {
         return this.prepareIdentifierNameForUse(cnstrmd.getName(), SchemaComponent.CONSTRAINT);
      } else {
         String suffix = "IDX";
         if (cnstrmd instanceof ForeignKeyMetaData) {
            suffix = "FK";
         }

         String idxName = cmd.getName() + this.wordSeparator + position + this.wordSeparator + suffix;
         return this.prepareIdentifierNameForUse(idxName, SchemaComponent.CONSTRAINT);
      }
   }

   public String getConstraintName(String className, AbstractMemberMetaData mmd, ConstraintMetaData cnstrmd) {
      if (cnstrmd != null && !StringUtils.isWhitespace(cnstrmd.getName())) {
         return this.prepareIdentifierNameForUse(cnstrmd.getName(), SchemaComponent.CONSTRAINT);
      } else {
         String suffix = "IDX";
         if (cnstrmd instanceof ForeignKeyMetaData) {
            suffix = "FK";
         }

         String idxName = className + this.wordSeparator + mmd.getName() + this.wordSeparator + suffix;
         return this.prepareIdentifierNameForUse(idxName, SchemaComponent.CONSTRAINT);
      }
   }

   public String getConstraintName(AbstractClassMetaData cmd, ConstraintMetaData cnstrmd, ColumnType type) {
      if (cnstrmd != null && !StringUtils.isWhitespace(cnstrmd.getName())) {
         return this.prepareIdentifierNameForUse(cnstrmd.getName(), SchemaComponent.CONSTRAINT);
      } else {
         String suffix = "IDX";
         if (cnstrmd instanceof ForeignKeyMetaData) {
            suffix = "FK";
         }

         String idxName = null;
         if (type == ColumnType.DATASTOREID_COLUMN) {
            idxName = cmd.getName() + this.wordSeparator + "DATASTORE" + this.wordSeparator + suffix;
         } else if (type == ColumnType.VERSION_COLUMN) {
            idxName = cmd.getName() + this.wordSeparator + "VERSION" + this.wordSeparator + suffix;
         } else if (type == ColumnType.MULTITENANCY_COLUMN) {
            idxName = cmd.getName() + this.wordSeparator + "TENANT" + this.wordSeparator + suffix;
         } else if (type == ColumnType.DISCRIMINATOR_COLUMN) {
            idxName = cmd.getName() + this.wordSeparator + "DISCRIM" + this.wordSeparator + suffix;
         } else {
            idxName = suffix;
         }

         return this.prepareIdentifierNameForUse(idxName, SchemaComponent.CONSTRAINT);
      }
   }

   public String getSequenceName(SequenceMetaData seqmd) {
      if (!StringUtils.isWhitespace(seqmd.getDatastoreSequence())) {
         return this.prepareIdentifierNameForUse(seqmd.getDatastoreSequence(), SchemaComponent.SEQUENCE);
      } else {
         String name = seqmd.getName() + this.wordSeparator + "SEQ";
         return this.prepareIdentifierNameForUse(name, SchemaComponent.SEQUENCE);
      }
   }

   private static final int calculateHashMax() {
      int hm = 1;

      for(int i = 0; i < 4; ++i) {
         hm *= 36;
      }

      return hm;
   }

   protected static String truncate(String name, int length) {
      if (length == 0) {
         return name;
      } else if (name.length() > length) {
         if (length < 4) {
            throw new IllegalArgumentException("The length argument (=" + length + ") is less than HASH_LENGTH(=" + 4 + ")!");
         } else {
            int tailIndex = length - 4;
            int tailHash = name.hashCode();
            if (tailHash < 0) {
               tailHash *= -1;
            }

            tailHash %= TRUNCATE_HASH_RANGE;
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

               return name.substring(0, tailIndex) + suffix;
            }
         }
      } else {
         return name;
      }
   }

   protected String getNameInRequiredCase(String name) {
      if (name == null) {
         return null;
      } else {
         StringBuilder id = new StringBuilder();
         if ((this.namingCase == NamingCase.LOWER_CASE_QUOTED || this.namingCase == NamingCase.MIXED_CASE_QUOTED || this.namingCase == NamingCase.UPPER_CASE_QUOTED) && !name.startsWith(this.quoteString)) {
            id.append(this.quoteString);
         }

         if (this.namingCase != NamingCase.LOWER_CASE && this.namingCase != NamingCase.LOWER_CASE_QUOTED) {
            if (this.namingCase != NamingCase.UPPER_CASE && this.namingCase != NamingCase.UPPER_CASE_QUOTED) {
               id.append(name);
            } else {
               id.append(name.toUpperCase());
            }
         } else {
            id.append(name.toLowerCase());
         }

         if ((this.namingCase == NamingCase.LOWER_CASE_QUOTED || this.namingCase == NamingCase.MIXED_CASE_QUOTED || this.namingCase == NamingCase.UPPER_CASE_QUOTED) && !name.endsWith(this.quoteString)) {
            id.append(this.quoteString);
         }

         return id.toString();
      }
   }

   protected String prepareIdentifierNameForUse(String name, SchemaComponent cmpt) {
      if (name == null) {
         return name;
      } else {
         String preparedName = name;
         int maxLength = this.getMaximumLengthForComponent(cmpt);
         if (maxLength > 0 && name.length() > maxLength) {
            preparedName = truncate(name, maxLength);
         }

         String casedName = this.getNameInRequiredCase(preparedName);
         if (!casedName.startsWith(this.quoteString) && this.reservedWords.contains(casedName.toUpperCase())) {
            casedName = this.quoteString + casedName + this.quoteString;
         }

         return casedName;
      }
   }
}
