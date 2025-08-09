package org.apache.derby.impl.tools.ij;

import java.io.IOException;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

public class ijException extends RuntimeException {
   private static final String IllegalStatementName = "IJ_IllegalStatementName";
   private static final String NotYetImplemented = "IJ_NotYetImpl";
   private static final String AlreadyHaveConnectionNamed = "IJ_AlreHaveACon";
   private static final String BangException = "IJ_ExceRunnComm";
   private static final String ConnectionGetWarningsFailed = "IJ_UnabToGetWar";
   private static final String ClassNotFoundForProtocol = "IJ_CoulNotLocaC";
   private static final String ClassNotFound = "IJ_CoulNotLocaC_5";
   private static final String DisconnectFailed = "IJ_FailToDisc";
   private static final String DriverNotClassName = "IJ_DrivNotClasN";
   private static final String FileNotFound = "IJ_FileNotFoun";
   private static final String ForwardOnlyCursor = "IJ_IsNotAlloOnA";
   private static final String GetConnectionFailed = "IJ_GetcCallFail";
   private static final String IOException = "IJ_Ioex";
   private static final String NeedToDisconnect = "IJ_NeedToDiscFi";
   private static final String NoSuchAsyncStatement = "IJ_NoAsynStatEx";
   private static final String NoSuchConnection = "IJ_NoConnExisWi";
   private static final String NoSuchProtocol = "IJ_NoProtExisWi";
   private static final String NoSuchTable = "IJ_NoSuchTable";
   private static final String NoUsingResults = "IJ_UsinClauHadN";
   private static final String ObjectWasNull = "IJ_UnabToEsta";
   private static final String ResultSetGetWarningsFailed = "IJ_UnabToGetWar_19";
   private static final String ResourceNotFound = "IJ_ResoNotFoun";
   private static final String ScrollCursorsNotSupported = "IJ_ScroCursAre1";
   private static final String StatementGetWarningsFailed = "IJ_UnabToGetWar_22";
   private static final String WaitInterrupted = "IJ_WaitForStatI";
   private static final String ZeroInvalidForAbsolute = "IJ_0IsAnInvaVal";
   private static final String NotAvailableForDriver = "IJ_NotAvailForDriver";

   public ijException(String var1) {
      super(var1);
   }

   static ijException notYetImplemented() {
      return new ijException(LocalizedResource.getMessage("IJ_NotYetImpl"));
   }

   static ijException illegalStatementName(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_IllegalStatementName", var0));
   }

   static ijException alreadyHaveConnectionNamed(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_AlreHaveACon", var0));
   }

   static ijException bangException(Throwable var0) {
      return new ijException(LocalizedResource.getMessage("IJ_ExceRunnComm", var0.toString()));
   }

   static ijException classNotFoundForProtocol(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_CoulNotLocaC", var0));
   }

   static ijException classNotFound(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_CoulNotLocaC_5", var0));
   }

   static ijException connectionGetWarningsFailed() {
      return new ijException(LocalizedResource.getMessage("IJ_UnabToGetWar"));
   }

   static ijException disconnectFailed() {
      return new ijException(LocalizedResource.getMessage("IJ_FailToDisc"));
   }

   static ijException driverNotClassName(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_DrivNotClasN", var0));
   }

   static ijException fileNotFound() {
      return new ijException(LocalizedResource.getMessage("IJ_FileNotFoun"));
   }

   public static ijException forwardOnlyCursor(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_IsNotAlloOnA", var0));
   }

   static ijException resourceNotFound() {
      return new ijException(LocalizedResource.getMessage("IJ_ResoNotFoun"));
   }

   static ijException getConnectionFailed() {
      return new ijException(LocalizedResource.getMessage("IJ_GetcCallFail"));
   }

   static ijException iOException(IOException var0) {
      return new ijException(LocalizedResource.getMessage("IJ_Ioex", var0.getMessage()));
   }

   static ijException needToDisconnect() {
      return new ijException(LocalizedResource.getMessage("IJ_NeedToDiscFi"));
   }

   static ijException noSuchAsyncStatement(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_NoAsynStatEx", var0));
   }

   static ijException noSuchConnection(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_NoConnExisWi", var0));
   }

   static ijException noSuchProtocol(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_NoProtExisWi", var0));
   }

   static ijException noSuchTable(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_NoSuchTable", var0));
   }

   static ijException noUsingResults() {
      return new ijException(LocalizedResource.getMessage("IJ_UsinClauHadN"));
   }

   public static ijException objectWasNull(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_UnabToEsta", var0));
   }

   static ijException resultSetGetWarningsFailed() {
      return new ijException(LocalizedResource.getMessage("IJ_UnabToGetWar_19"));
   }

   static ijException scrollCursorsNotSupported() {
      return new ijException(LocalizedResource.getMessage("IJ_ScroCursAre1"));
   }

   static ijException statementGetWarningsFailed() {
      return new ijException(LocalizedResource.getMessage("IJ_UnabToGetWar_22"));
   }

   static ijException waitInterrupted(Throwable var0) {
      return new ijException(LocalizedResource.getMessage("IJ_WaitForStatI", var0.toString()));
   }

   public static ijException zeroInvalidForAbsolute() {
      return new ijException(LocalizedResource.getMessage("IJ_0IsAnInvaVal"));
   }

   public static ijException notAvailableForDriver(String var0) {
      return new ijException(LocalizedResource.getMessage("IJ_NotAvailForDriver", var0));
   }
}
