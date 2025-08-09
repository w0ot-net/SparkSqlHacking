package scala.collection.generic;

import scala.collection.Factory;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!\u0002\u0006\f\u0003\u0003\u0011\u0002\"\u0002)\u0001\t\u0003\t\u0006\"B*\u0001\t\u0003!\u0006\"B3\u0001\r\u00031\u0007\"B7\u0001\t\u0003q\u0007\"\u0002?\u0001\r\u0003ihABA\t\u0001\u0001\t\u0019\u0002\u0003\u0004Q\r\u0011\u0005\u0011q\u0007\u0005\u0007'\u001a!\t!!\u0010\t\rM3A\u0011AA#\u00055\u0001\u0016M]'ba\u001a\u000b7\r^8ss*\u0011A\"D\u0001\bO\u0016tWM]5d\u0015\tqq\"\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0002\u0001+\r\u0019b$P\n\u0004\u0001QA\u0002CA\u000b\u0017\u001b\u0005y\u0011BA\f\u0010\u0005\u0019\te.\u001f*fMB\u0019\u0011D\u0007\u000f\u000e\u0003-I!aG\u0006\u0003-\u001d+g.\u001a:jGB\u000b'/T1q\u0007>l\u0007/\u00198j_:\u0004\"!\b\u0010\r\u0001\u0011)q\u0004\u0001b\u0001A\t\u00111iQ\u000b\u0004C=2\u0014C\u0001\u0012&!\t)2%\u0003\u0002%\u001f\t9aj\u001c;iS:<'c\u0001\u0014)q\u0019!q\u0005\u0001\u0001&\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0011ICFL\u001b\u000e\u0003)R!aK\u0007\u0002\u0011A\f'/\u00197mK2L!!\f\u0016\u0003\rA\u000b'/T1q!\tir\u0006B\u00031=\t\u0007\u0011GA\u0001Y#\t\u0011#\u0007\u0005\u0002\u0016g%\u0011Ag\u0004\u0002\u0004\u0003:L\bCA\u000f7\t\u00159dD1\u00012\u0005\u0005I\u0006cB\u0015:]Ub2\bP\u0005\u0003u)\u0012!\u0002U1s\u001b\u0006\u0004H*[6f!\u0011ibDL\u001b\u0011\tuid&\u000e\u0003\u0006}\u0001\u0011\ra\u0010\u0002\u000b'\u0016\fX/\u001a8uS\u0006dWc\u0001!I\u0015F\u0011!%\u0011\n\u0004\u0005\u000e[e\u0001B\u0014\u0001\u0001\u0005\u0003B\u0001R#H\u00136\tQ\"\u0003\u0002G\u001b\t\u0019Q*\u00199\u0011\u0005uAE!\u0002\u0019>\u0005\u0004\t\u0004CA\u000fK\t\u00159TH1\u00012!\u0019!EjR%O\u001f&\u0011Q*\u0004\u0002\u0007\u001b\u0006\u0004x\n]:\u0011\u0005ui\u0004\u0003B\u000f>\u000f&\u000ba\u0001P5oSRtD#\u0001*\u0011\te\u0001ADT\u0001\u0006CB\u0004H._\u000b\u0004+b[FC\u0001,^!\u0011ibd\u0016.\u0011\u0005uAF!B-\u0003\u0005\u0004\t$!A&\u0011\u0005uYF!\u0002/\u0003\u0005\u0004\t$!\u0001,\t\u000by\u0013\u0001\u0019A0\u0002\u000b\u0015dW-\\:\u0011\u0007U\u0001'-\u0003\u0002b\u001f\tQAH]3qK\u0006$X\r\u001a \u0011\tU\u0019wKW\u0005\u0003I>\u0011a\u0001V;qY\u0016\u0014\u0014!B3naRLXcA4kYV\t\u0001\u000e\u0005\u0003\u001e=%\\\u0007CA\u000fk\t\u0015I6A1\u00012!\tiB\u000eB\u0003]\u0007\t\u0007\u0011'\u0001\u0006oK^\u0014U/\u001b7eKJ,2a\u001c={+\u0005\u0001\b\u0003B9umnl\u0011A\u001d\u0006\u0003g6\tq!\\;uC\ndW-\u0003\u0002ve\n9!)^5mI\u0016\u0014\b\u0003B\u000bdof\u0004\"!\b=\u0005\u000be#!\u0019A\u0019\u0011\u0005uQH!\u0002/\u0005\u0005\u0004\t\u0004\u0003B\u000f\u001fof\f1B\\3x\u0007>l'-\u001b8feV)a0!\u0003\u0002\u000eU\tq\u0010E\u0004*\u0003\u0003\t)!a\u0004\n\u0007\u0005\r!F\u0001\u0005D_6\u0014\u0017N\\3s!\u0019)2-a\u0002\u0002\fA\u0019Q$!\u0003\u0005\u000be+!\u0019A\u0019\u0011\u0007u\ti\u0001B\u0003]\u000b\t\u0007\u0011\u0007\u0005\u0004\u001e=\u0005\u001d\u00111\u0002\u0002\u0012\u0007\u0006t7i\\7cS:,gI]8n\u001b\u0006\u0004XCCA\u000b\u0003C\t9#a\f\u00024M!a\u0001FA\f!%I\u0012\u0011DA\u000f\u0003W\t)$C\u0002\u0002\u001c-\u0011abQ1o\u0007>l'-\u001b8f\rJ|W\u000e\u0005\u0004\u001e=\u0005}\u0011Q\u0005\t\u0004;\u0005\u0005BABA\u0012\r\t\u0007\u0011GA\u0003Ge>l7\nE\u0002\u001e\u0003O!a!!\u000b\u0007\u0005\u0004\t$!\u0002$s_64\u0006CB\u000bd\u0003[\t\t\u0004E\u0002\u001e\u0003_!Q!\u0017\u0004C\u0002E\u00022!HA\u001a\t\u0015afA1\u00012!\u0019ib$!\f\u00022Q\u0011\u0011\u0011\b\t\f\u0003w1\u0011qDA\u0013\u0003[\t\t$D\u0001\u0001)\u0011\ty$!\u0011\u0011\u000f%\n\t!a\u000b\u00026!9\u00111\t\u0005A\u0002\u0005u\u0011\u0001\u00024s_6$\"!a\u0010"
)
public abstract class ParMapFactory implements GenericParMapCompanion {
   public Factory toFactory() {
      return GenericParMapCompanion.toFactory$(this);
   }

   public ParMap apply(final Seq elems) {
      return (ParMap)((Builder)this.newCombiner().$plus$plus$eq(elems)).result();
   }

   public abstract ParMap empty();

   public Builder newBuilder() {
      return this.newCombiner();
   }

   public abstract Combiner newCombiner();

   public ParMapFactory() {
      GenericParMapCompanion.$init$(this);
   }

   public class CanCombineFromMap implements CanCombineFrom {
      // $FF: synthetic field
      public final ParMapFactory $outer;

      public Combiner apply(final ParMap from) {
         return from.genericMapCombiner();
      }

      public Combiner apply() {
         return this.scala$collection$generic$ParMapFactory$CanCombineFromMap$$$outer().newCombiner();
      }

      // $FF: synthetic method
      public ParMapFactory scala$collection$generic$ParMapFactory$CanCombineFromMap$$$outer() {
         return this.$outer;
      }

      public CanCombineFromMap() {
         if (ParMapFactory.this == null) {
            throw null;
         } else {
            this.$outer = ParMapFactory.this;
            super();
         }
      }
   }
}
