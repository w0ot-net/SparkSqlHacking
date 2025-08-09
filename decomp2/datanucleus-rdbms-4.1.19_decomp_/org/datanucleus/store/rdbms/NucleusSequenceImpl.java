package org.datanucleus.store.rdbms;

import java.util.Properties;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.valuegenerator.ValueGenerationConnectionProvider;
import org.datanucleus.store.valuegenerator.ValueGenerationManager;
import org.datanucleus.transaction.TransactionUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class NucleusSequenceImpl extends org.datanucleus.store.NucleusSequenceImpl {
   public NucleusSequenceImpl(ExecutionContext objectMgr, RDBMSStoreManager storeMgr, SequenceMetaData seqmd) {
      super(objectMgr, storeMgr, seqmd);
   }

   public void setGenerator() {
      String valueGeneratorName = null;
      if (((RDBMSStoreManager)this.storeManager).getDatastoreAdapter().supportsOption("Sequences")) {
         valueGeneratorName = "sequence";
      } else {
         valueGeneratorName = "table-sequence";
      }

      Properties props = new Properties();
      ExtensionMetaData[] seqExtensions = this.seqMetaData.getExtensions();
      if (seqExtensions != null && seqExtensions.length > 0) {
         for(int i = 0; i < seqExtensions.length; ++i) {
            props.put(seqExtensions[i].getKey(), seqExtensions[i].getValue());
         }
      }

      props.put("sequence-name", this.seqMetaData.getDatastoreSequence());
      if (this.seqMetaData.getAllocationSize() > 0) {
         props.put("key-increment-by", "" + this.seqMetaData.getAllocationSize());
         props.put("key-cache-size", "" + this.seqMetaData.getAllocationSize());
      }

      if (this.seqMetaData.getInitialValue() > 0) {
         props.put("key-initial-value", "" + this.seqMetaData.getInitialValue());
      }

      ValueGenerationManager mgr = this.storeManager.getValueGenerationManager();
      ValueGenerationConnectionProvider connProvider = new ValueGenerationConnectionProvider() {
         ManagedConnection mconn;

         public ManagedConnection retrieveConnection() {
            Configuration conf = NucleusSequenceImpl.this.ec.getNucleusContext().getConfiguration();
            int isolationLevel = TransactionUtils.getTransactionIsolationLevelForName(conf.getStringProperty("datanucleus.valuegeneration.transactionIsolation"));
            this.mconn = ((RDBMSStoreManager)NucleusSequenceImpl.this.storeManager).getConnection(isolationLevel);
            return this.mconn;
         }

         public void releaseConnection() {
            try {
               this.mconn.release();
            } catch (NucleusException e) {
               NucleusLogger.PERSISTENCE.error(Localiser.msg("017007", new Object[]{e}));
               throw e;
            }
         }
      };
      Class cls = null;
      ConfigurationElement elem = this.ec.getNucleusContext().getPluginManager().getConfigurationElementForExtension("org.datanucleus.store_valuegenerator", new String[]{"name", "datastore"}, new String[]{valueGeneratorName, this.storeManager.getStoreManagerKey()});
      if (elem != null) {
         cls = this.ec.getNucleusContext().getPluginManager().loadClass(elem.getExtension().getPlugin().getSymbolicName(), elem.getAttribute("class-name"));
      }

      if (cls == null) {
         throw new NucleusException("Cannot create ValueGenerator for strategy " + valueGeneratorName);
      } else {
         this.generator = mgr.createValueGenerator(this.seqMetaData.getName(), cls, props, this.storeManager, connProvider);
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("017003", new Object[]{this.seqMetaData.getName(), valueGeneratorName}));
         }

      }
   }
}
