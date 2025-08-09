package org.datanucleus.store.rdbms.scostore;

import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.table.JoinTable;

public class BackingStoreHelper {
   public static int populateOwnerInStatement(ObjectProvider op, ExecutionContext ec, PreparedStatement ps, int jdbcPosition, BaseContainerStore bcs) {
      if (!bcs.getStoreManager().insertValuesOnInsert(bcs.getOwnerMapping().getDatastoreMapping(0))) {
         return jdbcPosition;
      } else {
         if (bcs.getOwnerMemberMetaData() != null) {
            bcs.getOwnerMapping().setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, bcs.getOwnerMapping()), op.getObject(), op, bcs.getOwnerMemberMetaData().getAbsoluteFieldNumber());
         } else {
            bcs.getOwnerMapping().setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, bcs.getOwnerMapping()), op.getObject());
         }

         return jdbcPosition + bcs.getOwnerMapping().getNumberOfDatastoreMappings();
      }
   }

   public static int populateRelationDiscriminatorInStatement(ExecutionContext ec, PreparedStatement ps, int jdbcPosition, ElementContainerStore ecs) {
      ecs.getRelationDiscriminatorMapping().setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, ecs.getRelationDiscriminatorMapping()), ecs.getRelationDiscriminatorValue());
      return jdbcPosition + ecs.getRelationDiscriminatorMapping().getNumberOfDatastoreMappings();
   }

   public static int populateOrderInStatement(ExecutionContext ec, PreparedStatement ps, int idx, int jdbcPosition, JavaTypeMapping orderMapping) {
      orderMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, orderMapping), idx);
      return jdbcPosition + orderMapping.getNumberOfDatastoreMappings();
   }

   public static int populateElementInStatement(ExecutionContext ec, PreparedStatement ps, Object element, int jdbcPosition, JavaTypeMapping elementMapping) {
      if (!elementMapping.getStoreManager().insertValuesOnInsert(elementMapping.getDatastoreMapping(0))) {
         return jdbcPosition;
      } else {
         elementMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, elementMapping), element);
         return jdbcPosition + elementMapping.getNumberOfDatastoreMappings();
      }
   }

   public static int populateElementForWhereClauseInStatement(ExecutionContext ec, PreparedStatement ps, Object element, int jdbcPosition, JavaTypeMapping elementMapping) {
      if (elementMapping.getStoreManager().insertValuesOnInsert(elementMapping.getDatastoreMapping(0))) {
         if (elementMapping instanceof ReferenceMapping && elementMapping.getNumberOfDatastoreMappings() > 1) {
            ReferenceMapping elemRefMapping = (ReferenceMapping)elementMapping;
            JavaTypeMapping[] elemFkMappings = elemRefMapping.getJavaTypeMapping();
            int[] positions = null;

            for(int i = 0; i < elemFkMappings.length; ++i) {
               if (elemFkMappings[i].getType().equals(element.getClass().getName())) {
                  positions = new int[elemFkMappings[i].getNumberOfDatastoreMappings()];

                  for(int j = 0; j < positions.length; ++j) {
                     positions[j] = jdbcPosition++;
                  }
               }
            }

            elementMapping.setObject(ec, ps, positions, element);
            jdbcPosition += positions.length;
         } else {
            elementMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, elementMapping), element);
            jdbcPosition += elementMapping.getNumberOfDatastoreMappings();
         }
      }

      return jdbcPosition;
   }

   public static int populateKeyInStatement(ExecutionContext ec, PreparedStatement ps, Object key, int jdbcPosition, JavaTypeMapping keyMapping) {
      if (!((AbstractDatastoreMapping)keyMapping.getDatastoreMapping(0)).insertValuesOnInsert()) {
         return jdbcPosition;
      } else {
         keyMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, keyMapping), key);
         return jdbcPosition + keyMapping.getNumberOfDatastoreMappings();
      }
   }

   public static int populateValueInStatement(ExecutionContext ec, PreparedStatement ps, Object value, int jdbcPosition, JavaTypeMapping valueMapping) {
      if (!((AbstractDatastoreMapping)valueMapping.getDatastoreMapping(0)).insertValuesOnInsert()) {
         return jdbcPosition;
      } else {
         valueMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, valueMapping), value);
         return jdbcPosition + valueMapping.getNumberOfDatastoreMappings();
      }
   }

   public static int populateElementDiscriminatorInStatement(ExecutionContext ec, PreparedStatement ps, int jdbcPosition, boolean includeSubclasses, ElementContainerStore.ElementInfo info, ClassLoaderResolver clr) {
      DiscriminatorStrategy strategy = info.getDiscriminatorStrategy();
      JavaTypeMapping discrimMapping = info.getDiscriminatorMapping();
      Class cls = clr.classForName(info.getClassName());
      if (!Modifier.isAbstract(cls.getModifiers())) {
         if (strategy == DiscriminatorStrategy.CLASS_NAME) {
            discrimMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, discrimMapping), info.getClassName());
            jdbcPosition += discrimMapping.getNumberOfDatastoreMappings();
         } else if (strategy == DiscriminatorStrategy.VALUE_MAP) {
            discrimMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, discrimMapping), info.getAbstractClassMetaData().getInheritanceMetaData().getDiscriminatorMetaData().getValue());
            jdbcPosition += discrimMapping.getNumberOfDatastoreMappings();
         }
      }

      if (includeSubclasses) {
         RDBMSStoreManager storeMgr = discrimMapping.getStoreManager();
         Collection<String> subclasses = storeMgr.getSubClassesForClass(info.getClassName(), true, clr);
         if (subclasses != null && subclasses.size() > 0) {
            for(String subclass : subclasses) {
               Class subcls = clr.classForName(subclass);
               if (!Modifier.isAbstract(subcls.getModifiers())) {
                  if (strategy == DiscriminatorStrategy.CLASS_NAME) {
                     discrimMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, discrimMapping), subclass);
                     jdbcPosition += discrimMapping.getNumberOfDatastoreMappings();
                  } else if (strategy == DiscriminatorStrategy.VALUE_MAP) {
                     AbstractClassMetaData subclassCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(subclass, clr);
                     discrimMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, discrimMapping), subclassCmd.getInheritanceMetaData().getDiscriminatorMetaData().getValue());
                     jdbcPosition += discrimMapping.getNumberOfDatastoreMappings();
                  }
               }
            }
         }
      }

      return jdbcPosition;
   }

   public static int populateEmbeddedElementFieldsInStatement(ObjectProvider op, Object element, PreparedStatement ps, int jdbcPosition, AbstractMemberMetaData ownerFieldMetaData, JavaTypeMapping elementMapping, AbstractClassMetaData emd, BaseContainerStore bcs) {
      EmbeddedElementPCMapping embeddedMapping = (EmbeddedElementPCMapping)elementMapping;
      StatementClassMapping mappingDefinition = new StatementClassMapping();
      int[] elementFieldNumbers = new int[embeddedMapping.getNumberOfJavaTypeMappings()];

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping fieldMapping = embeddedMapping.getJavaTypeMapping(i);
         int absFieldNum = emd.getAbsolutePositionOfMember(fieldMapping.getMemberMetaData().getName());
         elementFieldNumbers[i] = absFieldNum;
         StatementMappingIndex stmtMapping = new StatementMappingIndex(fieldMapping);
         int[] jdbcParamPositions = new int[fieldMapping.getNumberOfDatastoreMappings()];

         for(int j = 0; j < fieldMapping.getNumberOfDatastoreMappings(); ++j) {
            jdbcParamPositions[j] = jdbcPosition++;
         }

         stmtMapping.addParameterOccurrence(jdbcParamPositions);
         mappingDefinition.addMappingForMember(absFieldNum, stmtMapping);
      }

      ObjectProvider elementSM = bcs.getObjectProviderForEmbeddedPCObject(op, element, ownerFieldMetaData, (short)2);
      elementSM.provideFields(elementFieldNumbers, elementMapping.getStoreManager().getFieldManagerForStatementGeneration(elementSM, ps, mappingDefinition));
      return jdbcPosition;
   }

   public static int populateEmbeddedKeyFieldsInStatement(ObjectProvider op, Object key, PreparedStatement ps, int jdbcPosition, JoinTable joinTable, AbstractMapStore mapStore) {
      AbstractClassMetaData kmd = mapStore.getKmd();
      EmbeddedKeyPCMapping embeddedMapping = (EmbeddedKeyPCMapping)mapStore.getKeyMapping();
      StatementClassMapping mappingDefinition = new StatementClassMapping();
      int[] elementFieldNumbers = new int[embeddedMapping.getNumberOfJavaTypeMappings()];

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping fieldMapping = embeddedMapping.getJavaTypeMapping(i);
         int absFieldNum = kmd.getAbsolutePositionOfMember(fieldMapping.getMemberMetaData().getName());
         elementFieldNumbers[i] = absFieldNum;
         StatementMappingIndex stmtMapping = new StatementMappingIndex(fieldMapping);
         int[] jdbcParamPositions = new int[fieldMapping.getNumberOfDatastoreMappings()];

         for(int j = 0; j < fieldMapping.getNumberOfDatastoreMappings(); ++j) {
            jdbcParamPositions[j] = jdbcPosition++;
         }

         stmtMapping.addParameterOccurrence(jdbcParamPositions);
         mappingDefinition.addMappingForMember(absFieldNum, stmtMapping);
      }

      ObjectProvider elementSM = mapStore.getObjectProviderForEmbeddedPCObject(op, key, joinTable.getOwnerMemberMetaData(), (short)3);
      elementSM.provideFields(elementFieldNumbers, embeddedMapping.getStoreManager().getFieldManagerForStatementGeneration(elementSM, ps, mappingDefinition));
      return jdbcPosition;
   }

   public static int populateEmbeddedValueFieldsInStatement(ObjectProvider op, Object value, PreparedStatement ps, int jdbcPosition, JoinTable joinTable, AbstractMapStore mapStore) {
      AbstractClassMetaData vmd = mapStore.getVmd();
      EmbeddedValuePCMapping embeddedMapping = (EmbeddedValuePCMapping)mapStore.getValueMapping();
      StatementClassMapping mappingDefinition = new StatementClassMapping();
      int[] elementFieldNumbers = new int[embeddedMapping.getNumberOfJavaTypeMappings()];

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping fieldMapping = embeddedMapping.getJavaTypeMapping(i);
         int absFieldNum = vmd.getAbsolutePositionOfMember(fieldMapping.getMemberMetaData().getName());
         elementFieldNumbers[i] = absFieldNum;
         StatementMappingIndex stmtMapping = new StatementMappingIndex(fieldMapping);
         int[] jdbcParamPositions = new int[fieldMapping.getNumberOfDatastoreMappings()];

         for(int j = 0; j < fieldMapping.getNumberOfDatastoreMappings(); ++j) {
            jdbcParamPositions[j] = jdbcPosition++;
         }

         stmtMapping.addParameterOccurrence(jdbcParamPositions);
         mappingDefinition.addMappingForMember(absFieldNum, stmtMapping);
      }

      ObjectProvider elementSM = mapStore.getObjectProviderForEmbeddedPCObject(op, value, joinTable.getOwnerMemberMetaData(), (short)4);
      elementSM.provideFields(elementFieldNumbers, embeddedMapping.getStoreManager().getFieldManagerForStatementGeneration(elementSM, ps, mappingDefinition));
      return jdbcPosition;
   }

   public static void appendWhereClauseForElement(StringBuilder stmt, JavaTypeMapping elementMapping, Object element, boolean elementsSerialised, String containerAlias, boolean firstWhereClause) {
      if (!firstWhereClause) {
         stmt.append(" AND ");
      }

      if (elementMapping instanceof ReferenceMapping && elementMapping.getNumberOfDatastoreMappings() > 1) {
         for(int i = 0; i < elementMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(" AND ");
            }

            if (containerAlias != null) {
               stmt.append(containerAlias).append(".");
            }

            stmt.append(elementMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            if (((ReferenceMapping)elementMapping).getJavaTypeMapping()[i].getType().equals(element.getClass().getName())) {
               if (elementsSerialised) {
                  stmt.append(" LIKE ");
               } else {
                  stmt.append("=");
               }

               stmt.append(((AbstractDatastoreMapping)elementMapping.getDatastoreMapping(i)).getUpdateInputParameter());
            } else {
               stmt.append(" IS NULL");
            }
         }
      } else {
         for(int i = 0; i < elementMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(" AND ");
            }

            if (containerAlias != null) {
               stmt.append(containerAlias).append(".");
            }

            stmt.append(elementMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            if (elementsSerialised) {
               stmt.append(" LIKE ");
            } else {
               stmt.append("=");
            }

            stmt.append(((AbstractDatastoreMapping)elementMapping.getDatastoreMapping(i)).getUpdateInputParameter());
         }
      }

   }

   public static void appendWhereClauseForMapping(StringBuilder stmt, JavaTypeMapping mapping, String containerAlias, boolean firstWhereClause) {
      for(int i = 0; i < mapping.getNumberOfDatastoreMappings(); ++i) {
         if (!firstWhereClause || firstWhereClause && i > 0) {
            stmt.append(" AND ");
         }

         if (containerAlias != null) {
            stmt.append(containerAlias).append(".");
         }

         stmt.append(mapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append("=");
         stmt.append(((AbstractDatastoreMapping)mapping.getDatastoreMapping(i)).getInsertionInputParameter());
      }

   }
}
