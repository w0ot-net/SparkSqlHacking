package scala.collection.generic;

import java.io.Serializable;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eca\u0002\t\u0012!\u0003\r\t\u0001\u0007\u0005\u0006=\u0001!\ta\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006'\u00021\t\u0001\u0016\u0005\u00065\u0002!\u0019aW\u0004\u0006IFA\t!\u001a\u0004\u0006!EA\ta\u001a\u0005\u0006Q\"!\t!\u001b\u0005\u00065\"!\u0019A\u001b\u0004\u0005u\"!1\u0010C\u0005x\u0017\t\u0005\t\u0015!\u0003\u0002\u001e!1\u0001n\u0003C\u0001\u0003CAq!!\u000b\f\t\u0003\tY\u0003\u0003\u0004K\u0017\u0011\u0005\u0011q\u0007\u0002\u0014\u000f\u0016tWM]5d!\u0006\u00148i\\7qC:LwN\u001c\u0006\u0003%M\tqaZ3oKJL7M\u0003\u0002\u0015+\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003Y\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u001aQM\u0011\u0001A\u0007\t\u00037qi\u0011!F\u0005\u0003;U\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001!!\tY\u0012%\u0003\u0002#+\t!QK\\5u\u0003\u0015)W\u000e\u001d;z+\t)S(F\u0001'!\r9\u0003\u0006\u0010\u0007\u0001\t\u0019I\u0003\u0001\"b\u0001U\t\u00111iQ\u000b\u0003WY\n\"\u0001L\u0018\u0011\u0005mi\u0013B\u0001\u0018\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001M\u001a6\u001b\u0005\t$B\u0001\u001a\u0014\u0003!\u0001\u0018M]1mY\u0016d\u0017B\u0001\u001b2\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\u0005\u001d2D!B\u001c)\u0005\u0004A$!\u0001-\u0012\u00051J\u0004CA\u000e;\u0013\tYTCA\u0002B]f\u0004\"aJ\u001f\u0005\u000by\u0012!\u0019\u0001\u001d\u0003\u0003\u0005\u000bQ!\u00199qYf,\"!\u0011#\u0015\u0005\t+\u0005cA\u0014)\u0007B\u0011q\u0005\u0012\u0003\u0006}\r\u0011\r\u0001\u000f\u0005\u0006\r\u000e\u0001\raR\u0001\u0006K2,Wn\u001d\t\u00047!\u001b\u0015BA%\u0016\u0005)a$/\u001a9fCR,GMP\u0001\u000b]\u0016<()^5mI\u0016\u0014XC\u0001'R+\u0005i\u0005\u0003\u0002\u0019O!JK!aT\u0019\u0003\u0011\r{WNY5oKJ\u0004\"aJ)\u0005\u000by\"!\u0019\u0001\u001d\u0011\u0007\u001dB\u0003+A\u0006oK^\u001cu.\u001c2j]\u0016\u0014XCA+Y+\u00051\u0006\u0003\u0002\u0019O/f\u0003\"a\n-\u0005\u000by*!\u0019\u0001\u001d\u0011\u0007\u001dBs+A\u0005u_\u001a\u000b7\r^8ssV\u0011ALY\u000b\u0002;B!alX1d\u001b\u0005\u0019\u0012B\u00011\u0014\u0005\u001d1\u0015m\u0019;pef\u0004\"a\n2\u0005\u000by2!\u0019\u0001\u001d\u0011\u0007\u001dB\u0013-A\nHK:,'/[2QCJ\u001cu.\u001c9b]&|g\u000e\u0005\u0002g\u00115\t\u0011c\u0005\u0002\t5\u00051A(\u001b8jiz\"\u0012!Z\u000b\u0004W:\u0004HC\u00017w!\u0011qv,\\8\u0011\u0005\u001drG!\u0002 \u000b\u0005\u0004A\u0004cA\u0014q[\u0012)\u0011F\u0003b\u0001cV\u0011!/^\t\u0003YM\u00042\u0001M\u001au!\t9S\u000fB\u00038a\n\u0007\u0001\bC\u0003x\u0015\u0001\u0007\u00010\u0001\u0006qCJ4\u0015m\u0019;pef\u00042A\u001a\u0001z!\t9\u0003OA\u0005U_\u001a\u000b7\r^8ssV!Ap`A\u0002'\u0015Y!$`A\b!\u0015qvL`A\u0001!\t9s\u0010B\u0003?\u0017\t\u0007\u0001\b\u0005\u0003(\u0003\u0007qHAB\u0015\f\u0005\u0004\t)!\u0006\u0003\u0002\b\u00055\u0011c\u0001\u0017\u0002\nA!\u0001gMA\u0006!\r9\u0013Q\u0002\u0003\u0007o\u0005\r!\u0019\u0001\u001d\u0011\t\u0005E\u0011q\u0003\b\u00047\u0005M\u0011bAA\u000b+\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\r\u00037\u0011AbU3sS\u0006d\u0017N_1cY\u0016T1!!\u0006\u0016!\u00111\u0007!a\b\u0011\u0007\u001d\n\u0019\u0001\u0006\u0003\u0002$\u0005\u001d\u0002CBA\u0013\u0017y\fy\"D\u0001\t\u0011\u00199X\u00021\u0001\u0002\u001e\u0005aaM]8n'B,7-\u001b4jGR!\u0011\u0011AA\u0017\u0011\u001d\tyC\u0004a\u0001\u0003c\t!!\u001b;\u0011\ty\u000b\u0019D`\u0005\u0004\u0003k\u0019\"\u0001D%uKJ\f'\r\\3P]\u000e,WCAA\u001d!\u001d\tY$!\u0011\u007f\u0003\u0003i!!!\u0010\u000b\u0007\u0005}2#A\u0004nkR\f'\r\\3\n\t\u0005\r\u0013Q\b\u0002\b\u0005VLG\u000eZ3sQ\u001dY\u0011qIA'\u0003\u001f\u00022aGA%\u0013\r\tY%\u0006\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001"
)
public interface GenericParCompanion {
   // $FF: synthetic method
   static ParIterable empty$(final GenericParCompanion $this) {
      return $this.empty();
   }

   default ParIterable empty() {
      return (ParIterable)this.newBuilder().result();
   }

   // $FF: synthetic method
   static ParIterable apply$(final GenericParCompanion $this, final Seq elems) {
      return $this.apply(elems);
   }

   default ParIterable apply(final Seq elems) {
      if (elems.isEmpty()) {
         return this.empty();
      } else {
         Combiner b = this.newBuilder();
         b.$plus$plus$eq(elems);
         return (ParIterable)b.result();
      }
   }

   Combiner newBuilder();

   Combiner newCombiner();

   // $FF: synthetic method
   static Factory toFactory$(final GenericParCompanion $this) {
      return $this.toFactory();
   }

   default Factory toFactory() {
      return GenericParCompanion$.MODULE$.toFactory(this);
   }

   static void $init$(final GenericParCompanion $this) {
   }

   private static class ToFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final GenericParCompanion parFactory;

      public ParIterable fromSpecific(final IterableOnce it) {
         return (ParIterable)((Builder)this.parFactory.newBuilder().$plus$plus$eq(it)).result();
      }

      public Builder newBuilder() {
         return this.parFactory.newBuilder();
      }

      public ToFactory(final GenericParCompanion parFactory) {
         this.parFactory = parFactory;
      }
   }
}
