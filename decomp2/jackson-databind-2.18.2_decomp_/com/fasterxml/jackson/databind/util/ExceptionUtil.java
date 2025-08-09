package com.fasterxml.jackson.databind.util;

import java.io.IOException;

public class ExceptionUtil {
   private ExceptionUtil() {
   }

   public static void rethrowIfFatal(Throwable throwable) throws Error, RuntimeException {
      if (isFatal(throwable)) {
         if (throwable instanceof Error) {
            throw (Error)throwable;
         } else if (throwable instanceof RuntimeException) {
            throw (RuntimeException)throwable;
         } else {
            throw new RuntimeException(throwable);
         }
      }
   }

   private static boolean isFatal(Throwable throwable) {
      return throwable instanceof VirtualMachineError || throwable instanceof ThreadDeath || throwable instanceof InterruptedException || throwable instanceof ClassCircularityError || throwable instanceof ClassFormatError || throwable instanceof IncompatibleClassChangeError || throwable instanceof BootstrapMethodError || throwable instanceof VerifyError;
   }

   public static Object throwSneaky(IOException e) {
      _sneaky(e);
      return null;
   }

   private static void _sneaky(Throwable e) throws Throwable {
      throw e;
   }
}
