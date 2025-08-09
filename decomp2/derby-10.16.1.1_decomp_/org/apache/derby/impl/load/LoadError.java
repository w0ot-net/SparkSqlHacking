package org.apache.derby.impl.load;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

class LoadError {
   static SQLException connectionNull() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE01.S", new Object[0]));
   }

   static SQLException dataAfterStopDelimiter(int var0, int var1) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE03.S", new Object[]{var0, var1}));
   }

   static SQLException dataFileNotFound(String var0, Exception var1) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE04.S", var1, new Object[]{var0}));
   }

   static SQLException dataFileNull() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE05.S", new Object[0]));
   }

   static SQLException dataFileExists(String var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0S.S", new Object[]{var0}));
   }

   static SQLException lobsFileExists(String var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0T.S", new Object[]{var0}));
   }

   static SQLException entityNameMissing() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE06.S", new Object[0]));
   }

   static SQLException fieldAndRecordSeparatorsSubset() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE07.S", new Object[0]));
   }

   static SQLException invalidColumnName(String var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE08.S", new Object[]{var0}));
   }

   static SQLException invalidColumnNumber(int var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE09.S", new Object[]{var0}));
   }

   static SQLException nonSupportedTypeColumn(String var0, String var1) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0B.S", new Object[]{var0, var1}));
   }

   static SQLException recordSeparatorMissing(int var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0D.S", new Object[]{var0}));
   }

   static SQLException unexpectedEndOfFile(int var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0E.S", new Object[]{var0}));
   }

   static SQLException errorWritingData(IOException var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0I.S", var0, new Object[0]));
   }

   static SQLException periodAsCharDelimiterNotAllowed() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0K.S", new Object[0]));
   }

   static SQLException delimitersAreNotMutuallyExclusive() {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0J.S", new Object[0]));
   }

   static SQLException tableNotFound(String var0) {
      return PublicAPI.wrapStandardException(StandardException.newException("XIE0M.S", new Object[]{var0}));
   }

   static SQLException unexpectedError(Throwable var0) {
      return !(var0 instanceof SQLException) ? PublicAPI.wrapStandardException(StandardException.plainWrapException(var0)) : (SQLException)var0;
   }
}
