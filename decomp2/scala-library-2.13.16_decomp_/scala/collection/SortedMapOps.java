package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.List;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r=faB\u0015+!\u0003\r\ta\f\u0005\u0006M\u0002!\ta\u001a\u0005\u0006W\u00021\t\u0001\u001c\u0005\u0006a\u0002!)\"\u001d\u0005\b\u0003C\u0001a\u0011AA\u0012\u0011\u001d\t9\u0003\u0001D\u0001\u0003SAq!a\u000e\u0001\r\u0003\tI\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!9\u00111\n\u0001\u0005\u0002\u0005%\u0003bBA'\u0001\u0011\u0005\u0011q\n\u0005\b\u00037\u0002A\u0011AA/\u0011\u001d\t\t\u0007\u0001C\u0001\u0003GBq!!\u001b\u0001\t\u0003\nYG\u0002\u0004\u0002t\u0001A\u0011Q\u000f\u0005\b\u0003#sA\u0011AAJ\u0011\u001d\t9J\u0004C\u0001\u00033Cq!!*\u000f\t\u0003\t9KB\u0006\u0002\u0002\u0002\u0001\n1!\u0005\u0002\u0004\u00065\u0005\"\u00024\u0013\t\u00039\u0007B\u0002?\u0013\t\u0007\t)\tC\u0004\u0002(I!\t!!#\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"9\u0011\u0011\u001e\u0001\u0005\u0002\u0005-\bb\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\b\u0005c\u0001A\u0011\tB\u001a\u0011\u001d\u00119\u0005\u0001C#\u0005\u0013BqA!\u0018\u0001\t\u0003\u0012y\u0006C\u0004\u0003^\u0001!\tE!\"\b\u000f\t%&\u0006#\u0001\u0003,\u001a1\u0011F\u000bE\u0001\u0005[Cq!!%\u001f\t\u0003\u0011y\u000b\u0003\u0006\u00032z\u0011\r\u0011\"\u0002+\u0005gC\u0001Ba.\u001fA\u00035!Q\u0017\u0004\u0007\u0005ss\u0002Aa/\t\u0015\tM(E!A!\u0002\u0013\u0011)\u0010\u0003\u0006\u0004>\t\u0012\t\u0011)A\u0005\u0007\u007fAq!!%#\t\u0003\u00199\u0005C\u0004\u00024\n\"\ta!\u001c\t\u000f\u0005%(\u0005\"\u0001\u0004\n\"91q\u0015\u0012\u0005B\r%&\u0001D*peR,G-T1q\u001fB\u001c(BA\u0016-\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002[\u0005)1oY1mC\u000e\u0001Q#\u0002\u0019<\u000bB[5\u0003\u0002\u00012k\r\u0004\"AM\u001a\u000e\u00031J!\u0001\u000e\u0017\u0003\r\u0005s\u0017PU3g!\u00191t'\u000f#H\u00156\t!&\u0003\u00029U\t1Q*\u00199PaN\u0004\"AO\u001e\r\u0001\u0011)A\b\u0001b\u0001{\t\t1*\u0005\u0002?\u0003B\u0011!gP\u0005\u0003\u00012\u0012qAT8uQ&tw\r\u0005\u00023\u0005&\u00111\t\f\u0002\u0004\u0003:L\bC\u0001\u001eF\t\u00191\u0005\u0001\"b\u0001{\t\ta\u000b\u0005\u00027\u0011&\u0011\u0011J\u000b\u0002\u0004\u001b\u0006\u0004\bC\u0001\u001eL\t\u0019a\u0005\u0001\"b\u0001\u001b\n\t1)\u0005\u0002?\u001dB1a\u0007A\u001dE\u001f*\u0003\"A\u000f)\u0005\rE\u0003AQ1\u0001S\u0005\t\u00195)F\u0002T3r\u000b\"A\u0010+\u0013\u0007U;fL\u0002\u0003W\u0001\u0001!&\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004\u0003\u0002\u001cI1n\u0003\"AO-\u0005\u000bi\u0003&\u0019A\u001f\u0003\u0003a\u0003\"A\u000f/\u0005\u000bu\u0003&\u0019A\u001f\u0003\u0003e\u0003$aX1\u0011\rY\u0002\u0001lW(a!\tQ\u0014\rB\u0005c!\u0006\u0005\t\u0011!B\u0001{\t\u0019q\fJ\u0019\u0011\tY\"\u0017HS\u0005\u0003K*\u0012\u0011bU8si\u0016$w\n]:\u0002\r\u0011Jg.\u001b;%)\u0005A\u0007C\u0001\u001aj\u0013\tQGF\u0001\u0003V]&$\u0018\u0001E:peR,G-T1q\r\u0006\u001cGo\u001c:z+\u0005i\u0007c\u0001\u001co\u001f&\u0011qN\u000b\u0002\u0011'>\u0014H/\u001a3NCB4\u0015m\u0019;pef\fQc]8si\u0016$W*\u00199Ge>l\u0017\n^3sC\ndW-F\u0002smf$2a]A\u0005)\t!8\u0010\u0005\u0003;!VD\bC\u0001\u001ew\t\u001598A1\u0001>\u0005\tY%\u0007\u0005\u0002;s\u0012)!p\u0001b\u0001{\t\u0011aK\r\u0005\u0006y\u000e\u0001\u001d!`\u0001\t_J$WM]5oOB!a0a\u0001v\u001d\t\u0011t0C\u0002\u0002\u00021\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0006\u0005\u001d!\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0007\u0005\u0005A\u0006C\u0004\u0002\f\r\u0001\r!!\u0004\u0002\u0005%$\b#\u0002\u001c\u0002\u0010\u0005M\u0011bAA\tU\tA\u0011\n^3sC\ndW\rE\u00033\u0003+)\b0C\u0002\u0002\u00181\u0012a\u0001V;qY\u0016\u0014\u0004fA\u0002\u0002\u001cA\u0019!'!\b\n\u0007\u0005}AF\u0001\u0004j]2Lg.Z\u0001\tk:\u001cxN\u001d;fIV\u0011\u0011Q\u0005\t\u0005m!KD)\u0001\u0007ji\u0016\u0014\u0018\r^8s\rJ|W\u000e\u0006\u0003\u0002,\u0005M\u0002#\u0002\u001c\u0002.\u0005E\u0012bAA\u0018U\tA\u0011\n^3sCR|'\u000fE\u00033\u0003+ID\t\u0003\u0004\u00026\u0015\u0001\r!O\u0001\u0006gR\f'\u000f^\u0001\u0011W\u0016L8/\u0013;fe\u0006$xN\u001d$s_6$B!a\u000f\u0002>A!a'!\f:\u0011\u0019\t)D\u0002a\u0001s\u0005\u0011b/\u00197vKNLE/\u001a:bi>\u0014hI]8n)\u0011\t\u0019%!\u0012\u0011\tY\ni\u0003\u0012\u0005\u0007\u0003k9\u0001\u0019A\u001d\u0002\u0011\u0019L'o\u001d;LKf,\u0012!O\u0001\bY\u0006\u001cHoS3z\u0003!i\u0017N\\!gi\u0016\u0014H\u0003BA)\u0003/\u0002RAMA*\u0003cI1!!\u0016-\u0005\u0019y\u0005\u000f^5p]\"1\u0011\u0011\f\u0006A\u0002e\n1a[3z\u0003%i\u0017\r\u001f\"fM>\u0014X\r\u0006\u0003\u0002R\u0005}\u0003BBA-\u0017\u0001\u0007\u0011(A\u0004sC:<W\rV8\u0015\u0007)\u000b)\u0007\u0003\u0004\u0002h1\u0001\r!O\u0001\u0003i>\faa[3z'\u0016$XCAA7!\u00111\u0014qN\u001d\n\u0007\u0005E$FA\u0005T_J$X\rZ*fi\na1*Z=T_J$X\rZ*fiNAa\"MA7\u0003o\ny\b\u0005\u0003\u0002z\u0005mT\"\u0001\u0001\n\u0007\u0005utGA\u0005HK:\\U-_*fiB\u0019\u0011\u0011\u0010\n\u0003\u001f\u001d+gnS3z'>\u0014H/\u001a3TKR\u001cBAE\u0019\u0002xU\u0011\u0011q\u0011\t\u0005}\u0006\r\u0011\b\u0006\u0003\u0002<\u0005-\u0005BBA\u001b+\u0001\u0007\u0011H\u0005\u0004\u0002\u0010\u0006}\u0014Q\u000e\u0004\u0006-\u0002\u0001\u0011QR\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005U\u0005cAA=\u001d\u0005!A-\u001b4g)\u0011\ti'a'\t\u000f\u0005u\u0005\u00031\u0001\u0002 \u0006!A\u000f[1u!\u00111\u0014\u0011U\u001d\n\u0007\u0005\r&FA\u0002TKR\f\u0011B]1oO\u0016LU\u000e\u001d7\u0015\r\u00055\u0014\u0011VAX\u0011\u001d\tY+\u0005a\u0001\u0003[\u000bAA\u001a:p[B!!'a\u0015:\u0011\u001d\t\t,\u0005a\u0001\u0003[\u000bQ!\u001e8uS2\f1!\\1q+\u0019\t9,a0\u0002DR!\u0011\u0011XAo)\u0011\tY,!2\u0011\ri\u0002\u0016QXAa!\rQ\u0014q\u0018\u0003\u0006oZ\u0011\r!\u0010\t\u0004u\u0005\rG!\u0002>\u0017\u0005\u0004i\u0004B\u0002?\u0017\u0001\b\t9\rE\u0003\u007f\u0003\u0007\ti\f\u000b\u0005\u0002F\u0006-\u0017q[Am!\u0011\ti-a5\u000e\u0005\u0005='bAAiY\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005U\u0017q\u001a\u0002\u0011S6\u0004H.[2ji:{GOR8v]\u0012\f1!\\:hC\t\tY.AA\u000f\u001d>\u0004\u0013.\u001c9mS\u000eLG\u000fI(sI\u0016\u0014\u0018N\\4\\Im\\%'`/!M>,h\u000e\u001a\u0011u_\u0002\u0012W/\u001b7eA\u0005\u00043k\u001c:uK\u0012l\u0015\r].%w.\u0013T\u0010\f\u0011%wZ\u0013T0\u0018\u0018!3>,\b%\\1zA]\fg\u000e\u001e\u0011u_\u0002*\boY1ti\u0002\"x\u000eI1!\u001b\u0006\u00048\fJ>L{2\u0002Ce\u001f,~;\u00022\u0017N]:uA\tL\beY1mY&tw\r\t1v]N|'\u000f^3eA:Bq!a8\u0017\u0001\u0004\t\t/A\u0001g!\u001d\u0011\u00141]A\u0019\u0003OL1!!:-\u0005%1UO\\2uS>t\u0017\u0007E\u00043\u0003+\ti,!1\u0002\u000f\u0019d\u0017\r^'baV1\u0011Q^A{\u0003s$B!a<\u0003\u0002Q!\u0011\u0011_A~!\u0019Q\u0004+a=\u0002xB\u0019!(!>\u0005\u000b]<\"\u0019A\u001f\u0011\u0007i\nI\u0010B\u0003{/\t\u0007Q\b\u0003\u0004}/\u0001\u000f\u0011Q \t\u0006}\u0006\r\u00111\u001f\u0015\t\u0003w\fY-a6\u0002Z\"9\u0011q\\\fA\u0002\t\r\u0001c\u0002\u001a\u0002d\u0006E\"Q\u0001\t\u0006m\t\u001d!1B\u0005\u0004\u0005\u0013Q#\u0001D%uKJ\f'\r\\3P]\u000e,\u0007c\u0002\u001a\u0002\u0016\u0005M\u0018q_\u0001\bG>dG.Z2u+\u0019\u0011\tB!\u0007\u0003\u001eQ!!1\u0003B\u0013)\u0011\u0011)Ba\b\u0011\ri\u0002&q\u0003B\u000e!\rQ$\u0011\u0004\u0003\u0006ob\u0011\r!\u0010\t\u0004u\tuA!\u0002>\u0019\u0005\u0004i\u0004B\u0002?\u0019\u0001\b\u0011\t\u0003E\u0003\u007f\u0003\u0007\u00119\u0002\u000b\u0005\u0003 \u0005-\u0017q[Am\u0011\u001d\u00119\u0003\u0007a\u0001\u0005S\t!\u0001\u001d4\u0011\u000fI\u0012Y#!\r\u00030%\u0019!Q\u0006\u0017\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\u0004rAMA\u000b\u0005/\u0011Y\"\u0001\u0004d_:\u001c\u0017\r^\u000b\u0005\u0005k\u0011Y\u0004\u0006\u0003\u00038\t}\u0002#\u0002\u001eQs\te\u0002c\u0001\u001e\u0003<\u00111!0\u0007b\u0001\u0005{\t\"\u0001R!\t\u000f\t\u0005\u0013\u00041\u0001\u0003D\u000511/\u001e4gSb\u0004RA\u000eB\u0004\u0005\u000b\u0002bAMA\u000bs\te\u0012A\u0003\u0013qYV\u001cH\u0005\u001d7vgV!!1\nB))\u0011\u0011iEa\u0015\u0011\u000bi\u0002\u0016Ha\u0014\u0011\u0007i\u0012\t\u0006\u0002\u0004{5\t\u0007!Q\b\u0005\b\u0005+R\u0002\u0019\u0001B,\u0003\tA8\u000fE\u00037\u0005\u000f\u0011I\u0006\u0005\u00043\u0003+I$q\n\u0015\u00045\u0005m\u0011!\u0002\u0013qYV\u001cX\u0003\u0002B1\u0005O\"BAa\u0019\u0003lA)!\bU\u001d\u0003fA\u0019!Ha\u001a\u0005\u000f\t%4D1\u0001\u0003>\t\u0011a+\r\u0005\b\u0005[Z\u0002\u0019\u0001B8\u0003\tYg\u000f\u0005\u00043\u0003+I$Q\r\u0015\f7\tM$\u0011\u0010B>\u0005\u007f\u0012\t\tE\u00023\u0005kJ1Aa\u001e-\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0011i(\u0001 D_:\u001c\u0018\u000eZ3sAI,\u0017/^5sS:<\u0007%\u00198!S6lW\u000f^1cY\u0016\u0004S*\u00199!_J\u0004c-\u00197mA\t\f7m\u001b\u0011u_\u0002j\u0015\r\u001d\u0018d_:\u001c\u0017\r^\u0001\u0006g&t7-Z\u0011\u0003\u0005\u0007\u000baA\r\u00182g9\u0002T\u0003\u0002BD\u0005\u001b#\u0002B!#\u0003\u0010\nU%\u0011\u0014\t\u0006uAK$1\u0012\t\u0004u\t5Ea\u0002B59\t\u0007!Q\b\u0005\b\u0005#c\u0002\u0019\u0001BJ\u0003\u0015)G.Z72!\u0019\u0011\u0014QC\u001d\u0003\f\"9!q\u0013\u000fA\u0002\tM\u0015!B3mK6\u0014\u0004b\u0002BN9\u0001\u0007!QT\u0001\u0006K2,Wn\u001d\t\u0006e\t}%1S\u0005\u0004\u0005Cc#A\u0003\u001fsKB,\u0017\r^3e}!ZADa\u001d\u0003z\t\u0015&q\u0010BAC\t\u00119+A#Vg\u0016\u00043f\u000b\u0011xSRD\u0007%\u00198!Kb\u0004H.[2ji\u0002\u001aw\u000e\u001c7fGRLwN\u001c\u0011be\u001e,X.\u001a8uA%t7\u000f^3bI\u0002zg\rI\u0016!o&$\b\u000e\t<be\u0006\u0014xm]\u0001\r'>\u0014H/\u001a3NCB|\u0005o\u001d\t\u0003my\u0019\"AH\u0019\u0015\u0005\t-\u0016AB8sI6\u001bx-\u0006\u0002\u00036>\u0011\u0011\u0011\\\u0001\b_J$Wj]4!\u0005)9\u0016\u000e\u001e5GS2$XM]\u000b\r\u0005{\u0013iM!5\u0003V\n\u0005(q`\n\u0004E\t}\u0006\u0003\u0004Ba\u0005\u000f\u0014YMa4\u0003T\n}gb\u0001\u001c\u0003D&\u0019!Q\u0019\u0016\u0002\r5\u000b\u0007o\u00149t\u0013\u0011\u0011IL!3\u000b\u0007\t\u0015'\u0006E\u0002;\u0005\u001b$Q\u0001\u0010\u0012C\u0002u\u00022A\u000fBi\t\u00191%\u0005\"b\u0001{A\u0019!H!6\u0005\u0011\t]'\u0005\"b\u0001\u00053\u0014!\"\u0013;fe\u0006\u0014G.Z\"D+\ri$1\u001c\u0003\b\u0005;\u0014)N1\u0001>\u0005\u0011yF\u0005J\u0019\u0011\u0007i\u0012\t\u000f\u0002\u0005\u0003d\n\")\u0019\u0001Bs\u0005\u0015i\u0015\r]\"D+\u0019\u00119O!<\u0003rF\u0019aH!;\u0011\rYB%1\u001eBx!\rQ$Q\u001e\u0003\u00075\n\u0005(\u0019A\u001f\u0011\u0007i\u0012\t\u0010\u0002\u0004^\u0005C\u0014\r!P\u0001\u0005g\u0016dgM\u0005\u0005\u0003x\ne81EB\u0017\r\u00151f\u0004\u0001B{a\u0011\u0011Ypa\b\u0011\u0015Y\u0002!1\u001aBh\u0005{\u001ci\u0002E\u0002;\u0005\u007f$q!\u0015\u0012\u0005\u0006\u0004\u0019\t!\u0006\u0004\u0004\u0004\r51\u0011C\t\u0004}\r\u0015!CBB\u0004\u0007\u0013\u0019\u0019BB\u0003W=\u0001\u0019)\u0001\u0005\u00047\u0011\u000e-1q\u0002\t\u0004u\r5AA\u0002.\u0003\u0000\n\u0007Q\bE\u0002;\u0007#!a!\u0018B\u0000\u0005\u0004i\u0004\u0007BB\u000b\u00073\u0001\"B\u000e\u0001\u0004\f\r=!Q`B\f!\rQ4\u0011\u0004\u0003\f\u00077\u0011y0!A\u0001\u0002\u000b\u0005QHA\u0002`II\u00022AOB\u0010\t)\u0019\tcIA\u0001\u0002\u0003\u0015\t!\u0010\u0002\u0004?\u0012\u001a\u0004\u0007BB\u0013\u0007S\u0001\"BN\u001c\u0003L\n='q\\B\u0014!\rQ4\u0011\u0006\u0003\u000b\u0007W\u0019\u0013\u0011!A\u0001\u0006\u0003i$aA0%iA\"1qFB\u001d!%14\u0011GB\u001b\u0005'\u001c9$C\u0002\u00044)\u00121\"\u0013;fe\u0006\u0014G.Z(qgB9!'!\u0006\u0003L\n=\u0007c\u0001\u001e\u0004:\u0011Q11H\u0012\u0002\u0002\u0003\u0005)\u0011A\u001f\u0003\u0007}#S'A\u0001q!\u001d\u0011\u00141]B\u001b\u0007\u0003\u00022AMB\"\u0013\r\u0019)\u0005\f\u0002\b\u0005>|G.Z1o)\u0019\u0019Ie!\u0014\u0004lAi11\n\u0012\u0003L\n='1\u001bBp\u0005{l\u0011A\b\u0005\b\u0005g,\u0003\u0019AB(%!\u0019\tfa\u0015\u0004\\\r\rd!\u0002,\u001f\u0001\r=\u0003\u0007BB+\u00073\u0002\"B\u000e\u0001\u0003L\n='Q`B,!\rQ4\u0011\f\u0003\f\u0007C\u0019i%!A\u0001\u0002\u000b\u0005Q\b\r\u0003\u0004^\r\u0005\u0004C\u0003\u001c8\u0005\u0017\u0014yMa8\u0004`A\u0019!h!\u0019\u0005\u0017\r-2QJA\u0001\u0002\u0003\u0015\t!\u0010\u0019\u0005\u0007K\u001aI\u0007E\u00057\u0007c\u0019)Da5\u0004hA\u0019!h!\u001b\u0005\u0017\rm2QJA\u0001\u0002\u0003\u0015\t!\u0010\u0005\b\u0007{)\u0003\u0019AB +\u0019\u0019yga\u001e\u0004|Q!1\u0011OBB)\u0011\u0019\u0019h! \u0011\u000fi\u0012yp!\u001e\u0004zA\u0019!ha\u001e\u0005\u000b]4#\u0019A\u001f\u0011\u0007i\u001aY\bB\u0003{M\t\u0007Q\bC\u0005\u0004\u0000\u0019\n\t\u0011q\u0001\u0004\u0002\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u000by\f\u0019a!\u001e\t\u000f\u0005}g\u00051\u0001\u0004\u0006B9!'a9\u00046\r\u001d\u0005c\u0002\u001a\u0002\u0016\rU4\u0011P\u000b\u0007\u0007\u0017\u001b\u0019ja&\u0015\t\r55q\u0014\u000b\u0005\u0007\u001f\u001bI\nE\u0004;\u0005\u007f\u001c\tj!&\u0011\u0007i\u001a\u0019\nB\u0003xO\t\u0007Q\bE\u0002;\u0007/#QA_\u0014C\u0002uB\u0011ba'(\u0003\u0003\u0005\u001da!(\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0003\u007f\u0003\u0007\u0019\t\nC\u0004\u0002`\u001e\u0002\ra!)\u0011\u000fI\n\u0019o!\u000e\u0004$B)aGa\u0002\u0004&B9!'!\u0006\u0004\u0012\u000eU\u0015AC<ji\"4\u0015\u000e\u001c;feR!1\u0011JBV\u0011\u001d\u0019i\u000b\u000ba\u0001\u0007\u007f\t\u0011!\u001d"
)
public interface SortedMapOps extends MapOps, SortedOps {
   SortedMapFactory sortedMapFactory();

   // $FF: synthetic method
   static Map sortedMapFromIterable$(final SortedMapOps $this, final Iterable it, final Ordering ordering) {
      return $this.sortedMapFromIterable(it, ordering);
   }

   default Map sortedMapFromIterable(final Iterable it, final Ordering ordering) {
      return (Map)this.sortedMapFactory().from(it, ordering);
   }

   Map unsorted();

   Iterator iteratorFrom(final Object start);

   Iterator keysIteratorFrom(final Object start);

   // $FF: synthetic method
   static Iterator valuesIteratorFrom$(final SortedMapOps $this, final Object start) {
      return $this.valuesIteratorFrom(start);
   }

   default Iterator valuesIteratorFrom(final Object start) {
      return this.iteratorFrom(start).map((x$1) -> x$1._2());
   }

   // $FF: synthetic method
   static Object firstKey$(final SortedMapOps $this) {
      return $this.firstKey();
   }

   default Object firstKey() {
      return ((Tuple2)this.head())._1();
   }

   // $FF: synthetic method
   static Object lastKey$(final SortedMapOps $this) {
      return $this.lastKey();
   }

   default Object lastKey() {
      return ((Tuple2)this.last())._1();
   }

   // $FF: synthetic method
   static Option minAfter$(final SortedMapOps $this, final Object key) {
      return $this.minAfter(key);
   }

   default Option minAfter(final Object key) {
      return ((IterableOps)this.rangeFrom(key)).headOption();
   }

   // $FF: synthetic method
   static Option maxBefore$(final SortedMapOps $this, final Object key) {
      return $this.maxBefore(key);
   }

   default Option maxBefore(final Object key) {
      return ((IterableOps)this.rangeUntil(key)).lastOption();
   }

   // $FF: synthetic method
   static SortedMapOps rangeTo$(final SortedMapOps $this, final Object to) {
      return $this.rangeTo(to);
   }

   default SortedMapOps rangeTo(final Object to) {
      Iterator i = ((IterableOnce)this.keySet().rangeFrom(to)).iterator();
      if (i.isEmpty()) {
         return (SortedMapOps)this.coll();
      } else {
         Object next = i.next();
         if (this.ordering().compare(next, to) == 0) {
            return i.isEmpty() ? (SortedMapOps)this.coll() : (SortedMapOps)this.rangeUntil(i.next());
         } else {
            return (SortedMapOps)this.rangeUntil(next);
         }
      }
   }

   // $FF: synthetic method
   static SortedSet keySet$(final SortedMapOps $this) {
      return $this.keySet();
   }

   default SortedSet keySet() {
      return new KeySortedSet();
   }

   // $FF: synthetic method
   static Map map$(final SortedMapOps $this, final Function1 f, final Ordering ordering) {
      return $this.map(f, ordering);
   }

   default Map map(final Function1 f, final Ordering ordering) {
      return (Map)this.sortedMapFactory().from(new View.Map(this, f), ordering);
   }

   // $FF: synthetic method
   static Map flatMap$(final SortedMapOps $this, final Function1 f, final Ordering ordering) {
      return $this.flatMap(f, ordering);
   }

   default Map flatMap(final Function1 f, final Ordering ordering) {
      return (Map)this.sortedMapFactory().from(new View.FlatMap(this, f), ordering);
   }

   // $FF: synthetic method
   static Map collect$(final SortedMapOps $this, final PartialFunction pf, final Ordering ordering) {
      return $this.collect(pf, ordering);
   }

   default Map collect(final PartialFunction pf, final Ordering ordering) {
      return (Map)this.sortedMapFactory().from(new View.Collect(this, pf), ordering);
   }

   // $FF: synthetic method
   static Map concat$(final SortedMapOps $this, final IterableOnce suffix) {
      return $this.concat(suffix);
   }

   default Map concat(final IterableOnce suffix) {
      SortedMapFactory var10000 = this.sortedMapFactory();
      Object var10001;
      if (suffix instanceof Iterable) {
         Iterable var2 = (Iterable)suffix;
         var10001 = new View.Concat(this, var2);
      } else {
         var10001 = this.iterator().concat(() -> suffix.iterator());
      }

      return (Map)var10000.from((IterableOnce)var10001, this.ordering());
   }

   // $FF: synthetic method
   static Map $plus$plus$(final SortedMapOps $this, final IterableOnce xs) {
      return $this.$plus$plus(xs);
   }

   default Map $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   // $FF: synthetic method
   static Map $plus$(final SortedMapOps $this, final Tuple2 kv) {
      return $this.$plus(kv);
   }

   /** @deprecated */
   default Map $plus(final Tuple2 kv) {
      return (Map)this.sortedMapFactory().from(new View.Appended(this, kv), this.ordering());
   }

   // $FF: synthetic method
   static Map $plus$(final SortedMapOps $this, final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return $this.$plus(elem1, elem2, elems);
   }

   /** @deprecated */
   default Map $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return (Map)this.sortedMapFactory().from(new View.Concat(new View.Appended(new View.Appended(this, elem1), elem2), elems), this.ordering());
   }

   static void $init$(final SortedMapOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class KeySortedSet implements SortedSet, GenKeySortedSet {
      // $FF: synthetic field
      public final SortedMapOps $outer;

      public Ordering ordering() {
         return SortedMapOps.GenKeySortedSet.super.ordering();
      }

      public Iterator iteratorFrom(final Object start) {
         return SortedMapOps.GenKeySortedSet.super.iteratorFrom(start);
      }

      public Iterator iterator() {
         return MapOps.GenKeySet.iterator$(this);
      }

      public boolean contains(final Object key) {
         return MapOps.GenKeySet.contains$(this, key);
      }

      public int size() {
         return MapOps.GenKeySet.size$(this);
      }

      public int knownSize() {
         return MapOps.GenKeySet.knownSize$(this);
      }

      public boolean isEmpty() {
         return MapOps.GenKeySet.isEmpty$(this);
      }

      // $FF: synthetic method
      public boolean scala$collection$SortedSet$$super$equals(final Object that) {
         return Set.equals$(this, that);
      }

      public Set unsorted() {
         return SortedSet.unsorted$(this);
      }

      public SortedIterableFactory sortedIterableFactory() {
         return SortedSet.sortedIterableFactory$(this);
      }

      public String stringPrefix() {
         return SortedSet.stringPrefix$(this);
      }

      public boolean equals(final Object that) {
         return SortedSet.equals$(this, that);
      }

      public SortedSet fromSpecific(final IterableOnce coll) {
         return SortedSetFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return SortedSetFactoryDefaults.newSpecificBuilder$(this);
      }

      public SortedSet empty() {
         return SortedSetFactoryDefaults.empty$(this);
      }

      public SortedSetOps.WithFilter withFilter(final Function1 p) {
         return SortedSetFactoryDefaults.withFilter$(this, p);
      }

      // $FF: synthetic method
      public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      // $FF: synthetic method
      public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      /** @deprecated */
      public Iterator keysIteratorFrom(final Object start) {
         return SortedSetOps.keysIteratorFrom$(this, start);
      }

      public Object firstKey() {
         return SortedSetOps.firstKey$(this);
      }

      public Object lastKey() {
         return SortedSetOps.lastKey$(this);
      }

      public Option minAfter(final Object key) {
         return SortedSetOps.minAfter$(this, key);
      }

      public Option maxBefore(final Object key) {
         return SortedSetOps.maxBefore$(this, key);
      }

      public Object min(final Ordering ord) {
         return SortedSetOps.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return SortedSetOps.max$(this, ord);
      }

      public SortedSetOps rangeTo(final Object to) {
         return SortedSetOps.rangeTo$(this, to);
      }

      public SortedSet map(final Function1 f, final Ordering ev) {
         return SortedSetOps.map$(this, f, ev);
      }

      public SortedSet flatMap(final Function1 f, final Ordering ev) {
         return SortedSetOps.flatMap$(this, f, ev);
      }

      public SortedSet zip(final IterableOnce that, final Ordering ev) {
         return SortedSetOps.zip$(this, that, ev);
      }

      public SortedSet collect(final PartialFunction pf, final Ordering ev) {
         return SortedSetOps.collect$(this, pf, ev);
      }

      /** @deprecated */
      public int compare(final Object k0, final Object k1) {
         return SortedOps.compare$(this, k0, k1);
      }

      public Object range(final Object from, final Object until) {
         return SortedOps.range$(this, from, until);
      }

      /** @deprecated */
      public final Object from(final Object from) {
         return SortedOps.from$(this, from);
      }

      public Object rangeFrom(final Object from) {
         return SortedOps.rangeFrom$(this, from);
      }

      /** @deprecated */
      public final Object until(final Object until) {
         return SortedOps.until$(this, until);
      }

      public Object rangeUntil(final Object until) {
         return SortedOps.rangeUntil$(this, until);
      }

      /** @deprecated */
      public final Object to(final Object to) {
         return SortedOps.to$(this, to);
      }

      public boolean canEqual(final Object that) {
         return Set.canEqual$(this, that);
      }

      public int hashCode() {
         return Set.hashCode$(this);
      }

      public IterableFactory iterableFactory() {
         return Set.iterableFactory$(this);
      }

      public String toString() {
         return Set.toString$(this);
      }

      public final boolean apply(final Object elem) {
         return SetOps.apply$(this, elem);
      }

      public boolean subsetOf(final Set that) {
         return SetOps.subsetOf$(this, that);
      }

      public Iterator subsets(final int len) {
         return SetOps.subsets$(this, len);
      }

      public Iterator subsets() {
         return SetOps.subsets$(this);
      }

      public SetOps intersect(final Set that) {
         return SetOps.intersect$(this, that);
      }

      public final SetOps $amp(final Set that) {
         return SetOps.$amp$(this, that);
      }

      public final SetOps $amp$tilde(final Set that) {
         return SetOps.$amp$tilde$(this, that);
      }

      /** @deprecated */
      public SetOps $minus$minus(final IterableOnce that) {
         return SetOps.$minus$minus$(this, that);
      }

      /** @deprecated */
      public SetOps $minus(final Object elem) {
         return SetOps.$minus$(this, elem);
      }

      /** @deprecated */
      public SetOps $minus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
         return SetOps.$minus$(this, elem1, elem2, elems);
      }

      public SetOps concat(final IterableOnce that) {
         return SetOps.concat$(this, that);
      }

      /** @deprecated */
      public SetOps $plus(final Object elem) {
         return SetOps.$plus$(this, elem);
      }

      /** @deprecated */
      public SetOps $plus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
         return SetOps.$plus$(this, elem1, elem2, elems);
      }

      public final SetOps $plus$plus(final IterableOnce that) {
         return SetOps.$plus$plus$(this, that);
      }

      public final SetOps union(final Set that) {
         return SetOps.union$(this, that);
      }

      public final SetOps $bar(final Set that) {
         return SetOps.$bar$(this, that);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      /** @deprecated */
      public final Iterable toIterable() {
         return Iterable.toIterable$(this);
      }

      public final Iterable coll() {
         return Iterable.coll$(this);
      }

      /** @deprecated */
      public Iterable seq() {
         return Iterable.seq$(this);
      }

      public String className() {
         return Iterable.className$(this);
      }

      public final String collectionClassName() {
         return Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final Iterable that) {
         return Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final Iterable toTraversable() {
         return IterableOps.toTraversable$(this);
      }

      public boolean isTraversableAgain() {
         return IterableOps.isTraversableAgain$(this);
      }

      /** @deprecated */
      public final Object repr() {
         return IterableOps.repr$(this);
      }

      /** @deprecated */
      public IterableFactory companion() {
         return IterableOps.companion$(this);
      }

      public Object head() {
         return IterableOps.head$(this);
      }

      public Option headOption() {
         return IterableOps.headOption$(this);
      }

      public Object last() {
         return IterableOps.last$(this);
      }

      public Option lastOption() {
         return IterableOps.lastOption$(this);
      }

      public View view() {
         return IterableOps.view$(this);
      }

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      /** @deprecated */
      public View view(final int from, final int until) {
         return IterableOps.view$(this, from, until);
      }

      public Object transpose(final Function1 asIterable) {
         return IterableOps.transpose$(this, asIterable);
      }

      public Object filter(final Function1 pred) {
         return IterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return IterableOps.filterNot$(this, pred);
      }

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
      }

      public Object dropWhile(final Function1 p) {
         return IterableOps.dropWhile$(this, p);
      }

      public Iterator grouped(final int size) {
         return IterableOps.grouped$(this, size);
      }

      public Iterator sliding(final int size) {
         return IterableOps.sliding$(this, size);
      }

      public Iterator sliding(final int size, final int step) {
         return IterableOps.sliding$(this, size, step);
      }

      public Object tail() {
         return IterableOps.tail$(this);
      }

      public Object init() {
         return IterableOps.init$(this);
      }

      public Object slice(final int from, final int until) {
         return IterableOps.slice$(this, from, until);
      }

      public scala.collection.immutable.Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
         return IterableOps.groupMapReduce$(this, key, f, reduce);
      }

      public Object scan(final Object z, final Function2 op) {
         return IterableOps.scan$(this, z, op);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return IterableOps.scanLeft$(this, z, op);
      }

      public Object scanRight(final Object z, final Function2 op) {
         return IterableOps.scanRight$(this, z, op);
      }

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
      }

      public Object flatMap(final Function1 f) {
         return IterableOps.flatMap$(this, f);
      }

      public Object flatten(final Function1 asIterable) {
         return IterableOps.flatten$(this, asIterable);
      }

      public Object collect(final PartialFunction pf) {
         return IterableOps.collect$(this, pf);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return IterableOps.partitionMap$(this, f);
      }

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      public final Object $plus$plus(final IterableOnce suffix) {
         return IterableOps.$plus$plus$(this, suffix);
      }

      public Object zip(final IterableOnce that) {
         return IterableOps.zip$(this, that);
      }

      public Object zipWithIndex() {
         return IterableOps.zipWithIndex$(this);
      }

      public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
         return IterableOps.zipAll$(this, that, thisElem, thatElem);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return IterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return IterableOps.unzip3$(this, asTriple);
      }

      public Iterator tails() {
         return IterableOps.tails$(this);
      }

      public Iterator inits() {
         return IterableOps.inits$(this);
      }

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public scala.collection.immutable.Map toMap(final $less$colon$less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public scala.collection.immutable.Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public scala.collection.immutable.Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public scala.collection.immutable.IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public SortedSet diff(final Set that) {
         return this.fromSpecific((IterableOnce)this.view().filterNot(that));
      }

      public SortedSet rangeImpl(final Option from, final Option until) {
         SortedMapOps map = (SortedMapOps)this.scala$collection$SortedMapOps$KeySortedSet$$$outer().rangeImpl(from, until);
         return map.new KeySortedSet();
      }

      // $FF: synthetic method
      public SortedMapOps scala$collection$SortedMapOps$KeySortedSet$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public SortedMapOps scala$collection$SortedMapOps$GenKeySortedSet$$$outer() {
         return this.scala$collection$SortedMapOps$KeySortedSet$$$outer();
      }

      // $FF: synthetic method
      public MapOps scala$collection$MapOps$GenKeySet$$$outer() {
         return this.scala$collection$SortedMapOps$KeySortedSet$$$outer();
      }

      public KeySortedSet() {
         if (SortedMapOps.this == null) {
            throw null;
         } else {
            this.$outer = SortedMapOps.this;
            super();
         }
      }
   }

   public interface GenKeySortedSet extends MapOps.GenKeySet {
      default Ordering ordering() {
         return this.scala$collection$SortedMapOps$GenKeySortedSet$$$outer().ordering();
      }

      default Iterator iteratorFrom(final Object start) {
         return this.scala$collection$SortedMapOps$GenKeySortedSet$$$outer().keysIteratorFrom(start);
      }

      // $FF: synthetic method
      SortedMapOps scala$collection$SortedMapOps$GenKeySortedSet$$$outer();

      static void $init$(final GenKeySortedSet $this) {
      }
   }

   public static class WithFilter extends MapOps.WithFilter {
      private final SortedMapOps self;
      private final Function1 p;

      public Map map(final Function1 f, final Ordering evidence$1) {
         return (Map)this.self.sortedMapFactory().from(new View.Map(this.filtered(), f), evidence$1);
      }

      public Map flatMap(final Function1 f, final Ordering evidence$2) {
         return (Map)this.self.sortedMapFactory().from(new View.FlatMap(this.filtered(), f), evidence$2);
      }

      public WithFilter withFilter(final Function1 q) {
         return new WithFilter(this.self, (kv) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, q, kv)));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$withFilter$1(final WithFilter $this, final Function1 q$1, final Tuple2 kv) {
         return BoxesRunTime.unboxToBoolean($this.p.apply(kv)) && BoxesRunTime.unboxToBoolean(q$1.apply(kv));
      }

      public WithFilter(final SortedMapOps self, final Function1 p) {
         super(self, p);
         this.self = self;
         this.p = p;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
