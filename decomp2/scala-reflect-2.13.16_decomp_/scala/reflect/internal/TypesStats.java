package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005%4\u0001\u0002G\r\u0011\u0002\u0007\u0005\u0001%\u0018\u0005\u0006K\u0001!\tA\n\u0005\bU\u0001\u0011\r\u0011\"\u0001,\u0011\u001d!\u0004A1A\u0005\u0002UBq!\u000f\u0001C\u0002\u0013\u0005Q\u0007C\u0004;\u0001\t\u0007I\u0011A\u001b\t\u000fm\u0002!\u0019!C\u0001k!9A\b\u0001b\u0001\n\u0003)\u0004bB\u001f\u0001\u0005\u0004%\t!\u000e\u0005\b}\u0001\u0011\r\u0011\"\u00016\u0011\u001dy\u0004A1A\u0005\u0002\u0001Cq\u0001\u0012\u0001C\u0002\u0013\u0005\u0001\tC\u0004F\u0001\t\u0007I\u0011\u0001$\t\u000f)\u0003!\u0019!C\u0001\u0017\"9q\n\u0001b\u0001\n\u0003Y\u0005b\u0002)\u0001\u0005\u0004%\ta\u0013\u0005\b#\u0002\u0011\r\u0011\"\u0001L\u0011\u001d\u0011\u0006A1A\u0005\u0002-Cqa\u0015\u0001C\u0002\u0013\u00051\nC\u0004U\u0001\t\u0007I\u0011A&\t\u000fU\u0003!\u0019!C\u0001\u0001\"9a\u000b\u0001b\u0001\n\u0003\u0001\u0005bB,\u0001\u0005\u0004%\t\u0001\u0011\u0005\b1\u0002\u0011\r\u0011\"\u0001Z\u0005)!\u0016\u0010]3t'R\fGo\u001d\u0006\u00035m\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00039u\tqA]3gY\u0016\u001cGOC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\u0011\u0011\u0005\t\u001aS\"A\u000f\n\u0005\u0011j\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002OA\u0011!\u0005K\u0005\u0003Su\u0011A!\u00168ji\u0006yQO\\5rk\u0016$\u0016\u0010]3t-&,w/F\u0001-!\tic&D\u0001\u0001\u0013\ty\u0003G\u0001\u0003WS\u0016<\u0018BA\u00193\u0005)\u0019F/\u0019;jgRL7m\u001d\u0006\u0003ge\tA!\u001e;jY\u0006a!/Y<UsB,7i\\;oiV\ta\u0007\u0005\u0002.o%\u0011\u0001\b\r\u0002\b\u0007>,h\u000e^3s\u00031\u0019XO\u0019;za\u0016\u001cu.\u001e8u\u00035\u0019\u0018-\\3usB,7i\\;oi\u0006AA.\u001e2D_VtG/\u0001\boKN$X\r\u001a'vE\u000e{WO\u001c;\u0002\u001f\u0019Lg\u000eZ'f[\n,'oQ8v]R\f\u0001CZ5oI6+WNY3sg\u000e{WO\u001c;\u0002\u001b9|W*Z7cKJ\u001cu.\u001e8u+\u0005\t\u0005CA\u0017C\u0013\t\u0019\u0005G\u0001\u0006Tk\n\u001cu.\u001e8uKJ\fq\"\\;mi6+WNY3s\u0007>,h\u000e^\u0001\u000bif\u0004XM\u001d(b]>\u001cX#A$\u0011\u00055B\u0015BA%1\u0005\u0015!\u0016.\\3s\u0003!aWO\u0019(b]>\u001cX#\u0001'\u0011\u00055j\u0015B\u0001(1\u00059\u0019F/Y2lC\ndW\rV5nKJ\fAb];cif\u0004XMT1o_N\fqBZ5oI6+WNY3s\u001d\u0006twn]\u0001\u0011M&tG-T3nE\u0016\u00148OT1o_N\fq\"Y:TK\u0016tgI]8n\u001d\u0006twn]\u0001\u0011E\u0006\u001cX\rV=qKN+\u0017OT1o_N\f\u0001CY1tK\u000ec\u0017m]:fg:\u000bgn\\:\u00021\r|W\u000e]8v]\u0012\u0014\u0015m]3UsB,7+Z9D_VtG/A\fusB,'/\u001a4CCN,G+\u001f9f'\u0016\f8i\\;oi\u0006I2/\u001b8hY\u0016$xN\u001c\"bg\u0016$\u0016\u0010]3TKF\u001cu.\u001e8u\u00031!\u0018\u0010]3PaN\u001cF/Y2l+\u0005Q\u0006CA\u0017\\\u0013\ta\u0006G\u0001\u0006US6,'o\u0015;bG.\u00142A\u00181c\r\u0011y\u0006\u0001A/\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0005\u0005\u0004Q\"A\r\u0013\u0007\r$wM\u0002\u0003`\u0001\u0001\u0011\u0007CA1f\u0013\t1\u0017DA\tCCN,G+\u001f9f'\u0016\f8o\u0015;biN\u0004\"\u0001\u001b\u0019\u000e\u0003I\u0002"
)
public interface TypesStats {
   void scala$reflect$internal$TypesStats$_setter_$uniqueTypesView_$eq(final Statistics.View x$1);

   void scala$reflect$internal$TypesStats$_setter_$rawTypeCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$subtypeCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$sametypeCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$lubCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$nestedLubCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$findMemberCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$findMembersCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$TypesStats$_setter_$noMemberCount_$eq(final Statistics.SubCounter x$1);

   void scala$reflect$internal$TypesStats$_setter_$multMemberCount_$eq(final Statistics.SubCounter x$1);

   void scala$reflect$internal$TypesStats$_setter_$typerNanos_$eq(final Statistics.Timer x$1);

   void scala$reflect$internal$TypesStats$_setter_$lubNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$subtypeNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$findMemberNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$findMembersNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$asSeenFromNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$baseTypeSeqNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$baseClassesNanos_$eq(final Statistics.StackableTimer x$1);

   void scala$reflect$internal$TypesStats$_setter_$compoundBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1);

   void scala$reflect$internal$TypesStats$_setter_$typerefBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1);

   void scala$reflect$internal$TypesStats$_setter_$singletonBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1);

   void scala$reflect$internal$TypesStats$_setter_$typeOpsStack_$eq(final Statistics.TimerStack x$1);

   Statistics.View uniqueTypesView();

   Statistics.Counter rawTypeCount();

   Statistics.Counter subtypeCount();

   Statistics.Counter sametypeCount();

   Statistics.Counter lubCount();

   Statistics.Counter nestedLubCount();

   Statistics.Counter findMemberCount();

   Statistics.Counter findMembersCount();

   Statistics.SubCounter noMemberCount();

   Statistics.SubCounter multMemberCount();

   Statistics.Timer typerNanos();

   Statistics.StackableTimer lubNanos();

   Statistics.StackableTimer subtypeNanos();

   Statistics.StackableTimer findMemberNanos();

   Statistics.StackableTimer findMembersNanos();

   Statistics.StackableTimer asSeenFromNanos();

   Statistics.StackableTimer baseTypeSeqNanos();

   Statistics.StackableTimer baseClassesNanos();

   Statistics.SubCounter compoundBaseTypeSeqCount();

   Statistics.SubCounter typerefBaseTypeSeqCount();

   Statistics.SubCounter singletonBaseTypeSeqCount();

   Statistics.TimerStack typeOpsStack();

   static void $init$(final TypesStats $this) {
      $this.scala$reflect$internal$TypesStats$_setter_$uniqueTypesView_$eq(((Statistics)$this).newView("#unique types", .MODULE$, (JFunction0.mcI.sp)() -> {
         SymbolTable var10000 = ((Statistics)$this).symbolTable();
         if (var10000 == null) {
            throw null;
         } else {
            return Types.howManyUniqueTypes$(var10000);
         }
      }));
      $this.scala$reflect$internal$TypesStats$_setter_$rawTypeCount_$eq(((Statistics)$this).newCounter("#raw type creations", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$subtypeCount_$eq(((Statistics)$this).newCounter("#subtype ops", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$sametypeCount_$eq(((Statistics)$this).newCounter("#sametype ops", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$lubCount_$eq(((Statistics)$this).newCounter("#toplevel lubs/glbs", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$nestedLubCount_$eq(((Statistics)$this).newCounter("#all lubs/glbs", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$findMemberCount_$eq(((Statistics)$this).newCounter("#findMember ops", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$findMembersCount_$eq(((Statistics)$this).newCounter("#findMembers ops", .MODULE$));
      $this.scala$reflect$internal$TypesStats$_setter_$noMemberCount_$eq(((Statistics)$this).newSubCounter("  of which not found", $this.findMemberCount()));
      $this.scala$reflect$internal$TypesStats$_setter_$multMemberCount_$eq(((Statistics)$this).newSubCounter("  of which multiple overloaded", $this.findMemberCount()));
      $this.scala$reflect$internal$TypesStats$_setter_$typerNanos_$eq(((Statistics)$this).newTimer("time spent typechecking", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new String[]{"typer"})));
      $this.scala$reflect$internal$TypesStats$_setter_$lubNanos_$eq(((Statistics)$this).newStackableTimer("time spent in lubs", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$subtypeNanos_$eq(((Statistics)$this).newStackableTimer("time spent in <:<", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$findMemberNanos_$eq(((Statistics)$this).newStackableTimer("time spent in findmember", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$findMembersNanos_$eq(((Statistics)$this).newStackableTimer("time spent in findmembers", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$asSeenFromNanos_$eq(((Statistics)$this).newStackableTimer("time spent in asSeenFrom", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$baseTypeSeqNanos_$eq(((Statistics)$this).newStackableTimer("time spent in baseTypeSeq", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$baseClassesNanos_$eq(((Statistics)$this).newStackableTimer("time spent in baseClasses", $this.typerNanos()));
      $this.scala$reflect$internal$TypesStats$_setter_$compoundBaseTypeSeqCount_$eq(((Statistics)$this).newSubCounter("  of which for compound types", ((BaseTypeSeqsStats)$this).baseTypeSeqCount()));
      $this.scala$reflect$internal$TypesStats$_setter_$typerefBaseTypeSeqCount_$eq(((Statistics)$this).newSubCounter("  of which for typerefs", ((BaseTypeSeqsStats)$this).baseTypeSeqCount()));
      $this.scala$reflect$internal$TypesStats$_setter_$singletonBaseTypeSeqCount_$eq(((Statistics)$this).newSubCounter("  of which for singletons", ((BaseTypeSeqsStats)$this).baseTypeSeqCount()));
      $this.scala$reflect$internal$TypesStats$_setter_$typeOpsStack_$eq(((Statistics)$this).newTimerStack());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
