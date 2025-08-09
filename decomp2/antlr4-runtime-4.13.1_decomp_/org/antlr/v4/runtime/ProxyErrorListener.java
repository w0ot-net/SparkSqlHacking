package org.antlr.v4.runtime;

import java.util.BitSet;
import java.util.Collection;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class ProxyErrorListener implements ANTLRErrorListener {
   private final Collection delegates;

   public ProxyErrorListener(Collection delegates) {
      if (delegates == null) {
         throw new NullPointerException("delegates");
      } else {
         this.delegates = delegates;
      }
   }

   public void syntaxError(Recognizer recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
      for(ANTLRErrorListener listener : this.delegates) {
         listener.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
      }

   }

   public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
      for(ANTLRErrorListener listener : this.delegates) {
         listener.reportAmbiguity(recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs);
      }

   }

   public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
      for(ANTLRErrorListener listener : this.delegates) {
         listener.reportAttemptingFullContext(recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs);
      }

   }

   public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
      for(ANTLRErrorListener listener : this.delegates) {
         listener.reportContextSensitivity(recognizer, dfa, startIndex, stopIndex, prediction, configs);
      }

   }
}
