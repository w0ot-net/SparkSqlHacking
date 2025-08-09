package org.antlr.v4.runtime.atn;

import org.antlr.v4.runtime.TokenStream;

public class DecisionEventInfo {
   public final int decision;
   public final ATNConfigSet configs;
   public final TokenStream input;
   public final int startIndex;
   public final int stopIndex;
   public final boolean fullCtx;

   public DecisionEventInfo(int decision, ATNConfigSet configs, TokenStream input, int startIndex, int stopIndex, boolean fullCtx) {
      this.decision = decision;
      this.fullCtx = fullCtx;
      this.stopIndex = stopIndex;
      this.input = input;
      this.startIndex = startIndex;
      this.configs = configs;
   }
}
