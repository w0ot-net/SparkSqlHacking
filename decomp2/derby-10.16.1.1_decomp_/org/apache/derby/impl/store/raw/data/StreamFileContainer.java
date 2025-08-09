package org.apache.derby.impl.store.raw.data;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.Externalizable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.services.io.FormatIdInputStream;
import org.apache.derby.iapi.services.io.FormatIdOutputStream;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.LimitInputStream;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.services.io.TypedFormat;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.StreamContainerHandle;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

class StreamFileContainer implements TypedFormat {
   protected static int formatIdInteger = 290;
   protected static final int LARGE_SLOT_SIZE = 4;
   protected static final int MIN_BUFFER_SIZE = 1024;
   protected static final int FIELD_STATUS = StoredFieldHeader.setFixed(StoredFieldHeader.setInitial(), true);
   protected static final int FIELD_HEADER_SIZE;
   protected ContainerKey identity;
   private BaseDataFileFactory dataFactory;
   private int bufferSize;
   private StorageFile file;
   private OutputStream fileOut;
   private DynamicByteArrayOutputStream out;
   private FormatIdOutputStream logicalDataOut;
   private InputStream fileIn;
   private BufferedInputStream bufferedIn;
   private DecryptInputStream decryptIn;
   private LimitInputStream limitIn;
   private FormatIdInputStream logicalDataIn;
   private StoredRecordHeader recordHeader;
   private byte[] ciphertext;
   private byte[] zeroBytes;
   private static final int STORAGE_FILE_EXISTS_ACTION = 1;
   private static final int STORAGE_FILE_DELETE_ACTION = 2;
   private static final int STORAGE_FILE_MKDIRS_ACTION = 3;
   private static final int STORAGE_FILE_GET_OUTPUT_STREAM_ACTION = 4;
   private static final int STORAGE_FILE_GET_INPUT_STREAM_ACTION = 5;
   private int actionCode;
   private StorageFile actionStorageFile;

   StreamFileContainer(ContainerKey var1, BaseDataFileFactory var2) {
      this.identity = var1;
      this.dataFactory = var2;
   }

   StreamFileContainer(ContainerKey var1, BaseDataFileFactory var2, Properties var3) throws StandardException {
      this.identity = var1;
      this.dataFactory = var2;
      this.file = this.getFileName(var1, true, false);
      if (this.privExists(this.file)) {
         throw StandardException.newException("XSDF0.S", new Object[]{this.file});
      } else {
         this.getContainerProperties(var3);
      }
   }

   protected StreamFileContainer open(boolean var1) throws StandardException {
      this.file = this.getFileName(this.identity, false, true);
      if (!this.privExists(this.file)) {
         return null;
      } else {
         try {
            if (!var1) {
               this.fileIn = this.privGetInputStream(this.file);
               if (this.dataFactory.databaseEncrypted()) {
                  MemByteHolder var2 = new MemByteHolder(16384);
                  this.decryptIn = new DecryptInputStream(this.fileIn, var2, this.dataFactory);
                  this.limitIn = new LimitInputStream(this.decryptIn);
               } else {
                  this.bufferedIn = new BufferedInputStream(this.fileIn, 16384);
                  this.limitIn = new LimitInputStream(this.bufferedIn);
               }

               this.logicalDataIn = new FormatIdInputStream(this.limitIn);
               this.recordHeader = new StoredRecordHeader();
               this.recordHeader.read(this.logicalDataIn);
               return this;
            } else {
               return null;
            }
         } catch (IOException var3) {
            throw StandardException.newException("XSDF1.S", var3, new Object[]{this.file});
         }
      }
   }

   protected void close() {
      try {
         if (this.fileIn != null) {
            this.fileIn.close();
            this.fileIn = null;
            if (this.dataFactory.databaseEncrypted()) {
               this.decryptIn.close();
               this.decryptIn = null;
            } else {
               this.bufferedIn.close();
               this.bufferedIn = null;
            }

            this.logicalDataIn.close();
            this.logicalDataIn = null;
         }

         if (this.fileOut != null) {
            this.fileOut.close();
            this.logicalDataOut.close();
            this.fileOut = null;
            this.logicalDataOut = null;
            this.out = null;
         }
      } catch (IOException var2) {
      }

   }

   public int getTypeFormatId() {
      return 290;
   }

   public void getContainerProperties(Properties var1) throws StandardException {
      AccessFactory var2 = (AccessFactory)getServiceModule(this.dataFactory, "org.apache.derby.iapi.store.access.AccessFactory");
      TransactionController var3 = var2 == null ? null : var2.getTransaction(getContextService().getCurrentContextManager());
      this.bufferSize = PropertyUtil.getServiceInt(var3, var1, "derby.storage.streamFileBufferSize", 1024, Integer.MAX_VALUE, 16384);
   }

   public ContainerKey getIdentity() {
      return this.identity;
   }

   protected boolean use(StreamContainerHandle var1) throws StandardException {
      return true;
   }

   public void load(RowSource var1) throws StandardException {
      this.out = new DynamicByteArrayOutputStream(this.bufferSize);
      this.logicalDataOut = new FormatIdOutputStream(this.out);
      boolean var2 = this.dataFactory.databaseEncrypted();
      if (var2) {
         if (this.zeroBytes == null) {
            this.zeroBytes = new byte[this.dataFactory.getEncryptionBlockSize() - 1];
         }

         this.out.write(this.zeroBytes, 0, this.dataFactory.getEncryptionBlockSize() - 1);
      }

      try {
         this.fileOut = this.privGetOutputStream(this.file);
         FormatableBitSet var3 = var1.getValidColumns();
         DataValueDescriptor[] var4 = var1.getNextRowFromRowSource();
         int var5 = 0;
         if (var3 != null) {
            for(int var6 = var3.getLength() - 1; var6 >= 0; --var6) {
               if (var3.isSet(var6)) {
                  var5 = var6 + 1;
                  break;
               }
            }
         } else {
            var5 = var4.length;
         }

         this.recordHeader = new StoredRecordHeader(0, var5);
         int var16 = this.recordHeader.write(this.out);

         for(int var7 = var3 == null ? 0 : var3.getLength(); var4 != null; var4 = var1.getNextRowFromRowSource()) {
            int var8 = -1;

            for(int var9 = 0; var9 < var5; ++var9) {
               if (var3 == null) {
                  ++var8;
                  DataValueDescriptor var10 = var4[var8];
                  this.writeColumn(var10);
               } else if (var7 > var9 && var3.isSet(var9)) {
                  ++var8;
                  DataValueDescriptor var17 = var4[var8];
                  this.writeColumn(var17);
               } else {
                  this.writeColumn((Object)null);
               }

               if (this.out.getUsed() >= this.bufferSize || this.bufferSize - this.out.getUsed() < 1024) {
                  this.writeToFile();
               }
            }
         }

         if (var2) {
            if (this.out.getUsed() > this.dataFactory.getEncryptionBlockSize() - 1) {
               this.writeToFile();
            }
         } else if (this.out.getUsed() > 0) {
            this.writeToFile();
         }
      } catch (IOException var14) {
         throw StandardException.newException("XSDA4.S", var14, new Object[0]);
      } finally {
         this.close();
      }

   }

   private void writeToFile() throws StandardException {
      try {
         if (this.dataFactory.databaseEncrypted()) {
            int var1 = this.out.getUsed() - (this.dataFactory.getEncryptionBlockSize() - 1);
            int var2 = var1 % this.dataFactory.getEncryptionBlockSize();
            int var3 = var2 == 0 ? 0 : this.dataFactory.getEncryptionBlockSize() - var2;
            int var4 = var2 == 0 ? this.dataFactory.getEncryptionBlockSize() - 1 : var2 - 1;
            int var5 = var1 + var3;
            if (var1 <= 0) {
               return;
            }

            if (this.ciphertext == null) {
               this.ciphertext = new byte[var5];
            } else if (this.ciphertext.length < var5) {
               this.ciphertext = new byte[var5];
            }

            this.dataFactory.encrypt(this.out.getByteArray(), var4, var5, this.ciphertext, 0, false);
            CompressedNumber.writeInt(this.fileOut, var1);
            this.dataFactory.writeInProgress();

            try {
               this.fileOut.write(this.ciphertext, 0, var5);
            } finally {
               this.dataFactory.writeFinished();
            }

            this.out.reset();
            if (this.dataFactory.databaseEncrypted()) {
               if (this.zeroBytes == null) {
                  this.zeroBytes = new byte[this.dataFactory.getEncryptionBlockSize() - 1];
               }

               this.out.write(this.zeroBytes, 0, this.dataFactory.getEncryptionBlockSize() - 1);
            }
         } else {
            if (this.out.getUsed() == 0) {
               return;
            }

            this.dataFactory.writeInProgress();

            try {
               this.fileOut.write(this.out.getByteArray(), 0, this.out.getUsed());
            } finally {
               this.dataFactory.writeFinished();
            }

            this.out.reset();
         }

      } catch (IOException var16) {
         throw StandardException.newException("XSDA4.S", var16, new Object[0]);
      }
   }

   private void writeColumn(Object var1) throws StandardException, IOException {
      int var2 = FIELD_STATUS;
      if (var1 == null) {
         var2 = StoredFieldHeader.setNonexistent(var2);
         StoredFieldHeader.write(this.out, var2, 0, 4);
      } else {
         if (var1 instanceof Storable) {
            Storable var3 = (Storable)var1;
            if (var3.isNull()) {
               var2 = StoredFieldHeader.setNull(var2, true);
               StoredFieldHeader.write(this.out, var2, 0, 4);
               return;
            }
         }

         int var11 = this.out.getPosition();
         int var4 = 0;
         StoredFieldHeader.write(this.out, var2, var4, 4);
         if (var1 instanceof StreamStorable && ((StreamStorable)var1).returnStream() != null) {
            var1 = ((StreamStorable)var1).returnStream();
         }

         if (var1 instanceof InputStream) {
            InputStream var5 = (InputStream)var1;
            int var6 = Math.min(Math.max(var5.available(), 64), 8192);
            byte[] var7 = new byte[var6];

            while(true) {
               int var8 = var5.read(var7);
               if (var8 == -1) {
                  break;
               }

               var4 += var8;
               this.out.write(var7, 0, var8);
            }
         } else if (var1 instanceof Storable) {
            Storable var12 = (Storable)var1;
            var12.writeExternal(this.logicalDataOut);
            var4 = this.out.getPosition() - var11 - FIELD_HEADER_SIZE;
         } else {
            this.logicalDataOut.writeObject(var1);
            var4 = this.out.getPosition() - var11 - FIELD_HEADER_SIZE;
         }

         int var13 = this.out.getPosition();
         this.out.setPosition(var11);
         StoredFieldHeader.write(this.out, var2, var4, 4);
         if (!StoredFieldHeader.isNull(var2)) {
            this.out.setPosition(var13);
         }

      }
   }

   public boolean fetchNext(Object[] var1) throws StandardException {
      boolean var2 = false;
      boolean var3 = false;

      try {
         int var4 = this.recordHeader.getNumberFields();
         int var5 = 0;

         for(int var20 = 0; var20 < var4 && var5 < var1.length; ++var20) {
            this.limitIn.clearLimit();
            int var6 = StoredFieldHeader.readStatus(this.logicalDataIn);
            int var7 = StoredFieldHeader.readFieldDataLength(this.logicalDataIn, var6, 4);
            this.limitIn.setLimit(var7);
            Object var8 = var1[var5];
            if (StoredFieldHeader.isNullable(var6)) {
               if (var8 == null) {
                  throw StandardException.newException("XSDA6.S", new Object[]{Integer.toString(var20)});
               }

               if (!(var8 instanceof Storable)) {
                  throw StandardException.newException("XSDA6.S", new Object[]{var8.getClass().getName()});
               }

               Storable var9 = (Storable)var8;
               if (StoredFieldHeader.isNull(var6)) {
                  var9.restoreToNull();
                  ++var5;
               } else {
                  var2 = true;
                  var9.readExternal(this.logicalDataIn);
                  var2 = false;
                  ++var5;
               }
            } else {
               if (StoredFieldHeader.isNull(var6)) {
                  throw StandardException.newException("XSDA6.S", new Object[]{Integer.toString(var20)});
               }

               Object var21 = var1[var5];
               if (var21 instanceof Externalizable) {
                  Externalizable var10 = (Externalizable)var21;
                  var2 = true;
                  var10.readExternal(this.logicalDataIn);
                  var2 = false;
                  ++var5;
               } else {
                  var21 = null;
                  var2 = true;
                  var1[var5] = this.logicalDataIn.readObject();
                  var2 = false;
                  ++var5;
               }
            }
         }

         return true;
      } catch (IOException var11) {
         if (var2) {
            if (var11 instanceof EOFException) {
               throw StandardException.newException("XSDA7.S", var11, new Object[]{this.logicalDataIn.getErrorInfo()});
            } else {
               throw StandardException.newException("XSDA8.S", var11, new Object[]{this.logicalDataIn.getErrorInfo()});
            }
         } else if (var11 instanceof InvalidClassException) {
            throw StandardException.newException("XSDA8.S", var11, new Object[]{this.logicalDataIn.getErrorInfo()});
         } else if (var11 instanceof EOFException && !var3) {
            this.close();
            return false;
         } else {
            throw this.dataFactory.markCorrupt(StandardException.newException("XSDB9.D", var11, new Object[]{this.identity}));
         }
      } catch (ClassNotFoundException var12) {
         throw StandardException.newException("XSDA9.S", var12, new Object[]{this.logicalDataIn.getErrorInfo()});
      } catch (LinkageError var13) {
         if (var2) {
            throw StandardException.newException("XSDA8.S", var13, new Object[]{this.logicalDataIn.getErrorInfo()});
         } else {
            throw var13;
         }
      }
   }

   public boolean removeContainer() throws StandardException {
      this.close();
      return this.privExists(this.file) ? this.privDelete(this.file) : true;
   }

   protected StorageFile getFileName(ContainerKey var1, boolean var2, boolean var3) throws StandardException {
      if (var1.getSegmentId() == -1L) {
         return this.dataFactory.storageFactory.newStorageFile(this.dataFactory.storageFactory.getTempDir(), "T" + var1.getContainerId() + ".tmp");
      } else {
         StorageFile var4 = this.dataFactory.getContainerPath(var1, false);
         if (!this.privExists(var4)) {
            if (!var2) {
               return null;
            }

            StorageFile var5 = var4.getParentDir();
            if (!this.privExists(var5)) {
               synchronized(this.dataFactory) {
                  if (!this.privExists(var5)) {
                     boolean var7 = false;
                     IOException var8 = null;

                     try {
                        var7 = this.privMkdirs(var5);
                     } catch (IOException var11) {
                        var8 = var11;
                     }

                     if (!var7) {
                        if (var3) {
                           return null;
                        }

                        throw StandardException.newException("XSDF3.S", var8, new Object[]{var5});
                     }
                  }
               }
            }
         }

         return var4;
      }
   }

   private synchronized boolean privExists(StorageFile var1) {
      this.actionCode = 1;
      this.actionStorageFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (IOException var7) {
         var3 = false;
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private synchronized boolean privMkdirs(StorageFile var1) throws IOException {
      this.actionCode = 3;
      this.actionStorageFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private synchronized boolean privDelete(StorageFile var1) {
      this.actionCode = 2;
      this.actionStorageFile = var1;

      boolean var3;
      try {
         Object var2 = this.run();
         var3 = (Boolean)var2;
         return var3;
      } catch (IOException var7) {
         var3 = false;
      } finally {
         this.actionStorageFile = null;
      }

      return var3;
   }

   private synchronized OutputStream privGetOutputStream(StorageFile var1) throws FileNotFoundException {
      this.actionCode = 4;
      this.actionStorageFile = var1;

      OutputStream var2;
      try {
         var2 = (OutputStream)this.run();
      } catch (IOException var6) {
         throw (FileNotFoundException)var6;
      } finally {
         this.actionStorageFile = null;
      }

      return var2;
   }

   private synchronized InputStream privGetInputStream(StorageFile var1) throws FileNotFoundException {
      this.actionCode = 5;
      this.actionStorageFile = var1;

      InputStream var2;
      try {
         var2 = (InputStream)this.run();
      } catch (IOException var6) {
         throw (FileNotFoundException)var6;
      } finally {
         this.actionStorageFile = null;
      }

      return var2;
   }

   public Object run() throws IOException {
      switch (this.actionCode) {
         case 1:
            return this.actionStorageFile.exists();
         case 2:
            return this.actionStorageFile.delete();
         case 3:
            boolean var1 = this.actionStorageFile.mkdirs();
            this.actionStorageFile.limitAccessToOwner();
            return var1;
         case 4:
            return this.actionStorageFile.getOutputStream();
         case 5:
            return this.actionStorageFile.getInputStream();
         default:
            return null;
      }
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Object getServiceModule(Object var0, String var1) {
      return Monitor.getServiceModule(var0, var1);
   }

   static {
      FIELD_HEADER_SIZE = StoredFieldHeader.size(FIELD_STATUS, 0, 4);
   }
}
