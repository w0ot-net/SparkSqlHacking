package org.apache.spark.api.python;

import scala.MatchError;
import scala.runtime.BoxesRunTime;

public final class PythonEvalType$ {
   public static final PythonEvalType$ MODULE$ = new PythonEvalType$();
   private static final int NON_UDF = 0;
   private static final int SQL_BATCHED_UDF = 100;
   private static final int SQL_ARROW_BATCHED_UDF = 101;
   private static final int SQL_SCALAR_PANDAS_UDF = 200;
   private static final int SQL_GROUPED_MAP_PANDAS_UDF = 201;
   private static final int SQL_GROUPED_AGG_PANDAS_UDF = 202;
   private static final int SQL_WINDOW_AGG_PANDAS_UDF = 203;
   private static final int SQL_SCALAR_PANDAS_ITER_UDF = 204;
   private static final int SQL_MAP_PANDAS_ITER_UDF = 205;
   private static final int SQL_COGROUPED_MAP_PANDAS_UDF = 206;
   private static final int SQL_MAP_ARROW_ITER_UDF = 207;
   private static final int SQL_GROUPED_MAP_PANDAS_UDF_WITH_STATE = 208;
   private static final int SQL_GROUPED_MAP_ARROW_UDF = 209;
   private static final int SQL_COGROUPED_MAP_ARROW_UDF = 210;
   private static final int SQL_TRANSFORM_WITH_STATE_PANDAS_UDF = 211;
   private static final int SQL_TRANSFORM_WITH_STATE_PANDAS_INIT_STATE_UDF = 212;
   private static final int SQL_TABLE_UDF = 300;
   private static final int SQL_ARROW_TABLE_UDF = 301;

   public int NON_UDF() {
      return NON_UDF;
   }

   public int SQL_BATCHED_UDF() {
      return SQL_BATCHED_UDF;
   }

   public int SQL_ARROW_BATCHED_UDF() {
      return SQL_ARROW_BATCHED_UDF;
   }

   public int SQL_SCALAR_PANDAS_UDF() {
      return SQL_SCALAR_PANDAS_UDF;
   }

   public int SQL_GROUPED_MAP_PANDAS_UDF() {
      return SQL_GROUPED_MAP_PANDAS_UDF;
   }

   public int SQL_GROUPED_AGG_PANDAS_UDF() {
      return SQL_GROUPED_AGG_PANDAS_UDF;
   }

   public int SQL_WINDOW_AGG_PANDAS_UDF() {
      return SQL_WINDOW_AGG_PANDAS_UDF;
   }

   public int SQL_SCALAR_PANDAS_ITER_UDF() {
      return SQL_SCALAR_PANDAS_ITER_UDF;
   }

   public int SQL_MAP_PANDAS_ITER_UDF() {
      return SQL_MAP_PANDAS_ITER_UDF;
   }

   public int SQL_COGROUPED_MAP_PANDAS_UDF() {
      return SQL_COGROUPED_MAP_PANDAS_UDF;
   }

   public int SQL_MAP_ARROW_ITER_UDF() {
      return SQL_MAP_ARROW_ITER_UDF;
   }

   public int SQL_GROUPED_MAP_PANDAS_UDF_WITH_STATE() {
      return SQL_GROUPED_MAP_PANDAS_UDF_WITH_STATE;
   }

   public int SQL_GROUPED_MAP_ARROW_UDF() {
      return SQL_GROUPED_MAP_ARROW_UDF;
   }

   public int SQL_COGROUPED_MAP_ARROW_UDF() {
      return SQL_COGROUPED_MAP_ARROW_UDF;
   }

   public int SQL_TRANSFORM_WITH_STATE_PANDAS_UDF() {
      return SQL_TRANSFORM_WITH_STATE_PANDAS_UDF;
   }

   public int SQL_TRANSFORM_WITH_STATE_PANDAS_INIT_STATE_UDF() {
      return SQL_TRANSFORM_WITH_STATE_PANDAS_INIT_STATE_UDF;
   }

   public int SQL_TABLE_UDF() {
      return SQL_TABLE_UDF;
   }

   public int SQL_ARROW_TABLE_UDF() {
      return SQL_ARROW_TABLE_UDF;
   }

   public String toString(final int pythonEvalType) {
      if (this.NON_UDF() == pythonEvalType) {
         return "NON_UDF";
      } else if (this.SQL_BATCHED_UDF() == pythonEvalType) {
         return "SQL_BATCHED_UDF";
      } else if (this.SQL_ARROW_BATCHED_UDF() == pythonEvalType) {
         return "SQL_ARROW_BATCHED_UDF";
      } else if (this.SQL_SCALAR_PANDAS_UDF() == pythonEvalType) {
         return "SQL_SCALAR_PANDAS_UDF";
      } else if (this.SQL_GROUPED_MAP_PANDAS_UDF() == pythonEvalType) {
         return "SQL_GROUPED_MAP_PANDAS_UDF";
      } else if (this.SQL_GROUPED_AGG_PANDAS_UDF() == pythonEvalType) {
         return "SQL_GROUPED_AGG_PANDAS_UDF";
      } else if (this.SQL_WINDOW_AGG_PANDAS_UDF() == pythonEvalType) {
         return "SQL_WINDOW_AGG_PANDAS_UDF";
      } else if (this.SQL_SCALAR_PANDAS_ITER_UDF() == pythonEvalType) {
         return "SQL_SCALAR_PANDAS_ITER_UDF";
      } else if (this.SQL_MAP_PANDAS_ITER_UDF() == pythonEvalType) {
         return "SQL_MAP_PANDAS_ITER_UDF";
      } else if (this.SQL_COGROUPED_MAP_PANDAS_UDF() == pythonEvalType) {
         return "SQL_COGROUPED_MAP_PANDAS_UDF";
      } else if (this.SQL_MAP_ARROW_ITER_UDF() == pythonEvalType) {
         return "SQL_MAP_ARROW_ITER_UDF";
      } else if (this.SQL_GROUPED_MAP_PANDAS_UDF_WITH_STATE() == pythonEvalType) {
         return "SQL_GROUPED_MAP_PANDAS_UDF_WITH_STATE";
      } else if (this.SQL_GROUPED_MAP_ARROW_UDF() == pythonEvalType) {
         return "SQL_GROUPED_MAP_ARROW_UDF";
      } else if (this.SQL_COGROUPED_MAP_ARROW_UDF() == pythonEvalType) {
         return "SQL_COGROUPED_MAP_ARROW_UDF";
      } else if (this.SQL_TABLE_UDF() == pythonEvalType) {
         return "SQL_TABLE_UDF";
      } else if (this.SQL_ARROW_TABLE_UDF() == pythonEvalType) {
         return "SQL_ARROW_TABLE_UDF";
      } else if (this.SQL_TRANSFORM_WITH_STATE_PANDAS_UDF() == pythonEvalType) {
         return "SQL_TRANSFORM_WITH_STATE_PANDAS_UDF";
      } else if (this.SQL_TRANSFORM_WITH_STATE_PANDAS_INIT_STATE_UDF() == pythonEvalType) {
         return "SQL_TRANSFORM_WITH_STATE_PANDAS_INIT_STATE_UDF";
      } else {
         throw new MatchError(BoxesRunTime.boxToInteger(pythonEvalType));
      }
   }

   private PythonEvalType$() {
   }
}
