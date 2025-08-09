package org.apache.hadoop.hive.io;

import java.io.IOException;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;

public class HiveIOExceptionHandlerUtil {
   private static final ThreadLocal handlerChainInstance = new ThreadLocal();

   private static synchronized HiveIOExceptionHandlerChain get(JobConf job) {
      HiveIOExceptionHandlerChain cache = (HiveIOExceptionHandlerChain)handlerChainInstance.get();
      if (cache == null) {
         HiveIOExceptionHandlerChain toSet = HiveIOExceptionHandlerChain.getHiveIOExceptionHandlerChain(job);
         handlerChainInstance.set(toSet);
         cache = (HiveIOExceptionHandlerChain)handlerChainInstance.get();
      }

      return cache;
   }

   public static RecordReader handleRecordReaderCreationException(Exception e, JobConf job) throws IOException {
      HiveIOExceptionHandlerChain ioExpectionHandlerChain = get(job);
      if (ioExpectionHandlerChain != null) {
         return ioExpectionHandlerChain.handleRecordReaderCreationException(e);
      } else {
         throw new IOException(e);
      }
   }

   public static boolean handleRecordReaderNextException(Exception e, JobConf job) throws IOException {
      HiveIOExceptionHandlerChain ioExpectionHandlerChain = get(job);
      if (ioExpectionHandlerChain != null) {
         return ioExpectionHandlerChain.handleRecordReaderNextException(e);
      } else {
         throw new IOException(e);
      }
   }
}
