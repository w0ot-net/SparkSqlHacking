package org.antlr.v4.runtime.atn;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.antlr.v4.runtime.misc.AbstractEqualityComparator;
import org.antlr.v4.runtime.misc.FlexibleHashMap;
import org.antlr.v4.runtime.misc.MurmurHash;

public enum PredictionMode {
   SLL,
   LL,
   LL_EXACT_AMBIG_DETECTION;

   public static boolean hasSLLConflictTerminatingPrediction(PredictionMode mode, ATNConfigSet configs) {
      if (allConfigsInRuleStopStates(configs)) {
         return true;
      } else {
         if (mode == SLL && configs.hasSemanticContext) {
            ATNConfigSet dup = new ATNConfigSet();

            for(ATNConfig c : configs) {
               c = new ATNConfig(c, SemanticContext.Empty.Instance);
               dup.add(c);
            }

            configs = dup;
         }

         Collection<BitSet> altsets = getConflictingAltSubsets(configs);
         boolean heuristic = hasConflictingAltSet(altsets) && !hasStateAssociatedWithOneAlt(configs);
         return heuristic;
      }
   }

   public static boolean hasConfigInRuleStopState(ATNConfigSet configs) {
      for(ATNConfig c : configs) {
         if (c.state instanceof RuleStopState) {
            return true;
         }
      }

      return false;
   }

   public static boolean allConfigsInRuleStopStates(ATNConfigSet configs) {
      for(ATNConfig config : configs) {
         if (!(config.state instanceof RuleStopState)) {
            return false;
         }
      }

      return true;
   }

   public static int resolvesToJustOneViableAlt(Collection altsets) {
      return getSingleViableAlt(altsets);
   }

   public static boolean allSubsetsConflict(Collection altsets) {
      return !hasNonConflictingAltSet(altsets);
   }

   public static boolean hasNonConflictingAltSet(Collection altsets) {
      for(BitSet alts : altsets) {
         if (alts.cardinality() == 1) {
            return true;
         }
      }

      return false;
   }

   public static boolean hasConflictingAltSet(Collection altsets) {
      for(BitSet alts : altsets) {
         if (alts.cardinality() > 1) {
            return true;
         }
      }

      return false;
   }

   public static boolean allSubsetsEqual(Collection altsets) {
      Iterator<BitSet> it = altsets.iterator();
      BitSet first = (BitSet)it.next();

      while(it.hasNext()) {
         BitSet next = (BitSet)it.next();
         if (!next.equals(first)) {
            return false;
         }
      }

      return true;
   }

   public static int getUniqueAlt(Collection altsets) {
      BitSet all = getAlts(altsets);
      return all.cardinality() == 1 ? all.nextSetBit(0) : 0;
   }

   public static BitSet getAlts(Collection altsets) {
      BitSet all = new BitSet();

      for(BitSet alts : altsets) {
         all.or(alts);
      }

      return all;
   }

   public static BitSet getAlts(ATNConfigSet configs) {
      BitSet alts = new BitSet();

      for(ATNConfig config : configs) {
         alts.set(config.alt);
      }

      return alts;
   }

   public static Collection getConflictingAltSubsets(ATNConfigSet configs) {
      AltAndContextMap configToAlts = new AltAndContextMap();

      for(ATNConfig c : configs) {
         BitSet alts = (BitSet)configToAlts.get(c);
         if (alts == null) {
            alts = new BitSet();
            configToAlts.put(c, alts);
         }

         alts.set(c.alt);
      }

      return configToAlts.values();
   }

   public static Map getStateToAltMap(ATNConfigSet configs) {
      Map<ATNState, BitSet> m = new HashMap();

      for(ATNConfig c : configs) {
         BitSet alts = (BitSet)m.get(c.state);
         if (alts == null) {
            alts = new BitSet();
            m.put(c.state, alts);
         }

         alts.set(c.alt);
      }

      return m;
   }

   public static boolean hasStateAssociatedWithOneAlt(ATNConfigSet configs) {
      Map<ATNState, BitSet> x = getStateToAltMap(configs);

      for(BitSet alts : x.values()) {
         if (alts.cardinality() == 1) {
            return true;
         }
      }

      return false;
   }

   public static int getSingleViableAlt(Collection altsets) {
      BitSet viableAlts = new BitSet();

      for(BitSet alts : altsets) {
         int minAlt = alts.nextSetBit(0);
         viableAlts.set(minAlt);
         if (viableAlts.cardinality() > 1) {
            return 0;
         }
      }

      return viableAlts.nextSetBit(0);
   }

   static class AltAndContextMap extends FlexibleHashMap {
      public AltAndContextMap() {
         super(PredictionMode.AltAndContextConfigEqualityComparator.INSTANCE);
      }
   }

   private static final class AltAndContextConfigEqualityComparator extends AbstractEqualityComparator {
      public static final AltAndContextConfigEqualityComparator INSTANCE = new AltAndContextConfigEqualityComparator();

      public int hashCode(ATNConfig o) {
         int hashCode = MurmurHash.initialize(7);
         hashCode = MurmurHash.update(hashCode, o.state.stateNumber);
         hashCode = MurmurHash.update(hashCode, o.context);
         hashCode = MurmurHash.finish(hashCode, 2);
         return hashCode;
      }

      public boolean equals(ATNConfig a, ATNConfig b) {
         if (a == b) {
            return true;
         } else if (a != null && b != null) {
            return a.state.stateNumber == b.state.stateNumber && a.context.equals(b.context);
         } else {
            return false;
         }
      }
   }
}
