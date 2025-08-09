package org.datanucleus.cache;

import java.util.Collection;
import org.datanucleus.NucleusContext;

public class NullLevel2Cache extends AbstractLevel2Cache {
   private static final long serialVersionUID = -218917474395656038L;

   public NullLevel2Cache(NucleusContext nucleusCtx) {
      super(nucleusCtx);
   }

   public void close() {
   }

   public void evict(Object oid) {
   }

   public void evictAll() {
   }

   public void evictAll(Class pcClass, boolean subclasses) {
   }

   public void evictAll(Collection oids) {
   }

   public void evictAll(Object[] oids) {
   }

   public void pin(Object oid) {
   }

   public void pinAll(Class pcClass, boolean subclasses) {
   }

   public void pinAll(Collection oids) {
   }

   public void pinAll(Object[] oids) {
   }

   public void unpin(Object oid) {
   }

   public void unpinAll(Class pcClass, boolean subclasses) {
   }

   public void unpinAll(Collection oids) {
   }

   public void unpinAll(Object[] oids) {
   }

   public boolean containsOid(Object oid) {
      return false;
   }

   public CachedPC get(Object oid) {
      return null;
   }

   public int getNumberOfPinnedObjects() {
      return 0;
   }

   public int getNumberOfUnpinnedObjects() {
      return 0;
   }

   public int getSize() {
      return 0;
   }

   public boolean isEmpty() {
      return false;
   }

   public CachedPC put(Object oid, CachedPC pc) {
      return null;
   }
}
