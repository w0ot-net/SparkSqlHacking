package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.NumericType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uf!\u0002\u0010 \u0001\u0006J\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011\u0019\u0003!\u0011#Q\u0001\n\tC\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0013\")\u0001\u000b\u0001C\u0001#\")Q\u000b\u0001C\u0001-\")A\r\u0001C\u0001K\")\u0011\u000e\u0001C\u0001K\")!\u000e\u0001C\u0005W\")A\u0010\u0001C\u0005{\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\b\u0001\t\n\u0011\"\u0001\u0002\n!I\u0011q\u0004\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0005\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"a\u000e\u0001\u0003\u0003%\t!!\u000f\t\u0013\u0005\u0005\u0003!!A\u0005\u0002\u0005\r\u0003\"CA(\u0001\u0005\u0005I\u0011IA)\u0011%\ty\u0006AA\u0001\n\u0003\t\t\u0007C\u0005\u0002f\u0001\t\t\u0011\"\u0011\u0002h!I\u00111\u000e\u0001\u0002\u0002\u0013\u0005\u0013Q\u000e\u0005\n\u0003_\u0002\u0011\u0011!C!\u0003cB\u0011\"a\u001d\u0001\u0003\u0003%\t%!\u001e\b\u0015\u0005et$!A\t\u0002\u0005\nYHB\u0005\u001f?\u0005\u0005\t\u0012A\u0011\u0002~!1\u0001\u000b\u0007C\u0001\u0003+C\u0011\"a\u001c\u0019\u0003\u0003%)%!\u001d\t\u0013\u0005]\u0005$!A\u0005\u0002\u0006e\u0005\"CAP1\u0005\u0005I\u0011QAQ\u0011%\t\u0019\fGA\u0001\n\u0013\t)L\u0001\bQCJ\u001cX\r\u001a*G_JlW\u000f\\1\u000b\u0005\u0001\n\u0013a\u00024fCR,(/\u001a\u0006\u0003E\r\n!!\u001c7\u000b\u0005\u0011*\u0013!B:qCJ\\'B\u0001\u0014(\u0003\u0019\t\u0007/Y2iK*\t\u0001&A\u0002pe\u001e\u001cB\u0001\u0001\u00161gA\u00111FL\u0007\u0002Y)\tQ&A\u0003tG\u0006d\u0017-\u0003\u00020Y\t1\u0011I\\=SK\u001a\u0004\"aK\u0019\n\u0005Ib#a\u0002)s_\u0012,8\r\u001e\t\u0003iur!!N\u001e\u000f\u0005YRT\"A\u001c\u000b\u0005aJ\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u00035J!\u0001\u0010\u0017\u0002\u000fA\f7m[1hK&\u0011ah\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003y1\nQ\u0001\\1cK2,\u0012A\u0011\t\u0003\u0007\u0012k\u0011aH\u0005\u0003\u000b~\u0011\u0011bQ8mk6t'+\u001a4\u0002\r1\f'-\u001a7!\u0003\u0015!XM]7t+\u0005I\u0005c\u0001\u001bK\u0019&\u00111j\u0010\u0002\u0004'\u0016\f\bCA\"N\u0013\tquD\u0001\u0003UKJl\u0017A\u0002;fe6\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0004%N#\u0006CA\"\u0001\u0011\u0015\u0001U\u00011\u0001C\u0011\u00159U\u00011\u0001J\u0003\u001d\u0011Xm]8mm\u0016$\"a\u0016.\u0011\u0005\rC\u0016BA- \u0005A\u0011Vm]8mm\u0016$'KR8s[Vd\u0017\rC\u0003\\\r\u0001\u0007A,\u0001\u0004tG\",W.\u0019\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\fQ\u0001^=qKNT!!Y\u0012\u0002\u0007M\fH.\u0003\u0002d=\nQ1\u000b\u001e:vGR$\u0016\u0010]3\u0002\u0011!\f7\u000fT1cK2,\u0012A\u001a\t\u0003W\u001dL!\u0001\u001b\u0017\u0003\u000f\t{w\u000e\\3b]\u0006a\u0001.Y:J]R,'oY3qi\u0006\tR\r\u001f9b]\u0012Le\u000e^3sC\u000e$\u0018n\u001c8\u0015\u000714x\u000fE\u00025\u00156\u00042\u0001\u000e&o!\ty7O\u0004\u0002qcB\u0011a\u0007L\u0005\u0003e2\na\u0001\u0015:fI\u00164\u0017B\u0001;v\u0005\u0019\u0019FO]5oO*\u0011!\u000f\f\u0005\u00067&\u0001\r\u0001\u0018\u0005\u0006\u000f&\u0001\r\u0001\u001f\t\u0004i)K\bCA\"{\u0013\tYxD\u0001\tJ]R,'/Y2uC\ndW\rV3s[\u0006IQ\r\u001f9b]\u0012$u\u000e\u001e\u000b\u0003[zDQa\u0017\u0006A\u0002q\u000bAaY8qsR)!+a\u0001\u0002\u0006!9\u0001i\u0003I\u0001\u0002\u0004\u0011\u0005bB$\f!\u0003\u0005\r!S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tYAK\u0002C\u0003\u001bY#!a\u0004\u0011\t\u0005E\u00111D\u0007\u0003\u0003'QA!!\u0006\u0002\u0018\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u00033a\u0013AC1o]>$\u0018\r^5p]&!\u0011QDA\n\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\t\u0019CK\u0002J\u0003\u001b\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0015!\u0011\tY#!\u000e\u000e\u0005\u00055\"\u0002BA\u0018\u0003c\tA\u0001\\1oO*\u0011\u00111G\u0001\u0005U\u00064\u0018-C\u0002u\u0003[\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u000f\u0011\u0007-\ni$C\u0002\u0002@1\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0012\u0002LA\u00191&a\u0012\n\u0007\u0005%CFA\u0002B]fD\u0011\"!\u0014\u0011\u0003\u0003\u0005\r!a\u000f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0006\u0005\u0004\u0002V\u0005m\u0013QI\u0007\u0003\u0003/R1!!\u0017-\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003;\n9F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u00014\u0002d!I\u0011Q\n\n\u0002\u0002\u0003\u0007\u0011QI\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002*\u0005%\u0004\"CA''\u0005\u0005\t\u0019AA\u001e\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001e\u0003!!xn\u0015;sS:<GCAA\u0015\u0003\u0019)\u0017/^1mgR\u0019a-a\u001e\t\u0013\u00055c#!AA\u0002\u0005\u0015\u0013A\u0004)beN,GM\u0015$pe6,H.\u0019\t\u0003\u0007b\u0019R\u0001GA@\u0003\u0017\u0003r!!!\u0002\b\nK%+\u0004\u0002\u0002\u0004*\u0019\u0011Q\u0011\u0017\u0002\u000fI,h\u000e^5nK&!\u0011\u0011RAB\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u001b\u000b\u0019*\u0004\u0002\u0002\u0010*!\u0011\u0011SA\u0019\u0003\tIw.C\u0002?\u0003\u001f#\"!a\u001f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bI\u000bY*!(\t\u000b\u0001[\u0002\u0019\u0001\"\t\u000b\u001d[\u0002\u0019A%\u0002\u000fUt\u0017\r\u001d9msR!\u00111UAX!\u0015Y\u0013QUAU\u0013\r\t9\u000b\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b-\nYKQ%\n\u0007\u00055FF\u0001\u0004UkBdWM\r\u0005\t\u0003cc\u0012\u0011!a\u0001%\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005]\u0006\u0003BA\u0016\u0003sKA!a/\u0002.\t1qJ\u00196fGR\u0004"
)
public class ParsedRFormula implements Product, Serializable {
   private final ColumnRef label;
   private final Seq terms;

   public static Option unapply(final ParsedRFormula x$0) {
      return ParsedRFormula$.MODULE$.unapply(x$0);
   }

   public static ParsedRFormula apply(final ColumnRef label, final Seq terms) {
      return ParsedRFormula$.MODULE$.apply(label, terms);
   }

   public static Function1 tupled() {
      return ParsedRFormula$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ParsedRFormula$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ColumnRef label() {
      return this.label;
   }

   public Seq terms() {
      return this.terms;
   }

   public ResolvedRFormula resolve(final StructType schema) {
      Seq dotTerms = this.expandDot(schema);
      ObjectRef includedTerms = ObjectRef.create((Seq).MODULE$);
      Set seen = (Set)scala.collection.mutable.Set..MODULE$.apply(.MODULE$);
      this.terms().foreach((x0$1) -> {
         $anonfun$resolve$1(this, includedTerms, schema, seen, dotTerms, x0$1);
         return BoxedUnit.UNIT;
      });
      return new ResolvedRFormula(this.label().value(), (Seq)((Seq)includedTerms.elem).distinct(), this.hasIntercept());
   }

   public boolean hasLabel() {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(this.label().value()));
   }

   public boolean hasIntercept() {
      BooleanRef intercept = BooleanRef.create(true);
      this.terms().foreach((x0$1) -> {
         $anonfun$hasIntercept$1(intercept, x0$1);
         return BoxedUnit.UNIT;
      });
      return intercept.elem;
   }

   private Seq expandInteraction(final StructType schema, final Seq terms) {
      if (terms.isEmpty()) {
         return new scala.collection.immutable..colon.colon(.MODULE$, .MODULE$);
      } else {
         Seq rest = this.expandInteraction(schema, (Seq)terms.tail());
         InteractableTerm var6 = (InteractableTerm)terms.head();
         Seq var10000;
         if (Dot$.MODULE$.equals(var6)) {
            var10000 = (Seq)this.expandDot(schema).flatMap((t) -> (Seq)rest.map((r) -> (Seq)(new scala.collection.immutable..colon.colon(t, .MODULE$)).$plus$plus(r)));
         } else {
            if (!(var6 instanceof ColumnRef)) {
               throw new MatchError(var6);
            }

            ColumnRef var7 = (ColumnRef)var6;
            String value = var7.value();
            var10000 = (Seq)rest.map((x$4) -> (Seq)(new scala.collection.immutable..colon.colon(value, .MODULE$)).$plus$plus(x$4));
         }

         Seq validInteractions = (Seq)var10000.map((x$5) -> (Seq)x$5.distinct());
         Set seen = (Set)scala.collection.mutable.Set..MODULE$.apply(.MODULE$);
         return (Seq)((SeqOps)validInteractions.flatMap((x0$1) -> {
            if (seen.contains(x0$1.toSet())) {
               return scala.None..MODULE$;
            } else {
               seen.$plus$eq(x0$1.toSet());
               return new Some(x0$1);
            }
         })).sortBy((x$6) -> BoxesRunTime.boxToInteger($anonfun$expandInteraction$6(x$6)), scala.math.Ordering.Int..MODULE$);
      }
   }

   private Seq expandDot(final StructType schema) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$expandDot$1(x$7)))), (x$8) -> x$8.name(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$expandDot$3(this, x$9)))).toImmutableArraySeq();
   }

   public ParsedRFormula copy(final ColumnRef label, final Seq terms) {
      return new ParsedRFormula(label, terms);
   }

   public ColumnRef copy$default$1() {
      return this.label();
   }

   public Seq copy$default$2() {
      return this.terms();
   }

   public String productPrefix() {
      return "ParsedRFormula";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.label();
         }
         case 1 -> {
            return this.terms();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ParsedRFormula;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "label";
         }
         case 1 -> {
            return "terms";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ParsedRFormula) {
               label48: {
                  ParsedRFormula var4 = (ParsedRFormula)x$1;
                  ColumnRef var10000 = this.label();
                  ColumnRef var5 = var4.label();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.terms();
                  Seq var6 = var4.terms();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolve$4(final ColumnRef x2$1, final Seq x$2) {
      boolean var10000;
      label23: {
         scala.collection.immutable..colon.colon var2 = new scala.collection.immutable..colon.colon(x2$1.value(), .MODULE$);
         if (x$2 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!x$2.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolve$6(final Seq fromInteraction$1, final Seq t) {
      return !fromInteraction$1.contains(t.toSet());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolve$7(final Seq dotTerms$1, final Seq x0$2) {
      if (x0$2 != null) {
         SeqOps var4 = scala.package..MODULE$.Seq().unapplySeq(x0$2);
         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1) == 0) {
            String t = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
            return !dotTerms$1.contains(t);
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$resolve$1(final ParsedRFormula $this, final ObjectRef includedTerms$1, final StructType schema$1, final Set seen$1, final Seq dotTerms$1, final Term x0$1) {
      if (x0$1 instanceof ColumnRef var9) {
         includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).$colon$plus(new scala.collection.immutable..colon.colon(var9.value(), .MODULE$));
         BoxedUnit var31 = BoxedUnit.UNIT;
      } else if (x0$1 instanceof ColumnInteraction var10) {
         Seq cols = var10.terms();
         $this.expandInteraction(schema$1, cols).foreach((t) -> {
            if (!seen$1.contains(t.toSet())) {
               includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).$colon$plus(t);
               return seen$1.$plus$eq(t.toSet());
            } else {
               return BoxedUnit.UNIT;
            }
         });
         BoxedUnit var30 = BoxedUnit.UNIT;
      } else if (Dot$.MODULE$.equals(x0$1)) {
         includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).$plus$plus((IterableOnce)dotTerms$1.map((x$1) -> new scala.collection.immutable..colon.colon(x$1, .MODULE$)));
         BoxedUnit var29 = BoxedUnit.UNIT;
      } else {
         if (x0$1 instanceof Deletion) {
            Deletion var12 = (Deletion)x0$1;
            Term term = var12.term();
            if (term != null) {
               if (term instanceof ColumnRef) {
                  ColumnRef var16 = (ColumnRef)term;
                  includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$resolve$4(var16, x$2)));
                  BoxedUnit var22 = BoxedUnit.UNIT;
               } else if (term instanceof ColumnInteraction) {
                  ColumnInteraction var17 = (ColumnInteraction)term;
                  Seq cols = var17.terms();
                  Seq fromInteraction = (Seq)$this.expandInteraction(schema$1, cols).map((x$3) -> x$3.toSet());
                  includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).filter((t) -> BoxesRunTime.boxToBoolean($anonfun$resolve$6(fromInteraction, t)));
                  BoxedUnit var23 = BoxedUnit.UNIT;
               } else if (Dot$.MODULE$.equals(term)) {
                  includedTerms$1.elem = (Seq)((Seq)includedTerms$1.elem).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$resolve$7(dotTerms$1, x0$2)));
                  BoxedUnit var24 = BoxedUnit.UNIT;
               } else {
                  if (term instanceof Deletion) {
                     throw new RuntimeException("Deletion terms cannot be nested");
                  }

                  if (term instanceof Intercept) {
                     BoxedUnit var25 = BoxedUnit.UNIT;
                  } else if (term instanceof Terms) {
                     BoxedUnit var26 = BoxedUnit.UNIT;
                  } else {
                     if (!EmptyTerm$.MODULE$.equals(term)) {
                        throw new MatchError(term);
                     }

                     BoxedUnit var27 = BoxedUnit.UNIT;
                  }
               }

               BoxedUnit var28 = BoxedUnit.UNIT;
               return;
            }
         }

         if (x0$1 instanceof Intercept) {
            BoxedUnit var21 = BoxedUnit.UNIT;
         } else if (x0$1 instanceof Terms) {
            BoxedUnit var20 = BoxedUnit.UNIT;
         } else if (EmptyTerm$.MODULE$.equals(x0$1)) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$hasIntercept$1(final BooleanRef intercept$1, final Term x0$1) {
      if (x0$1 instanceof Intercept var4) {
         boolean enabled = var4.enabled();
         intercept$1.elem = enabled;
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else {
         if (x0$1 instanceof Deletion var6) {
            Term var7 = var6.term();
            if (var7 instanceof Intercept var8) {
               boolean enabled = var8.enabled();
               intercept$1.elem = !enabled;
               BoxedUnit var10 = BoxedUnit.UNIT;
               return;
            }
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$expandInteraction$6(final Seq x$6) {
      return x$6.length();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$expandDot$1(final StructField x$7) {
      DataType var3 = x$7.dataType();
      return var3 instanceof NumericType ? true : (org.apache.spark.sql.types.StringType..MODULE$.equals(var3) ? true : (org.apache.spark.sql.types.BooleanType..MODULE$.equals(var3) ? true : var3 instanceof VectorUDT));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$expandDot$3(final ParsedRFormula $this, final String x$9) {
      boolean var10000;
      label23: {
         String var2 = $this.label().value();
         if (x$9 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!x$9.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public ParsedRFormula(final ColumnRef label, final Seq terms) {
      this.label = label;
      this.terms = terms;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
