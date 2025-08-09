package org.datanucleus.store.rdbms.mapping.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.TransactionEventListener;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.NucleusLogger;

public class SerialisedLocalFileMapping extends JavaTypeMapping implements MappingCallbacks {
   public static final String SERIALIZE_TO_FOLDER_EXTENSION = "serializeToFileLocation";
   String folderName = null;

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      super.initialize(mmd, table, clr);
      this.folderName = mmd.getValueForExtension("serializeToFileLocation");
      File folder = new File(this.folderName);
      if (!folder.exists()) {
         NucleusLogger.PERSISTENCE.debug("Creating folder for persistence data for field " + mmd.getFullFieldName() + " : folder=" + this.folderName);
         folder.mkdir();
      }

   }

   public boolean includeInFetchStatement() {
      return false;
   }

   public boolean includeInUpdateStatement() {
      return false;
   }

   public boolean includeInInsertStatement() {
      return false;
   }

   public Class getJavaType() {
      return this.mmd.getType();
   }

   public void insertPostProcessing(ObjectProvider op) {
   }

   public void postInsert(final ObjectProvider op) {
      Object val = op.provideField(this.mmd.getAbsoluteFieldNumber());
      this.serialiseFieldValue(op, val);
      if (op.getExecutionContext().getTransaction().isActive()) {
         op.getExecutionContext().getTransaction().addTransactionEventListener(new TransactionEventListener() {
            public void transactionPreRollBack() {
               File fieldFile = new File(SerialisedLocalFileMapping.this.getFilenameForObjectProvider(op));
               if (fieldFile.exists()) {
                  fieldFile.delete();
               }

            }

            public void transactionStarted() {
            }

            public void transactionRolledBack() {
            }

            public void transactionPreFlush() {
            }

            public void transactionPreCommit() {
            }

            public void transactionFlushed() {
            }

            public void transactionEnded() {
            }

            public void transactionCommitted() {
            }

            public void transactionSetSavepoint(String name) {
            }

            public void transactionReleaseSavepoint(String name) {
            }

            public void transactionRollbackToSavepoint(String name) {
            }
         });
      }

   }

   public void postFetch(ObjectProvider op) {
      Object value = this.deserialiseFieldValue(op);
      op.replaceField(this.mmd.getAbsoluteFieldNumber(), value);
   }

   public void postUpdate(final ObjectProvider op) {
      final Object oldValue = this.deserialiseFieldValue(op);
      Object val = op.provideField(this.mmd.getAbsoluteFieldNumber());
      this.serialiseFieldValue(op, val);
      if (op.getExecutionContext().getTransaction().isActive()) {
         op.getExecutionContext().getTransaction().addTransactionEventListener(new TransactionEventListener() {
            public void transactionPreRollBack() {
               SerialisedLocalFileMapping.this.serialiseFieldValue(op, oldValue);
            }

            public void transactionStarted() {
            }

            public void transactionRolledBack() {
            }

            public void transactionPreFlush() {
            }

            public void transactionPreCommit() {
            }

            public void transactionFlushed() {
            }

            public void transactionEnded() {
            }

            public void transactionCommitted() {
            }

            public void transactionSetSavepoint(String name) {
            }

            public void transactionReleaseSavepoint(String name) {
            }

            public void transactionRollbackToSavepoint(String name) {
            }
         });
      }

   }

   public void preDelete(final ObjectProvider op) {
      final Object oldValue = op.provideField(this.mmd.getAbsoluteFieldNumber());
      File fieldFile = new File(this.getFilenameForObjectProvider(op));
      if (fieldFile.exists()) {
         fieldFile.delete();
      }

      if (op.getExecutionContext().getTransaction().isActive()) {
         op.getExecutionContext().getTransaction().addTransactionEventListener(new TransactionEventListener() {
            public void transactionPreRollBack() {
               SerialisedLocalFileMapping.this.serialiseFieldValue(op, oldValue);
            }

            public void transactionStarted() {
            }

            public void transactionRolledBack() {
            }

            public void transactionPreFlush() {
            }

            public void transactionPreCommit() {
            }

            public void transactionFlushed() {
            }

            public void transactionEnded() {
            }

            public void transactionCommitted() {
            }

            public void transactionSetSavepoint(String name) {
            }

            public void transactionReleaseSavepoint(String name) {
            }

            public void transactionRollbackToSavepoint(String name) {
            }
         });
      }

   }

   protected String getFilenameForObjectProvider(ObjectProvider op) {
      return this.folderName + System.getProperty("file.separator") + op.getInternalObjectId();
   }

   protected void serialiseFieldValue(ObjectProvider op, Object value) {
      try {
         FileOutputStream fileOut = new FileOutputStream(this.getFilenameForObjectProvider(op));
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(value);
         out.close();
         fileOut.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

   }

   protected Object deserialiseFieldValue(ObjectProvider op) {
      Object value = null;

      try {
         FileInputStream fileIn = new FileInputStream(this.getFilenameForObjectProvider(op));
         ObjectInputStream in = new ObjectInputStream(fileIn);
         value = in.readObject();
         in.close();
         fileIn.close();
         return value;
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }
}
