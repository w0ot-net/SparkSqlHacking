package org.apache.hadoop.hive.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.util.ReflectionUtils;

public class HiveIOExceptionHandlerChain {
   public static final String HIVE_IO_EXCEPTION_HANDLE_CHAIN = "hive.io.exception.handlers";
   private List handlerChain;

   public static HiveIOExceptionHandlerChain getHiveIOExceptionHandlerChain(JobConf conf) {
      HiveIOExceptionHandlerChain chain = new HiveIOExceptionHandlerChain();
      String exceptionHandlerStr = conf.get("hive.io.exception.handlers");
      List<HiveIOExceptionHandler> handlerChain = new ArrayList();
      if (exceptionHandlerStr != null && !exceptionHandlerStr.trim().equals("")) {
         String[] handlerArr = exceptionHandlerStr.split(",");
         if (handlerArr != null && handlerArr.length > 0) {
            for(String handlerStr : handlerArr) {
               if (!handlerStr.trim().equals("")) {
                  try {
                     Class<? extends HiveIOExceptionHandler> handlerCls = Class.forName(handlerStr);
                     HiveIOExceptionHandler handler = (HiveIOExceptionHandler)ReflectionUtils.newInstance(handlerCls, (Configuration)null);
                     handlerChain.add(handler);
                  } catch (Exception var11) {
                  }
               }
            }
         }
      }

      chain.setHandlerChain(handlerChain);
      return chain;
   }

   protected List getHandlerChain() {
      return this.handlerChain;
   }

   protected void setHandlerChain(List handlerChain) {
      this.handlerChain = handlerChain;
   }

   public RecordReader handleRecordReaderCreationException(Exception e) throws IOException {
      RecordReader<?, ?> ret = null;
      if (this.handlerChain != null && this.handlerChain.size() > 0) {
         for(HiveIOExceptionHandler handler : this.handlerChain) {
            ret = handler.handleRecordReaderCreationException(e);
            if (ret != null) {
               return ret;
            }
         }
      }

      throw new IOException(e);
   }

   public boolean handleRecordReaderNextException(Exception e) throws IOException {
      HiveIOExceptionNextHandleResult result = new HiveIOExceptionNextHandleResult();
      if (this.handlerChain != null && this.handlerChain.size() > 0) {
         for(HiveIOExceptionHandler handler : this.handlerChain) {
            handler.handleRecorReaderNextException(e, result);
            if (result.getHandled()) {
               return result.getHandleResult();
            }
         }
      }

      throw new IOException(e);
   }
}
