package org.jline.reader;

import java.util.List;
import java.util.Map;

public interface CompletionMatcher {
   void compile(Map var1, boolean var2, CompletingParsedLine var3, boolean var4, int var5, String var6);

   List matches(List var1);

   Candidate exactMatch();

   String getCommonPrefix();
}
