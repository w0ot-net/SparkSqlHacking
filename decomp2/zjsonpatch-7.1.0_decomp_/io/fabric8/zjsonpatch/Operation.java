package io.fabric8.zjsonpatch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Operation {
   ADD("add"),
   REMOVE("remove"),
   REPLACE("replace"),
   MOVE("move"),
   COPY("copy"),
   TEST("test");

   private static final Map OPS = createImmutableMap();
   private final String rfcName;

   private static Map createImmutableMap() {
      Map<String, Operation> map = new HashMap();
      map.put(ADD.rfcName, ADD);
      map.put(REMOVE.rfcName, REMOVE);
      map.put(REPLACE.rfcName, REPLACE);
      map.put(MOVE.rfcName, MOVE);
      map.put(COPY.rfcName, COPY);
      map.put(TEST.rfcName, TEST);
      return Collections.unmodifiableMap(map);
   }

   private Operation(String rfcName) {
      this.rfcName = rfcName;
   }

   public static Operation fromRfcName(String rfcName) {
      if (rfcName == null) {
         throw new JsonPatchException("rfcName cannot be null");
      } else {
         Operation op = (Operation)OPS.get(rfcName.toLowerCase());
         if (op == null) {
            throw new JsonPatchException("unknown / unsupported operation " + rfcName);
         } else {
            return op;
         }
      }
   }

   public String rfcName() {
      return this.rfcName;
   }
}
