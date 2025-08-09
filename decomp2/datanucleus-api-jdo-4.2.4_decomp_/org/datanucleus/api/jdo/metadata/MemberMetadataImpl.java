package org.datanucleus.api.jdo.metadata;

import javax.jdo.AttributeConverter;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceModifier;
import javax.jdo.metadata.ArrayMetadata;
import javax.jdo.metadata.CollectionMetadata;
import javax.jdo.metadata.ColumnMetadata;
import javax.jdo.metadata.ElementMetadata;
import javax.jdo.metadata.EmbeddedMetadata;
import javax.jdo.metadata.ForeignKeyMetadata;
import javax.jdo.metadata.IndexMetadata;
import javax.jdo.metadata.JoinMetadata;
import javax.jdo.metadata.KeyMetadata;
import javax.jdo.metadata.MapMetadata;
import javax.jdo.metadata.MemberMetadata;
import javax.jdo.metadata.OrderMetadata;
import javax.jdo.metadata.UniqueMetadata;
import javax.jdo.metadata.ValueMetadata;
import org.datanucleus.api.jdo.JDOTypeConverter;
import org.datanucleus.api.jdo.JDOTypeConverterUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ArrayMetaData;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IndexMetaData;
import org.datanucleus.metadata.IndexedValue;
import org.datanucleus.metadata.JoinMetaData;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.metadata.OrderMetaData;
import org.datanucleus.metadata.UniqueMetaData;
import org.datanucleus.metadata.ValueMetaData;
import org.datanucleus.util.NucleusLogger;

public class MemberMetadataImpl extends AbstractMetadataImpl implements MemberMetadata {
   public MemberMetadataImpl(MetaData internal) {
      super(internal);
   }

   public AbstractMemberMetaData getInternal() {
      return (AbstractMemberMetaData)this.internalMD;
   }

   public ArrayMetadata getArrayMetadata() {
      ArrayMetaData internalArrmd = this.getInternal().getArray();
      if (internalArrmd == null) {
         return null;
      } else {
         ArrayMetadataImpl arrmd = new ArrayMetadataImpl(internalArrmd);
         arrmd.parent = this;
         return arrmd;
      }
   }

   public boolean getCacheable() {
      return this.getInternal().isCacheable();
   }

   public CollectionMetadata getCollectionMetadata() {
      CollectionMetaData internalCollmd = this.getInternal().getCollection();
      if (internalCollmd == null) {
         return null;
      } else {
         CollectionMetadataImpl collmd = new CollectionMetadataImpl(internalCollmd);
         collmd.parent = this;
         return collmd;
      }
   }

   public String getColumn() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null && colmds.length > 0 ? colmds[0].getName() : null;
   }

   public String getCustomStrategy() {
      IdentityStrategy strategy = this.getInternal().getValueStrategy();
      return strategy != IdentityStrategy.IDENTITY && strategy != IdentityStrategy.INCREMENT && strategy != IdentityStrategy.NATIVE && strategy != IdentityStrategy.SEQUENCE && strategy != IdentityStrategy.UUIDHEX && strategy != IdentityStrategy.UUIDSTRING && strategy != null ? strategy.toString() : null;
   }

   public Boolean getDefaultFetchGroup() {
      return this.getInternal().isDefaultFetchGroup();
   }

   public ForeignKeyAction getDeleteAction() {
      ForeignKeyMetaData fkmd = this.getInternal().getForeignKeyMetaData();
      if (fkmd != null) {
         org.datanucleus.metadata.ForeignKeyAction fk = fkmd.getDeleteAction();
         if (fk == org.datanucleus.metadata.ForeignKeyAction.CASCADE) {
            return ForeignKeyAction.CASCADE;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.DEFAULT) {
            return ForeignKeyAction.DEFAULT;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.NONE) {
            return ForeignKeyAction.NONE;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.NULL) {
            return ForeignKeyAction.NULL;
         }

         if (fk == org.datanucleus.metadata.ForeignKeyAction.RESTRICT) {
            return ForeignKeyAction.RESTRICT;
         }
      }

      return ForeignKeyAction.UNSPECIFIED;
   }

   public Boolean getDependent() {
      return this.getInternal().isDependent();
   }

   public ElementMetadata getElementMetadata() {
      ElementMetaData internalElemmd = this.getInternal().getElementMetaData();
      if (internalElemmd == null) {
         return null;
      } else {
         ElementMetadataImpl elemmd = new ElementMetadataImpl(internalElemmd);
         elemmd.parent = this;
         return elemmd;
      }
   }

   public Boolean getEmbedded() {
      return this.getInternal().isEmbedded();
   }

   public EmbeddedMetadata getEmbeddedMetadata() {
      EmbeddedMetaData internalEmbmd = this.getInternal().getEmbeddedMetaData();
      EmbeddedMetadataImpl embmd = new EmbeddedMetadataImpl(internalEmbmd);
      embmd.parent = this;
      return embmd;
   }

   public OrderMetadata getOrderMetadata() {
      OrderMetaData internalOrdmd = this.getInternal().getOrderMetaData();
      OrderMetadataImpl ordmd = new OrderMetadataImpl(internalOrdmd);
      ordmd.parent = this;
      return ordmd;
   }

   public String getFieldType() {
      return this.getInternal().getTypeName();
   }

   public ForeignKeyMetadata getForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().getForeignKeyMetaData();
      if (internalFkmd == null) {
         return null;
      } else {
         ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
         fkmd.parent = this;
         return fkmd;
      }
   }

   public IndexMetadata getIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().getIndexMetaData();
      if (internalIdxmd == null) {
         return null;
      } else {
         IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
         idxmd.parent = this;
         return idxmd;
      }
   }

   public Boolean getIndexed() {
      IndexedValue val = this.getInternal().getIndexed();
      if (val == IndexedValue.TRUE) {
         return true;
      } else {
         return val == IndexedValue.FALSE ? false : null;
      }
   }

   public JoinMetadata getJoinMetadata() {
      JoinMetaData internalJoinmd = this.getInternal().getJoinMetaData();
      if (internalJoinmd == null) {
         return null;
      } else {
         JoinMetadataImpl joinmd = new JoinMetadataImpl(internalJoinmd);
         joinmd.parent = this;
         return joinmd;
      }
   }

   public KeyMetadata getKeyMetadata() {
      KeyMetaData internalKeymd = this.getInternal().getKeyMetaData();
      if (internalKeymd == null) {
         return null;
      } else {
         KeyMetadataImpl keymd = new KeyMetadataImpl(internalKeymd);
         keymd.parent = this;
         return keymd;
      }
   }

   public String getLoadFetchGroup() {
      return this.getInternal().getLoadFetchGroup();
   }

   public MapMetadata getMapMetadata() {
      MapMetaData internalMapmd = this.getInternal().getMap();
      if (internalMapmd == null) {
         return null;
      } else {
         MapMetadataImpl mapmd = new MapMetadataImpl(internalMapmd);
         mapmd.parent = this;
         return mapmd;
      }
   }

   public String getMappedBy() {
      return this.getInternal().getMappedBy();
   }

   public String getName() {
      return this.getInternal().getName();
   }

   public NullValue getNullValue() {
      org.datanucleus.metadata.NullValue val = this.getInternal().getNullValue();
      if (val == null) {
         return null;
      } else if (val == org.datanucleus.metadata.NullValue.DEFAULT) {
         return NullValue.DEFAULT;
      } else if (val == org.datanucleus.metadata.NullValue.EXCEPTION) {
         return NullValue.EXCEPTION;
      } else {
         return val == org.datanucleus.metadata.NullValue.NONE ? NullValue.NONE : null;
      }
   }

   public PersistenceModifier getPersistenceModifier() {
      FieldPersistenceModifier mod = this.getInternal().getPersistenceModifier();
      if (mod == FieldPersistenceModifier.NONE) {
         return PersistenceModifier.NONE;
      } else if (mod == FieldPersistenceModifier.TRANSACTIONAL) {
         return PersistenceModifier.TRANSACTIONAL;
      } else {
         return mod == FieldPersistenceModifier.PERSISTENT ? PersistenceModifier.PERSISTENT : PersistenceModifier.UNSPECIFIED;
      }
   }

   public boolean getPrimaryKey() {
      return this.getInternal().isPrimaryKey();
   }

   public int getRecursionDepth() {
      return this.getInternal().getRecursionDepth();
   }

   public String getSequence() {
      return this.getInternal().getSequence();
   }

   public Boolean getSerialized() {
      return this.getInternal().isSerialized();
   }

   public String getTable() {
      return this.getInternal().getTable();
   }

   public Boolean getUnique() {
      return this.getInternal().isUnique();
   }

   public UniqueMetadata getUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().getUniqueMetaData();
      if (internalUnimd == null) {
         return null;
      } else {
         UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
         unimd.parent = this;
         return unimd;
      }
   }

   public ValueMetadata getValueMetadata() {
      ValueMetaData internalValmd = this.getInternal().getValueMetaData();
      if (internalValmd == null) {
         return null;
      } else {
         ValueMetadataImpl valmd = new ValueMetadataImpl(internalValmd);
         valmd.parent = this;
         return valmd;
      }
   }

   public IdGeneratorStrategy getValueStrategy() {
      IdentityStrategy strategy = this.getInternal().getValueStrategy();
      if (strategy == IdentityStrategy.IDENTITY) {
         return IdGeneratorStrategy.IDENTITY;
      } else if (strategy == IdentityStrategy.INCREMENT) {
         return IdGeneratorStrategy.INCREMENT;
      } else if (strategy == IdentityStrategy.NATIVE) {
         return IdGeneratorStrategy.NATIVE;
      } else if (strategy == IdentityStrategy.SEQUENCE) {
         return IdGeneratorStrategy.SEQUENCE;
      } else if (strategy == IdentityStrategy.UUIDHEX) {
         return IdGeneratorStrategy.UUIDHEX;
      } else {
         return strategy == IdentityStrategy.UUIDSTRING ? IdGeneratorStrategy.UUIDSTRING : IdGeneratorStrategy.UNSPECIFIED;
      }
   }

   public ArrayMetadata newArrayMetadata() {
      ArrayMetaData internalArrmd = this.getInternal().newArrayMetaData();
      ArrayMetadataImpl arrmd = new ArrayMetadataImpl(internalArrmd);
      arrmd.parent = this;
      return arrmd;
   }

   public CollectionMetadata newCollectionMetadata() {
      CollectionMetaData internalCollmd = this.getInternal().newCollectionMetaData();
      CollectionMetadataImpl collmd = new CollectionMetadataImpl(internalCollmd);
      collmd.parent = this;
      return collmd;
   }

   public ElementMetadata newElementMetadata() {
      ElementMetaData internalElemmd = this.getInternal().newElementMetaData();
      ElementMetadataImpl elemmd = new ElementMetadataImpl(internalElemmd);
      elemmd.parent = this;
      return elemmd;
   }

   public EmbeddedMetadata newEmbeddedMetadata() {
      EmbeddedMetaData internalEmbmd = this.getInternal().newEmbeddedMetaData();
      EmbeddedMetadataImpl embmd = new EmbeddedMetadataImpl(internalEmbmd);
      embmd.parent = this;
      return embmd;
   }

   public ForeignKeyMetadata newForeignKeyMetadata() {
      ForeignKeyMetaData internalFkmd = this.getInternal().newForeignKeyMetaData();
      ForeignKeyMetadataImpl fkmd = new ForeignKeyMetadataImpl(internalFkmd);
      fkmd.parent = this;
      return fkmd;
   }

   public IndexMetadata newIndexMetadata() {
      IndexMetaData internalIdxmd = this.getInternal().newIndexMetaData();
      IndexMetadataImpl idxmd = new IndexMetadataImpl(internalIdxmd);
      idxmd.parent = this;
      return idxmd;
   }

   public JoinMetadata newJoinMetadata() {
      JoinMetaData internalJoinmd = this.getInternal().newJoinMetaData();
      JoinMetadataImpl joinmd = new JoinMetadataImpl(internalJoinmd);
      joinmd.parent = this;
      return joinmd;
   }

   public KeyMetadata newKeyMetadata() {
      KeyMetaData internalKeymd = this.getInternal().newKeyMetaData();
      KeyMetadataImpl keymd = new KeyMetadataImpl(internalKeymd);
      keymd.parent = this;
      return keymd;
   }

   public MapMetadata newMapMetadata() {
      MapMetaData internalMapmd = this.getInternal().newMapMetaData();
      MapMetadataImpl mapmd = new MapMetadataImpl(internalMapmd);
      mapmd.parent = this;
      return mapmd;
   }

   public OrderMetadata newOrderMetadata() {
      OrderMetaData internalOrdmd = this.getInternal().newOrderMetaData();
      OrderMetadataImpl ordmd = new OrderMetadataImpl(internalOrdmd);
      ordmd.parent = this;
      return ordmd;
   }

   public UniqueMetadata newUniqueMetadata() {
      UniqueMetaData internalUnimd = this.getInternal().newUniqueMetaData();
      UniqueMetadataImpl unimd = new UniqueMetadataImpl(internalUnimd);
      unimd.parent = this;
      return unimd;
   }

   public ValueMetadata newValueMetadata() {
      ValueMetaData internalValmd = this.getInternal().newValueMetaData();
      ValueMetadataImpl valmd = new ValueMetadataImpl(internalValmd);
      valmd.parent = this;
      return valmd;
   }

   public MemberMetadata setCacheable(boolean cache) {
      this.getInternal().setCacheable(cache);
      return this;
   }

   public MemberMetadata setColumn(String name) {
      this.getInternal().setColumn(name);
      return this;
   }

   public MemberMetadata setCustomStrategy(String strategy) {
      this.getInternal().setValueStrategy(IdentityStrategy.getIdentityStrategy(strategy));
      return this;
   }

   public MemberMetadata setDefaultFetchGroup(boolean dfg) {
      this.getInternal().setDefaultFetchGroup(dfg);
      return this;
   }

   public MemberMetadata setDeleteAction(ForeignKeyAction fk) {
      ForeignKeyMetaData fkmd = this.getInternal().getForeignKeyMetaData();
      if (fk == ForeignKeyAction.CASCADE) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.CASCADE);
      } else if (fk == ForeignKeyAction.DEFAULT) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.DEFAULT);
      } else if (fk == ForeignKeyAction.NONE) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NONE);
      } else if (fk == ForeignKeyAction.NULL) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.NULL);
      } else if (fk == ForeignKeyAction.RESTRICT) {
         fkmd.setDeleteAction(org.datanucleus.metadata.ForeignKeyAction.RESTRICT);
      }

      return this;
   }

   public MemberMetadata setDependent(boolean flag) {
      this.getInternal().setDependent(flag);
      return this;
   }

   public MemberMetadata setEmbedded(boolean flag) {
      this.getInternal().setEmbedded(flag);
      return this;
   }

   public MemberMetadata setFieldType(String types) {
      this.getInternal().setFieldTypes(types);
      return this;
   }

   public MemberMetadata setIndexed(boolean flag) {
      if (flag) {
         this.getInternal().setIndexed(IndexedValue.TRUE);
      } else {
         this.getInternal().setIndexed(IndexedValue.FALSE);
      }

      return this;
   }

   public MemberMetadata setLoadFetchGroup(String load) {
      this.getInternal().setLoadFetchGroup(load);
      return this;
   }

   public MemberMetadata setMappedBy(String mappedBy) {
      this.getInternal().setMappedBy(mappedBy);
      return this;
   }

   public MemberMetadata setName(String name) {
      return this;
   }

   public MemberMetadata setNullValue(NullValue val) {
      if (val == NullValue.DEFAULT) {
         this.getInternal().setNullValue(org.datanucleus.metadata.NullValue.DEFAULT);
      } else if (val == NullValue.EXCEPTION) {
         this.getInternal().setNullValue(org.datanucleus.metadata.NullValue.EXCEPTION);
      } else if (val == NullValue.NONE) {
         this.getInternal().setNullValue(org.datanucleus.metadata.NullValue.NONE);
      }

      return this;
   }

   public MemberMetadata setPersistenceModifier(PersistenceModifier val) {
      if (val == PersistenceModifier.NONE) {
         this.getInternal().setPersistenceModifier(FieldPersistenceModifier.NONE);
      } else if (val == PersistenceModifier.PERSISTENT) {
         this.getInternal().setPersistenceModifier(FieldPersistenceModifier.PERSISTENT);
      } else if (val == PersistenceModifier.TRANSACTIONAL) {
         this.getInternal().setPersistenceModifier(FieldPersistenceModifier.TRANSACTIONAL);
      }

      return this;
   }

   public MemberMetadata setPrimaryKey(boolean flag) {
      this.getInternal().setPrimaryKey(flag);
      return this;
   }

   public MemberMetadata setRecursionDepth(int depth) {
      this.getInternal().setRecursionDepth(depth);
      return this;
   }

   public MemberMetadata setSequence(String seq) {
      this.getInternal().setSequence(seq);
      return this;
   }

   public MemberMetadata setSerialized(boolean flag) {
      this.getInternal().setSerialised(flag);
      return this;
   }

   public MemberMetadata setTable(String table) {
      this.getInternal().setTable(table);
      return this;
   }

   public MemberMetadata setUnique(boolean flag) {
      this.getInternal().setUnique(flag);
      return this;
   }

   public MemberMetadata setValueStrategy(IdGeneratorStrategy strategy) {
      if (strategy == IdGeneratorStrategy.IDENTITY) {
         this.getInternal().setValueStrategy(IdentityStrategy.IDENTITY);
      } else if (strategy == IdGeneratorStrategy.INCREMENT) {
         this.getInternal().setValueStrategy(IdentityStrategy.INCREMENT);
      } else if (strategy == IdGeneratorStrategy.NATIVE) {
         this.getInternal().setValueStrategy(IdentityStrategy.NATIVE);
      } else if (strategy == IdGeneratorStrategy.SEQUENCE) {
         this.getInternal().setValueStrategy(IdentityStrategy.SEQUENCE);
      } else if (strategy == IdGeneratorStrategy.UUIDHEX) {
         this.getInternal().setValueStrategy(IdentityStrategy.UUIDHEX);
      } else if (strategy == IdGeneratorStrategy.UUIDSTRING) {
         this.getInternal().setValueStrategy(IdentityStrategy.UUIDSTRING);
      }

      return this;
   }

   public ColumnMetadata[] getColumns() {
      ColumnMetaData[] internalColmds = this.getInternal().getColumnMetaData();
      if (internalColmds == null) {
         return null;
      } else {
         ColumnMetadataImpl[] colmds = new ColumnMetadataImpl[internalColmds.length];

         for(int i = 0; i < colmds.length; ++i) {
            colmds[i] = new ColumnMetadataImpl(internalColmds[i]);
            colmds[i].parent = this;
         }

         return colmds;
      }
   }

   public int getNumberOfColumns() {
      ColumnMetaData[] colmds = this.getInternal().getColumnMetaData();
      return colmds != null ? colmds.length : 0;
   }

   public ColumnMetadata newColumnMetadata() {
      ColumnMetaData internalColmd = this.getInternal().newColumnMetaData();
      ColumnMetadataImpl colmd = new ColumnMetadataImpl(internalColmd);
      colmd.parent = this;
      return colmd;
   }

   public AttributeConverter getConverter() {
      String typeConverterName = this.getInternal().getTypeConverterName();
      if (typeConverterName != null) {
      }

      return null;
   }

   public MemberMetadata setConverter(AttributeConverter conv) {
      Class attrType = JDOTypeConverterUtils.getAttributeTypeForAttributeConverter(conv.getClass(), this.getInternal().getType());
      Class dbType = JDOTypeConverterUtils.getDatastoreTypeForAttributeConverter(conv.getClass(), attrType, (Class)null);
      JDOTypeConverter typeConv = new JDOTypeConverter(conv, attrType, dbType);
      NucleusLogger.GENERAL.debug(">> Need to register JDOTypeConverter " + typeConv + " under name " + conv.getClass().getName());
      this.getInternal().setTypeConverterName(conv.getClass().getName());
      return this;
   }

   public Boolean getUseDefaultConversion() {
      return this.getInternal().isTypeConversionDisabled();
   }

   public MemberMetadata setUseDefaultConversion(Boolean disable) {
      if (disable) {
         this.getInternal().setTypeConverterDisabled();
      }

      return this;
   }
}
