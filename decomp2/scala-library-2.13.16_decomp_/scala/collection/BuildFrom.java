package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ImmutableBuilder;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tMca\u0002\n\u0014!\u0003\r\t\u0001\u0007\u0005\u0006=\u0001!\ta\b\u0005\u0006G\u00011\t\u0001\n\u0005\u0006y\u00011\t!\u0010\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006-\u0002!\taV\u0004\u0006MNA\ta\u001a\u0004\u0006%MA\t\u0001\u001b\u0005\u0006_\u001e!\t\u0001\u001d\u0005\u0006c\u001e!\u0019A\u001d\u0005\b\u0003\u0013:A1AA&\u0011\u001d\t9l\u0002C\u0002\u0003sC\u0011\"a7\b\u0005\u0004%\u0019!!8\t\u0011\u0005]x\u0001)A\u0005\u0003?D\u0011\"!?\b\u0005\u0004%\u0019!a?\t\u0011\t-q\u0001)A\u0005\u0003{DqA!\u0004\b\t\u0007\u0011y\u0001C\u0004\u0003:\u001d!\u0019Aa\u000f\u0003\u0013\t+\u0018\u000e\u001c3Ge>l'B\u0001\u000b\u0016\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002-\u0005)1oY1mC\u000e\u0001Q\u0003B\r;k!\u001a\"\u0001\u0001\u000e\u0011\u0005maR\"A\u000b\n\u0005u)\"aA!os\u00061A%\u001b8ji\u0012\"\u0012\u0001\t\t\u00037\u0005J!AI\u000b\u0003\tUs\u0017\u000e^\u0001\rMJ|Wn\u00159fG&4\u0017n\u0019\u000b\u0003K]\"\"A\n\u0018\u0011\u0005\u001dBC\u0002\u0001\u0003\u0007S\u0001!)\u0019\u0001\u0016\u0003\u0003\r\u000b\"a\u000b\u000e\u0011\u0005ma\u0013BA\u0017\u0016\u0005\u001dqu\u000e\u001e5j]\u001eDQa\f\u0002A\u0002A\n!!\u001b;\u0011\u0007E\u0012D'D\u0001\u0014\u0013\t\u00194C\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW\r\u0005\u0002(k\u00111a\u0007\u0001EC\u0002)\u0012\u0011!\u0011\u0005\u0006q\t\u0001\r!O\u0001\u0005MJ|W\u000e\u0005\u0002(u\u001111\b\u0001EC\u0002)\u0012AA\u0012:p[\u0006Qa.Z<Ck&dG-\u001a:\u0015\u0005y\"\u0005\u0003B Ci\u0019j\u0011\u0001\u0011\u0006\u0003\u0003N\tq!\\;uC\ndW-\u0003\u0002D\u0001\n9!)^5mI\u0016\u0014\b\"\u0002\u001d\u0004\u0001\u0004I\u0014!B1qa2LHC\u0001 H\u0011\u0015AD\u00011\u0001:Q\u0019!\u0011\nT'P!B\u00111DS\u0005\u0003\u0017V\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013AT\u0001$+N,\u0007E\\3x\u0005VLG\u000eZ3sQ%\u0002\u0013N\\:uK\u0006$\u0007e\u001c4!CB\u0004H.\u001f\u0015*\u0003\u0015\u0019\u0018N\\2fC\u0005\t\u0016A\u0002\u001a/cMr\u0003\u0007\u000b\u0002\u0005'B\u00111\u0004V\u0005\u0003+V\u0011a!\u001b8mS:,\u0017!\u0003;p\r\u0006\u001cGo\u001c:z)\tA6\f\u0005\u000323R2\u0013B\u0001.\u0014\u0005\u001d1\u0015m\u0019;pefDQ\u0001O\u0003A\u0002eBC\u0001A/dIB\u0011a,Y\u0007\u0002?*\u0011\u0001-F\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00012`\u0005AIW\u000e\u001d7jG&$hj\u001c;G_VtG-A\u0002ng\u001e\f\u0013!Z\u0001m\u0007\u0006tgn\u001c;!G>t7\u000f\u001e:vGR\u0004\u0013\rI2pY2,7\r^5p]\u0002zg\r\t;za\u0016\u0004Ce_\"~A]LG\u000f\u001b\u0011fY\u0016lWM\u001c;tA=4\u0007\u0005^=qK\u0002\"30Q?!E\u0006\u001cX\r\u001a\u0011p]\u0002\n\u0007eY8mY\u0016\u001cG/[8oA=4\u0007\u0005^=qK\u0002\"3P\u0012:p[vt\u0013!\u0003\"vS2$gI]8n!\t\ttaE\u0002\bS2\u0004\"a\u00076\n\u0005-,\"AB!osJ+g\r\u0005\u00022[&\u0011an\u0005\u0002\u0016\u0005VLG\u000e\u001a$s_6dun\u001e)sS>\u0014\u0018\u000e^=2\u0003\u0019a\u0014N\\5u}Q\tq-A\bck&dGM\u0012:p[6\u000b\u0007o\u00149t+)\u0019\u00180a\t\u0002*\u0005]\u0012QH\u000b\u0002iB9\u0011\u0007A;\u00020\u0005\u0005#\u0003\u0002<y\u0003[1Aa^\u0004\u0001k\naAH]3gS:,W.\u001a8u}A1q%_A\u0011\u0003O!QA_\u0005C\u0002m\u0014!aQ\"\u0016\u000bq\f9!!\u0004\u0012\u0005-j(\u0003\u0002@\u0000\u0003#1Aa^\u0004\u0001{B9\u0011'!\u0001\u0002\u0006\u0005-\u0011bAA\u0002'\t\u0019Q*\u00199\u0011\u0007\u001d\n9\u0001\u0002\u0004\u0002\ne\u0014\rA\u000b\u0002\u00021B\u0019q%!\u0004\u0005\r\u0005=\u0011P1\u0001+\u0005\u0005I\u0006\u0007BA\n\u0003;\u00012\"MA\u000b\u0003\u000b\tY!!\u0007\u0002\u001c%\u0019\u0011qC\n\u0003\r5\u000b\u0007o\u00149t!\t9\u0013\u0010E\u0002(\u0003;!!\"a\bz\u0003\u0003\u0005\tQ!\u0001+\u0005\ryF%\r\t\u0004O\u0005\rBABA\u0013\u0013\t\u0007!F\u0001\u0002LaA\u0019q%!\u000b\u0005\r\u0005-\u0012B1\u0001+\u0005\t1\u0006\u0007E\u00042\u0003\u0003\t\t#a\n\u0011\u000fm\t\t$!\u000e\u0002<%\u0019\u00111G\u000b\u0003\rQ+\b\u000f\\33!\r9\u0013q\u0007\u0003\u0007\u0003sI!\u0019\u0001\u0016\u0003\u0003-\u00032aJA\u001f\t\u0019\ty$\u0003b\u0001U\t\taK\u0005\u0004\u0002D\u0005\u0015\u0013q\t\u0004\u0006o\u001e\u0001\u0011\u0011\t\t\u0007Oe\f)$a\u000f\u0011\u000fE\n\t!!\u000e\u0002<\u0005)\"-^5mI\u001a\u0013x.\\*peR,G-T1q\u001fB\u001cX\u0003DA'\u0003/\n\t)!\"\u0002\u000e\u0006EE\u0003BA(\u00037\u0003\u0002\"\r\u0001\u0002R\u0005%\u00151\u0013\n\u0007\u0003'\n)&a\"\u0007\u000b]<\u0001!!\u0015\u0011\u000f\u001d\n9&a \u0002\u0004\u00121!P\u0003b\u0001\u00033*b!a\u0017\u0002j\u00055\u0014cA\u0016\u0002^I1\u0011qLA1\u0003_2Qa^\u0004\u0001\u0003;\u0002r!MA2\u0003O\nY'C\u0002\u0002fM\u0011\u0011bU8si\u0016$W*\u00199\u0011\u0007\u001d\nI\u0007B\u0004\u0002\n\u0005]#\u0019\u0001\u0016\u0011\u0007\u001d\ni\u0007B\u0004\u0002\u0010\u0005]#\u0019\u0001\u00161\t\u0005E\u00141\u0010\t\fc\u0005M\u0014qMA6\u0003o\nI(C\u0002\u0002vM\u0011AbU8si\u0016$W*\u00199PaN\u00042aJA,!\r9\u00131\u0010\u0003\f\u0003{\n9&!A\u0001\u0002\u000b\u0005!FA\u0002`IQ\u00022aJAA\t\u0019\t)C\u0003b\u0001UA\u0019q%!\"\u0005\r\u0005-\"B1\u0001+!\u001d\t\u00141MA@\u0003\u0007\u0003raGA\u0019\u0003\u0017\u000by\tE\u0002(\u0003\u001b#a!!\u000f\u000b\u0005\u0004Q\u0003cA\u0014\u0002\u0012\u00121\u0011q\b\u0006C\u0002)\u0012b!!&\u0002\u0018\u0006ee!B<\b\u0001\u0005M\u0005cB\u0014\u0002X\u0005-\u0015q\u0012\t\bc\u0005\r\u00141RAH\u0011%\tiJCA\u0001\u0002\b\ty*\u0001\u0006fm&$WM\\2fIE\u0002b!!)\u00022\u0006-e\u0002BAR\u0003[sA!!*\u0002,6\u0011\u0011q\u0015\u0006\u0004\u0003S;\u0012A\u0002\u001fs_>$h(C\u0001\u0017\u0013\r\ty+F\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019,!.\u0003\u0011=\u0013H-\u001a:j]\u001eT1!a,\u0016\u0003=\u0011W/\u001b7e\rJ|WNQ5u'\u0016$X\u0003BA^\u0003\u0003,\"!!0\u0011\u0011E\u0002\u0011qXAk\u0003\u007f\u00032aJAa\t\u0019I3B1\u0001\u0002DF\u00191&!2\u0013\r\u0005\u001d\u0017\u0011ZAh\r\u00159x\u0001AAc!\r\t\u00141Z\u0005\u0004\u0003\u001b\u001c\"A\u0002\"jiN+G\u000fE\u00032\u0003#\fy,C\u0002\u0002TN\u0011\u0011BQ5u'\u0016$x\n]:\u0011\u0007m\t9.C\u0002\u0002ZV\u00111!\u00138u\u0003=\u0011W/\u001b7e\rJ|Wn\u0015;sS:<WCAAp!!\t\u0004!!9\u0002r\u0006\u0005\b\u0003BAr\u0003WtA!!:\u0002hB\u0019\u0011QU\u000b\n\u0007\u0005%X#\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003[\fyO\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003S,\u0002cA\u000e\u0002t&\u0019\u0011Q_\u000b\u0003\t\rC\u0017M]\u0001\u0011EVLG\u000e\u001a$s_6\u001cFO]5oO\u0002\naCY;jY\u00124%o\\7Xe\u0006\u0004\b/\u001a3TiJLgnZ\u000b\u0003\u0003{\u0004\u0002\"\r\u0001\u0002\u0000\u0006E\u0018q \t\u0005\u0005\u0003\u00119!\u0004\u0002\u0003\u0004)\u0019!QA\n\u0002\u0013%lW.\u001e;bE2,\u0017\u0002\u0002B\u0005\u0005\u0007\u0011Qb\u0016:baB,Gm\u0015;sS:<\u0017a\u00062vS2$gI]8n/J\f\u0007\u000f]3e'R\u0014\u0018N\\4!\u00039\u0011W/\u001b7e\rJ|W.\u0011:sCf,BA!\u0005\u0003&Q!!1\u0003B\u0015!!\t\u0004A!\u0006\u0003$\t\u001d\u0002\u0007\u0002B\f\u0005?\u0001Ra\u0007B\r\u0005;I1Aa\u0007\u0016\u0005\u0015\t%O]1z!\r9#q\u0004\u0003\u000b\u0005C\u0001\u0012\u0011!A\u0001\u0006\u0003Q#aA0%oA\u0019qE!\n\u0005\u000bY\u0002\"\u0019\u0001\u0016\u0011\u000bm\u0011IBa\t\t\u0013\t-\u0002#!AA\u0004\t5\u0012AC3wS\u0012,gnY3%eA1!q\u0006B\u001b\u0005Gi!A!\r\u000b\u0007\tMR#A\u0004sK\u001adWm\u0019;\n\t\t]\"\u0011\u0007\u0002\t\u00072\f7o\u001d+bO\u0006i!-^5mI\u001a\u0013x.\u001c,jK^,bA!\u0010\u0003J\t5SC\u0001B !!\t\u0004A!\u0011\u0003L\tE\u0003#B\u0019\u0003D\t\u001d\u0013b\u0001B#'\t!a+[3x!\r9#\u0011\n\u0003\u0006mE\u0011\rA\u000b\t\u0004O\t5CA\u0002B(#\t\u0007!FA\u0001C!\u0015\t$1\tB&\u0001"
)
public interface BuildFrom {
   static BuildFrom buildFromView() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public View fromSpecific(final View from, final IterableOnce it) {
            return View$.MODULE$.from(it);
         }

         public Builder newBuilder(final View from) {
            return View$.MODULE$.newBuilder();
         }
      };
   }

   static BuildFrom buildFromArray(final ClassTag evidence$2) {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom(evidence$2) {
         private final ClassTag evidence$2$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Object fromSpecific(final Object from, final IterableOnce it) {
            Factory$ var10000 = Factory$.MODULE$;
            ClassTag arrayFactory_evidence$1 = this.evidence$2$1;
            Factory.ArrayFactory var5 = new Factory.ArrayFactory(arrayFactory_evidence$1);
            arrayFactory_evidence$1 = null;
            return var5.fromSpecific(it);
         }

         public Builder newBuilder(final Object from) {
            Factory$ var10000 = Factory$.MODULE$;
            ClassTag arrayFactory_evidence$1 = this.evidence$2$1;
            Factory.ArrayFactory var4 = new Factory.ArrayFactory(arrayFactory_evidence$1);
            arrayFactory_evidence$1 = null;
            return var4.newBuilder();
         }

         public {
            this.evidence$2$1 = evidence$2$1;
         }
      };
   }

   static BuildFrom buildFromWrappedString() {
      return BuildFrom$.MODULE$.buildFromWrappedString();
   }

   static BuildFrom buildFromString() {
      return BuildFrom$.MODULE$.buildFromString();
   }

   static BuildFrom buildFromBitSet() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public BitSet fromSpecific(final BitSet from, final IterableOnce it) {
            return (BitSet)from.bitSetFactory().fromSpecific(it);
         }

         public Builder newBuilder(final BitSet from) {
            return from.bitSetFactory().newBuilder();
         }
      };
   }

   static BuildFrom buildFromSortedMapOps(final Ordering evidence$1) {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom(evidence$1) {
         private final Ordering evidence$1$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final SortedMap from) {
            return from.sortedMapFactory().newBuilder(this.evidence$1$1);
         }

         public SortedMap fromSpecific(final SortedMap from, final IterableOnce it) {
            return (SortedMap)from.sortedMapFactory().from(it, this.evidence$1$1);
         }

         public {
            this.evidence$1$1 = evidence$1$1;
         }
      };
   }

   static BuildFrom buildFromMapOps() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.apply$(this, from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.toFactory$(this, from);
         }

         public Builder newBuilder(final Map from) {
            return from.mapFactory().newBuilder();
         }

         public Map fromSpecific(final Map from, final IterableOnce it) {
            return (Map)from.mapFactory().from(it);
         }
      };
   }

   static BuildFrom fallbackStringCanBuildFrom() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.super.apply(from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.super.toFactory(from);
         }

         public scala.collection.immutable.IndexedSeq fromSpecific(final String from, final IterableOnce it) {
            return scala.collection.immutable.IndexedSeq$.MODULE$.from(it);
         }

         public Builder newBuilder(final String from) {
            return scala.collection.immutable.IndexedSeq$.MODULE$.newBuilder();
         }
      };
   }

   static BuildFrom buildFromSortedSetOps(final Ordering evidence$3) {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom(evidence$3) {
         private final Ordering evidence$3$1;

         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.super.apply(from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.super.toFactory(from);
         }

         public Builder newBuilder(final SortedSet from) {
            return from.sortedIterableFactory().newBuilder(this.evidence$3$1);
         }

         public SortedSet fromSpecific(final SortedSet from, final IterableOnce it) {
            return (SortedSet)from.sortedIterableFactory().from(it, this.evidence$3$1);
         }

         public {
            this.evidence$3$1 = evidence$3$1;
         }
      };
   }

   static BuildFrom buildFromIterator() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.super.apply(from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.super.toFactory(from);
         }

         public Builder newBuilder(final Iterator from) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return new ImmutableBuilder() {
               public <undefinedtype> addOne(final Object elem) {
                  Iterator var10001 = (Iterator)this.elems();
                  Function0 $plus$plus_xs = () -> Iterator$.MODULE$.single(elem);
                  if (var10001 == null) {
                     throw null;
                  } else {
                     var10001 = var10001.concat($plus$plus_xs);
                     $plus$plus_xs = null;
                     this.elems_$eq(var10001);
                     return this;
                  }
               }

               public {
                  Iterator$ var10001 = Iterator$.MODULE$;
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            };
         }

         public Iterator fromSpecific(final Iterator from, final IterableOnce it) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return it.iterator();
         }
      };
   }

   static BuildFrom buildFromIterableOps() {
      BuildFrom$ var10000 = BuildFrom$.MODULE$;
      return new BuildFrom() {
         /** @deprecated */
         public Builder apply(final Object from) {
            return BuildFrom.super.apply(from);
         }

         public Factory toFactory(final Object from) {
            return BuildFrom.super.toFactory(from);
         }

         public Builder newBuilder(final Iterable from) {
            return from.iterableFactory().newBuilder();
         }

         public Iterable fromSpecific(final Iterable from, final IterableOnce it) {
            return (Iterable)from.iterableFactory().from(it);
         }
      };
   }

   Object fromSpecific(final Object from, final IterableOnce it);

   Builder newBuilder(final Object from);

   /** @deprecated */
   default Builder apply(final Object from) {
      return this.newBuilder(from);
   }

   default Factory toFactory(final Object from) {
      return new Factory(from) {
         // $FF: synthetic field
         private final BuildFrom $outer;
         private final Object from$1;

         public Object fromSpecific(final IterableOnce it) {
            return this.$outer.fromSpecific(this.from$1, it);
         }

         public Builder newBuilder() {
            return this.$outer.newBuilder(this.from$1);
         }

         public {
            if (BuildFrom.this == null) {
               throw null;
            } else {
               this.$outer = BuildFrom.this;
               this.from$1 = from$1;
            }
         }
      };
   }

   static void $init$(final BuildFrom $this) {
   }
}
