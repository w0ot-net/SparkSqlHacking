package org.apache.hadoop.hive.metastore;

import com.google.common.collect.ImmutableMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.hive.common.util.HiveVersionInfo;

public class MetaStoreSchemaInfo {
   private static final String SQL_FILE_EXTENSION = ".sql";
   private static final String UPGRADE_FILE_PREFIX = "upgrade-";
   private static final String INIT_FILE_PREFIX = "hive-schema-";
   private static final String VERSION_UPGRADE_LIST = "upgrade.order";
   private static final String PRE_UPGRADE_PREFIX = "pre-";
   private final String dbType;
   private final String[] hiveSchemaVersions;
   private final String hiveHome;
   private static final Map EQUIVALENT_VERSIONS = ImmutableMap.of("0.13.1", "0.13.0", "1.0.0", "0.14.0", "1.0.1", "1.0.0", "1.1.1", "1.1.0", "1.2.1", "1.2.0");

   public MetaStoreSchemaInfo(String hiveHome, String dbType) throws HiveMetaException {
      this.hiveHome = hiveHome;
      this.dbType = dbType;
      List<String> upgradeOrderList = new ArrayList();
      String upgradeListFile = this.getMetaStoreScriptDir() + File.separator + "upgrade.order" + "." + dbType;

      try {
         FileReader fr = new FileReader(upgradeListFile);
         Throwable var6 = null;

         try {
            BufferedReader bfReader = new BufferedReader(fr);
            Throwable var8 = null;

            try {
               String currSchemaVersion;
               try {
                  while((currSchemaVersion = bfReader.readLine()) != null) {
                     upgradeOrderList.add(currSchemaVersion.trim());
                  }
               } catch (Throwable var35) {
                  var8 = var35;
                  throw var35;
               }
            } finally {
               if (bfReader != null) {
                  if (var8 != null) {
                     try {
                        bfReader.close();
                     } catch (Throwable var34) {
                        var8.addSuppressed(var34);
                     }
                  } else {
                     bfReader.close();
                  }
               }

            }
         } catch (Throwable var37) {
            var6 = var37;
            throw var37;
         } finally {
            if (fr != null) {
               if (var6 != null) {
                  try {
                     fr.close();
                  } catch (Throwable var33) {
                     var6.addSuppressed(var33);
                  }
               } else {
                  fr.close();
               }
            }

         }
      } catch (FileNotFoundException e) {
         throw new HiveMetaException("File " + upgradeListFile + "not found ", e);
      } catch (IOException e) {
         throw new HiveMetaException("Error reading " + upgradeListFile, e);
      }

      this.hiveSchemaVersions = (String[])upgradeOrderList.toArray(new String[0]);
   }

   public List getUpgradeScripts(String fromVersion) throws HiveMetaException {
      List<String> upgradeScriptList = new ArrayList();
      if (getHiveSchemaVersion().equals(fromVersion)) {
         return upgradeScriptList;
      } else {
         int firstScript = this.hiveSchemaVersions.length;

         for(int i = 0; i < this.hiveSchemaVersions.length; ++i) {
            if (this.hiveSchemaVersions[i].startsWith(fromVersion)) {
               firstScript = i;
            }
         }

         if (firstScript == this.hiveSchemaVersions.length) {
            throw new HiveMetaException("Unknown version specified for upgrade " + fromVersion + " Metastore schema may be too old or newer");
         } else {
            for(int i = firstScript; i < this.hiveSchemaVersions.length; ++i) {
               String scriptFile = this.generateUpgradeFileName(this.hiveSchemaVersions[i]);
               upgradeScriptList.add(scriptFile);
            }

            return upgradeScriptList;
         }
      }
   }

   public String generateInitFileName(String toVersion) throws HiveMetaException {
      if (toVersion == null) {
         toVersion = getHiveSchemaVersion();
      }

      String initScriptName = "hive-schema-" + toVersion + "." + this.dbType + ".sql";
      if (!(new File(this.getMetaStoreScriptDir() + File.separatorChar + initScriptName)).exists()) {
         throw new HiveMetaException("Unknown version specified for initialization: " + toVersion);
      } else {
         return initScriptName;
      }
   }

   public String getMetaStoreScriptDir() {
      return this.hiveHome + File.separatorChar + "scripts" + File.separatorChar + "metastore" + File.separatorChar + "upgrade" + File.separatorChar + this.dbType;
   }

   private String generateUpgradeFileName(String fileVersion) {
      return "upgrade-" + fileVersion + "." + this.dbType + ".sql";
   }

   public static String getPreUpgradeScriptName(int index, String upgradeScriptName) {
      return "pre-" + index + "-" + upgradeScriptName;
   }

   public static String getHiveSchemaVersion() {
      String hiveVersion = HiveVersionInfo.getShortVersion();
      return getEquivalentVersion(hiveVersion);
   }

   private static String getEquivalentVersion(String hiveVersion) {
      String equivalentVersion = (String)EQUIVALENT_VERSIONS.get(hiveVersion);
      return equivalentVersion != null ? equivalentVersion : hiveVersion;
   }

   public static boolean isVersionCompatible(String hiveVersion, String dbVersion) {
      hiveVersion = getEquivalentVersion(hiveVersion);
      dbVersion = getEquivalentVersion(dbVersion);
      if (hiveVersion.equals(dbVersion)) {
         return true;
      } else {
         String[] hiveVerParts = hiveVersion.split("\\.");
         String[] dbVerParts = dbVersion.split("\\.");
         if (hiveVerParts.length == 3 && dbVerParts.length == 3) {
            for(int i = 0; i < dbVerParts.length; ++i) {
               int dbVerPart = Integer.parseInt(dbVerParts[i]);
               int hiveVerPart = Integer.parseInt(hiveVerParts[i]);
               if (dbVerPart > hiveVerPart) {
                  return true;
               }

               if (dbVerPart < hiveVerPart) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }
}
