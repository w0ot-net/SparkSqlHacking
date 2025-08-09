package org.apache.hadoop.hive.io;

import java.io.IOException;
import org.apache.hadoop.mapred.RecordReader;

public interface HiveIOExceptionHandler {
   RecordReader handleRecordReaderCreationException(Exception var1) throws IOException;

   void handleRecorReaderNextException(Exception var1, HiveIOExceptionNextHandleResult var2) throws IOException;
}
