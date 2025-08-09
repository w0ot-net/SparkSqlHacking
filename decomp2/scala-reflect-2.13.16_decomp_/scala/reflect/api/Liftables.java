package scala.reflect.api;

import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554\u0001BD\b\u0011\u0002\u0007\u0005a#\u001b\u0005\u00067\u0001!\t\u0001\b\u0004\bA\u0001\u0001\n1%\u0001\"\u0011\u0015\u0019#A\"\u0001%\u000f\u0015A\u0004\u0001#\u0001:\r\u0015\u0001\u0003\u0001#\u0001;\u0011\u0015\u0001U\u0001\"\u0001B\u0011\u0015\u0019S\u0001\"\u0001C\r\u001da\u0005\u0001%A\u0012\u00025CQa\u0014\u0005\u0007\u0002A;Q\u0001\u0017\u0001\t\u0002e3Q\u0001\u0014\u0001\t\u0002iCQ\u0001Q\u0006\u0005\u0002yCQaI\u0006\u0005\u0002}\u0013\u0011\u0002T5gi\u0006\u0014G.Z:\u000b\u0005A\t\u0012aA1qS*\u0011!cE\u0001\be\u00164G.Z2u\u0015\u0005!\u0012!B:dC2\f7\u0001A\n\u0003\u0001]\u0001\"\u0001G\r\u000e\u0003MI!AG\n\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0004\u0005\u0002\u0019=%\u0011qd\u0005\u0002\u0005+:LGO\u0001\u0005MS\u001a$\u0018M\u00197f+\t\u0011sf\u0005\u0002\u0003/\u0005)\u0011\r\u001d9msR\u0011Qe\u000b\t\u0003M\u001dj\u0011\u0001A\u0005\u0003Q%\u0012A\u0001\u0016:fK&\u0011!f\u0004\u0002\u0006)J,Wm\u001d\u0005\u0006Y\r\u0001\r!L\u0001\u0006m\u0006dW/\u001a\t\u0003]=b\u0001\u0001B\u00031\u0005\t\u0007\u0011GA\u0001U#\t\u0011T\u0007\u0005\u0002\u0019g%\u0011Ag\u0005\u0002\b\u001d>$\b.\u001b8h!\tAb'\u0003\u00028'\t\u0019\u0011I\\=\u0002\u00111Kg\r^1cY\u0016\u0004\"AJ\u0003\u0014\u0007\u001592\b\u0005\u0002'y%\u0011QH\u0010\u0002\u001a'R\fg\u000eZ1sI2Kg\r^1cY\u0016Len\u001d;b]\u000e,7/\u0003\u0002@\u001f\t\t2\u000b^1oI\u0006\u0014H\rT5gi\u0006\u0014G.Z:\u0002\rqJg.\u001b;?)\u0005ITCA\"G)\t!u\tE\u0002'\u0005\u0015\u0003\"A\f$\u0005\u000bA:!\u0019A\u0019\t\u000b!;\u0001\u0019A%\u0002\u0003\u0019\u0004B\u0001\u0007&FK%\u00111j\u0005\u0002\n\rVt7\r^5p]F\u0012!\"\u00168mS\u001a$\u0018M\u00197f+\tqUk\u0005\u0002\t/\u00059QO\\1qa2LHCA)W!\rA\"\u000bV\u0005\u0003'N\u0011aa\u00149uS>t\u0007C\u0001\u0018V\t\u0015\u0001\u0004B1\u00012\u0011\u00159\u0016\u00021\u0001&\u0003\u0011!(/Z3\u0002\u0015UsG.\u001b4uC\ndW\r\u0005\u0002'\u0017M\u00191bF.\u0011\u0005\u0019b\u0016BA/?\u0005m\u0019F/\u00198eCJ$WK\u001c7jMR\f'\r\\3J]N$\u0018M\\2fgR\t\u0011,\u0006\u0002aGR\u0011\u0011\r\u001a\t\u0004M!\u0011\u0007C\u0001\u0018d\t\u0015\u0001TB1\u00012\u0011\u0015)W\u00021\u0001g\u0003\t\u0001h\r\u0005\u0003\u0019O\u0016\u0012\u0017B\u00015\u0014\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007C\u00016l\u001b\u0005y\u0011B\u00017\u0010\u0005!)f.\u001b<feN,\u0007"
)
public interface Liftables {
   Liftable$ Liftable();

   Unliftable$ Unliftable();

   static void $init$(final Liftables $this) {
   }

   public class Liftable$ implements StandardLiftables.StandardLiftableInstances {
      // $FF: synthetic field
      private final Universe $outer;

      public Liftable liftByte() {
         return StandardLiftables.StandardLiftableInstances.liftByte$(this);
      }

      public Liftable liftShort() {
         return StandardLiftables.StandardLiftableInstances.liftShort$(this);
      }

      public Liftable liftChar() {
         return StandardLiftables.StandardLiftableInstances.liftChar$(this);
      }

      public Liftable liftInt() {
         return StandardLiftables.StandardLiftableInstances.liftInt$(this);
      }

      public Liftable liftLong() {
         return StandardLiftables.StandardLiftableInstances.liftLong$(this);
      }

      public Liftable liftFloat() {
         return StandardLiftables.StandardLiftableInstances.liftFloat$(this);
      }

      public Liftable liftDouble() {
         return StandardLiftables.StandardLiftableInstances.liftDouble$(this);
      }

      public Liftable liftBoolean() {
         return StandardLiftables.StandardLiftableInstances.liftBoolean$(this);
      }

      public Liftable liftUnit() {
         return StandardLiftables.StandardLiftableInstances.liftUnit$(this);
      }

      public Liftable liftString() {
         return StandardLiftables.StandardLiftableInstances.liftString$(this);
      }

      public Liftable liftScalaSymbol() {
         return StandardLiftables.StandardLiftableInstances.liftScalaSymbol$(this);
      }

      public Liftable liftTree() {
         return StandardLiftables.StandardLiftableInstances.liftTree$(this);
      }

      public Liftable liftName() {
         return StandardLiftables.StandardLiftableInstances.liftName$(this);
      }

      public Liftable liftExpr() {
         return StandardLiftables.StandardLiftableInstances.liftExpr$(this);
      }

      public Liftable liftType() {
         return StandardLiftables.StandardLiftableInstances.liftType$(this);
      }

      public Liftable liftTypeTag() {
         return StandardLiftables.StandardLiftableInstances.liftTypeTag$(this);
      }

      public Liftable liftConstant() {
         return StandardLiftables.StandardLiftableInstances.liftConstant$(this);
      }

      public Liftable liftArray(final Liftable evidence$2) {
         return StandardLiftables.StandardLiftableInstances.liftArray$(this, evidence$2);
      }

      public Liftable liftVector(final Liftable evidence$3) {
         return StandardLiftables.StandardLiftableInstances.liftVector$(this, evidence$3);
      }

      public Liftable liftList(final Liftable evidence$4) {
         return StandardLiftables.StandardLiftableInstances.liftList$(this, evidence$4);
      }

      public Liftable liftNil() {
         return StandardLiftables.StandardLiftableInstances.liftNil$(this);
      }

      public Liftable liftMap(final Liftable evidence$5, final Liftable evidence$6) {
         return StandardLiftables.StandardLiftableInstances.liftMap$(this, evidence$5, evidence$6);
      }

      public Liftable liftSet(final Liftable evidence$7) {
         return StandardLiftables.StandardLiftableInstances.liftSet$(this, evidence$7);
      }

      public Liftable liftSome(final Liftable evidence$8) {
         return StandardLiftables.StandardLiftableInstances.liftSome$(this, evidence$8);
      }

      public Liftable liftNone() {
         return StandardLiftables.StandardLiftableInstances.liftNone$(this);
      }

      public Liftable liftOption(final Liftable evidence$9) {
         return StandardLiftables.StandardLiftableInstances.liftOption$(this, evidence$9);
      }

      public Liftable liftLeft(final Liftable evidence$10) {
         return StandardLiftables.StandardLiftableInstances.liftLeft$(this, evidence$10);
      }

      public Liftable liftRight(final Liftable evidence$11) {
         return StandardLiftables.StandardLiftableInstances.liftRight$(this, evidence$11);
      }

      public Liftable liftEither(final Liftable evidence$12, final Liftable evidence$13) {
         return StandardLiftables.StandardLiftableInstances.liftEither$(this, evidence$12, evidence$13);
      }

      public Liftable liftTuple2(final Liftable liftT1, final Liftable liftT2) {
         return StandardLiftables.StandardLiftableInstances.liftTuple2$(this, liftT1, liftT2);
      }

      public Liftable liftTuple3(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3) {
         return StandardLiftables.StandardLiftableInstances.liftTuple3$(this, liftT1, liftT2, liftT3);
      }

      public Liftable liftTuple4(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4) {
         return StandardLiftables.StandardLiftableInstances.liftTuple4$(this, liftT1, liftT2, liftT3, liftT4);
      }

      public Liftable liftTuple5(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5) {
         return StandardLiftables.StandardLiftableInstances.liftTuple5$(this, liftT1, liftT2, liftT3, liftT4, liftT5);
      }

      public Liftable liftTuple6(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6) {
         return StandardLiftables.StandardLiftableInstances.liftTuple6$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6);
      }

      public Liftable liftTuple7(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7) {
         return StandardLiftables.StandardLiftableInstances.liftTuple7$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7);
      }

      public Liftable liftTuple8(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8) {
         return StandardLiftables.StandardLiftableInstances.liftTuple8$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8);
      }

      public Liftable liftTuple9(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9) {
         return StandardLiftables.StandardLiftableInstances.liftTuple9$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9);
      }

      public Liftable liftTuple10(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10) {
         return StandardLiftables.StandardLiftableInstances.liftTuple10$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10);
      }

      public Liftable liftTuple11(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11) {
         return StandardLiftables.StandardLiftableInstances.liftTuple11$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11);
      }

      public Liftable liftTuple12(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12) {
         return StandardLiftables.StandardLiftableInstances.liftTuple12$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12);
      }

      public Liftable liftTuple13(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13) {
         return StandardLiftables.StandardLiftableInstances.liftTuple13$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13);
      }

      public Liftable liftTuple14(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14) {
         return StandardLiftables.StandardLiftableInstances.liftTuple14$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14);
      }

      public Liftable liftTuple15(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15) {
         return StandardLiftables.StandardLiftableInstances.liftTuple15$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15);
      }

      public Liftable liftTuple16(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16) {
         return StandardLiftables.StandardLiftableInstances.liftTuple16$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16);
      }

      public Liftable liftTuple17(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17) {
         return StandardLiftables.StandardLiftableInstances.liftTuple17$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17);
      }

      public Liftable liftTuple18(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17, final Liftable liftT18) {
         return StandardLiftables.StandardLiftableInstances.liftTuple18$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18);
      }

      public Liftable liftTuple19(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17, final Liftable liftT18, final Liftable liftT19) {
         return StandardLiftables.StandardLiftableInstances.liftTuple19$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19);
      }

      public Liftable liftTuple20(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17, final Liftable liftT18, final Liftable liftT19, final Liftable liftT20) {
         return StandardLiftables.StandardLiftableInstances.liftTuple20$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20);
      }

      public Liftable liftTuple21(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17, final Liftable liftT18, final Liftable liftT19, final Liftable liftT20, final Liftable liftT21) {
         return StandardLiftables.StandardLiftableInstances.liftTuple21$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20, liftT21);
      }

      public Liftable liftTuple22(final Liftable liftT1, final Liftable liftT2, final Liftable liftT3, final Liftable liftT4, final Liftable liftT5, final Liftable liftT6, final Liftable liftT7, final Liftable liftT8, final Liftable liftT9, final Liftable liftT10, final Liftable liftT11, final Liftable liftT12, final Liftable liftT13, final Liftable liftT14, final Liftable liftT15, final Liftable liftT16, final Liftable liftT17, final Liftable liftT18, final Liftable liftT19, final Liftable liftT20, final Liftable liftT21, final Liftable liftT22) {
         return StandardLiftables.StandardLiftableInstances.liftTuple22$(this, liftT1, liftT2, liftT3, liftT4, liftT5, liftT6, liftT7, liftT8, liftT9, liftT10, liftT11, liftT12, liftT13, liftT14, liftT15, liftT16, liftT17, liftT18, liftT19, liftT20, liftT21, liftT22);
      }

      public Liftable apply(final Function1 f) {
         return null.new Liftable(f) {
            private final Function1 f$1;

            public Trees.TreeApi apply(final Object value) {
               return (Trees.TreeApi)this.f$1.apply(value);
            }

            public {
               this.f$1 = f$1;
            }
         };
      }

      // $FF: synthetic method
      public StandardLiftables scala$reflect$api$StandardLiftables$StandardLiftableInstances$$$outer() {
         return this.$outer;
      }

      public Liftable$() {
         if (Liftables.this == null) {
            throw null;
         } else {
            this.$outer = Liftables.this;
            super();
         }
      }
   }

   public class Unliftable$ implements StandardLiftables.StandardUnliftableInstances {
      // $FF: synthetic field
      private final Universe $outer;

      public Unliftable unliftByte() {
         return StandardLiftables.StandardUnliftableInstances.unliftByte$(this);
      }

      public Unliftable unliftShort() {
         return StandardLiftables.StandardUnliftableInstances.unliftShort$(this);
      }

      public Unliftable unliftChar() {
         return StandardLiftables.StandardUnliftableInstances.unliftChar$(this);
      }

      public Unliftable unliftInt() {
         return StandardLiftables.StandardUnliftableInstances.unliftInt$(this);
      }

      public Unliftable unliftLong() {
         return StandardLiftables.StandardUnliftableInstances.unliftLong$(this);
      }

      public Unliftable unliftFloat() {
         return StandardLiftables.StandardUnliftableInstances.unliftFloat$(this);
      }

      public Unliftable unliftDouble() {
         return StandardLiftables.StandardUnliftableInstances.unliftDouble$(this);
      }

      public Unliftable unliftBoolean() {
         return StandardLiftables.StandardUnliftableInstances.unliftBoolean$(this);
      }

      public Unliftable unliftUnit() {
         return StandardLiftables.StandardUnliftableInstances.unliftUnit$(this);
      }

      public Unliftable unliftString() {
         return StandardLiftables.StandardUnliftableInstances.unliftString$(this);
      }

      public Unliftable unliftScalaSymbol() {
         return StandardLiftables.StandardUnliftableInstances.unliftScalaSymbol$(this);
      }

      public Unliftable unliftName(final ClassTag evidence$16) {
         return StandardLiftables.StandardUnliftableInstances.unliftName$(this, evidence$16);
      }

      public Unliftable unliftType() {
         return StandardLiftables.StandardUnliftableInstances.unliftType$(this);
      }

      public Unliftable unliftConstant() {
         return StandardLiftables.StandardUnliftableInstances.unliftConstant$(this);
      }

      public Unliftable unliftTuple2(final Unliftable UnliftT1, final Unliftable UnliftT2) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple2$(this, UnliftT1, UnliftT2);
      }

      public Unliftable unliftTuple3(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple3$(this, UnliftT1, UnliftT2, UnliftT3);
      }

      public Unliftable unliftTuple4(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple4$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4);
      }

      public Unliftable unliftTuple5(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple5$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5);
      }

      public Unliftable unliftTuple6(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple6$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6);
      }

      public Unliftable unliftTuple7(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple7$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7);
      }

      public Unliftable unliftTuple8(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple8$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8);
      }

      public Unliftable unliftTuple9(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple9$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9);
      }

      public Unliftable unliftTuple10(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple10$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10);
      }

      public Unliftable unliftTuple11(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple11$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11);
      }

      public Unliftable unliftTuple12(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple12$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12);
      }

      public Unliftable unliftTuple13(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple13$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13);
      }

      public Unliftable unliftTuple14(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple14$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14);
      }

      public Unliftable unliftTuple15(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple15$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15);
      }

      public Unliftable unliftTuple16(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple16$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16);
      }

      public Unliftable unliftTuple17(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple17$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17);
      }

      public Unliftable unliftTuple18(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17, final Unliftable UnliftT18) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple18$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18);
      }

      public Unliftable unliftTuple19(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17, final Unliftable UnliftT18, final Unliftable UnliftT19) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple19$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19);
      }

      public Unliftable unliftTuple20(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17, final Unliftable UnliftT18, final Unliftable UnliftT19, final Unliftable UnliftT20) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple20$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20);
      }

      public Unliftable unliftTuple21(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17, final Unliftable UnliftT18, final Unliftable UnliftT19, final Unliftable UnliftT20, final Unliftable UnliftT21) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple21$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20, UnliftT21);
      }

      public Unliftable unliftTuple22(final Unliftable UnliftT1, final Unliftable UnliftT2, final Unliftable UnliftT3, final Unliftable UnliftT4, final Unliftable UnliftT5, final Unliftable UnliftT6, final Unliftable UnliftT7, final Unliftable UnliftT8, final Unliftable UnliftT9, final Unliftable UnliftT10, final Unliftable UnliftT11, final Unliftable UnliftT12, final Unliftable UnliftT13, final Unliftable UnliftT14, final Unliftable UnliftT15, final Unliftable UnliftT16, final Unliftable UnliftT17, final Unliftable UnliftT18, final Unliftable UnliftT19, final Unliftable UnliftT20, final Unliftable UnliftT21, final Unliftable UnliftT22) {
         return StandardLiftables.StandardUnliftableInstances.unliftTuple22$(this, UnliftT1, UnliftT2, UnliftT3, UnliftT4, UnliftT5, UnliftT6, UnliftT7, UnliftT8, UnliftT9, UnliftT10, UnliftT11, UnliftT12, UnliftT13, UnliftT14, UnliftT15, UnliftT16, UnliftT17, UnliftT18, UnliftT19, UnliftT20, UnliftT21, UnliftT22);
      }

      public Unliftable apply(final PartialFunction pf) {
         return null.new Unliftable(pf) {
            private final PartialFunction pf$1;

            public Option unapply(final Trees.TreeApi value) {
               return (Option)this.pf$1.lift().apply(value);
            }

            public {
               this.pf$1 = pf$1;
            }
         };
      }

      // $FF: synthetic method
      public StandardLiftables scala$reflect$api$StandardLiftables$StandardUnliftableInstances$$$outer() {
         return this.$outer;
      }

      public Unliftable$() {
         if (Liftables.this == null) {
            throw null;
         } else {
            this.$outer = Liftables.this;
            super();
         }
      }
   }

   public interface Liftable {
      Trees.TreeApi apply(final Object value);
   }

   public interface Unliftable {
      Option unapply(final Trees.TreeApi tree);
   }
}
