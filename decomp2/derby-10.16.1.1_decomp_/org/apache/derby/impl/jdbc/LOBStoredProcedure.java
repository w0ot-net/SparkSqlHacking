package org.apache.derby.impl.jdbc;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.derby.iapi.jdbc.EngineLOB;
import org.apache.derby.iapi.jdbc.InternalDriver;

public class LOBStoredProcedure {
   public static int CLOBCREATELOCATOR() throws SQLException {
      EngineLOB var0 = (EngineLOB)getEmbedConnection().createClob();
      return var0.getLocator();
   }

   public static void CLOBRELEASELOCATOR(int var0) throws SQLException {
      Clob var1 = (Clob)getEmbedConnection().getLOBMapping(var0);
      if (var1 != null) {
         EmbedClob var2 = (EmbedClob)var1;
         var2.free();
         getEmbedConnection().removeLOBMapping(var0);
      }
   }

   public static long CLOBGETPOSITIONFROMSTRING(int var0, String var1, long var2) throws SQLException {
      return getClobObjectCorrespondingtoLOCATOR(var0).position(var1, var2);
   }

   public static long CLOBGETPOSITIONFROMLOCATOR(int var0, int var1, long var2) throws SQLException {
      return getClobObjectCorrespondingtoLOCATOR(var0).position(getClobObjectCorrespondingtoLOCATOR(var1), var2);
   }

   public static long CLOBGETLENGTH(int var0) throws SQLException {
      return getClobObjectCorrespondingtoLOCATOR(var0).length();
   }

   public static String CLOBGETSUBSTRING(int var0, long var1, int var3) throws SQLException {
      var3 = Math.min(var3, 10890);
      return getClobObjectCorrespondingtoLOCATOR(var0).getSubString(var1, var3);
   }

   public static void CLOBSETSTRING(int var0, long var1, int var3, String var4) throws SQLException {
      getClobObjectCorrespondingtoLOCATOR(var0).setString(var1, var4, 0, var3);
   }

   public static void CLOBTRUNCATE(int var0, long var1) throws SQLException {
      getClobObjectCorrespondingtoLOCATOR(var0).truncate(var1);
   }

   private static Clob getClobObjectCorrespondingtoLOCATOR(int var0) throws SQLException {
      Clob var1 = (Clob)getEmbedConnection().getLOBMapping(var0);
      if (var1 == null) {
         throw newSQLException("XJ217.S");
      } else {
         return var1;
      }
   }

   public static int BLOBCREATELOCATOR() throws SQLException {
      EngineLOB var0 = (EngineLOB)getEmbedConnection().createBlob();
      return var0.getLocator();
   }

   public static void BLOBRELEASELOCATOR(int var0) throws SQLException {
      Blob var1 = (Blob)getEmbedConnection().getLOBMapping(var0);
      if (var1 != null) {
         EmbedBlob var2 = (EmbedBlob)var1;
         var2.free();
         getEmbedConnection().removeLOBMapping(var0);
      }
   }

   public static long BLOBGETPOSITIONFROMLOCATOR(int var0, int var1, long var2) throws SQLException {
      return getBlobObjectCorrespondingtoLOCATOR(var0).position(getBlobObjectCorrespondingtoLOCATOR(var1), var2);
   }

   public static long BLOBGETPOSITIONFROMBYTES(int var0, byte[] var1, long var2) throws SQLException {
      return getBlobObjectCorrespondingtoLOCATOR(var0).position(var1, var2);
   }

   public static long BLOBGETLENGTH(int var0) throws SQLException {
      return getBlobObjectCorrespondingtoLOCATOR(var0).length();
   }

   public static byte[] BLOBGETBYTES(int var0, long var1, int var3) throws SQLException {
      var3 = Math.min(var3, 32672);
      return getBlobObjectCorrespondingtoLOCATOR(var0).getBytes(var1, var3);
   }

   public static void BLOBSETBYTES(int var0, long var1, int var3, byte[] var4) throws SQLException {
      getBlobObjectCorrespondingtoLOCATOR(var0).setBytes(var1, var4, 0, var3);
   }

   public static void BLOBTRUNCATE(int var0, long var1) throws SQLException {
      getBlobObjectCorrespondingtoLOCATOR(var0).truncate(var1);
   }

   private static Blob getBlobObjectCorrespondingtoLOCATOR(int var0) throws SQLException {
      Blob var1 = (Blob)getEmbedConnection().getLOBMapping(var0);
      if (var1 == null) {
         throw newSQLException("XJ217.S");
      } else {
         return var1;
      }
   }

   private static EmbedConnection getEmbedConnection() throws SQLException {
      InternalDriver var0 = InternalDriver.activeDriver();
      if (var0 != null) {
         EmbedConnection var1 = (EmbedConnection)var0.connect("jdbc:default:connection", (Properties)null, 0);
         if (var1 != null) {
            return var1;
         }
      }

      throw Util.noCurrentConnection();
   }

   private static SQLException newSQLException(String var0) {
      return Util.generateCsSQLException(var0);
   }
}
