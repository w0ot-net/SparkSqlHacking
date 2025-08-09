package org.datanucleus.enhancement;

public interface StateManager {
   ExecutionContextReference getExecutionContext(Persistable var1);

   byte replacingFlags(Persistable var1);

   StateManager replacingStateManager(Persistable var1, StateManager var2);

   boolean isDirty(Persistable var1);

   boolean isTransactional(Persistable var1);

   boolean isPersistent(Persistable var1);

   boolean isNew(Persistable var1);

   boolean isDeleted(Persistable var1);

   void makeDirty(Persistable var1, String var2);

   Object getObjectId(Persistable var1);

   Object getTransactionalObjectId(Persistable var1);

   Object getVersion(Persistable var1);

   boolean isLoaded(Persistable var1, int var2);

   void preSerialize(Persistable var1);

   boolean getBooleanField(Persistable var1, int var2, boolean var3);

   char getCharField(Persistable var1, int var2, char var3);

   byte getByteField(Persistable var1, int var2, byte var3);

   short getShortField(Persistable var1, int var2, short var3);

   int getIntField(Persistable var1, int var2, int var3);

   long getLongField(Persistable var1, int var2, long var3);

   float getFloatField(Persistable var1, int var2, float var3);

   double getDoubleField(Persistable var1, int var2, double var3);

   String getStringField(Persistable var1, int var2, String var3);

   Object getObjectField(Persistable var1, int var2, Object var3);

   void setBooleanField(Persistable var1, int var2, boolean var3, boolean var4);

   void setCharField(Persistable var1, int var2, char var3, char var4);

   void setByteField(Persistable var1, int var2, byte var3, byte var4);

   void setShortField(Persistable var1, int var2, short var3, short var4);

   void setIntField(Persistable var1, int var2, int var3, int var4);

   void setLongField(Persistable var1, int var2, long var3, long var5);

   void setFloatField(Persistable var1, int var2, float var3, float var4);

   void setDoubleField(Persistable var1, int var2, double var3, double var5);

   void setStringField(Persistable var1, int var2, String var3, String var4);

   void setObjectField(Persistable var1, int var2, Object var3, Object var4);

   void providedBooleanField(Persistable var1, int var2, boolean var3);

   void providedCharField(Persistable var1, int var2, char var3);

   void providedByteField(Persistable var1, int var2, byte var3);

   void providedShortField(Persistable var1, int var2, short var3);

   void providedIntField(Persistable var1, int var2, int var3);

   void providedLongField(Persistable var1, int var2, long var3);

   void providedFloatField(Persistable var1, int var2, float var3);

   void providedDoubleField(Persistable var1, int var2, double var3);

   void providedStringField(Persistable var1, int var2, String var3);

   void providedObjectField(Persistable var1, int var2, Object var3);

   boolean replacingBooleanField(Persistable var1, int var2);

   char replacingCharField(Persistable var1, int var2);

   byte replacingByteField(Persistable var1, int var2);

   short replacingShortField(Persistable var1, int var2);

   int replacingIntField(Persistable var1, int var2);

   long replacingLongField(Persistable var1, int var2);

   float replacingFloatField(Persistable var1, int var2);

   double replacingDoubleField(Persistable var1, int var2);

   String replacingStringField(Persistable var1, int var2);

   Object replacingObjectField(Persistable var1, int var2);

   Object[] replacingDetachedState(Detachable var1, Object[] var2);
}
