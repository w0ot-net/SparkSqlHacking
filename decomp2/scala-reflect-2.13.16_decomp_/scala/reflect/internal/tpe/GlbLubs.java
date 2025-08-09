package scala.reflect.internal.tpe;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.AbstractSeq;
import scala.collection.IterableOnceOps;
import scala.collection.LinearSeq;
import scala.collection.LinearSeqOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.AnnotationCheckers;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.TableDef;
import scala.reflect.internal.util.TableDef$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mfA\u0003\u0010 !\u0003\r\t!I\u0014\u00026\")A\u0006\u0001C\u0001]!9!\u0007\u0001b\u0001\n\u001b\u0019\u0004bB\u001c\u0001\u0005\u0004%i\u0001\u000f\u0005\u0006w\u0001!I\u0001\u0010\u0005\u0006;\u0002!\tA\u0018\u0005\u0007E\u0002\u0001K\u0011B2\t\u000b\u0019\u0004A\u0011A4\t\u000f%\u0004!\u0019!C\u0005U\")\u0001\u000f\u0001C\u0005c\")\u0011\u0010\u0001C\u0005u\")A\u0010\u0001C\u0005{\"9\u0011q\u0002\u0001\u0005\u0002\u0005E\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003;\u0001A\u0011AA\u0010\u0011%\t\u0019\u0003\u0001b!\n\u0013\t)\u0003C\u0004\u0002>\u0001!\t!!\n\t\u0013\u0005}\u0002A1Q\u0005\n\u0005\u0015\u0002bBA!\u0001\u0011\u0005\u0011Q\u0005\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011!\t\u0019\u0005\u0001C\tC\u0005%\u0003\"CA)\u0001\t\u0007I\u0011AA*\u0011%\t)\u0007\u0001a!\n\u0013\t9\u0007C\u0005\u0002j\u0001\u0001\r\u0015\"\u0003\u0002l!I\u0011\u0011\u000f\u0001C\u0002\u00135\u0011q\r\u0005\b\u0003g\u0002A\u0011AA;\u0011!\t\u0019\b\u0001C\tC\u0005e\u0004bBA@\u0001\u0011E\u0011\u0011\u0011\u0005\b\u0003\u000f\u0003A\u0011BAE\u0011\u001d\tY\u000b\u0001C\u0005\u0003[\u0013qa\u00127c\u0019V\u00147O\u0003\u0002!C\u0005\u0019A\u000f]3\u000b\u0005\t\u001a\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005\u0011*\u0013a\u0002:fM2,7\r\u001e\u0006\u0002M\u0005)1oY1mCN\u0011\u0001\u0001\u000b\t\u0003S)j\u0011!J\u0005\u0003W\u0015\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003=\u0002\"!\u000b\u0019\n\u0005E*#\u0001B+oSR\f\u0011\u0002\u001d:j]RdUOY:\u0016\u0003Q\u0002\"!K\u001b\n\u0005Y*#a\u0002\"p_2,\u0017M\\\u0001\u000bm\u0016\u0014\u0018NZ=Mk\n\u001cX#A\u001d\u0010\u0003iJ\u0012!A\u0001\u000faJLg\u000e\u001e'vE6\u000bGO]5y)\rySh\u0016\u0005\u0006}\u0011\u0001\raP\u0001\u0007ER\u001cX*\u00199\u0011\t\u0001;%\n\u0015\b\u0003\u0003\u0016\u0003\"AQ\u0013\u000e\u0003\rS!\u0001R\u0017\u0002\rq\u0012xn\u001c;?\u0013\t1U%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u00131!T1q\u0015\t1U\u0005\u0005\u0002L\u00196\t\u0001!\u0003\u0002N\u001d\n!A+\u001f9f\u0013\ty\u0015EA\u0003UsB,7\u000fE\u0002R)*s!!\u000b*\n\u0005M+\u0013a\u00029bG.\fw-Z\u0005\u0003+Z\u0013A\u0001T5ti*\u00111+\n\u0005\u00061\u0012\u0001\r!W\u0001\u0006I\u0016\u0004H\u000f\u001b\t\u00035nk\u0011!I\u0005\u00039\u0006\u0012Q\u0001R3qi\"\fq\u0001\\;c\u0019&\u001cH\u000fF\u0002Q?\u0006DQ\u0001Y\u0003A\u0002A\u000b!\u0001^:\t\u000ba+\u0001\u0019A-\u0002\u00131,(\rT5ti~CHc\u0001)eK\")\u0001M\u0002a\u0001!\")\u0001L\u0002a\u00013\u0006i1\u000f]1o]&tw\rV=qKN$\"\u0001\u00155\t\u000b\u0001<\u0001\u0019\u0001)\u0002K%\u001cx+\u001b7e\u0007\u0006\u0014Hm\u0014:O_:<%o\\;oIRK\b/\u001a,be\u000e{G\u000e\\3di>\u0014X#A6\u0011\u0005-c\u0017BA7o\u0005E1\u0015N\u001c3UsB,7i\u001c7mK\u000e$xN]\u0005\u0003_~\u0011\u0001\u0002V=qK6\u000b\u0007o]\u0001\t[\u0006DH+\u001f9fgR\u0011!\u000f\u001f\u000b\u0003!NDQ\u0001^\u0005A\u0002U\f!\u0001]8\u0011\u000b%2(J\u0013\u001b\n\u0005],#!\u0003$v]\u000e$\u0018n\u001c83\u0011\u0015\u0001\u0017\u00021\u0001Q\u0003%)G.[7TkB,'\u000f\u0006\u0002Qw\")\u0001M\u0003a\u0001!\u00069Q\r\\5n'V\u0014Gc\u0001)\u007f\u007f\")\u0001m\u0003a\u0001!\")\u0001l\u0003a\u00013\"\u001a1\"a\u0001\u0011\t\u0005\u0015\u00111B\u0007\u0003\u0003\u000fQ1!!\u0003&\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u001b\t9AA\u0004uC&d'/Z2\u0002!M\fW.Z,fC.dUOY!t\u0019V\u0014Gc\u0001\u001b\u0002\u0014!1\u0011Q\u0003\u0007A\u0002A\u000b1\u0001\u001e9t\u0003\u001d9X-Y6Mk\n$2ASA\u000e\u0011\u0019\t)\"\u0004a\u0001!\u0006Qa.^7fe&\u001cG*\u001e2\u0015\u0007)\u000b\t\u0003C\u0003a\u001d\u0001\u0007\u0001+A\u0006`YV\u0014'+Z:vYR\u001cXCAA\u0014!\u001d\tI#a\r\u00028)k!!a\u000b\u000b\t\u00055\u0012qF\u0001\b[V$\u0018M\u00197f\u0015\r\t\t$J\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u001b\u0003W\u0011q\u0001S1tQ6\u000b\u0007\u000fE\u0003*\u0003sI\u0006+C\u0002\u0002<\u0015\u0012a\u0001V;qY\u0016\u0014\u0014A\u00037vEJ+7/\u001e7ug\u0006Yql\u001a7c%\u0016\u001cX\u000f\u001c;t\u0003)9GN\u0019*fgVdGo]\u0001\u0004YV\u0014Gc\u0001&\u0002H!)\u0001m\u0005a\u0001!R)!*a\u0013\u0002P!1\u0011Q\n\u000bA\u0002A\u000b1\u0001^:1\u0011\u0015AF\u00031\u0001Z\u0003)9EN\u0019$bS2,(/Z\u000b\u0003\u0003+\u0002B!a\u0016\u0002b5\u0011\u0011\u0011\f\u0006\u0005\u00037\ni&\u0001\u0003mC:<'BAA0\u0003\u0011Q\u0017M^1\n\t\u0005\r\u0014\u0011\f\u0002\n)\"\u0014xn^1cY\u0016\fab\u001a7pE\u0006dw\t\u001c2EKB$\b.F\u0001Z\u0003I9Gn\u001c2bY\u001ec'\rR3qi\"|F%Z9\u0015\u0007=\ni\u0007\u0003\u0005\u0002p]\t\t\u00111\u0001Z\u0003\rAH%M\u0001\u000fO2|'-\u00197HY\nd\u0015.\\5u\u0003\r9GN\u0019\u000b\u0004\u0015\u0006]\u0004\"\u00021\u001a\u0001\u0004\u0001F#\u0002&\u0002|\u0005u\u0004\"\u00021\u001b\u0001\u0004\u0001\u0006\"\u0002-\u001b\u0001\u0004I\u0016aB4mE:{'/\u001c\u000b\u0006\u0015\u0006\r\u0015Q\u0011\u0005\u0007\u0003\u001bZ\u0002\u0019\u0001)\t\u000ba[\u0002\u0019A-\u0002\u001bA|G.\u001f+za\u0016l\u0015\r^2i)1\tY)!%\u0002\u0016\u0006e\u00151TAQ!\rY\u0015QR\u0005\u0004\u0003\u001fs%\u0001\u0003)pYf$\u0016\u0010]3\t\u000f\u0005ME\u00041\u0001\u0002\f\u00061\u0001\u000f\u001e%fC\u0012Da!a&\u001d\u0001\u0004\u0001\u0016A\u00029u%\u0016\u001cH\u000fC\u0003Y9\u0001\u0007\u0011\fC\u0004\u0002\u001er\u0001\r!a(\u0002\u0019%tgm\u001c\"pk:$Gk\u001c9\u0011\u000b%2\b+\u0017&\t\u000f\u0005\rF\u00041\u0001\u0002&\u0006\u0001\"/Z:vYR$\u0016\u0010]3C_R$x.\u001c\t\u0006S\u0005\u001d\u0006KS\u0005\u0004\u0003S+#!\u0003$v]\u000e$\u0018n\u001c82\u0003Ai\u0017\r^2iS:<'+Z:usB,7\u000fF\u0003Q\u0003_\u000b\t\f\u0003\u0004\u0002\u0016u\u0001\r\u0001\u0015\u0005\u0007\u0003gk\u0002\u0019\u0001)\u0002\u0007A$8\u000fE\u0002[\u0003oK1!!/\"\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3"
)
public interface GlbLubs {
   void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(final boolean x$1);

   void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector_$eq(final TypeMaps.FindTypeCollector x$1);

   void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(final HashMap x$1);

   void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(final HashMap x$1);

   void scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(final Throwable x$1);

   void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(final int x$1);

   boolean scala$reflect$internal$tpe$GlbLubs$$printLubs();

   private boolean verifyLubs() {
      return true;
   }

   private void printLubMatrix(final Map btsMap, final int depth) {
      List sorted = (List)btsMap.toList().sortWith((x, y) -> BoxesRunTime.boxToBoolean($anonfun$printLubMatrix$1(x, y)));
      if (sorted == null) {
         throw null;
      } else {
         Object var10000;
         if (sorted == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$printLubMatrix$2((Tuple2)sorted.head()), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)sorted.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$printLubMatrix$2((Tuple2)map_rest.head()), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var23 = null;
         Object var24 = null;
         Object var25 = null;
         Object var26 = null;
         int maxSeqLength = BoxesRunTime.unboxToInt(((List)var10000).max(scala.math.Ordering.Int..MODULE$));
         if (sorted == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Tuple2 var21 = (Tuple2)sorted.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$printLubMatrix$3(this, maxSeqLength, var21), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)sorted.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var21 = (Tuple2)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$printLubMatrix$3(this, maxSeqLength, var21), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var27 = null;
         Object var28 = null;
         Object var29 = null;
         Object var30 = null;
         List transposed = (List)((List)var10000).transpose(scala..less.colon.less..MODULE$.refl());
         Collections var37 = (Collections)this;
         ListBuffer mapWithIndex_lb = new ListBuffer();
         int mapWithIndex_index = 0;

         for(List mapWithIndex_ys = sorted; !mapWithIndex_ys.isEmpty(); ++mapWithIndex_index) {
            Tuple2 var22 = (Tuple2)mapWithIndex_ys.head();
            Object mapWithIndex_$plus$eq_elem = $anonfun$printLubMatrix$4(this, var22, mapWithIndex_index);
            mapWithIndex_lb.addOne(mapWithIndex_$plus$eq_elem);
            mapWithIndex_$plus$eq_elem = null;
            mapWithIndex_ys = (List)mapWithIndex_ys.tail();
         }

         List var38 = mapWithIndex_lb.toList();
         Object var31 = null;
         Object var32 = null;
         Object var34 = null;
         List columns = var38;
         TableDef$ var39 = TableDef$.MODULE$;
         TableDef.Table formatted = (new TableDef(columns)).table(transposed);
         Object println_x = (new StringBuilder(13)).append("** Depth is ").append(new Depth(depth)).append("\n").append(formatted).toString();
         scala.Console..MODULE$.println(println_x);
      }
   }

   // $FF: synthetic method
   static List lubList$(final GlbLubs $this, final List ts, final int depth) {
      return $this.lubList(ts, depth);
   }

   default List lubList(final List ts, final int depth) {
      if (.MODULE$.equals(ts)) {
         return .MODULE$;
      } else {
         if (ts instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)ts;
            Types.Type ty = (Types.Type)var3.head();
            List var5 = var3.next$access$1();
            if (.MODULE$.equals(var5)) {
               return ty.baseTypeSeq().toList();
            }
         }

         return this.lubList_x(ts, depth);
      }
   }

   private List lubList_x(final List ts, final int depth) {
      int lubListDepth = Depth$.MODULE$.Zero();
      Collections var10000 = (Collections)this;
      Object mapToArray_arr = new BaseTypeSeqs.BaseTypeSeq[ts.length()];
      int mapToArray_ix = 0;

      for(List mapToArray_ys = ts; mapToArray_ix < ((Object[])mapToArray_arr).length; mapToArray_ys = (List)mapToArray_ys.tail()) {
         Object array_update_value = ((Types.Type)mapToArray_ys.head()).baseTypeSeq();
         ((Object[])mapToArray_arr)[mapToArray_ix] = array_update_value;
         array_update_value = null;
         ++mapToArray_ix;
      }

      Object var46 = null;
      Object var47 = null;
      BaseTypeSeqs.BaseTypeSeq[] baseTypeSeqs = (BaseTypeSeqs.BaseTypeSeq[])mapToArray_arr;
      int[] ices = new int[((Object[])mapToArray_arr).length];
      ListBuffer var61 = scala.collection.mutable.ListBuffer..MODULE$;
      ListBuffer pretypes = new ListBuffer();
      boolean isFinished = false;

      while(!isFinished && ices[0] < baseTypeSeqs[0].length()) {
         Depth$ incr$extension_this = Depth$.MODULE$;
         int var62 = incr$extension_this.incr$extension(lubListDepth, 1);
         Object var55 = null;
         lubListDepth = var62;
         boolean isUniformFrontier = true;
         int headOf$1_ix = 0;
         Symbols.Symbol sym = baseTypeSeqs[headOf$1_ix].rawElem(ices[headOf$1_ix]).typeSymbol();

         for(int ix = 0; !isFinished && ix < baseTypeSeqs.length; ++ix) {
            if (ices[ix] == baseTypeSeqs[ix].length()) {
               isFinished = true;
            } else {
               Symbols.Symbol btySym = baseTypeSeqs[ix].rawElem(ices[ix]).typeSymbol();
               isUniformFrontier = isUniformFrontier && sym == btySym;
               if (btySym.isLess(sym)) {
                  sym = btySym;
               }
            }
         }

         if (!isFinished) {
            List ys = .MODULE$;

            Types.Type var14;
            for(int kx = baseTypeSeqs.length; kx > 0; ys = ys.$colon$colon(var14)) {
               --kx;
               var14 = baseTypeSeqs[kx].rawElem(ices[kx]);
            }

            if (isUniformFrontier) {
               List var67 = this.elimSub(ys, depth);
               if (var67 == null) {
                  throw null;
               }

               List map_this = var67;
               Object var68;
               if (map_this == .MODULE$) {
                  var68 = .MODULE$;
               } else {
                  Types.Type var38 = (Types.Type)map_this.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$lubList_x$6(this, ts, var38), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     var38 = (Types.Type)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$lubList_x$6(this, ts, var38), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var68 = map_h;
               }

               Object var48 = null;
               Object var49 = null;
               Object var50 = null;
               Object var51 = null;
               Object var52 = null;
               List ts1 = (List)var68;
               Types.Type var16 = ((Types)this).mergePrefixAndArgs(ts1, Variance$.MODULE$.Covariant(), depth);
               if (!((Types)this).NoType().equals(var16)) {
                  pretypes.addOne(var16);
               }

               for(int jx = 0; jx < baseTypeSeqs.length; ++jx) {
                  int var69 = ices[jx]++;
               }
            } else {
               for(int jx = 0; jx < baseTypeSeqs.length; ++jx) {
                  Symbols.Symbol var63 = baseTypeSeqs[jx].rawElem(ices[jx]).typeSymbol();
                  if (var63 == null) {
                     if (sym != null) {
                        continue;
                     }
                  } else if (!var63.equals(sym)) {
                     continue;
                  }

                  int var10002 = ices[jx]++;
               }

               if (this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
                  Predef var64 = scala.Predef..MODULE$;
                  Object map$extension_$this = scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(baseTypeSeqs);
                  int map$extension_len = ((Object[])map$extension_$this).length;
                  Object map$extension_ys = new String[map$extension_len];
                  if (map$extension_len > 0) {
                     for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
                        Object var39 = ((Object[])map$extension_$this)[map$extension_i];
                        Object array_update_value = $anonfun$lubList_x$7(ices, (Tuple2)var39);
                        ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                        array_update_value = null;
                     }
                  }

                  map$extension_$this = null;
                  Object var54 = null;
                  ArraySeq.ofRef var65 = var64.wrapRefArray((Object[])map$extension_ys);
                  String mkString_end = ")";
                  String mkString_sep = "";
                  String mkString_start = "Frontier(\n";
                  if (var65 == null) {
                     throw null;
                  }

                  AbstractIterable mkString_this = var65;
                  String var66 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
                  mkString_this = null;
                  Object var43 = null;
                  Object var44 = null;
                  Object var45 = null;
                  Object println_x = var66;
                  scala.Console..MODULE$.println(println_x);
                  println_x = null;
                  this.printLubMatrixAux$1(lubListDepth, ts, baseTypeSeqs, ices);
               }
            }
         }
      }

      if (this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
         this.printLubMatrixAux$1(depth, ts, baseTypeSeqs, ices);
      }

      return pretypes.toList();
   }

   // $FF: synthetic method
   static List spanningTypes$(final GlbLubs $this, final List ts) {
      return $this.spanningTypes(ts);
   }

   default List spanningTypes(final List ts) {
      if (ts != null) {
         List var10000 = scala.package..MODULE$.List();
         if (var10000 == null) {
            throw null;
         }

         List unapplySeq_this = var10000;
         SeqOps var60 = SeqFactory.unapplySeq$(unapplySeq_this, ts);
         Object var25 = null;
         SeqOps var2 = var60;
         SeqFactory.UnapplySeqWrapper var61 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var2);
         var61 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var61 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 0;
         if (var2.lengthCompare(lengthCompare$extension_len) == 0) {
            return .MODULE$;
         }
      }

      if (!(ts instanceof scala.collection.immutable..colon.colon)) {
         throw new MatchError(ts);
      } else {
         scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)ts;
         Types.Type first = (Types.Type)var3.head();
         List rest = var3.next$access$1();
         if (rest == null) {
            throw null;
         } else {
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = rest;

            Object var65;
            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var65 = .MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Types.Type var24 = (Types.Type)filter_filterCommon_noneIn$1_h;
               if ($anonfun$spanningTypes$1(first, var24) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var65 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var24 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$spanningTypes$1(first, var24) == filter_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           var24 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$spanningTypes$1(first, var24) != filter_filterCommon_isFlipped) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var65 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var34 = null;
                        Object var37 = null;
                        Object var40 = null;
                        Object var43 = null;
                        Object var46 = null;
                        Object var49 = null;
                        Object var52 = null;
                        Object var55 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var30 = null;
                  Object var32 = null;
                  Object var35 = null;
                  Object var38 = null;
                  Object var41 = null;
                  Object var44 = null;
                  Object var47 = null;
                  Object var50 = null;
                  Object var53 = null;
                  Object var56 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var27 = null;
            Object var28 = null;
            Object var29 = null;
            Object var31 = null;
            Object var33 = null;
            Object var36 = null;
            Object var39 = null;
            Object var42 = null;
            Object var45 = null;
            Object var48 = null;
            Object var51 = null;
            Object var54 = null;
            Object var57 = null;
            List filter_filterCommon_result = (List)var65;
            Statics.releaseFence();
            var65 = filter_filterCommon_result;
            filter_filterCommon_result = null;
            List var64 = this.spanningTypes((List)var65);
            if (var64 == null) {
               throw null;
            } else {
               List $colon$colon_this = var64;
               return new scala.collection.immutable..colon.colon(first, $colon$colon_this);
            }
         }
      }
   }

   TypeMaps.FindTypeCollector scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector();

   private List maxTypes(final List ts, final Function2 po) {
      Collections partitionConserve_this = (Collections)this;
      Tuple2 var10000;
      if (ts.isEmpty()) {
         var10000 = partitionConserve_this.scala$reflect$internal$util$Collections$$TupleOfNil();
      } else {
         boolean partitionConserve_b0 = true;
         boolean partitionConserve_canConserve = true;
         List partitionConserve_ys = ts;
         ListBuffer partitionConserve_ayes = null;
         ListBuffer partitionConserve_nays = null;

         for(int partitionConserve_n = 0; !partitionConserve_ys.isEmpty(); partitionConserve_ys = (List)partitionConserve_ys.tail()) {
            Object partitionConserve_y = partitionConserve_ys.head();
            Types.Type var69 = (Types.Type)partitionConserve_y;
            boolean boxToBoolean_b = $anonfun$maxTypes$5(this, var69);
            if (!partitionConserve_canConserve) {
               (boxToBoolean_b ? partitionConserve_ayes : partitionConserve_nays).$plus$eq(partitionConserve_y);
            } else {
               if (partitionConserve_n == 0) {
                  partitionConserve_b0 = boxToBoolean_b;
               } else if (boxToBoolean_b != partitionConserve_b0) {
                  partitionConserve_canConserve = false;
                  partitionConserve_ayes = new ListBuffer();
                  partitionConserve_nays = new ListBuffer();
                  ListBuffer partitionConserve_prefix = partitionConserve_b0 ? partitionConserve_ayes : partitionConserve_nays;
                  int partitionConserve_j = 0;

                  for(List partitionConserve_zs = ts; partitionConserve_j < partitionConserve_n; ++partitionConserve_j) {
                     Object partitionConserve_$plus$eq_elem = partitionConserve_zs.head();
                     partitionConserve_prefix.addOne(partitionConserve_$plus$eq_elem);
                     partitionConserve_$plus$eq_elem = null;
                     partitionConserve_zs = (List)partitionConserve_zs.tail();
                  }

                  (boxToBoolean_b ? partitionConserve_ayes : partitionConserve_nays).addOne(partitionConserve_y);
               }

               ++partitionConserve_n;
            }
         }

         var10000 = partitionConserve_canConserve ? (partitionConserve_b0 ? new Tuple2(ts, .MODULE$) : new Tuple2(.MODULE$, ts)) : new Tuple2(partitionConserve_ayes.toList(), partitionConserve_nays.toList());
      }

      Object var75 = null;
      Object var76 = null;
      Object var77 = null;
      Object var78 = null;
      Object var79 = null;
      Object var80 = null;
      Object var82 = null;
      Tuple2 var4 = var10000;
      if (var4 == null) {
         throw new MatchError((Object)null);
      } else {
         List wilds = (List)var4._1();
         List ts1 = (List)var4._2();
         List sorted = wilds.$colon$colon$colon(ts1);
         if (sorted.lengthCompare(5) > 0) {
            Object loop$1_toCull = .MODULE$;
            Object loop$1_survivors = sorted;

            GlbLubs loop$1_this;
            for(loop$1_this = this; loop$1_survivors instanceof scala.collection.immutable..colon.colon; loop$1_this = var156) {
               scala.collection.immutable..colon.colon var22 = (scala.collection.immutable..colon.colon)loop$1_survivors;
               Types.Type loop$1_h = (Types.Type)var22.head();
               List loop$1_rest = var22.next$access$1();
               var156 = (SymbolTable)loop$1_this;
               if (loop$1_rest == null) {
                  throw null;
               }

               boolean loop$1_filterNot_filterCommon_isFlipped = true;
               List loop$1_filterNot_filterCommon_noneIn$1_l = loop$1_rest;

               Object var10001;
               while(true) {
                  if (loop$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var10001 = .MODULE$;
                     break;
                  }

                  Object loop$1_filterNot_filterCommon_noneIn$1_h = loop$1_filterNot_filterCommon_noneIn$1_l.head();
                  List loop$1_filterNot_filterCommon_noneIn$1_t = (List)loop$1_filterNot_filterCommon_noneIn$1_l.tail();
                  Types.Type var70 = (Types.Type)loop$1_filterNot_filterCommon_noneIn$1_h;
                  if (BoxesRunTime.unboxToBoolean(po.apply(var70, loop$1_h)) != loop$1_filterNot_filterCommon_isFlipped) {
                     List loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = loop$1_filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var10001 = loop$1_filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var70 = (Types.Type)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if (BoxesRunTime.unboxToBoolean(po.apply(var70, loop$1_h)) == loop$1_filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(loop$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                           List loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)loop$1_filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                              loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var70 = (Types.Type)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if (BoxesRunTime.unboxToBoolean(po.apply(var70, loop$1_h)) != loop$1_filterNot_filterCommon_isFlipped) {
                                 loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                    loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var10001 = loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var91 = null;
                           Object var94 = null;
                           Object var97 = null;
                           Object var100 = null;
                           Object var103 = null;
                           Object var106 = null;
                           Object var109 = null;
                           Object var112 = null;
                           break;
                        }

                        loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var87 = null;
                     Object var89 = null;
                     Object var92 = null;
                     Object var95 = null;
                     Object var98 = null;
                     Object var101 = null;
                     Object var104 = null;
                     Object var107 = null;
                     Object var110 = null;
                     Object var113 = null;
                     break;
                  }

                  loop$1_filterNot_filterCommon_noneIn$1_l = loop$1_filterNot_filterCommon_noneIn$1_t;
               }

               Object var84 = null;
               Object var85 = null;
               Object var86 = null;
               Object var88 = null;
               Object var90 = null;
               Object var93 = null;
               Object var96 = null;
               Object var99 = null;
               Object var102 = null;
               Object var105 = null;
               Object var108 = null;
               Object var111 = null;
               Object var114 = null;
               List loop$1_filterNot_filterCommon_result = (List)var10001;
               Statics.releaseFence();
               Object var83 = null;
               loop$1_toCull = new scala.collection.immutable..colon.colon(loop$1_h, (List)loop$1_toCull);
               loop$1_survivors = loop$1_filterNot_filterCommon_result;
            }

            if (loop$1_toCull instanceof scala.collection.immutable..colon.colon) {
               List var25 = ((scala.collection.immutable..colon.colon)loop$1_toCull).next$access$1();
               if (.MODULE$.equals(var25)) {
                  return (List)loop$1_toCull;
               }
            }

            Object loop$1_sieve$1_remaining = loop$1_toCull;
            Object loop$1_sieve$1_res = .MODULE$;

            for(GlbLubs loop$1_sieve$1_this = loop$1_this; loop$1_sieve$1_remaining instanceof scala.collection.immutable..colon.colon; loop$1_sieve$1_this = var158) {
               scala.collection.immutable..colon.colon var44 = (scala.collection.immutable..colon.colon)loop$1_sieve$1_remaining;
               Types.Type loop$1_sieve$1_h = (Types.Type)var44.head();
               List loop$1_sieve$1_tail = var44.next$access$1();
               List loop$1_sieve$1_exists_these = (List)loop$1_sieve$1_res;

               while(true) {
                  if (loop$1_sieve$1_exists_these.isEmpty()) {
                     var157 = false;
                     break;
                  }

                  Types.Type var71 = (Types.Type)loop$1_sieve$1_exists_these.head();
                  if (BoxesRunTime.unboxToBoolean(po.apply(loop$1_sieve$1_h, var71))) {
                     var157 = true;
                     break;
                  }

                  loop$1_sieve$1_exists_these = (List)loop$1_sieve$1_exists_these.tail();
               }

               loop$1_sieve$1_exists_these = null;
               List loop$1_sieve$1_res1 = (List)(var157 ? loop$1_sieve$1_res : new scala.collection.immutable..colon.colon(loop$1_sieve$1_h, (List)loop$1_sieve$1_res));
               var158 = (SymbolTable)loop$1_sieve$1_this;
               loop$1_sieve$1_remaining = loop$1_sieve$1_tail;
               loop$1_sieve$1_res = loop$1_sieve$1_res1;
            }

            return (List)loop$1_sieve$1_res;
         } else if (!(sorted instanceof scala.collection.immutable..colon.colon)) {
            if (.MODULE$.equals(sorted)) {
               return .MODULE$;
            } else {
               throw new MatchError(sorted);
            }
         } else {
            scala.collection.immutable..colon.colon var49 = (scala.collection.immutable..colon.colon)sorted;
            Types.Type stacked$1_t = (Types.Type)var49.head();
            List stacked$1_ts1 = var49.next$access$1();
            if (stacked$1_ts1 == null) {
               throw null;
            } else {
               boolean stacked$1_filterNot_filterCommon_isFlipped = true;
               List stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_ts1;

               while(true) {
                  if (stacked$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var153 = .MODULE$;
                     break;
                  }

                  Object stacked$1_filterNot_filterCommon_noneIn$1_h = stacked$1_filterNot_filterCommon_noneIn$1_l.head();
                  List stacked$1_filterNot_filterCommon_noneIn$1_t = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();
                  Types.Type var72 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_h;
                  if (BoxesRunTime.unboxToBoolean(po.apply(var72, stacked$1_t)) != stacked$1_filterNot_filterCommon_isFlipped) {
                     List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = stacked$1_filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var153 = stacked$1_filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var72 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if (BoxesRunTime.unboxToBoolean(po.apply(var72, stacked$1_t)) == stacked$1_filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                           List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                              stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var72 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if (BoxesRunTime.unboxToBoolean(po.apply(var72, stacked$1_t)) != stacked$1_filterNot_filterCommon_isFlipped) {
                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var153 = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var124 = null;
                           Object var127 = null;
                           Object var130 = null;
                           Object var133 = null;
                           Object var136 = null;
                           Object var139 = null;
                           Object var142 = null;
                           Object var145 = null;
                           break;
                        }

                        stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var120 = null;
                     Object var122 = null;
                     Object var125 = null;
                     Object var128 = null;
                     Object var131 = null;
                     Object var134 = null;
                     Object var137 = null;
                     Object var140 = null;
                     Object var143 = null;
                     Object var146 = null;
                     break;
                  }

                  stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_filterNot_filterCommon_noneIn$1_t;
               }

               Object var117 = null;
               Object var118 = null;
               Object var119 = null;
               Object var121 = null;
               Object var123 = null;
               Object var126 = null;
               Object var129 = null;
               Object var132 = null;
               Object var135 = null;
               Object var138 = null;
               Object var141 = null;
               Object var144 = null;
               Object var147 = null;
               List stacked$1_filterNot_filterCommon_result = (List)var153;
               Statics.releaseFence();
               Object var116 = null;
               List stacked$1_ts2 = stacked$1(stacked$1_filterNot_filterCommon_result, po);
               if (stacked$1_ts2 == null) {
                  throw null;
               } else {
                  List stacked$1_exists_these = stacked$1_ts2;

                  while(true) {
                     if (stacked$1_exists_these.isEmpty()) {
                        var155 = false;
                        break;
                     }

                     Types.Type var73 = (Types.Type)stacked$1_exists_these.head();
                     if (BoxesRunTime.unboxToBoolean(po.apply(stacked$1_t, var73))) {
                        var155 = true;
                        break;
                     }

                     stacked$1_exists_these = (List)stacked$1_exists_these.tail();
                  }

                  Object var148 = null;
                  return (List)(var155 ? stacked$1_ts2 : new scala.collection.immutable..colon.colon(stacked$1_t, stacked$1_ts2));
               }
            }
         }
      }
   }

   private List elimSuper(final List ts) {
      if (ts.lengthCompare(1) <= 0) {
         return ts;
      } else {
         Function2 maxTypes_po = (t1, t2) -> BoxesRunTime.boxToBoolean($anonfun$elimSuper$1(t1, t2));
         Collections maxTypes_partitionConserve_this = (Collections)this;
         Tuple2 var10000;
         if (ts.isEmpty()) {
            var10000 = maxTypes_partitionConserve_this.scala$reflect$internal$util$Collections$$TupleOfNil();
         } else {
            boolean maxTypes_partitionConserve_b0 = true;
            boolean maxTypes_partitionConserve_canConserve = true;
            List maxTypes_partitionConserve_ys = ts;
            ListBuffer maxTypes_partitionConserve_ayes = null;
            ListBuffer maxTypes_partitionConserve_nays = null;

            for(int maxTypes_partitionConserve_n = 0; !maxTypes_partitionConserve_ys.isEmpty(); maxTypes_partitionConserve_ys = (List)maxTypes_partitionConserve_ys.tail()) {
               Object maxTypes_partitionConserve_y = maxTypes_partitionConserve_ys.head();
               Types.Type var91 = (Types.Type)maxTypes_partitionConserve_y;
               boolean maxTypes_partitionConserve_b = $anonfun$maxTypes$5(this, var91);
               if (!maxTypes_partitionConserve_canConserve) {
                  (maxTypes_partitionConserve_b ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays).$plus$eq(maxTypes_partitionConserve_y);
               } else {
                  if (maxTypes_partitionConserve_n == 0) {
                     maxTypes_partitionConserve_b0 = maxTypes_partitionConserve_b;
                  } else if (maxTypes_partitionConserve_b != maxTypes_partitionConserve_b0) {
                     maxTypes_partitionConserve_canConserve = false;
                     maxTypes_partitionConserve_ayes = new ListBuffer();
                     maxTypes_partitionConserve_nays = new ListBuffer();
                     ListBuffer maxTypes_partitionConserve_prefix = maxTypes_partitionConserve_b0 ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays;
                     int maxTypes_partitionConserve_j = 0;

                     for(List maxTypes_partitionConserve_zs = ts; maxTypes_partitionConserve_j < maxTypes_partitionConserve_n; ++maxTypes_partitionConserve_j) {
                        Object maxTypes_partitionConserve_$plus$eq_elem = maxTypes_partitionConserve_zs.head();
                        maxTypes_partitionConserve_prefix.addOne(maxTypes_partitionConserve_$plus$eq_elem);
                        maxTypes_partitionConserve_$plus$eq_elem = null;
                        maxTypes_partitionConserve_zs = (List)maxTypes_partitionConserve_zs.tail();
                     }

                     (maxTypes_partitionConserve_b ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays).addOne(maxTypes_partitionConserve_y);
                  }

                  ++maxTypes_partitionConserve_n;
               }
            }

            var10000 = maxTypes_partitionConserve_canConserve ? (maxTypes_partitionConserve_b0 ? new Tuple2(ts, .MODULE$) : new Tuple2(.MODULE$, ts)) : new Tuple2(maxTypes_partitionConserve_ayes.toList(), maxTypes_partitionConserve_nays.toList());
         }

         Object var96 = null;
         Object var97 = null;
         Object var98 = null;
         Object var99 = null;
         Object var100 = null;
         Object var101 = null;
         Object var103 = null;
         Tuple2 var4 = var10000;
         if (var4 == null) {
            throw new MatchError((Object)null);
         } else {
            List maxTypes_wilds = (List)var4._1();
            List maxTypes_ts1 = (List)var4._2();
            List maxTypes_sorted = maxTypes_wilds.$colon$colon$colon(maxTypes_ts1);
            if (maxTypes_sorted.lengthCompare(5) > 0) {
               Object maxTypes_loop$1_toCull = .MODULE$;
               Object maxTypes_loop$1_survivors = maxTypes_sorted;

               GlbLubs maxTypes_loop$1_this;
               for(maxTypes_loop$1_this = this; maxTypes_loop$1_survivors instanceof scala.collection.immutable..colon.colon; maxTypes_loop$1_this = var238) {
                  scala.collection.immutable..colon.colon var23 = (scala.collection.immutable..colon.colon)maxTypes_loop$1_survivors;
                  Types.Type maxTypes_loop$1_h = (Types.Type)var23.head();
                  List maxTypes_loop$1_rest = var23.next$access$1();
                  var238 = (SymbolTable)maxTypes_loop$1_this;
                  if (maxTypes_loop$1_rest == null) {
                     throw null;
                  }

                  boolean maxTypes_loop$1_filterNot_filterCommon_isFlipped = true;
                  List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l = maxTypes_loop$1_rest;

                  Object var10001;
                  while(true) {
                     if (maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                        var10001 = .MODULE$;
                        break;
                     }

                     Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_h = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.head();
                     List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.tail();
                     Types.Type var228 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_h;
                     if (maxTypes_loop$1_h.$less$colon$less(var228) != maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                        List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t;

                        while(true) {
                           if (maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                              var10001 = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l;
                              break;
                           }

                           Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                           var228 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                           if (maxTypes_loop$1_h.$less$colon$less(var228) == maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                              scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                              List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.tail();

                              scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                              for(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                 scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              }

                              List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                              List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                              while(!maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                 Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                 var228 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                                 if (maxTypes_loop$1_h.$less$colon$less(var228) != maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 } else {
                                    while(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                       scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                       maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                       maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                       maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                    }

                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 }
                              }

                              if (!maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                              }

                              var10001 = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                              Object var112 = null;
                              Object var115 = null;
                              Object var118 = null;
                              Object var121 = null;
                              Object var124 = null;
                              Object var127 = null;
                              Object var130 = null;
                              Object var133 = null;
                              break;
                           }

                           maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        }

                        Object var108 = null;
                        Object var110 = null;
                        Object var113 = null;
                        Object var116 = null;
                        Object var119 = null;
                        Object var122 = null;
                        Object var125 = null;
                        Object var128 = null;
                        Object var131 = null;
                        Object var134 = null;
                        break;
                     }

                     maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t;
                  }

                  Object var105 = null;
                  Object var106 = null;
                  Object var107 = null;
                  Object var109 = null;
                  Object var111 = null;
                  Object var114 = null;
                  Object var117 = null;
                  Object var120 = null;
                  Object var123 = null;
                  Object var126 = null;
                  Object var129 = null;
                  Object var132 = null;
                  Object var135 = null;
                  List maxTypes_loop$1_filterNot_filterCommon_result = (List)var10001;
                  Statics.releaseFence();
                  Object var104 = null;
                  maxTypes_loop$1_toCull = new scala.collection.immutable..colon.colon(maxTypes_loop$1_h, (List)maxTypes_loop$1_toCull);
                  maxTypes_loop$1_survivors = maxTypes_loop$1_filterNot_filterCommon_result;
               }

               if (maxTypes_loop$1_toCull instanceof scala.collection.immutable..colon.colon) {
                  List var26 = ((scala.collection.immutable..colon.colon)maxTypes_loop$1_toCull).next$access$1();
                  if (.MODULE$.equals(var26)) {
                     return (List)maxTypes_loop$1_toCull;
                  }
               }

               Object maxTypes_loop$1_sieve$1_remaining = maxTypes_loop$1_toCull;
               Object maxTypes_loop$1_sieve$1_res = .MODULE$;

               for(GlbLubs maxTypes_loop$1_sieve$1_this = maxTypes_loop$1_this; maxTypes_loop$1_sieve$1_remaining instanceof scala.collection.immutable..colon.colon; maxTypes_loop$1_sieve$1_this = var240) {
                  scala.collection.immutable..colon.colon var45 = (scala.collection.immutable..colon.colon)maxTypes_loop$1_sieve$1_remaining;
                  Types.Type maxTypes_loop$1_sieve$1_h = (Types.Type)var45.head();
                  List maxTypes_loop$1_sieve$1_tail = var45.next$access$1();
                  List maxTypes_loop$1_sieve$1_exists_these = (List)maxTypes_loop$1_sieve$1_res;

                  while(true) {
                     if (maxTypes_loop$1_sieve$1_exists_these.isEmpty()) {
                        var239 = false;
                        break;
                     }

                     Types.Type var92 = (Types.Type)maxTypes_loop$1_sieve$1_exists_these.head();
                     if (var92.$less$colon$less(maxTypes_loop$1_sieve$1_h)) {
                        var239 = true;
                        break;
                     }

                     maxTypes_loop$1_sieve$1_exists_these = (List)maxTypes_loop$1_sieve$1_exists_these.tail();
                  }

                  maxTypes_loop$1_sieve$1_exists_these = null;
                  List maxTypes_loop$1_sieve$1_res1 = (List)(var239 ? maxTypes_loop$1_sieve$1_res : new scala.collection.immutable..colon.colon(maxTypes_loop$1_sieve$1_h, (List)maxTypes_loop$1_sieve$1_res));
                  var240 = (SymbolTable)maxTypes_loop$1_sieve$1_this;
                  maxTypes_loop$1_sieve$1_remaining = maxTypes_loop$1_sieve$1_tail;
                  maxTypes_loop$1_sieve$1_res = maxTypes_loop$1_sieve$1_res1;
               }

               return (List)maxTypes_loop$1_sieve$1_res;
            } else if (!(maxTypes_sorted instanceof scala.collection.immutable..colon.colon)) {
               if (.MODULE$.equals(maxTypes_sorted)) {
                  return .MODULE$;
               } else {
                  throw new MatchError(maxTypes_sorted);
               }
            } else {
               scala.collection.immutable..colon.colon var50 = (scala.collection.immutable..colon.colon)maxTypes_sorted;
               Types.Type maxTypes_stacked$1_t = (Types.Type)var50.head();
               List maxTypes_stacked$1_ts1 = var50.next$access$1();
               if (maxTypes_stacked$1_ts1 == null) {
                  throw null;
               } else {
                  boolean maxTypes_stacked$1_filterNot_filterCommon_isFlipped = true;
                  List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l = maxTypes_stacked$1_ts1;

                  while(true) {
                     if (maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                        var231 = .MODULE$;
                        break;
                     }

                     Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_h = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.head();
                     List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.tail();
                     Types.Type var95 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_h;
                     if (maxTypes_stacked$1_t.$less$colon$less(var95) != maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                        List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t;

                        while(true) {
                           if (maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                              var231 = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l;
                              break;
                           }

                           Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                           var95 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                           if (maxTypes_stacked$1_t.$less$colon$less(var95) == maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                              scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                              List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.tail();

                              scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                              for(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                 scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              }

                              List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                              List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                              while(!maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                 Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                 var95 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                                 if (maxTypes_stacked$1_t.$less$colon$less(var95) != maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 } else {
                                    while(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                       scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                       maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                       maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                       maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                    }

                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 }
                              }

                              if (!maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                              }

                              var231 = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                              Object var145 = null;
                              Object var148 = null;
                              Object var151 = null;
                              Object var154 = null;
                              Object var157 = null;
                              Object var160 = null;
                              Object var163 = null;
                              Object var166 = null;
                              break;
                           }

                           maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        }

                        Object var141 = null;
                        Object var143 = null;
                        Object var146 = null;
                        Object var149 = null;
                        Object var152 = null;
                        Object var155 = null;
                        Object var158 = null;
                        Object var161 = null;
                        Object var164 = null;
                        Object var167 = null;
                        break;
                     }

                     maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t;
                  }

                  Object var138 = null;
                  Object var139 = null;
                  Object var140 = null;
                  Object var142 = null;
                  Object var144 = null;
                  Object var147 = null;
                  Object var150 = null;
                  Object var153 = null;
                  Object var156 = null;
                  Object var159 = null;
                  Object var162 = null;
                  Object var165 = null;
                  Object var168 = null;
                  List maxTypes_stacked$1_filterNot_filterCommon_result = (List)var231;
                  Statics.releaseFence();
                  maxTypes_stacked$1_filterNot_filterCommon_result = null;
                  Object var236;
                  if (maxTypes_stacked$1_filterNot_filterCommon_result instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var71 = (scala.collection.immutable..colon.colon)maxTypes_stacked$1_filterNot_filterCommon_result;
                     Types.Type stacked$1_t = (Types.Type)var71.head();
                     List stacked$1_ts1 = var71.next$access$1();
                     if (stacked$1_ts1 == null) {
                        throw null;
                     }

                     boolean stacked$1_filterNot_filterCommon_isFlipped = true;
                     List stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_ts1;

                     while(true) {
                        if (stacked$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                           var236 = .MODULE$;
                           break;
                        }

                        Object stacked$1_filterNot_filterCommon_noneIn$1_h = stacked$1_filterNot_filterCommon_noneIn$1_l.head();
                        List stacked$1_filterNot_filterCommon_noneIn$1_t = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();
                        Types.Type var225 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_h;
                        if (stacked$1_t.$less$colon$less(var225) != stacked$1_filterNot_filterCommon_isFlipped) {
                           List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = stacked$1_filterNot_filterCommon_noneIn$1_t;

                           while(true) {
                              if (stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                                 var236 = stacked$1_filterNot_filterCommon_noneIn$1_l;
                                 break;
                              }

                              Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                              var225 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                              if (stacked$1_t.$less$colon$less(var225) == stacked$1_filterNot_filterCommon_isFlipped) {
                                 scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                                 List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();

                                 scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                                 for(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                    scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 }

                                 List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                                 List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                                 while(!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                    Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                    var225 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                                    if (stacked$1_t.$less$colon$less(var225) != stacked$1_filterNot_filterCommon_isFlipped) {
                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    } else {
                                       while(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                          scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                          stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                          stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                          stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                       }

                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    }
                                 }

                                 if (!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                                 }

                                 var236 = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                                 Object var189 = null;
                                 Object var193 = null;
                                 Object var197 = null;
                                 Object var201 = null;
                                 Object var205 = null;
                                 Object var209 = null;
                                 Object var213 = null;
                                 Object var217 = null;
                                 break;
                              }

                              stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           }

                           Object var183 = null;
                           Object var186 = null;
                           Object var190 = null;
                           Object var194 = null;
                           Object var198 = null;
                           Object var202 = null;
                           Object var206 = null;
                           Object var210 = null;
                           Object var214 = null;
                           Object var218 = null;
                           break;
                        }

                        stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_filterNot_filterCommon_noneIn$1_t;
                     }

                     Object var177 = null;
                     Object var179 = null;
                     Object var181 = null;
                     Object var184 = null;
                     Object var187 = null;
                     Object var191 = null;
                     Object var195 = null;
                     Object var199 = null;
                     Object var203 = null;
                     Object var207 = null;
                     Object var211 = null;
                     Object var215 = null;
                     Object var219 = null;
                     List stacked$1_filterNot_filterCommon_result = (List)var236;
                     Statics.releaseFence();
                     Object var175 = null;
                     List stacked$1_ts2 = stacked$1(stacked$1_filterNot_filterCommon_result, maxTypes_po);
                     if (stacked$1_ts2 == null) {
                        throw null;
                     }

                     List stacked$1_exists_these = stacked$1_ts2;

                     while(true) {
                        if (stacked$1_exists_these.isEmpty()) {
                           var235 = false;
                           break;
                        }

                        Types.Type var93 = (Types.Type)stacked$1_exists_these.head();
                        if (var93.$less$colon$less(stacked$1_t)) {
                           var235 = true;
                           break;
                        }

                        stacked$1_exists_these = (List)stacked$1_exists_these.tail();
                     }

                     Object var221 = null;
                     var236 = var235 ? stacked$1_ts2 : new scala.collection.immutable..colon.colon(stacked$1_t, stacked$1_ts2);
                  } else {
                     if (!.MODULE$.equals(maxTypes_stacked$1_filterNot_filterCommon_result)) {
                        throw new MatchError(maxTypes_stacked$1_filterNot_filterCommon_result);
                     }

                     var236 = .MODULE$;
                  }

                  Object var170 = null;
                  Object var171 = null;
                  Object var172 = null;
                  Object var173 = null;
                  Object var174 = null;
                  Object var176 = null;
                  Object var178 = null;
                  Object var180 = null;
                  Object var182 = null;
                  Object var185 = null;
                  Object var188 = null;
                  Object var192 = null;
                  Object var196 = null;
                  Object var200 = null;
                  Object var204 = null;
                  Object var208 = null;
                  Object var212 = null;
                  Object var216 = null;
                  Object var220 = null;
                  Object var222 = null;
                  List maxTypes_stacked$1_ts2 = (List)var236;
                  List maxTypes_stacked$1_exists_these = maxTypes_stacked$1_ts2;

                  while(true) {
                     if (maxTypes_stacked$1_exists_these.isEmpty()) {
                        var237 = false;
                        break;
                     }

                     Types.Type var94 = (Types.Type)maxTypes_stacked$1_exists_these.head();
                     if (var94.$less$colon$less(maxTypes_stacked$1_t)) {
                        var237 = true;
                        break;
                     }

                     maxTypes_stacked$1_exists_these = (List)maxTypes_stacked$1_exists_these.tail();
                  }

                  maxTypes_stacked$1_exists_these = null;
                  return (List)(var237 ? maxTypes_stacked$1_ts2 : new scala.collection.immutable..colon.colon(maxTypes_stacked$1_t, maxTypes_stacked$1_ts2));
               }
            }
         }
      }
   }

   private List elimSub(final List ts, final int depth) {
      while(ts.lengthCompare(1) > 0) {
         Function2 maxTypes_po = (x$14, x$15) -> BoxesRunTime.boxToBoolean($anonfun$elimSub$1(this, depth, x$14, x$15));
         Collections maxTypes_partitionConserve_this = (Collections)this;
         Tuple2 var10000;
         if (ts.isEmpty()) {
            var10000 = maxTypes_partitionConserve_this.scala$reflect$internal$util$Collections$$TupleOfNil();
         } else {
            boolean maxTypes_partitionConserve_b0 = true;
            boolean maxTypes_partitionConserve_canConserve = true;
            List maxTypes_partitionConserve_ys = ts;
            ListBuffer maxTypes_partitionConserve_ayes = null;
            ListBuffer maxTypes_partitionConserve_nays = null;

            for(int maxTypes_partitionConserve_n = 0; !maxTypes_partitionConserve_ys.isEmpty(); maxTypes_partitionConserve_ys = (List)maxTypes_partitionConserve_ys.tail()) {
               Object maxTypes_partitionConserve_y = maxTypes_partitionConserve_ys.head();
               Types.Type var107 = (Types.Type)maxTypes_partitionConserve_y;
               boolean maxTypes_partitionConserve_b = $anonfun$maxTypes$5((GlbLubs)this, var107);
               if (!maxTypes_partitionConserve_canConserve) {
                  (maxTypes_partitionConserve_b ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays).$plus$eq(maxTypes_partitionConserve_y);
               } else {
                  if (maxTypes_partitionConserve_n == 0) {
                     maxTypes_partitionConserve_b0 = maxTypes_partitionConserve_b;
                  } else if (maxTypes_partitionConserve_b != maxTypes_partitionConserve_b0) {
                     maxTypes_partitionConserve_canConserve = false;
                     maxTypes_partitionConserve_ayes = new ListBuffer();
                     maxTypes_partitionConserve_nays = new ListBuffer();
                     ListBuffer maxTypes_partitionConserve_prefix = maxTypes_partitionConserve_b0 ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays;
                     int maxTypes_partitionConserve_j = 0;

                     for(List maxTypes_partitionConserve_zs = ts; maxTypes_partitionConserve_j < maxTypes_partitionConserve_n; ++maxTypes_partitionConserve_j) {
                        Object maxTypes_partitionConserve_$plus$eq_elem = maxTypes_partitionConserve_zs.head();
                        maxTypes_partitionConserve_prefix.addOne(maxTypes_partitionConserve_$plus$eq_elem);
                        maxTypes_partitionConserve_$plus$eq_elem = null;
                        maxTypes_partitionConserve_zs = (List)maxTypes_partitionConserve_zs.tail();
                     }

                     (maxTypes_partitionConserve_b ? maxTypes_partitionConserve_ayes : maxTypes_partitionConserve_nays).addOne(maxTypes_partitionConserve_y);
                  }

                  ++maxTypes_partitionConserve_n;
               }
            }

            var10000 = maxTypes_partitionConserve_canConserve ? (maxTypes_partitionConserve_b0 ? new Tuple2(ts, .MODULE$) : new Tuple2(.MODULE$, ts)) : new Tuple2(maxTypes_partitionConserve_ayes.toList(), maxTypes_partitionConserve_nays.toList());
         }

         Object var131 = null;
         Object var132 = null;
         Object var133 = null;
         Object var134 = null;
         Object var135 = null;
         Object var136 = null;
         Object var138 = null;
         Tuple2 var20 = var10000;
         if (var20 == null) {
            throw new MatchError((Object)null);
         }

         List maxTypes_wilds = (List)var20._1();
         List maxTypes_ts1 = (List)var20._2();
         List maxTypes_sorted = maxTypes_wilds.$colon$colon$colon(maxTypes_ts1);
         Object var371;
         if (maxTypes_sorted.lengthCompare(5) > 0) {
            Object maxTypes_loop$1_toCull = .MODULE$;
            Object maxTypes_loop$1_survivors = maxTypes_sorted;

            GlbLubs maxTypes_loop$1_this;
            for(maxTypes_loop$1_this = (GlbLubs)this; maxTypes_loop$1_survivors instanceof scala.collection.immutable..colon.colon; maxTypes_loop$1_this = var368) {
               scala.collection.immutable..colon.colon var39 = (scala.collection.immutable..colon.colon)maxTypes_loop$1_survivors;
               Types.Type maxTypes_loop$1_h = (Types.Type)var39.head();
               List maxTypes_loop$1_rest = var39.next$access$1();
               var368 = (SymbolTable)maxTypes_loop$1_this;
               if (maxTypes_loop$1_rest == null) {
                  throw null;
               }

               boolean maxTypes_loop$1_filterNot_filterCommon_isFlipped = true;
               List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l = maxTypes_loop$1_rest;

               Object var10001;
               while(true) {
                  if (maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var10001 = .MODULE$;
                     break;
                  }

                  Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_h = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.head();
                  List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.tail();
                  Types.Type var112 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$elimSub$1((GlbLubs)this, depth, var112, maxTypes_loop$1_h) != maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                     List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var10001 = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var112 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$elimSub$1((GlbLubs)this, depth, var112, maxTypes_loop$1_h) == maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                           List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                              maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var112 = (Types.Type)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$elimSub$1((GlbLubs)this, depth, var112, maxTypes_loop$1_h) != maxTypes_loop$1_filterNot_filterCommon_isFlipped) {
                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var10001 = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var172 = null;
                           Object var177 = null;
                           Object var182 = null;
                           Object var187 = null;
                           Object var192 = null;
                           Object var197 = null;
                           Object var202 = null;
                           Object var207 = null;
                           break;
                        }

                        maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)maxTypes_loop$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var164 = null;
                     Object var168 = null;
                     Object var173 = null;
                     Object var178 = null;
                     Object var183 = null;
                     Object var188 = null;
                     Object var193 = null;
                     Object var198 = null;
                     Object var203 = null;
                     Object var208 = null;
                     break;
                  }

                  maxTypes_loop$1_filterNot_filterCommon_noneIn$1_l = maxTypes_loop$1_filterNot_filterCommon_noneIn$1_t;
               }

               Object var155 = null;
               Object var158 = null;
               Object var161 = null;
               Object var165 = null;
               Object var169 = null;
               Object var174 = null;
               Object var179 = null;
               Object var184 = null;
               Object var189 = null;
               Object var194 = null;
               Object var199 = null;
               Object var204 = null;
               Object var209 = null;
               List maxTypes_loop$1_filterNot_filterCommon_result = (List)var10001;
               Statics.releaseFence();
               Object var152 = null;
               maxTypes_loop$1_toCull = new scala.collection.immutable..colon.colon(maxTypes_loop$1_h, (List)maxTypes_loop$1_toCull);
               maxTypes_loop$1_survivors = maxTypes_loop$1_filterNot_filterCommon_result;
            }

            label487: {
               if (maxTypes_loop$1_toCull instanceof scala.collection.immutable..colon.colon) {
                  List var42 = ((scala.collection.immutable..colon.colon)maxTypes_loop$1_toCull).next$access$1();
                  if (.MODULE$.equals(var42)) {
                     var371 = maxTypes_loop$1_toCull;
                     break label487;
                  }
               }

               Object maxTypes_loop$1_sieve$1_remaining = maxTypes_loop$1_toCull;
               Object maxTypes_loop$1_sieve$1_res = .MODULE$;

               for(GlbLubs maxTypes_loop$1_sieve$1_this = maxTypes_loop$1_this; maxTypes_loop$1_sieve$1_remaining instanceof scala.collection.immutable..colon.colon; maxTypes_loop$1_sieve$1_this = var370) {
                  scala.collection.immutable..colon.colon var61 = (scala.collection.immutable..colon.colon)maxTypes_loop$1_sieve$1_remaining;
                  Types.Type maxTypes_loop$1_sieve$1_h = (Types.Type)var61.head();
                  List maxTypes_loop$1_sieve$1_tail = var61.next$access$1();
                  List maxTypes_loop$1_sieve$1_exists_these = (List)maxTypes_loop$1_sieve$1_res;

                  while(true) {
                     if (maxTypes_loop$1_sieve$1_exists_these.isEmpty()) {
                        var369 = false;
                        break;
                     }

                     Types.Type var108 = (Types.Type)maxTypes_loop$1_sieve$1_exists_these.head();
                     if ($anonfun$elimSub$1((GlbLubs)this, depth, maxTypes_loop$1_sieve$1_h, var108)) {
                        var369 = true;
                        break;
                     }

                     maxTypes_loop$1_sieve$1_exists_these = (List)maxTypes_loop$1_sieve$1_exists_these.tail();
                  }

                  maxTypes_loop$1_sieve$1_exists_these = null;
                  List maxTypes_loop$1_sieve$1_res1 = (List)(var369 ? maxTypes_loop$1_sieve$1_res : new scala.collection.immutable..colon.colon(maxTypes_loop$1_sieve$1_h, (List)maxTypes_loop$1_sieve$1_res));
                  var370 = (SymbolTable)maxTypes_loop$1_sieve$1_this;
                  maxTypes_loop$1_sieve$1_remaining = maxTypes_loop$1_sieve$1_tail;
                  maxTypes_loop$1_sieve$1_res = maxTypes_loop$1_sieve$1_res1;
               }

               var371 = maxTypes_loop$1_sieve$1_res;
               maxTypes_loop$1_sieve$1_res = null;
               maxTypes_loop$1_sieve$1_remaining = null;
               Object var220 = null;
               Object var223 = null;
               Object var226 = null;
               Object var229 = null;
               Object var233 = null;
            }

            maxTypes_loop$1_survivors = null;
            maxTypes_loop$1_toCull = null;
            Object var144 = null;
            Object var146 = null;
            Object var148 = null;
            Object var150 = null;
            Object var153 = null;
            Object var156 = null;
            Object var159 = null;
            Object var162 = null;
            Object var166 = null;
            Object var170 = null;
            Object var175 = null;
            Object var180 = null;
            Object var185 = null;
            Object var190 = null;
            Object var195 = null;
            Object var200 = null;
            Object var205 = null;
            Object var210 = null;
            Object var212 = null;
            Object var215 = null;
            Object var218 = null;
            Object var221 = null;
            Object var224 = null;
            Object var227 = null;
            Object var230 = null;
            Object var234 = null;
         } else {
            if (!(maxTypes_sorted instanceof scala.collection.immutable..colon.colon)) {
               if (!.MODULE$.equals(maxTypes_sorted)) {
                  throw new MatchError(maxTypes_sorted);
               }

               var371 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon var66 = (scala.collection.immutable..colon.colon)maxTypes_sorted;
               Types.Type maxTypes_stacked$1_t = (Types.Type)var66.head();
               List maxTypes_stacked$1_ts1 = var66.next$access$1();
               if (maxTypes_stacked$1_ts1 == null) {
                  throw null;
               }

               boolean maxTypes_stacked$1_filterNot_filterCommon_isFlipped = true;
               List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l = maxTypes_stacked$1_ts1;

               while(true) {
                  if (maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var371 = .MODULE$;
                     break;
                  }

                  Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_h = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.head();
                  List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.tail();
                  Types.Type var362 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$elimSub$1((GlbLubs)this, depth, var362, maxTypes_stacked$1_t) != maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                     List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var371 = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var362 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$elimSub$1((GlbLubs)this, depth, var362, maxTypes_stacked$1_t) == maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                           List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                              maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var362 = (Types.Type)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$elimSub$1((GlbLubs)this, depth, var362, maxTypes_stacked$1_t) != maxTypes_stacked$1_filterNot_filterCommon_isFlipped) {
                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var371 = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var264 = null;
                           Object var269 = null;
                           Object var274 = null;
                           Object var279 = null;
                           Object var284 = null;
                           Object var289 = null;
                           Object var294 = null;
                           Object var299 = null;
                           break;
                        }

                        maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var256 = null;
                     Object var260 = null;
                     Object var265 = null;
                     Object var270 = null;
                     Object var275 = null;
                     Object var280 = null;
                     Object var285 = null;
                     Object var290 = null;
                     Object var295 = null;
                     Object var300 = null;
                     break;
                  }

                  maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_l = maxTypes_stacked$1_filterNot_filterCommon_noneIn$1_t;
               }

               Object var247 = null;
               Object var250 = null;
               Object var253 = null;
               Object var257 = null;
               Object var261 = null;
               Object var266 = null;
               Object var271 = null;
               Object var276 = null;
               Object var281 = null;
               Object var286 = null;
               Object var291 = null;
               Object var296 = null;
               Object var301 = null;
               List maxTypes_stacked$1_filterNot_filterCommon_result = (List)var371;
               Statics.releaseFence();
               maxTypes_stacked$1_filterNot_filterCommon_result = null;
               if (maxTypes_stacked$1_filterNot_filterCommon_result instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var87 = (scala.collection.immutable..colon.colon)maxTypes_stacked$1_filterNot_filterCommon_result;
                  Types.Type stacked$1_t = (Types.Type)var87.head();
                  List stacked$1_ts1 = var87.next$access$1();
                  if (stacked$1_ts1 == null) {
                     throw null;
                  }

                  boolean stacked$1_filterNot_filterCommon_isFlipped = true;
                  List stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_ts1;

                  while(true) {
                     if (stacked$1_filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                        var371 = .MODULE$;
                        break;
                     }

                     Object stacked$1_filterNot_filterCommon_noneIn$1_h = stacked$1_filterNot_filterCommon_noneIn$1_l.head();
                     List stacked$1_filterNot_filterCommon_noneIn$1_t = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();
                     Types.Type var365 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_h;
                     if ($anonfun$elimSub$1((GlbLubs)this, depth, var365, stacked$1_t) != stacked$1_filterNot_filterCommon_isFlipped) {
                        List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = stacked$1_filterNot_filterCommon_noneIn$1_t;

                        while(true) {
                           if (stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                              var371 = stacked$1_filterNot_filterCommon_noneIn$1_l;
                              break;
                           }

                           Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                           var365 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_x;
                           if (BoxesRunTime.unboxToBoolean($anonfun$elimSub$1$adapted((GlbLubs)this, depth, var365, stacked$1_t)) == stacked$1_filterNot_filterCommon_isFlipped) {
                              scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                              List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_l.tail();

                              scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                              for(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining; stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                 scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              }

                              List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                              List stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                              while(!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                 Object stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                 var365 = (Types.Type)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                                 if (BoxesRunTime.unboxToBoolean($anonfun$elimSub$1$adapted((GlbLubs)this, depth, var365, stacked$1_t)) != stacked$1_filterNot_filterCommon_isFlipped) {
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 } else {
                                    while(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                       scala.collection.immutable..colon.colon stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                       stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                    }

                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 }
                              }

                              if (!stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                                 stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                              }

                              var371 = stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                              Object var326 = null;
                              Object var330 = null;
                              Object var334 = null;
                              Object var338 = null;
                              Object var342 = null;
                              Object var346 = null;
                              Object var350 = null;
                              Object var354 = null;
                              break;
                           }

                           stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)stacked$1_filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        }

                        Object var320 = null;
                        Object var323 = null;
                        Object var327 = null;
                        Object var331 = null;
                        Object var335 = null;
                        Object var339 = null;
                        Object var343 = null;
                        Object var347 = null;
                        Object var351 = null;
                        Object var355 = null;
                        break;
                     }

                     stacked$1_filterNot_filterCommon_noneIn$1_l = stacked$1_filterNot_filterCommon_noneIn$1_t;
                  }

                  Object var314 = null;
                  Object var316 = null;
                  Object var318 = null;
                  Object var321 = null;
                  Object var324 = null;
                  Object var328 = null;
                  Object var332 = null;
                  Object var336 = null;
                  Object var340 = null;
                  Object var344 = null;
                  Object var348 = null;
                  Object var352 = null;
                  Object var356 = null;
                  List stacked$1_filterNot_filterCommon_result = (List)var371;
                  Statics.releaseFence();
                  Object var312 = null;
                  List stacked$1_ts2 = stacked$1(stacked$1_filterNot_filterCommon_result, maxTypes_po);
                  if (stacked$1_ts2 == null) {
                     throw null;
                  }

                  List stacked$1_exists_these = stacked$1_ts2;

                  while(true) {
                     if (stacked$1_exists_these.isEmpty()) {
                        var376 = false;
                        break;
                     }

                     Types.Type var109 = (Types.Type)stacked$1_exists_these.head();
                     if (BoxesRunTime.unboxToBoolean($anonfun$elimSub$1$adapted((GlbLubs)this, depth, stacked$1_t, var109))) {
                        var376 = true;
                        break;
                     }

                     stacked$1_exists_these = (List)stacked$1_exists_these.tail();
                  }

                  Object var358 = null;
                  var371 = var376 ? stacked$1_ts2 : new scala.collection.immutable..colon.colon(stacked$1_t, stacked$1_ts2);
               } else {
                  if (!.MODULE$.equals(maxTypes_stacked$1_filterNot_filterCommon_result)) {
                     throw new MatchError(maxTypes_stacked$1_filterNot_filterCommon_result);
                  }

                  var371 = .MODULE$;
               }

               Object var307 = null;
               Object var308 = null;
               Object var309 = null;
               Object var310 = null;
               Object var311 = null;
               Object var313 = null;
               Object var315 = null;
               Object var317 = null;
               Object var319 = null;
               Object var322 = null;
               Object var325 = null;
               Object var329 = null;
               Object var333 = null;
               Object var337 = null;
               Object var341 = null;
               Object var345 = null;
               Object var349 = null;
               Object var353 = null;
               Object var357 = null;
               Object var359 = null;
               List maxTypes_stacked$1_ts2 = (List)var371;
               List maxTypes_stacked$1_exists_these = maxTypes_stacked$1_ts2;

               while(true) {
                  if (maxTypes_stacked$1_exists_these.isEmpty()) {
                     var378 = false;
                     break;
                  }

                  Types.Type var110 = (Types.Type)maxTypes_stacked$1_exists_these.head();
                  if (BoxesRunTime.unboxToBoolean($anonfun$elimSub$1$adapted((GlbLubs)this, depth, maxTypes_stacked$1_t, var110))) {
                     var378 = true;
                     break;
                  }

                  maxTypes_stacked$1_exists_these = (List)maxTypes_stacked$1_exists_these.tail();
               }

               maxTypes_stacked$1_exists_these = null;
               var371 = var378 ? maxTypes_stacked$1_ts2 : new scala.collection.immutable..colon.colon(maxTypes_stacked$1_t, maxTypes_stacked$1_ts2);
            }

            Object var236 = null;
            Object var238 = null;
            Object var240 = null;
            Object var242 = null;
            Object var245 = null;
            Object var248 = null;
            Object var251 = null;
            Object var254 = null;
            Object var258 = null;
            Object var262 = null;
            Object var267 = null;
            Object var272 = null;
            Object var277 = null;
            Object var282 = null;
            Object var287 = null;
            Object var292 = null;
            Object var297 = null;
            Object var302 = null;
            Object var305 = null;
         }

         Object var126 = null;
         Object var127 = null;
         Object var128 = null;
         Object var129 = null;
         Object var130 = null;
         Object var139 = null;
         Object var141 = null;
         Object var143 = null;
         Object var145 = null;
         Object var147 = null;
         Object var149 = null;
         Object var151 = null;
         Object var154 = null;
         Object var157 = null;
         Object var160 = null;
         Object var163 = null;
         Object var167 = null;
         Object var171 = null;
         Object var176 = null;
         Object var181 = null;
         Object var186 = null;
         Object var191 = null;
         Object var196 = null;
         Object var201 = null;
         Object var206 = null;
         Object var211 = null;
         Object var213 = null;
         Object var216 = null;
         Object var219 = null;
         Object var222 = null;
         Object var225 = null;
         Object var228 = null;
         Object var231 = null;
         Object var235 = null;
         Object var237 = null;
         Object var239 = null;
         Object var241 = null;
         Object var243 = null;
         Object var246 = null;
         Object var249 = null;
         Object var252 = null;
         Object var255 = null;
         Object var259 = null;
         Object var263 = null;
         Object var268 = null;
         Object var273 = null;
         Object var278 = null;
         Object var283 = null;
         Object var288 = null;
         Object var293 = null;
         Object var298 = null;
         Object var303 = null;
         Object var306 = null;
         List ts1 = (List)var371;
         if (ts1.lengthCompare(1) <= 0) {
            return ts1;
         }

         Object mapConserve_loop$3_pending = ts1;
         Object mapConserve_loop$3_unchanged = ts1;
         scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
         List mapConserve_loop$3_mappedHead = null;

         while(!((List)mapConserve_loop$3_pending).isEmpty()) {
            Object mapConserve_loop$3_head0 = ((List)mapConserve_loop$3_pending).head();
            Types.Type var111 = (Types.Type)mapConserve_loop$3_head0;
            Object mapConserve_loop$3_head1 = $anonfun$elimSub$2((GlbLubs)this, var111);
            if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
               mapConserve_loop$3_pending = (List)((List)mapConserve_loop$3_pending).tail();
               mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
               mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
               mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
            } else {
               List mapConserve_loop$3_xc = (List)mapConserve_loop$3_unchanged;
               List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

               scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
               for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                  scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), .MODULE$);
                  if (mapConserve_loop$3_mappedHead1 == null) {
                     mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                  }

                  if (mapConserve_loop$3_mappedLast1 != null) {
                     mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                  }

                  mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
               }

               scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, .MODULE$);
               if (mapConserve_loop$3_mappedHead1 == null) {
                  mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
               }

               if (mapConserve_loop$3_mappedLast1 != null) {
                  mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
               }

               List mapConserve_loop$3_tail0 = (List)((List)mapConserve_loop$3_pending).tail();
               mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
               mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
               mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
               mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
            }
         }

         if (mapConserve_loop$3_mappedHead == null) {
            var371 = mapConserve_loop$3_unchanged;
         } else {
            mapConserve_loop$3_mappedLast.next_$eq((List)mapConserve_loop$3_unchanged);
            var371 = mapConserve_loop$3_mappedHead;
         }

         mapConserve_loop$3_mappedHead = null;
         Object var115 = null;
         mapConserve_loop$3_unchanged = null;
         mapConserve_loop$3_pending = null;
         Object var118 = null;
         Object var119 = null;
         Object var120 = null;
         Object var121 = null;
         Object var122 = null;
         Object var123 = null;
         Object var124 = null;
         Object var125 = null;
         List mapConserve_result = (List)var371;
         Statics.releaseFence();
         mapConserve_result = null;
         if (ts1 == mapConserve_result) {
            return ts1;
         }

         SymbolTable var381 = (SymbolTable)this;
         depth = depth;
         ts = mapConserve_result;
         this = var381;
      }

      return ts;
   }

   // $FF: synthetic method
   static boolean sameWeakLubAsLub$(final GlbLubs $this, final List tps) {
      return $this.sameWeakLubAsLub(tps);
   }

   default boolean sameWeakLubAsLub(final List tps) {
      if (.MODULE$.equals(tps)) {
         return true;
      } else {
         if (tps instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)tps;
            Types.Type tp = (Types.Type)var2.head();
            List var4 = var2.next$access$1();
            if (.MODULE$.equals(var4)) {
               return tp.annotations().isEmpty();
            }
         }

         if (tps == null) {
            throw null;
         } else {
            List forall_these = tps;

            boolean var10000;
            while(true) {
               if (forall_these.isEmpty()) {
                  var10000 = true;
                  break;
               }

               if (!$anonfun$sameWeakLubAsLub$1((Types.Type)forall_these.head())) {
                  var10000 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var8 = null;
            if (var10000) {
               List forall_these = tps;

               while(true) {
                  if (forall_these.isEmpty()) {
                     var10000 = true;
                     break;
                  }

                  Types.Type var7 = (Types.Type)forall_these.head();
                  if (!$anonfun$sameWeakLubAsLub$2(this, var7)) {
                     var10000 = false;
                     break;
                  }

                  forall_these = (List)forall_these.tail();
               }

               Object var9 = null;
               if (!var10000) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   // $FF: synthetic method
   static Types.Type weakLub$(final GlbLubs $this, final List tps) {
      return $this.weakLub(tps);
   }

   default Types.Type weakLub(final List tps) {
      if (tps.isEmpty()) {
         return ((Definitions)this).definitions().NothingTpe();
      } else {
         List forall_these = tps;

         boolean var10000;
         while(true) {
            if (forall_these.isEmpty()) {
               var10000 = true;
               break;
            }

            Types.Type var8 = (Types.Type)forall_these.head();
            if (!$anonfun$weakLub$1(this, var8)) {
               var10000 = false;
               break;
            }

            forall_these = (List)forall_these.tail();
         }

         Object var9 = null;
         if (var10000) {
            return this.numericLub(tps);
         } else {
            List exists_these = tps;

            while(true) {
               if (exists_these.isEmpty()) {
                  var10000 = false;
                  break;
               }

               if ($anonfun$weakLub$2((Types.Type)exists_these.head())) {
                  var10000 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var10 = null;
            if (!var10000) {
               return this.lub(tps);
            } else {
               AnnotationCheckers var16 = (AnnotationCheckers)this;
               Object var10002;
               if (tps == .MODULE$) {
                  var10002 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)tps.head()).withoutAnnotations(), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)tps.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).withoutAnnotations(), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10002 = map_h;
               }

               Object var11 = null;
               Object var12 = null;
               Object var13 = null;
               Object var14 = null;
               return var16.annotationsLub(this.lub((List)var10002), tps);
            }
         }
      }
   }

   // $FF: synthetic method
   static Types.Type numericLub$(final GlbLubs $this, final List ts) {
      return $this.numericLub(ts);
   }

   default Types.Type numericLub(final List ts) {
      return (Types.Type)ts.reduceLeft((t1, t2) -> {
         if (((TypeComparers)this).isNumericSubType(t1, t2)) {
            return t2.dealiasWiden();
         } else {
            return ((TypeComparers)this).isNumericSubType(t2, t1) ? t1.dealiasWiden() : ((Definitions)this).definitions().IntTpe();
         }
      });
   }

   HashMap scala$reflect$internal$tpe$GlbLubs$$_lubResults();

   // $FF: synthetic method
   static HashMap lubResults$(final GlbLubs $this) {
      return $this.lubResults();
   }

   default HashMap lubResults() {
      return this.scala$reflect$internal$tpe$GlbLubs$$_lubResults();
   }

   HashMap scala$reflect$internal$tpe$GlbLubs$$_glbResults();

   // $FF: synthetic method
   static HashMap glbResults$(final GlbLubs $this) {
      return $this.glbResults();
   }

   default HashMap glbResults() {
      return this.scala$reflect$internal$tpe$GlbLubs$$_glbResults();
   }

   // $FF: synthetic method
   static Types.Type lub$(final GlbLubs $this, final List ts) {
      return $this.lub(ts);
   }

   default Types.Type lub(final List ts) {
      if (.MODULE$.equals(ts)) {
         return ((Definitions)this).definitions().NothingTpe();
      } else {
         if (ts instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)ts;
            Types.Type t = (Types.Type)var2.head();
            List var4 = var2.next$access$1();
            if (.MODULE$.equals(var4)) {
               return t;
            }
         }

         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var61 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var62 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var62;
         boolean var63 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         if (var63) {
            Statistics var64 = ((SymbolTable)this).statistics();
            Statistics.Counter incCounter_c = ((TypesStats)((SymbolTable)this).statistics()).lubCount();
            if (var64 == null) {
               throw null;
            }

            Statistics incCounter_this = var64;
            MutableSettings.SettingsOps$ var65 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var66 = MutableSettings$.MODULE$;
            MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var67 = incCounter_enabled_SettingsOps_settings;
            incCounter_enabled_SettingsOps_settings = null;
            MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var67;
            boolean var68 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            incCounter_enabled_areStatisticsEnabled$extension_$this = null;
            if (var68 && incCounter_c != null) {
               incCounter_c.value_$eq(incCounter_c.value() + 1);
            }

            Object var41 = null;
            Object var42 = null;
         }

         MutableSettings.SettingsOps$ var69 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var70 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var71 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var71;
         boolean var72 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         Tuple2 var78;
         if (var72) {
            Statistics var73 = ((SymbolTable)this).statistics();
            Statistics.TimerStack pushTimer_timers = ((TypesStats)((SymbolTable)this).statistics()).typeOpsStack();
            if (var73 == null) {
               throw null;
            }

            Statistics pushTimer_this = var73;
            MutableSettings.SettingsOps$ var74 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var75 = MutableSettings$.MODULE$;
            MutableSettings pushTimer_enabled_SettingsOps_settings = pushTimer_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var76 = pushTimer_enabled_SettingsOps_settings;
            pushTimer_enabled_SettingsOps_settings = null;
            MutableSettings pushTimer_enabled_areStatisticsEnabled$extension_$this = var76;
            boolean var77 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(pushTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            pushTimer_enabled_areStatisticsEnabled$extension_$this = null;
            var78 = var77 && pushTimer_timers != null ? pushTimer_timers.push($anonfun$lub$1(this)) : null;
            Object var46 = null;
            Object var47 = null;
         } else {
            var78 = null;
         }

         Tuple2 start = var78;

         try {
            Types.Type res = this.lub(ts, ((Types)this).lubDepth(ts));
            List var79 = res.typeParams();
            if (var79 == null) {
               throw null;
            }

            AbstractSeq size_this = var79;
            int var80 = SeqOps.size$(size_this);
            size_this = null;
            int rtps = var80;
            List var81 = ((Types.Type)ts.head()).typeParams();
            if (var81 == null) {
               throw null;
            }

            int hs = SeqOps.size$(var81);
            if (hs != rtps) {
               List forall_these = ts;

               while(true) {
                  if (forall_these.isEmpty()) {
                     var82 = true;
                     break;
                  }

                  Types.Type var37 = (Types.Type)forall_these.head();
                  if (!$anonfun$lub$2(hs, var37)) {
                     var82 = false;
                     break;
                  }

                  forall_these = (List)forall_these.tail();
               }

               Object var57 = null;
               if (var82) {
                  SymbolTable var84 = (SymbolTable)this;
                  Function0 var10001 = () -> (new StringBuilder(61)).append("Stripping type args from lub because ").append(res).append(" is not consistent with ").append(ts).toString();
                  Object logResult_result = res.typeConstructor();
                  var84.log(SymbolTable::$anonfun$logResult$1);
                  var83 = logResult_result;
                  logResult_result = null;
                  return (Types.Type)var83;
               }
            }

            var83 = res;
         } finally {
            this.lubResults().clear();
            this.glbResults().clear();
            MutableSettings.SettingsOps$ var85 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var86 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
            MutableSettings var87 = SettingsOps_settings;
            SettingsOps_settings = null;
            MutableSettings areStatisticsEnabled$extension_$this = var87;
            boolean var88 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            areStatisticsEnabled$extension_$this = null;
            if (var88) {
               Statistics var89 = ((SymbolTable)this).statistics();
               Statistics.TimerStack popTimer_timers = ((TypesStats)((SymbolTable)this).statistics()).typeOpsStack();
               if (var89 == null) {
                  throw null;
               }

               Statistics popTimer_this = var89;
               MutableSettings.SettingsOps$ var90 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var91 = MutableSettings$.MODULE$;
               MutableSettings popTimer_enabled_SettingsOps_settings = popTimer_this.scala$reflect$internal$util$Statistics$$settings;
               MutableSettings var92 = popTimer_enabled_SettingsOps_settings;
               popTimer_enabled_SettingsOps_settings = null;
               MutableSettings popTimer_enabled_areStatisticsEnabled$extension_$this = var92;
               boolean var93 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(popTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
               popTimer_enabled_areStatisticsEnabled$extension_$this = null;
               if (var93 && popTimer_timers != null) {
                  popTimer_timers.pop(start);
               }

               Object var52 = null;
               Object var53 = null;
            }

         }

         return (Types.Type)var83;
      }
   }

   // $FF: synthetic method
   static Types.Type lub$(final GlbLubs $this, final List ts0, final int depth) {
      return $this.lub(ts0, depth);
   }

   default Types.Type lub(final List ts0, final int depth) {
      if (this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
         Object println_x = (new StringBuilder(17)).append(((Types)this).indent()).append("lub of ").append(ts0).append(" at depth ").append(new Depth(depth)).toString();
         scala.Console..MODULE$.println(println_x);
         println_x = null;
         ((Types)this).indent_$eq((new StringBuilder(2)).append(((Types)this).indent()).append("  ").toString());
         SymbolTable var10000 = (SymbolTable)this;
         boolean assert_assertion = ((Types)this).indent().length() <= 100;
         SymbolTable assert_this = var10000;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("LUB is highly indented");
         }
      }

      MutableSettings.SettingsOps$ var21 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var22 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var23 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var23;
      boolean var24 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      if (var24) {
         Statistics var25 = ((SymbolTable)this).statistics();
         Statistics.Counter incCounter_c = ((TypesStats)((SymbolTable)this).statistics()).nestedLubCount();
         if (var25 == null) {
            throw null;
         }

         Statistics incCounter_this = var25;
         MutableSettings.SettingsOps$ var26 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var27 = MutableSettings$.MODULE$;
         MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var28 = incCounter_enabled_SettingsOps_settings;
         incCounter_enabled_SettingsOps_settings = null;
         MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var28;
         boolean var29 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         incCounter_enabled_areStatisticsEnabled$extension_$this = null;
         if (var29 && incCounter_c != null) {
            incCounter_c.value_$eq(incCounter_c.value() + 1);
         }

         Object var15 = null;
         Object var16 = null;
      }

      Types.Type res = this.lub0$1(ts0, depth);
      if (this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
         ((Types)this).indent_$eq(scala.collection.StringOps..MODULE$.stripSuffix$extension(((Types)this).indent(), "  "));
         Object println_x = (new StringBuilder(11)).append(((Types)this).indent()).append("lub of ").append(ts0).append(" is ").append(res).toString();
         scala.Console..MODULE$.println(println_x);
      }

      return res;
   }

   Throwable GlbFailure();

   int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth();

   void scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(final int x$1);

   int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit();

   // $FF: synthetic method
   static Types.Type glb$(final GlbLubs $this, final List ts) {
      return $this.glb(ts);
   }

   default Types.Type glb(final List ts) {
      List var2 = this.elimSuper(ts);
      if (var2 != null) {
         List var10000 = scala.package..MODULE$.List();
         if (var10000 == null) {
            throw null;
         }

         List unapplySeq_this = var10000;
         SeqOps var58 = SeqFactory.unapplySeq$(unapplySeq_this, var2);
         Object var53 = null;
         SeqOps var3 = var58;
         SeqFactory.UnapplySeqWrapper var59 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var3);
         var59 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var59 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 0;
         if (var3.lengthCompare(lengthCompare$extension_len) == 0) {
            return ((Definitions)this).definitions().AnyTpe();
         }
      }

      if (var2 != null) {
         List var62 = scala.package..MODULE$.List();
         if (var62 == null) {
            throw null;
         }

         List unapplySeq_this = var62;
         SeqOps var63 = SeqFactory.unapplySeq$(unapplySeq_this, var2);
         Object var54 = null;
         SeqOps var4 = var63;
         SeqFactory.UnapplySeqWrapper var64 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var89 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var4);
         var64 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var64 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 1;
         if (var4.lengthCompare(lengthCompare$extension_len) == 0) {
            var64 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var64 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int apply$extension_i = 0;
            return (Types.Type)var4.apply(apply$extension_i);
         }
      }

      MutableSettings.SettingsOps$ var67 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var68 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var69 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var69;
      boolean var70 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      if (var70) {
         Statistics var71 = ((SymbolTable)this).statistics();
         Statistics.Counter incCounter_c = ((TypesStats)((SymbolTable)this).statistics()).lubCount();
         if (var71 == null) {
            throw null;
         }

         Statistics incCounter_this = var71;
         MutableSettings.SettingsOps$ var72 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var73 = MutableSettings$.MODULE$;
         MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var74 = incCounter_enabled_SettingsOps_settings;
         incCounter_enabled_SettingsOps_settings = null;
         MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var74;
         boolean var75 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         incCounter_enabled_areStatisticsEnabled$extension_$this = null;
         if (var75 && incCounter_c != null) {
            incCounter_c.value_$eq(incCounter_c.value() + 1);
         }

         Object var39 = null;
         Object var40 = null;
      }

      MutableSettings.SettingsOps$ var76 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var77 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var78 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var78;
      boolean var79 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      Tuple2 var85;
      if (var79) {
         Statistics var80 = ((SymbolTable)this).statistics();
         Statistics.TimerStack pushTimer_timers = ((TypesStats)((SymbolTable)this).statistics()).typeOpsStack();
         if (var80 == null) {
            throw null;
         }

         Statistics pushTimer_this = var80;
         MutableSettings.SettingsOps$ var81 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var82 = MutableSettings$.MODULE$;
         MutableSettings pushTimer_enabled_SettingsOps_settings = pushTimer_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var83 = pushTimer_enabled_SettingsOps_settings;
         pushTimer_enabled_SettingsOps_settings = null;
         MutableSettings pushTimer_enabled_areStatisticsEnabled$extension_$this = var83;
         boolean var84 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(pushTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         pushTimer_enabled_areStatisticsEnabled$extension_$this = null;
         var85 = var84 && pushTimer_timers != null ? pushTimer_timers.push($anonfun$glb$1(this)) : null;
         Object var44 = null;
         Object var45 = null;
      } else {
         var85 = null;
      }

      Tuple2 start = var85;

      try {
         var86 = this.glbNorm(var2, ((Types)this).lubDepth(var2));
      } finally {
         this.lubResults().clear();
         this.glbResults().clear();
         MutableSettings.SettingsOps$ var90 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var91 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var92 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings areStatisticsEnabled$extension_$this = var92;
         boolean var93 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         areStatisticsEnabled$extension_$this = null;
         if (var93) {
            Statistics var94 = ((SymbolTable)this).statistics();
            Statistics.TimerStack popTimer_timers = ((TypesStats)((SymbolTable)this).statistics()).typeOpsStack();
            if (var94 == null) {
               throw null;
            }

            Statistics popTimer_this = var94;
            MutableSettings.SettingsOps$ var95 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var96 = MutableSettings$.MODULE$;
            MutableSettings popTimer_enabled_SettingsOps_settings = popTimer_this.scala$reflect$internal$util$Statistics$$settings;
            MutableSettings var97 = popTimer_enabled_SettingsOps_settings;
            popTimer_enabled_SettingsOps_settings = null;
            MutableSettings popTimer_enabled_areStatisticsEnabled$extension_$this = var97;
            boolean var98 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(popTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
            popTimer_enabled_areStatisticsEnabled$extension_$this = null;
            if (var98 && popTimer_timers != null) {
               popTimer_timers.pop(start);
            }

            Object var49 = null;
            Object var50 = null;
         }

      }

      return var86;
   }

   // $FF: synthetic method
   static Types.Type glb$(final GlbLubs $this, final List ts, final int depth) {
      return $this.glb(ts, depth);
   }

   default Types.Type glb(final List ts, final int depth) {
      List var3 = this.elimSuper(ts);
      if (var3 != null) {
         List var10000 = scala.package..MODULE$.List();
         if (var10000 == null) {
            throw null;
         }

         List unapplySeq_this = var10000;
         SeqOps var13 = SeqFactory.unapplySeq$(unapplySeq_this, var3);
         Object var11 = null;
         SeqOps var4 = var13;
         SeqFactory.UnapplySeqWrapper var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var4);
         var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var14 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 0;
         if (var4.lengthCompare(lengthCompare$extension_len) == 0) {
            return ((Definitions)this).definitions().AnyTpe();
         }
      }

      if (var3 != null) {
         List var17 = scala.package..MODULE$.List();
         if (var17 == null) {
            throw null;
         }

         List unapplySeq_this = var17;
         SeqOps var18 = SeqFactory.unapplySeq$(unapplySeq_this, var3);
         Object var12 = null;
         SeqOps var5 = var18;
         SeqFactory.UnapplySeqWrapper var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var5);
         var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 1;
         if (var5.lengthCompare(lengthCompare$extension_len) == 0) {
            var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var19 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int apply$extension_i = 0;
            return (Types.Type)var5.apply(apply$extension_i);
         }
      }

      return this.glbNorm(var3, depth);
   }

   // $FF: synthetic method
   static Types.Type glbNorm$(final GlbLubs $this, final List ts0, final int depth) {
      return $this.glbNorm(ts0, depth);
   }

   default Types.Type glbNorm(final List ts0, final int depth) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var15 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var16 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var16;
      boolean var17 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      if (var17) {
         Statistics var18 = ((SymbolTable)this).statistics();
         Statistics.Counter incCounter_c = ((TypesStats)((SymbolTable)this).statistics()).nestedLubCount();
         if (var18 == null) {
            throw null;
         }

         Statistics incCounter_this = var18;
         MutableSettings.SettingsOps$ var19 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var20 = MutableSettings$.MODULE$;
         MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var21 = incCounter_enabled_SettingsOps_settings;
         incCounter_enabled_SettingsOps_settings = null;
         MutableSettings incCounter_enabled_areStatisticsEnabled$extension_$this = var21;
         boolean var22 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         incCounter_enabled_areStatisticsEnabled$extension_$this = null;
         if (var22 && incCounter_c != null) {
            incCounter_c.value_$eq(incCounter_c.value() + 1);
         }

         Object var10 = null;
         Object var11 = null;
      }

      return this.glb0$1(ts0, depth);
   }

   private Types.PolyType polyTypeMatch(final Types.PolyType ptHead, final List ptRest, final int depth, final Function2 infoBoundTop, final Function1 resultTypeBottom) {
      List tparamsHead = ptHead.typeParams();
      if (ptRest == null) {
         throw null;
      } else {
         Object var10000;
         if (ptRest == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Types.Type var29 = (Types.Type)ptRest.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, tparamsHead, ptHead, ptRest, var29), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)ptRest.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var29 = (Types.Type)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, tparamsHead, ptHead, ptRest, var29), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var34 = null;
         Object var35 = null;
         Object var36 = null;
         Object var37 = null;
         List $colon$colon_this = (List)var10000;
         scala.collection.immutable..colon.colon var56 = new scala.collection.immutable..colon.colon(ptHead, $colon$colon_this);
         $colon$colon_this = null;
         List ntps = var56;
         List var57 = ((scala.collection.immutable..colon.colon)ntps).next();
         if (var57 == null) {
            throw null;
         } else {
            List map_this = var57;
            if (map_this == .MODULE$) {
               var57 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)map_this.head(), tparamsHead), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)map_rest.head(), tparamsHead), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var57 = map_h;
            }

            Object var38 = null;
            Object var39 = null;
            Object var40 = null;
            Object var41 = null;
            Object var42 = null;
            List boundsTts = (List)var57.transpose(scala..less.colon.less..MODULE$.refl());
            Collections var59 = (Collections)this;
            ListBuffer map2_lb = new ListBuffer();
            List map2_ys1 = tparamsHead;

            for(List map2_ys2 = boundsTts; !map2_ys1.isEmpty() && !map2_ys2.isEmpty(); map2_ys2 = (List)map2_ys2.tail()) {
               var59 = (Collections)map2_ys1.head();
               List var31 = (List)map2_ys2.head();
               Symbols.Symbol var30 = (Symbols.Symbol)var59;
               Symbols.Symbol var61 = var30.cloneSymbol();
               Types.Type var33 = var30.info();
               if (var31 == null) {
                  throw null;
               }

               var61 = var61.setInfo((Types.Type)infoBoundTop.apply(new scala.collection.immutable..colon.colon(var33, var31), new Depth(depth)));
               var33 = null;
               Object map2_$plus$eq_elem = var61;
               map2_lb.addOne(map2_$plus$eq_elem);
               map2_$plus$eq_elem = null;
               map2_ys1 = (List)map2_ys1.tail();
            }

            List var63 = map2_lb.toList();
            Object var43 = null;
            Object var44 = null;
            Object var45 = null;
            Object var47 = null;
            List tparams1 = var63;
            Object var64;
            if (ntps == .MODULE$) {
               var64 = .MODULE$;
            } else {
               Types.PolyType var32 = (Types.PolyType)ntps.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(tparams1, var32), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = ((scala.collection.immutable..colon.colon)ntps).next(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var32 = (Types.PolyType)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(tparams1, var32), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var64 = map_h;
            }

            Object var48 = null;
            Object var49 = null;
            Object var50 = null;
            Object var51 = null;
            List matchingInstTypes = (List)var64;
            return (SymbolTable)this.new PolyType(tparams1, (Types.Type)resultTypeBottom.apply(matchingInstTypes));
         }
      }
   }

   private List matchingRestypes(final List tps, final List pts) {
      if (tps == null) {
         throw null;
      } else if (tps == .MODULE$) {
         return .MODULE$;
      } else {
         Types.Type var7 = (Types.Type)tps.head();
         scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$matchingRestypes$1(this, pts, tps, var7), .MODULE$);
         scala.collection.immutable..colon.colon map_t = map_h;

         for(List map_rest = (List)tps.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
            var7 = (Types.Type)map_rest.head();
            scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$matchingRestypes$1(this, pts, tps, var7), .MODULE$);
            map_t.next_$eq(map_nx);
            map_t = map_nx;
         }

         Statics.releaseFence();
         return map_h;
      }
   }

   private String str$1(final Types.Type tp) {
      Types.NoType$ var2 = ((Types)this).NoType();
      if (tp == null) {
         if (var2 == null) {
            return "";
         }
      } else if (tp.equals(var2)) {
         return "";
      }

      String s = String.valueOf(tp).replaceAll("[\\w.]+\\.(\\w+)", "$1");
      if (s.length() < 60) {
         return s;
      } else {
         return (new StringBuilder(3)).append(scala.collection.StringOps..MODULE$.take$extension(s, 57)).append("...").toString();
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$printLubMatrix$1(final Tuple2 x, final Tuple2 y) {
      return ((Types.Type)x._1()).typeSymbol().isLess(((Types.Type)y._1()).typeSymbol());
   }

   // $FF: synthetic method
   static int $anonfun$printLubMatrix$2(final Tuple2 x$1) {
      SeqOps var10000 = (SeqOps)x$1._2();
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.length();
      }
   }

   // $FF: synthetic method
   static List $anonfun$printLubMatrix$3(final GlbLubs $this, final int maxSeqLength$1, final Tuple2 x$2) {
      return (List)((StrictOptimizedSeqOps)x$2._2()).padTo(maxSeqLength$1, ((Types)$this).NoType());
   }

   // $FF: synthetic method
   static TableDef.Column $anonfun$printLubMatrix$4(final GlbLubs $this, final Tuple2 x0$1, final int x1$1) {
      Integer var5 = x1$1;
      Tuple2 var3 = new Tuple2(x0$1, var5);
      if (x0$1 != null) {
         Types.Type k = (Types.Type)x0$1._1();
         return new TableDef.Column($this.str$1(k), (xs) -> {
            if (xs == null) {
               throw null;
            } else {
               return $this.str$1((Types.Type)LinearSeqOps.apply$(xs, x1$1));
            }
         }, true);
      } else {
         throw new MatchError(var3);
      }
   }

   private static boolean isHotForT$1(final Symbols.Symbol tyPar, final Types.Type x) {
      return tyPar == x.typeSymbol();
   }

   // $FF: synthetic method
   static boolean $anonfun$lubList_x$2(final Symbols.Symbol x$4, final Types.Type x$5) {
      return isHotForT$1(x$4, x$5);
   }

   // $FF: synthetic method
   static boolean $anonfun$lubList_x$1(final List xs$1, final Types.Type x$3) {
      return x$3.typeParams().corresponds(xs$1, (x$4, x$5) -> BoxesRunTime.boxToBoolean($anonfun$lubList_x$2(x$4, x$5)));
   }

   private static boolean isHotForTs$1(final List xs, final List ts$1) {
      if (ts$1 == null) {
         throw null;
      } else {
         for(List exists_these = ts$1; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            Types.Type var3 = (Types.Type)exists_these.head();
            if ($anonfun$lubList_x$1(xs, var3)) {
               return true;
            }
         }

         return false;
      }
   }

   private Types.Type elimHigherOrderTypeParam$1(final Types.Type tp, final List ts$1) {
      if (tp instanceof Types.TypeRef) {
         List args = ((Types.TypeRef)tp).args();
         if (args.nonEmpty() && isHotForTs$1(args, ts$1)) {
            SymbolTable var10000 = (SymbolTable)this;
            Function0 var10001 = () -> (new StringBuilder(35)).append("Retracting dummies from ").append(tp).append(" in lublist").toString();
            Object logResult_result = tp.typeConstructor();
            var10000.log(SymbolTable::$anonfun$logResult$1);
            return (Types.Type)logResult_result;
         }
      }

      return tp;
   }

   // $FF: synthetic method
   static BaseTypeSeqs.BaseTypeSeq $anonfun$lubList_x$4(final Types.Type x$6) {
      return x$6.baseTypeSeq();
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$lubList_x$5(final BaseTypeSeqs.BaseTypeSeq[] baseTypeSeqs$1, final int[] ices$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Types.Type ty = (Types.Type)x0$1._1();
         int ix = x0$1._2$mcI$sp();
         Predef.ArrowAssoc var10000 = scala.Predef.ArrowAssoc..MODULE$;
         List var9 = baseTypeSeqs$1[ix].toList();
         int drop_n = ices$1[ix];
         if (var9 == null) {
            throw null;
         } else {
            List drop_this = var9;
            LinearSeq var10 = StrictOptimizedLinearSeqOps.drop$(drop_this, drop_n);
            Object var8 = null;
            Object $minus$greater$extension_y = var10;
            return new Tuple2(ty, $minus$greater$extension_y);
         }
      } else {
         throw new MatchError((Object)null);
      }
   }

   private void printLubMatrixAux$1(final int depth, final List ts$1, final BaseTypeSeqs.BaseTypeSeq[] baseTypeSeqs$1, final int[] ices$1) {
      if (ts$1 == null) {
         throw null;
      } else {
         List var10000 = (List)StrictOptimizedIterableOps.zipWithIndex$(ts$1);
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            if (map_this == .MODULE$) {
               var10000 = .MODULE$;
            } else {
               Tuple2 var11 = (Tuple2)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$lubList_x$5(baseTypeSeqs$1, ices$1, var11), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var11 = (Tuple2)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$lubList_x$5(baseTypeSeqs$1, ices$1, var11), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10000 = map_h;
            }

            Object var12 = null;
            Object var13 = null;
            Object var14 = null;
            Object var15 = null;
            Object var16 = null;
            Map btsMap = var10000.toMap(scala..less.colon.less..MODULE$.refl());
            this.printLubMatrix(btsMap, depth);
         }
      }
   }

   private static Types.Type headOf$1(final int ix, final BaseTypeSeqs.BaseTypeSeq[] baseTypeSeqs$1, final int[] ices$1) {
      return baseTypeSeqs$1[ix].rawElem(ices$1[ix]);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lubList_x$6(final GlbLubs $this, final List ts$1, final Types.Type tp) {
      return $this.elimHigherOrderTypeParam$1(tp, ts$1);
   }

   // $FF: synthetic method
   static String $anonfun$lubList_x$8(final Types.Type x$7) {
      return (new StringBuilder(8)).append("        ").append(x$7).toString();
   }

   // $FF: synthetic method
   static String $anonfun$lubList_x$7(final int[] ices$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError((Object)null);
      } else {
         BaseTypeSeqs.BaseTypeSeq tps = (BaseTypeSeqs.BaseTypeSeq)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         List var10000 = tps.toList();
         int drop_n = ices$1[idx];
         if (var10000 == null) {
            throw null;
         } else {
            var10000 = (List)StrictOptimizedLinearSeqOps.drop$(var10000, drop_n);
            if (var10000 == null) {
               throw null;
            } else {
               List map_this = var10000;
               Object var16;
               if (map_this == .MODULE$) {
                  var16 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$lubList_x$8((Types.Type)map_this.head()), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$lubList_x$8((Types.Type)map_rest.head()), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var16 = map_h;
               }

               Object var10 = null;
               Object var11 = null;
               Object var12 = null;
               Object var13 = null;
               Object var14 = null;
               return IterableOnceOps.mkString$((IterableOnceOps)var16, (new StringBuilder(6)).append("   (").append(idx).append(")\n").toString(), "\n", "\n");
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$spanningTypes$1(final Types.Type first$1, final Types.Type t) {
      return !first$1.typeSymbol().isSubClass(t.typeSymbol());
   }

   // $FF: synthetic method
   static boolean $anonfun$isWildCardOrNonGroundTypeVarCollector$1(final Types.Type x0$1) {
      if (x0$1 instanceof Types.TypeVar) {
         return !((Types.TypeVar)x0$1).isGround();
      } else {
         return x0$1.isWildcard();
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$maxTypes$1(final Function2 po$1, final Types.Type t$1, final Types.Type x$8) {
      return BoxesRunTime.unboxToBoolean(po$1.apply(x$8, t$1));
   }

   // $FF: synthetic method
   static boolean $anonfun$maxTypes$2(final Function2 po$1, final Types.Type t$1, final Types.Type x$9) {
      return BoxesRunTime.unboxToBoolean(po$1.apply(t$1, x$9));
   }

   private static List stacked$1(final List ts, final Function2 po$1) {
      if (!(ts instanceof scala.collection.immutable..colon.colon)) {
         if (.MODULE$.equals(ts)) {
            return .MODULE$;
         } else {
            throw new MatchError(ts);
         }
      } else {
         scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)ts;
         Types.Type t = (Types.Type)var2.head();
         List ts1 = var2.next$access$1();
         if (ts1 == null) {
            throw null;
         } else {
            boolean filterNot_filterCommon_isFlipped = true;
            List filterNot_filterCommon_noneIn$1_l = ts1;

            Object var10000;
            while(true) {
               if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                  var10000 = .MODULE$;
                  break;
               }

               Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
               List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
               Types.Type var22 = (Types.Type)filterNot_filterCommon_noneIn$1_h;
               if (BoxesRunTime.unboxToBoolean(po$1.apply(var22, t)) != filterNot_filterCommon_isFlipped) {
                  List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10000 = filterNot_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var22 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x;
                     if (BoxesRunTime.unboxToBoolean(po$1.apply(var22, t)) == filterNot_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           var22 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if (BoxesRunTime.unboxToBoolean(po$1.apply(var22, t)) != filterNot_filterCommon_isFlipped) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var10000 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var32 = null;
                        Object var35 = null;
                        Object var38 = null;
                        Object var41 = null;
                        Object var44 = null;
                        Object var47 = null;
                        Object var50 = null;
                        Object var53 = null;
                        break;
                     }

                     filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var28 = null;
                  Object var30 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  Object var48 = null;
                  Object var51 = null;
                  Object var54 = null;
                  break;
               }

               filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
            }

            Object var25 = null;
            Object var26 = null;
            Object var27 = null;
            Object var29 = null;
            Object var31 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            Object var49 = null;
            Object var52 = null;
            Object var55 = null;
            List filterNot_filterCommon_result = (List)var10000;
            Statics.releaseFence();
            var10000 = filterNot_filterCommon_result;
            filterNot_filterCommon_result = null;
            List ts2 = stacked$1((List)var10000, po$1);
            if (ts2 == null) {
               throw null;
            } else {
               List exists_these = ts2;

               while(true) {
                  if (exists_these.isEmpty()) {
                     var60 = false;
                     break;
                  }

                  Types.Type var23 = (Types.Type)exists_these.head();
                  if (BoxesRunTime.unboxToBoolean(po$1.apply(t, var23))) {
                     var60 = true;
                     break;
                  }

                  exists_these = (List)exists_these.tail();
               }

               Object var56 = null;
               return (List)(var60 ? ts2 : new scala.collection.immutable..colon.colon(t, ts2));
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$maxTypes$3(final Function2 po$1, final Types.Type h$1, final Types.Type x$10) {
      return BoxesRunTime.unboxToBoolean(po$1.apply(x$10, h$1));
   }

   // $FF: synthetic method
   static boolean $anonfun$maxTypes$4(final Function2 po$1, final Types.Type h$2, final Types.Type x$11) {
      return BoxesRunTime.unboxToBoolean(po$1.apply(h$2, x$11));
   }

   private List sieve$1(final List res, final List remaining, final Function2 po$1) {
      while(remaining instanceof scala.collection.immutable..colon.colon) {
         scala.collection.immutable..colon.colon var4 = (scala.collection.immutable..colon.colon)remaining;
         Types.Type h = (Types.Type)var4.head();
         List tail = var4.next$access$1();
         if (res == null) {
            throw null;
         }

         List exists_these = res;

         boolean var10000;
         while(true) {
            if (exists_these.isEmpty()) {
               var10000 = false;
               break;
            }

            Types.Type var9 = (Types.Type)exists_these.head();
            if (BoxesRunTime.unboxToBoolean(po$1.apply(h, var9))) {
               var10000 = true;
               break;
            }

            exists_these = (List)exists_these.tail();
         }

         exists_these = null;
         List res1 = (List)(var10000 ? res : new scala.collection.immutable..colon.colon(h, res));
         SymbolTable var11 = (SymbolTable)this;
         remaining = tail;
         res = res1;
         this = var11;
      }

      return res;
   }

   private List loop$1(final List survivors, final List toCull, final Function2 po$1) {
      while(survivors instanceof scala.collection.immutable..colon.colon) {
         scala.collection.immutable..colon.colon var4 = (scala.collection.immutable..colon.colon)survivors;
         Types.Type h = (Types.Type)var4.head();
         List rest = var4.next$access$1();
         SymbolTable var10000 = (SymbolTable)this;
         if (rest == null) {
            throw null;
         }

         boolean filterNot_filterCommon_isFlipped = true;
         List filterNot_filterCommon_noneIn$1_l = rest;

         Object var10001;
         while(true) {
            if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
               var10001 = .MODULE$;
               break;
            }

            Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
            List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
            Types.Type var31 = (Types.Type)filterNot_filterCommon_noneIn$1_h;
            if (BoxesRunTime.unboxToBoolean(po$1.apply(var31, h)) != filterNot_filterCommon_isFlipped) {
               List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                     var10001 = filterNot_filterCommon_noneIn$1_l;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                  var31 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x;
                  if (BoxesRunTime.unboxToBoolean(po$1.apply(var31, h)) == filterNot_filterCommon_isFlipped) {
                     scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                     scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                     for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                        filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                        filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                     }

                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                     while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                        Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                        var31 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                        if (BoxesRunTime.unboxToBoolean(po$1.apply(var31, h)) != filterNot_filterCommon_isFlipped) {
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        } else {
                           while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                              scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                           }

                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        }
                     }

                     if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                        filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                     }

                     var10001 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     Object var50 = null;
                     Object var53 = null;
                     Object var56 = null;
                     Object var59 = null;
                     Object var62 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
               }

               Object var37 = null;
               Object var39 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               Object var60 = null;
               Object var63 = null;
               break;
            }

            filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
         }

         Object var34 = null;
         Object var35 = null;
         Object var36 = null;
         Object var38 = null;
         Object var40 = null;
         Object var43 = null;
         Object var46 = null;
         Object var49 = null;
         Object var52 = null;
         Object var55 = null;
         Object var58 = null;
         Object var61 = null;
         Object var64 = null;
         List filterNot_filterCommon_result = (List)var10001;
         Statics.releaseFence();
         var10001 = filterNot_filterCommon_result;
         filterNot_filterCommon_result = null;
         if (toCull == null) {
            throw null;
         }

         toCull = new scala.collection.immutable..colon.colon(h, toCull);
         survivors = (List)var10001;
         this = var10000;
      }

      if (toCull instanceof scala.collection.immutable..colon.colon) {
         List var7 = ((scala.collection.immutable..colon.colon)toCull).next$access$1();
         if (.MODULE$.equals(var7)) {
            return toCull;
         }
      }

      Object sieve$1_remaining = toCull;
      Object sieve$1_res = .MODULE$;

      SymbolTable var69;
      for(GlbLubs sieve$1_this = (GlbLubs)this; sieve$1_remaining instanceof scala.collection.immutable..colon.colon; sieve$1_this = var69) {
         scala.collection.immutable..colon.colon var26 = (scala.collection.immutable..colon.colon)sieve$1_remaining;
         Types.Type sieve$1_h = (Types.Type)var26.head();
         List sieve$1_tail = var26.next$access$1();
         List sieve$1_exists_these = (List)sieve$1_res;

         while(true) {
            if (sieve$1_exists_these.isEmpty()) {
               var68 = false;
               break;
            }

            Types.Type var32 = (Types.Type)sieve$1_exists_these.head();
            if (BoxesRunTime.unboxToBoolean(po$1.apply(sieve$1_h, var32))) {
               var68 = true;
               break;
            }

            sieve$1_exists_these = (List)sieve$1_exists_these.tail();
         }

         sieve$1_exists_these = null;
         List sieve$1_res1 = (List)(var68 ? sieve$1_res : new scala.collection.immutable..colon.colon(sieve$1_h, (List)sieve$1_res));
         var69 = (SymbolTable)sieve$1_this;
         sieve$1_remaining = sieve$1_tail;
         sieve$1_res = sieve$1_res1;
      }

      return (List)sieve$1_res;
   }

   // $FF: synthetic method
   static boolean $anonfun$maxTypes$5(final GlbLubs $this, final Types.Type x$12) {
      return ((Option)$this.scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector().collect(x$12)).isDefined();
   }

   // $FF: synthetic method
   static boolean $anonfun$elimSuper$1(final Types.Type t1, final Types.Type t2) {
      return t2.$less$colon$less(t1);
   }

   // $FF: synthetic method
   static boolean $anonfun$elimSub$1(final GlbLubs $this, final int depth$1, final Types.Type x$14, final Types.Type x$15) {
      return ((TypeComparers)$this).isSubType(x$14, x$15, Depth$.MODULE$.decr$extension(depth$1, 1));
   }

   // $FF: synthetic method
   static Types.Type $anonfun$elimSub$2(final GlbLubs $this, final Types.Type t) {
      return ((Types)$this).elimAnonymousClass(t.dealiasWiden());
   }

   // $FF: synthetic method
   static boolean $anonfun$sameWeakLubAsLub$1(final Types.Type x$16) {
      return x$16.annotations().isEmpty();
   }

   // $FF: synthetic method
   static boolean $anonfun$sameWeakLubAsLub$2(final GlbLubs $this, final Types.Type tp) {
      return ((Definitions)$this).definitions().isNumericValueType(tp);
   }

   // $FF: synthetic method
   static boolean $anonfun$weakLub$1(final GlbLubs $this, final Types.Type tp) {
      return ((Definitions)$this).definitions().isNumericValueType(tp);
   }

   // $FF: synthetic method
   static boolean $anonfun$weakLub$2(final Types.Type x$17) {
      return !x$17.annotations().isEmpty();
   }

   // $FF: synthetic method
   static Types.Type $anonfun$weakLub$3(final Types.Type x$18) {
      return x$18.withoutAnnotations();
   }

   // $FF: synthetic method
   static Statistics.StackableTimer $anonfun$lub$1(final GlbLubs $this) {
      return ((TypesStats)((SymbolTable)$this).statistics()).lubNanos();
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$2(final int hs$1, final Types.Type x$19) {
      List var10000 = x$19.typeParams();
      if (var10000 == null) {
         throw null;
      } else {
         return SeqOps.size$(var10000) == hs$1;
      }
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$4(final GlbLubs $this, final List ts, final int depth) {
      return $this.glb(ts, depth);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$5(final GlbLubs $this, final int depth$2, final List ts0) {
      return $this.lub0$1(ts0, depth$2);
   }

   private Types.Type lub0$1(final List ts0, final int depth$2) {
      boolean var3 = false;
      scala.collection.immutable..colon.colon var4 = null;
      List var5 = this.elimSub(ts0, depth$2);
      if (var5 != null) {
         List var10000 = scala.package..MODULE$.List();
         if (var10000 == null) {
            throw null;
         }

         List unapplySeq_this = var10000;
         SeqOps var76 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
         Object var52 = null;
         SeqOps var6 = var76;
         SeqFactory.UnapplySeqWrapper var77 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var6);
         var77 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var77 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 0;
         if (var6.lengthCompare(lengthCompare$extension_len) == 0) {
            return ((Definitions)this).definitions().NothingTpe();
         }
      }

      if (var5 != null) {
         List var80 = scala.package..MODULE$.List();
         if (var80 == null) {
            throw null;
         }

         List unapplySeq_this = var80;
         SeqOps var81 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
         Object var53 = null;
         SeqOps var7 = var81;
         SeqFactory.UnapplySeqWrapper var82 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var97 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var7);
         var82 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var82 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 1;
         if (var7.lengthCompare(lengthCompare$extension_len) == 0) {
            var82 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var82 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int apply$extension_i = 0;
            return (Types.Type)var7.apply(apply$extension_i);
         }
      }

      if (var5 instanceof scala.collection.immutable..colon.colon) {
         var3 = true;
         var4 = (scala.collection.immutable..colon.colon)var5;
         Types.Type pt = (Types.Type)var4.head();
         List rest = var4.next$access$1();
         if (pt instanceof Types.PolyType) {
            Types.PolyType var10 = (Types.PolyType)pt;
            List polyTypeMatch_tparamsHead = var10.typeParams();
            if (rest == null) {
               throw null;
            }

            Object var85;
            if (rest == .MODULE$) {
               var85 = .MODULE$;
            } else {
               Types.Type var45 = (Types.Type)rest.head();
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, polyTypeMatch_tparamsHead, var10, rest, var45), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = (List)rest.tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  var45 = (Types.Type)polyTypeMatch_map_rest.head();
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, polyTypeMatch_tparamsHead, var10, rest, var45), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var85 = polyTypeMatch_map_h;
            }

            Object var54 = null;
            Object var55 = null;
            Object var56 = null;
            Object var57 = null;
            List polyTypeMatch_$colon$colon_this = (List)var85;
            scala.collection.immutable..colon.colon var86 = new scala.collection.immutable..colon.colon(var10, polyTypeMatch_$colon$colon_this);
            polyTypeMatch_$colon$colon_this = null;
            List polyTypeMatch_ntps = var86;
            List var87 = ((scala.collection.immutable..colon.colon)polyTypeMatch_ntps).tail();
            if (var87 == null) {
               throw null;
            }

            List polyTypeMatch_map_this = var87;
            if (polyTypeMatch_map_this == .MODULE$) {
               var87 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)polyTypeMatch_map_this.head(), polyTypeMatch_tparamsHead), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = (List)polyTypeMatch_map_this.tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)polyTypeMatch_map_rest.head(), polyTypeMatch_tparamsHead), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var87 = polyTypeMatch_map_h;
            }

            Object var58 = null;
            Object var59 = null;
            Object var60 = null;
            Object var61 = null;
            Object var62 = null;
            List polyTypeMatch_boundsTts = (List)var87.transpose(scala..less.colon.less..MODULE$.refl());
            Collections var89 = (Collections)this;
            ListBuffer polyTypeMatch_map2_lb = new ListBuffer();
            List polyTypeMatch_map2_ys1 = polyTypeMatch_tparamsHead;

            for(List polyTypeMatch_map2_ys2 = polyTypeMatch_boundsTts; !polyTypeMatch_map2_ys1.isEmpty() && !polyTypeMatch_map2_ys2.isEmpty(); polyTypeMatch_map2_ys2 = (List)polyTypeMatch_map2_ys2.tail()) {
               var89 = (Collections)polyTypeMatch_map2_ys1.head();
               List var47 = (List)polyTypeMatch_map2_ys2.head();
               Symbols.Symbol var46 = (Symbols.Symbol)var89;
               Symbols.Symbol var91 = var46.cloneSymbol();
               Types.Type var49 = var46.info();
               if (var47 == null) {
                  throw null;
               }

               scala.collection.immutable..colon.colon var98 = new scala.collection.immutable..colon.colon(var49, var47);
               Depth var51 = new Depth(depth$2);
               scala.collection.immutable..colon.colon var50 = var98;
               var91 = var91.setInfo($anonfun$lub$4$adapted(this, var50, var51));
               var49 = null;
               Object polyTypeMatch_map2_$plus$eq_elem = var91;
               polyTypeMatch_map2_lb.addOne(polyTypeMatch_map2_$plus$eq_elem);
               polyTypeMatch_map2_$plus$eq_elem = null;
               polyTypeMatch_map2_ys1 = (List)polyTypeMatch_map2_ys1.tail();
            }

            List var93 = polyTypeMatch_map2_lb.toList();
            Object var63 = null;
            Object var64 = null;
            Object var65 = null;
            Object var67 = null;
            List polyTypeMatch_tparams1 = var93;
            Object var94;
            if (polyTypeMatch_ntps == .MODULE$) {
               var94 = .MODULE$;
            } else {
               Types.PolyType var48 = (Types.PolyType)polyTypeMatch_ntps.head();
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(polyTypeMatch_tparams1, var48), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = ((scala.collection.immutable..colon.colon)polyTypeMatch_ntps).tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  var48 = (Types.PolyType)polyTypeMatch_map_rest.head();
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(polyTypeMatch_tparams1, var48), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var94 = polyTypeMatch_map_h;
            }

            Object var68 = null;
            Object var69 = null;
            Object var70 = null;
            Object var71 = null;
            List polyTypeMatch_matchingInstTypes = (List)var94;
            SymbolTable var10002 = (SymbolTable)this;
            List var44 = polyTypeMatch_matchingInstTypes;
            return var10002.new PolyType(polyTypeMatch_tparams1, $anonfun$lub$5(this, depth$2, var44));
         }
      }

      if (var3) {
         Types.Type mt = (Types.Type)var4.head();
         if (mt instanceof Types.MethodType) {
            Types.MethodType var12 = (Types.MethodType)mt;
            List params = var12.params();
            return (SymbolTable)this.new MethodType(params, this.lub0$1(this.matchingRestypes(var4, var12.paramTypes()), depth$2));
         }
      }

      if (var3 && (Types.Type)var4.head() instanceof Types.NullaryMethodType) {
         return (SymbolTable)this.new NullaryMethodType(this.lub0$1(this.matchingRestypes(var4, .MODULE$), depth$2));
      } else if (var3 && (Types.Type)var4.head() instanceof Types.TypeBounds) {
         return ((Types)this).TypeBounds().apply(this.glb(var4.map((x$20) -> x$20.lowerBound()), depth$2), this.lub(var4.map((x$21) -> x$21.upperBound()), depth$2));
      } else if (var3 && (Types.Type)var4.head() instanceof Types.AnnotatedType) {
         return ((AnnotationCheckers)this).annotationsLub(this.lub0$1(var4.map((x$22) -> x$22.withoutAnnotations()), depth$2), var4);
      } else {
         Option var14 = this.lubResults().get(new Tuple2(new Depth(depth$2), var5));
         if (var14 instanceof Some) {
            return (Types.Type)((Some)var14).value();
         } else if (scala.None..MODULE$.equals(var14)) {
            this.lubResults().update(new Tuple2(new Depth(depth$2), var5), ((Definitions)this).definitions().AnyTpe());
            Types.Type res = Depth$.MODULE$.isNegative$extension(depth$2) ? ((Definitions)this).definitions().AnyTpe() : this.lub1$1(var5, depth$2);
            this.lubResults().update(new Tuple2(new Depth(depth$2), var5), res);
            return res;
         } else {
            throw new MatchError(var14);
         }
      }
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$9(final Types.Type x$24) {
      return x$24.narrow();
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$10(final GlbLubs $this, final Symbols.Symbol sym$1, final Types.Type lubThisType$1, final int depth$2, final Types.Type t) {
      return !$this.refines$1(t, sym$1, lubThisType$1, depth$2);
   }

   private boolean excludeFromLub$1(final Symbols.Symbol sym, final List narrowts$1, final Types.Type lubThisType$1, final int depth$2) {
      if (!sym.isClass() && !sym.isConstructor() && sym.isPublic()) {
         Definitions.definitions$ var10000 = ((Definitions)this).definitions();
         if (var10000 == null) {
            throw null;
         }

         if (!Definitions.ValueClassDefinitions.isGetClass$(var10000, sym) && !sym.isFinal()) {
            if (narrowts$1 == null) {
               throw null;
            }

            List exists_these = narrowts$1;

            while(true) {
               if (exists_these.isEmpty()) {
                  var8 = false;
                  break;
               }

               Types.Type var6 = (Types.Type)exists_these.head();
               if ($anonfun$lub$10(this, sym, lubThisType$1, depth$2, var6)) {
                  var8 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var7 = null;
            if (!var8) {
               return false;
            }
         }
      }

      return true;
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$12(final Types.Type prototp$1, final Types.Type lubThisType$1, final Types.Type t$2, final Symbols.Symbol sym) {
      if (sym == null) {
         throw null;
      } else {
         return sym.tpe_$times().matches(prototp$1.substThis(lubThisType$1.typeSymbol(), t$2));
      }
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$lub$11(final Symbols.Symbol proto$1, final Types.Type prototp$1, final Types.Type lubThisType$1, final Types.Type t) {
      return t.nonPrivateMember(proto$1.name()).filter((sym) -> BoxesRunTime.boxToBoolean($anonfun$lub$12(prototp$1, lubThisType$1, t, sym)));
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$13(final Types.Type lubThisType$1, final Types.Type t, final Symbols.Symbol sym) {
      return t.memberInfo(sym).substThis(t.typeSymbol(), lubThisType$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$14(final List symtypes$1, final Types.Type x$25) {
      return ((Types.Type)symtypes$1.head()).$eq$colon$eq(x$25);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$15(final Types.Type x$26) {
      return x$26.lowerBound();
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$16(final Types.Type x$27) {
      return x$27.upperBound();
   }

   private Symbols.Symbol lubsym$1(final Symbols.Symbol proto, final Types.Type lubThisType$1, final List narrowts$1, final Types.Type lubRefined$1, final int depth$2) {
      Types.Type prototp = lubThisType$1.memberInfo(proto);
      if (narrowts$1 == null) {
         throw null;
      } else {
         Object var10000;
         if (narrowts$1 == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            Types.Type var32 = (Types.Type)narrowts$1.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$lub$11(proto, prototp, lubThisType$1, var32), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)narrowts$1.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var32 = (Types.Type)map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$lub$11(proto, prototp, lubThisType$1, var32), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var36 = null;
         Object var37 = null;
         Object var38 = null;
         Object var39 = null;
         List syms = (List)var10000;
         if (syms.contains(((Symbols)this).NoSymbol())) {
            return ((Symbols)this).NoSymbol();
         } else {
            Collections var55 = (Collections)this;
            ListBuffer map2_lb = new ListBuffer();
            List map2_ys1 = narrowts$1;

            for(List map2_ys2 = syms; !map2_ys1.isEmpty() && !map2_ys2.isEmpty(); map2_ys2 = (List)map2_ys2.tail()) {
               var55 = (Collections)map2_ys1.head();
               Symbols.Symbol var34 = (Symbols.Symbol)map2_ys2.head();
               Types.Type var33 = (Types.Type)var55;
               Object map2_$plus$eq_elem = $anonfun$lub$13(lubThisType$1, var33, var34);
               map2_lb.addOne(map2_$plus$eq_elem);
               map2_$plus$eq_elem = null;
               map2_ys1 = (List)map2_ys1.tail();
            }

            List var57 = map2_lb.toList();
            Object var40 = null;
            Object var41 = null;
            Object var42 = null;
            Object var44 = null;
            List symtypes = var57;
            if (proto.isTerm()) {
               return proto.cloneSymbol(lubRefined$1.typeSymbol()).setInfoOwnerAdjusted(this.lub(symtypes, Depth$.MODULE$.decr$extension(depth$2, 1)));
            } else {
               var57 = (List)symtypes.tail();
               if (var57 == null) {
                  throw null;
               } else {
                  List forall_these = var57;

                  while(true) {
                     if (forall_these.isEmpty()) {
                        var59 = true;
                        break;
                     }

                     Types.Type var35 = (Types.Type)forall_these.head();
                     if (!$anonfun$lub$14(symtypes, var35)) {
                        var59 = false;
                        break;
                     }

                     forall_these = (List)forall_these.tail();
                  }

                  Object var45 = null;
                  if (var59) {
                     return proto.cloneSymbol(lubRefined$1.typeSymbol()).setInfoOwnerAdjusted((Types.Type)symtypes.head());
                  } else {
                     Types.TypeBounds$ var60 = ((Types)this).TypeBounds();
                     Object var10002;
                     if (symtypes == .MODULE$) {
                        var10002 = .MODULE$;
                     } else {
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)symtypes.head()).lowerBound(), .MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)symtypes.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).lowerBound(), .MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var10002 = map_h;
                     }

                     Object var46 = null;
                     Object var47 = null;
                     Object var48 = null;
                     Object var49 = null;
                     Types.Type var10001 = this.glb((List)var10002, Depth$.MODULE$.decr$extension(depth$2, 1));
                     Object var10003;
                     if (symtypes == .MODULE$) {
                        var10003 = .MODULE$;
                     } else {
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)symtypes.head()).upperBound(), .MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)symtypes.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).upperBound(), .MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var10003 = map_h;
                     }

                     Object var50 = null;
                     Object var51 = null;
                     Object var52 = null;
                     Object var53 = null;
                     Types.TypeBounds lubBs = var60.apply(var10001, this.lub((List)var10003, Depth$.MODULE$.decr$extension(depth$2, 1)));
                     Symbols.Symbol qual$1 = lubRefined$1.typeSymbol();
                     Names.TypeName x$1 = proto.name().toTypeName();
                     Position x$2 = proto.pos();
                     if (qual$1 == null) {
                        throw null;
                     } else {
                        long x$3 = 0L;
                        return qual$1.createAbstractTypeSymbol(x$1, x$2, 16L | x$3).setInfoOwnerAdjusted(lubBs);
                     }
                  }
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$17(final GlbLubs $this, final Symbols.Symbol sym$2, final Types.Type lubThisType$1, final Types.Type tp$2, final int depth$2, final Symbols.Symbol alt) {
      if (alt == null) {
         if (sym$2 == null) {
            return false;
         }
      } else if (alt.equals(sym$2)) {
         return false;
      }

      if (!((Types)$this).specializesSym(lubThisType$1, sym$2, tp$2, alt, depth$2)) {
         return true;
      } else {
         return false;
      }
   }

   private boolean refines$1(final Types.Type tp, final Symbols.Symbol sym, final Types.Type lubThisType$1, final int depth$2) {
      List syms = tp.nonPrivateMember(sym.name()).alternatives();
      if (!syms.isEmpty()) {
         List forall_these = syms;

         boolean var10000;
         while(true) {
            if (forall_these.isEmpty()) {
               var10000 = true;
               break;
            }

            Symbols.Symbol var7 = (Symbols.Symbol)forall_these.head();
            if (!$anonfun$lub$17(this, sym, lubThisType$1, tp, depth$2, var7)) {
               var10000 = false;
               break;
            }

            forall_these = (List)forall_these.tail();
         }

         Object var8 = null;
         if (var10000) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$18(final GlbLubs $this, final List narrowts$1, final Types.Type lubThisType$1, final int depth$2, final Symbols.Symbol sym) {
      return !$this.excludeFromLub$1(sym, narrowts$1, lubThisType$1, depth$2);
   }

   // $FF: synthetic method
   static void $anonfun$lub$20(final GlbLubs $this, final Types.Type lubThisType$1, final Types.Type lubRefined$1, final int depth$2, final Symbols.Symbol x$28) {
      ((Types)$this).addMember(lubThisType$1, lubRefined$1, x$28, depth$2);
   }

   // $FF: synthetic method
   static boolean $anonfun$lub$21(final GlbLubs $this, final Types.Type lubRefined$1, final int depth$2, final Types.Type lubBase$1, final Types.Type t) {
      if (((TypeComparers)$this).isSubType(t, lubRefined$1, Depth$.MODULE$.decr$extension(depth$2, 1))) {
         return true;
      } else {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var9 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)$this).settings();
         MutableSettings var10 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var10;
         boolean var11 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var11 || $this.scala$reflect$internal$tpe$GlbLubs$$printLubs()) {
            scala.Console..MODULE$.println((new StringBuilder(61)).append("Malformed lub: ").append(lubRefined$1).append("\n").append("Argument ").append(t).append(" does not conform.  Falling back to ").append(lubBase$1).toString());
         }

         return false;
      }
   }

   private Types.Type lub1$1(final List ts0, final int depth$2) {
      Types var10000 = (Types)this;
      Types var10002 = (Types)this;
      Tuple2 var3 = var10000.stripExistentialsAndTypeVars(ts0, false);
      if (var3 == null) {
         throw new MatchError((Object)null);
      } else {
         List ts = (List)var3._1();
         List tparams = (List)var3._2();
         List lubBaseTypes = this.lubList(ts, depth$2);
         List lubParents = this.spanningTypes(lubBaseTypes);
         Symbols.Symbol lubOwner = ((CommonOwners)this).commonOwner(ts);
         Types.Type lubBase = ((Types)this).intersectionType(lubParents, lubOwner);
         Types.Type var25;
         if (!((SymbolTable)this).phase().erasedTypes() && !Depth$.MODULE$.isZero$extension(depth$2)) {
            Types.Type lubRefined = ((Types)this).refinedType(lubParents, lubOwner);
            Types.Type lubThisType = lubRefined.typeSymbol().thisType();
            if (ts == null) {
               throw null;
            }

            if (ts == .MODULE$) {
               var25 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)ts.head()).narrow(), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)ts.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).narrow(), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var25 = map_h;
            }

            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var23 = null;
            List narrowts = var25;
            lubBase.nonPrivateMembers().withFilter((sym) -> BoxesRunTime.boxToBoolean($anonfun$lub$18(this, narrowts, lubThisType, depth$2, sym))).foreach((sym) -> {
               try {
                  Symbols.Symbol var10000 = this.lubsym$1(sym, lubThisType, narrowts, lubRefined, depth$2);
                  if (var10000 == null) {
                     throw null;
                  } else {
                     Symbols.Symbol andAlso_this = var10000;
                     if (andAlso_this != andAlso_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        ((Types)this).addMember(lubThisType, lubRefined, andAlso_this, depth$2);
                     }

                     return andAlso_this;
                  }
               } catch (Types.NoCommonType var7) {
                  return BoxedUnit.UNIT;
               }
            });
            if (lubRefined.decls().isEmpty()) {
               var25 = lubBase;
            } else {
               List forall_these = ts;

               while(true) {
                  if (forall_these.isEmpty()) {
                     var27 = true;
                     break;
                  }

                  Types.Type var19 = (Types.Type)forall_these.head();
                  if (!$anonfun$lub$21(this, lubRefined, depth$2, lubBase, var19)) {
                     var27 = false;
                     break;
                  }

                  forall_these = (List)forall_these.tail();
               }

               Object var24 = null;
               var25 = var27 ? lubRefined : lubBase;
            }
         } else {
            var25 = lubBase;
         }

         Types.Type lubType = var25;
         return ((Types)this).existentialAbstraction(tparams, ((TypeMaps)this).dropIllegalStarTypes().apply(lubType), ((Types)this).existentialAbstraction$default$3());
      }
   }

   // $FF: synthetic method
   static String $anonfun$lub$22() {
      return "LUB is highly indented";
   }

   // $FF: synthetic method
   static Statistics.StackableTimer $anonfun$glb$1(final GlbLubs $this) {
      return ((TypesStats)((SymbolTable)$this).statistics()).lubNanos();
   }

   // $FF: synthetic method
   static Types.Type $anonfun$glbNorm$1(final GlbLubs $this, final List ts0, final int depth) {
      return $this.lub(ts0, depth);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$glbNorm$2(final GlbLubs $this, final int depth$3, final List ts0) {
      return $this.glb0$1(ts0, depth$3);
   }

   private Types.Type glb0$1(final List ts0, final int depth$3) {
      boolean var3 = false;
      scala.collection.immutable..colon.colon var4 = null;
      if (ts0 != null) {
         List var10000 = scala.package..MODULE$.List();
         if (var10000 == null) {
            throw null;
         }

         List unapplySeq_this = var10000;
         SeqOps var75 = SeqFactory.unapplySeq$(unapplySeq_this, ts0);
         Object var51 = null;
         SeqOps var5 = var75;
         SeqFactory.UnapplySeqWrapper var76 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var5);
         var76 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var76 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 0;
         if (var5.lengthCompare(lengthCompare$extension_len) == 0) {
            return ((Definitions)this).definitions().AnyTpe();
         }
      }

      if (ts0 != null) {
         List var79 = scala.package..MODULE$.List();
         if (var79 == null) {
            throw null;
         }

         List unapplySeq_this = var79;
         SeqOps var80 = SeqFactory.unapplySeq$(unapplySeq_this, ts0);
         Object var52 = null;
         SeqOps var6 = var80;
         SeqFactory.UnapplySeqWrapper var81 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         SeqFactory.UnapplySeqWrapper var96 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         new SeqFactory.UnapplySeqWrapper(var6);
         var81 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         var81 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
         int lengthCompare$extension_len = 1;
         if (var6.lengthCompare(lengthCompare$extension_len) == 0) {
            var81 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            var81 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
            int apply$extension_i = 0;
            return (Types.Type)var6.apply(apply$extension_i);
         }
      }

      if (ts0 instanceof scala.collection.immutable..colon.colon) {
         var3 = true;
         var4 = (scala.collection.immutable..colon.colon)ts0;
         Types.Type pt = (Types.Type)var4.head();
         List rest = var4.next$access$1();
         if (pt instanceof Types.PolyType) {
            Types.PolyType var9 = (Types.PolyType)pt;
            List polyTypeMatch_tparamsHead = var9.typeParams();
            if (rest == null) {
               throw null;
            }

            Object var84;
            if (rest == .MODULE$) {
               var84 = .MODULE$;
            } else {
               Types.Type var44 = (Types.Type)rest.head();
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, polyTypeMatch_tparamsHead, var9, rest, var44), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = (List)rest.tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  var44 = (Types.Type)polyTypeMatch_map_rest.head();
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$1(this, polyTypeMatch_tparamsHead, var9, rest, var44), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var84 = polyTypeMatch_map_h;
            }

            Object var53 = null;
            Object var54 = null;
            Object var55 = null;
            Object var56 = null;
            List polyTypeMatch_$colon$colon_this = (List)var84;
            scala.collection.immutable..colon.colon var85 = new scala.collection.immutable..colon.colon(var9, polyTypeMatch_$colon$colon_this);
            polyTypeMatch_$colon$colon_this = null;
            List polyTypeMatch_ntps = var85;
            List var86 = ((scala.collection.immutable..colon.colon)polyTypeMatch_ntps).tail();
            if (var86 == null) {
               throw null;
            }

            List polyTypeMatch_map_this = var86;
            if (polyTypeMatch_map_this == .MODULE$) {
               var86 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)polyTypeMatch_map_this.head(), polyTypeMatch_tparamsHead), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = (List)polyTypeMatch_map_this.tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon(unifyBounds$1((Types.PolyType)polyTypeMatch_map_rest.head(), polyTypeMatch_tparamsHead), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var86 = polyTypeMatch_map_h;
            }

            Object var57 = null;
            Object var58 = null;
            Object var59 = null;
            Object var60 = null;
            Object var61 = null;
            List polyTypeMatch_boundsTts = (List)var86.transpose(scala..less.colon.less..MODULE$.refl());
            Collections var88 = (Collections)this;
            ListBuffer polyTypeMatch_map2_lb = new ListBuffer();
            List polyTypeMatch_map2_ys1 = polyTypeMatch_tparamsHead;

            for(List polyTypeMatch_map2_ys2 = polyTypeMatch_boundsTts; !polyTypeMatch_map2_ys1.isEmpty() && !polyTypeMatch_map2_ys2.isEmpty(); polyTypeMatch_map2_ys2 = (List)polyTypeMatch_map2_ys2.tail()) {
               var88 = (Collections)polyTypeMatch_map2_ys1.head();
               List var46 = (List)polyTypeMatch_map2_ys2.head();
               Symbols.Symbol var45 = (Symbols.Symbol)var88;
               Symbols.Symbol var90 = var45.cloneSymbol();
               Types.Type var48 = var45.info();
               if (var46 == null) {
                  throw null;
               }

               scala.collection.immutable..colon.colon var97 = new scala.collection.immutable..colon.colon(var48, var46);
               Depth var50 = new Depth(depth$3);
               scala.collection.immutable..colon.colon var49 = var97;
               var90 = var90.setInfo($anonfun$glbNorm$1$adapted(this, var49, var50));
               var48 = null;
               Object polyTypeMatch_map2_$plus$eq_elem = var90;
               polyTypeMatch_map2_lb.addOne(polyTypeMatch_map2_$plus$eq_elem);
               polyTypeMatch_map2_$plus$eq_elem = null;
               polyTypeMatch_map2_ys1 = (List)polyTypeMatch_map2_ys1.tail();
            }

            List var92 = polyTypeMatch_map2_lb.toList();
            Object var62 = null;
            Object var63 = null;
            Object var64 = null;
            Object var66 = null;
            List polyTypeMatch_tparams1 = var92;
            Object var93;
            if (polyTypeMatch_ntps == .MODULE$) {
               var93 = .MODULE$;
            } else {
               Types.PolyType var47 = (Types.PolyType)polyTypeMatch_ntps.head();
               scala.collection.immutable..colon.colon polyTypeMatch_map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(polyTypeMatch_tparams1, var47), .MODULE$);
               scala.collection.immutable..colon.colon polyTypeMatch_map_t = polyTypeMatch_map_h;

               for(List polyTypeMatch_map_rest = ((scala.collection.immutable..colon.colon)polyTypeMatch_ntps).tail(); polyTypeMatch_map_rest != .MODULE$; polyTypeMatch_map_rest = (List)polyTypeMatch_map_rest.tail()) {
                  var47 = (Types.PolyType)polyTypeMatch_map_rest.head();
                  scala.collection.immutable..colon.colon polyTypeMatch_map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$5(polyTypeMatch_tparams1, var47), .MODULE$);
                  polyTypeMatch_map_t.next_$eq(polyTypeMatch_map_nx);
                  polyTypeMatch_map_t = polyTypeMatch_map_nx;
               }

               Statics.releaseFence();
               var93 = polyTypeMatch_map_h;
            }

            Object var67 = null;
            Object var68 = null;
            Object var69 = null;
            Object var70 = null;
            List polyTypeMatch_matchingInstTypes = (List)var93;
            SymbolTable var10002 = (SymbolTable)this;
            List var43 = polyTypeMatch_matchingInstTypes;
            return var10002.new PolyType(polyTypeMatch_tparams1, $anonfun$glbNorm$2(this, depth$3, var43));
         }
      }

      if (var3) {
         Types.Type mt = (Types.Type)var4.head();
         if (mt instanceof Types.MethodType) {
            Types.MethodType var11 = (Types.MethodType)mt;
            List params = var11.params();
            return (SymbolTable)this.new MethodType(params, this.glbNorm(this.matchingRestypes(var4, var11.paramTypes()), depth$3));
         }
      }

      if (var3 && (Types.Type)var4.head() instanceof Types.NullaryMethodType) {
         return (SymbolTable)this.new NullaryMethodType(this.glbNorm(this.matchingRestypes(var4, .MODULE$), depth$3));
      } else if (var3 && (Types.Type)var4.head() instanceof Types.TypeBounds) {
         return ((Types)this).TypeBounds().apply(this.lub(var4.map((x$29) -> x$29.lowerBound()), depth$3), this.glb(var4.map((x$30) -> x$30.upperBound()), depth$3));
      } else {
         Option var13 = this.glbResults().get(new Tuple2(new Depth(depth$3), ts0));
         if (var13 instanceof Some) {
            return (Types.Type)((Some)var13).value();
         } else {
            this.glbResults().update(new Tuple2(new Depth(depth$3), ts0), ((Definitions)this).definitions().NothingTpe());
            Types.Type res = Depth$.MODULE$.isNegative$extension(depth$3) ? ((Definitions)this).definitions().NothingTpe() : this.glb1$1(ts0, depth$3);
            this.glbResults().update(new Tuple2(new Depth(depth$3), ts0), res);
            return res;
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$5(final ListBuffer res$2, final Types.Type ty) {
      loop$2(ty, res$2);
   }

   private static void loop$2(final Types.Type ty, final ListBuffer res$2) {
      if (!(ty instanceof Types.RefinedType)) {
         if (res$2 == null) {
            throw null;
         } else {
            res$2.addOne(ty);
         }
      } else {
         List ps = ((Types.RefinedType)ty).parents();
         if (ps == null) {
            throw null;
         } else {
            for(List foreach_these = ps; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               loop$2((Types.Type)foreach_these.head(), res$2);
            }

         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$6(final ListBuffer res$2, final Types.Type ty) {
      loop$2(ty, res$2);
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$8(final Types.Type glbThisType$1, final Types.Type prototp$2, final ListBuffer res$3, final Symbols.Symbol alt) {
      Types.Type mi = glbThisType$1.memberInfo(alt);
      if (mi.matches(prototp$2)) {
         if (res$3 == null) {
            throw null;
         } else {
            return res$3.addOne(mi);
         }
      } else {
         return BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$7(final Symbols.Symbol proto$2, final Types.Type glbThisType$1, final Types.Type prototp$2, final ListBuffer res$3, final Types.Type t) {
      List var10000 = t.nonPrivateMember(proto$2.name()).alternatives();
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Symbols.Symbol var6 = (Symbols.Symbol)foreach_these.head();
            $anonfun$glbNorm$8(glbThisType$1, prototp$2, res$3, var6);
         }

      }
   }

   // $FF: synthetic method
   static String $anonfun$glbNorm$9() {
      return "No types for GLB";
   }

   private static boolean isTypeBound$1(final Types.Type tp) {
      return tp instanceof Types.TypeBounds;
   }

   // $FF: synthetic method
   static Types.Type $anonfun$glbNorm$10(final Types.Type x$32) {
      return x$32.lowerBound();
   }

   // $FF: synthetic method
   static Types.Type $anonfun$glbNorm$11(final Types.Type x$33) {
      return x$33.upperBound();
   }

   private Types.TypeBounds glbBounds$1(final List bnds, final int depth$3) {
      if (bnds == null) {
         throw null;
      } else {
         Object var10001;
         if (bnds == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)bnds.head()).lowerBound(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)bnds.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).lowerBound(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10001 = map_h;
         }

         Object var13 = null;
         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Types.Type lo = this.lub((List)var10001, Depth$.MODULE$.decr$extension(depth$3, 1));
         if (bnds == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Types.Type)bnds.head()).upperBound(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)bnds.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Types.Type)map_rest.head()).upperBound(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10001 = map_h;
         }

         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         Object var20 = null;
         Types.Type hi = this.glb((List)var10001, Depth$.MODULE$.decr$extension(depth$3, 1));
         if (lo.$less$colon$less(hi)) {
            return ((Types)this).TypeBounds().apply(lo, hi);
         } else {
            throw this.GlbFailure();
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$glbNorm$12(final Types.Type tp) {
      return isTypeBound$1(tp);
   }

   // $FF: synthetic method
   static boolean $anonfun$glbNorm$13(final Types.Type t) {
      return !isTypeBound$1(t);
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$14(final GlbLubs $this, final ObjectRef result$1, final Types.Type t) {
      if (((Types.Type)result$1.elem).bounds().containsType(t)) {
         result$1.elem = t;
      } else {
         throw $this.GlbFailure();
      }
   }

   private Symbols.Symbol glbsym$1(final Symbols.Symbol proto, final Types.Type glbThisType$1, final List ts$3, final Types.Type glbRefined$1, final int depth$3) {
      Types.Type prototp = glbThisType$1.memberInfo(proto);
      ListBuffer var10000 = scala.collection.mutable.ListBuffer..MODULE$;
      ListBuffer res = new ListBuffer();
      if (ts$3 == null) {
         throw null;
      } else {
         for(List foreach_these = ts$3; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Types.Type var30 = (Types.Type)foreach_these.head();
            $anonfun$glbNorm$7(proto, glbThisType$1, prototp, res, var30);
         }

         Object var31 = null;
         List symtypes = res.toList();
         SymbolTable var65 = (SymbolTable)this;
         boolean assert_assertion = !symtypes.isEmpty();
         SymbolTable assert_this = var65;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("No types for GLB");
         } else {
            Symbols.Symbol var66 = proto.cloneSymbol(glbRefined$1.typeSymbol());
            Types.Type var10001;
            if (proto.isTerm()) {
               var10001 = this.glb(symtypes, Depth$.MODULE$.decr$extension(depth$3, 1));
            } else {
               boolean filter_filterCommon_isFlipped = false;
               List filter_filterCommon_noneIn$1_l = symtypes;

               while(true) {
                  if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                     var10001 = .MODULE$;
                     break;
                  }

                  Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
                  List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
                  if (isTypeBound$1((Types.Type)filter_filterCommon_noneIn$1_h) != filter_filterCommon_isFlipped) {
                     List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var10001 = filter_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                        if (isTypeBound$1((Types.Type)filter_filterCommon_noneIn$1_allIn$1_x) == filter_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              if (isTypeBound$1((Types.Type)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head) != filter_filterCommon_isFlipped) {
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var10001 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var40 = null;
                           Object var43 = null;
                           Object var46 = null;
                           Object var49 = null;
                           Object var52 = null;
                           Object var55 = null;
                           Object var58 = null;
                           Object var61 = null;
                           break;
                        }

                        filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var36 = null;
                     Object var38 = null;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     Object var50 = null;
                     Object var53 = null;
                     Object var56 = null;
                     Object var59 = null;
                     Object var62 = null;
                     break;
                  }

                  filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
               }

               Object var33 = null;
               Object var34 = null;
               Object var35 = null;
               Object var37 = null;
               Object var39 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               Object var60 = null;
               Object var63 = null;
               List filter_filterCommon_result = var10001;
               Statics.releaseFence();
               var10001 = filter_filterCommon_result;
               filter_filterCommon_result = null;
               List symbounds = var10001;
               U create_e = (U)(symbounds.isEmpty() ? ((Types)this).TypeBounds().empty() : this.glbBounds$1(symbounds, depth$3));
               ObjectRef var69 = new ObjectRef(create_e);
               create_e = (U)null;
               ObjectRef result = var69;
               symtypes.withFilter((t) -> BoxesRunTime.boxToBoolean($anonfun$glbNorm$13(t))).foreach((t) -> {
                  $anonfun$glbNorm$14(this, result, t);
                  return BoxedUnit.UNIT;
               });
               var10001 = (Types.Type)result.elem;
            }

            return var66.setInfoOwnerAdjusted(var10001);
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$15(final GlbLubs $this, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3, final Types.Type ty) {
      $this.foreachRefinedDecls$1(ty, glbThisType$1, depth$3, glbRefined$1, ts$3);
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$16(final GlbLubs $this, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3, final Symbols.Symbol sym) {
      if (Ordered.$less$(new Depth($this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth()), new Depth($this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit())) && !((Types)$this).specializesSym(glbThisType$1, sym, depth$3)) {
         try {
            ((Types)$this).addMember(glbThisType$1, glbRefined$1, $this.glbsym$1(sym, glbThisType$1, ts$3, glbRefined$1, depth$3), depth$3);
         } catch (Types.NoCommonType var6) {
         }
      }
   }

   private void foreachRefinedDecls$1(final Types.Type ty, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3) {
      if (ty instanceof Types.RefinedType) {
         Types.RefinedType var6 = (Types.RefinedType)ty;
         List ps = var6.parents();
         Scopes.Scope decls = var6.decls();
         if (ps == null) {
            throw null;
         } else {
            for(List foreach_these = ps; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Types.Type var10 = (Types.Type)foreach_these.head();
               $anonfun$glbNorm$15(this, glbThisType$1, depth$3, glbRefined$1, ts$3, var10);
            }

            Object var11 = null;
            if (!decls.isEmpty()) {
               decls.iterator().foreach((sym) -> {
                  $anonfun$glbNorm$16(this, glbThisType$1, depth$3, glbRefined$1, ts$3, sym);
                  return BoxedUnit.UNIT;
               });
            }
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$glbNorm$17(final GlbLubs $this, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3, final Types.Type ty) {
      $this.foreachRefinedDecls$1(ty, glbThisType$1, depth$3, glbRefined$1, ts$3);
   }

   // $FF: synthetic method
   static boolean $anonfun$glbNorm$18(final GlbLubs $this, final Types.Type x$34) {
      return ((Definitions)$this).definitions().NullTpe().$less$colon$less(x$34);
   }

   private Types.Type glb1$1(final List ts0, final int depth$3) {
      try {
         Types var27 = (Types)this;
         Types var10002 = (Types)this;
         Tuple2 var3 = var27.stripExistentialsAndTypeVars(ts0, false);
         if (var3 == null) {
            throw new MatchError((Object)null);
         } else {
            List ts = (List)var3._1();
            List tparams = (List)var3._2();
            Symbols.Symbol glbOwner = ((CommonOwners)this).commonOwner(ts);
            ListBuffer var28 = scala.collection.mutable.ListBuffer..MODULE$;
            ListBuffer res = new ListBuffer();
            if (ts == null) {
               throw null;
            } else {
               for(List foreach_these = ts; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  loop$2((Types.Type)foreach_these.head(), res);
               }

               Object var23 = null;
               List ts1 = res.toList();
               Types.Type var29;
               if (!((SymbolTable)this).phase().erasedTypes() && !Depth$.MODULE$.isZero$extension(depth$3)) {
                  Types.Type glbRefined = ((Types)this).refinedType(ts1, glbOwner);
                  Types.Type glbThisType = glbRefined.typeSymbol().thisType();
                  if (Ordered.$less$(new Depth(this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth()), new Depth(this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit()))) {
                     try {
                        this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(Depth$.MODULE$.incr$extension(this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth(), 1));

                        for(List foreach_these = ts; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                           Types.Type var17 = (Types.Type)foreach_these.head();
                           $anonfun$glbNorm$17(this, glbThisType, depth$3, glbRefined, ts, var17);
                        }

                        Object var24 = null;
                     } finally {
                        this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(Depth$.MODULE$.decr$extension(this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth(), 1));
                     }
                  }

                  var29 = glbRefined.decls().isEmpty() ? ((Types)this).intersectionType(ts1, glbOwner) : glbRefined;
               } else {
                  var29 = ((Types)this).intersectionType(ts1, glbOwner);
               }

               Types.Type glbType = var29;
               return ((Types)this).existentialAbstraction(tparams, glbType, ((Types)this).existentialAbstraction$default$3());
            }
         }
      } catch (Throwable var22) {
         Throwable var10000 = this.GlbFailure();
         if (var10000 == null) {
            if (var22 != null) {
               throw var22;
            }
         } else if (!var10000.equals(var22)) {
            throw var22;
         }

         if (ts0 == null) {
            throw null;
         } else {
            List forall_these = ts0;

            while(true) {
               if (forall_these.isEmpty()) {
                  var26 = true;
                  break;
               }

               Types.Type var18 = (Types.Type)forall_these.head();
               if (!$anonfun$glbNorm$18(this, var18)) {
                  var26 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var25 = null;
            if (var26) {
               return ((Definitions)this).definitions().NullTpe();
            } else {
               return ((Definitions)this).definitions().NothingTpe();
            }
         }
      }
   }

   private Types.PolyType normalizeIter$1(final Types.Type ty, final List tparamsHead$1, final Types.PolyType ptHead$1, final List ptRest$1) {
      while(true) {
         if (ty instanceof Types.PolyType) {
            Types.PolyType var5 = (Types.PolyType)ty;
            List typeParams = var5.typeParams();
            if (((Collections)this).sameLength(typeParams, tparamsHead$1)) {
               return var5;
            }
         }

         Types.Type tpn = ty.normalize();
         if (ty == tpn) {
            Types.NoCommonType var8 = new Types.NoCommonType;
            SymbolTable var10002 = (SymbolTable)this;
            if (ptRest$1 == null) {
               throw null;
            }

            var8.<init>(new scala.collection.immutable..colon.colon(ptHead$1, ptRest$1));
            throw var8;
         }

         SymbolTable var10000 = (SymbolTable)this;
         ty = tpn;
         this = var10000;
      }
   }

   // $FF: synthetic method
   static Types.PolyType $anonfun$polyTypeMatch$1(final GlbLubs $this, final List tparamsHead$1, final Types.PolyType ptHead$1, final List ptRest$1, final Types.Type ty) {
      return $this.normalizeIter$1(ty, tparamsHead$1, ptHead$1, ptRest$1);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$polyTypeMatch$2(final List tparams1$1, final List tparamsHead$1, final Symbols.Symbol tparam) {
      return tparam.info().substSym(tparams1$1, tparamsHead$1);
   }

   private static List unifyBounds$1(final Types.PolyType ntp, final List tparamsHead$1) {
      List tparams1 = ntp.typeParams();
      if (tparams1 == null) {
         throw null;
      } else if (tparams1 == .MODULE$) {
         return .MODULE$;
      } else {
         Symbols.Symbol var7 = (Symbols.Symbol)tparams1.head();
         scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$2(tparams1, tparamsHead$1, var7), .MODULE$);
         scala.collection.immutable..colon.colon map_t = map_h;

         for(List map_rest = (List)tparams1.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
            var7 = (Symbols.Symbol)map_rest.head();
            scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$polyTypeMatch$2(tparams1, tparamsHead$1, var7), .MODULE$);
            map_t.next_$eq(map_nx);
            map_t = map_nx;
         }

         Statics.releaseFence();
         return map_h;
      }
   }

   // $FF: synthetic method
   static List $anonfun$polyTypeMatch$3(final List tparamsHead$1, final Types.PolyType ntp) {
      return unifyBounds$1(ntp, tparamsHead$1);
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$polyTypeMatch$4(final Function2 infoBoundTop$1, final int depth$4, final Symbols.Symbol tparam, final List bounds) {
      Symbols.Symbol var10000 = tparam.cloneSymbol();
      Types.Type var4 = tparam.info();
      if (bounds == null) {
         throw null;
      } else {
         return var10000.setInfo((Types.Type)infoBoundTop$1.apply(new scala.collection.immutable..colon.colon(var4, bounds), new Depth(depth$4)));
      }
   }

   // $FF: synthetic method
   static Types.Type $anonfun$polyTypeMatch$5(final List tparams1$2, final Types.PolyType ntp) {
      return ntp.resultType().substSym(ntp.typeParams(), tparams1$2);
   }

   // $FF: synthetic method
   static Types.Type $anonfun$matchingRestypes$1(final GlbLubs $this, final List pts$1, final List tps$1, final Types.Type x0$1) {
      if (x0$1 instanceof Types.MethodType) {
         Types.MethodType var4 = (Types.MethodType)x0$1;
         Types.Type res = var4.resultType();
         if (((Types)$this).isSameTypes(var4.paramTypes(), pts$1)) {
            return res;
         }
      }

      if (x0$1 instanceof Types.NullaryMethodType) {
         Types.Type res = ((Types.NullaryMethodType)x0$1).resultType();
         if (pts$1.isEmpty()) {
            return res;
         }
      }

      throw (SymbolTable)$this.new NoCommonType(tps$1);
   }

   static void $init$(final GlbLubs $this) {
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(System.getProperty("scalac.debug.lub") != null);
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector_$eq((SymbolTable)$this.new FindTypeCollector((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$isWildCardOrNonGroundTypeVarCollector$1(x0$1))));
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(new HashMap());
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(new HashMap());
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(new Throwable());
      $this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(Depth$.MODULE$.Zero());
      byte apply_depth = 2;
      Depth$ apply_this = Depth$.MODULE$;
      int var10001 = apply_depth < -3 ? apply_this.AnyDepth() : apply_depth;
      Object var3 = null;
      $this.scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(var10001);
   }

   // $FF: synthetic method
   static Object $anonfun$printLubMatrix$2$adapted(final Tuple2 x$1) {
      return BoxesRunTime.boxToInteger($anonfun$printLubMatrix$2(x$1));
   }

   // $FF: synthetic method
   static TableDef.Column $anonfun$printLubMatrix$4$adapted(final GlbLubs $this, final Tuple2 x0$1, final Object x1$1) {
      return $anonfun$printLubMatrix$4($this, x0$1, BoxesRunTime.unboxToInt(x1$1));
   }

   // $FF: synthetic method
   static Object $anonfun$spanningTypes$1$adapted(final Types.Type first$1, final Types.Type t) {
      return BoxesRunTime.boxToBoolean($anonfun$spanningTypes$1(first$1, t));
   }

   // $FF: synthetic method
   static Object $anonfun$maxTypes$5$adapted(final GlbLubs $this, final Types.Type x$12) {
      return BoxesRunTime.boxToBoolean($anonfun$maxTypes$5($this, x$12));
   }

   // $FF: synthetic method
   static Object $anonfun$sameWeakLubAsLub$1$adapted(final Types.Type x$16) {
      return BoxesRunTime.boxToBoolean($anonfun$sameWeakLubAsLub$1(x$16));
   }

   // $FF: synthetic method
   static Object $anonfun$sameWeakLubAsLub$2$adapted(final GlbLubs $this, final Types.Type tp) {
      return BoxesRunTime.boxToBoolean($anonfun$sameWeakLubAsLub$2($this, tp));
   }

   // $FF: synthetic method
   static Object $anonfun$weakLub$1$adapted(final GlbLubs $this, final Types.Type tp) {
      return BoxesRunTime.boxToBoolean($anonfun$weakLub$1($this, tp));
   }

   // $FF: synthetic method
   static Object $anonfun$weakLub$2$adapted(final Types.Type x$17) {
      return BoxesRunTime.boxToBoolean($anonfun$weakLub$2(x$17));
   }

   // $FF: synthetic method
   static Object $anonfun$lub$2$adapted(final int hs$1, final Types.Type x$19) {
      return BoxesRunTime.boxToBoolean($anonfun$lub$2(hs$1, x$19));
   }

   // $FF: synthetic method
   static Object $anonfun$lubList_x$1$adapted(final List xs$1, final Types.Type x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$lubList_x$1(xs$1, x$3));
   }

   // $FF: synthetic method
   static Object $anonfun$maxTypes$1$adapted(final Function2 po$1, final Types.Type t$1, final Types.Type x$8) {
      return BoxesRunTime.boxToBoolean($anonfun$maxTypes$1(po$1, t$1, x$8));
   }

   // $FF: synthetic method
   static Object $anonfun$maxTypes$2$adapted(final Function2 po$1, final Types.Type t$1, final Types.Type x$9) {
      return BoxesRunTime.boxToBoolean($anonfun$maxTypes$2(po$1, t$1, x$9));
   }

   // $FF: synthetic method
   static Object $anonfun$maxTypes$4$adapted(final Function2 po$1, final Types.Type h$2, final Types.Type x$11) {
      return BoxesRunTime.boxToBoolean($anonfun$maxTypes$4(po$1, h$2, x$11));
   }

   // $FF: synthetic method
   static Object $anonfun$maxTypes$3$adapted(final Function2 po$1, final Types.Type h$1, final Types.Type x$10) {
      return BoxesRunTime.boxToBoolean($anonfun$maxTypes$3(po$1, h$1, x$10));
   }

   // $FF: synthetic method
   static Types.Type $anonfun$lub$4$adapted(final GlbLubs $this, final List ts, final Object depth) {
      int $anonfun$lub$4_depth = ((Depth)depth).depth();
      return $this.glb(ts, $anonfun$lub$4_depth);
   }

   // $FF: synthetic method
   static Object $anonfun$lub$10$adapted(final GlbLubs $this, final Symbols.Symbol sym$1, final Types.Type lubThisType$1, final int depth$2, final Types.Type t) {
      return BoxesRunTime.boxToBoolean($anonfun$lub$10($this, sym$1, lubThisType$1, depth$2, t));
   }

   // $FF: synthetic method
   static Object $anonfun$lub$14$adapted(final List symtypes$1, final Types.Type x$25) {
      return BoxesRunTime.boxToBoolean($anonfun$lub$14(symtypes$1, x$25));
   }

   // $FF: synthetic method
   static Object $anonfun$lub$17$adapted(final GlbLubs $this, final Symbols.Symbol sym$2, final Types.Type lubThisType$1, final Types.Type tp$2, final int depth$2, final Symbols.Symbol alt) {
      return BoxesRunTime.boxToBoolean($anonfun$lub$17($this, sym$2, lubThisType$1, tp$2, depth$2, alt));
   }

   // $FF: synthetic method
   static Object $anonfun$lub$20$adapted(final GlbLubs $this, final Types.Type lubThisType$1, final Types.Type lubRefined$1, final int depth$2, final Symbols.Symbol x$28) {
      $anonfun$lub$20($this, lubThisType$1, lubRefined$1, depth$2, x$28);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$lub$21$adapted(final GlbLubs $this, final Types.Type lubRefined$1, final int depth$2, final Types.Type lubBase$1, final Types.Type t) {
      return BoxesRunTime.boxToBoolean($anonfun$lub$21($this, lubRefined$1, depth$2, lubBase$1, t));
   }

   // $FF: synthetic method
   static Types.Type $anonfun$glbNorm$1$adapted(final GlbLubs $this, final List ts0, final Object depth) {
      int $anonfun$glbNorm$1_depth = ((Depth)depth).depth();
      return $this.lub(ts0, $anonfun$glbNorm$1_depth);
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$5$adapted(final ListBuffer res$2, final Types.Type ty) {
      $anonfun$glbNorm$5(res$2, ty);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$7$adapted(final Symbols.Symbol proto$2, final Types.Type glbThisType$1, final Types.Type prototp$2, final ListBuffer res$3, final Types.Type t) {
      $anonfun$glbNorm$7(proto$2, glbThisType$1, prototp$2, res$3, t);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$12$adapted(final Types.Type tp) {
      return BoxesRunTime.boxToBoolean($anonfun$glbNorm$12(tp));
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$15$adapted(final GlbLubs $this, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3, final Types.Type ty) {
      $anonfun$glbNorm$15($this, glbThisType$1, depth$3, glbRefined$1, ts$3, ty);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$6$adapted(final ListBuffer res$2, final Types.Type ty) {
      $anonfun$glbNorm$6(res$2, ty);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$17$adapted(final GlbLubs $this, final Types.Type glbThisType$1, final int depth$3, final Types.Type glbRefined$1, final List ts$3, final Types.Type ty) {
      $anonfun$glbNorm$17($this, glbThisType$1, depth$3, glbRefined$1, ts$3, ty);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$glbNorm$18$adapted(final GlbLubs $this, final Types.Type x$34) {
      return BoxesRunTime.boxToBoolean($anonfun$glbNorm$18($this, x$34));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
