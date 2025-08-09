package com.ibm.icu.impl.units;

import com.ibm.icu.util.BytesTrie;
import com.ibm.icu.util.CharsTrie;
import com.ibm.icu.util.CharsTrieBuilder;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import com.ibm.icu.util.MeasureUnit;
import com.ibm.icu.util.StringTrieBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MeasureUnitImpl {
   private String identifier;
   private MeasureUnit.Complexity complexity;
   private final ArrayList singleUnits;

   public MeasureUnitImpl() {
      this.identifier = null;
      this.complexity = MeasureUnit.Complexity.SINGLE;
      this.singleUnits = new ArrayList();
   }

   public MeasureUnitImpl(SingleUnitImpl singleUnit) {
      this();
      this.appendSingleUnit(singleUnit);
   }

   public static MeasureUnitImpl forIdentifier(String identifier) {
      return MeasureUnitImpl.UnitsParser.parseForIdentifier(identifier);
   }

   public static MeasureUnitImpl forCurrencyCode(String currencyCode) {
      MeasureUnitImpl result = new MeasureUnitImpl();
      result.identifier = currencyCode;
      return result;
   }

   public MeasureUnitImpl copy() {
      MeasureUnitImpl result = new MeasureUnitImpl();
      result.complexity = this.complexity;
      result.identifier = this.identifier;

      for(SingleUnitImpl singleUnit : this.singleUnits) {
         result.singleUnits.add(singleUnit.copy());
      }

      return result;
   }

   public MeasureUnitImpl copyAndSimplify() {
      MeasureUnitImpl result = new MeasureUnitImpl();

      for(SingleUnitImpl singleUnit : this.getSingleUnits()) {
         boolean unitExist = false;

         for(SingleUnitImpl resultSingleUnit : result.getSingleUnits()) {
            if (resultSingleUnit.getSimpleUnitID().compareTo(singleUnit.getSimpleUnitID()) == 0 && resultSingleUnit.getPrefix().getIdentifier().compareTo(singleUnit.getPrefix().getIdentifier()) == 0) {
               unitExist = true;
               resultSingleUnit.setDimensionality(resultSingleUnit.getDimensionality() + singleUnit.getDimensionality());
               break;
            }
         }

         if (!unitExist) {
            result.appendSingleUnit(singleUnit);
         }
      }

      return result;
   }

   public ArrayList getSingleUnits() {
      return this.singleUnits;
   }

   public void takeReciprocal() {
      this.identifier = null;

      for(SingleUnitImpl singleUnit : this.singleUnits) {
         singleUnit.setDimensionality(singleUnit.getDimensionality() * -1);
      }

   }

   public ArrayList extractIndividualUnitsWithIndices() {
      ArrayList<MeasureUnitImplWithIndex> result = new ArrayList();
      if (this.getComplexity() != MeasureUnit.Complexity.MIXED) {
         result.add(new MeasureUnitImplWithIndex(0, this.copy()));
         return result;
      } else {
         int i = 0;

         for(SingleUnitImpl singleUnit : this.getSingleUnits()) {
            result.add(new MeasureUnitImplWithIndex(i++, new MeasureUnitImpl(singleUnit)));
         }

         return result;
      }
   }

   public void applyDimensionality(int dimensionality) {
      for(SingleUnitImpl singleUnit : this.singleUnits) {
         singleUnit.setDimensionality(singleUnit.getDimensionality() * dimensionality);
      }

   }

   public boolean appendSingleUnit(SingleUnitImpl singleUnit) {
      this.identifier = null;
      if (singleUnit == null) {
         return false;
      } else {
         SingleUnitImpl oldUnit = null;

         for(SingleUnitImpl candidate : this.singleUnits) {
            if (candidate.isCompatibleWith(singleUnit)) {
               oldUnit = candidate;
               break;
            }
         }

         if (oldUnit != null) {
            oldUnit.setDimensionality(oldUnit.getDimensionality() + singleUnit.getDimensionality());
            return false;
         } else {
            this.singleUnits.add(singleUnit.copy());
            if (this.singleUnits.size() > 1 && this.complexity == MeasureUnit.Complexity.SINGLE) {
               this.setComplexity(MeasureUnit.Complexity.COMPOUND);
            }

            return true;
         }
      }
   }

   public MeasureUnit build() {
      return MeasureUnit.fromMeasureUnitImpl(this);
   }

   public SingleUnitImpl getSingleUnitImpl() {
      if (this.singleUnits.size() == 0) {
         return new SingleUnitImpl();
      } else if (this.singleUnits.size() == 1) {
         return ((SingleUnitImpl)this.singleUnits.get(0)).copy();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public MeasureUnit.Complexity getComplexity() {
      return this.complexity;
   }

   public void setComplexity(MeasureUnit.Complexity complexity) {
      this.complexity = complexity;
   }

   public void serialize() {
      if (this.getSingleUnits().size() != 0) {
         if (this.complexity == MeasureUnit.Complexity.COMPOUND) {
            Collections.sort(this.getSingleUnits(), new SingleUnitComparator());
         }

         StringBuilder result = new StringBuilder();
         boolean beforePer = true;
         boolean firstTimeNegativeDimension = false;

         for(SingleUnitImpl singleUnit : this.getSingleUnits()) {
            if (beforePer && singleUnit.getDimensionality() < 0) {
               beforePer = false;
               firstTimeNegativeDimension = true;
            } else if (singleUnit.getDimensionality() < 0) {
               firstTimeNegativeDimension = false;
            }

            if (this.getComplexity() == MeasureUnit.Complexity.MIXED) {
               if (result.length() != 0) {
                  result.append("-and-");
               }
            } else if (firstTimeNegativeDimension) {
               if (result.length() == 0) {
                  result.append("per-");
               } else {
                  result.append("-per-");
               }
            } else if (result.length() != 0) {
               result.append("-");
            }

            result.append(singleUnit.getNeutralIdentifier());
         }

         this.identifier = result.toString();
      }
   }

   public String toString() {
      return "MeasureUnitImpl [" + this.build().getIdentifier() + "]";
   }

   public static enum CompoundPart {
      PER(0),
      TIMES(1),
      AND(2);

      private final int index;

      private CompoundPart(int index) {
         this.index = index;
      }

      public static CompoundPart getCompoundPartFromTrieIndex(int trieIndex) {
         int index = trieIndex - 128;
         switch (index) {
            case 0:
               return PER;
            case 1:
               return TIMES;
            case 2:
               return AND;
            default:
               throw new AssertionError("CompoundPart index must be 0, 1 or 2");
         }
      }

      public int getTrieIndex() {
         return this.index + 128;
      }

      public int getValue() {
         return this.index;
      }
   }

   public static enum PowerPart {
      P2(2),
      P3(3),
      P4(4),
      P5(5),
      P6(6),
      P7(7),
      P8(8),
      P9(9),
      P10(10),
      P11(11),
      P12(12),
      P13(13),
      P14(14),
      P15(15);

      private final int power;

      private PowerPart(int power) {
         this.power = power;
      }

      public static int getPowerFromTrieIndex(int trieIndex) {
         return trieIndex - 256;
      }

      public int getTrieIndex() {
         return this.power + 256;
      }

      public int getValue() {
         return this.power;
      }
   }

   public static enum InitialCompoundPart {
      INITIAL_COMPOUND_PART_PER(0);

      private final int index;

      private InitialCompoundPart(int powerIndex) {
         this.index = powerIndex;
      }

      public static InitialCompoundPart getInitialCompoundPartFromTrieIndex(int trieIndex) {
         int index = trieIndex - 192;
         if (index == 0) {
            return INITIAL_COMPOUND_PART_PER;
         } else {
            throw new IllegalArgumentException("Incorrect trieIndex");
         }
      }

      public int getTrieIndex() {
         return this.index + 192;
      }

      public int getValue() {
         return this.index;
      }
   }

   public static class MeasureUnitImplWithIndex {
      int index;
      MeasureUnitImpl unitImpl;

      MeasureUnitImplWithIndex(int index, MeasureUnitImpl unitImpl) {
         this.index = index;
         this.unitImpl = unitImpl;
      }
   }

   public static class UnitsParser {
      private static volatile CharsTrie savedTrie = null;
      private final CharsTrie trie;
      private final String fSource;
      private int fIndex = 0;
      private boolean fAfterPer = false;
      private boolean fSawAnd = false;
      private static MeasureUnit.MeasurePrefix[] measurePrefixValues = MeasureUnit.MeasurePrefix.values();

      private UnitsParser(String identifier) {
         this.fSource = identifier;

         try {
            this.trie = savedTrie.clone();
         } catch (CloneNotSupportedException var3) {
            throw new ICUCloneNotSupportedException();
         }
      }

      public static MeasureUnitImpl parseForIdentifier(String identifier) {
         if (identifier != null && !identifier.isEmpty()) {
            UnitsParser parser = new UnitsParser(identifier);
            return parser.parse();
         } else {
            return null;
         }
      }

      private static MeasureUnit.MeasurePrefix getPrefixFromTrieIndex(int trieIndex) {
         return measurePrefixValues[trieIndex - 64];
      }

      private static int getTrieIndexForPrefix(MeasureUnit.MeasurePrefix prefix) {
         return prefix.ordinal() + 64;
      }

      private MeasureUnitImpl parse() {
         MeasureUnitImpl result = new MeasureUnitImpl();
         if (this.fSource.isEmpty()) {
            return null;
         } else {
            while(this.hasNext()) {
               this.fSawAnd = false;
               SingleUnitImpl singleUnit = this.nextSingleUnit();
               boolean added = result.appendSingleUnit(singleUnit);
               if (this.fSawAnd && !added) {
                  throw new IllegalArgumentException("Two similar units are not allowed in a mixed unit.");
               }

               if (result.singleUnits.size() >= 2) {
                  MeasureUnit.Complexity complexity = this.fSawAnd ? MeasureUnit.Complexity.MIXED : MeasureUnit.Complexity.COMPOUND;
                  if (result.getSingleUnits().size() == 2) {
                     assert result.getComplexity() == MeasureUnit.Complexity.COMPOUND;

                     result.setComplexity(complexity);
                  } else if (result.getComplexity() != complexity) {
                     throw new IllegalArgumentException("Can't have mixed compound units");
                  }
               }
            }

            return result;
         }
      }

      private SingleUnitImpl nextSingleUnit() {
         SingleUnitImpl result = new SingleUnitImpl();
         int state = 0;
         boolean atStart = this.fIndex == 0;
         Token token = this.nextToken();
         if (atStart) {
            if (token.getType() == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_INITIAL_COMPOUND_PART) {
               assert token.getInitialCompoundPart() == MeasureUnitImpl.InitialCompoundPart.INITIAL_COMPOUND_PART_PER;

               this.fAfterPer = true;
               result.setDimensionality(-1);
               token = this.nextToken();
            }
         } else {
            if (token.getType() != MeasureUnitImpl.UnitsParser.Token.Type.TYPE_COMPOUND_PART) {
               throw new IllegalArgumentException("token type must be TYPE_COMPOUND_PART");
            }

            CompoundPart compoundPart = MeasureUnitImpl.CompoundPart.getCompoundPartFromTrieIndex(token.getMatch());
            switch (compoundPart) {
               case PER:
                  if (this.fSawAnd) {
                     throw new IllegalArgumentException("Mixed compound units not yet supported");
                  }

                  this.fAfterPer = true;
                  result.setDimensionality(-1);
                  break;
               case TIMES:
                  if (this.fAfterPer) {
                     result.setDimensionality(-1);
                  }
                  break;
               case AND:
                  if (this.fAfterPer) {
                     throw new IllegalArgumentException("Can't start with \"-and-\", and mixed compound units");
                  }

                  this.fSawAnd = true;
            }

            token = this.nextToken();
         }

         while(true) {
            switch (token.getType()) {
               case TYPE_POWER_PART:
                  if (state > 0) {
                     throw new IllegalArgumentException();
                  }

                  result.setDimensionality(result.getDimensionality() * token.getPower());
                  state = 1;
                  break;
               case TYPE_PREFIX:
                  if (state > 1) {
                     throw new IllegalArgumentException();
                  }

                  result.setPrefix(token.getPrefix());
                  state = 2;
                  break;
               case TYPE_SIMPLE_UNIT:
                  result.setSimpleUnit(token.getSimpleUnitIndex(), UnitsData.getSimpleUnits());
                  return result;
               default:
                  throw new IllegalArgumentException();
            }

            if (!this.hasNext()) {
               throw new IllegalArgumentException("We ran out of tokens before finding a complete single unit.");
            }

            token = this.nextToken();
         }
      }

      private boolean hasNext() {
         return this.fIndex < this.fSource.length();
      }

      private Token nextToken() {
         this.trie.reset();
         int match = -1;
         int previ = -1;

         while(this.fIndex < this.fSource.length()) {
            BytesTrie.Result result = this.trie.next(this.fSource.charAt(this.fIndex++));
            if (result == BytesTrie.Result.NO_MATCH) {
               break;
            }

            if (result != BytesTrie.Result.NO_VALUE) {
               match = this.trie.getValue();
               previ = this.fIndex;
               if (result == BytesTrie.Result.FINAL_VALUE) {
                  break;
               }

               if (result != BytesTrie.Result.INTERMEDIATE_VALUE) {
                  throw new IllegalArgumentException("result must has an intermediate value");
               }
            }
         }

         if (match < 0) {
            throw new IllegalArgumentException("Encountered unknown token starting at index " + previ);
         } else {
            this.fIndex = previ;
            return new Token(match);
         }
      }

      static {
         CharsTrieBuilder trieBuilder = new CharsTrieBuilder();

         for(MeasureUnit.MeasurePrefix unitPrefix : measurePrefixValues) {
            trieBuilder.add(unitPrefix.getIdentifier(), getTrieIndexForPrefix(unitPrefix));
         }

         trieBuilder.add("-per-", MeasureUnitImpl.CompoundPart.PER.getTrieIndex());
         trieBuilder.add("-", MeasureUnitImpl.CompoundPart.TIMES.getTrieIndex());
         trieBuilder.add("-and-", MeasureUnitImpl.CompoundPart.AND.getTrieIndex());
         trieBuilder.add("per-", MeasureUnitImpl.InitialCompoundPart.INITIAL_COMPOUND_PART_PER.getTrieIndex());
         trieBuilder.add("square-", MeasureUnitImpl.PowerPart.P2.getTrieIndex());
         trieBuilder.add("cubic-", MeasureUnitImpl.PowerPart.P3.getTrieIndex());
         trieBuilder.add("pow2-", MeasureUnitImpl.PowerPart.P2.getTrieIndex());
         trieBuilder.add("pow3-", MeasureUnitImpl.PowerPart.P3.getTrieIndex());
         trieBuilder.add("pow4-", MeasureUnitImpl.PowerPart.P4.getTrieIndex());
         trieBuilder.add("pow5-", MeasureUnitImpl.PowerPart.P5.getTrieIndex());
         trieBuilder.add("pow6-", MeasureUnitImpl.PowerPart.P6.getTrieIndex());
         trieBuilder.add("pow7-", MeasureUnitImpl.PowerPart.P7.getTrieIndex());
         trieBuilder.add("pow8-", MeasureUnitImpl.PowerPart.P8.getTrieIndex());
         trieBuilder.add("pow9-", MeasureUnitImpl.PowerPart.P9.getTrieIndex());
         trieBuilder.add("pow10-", MeasureUnitImpl.PowerPart.P10.getTrieIndex());
         trieBuilder.add("pow11-", MeasureUnitImpl.PowerPart.P11.getTrieIndex());
         trieBuilder.add("pow12-", MeasureUnitImpl.PowerPart.P12.getTrieIndex());
         trieBuilder.add("pow13-", MeasureUnitImpl.PowerPart.P13.getTrieIndex());
         trieBuilder.add("pow14-", MeasureUnitImpl.PowerPart.P14.getTrieIndex());
         trieBuilder.add("pow15-", MeasureUnitImpl.PowerPart.P15.getTrieIndex());
         String[] simpleUnits = UnitsData.getSimpleUnits();

         for(int i = 0; i < simpleUnits.length; ++i) {
            trieBuilder.add(simpleUnits[i], i + 512);
         }

         savedTrie = trieBuilder.build(StringTrieBuilder.Option.FAST);
      }

      static class Token {
         private final int fMatch;
         private final Type type;

         public Token(int fMatch) {
            this.fMatch = fMatch;
            this.type = this.calculateType(fMatch);
         }

         public Type getType() {
            return this.type;
         }

         public MeasureUnit.MeasurePrefix getPrefix() {
            assert this.type == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_PREFIX;

            return MeasureUnitImpl.UnitsParser.getPrefixFromTrieIndex(this.fMatch);
         }

         public int getMatch() {
            assert this.getType() == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_COMPOUND_PART;

            return this.fMatch;
         }

         public InitialCompoundPart getInitialCompoundPart() {
            assert this.type == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_INITIAL_COMPOUND_PART && this.fMatch == MeasureUnitImpl.InitialCompoundPart.INITIAL_COMPOUND_PART_PER.getTrieIndex();

            return MeasureUnitImpl.InitialCompoundPart.getInitialCompoundPartFromTrieIndex(this.fMatch);
         }

         public int getPower() {
            assert this.type == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_POWER_PART;

            return MeasureUnitImpl.PowerPart.getPowerFromTrieIndex(this.fMatch);
         }

         public int getSimpleUnitIndex() {
            assert this.type == MeasureUnitImpl.UnitsParser.Token.Type.TYPE_SIMPLE_UNIT;

            return this.fMatch - 512;
         }

         private Type calculateType(int fMatch) {
            if (fMatch <= 0) {
               throw new AssertionError("fMatch must have a positive value");
            } else if (fMatch < 128) {
               return MeasureUnitImpl.UnitsParser.Token.Type.TYPE_PREFIX;
            } else if (fMatch < 192) {
               return MeasureUnitImpl.UnitsParser.Token.Type.TYPE_COMPOUND_PART;
            } else if (fMatch < 256) {
               return MeasureUnitImpl.UnitsParser.Token.Type.TYPE_INITIAL_COMPOUND_PART;
            } else {
               return fMatch < 512 ? MeasureUnitImpl.UnitsParser.Token.Type.TYPE_POWER_PART : MeasureUnitImpl.UnitsParser.Token.Type.TYPE_SIMPLE_UNIT;
            }
         }

         static enum Type {
            TYPE_UNDEFINED,
            TYPE_PREFIX,
            TYPE_COMPOUND_PART,
            TYPE_INITIAL_COMPOUND_PART,
            TYPE_POWER_PART,
            TYPE_SIMPLE_UNIT;
         }
      }
   }

   static class MeasureUnitImplComparator implements Comparator {
      private final ConversionRates conversionRates;

      public MeasureUnitImplComparator(ConversionRates conversionRates) {
         this.conversionRates = conversionRates;
      }

      public int compare(MeasureUnitImpl o1, MeasureUnitImpl o2) {
         String special1 = this.conversionRates.getSpecialMappingName(o1);
         String special2 = this.conversionRates.getSpecialMappingName(o2);
         if (special1 == null && special2 == null) {
            BigDecimal factor1 = this.conversionRates.getFactorToBase(o1).getConversionRate();
            BigDecimal factor2 = this.conversionRates.getFactorToBase(o2).getConversionRate();
            return factor1.compareTo(factor2);
         } else if (special1 == null) {
            return -1;
         } else {
            return special2 == null ? 1 : special1.compareTo(special2);
         }
      }
   }

   static class MeasureUnitImplWithIndexComparator implements Comparator {
      private MeasureUnitImplComparator measureUnitImplComparator;

      public MeasureUnitImplWithIndexComparator(ConversionRates conversionRates) {
         this.measureUnitImplComparator = new MeasureUnitImplComparator(conversionRates);
      }

      public int compare(MeasureUnitImplWithIndex o1, MeasureUnitImplWithIndex o2) {
         return this.measureUnitImplComparator.compare(o1.unitImpl, o2.unitImpl);
      }
   }

   static class SingleUnitComparator implements Comparator {
      public int compare(SingleUnitImpl o1, SingleUnitImpl o2) {
         return o1.compareTo(o2);
      }
   }
}
