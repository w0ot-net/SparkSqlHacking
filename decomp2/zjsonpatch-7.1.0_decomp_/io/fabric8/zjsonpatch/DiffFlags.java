package io.fabric8.zjsonpatch;

import java.util.EnumSet;

public enum DiffFlags {
   OMIT_VALUE_ON_REMOVE,
   OMIT_MOVE_OPERATION,
   OMIT_COPY_OPERATION,
   ADD_ORIGINAL_VALUE_ON_REPLACE,
   ADD_EXPLICIT_REMOVE_ADD_ON_REPLACE,
   EMIT_TEST_OPERATIONS;

   public static EnumSet defaults() {
      return EnumSet.of(OMIT_VALUE_ON_REMOVE);
   }

   public static EnumSet dontNormalizeOpIntoMoveAndCopy() {
      return EnumSet.of(OMIT_MOVE_OPERATION, OMIT_COPY_OPERATION);
   }
}
