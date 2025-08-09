package org.apache.spark.mllib.util;

import java.util.StringTokenizer;
import org.apache.spark.SparkException;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.ArrayBuilder.;
import scala.runtime.BoxesRunTime;

public final class NumericParser$ {
   public static final NumericParser$ MODULE$ = new NumericParser$();

   public Object parse(final String s) {
      StringTokenizer tokenizer = new StringTokenizer(s, "()[],", true);
      if (!tokenizer.hasMoreTokens()) {
         throw new SparkException("Cannot find any token from the input string.");
      } else {
         String token = tokenizer.nextToken();
         String var4 = "(";
         if (token == null) {
            if (var4 == null) {
               return this.parseTuple(tokenizer);
            }
         } else if (token.equals(var4)) {
            return this.parseTuple(tokenizer);
         }

         String var5 = "[";
         if (token == null) {
            if (var5 == null) {
               return this.parseArray(tokenizer);
            }
         } else if (token.equals(var5)) {
            return this.parseArray(tokenizer);
         }

         return BoxesRunTime.boxToDouble(this.parseDouble(token));
      }
   }

   private double[] parseArray(final StringTokenizer tokenizer) {
      ArrayBuilder values = .MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      boolean parsing = true;
      boolean allowComma = false;
      String token = null;

      while(parsing && tokenizer.hasMoreTokens()) {
         label52: {
            token = tokenizer.nextToken();
            String var6 = "]";
            if (token == null) {
               if (var6 == null) {
                  break label52;
               }
            } else if (token.equals(var6)) {
               break label52;
            }

            label53: {
               String var7 = ",";
               if (token == null) {
                  if (var7 != null) {
                     break label53;
                  }
               } else if (!token.equals(var7)) {
                  break label53;
               }

               if (!allowComma) {
                  throw new SparkException("Found a ',' at a wrong position.");
               }

               allowComma = false;
               continue;
            }

            values.$plus$eq(BoxesRunTime.boxToDouble(this.parseDouble(token)));
            allowComma = true;
            continue;
         }

         parsing = false;
      }

      if (parsing) {
         throw new SparkException("An array must end with ']'.");
      } else {
         return (double[])values.result();
      }
   }

   private Seq parseTuple(final StringTokenizer tokenizer) {
      ListBuffer items = scala.collection.mutable.ListBuffer..MODULE$.empty();
      boolean parsing = true;
      boolean allowComma = false;
      String token = null;

      while(parsing && tokenizer.hasMoreTokens()) {
         label79: {
            token = tokenizer.nextToken();
            String var6 = "(";
            if (token == null) {
               if (var6 == null) {
                  break label79;
               }
            } else if (token.equals(var6)) {
               break label79;
            }

            label80: {
               String var7 = "[";
               if (token == null) {
                  if (var7 == null) {
                     break label80;
                  }
               } else if (token.equals(var7)) {
                  break label80;
               }

               label81: {
                  String var8 = ",";
                  if (token == null) {
                     if (var8 == null) {
                        break label81;
                     }
                  } else if (token.equals(var8)) {
                     break label81;
                  }

                  label50: {
                     String var9 = ")";
                     if (token == null) {
                        if (var9 == null) {
                           break label50;
                        }
                     } else if (token.equals(var9)) {
                        break label50;
                     }

                     if (!token.trim().isEmpty()) {
                        items.$plus$eq(BoxesRunTime.boxToDouble(this.parseDouble(token)));
                        allowComma = true;
                     }
                     continue;
                  }

                  parsing = false;
                  continue;
               }

               if (!allowComma) {
                  throw new SparkException("Found a ',' at a wrong position.");
               }

               allowComma = false;
               continue;
            }

            items.$plus$eq(this.parseArray(tokenizer));
            allowComma = true;
            continue;
         }

         items.$plus$eq(this.parseTuple(tokenizer));
         allowComma = true;
      }

      if (parsing) {
         throw new SparkException("A tuple must end with ')'.");
      } else {
         return items.toSeq();
      }
   }

   private double parseDouble(final String s) {
      try {
         return Double.parseDouble(s);
      } catch (NumberFormatException var3) {
         throw new SparkException("Cannot parse a double from: " + s, var3);
      }
   }

   private NumericParser$() {
   }
}
