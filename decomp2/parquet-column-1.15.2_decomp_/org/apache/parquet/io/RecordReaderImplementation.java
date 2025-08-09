package org.apache.parquet.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.parquet.column.ColumnReader;
import org.apache.parquet.column.impl.ColumnReadStoreImpl;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.io.api.RecordConsumer;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RecordReaderImplementation extends RecordReader {
   private static final Logger LOG = LoggerFactory.getLogger(RecordReaderImplementation.class);
   private final GroupConverter recordRootConverter;
   private final RecordMaterializer recordMaterializer;
   private State[] states;
   private ColumnReader[] columnReaders;
   private boolean shouldSkipCurrentRecord = false;

   public RecordReaderImplementation(MessageColumnIO root, RecordMaterializer recordMaterializer, boolean validating, ColumnReadStoreImpl columnStore) {
      this.recordMaterializer = recordMaterializer;
      this.recordRootConverter = recordMaterializer.getRootConverter();
      PrimitiveColumnIO[] leaves = (PrimitiveColumnIO[])root.getLeaves().toArray(new PrimitiveColumnIO[0]);
      this.columnReaders = new ColumnReader[leaves.length];
      int[][] nextColumnIdxForRepLevel = new int[leaves.length][];
      int[][] levelToClose = new int[leaves.length][];
      GroupConverter[][] groupConverterPaths = new GroupConverter[leaves.length][];
      PrimitiveConverter[] leafConverters = new PrimitiveConverter[leaves.length];
      int[] firstIndexForLevel = new int[256];

      for(int i = 0; i < leaves.length; ++i) {
         PrimitiveColumnIO leafColumnIO = leaves[i];
         int[] indexFieldPath = leafColumnIO.getIndexFieldPath();
         groupConverterPaths[i] = new GroupConverter[indexFieldPath.length - 1];
         GroupConverter current = this.recordRootConverter;

         for(int j = 0; j < indexFieldPath.length - 1; ++j) {
            current = current.getConverter(indexFieldPath[j]).asGroupConverter();
            groupConverterPaths[i][j] = current;
         }

         leafConverters[i] = current.getConverter(indexFieldPath[indexFieldPath.length - 1]).asPrimitiveConverter();
         this.columnReaders[i] = columnStore.getColumnReader(leafColumnIO.getColumnDescriptor());
         int maxRepetitionLevel = leafColumnIO.getRepetitionLevel();
         nextColumnIdxForRepLevel[i] = new int[maxRepetitionLevel + 1];
         levelToClose[i] = new int[maxRepetitionLevel + 1];

         for(int nextRepLevel = 0; nextRepLevel <= maxRepetitionLevel; ++nextRepLevel) {
            if (leafColumnIO.isFirst(nextRepLevel)) {
               firstIndexForLevel[nextRepLevel] = i;
            }

            int nextColIdx;
            if (nextRepLevel == 0) {
               nextColIdx = i + 1;
            } else if (leafColumnIO.isLast(nextRepLevel)) {
               nextColIdx = firstIndexForLevel[nextRepLevel];
            } else {
               nextColIdx = i + 1;
            }

            if (nextColIdx == leaves.length) {
               levelToClose[i][nextRepLevel] = 0;
            } else if (leafColumnIO.isLast(nextRepLevel)) {
               ColumnIO parent = leafColumnIO.getParent(nextRepLevel);
               levelToClose[i][nextRepLevel] = parent.getFieldPath().length - 1;
            } else {
               levelToClose[i][nextRepLevel] = this.getCommonParentLevel(leafColumnIO.getFieldPath(), leaves[nextColIdx].getFieldPath());
            }

            if (levelToClose[i][nextRepLevel] > leaves[i].getFieldPath().length - 1) {
               throw new ParquetEncodingException(Arrays.toString(leaves[i].getFieldPath()) + " -(" + nextRepLevel + ")-> " + levelToClose[i][nextRepLevel]);
            }

            nextColumnIdxForRepLevel[i][nextRepLevel] = nextColIdx;
         }
      }

      this.states = new State[leaves.length];

      for(int i = 0; i < leaves.length; ++i) {
         this.states[i] = new State(i, leaves[i], this.columnReaders[i], levelToClose[i], groupConverterPaths[i], leafConverters[i]);
         int[] definitionLevelToDepth = new int[this.states[i].primitiveColumnIO.getDefinitionLevel() + 1];
         ColumnIO[] path = this.states[i].primitiveColumnIO.getPath();
         int depth = 0;

         for(int d = 0; d < definitionLevelToDepth.length; ++d) {
            while(depth < this.states[i].fieldPath.length - 1 && d >= path[depth + 1].getDefinitionLevel()) {
               ++depth;
            }

            definitionLevelToDepth[d] = depth - 1;
         }

         this.states[i].definitionLevelToDepth = definitionLevelToDepth;
      }

      for(int i = 0; i < leaves.length; ++i) {
         State state = this.states[i];
         int[] nextStateIds = nextColumnIdxForRepLevel[i];
         state.nextState = new State[nextStateIds.length];

         for(int j = 0; j < nextStateIds.length; ++j) {
            state.nextState[j] = nextStateIds[j] == this.states.length ? null : this.states[nextStateIds[j]];
         }
      }

      for(int i = 0; i < this.states.length; ++i) {
         State state = this.states[i];
         Map<Case, Case> definedCases = new HashMap();
         Map<Case, Case> undefinedCases = new HashMap();
         Case[][][] caseLookup = new Case[state.fieldPath.length][][];

         for(int currentLevel = 0; currentLevel < state.fieldPath.length; ++currentLevel) {
            caseLookup[currentLevel] = new Case[state.maxDefinitionLevel + 1][];

            for(int d = 0; d <= state.maxDefinitionLevel; ++d) {
               caseLookup[currentLevel][d] = new Case[state.maxRepetitionLevel + 1];

               for(int nextR = 0; nextR <= state.maxRepetitionLevel; ++nextR) {
                  int caseDepth = Math.max(state.getDepth(d), currentLevel - 1);
                  int caseNextLevel = Math.min(state.nextLevel[nextR], caseDepth + 1);
                  Case currentCase = new Case(currentLevel, caseDepth, caseNextLevel, this.getNextReader(state.id, nextR), d == state.maxDefinitionLevel);
                  Map<Case, Case> cases = currentCase.isDefined() ? definedCases : undefinedCases;
                  if (!cases.containsKey(currentCase)) {
                     currentCase.setID(cases.size());
                     cases.put(currentCase, currentCase);
                  } else {
                     currentCase = (Case)cases.get(currentCase);
                  }

                  caseLookup[currentLevel][d][nextR] = currentCase;
               }
            }
         }

         state.caseLookup = caseLookup;
         state.definedCases = new ArrayList(definedCases.values());
         state.undefinedCases = new ArrayList(undefinedCases.values());
         Comparator<Case> caseComparator = new Comparator() {
            public int compare(Case o1, Case o2) {
               return o1.id - o2.id;
            }
         };
         Collections.sort(state.definedCases, caseComparator);
         Collections.sort(state.undefinedCases, caseComparator);
      }

   }

   private RecordConsumer validator(RecordConsumer recordConsumer, boolean validating, MessageType schema) {
      return (RecordConsumer)(validating ? new ValidatingRecordConsumer(recordConsumer, schema) : recordConsumer);
   }

   private RecordConsumer wrap(RecordConsumer recordConsumer) {
      return (RecordConsumer)(LOG.isDebugEnabled() ? new RecordConsumerLoggingWrapper(recordConsumer) : recordConsumer);
   }

   public Object read() {
      int currentLevel = 0;
      this.recordRootConverter.start();
      State currentState = this.states[0];

      do {
         ColumnReader columnReader = currentState.column;
         int d = columnReader.getCurrentDefinitionLevel();

         for(int depth = currentState.definitionLevelToDepth[d]; currentLevel <= depth; ++currentLevel) {
            currentState.groupConverterPath[currentLevel].start();
         }

         if (d >= currentState.maxDefinitionLevel) {
            columnReader.writeCurrentValueToConverter();
         }

         columnReader.consume();
         int nextR = currentState.maxRepetitionLevel == 0 ? 0 : columnReader.getCurrentRepetitionLevel();

         for(int next = currentState.nextLevel[nextR]; currentLevel > next; --currentLevel) {
            currentState.groupConverterPath[currentLevel - 1].end();
         }

         currentState = currentState.nextState[nextR];
      } while(currentState != null);

      this.recordRootConverter.end();
      T record = (T)this.recordMaterializer.getCurrentRecord();
      this.shouldSkipCurrentRecord = record == null;
      if (this.shouldSkipCurrentRecord) {
         this.recordMaterializer.skipCurrentRecord();
      }

      return record;
   }

   public boolean shouldSkipCurrentRecord() {
      return this.shouldSkipCurrentRecord;
   }

   private static void log(String string) {
      LOG.debug(string);
   }

   int getNextReader(int current, int nextRepetitionLevel) {
      State nextState = this.states[current].nextState[nextRepetitionLevel];
      return nextState == null ? this.states.length : nextState.id;
   }

   int getNextLevel(int current, int nextRepetitionLevel) {
      return this.states[current].nextLevel[nextRepetitionLevel];
   }

   private int getCommonParentLevel(String[] previous, String[] next) {
      int i;
      for(i = 0; i < Math.min(previous.length, next.length) && previous[i].equals(next[i]); ++i) {
      }

      return i;
   }

   protected int getStateCount() {
      return this.states.length;
   }

   protected State getState(int i) {
      return this.states[i];
   }

   protected RecordMaterializer getMaterializer() {
      return this.recordMaterializer;
   }

   protected Converter getRecordConsumer() {
      return this.recordRootConverter;
   }

   protected Iterable getColumnReaders() {
      return Arrays.asList(this.columnReaders);
   }

   public static class Case {
      private int id;
      private final int startLevel;
      private final int depth;
      private final int nextLevel;
      private final boolean goingUp;
      private final boolean goingDown;
      private final int nextState;
      private final boolean defined;

      public Case(int startLevel, int depth, int nextLevel, int nextState, boolean defined) {
         this.startLevel = startLevel;
         this.depth = depth;
         this.nextLevel = nextLevel;
         this.nextState = nextState;
         this.defined = defined;
         this.goingUp = startLevel <= depth;
         this.goingDown = depth + 1 > nextLevel;
      }

      public void setID(int id) {
         this.id = id;
      }

      public int hashCode() {
         int hashCode = 17;
         hashCode += 31 * this.startLevel;
         hashCode += 31 * this.depth;
         hashCode += 31 * this.nextLevel;
         hashCode += 31 * this.nextState;
         hashCode += 31 * (this.defined ? 0 : 1);
         return hashCode;
      }

      public boolean equals(Object obj) {
         return obj instanceof Case ? this.equals((Case)obj) : false;
      }

      public boolean equals(Case other) {
         return other != null && this.startLevel == other.startLevel && this.depth == other.depth && this.nextLevel == other.nextLevel && this.nextState == other.nextState && (this.defined && other.defined || !this.defined && !other.defined);
      }

      public int getID() {
         return this.id;
      }

      public int getStartLevel() {
         return this.startLevel;
      }

      public int getDepth() {
         return this.depth;
      }

      public int getNextLevel() {
         return this.nextLevel;
      }

      public int getNextState() {
         return this.nextState;
      }

      public boolean isGoingUp() {
         return this.goingUp;
      }

      public boolean isGoingDown() {
         return this.goingDown;
      }

      public boolean isDefined() {
         return this.defined;
      }

      public String toString() {
         return "Case " + this.startLevel + " -> " + this.depth + " -> " + this.nextLevel + "; goto sate_" + this.getNextState();
      }
   }

   public static class State {
      public final int id;
      public final PrimitiveColumnIO primitiveColumnIO;
      public final int maxDefinitionLevel;
      public final int maxRepetitionLevel;
      public final PrimitiveType.PrimitiveTypeName primitive;
      public final ColumnReader column;
      public final String[] fieldPath;
      public final int[] indexFieldPath;
      public final GroupConverter[] groupConverterPath;
      public final PrimitiveConverter primitiveConverter;
      public final String primitiveField;
      public final int primitiveFieldIndex;
      public final int[] nextLevel;
      private int[] definitionLevelToDepth;
      private State[] nextState;
      private Case[][][] caseLookup;
      private List definedCases;
      private List undefinedCases;

      private State(int id, PrimitiveColumnIO primitiveColumnIO, ColumnReader column, int[] nextLevel, GroupConverter[] groupConverterPath, PrimitiveConverter primitiveConverter) {
         this.id = id;
         this.primitiveColumnIO = primitiveColumnIO;
         this.maxDefinitionLevel = primitiveColumnIO.getDefinitionLevel();
         this.maxRepetitionLevel = primitiveColumnIO.getRepetitionLevel();
         this.column = column;
         this.nextLevel = nextLevel;
         this.groupConverterPath = groupConverterPath;
         this.primitiveConverter = primitiveConverter;
         this.primitive = primitiveColumnIO.getType().asPrimitiveType().getPrimitiveTypeName();
         this.fieldPath = primitiveColumnIO.getFieldPath();
         this.primitiveField = this.fieldPath[this.fieldPath.length - 1];
         this.indexFieldPath = primitiveColumnIO.getIndexFieldPath();
         this.primitiveFieldIndex = this.indexFieldPath[this.indexFieldPath.length - 1];
      }

      public int getDepth(int definitionLevel) {
         return this.definitionLevelToDepth[definitionLevel];
      }

      public List getDefinedCases() {
         return this.definedCases;
      }

      public List getUndefinedCases() {
         return this.undefinedCases;
      }

      public Case getCase(int currentLevel, int d, int nextR) {
         return this.caseLookup[currentLevel][d][nextR];
      }

      public State getNextState(int nextR) {
         return this.nextState[nextR];
      }
   }
}
