package org.datanucleus.store.rdbms.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.exceptions.NoSuchPersistentFieldException;
import org.datanucleus.store.rdbms.exceptions.PersistentSuperclassNotAllowedException;
import org.datanucleus.store.rdbms.exceptions.ViewDefinitionException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.MacroString;
import org.datanucleus.util.NucleusLogger;

public class ClassView extends ViewImpl implements DatastoreClass {
   private final ClassMetaData cmd;
   private final MacroString viewDef;
   private String createStatementDDL;
   private JavaTypeMapping[] fieldMappings;

   public ClassView(DatastoreIdentifier tableName, RDBMSStoreManager storeMgr, ClassMetaData cmd) {
      super(tableName, storeMgr);
      this.cmd = cmd;
      if (cmd.getIdentityType() != IdentityType.APPLICATION && cmd.getIdentityType() != IdentityType.DATASTORE) {
         if (cmd.getIdentityType() == IdentityType.NONDURABLE) {
         }

         if (cmd.getPersistableSuperclass() != null) {
            throw new PersistentSuperclassNotAllowedException(cmd.getFullClassName());
         } else {
            String viewImpStr = cmd.getValueForExtension("view-imports");
            String viewDefStr = null;
            if (this.dba.getVendorID() != null) {
               viewDefStr = cmd.getValueForExtension("view-definition-" + this.dba.getVendorID());
            }

            if (viewDefStr == null) {
               viewDefStr = cmd.getValueForExtension("view-definition");
            }

            if (viewDefStr == null) {
               throw new ViewDefinitionException(cmd.getFullClassName(), (String)null);
            } else {
               this.viewDef = new MacroString(cmd.getFullClassName(), viewImpStr, viewDefStr);
            }
         }
      } else {
         throw new NucleusUserException(Localiser.msg("031005", new Object[]{cmd.getFullClassName(), cmd.getIdentityType()}));
      }
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      int fieldCount = this.cmd.getNoOfManagedMembers();
      this.fieldMappings = new JavaTypeMapping[fieldCount];

      for(int fieldNumber = 0; fieldNumber < fieldCount; ++fieldNumber) {
         AbstractMemberMetaData fmd = this.cmd.getMetaDataForManagedMemberAtRelativePosition(fieldNumber);
         if (fmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
            this.fieldMappings[fieldNumber] = this.storeMgr.getMappingManager().getMapping(this, fmd, clr, FieldRole.ROLE_FIELD);
         } else if (fmd.getPersistenceModifier() != FieldPersistenceModifier.TRANSACTIONAL) {
            throw (new NucleusException(Localiser.msg("031006", new Object[]{this.cmd.getFullClassName(), fmd.getName(), fmd.getPersistenceModifier()}))).setFatal();
         }
      }

      if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
         NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("057023", new Object[]{this}));
      }

      this.storeMgr.registerTableInitialized(this);
      this.state = 2;
   }

   public void postInitialize(final ClassLoaderResolver clr) {
      this.assertIsInitialized();
      this.createStatementDDL = this.viewDef.substituteMacros(new MacroString.MacroHandler() {
         public void onIdentifierMacro(MacroString.IdentifierMacro im) {
            ClassView.this.storeMgr.resolveIdentifierMacro(im, clr);
         }

         public void onParameterMacro(MacroString.ParameterMacro pm) {
            throw new NucleusUserException(Localiser.msg("031009", new Object[]{ClassView.this.cmd.getFullClassName(), pm}));
         }
      }, clr);
   }

   public JavaTypeMapping getIdMapping() {
      for(int i = 0; i < this.fieldMappings.length; ++i) {
         if (this.fieldMappings[i] != null) {
            return this.fieldMappings[i];
         }
      }

      return null;
   }

   public DatastoreClass getBaseDatastoreClassWithMember(AbstractMemberMetaData mmd) {
      return null;
   }

   public DatastoreClass getSuperDatastoreClass() {
      return null;
   }

   public boolean isSuperDatastoreClass(DatastoreClass table) {
      return false;
   }

   public Collection getSecondaryDatastoreClasses() {
      return null;
   }

   public JavaTypeMapping getDatastoreIdMapping() {
      return null;
   }

   public boolean managesClass(String className) {
      return false;
   }

   public String[] getManagedClasses() {
      return null;
   }

   public boolean managesMapping(JavaTypeMapping mapping) {
      return false;
   }

   public AbstractMemberMetaData getFieldMetaData(String fieldName) {
      return this.cmd.getMetaDataForMember(fieldName);
   }

   public IdentityType getIdentityType() {
      return this.cmd.getIdentityType();
   }

   public boolean isBaseDatastoreClass() {
      return true;
   }

   public DatastoreClass getBaseDatastoreClass() {
      return this;
   }

   public boolean isObjectIdDatastoreAttributed() {
      return false;
   }

   public void provideDatastoreIdMappings(MappingConsumer consumer) {
   }

   public void provideDiscriminatorMappings(MappingConsumer consumer) {
   }

   public void provideMultitenancyMapping(MappingConsumer consumer) {
   }

   public void provideMappingsForMembers(MappingConsumer consumer, AbstractMemberMetaData[] fieldNumbers, boolean includeSecondaryTables) {
   }

   public void provideNonPrimaryKeyMappings(MappingConsumer consumer) {
   }

   public void providePrimaryKeyMappings(MappingConsumer consumer) {
   }

   public void provideVersionMappings(MappingConsumer consumer) {
   }

   public void provideExternalMappings(MappingConsumer consumer, int mappingType) {
   }

   public void provideUnmappedColumns(MappingConsumer consumer) {
   }

   public String getType() {
      return this.cmd.getFullClassName();
   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      this.assertIsInitialized();
      JavaTypeMapping m = this.fieldMappings[mmd.getAbsoluteFieldNumber()];
      if (m == null) {
         throw new NoSuchPersistentFieldException(this.cmd.getFullClassName(), mmd.getAbsoluteFieldNumber());
      } else {
         return m;
      }
   }

   public JavaTypeMapping getMemberMappingInDatastoreClass(AbstractMemberMetaData mmd) {
      return this.getMemberMapping(mmd);
   }

   public JavaTypeMapping getMemberMapping(String fieldName) {
      this.assertIsInitialized();
      int rfn = this.cmd.getRelativePositionOfMember(fieldName);
      if (rfn < 0) {
         throw new NoSuchPersistentFieldException(this.cmd.getFullClassName(), fieldName);
      } else {
         return this.getMemberMapping(this.cmd.getMetaDataForManagedMemberAtRelativePosition(rfn));
      }
   }

   protected List getSQLCreateStatements(Properties props) {
      this.assertIsInitialized();
      ArrayList stmts = new ArrayList();
      StringTokenizer tokens = new StringTokenizer(this.createStatementDDL, ";");

      while(tokens.hasMoreTokens()) {
         String token = tokens.nextToken();
         if (!token.startsWith("--")) {
            stmts.add(token);
         }
      }

      return stmts;
   }

   public final DiscriminatorMetaData getDiscriminatorMetaData() {
      return null;
   }

   public JavaTypeMapping getDiscriminatorMapping(boolean allowSuperclasses) {
      return null;
   }

   public final VersionMetaData getVersionMetaData() {
      return null;
   }

   public JavaTypeMapping getVersionMapping(boolean allowSuperclasses) {
      return null;
   }

   public JavaTypeMapping getExternalMapping(AbstractMemberMetaData fmd, int mappingType) {
      return null;
   }

   public AbstractMemberMetaData getMetaDataForExternalMapping(JavaTypeMapping mapping, int mappingType) {
      return null;
   }
}
