package com.univocity.parsers.common;

import com.univocity.parsers.common.processor.core.Processor;
import java.util.Arrays;

class Internal {
   public static final void process(String[] row, Processor processor, Context context, ProcessorErrorHandler errorHandler) {
      try {
         processor.rowProcessed(row, context);
      } catch (DataProcessingException var9) {
         DataProcessingException ex = var9;
         var9.setContext(context);
         if (!var9.isFatal() && !var9.isHandled() && var9.getColumnIndex() > -1 && errorHandler instanceof RetryableErrorHandler) {
            RetryableErrorHandler retry = (RetryableErrorHandler)errorHandler;
            var9.markAsHandled(errorHandler);
            retry.handleError(var9, row, context);
            if (!retry.isRecordSkipped()) {
               try {
                  processor.rowProcessed(row, context);
                  return;
               } catch (DataProcessingException e) {
                  ex = e;
               } catch (Throwable t) {
                  throwDataProcessingException(processor, t, row, context.errorContentLength());
               }
            }
         }

         ex.setErrorContentLength(context.errorContentLength());
         if (ex.isFatal()) {
            throw ex;
         }

         ex.markAsHandled(errorHandler);
         errorHandler.handleError(ex, row, context);
      } catch (Throwable t) {
         throwDataProcessingException(processor, t, row, context.errorContentLength());
      }

   }

   private static final void throwDataProcessingException(Processor processor, Throwable t, String[] row, int errorContentLength) throws DataProcessingException {
      DataProcessingException ex = new DataProcessingException("Unexpected error processing input row " + AbstractException.restrictContent(errorContentLength, (CharSequence)Arrays.toString(row)) + " using Processor " + processor.getClass().getName() + '.', AbstractException.restrictContent(errorContentLength, (Object[])row), t);
      ex.restrictContent(errorContentLength);
      throw ex;
   }
}
