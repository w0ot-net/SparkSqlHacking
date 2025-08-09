package scala.xml.dtd;

import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3A!\u0007\u000e\u0001C!)a\u0005\u0001C\u0001O!9!\u0006\u0001b\u0001\n\u000bY\u0003BB\u0018\u0001A\u00035A\u0006C\u00041\u0001\t\u0007IQA\u0016\t\rE\u0002\u0001\u0015!\u0004-\u0011\u001d\u0011\u0004A1A\u0005\u0006-Baa\r\u0001!\u0002\u001ba\u0003b\u0002\u001b\u0001\u0005\u0004%)a\u000b\u0005\u0007k\u0001\u0001\u000bQ\u0002\u0017\t\u000fY\u0002!\u0019!C\u0003W!1q\u0007\u0001Q\u0001\u000e1Bq\u0001\u000f\u0001C\u0002\u0013\u00151\u0006\u0003\u0004:\u0001\u0001\u0006i\u0001\f\u0005\bu\u0001\u0011\r\u0011\"\u0002,\u0011\u0019Y\u0004\u0001)A\u0007Y!9A\b\u0001b\u0001\n\u000bY\u0003BB\u001f\u0001A\u00035A\u0006C\u0004?\u0001\t\u0007IQA\u0016\t\r}\u0002\u0001\u0015!\u0004-\u0011\u001d\u0001\u0005A1A\u0005\u0006-Ba!\u0011\u0001!\u0002\u001ba\u0003b\u0002\"\u0001\u0005\u0004%)a\u000b\u0005\u0007\u0007\u0002\u0001\u000bQ\u0002\u0017\t\u000b\u0011\u0003AQA#\u0003\rQ{7.\u001a8t\u0015\tYB$A\u0002ei\u0012T!!\b\u0010\u0002\u0007alGNC\u0001 \u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0012\u0011\u0005\r\"S\"\u0001\u0010\n\u0005\u0015r\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002QA\u0011\u0011\u0006A\u0007\u00025\u0005aAkT&F\u001d~\u00036\tR!U\u0003V\tA\u0006\u0005\u0002$[%\u0011aF\b\u0002\u0004\u0013:$\u0018!\u0004+P\u0017\u0016su\fU\"E\u0003R\u000b\u0005%\u0001\u0003O\u00036+\u0015!\u0002(B\u001b\u0016\u0003\u0013A\u0002'Q\u0003J+e*A\u0004M!\u0006\u0013VI\u0014\u0011\u0002\rI\u0003\u0016IU#O\u0003\u001d\u0011\u0006+\u0011*F\u001d\u0002\nQaQ(N\u001b\u0006\u000baaQ(N\u001b\u0006\u0003\u0013\u0001B*U\u0003J\u000bQa\u0015+B%\u0002\nA\u0001\u0015'V'\u0006)\u0001\u000bT+TA\u0005\u0019q\n\u0015+\u0002\t=\u0003F\u000bI\u0001\u0007\u0007\"{\u0015jQ#\u0002\u000f\rCu*S\"FA\u0005\u0019QI\u0014#\u0002\t\u0015sE\tI\u0001\u0002'\u0006\u00111\u000bI\u0001\ri>\\WM\u001c\u001atiJLgn\u001a\u000b\u0003\rF\u0003\"a\u0012(\u000f\u0005!c\u0005CA%\u001f\u001b\u0005Q%BA&!\u0003\u0019a$o\\8u}%\u0011QJH\u0001\u0007!J,G-\u001a4\n\u0005=\u0003&AB*ue&twM\u0003\u0002N=!)!\u000b\u0007a\u0001Y\u0005\t\u0011\u000e"
)
public class Tokens {
   private final int TOKEN_PCDATA = 0;
   private final int NAME = 1;
   private final int LPAREN = 3;
   private final int RPAREN = 4;
   private final int COMMA = 5;
   private final int STAR = 6;
   private final int PLUS = 7;
   private final int OPT = 8;
   private final int CHOICE = 9;
   private final int END = 10;
   private final int S = 13;

   public final int TOKEN_PCDATA() {
      return this.TOKEN_PCDATA;
   }

   public final int NAME() {
      return this.NAME;
   }

   public final int LPAREN() {
      return this.LPAREN;
   }

   public final int RPAREN() {
      return this.RPAREN;
   }

   public final int COMMA() {
      return this.COMMA;
   }

   public final int STAR() {
      return this.STAR;
   }

   public final int PLUS() {
      return this.PLUS;
   }

   public final int OPT() {
      return this.OPT;
   }

   public final int CHOICE() {
      return this.CHOICE;
   }

   public final int END() {
      return this.END;
   }

   public final int S() {
      return this.S;
   }

   public final String token2string(final int i) {
      switch (i) {
         case 0:
            return "#PCDATA";
         case 1:
            return "NAME";
         case 2:
         case 11:
         case 12:
         default:
            throw new MatchError(BoxesRunTime.boxToInteger(i));
         case 3:
            return "(";
         case 4:
            return ")";
         case 5:
            return ",";
         case 6:
            return "*";
         case 7:
            return "+";
         case 8:
            return "?";
         case 9:
            return "|";
         case 10:
            return "END";
         case 13:
            return " ";
      }
   }
}
