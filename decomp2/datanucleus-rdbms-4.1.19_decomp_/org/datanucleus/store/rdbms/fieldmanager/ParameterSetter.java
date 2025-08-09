package org.datanucleus.store.rdbms.fieldmanager;

import java.sql.PreparedStatement;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.NullValue;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.fieldmanager.AbstractFieldManager;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.InterfaceMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;

public class ParameterSetter extends AbstractFieldManager {
   protected final ObjectProvider op;
   protected final ExecutionContext ec;
   protected final PreparedStatement statement;
   protected final StatementClassMapping stmtMappings;

   public ParameterSetter(ObjectProvider op, PreparedStatement stmt, StatementClassMapping stmtMappings) {
      this.op = op;
      this.ec = op.getExecutionContext();
      this.statement = stmt;
      this.stmtMappings = stmtMappings;
   }

   public void storeBooleanField(int fieldNumber, boolean value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setBoolean(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeCharField(int fieldNumber, char value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setChar(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeByteField(int fieldNumber, byte value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setByte(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeShortField(int fieldNumber, short value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setShort(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeIntField(int fieldNumber, int value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setInt(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeLongField(int fieldNumber, long value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setLong(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeFloatField(int fieldNumber, float value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setFloat(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeDoubleField(int fieldNumber, double value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);

      for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
         mapIdx.getMapping().setDouble(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
      }

   }

   public void storeStringField(int fieldNumber, String value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);
      if (value == null && mapIdx.getMapping().getMemberMetaData().getNullValue() == NullValue.EXCEPTION) {
         throw new NucleusUserException(Localiser.msg("052400", new Object[]{mapIdx.getMapping().getMemberMetaData().getFullFieldName()}));
      } else {
         for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
            mapIdx.getMapping().setString(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
         }

      }
   }

   public void storeObjectField(int fieldNumber, Object value) {
      StatementMappingIndex mapIdx = this.stmtMappings.getMappingForMemberPosition(fieldNumber);
      if (value == null && mapIdx.getMapping().getMemberMetaData().getNullValue() == NullValue.EXCEPTION) {
         throw new NucleusUserException(Localiser.msg("052400", new Object[]{mapIdx.getMapping().getMemberMetaData().getFullFieldName()}));
      } else {
         try {
            JavaTypeMapping mapping = mapIdx.getMapping();
            boolean provideOwner = false;
            if (mapping instanceof EmbeddedPCMapping || mapping instanceof SerialisedPCMapping || mapping instanceof SerialisedReferenceMapping || mapping instanceof PersistableMapping || mapping instanceof InterfaceMapping) {
               provideOwner = true;
            }

            if (mapIdx.getNumberOfParameterOccurrences() > 0) {
               for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                  if (provideOwner) {
                     mapping.setObject(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value, this.op, fieldNumber);
                  } else {
                     mapping.setObject(this.ec, this.statement, mapIdx.getParameterPositionsForOccurrence(i), value);
                  }
               }
            } else if (provideOwner) {
               mapping.setObject(this.ec, this.statement, (int[])null, value, this.op, fieldNumber);
            } else {
               mapping.setObject(this.ec, this.statement, (int[])null, value);
            }

            AbstractMemberMetaData mmd = this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
            RelationType relationType = mmd.getRelationType(this.ec.getClassLoaderResolver());
            if (this.op.getClassMetaData().getSCOMutableMemberFlags()[fieldNumber]) {
               SCOUtils.wrapSCOField(this.op, fieldNumber, value, true);
            } else if (RelationType.isRelationSingleValued(relationType) && mmd.getEmbeddedMetaData() != null && mmd.getEmbeddedMetaData().getOwnerMember() != null) {
               this.op.updateOwnerFieldInEmbeddedField(fieldNumber, value);
            }
         } catch (NotYetFlushedException e) {
            if (this.op.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getNullValue() == NullValue.EXCEPTION) {
               throw e;
            }

            this.op.updateFieldAfterInsert(e.getPersistable(), fieldNumber);
         }

      }
   }
}
