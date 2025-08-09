package com.sun.istack.logging;

import java.lang.StackWalker.Option;

class StackHelper {
   static String getCallerMethodName() {
      return (String)StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE).walk((frames) -> (String)frames.dropWhile((f) -> !Logger.class.equals(f.getDeclaringClass())).dropWhile((f) -> Logger.class.equals(f.getDeclaringClass())).findFirst().map(StackWalker.StackFrame::getMethodName).orElse("UNKNOWN METHOD"));
   }
}
