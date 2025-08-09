package org.datanucleus.api.jdo.metadata;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import javax.jdo.JDOUserException;
import javax.jdo.annotations.IdentityType;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.DatastoreIdentityMetadata;
import javax.jdo.metadata.FetchGroupMetadata;
import javax.jdo.metadata.ForeignKeyMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.InheritanceMetadata;
import javax.jdo.metadata.JoinMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.PrimaryKeyMetadata;
import javax.jdo.metadata.PropertyMetadata;
import javax.jdo.metadata.QueryMetadata;
import javax.jdo.metadata.TypeMetadata;
import javax.jdo.metadata.UniqueMetadata;
import javax.jdo.metadata.VersionMetadata;
import org.datanucleus.api.jdo.NucleusJDOHelper;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FetchGroupMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityMetaData;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.PrimaryKeyMetaData;
import org.datanucleus.metadata.PropertyMetaData;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.util.StringUtils;

public abstract class TypeMetadataImpl extends AbstractMetadataImpl implements TypeMetadata {
   public TypeMetadataImpl(MetaData internal) {
      super(internal);
   }

   public AbstractClassMetaData getInternal() {
      return (AbstractClassMetaData)this.internalMD;
   }

   public int getNumberOfMembers() {
      return this.getInternal().getNoOfMembers();
   }

   public MemberMetadata[] getMembers() {
      AbstractMemberMetaData[] internalMmds = this.getInternal().getManagedMembers();
      if (internalMmds == null) {
         return null;
      } else {
         MemberMetadataImpl[] mmds = new MemberMetadataImpl[internalMmds.length];

         for(int i = 0; i < mmds.length; ++i) {
            if (internalMmds[i] instanceof FieldMetaData) {
               mmds[i] = new FieldMetadataImpl((FieldMetaData)internalMmds[i]);
            } else {
               mmds[i] = new PropertyMetadataImpl((PropertyMetaData)internalMmds[i]);
            }

            mmds[i].parent = this;
         }

         return mmds;
      }
   }

   public PropertyMetadata newPropertyMetadata(String name) {
      PropertyMetaData internalPmd = this.getInternal().newPropertyMetadata(name);
      PropertyMetadataImpl pmd = new PropertyMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public PropertyMetadata newPropertyMetadata(Method method) {
      String methodName = method.getName();
      String name = null;
      if (methodName.startsWith("set")) {
         name = methodName.substring(3);
      } else if (methodName.startsWith("get")) {
         name = methodName.substring(3);
      } else {
         if (!methodName.startsWith("is")) {
            throw new JDOUserException("Method " + methodName + " is not a Java-bean method");
         }

         name = methodName.substring(2);
      }

      String propertyName = name.substring(0, 1).toLowerCase() + name.substring(1);
      PropertyMetaData internalPmd = this.getInternal().newPropertyMetadata(propertyName);
      PropertyMetadataImpl pmd = new PropertyMetadataImpl(internalPmd);
      pmd.parent = this;
      return pmd;
   }

   public boolean getCacheable() {
      return this.getInternal().isCacheable();
   }

   public String getCatalog() {
      return this.getInternal().getCatalog();
   }

   public DatastoreIdentityMetadata getDatastoreIdentityMetadata() {
      IdentityMetaData internalIdmd = this.getInternal().getIdentityMetaData();
      DatastoreIdentityMetadataImpl idmd = new DatastoreIdentityMetadataImpl(internalIdmd);
      idmd.parent = this;
      return idmd;
   }

   public boolean getDetachable() {
      return this.getInternal().isDetachable();
   }

   public Boolean getEmbeddedOnly() {
      return this.getInternal().isEmbeddedOnly();
   }

   public boolean getSerializeRead() {
      return this.getInternal().isSerializeRead();
   }

   public FetchGroupMetadata[] getFetchGroups() {
      Set<FetchGroupMetaData> internalFgmds = this.getInternal().getFetchGroupMetaData();
      if (internalFgmds == null) {
         return null;
      } else {
         FetchGroupMetadataImpl[] fgmds = new FetchGroupMetadataImpl[internalFgmds.size()];
         int i = 0;

         for(FetchGroupMetaData fgmd : internalFgmds) {
            fgmds[i] = new FetchGroupMetadataImpl(fgmd);
            fgmds[i].parent = this;
            ++i;
         }

         return fgmds;
      }
   }

   public ForeignKeyMetadata[] getForeignKeys() {
      ForeignKeyMetaData[] internalFks = this.getInternal().getForeignKeyMetaData();
      if (internalFks == null) {
         return null;
      } else {
         ForeignKeyMetadataImpl[] fkmds = new ForeignKeyMetadataImpl[internalFks.length];

         for(int i = 0; i < fkmds.length; ++i) {
            fkmds[i] = new ForeignKeyMetadataImpl(internalFks[i]);
            fkmds[i].parent = this;
         }

         return fkmds;
      }
   }

   public IdentityType getIdentityType() {
      org.datanucleus.metadata.IdentityType idType = this.getInternal().getIdentityType();
      if (idType == org.datanucleus.metadata.IdentityType.APPLICATION) {
         return IdentityType.APPLICATION;
      } else {
         return idType == org.datanucleus.metadata.IdentityType.DATASTORE ? IdentityType.DATASTORE : IdentityType.NONDURABLE;
      }
   }

   public IndexMetadata[] getIndices() {
      IndexMetaData[] internalIdxmds = this.getInternal().getIndexMetaData();
      if (internalIdxmds == null) {
         return null;
      } else {
         IndexMetadataImpl[] idxmds = new IndexMetadataImpl[internalIdxmds.length];

         for(int i = 0; i < idxmds.length; ++i) {
            idxmds[i] = new IndexMetadataImpl(internalIdxmds[i]);
            idxmds[i].parent = this;
         }

         return idxmds;
      }
   }

   public InheritanceMetadata getInheritanceMetadata() {
      InheritanceMetaData internalInhmd = this.getInternal().getInheritanceMetaData();
      InheritanceMetadataImpl inhmd = new InheritanceMetadataImpl(internalInhmd);
      inhmd.parent = this;
      return inhmd;
   }

   public JoinMetadata[] getJoins() {
      JoinMetaData[] internalJoins = this.getInternal().getJoinMetaData();
      if (internalJoins == null) {
         return null;
      } else {
         JoinMetadataImpl[] joins = new JoinMetadataImpl[internalJoins.length];

         for(int i = 0; i < joins.length; ++i) {
            joins[i] = new JoinMetadataImpl(internalJoins[i]);
            joins[i].parent = this;
         }

         return joins;
      }
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public int getNumberOfFetchGroups() {
      Set<FetchGroupMetaData> fgmds = this.getInternal().getFetchGroupMetaData();
      return fgmds != null ? fgmds.size() : 0;
   }

   public int getNumberOfForeignKeys() {
      ForeignKeyMetaData[] fkmds = this.getInternal().getForeignKeyMetaData();
      return fkmds != null ? fkmds.length : 0;
   }

   public int getNumberOfIndices() {
      IndexMetaData[] indexmds = this.getInternal().getIndexMetaData();
      return indexmds != null ? indexmds.length : 0;
   }

   public int getNumberOfJoins() {
      JoinMetaData[] joinmds = this.getInternal().getJoinMetaData();
      return joinmds != null ? joinmds.length : 0;
   }

   public int getNumberOfQueries() {
      return this.getInternal().getNoOfQueries();
   }

   public int getNumberOfUniques() {
      UniqueMetaData[] uniquemds = this.getInternal().getUniqueMetaData();
      return uniquemds != null ? uniquemds.length : 0;
   }

   public String getObjectIdClass() {
      return this.getInternal().getObjectidClass();
   }

   public PrimaryKeyMetadata getPrimaryKeyMetadata() {
      PrimaryKeyMetaData internalPkmd = this.getInternal().getPrimaryKeyMetaData();
      PrimaryKeyMetadataImpl pkmd = new PrimaryKeyMetadataImpl(internalPkmd);
      pkmd.parent = this;
      return pkmd;
   }

   public QueryMetadata[] getQueries() {
      QueryMetaData[] baseQueries = this.getInternal().getQueries();
      if (baseQueries == null) {
         return null;
      } else {
         QueryMetadataImpl[] queries = new QueryMetadataImpl[this.getInternal().getNoOfQueries()];

         for(int i = 0; i < queries.length; ++i) {
            queries[i] = new QueryMetadataImpl(baseQueries[i]);
            queries[i].parent = this;
         }

         return queries;
      }
   }

   public boolean getRequiresExtent() {
      return this.getInternal().isRequiresExtent();
   }

   public String getSchema() {
      return this.getInternal().getSchema();
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public UniqueMetadata[] getUniques() {
      UniqueMetaData[] internalUnimds = this.getInternal().getUniqueMetaData();
      if (internalUnimds == null) {
         return null;
      } else {
         UniqueMetadataImpl[] unimds = new UniqueMetadataImpl[internalUnimds.length];

         for(int i = 0; i < unimds.length; ++i) {
            unimds[i] = new UniqueMetadataImpl(internalUnimds[i]);
            unimds[i].parent = this;
         }

         return unimds;
      }
   }

   public VersionMetadata getVersionMetadata() {
      VersionMetaData internalVermd = this.getInternal().getVersionMetaData();
      VersionMetadataImpl vermd = new VersionMetadataImpl(internalVermd);
      vermd.parent = this;
      return vermd;
   }

   public DatastoreIdentityMetadata newDatastoreIdentityMetadata() {
      IdentityMetaData idmd = this.getInternal().newIdentityMetadata();
      DatastoreIdentityMetadataImpl dimd = new DatastoreIdentityMetadataImpl(idmd);
      dimd.parent = this;
      return dimd;
   }

   public FetchGroupMetadata newFetchGroupMetadata(String name) {
      FetchGroupMetaData internalFgmd = this.getInternal().newFetchGroupMetaData(name);
      FetchGroupMetadataImpl fgmd = new FetchGroupMetadataImpl(internalFgmd);
      fgmd.parent = this;
      return fgmd;
   }

   public ForeignKeyMetadata newForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().newForeignKeyMetadata();
      ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
      fkmd.parent = this;
      return fkmd;
   }

   public IndexMetadata newIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().newIndexMetadata();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
   }

   public InheritanceMetadata newInheritanceMetadata() {
      InheritanceMetaData internalInhmd = this.getInternal().newInheritanceMetadata();
      InheritanceMetadataImpl inhmd = new InheritanceMetadataImpl(internalInhmd);
      inhmd.parent = this;
      return inhmd;
   }

   public JoinMetadata newJoinMetadata() {
      JoinMetaData internalJoinmd = this.getInternal().newJoinMetaData();
      JoinMetadataImpl joinmd = new JoinMetadataImpl(internalJoinmd);
      joinmd.parent = this;
      return joinmd;
   }

   public PrimaryKeyMetadata newPrimaryKeyMetadata() {
      PrimaryKeyMetaData internalPkmd = this.getInternal().newPrimaryKeyMetadata();
      PrimaryKeyMetadataImpl pkmd = new PrimaryKeyMetadataImpl(internalPkmd);
      pkmd.parent = this;
      return pkmd;
   }

   public QueryMetadata newQueryMetadata(String name) {
      QueryMetaData internalQmd = this.getInternal().newQueryMetadata(name);
      QueryMetadataImpl qmd = new QueryMetadataImpl(internalQmd);
      qmd.parent = this;
      return qmd;
   }

   public UniqueMetadata newUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().newUniqueMetadata();
      UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
      unimd.parent = this;
      return unimd;
   }

   public VersionMetadata newVersionMetadata() {
      VersionMetaData internalVermd = this.getInternal().newVersionMetadata();
      VersionMetadataImpl vermd = new VersionMetadataImpl(internalVermd);
      vermd.parent = this;
      return vermd;
   }

   public TypeMetadata setCacheable(boolean cache) {
      this.getInternal().setCacheable(cache);
      return this;
   }

   public TypeMetadata setCatalog(String cat) {
      this.getInternal().setCatalog(cat);
      return this;
   }

   public TypeMetadata setDetachable(boolean flag) {
      this.getInternal().setDetachable(flag);
      return this;
   }

   public TypeMetadata setSerializeRead(boolean flag) {
      this.getInternal().setSerializeRead(flag);
      return this;
   }

   public TypeMetadata setEmbeddedOnly(boolean flag) {
      this.getInternal().setEmbeddedOnly(flag);
      return this;
   }

   public TypeMetadata setIdentityType(IdentityType type) {
      if (type == IdentityType.APPLICATION) {
         this.getInternal().setIdentityType(org.datanucleus.metadata.IdentityType.APPLICATION);
      } else if (type == IdentityType.DATASTORE) {
         this.getInternal().setIdentityType(org.datanucleus.metadata.IdentityType.DATASTORE);
      } else if (type == IdentityType.NONDURABLE) {
         this.getInternal().setIdentityType(org.datanucleus.metadata.IdentityType.NONDURABLE);
      }

      return this;
   }

   public TypeMetadata setObjectIdClass(String clsName) {
      if (!StringUtils.isWhitespace(clsName)) {
         this.getInternal().setObjectIdClass(NucleusJDOHelper.getObjectIdClassForInputIdClass(clsName));
      }

      return this;
   }

   public TypeMetadata setRequiresExtent(boolean flag) {
      this.getInternal().setRequiresExtent(flag);
      return this;
   }

   public TypeMetadata setSchema(String schema) {
      this.getInternal().setSchema(schema);
      return this;
   }

   public TypeMetadata setTable(String table) {
      this.getInternal().setTable(table);
      return this;
   }

   public ColumnMetadata[] getColumns() {
      List internalColmds = this.getInternal().getUnmappedColumns();
      if (internalColmds == null) {
         return null;
      } else {
         ColumnMetadataImpl[] colmds = new ColumnMetadataImpl[internalColmds.size()];

         for(int i = 0; i < colmds.length; ++i) {
            colmds[i] = new ColumnMetadataImpl((ColumnMetaData)internalColmds.get(i));
            colmds[i].parent = this;
         }

         return colmds;
      }
   }

   public int getNumberOfColumns() {
      List colmds = this.getInternal().getUnmappedColumns();
      return colmds != null ? colmds.size() : 0;
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newUnmappedColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }
}
