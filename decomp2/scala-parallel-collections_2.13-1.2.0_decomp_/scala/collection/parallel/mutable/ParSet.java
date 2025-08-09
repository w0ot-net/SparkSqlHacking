package scala.collection.parallel.mutable;

import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4q\u0001D\u0007\u0011\u0002\u0007\u0005a\u0003C\u0003?\u0001\u0011\u0005q\bC\u0003D\u0001\u0011\u0005C\tC\u0003I\u0001\u0011\u0005\u0013\nC\u0003N\u0001\u0011\u0005c\nC\u0003P\u0001\u0019\u0005\u0001kB\u0003R\u001b!\u0005!KB\u0003\r\u001b!\u00051\u000bC\u0003X\u000f\u0011\u0005\u0001\fC\u0003Z\u000f\u0011\r!\fC\u0003g\u000f\u0011\u0005s\rC\u0003p\u000f\u0011\u0005\u0003O\u0001\u0004QCJ\u001cV\r\u001e\u0006\u0003\u001d=\tq!\\;uC\ndWM\u0003\u0002\u0011#\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0013'\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003Q\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u0018EM1\u0001\u0001\u0007\u000f,]U\u0002\"!\u0007\u000e\u000e\u0003MI!aG\n\u0003\r\u0005s\u0017PU3g!\rib\u0004I\u0007\u0002\u001b%\u0011q$\u0004\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#!\u0001+\u0012\u0005\u0015B\u0003CA\r'\u0013\t93CA\u0004O_RD\u0017N\\4\u0011\u0005eI\u0013B\u0001\u0016\u0014\u0005\r\te.\u001f\t\u0004Y5\u0002S\"A\b\n\u00051y\u0001\u0003B\u00183AQj\u0011\u0001\r\u0006\u0003cE\tqaZ3oKJL7-\u0003\u00024a\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f!\ti\u0002\u0001\u0005\u0004\u001em\u0001\"\u0004(O\u0005\u0003o5\u0011!\u0002U1s'\u0016$H*[6f!\ri\u0002\u0001\t\t\u0004uq\u0002S\"A\u001e\u000b\u00059\t\u0012BA\u001f<\u0005\r\u0019V\r^\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0001\u0003\"!G!\n\u0005\t\u001b\"\u0001B+oSR\f\u0011b\u001b8po:\u001c\u0016N_3\u0016\u0003\u0015\u0003\"!\u0007$\n\u0005\u001d\u001b\"aA%oi\u0006I1m\\7qC:LwN\\\u000b\u0002\u0015B\u0019qf\u0013\u001b\n\u00051\u0003$aE$f]\u0016\u0014\u0018n\u0019)be\u000e{W\u000e]1oS>t\u0017!B3naRLX#\u0001\u001d\u0002\u0007M,\u0017/F\u0001:\u0003\u0019\u0001\u0016M]*fiB\u0011QdB\n\u0003\u000fQ\u00032aL+5\u0013\t1\u0006GA\u0007QCJ\u001cV\r\u001e$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003I\u000bAbY1o\u0005VLG\u000e\u001a$s_6,2aW1e+\u0005a\u0006#B\u0018^?\u000e,\u0017B\u000101\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u00042!\b\u0001a!\t\t\u0013\rB\u0003c\u0013\t\u0007AEA\u0001T!\t\tC\rB\u0003$\u0013\t\u0007A\u0005E\u0002\u001e\u0001\r\f!B\\3x\u0005VLG\u000eZ3s+\tAW.F\u0001j!\u0011a#\u000e\u001c8\n\u0005-|!\u0001C\"p[\nLg.\u001a:\u0011\u0005\u0005jG!B\u0012\u000b\u0005\u0004!\u0003cA\u000f\u0001Y\u0006Ya.Z<D_6\u0014\u0017N\\3s+\t\tH/F\u0001s!\u0011a#n];\u0011\u0005\u0005\"H!B\u0012\f\u0005\u0004!\u0003cA\u000f\u0001g\u0002"
)
public interface ParSet extends ParIterable, scala.collection.parallel.ParSet, ParSetLike {
   static CanCombineFrom canBuildFrom() {
      return ParSet$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParSet$.MODULE$.toFactory();
   }

   // $FF: synthetic method
   static int knownSize$(final ParSet $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return -1;
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParSet $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParSet$.MODULE$;
   }

   // $FF: synthetic method
   static ParSet empty$(final ParSet $this) {
      return $this.empty();
   }

   default ParSet empty() {
      return (ParSet)ParHashSet$.MODULE$.apply(.MODULE$);
   }

   Set seq();

   static void $init$(final ParSet $this) {
   }
}
