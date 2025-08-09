package scala.collection.mutable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Array$;
import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.RichInt$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011%c\u0001CA\b\u0003#\t\t#a\b\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0002j!I\u0011Q\u000e\u0001AB\u0013E\u0011q\u000e\u0005\n\u0003o\u0002\u0001\u0019)C\t\u0003sB\u0001\"!\"\u0001A\u0003&\u0011\u0011\u000f\u0005\t\u0003\u000f\u0003\u0001U\"\u0005\u0002\n\"I\u00111\u0012\u0001A\u0002\u0013E\u0011q\u000e\u0005\n\u0003\u001b\u0003\u0001\u0019!C\t\u0003\u001fC\u0001\"a%\u0001A\u0003&\u0011\u0011\u000f\u0005\b\u0003+\u0003A\u0011AA8\u0011\u001d\t9\n\u0001C!\u0003_B\u0001\"!'\u0001A\u0013U\u00111\u0014\u0005\b\u0003?\u0003AQIAQ\u0011\u001d\t)\u000b\u0001C\u0001\u0003OC\u0001\"!+\u0001A\u001bE\u00111\u0016\u0005\b\u0003_\u0003A\u0011AAY\u0011\u001d\ty\u000b\u0001C\u0001\u0003\u000bDq!!7\u0001\t\u0013\tY\u000eC\u0004\u00020\u0002!\t%!<\b\u0011\u0011\u001d\u0013\u0011\u0003E\u0001\u0005\u001f1\u0001\"a\u0004\u0002\u0012!\u0005\u0011Q \u0005\b\u0003O\"B\u0011\u0001B\u0007\u0011\u001d\u0011\t\u0002\u0006C\u0001\u0005'1aA!\u000e\u0015\u0005\t]\u0002B\u0003B\"/\t\u0005\t\u0015a\u0003\u0003F!9\u0011qM\f\u0005\u0002\t\u001d\u0003bCAD/\u0001\u0007\t\u0019!C\t\u0005#B1B!\u0016\u0018\u0001\u0004\u0005\r\u0011\"\u0005\u0003X!Y!1L\fA\u0002\u0003\u0005\u000b\u0015\u0002B*\u0011\u001d\u0011if\u0006C\u0005\u0005?B\u0001\"!+\u0018A\u0013E!1\r\u0005\b\u0005O:B\u0011\u0001B5\u0011\u001d\u0011\th\u0006C\u0001\u0005gBq!!*\u0018\t\u0003\n9\u000bC\u0004\u0003v]!\tEa\u001e\t\u000f\t\ru\u0003\"\u0011\u0003\u0006\u001a1!q\u0014\u000b\u0003\u0005CCq!a\u001a%\t\u0003\u0011Y\u000bC\u0006\u0002\b\u0012\u0002\r\u00111A\u0005\u0012\t=\u0006b\u0003B+I\u0001\u0007\t\u0019!C\t\u0005gC1Ba\u0017%\u0001\u0004\u0005\t\u0015)\u0003\u00032\"9!Q\f\u0013\u0005\n\t]\u0006\u0002CAUI\u0001&\tBa/\t\u000f\t\u001dD\u0005\"\u0001\u0003@\"9!\u0011\u000f\u0013\u0005\u0002\t\u0015\u0007b\u0002B;I\u0011\u0005#q\u0019\u0005\b\u0005\u0007#C\u0011\tBC\r\u0019\u0011i\r\u0006\u0002\u0003P\"9\u0011qM\u0018\u0005\u0002\te\u0007bCAD_\u0001\u0007\t\u0019!C\t\u0005;D1B!\u00160\u0001\u0004\u0005\r\u0011\"\u0005\u0003b\"Y!1L\u0018A\u0002\u0003\u0005\u000b\u0015\u0002Bp\u0011\u001d\u0011if\fC\u0005\u0005KD\u0001\"!+0A\u0013E!\u0011\u001e\u0005\b\u0005OzC\u0011\u0001Bw\u0011\u001d\u0011\th\fC\u0001\u0005gDqA!\u001e0\t\u0003\u0012)\u0010C\u0004\u0003\u0004>\"\tE!\"\u0007\r\tmHC\u0001B\u007f\u0011\u001d\t9G\u000fC\u0001\u0007\u000fA1\"a\";\u0001\u0004\u0005\r\u0011\"\u0005\u0004\f!Y!Q\u000b\u001eA\u0002\u0003\u0007I\u0011CB\b\u0011-\u0011YF\u000fa\u0001\u0002\u0003\u0006Ka!\u0004\t\u000f\tu#\b\"\u0003\u0004\u0014!A\u0011\u0011\u0016\u001e!\n#\u00199\u0002C\u0004\u0003hi\"\taa\u0007\t\u000f\tE$\b\"\u0001\u0004\"!9!Q\u000f\u001e\u0005B\r\r\u0002b\u0002BBu\u0011\u0005#Q\u0011\u0004\u0007\u0007S!\"aa\u000b\t\u000f\u0005\u001dT\t\"\u0001\u00040!Y\u0011qQ#A\u0002\u0003\u0007I\u0011CB\u001a\u0011-\u0011)&\u0012a\u0001\u0002\u0004%\tba\u000e\t\u0017\tmS\t1A\u0001B\u0003&1Q\u0007\u0005\b\u0005;*E\u0011BB\u001e\u0011!\tI+\u0012Q\u0005\u0012\r}\u0002b\u0002B4\u000b\u0012\u000511\t\u0005\b\u0005c*E\u0011AB%\u0011\u001d\u0011)(\u0012C!\u0007\u0017BqAa!F\t\u0003\u0012)I\u0002\u0004\u0004RQ\u001111\u000b\u0005\b\u0003O\u0002F\u0011AB/\u0011-\t9\t\u0015a\u0001\u0002\u0004%\tb!\u0019\t\u0017\tU\u0003\u000b1AA\u0002\u0013E1Q\r\u0005\f\u00057\u0002\u0006\u0019!A!B\u0013\u0019\u0019\u0007C\u0004\u0003^A#Ia!\u001b\t\u0011\u0005%\u0006\u000b)C\t\u0007[BqAa\u001aQ\t\u0003\u0019\t\bC\u0004\u0003rA#\taa\u001e\t\u000f\tU\u0004\u000b\"\u0011\u0004z!9!1\u0011)\u0005B\t\u0015eABB@)\t\u0019\t\tC\u0004\u0002hm#\taa#\t\u0017\u0005\u001d5\f1AA\u0002\u0013E1q\u0012\u0005\f\u0005+Z\u0006\u0019!a\u0001\n#\u0019\u0019\nC\u0006\u0003\\m\u0003\r\u0011!Q!\n\rE\u0005b\u0002B/7\u0012%1q\u0013\u0005\t\u0003S[\u0006\u0015\"\u0005\u0004\u001c\"9!qM.\u0005\u0002\r}\u0005b\u0002B97\u0012\u00051Q\u0015\u0005\b\u0005kZF\u0011IBT\u0011\u001d\u0011\u0019i\u0017C!\u0005\u000b3aa!,\u0015\u0005\r=\u0006bBA4M\u0012\u00051\u0011\u0018\u0005\f\u0003\u000f3\u0007\u0019!a\u0001\n#\u0019i\fC\u0006\u0003V\u0019\u0004\r\u00111A\u0005\u0012\r\u0005\u0007b\u0003B.M\u0002\u0007\t\u0011)Q\u0005\u0007\u007fCqA!\u0018g\t\u0013\u0019)\r\u0003\u0005\u0002*\u001a\u0004K\u0011CBe\u0011\u001d\u00119G\u001aC\u0001\u0007\u001bDqA!\u001dg\t\u0003\u0019\u0019\u000eC\u0004\u0003v\u0019$\te!6\t\u000f\t\re\r\"\u0011\u0003\u0006\u001a1\u00111 \u000b\u0001\t?Aq!a\u001ar\t\u0003!\u0019\u0003C\u0006\u0002\bF\u0004\r\u00111A\u0005\u0012\u0011\u001d\u0002b\u0003B+c\u0002\u0007\t\u0019!C\t\tWA1Ba\u0017r\u0001\u0004\u0005\t\u0015)\u0003\u0005*!9!QL9\u0005\n\u0011=\u0002\u0002CAUc\u0002&\t\u0002b\r\t\u000f\t\u001d\u0014\u000f\"\u0001\u00058!9!\u0011O9\u0005\u0002\u0011u\u0002b\u0002B;c\u0012\u0005Cq\b\u0005\b\u0005\u0007\u000bH\u0011\tBC\r\u0019\u0019Y\u000e\u0006\u0002\u0004^\"9\u0011q\r?\u0005\u0002\r\u0005\bbBADy\u0012E1Q\u001d\u0005\b\u0005ObH\u0011ABu\u0011\u001d\ty\u000b C!\u0007_Dq!a,}\t\u0003\u001a)\u0010C\u0004\u0003rq$\t\u0001\"\u0003\t\u000f\tUD\u0010\"\u0011\u0005\f!A\u0011\u0011\u0016?!\n#!y\u0001C\u0004\u0003\u0004r$\tE!\"\t\u0013\u0011UA#!A\u0005\n\u0011]!\u0001D!se\u0006L()^5mI\u0016\u0014(\u0002BA\n\u0003+\tq!\\;uC\ndWM\u0003\u0003\u0002\u0018\u0005e\u0011AC2pY2,7\r^5p]*\u0011\u00111D\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u0011\t\t#a\u000e\u0014\u000f\u0001\t\u0019#a\u000b\u0002PA!\u0011QEA\u0014\u001b\t\tI\"\u0003\u0003\u0002*\u0005e!AB!osJ+g\r\u0005\u0005\u0002.\u0005=\u00121GA%\u001b\t\t\t\"\u0003\u0003\u00022\u0005E!a\u0004*fkN\f'\r\\3Ck&dG-\u001a:\u0011\t\u0005U\u0012q\u0007\u0007\u0001\t\u001d\tI\u0004\u0001b\u0001\u0003w\u0011\u0011\u0001V\t\u0005\u0003{\t\u0019\u0005\u0005\u0003\u0002&\u0005}\u0012\u0002BA!\u00033\u0011qAT8uQ&tw\r\u0005\u0003\u0002&\u0005\u0015\u0013\u0002BA$\u00033\u00111!\u00118z!\u0019\t)#a\u0013\u00024%!\u0011QJA\r\u0005\u0015\t%O]1z!\u0011\t\t&!\u0019\u000f\t\u0005M\u0013Q\f\b\u0005\u0003+\nY&\u0004\u0002\u0002X)!\u0011\u0011LA\u000f\u0003\u0019a$o\\8u}%\u0011\u00111D\u0005\u0005\u0003?\nI\"A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\r\u0014Q\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0005\u0003?\nI\"\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003W\u0002R!!\f\u0001\u0003g\t\u0001bY1qC\u000eLG/_\u000b\u0003\u0003c\u0002B!!\n\u0002t%!\u0011QOA\r\u0005\rIe\u000e^\u0001\rG\u0006\u0004\u0018mY5us~#S-\u001d\u000b\u0005\u0003w\n\t\t\u0005\u0003\u0002&\u0005u\u0014\u0002BA@\u00033\u0011A!\u00168ji\"I\u00111Q\u0002\u0002\u0002\u0003\u0007\u0011\u0011O\u0001\u0004q\u0012\n\u0014!C2ba\u0006\u001c\u0017\u000e^=!\u0003\u0015)G.Z7t+\t\tI%\u0001\u0003tSj,\u0017\u0001C:ju\u0016|F%Z9\u0015\t\u0005m\u0014\u0011\u0013\u0005\n\u0003\u0007;\u0011\u0011!a\u0001\u0003c\nQa]5{K\u0002\na\u0001\\3oORD\u0017!C6o_^t7+\u001b>f\u0003))gn];sKNK'0\u001a\u000b\u0005\u0003w\ni\nC\u0004\u0002\f.\u0001\r!!\u001d\u0002\u0011ML'0\u001a%j]R$B!a\u001f\u0002$\"9\u00111\u0012\u0007A\u0002\u0005E\u0014!B2mK\u0006\u0014HCAA>\u0003\u0019\u0011Xm]5{KR!\u00111PAW\u0011\u001d\tYI\u0004a\u0001\u0003c\na!\u00193e\u00032dG\u0003BAZ\u0003kk\u0011\u0001\u0001\u0005\b\u0003o{\u0001\u0019AA]\u0003\tA8\u000f\r\u0003\u0002<\u0006}\u0006CBA\u0013\u0003\u0017\ni\f\u0005\u0003\u00026\u0005}F\u0001DAa\u0003k\u000b\t\u0011!A\u0003\u0002\u0005\r'aA0%cE!\u0011QHA\u001a)!\t\u0019,a2\u0002T\u0006]\u0007bBA\\!\u0001\u0007\u0011\u0011\u001a\u0019\u0005\u0003\u0017\fy\r\u0005\u0004\u0002&\u0005-\u0013Q\u001a\t\u0005\u0003k\ty\r\u0002\u0007\u0002R\u0006\u001d\u0017\u0011!A\u0001\u0006\u0003\t\u0019MA\u0002`IIBq!!6\u0011\u0001\u0004\t\t(\u0001\u0004pM\u001a\u001cX\r\u001e\u0005\b\u0003+\u0003\u0002\u0019AA9\u0003!!w.\u00113e\u00032dG\u0003CAZ\u0003;\fI/a;\t\u000f\u0005]\u0016\u00031\u0001\u0002`B\"\u0011\u0011]As!\u0019\t)#a\u0013\u0002dB!\u0011QGAs\t1\t9/!8\u0002\u0002\u0003\u0005)\u0011AAb\u0005\ryFe\r\u0005\b\u0003+\f\u0002\u0019AA9\u0011\u001d\t)*\u0005a\u0001\u0003c\"B!a-\u0002p\"9\u0011q\u0017\nA\u0002\u0005E\bCBAz\u0003k\f\u0019$\u0004\u0002\u0002\u0016%!\u0011q_A\u000b\u00051IE/\u001a:bE2,wJ\\2fS-\u0001\u0011\u000f\n\u001eg7\u0016\u0003vc\f?\u0003\u0013=4'i\\8mK\u0006t7#\u0002\u000b\u0002$\u0005}\b\u0003\u0002B\u0001\u0005\u0017i!Aa\u0001\u000b\t\t\u0015!qA\u0001\u0003S>T!A!\u0003\u0002\t)\fg/Y\u0005\u0005\u0003G\u0012\u0019\u0001\u0006\u0002\u0003\u0010A\u0019\u0011Q\u0006\u000b\u0002\t5\f7.Z\u000b\u0005\u0005+\u0011Y\u0002\u0006\u0003\u0003\u0018\tu\u0001#BA\u0017\u0001\te\u0001\u0003BA\u001b\u00057!q!!\u000f\u0017\u0005\u0004\tY\u0004C\u0005\u0003 Y\t\t\u0011q\u0001\u0003\"\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\t\r\"\u0011\u0006B\r\u001b\t\u0011)C\u0003\u0003\u0003(\u0005e\u0011a\u0002:fM2,7\r^\u0005\u0005\u0005W\u0011)C\u0001\u0005DY\u0006\u001c8\u000fV1hQ\r1\"q\u0006\t\u0005\u0003K\u0011\t$\u0003\u0003\u00034\u0005e!AB5oY&tWMA\u0003pMJ+g-\u0006\u0003\u0003:\t}2cA\f\u0003<A)\u0011Q\u0006\u0001\u0003>A!\u0011Q\u0007B \t\u001d\tId\u0006b\u0001\u0005\u0003\nB!!\u0010\u0002$\u0005\u00111\r\u001e\t\u0007\u0005G\u0011IC!\u0010\u0015\u0005\t%C\u0003\u0002B&\u0005\u001f\u0002RA!\u0014\u0018\u0005{i\u0011\u0001\u0006\u0005\b\u0005\u0007J\u00029\u0001B#+\t\u0011\u0019\u0006\u0005\u0004\u0002&\u0005-#QH\u0001\nK2,Wn]0%KF$B!a\u001f\u0003Z!I\u00111Q\u000e\u0002\u0002\u0003\u0007!1K\u0001\u0007K2,Wn\u001d\u0011\u0002\u000f5\\\u0017I\u001d:bsR!!1\u000bB1\u0011\u001d\tY)\ba\u0001\u0003c\"B!a\u001f\u0003f!9\u00111\u0012\u0010A\u0002\u0005E\u0014AB1eI>sW\r\u0006\u0003\u0003l\t5T\"A\f\t\u000f\t=t\u00041\u0001\u0003>\u0005!Q\r\\3n\u0003\u0019\u0011Xm];miR\u0011!1K\u0001\u0007KF,\u0018\r\\:\u0015\t\te$q\u0010\t\u0005\u0003K\u0011Y(\u0003\u0003\u0003~\u0005e!a\u0002\"p_2,\u0017M\u001c\u0005\b\u0005\u0003\u0013\u0003\u0019AA\"\u0003\u0015yG\u000f[3s\u0003!!xn\u0015;sS:<GC\u0001BD!\u0011\u0011IIa$\u000e\u0005\t-%\u0002\u0002BG\u0005\u000f\tA\u0001\\1oO&!!\u0011\u0013BF\u0005\u0019\u0019FO]5oO\":qC!&\u0003\u001c\nu\u0005\u0003BA\u0013\u0005/KAA!'\u0002\u001a\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007\t1qN\u001a\"zi\u0016\u001c2\u0001\nBR!\u0015\ti\u0003\u0001BS!\u0011\t)Ca*\n\t\t%\u0016\u0011\u0004\u0002\u0005\u0005f$X\r\u0006\u0002\u0003.B\u0019!Q\n\u0013\u0016\u0005\tE\u0006CBA\u0013\u0003\u0017\u0012)\u000b\u0006\u0003\u0002|\tU\u0006\"CABO\u0005\u0005\t\u0019\u0001BY)\u0011\u0011\tL!/\t\u000f\u0005-\u0015\u00061\u0001\u0002rQ!\u00111\u0010B_\u0011\u001d\tYI\u000ba\u0001\u0003c\"BA!1\u0003D6\tA\u0005C\u0004\u0003p-\u0002\rA!*\u0015\u0005\tEF\u0003\u0002B=\u0005\u0013DqA!!.\u0001\u0004\t\u0019\u0005K\u0004%\u0005+\u0013YJ!(\u0003\u000f=47\u000b[8siN\u0019qF!5\u0011\u000b\u00055\u0002Aa5\u0011\t\u0005\u0015\"Q[\u0005\u0005\u0005/\fIBA\u0003TQ>\u0014H\u000f\u0006\u0002\u0003\\B\u0019!QJ\u0018\u0016\u0005\t}\u0007CBA\u0013\u0003\u0017\u0012\u0019\u000e\u0006\u0003\u0002|\t\r\b\"CABe\u0005\u0005\t\u0019\u0001Bp)\u0011\u0011yNa:\t\u000f\u0005-E\u00071\u0001\u0002rQ!\u00111\u0010Bv\u0011\u001d\tY)\u000ea\u0001\u0003c\"BAa<\u0003r6\tq\u0006C\u0004\u0003pY\u0002\rAa5\u0015\u0005\t}G\u0003\u0002B=\u0005oDqA!!9\u0001\u0004\t\u0019\u0005K\u00040\u0005+\u0013YJ!(\u0003\r=47\t[1s'\rQ$q \t\u0006\u0003[\u00011\u0011\u0001\t\u0005\u0003K\u0019\u0019!\u0003\u0003\u0004\u0006\u0005e!\u0001B\"iCJ$\"a!\u0003\u0011\u0007\t5#(\u0006\u0002\u0004\u000eA1\u0011QEA&\u0007\u0003!B!a\u001f\u0004\u0012!I\u00111Q\u001f\u0002\u0002\u0003\u00071Q\u0002\u000b\u0005\u0007\u001b\u0019)\u0002C\u0004\u0002\f~\u0002\r!!\u001d\u0015\t\u0005m4\u0011\u0004\u0005\b\u0003\u0017\u0003\u0005\u0019AA9)\u0011\u0019iba\b\u000e\u0003iBqAa\u001cB\u0001\u0004\u0019\t\u0001\u0006\u0002\u0004\u000eQ!!\u0011PB\u0013\u0011\u001d\u0011\ti\u0011a\u0001\u0003\u0007BsA\u000fBK\u00057\u0013iJA\u0003pM&sGoE\u0002F\u0007[\u0001R!!\f\u0001\u0003c\"\"a!\r\u0011\u0007\t5S)\u0006\u0002\u00046A1\u0011QEA&\u0003c\"B!a\u001f\u0004:!I\u00111\u0011%\u0002\u0002\u0003\u00071Q\u0007\u000b\u0005\u0007k\u0019i\u0004C\u0004\u0002\f*\u0003\r!!\u001d\u0015\t\u0005m4\u0011\t\u0005\b\u0003\u0017[\u0005\u0019AA9)\u0011\u0019)ea\u0012\u000e\u0003\u0015CqAa\u001cM\u0001\u0004\t\t\b\u0006\u0002\u00046Q!!\u0011PB'\u0011\u001d\u0011\tI\u0014a\u0001\u0003\u0007Bs!\u0012BK\u00057\u0013iJ\u0001\u0004pM2{gnZ\n\u0004!\u000eU\u0003#BA\u0017\u0001\r]\u0003\u0003BA\u0013\u00073JAaa\u0017\u0002\u001a\t!Aj\u001c8h)\t\u0019y\u0006E\u0002\u0003NA+\"aa\u0019\u0011\r\u0005\u0015\u00121JB,)\u0011\tYha\u001a\t\u0013\u0005\r5+!AA\u0002\r\rD\u0003BB2\u0007WBq!a#V\u0001\u0004\t\t\b\u0006\u0003\u0002|\r=\u0004bBAF-\u0002\u0007\u0011\u0011\u000f\u000b\u0005\u0007g\u001a)(D\u0001Q\u0011\u001d\u0011yg\u0016a\u0001\u0007/\"\"aa\u0019\u0015\t\te41\u0010\u0005\b\u0005\u0003K\u0006\u0019AA\"Q\u001d\u0001&Q\u0013BN\u0005;\u0013qa\u001c4GY>\fGoE\u0002\\\u0007\u0007\u0003R!!\f\u0001\u0007\u000b\u0003B!!\n\u0004\b&!1\u0011RA\r\u0005\u00151En\\1u)\t\u0019i\tE\u0002\u0003Nm+\"a!%\u0011\r\u0005\u0015\u00121JBC)\u0011\tYh!&\t\u0013\u0005\re,!AA\u0002\rEE\u0003BBI\u00073Cq!a#a\u0001\u0004\t\t\b\u0006\u0003\u0002|\ru\u0005bBAFC\u0002\u0007\u0011\u0011\u000f\u000b\u0005\u0007C\u001b\u0019+D\u0001\\\u0011\u001d\u0011yG\u0019a\u0001\u0007\u000b#\"a!%\u0015\t\te4\u0011\u0016\u0005\b\u0005\u0003#\u0007\u0019AA\"Q\u001dY&Q\u0013BN\u0005;\u0013\u0001b\u001c4E_V\u0014G.Z\n\u0004M\u000eE\u0006#BA\u0017\u0001\rM\u0006\u0003BA\u0013\u0007kKAaa.\u0002\u001a\t1Ai\\;cY\u0016$\"aa/\u0011\u0007\t5c-\u0006\u0002\u0004@B1\u0011QEA&\u0007g#B!a\u001f\u0004D\"I\u00111Q5\u0002\u0002\u0003\u00071q\u0018\u000b\u0005\u0007\u007f\u001b9\rC\u0004\u0002\f.\u0004\r!!\u001d\u0015\t\u0005m41\u001a\u0005\b\u0003\u0017c\u0007\u0019AA9)\u0011\u0019ym!5\u000e\u0003\u0019DqAa\u001cn\u0001\u0004\u0019\u0019\f\u0006\u0002\u0004@R!!\u0011PBl\u0011\u001d\u0011\ti\u001ca\u0001\u0003\u0007BsA\u001aBK\u00057\u0013iJ\u0001\u0004pMVs\u0017\u000e^\n\u0004y\u000e}\u0007#BA\u0017\u0001\u0005mDCABr!\r\u0011i\u0005`\u000b\u0003\u0007O\u0004b!!\n\u0002L\u0005mD\u0003BBv\u0007[l\u0011\u0001 \u0005\b\u0005_z\b\u0019AA>)\u0011\u0019Yo!=\t\u0011\u0005]\u0016\u0011\u0001a\u0001\u0007g\u0004b!a=\u0002v\u0006mD\u0003CBv\u0007o$)\u0001b\u0002\t\u0011\u0005]\u00161\u0001a\u0001\u0007s\u0004Daa?\u0004\u0000B1\u0011QEA&\u0007{\u0004B!!\u000e\u0004\u0000\u0012aA\u0011AB|\u0003\u0003\u0005\tQ!\u0001\u0005\u0004\t\u0019q\f\n\u001b\u0012\t\u0005u\u00121\u0010\u0005\t\u0003+\f\u0019\u00011\u0001\u0002r!A\u0011QSA\u0002\u0001\u0004\t\t\b\u0006\u0002\u0004hR!!\u0011\u0010C\u0007\u0011!\u0011\t)a\u0002A\u0002\u0005\rC\u0003BA>\t#A\u0001\"a#\u0002\n\u0001\u0007\u0011\u0011\u000f\u0015\by\nU%1\u0014BO\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t!I\u0002\u0005\u0003\u0003\n\u0012m\u0011\u0002\u0002C\u000f\u0005\u0017\u0013aa\u00142kK\u000e$8cA9\u0005\"A)\u0011Q\u0006\u0001\u0003zQ\u0011AQ\u0005\t\u0004\u0005\u001b\nXC\u0001C\u0015!\u0019\t)#a\u0013\u0003zQ!\u00111\u0010C\u0017\u0011%\t\u0019\t^A\u0001\u0002\u0004!I\u0003\u0006\u0003\u0005*\u0011E\u0002bBAFm\u0002\u0007\u0011\u0011\u000f\u000b\u0005\u0003w\")\u0004C\u0004\u0002\f^\u0004\r!!\u001d\u0015\t\u0011eB1H\u0007\u0002c\"9!q\u000e=A\u0002\teDC\u0001C\u0015)\u0011\u0011I\b\"\u0011\t\u000f\t\u0005%\u00101\u0001\u0002D!:\u0011O!&\u0003\u001c\nu\u0005f\u0002\u0001\u0003\u0016\nm%QT\u0001\r\u0003J\u0014\u0018-\u001f\"vS2$WM\u001d"
)
public abstract class ArrayBuilder implements ReusableBuilder, Serializable {
   private static final long serialVersionUID = 3L;
   private int capacity = 0;
   private int size = 0;

   public static ArrayBuilder make(final ClassTag evidence$1) {
      ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
      Class var1 = evidence$1.runtimeClass();
      Class var2 = Byte.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofByte();
         }
      } else if (var2.equals(var1)) {
         return new ofByte();
      }

      var2 = Short.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofShort();
         }
      } else if (var2.equals(var1)) {
         return new ofShort();
      }

      var2 = Character.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofChar();
         }
      } else if (var2.equals(var1)) {
         return new ofChar();
      }

      var2 = Integer.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofInt();
         }
      } else if (var2.equals(var1)) {
         return new ofInt();
      }

      var2 = Long.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofLong();
         }
      } else if (var2.equals(var1)) {
         return new ofLong();
      }

      var2 = Float.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofFloat();
         }
      } else if (var2.equals(var1)) {
         return new ofFloat();
      }

      var2 = Double.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofDouble();
         }
      } else if (var2.equals(var1)) {
         return new ofDouble();
      }

      var2 = Boolean.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofBoolean();
         }
      } else if (var2.equals(var1)) {
         return new ofBoolean();
      }

      var2 = Void.TYPE;
      if (var2 == null) {
         if (var1 == null) {
            return new ofUnit();
         }
      } else if (var2.equals(var1)) {
         return new ofUnit();
      }

      return new ofRef(evidence$1);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public int capacity() {
      return this.capacity;
   }

   public void capacity_$eq(final int x$1) {
      this.capacity = x$1;
   }

   public abstract Object elems();

   public int size() {
      return this.size;
   }

   public void size_$eq(final int x$1) {
      this.size = x$1;
   }

   public int length() {
      return this.size();
   }

   public int knownSize() {
      return this.size();
   }

   public final void ensureSize(final int size) {
      int newLen = ArrayBuffer$.MODULE$.resizeUp(this.capacity(), size);
      if (newLen > 0) {
         this.resize(newLen);
      }
   }

   public final void sizeHint(final int size) {
      if (this.capacity() < size) {
         this.resize(size);
      }
   }

   public void clear() {
      this.size_$eq(0);
   }

   public abstract void resize(final int size);

   public ArrayBuilder addAll(final Object xs) {
      return this.addAll(xs, 0, Array.getLength(xs));
   }

   public ArrayBuilder addAll(final Object xs, final int offset, final int length) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var10 = scala.math.package$.MODULE$;
      int offset1 = Math.max(offset, max$extension_that);
      RichInt$ var11 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var12 = scala.math.package$.MODULE$;
      int length1 = Math.max(length, max$extension_that);
      RichInt$ var13 = RichInt$.MODULE$;
      int min$extension_that = Array.getLength(xs) - offset1;
      scala.math.package$ var14 = scala.math.package$.MODULE$;
      int effectiveLength = Math.min(length1, min$extension_that);
      return this.doAddAll(xs, offset1, effectiveLength);
   }

   private ArrayBuilder doAddAll(final Object xs, final int offset, final int length) {
      if (length > 0) {
         this.ensureSize(this.size() + length);
         Array$.MODULE$.copy(xs, offset, this.elems(), this.size(), length);
         this.size_$eq(this.size() + length);
      }

      return this;
   }

   public ArrayBuilder addAll(final IterableOnce xs) {
      int k = xs.knownSize();
      if (k > 0) {
         this.ensureSize(this.size() + k);
         IterableOnce$ var10000 = IterableOnce$.MODULE$;
         var10000 = (IterableOnce$)this.elems();
         int var10001 = this.size();
         IterableOnce$ var10002 = IterableOnce$.MODULE$;
         int copyElemsToArray_len = Integer.MAX_VALUE;
         int copyElemsToArray_start = var10001;
         Object copyElemsToArray_xs = var10000;
         int var9 = xs instanceof scala.collection.Iterable ? ((scala.collection.Iterable)xs).copyToArray(copyElemsToArray_xs, copyElemsToArray_start, copyElemsToArray_len) : xs.iterator().copyToArray(copyElemsToArray_xs, copyElemsToArray_start, copyElemsToArray_len);
         copyElemsToArray_xs = null;
         int actual = var9;
         if (actual != k) {
            throw new IllegalStateException((new java.lang.StringBuilder(11)).append("Copied ").append(actual).append(" of ").append(k).toString());
         }

         this.size_$eq(this.size() + k);
      } else if (k < 0) {
         Growable.addAll$(this, xs);
      }

      return this;
   }

   public static final class ofRef extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private final ClassTag ct;
      private Object[] elems;

      public Object[] elems() {
         return this.elems;
      }

      public void elems_$eq(final Object[] x$1) {
         this.elems = x$1;
      }

      private Object[] mkArray(final int size) {
         if (this.capacity() == size && this.capacity() > 0) {
            return this.elems();
         } else {
            return this.elems() == null ? (Object[])this.ct.newArray(size) : Arrays.copyOf(this.elems(), size);
         }
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofRef addOne(final Object elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public Object[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            Object[] res = this.elems();
            this.elems_$eq((Object[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public void clear() {
         super.clear();
         if (this.elems() != null) {
            Arrays.fill(this.elems(), (Object)null);
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofRef) {
            ofRef var2 = (ofRef)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofRef";
      }

      public ofRef(final ClassTag ct) {
         this.ct = ct;
      }
   }

   public static final class ofByte extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private byte[] elems;

      public byte[] elems() {
         return this.elems;
      }

      public void elems_$eq(final byte[] x$1) {
         this.elems = x$1;
      }

      private byte[] mkArray(final int size) {
         byte[] newelems = new byte[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofByte addOne(final byte elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public byte[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            byte[] res = this.elems();
            this.elems_$eq((byte[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofByte) {
            ofByte var2 = (ofByte)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofByte";
      }
   }

   public static final class ofShort extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private short[] elems;

      public short[] elems() {
         return this.elems;
      }

      public void elems_$eq(final short[] x$1) {
         this.elems = x$1;
      }

      private short[] mkArray(final int size) {
         short[] newelems = new short[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofShort addOne(final short elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public short[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            short[] res = this.elems();
            this.elems_$eq((short[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofShort) {
            ofShort var2 = (ofShort)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofShort";
      }
   }

   public static final class ofChar extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private char[] elems;

      public char[] elems() {
         return this.elems;
      }

      public void elems_$eq(final char[] x$1) {
         this.elems = x$1;
      }

      private char[] mkArray(final int size) {
         char[] newelems = new char[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofChar addOne(final char elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public char[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            char[] res = this.elems();
            this.elems_$eq((char[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofChar) {
            ofChar var2 = (ofChar)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofChar";
      }
   }

   public static final class ofInt extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private int[] elems;

      public int[] elems() {
         return this.elems;
      }

      public void elems_$eq(final int[] x$1) {
         this.elems = x$1;
      }

      private int[] mkArray(final int size) {
         int[] newelems = new int[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofInt addOne(final int elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public int[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            int[] res = this.elems();
            this.elems_$eq((int[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofInt) {
            ofInt var2 = (ofInt)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofInt";
      }
   }

   public static final class ofLong extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private long[] elems;

      public long[] elems() {
         return this.elems;
      }

      public void elems_$eq(final long[] x$1) {
         this.elems = x$1;
      }

      private long[] mkArray(final int size) {
         long[] newelems = new long[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofLong addOne(final long elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public long[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            long[] res = this.elems();
            this.elems_$eq((long[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofLong) {
            ofLong var2 = (ofLong)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofLong";
      }
   }

   public static final class ofFloat extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private float[] elems;

      public float[] elems() {
         return this.elems;
      }

      public void elems_$eq(final float[] x$1) {
         this.elems = x$1;
      }

      private float[] mkArray(final int size) {
         float[] newelems = new float[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofFloat addOne(final float elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public float[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            float[] res = this.elems();
            this.elems_$eq((float[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofFloat) {
            ofFloat var2 = (ofFloat)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofFloat";
      }
   }

   public static final class ofDouble extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private double[] elems;

      public double[] elems() {
         return this.elems;
      }

      public void elems_$eq(final double[] x$1) {
         this.elems = x$1;
      }

      private double[] mkArray(final int size) {
         double[] newelems = new double[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofDouble addOne(final double elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public double[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            double[] res = this.elems();
            this.elems_$eq((double[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofDouble) {
            ofDouble var2 = (ofDouble)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofDouble";
      }
   }

   public static class ofBoolean extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private boolean[] elems;

      public boolean[] elems() {
         return this.elems;
      }

      public void elems_$eq(final boolean[] x$1) {
         this.elems = x$1;
      }

      private boolean[] mkArray(final int size) {
         boolean[] newelems = new boolean[size];
         if (this.size() > 0) {
            Array$.MODULE$.copy(this.elems(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofBoolean addOne(final boolean elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public boolean[] result() {
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            boolean[] res = this.elems();
            this.elems_$eq((boolean[])null);
            return res;
         } else {
            return this.mkArray(this.size());
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof ofBoolean) {
            ofBoolean var2 = (ofBoolean)other;
            return this.size() == var2.size() && this.elems() == var2.elems();
         } else {
            return false;
         }
      }

      public String toString() {
         return "ArrayBuilder.ofBoolean";
      }
   }

   public static final class ofUnit extends ArrayBuilder {
      private static final long serialVersionUID = 3L;

      public BoxedUnit[] elems() {
         throw new UnsupportedOperationException();
      }

      public ofUnit addOne(final BoxedUnit elem) {
         int newSize = this.size() + 1;
         this.ensureSize(newSize);
         this.size_$eq(newSize);
         return this;
      }

      public ofUnit addAll(final IterableOnce xs) {
         int newSize = this.size() + xs.iterator().size();
         this.ensureSize(newSize);
         this.size_$eq(newSize);
         return this;
      }

      public ofUnit addAll(final Object xs, final int offset, final int length) {
         int newSize = this.size() + length;
         this.ensureSize(newSize);
         this.size_$eq(newSize);
         return this;
      }

      public BoxedUnit[] result() {
         BoxedUnit[] ans = new BoxedUnit[this.size()];

         for(int i = 0; i < this.size(); ++i) {
            ans[i] = BoxedUnit.UNIT;
         }

         return ans;
      }

      public boolean equals(final Object other) {
         if (other instanceof ofUnit) {
            ofUnit var2 = (ofUnit)other;
            return this.size() == var2.size();
         } else {
            return false;
         }
      }

      public void resize(final int size) {
         this.capacity_$eq(size);
      }

      public String toString() {
         return "ArrayBuilder.ofUnit";
      }
   }
}
