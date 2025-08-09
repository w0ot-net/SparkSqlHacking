package scala.reflect.internal;

import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichChar;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t5f!C!C!\u0003\r\t!\u0013BS\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u001dA\u0006A1A\u0005\u0006eCq\u0001\u0018\u0001C\u0002\u0013\u0015Q\fC\u0004a\u0001\t\u0007IQA1\t\u000f\u0011\u0004!\u0019!C\u0003K\"9\u0001\u000e\u0001b\u0001\n\u000bI\u0007b\u00027\u0001\u0005\u0004%)!\u001c\u0005\ba\u0002\u0011\r\u0011\"\u0002r\u0011\u001d!\bA1A\u0005\u0006UDq\u0001\u001f\u0001C\u0002\u0013\u0015\u0011\u0010C\u0004}\u0001\t\u0007IQA?\t\u0013\u0005\u0005\u0001A1A\u0005\u0006\u0005\r\u0001\"CA\u0005\u0001\t\u0007IQAA\u0006\u0011%\t\t\u0002\u0001b\u0001\n\u000b\t\u0019\u0002C\u0005\u0002\u001a\u0001\u0011\r\u0011\"\u0002\u0002\u001c\u00191\u0011\u0011\u0005\u0001A\u0003GA!\"a\u0013\u0011\u0005+\u0007I\u0011AA'\u0011)\t)\u0006\u0005B\tB\u0003%\u0011q\n\u0005\b\u0003/\u0002B\u0011AA-\u0011%\ty\u0006\u0005b\u0001\n\u0003\t\t\u0007\u0003\u0005\u0002jA\u0001\u000b\u0011BA2\u0011\u001d\tY\u0007\u0005C\u0001\u0003[Bq!!\u001e\u0011\t\u0003\ti\u0007C\u0004\u0002xA!\t!!\u001c\t\u000f\u0005e\u0004\u0003\"\u0001\u0002n!9\u00111\u0010\t\u0005\u0002\u00055\u0004bBA?!\u0011\u0005\u0011Q\u000e\u0005\b\u0003\u007f\u0002B\u0011AA7\u0011\u001d\t\t\t\u0005C\u0001\u0003[Bq!a!\u0011\t\u0003\ti\u0007C\u0004\u0002\u0006B!\t!!\u001c\t\u000f\u0005\u001d\u0005\u0003\"\u0001\u0002n!9\u0011\u0011\u0012\t\u0005\u0002\u0005-\u0005bBAL!\u0011\u0005\u0013\u0011\u0014\u0005\b\u0003?\u0003B\u0011AA7\u0011\u001d\t\t\u000b\u0005C\u0001\u0003[Bq!a)\u0011\t\u0003\t)\u000bC\u0004\u0002.B!\t!a,\t\u000f\u0005]\u0006\u0003\"\u0001\u0002:\"9\u0011\u0011\u0019\t\u0005\u0002\u0005\u0005\u0004bBAb!\u0011\u0005\u0011Q\u0019\u0005\b\u0003\u001b\u0004B\u0011AAh\u0011\u001d\t9\u000e\u0005C\u0001\u00033Dq!!9\u0011\t\u0003\t\u0019\u000fC\u0004\u0002jB!\t!a;\t\u000f\u0005u\b\u0003\"\u0001\u0002l\"9\u0011q \t\u0005\u0002\u0005-\u0005b\u0002B\u0001!\u0011\u0005!1\u0001\u0005\b\u0005\u001f\u0001B\u0011\tB\t\u0011%\u0011\u0019\u0002EA\u0001\n\u0003\u0011)\u0002C\u0005\u0003\u001aA\t\n\u0011\"\u0001\u0003\u001c!I!\u0011\u0007\t\u0002\u0002\u0013\u0005#1\u0007\u0005\n\u0005\u0007\u0002\u0012\u0011!C\u0001\u0003CB\u0011B!\u0012\u0011\u0003\u0003%\tAa\u0012\t\u0013\t5\u0003#!A\u0005B\t=\u0003\"\u0003B/!\u0005\u0005I\u0011\u0001B0\u0011%\u0011\u0019\u0007EA\u0001\n\u0003\u0012)\u0007C\u0005\u0003jA\t\t\u0011\"\u0011\u0003l\u001d9!Q\u000e\u0001\t\u0002\t=daBA\u0011\u0001!\u0005!\u0011\u000f\u0005\b\u0003/bD\u0011\u0001BB\u0011%\u0011)\tPA\u0001\n\u0003\u00139\tC\u0005\u0003\fr\n\t\u0011\"!\u0003\u000e\"I!\u0011\u0014\u0001C\u0002\u0013\r!1\u0014\u0002\n\u0007>t7\u000f^1oiNT!a\u0011#\u0002\u0011%tG/\u001a:oC2T!!\u0012$\u0002\u000fI,g\r\\3di*\tq)A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qe\n\u0005\u0002L\u00196\ta)\u0003\u0002N\r\n1\u0011I\\=SK\u001a\u0004\"a\u0014*\u000e\u0003AS!!\u0015#\u0002\u0007\u0005\u0004\u0018.\u0003\u0002B!\u00061A%\u001b8ji\u0012\"\u0012!\u0016\t\u0003\u0017ZK!a\u0016$\u0003\tUs\u0017\u000e^\u0001\u0006\u001d>$\u0016mZ\u000b\u00025>\t1,H\u0001\u0001\u0003\u001d)f.\u001b;UC\u001e,\u0012AX\b\u0002?v\t\u0011!\u0001\u0006C_>dW-\u00198UC\u001e,\u0012AY\b\u0002Gv\t!!A\u0004CsR,G+Y4\u0016\u0003\u0019|\u0011aZ\u000f\u0002\u0007\u0005A1\u000b[8siR\u000bw-F\u0001k\u001f\u0005YW$\u0001\u0003\u0002\u000f\rC\u0017M\u001d+bOV\tanD\u0001p;\u0005)\u0011AB%oiR\u000bw-F\u0001s\u001f\u0005\u0019X$\u0001\u0004\u0002\u000f1{gn\u001a+bOV\taoD\u0001x;\u00059\u0011\u0001\u0003$m_\u0006$H+Y4\u0016\u0003i|\u0011a_\u000f\u0002\u0011\u0005IAi\\;cY\u0016$\u0016mZ\u000b\u0002}>\tq0H\u0001\n\u0003%\u0019FO]5oOR\u000bw-\u0006\u0002\u0002\u0006=\u0011\u0011qA\u000f\u0002\u0015\u00059a*\u001e7m)\u0006<WCAA\u0007\u001f\t\ty!H\u0001\f\u0003!\u0019E.\u0019>{)\u0006<WCAA\u000b\u001f\t\t9\"H\u0001\r\u0003\u001d)e.^7UC\u001e,\"!!\b\u0010\u0005\u0005}Q$A\u0007\u0003\u0011\r{gn\u001d;b]R\u001cr\u0001EA\u0013\u0003[\t\u0019\u0004\u0005\u0003\u0002(\u0005%R\"\u0001\u0001\n\u0007\u0005-\"KA\u0006D_:\u001cH/\u00198u\u0003BL\u0007cA&\u00020%\u0019\u0011\u0011\u0007$\u0003\u000fA\u0013x\u000eZ;diB!\u0011QGA#\u001d\u0011\t9$!\u0011\u000f\t\u0005e\u0012qH\u0007\u0003\u0003wQ1!!\u0010I\u0003\u0019a$o\\8u}%\tq)C\u0002\u0002D\u0019\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002H\u0005%#\u0001D*fe&\fG.\u001b>bE2,'bAA\"\r\u0006)a/\u00197vKV\u0011\u0011q\n\t\u0004\u0017\u0006E\u0013bAA*\r\n\u0019\u0011I\\=\u0002\rY\fG.^3!\u0003\u0019a\u0014N\\5u}Q!\u00111LA/!\r\t9\u0003\u0005\u0005\b\u0003\u0017\u001a\u0002\u0019AA(\u0003\r!\u0018mZ\u000b\u0003\u0003G\u00022aSA3\u0013\r\t9G\u0012\u0002\u0004\u0013:$\u0018\u0001\u0002;bO\u0002\n1\"[:CsR,'+\u00198hKV\u0011\u0011q\u000e\t\u0004\u0017\u0006E\u0014bAA:\r\n9!i\\8mK\u0006t\u0017\u0001D5t'\"|'\u000f\u001e*b]\u001e,\u0017aC5t\u0007\"\f'OU1oO\u0016\f!\"[:J]R\u0014\u0016M\\4f\u0003-I7\u000fT8oOJ\u000bgnZ3\u0002)%\u001ch\t\\8biJ+\u0007O]3tK:$\u0018M\u00197f\u0003UI7\u000fR8vE2,'+\u001a9sKN,g\u000e^1cY\u0016\f\u0011\"[:Ok6,'/[2\u0002\u001f%\u001chj\u001c8V]&$\u0018I\\=WC2\fQ#[:Tk&$\u0018M\u00197f\u0019&$XM]1m)f\u0004X-\u0001\u0005jg\u0006s\u0017PV1m\u0003\r!\b/Z\u000b\u0003\u0003\u001b\u0003B!a\n\u0002\u0010&!\u0011\u0011SAJ\u0005\u0011!\u0016\u0010]3\n\u0007\u0005U%IA\u0003UsB,7/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003_\nY\nC\u0004\u0002\u001e\n\u0002\r!a\u0014\u0002\u000b=$\b.\u001a:\u0002\u000b%\u001ch*\u0019(\u0002\u0019\t|w\u000e\\3b]Z\u000bG.^3\u0002\u0013\tLH/\u001a,bYV,WCAAT!\rY\u0015\u0011V\u0005\u0004\u0003W3%\u0001\u0002\"zi\u0016\f!b\u001d5peR4\u0016\r\\;f+\t\t\t\fE\u0002L\u0003gK1!!.G\u0005\u0015\u0019\u0006n\u001c:u\u0003%\u0019\u0007.\u0019:WC2,X-\u0006\u0002\u0002<B\u00191*!0\n\u0007\u0005}fI\u0001\u0003DQ\u0006\u0014\u0018\u0001C5oiZ\u000bG.^3\u0002\u00131|gn\u001a,bYV,WCAAd!\rY\u0015\u0011Z\u0005\u0004\u0003\u00174%\u0001\u0002'p]\u001e\f!B\u001a7pCR4\u0016\r\\;f+\t\t\t\u000eE\u0002L\u0003'L1!!6G\u0005\u00151En\\1u\u0003-!w.\u001e2mKZ\u000bG.^3\u0016\u0005\u0005m\u0007cA&\u0002^&\u0019\u0011q\u001c$\u0003\r\u0011{WO\u00197f\u0003%\u0019wN\u001c<feR$v\u000e\u0006\u0003\u0002\\\u0005\u0015\bbBAtY\u0001\u0007\u0011QR\u0001\u0003aR\f1b\u001d;sS:<g+\u00197vKV\u0011\u0011Q\u001e\t\u0005\u0003_\f9P\u0004\u0003\u0002r\u0006M\bcAA\u001d\r&\u0019\u0011Q\u001f$\u0002\rA\u0013X\rZ3g\u0013\u0011\tI0a?\u0003\rM#(/\u001b8h\u0015\r\t)PR\u0001\u0013KN\u001c\u0017\r]3e'R\u0014\u0018N\\4WC2,X-A\u0005usB,g+\u00197vK\u0006Y1/_7c_24\u0016\r\\;f+\t\u0011)\u0001\u0005\u0003\u0002(\t\u001d\u0011\u0002\u0002B\u0005\u0005\u0017\u0011aaU=nE>d\u0017b\u0001B\u0007\u0005\n91+_7c_2\u001c\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\r\u0014\u0001B2paf$B!a\u0017\u0003\u0018!I\u00111\n\u001a\u0011\u0002\u0003\u0007\u0011qJ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011iB\u000b\u0003\u0002P\t}1F\u0001B\u0011!\u0011\u0011\u0019C!\f\u000e\u0005\t\u0015\"\u0002\u0002B\u0014\u0005S\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t-b)\u0001\u0006b]:|G/\u0019;j_:LAAa\f\u0003&\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011)\u0004\u0005\u0003\u00038\t\u0005SB\u0001B\u001d\u0015\u0011\u0011YD!\u0010\u0002\t1\fgn\u001a\u0006\u0003\u0005\u007f\tAA[1wC&!\u0011\u0011 B\u001d\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0014\u0003J!I!1\n\u001c\u0002\u0002\u0003\u0007\u00111M\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\tE\u0003C\u0002B*\u00053\ny%\u0004\u0002\u0003V)\u0019!q\u000b$\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003\\\tU#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u001c\u0003b!I!1\n\u001d\u0002\u0002\u0003\u0007\u0011qJ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u00036\t\u001d\u0004\"\u0003B&s\u0005\u0005\t\u0019AA2\u0003!!xn\u0015;sS:<GC\u0001B\u001b\u0003!\u0019uN\\:uC:$\bcAA\u0014yM)AHa\u001d\u0003zA!\u0011q\u0005B;\u0013\r\u00119H\u0015\u0002\u0012\u0007>t7\u000f^1oi\u0016CHO]1di>\u0014\b\u0003\u0002B>\u0005\u0003k!A! \u000b\t\t}$QH\u0001\u0003S>LA!a\u0012\u0003~Q\u0011!qN\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u00037\u0012I\tC\u0004\u0002Ly\u0002\r!a\u0014\u0002\u000fUt\u0017\r\u001d9msR!!q\u0012BK!\u0015Y%\u0011SA(\u0013\r\u0011\u0019J\u0012\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\t]u(!AA\u0002\u0005m\u0013a\u0001=%a\u0005Y1i\u001c8ti\u0006tG\u000fV1h+\t\u0011i\n\u0005\u0004\u0003 \n\u0005\u00161L\u0007\u0002\t&\u0019!1\u0015#\u0003\u0011\rc\u0017m]:UC\u001e\u0004BAa*\u0003*6\t!)C\u0002\u0003,\n\u00131bU=nE>dG+\u00192mK\u0002"
)
public interface Constants extends scala.reflect.api.Constants {
   Constant$ Constant();

   void scala$reflect$internal$Constants$_setter_$ConstantTag_$eq(final ClassTag x$1);

   // $FF: synthetic method
   static int NoTag$(final Constants $this) {
      return $this.NoTag();
   }

   default int NoTag() {
      return 0;
   }

   // $FF: synthetic method
   static int UnitTag$(final Constants $this) {
      return $this.UnitTag();
   }

   default int UnitTag() {
      return 1;
   }

   // $FF: synthetic method
   static int BooleanTag$(final Constants $this) {
      return $this.BooleanTag();
   }

   default int BooleanTag() {
      return 2;
   }

   // $FF: synthetic method
   static int ByteTag$(final Constants $this) {
      return $this.ByteTag();
   }

   default int ByteTag() {
      return 3;
   }

   // $FF: synthetic method
   static int ShortTag$(final Constants $this) {
      return $this.ShortTag();
   }

   default int ShortTag() {
      return 4;
   }

   // $FF: synthetic method
   static int CharTag$(final Constants $this) {
      return $this.CharTag();
   }

   default int CharTag() {
      return 5;
   }

   // $FF: synthetic method
   static int IntTag$(final Constants $this) {
      return $this.IntTag();
   }

   default int IntTag() {
      return 6;
   }

   // $FF: synthetic method
   static int LongTag$(final Constants $this) {
      return $this.LongTag();
   }

   default int LongTag() {
      return 7;
   }

   // $FF: synthetic method
   static int FloatTag$(final Constants $this) {
      return $this.FloatTag();
   }

   default int FloatTag() {
      return 8;
   }

   // $FF: synthetic method
   static int DoubleTag$(final Constants $this) {
      return $this.DoubleTag();
   }

   default int DoubleTag() {
      return 9;
   }

   // $FF: synthetic method
   static int StringTag$(final Constants $this) {
      return $this.StringTag();
   }

   default int StringTag() {
      return 10;
   }

   // $FF: synthetic method
   static int NullTag$(final Constants $this) {
      return $this.NullTag();
   }

   default int NullTag() {
      return 11;
   }

   // $FF: synthetic method
   static int ClazzTag$(final Constants $this) {
      return $this.ClazzTag();
   }

   default int ClazzTag() {
      return 12;
   }

   // $FF: synthetic method
   static int EnumTag$(final Constants $this) {
      return $this.EnumTag();
   }

   default int EnumTag() {
      return 13;
   }

   ClassTag ConstantTag();

   static void $init$(final Constants $this) {
      $this.scala$reflect$internal$Constants$_setter_$ConstantTag_$eq(.MODULE$.apply(Constant.class));
   }

   public class Constant extends scala.reflect.api.Constants.ConstantApi implements Product, Serializable {
      private final Object value;
      private final int tag;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object value() {
         return this.value;
      }

      public int tag() {
         return this.tag;
      }

      public boolean isByteRange() {
         return this.isIntRange() && -128 <= this.intValue() && this.intValue() <= 127;
      }

      public boolean isShortRange() {
         return this.isIntRange() && -32768 <= this.intValue() && this.intValue() <= 32767;
      }

      public boolean isCharRange() {
         return this.isIntRange() && 0 <= this.intValue() && this.intValue() <= 65535;
      }

      public boolean isIntRange() {
         return 3 <= this.tag() && this.tag() <= 6;
      }

      public boolean isLongRange() {
         return 3 <= this.tag() && this.tag() <= 7;
      }

      public boolean isFloatRepresentable() {
         return 3 <= this.tag() && this.tag() <= 8 && (this.tag() != 6 || this.intValue() == (int)((float)this.intValue())) && (this.tag() != 7 || this.longValue() == (long)((float)this.longValue()));
      }

      public boolean isDoubleRepresentable() {
         return 3 <= this.tag() && this.tag() <= 9 && (this.tag() != 7 || this.longValue() == (long)((double)this.longValue()));
      }

      public boolean isNumeric() {
         return 3 <= this.tag() && this.tag() <= 9;
      }

      public boolean isNonUnitAnyVal() {
         return 2 <= this.tag() && this.tag() <= 9;
      }

      public boolean isSuitableLiteralType() {
         return 2 <= this.tag() && this.tag() <= 11;
      }

      public boolean isAnyVal() {
         return 1 <= this.tag() && this.tag() <= 9;
      }

      public Types.Type tpe() {
         int var1 = this.tag();
         switch (var1) {
            case 1:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().UnitTpe();
            case 2:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().BooleanTpe();
            case 3:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ByteTpe();
            case 4:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ShortTpe();
            case 5:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().CharTpe();
            case 6:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().IntTpe();
            case 7:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().LongTpe();
            case 8:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().FloatTpe();
            case 9:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().DoubleTpe();
            case 10:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().StringTpe();
            case 11:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().NullTpe();
            case 12:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ClassType(this.typeValue());
            case 13:
               return this.scala$reflect$internal$Constants$Constant$$$outer().definitions().EnumType(this.symbolValue());
            default:
               throw new MatchError(var1);
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof Constant && ((Constant)other).scala$reflect$internal$Constants$Constant$$$outer() == this.scala$reflect$internal$Constants$Constant$$$outer()) {
            Constant var2 = (Constant)other;
            if (this.tag() == var2.tag()) {
               boolean var10000;
               switch (this.tag()) {
                  case 8:
                     var10000 = Float.floatToRawIntBits(BoxesRunTime.unboxToFloat(this.value())) == Float.floatToRawIntBits(BoxesRunTime.unboxToFloat(var2.value()));
                     break;
                  case 9:
                     var10000 = Double.doubleToRawLongBits(BoxesRunTime.unboxToDouble(this.value())) == Double.doubleToRawLongBits(BoxesRunTime.unboxToDouble(var2.value()));
                     break;
                  case 10:
                  default:
                     var10000 = this.value().equals(var2.value());
                     break;
                  case 11:
                     var10000 = true;
               }

               if (var10000) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }

      public boolean isNaN() {
         Object var1 = this.value();
         if (var1 instanceof Float) {
            return Float.isNaN(BoxesRunTime.unboxToFloat(var1));
         } else {
            return var1 instanceof Double ? Double.isNaN(BoxesRunTime.unboxToDouble(var1)) : false;
         }
      }

      public boolean booleanValue() {
         if (this.tag() == 2) {
            return BoxesRunTime.unboxToBoolean(this.value());
         } else {
            throw new Error((new StringBuilder(23)).append("value ").append(this.value()).append(" is not a boolean").toString());
         }
      }

      public byte byteValue() {
         switch (this.tag()) {
            case 3:
               return BoxesRunTime.unboxToByte(this.value());
            case 4:
               return (byte)BoxesRunTime.unboxToShort(this.value());
            case 5:
               return (byte)BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (byte)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (byte)((int)BoxesRunTime.unboxToLong(this.value()));
            case 8:
               return (byte)((int)BoxesRunTime.unboxToFloat(this.value()));
            case 9:
               return (byte)((int)BoxesRunTime.unboxToDouble(this.value()));
            default:
               throw new Error((new StringBuilder(20)).append("value ").append(this.value()).append(" is not a Byte").toString());
         }
      }

      public short shortValue() {
         switch (this.tag()) {
            case 3:
               return BoxesRunTime.unboxToByte(this.value());
            case 4:
               return BoxesRunTime.unboxToShort(this.value());
            case 5:
               return (short)BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (short)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (short)((int)BoxesRunTime.unboxToLong(this.value()));
            case 8:
               return (short)((int)BoxesRunTime.unboxToFloat(this.value()));
            case 9:
               return (short)((int)BoxesRunTime.unboxToDouble(this.value()));
            default:
               throw new Error((new StringBuilder(21)).append("value ").append(this.value()).append(" is not a Short").toString());
         }
      }

      public char charValue() {
         switch (this.tag()) {
            case 3:
               return (char)BoxesRunTime.unboxToByte(this.value());
            case 4:
               return (char)BoxesRunTime.unboxToShort(this.value());
            case 5:
               return BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (char)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (char)((int)BoxesRunTime.unboxToLong(this.value()));
            case 8:
               return (char)((int)BoxesRunTime.unboxToFloat(this.value()));
            case 9:
               return (char)((int)BoxesRunTime.unboxToDouble(this.value()));
            default:
               throw new Error((new StringBuilder(20)).append("value ").append(this.value()).append(" is not a Char").toString());
         }
      }

      public int intValue() {
         switch (this.tag()) {
            case 3:
               return BoxesRunTime.unboxToByte(this.value());
            case 4:
               return BoxesRunTime.unboxToShort(this.value());
            case 5:
               return BoxesRunTime.unboxToChar(this.value());
            case 6:
               return BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (int)BoxesRunTime.unboxToLong(this.value());
            case 8:
               return (int)BoxesRunTime.unboxToFloat(this.value());
            case 9:
               return (int)BoxesRunTime.unboxToDouble(this.value());
            default:
               throw new Error((new StringBuilder(20)).append("value ").append(this.value()).append(" is not an Int").toString());
         }
      }

      public long longValue() {
         switch (this.tag()) {
            case 3:
               return (long)BoxesRunTime.unboxToByte(this.value());
            case 4:
               return (long)BoxesRunTime.unboxToShort(this.value());
            case 5:
               return (long)BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (long)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return BoxesRunTime.unboxToLong(this.value());
            case 8:
               return (long)BoxesRunTime.unboxToFloat(this.value());
            case 9:
               return (long)BoxesRunTime.unboxToDouble(this.value());
            default:
               throw new Error((new StringBuilder(20)).append("value ").append(this.value()).append(" is not a Long").toString());
         }
      }

      public float floatValue() {
         switch (this.tag()) {
            case 3:
               return (float)BoxesRunTime.unboxToByte(this.value());
            case 4:
               return (float)BoxesRunTime.unboxToShort(this.value());
            case 5:
               return (float)BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (float)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (float)BoxesRunTime.unboxToLong(this.value());
            case 8:
               return BoxesRunTime.unboxToFloat(this.value());
            case 9:
               return (float)BoxesRunTime.unboxToDouble(this.value());
            default:
               throw new Error((new StringBuilder(21)).append("value ").append(this.value()).append(" is not a Float").toString());
         }
      }

      public double doubleValue() {
         switch (this.tag()) {
            case 3:
               return (double)BoxesRunTime.unboxToByte(this.value());
            case 4:
               return (double)BoxesRunTime.unboxToShort(this.value());
            case 5:
               return (double)BoxesRunTime.unboxToChar(this.value());
            case 6:
               return (double)BoxesRunTime.unboxToInt(this.value());
            case 7:
               return (double)BoxesRunTime.unboxToLong(this.value());
            case 8:
               return (double)BoxesRunTime.unboxToFloat(this.value());
            case 9:
               return BoxesRunTime.unboxToDouble(this.value());
            default:
               throw new Error((new StringBuilder(22)).append("value ").append(this.value()).append(" is not a Double").toString());
         }
      }

      public Constant convertTo(final Types.Type pt) {
         Symbols.Symbol target = pt.typeSymbol();
         Symbols.Symbol var3 = this.tpe().typeSymbol();
         if (target == null) {
            if (var3 == null) {
               return this;
            }
         } else if (target.equals(var3)) {
            return this;
         }

         label99: {
            Symbols.ClassSymbol var4 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ByteClass();
            if (target == null) {
               if (var4 != null) {
                  break label99;
               }
            } else if (!target.equals(var4)) {
               break label99;
            }

            if (this.isByteRange()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.byteValue());
            }
         }

         label93: {
            Symbols.ClassSymbol var5 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().ShortClass();
            if (target == null) {
               if (var5 != null) {
                  break label93;
               }
            } else if (!target.equals(var5)) {
               break label93;
            }

            if (this.isShortRange()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.shortValue());
            }
         }

         label87: {
            Symbols.ClassSymbol var6 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().CharClass();
            if (target == null) {
               if (var6 != null) {
                  break label87;
               }
            } else if (!target.equals(var6)) {
               break label87;
            }

            if (this.isCharRange()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.charValue());
            }
         }

         label81: {
            Symbols.ClassSymbol var7 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().IntClass();
            if (target == null) {
               if (var7 != null) {
                  break label81;
               }
            } else if (!target.equals(var7)) {
               break label81;
            }

            if (this.isIntRange()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.intValue());
            }
         }

         label75: {
            Symbols.ClassSymbol var8 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().LongClass();
            if (target == null) {
               if (var8 != null) {
                  break label75;
               }
            } else if (!target.equals(var8)) {
               break label75;
            }

            if (this.isLongRange()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.longValue());
            }
         }

         label69: {
            Symbols.ClassSymbol var9 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().FloatClass();
            if (target == null) {
               if (var9 != null) {
                  break label69;
               }
            } else if (!target.equals(var9)) {
               break label69;
            }

            if (this.isFloatRepresentable()) {
               return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.floatValue());
            }
         }

         Symbols.ClassSymbol var10 = this.scala$reflect$internal$Constants$Constant$$$outer().definitions().DoubleClass();
         if (target == null) {
            if (var10 != null) {
               return null;
            }
         } else if (!target.equals(var10)) {
            return null;
         }

         if (this.isDoubleRepresentable()) {
            return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(this.doubleValue());
         } else {
            return null;
         }
      }

      public String stringValue() {
         if (this.value() == null) {
            return "null";
         } else {
            return this.tag() == 12 ? this.scala$reflect$internal$Constants$Constant$$$outer().definitions().signature(this.typeValue()) : this.value().toString();
         }
      }

      public String escapedStringValue() {
         switch (this.tag()) {
            case 5:
               char c = this.charValue();
               if (requiresFormat$1(c)) {
                  StringBuilder b = (new StringBuilder()).append('\'');
                  escapedChar$1(b, c);
                  return b.append('\'').toString();
               }

               return (new StringBuilder(2)).append("'").append(c).append("'").toString();
            case 7:
               return (new StringBuilder(1)).append(Long.toString(this.longValue())).append("L").toString();
            case 10:
               return escape$1(this.stringValue());
            case 11:
               return "null";
            case 12:
               Types.Type var1 = this.typeValue();
               if (var1 instanceof Types.ErasedValueType) {
                  Symbols.Symbol clazz = ((Types.ErasedValueType)var1).valueClazz();
                  return this.show$1(clazz.tpe_$times());
               }

               return this.show$1(this.typeValue());
            case 13:
               return this.symbolValue().name().toString();
            default:
               return String.valueOf(this.value());
         }
      }

      public Types.Type typeValue() {
         return (Types.Type)this.value();
      }

      public Symbols.Symbol symbolValue() {
         return (Symbols.Symbol)this.value();
      }

      public int hashCode() {
         int seed = 17;
         int h = scala.util.hashing.MurmurHash3..MODULE$.mix(seed, this.tag());
         int var10000;
         switch (this.tag()) {
            case 8:
               var10000 = Integer.hashCode(Float.floatToRawIntBits(BoxesRunTime.unboxToFloat(this.value())));
               break;
            case 9:
               var10000 = Long.hashCode(Double.doubleToRawLongBits(BoxesRunTime.unboxToDouble(this.value())));
               break;
            case 10:
            default:
               var10000 = this.value().hashCode();
               break;
            case 11:
               var10000 = 0;
         }

         int valueHash = var10000;
         h = scala.util.hashing.MurmurHash3..MODULE$.mix(h, valueHash);
         int finalizeHash_length = 2;
         return scala.util.hashing.MurmurHash3..MODULE$.scala$util$hashing$MurmurHash3$$avalanche(h ^ finalizeHash_length);
      }

      public Constant copy(final Object value) {
         return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(value);
      }

      public Object copy$default$1() {
         return this.value();
      }

      public String productPrefix() {
         return "Constant";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.value();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Constant;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "value";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Constants$Constant$$$outer() {
         return (SymbolTable)this.$outer;
      }

      private static final boolean requiresFormat$1(final char c) {
         switch (c) {
            case '\b':
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case '"':
            case '\'':
            case '\\':
               return true;
            default:
               RichChar var10000 = scala.runtime.RichChar..MODULE$;
               return Character.isISOControl(c);
         }
      }

      private static final void quadNibble$1(final StringBuilder b, final int x, final int i) {
         if (i < 4) {
            quadNibble$1(b, x >> 4, i + 1);
            int n = x & 15;
            int c = n < 10 ? 48 + n : 65 + (n - 10);
            b.append((char)c);
         }
      }

      private static final void escapedChar$1(final StringBuilder b, final char c) {
         String var10000;
         switch (c) {
            case '\b':
               var10000 = "\\b";
               break;
            case '\t':
               var10000 = "\\t";
               break;
            case '\n':
               var10000 = "\\n";
               break;
            case '\f':
               var10000 = "\\f";
               break;
            case '\r':
               var10000 = "\\r";
               break;
            case '"':
               var10000 = "\\\"";
               break;
            case '\'':
               var10000 = "\\'";
               break;
            case '\\':
               var10000 = "\\\\";
               break;
            default:
               RichChar var3 = scala.runtime.RichChar..MODULE$;
               if (Character.isISOControl(c)) {
                  b.append("\\u");
                  quadNibble$1(b, c, 0);
                  return;
               }

               b.append(c);
               return;
         }

         String replace = var10000;
         b.append(replace);
      }

      private static final boolean mustBuild$1(final String text$1) {
         for(int i = 0; i < text$1.length(); ++i) {
            if (requiresFormat$1(text$1.charAt(i))) {
               return true;
            }
         }

         return false;
      }

      private static final String escape$1(final String text) {
         if (!mustBuild$1(text)) {
            return (new StringBuilder(2)).append("\"").append(text).append("\"").toString();
         } else {
            StringBuilder b = (new StringBuilder(text.length() + 16)).append('"');

            for(int i = 0; i < text.length(); ++i) {
               escapedChar$1(b, text.charAt(i));
            }

            return b.append('"').toString();
         }
      }

      private final String show$1(final Types.Type tpe) {
         return (new StringBuilder(9)).append("classOf[").append(this.scala$reflect$internal$Constants$Constant$$$outer().definitions().signature(tpe)).append("]").toString();
      }

      public Constant(final Object value) {
         this.value = value;
         byte var10001;
         if (value == null) {
            var10001 = 11;
         } else if (value instanceof BoxedUnit) {
            var10001 = 1;
         } else if (value instanceof Boolean) {
            var10001 = 2;
         } else if (value instanceof Byte) {
            var10001 = 3;
         } else if (value instanceof Short) {
            var10001 = 4;
         } else if (value instanceof Integer) {
            var10001 = 6;
         } else if (value instanceof Long) {
            var10001 = 7;
         } else if (value instanceof Float) {
            var10001 = 8;
         } else if (value instanceof Double) {
            var10001 = 9;
         } else if (value instanceof String) {
            var10001 = 10;
         } else if (value instanceof Character) {
            var10001 = 5;
         } else if (value instanceof Types.Type && ((Types.Type)value).scala$reflect$internal$Types$Type$$$outer() == Constants.this) {
            var10001 = 12;
         } else {
            if (!(value instanceof Symbols.Symbol) || ((Symbols.Symbol)value).scala$reflect$internal$Symbols$Symbol$$$outer() != Constants.this) {
               throw new Error((new StringBuilder(30)).append("bad constant value: ").append(value).append(" of class ").append(value.getClass()).toString());
            }

            var10001 = 13;
         }

         this.tag = var10001;
      }
   }

   public class Constant$ extends scala.reflect.api.Constants.ConstantExtractor implements Serializable {
      public Constant apply(final Object value) {
         return this.scala$reflect$internal$Constants$Constant$$$outer().new Constant(value);
      }

      public Option unapply(final Constant x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.value()));
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Constants$Constant$$$outer() {
         return (SymbolTable)this.$outer;
      }
   }
}
