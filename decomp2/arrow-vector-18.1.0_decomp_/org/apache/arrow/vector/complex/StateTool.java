package org.apache.arrow.vector.complex;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateTool {
   static final Logger logger = LoggerFactory.getLogger(StateTool.class);

   private StateTool() {
   }

   public static void check(Enum currentState, Enum... expectedStates) {
      for(Enum s : expectedStates) {
         if (s == currentState) {
            return;
         }
      }

      throw new IllegalArgumentException(String.format("Expected to be in one of these states %s but was actually in state %s", Arrays.toString(expectedStates), currentState));
   }
}
