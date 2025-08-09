package scala.xml;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.Seq;
import scala.collection.immutable.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rr!B\u0016-\u0011\u0003\td!B\u001a-\u0011\u0003!\u0004\"B!\u0002\t\u0003\u0011\u0005\"B\"\u0002\t\u0003!\u0005bBA|\u0003\u0011\u0005\u0011\u0011 \u0005\b\u0003\u007f\fA\u0011\u0001B\u0001\u0011\u001d\u0011I!\u0001C\u0001\u0005\u0017A\u0011Ba\u0005\u0002\u0003\u0003%IA!\u0006\u0007\u000bMb\u0013\u0011\u0001$\t\u000b\u0005CA\u0011A-\t\riCA\u0011\u0001\u0017\\\u0011\u0015y\u0006\u0002\"\u0001a\u0011\u001dA\u0007\"%A\u0005\u0002%DQ\u0001\u001e\u0005\u0007\u0002UDa\u0001\u001e\u0005\u0005\u0006\u0005M\u0001B\u0002;\t\r\u0003\ty\u0002C\u0004\u0002,!1\t!!\f\t\u000f\u0005M\u0002B\"\u0001\u00026!1\u0011\u0011\b\u0005\u0005\u0002mCq!a\u000f\t\t\u0003\ti\u0004C\u0004\u0002<!!\t!!\u0012\t\r\u0005-\u0003B\"\u0001\\\u0011\u001d\ti\u0005\u0003C!\u0003\u001fBq!a\u0017\t\t\u0003\ni\u0006C\u0004\u0002b!!\t&a\u0019\t\u000f\u0005\u001d\u0004\u0002\"\u0011\u0002j!9\u0011Q\u000f\u0005\u0005\u0002\u0005]\u0004BB?\t\r\u0003\tI\bC\u0004\u0002|!1\t!! \t\u000f\u0005}\u0004\u0002\"\u0001\u0002z!9\u0011\u0011\u0011\u0005\u0005\u0002\u0005\r\u0005bBA\u0019\u0011\u0019\u0005\u0011q\u000f\u0005\b\u0003\u0017CAQAAG\u0011\u001d\tY\t\u0003C\u0003\u0003/Cq!a#\t\t\u000b\t\t\u000bC\u0004\u0002*\"!\t\"!\u001f\t\u000f\u0005%\u0006B\"\u0005\u0002,\"9\u0011Q\u0018\u0005\u0005B\u0005}\u0006bBAa\u0011\u0011\u0005\u00111\u0019\u0005\b\u0003\u000fDa\u0011AAe\u0011\u001d\ti\r\u0003D\u0001\u0003\u001fDq!!4\t\r\u0003\t\u0019\u000eC\u0004\u0002N\"!)!!8\u0002\u00115+G/\u0019#bi\u0006T!!\f\u0018\u0002\u0007alGNC\u00010\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AM\u0001\u000e\u00031\u0012\u0001\"T3uC\u0012\u000bG/Y\n\u0004\u0003UJ\u0004C\u0001\u001c8\u001b\u0005q\u0013B\u0001\u001d/\u0005\u0019\te.\u001f*fMB\u0011!hP\u0007\u0002w)\u0011A(P\u0001\u0003S>T\u0011AP\u0001\u0005U\u00064\u0018-\u0003\u0002Aw\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!M\u0001\fG>t7-\u0019;f]\u0006$X\rF\u0003F\u0003K\fI\u000f\u0005\u00023\u0011M)\u0001bR'U/B\u0019\u0001jS#\u000e\u0003%S!A\u0013\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002M\u0013\n\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006\u0014G.\u001a\t\u0004\u001dF+eB\u0001\u001cP\u0013\t\u0001f&A\u0004qC\u000e\\\u0017mZ3\n\u0005I\u001b&\u0001C%uKJ\f'\r\\3\u000b\u0005As\u0003C\u0001\u001aV\u0013\t1FF\u0001\u0005FcV\fG.\u001b;z!\tq\u0005,\u0003\u0002A'R\tQ)\u0001\u0004jg:+H\u000e\\\u000b\u00029B\u0011a'X\u0005\u0003=:\u0012qAQ8pY\u0016\fg.\u0001\u0004baB,g\u000e\u001a\u000b\u0004\u000b\u0006\u001c\u0007\"\u00022\f\u0001\u0004)\u0015aB;qI\u0006$Xm\u001d\u0005\bI.\u0001\n\u00111\u0001f\u0003\u0015\u00198m\u001c9f!\t\u0011d-\u0003\u0002hY\t\u0001b*Y7fgB\f7-\u001a\"j]\u0012LgnZ\u0001\u0011CB\u0004XM\u001c3%I\u00164\u0017-\u001e7uII*\u0012A\u001b\u0016\u0003K.\\\u0013\u0001\u001c\t\u0003[Jl\u0011A\u001c\u0006\u0003_B\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005Et\u0013AC1o]>$\u0018\r^5p]&\u00111O\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!B1qa2LHC\u0001<}!\rAu/_\u0005\u0003q&\u00131aU3r!\t\u0011$0\u0003\u0002|Y\t!aj\u001c3f\u0011\u0015iX\u00021\u0001\u007f\u0003\rYW-\u001f\t\u0004\u007f\u00065a\u0002BA\u0001\u0003\u0013\u00012!a\u0001/\u001b\t\t)AC\u0002\u0002\bA\na\u0001\u0010:p_Rt\u0014bAA\u0006]\u00051\u0001K]3eK\u001aLA!a\u0004\u0002\u0012\t11\u000b\u001e:j]\u001eT1!a\u0003/)\u001d1\u0018QCA\r\u0003;Aa!a\u0006\u000f\u0001\u0004q\u0018!\u00048b[\u0016\u001c\b/Y2f?V\u0014\u0018\u000e\u0003\u0004\u0002\u001c9\u0001\r!_\u0001\u0006_^tWM\u001d\u0005\u0006{:\u0001\rA \u000b\bm\u0006\u0005\u00121EA\u0014\u0011\u0019\t9b\u0004a\u0001}\"1\u0011QE\bA\u0002\u0015\f1a]2q\u0011\u0019\tIc\u0004a\u0001}\u0006\t1.\u0001\u0003d_BLHcA#\u00020!1\u0011\u0011\u0007\tA\u0002\u0015\u000bAA\\3yi\u0006aq-\u001a;OC6,7\u000f]1dKR\u0019a0a\u000e\t\r\u0005m\u0011\u00031\u0001z\u0003\u001dA\u0017m\u001d(fqR\fa\u0001\\3oORDWCAA !\r1\u0014\u0011I\u0005\u0004\u0003\u0007r#aA%oiR!\u0011qHA$\u0011\u001d\tI\u0005\u0006a\u0001\u0003\u007f\t\u0011![\u0001\u000bSN\u0004&/\u001a4jq\u0016$\u0017\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007q\u000b\t\u0006C\u0004\u0002TY\u0001\r!!\u0016\u0002\u000b=$\b.\u001a:\u0011\u0007Y\n9&C\u0002\u0002Z9\u00121!\u00118z\u00035\u0019HO]5di~#S-\u001d\u0013fcR\u0019A,a\u0018\t\r\u0005Ms\u00031\u0001U\u0003A\u0011\u0017m]5t\r>\u0014\b*Y:i\u0007>$W-\u0006\u0002\u0002fA!\u0001j^A+\u0003\u00191\u0017\u000e\u001c;feR\u0019Q)a\u001b\t\u000f\u00055\u0014\u00041\u0001\u0002p\u0005\ta\rE\u00037\u0003c*E,C\u0002\u0002t9\u0012\u0011BR;oGRLwN\\\u0019\u0002\u000fI,g/\u001a:tKV\tQ)F\u0001\u007f\u0003\u00151\u0018\r\\;f+\u00051\u0018a\u00039sK\u001aL\u00070\u001a3LKf\f\u0011\"Y:BiR\u0014X*\u00199\u0016\u0005\u0005\u0015\u0005#B@\u0002\bzt\u0018\u0002BAE\u0003#\u00111!T1q\u0003\r9W\r\u001e\u000b\u0005\u0003\u001f\u000b)\n\u0005\u00037\u0003#3\u0018bAAJ]\t1q\n\u001d;j_:DQ! \u0011A\u0002y$\u0002\"a$\u0002\u001a\u0006u\u0015q\u0014\u0005\u0007\u00037\u000b\u0003\u0019\u0001@\u0002\u0007U\u0014\u0018\u000e\u0003\u0004\u0002\u001c\u0005\u0002\r!\u001f\u0005\u0006{\u0006\u0002\rA \u000b\t\u0003\u001f\u000b\u0019+!*\u0002(\"1\u00111\u0014\u0012A\u0002yDQ\u0001\u001a\u0012A\u0002\u0015DQ! \u0012A\u0002y\f\u0011\u0002^8TiJLgnZ\u0019\u0015\t\u00055\u00161\u0017\t\u0004m\u0005=\u0016bAAY]\t!QK\\5u\u0011\u001d\t)\f\na\u0001\u0003o\u000b!a\u001d2\u0011\u00079\u000bI,C\u0002\u0002<N\u0013Qb\u0015;sS:<')^5mI\u0016\u0014\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003y\f1BY;jY\u0012\u001cFO]5oOR!\u0011qWAc\u0011\u001d\t)L\na\u0001\u0003o\u000b!b^3mY\u001a|'/\\3e)\ra\u00161\u001a\u0005\u0006I\u001e\u0002\r!Z\u0001\u0007e\u0016lwN^3\u0015\u0007\u0015\u000b\t\u000eC\u0003~Q\u0001\u0007a\u0010F\u0004F\u0003+\fI.a7\t\r\u0005]\u0017\u00061\u0001\u007f\u0003%q\u0017-\\3ta\u0006\u001cW\rC\u0003eS\u0001\u0007Q\rC\u0003~S\u0001\u0007a\u0010F\u0004F\u0003?\f\t/a9\t\r\u0005]'\u00061\u0001\u007f\u0011\u0019\tYB\u000ba\u0001s\")QP\u000ba\u0001}\"1\u0011q]\u0002A\u0002\u0015\u000bq!\u0019;ue&\u00147\u000f\u0003\u0004\u0002l\u000e\u0001\r!R\u0001\t]\u0016<x\f^1jY\"\u001a1!a<\u0011\t\u0005E\u00181_\u0007\u0002a&\u0019\u0011Q\u001f9\u0003\u000fQ\f\u0017\u000e\u001c:fG\u0006Ian\u001c:nC2L'0\u001a\u000b\u0006\u000b\u0006m\u0018Q \u0005\u0007\u0003O$\u0001\u0019A#\t\u000b\u0011$\u0001\u0019A3\u0002\u001f\u001d,G/\u00168jm\u0016\u00148/\u00197LKf$RA B\u0002\u0005\u000fAaA!\u0002\u0006\u0001\u0004)\u0015AB1uiJL'\rC\u0003e\u000b\u0001\u0007Q-\u0001\u0004va\u0012\fG/\u001a\u000b\b\u000b\n5!q\u0002B\t\u0011\u0019\t9O\u0002a\u0001\u000b\")AM\u0002a\u0001K\")!M\u0002a\u0001\u000b\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0003\t\u0005\u00053\u0011y\"\u0004\u0002\u0003\u001c)\u0019!QD\u001f\u0002\t1\fgnZ\u0005\u0005\u0005C\u0011YB\u0001\u0004PE*,7\r\u001e"
)
public abstract class MetaData extends AbstractIterable implements Equality, Serializable {
   public static MetaData update(final MetaData attribs, final NamespaceBinding scope, final MetaData updates) {
      return MetaData$.MODULE$.update(attribs, scope, updates);
   }

   public static String getUniversalKey(final MetaData attrib, final NamespaceBinding scope) {
      return MetaData$.MODULE$.getUniversalKey(attrib, scope);
   }

   public static MetaData normalize(final MetaData attribs, final NamespaceBinding scope) {
      return MetaData$.MODULE$.normalize(attribs, scope);
   }

   public static MetaData concatenate(final MetaData attribs, final MetaData new_tail) {
      return MetaData$.MODULE$.concatenate(attribs, new_tail);
   }

   public boolean strict_$bang$eq(final Equality other) {
      return Equality.strict_$bang$eq$(this, other);
   }

   public int hashCode() {
      return Equality.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Equality.equals$(this, other);
   }

   public final boolean xml_$eq$eq(final Object other) {
      return Equality.xml_$eq$eq$(this, other);
   }

   public final boolean xml_$bang$eq(final Object other) {
      return Equality.xml_$bang$eq$(this, other);
   }

   public boolean isNull() {
      return this == Null$.MODULE$;
   }

   public MetaData append(final MetaData updates, final NamespaceBinding scope) {
      return MetaData$.MODULE$.update(this, scope, updates);
   }

   public NamespaceBinding append$default$2() {
      return TopScope$.MODULE$;
   }

   public abstract Seq apply(final String key);

   public final Seq apply(final String namespace_uri, final Node owner, final String key) {
      return this.apply(namespace_uri, owner.scope(), key);
   }

   public abstract Seq apply(final String namespace_uri, final NamespaceBinding scp, final String k);

   public abstract MetaData copy(final MetaData next);

   public abstract String getNamespace(final Node owner);

   public boolean hasNext() {
      return !Null$.MODULE$.equals(this.next());
   }

   public int length() {
      return this.length(0);
   }

   public int length(final int i) {
      return this.next().length(i + 1);
   }

   public abstract boolean isPrefixed();

   public boolean canEqual(final Object other) {
      return other instanceof MetaData;
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (!(other instanceof MetaData)) {
         return false;
      } else {
         boolean var6;
         label30: {
            MetaData var4 = (MetaData)other;
            Map var10000 = this.asAttrMap();
            Map var5 = var4.asAttrMap();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public Seq basisForHashCode() {
      return new .colon.colon(this.asAttrMap(), scala.collection.immutable.Nil..MODULE$);
   }

   public MetaData filter(final Function1 f) {
      return BoxesRunTime.unboxToBoolean(f.apply(this)) ? this.copy(this.next().filter(f)) : this.next().filter(f);
   }

   public MetaData reverse() {
      return (MetaData)this.foldLeft(Null$.MODULE$, (x, xs) -> xs.copy(x));
   }

   public abstract String key();

   public abstract Seq value();

   public String prefixedKey() {
      if (this instanceof Attribute) {
         Attribute var3 = (Attribute)this;
         if (var3.isPrefixed()) {
            return (new StringBuilder(1)).append(var3.pre()).append(":").append(this.key()).toString();
         }
      }

      return this.key();
   }

   public Map asAttrMap() {
      return this.iterator().map((x) -> new Tuple2(x.prefixedKey(), NodeSeq$.MODULE$.seqToNodeSeq(x.value()).text())).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public abstract MetaData next();

   public final Option get(final String key) {
      return scala.Option..MODULE$.apply(this.apply(key));
   }

   public final Option get(final String uri, final Node owner, final String key) {
      return this.get(uri, owner.scope(), key);
   }

   public final Option get(final String uri, final NamespaceBinding scope, final String key) {
      return scala.Option..MODULE$.apply(this.apply(uri, scope, key));
   }

   public String toString1() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString1$1(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public abstract void toString1(final scala.collection.mutable.StringBuilder sb);

   public String toString() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString$1(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public scala.collection.mutable.StringBuilder buildString(final scala.collection.mutable.StringBuilder sb) {
      sb.append(' ');
      this.toString1(sb);
      return this.next().buildString(sb);
   }

   public abstract boolean wellformed(final NamespaceBinding scope);

   public abstract MetaData remove(final String key);

   public abstract MetaData remove(final String namespace, final NamespaceBinding scope, final String key);

   public final MetaData remove(final String namespace, final Node owner, final String key) {
      return this.remove(namespace, owner.scope(), key);
   }

   // $FF: synthetic method
   public static final void $anonfun$toString1$1(final MetaData $this, final scala.collection.mutable.StringBuilder sb) {
      $this.toString1(sb);
   }

   // $FF: synthetic method
   public static final void $anonfun$toString$1(final MetaData $this, final scala.collection.mutable.StringBuilder sb) {
      $this.buildString(sb);
   }

   public MetaData() {
      Equality.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
