package org.glassfish.jaxb.core;

final class StackHelper {
   private StackHelper() {
   }

   static String getCallerClassName() {
      return (String)StackWalker.getInstance().walk((frames) -> (String)frames.map(StackWalker.StackFrame::getClassName).skip(2L).findFirst().get());
   }
}
