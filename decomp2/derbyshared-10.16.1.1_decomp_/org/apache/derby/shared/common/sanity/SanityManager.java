package org.apache.derby.shared.common.sanity;

import java.io.PrintWriter;
import java.util.Hashtable;

public class SanityManager {
   public static final boolean ASSERT = false;
   public static final boolean DEBUG = false;
   public static final String DEBUGDEBUG = "DumpSanityDebug";
   private static PrintWriter debugStream;
   private static Hashtable DebugFlags;
   private static boolean AllDebugOn;
   private static boolean AllDebugOff;

   public static final void ASSERT(boolean var0) {
   }

   public static final void ASSERT(boolean var0, String var1) {
   }

   public static final void THROWASSERT(String var0) {
      THROWASSERT(var0, (Throwable)null);
   }

   public static final void THROWASSERT(String var0, Throwable var1) {
      AssertFailure var2 = new AssertFailure("ASSERT FAILED " + var0, var1);
      if (var1 != null) {
         showTrace(var1);
      }

      throw var2;
   }

   public static final void THROWASSERT(Throwable var0) {
      THROWASSERT(var0.toString(), var0);
   }

   public static final void DEBUG(String var0, String var1) {
   }

   public static final boolean DEBUG_ON(String var0) {
      return false;
   }

   public static final void DEBUG_SET(String var0) {
   }

   public static final void DEBUG_CLEAR(String var0) {
   }

   public static final void DEBUG_ALL_ON() {
   }

   public static final void DEBUG_ALL_OFF() {
   }

   public static void SET_DEBUG_STREAM(PrintWriter var0) {
      debugStream = var0;
   }

   public static PrintWriter GET_DEBUG_STREAM() {
      return debugStream;
   }

   private static void showTrace(AssertFailure var0) {
      var0.printStackTrace();
      PrintWriter var1 = GET_DEBUG_STREAM();
      var1.println("Assertion trace:");
      var0.printStackTrace(var1);
      var1.flush();
   }

   public static void showTrace(Throwable var0) {
      PrintWriter var1 = GET_DEBUG_STREAM();
      var1.println("Exception trace: ");
      var0.printStackTrace(var1);
   }

   public static void DEBUG_PRINT(String var0, String var1) {
      PrintWriter var2 = GET_DEBUG_STREAM();
      var2.println("DEBUG " + var0 + " OUTPUT: " + var1);
      var2.flush();
   }

   public static void NOTREACHED() {
      THROWASSERT("code should not be reached");
   }

   static {
      debugStream = new PrintWriter(System.err);
      DebugFlags = new Hashtable();
      AllDebugOn = false;
      AllDebugOff = false;
   }
}
