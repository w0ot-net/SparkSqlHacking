package scala.math;

import java.math.BigInteger;
import scala.Tuple2;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.NumericRange$;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong$;
import scala.runtime.Statics;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\ruu!\u00022d\u0011\u0003Ag!\u00026d\u0011\u0003Y\u0007\"\u0002=\u0002\t\u0003I\bb\u0002>\u0002\u0005\u0004%Ia\u001f\u0005\b\u0003\u0007\t\u0001\u0015!\u0003}\u0011%\t)!\u0001b\u0001\n\u0013\t9\u0001\u0003\u0005\u0004\n\u0005\u0001\u000b\u0011BA\u0005\u0011!\u0019Y!\u0001Q\u0001\n\u0005\r\u0004\u0002CB\u0007\u0003\u0001\u0006I!a\u0019\t\u0011\r=\u0011\u0001)A\u0005\u0007#A\u0001ba\u0005\u0002A\u0013%1Q\u0003\u0005\t\u00077\t!\u0019!C\u0005w\"91QD\u0001!\u0002\u0013a\bbBB\u0010\u0003\u0011\u00051\u0011\u0005\u0005\b\u0007?\tA\u0011AB\u0013\u0011\u001d\u0019y\"\u0001C\u0001\u0007WAqaa\b\u0002\t\u0003\u0019\t\u0004C\u0004\u0004 \u0005!\ta!\u000f\t\u000f\r}\u0011\u0001\"\u0001\u0004R!91qD\u0001\u0005\u0002\re\u0003bBB\u0010\u0003\u0011\u00051Q\f\u0005\b\u0007?\tA\u0011AB2\u0011\u001d\u00199'\u0001C\u0001\u0007SBqaa\u001c\u0002\t\u0007\u0019\t\bC\u0004\u0004v\u0005!\u0019aa\u001e\t\u000f\rm\u0014\u0001b\u0001\u0004~!91\u0011Q\u0001\u0005\n\r\r\u0005\"CBG\u0003\u0005\u0005I\u0011BBH\r\u0015Q7MAA\u0006\u0011%\tY\u0003\bBA\u0002\u0013%1\u0010\u0003\u0006\u0002.q\u0011\t\u0019!C\u0005\u0003_A\u0011\"a\u000f\u001d\u0005\u0003\u0005\u000b\u0015\u0002?\t\u0015\u0005uBD!b\u0001\n\u0013\ty\u0004\u0003\u0006\u0002Hq\u0011\t\u0011)A\u0005\u0003\u0003Ba\u0001\u001f\u000f\u0005\n\u0005%\u0003B\u0002=\u001d\t\u0003\ty\u0005C\u0004\u0002Vq!I!a\u0016\t\r\u0005MC\u0004\"\u0001|\u0011\u001d\ty\u0006\bC!\u0003CBq!!\u001b\u001d\t\u0003\nY\u0007C\u0004\u0002xq!\t%a\u0016\t\u000f\u0005eD\u0004\"\u0011\u0002X!9\u00111\u0010\u000f\u0005B\u0005]\u0003bBA?9\u0011\u0005\u0013q\u000b\u0005\b\u0003\u007fbB\u0011AA,\u0011\u001d\t\t\t\bC\u0001\u0003/Bq!a!\u001d\t\u0003\t9\u0006C\u0004\u0002\u0006r!I!a\u0016\t\u000f\u0005\u001dE\u0004\"\u0001\u0002X!9\u0011Q\u0014\u000f\u0005\u0002\u0005}\u0005bBA59\u0011\u0005\u0011\u0011\u0015\u0005\b\u0003KcB\u0011AAT\u0011\u001d\tY\u000b\bC\u0001\u0003[Cq!!-\u001d\t\u0003\t\u0019\fC\u0004\u00028r!\t!!/\t\u000f\u0005uF\u0004\"\u0001\u0002@\"9\u00111\u0019\u000f\u0005\u0002\u0005\u0015\u0007bBAe9\u0011\u0005\u00111\u001a\u0005\b\u0003+dB\u0011AAl\u0011\u001d\ti\u000e\bC\u0001\u0003?Dq!a9\u001d\t\u0003\t)\u000fC\u0004\u0002jr!\t!a;\t\u000f\u0005=H\u0004\"\u0001\u0002r\"9\u0011Q\u001f\u000f\u0005\u0002\u0005]\bbBA~9\u0011\u0005\u0011Q \u0005\b\u0005\u0003aB\u0011\u0001B\u0002\u0011\u001d\u00119\u0001\bC\u0001\u0005\u0013AqA!\u0004\u001d\t\u0003\u0011y\u0001C\u0004\u0003\u0014q!\tA!\u0006\t\u000f\tmA\u0004\"\u0001\u0003\u001e!9!Q\u0005\u000f\u0005\u0002\t\u001d\u0002b\u0002B\u00169\u0011\u0005\u0011q\u0001\u0005\b\u0005[aB\u0011AA\u0004\u0011\u001d\u0011y\u0003\bC\u0001\u0005cAqAa\r\u001d\t\u0003\t9\u0001C\u0004\u00036q!\t!a\u0002\t\u000f\t]B\u0004\"\u0001\u0003:!9!Q\b\u000f\u0005\u0002\t}\u0002b\u0002B\"9\u0011\u0005!Q\t\u0005\b\u0005\u0013bB\u0011\u0001B&\u0011\u001d\u0011y\u0005\bC\u0001\u0005cAqA!\u0015\u001d\t\u0003\u0011\t\u0004C\u0004\u0003Tq!\tA!\r\t\u000f\tUC\u0004\"\u0001\u0003X!9!Q\f\u000f\u0005B\t}\u0003b\u0002B49\u0011\u0005#\u0011\u000e\u0005\b\u0005cbB\u0011\u0001B:\u0011\u001d\u0011Y\b\bC\u0001\u0005cAqA! \u001d\t\u0003\ty\u0004C\u0004\u0003\u0000q!\tA!!\t\u000f\t%E\u0004\"\u0001\u0003\f\"9!1\u0013\u000f\u0005\u0002\tU\u0005\"\u0003B\\9E\u0005I\u0011\u0001B]\u0011\u001d\u0011y\r\bC\u0001\u0005#D\u0011B!8\u001d#\u0003%\tA!/\t\u000f\t}G\u0004\"\u0011\u0003b\"9!q\u001c\u000f\u0005\u0002\te\bb\u0002B\u00009\u0011\u00051\u0011A\u0001\u0007\u0005&<\u0017J\u001c;\u000b\u0005\u0011,\u0017\u0001B7bi\"T\u0011AZ\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tI\u0017!D\u0001d\u0005\u0019\u0011\u0015nZ%oiN\u0019\u0011\u0001\u001c9\u0011\u00055tW\"A3\n\u0005=,'AB!osJ+g\r\u0005\u0002rm6\t!O\u0003\u0002ti\u0006\u0011\u0011n\u001c\u0006\u0002k\u0006!!.\u0019<b\u0013\t9(O\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002Q\u00061Bn\u001c8h\u001b&tg+\u00197vK\nKw-\u00138uK\u001e,'/F\u0001}!\tix0D\u0001\u007f\u0015\t!G/C\u0002\u0002\u0002y\u0014!BQ5h\u0013:$XmZ3s\u0003]awN\\4NS:4\u0016\r\\;f\u0005&<\u0017J\u001c;fO\u0016\u0014\b%\u0001\u0007m_:<W*\u001b8WC2,X-\u0006\u0002\u0002\nA\u0011\u0011\u000eH\n\n9\u00055\u00111CA\r\u0003K\u00012![A\b\u0013\r\t\tb\u0019\u0002\f'\u000e\fG.\u0019(v[\n,'\u000fE\u0002j\u0003+I1!a\u0006d\u0005]\u00196-\u00197b\u001dVlWM]5d\u0007>tg/\u001a:tS>t7\u000f\u0005\u0003\u0002\u001c\u0005\u0005bbA7\u0002\u001e%\u0019\u0011qD3\u0002\u000fA\f7m[1hK&\u0019q/a\t\u000b\u0007\u0005}Q\rE\u0003j\u0003O\tI!C\u0002\u0002*\r\u0014qa\u0014:eKJ,G-A\u0006`E&<\u0017J\u001c;fO\u0016\u0014\u0018aD0cS\u001eLe\u000e^3hKJ|F%Z9\u0015\t\u0005E\u0012q\u0007\t\u0004[\u0006M\u0012bAA\u001bK\n!QK\\5u\u0011!\tIDHA\u0001\u0002\u0004a\u0018a\u0001=%c\u0005aqLY5h\u0013:$XmZ3sA\u0005)q\f\\8oOV\u0011\u0011\u0011\t\t\u0004[\u0006\r\u0013bAA#K\n!Aj\u001c8h\u0003\u0019yFn\u001c8hAQ1\u0011\u0011BA&\u0003\u001bBa!a\u000b#\u0001\u0004a\bbBA\u001fE\u0001\u0007\u0011\u0011\t\u000b\u0005\u0003\u0013\t\t\u0006\u0003\u0004\u0002T\r\u0002\r\u0001`\u0001\u000bE&<\u0017J\u001c;fO\u0016\u0014\u0018\u0001\u00047p]\u001e,enY8eS:<WCAA-!\ri\u00171L\u0005\u0004\u0003;*'a\u0002\"p_2,\u0017M\\\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111\r\t\u0004[\u0006\u0015\u0014bAA4K\n\u0019\u0011J\u001c;\u0002\r\u0015\fX/\u00197t)\u0011\tI&!\u001c\t\u000f\u0005=t\u00051\u0001\u0002r\u0005!A\u000f[1u!\ri\u00171O\u0005\u0004\u0003k*'aA!os\u0006Y\u0011n\u001d,bY&$')\u001f;f\u00031I7OV1mS\u0012\u001c\u0006n\u001c:u\u0003-I7OV1mS\u0012\u001c\u0005.\u0019:\u0002\u0015%\u001ch+\u00197jI&sG/A\u0006jgZ\u000bG.\u001b3M_:<\u0017\u0001D5t-\u0006d\u0017\u000e\u001a$m_\u0006$\u0018!D5t-\u0006d\u0017\u000e\u001a#pk\ndW-A\tcSRdUM\\4uQ>3XM\u001d4m_^\fq![:XQ>dW\rK\u00061\u0003\u0017\u000b\t*a%\u0002\u0018\u0006e\u0005cA7\u0002\u000e&\u0019\u0011qR3\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005U\u0015!K5t/\"|G.\u001a\u0011p]\u0002\ng\u000eI5oi\u0016<WM\u001d\u0011usB,\u0007%[:!C2<\u0018-_:!iJ,X-A\u0003tS:\u001cW-\t\u0002\u0002\u001c\u00069!GL\u00193]E*\u0014AC;oI\u0016\u0014H._5oOR\tA\u0010\u0006\u0003\u0002Z\u0005\r\u0006bBA8e\u0001\u0007\u0011\u0011B\u0001\bG>l\u0007/\u0019:f)\u0011\t\u0019'!+\t\u000f\u0005=4\u00071\u0001\u0002\n\u0005)A\u0005\u001d7vgR!\u0011\u0011BAX\u0011\u001d\ty\u0007\u000ea\u0001\u0003\u0013\ta\u0001J7j]V\u001cH\u0003BA\u0005\u0003kCq!a\u001c6\u0001\u0004\tI!\u0001\u0004%i&lWm\u001d\u000b\u0005\u0003\u0013\tY\fC\u0004\u0002pY\u0002\r!!\u0003\u0002\t\u0011\"\u0017N\u001e\u000b\u0005\u0003\u0013\t\t\rC\u0004\u0002p]\u0002\r!!\u0003\u0002\u0011\u0011\u0002XM]2f]R$B!!\u0003\u0002H\"9\u0011q\u000e\u001dA\u0002\u0005%\u0011\u0001\u0004\u0013eSZ$\u0003/\u001a:dK:$H\u0003BAg\u0003'\u0004r!\\Ah\u0003\u0013\tI!C\u0002\u0002R\u0016\u0014a\u0001V;qY\u0016\u0014\u0004bBA8s\u0001\u0007\u0011\u0011B\u0001\u000bI1,7o\u001d\u0013mKN\u001cH\u0003BA\u0005\u00033Dq!a7;\u0001\u0004\t\u0019'A\u0001o\u0003A!sM]3bi\u0016\u0014He\u001a:fCR,'\u000f\u0006\u0003\u0002\n\u0005\u0005\bbBAnw\u0001\u0007\u00111M\u0001\u0005I\u0005l\u0007\u000f\u0006\u0003\u0002\n\u0005\u001d\bbBA8y\u0001\u0007\u0011\u0011B\u0001\u0005I\t\f'\u000f\u0006\u0003\u0002\n\u00055\bbBA8{\u0001\u0007\u0011\u0011B\u0001\u0004IU\u0004H\u0003BA\u0005\u0003gDq!a\u001c?\u0001\u0004\tI!\u0001\u0006%C6\u0004H\u0005^5mI\u0016$B!!\u0003\u0002z\"9\u0011qN A\u0002\u0005%\u0011aA4dIR!\u0011\u0011BA\u0000\u0011\u001d\ty\u0007\u0011a\u0001\u0003\u0013\t1!\\8e)\u0011\tIA!\u0002\t\u000f\u0005=\u0014\t1\u0001\u0002\n\u0005\u0019Q.\u001b8\u0015\t\u0005%!1\u0002\u0005\b\u0003_\u0012\u0005\u0019AA\u0005\u0003\ri\u0017\r\u001f\u000b\u0005\u0003\u0013\u0011\t\u0002C\u0004\u0002p\r\u0003\r!!\u0003\u0002\u0007A|w\u000f\u0006\u0003\u0002\n\t]\u0001b\u0002B\r\t\u0002\u0007\u00111M\u0001\u0004Kb\u0004\u0018AB7pIB{w\u000f\u0006\u0004\u0002\n\t}!\u0011\u0005\u0005\b\u00053)\u0005\u0019AA\u0005\u0011\u001d\u0011\u0019#\u0012a\u0001\u0003\u0013\t\u0011!\\\u0001\u000b[>$\u0017J\u001c<feN,G\u0003BA\u0005\u0005SAqAa\tG\u0001\u0004\tI!\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/A\u0002bEN\faa]5h]VlWCAA2\u0003\u0011\u0019\u0018n\u001a8\u0002\u0019Ut\u0017M]=`IQLG\u000eZ3\u0002\u000fQ,7\u000f\u001e\"jiR!\u0011\u0011\fB\u001e\u0011\u001d\tY\u000e\u0014a\u0001\u0003G\naa]3u\u0005&$H\u0003BA\u0005\u0005\u0003Bq!a7N\u0001\u0004\t\u0019'\u0001\u0005dY\u0016\f'OQ5u)\u0011\tIAa\u0012\t\u000f\u0005mg\n1\u0001\u0002d\u00059a\r\\5q\u0005&$H\u0003BA\u0005\u0005\u001bBq!a7P\u0001\u0004\t\u0019'\u0001\u0007m_^,7\u000f^*fi\nKG/A\u0005cSRdUM\\4uQ\u0006A!-\u001b;D_VtG/A\bjgB\u0013xNY1cY\u0016\u0004&/[7f)\u0011\tIF!\u0017\t\u000f\tm3\u000b1\u0001\u0002d\u0005I1-\u001a:uC&tG/_\u0001\nEf$XMV1mk\u0016,\"A!\u0019\u0011\u00075\u0014\u0019'C\u0002\u0003f\u0015\u0014AAQ=uK\u0006Q1\u000f[8siZ\u000bG.^3\u0016\u0005\t-\u0004cA7\u0003n%\u0019!qN3\u0003\u000bMCwN\u001d;\u0002\u0013\rD\u0017M\u001d,bYV,WC\u0001B;!\ri'qO\u0005\u0004\u0005s*'\u0001B\"iCJ\f\u0001\"\u001b8u-\u0006dW/Z\u0001\nY>twMV1mk\u0016\f!B\u001a7pCR4\u0016\r\\;f+\t\u0011\u0019\tE\u0002n\u0005\u000bK1Aa\"f\u0005\u00151En\\1u\u0003-!w.\u001e2mKZ\u000bG.^3\u0016\u0005\t5\u0005cA7\u0003\u0010&\u0019!\u0011S3\u0003\r\u0011{WO\u00197f\u0003\u0015)h\u000e^5m)\u0019\u00119Ja,\u00034B1!\u0011\u0014BU\u0003\u0013qAAa'\u0003&6\u0011!Q\u0014\u0006\u0005\u0005?\u0013\t+A\u0005j[6,H/\u00192mK*\u0019!1U3\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003(\nu\u0015\u0001\u0004(v[\u0016\u0014\u0018n\u0019*b]\u001e,\u0017\u0002\u0002BV\u0005[\u0013\u0011\"\u0012=dYV\u001c\u0018N^3\u000b\t\t\u001d&Q\u0014\u0005\b\u0005c[\u0006\u0019AA\u0005\u0003\r)g\u000e\u001a\u0005\n\u0005k[\u0006\u0013!a\u0001\u0003\u0013\tAa\u001d;fa\u0006yQO\u001c;jY\u0012\"WMZ1vYR$#'\u0006\u0002\u0003<*\"\u0011\u0011\u0002B_W\t\u0011y\f\u0005\u0003\u0003B\n-WB\u0001Bb\u0015\u0011\u0011)Ma2\u0002\u0013Ut7\r[3dW\u0016$'b\u0001BeK\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t5'1\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017A\u0001;p)\u0019\u0011\u0019N!7\u0003\\B1!\u0011\u0014Bk\u0003\u0013IAAa6\u0003.\nI\u0011J\\2mkNLg/\u001a\u0005\b\u0005ck\u0006\u0019AA\u0005\u0011%\u0011),\u0018I\u0001\u0002\u0004\tI!\u0001\u0007u_\u0012\"WMZ1vYR$#'\u0001\u0005u_N#(/\u001b8h)\t\u0011\u0019\u000f\u0005\u0003\u0003f\nMh\u0002\u0002Bt\u0005_\u00042A!;f\u001b\t\u0011YOC\u0002\u0003n\u001e\fa\u0001\u0010:p_Rt\u0014b\u0001ByK\u00061\u0001K]3eK\u001aLAA!>\u0003x\n11\u000b\u001e:j]\u001eT1A!=f)\u0011\u0011\u0019Oa?\t\u000f\tu\b\r1\u0001\u0002d\u0005)!/\u00193jq\u0006YAo\u001c\"zi\u0016\f%O]1z+\t\u0019\u0019\u0001E\u0003n\u0007\u000b\u0011\t'C\u0002\u0004\b\u0015\u0014Q!\u0011:sCf\fQ\u0002\\8oO6KgNV1mk\u0016\u0004\u0013!C7j]\u000e\u000b7\r[3e\u0003%i\u0017\r_\"bG\",G-A\u0003dC\u000eDW\rE\u0003n\u0007\u000b\tI!A\u0005hKR\u001c\u0015m\u00195fIR!\u0011\u0011BB\f\u0011\u001d\u0019IB\u0003a\u0001\u0003G\n\u0011![\u0001\t[&tWo](oK\u0006IQ.\u001b8vg>sW\rI\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003\u0013\u0019\u0019\u0003C\u0004\u0004\u001a5\u0001\r!a\u0019\u0015\t\u0005%1q\u0005\u0005\b\u0007Sq\u0001\u0019AA!\u0003\u0005aG\u0003BA\u0005\u0007[Aqaa\f\u0010\u0001\u0004\u0019\u0019!A\u0001y)\u0019\tIaa\r\u00046!9!q\u0006\tA\u0002\u0005\r\u0004bBB\u001c!\u0001\u000711A\u0001\n[\u0006<g.\u001b;vI\u0016$\u0002\"!\u0003\u0004<\r}2\u0011\t\u0005\b\u0007{\t\u0002\u0019AA2\u0003%\u0011\u0017\u000e\u001e7f]\u001e$\b\u000eC\u0004\u0003\\E\u0001\r!a\u0019\t\u000f\r\r\u0013\u00031\u0001\u0004F\u0005\u0019!O\u001c3\u0011\t\r\u001d3QJ\u0007\u0003\u0007\u0013R1aa\u0013f\u0003\u0011)H/\u001b7\n\t\r=3\u0011\n\u0002\u0007%\u0006tGm\\7\u0015\r\u0005%11KB,\u0011\u001d\u0019)F\u0005a\u0001\u0003G\nqA\\;nE&$8\u000fC\u0004\u0004DI\u0001\ra!\u0012\u0015\t\u0005%11\f\u0005\b\u0007_\u0019\u0002\u0019\u0001Br)\u0019\tIaa\u0018\u0004b!91q\u0006\u000bA\u0002\t\r\bb\u0002B\u007f)\u0001\u0007\u00111\r\u000b\u0005\u0003\u0013\u0019)\u0007\u0003\u0004\u00040U\u0001\r\u0001`\u0001\u000eaJ|'-\u00192mKB\u0013\u0018.\\3\u0015\r\u0005%11NB7\u0011\u001d\u0011\tF\u0006a\u0001\u0003GBqaa\u0011\u0017\u0001\u0004\u0019)%\u0001\u0006j]R\u0014$-[4J]R$B!!\u0003\u0004t!91\u0011D\fA\u0002\u0005\r\u0014a\u00037p]\u001e\u0014$-[4J]R$B!!\u0003\u0004z!91\u0011\u0006\rA\u0002\u0005\u0005\u0013!\u00066bm\u0006\u0014\u0015nZ%oi\u0016<WM\u001d\u001acS\u001eLe\u000e\u001e\u000b\u0005\u0003\u0013\u0019y\b\u0003\u0004\u00040e\u0001\r\u0001`\u0001\bY>twmR2e)\u0019\t\te!\"\u0004\n\"91q\u0011\u000eA\u0002\u0005\u0005\u0013!A1\t\u000f\r-%\u00041\u0001\u0002B\u0005\t!-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004\u0012B!11SBM\u001b\t\u0019)JC\u0002\u0004\u0018R\fA\u0001\\1oO&!11TBK\u0005\u0019y%M[3di\u0002"
)
public final class BigInt extends ScalaNumber implements ScalaNumericConversions, Ordered {
   private BigInteger _bigInteger;
   private final long _long;

   public static BigInt javaBigInteger2bigInt(final BigInteger x) {
      return BigInt$.MODULE$.javaBigInteger2bigInt(x);
   }

   public static BigInt long2bigInt(final long l) {
      return BigInt$.MODULE$.apply(l);
   }

   public static BigInt int2bigInt(final int i) {
      return BigInt$.MODULE$.apply(i);
   }

   public static BigInt probablePrime(final int bitLength, final Random rnd) {
      return BigInt$.MODULE$.probablePrime(bitLength, rnd);
   }

   public static BigInt apply(final BigInteger x) {
      return BigInt$.MODULE$.apply(x);
   }

   public static BigInt apply(final String x, final int radix) {
      return BigInt$.MODULE$.apply(x, radix);
   }

   public static BigInt apply(final String x) {
      return BigInt$.MODULE$.apply(x);
   }

   public static BigInt apply(final int numbits, final Random rnd) {
      return BigInt$.MODULE$.apply(numbits, rnd);
   }

   public static BigInt apply(final int bitlength, final int certainty, final Random rnd) {
      return BigInt$.MODULE$.apply(bitlength, certainty, rnd);
   }

   public static BigInt apply(final int signum, final byte[] magnitude) {
      return BigInt$.MODULE$.apply(signum, magnitude);
   }

   public static BigInt apply(final byte[] x) {
      return BigInt$.MODULE$.apply(x);
   }

   public static BigInt apply(final long l) {
      return BigInt$.MODULE$.apply(l);
   }

   public static BigInt apply(final int i) {
      return BigInt$.MODULE$.apply(i);
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   private BigInteger _bigInteger() {
      return this._bigInteger;
   }

   private void _bigInteger_$eq(final BigInteger x$1) {
      this._bigInteger = x$1;
   }

   private long _long() {
      return this._long;
   }

   private boolean longEncoding() {
      return this._long() != Long.MIN_VALUE;
   }

   public BigInteger bigInteger() {
      BigInteger read = this._bigInteger();
      if (read != null) {
         return read;
      } else {
         BigInteger write = BigInteger.valueOf(this._long());
         this._bigInteger_$eq(write);
         return write;
      }
   }

   public int hashCode() {
      return this.isValidLong() ? ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this) : Statics.anyHash(this.bigInteger());
   }

   public boolean equals(final Object that) {
      if (that instanceof BigInt) {
         BigInt var2 = (BigInt)that;
         return this.equals(var2);
      } else if (that instanceof BigDecimal) {
         return ((BigDecimal)that).equals((Object)this);
      } else if (that instanceof Double) {
         double var3 = BoxesRunTime.unboxToDouble(that);
         return this.isValidDouble() && this.doubleValue() == var3;
      } else if (that instanceof Float) {
         float var5 = BoxesRunTime.unboxToFloat(that);
         return this.isValidFloat() && this.floatValue() == var5;
      } else {
         return this.isValidLong() && ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, that);
      }
   }

   public boolean isValidByte() {
      return this._long() >= (long)-128 && this._long() <= (long)127;
   }

   public boolean isValidShort() {
      return this._long() >= (long)Short.MIN_VALUE && this._long() <= (long)32767;
   }

   public boolean isValidChar() {
      return this._long() >= (long)0 && this._long() <= (long)'\uffff';
   }

   public boolean isValidInt() {
      return this._long() >= -2147483648L && this._long() <= 2147483647L;
   }

   public boolean isValidLong() {
      return this.longEncoding() || BoxesRunTime.equalsNumNum(this._bigInteger(), BigInt$.MODULE$.scala$math$BigInt$$longMinValueBigInteger());
   }

   public boolean isValidFloat() {
      int bitLen = this.bitLength();
      if (bitLen > 24) {
         int lowest = this.lowestSetBit();
         if (bitLen > 128 || lowest < bitLen - 24 || lowest >= 128) {
            return false;
         }
      }

      if (!this.bitLengthOverflow()) {
         return true;
      } else {
         return false;
      }
   }

   public boolean isValidDouble() {
      int bitLen = this.bitLength();
      if (bitLen > 53) {
         int lowest = this.lowestSetBit();
         if (bitLen > 1024 || lowest < bitLen - 53 || lowest >= 1024) {
            return false;
         }
      }

      if (!this.bitLengthOverflow()) {
         return true;
      } else {
         return false;
      }
   }

   private boolean bitLengthOverflow() {
      BigInteger shifted = this.bigInteger().shiftRight(Integer.MAX_VALUE);
      return shifted.signum() != 0 && !shifted.equals(BigInt$.MODULE$.scala$math$BigInt$$minusOne());
   }

   /** @deprecated */
   public boolean isWhole() {
      return true;
   }

   public BigInteger underlying() {
      return this.bigInteger();
   }

   public boolean equals(final BigInt that) {
      if (this.longEncoding()) {
         return that.longEncoding() && this._long() == that._long();
      } else {
         return !that.longEncoding() && BoxesRunTime.equalsNumNum(this._bigInteger(), that._bigInteger());
      }
   }

   public int compare(final BigInt that) {
      if (this.longEncoding()) {
         return that.longEncoding() ? Long.compare(this._long(), that._long()) : -that._bigInteger().signum();
      } else {
         return that.longEncoding() ? this._bigInteger().signum() : this._bigInteger().compareTo(that._bigInteger());
      }
   }

   public BigInt $plus(final BigInt that) {
      if (this.longEncoding() && that.longEncoding()) {
         long x = this._long();
         long y = that._long();
         long z = x + y;
         if ((~(x ^ y) & (x ^ z)) >= 0L) {
            return BigInt$.MODULE$.apply(z);
         }
      }

      return BigInt$.MODULE$.apply(this.bigInteger().add(that.bigInteger()));
   }

   public BigInt $minus(final BigInt that) {
      if (this.longEncoding() && that.longEncoding()) {
         long x = this._long();
         long y = that._long();
         long z = x - y;
         if (((x ^ y) & (x ^ z)) >= 0L) {
            return BigInt$.MODULE$.apply(z);
         }
      }

      return BigInt$.MODULE$.apply(this.bigInteger().subtract(that.bigInteger()));
   }

   public BigInt $times(final BigInt that) {
      if (this.longEncoding() && that.longEncoding()) {
         long x = this._long();
         long y = that._long();
         long z = x * y;
         if (x == 0L || y == z / x) {
            return BigInt$.MODULE$.apply(z);
         }
      }

      return BigInt$.MODULE$.apply(this.bigInteger().multiply(that.bigInteger()));
   }

   public BigInt $div(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() / that._long()) : BigInt$.MODULE$.apply(this.bigInteger().divide(that.bigInteger()));
   }

   public BigInt $percent(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() % that._long()) : BigInt$.MODULE$.apply(this.bigInteger().remainder(that.bigInteger()));
   }

   public Tuple2 $div$percent(final BigInt that) {
      if (this.longEncoding() && that.longEncoding()) {
         long x = this._long();
         long y = that._long();
         return new Tuple2(BigInt$.MODULE$.apply(x / y), BigInt$.MODULE$.apply(x % y));
      } else {
         BigInteger[] dr = this.bigInteger().divideAndRemainder(that.bigInteger());
         return new Tuple2(BigInt$.MODULE$.apply(dr[0]), BigInt$.MODULE$.apply(dr[1]));
      }
   }

   public BigInt $less$less(final int n) {
      return this.longEncoding() && n <= 0 ? this.$greater$greater(-n) : BigInt$.MODULE$.apply(this.bigInteger().shiftLeft(n));
   }

   public BigInt $greater$greater(final int n) {
      if (this.longEncoding() && n >= 0) {
         if (n < 64) {
            return BigInt$.MODULE$.apply(this._long() >> n);
         } else {
            return this._long() < 0L ? BigInt$.MODULE$.apply(-1) : BigInt$.MODULE$.apply(0);
         }
      } else {
         return BigInt$.MODULE$.apply(this.bigInteger().shiftRight(n));
      }
   }

   public BigInt $amp(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() & that._long()) : BigInt$.MODULE$.apply(this.bigInteger().and(that.bigInteger()));
   }

   public BigInt $bar(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() | that._long()) : BigInt$.MODULE$.apply(this.bigInteger().or(that.bigInteger()));
   }

   public BigInt $up(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() ^ that._long()) : BigInt$.MODULE$.apply(this.bigInteger().xor(that.bigInteger()));
   }

   public BigInt $amp$tilde(final BigInt that) {
      return this.longEncoding() && that.longEncoding() ? BigInt$.MODULE$.apply(this._long() & ~that._long()) : BigInt$.MODULE$.apply(this.bigInteger().andNot(that.bigInteger()));
   }

   public BigInt gcd(final BigInt that) {
      while(this.longEncoding()) {
         if (this._long() == 0L) {
            return that.abs();
         }

         if (that.longEncoding()) {
            if (that._long() == 0L) {
               return this.abs();
            }

            BigInt$ var12 = BigInt$.MODULE$;
            BigInt$ var10001 = BigInt$.MODULE$;
            RichLong$ var10002 = RichLong$.MODULE$;
            long var4 = this._long();
            package$ var18 = package$.MODULE$;
            long var19 = Math.abs(var4);
            RichLong$ var10003 = RichLong$.MODULE$;
            long var6 = that._long();
            package$ var22 = package$.MODULE$;
            return var12.apply(var10001.scala$math$BigInt$$longGcd(var19, Math.abs(var6)));
         }

         BigInt var10000 = that;
         that = this;
         this = var10000;
      }

      if (that.longEncoding()) {
         if (that._long() == 0L) {
            return this.abs();
         } else {
            BigInteger var13 = this._bigInteger();
            RichLong$ var15 = RichLong$.MODULE$;
            long var8 = that._long();
            package$ var16 = package$.MODULE$;
            long red = var13.mod(BigInteger.valueOf(Math.abs(var8))).longValue();
            if (red == 0L) {
               return that.abs();
            } else {
               BigInt$ var14 = BigInt$.MODULE$;
               BigInt$ var17 = BigInt$.MODULE$;
               RichLong$ var20 = RichLong$.MODULE$;
               long var10 = that._long();
               package$ var21 = package$.MODULE$;
               return var14.apply(var17.scala$math$BigInt$$longGcd(Math.abs(var10), red));
            }
         }
      } else {
         return BigInt$.MODULE$.apply(this.bigInteger().gcd(that.bigInteger()));
      }
   }

   public BigInt mod(final BigInt that) {
      if (this.longEncoding() && that.longEncoding() && that._long() > 0L) {
         long res = this._long() % that._long();
         return res >= 0L ? BigInt$.MODULE$.apply(res) : BigInt$.MODULE$.apply(res + that._long());
      } else {
         return BigInt$.MODULE$.apply(this.bigInteger().mod(that.bigInteger()));
      }
   }

   public BigInt min(final BigInt that) {
      return Ordered.$less$eq$(this, that) ? this : that;
   }

   public BigInt max(final BigInt that) {
      return Ordered.$greater$eq$(this, that) ? this : that;
   }

   public BigInt pow(final int exp) {
      return BigInt$.MODULE$.apply(this.bigInteger().pow(exp));
   }

   public BigInt modPow(final BigInt exp, final BigInt m) {
      return BigInt$.MODULE$.apply(this.bigInteger().modPow(exp.bigInteger(), m.bigInteger()));
   }

   public BigInt modInverse(final BigInt m) {
      return BigInt$.MODULE$.apply(this.bigInteger().modInverse(m.bigInteger()));
   }

   public BigInt unary_$minus() {
      return this.longEncoding() ? BigInt$.MODULE$.apply(-this._long()) : BigInt$.MODULE$.apply(this.bigInteger().negate());
   }

   public BigInt abs() {
      return this.signum() < 0 ? this.unary_$minus() : this;
   }

   public int signum() {
      return this.longEncoding() ? Long.signum(this._long()) : this._bigInteger().signum();
   }

   public BigInt sign() {
      return BigInt$.MODULE$.apply(this.signum());
   }

   public BigInt unary_$tilde() {
      return this.longEncoding() && this._long() != Long.MAX_VALUE ? BigInt$.MODULE$.apply(-(this._long() + 1L)) : BigInt$.MODULE$.apply(this.bigInteger().not());
   }

   public boolean testBit(final int n) {
      if (this.longEncoding() && n >= 0) {
         if (n <= 63) {
            return (this._long() & 1L << n) != 0L;
         } else {
            return this._long() < 0L;
         }
      } else {
         return this.bigInteger().testBit(n);
      }
   }

   public BigInt setBit(final int n) {
      return this.longEncoding() && n <= 62 && n >= 0 ? BigInt$.MODULE$.apply(this._long() | 1L << n) : BigInt$.MODULE$.apply(this.bigInteger().setBit(n));
   }

   public BigInt clearBit(final int n) {
      return this.longEncoding() && n <= 62 && n >= 0 ? BigInt$.MODULE$.apply(this._long() & ~(1L << n)) : BigInt$.MODULE$.apply(this.bigInteger().clearBit(n));
   }

   public BigInt flipBit(final int n) {
      return this.longEncoding() && n <= 62 && n >= 0 ? BigInt$.MODULE$.apply(this._long() ^ 1L << n) : BigInt$.MODULE$.apply(this.bigInteger().flipBit(n));
   }

   public int lowestSetBit() {
      if (this.longEncoding()) {
         return this._long() == 0L ? -1 : Long.numberOfTrailingZeros(this._long());
      } else {
         return this.bigInteger().getLowestSetBit();
      }
   }

   public int bitLength() {
      if (this.longEncoding()) {
         return this._long() < 0L ? 64 - Long.numberOfLeadingZeros(-(this._long() + 1L)) : 64 - Long.numberOfLeadingZeros(this._long());
      } else {
         return this._bigInteger().bitLength();
      }
   }

   public int bitCount() {
      if (this.longEncoding()) {
         return this._long() < 0L ? Long.bitCount(-(this._long() + 1L)) : Long.bitCount(this._long());
      } else {
         return this.bigInteger().bitCount();
      }
   }

   public boolean isProbablePrime(final int certainty) {
      return this.bigInteger().isProbablePrime(certainty);
   }

   public byte byteValue() {
      return (byte)this.intValue();
   }

   public short shortValue() {
      return (short)this.intValue();
   }

   public char charValue() {
      return (char)this.intValue();
   }

   public int intValue() {
      return this.longEncoding() ? (int)this._long() : this.bigInteger().intValue();
   }

   public long longValue() {
      return this.longEncoding() ? this._long() : this._bigInteger().longValue();
   }

   public float floatValue() {
      return this.bigInteger().floatValue();
   }

   public double doubleValue() {
      return this.isValidLong() && -9007199254740992L <= this._long() && this._long() <= 9007199254740992L ? (double)this._long() : this.bigInteger().doubleValue();
   }

   public NumericRange.Exclusive until(final BigInt end, final BigInt step) {
      Range.BigInt$ var10000 = Range.BigInt$.MODULE$;
      NumericRange$ var4 = NumericRange$.MODULE$;
      Integral apply_apply_num = Numeric.BigIntIsIntegral$.MODULE$;
      return new NumericRange.Exclusive(this, end, step, apply_apply_num);
   }

   public BigInt until$default$2() {
      return BigInt$.MODULE$.apply(1);
   }

   public NumericRange.Inclusive to(final BigInt end, final BigInt step) {
      Range.BigInt$ var10000 = Range.BigInt$.MODULE$;
      NumericRange$ var4 = NumericRange$.MODULE$;
      Integral inclusive_inclusive_num = Numeric.BigIntIsIntegral$.MODULE$;
      return new NumericRange.Inclusive(this, end, step, inclusive_inclusive_num);
   }

   public BigInt to$default$2() {
      return BigInt$.MODULE$.apply(1);
   }

   public String toString() {
      return this.longEncoding() ? Long.toString(this._long()) : this._bigInteger().toString();
   }

   public String toString(final int radix) {
      return this.bigInteger().toString(radix);
   }

   public byte[] toByteArray() {
      return this.bigInteger().toByteArray();
   }

   public BigInt(final BigInteger _bigInteger, final long _long) {
      this._bigInteger = _bigInteger;
      this._long = _long;
      super();
   }

   public BigInt(final BigInteger bigInteger) {
      this(bigInteger, bigInteger.bitLength() <= 63 ? bigInteger.longValue() : Long.MIN_VALUE);
   }
}
