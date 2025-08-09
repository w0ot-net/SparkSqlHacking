package org.antlr.v4.runtime.atn;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;

public class ATNDeserializer {
   public static final int SERIALIZED_VERSION = 4;
   private final ATNDeserializationOptions deserializationOptions;

   public ATNDeserializer() {
      this(ATNDeserializationOptions.getDefaultOptions());
   }

   public ATNDeserializer(ATNDeserializationOptions deserializationOptions) {
      if (deserializationOptions == null) {
         deserializationOptions = ATNDeserializationOptions.getDefaultOptions();
      }

      this.deserializationOptions = deserializationOptions;
   }

   public ATN deserialize(char[] data) {
      return this.deserialize(decodeIntsEncodedAs16BitWords(data));
   }

   public ATN deserialize(int[] data) {
      int p = 0;
      int version = data[p++];
      if (version != SERIALIZED_VERSION) {
         String reason = String.format(Locale.getDefault(), "Could not deserialize ATN with version %d (expected %d).", version, SERIALIZED_VERSION);
         throw new UnsupportedOperationException(new InvalidClassException(ATN.class.getName(), reason));
      } else {
         ATNType grammarType = ATNType.values()[data[p++]];
         int maxTokenType = data[p++];
         ATN atn = new ATN(grammarType, maxTokenType);
         List<Pair<LoopEndState, Integer>> loopBackStateNumbers = new ArrayList();
         List<Pair<BlockStartState, Integer>> endStateNumbers = new ArrayList();
         int nstates = data[p++];

         for(int i = 0; i < nstates; ++i) {
            int stype = data[p++];
            if (stype == 0) {
               atn.addState((ATNState)null);
            } else {
               int ruleIndex = data[p++];
               ATNState s = this.stateFactory(stype, ruleIndex);
               if (stype == 12) {
                  int loopBackStateNumber = data[p++];
                  loopBackStateNumbers.add(new Pair((LoopEndState)s, loopBackStateNumber));
               } else if (s instanceof BlockStartState) {
                  int endStateNumber = data[p++];
                  endStateNumbers.add(new Pair((BlockStartState)s, endStateNumber));
               }

               atn.addState(s);
            }
         }

         for(Pair pair : loopBackStateNumbers) {
            ((LoopEndState)pair.a).loopBackState = (ATNState)atn.states.get((Integer)pair.b);
         }

         for(Pair pair : endStateNumbers) {
            ((BlockStartState)pair.a).endState = (BlockEndState)atn.states.get((Integer)pair.b);
         }

         int numNonGreedyStates = data[p++];

         for(int i = 0; i < numNonGreedyStates; ++i) {
            int stateNumber = data[p++];
            ((DecisionState)atn.states.get(stateNumber)).nonGreedy = true;
         }

         int numPrecedenceStates = data[p++];

         for(int i = 0; i < numPrecedenceStates; ++i) {
            int stateNumber = data[p++];
            ((RuleStartState)atn.states.get(stateNumber)).isLeftRecursiveRule = true;
         }

         int nrules = data[p++];
         if (atn.grammarType == ATNType.LEXER) {
            atn.ruleToTokenType = new int[nrules];
         }

         atn.ruleToStartState = new RuleStartState[nrules];

         for(int i = 0; i < nrules; ++i) {
            int s = data[p++];
            RuleStartState startState = (RuleStartState)atn.states.get(s);
            atn.ruleToStartState[i] = startState;
            if (atn.grammarType == ATNType.LEXER) {
               int tokenType = data[p++];
               atn.ruleToTokenType[i] = tokenType;
            }
         }

         atn.ruleToStopState = new RuleStopState[nrules];

         for(ATNState state : atn.states) {
            if (state instanceof RuleStopState) {
               RuleStopState stopState = (RuleStopState)state;
               atn.ruleToStopState[state.ruleIndex] = stopState;
               atn.ruleToStartState[state.ruleIndex].stopState = stopState;
            }
         }

         int nmodes = data[p++];

         for(int i = 0; i < nmodes; ++i) {
            int s = data[p++];
            atn.modeToStartState.add((TokensStartState)atn.states.get(s));
         }

         List<IntervalSet> sets = new ArrayList();
         p = this.deserializeSets(data, p, sets);
         int nedges = data[p++];

         for(int i = 0; i < nedges; ++i) {
            int src = data[p];
            int trg = data[p + 1];
            int ttype = data[p + 2];
            int arg1 = data[p + 3];
            int arg2 = data[p + 4];
            int arg3 = data[p + 5];
            Transition trans = this.edgeFactory(atn, ttype, src, trg, arg1, arg2, arg3, sets);
            ATNState srcState = (ATNState)atn.states.get(src);
            srcState.addTransition(trans);
            p += 6;
         }

         for(ATNState state : atn.states) {
            for(int i = 0; i < state.getNumberOfTransitions(); ++i) {
               Transition t = state.transition(i);
               if (t instanceof RuleTransition) {
                  RuleTransition ruleTransition = (RuleTransition)t;
                  int outermostPrecedenceReturn = -1;
                  if (atn.ruleToStartState[ruleTransition.target.ruleIndex].isLeftRecursiveRule && ruleTransition.precedence == 0) {
                     outermostPrecedenceReturn = ruleTransition.target.ruleIndex;
                  }

                  EpsilonTransition returnTransition = new EpsilonTransition(ruleTransition.followState, outermostPrecedenceReturn);
                  atn.ruleToStopState[ruleTransition.target.ruleIndex].addTransition(returnTransition);
               }
            }
         }

         for(ATNState state : atn.states) {
            if (state instanceof BlockStartState) {
               if (((BlockStartState)state).endState == null) {
                  throw new IllegalStateException();
               }

               if (((BlockStartState)state).endState.startState != null) {
                  throw new IllegalStateException();
               }

               ((BlockStartState)state).endState.startState = (BlockStartState)state;
            }

            if (state instanceof PlusLoopbackState) {
               PlusLoopbackState loopbackState = (PlusLoopbackState)state;

               for(int i = 0; i < loopbackState.getNumberOfTransitions(); ++i) {
                  ATNState target = loopbackState.transition(i).target;
                  if (target instanceof PlusBlockStartState) {
                     ((PlusBlockStartState)target).loopBackState = loopbackState;
                  }
               }
            } else if (state instanceof StarLoopbackState) {
               StarLoopbackState loopbackState = (StarLoopbackState)state;

               for(int i = 0; i < loopbackState.getNumberOfTransitions(); ++i) {
                  ATNState target = loopbackState.transition(i).target;
                  if (target instanceof StarLoopEntryState) {
                     ((StarLoopEntryState)target).loopBackState = loopbackState;
                  }
               }
            }
         }

         int ndecisions = data[p++];

         for(int i = 1; i <= ndecisions; ++i) {
            int s = data[p++];
            DecisionState decState = (DecisionState)atn.states.get(s);
            atn.decisionToState.add(decState);
            decState.decision = i - 1;
         }

         if (atn.grammarType == ATNType.LEXER) {
            atn.lexerActions = new LexerAction[data[p++]];

            for(int i = 0; i < atn.lexerActions.length; ++i) {
               LexerActionType actionType = LexerActionType.values()[data[p++]];
               int data1 = data[p++];
               int data2 = data[p++];
               LexerAction lexerAction = this.lexerActionFactory(actionType, data1, data2);
               atn.lexerActions[i] = lexerAction;
            }
         }

         this.markPrecedenceDecisions(atn);
         if (this.deserializationOptions.isVerifyATN()) {
            this.verifyATN(atn);
         }

         if (this.deserializationOptions.isGenerateRuleBypassTransitions() && atn.grammarType == ATNType.PARSER) {
            atn.ruleToTokenType = new int[atn.ruleToStartState.length];

            for(int i = 0; i < atn.ruleToStartState.length; ++i) {
               atn.ruleToTokenType[i] = atn.maxTokenType + i + 1;
            }

            for(int i = 0; i < atn.ruleToStartState.length; ++i) {
               BasicBlockStartState bypassStart = new BasicBlockStartState();
               bypassStart.ruleIndex = i;
               atn.addState(bypassStart);
               BlockEndState bypassStop = new BlockEndState();
               bypassStop.ruleIndex = i;
               atn.addState(bypassStop);
               bypassStart.endState = bypassStop;
               atn.defineDecisionState(bypassStart);
               bypassStop.startState = bypassStart;
               Transition excludeTransition = null;
               ATNState endState;
               if (!atn.ruleToStartState[i].isLeftRecursiveRule) {
                  endState = atn.ruleToStopState[i];
               } else {
                  endState = null;

                  for(ATNState state : atn.states) {
                     if (state.ruleIndex == i && state instanceof StarLoopEntryState) {
                        ATNState maybeLoopEndState = state.transition(state.getNumberOfTransitions() - 1).target;
                        if (maybeLoopEndState instanceof LoopEndState && maybeLoopEndState.epsilonOnlyTransitions && maybeLoopEndState.transition(0).target instanceof RuleStopState) {
                           endState = state;
                           break;
                        }
                     }
                  }

                  if (endState == null) {
                     throw new UnsupportedOperationException("Couldn't identify final state of the precedence rule prefix section.");
                  }

                  excludeTransition = ((StarLoopEntryState)endState).loopBackState.transition(0);
               }

               for(ATNState state : atn.states) {
                  for(Transition transition : state.transitions) {
                     if (transition != excludeTransition && transition.target == endState) {
                        transition.target = bypassStop;
                     }
                  }
               }

               while(atn.ruleToStartState[i].getNumberOfTransitions() > 0) {
                  Transition transition = atn.ruleToStartState[i].removeTransition(atn.ruleToStartState[i].getNumberOfTransitions() - 1);
                  bypassStart.addTransition(transition);
               }

               atn.ruleToStartState[i].addTransition(new EpsilonTransition(bypassStart));
               bypassStop.addTransition(new EpsilonTransition(endState));
               ATNState matchState = new BasicState();
               atn.addState(matchState);
               matchState.addTransition(new AtomTransition(bypassStop, atn.ruleToTokenType[i]));
               bypassStart.addTransition(new EpsilonTransition(matchState));
            }

            if (this.deserializationOptions.isVerifyATN()) {
               this.verifyATN(atn);
            }
         }

         return atn;
      }
   }

   private int deserializeSets(int[] data, int p, List sets) {
      int nsets = data[p++];

      for(int i = 0; i < nsets; ++i) {
         int nintervals = data[p];
         ++p;
         IntervalSet set = new IntervalSet(new int[0]);
         sets.add(set);
         boolean containsEof = data[p++] != 0;
         if (containsEof) {
            set.add(-1);
         }

         for(int j = 0; j < nintervals; ++j) {
            int a = data[p++];
            int b = data[p++];
            set.add(a, b);
         }
      }

      return p;
   }

   protected void markPrecedenceDecisions(ATN atn) {
      for(ATNState state : atn.states) {
         if (state instanceof StarLoopEntryState && atn.ruleToStartState[state.ruleIndex].isLeftRecursiveRule) {
            ATNState maybeLoopEndState = state.transition(state.getNumberOfTransitions() - 1).target;
            if (maybeLoopEndState instanceof LoopEndState && maybeLoopEndState.epsilonOnlyTransitions && maybeLoopEndState.transition(0).target instanceof RuleStopState) {
               ((StarLoopEntryState)state).isPrecedenceDecision = true;
            }
         }
      }

   }

   protected void verifyATN(ATN atn) {
      for(ATNState state : atn.states) {
         if (state != null) {
            this.checkCondition(state.onlyHasEpsilonTransitions() || state.getNumberOfTransitions() <= 1);
            if (state instanceof PlusBlockStartState) {
               this.checkCondition(((PlusBlockStartState)state).loopBackState != null);
            }

            if (state instanceof StarLoopEntryState) {
               StarLoopEntryState starLoopEntryState = (StarLoopEntryState)state;
               this.checkCondition(starLoopEntryState.loopBackState != null);
               this.checkCondition(starLoopEntryState.getNumberOfTransitions() == 2);
               if (starLoopEntryState.transition(0).target instanceof StarBlockStartState) {
                  this.checkCondition(starLoopEntryState.transition(1).target instanceof LoopEndState);
                  this.checkCondition(!starLoopEntryState.nonGreedy);
               } else {
                  if (!(starLoopEntryState.transition(0).target instanceof LoopEndState)) {
                     throw new IllegalStateException();
                  }

                  this.checkCondition(starLoopEntryState.transition(1).target instanceof StarBlockStartState);
                  this.checkCondition(starLoopEntryState.nonGreedy);
               }
            }

            if (state instanceof StarLoopbackState) {
               this.checkCondition(state.getNumberOfTransitions() == 1);
               this.checkCondition(state.transition(0).target instanceof StarLoopEntryState);
            }

            if (state instanceof LoopEndState) {
               this.checkCondition(((LoopEndState)state).loopBackState != null);
            }

            if (state instanceof RuleStartState) {
               this.checkCondition(((RuleStartState)state).stopState != null);
            }

            if (state instanceof BlockStartState) {
               this.checkCondition(((BlockStartState)state).endState != null);
            }

            if (state instanceof BlockEndState) {
               this.checkCondition(((BlockEndState)state).startState != null);
            }

            if (state instanceof DecisionState) {
               DecisionState decisionState = (DecisionState)state;
               this.checkCondition(decisionState.getNumberOfTransitions() <= 1 || decisionState.decision >= 0);
            } else {
               this.checkCondition(state.getNumberOfTransitions() <= 1 || state instanceof RuleStopState);
            }
         }
      }

   }

   protected void checkCondition(boolean condition) {
      this.checkCondition(condition, (String)null);
   }

   protected void checkCondition(boolean condition, String message) {
      if (!condition) {
         throw new IllegalStateException(message);
      }
   }

   protected static int toInt(char c) {
      return c;
   }

   protected static int toInt32(char[] data, int offset) {
      return data[offset] | data[offset + 1] << 16;
   }

   protected static int toInt32(int[] data, int offset) {
      return data[offset] | data[offset + 1] << 16;
   }

   protected Transition edgeFactory(ATN atn, int type, int src, int trg, int arg1, int arg2, int arg3, List sets) {
      ATNState target = (ATNState)atn.states.get(trg);
      switch (type) {
         case 1:
            return new EpsilonTransition(target);
         case 2:
            if (arg3 != 0) {
               return new RangeTransition(target, -1, arg2);
            }

            return new RangeTransition(target, arg1, arg2);
         case 3:
            RuleTransition rt = new RuleTransition((RuleStartState)atn.states.get(arg1), arg2, arg3, target);
            return rt;
         case 4:
            PredicateTransition pt = new PredicateTransition(target, arg1, arg2, arg3 != 0);
            return pt;
         case 5:
            if (arg3 != 0) {
               return new AtomTransition(target, -1);
            }

            return new AtomTransition(target, arg1);
         case 6:
            ActionTransition a = new ActionTransition(target, arg1, arg2, arg3 != 0);
            return a;
         case 7:
            return new SetTransition(target, (IntervalSet)sets.get(arg1));
         case 8:
            return new NotSetTransition(target, (IntervalSet)sets.get(arg1));
         case 9:
            return new WildcardTransition(target);
         case 10:
            return new PrecedencePredicateTransition(target, arg1);
         default:
            throw new IllegalArgumentException("The specified transition type is not valid.");
      }
   }

   protected ATNState stateFactory(int type, int ruleIndex) {
      ATNState s;
      switch (type) {
         case 0:
            return null;
         case 1:
            s = new BasicState();
            break;
         case 2:
            s = new RuleStartState();
            break;
         case 3:
            s = new BasicBlockStartState();
            break;
         case 4:
            s = new PlusBlockStartState();
            break;
         case 5:
            s = new StarBlockStartState();
            break;
         case 6:
            s = new TokensStartState();
            break;
         case 7:
            s = new RuleStopState();
            break;
         case 8:
            s = new BlockEndState();
            break;
         case 9:
            s = new StarLoopbackState();
            break;
         case 10:
            s = new StarLoopEntryState();
            break;
         case 11:
            s = new PlusLoopbackState();
            break;
         case 12:
            s = new LoopEndState();
            break;
         default:
            String message = String.format(Locale.getDefault(), "The specified state type %d is not valid.", type);
            throw new IllegalArgumentException(message);
      }

      s.ruleIndex = ruleIndex;
      return s;
   }

   protected LexerAction lexerActionFactory(LexerActionType type, int data1, int data2) {
      switch (type) {
         case CHANNEL:
            return new LexerChannelAction(data1);
         case CUSTOM:
            return new LexerCustomAction(data1, data2);
         case MODE:
            return new LexerModeAction(data1);
         case MORE:
            return LexerMoreAction.INSTANCE;
         case POP_MODE:
            return LexerPopModeAction.INSTANCE;
         case PUSH_MODE:
            return new LexerPushModeAction(data1);
         case SKIP:
            return LexerSkipAction.INSTANCE;
         case TYPE:
            return new LexerTypeAction(data1);
         default:
            throw new IllegalArgumentException(String.format(Locale.getDefault(), "The specified lexer action type %s is not valid.", type));
      }
   }

   public static IntegerList encodeIntsWith16BitWords(IntegerList data) {
      IntegerList data16 = new IntegerList((int)((double)data.size() * (double)1.5F));

      for(int i = 0; i < data.size(); ++i) {
         int v = data.get(i);
         if (v == -1) {
            data16.add(65535);
            data16.add(65535);
         } else if (v <= 32767) {
            data16.add(v);
         } else {
            if (v >= Integer.MAX_VALUE) {
               throw new UnsupportedOperationException("Serialized ATN data element[" + i + "] = " + v + " doesn't fit in 31 bits");
            }

            v &= Integer.MAX_VALUE;
            data16.add(v >> 16 | '耀');
            data16.add(v & '\uffff');
         }
      }

      return data16;
   }

   public static int[] decodeIntsEncodedAs16BitWords(char[] data16) {
      return decodeIntsEncodedAs16BitWords(data16, false);
   }

   public static int[] decodeIntsEncodedAs16BitWords(char[] data16, boolean trimToSize) {
      int[] data = new int[data16.length];
      int i = 0;
      int i2 = 0;

      while(i < data16.length) {
         char v = data16[i++];
         if ((v & '耀') == 0) {
            data[i2++] = v;
         } else {
            char vnext = data16[i++];
            if (v == '\uffff' && vnext == '\uffff') {
               data[i2++] = -1;
            } else {
               data[i2++] = (v & 32767) << 16 | vnext & '\uffff';
            }
         }
      }

      if (trimToSize) {
         return Arrays.copyOf(data, i2);
      } else {
         return data;
      }
   }
}
