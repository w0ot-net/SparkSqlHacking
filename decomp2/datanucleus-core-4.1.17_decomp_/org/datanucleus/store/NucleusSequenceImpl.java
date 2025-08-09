package org.datanucleus.store;

import java.util.Properties;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.ExtensionMetaData;
import org.datanucleus.metadata.SequenceMetaData;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.valuegenerator.ValueGenerationConnectionProvider;
import org.datanucleus.store.valuegenerator.ValueGenerationManager;
import org.datanucleus.store.valuegenerator.ValueGenerator;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class NucleusSequenceImpl implements NucleusSequence {
   protected final StoreManager storeManager;
   protected final SequenceMetaData seqMetaData;
   protected ValueGenerator generator;
   protected final ExecutionContext ec;

   public NucleusSequenceImpl(ExecutionContext objectMgr, StoreManager storeMgr, SequenceMetaData seqmd) {
      this.ec = objectMgr;
      this.storeManager = storeMgr;
      this.seqMetaData = seqmd;
      this.setGenerator();
   }

   protected void setGenerator() {
      String valueGeneratorName = "sequence";
      Properties props = new Properties();
      ExtensionMetaData[] seqExtensions = this.seqMetaData.getExtensions();
      if (seqExtensions != null && seqExtensions.length > 0) {
         for(int i = 0; i < seqExtensions.length; ++i) {
            props.put(seqExtensions[i].getKey(), seqExtensions[i].getValue());
         }
      }

      props.put("sequence-name", this.seqMetaData.getDatastoreSequence());
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
            this.mconn = NucleusSequenceImpl.this.storeManager.getConnection(NucleusSequenceImpl.this.ec);
            return this.mconn;
         }

         public void releaseConnection() {
            this.mconn.release();
            this.mconn = null;
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
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("017003", this.seqMetaData.getName(), valueGeneratorName));
         }

      }
   }

   public String getName() {
      return this.seqMetaData.getName();
   }

   public void allocate(int additional) {
      this.generator.allocate(additional);
   }

   public Object next() {
      return this.generator.next();
   }

   public long nextValue() {
      return this.generator.nextValue();
   }

   public Object current() {
      return this.generator.current();
   }

   public long currentValue() {
      return this.generator.currentValue();
   }
}
