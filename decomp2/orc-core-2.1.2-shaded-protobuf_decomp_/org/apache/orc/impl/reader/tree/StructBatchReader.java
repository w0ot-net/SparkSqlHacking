package org.apache.orc.impl.reader.tree;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.impl.OrcFilterContextImpl;
import org.apache.orc.impl.PositionProvider;
import org.apache.orc.impl.TreeReaderFactory;
import org.apache.orc.impl.reader.StripePlanner;

public class StructBatchReader extends BatchReader {
   private final TreeReaderFactory.Context context;
   private final OrcFilterContextImpl filterContext;
   private final TreeReaderFactory.StructTreeReader structReader;

   public StructBatchReader(TypeReader rowReader, TreeReaderFactory.Context context) {
      super(rowReader);
      this.context = context;
      this.filterContext = new OrcFilterContextImpl(context.getSchemaEvolution().getReaderSchema(), context.getSchemaEvolution().isSchemaEvolutionCaseAware());
      this.structReader = (TreeReaderFactory.StructTreeReader)rowReader;
   }

   private void readBatchColumn(VectorizedRowBatch batch, TypeReader child, int batchSize, int index, TypeReader.ReadPhase readPhase) throws IOException {
      ColumnVector colVector = batch.cols[index];
      if (colVector != null) {
         if (readPhase.contains(child.getReaderCategory())) {
            colVector.reset();
            colVector.ensureSize(batchSize, false);
         }

         child.nextVector(colVector, (boolean[])null, batchSize, batch, readPhase);
      }

   }

   public void nextBatch(VectorizedRowBatch batch, int batchSize, TypeReader.ReadPhase readPhase) throws IOException {
      if (readPhase == TypeReader.ReadPhase.ALL || readPhase == TypeReader.ReadPhase.LEADERS) {
         batch.selectedInUse = false;
      }

      this.nextBatchForLevel(batch, batchSize, readPhase);
      if (readPhase == TypeReader.ReadPhase.ALL || readPhase == TypeReader.ReadPhase.LEADERS) {
         batch.size = batchSize;
      }

      if (readPhase == TypeReader.ReadPhase.LEADERS && this.context.getColumnFilterCallback() != null) {
         this.context.getColumnFilterCallback().accept(this.filterContext.setBatch(batch));
      }

   }

   private void nextBatchForLevel(VectorizedRowBatch batch, int batchSize, TypeReader.ReadPhase readPhase) throws IOException {
      TypeReader[] children = this.structReader.fields;

      for(int i = 0; i < children.length && (this.vectorColumnCount == -1 || i < this.vectorColumnCount); ++i) {
         if (TypeReader.shouldProcessChild(children[i], readPhase)) {
            this.readBatchColumn(batch, children[i], batchSize, i, readPhase);
         }
      }

   }

   public void startStripe(StripePlanner planner, TypeReader.ReadPhase readPhase) throws IOException {
      TypeReader[] children = ((TreeReaderFactory.StructTreeReader)this.rootType).fields;

      for(int i = 0; i < children.length && (this.vectorColumnCount == -1 || i < this.vectorColumnCount); ++i) {
         if (TypeReader.shouldProcessChild(children[i], readPhase)) {
            children[i].startStripe(planner, readPhase);
         }
      }

   }

   public void skipRows(long rows, TypeReader.ReadPhase readerCategory) throws IOException {
      TypeReader[] children = ((TreeReaderFactory.StructTreeReader)this.rootType).fields;

      for(int i = 0; i < children.length && (this.vectorColumnCount == -1 || i < this.vectorColumnCount); ++i) {
         if (TypeReader.shouldProcessChild(children[i], readerCategory)) {
            children[i].skipRows(rows, readerCategory);
         }
      }

   }

   public void seek(PositionProvider[] index, TypeReader.ReadPhase readPhase) throws IOException {
      TypeReader[] children = ((TreeReaderFactory.StructTreeReader)this.rootType).fields;

      for(int i = 0; i < children.length && (this.vectorColumnCount == -1 || i < this.vectorColumnCount); ++i) {
         if (TypeReader.shouldProcessChild(children[i], readPhase)) {
            children[i].seek(index, readPhase);
         }
      }

   }
}
