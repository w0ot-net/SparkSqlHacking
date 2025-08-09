package org.datanucleus.enhancement;

public interface Persistable {
   byte READ_WRITE_OK = 0;
   byte LOAD_REQUIRED = 1;
   byte READ_OK = -1;
   byte CHECK_READ = 1;
   byte MEDIATE_READ = 2;
   byte CHECK_WRITE = 4;
   byte MEDIATE_WRITE = 8;
   byte SERIALIZABLE = 16;

   ExecutionContextReference dnGetExecutionContext();

   void dnReplaceStateManager(StateManager var1) throws SecurityException;

   void dnProvideField(int var1);

   void dnProvideFields(int[] var1);

   void dnReplaceField(int var1);

   void dnReplaceFields(int[] var1);

   void dnReplaceFlags();

   void dnCopyFields(Object var1, int[] var2);

   void dnMakeDirty(String var1);

   Object dnGetObjectId();

   Object dnGetTransactionalObjectId();

   Object dnGetVersion();

   boolean dnIsDirty();

   boolean dnIsTransactional();

   boolean dnIsPersistent();

   boolean dnIsNew();

   boolean dnIsDeleted();

   boolean dnIsDetached();

   Persistable dnNewInstance(StateManager var1);

   Persistable dnNewInstance(StateManager var1, Object var2);

   Object dnNewObjectIdInstance();

   Object dnNewObjectIdInstance(Object var1);

   void dnCopyKeyFieldsToObjectId(Object var1);

   void dnCopyKeyFieldsToObjectId(ObjectIdFieldSupplier var1, Object var2);

   void dnCopyKeyFieldsFromObjectId(ObjectIdFieldConsumer var1, Object var2);

   public interface ObjectIdFieldConsumer {
      void storeBooleanField(int var1, boolean var2);

      void storeCharField(int var1, char var2);

      void storeByteField(int var1, byte var2);

      void storeShortField(int var1, short var2);

      void storeIntField(int var1, int var2);

      void storeLongField(int var1, long var2);

      void storeFloatField(int var1, float var2);

      void storeDoubleField(int var1, double var2);

      void storeStringField(int var1, String var2);

      void storeObjectField(int var1, Object var2);
   }

   public interface ObjectIdFieldManager extends ObjectIdFieldConsumer, ObjectIdFieldSupplier {
   }

   public interface ObjectIdFieldSupplier {
      boolean fetchBooleanField(int var1);

      char fetchCharField(int var1);

      byte fetchByteField(int var1);

      short fetchShortField(int var1);

      int fetchIntField(int var1);

      long fetchLongField(int var1);

      float fetchFloatField(int var1);

      double fetchDoubleField(int var1);

      String fetchStringField(int var1);

      Object fetchObjectField(int var1);
   }
}
