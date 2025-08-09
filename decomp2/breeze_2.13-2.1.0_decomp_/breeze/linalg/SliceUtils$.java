package breeze.linalg;

import breeze.linalg.support.RangeExtender$;
import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.runtime.java8.JFunction1;

public final class SliceUtils$ {
   public static final SliceUtils$ MODULE$ = new SliceUtils$();

   public IndexedSeq mapColumnSeq(final Seq cols, final int nCols) {
      IndexedSeq var3;
      if (cols instanceof Range) {
         Range var5 = (Range)cols;
         var3 = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(var5), nCols).map((JFunction1.mcII.sp)(col) -> MODULE$.mapColumn(col, nCols));
      } else {
         var3 = ((IterableOnceOps)cols.map((JFunction1.mcII.sp)(col) -> MODULE$.mapColumn(col, nCols))).toIndexedSeq();
      }

      return var3;
   }

   public int mapColumn(final int col, final int nCols) {
      switch (col) {
         default:
            if (col < -nCols) {
               throw new ArrayIndexOutOfBoundsException("Column must be in bounds for slice!");
            } else if (col >= nCols) {
               throw new ArrayIndexOutOfBoundsException("Column must be in bounds for slice!");
            } else {
               return col < 0 ? col + nCols : col;
            }
      }
   }

   public IndexedSeq mapRowSeq(final Seq rows, final int nRows) {
      IndexedSeq var3;
      if (rows instanceof Range) {
         Range var5 = (Range)rows;
         var3 = RangeExtender$.MODULE$.getRangeWithoutNegativeIndexes$extension(package$.MODULE$.RangeToRangeExtender(var5), nRows).map((JFunction1.mcII.sp)(row) -> MODULE$.mapRow(row, nRows));
      } else {
         var3 = ((IterableOnceOps)rows.map((JFunction1.mcII.sp)(row) -> MODULE$.mapRow(row, nRows))).toIndexedSeq();
      }

      return var3;
   }

   public int mapRow(final int row, final int nRows) {
      switch (row) {
         default:
            if (row < -nRows) {
               throw new ArrayIndexOutOfBoundsException("Row must be in bounds for slice!");
            } else if (row >= nRows) {
               throw new ArrayIndexOutOfBoundsException("Row must be in bounds for slice!");
            } else {
               return row < 0 ? row + nRows : row;
            }
      }
   }

   private SliceUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
