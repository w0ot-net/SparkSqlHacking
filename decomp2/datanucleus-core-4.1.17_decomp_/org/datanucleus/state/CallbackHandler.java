package org.datanucleus.state;

public interface CallbackHandler {
   void setValidationListener(CallbackHandler var1);

   void postCreate(Object var1);

   void prePersist(Object var1);

   void preStore(Object var1);

   void postStore(Object var1);

   void preClear(Object var1);

   void postClear(Object var1);

   void preDelete(Object var1);

   void postDelete(Object var1);

   void preDirty(Object var1);

   void postDirty(Object var1);

   void postLoad(Object var1);

   void postRefresh(Object var1);

   void preDetach(Object var1);

   void postDetach(Object var1, Object var2);

   void preAttach(Object var1);

   void postAttach(Object var1, Object var2);

   void addListener(Object var1, Class[] var2);

   void removeListener(Object var1);

   void close();
}
