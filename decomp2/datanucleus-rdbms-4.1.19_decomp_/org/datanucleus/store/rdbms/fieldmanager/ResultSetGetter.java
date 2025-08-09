package org.datanucleus.store.rdbms.fieldmanager;

import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.types.SCOUtils;

public class ResultSetGetter extends AbstractFieldManager {
   private final RDBMSStoreManager storeMgr;
   private final ObjectProvider op;
   private final AbstractClassMetaData cmd;
   private final ExecutionContext ec;
   private final ResultSet resultSet;
   private final StatementClassMapping resultMappings;

   public ResultSetGetter(RDBMSStoreManager storeMgr, ObjectProvider op, ResultSet rs, StatementClassMapping resultMappings) {
      this.storeMgr = storeMgr;
      this.op = op;
      this.cmd = op.getClassMetaData();
      this.ec = op.getExecutionContext();
      this.resultSet = rs;
      this.resultMappings = resultMappings;
   }

   public ResultSetGetter(RDBMSStoreManager storeMgr, ExecutionContext ec, ResultSet rs, StatementClassMapping resultMappings, AbstractClassMetaData cmd) {
      this.storeMgr = storeMgr;
      this.op = null;
      this.cmd = cmd;
      this.ec = ec;
      this.resultSet = rs;
      this.resultMappings = resultMappings;
   }

   public boolean fetchBooleanField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getBoolean(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public char fetchCharField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getChar(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public byte fetchByteField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getByte(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public short fetchShortField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getShort(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public int fetchIntField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getInt(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public long fetchLongField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getLong(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public float fetchFloatField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getFloat(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public double fetchDoubleField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getDouble(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public String fetchStringField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      return mapIdx.getMapping().getString(this.ec, this.resultSet, mapIdx.getColumnPositions());
   }

   public Object fetchObjectField(int fieldNumber) {
      StatementMappingIndex mapIdx = this.resultMappings.getMappingForMemberPosition(fieldNumber);
      JavaTypeMapping mapping = mapIdx.getMapping();
      AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
      RelationType relationType = mmd.getRelationType(this.ec.getClassLoaderResolver());
      Object value;
      if (!(mapping instanceof EmbeddedPCMapping) && !(mapping instanceof SerialisedPCMapping) && !(mapping instanceof SerialisedReferenceMapping)) {
         if (RelationType.isRelationSingleValued(relationType)) {
            StatementClassMapping relationMappings = this.resultMappings.getMappingDefinitionForMemberPosition(fieldNumber);
            if (relationMappings != null) {
               ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
               Class fieldType = mmd.getType();
               AbstractClassMetaData relatedCmd = this.ec.getMetaDataManager().getMetaDataForClass(mmd.getType(), clr);
               if (mapping instanceof ReferenceMapping) {
                  ReferenceMapping refMapping = (ReferenceMapping)mapping;
                  if (refMapping.getMappingStrategy() == 0) {
                     JavaTypeMapping[] subMappings = refMapping.getJavaTypeMapping();
                     if (subMappings != null && subMappings.length == 1) {
                        relatedCmd = this.ec.getMetaDataManager().getMetaDataForClass(subMappings[0].getType(), clr);
                        fieldType = clr.classForName(subMappings[0].getType());
                     }
                  }
               }

               ResultObjectFactory relationROF = new PersistentClassROF(this.storeMgr, relatedCmd, relationMappings, false, this.ec.getFetchPlan(), fieldType);
               value = relationROF.getObject(this.ec, this.resultSet);
            } else {
               value = mapping.getObject(this.ec, this.resultSet, mapIdx.getColumnPositions());
            }
         } else {
            value = mapping.getObject(this.ec, this.resultSet, mapIdx.getColumnPositions());
         }
      } else {
         value = mapping.getObject(this.ec, this.resultSet, mapIdx.getColumnPositions(), this.op, fieldNumber);
      }

      if (this.op != null) {
         if (this.op.getClassMetaData().getSCOMutableMemberFlags()[fieldNumber]) {
            return SCOUtils.wrapSCOField(this.op, fieldNumber, value, false);
         }

         if (RelationType.isRelationSingleValued(relationType) && mmd.getEmbeddedMetaData() != null && mmd.getEmbeddedMetaData().getOwnerMember() != null) {
            this.op.updateOwnerFieldInEmbeddedField(fieldNumber, value);
            return value;
         }
      }

      return value;
   }
}
