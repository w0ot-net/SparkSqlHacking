package org.apache.spark.util;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.Product;
import scala.Product2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t]h\u0001B\u000e\u001d\u0001\u0016B\u0001\"\u001e\u0001\u0003\u0012\u0004%\tA\u001e\u0005\to\u0002\u0011\t\u0019!C\u0001q\"Aa\u0010\u0001B\tB\u0003&\u0001\u0007C\u0005\u0000\u0001\tE\r\u0011\"\u0001\u0002\u0002!Q\u00111\u0001\u0001\u0003\u0002\u0004%\t!!\u0002\t\u0013\u0005%\u0001A!E!B\u0013i\u0006bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003\u0017\u0001A\u0011AA\f\u0011\u001d\tI\u0002\u0001C\u0001\u00037Aq!!\n\u0001\t\u0003\n9\u0003C\u0004\u0002:\u0001!\t%a\u000f\t\u0013\u0005\u001d\u0003!!A\u0005\u0002\u0005%\u0003\"CAD\u0001E\u0005I\u0011AAE\u0011%\t\t\u000eAI\u0001\n\u0003\t\u0019\u000eC\u0005\u0003\n\u0001\t\t\u0011\"\u0011\u0003\f!I!1\u0004\u0001\u0002\u0002\u0013\u0005#Q\u0004\u0005\n\u0005W\u0001\u0011\u0011!C!\u0005[A\u0011Ba\u000e\u0001\u0003\u0003%\tE!\u000f\t\u0013\tm\u0002!!A\u0005B\tur!\u0003B'9\u0005\u0005\t\u0012\u0001B(\r!YB$!A\t\u0002\tE\u0003bBA\u0006+\u0011\u0005!Q\f\u0005\n\u0003K)\u0012\u0011!C#\u0005?B\u0011B!\u0019\u0016\u0003\u0003%\tIa\u0019\t\u0013\t\u0005V#!A\u0005\u0002\n\r\u0006\"\u0003Bw+\u0005\u0005I\u0011\u0002Bx\u0005-iU\u000f^1cY\u0016\u0004\u0016-\u001b:\u000b\u0005uq\u0012\u0001B;uS2T!a\b\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0005\u0012\u0013AB1qC\u000eDWMC\u0001$\u0003\ry'oZ\u0002\u0001+\r1#GX\n\u0006\u0001\u001dj3N\u001c\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\t!r\u0003'X\u0005\u0003_%\u0012\u0001\u0002\u0015:pIV\u001cGO\r\t\u0003cIb\u0001\u0001B\u00054\u0001\u0001\u0006\t\u0011!b\u0001i\t\u0011A+M\t\u0003ka\u0002\"\u0001\u000b\u001c\n\u0005]J#a\u0002(pi\"Lgn\u001a\t\u0003QeJ!AO\u0015\u0003\u0007\u0005s\u0017\u0010K\u00043y}Jej\u0015-\u0011\u0005!j\u0014B\u0001 *\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u0002\u0015i\u0011\"\u000f\u0005!\n\u0015B\u0001\"*\u0003\rIe\u000e^\u0019\u0005I\u0011C%F\u0004\u0002F\u00116\taI\u0003\u0002HI\u00051AH]8pizJ\u0011AK\u0019\u0006G)[U\n\u0014\b\u0003Q-K!\u0001T\u0015\u0002\t1{gnZ\u0019\u0005I\u0011C%&M\u0003$\u001fB\u0013\u0016K\u0004\u0002)!&\u0011\u0011+K\u0001\u0007\t>,(\r\\32\t\u0011\"\u0005JK\u0019\u0006GQ+vK\u0016\b\u0003QUK!AV\u0015\u0002\t\rC\u0017M]\u0019\u0005I\u0011C%&M\u0003$3jc6L\u0004\u0002)5&\u00111,K\u0001\b\u0005>|G.Z1oc\u0011!C\t\u0013\u0016\u0011\u0005ErF!C0\u0001A\u0003\u0005\tQ1\u00015\u0005\t!&\u0007K\u0004_y\u0005\u001cWmZ52\u000b\r\u0002\u0015I\u0019\"2\t\u0011\"\u0005JK\u0019\u0006G)[E\rT\u0019\u0005I\u0011C%&M\u0003$\u001fB3\u0017+\r\u0003%\t\"S\u0013'B\u0012U+\"4\u0016\u0007\u0002\u0013E\u0011*\nTaI-[Un\u000bD\u0001\n#IUA\u0011\u0001\u0006\\\u0005\u0003[&\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002pe:\u0011A\t]\u0005\u0003c&\nq\u0001]1dW\u0006<W-\u0003\u0002ti\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011/K\u0001\u0003?F*\u0012\u0001M\u0001\u0007?FzF%Z9\u0015\u0005ed\bC\u0001\u0015{\u0013\tY\u0018F\u0001\u0003V]&$\bbB?\u0003\u0003\u0003\u0005\r\u0001M\u0001\u0004q\u0012\n\u0014aA02A\u0005\u0011qLM\u000b\u0002;\u00061qLM0%KF$2!_A\u0004\u0011\u001diX!!AA\u0002u\u000b1a\u0018\u001a!\u0003\u0019a\u0014N\\5u}Q1\u0011qBA\n\u0003+\u0001R!!\u0005\u0001auk\u0011\u0001\b\u0005\u0006k\u001e\u0001\r\u0001\r\u0005\u0006\u007f\u001e\u0001\r!\u0018\u000b\u0003\u0003\u001f\ta!\u001e9eCR,GCBA\b\u0003;\t\t\u0003\u0003\u0004\u0002 %\u0001\r\u0001M\u0001\u0003]FBa!a\t\n\u0001\u0004i\u0016A\u000183\u0003!!xn\u0015;sS:<GCAA\u0015!\u0011\tY#a\r\u000f\t\u00055\u0012q\u0006\t\u0003\u000b&J1!!\r*\u0003\u0019\u0001&/\u001a3fM&!\u0011QGA\u001c\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011G\u0015\u0002\u0011\r\fg.R9vC2$B!!\u0010\u0002DA\u0019\u0001&a\u0010\n\u0007\u0005\u0005\u0013FA\u0004C_>dW-\u00198\t\r\u0005\u00153\u00021\u00019\u0003\u0011!\b.\u0019;\u0002\t\r|\u0007/_\u000b\u0007\u0003\u0017\n\t&a\u001b\u0015\r\u00055\u00131QAC!\u001d\t\t\u0002AA(\u0003S\u00022!MA)\t%\u0019D\u0002)A\u0001\u0002\u000b\u0007A\u0007K\u0007\u0002Rq\n)&!\u0017\u0002^\u0005\u0005\u0014QM\u0019\u0007G\u0001\u000b\u0015q\u000b\"2\t\u0011\"\u0005JK\u0019\u0007G)[\u00151\f'2\t\u0011\"\u0005JK\u0019\u0007G=\u0003\u0016qL)2\t\u0011\"\u0005JK\u0019\u0007GQ+\u00161\r,2\t\u0011\"\u0005JK\u0019\u0007GeS\u0016qM.2\t\u0011\"\u0005J\u000b\t\u0004c\u0005-D!C0\rA\u0003\u0005\tQ1\u00015Q5\tY\u0007PA8\u0003g\n9(a\u001f\u0002\u0000E21\u0005Q!\u0002r\t\u000bD\u0001\n#IUE21ES&\u0002v1\u000bD\u0001\n#IUE21e\u0014)\u0002zE\u000bD\u0001\n#IUE21\u0005V+\u0002~Y\u000bD\u0001\n#IUE21%\u0017.\u0002\u0002n\u000bD\u0001\n#IU!AQ\u000f\u0004I\u0001\u0002\u0004\ty\u0005\u0003\u0005\u0000\u0019A\u0005\t\u0019AA5\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*b!a#\u0002\"\u0006eVCAAGU\r\u0001\u0014qR\u0016\u0003\u0003#\u0003B!a%\u0002\u001e6\u0011\u0011Q\u0013\u0006\u0005\u0003/\u000bI*A\u0005v]\u000eDWmY6fI*\u0019\u00111T\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002 \u0006U%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012I1'\u0004Q\u0001\u0002\u0003\u0015\r\u0001\u000e\u0015\u000e\u0003Cc\u0014QUAU\u0003[\u000b\t,!.2\r\r\u0002\u0015)a*Cc\u0011!C\t\u0013\u00162\r\rR5*a+Mc\u0011!C\t\u0013\u00162\r\rz\u0005+a,Rc\u0011!C\t\u0013\u00162\r\r\"V+a-Wc\u0011!C\t\u0013\u00162\r\rJ&,a.\\c\u0011!C\t\u0013\u0016\u0005\u0013}k\u0001\u0015!A\u0001\u0006\u0004!\u0004&DA]y\u0005u\u0016\u0011YAc\u0003\u0013\fi-\r\u0004$\u0001\u0006\u000byLQ\u0019\u0005I\u0011C%&\r\u0004$\u0015.\u000b\u0019\rT\u0019\u0005I\u0011C%&\r\u0004$\u001fB\u000b9-U\u0019\u0005I\u0011C%&\r\u0004$)V\u000bYMV\u0019\u0005I\u0011C%&\r\u0004$3j\u000bymW\u0019\u0005I\u0011C%&\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\r\u0005U\u0017\u0011\\Ay+\t\t9NK\u0002^\u0003\u001f#\u0011b\r\b!\u0002\u0003\u0005)\u0019\u0001\u001b)\u001b\u0005eG(!8\u0002b\u0006\u0015\u0018\u0011^Awc\u0019\u0019\u0003)QAp\u0005F\"A\u0005\u0012%+c\u0019\u0019#jSAr\u0019F\"A\u0005\u0012%+c\u0019\u0019s\nUAt#F\"A\u0005\u0012%+c\u0019\u0019C+VAv-F\"A\u0005\u0012%+c\u0019\u0019\u0013LWAx7F\"A\u0005\u0012%+\t%yf\u0002)A\u0001\u0002\u000b\u0007A\u0007K\u0007\u0002rr\n)0!?\u0002~\n\u0005!QA\u0019\u0007G\u0001\u000b\u0015q\u001f\"2\t\u0011\"\u0005JK\u0019\u0007G)[\u00151 '2\t\u0011\"\u0005JK\u0019\u0007G=\u0003\u0016q`)2\t\u0011\"\u0005JK\u0019\u0007GQ+&1\u0001,2\t\u0011\"\u0005JK\u0019\u0007GeS&qA.2\t\u0011\"\u0005JK\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t5\u0001\u0003\u0002B\b\u00053i!A!\u0005\u000b\t\tM!QC\u0001\u0005Y\u0006twM\u0003\u0002\u0003\u0018\u0005!!.\u0019<b\u0013\u0011\t)D!\u0005\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"Aa\b\u0011\u000b\t\u0005\"q\u0005\u001d\u000e\u0005\t\r\"b\u0001B\u0013S\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t%\"1\u0005\u0002\t\u0013R,'/\u0019;pe\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011iAa\f\t\u0011u\f\u0012\u0011!a\u0001\u0005c\u00012\u0001\u000bB\u001a\u0013\r\u0011)$\u000b\u0002\u0004\u0013:$\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\tE\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002>\t}\u0002bB?\u0014\u0003\u0003\u0005\r\u0001\u000f\u0015\u0004\u0001\t\r\u0003\u0003\u0002B#\u0005\u0013j!Aa\u0012\u000b\u0007\u0005me$\u0003\u0003\u0003L\t\u001d#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017aC'vi\u0006\u0014G.\u001a)bSJ\u00042!!\u0005\u0016'\u0011)rEa\u0015\u0011\t\tU#1L\u0007\u0003\u0005/RAA!\u0017\u0003\u0016\u0005\u0011\u0011n\\\u0005\u0004g\n]CC\u0001B()\t\u0011i!A\u0003baBd\u00170\u0006\u0004\u0003f\t-$Q\u0011\u000b\u0007\u0005O\u0012iJa(\u0011\u000f\u0005E\u0001A!\u001b\u0003\u0004B\u0019\u0011Ga\u001b\u0005\u0013MB\u0002\u0015!A\u0001\u0006\u0004!\u0004&\u0004B6y\t=$1\u000fB<\u0005w\u0012y(\r\u0004$\u0001\u0006\u0013\tHQ\u0019\u0005I\u0011C%&\r\u0004$\u0015.\u0013)\bT\u0019\u0005I\u0011C%&\r\u0004$\u001fB\u0013I(U\u0019\u0005I\u0011C%&\r\u0004$)V\u0013iHV\u0019\u0005I\u0011C%&\r\u0004$3j\u0013\tiW\u0019\u0005I\u0011C%\u0006E\u00022\u0005\u000b#\u0011b\u0018\r!\u0002\u0003\u0005)\u0019\u0001\u001b)\u001b\t\u0015EH!#\u0003\u000e\nE%Q\u0013BMc\u0019\u0019\u0003)\u0011BF\u0005F\"A\u0005\u0012%+c\u0019\u0019#j\u0013BH\u0019F\"A\u0005\u0012%+c\u0019\u0019s\n\u0015BJ#F\"A\u0005\u0012%+c\u0019\u0019C+\u0016BL-F\"A\u0005\u0012%+c\u0019\u0019\u0013L\u0017BN7F\"A\u0005\u0012%+\u0011\u0019)\b\u00041\u0001\u0003j!1q\u0010\u0007a\u0001\u0005\u0007\u000bq!\u001e8baBd\u00170\u0006\u0004\u0003&\nU&q\u001a\u000b\u0005\u0005O\u00139\u000fE\u0003)\u0005S\u0013i+C\u0002\u0003,&\u0012aa\u00149uS>t\u0007c\u0002\u0015\u00030\nM&QZ\u0005\u0004\u0005cK#A\u0002+va2,'\u0007E\u00022\u0005k#\u0011bM\r!\u0002\u0003\u0005)\u0019\u0001\u001b)\u001b\tUFH!/\u0003>\n\u0005'Q\u0019Bec\u0019\u0019\u0003)\u0011B^\u0005F\"A\u0005\u0012%+c\u0019\u0019#j\u0013B`\u0019F\"A\u0005\u0012%+c\u0019\u0019s\n\u0015Bb#F\"A\u0005\u0012%+c\u0019\u0019C+\u0016Bd-F\"A\u0005\u0012%+c\u0019\u0019\u0013L\u0017Bf7F\"A\u0005\u0012%+!\r\t$q\u001a\u0003\n?f\u0001\u000b\u0011!AC\u0002QBSBa4=\u0005'\u00149Na7\u0003`\n\r\u0018GB\u0012A\u0003\nU')\r\u0003%\t\"S\u0013GB\u0012K\u0017\neG*\r\u0003%\t\"S\u0013GB\u0012P!\nu\u0017+\r\u0003%\t\"S\u0013GB\u0012U+\n\u0005h+\r\u0003%\t\"S\u0013GB\u0012Z5\n\u00158,\r\u0003%\t\"S\u0003\"\u0003Bu3\u0005\u0005\t\u0019\u0001Bv\u0003\rAH\u0005\r\t\b\u0003#\u0001!1\u0017Bg\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\t\u0010\u0005\u0003\u0003\u0010\tM\u0018\u0002\u0002B{\u0005#\u0011aa\u00142kK\u000e$\b"
)
public class MutablePair implements Product2, Serializable {
   public Object _1;
   public Object _2;

   public static Option unapply(final MutablePair x$0) {
      return MutablePair$.MODULE$.unapply(x$0);
   }

   public static MutablePair apply(final Object _1, final Object _2) {
      return MutablePair$.MODULE$.apply(_1, _2);
   }

   public int productArity() {
      return Product2.productArity$(this);
   }

   public Object productElement(final int n) throws IndexOutOfBoundsException {
      return Product2.productElement$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object _1() {
      return this._1;
   }

   public void _1_$eq(final Object x$1) {
      this._1 = x$1;
   }

   public Object _2() {
      return this._2;
   }

   public void _2_$eq(final Object x$1) {
      this._2 = x$1;
   }

   public MutablePair update(final Object n1, final Object n2) {
      this._1_$eq(n1);
      this._2_$eq(n2);
      return this;
   }

   public String toString() {
      Object var10000 = this._1();
      return "(" + var10000 + "," + this._2() + ")";
   }

   public boolean canEqual(final Object that) {
      return that instanceof MutablePair;
   }

   public MutablePair copy(final Object _1, final Object _2) {
      return new MutablePair(_1, _2);
   }

   public Object copy$default$1() {
      return this._1();
   }

   public Object copy$default$2() {
      return this._2();
   }

   public String productPrefix() {
      return "MutablePair";
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "_1";
         }
         case 1 -> {
            return "_2";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof MutablePair) {
               MutablePair var4 = (MutablePair)x$1;
               if (BoxesRunTime.equals(this._1(), var4._1()) && BoxesRunTime.equals(this._2(), var4._2()) && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean _1$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this._1());
   }

   public char _1$mcC$sp() {
      return BoxesRunTime.unboxToChar(this._1());
   }

   public double _1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this._1());
   }

   public int _1$mcI$sp() {
      return BoxesRunTime.unboxToInt(this._1());
   }

   public long _1$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this._1());
   }

   public void _1$mcZ$sp_$eq(final boolean x$1) {
      this._1_$eq(BoxesRunTime.boxToBoolean(x$1));
   }

   public void _1$mcC$sp_$eq(final char x$1) {
      this._1_$eq(BoxesRunTime.boxToCharacter(x$1));
   }

   public void _1$mcD$sp_$eq(final double x$1) {
      this._1_$eq(BoxesRunTime.boxToDouble(x$1));
   }

   public void _1$mcI$sp_$eq(final int x$1) {
      this._1_$eq(BoxesRunTime.boxToInteger(x$1));
   }

   public void _1$mcJ$sp_$eq(final long x$1) {
      this._1_$eq(BoxesRunTime.boxToLong(x$1));
   }

   public boolean _2$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this._2());
   }

   public char _2$mcC$sp() {
      return BoxesRunTime.unboxToChar(this._2());
   }

   public double _2$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this._2());
   }

   public int _2$mcI$sp() {
      return BoxesRunTime.unboxToInt(this._2());
   }

   public long _2$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this._2());
   }

   public void _2$mcZ$sp_$eq(final boolean x$1) {
      this._2_$eq(BoxesRunTime.boxToBoolean(x$1));
   }

   public void _2$mcC$sp_$eq(final char x$1) {
      this._2_$eq(BoxesRunTime.boxToCharacter(x$1));
   }

   public void _2$mcD$sp_$eq(final double x$1) {
      this._2_$eq(BoxesRunTime.boxToDouble(x$1));
   }

   public void _2$mcI$sp_$eq(final int x$1) {
      this._2_$eq(BoxesRunTime.boxToInteger(x$1));
   }

   public void _2$mcJ$sp_$eq(final long x$1) {
      this._2_$eq(BoxesRunTime.boxToLong(x$1));
   }

   public MutablePair update$mcZZ$sp(final boolean n1, final boolean n2) {
      return this.update(BoxesRunTime.boxToBoolean(n1), BoxesRunTime.boxToBoolean(n2));
   }

   public MutablePair update$mcZC$sp(final boolean n1, final char n2) {
      return this.update(BoxesRunTime.boxToBoolean(n1), BoxesRunTime.boxToCharacter(n2));
   }

   public MutablePair update$mcZD$sp(final boolean n1, final double n2) {
      return this.update(BoxesRunTime.boxToBoolean(n1), BoxesRunTime.boxToDouble(n2));
   }

   public MutablePair update$mcZI$sp(final boolean n1, final int n2) {
      return this.update(BoxesRunTime.boxToBoolean(n1), BoxesRunTime.boxToInteger(n2));
   }

   public MutablePair update$mcZJ$sp(final boolean n1, final long n2) {
      return this.update(BoxesRunTime.boxToBoolean(n1), BoxesRunTime.boxToLong(n2));
   }

   public MutablePair update$mcCZ$sp(final char n1, final boolean n2) {
      return this.update(BoxesRunTime.boxToCharacter(n1), BoxesRunTime.boxToBoolean(n2));
   }

   public MutablePair update$mcCC$sp(final char n1, final char n2) {
      return this.update(BoxesRunTime.boxToCharacter(n1), BoxesRunTime.boxToCharacter(n2));
   }

   public MutablePair update$mcCD$sp(final char n1, final double n2) {
      return this.update(BoxesRunTime.boxToCharacter(n1), BoxesRunTime.boxToDouble(n2));
   }

   public MutablePair update$mcCI$sp(final char n1, final int n2) {
      return this.update(BoxesRunTime.boxToCharacter(n1), BoxesRunTime.boxToInteger(n2));
   }

   public MutablePair update$mcCJ$sp(final char n1, final long n2) {
      return this.update(BoxesRunTime.boxToCharacter(n1), BoxesRunTime.boxToLong(n2));
   }

   public MutablePair update$mcDZ$sp(final double n1, final boolean n2) {
      return this.update(BoxesRunTime.boxToDouble(n1), BoxesRunTime.boxToBoolean(n2));
   }

   public MutablePair update$mcDC$sp(final double n1, final char n2) {
      return this.update(BoxesRunTime.boxToDouble(n1), BoxesRunTime.boxToCharacter(n2));
   }

   public MutablePair update$mcDD$sp(final double n1, final double n2) {
      return this.update(BoxesRunTime.boxToDouble(n1), BoxesRunTime.boxToDouble(n2));
   }

   public MutablePair update$mcDI$sp(final double n1, final int n2) {
      return this.update(BoxesRunTime.boxToDouble(n1), BoxesRunTime.boxToInteger(n2));
   }

   public MutablePair update$mcDJ$sp(final double n1, final long n2) {
      return this.update(BoxesRunTime.boxToDouble(n1), BoxesRunTime.boxToLong(n2));
   }

   public MutablePair update$mcIZ$sp(final int n1, final boolean n2) {
      return this.update(BoxesRunTime.boxToInteger(n1), BoxesRunTime.boxToBoolean(n2));
   }

   public MutablePair update$mcIC$sp(final int n1, final char n2) {
      return this.update(BoxesRunTime.boxToInteger(n1), BoxesRunTime.boxToCharacter(n2));
   }

   public MutablePair update$mcID$sp(final int n1, final double n2) {
      return this.update(BoxesRunTime.boxToInteger(n1), BoxesRunTime.boxToDouble(n2));
   }

   public MutablePair update$mcII$sp(final int n1, final int n2) {
      return this.update(BoxesRunTime.boxToInteger(n1), BoxesRunTime.boxToInteger(n2));
   }

   public MutablePair update$mcIJ$sp(final int n1, final long n2) {
      return this.update(BoxesRunTime.boxToInteger(n1), BoxesRunTime.boxToLong(n2));
   }

   public MutablePair update$mcJZ$sp(final long n1, final boolean n2) {
      return this.update(BoxesRunTime.boxToLong(n1), BoxesRunTime.boxToBoolean(n2));
   }

   public MutablePair update$mcJC$sp(final long n1, final char n2) {
      return this.update(BoxesRunTime.boxToLong(n1), BoxesRunTime.boxToCharacter(n2));
   }

   public MutablePair update$mcJD$sp(final long n1, final double n2) {
      return this.update(BoxesRunTime.boxToLong(n1), BoxesRunTime.boxToDouble(n2));
   }

   public MutablePair update$mcJI$sp(final long n1, final int n2) {
      return this.update(BoxesRunTime.boxToLong(n1), BoxesRunTime.boxToInteger(n2));
   }

   public MutablePair update$mcJJ$sp(final long n1, final long n2) {
      return this.update(BoxesRunTime.boxToLong(n1), BoxesRunTime.boxToLong(n2));
   }

   public MutablePair copy$mZZc$sp(final boolean _1, final boolean _2) {
      return new MutablePair$mcZZ$sp(_1, _2);
   }

   public MutablePair copy$mZCc$sp(final boolean _1, final char _2) {
      return new MutablePair$mcZC$sp(_1, _2);
   }

   public MutablePair copy$mZDc$sp(final boolean _1, final double _2) {
      return new MutablePair$mcZD$sp(_1, _2);
   }

   public MutablePair copy$mZIc$sp(final boolean _1, final int _2) {
      return new MutablePair$mcZI$sp(_1, _2);
   }

   public MutablePair copy$mZJc$sp(final boolean _1, final long _2) {
      return new MutablePair$mcZJ$sp(_1, _2);
   }

   public MutablePair copy$mCZc$sp(final char _1, final boolean _2) {
      return new MutablePair$mcCZ$sp(_1, _2);
   }

   public MutablePair copy$mCCc$sp(final char _1, final char _2) {
      return new MutablePair$mcCC$sp(_1, _2);
   }

   public MutablePair copy$mCDc$sp(final char _1, final double _2) {
      return new MutablePair$mcCD$sp(_1, _2);
   }

   public MutablePair copy$mCIc$sp(final char _1, final int _2) {
      return new MutablePair$mcCI$sp(_1, _2);
   }

   public MutablePair copy$mCJc$sp(final char _1, final long _2) {
      return new MutablePair$mcCJ$sp(_1, _2);
   }

   public MutablePair copy$mDZc$sp(final double _1, final boolean _2) {
      return new MutablePair$mcDZ$sp(_1, _2);
   }

   public MutablePair copy$mDCc$sp(final double _1, final char _2) {
      return new MutablePair$mcDC$sp(_1, _2);
   }

   public MutablePair copy$mDDc$sp(final double _1, final double _2) {
      return new MutablePair$mcDD$sp(_1, _2);
   }

   public MutablePair copy$mDIc$sp(final double _1, final int _2) {
      return new MutablePair$mcDI$sp(_1, _2);
   }

   public MutablePair copy$mDJc$sp(final double _1, final long _2) {
      return new MutablePair$mcDJ$sp(_1, _2);
   }

   public MutablePair copy$mIZc$sp(final int _1, final boolean _2) {
      return new MutablePair$mcIZ$sp(_1, _2);
   }

   public MutablePair copy$mICc$sp(final int _1, final char _2) {
      return new MutablePair$mcIC$sp(_1, _2);
   }

   public MutablePair copy$mIDc$sp(final int _1, final double _2) {
      return new MutablePair$mcID$sp(_1, _2);
   }

   public MutablePair copy$mIIc$sp(final int _1, final int _2) {
      return new MutablePair$mcII$sp(_1, _2);
   }

   public MutablePair copy$mIJc$sp(final int _1, final long _2) {
      return new MutablePair$mcIJ$sp(_1, _2);
   }

   public MutablePair copy$mJZc$sp(final long _1, final boolean _2) {
      return new MutablePair$mcJZ$sp(_1, _2);
   }

   public MutablePair copy$mJCc$sp(final long _1, final char _2) {
      return new MutablePair$mcJC$sp(_1, _2);
   }

   public MutablePair copy$mJDc$sp(final long _1, final double _2) {
      return new MutablePair$mcJD$sp(_1, _2);
   }

   public MutablePair copy$mJIc$sp(final long _1, final int _2) {
      return new MutablePair$mcJI$sp(_1, _2);
   }

   public MutablePair copy$mJJc$sp(final long _1, final long _2) {
      return new MutablePair$mcJJ$sp(_1, _2);
   }

   public boolean copy$default$1$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.copy$default$1());
   }

   public char copy$default$1$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.copy$default$1());
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public int copy$default$1$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.copy$default$1());
   }

   public long copy$default$1$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.copy$default$1());
   }

   public boolean copy$default$2$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.copy$default$2());
   }

   public char copy$default$2$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.copy$default$2());
   }

   public double copy$default$2$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$2());
   }

   public int copy$default$2$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.copy$default$2());
   }

   public long copy$default$2$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.copy$default$2());
   }

   public boolean specInstance$() {
      return false;
   }

   public MutablePair(final Object _1, final Object _2) {
      this._1 = _1;
      this._2 = _2;
      super();
      Product.$init$(this);
      Product2.$init$(this);
   }

   public MutablePair() {
      this((Object)null, (Object)null);
   }
}
