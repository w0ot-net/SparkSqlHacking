package org.apache.derby.impl.io.vfmem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.derby.io.StorageFile;

public final class DataStore {
   private static final char SEP;
   private static final String[] EMPTY_STR_ARR;
   private final Object LOCK = new Object();
   private final Object TMP_COUNTER_LOCK = new Object();
   private final Map files = new HashMap(80);
   private final String databaseName;
   private long tmpFileCounter = 0L;
   private boolean deleteMe;

   public DataStore(String var1) {
      this.databaseName = var1;
   }

   public String getDatabaseName() {
      return this.databaseName;
   }

   public boolean scheduledForDeletion() {
      return this.deleteMe;
   }

   public DataStoreEntry createEntry(String var1, boolean var2) {
      String var3 = (new File(var1)).getPath();
      synchronized(this.LOCK) {
         if (this.files.containsKey(var3)) {
            return null;
         } else {
            String[] var5 = this.getParentList(var3);

            for(int var6 = var5.length - 1; var6 >= 0; --var6) {
               DataStoreEntry var7 = (DataStoreEntry)this.files.get(var5[var6]);
               if (var7 == null) {
                  return null;
               }

               if (!var7.isDirectory()) {
                  return null;
               }
            }

            DataStoreEntry var10 = new DataStoreEntry(var3, var2);
            this.files.put(var3, var10);
            return var10;
         }
      }
   }

   public boolean createAllParents(String var1) {
      String var2 = (new File(var1)).getPath();
      String[] var3 = this.getParentList(var2);
      synchronized(this.LOCK) {
         for(int var5 = var3.length - 1; var5 >= 0; --var5) {
            String var6 = var3[var5];
            DataStoreEntry var7 = (DataStoreEntry)this.files.get(var6);
            if (var7 == null) {
               this.createEntry(var6, true);
            } else if (!var7.isDirectory()) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean deleteEntry(String var1) {
      String var2 = (new File(var1)).getPath();
      DataStoreEntry var3;
      synchronized(this.LOCK) {
         var3 = (DataStoreEntry)this.files.remove(var2);
         if (var3 != null) {
            if (var3.isDirectory()) {
               String[] var5 = this.listChildren(var2);
               if (var5.length > 0) {
                  this.files.put(var2, var3);
                  return false;
               }

               if (var2.equals(this.databaseName) && this.files.get(this.databaseName) == null) {
                  this.deleteMe = true;
               }
            }

            var3.release();
         }
      }

      return var3 != null;
   }

   public DataStoreEntry getEntry(String var1) {
      synchronized(this.LOCK) {
         return (DataStoreEntry)this.files.get((new File(var1)).getPath());
      }
   }

   public boolean deleteAll(String var1) {
      String var2 = (new File(var1)).getPath();
      synchronized(this.LOCK) {
         DataStoreEntry var4 = (DataStoreEntry)this.files.remove(var2);
         if (var4 == null) {
            return false;
         } else if (var4.isDirectory()) {
            boolean var5 = this._deleteAll(var2);
            if (this.files.get(this.databaseName) == null) {
               this.deleteMe = true;
            }

            return var5;
         } else {
            var4.release();
            return true;
         }
      }
   }

   public String[] listChildren(String var1) {
      if (var1.equals("")) {
         throw new IllegalArgumentException("The empty string is not a valid path");
      } else {
         String var2 = (new File(var1)).getPath();
         if (var2.charAt(var2.length() - 1) != SEP) {
            var2 = var2 + SEP;
         }

         ArrayList var3 = new ArrayList();
         synchronized(this.LOCK) {
            for(String var6 : this.files.keySet()) {
               if (var6.startsWith(var2)) {
                  var6 = var6.substring(var2.length());
                  if (var6.indexOf(PathUtil.SEP_STR) < 0) {
                     var3.add(var6);
                  }
               }
            }
         }

         return (String[])var3.toArray(EMPTY_STR_ARR);
      }
   }

   public boolean move(StorageFile var1, StorageFile var2) {
      String var3 = (new File(var1.getPath())).getPath();
      String var4 = (new File(var2.getPath())).getPath();
      synchronized(this.LOCK) {
         if (this.files.containsKey(var4)) {
            return false;
         } else {
            DataStoreEntry var6 = (DataStoreEntry)this.files.remove(var3);
            if (var6 == null) {
               return false;
            } else {
               this.files.put(var4, var6);
               return true;
            }
         }
      }
   }

   public void purge() {
      synchronized(this.LOCK) {
         Iterator var2 = this.files.values().iterator();

         while(var2.hasNext()) {
            ((DataStoreEntry)var2.next()).release();
         }

         this.files.clear();
      }
   }

   private boolean _deleteAll(String var1) {
      if (var1.charAt(var1.length() - 1) != SEP) {
         var1 = var1 + SEP;
      }

      ArrayList var2 = new ArrayList();

      for(String var4 : this.files.keySet()) {
         if (var4.startsWith(var1)) {
            var2.add(var4);
         }
      }

      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         DataStoreEntry var5 = (DataStoreEntry)this.files.remove((String)var6.next());
         var5.release();
      }

      return true;
   }

   public long getTempFileCounter() {
      synchronized(this.TMP_COUNTER_LOCK) {
         return ++this.tmpFileCounter;
      }
   }

   private String[] getParentList(String var1) {
      ArrayList var2 = new ArrayList();
      String var3 = var1;

      while((var3 = (new File(var3)).getParent()) != null) {
         var2.add(var3);
      }

      return (String[])var2.toArray(new String[var2.size()]);
   }

   static {
      SEP = PathUtil.SEP;
      EMPTY_STR_ARR = new String[0];
   }
}
