package org.apache.commons.collections4.sequence;

import java.util.List;

@FunctionalInterface
public interface ReplacementsHandler {
   void handleReplacement(int var1, List var2, List var3);
}
