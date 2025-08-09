package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.EnumSet;

class CopyingApplyProcessor extends InPlaceApplyProcessor {
   CopyingApplyProcessor(JsonNode target, EnumSet flags) {
      super(target.deepCopy(), flags);
   }
}
