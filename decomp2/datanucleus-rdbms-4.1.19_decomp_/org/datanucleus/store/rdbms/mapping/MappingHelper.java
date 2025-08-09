package org.datanucleus.store.rdbms.mapping;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class MappingHelper {
   public static int[] getMappingIndices(int initialPosition, JavaTypeMapping mapping) {
      if (mapping.getNumberOfDatastoreMappings() < 1) {
         return new int[]{initialPosition};
      } else {
         int[] parameter = new int[mapping.getNumberOfDatastoreMappings()];

         for(int i = 0; i < parameter.length; ++i) {
            parameter[i] = initialPosition + i;
         }

         return parameter;
      }
   }

   public static Object getObjectForDatastoreIdentity(ExecutionContext ec, JavaTypeMapping mapping, ResultSet rs, int[] resultIndexes, AbstractClassMetaData cmd) {
      Object oid = null;
      if (mapping.getNumberOfDatastoreMappings() > 0) {
         oid = mapping.getDatastoreMapping(0).getObject(rs, resultIndexes[0]);
      } else {
         if (mapping.getReferenceMapping() != null) {
            return mapping.getReferenceMapping().getObject(ec, rs, resultIndexes);
         }

         Class fieldType = mapping.getMemberMetaData().getType();
         JavaTypeMapping referenceMapping = mapping.getStoreManager().getDatastoreClass(fieldType.getName(), ec.getClassLoaderResolver()).getIdMapping();
         oid = referenceMapping.getDatastoreMapping(0).getObject(rs, resultIndexes[0]);
      }

      if (oid != null) {
         oid = ec.getNucleusContext().getIdentityManager().getDatastoreId(mapping.getType(), oid);
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("041034", new Object[]{oid}));
         }
      }

      ApiAdapter api = ec.getApiAdapter();
      if (api.isPersistable(oid)) {
         return oid;
      } else {
         return oid == null ? null : ec.findObject(oid, false, true, (String)null);
      }
   }

   public static Object getObjectForApplicationIdentity(final ExecutionContext ec, JavaTypeMapping mapping, ResultSet rs, int[] resultIndexes, AbstractClassMetaData cmd) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      if (((ClassMetaData)cmd).isAbstract() && cmd.getObjectidClass() != null) {
         return getObjectForAbstractClass(ec, mapping, rs, resultIndexes, cmd);
      } else {
         int totalFieldCount = cmd.getNoOfManagedMembers() + cmd.getNoOfInheritedManagedMembers();
         StatementMappingIndex[] statementExpressionIndex = new StatementMappingIndex[totalFieldCount];
         int paramIndex = 0;
         DatastoreClass datastoreClass = mapping.getStoreManager().getDatastoreClass(cmd.getFullClassName(), clr);
         final int[] pkFieldNumbers = cmd.getPKMemberPositions();

         for(int i = 0; i < pkFieldNumbers.length; ++i) {
            AbstractMemberMetaData fmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNumbers[i]);
            JavaTypeMapping m = datastoreClass.getMemberMapping(fmd);
            statementExpressionIndex[fmd.getAbsoluteFieldNumber()] = new StatementMappingIndex(m);
            int[] expressionsIndex = new int[m.getNumberOfDatastoreMappings()];

            for(int j = 0; j < expressionsIndex.length; ++j) {
               expressionsIndex[j] = resultIndexes[paramIndex++];
            }

            statementExpressionIndex[fmd.getAbsoluteFieldNumber()].setColumnPositions(expressionsIndex);
         }

         StatementClassMapping resultMappings = new StatementClassMapping();

         for(int i = 0; i < pkFieldNumbers.length; ++i) {
            resultMappings.addMappingForMember(pkFieldNumbers[i], statementExpressionIndex[pkFieldNumbers[i]]);
         }

         final FieldManager resultsFM = mapping.getStoreManager().getFieldManagerForResultProcessing(ec, rs, resultMappings, cmd);
         Object id = IdentityUtils.getApplicationIdentityForResultSetRow(ec, cmd, (Class)null, false, resultsFM);
         Class type = ec.getClassLoaderResolver().classForName(cmd.getFullClassName());
         return ec.findObject(id, new FieldValues() {
            public void fetchFields(ObjectProvider sm) {
               sm.replaceFields(pkFieldNumbers, resultsFM);
            }

            public void fetchNonLoadedFields(ObjectProvider sm) {
               sm.replaceNonLoadedFields(pkFieldNumbers, resultsFM);
            }

            public FetchPlan getFetchPlanForLoading() {
               return ec.getFetchPlan();
            }
         }, type, false, true);
      }
   }

   protected static Object createSingleFieldIdentity(ExecutionContext ec, JavaTypeMapping mapping, ResultSet rs, int[] param, AbstractClassMetaData cmd, Class objectIdClass, Class pcClass) {
      int paramNumber = param[0];

      try {
         Object idObj = mapping.getStoreManager().getResultValueAtPosition(rs, mapping, paramNumber);
         if (idObj == null) {
            throw (new NucleusException(Localiser.msg("041039"))).setFatal();
         } else {
            Class keyType = IdentityUtils.getKeyTypeForSingleFieldIdentityType(objectIdClass);
            idObj = ClassUtils.convertValue(idObj, keyType);
            return ec.getNucleusContext().getIdentityManager().getSingleFieldId(objectIdClass, pcClass, idObj);
         }
      } catch (Exception e) {
         NucleusLogger.PERSISTENCE.error(Localiser.msg("041036", new Object[]{cmd.getObjectidClass(), e}));
         return null;
      }
   }

   protected static Object createObjectIdInstanceReflection(ExecutionContext ec, JavaTypeMapping mapping, ResultSet rs, int[] param, AbstractClassMetaData cmd, Class objectIdClass) {
      Object fieldValue = null;

      try {
         Object id = objectIdClass.newInstance();
         int paramIndex = 0;
         int[] pkFieldNums = cmd.getPKMemberPositions();

         for(int i = 0; i < pkFieldNums.length; ++i) {
            AbstractMemberMetaData fmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkFieldNums[i]);
            Field field = objectIdClass.getField(fmd.getName());
            JavaTypeMapping m = mapping.getStoreManager().getDatastoreClass(cmd.getFullClassName(), ec.getClassLoaderResolver()).getMemberMapping(fmd);

            for(int j = 0; j < m.getNumberOfDatastoreMappings(); ++j) {
               Object obj = mapping.getStoreManager().getResultValueAtPosition(rs, mapping, param[paramIndex++]);
               if (obj instanceof BigDecimal) {
                  BigDecimal bigDecimal = (BigDecimal)obj;
                  Class keyType = IdentityUtils.getKeyTypeForSingleFieldIdentityType(field.getType());
                  obj = ClassUtils.convertValue(bigDecimal, keyType);
                  if (!bigDecimal.subtract(new BigDecimal("" + obj)).equals(new BigDecimal("0"))) {
                     throw (new NucleusException("Cannot convert retrieved BigInteger value to field of object id class!")).setFatal();
                  }
               }

               fieldValue = obj;
            }

            field.set(id, fieldValue);
         }

         return id;
      } catch (Exception e) {
         AbstractMemberMetaData mmd = mapping.getMemberMetaData();
         NucleusLogger.PERSISTENCE.error(Localiser.msg("041037", new Object[]{cmd.getObjectidClass(), mmd == null ? null : mmd.getName(), fieldValue, e}));
         return null;
      }
   }

   protected static Object getObjectForAbstractClass(ExecutionContext ec, JavaTypeMapping mapping, ResultSet rs, int[] resultIndexes, AbstractClassMetaData cmd) {
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      Class objectIdClass = clr.classForName(cmd.getObjectidClass());
      Class pcClass = clr.classForName(cmd.getFullClassName());
      Object id;
      if (cmd.usesSingleFieldIdentityClass()) {
         id = createSingleFieldIdentity(ec, mapping, rs, resultIndexes, cmd, objectIdClass, pcClass);
      } else {
         id = createObjectIdInstanceReflection(ec, mapping, rs, resultIndexes, cmd, objectIdClass);
      }

      return ec.findObject(id, false, true, (String)null);
   }
}
