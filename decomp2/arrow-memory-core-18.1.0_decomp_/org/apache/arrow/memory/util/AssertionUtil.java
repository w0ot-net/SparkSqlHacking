package org.apache.arrow.memory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertionUtil {
   public static final boolean ASSERT_ENABLED;
   static final Logger logger = LoggerFactory.getLogger(AssertionUtil.class);
   // $FF: synthetic field
   static final boolean $assertionsDisabled = !AssertionUtil.class.desiredAssertionStatus();

   private AssertionUtil() {
   }

   public static boolean isAssertionsEnabled() {
      return ASSERT_ENABLED;
   }

   static {
      boolean isAssertEnabled = false;
      if (!$assertionsDisabled) {
         isAssertEnabled = true;
         if (false) {
            throw new AssertionError();
         }
      }

      ASSERT_ENABLED = isAssertEnabled;
   }
}
