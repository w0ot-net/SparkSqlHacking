package breeze.math;

import breeze.generic.UFunc;
import breeze.generic.UFunc$UImpl$mcDD$sp;
import breeze.generic.UFunc$UImpl$mcFD$sp;
import breeze.generic.UFunc$UImpl$mcID$sp;
import breeze.numerics.package$pow$powFloatFloatImpl$;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115c\u0001\u0003?~!\u0003\r\t!!\u0002\t\u000f\u0005]\u0004\u0001\"\u0001\u0002z!9\u0011\u0011\u0011\u0001\u0007\u0002\u0005\r\u0005bBAG\u0001\u0011\u0005\u0011q\u0012\u0005\b\u0003'\u0003a\u0011AAK\u000f\u001d\tY* E\u0001\u0003;3a\u0001`?\t\u0002\u0005}\u0005bBAY\r\u0011\u0005\u00111W\u0004\b\u0003k3\u00012AA\\\r\u001d\tYL\u0002E\u0001\u0003{Cq!!-\n\t\u0003\t\u0019\u000eC\u0004\u0002V&!\t!a6\t\u000f\u0005e\u0017\u0002\"\u0001\u0002X\"9\u00111\\\u0005\u0005\u0002\u0005u\u0007bBAu\u0013\u0011\u0005\u00111\u001e\u0005\b\u0003cLA\u0011AAz\u0011\u001d\tI0\u0003C\u0001\u0003wDqA!\u0001\n\t\u0003\u0011\u0019\u0001C\u0004\u0002\u0002&!\tA!\u0003\t\u000f\t=\u0011\u0002\"\u0001\u0003\u0012!9\u00111S\u0005\u0005\u0002\t]\u0001\"\u0003B\u000f\u0013\t\u0007I1\u0001B\u0010\u0011!\u0011\t%\u0003Q\u0001\n\t\u0005\u0002\"\u0003B\"\u0013\u0005\u0005I\u0011\u0002B#\u000f\u001d\u0011\tG\u0002E\u0002\u0005G2qA!\u001a\u0007\u0011\u0003\u00119\u0007C\u0004\u00022f!\tA!\u001d\t\u000f\u0005U\u0017\u0004\"\u0001\u0003t!9\u0011\u0011\\\r\u0005\u0002\tM\u0004bBAn3\u0011\u0005!Q\u000f\u0005\b\u0003SLB\u0011\u0001B>\u0011\u001d\t\t0\u0007C\u0001\u0005\u0003Cq!!?\u001a\t\u0003\u00119\tC\u0004\u0003\u0002e!\tA!$\t\u000f\u0005\u0005\u0015\u0004\"\u0001\u0003\u0014\"9!qB\r\u0005\u0002\te\u0005bBAJ3\u0011\u0005!q\u0014\u0005\n\u0005;I\"\u0019!C\u0002\u0005KC\u0001B!\u0011\u001aA\u0003%!q\u0015\u0005\n\u0005\u0007J\u0012\u0011!C\u0005\u0005\u000b:qA!,\u0007\u0011\u0007\u0011yKB\u0004\u00032\u001aA\tAa-\t\u000f\u0005E\u0016\u0006\"\u0001\u0003>\"9\u0011Q[\u0015\u0005\u0002\t}\u0006bBAmS\u0011\u0005!q\u0018\u0005\b\u00037LC\u0011\u0001Ba\u0011\u001d\tI/\u000bC\u0001\u0005\u000fDq!!=*\t\u0003\u0011i\rC\u0004\u0002z&\"\tAa5\t\u000f\t\u0005\u0011\u0006\"\u0001\u0003Z\"9\u0011\u0011Q\u0015\u0005\u0002\t}\u0007b\u0002B\bS\u0011\u0005!Q\u001d\u0005\b\u0003'KC\u0011\u0001Bv\u0011%\u0011i\"\u000bb\u0001\n\u0007\u0011\t\u0010\u0003\u0005\u0003B%\u0002\u000b\u0011\u0002Bz\u0011%\u0011\u0019%KA\u0001\n\u0013\u0011)eB\u0004\u0003z\u001aA\u0019Aa?\u0007\u000f\tuh\u0001#\u0001\u0003\u0000\"9\u0011\u0011W\u001d\u0005\u0002\r%\u0001bBAks\u0011\u000511\u0002\u0005\b\u00033LD\u0011AB\u0006\u0011\u001d\tY.\u000fC\u0001\u0007+Aq!!;:\t\u0003\u0019Y\u0002C\u0004\u0002rf\"\ta!\t\t\u000f\u0005e\u0018\b\"\u0001\u0004(!9!\u0011A\u001d\u0005\u0002\r5\u0002bBAAs\u0011\u000511\u0007\u0005\b\u0005\u001fID\u0011AB\u001d\u0011\u001d\t\u0019*\u000fC\u0001\u0007\u007fA\u0011B!\b:\u0005\u0004%\u0019a!\u0012\t\u0011\t\u0005\u0013\b)A\u0005\u0007\u000fB\u0011Ba\u0011:\u0003\u0003%IA!\u0012\b\u000f\r5c\u0001c\u0001\u0004P\u001991\u0011\u000b\u0004\t\u0002\rM\u0003bBAY\u0013\u0012\u00051Q\f\u0005\b\u0003+LE\u0011AB0\u0011\u001d\tI.\u0013C\u0001\u0007?Bq!a7J\t\u0003\u0019)\u0007C\u0004\u0002j&#\taa\u001b\t\u000f\u0005E\u0018\n\"\u0001\u0004r!9\u0011\u0011`%\u0005\u0002\r]\u0004b\u0002B\u0001\u0013\u0012\u00051Q\u0010\u0005\b\u0003\u0003KE\u0011ABB\u0011\u001d\u0011y!\u0013C\u0001\u0007\u0013Cq!a%J\t\u0003\u0019y\tC\u0004\u0004\u0016&#\tea&\t\u0013\tu\u0011J1A\u0005\u0004\r\u0005\u0006\u0002\u0003B!\u0013\u0002\u0006Iaa)\t\u0013\t\r\u0013*!A\u0005\n\t\u0015saBBU\r!\r11\u0016\u0004\b\u0007[3\u0001\u0012ABX\u0011\u001d\t\tL\u0017C\u0001\u0007sCq!!6[\t\u0003\u0019Y\fC\u0004\u0002Zj#\taa/\t\u000f\u0005m'\f\"\u0001\u0004>\"9\u0011\u0011\u001e.\u0005\u0002\r\r\u0007bBAy5\u0012\u00051\u0011\u001a\u0005\b\u0003sTF\u0011ABh\u0011\u001d\u0011\tA\u0017C\u0001\u0007+Dq!!![\t\u0003\u0019Y\u000eC\u0004\u0003\u0010i#\ta!9\t\u000f\u0005M%\f\"\u0001\u0004h\"91Q\u0013.\u0005B\r5\b\"\u0003B\u000f5\n\u0007I1AB{\u0011!\u0011\tE\u0017Q\u0001\n\r]\b\"\u0003B\"5\u0006\u0005I\u0011\u0002B#\u000f\u001d\u0019iP\u0002E\u0002\u0007\u007f4q\u0001\"\u0001\u0007\u0011\u0003!\u0019\u0001C\u0004\u00022.$\t\u0001b\u0002\t\u000f\u0005U7\u000e\"\u0001\u0005\n!9\u0011\u0011\\6\u0005\u0002\u0011%\u0001bBAnW\u0012\u0005A1\u0002\u0005\b\u0003S\\G\u0011\u0001C\t\u0011\u001d\t\tp\u001bC\u0001\t/Aq!!?l\t\u0003!i\u0002C\u0004\u0003\u0002-$\t\u0001b\t\t\u000f\u0005\u00055\u000e\"\u0001\u0005*!9!qB6\u0005\u0002\u0011=\u0002bBAJW\u0012\u0005AQ\u0007\u0005\b\u0007+[G\u0011\tC\u001e\u0011%\u0011ib\u001bb\u0001\n\u0007!\u0019\u0005\u0003\u0005\u0003B-\u0004\u000b\u0011\u0002C#\u0011%\u0011\u0019e[A\u0001\n\u0013\u0011)\u0005C\u0005\u0003D\u0019\t\t\u0011\"\u0003\u0003F\t)a)[3mI*\u0011ap`\u0001\u0005[\u0006$\bN\u0003\u0002\u0002\u0002\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003\u0002\b\u0005\u00052#\u0002\u0001\u0002\n\u0005U\u0001\u0003BA\u0006\u0003#i!!!\u0004\u000b\u0005\u0005=\u0011!B:dC2\f\u0017\u0002BA\n\u0003\u001b\u0011a!\u00118z%\u00164\u0007CBA\f\u00033\ti\"D\u0001~\u0013\r\tY\" \u0002\u0005%&tw\r\u0005\u0003\u0002 \u0005\u0005B\u0002\u0001\u0003\f\u0003G\u0001\u0001\u0015!A\u0001\u0006\u0004\t)CA\u0001W#\u0011\t9#!\f\u0011\t\u0005-\u0011\u0011F\u0005\u0005\u0003W\tiAA\u0004O_RD\u0017N\\4\u0011\t\u0005-\u0011qF\u0005\u0005\u0003c\tiAA\u0002B]fDc\"!\t\u00026\u0005m\u0012qJA-\u0003G\ni\u0007\u0005\u0003\u0002\f\u0005]\u0012\u0002BA\u001d\u0003\u001b\u00111b\u001d9fG&\fG.\u001b>fIFJ1%!\u0010\u0002@\u0005\r\u0013\u0011\t\b\u0005\u0003\u0017\ty$\u0003\u0003\u0002B\u00055\u0011aA%oiF:A%!\u0012\u0002N\u0005=a\u0002BA$\u0003\u001bj!!!\u0013\u000b\t\u0005-\u00131A\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005=\u0011'C\u0012\u0002R\u0005M\u0013qKA+\u001d\u0011\tY!a\u0015\n\t\u0005U\u0013QB\u0001\u0006'\"|'\u000f^\u0019\bI\u0005\u0015\u0013QJA\bc%\u0019\u00131LA/\u0003C\nyF\u0004\u0003\u0002\f\u0005u\u0013\u0002BA0\u0003\u001b\tA\u0001T8oOF:A%!\u0012\u0002N\u0005=\u0011'C\u0012\u0002f\u0005\u001d\u00141NA5\u001d\u0011\tY!a\u001a\n\t\u0005%\u0014QB\u0001\u0006\r2|\u0017\r^\u0019\bI\u0005\u0015\u0013QJA\bc%\u0019\u0013qNA9\u0003k\n\u0019H\u0004\u0003\u0002\f\u0005E\u0014\u0002BA:\u0003\u001b\ta\u0001R8vE2,\u0017g\u0002\u0013\u0002F\u00055\u0013qB\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\u0005m\u0004\u0003BA\u0006\u0003{JA!a \u0002\u000e\t!QK\\5u\u0003\u0011!C-\u001b<\u0015\r\u0005u\u0011QQAE\u0011\u001d\t9I\u0001a\u0001\u0003;\t\u0011!\u0019\u0005\b\u0003\u0017\u0013\u0001\u0019AA\u000f\u0003\u0005\u0011\u0017aB5om\u0016\u00148/\u001a\u000b\u0005\u0003;\t\t\nC\u0004\u0002\b\u000e\u0001\r!!\b\u0002\u0007A|w\u000f\u0006\u0004\u0002\u001e\u0005]\u0015\u0011\u0014\u0005\b\u0003\u000f#\u0001\u0019AA\u000f\u0011\u001d\tY\t\u0002a\u0001\u0003;\tQAR5fY\u0012\u00042!a\u0006\u0007'\u00151\u0011\u0011BAQ!\u0011\t\u0019+!,\u000e\u0005\u0005\u0015&\u0002BAT\u0003S\u000b!![8\u000b\u0005\u0005-\u0016\u0001\u00026bm\u0006LA!a,\u0002&\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\"!!(\u0002\u0011\u0019LW\r\u001c3J]R\u00042!!/\n\u001b\u00051!\u0001\u00034jK2$\u0017J\u001c;\u0014\u000f%\tI!a0\u0002HB)\u0011q\u0003\u0001\u0002BB!\u00111BAb\u0013\u0011\t)-!\u0004\u0003\u0007%sG\u000f\u0005\u0003\u0002J\u0006=g\u0002BA#\u0003\u0017LA!!4\u0002\u000e\u00059\u0001/Y2lC\u001e,\u0017\u0002BAX\u0003#TA!!4\u0002\u000eQ\u0011\u0011qW\u0001\u0005u\u0016\u0014x.\u0006\u0002\u0002B\u0006\u0019qN\\3\u0002\r\u0011*\u0017\u000fJ3r)\u0019\ty.!:\u0002hB!\u00111BAq\u0013\u0011\t\u0019/!\u0004\u0003\u000f\t{w\u000e\\3b]\"9\u0011qQ\u0007A\u0002\u0005\u0005\u0007bBAF\u001b\u0001\u0007\u0011\u0011Y\u0001\tI\t\fgn\u001a\u0013fcR1\u0011q\\Aw\u0003_Dq!a\"\u000f\u0001\u0004\t\t\rC\u0004\u0002\f:\u0001\r!!1\u0002\u000b\u0011\u0002H.^:\u0015\r\u0005\u0005\u0017Q_A|\u0011\u001d\t9i\u0004a\u0001\u0003\u0003Dq!a#\u0010\u0001\u0004\t\t-\u0001\u0004%[&tWo\u001d\u000b\u0007\u0003\u0003\fi0a@\t\u000f\u0005\u001d\u0005\u00031\u0001\u0002B\"9\u00111\u0012\tA\u0002\u0005\u0005\u0017A\u0002\u0013uS6,7\u000f\u0006\u0004\u0002B\n\u0015!q\u0001\u0005\b\u0003\u000f\u000b\u0002\u0019AAa\u0011\u001d\tY)\u0005a\u0001\u0003\u0003$b!!1\u0003\f\t5\u0001bBAD%\u0001\u0007\u0011\u0011\u0019\u0005\b\u0003\u0017\u0013\u0002\u0019AAa\u0003!!\u0003/\u001a:dK:$HCBAa\u0005'\u0011)\u0002C\u0004\u0002\bN\u0001\r!!1\t\u000f\u0005-5\u00031\u0001\u0002BR1\u0011\u0011\u0019B\r\u00057Aq!a\"\u0015\u0001\u0004\t\t\rC\u0004\u0002\fR\u0001\r!!1\u0002\u00119|'/\\%na2,\"A!\t\u0011\u0011\t\r\"qFAa\u0005wqAA!\n\u0003,5\u0011!q\u0005\u0006\u0004\u0005Sy\u0018A\u00027j]\u0006dw-\u0003\u0003\u0003.\t\u001d\u0012\u0001\u00028pe6LAA!\r\u00034\t!\u0011*\u001c9m\u0013\u0011\u0011)Da\u000e\u0003\u000bU3UO\\2\u000b\u0007\ter0A\u0004hK:,'/[2\u0011\t\u0005-!QH\u0005\u0005\u0005\u007f\tiA\u0001\u0004E_V\u0014G.Z\u0001\n]>\u0014X.S7qY\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0012\u0011\t\t%#qJ\u0007\u0003\u0005\u0017RAA!\u0014\u0002*\u0006!A.\u00198h\u0013\u0011\u0011\tFa\u0013\u0003\r=\u0013'.Z2uQ\u001dI!Q\u000bB.\u0005;\u0002B!a\u0003\u0003X%!!\u0011LA\u0007\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002Q\u001dA!Q\u000bB.\u0005;\n!BZ5fY\u0012\u001c\u0006n\u001c:u!\r\tI,\u0007\u0002\u000bM&,G\u000eZ*i_J$8cB\r\u0002\n\t%\u0014q\u0019\t\u0006\u0003/\u0001!1\u000e\t\u0005\u0003\u0017\u0011i'\u0003\u0003\u0003p\u00055!!B*i_J$HC\u0001B2+\t\u0011Y\u0007\u0006\u0004\u0002`\n]$\u0011\u0010\u0005\b\u0003\u000fk\u0002\u0019\u0001B6\u0011\u001d\tY)\ba\u0001\u0005W\"b!a8\u0003~\t}\u0004bBAD=\u0001\u0007!1\u000e\u0005\b\u0003\u0017s\u0002\u0019\u0001B6)\u0019\u0011YGa!\u0003\u0006\"9\u0011qQ\u0010A\u0002\t-\u0004bBAF?\u0001\u0007!1\u000e\u000b\u0007\u0005W\u0012IIa#\t\u000f\u0005\u001d\u0005\u00051\u0001\u0003l!9\u00111\u0012\u0011A\u0002\t-DC\u0002B6\u0005\u001f\u0013\t\nC\u0004\u0002\b\u0006\u0002\rAa\u001b\t\u000f\u0005-\u0015\u00051\u0001\u0003lQ1!1\u000eBK\u0005/Cq!a\"#\u0001\u0004\u0011Y\u0007C\u0004\u0002\f\n\u0002\rAa\u001b\u0015\r\t-$1\u0014BO\u0011\u001d\t9i\ta\u0001\u0005WBq!a#$\u0001\u0004\u0011Y\u0007\u0006\u0004\u0003l\t\u0005&1\u0015\u0005\b\u0003\u000f#\u0003\u0019\u0001B6\u0011\u001d\tY\t\na\u0001\u0005W*\"Aa*\u0011\u0011\t\r\"q\u0006B6\u0005wAs!\u0007B+\u00057\u0012i\u0006K\u0004\u0019\u0005+\u0012YF!\u0018\u0002\u0013\u0019LW\r\u001c3M_:<\u0007cAA]S\tIa-[3mI2{gnZ\n\bS\u0005%!QWAd!\u0015\t9\u0002\u0001B\\!\u0011\tYA!/\n\t\tm\u0016Q\u0002\u0002\u0005\u0019>tw\r\u0006\u0002\u00030V\u0011!q\u0017\u000b\u0007\u0003?\u0014\u0019M!2\t\u000f\u0005\u001dU\u00061\u0001\u00038\"9\u00111R\u0017A\u0002\t]FCBAp\u0005\u0013\u0014Y\rC\u0004\u0002\b:\u0002\rAa.\t\u000f\u0005-e\u00061\u0001\u00038R1!q\u0017Bh\u0005#Dq!a\"0\u0001\u0004\u00119\fC\u0004\u0002\f>\u0002\rAa.\u0015\r\t]&Q\u001bBl\u0011\u001d\t9\t\ra\u0001\u0005oCq!a#1\u0001\u0004\u00119\f\u0006\u0004\u00038\nm'Q\u001c\u0005\b\u0003\u000f\u000b\u0004\u0019\u0001B\\\u0011\u001d\tY)\ra\u0001\u0005o#bAa.\u0003b\n\r\bbBADe\u0001\u0007!q\u0017\u0005\b\u0003\u0017\u0013\u0004\u0019\u0001B\\)\u0019\u00119La:\u0003j\"9\u0011qQ\u001aA\u0002\t]\u0006bBAFg\u0001\u0007!q\u0017\u000b\u0007\u0005o\u0013iOa<\t\u000f\u0005\u001dE\u00071\u0001\u00038\"9\u00111\u0012\u001bA\u0002\t]VC\u0001Bz!!\u0011\u0019Ca\f\u00038\nm\u0002fB\u0015\u0003V\tm#Q\f\u0015\bQ\tU#1\fB/\u0003-1\u0017.\u001a7e\u0005&<\u0017J\u001c;\u0011\u0007\u0005e\u0016HA\u0006gS\u0016dGMQ5h\u0013:$8cB\u001d\u0002\n\r\u0005\u0011q\u0019\t\u0006\u0003/\u000111\u0001\t\u0005\u0003\u0013\u001c)!\u0003\u0003\u0004\b\u0005E'A\u0002\"jO&sG\u000f\u0006\u0002\u0003|V\u00111Q\u0002\t\u0005\u0007\u001f\u0019\u0019\"\u0004\u0002\u0004\u0012)\u0019a0!\u0004\n\t\r\u001d1\u0011\u0003\u000b\u0007\u0003?\u001c9b!\u0007\t\u000f\u0005\u001dU\b1\u0001\u0004\u0004!9\u00111R\u001fA\u0002\r\rACBAp\u0007;\u0019y\u0002C\u0004\u0002\bz\u0002\raa\u0001\t\u000f\u0005-e\b1\u0001\u0004\u0004Q11QBB\u0012\u0007KAq!a\"@\u0001\u0004\u0019\u0019\u0001C\u0004\u0002\f~\u0002\raa\u0001\u0015\r\r51\u0011FB\u0016\u0011\u001d\t9\t\u0011a\u0001\u0007\u0007Aq!a#A\u0001\u0004\u0019\u0019\u0001\u0006\u0004\u0004\u000e\r=2\u0011\u0007\u0005\b\u0003\u000f\u000b\u0005\u0019AB\u0002\u0011\u001d\tY)\u0011a\u0001\u0007\u0007!ba!\u0004\u00046\r]\u0002bBAD\u0005\u0002\u000711\u0001\u0005\b\u0003\u0017\u0013\u0005\u0019AB\u0002)\u0019\u0019iaa\u000f\u0004>!9\u0011qQ\"A\u0002\r\r\u0001bBAF\u0007\u0002\u000711\u0001\u000b\u0007\u0007\u0007\u0019\tea\u0011\t\u000f\u0005\u001dE\t1\u0001\u0004\u0004!9\u00111\u0012#A\u0002\r\rQCAB$!!\u0011\u0019Ca\f\u0004\u0004\tm\u0002fB\u001d\u0003V\tm#Q\f\u0015\bq\tU#1\fB/\u0003=1\u0017.\u001a7e\u0005&<G)Z2j[\u0006d\u0007cAA]\u0013\nya-[3mI\nKw\rR3dS6\fGnE\u0004J\u0003\u0013\u0019)&a2\u0011\u000b\u0005]\u0001aa\u0016\u0011\t\u0005%7\u0011L\u0005\u0005\u00077\n\tN\u0001\u0006CS\u001e$UmY5nC2$\"aa\u0014\u0016\u0005\r\u0005\u0004\u0003BB\b\u0007GJAaa\u0017\u0004\u0012Q1\u0011q\\B4\u0007SBq!a\"N\u0001\u0004\u00199\u0006C\u0004\u0002\f6\u0003\raa\u0016\u0015\r\u0005}7QNB8\u0011\u001d\t9I\u0014a\u0001\u0007/Bq!a#O\u0001\u0004\u00199\u0006\u0006\u0004\u0004b\rM4Q\u000f\u0005\b\u0003\u000f{\u0005\u0019AB,\u0011\u001d\tYi\u0014a\u0001\u0007/\"ba!\u0019\u0004z\rm\u0004bBAD!\u0002\u00071q\u000b\u0005\b\u0003\u0017\u0003\u0006\u0019AB,)\u0019\u0019\tga \u0004\u0002\"9\u0011qQ)A\u0002\r]\u0003bBAF#\u0002\u00071q\u000b\u000b\u0007\u0007C\u001a)ia\"\t\u000f\u0005\u001d%\u000b1\u0001\u0004X!9\u00111\u0012*A\u0002\r]CCBB1\u0007\u0017\u001bi\tC\u0004\u0002\bN\u0003\raa\u0016\t\u000f\u0005-5\u000b1\u0001\u0004XQ11qKBI\u0007'Cq!a\"U\u0001\u0004\u00199\u0006C\u0004\u0002\fR\u0003\raa\u0016\u0002\u000b\rdwn]3\u0015\u0011\u0005}7\u0011TBN\u0007;Cq!a\"V\u0001\u0004\u00199\u0006C\u0004\u0002\fV\u0003\raa\u0016\t\u0013\r}U\u000b%AA\u0002\tm\u0012!\u0003;pY\u0016\u0014\u0018M\\2f+\t\u0019\u0019\u000b\u0005\u0005\u0003$\t=2q\u000bB\u001eQ\u001dI%Q\u000bB.\u0005;Bs\u0001\u0013B+\u00057\u0012i&\u0001\u0006gS\u0016dGM\u00127pCR\u00042!!/[\u0005)1\u0017.\u001a7e\r2|\u0017\r^\n\b5\u0006%1\u0011WAd!\u0015\t9\u0002ABZ!\u0011\tYa!.\n\t\r]\u0016Q\u0002\u0002\u0006\r2|\u0017\r\u001e\u000b\u0003\u0007W+\"aa-\u0015\r\u0005}7qXBa\u0011\u001d\t9I\u0018a\u0001\u0007gCq!a#_\u0001\u0004\u0019\u0019\f\u0006\u0004\u0002`\u000e\u00157q\u0019\u0005\b\u0003\u000f{\u0006\u0019ABZ\u0011\u001d\tYi\u0018a\u0001\u0007g#baa-\u0004L\u000e5\u0007bBADA\u0002\u000711\u0017\u0005\b\u0003\u0017\u0003\u0007\u0019ABZ)\u0019\u0019\u0019l!5\u0004T\"9\u0011qQ1A\u0002\rM\u0006bBAFC\u0002\u000711\u0017\u000b\u0007\u0007g\u001b9n!7\t\u000f\u0005\u001d%\r1\u0001\u00044\"9\u00111\u00122A\u0002\rMFCBBZ\u0007;\u001cy\u000eC\u0004\u0002\b\u000e\u0004\raa-\t\u000f\u0005-5\r1\u0001\u00044R111WBr\u0007KDq!a\"e\u0001\u0004\u0019\u0019\fC\u0004\u0002\f\u0012\u0004\raa-\u0015\r\rM6\u0011^Bv\u0011\u001d\t9)\u001aa\u0001\u0007gCq!a#f\u0001\u0004\u0019\u0019\f\u0006\u0005\u0002`\u000e=8\u0011_Bz\u0011\u001d\t9I\u001aa\u0001\u0007gCq!a#g\u0001\u0004\u0019\u0019\fC\u0005\u0004 \u001a\u0004\n\u00111\u0001\u0003<U\u00111q\u001f\t\t\u0005G\u0011yca-\u0003<!:!L!\u0016\u0003\\\tu\u0003fB-\u0003V\tm#QL\u0001\fM&,G\u000e\u001a#pk\ndW\rE\u0002\u0002:.\u00141BZ5fY\u0012$u.\u001e2mKN91.!\u0003\u0005\u0006\u0005\u001d\u0007#BA\f\u0001\tmBCAB\u0000+\t\u0011Y\u0004\u0006\u0004\u0002`\u00125Aq\u0002\u0005\b\u0003\u000f{\u0007\u0019\u0001B\u001e\u0011\u001d\tYi\u001ca\u0001\u0005w!b!a8\u0005\u0014\u0011U\u0001bBADa\u0002\u0007!1\b\u0005\b\u0003\u0017\u0003\b\u0019\u0001B\u001e)\u0019\u0011Y\u0004\"\u0007\u0005\u001c!9\u0011qQ9A\u0002\tm\u0002bBAFc\u0002\u0007!1\b\u000b\u0007\u0005w!y\u0002\"\t\t\u000f\u0005\u001d%\u000f1\u0001\u0003<!9\u00111\u0012:A\u0002\tmBC\u0002B\u001e\tK!9\u0003C\u0004\u0002\bN\u0004\rAa\u000f\t\u000f\u0005-5\u000f1\u0001\u0003<Q1!1\bC\u0016\t[Aq!a\"u\u0001\u0004\u0011Y\u0004C\u0004\u0002\fR\u0004\rAa\u000f\u0015\r\tmB\u0011\u0007C\u001a\u0011\u001d\t9)\u001ea\u0001\u0005wAq!a#v\u0001\u0004\u0011Y\u0004\u0006\u0004\u0003<\u0011]B\u0011\b\u0005\b\u0003\u000f3\b\u0019\u0001B\u001e\u0011\u001d\tYI\u001ea\u0001\u0005w!\u0002\"a8\u0005>\u0011}B\u0011\t\u0005\b\u0003\u000f;\b\u0019\u0001B\u001e\u0011\u001d\tYi\u001ea\u0001\u0005wA\u0011ba(x!\u0003\u0005\rAa\u000f\u0016\u0005\u0011\u0015\u0003\u0003\u0003B\u0012\u0005_\u0011YDa\u000f)\u000f-\u0014)Fa\u0017\u0005JyAQ6WwJV2\bL\u001dK\u0004k\u0005+\u0012Y\u0006\"\u0013"
)
public interface Field extends Ring {
   Object $div(final Object a, final Object b);

   default Object inverse(final Object a) {
      return this.$div(this.one(), a);
   }

   Object pow(final Object a, final Object b);

   default double $div$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.$div(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   default float $div$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.$div(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   default int $div$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.$div(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   default long $div$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.$div(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   default short $div$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.$div(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   default double inverse$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.inverse(BoxesRunTime.boxToDouble(a)));
   }

   default float inverse$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.inverse(BoxesRunTime.boxToFloat(a)));
   }

   default int inverse$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.inverse(BoxesRunTime.boxToInteger(a)));
   }

   default long inverse$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.inverse(BoxesRunTime.boxToLong(a)));
   }

   default short inverse$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.inverse(BoxesRunTime.boxToShort(a)));
   }

   default double pow$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.pow(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   default float pow$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.pow(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   default int pow$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.pow(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   default long pow$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.pow(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   default short pow$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.pow(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   static void $init$(final Field $this) {
   }

   public static class fieldInt$ implements Field$mcI$sp {
      public static final fieldInt$ MODULE$ = new fieldInt$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc$UImpl$mcID$sp() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final int v) {
               return this.apply$mcID$sp(v);
            }

            public double apply$mcID$sp(final int v) {
               return (double).MODULE$.abs(v);
            }
         };
      }

      public int inverse(final int a) {
         return Field$mcI$sp.inverse$(this, a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field$mcI$sp.inverse$mcI$sp$(this, a);
      }

      public int negate(final int s) {
         return Ring$mcI$sp.negate$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring$mcI$sp.negate$mcI$sp$(this, s);
      }

      public double sNorm(final int a) {
         return Ring$mcI$sp.sNorm$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring$mcI$sp.sNorm$mcI$sp$(this, a);
      }

      public boolean close(final int a, final int b, final double tolerance) {
         return Semiring$mcI$sp.close$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring$mcI$sp.close$mcI$sp$(this, a, b, tolerance);
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public int zero() {
         return this.zero$mcI$sp();
      }

      public int one() {
         return this.one$mcI$sp();
      }

      public boolean $eq$eq(final int a, final int b) {
         return this.$eq$eq$mcI$sp(a, b);
      }

      public boolean $bang$eq(final int a, final int b) {
         return this.$bang$eq$mcI$sp(a, b);
      }

      public int $plus(final int a, final int b) {
         return this.$plus$mcI$sp(a, b);
      }

      public int $minus(final int a, final int b) {
         return this.$minus$mcI$sp(a, b);
      }

      public int $times(final int a, final int b) {
         return this.$times$mcI$sp(a, b);
      }

      public int $div(final int a, final int b) {
         return this.$div$mcI$sp(a, b);
      }

      public int $percent(final int a, final int b) {
         return this.$percent$mcI$sp(a, b);
      }

      public int pow(final int a, final int b) {
         return this.pow$mcI$sp(a, b);
      }

      public UFunc.UImpl normImpl() {
         return this.normImpl$mcI$sp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldInt$.class);
      }

      public int zero$mcI$sp() {
         return 0;
      }

      public int one$mcI$sp() {
         return 1;
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return a == b;
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return a != b;
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return a + b;
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return a - b;
      }

      public int $times$mcI$sp(final int a, final int b) {
         return a * b;
      }

      public int $div$mcI$sp(final int a, final int b) {
         return a / b;
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return a % b;
      }

      public int pow$mcI$sp(final int a, final int b) {
         return (int).MODULE$.pow((double)a, (double)b);
      }

      public UFunc.UImpl normImpl$mcI$sp() {
         return normImpl;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static class fieldShort$ implements Field$mcS$sp {
      public static final fieldShort$ MODULE$ = new fieldShort$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc.UImpl() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final short v) {
               return (double).MODULE$.abs(v);
            }
         };
      }

      public short inverse(final short a) {
         return Field$mcS$sp.inverse$(this, a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field$mcS$sp.inverse$mcS$sp$(this, a);
      }

      public short negate(final short s) {
         return Ring$mcS$sp.negate$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring$mcS$sp.negate$mcS$sp$(this, s);
      }

      public double sNorm(final short a) {
         return Ring$mcS$sp.sNorm$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring$mcS$sp.sNorm$mcS$sp$(this, a);
      }

      public boolean close(final short a, final short b, final double tolerance) {
         return Semiring$mcS$sp.close$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring$mcS$sp.close$mcS$sp$(this, a, b, tolerance);
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public short zero() {
         return this.zero$mcS$sp();
      }

      public short one() {
         return this.one$mcS$sp();
      }

      public boolean $eq$eq(final short a, final short b) {
         return this.$eq$eq$mcS$sp(a, b);
      }

      public boolean $bang$eq(final short a, final short b) {
         return this.$bang$eq$mcS$sp(a, b);
      }

      public short $plus(final short a, final short b) {
         return this.$plus$mcS$sp(a, b);
      }

      public short $minus(final short a, final short b) {
         return this.$minus$mcS$sp(a, b);
      }

      public short $times(final short a, final short b) {
         return this.$times$mcS$sp(a, b);
      }

      public short $div(final short a, final short b) {
         return this.$div$mcS$sp(a, b);
      }

      public short $percent(final short a, final short b) {
         return this.$percent$mcS$sp(a, b);
      }

      public short pow(final short a, final short b) {
         return this.pow$mcS$sp(a, b);
      }

      public UFunc.UImpl normImpl() {
         return this.normImpl$mcS$sp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldShort$.class);
      }

      public short zero$mcS$sp() {
         return (short)0;
      }

      public short one$mcS$sp() {
         return (short)1;
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return a == b;
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return a != b;
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return (short)(a + b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return (short)(a - b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return (short)(a * b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return (short)(a / b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return (short)(a % b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return (short)((int).MODULE$.pow((double)a, (double)b));
      }

      public UFunc.UImpl normImpl$mcS$sp() {
         return normImpl;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static class fieldLong$ implements Field$mcJ$sp {
      public static final fieldLong$ MODULE$ = new fieldLong$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc.UImpl() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final long v) {
               return (double).MODULE$.abs(v);
            }
         };
      }

      public long inverse(final long a) {
         return Field$mcJ$sp.inverse$(this, a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field$mcJ$sp.inverse$mcJ$sp$(this, a);
      }

      public long negate(final long s) {
         return Ring$mcJ$sp.negate$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring$mcJ$sp.negate$mcJ$sp$(this, s);
      }

      public double sNorm(final long a) {
         return Ring$mcJ$sp.sNorm$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring$mcJ$sp.sNorm$mcJ$sp$(this, a);
      }

      public boolean close(final long a, final long b, final double tolerance) {
         return Semiring$mcJ$sp.close$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring$mcJ$sp.close$mcJ$sp$(this, a, b, tolerance);
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public long zero() {
         return this.zero$mcJ$sp();
      }

      public long one() {
         return this.one$mcJ$sp();
      }

      public boolean $eq$eq(final long a, final long b) {
         return this.$eq$eq$mcJ$sp(a, b);
      }

      public boolean $bang$eq(final long a, final long b) {
         return this.$bang$eq$mcJ$sp(a, b);
      }

      public long $plus(final long a, final long b) {
         return this.$plus$mcJ$sp(a, b);
      }

      public long $minus(final long a, final long b) {
         return this.$minus$mcJ$sp(a, b);
      }

      public long $times(final long a, final long b) {
         return this.$times$mcJ$sp(a, b);
      }

      public long $div(final long a, final long b) {
         return this.$div$mcJ$sp(a, b);
      }

      public long $percent(final long a, final long b) {
         return this.$percent$mcJ$sp(a, b);
      }

      public long pow(final long a, final long b) {
         return this.pow$mcJ$sp(a, b);
      }

      public UFunc.UImpl normImpl() {
         return this.normImpl$mcJ$sp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldLong$.class);
      }

      public long zero$mcJ$sp() {
         return 0L;
      }

      public long one$mcJ$sp() {
         return 1L;
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return a == b;
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return a != b;
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return a + b;
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return a - b;
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return a * b;
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return a / b;
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return a % b;
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return (long).MODULE$.pow((double)a, (double)b);
      }

      public UFunc.UImpl normImpl$mcJ$sp() {
         return normImpl;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static class fieldBigInt$ implements Field {
      public static final fieldBigInt$ MODULE$ = new fieldBigInt$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc.UImpl() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final BigInt v) {
               return v.abs().toDouble();
            }
         };
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public Object inverse(final Object a) {
         return Field.super.inverse(a);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public Object negate(final Object s) {
         return Ring.negate$(this, s);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm(final Object a) {
         return Ring.sNorm$(this, a);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public boolean specInstance$() {
         return Ring.specInstance$$(this);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close(final Object a, final Object b, final double tolerance) {
         return Semiring.close$(this, a, b, tolerance);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public BigInt zero() {
         return scala.math.BigInt..MODULE$.long2bigInt(0L);
      }

      public BigInt one() {
         return scala.math.BigInt..MODULE$.long2bigInt(1L);
      }

      public boolean $eq$eq(final BigInt a, final BigInt b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b == null) {
                  break label23;
               }
            } else if (a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean $bang$eq(final BigInt a, final BigInt b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b != null) {
                  break label23;
               }
            } else if (!a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public BigInt $plus(final BigInt a, final BigInt b) {
         return a.$plus(b);
      }

      public BigInt $minus(final BigInt a, final BigInt b) {
         return a.$minus(b);
      }

      public BigInt $times(final BigInt a, final BigInt b) {
         return a.$times(b);
      }

      public BigInt $div(final BigInt a, final BigInt b) {
         return a.$div(b);
      }

      public BigInt $percent(final BigInt a, final BigInt b) {
         return a.$percent(b);
      }

      public BigInt pow(final BigInt a, final BigInt b) {
         return a.pow(b.toInt());
      }

      public UFunc.UImpl normImpl() {
         return normImpl;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldBigInt$.class);
      }
   }

   public static class fieldBigDecimal$ implements Field {
      public static final fieldBigDecimal$ MODULE$ = new fieldBigDecimal$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc.UImpl() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final BigDecimal v) {
               return v.abs().toDouble();
            }
         };
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public Object inverse(final Object a) {
         return Field.super.inverse(a);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public Object negate(final Object s) {
         return Ring.negate$(this, s);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm(final Object a) {
         return Ring.sNorm$(this, a);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public boolean specInstance$() {
         return Ring.specInstance$$(this);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public BigDecimal zero() {
         return scala.math.BigDecimal..MODULE$.long2bigDecimal(0L);
      }

      public BigDecimal one() {
         return scala.math.BigDecimal..MODULE$.long2bigDecimal(1L);
      }

      public boolean $eq$eq(final BigDecimal a, final BigDecimal b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b == null) {
                  break label23;
               }
            } else if (a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean $bang$eq(final BigDecimal a, final BigDecimal b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b != null) {
                  break label23;
               }
            } else if (!a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public BigDecimal $plus(final BigDecimal a, final BigDecimal b) {
         return a.$plus(b);
      }

      public BigDecimal $minus(final BigDecimal a, final BigDecimal b) {
         return a.$minus(b);
      }

      public BigDecimal $times(final BigDecimal a, final BigDecimal b) {
         return a.$times(b);
      }

      public BigDecimal $div(final BigDecimal a, final BigDecimal b) {
         return a.$div(b);
      }

      public BigDecimal $percent(final BigDecimal a, final BigDecimal b) {
         return a.$percent(b);
      }

      public BigDecimal pow(final BigDecimal a, final BigDecimal b) {
         return a.pow(b.toInt());
      }

      public boolean close(final BigDecimal a, final BigDecimal b, final double tolerance) {
         return a.$minus(b).abs().$less$eq(scala.math.BigDecimal..MODULE$.double2bigDecimal(tolerance).$times(a.abs().max(b.abs())));
      }

      public UFunc.UImpl normImpl() {
         return normImpl;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldBigDecimal$.class);
      }
   }

   public static class fieldFloat$ implements Field$mcF$sp {
      public static final fieldFloat$ MODULE$ = new fieldFloat$();
      private static final long serialVersionUID = 1L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc$UImpl$mcFD$sp() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final float v) {
               return this.apply$mcFD$sp(v);
            }

            public double apply$mcFD$sp(final float v) {
               return (double).MODULE$.abs(v);
            }
         };
      }

      public float inverse(final float a) {
         return Field$mcF$sp.inverse$(this, a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field$mcF$sp.inverse$mcF$sp$(this, a);
      }

      public float negate(final float s) {
         return Ring$mcF$sp.negate$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring$mcF$sp.negate$mcF$sp$(this, s);
      }

      public double sNorm(final float a) {
         return Ring$mcF$sp.sNorm$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring$mcF$sp.sNorm$mcF$sp$(this, a);
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.super.$div$mcD$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.super.inverse$mcD$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.super.pow$mcD$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public float zero() {
         return this.zero$mcF$sp();
      }

      public float one() {
         return this.one$mcF$sp();
      }

      public boolean $eq$eq(final float a, final float b) {
         return this.$eq$eq$mcF$sp(a, b);
      }

      public boolean $bang$eq(final float a, final float b) {
         return this.$bang$eq$mcF$sp(a, b);
      }

      public float $plus(final float a, final float b) {
         return this.$plus$mcF$sp(a, b);
      }

      public float $minus(final float a, final float b) {
         return this.$minus$mcF$sp(a, b);
      }

      public float $times(final float a, final float b) {
         return this.$times$mcF$sp(a, b);
      }

      public float $div(final float a, final float b) {
         return this.$div$mcF$sp(a, b);
      }

      public float $percent(final float a, final float b) {
         return this.$percent$mcF$sp(a, b);
      }

      public float pow(final float a, final float b) {
         return this.pow$mcF$sp(a, b);
      }

      public boolean close(final float a, final float b, final double tolerance) {
         return this.close$mcF$sp(a, b, tolerance);
      }

      public UFunc.UImpl normImpl() {
         return this.normImpl$mcF$sp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldFloat$.class);
      }

      public float zero$mcF$sp() {
         return 0.0F;
      }

      public float one$mcF$sp() {
         return 1.0F;
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return a == b;
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return a != b;
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return a + b;
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return a - b;
      }

      public float $times$mcF$sp(final float a, final float b) {
         return a * b;
      }

      public float $div$mcF$sp(final float a, final float b) {
         return a / b;
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return a % b;
      }

      public float pow$mcF$sp(final float a, final float b) {
         return breeze.numerics.package.pow$.MODULE$.apply$mFFFc$sp(a, b, package$pow$powFloatFloatImpl$.MODULE$);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         float diff = .MODULE$.abs(a - b);
         return a == b || (double)diff <= (double).MODULE$.max(scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(a)), scala.runtime.RichFloat..MODULE$.abs$extension(scala.Predef..MODULE$.floatWrapper(b))) * tolerance || (a == (float)0 || b == (float)0 || diff < Float.MIN_NORMAL) && (double)diff < tolerance * (double)10 * (double)Float.MIN_NORMAL;
      }

      public UFunc.UImpl normImpl$mcF$sp() {
         return normImpl;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static class fieldDouble$ implements Field$mcD$sp {
      public static final fieldDouble$ MODULE$ = new fieldDouble$();
      private static final long serialVersionUID = -5955467582882664220L;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         normImpl = new UFunc$UImpl$mcDD$sp() {
            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final double v) {
               return this.apply$mcDD$sp(v);
            }

            public double apply$mcDD$sp(final double v) {
               return .MODULE$.abs(v);
            }
         };
      }

      public double inverse(final double a) {
         return Field$mcD$sp.inverse$(this, a);
      }

      public double inverse$mcD$sp(final double a) {
         return Field$mcD$sp.inverse$mcD$sp$(this, a);
      }

      public double negate(final double s) {
         return Ring$mcD$sp.negate$(this, s);
      }

      public double negate$mcD$sp(final double s) {
         return Ring$mcD$sp.negate$mcD$sp$(this, s);
      }

      public double sNorm(final double a) {
         return Ring$mcD$sp.sNorm$(this, a);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring$mcD$sp.sNorm$mcD$sp$(this, a);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.super.$div$mcF$sp(a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.super.$div$mcI$sp(a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.super.$div$mcJ$sp(a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.super.$div$mcS$sp(a, b);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.super.inverse$mcF$sp(a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.super.inverse$mcI$sp(a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.super.inverse$mcJ$sp(a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.super.inverse$mcS$sp(a);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.super.pow$mcF$sp(a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.super.pow$mcI$sp(a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.super.pow$mcJ$sp(a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.super.pow$mcS$sp(a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public double zero() {
         return this.zero$mcD$sp();
      }

      public double one() {
         return this.one$mcD$sp();
      }

      public boolean $eq$eq(final double a, final double b) {
         return this.$eq$eq$mcD$sp(a, b);
      }

      public boolean $bang$eq(final double a, final double b) {
         return this.$bang$eq$mcD$sp(a, b);
      }

      public double $plus(final double a, final double b) {
         return this.$plus$mcD$sp(a, b);
      }

      public double $minus(final double a, final double b) {
         return this.$minus$mcD$sp(a, b);
      }

      public double $times(final double a, final double b) {
         return this.$times$mcD$sp(a, b);
      }

      public double $div(final double a, final double b) {
         return this.$div$mcD$sp(a, b);
      }

      public double $percent(final double a, final double b) {
         return this.$percent$mcD$sp(a, b);
      }

      public double pow(final double a, final double b) {
         return this.pow$mcD$sp(a, b);
      }

      public boolean close(final double a, final double b, final double tolerance) {
         return this.close$mcD$sp(a, b, tolerance);
      }

      public UFunc.UImpl normImpl() {
         return this.normImpl$mcD$sp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldDouble$.class);
      }

      public double zero$mcD$sp() {
         return (double)0.0F;
      }

      public double one$mcD$sp() {
         return (double)1.0F;
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return a == b;
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return a != b;
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return a + b;
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return a - b;
      }

      public double $times$mcD$sp(final double a, final double b) {
         return a * b;
      }

      public double $div$mcD$sp(final double a, final double b) {
         return a / b;
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return a % b;
      }

      public double pow$mcD$sp(final double a, final double b) {
         return .MODULE$.pow(a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         double diff = .MODULE$.abs(a - b);
         return a == b || diff <= tolerance || diff <= .MODULE$.max(scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(a)), scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(b))) * tolerance || (a == (double)0 || b == (double)0 || diff < Double.MIN_NORMAL) && diff < tolerance * (double)10 * Double.MIN_NORMAL;
      }

      public UFunc.UImpl normImpl$mcD$sp() {
         return normImpl;
      }

      public boolean specInstance$() {
         return true;
      }
   }
}
