package spire.math;

import algebra.ring.CommutativeRig;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\rmv!B/_\u0011\u0003\u0019g!B3_\u0011\u00031\u0007\"\u00029\u0002\t\u0003\t\b\"\u0002:\u0002\t\u000b\u0019\bB\u0002:\u0002\t\u000b\u0011i\u0003C\u0004\u00034\u0005!)A!\u000e\t\u000f\te\u0012\u0001\"\u0002\u0003<!9!qH\u0001\u0005\u0006\t\u0005\u0003b\u0002B#\u0003\u0011\r!q\t\u0005\n\u0005\u0017\n!\u0019!C\u0003\u0003kCqA!\u0014\u0002A\u00035A\u000fC\u0005\u0003R\u0005\u0011\r\u0011\"\u0002\u00026\"9!1K\u0001!\u0002\u001b!\b\u0002\u0003B,\u0003\u0011\u0015aL!\u0017\t\u0011\t=\u0011\u0001\"\u0002_\u0005kB!Ba \u0002\u0005\u0004%)\u0001YA\u001d\u0011!\u0011\t)\u0001Q\u0001\u000e\u0005m\u0002B\u0003BB\u0003\t\u0007IQ\u00011\u0002D!A!QQ\u0001!\u0002\u001b\t)\u0005C\u0004\u0003\b\u0006!)A!#\t\u000f\t=\u0015\u0001\"\u0002\u0003\u0012\"9!QS\u0001\u0005\u0006\t]\u0005b\u0002BN\u0003\u0011\u0015!Q\u0014\u0005\b\u0005C\u000bAQ\u0001BR\u0011\u001d\u00119+\u0001C\u0003\u0005SCqA!,\u0002\t\u000b\u0011y\u000bC\u0004\u00034\u0006!)A!.\t\u000f\te\u0016\u0001\"\u0002\u0003<\"9!qX\u0001\u0005\u0006\t\u0005\u0007b\u0002Be\u0003\u0011\u0015!1\u001a\u0005\b\u0005'\fAQ\u0001Bk\u0011\u001d\u0011i.\u0001C\u0003\u0005?DqAa:\u0002\t\u000b\u0011I\u000fC\u0004\u0003r\u0006!)Aa=\t\u000f\tm\u0018\u0001\"\u0002\u0003~\"91qA\u0001\u0005\u0006\r%\u0001bBB\n\u0003\u0011\u00151Q\u0003\u0005\b\u00073\tAQAB\u000e\u0011\u001d\u0019\u0019#\u0001C\u0003\u0007KAqa!\f\u0002\t\u000b\u0019y\u0003C\u0004\u00048\u0005!)a!\u000f\t\u000f\r\u0005\u0013\u0001\"\u0002\u0004D!911J\u0001\u0005\u0006\r5\u0003bBB+\u0003\u0011\u00151q\u000b\u0005\b\u00077\nAQAB/\u0011\u001d\u0019)'\u0001C\u0003\u0007OBqaa\u001c\u0002\t\u000b\u0019\t\bC\u0004\u0004z\u0005!)aa\u001f\t\u000f\r\r\u0015\u0001\"\u0002\u0004\u0006\"91QR\u0001\u0005\u0006\r=\u0005bBBL\u0003\u0011\u00151\u0011\u0014\u0005\b\u0007C\u000bAQABR\u0011%\u0019Y+AA\u0001\n\u000b\u0019i\u000bC\u0005\u00042\u0006\t\t\u0011\"\u0002\u00044\u001a!QM\u0018\u0002v\u0011!IhG!b\u0001\n\u0003Q\b\u0002\u0003@7\u0005\u0003\u0005\u000b\u0011B>\t\u000bA4D\u0011A@\t\u000f\u0005\ra\u0007\"\u0002\u0002\u0006!9\u0011Q\u0002\u001c\u0005\u0006\u0005=\u0001bBA\fm\u0011\u0015\u0011\u0011\u0004\u0005\b\u0003C1DQAA\u0012\u0011\u0019\tYC\u000eC\u0003u\"9\u0011Q\u0006\u001c\u0005\u0006\u0005=\u0002bBA\u001cm\u0011\u0015\u0011\u0011\b\u0005\b\u0003\u00032DQAA\"\u0011\u001d\tiF\u000eC#\u0003?Bq!!\u001d7\t\u000b\t\u0019\bC\u0004\u0002\u0000Y\")!!!\t\u000f\u0005\u0015e\u0007\"\u0002\u0002\b\"9\u00111\u0012\u001c\u0005\u0006\u00055\u0005bBAIm\u0011\u0015\u00111\u0013\u0005\b\u0003/3DQAAM\u0011\u001d\tiJ\u000eC\u0003\u0003?Cq!a+7\t\u000b\ti\u000bC\u0004\u00024Z\")!!.\t\u000f\u0005]f\u0007\"\u0002\u0002:\"9\u0011Q\u0018\u001c\u0005\u0006\u0005}\u0006bBAbm\u0011\u0015\u0011Q\u0019\u0005\b\u0003\u00134DQAAf\u0011\u001d\tyM\u000eC\u0003\u0003#Dq!!67\t\u000b\t9\u000eC\u0004\u0002bZ\")!!.\t\u000f\u0005\rh\u0007\"\u0002\u0002f\"9\u00111\u001e\u001c\u0005\u0006\u00055\bbBAym\u0011\u0015\u00111\u001f\u0005\b\u0003o4DQAA}\u0011\u001d\tiP\u000eC\u0003\u0003\u007fDqAa\u00017\t\u000b\u0011)\u0001C\u0004\u0003\nY\")Aa\u0003\t\u000f\t=a\u0007\"\u0002\u0003\u0012!I!Q\u0003\u001c\u0002\u0002\u0013\u0005#q\u0003\u0005\n\u000531\u0014\u0011!C!\u00057\tQ!\u0016'p]\u001eT!a\u00181\u0002\t5\fG\u000f\u001b\u0006\u0002C\u0006)1\u000f]5sK\u000e\u0001\u0001C\u00013\u0002\u001b\u0005q&!B+M_:<7cA\u0001h[B\u0011\u0001n[\u0007\u0002S*\t!.A\u0003tG\u0006d\u0017-\u0003\u0002mS\n1\u0011I\\=SK\u001a\u0004\"\u0001\u001a8\n\u0005=t&AD+M_:<\u0017J\\:uC:\u001cWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\r\fQ!\u00199qYf$2\u0001\u001eB\u0014!\t!gg\u0005\u00027mB\u0011\u0001n^\u0005\u0003q&\u0014a!\u00118z-\u0006d\u0017AB:jO:,G-F\u0001|!\tAG0\u0003\u0002~S\n!Aj\u001c8h\u0003\u001d\u0019\u0018n\u001a8fI\u0002\"2\u0001^A\u0001\u0011\u0015I\u0018\b1\u0001|\u0003\u0019!xNQ=uKV\u0011\u0011q\u0001\t\u0004Q\u0006%\u0011bAA\u0006S\n!!)\u001f;f\u0003\u0019!xn\u00115beV\u0011\u0011\u0011\u0003\t\u0004Q\u0006M\u0011bAA\u000bS\n!1\t[1s\u0003\u001d!xn\u00155peR,\"!a\u0007\u0011\u0007!\fi\"C\u0002\u0002 %\u0014Qa\u00155peR\fQ\u0001^8J]R,\"!!\n\u0011\u0007!\f9#C\u0002\u0002*%\u00141!\u00138u\u0003\u0019!x\u000eT8oO\u00069Ao\u001c$m_\u0006$XCAA\u0019!\rA\u00171G\u0005\u0004\u0003kI'!\u0002$m_\u0006$\u0018\u0001\u0003;p\t>,(\r\\3\u0016\u0005\u0005m\u0002c\u00015\u0002>%\u0019\u0011qH5\u0003\r\u0011{WO\u00197f\u0003!!xNQ5h\u0013:$XCAA#!\u0011\t9%a\u0016\u000f\t\u0005%\u00131\u000b\b\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011q\n2\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0017bAA+S\u00069\u0001/Y2lC\u001e,\u0017\u0002BA-\u00037\u0012aAQ5h\u0013:$(bAA+S\u0006AAo\\*ue&tw\r\u0006\u0002\u0002bA!\u00111MA6\u001d\u0011\t)'a\u001a\u0011\u0007\u0005-\u0013.C\u0002\u0002j%\fa\u0001\u0015:fI\u00164\u0017\u0002BA7\u0003_\u0012aa\u0015;sS:<'bAA5S\u00061A%Z9%KF$B!!\u001e\u0002|A\u0019\u0001.a\u001e\n\u0007\u0005e\u0014NA\u0004C_>dW-\u00198\t\r\u0005u4\t1\u0001u\u0003\u0011!\b.\u0019;\u0002\u0011\u0011\u0012\u0017M\\4%KF$B!!\u001e\u0002\u0004\"1\u0011Q\u0010#A\u0002Q\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005U\u0014\u0011\u0012\u0005\u0007\u0003{*\u0005\u0019\u0001;\u0002\u0017\u0011*\u0017\u000f\n2b]\u001e$S-\u001d\u000b\u0005\u0003k\ny\t\u0003\u0004\u0002~\u0019\u0003\r\u0001^\u0001\tI1,7o\u001d\u0013fcR!\u0011QOAK\u0011\u0019\tih\u0012a\u0001i\u0006)A\u0005\\3tgR!\u0011QOAN\u0011\u0019\ti\b\u0013a\u0001i\u0006YAe\u001a:fCR,'\u000fJ3r)\u0011\t)(!)\t\r\u0005u\u0014\n1\u0001uQ\rI\u0015Q\u0015\t\u0004Q\u0006\u001d\u0016bAAUS\n1\u0011N\u001c7j]\u0016\f\u0001\u0002J4sK\u0006$XM\u001d\u000b\u0005\u0003k\ny\u000b\u0003\u0004\u0002~)\u0003\r\u0001\u001e\u0015\u0004\u0015\u0006\u0015\u0016\u0001D;oCJLx\fJ7j]V\u001cX#\u0001;\u0002\u000b\u0011\u0002H.^:\u0015\u0007Q\fY\f\u0003\u0004\u0002~1\u0003\r\u0001^\u0001\u0007I5Lg.^:\u0015\u0007Q\f\t\r\u0003\u0004\u0002~5\u0003\r\u0001^\u0001\u0007IQLW.Z:\u0015\u0007Q\f9\r\u0003\u0004\u0002~9\u0003\r\u0001^\u0001\u0005I\u0011Lg\u000fF\u0002u\u0003\u001bDa!! P\u0001\u0004!\u0018\u0001\u0003\u0013qKJ\u001cWM\u001c;\u0015\u0007Q\f\u0019\u000e\u0003\u0004\u0002~A\u0003\r\u0001^\u0001\rI\u0011Lg\u000f\n9fe\u000e,g\u000e\u001e\u000b\u0005\u00033\fy\u000eE\u0003i\u00037$H/C\u0002\u0002^&\u0014a\u0001V;qY\u0016\u0014\u0004BBA?#\u0002\u0007A/\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013uS2$W-\u0001\u0006%Y\u0016\u001c8\u000f\n7fgN$2\u0001^At\u0011\u001d\tIo\u0015a\u0001\u0003K\tQa\u001d5jMR\f\u0001\u0003J4sK\u0006$XM\u001d\u0013he\u0016\fG/\u001a:\u0015\u0007Q\fy\u000fC\u0004\u0002jR\u0003\r!!\n\u00021\u0011:'/Z1uKJ$sM]3bi\u0016\u0014He\u001a:fCR,'\u000fF\u0002u\u0003kDq!!;V\u0001\u0004\t)#\u0001\u0003%C6\u0004Hc\u0001;\u0002|\"1\u0011Q\u0010,A\u0002Q\fA\u0001\n2beR\u0019AO!\u0001\t\r\u0005ut\u000b1\u0001u\u0003\r!S\u000f\u001d\u000b\u0004i\n\u001d\u0001BBA?1\u0002\u0007A/\u0001\u0007%i&lWm\u001d\u0013uS6,7\u000fF\u0002u\u0005\u001bAa!! Z\u0001\u0004!\u0018aA4dIR\u0019AOa\u0005\t\r\u0005u$\f1\u0001u\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0013\u0003\u0019)\u0017/^1mgR!\u0011Q\u000fB\u000f\u0011%\u0011y\u0002XA\u0001\u0002\u0004\u0011\t#A\u0002yIE\u00022\u0001\u001bB\u0012\u0013\r\u0011)#\u001b\u0002\u0004\u0003:L\bB\u0002B\u0015\u0007\u0001\u000710A\u0001oQ\r\u0019\u0011Q\u0015\u000b\u0004i\n=\u0002b\u0002B\u0019\t\u0001\u0007\u0011\u0011M\u0001\u0002g\u00069aM]8n\u0013:$Hc\u0001;\u00038!9!\u0011F\u0003A\u0002\u0005\u0015\u0012\u0001\u00034s_6duN\\4\u0015\u0007Q\u0014i\u0004\u0003\u0004\u0003*\u0019\u0001\ra_\u0001\u000bMJ|WNQ5h\u0013:$Hc\u0001;\u0003D!9!\u0011F\u0004A\u0002\u0005\u0015\u0013!D;m_:<Gk\u001c\"jO&sG\u000f\u0006\u0003\u0002F\t%\u0003B\u0002B\u0015\u0011\u0001\u0007A/\u0001\u0005NS:4\u0016\r\\;f\u0003%i\u0015N\u001c,bYV,\u0007\u0005K\u0002\u000b\u0003K\u000b\u0001\"T1y-\u0006dW/Z\u0001\n\u001b\u0006Dh+\u00197vK\u0002B3\u0001DAS\u0003\r\u0001xn\u001e\u000b\bi\nm#q\fB2\u0011\u0019\u0011i&\u0004a\u0001w\u0006\tA\u000f\u0003\u0004\u0003b5\u0001\ra_\u0001\u0002E\"1!QM\u0007A\u0002m\f\u0011!\u001a\u0015\u0004\u001b\t%\u0004\u0003\u0002B6\u0005cj!A!\u001c\u000b\u0007\t=\u0014.\u0001\u0006b]:|G/\u0019;j_:LAAa\u001d\u0003n\t9A/Y5me\u0016\u001cG#\u0002;\u0003x\tm\u0004B\u0002B=\u001d\u0001\u0007A/A\u0001b\u0011\u0019\u0011\tG\u0004a\u0001i\"\u001aaB!\u001b\u0002\u001b1KW.\u001b;Bg\u0012{WO\u00197f\u00039a\u0015.\\5u\u0003N$u.\u001e2mK\u0002\nQ\u0002T5nSR\f5OQ5h\u0013:$\u0018A\u0004'j[&$\u0018i\u001d\"jO&sG\u000fI\u0001\u0011i>\u0014\u0015\u0010^3%Kb$XM\\:j_:$B!a\u0002\u0003\f\"1!QR\nA\u0002Q\fQ\u0001\n;iSN\f\u0001\u0003^8DQ\u0006\u0014H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005E!1\u0013\u0005\u0007\u0005\u001b#\u0002\u0019\u0001;\u0002#Q|7\u000b[8si\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0002\u001c\te\u0005B\u0002BG+\u0001\u0007A/A\bu_&sG\u000fJ3yi\u0016t7/[8o)\u0011\t)Ca(\t\r\t5e\u00031\u0001u\u0003A!x\u000eT8oO\u0012*\u0007\u0010^3og&|g\u000eF\u0002|\u0005KCaA!$\u0018\u0001\u0004!\u0018!\u0005;p\r2|\u0017\r\u001e\u0013fqR,gn]5p]R!\u0011\u0011\u0007BV\u0011\u0019\u0011i\t\u0007a\u0001i\u0006\u0011Bo\u001c#pk\ndW\rJ3yi\u0016t7/[8o)\u0011\tYD!-\t\r\t5\u0015\u00041\u0001u\u0003I!xNQ5h\u0013:$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005\u0015#q\u0017\u0005\u0007\u0005\u001bS\u0002\u0019\u0001;\u0002%Q|7\u000b\u001e:j]\u001e$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003?\u0012i\f\u0003\u0004\u0003\u000en\u0001\r\u0001^\u0001\u0011I\u0015\fH%Z9%Kb$XM\\:j_:$BAa1\u0003HR!\u0011Q\u000fBc\u0011\u0019\ti\b\ba\u0001i\"1!Q\u0012\u000fA\u0002Q\f!\u0003\n2b]\u001e$S-\u001d\u0013fqR,gn]5p]R!!Q\u001aBi)\u0011\t)Ha4\t\r\u0005uT\u00041\u0001u\u0011\u0019\u0011i)\ba\u0001i\u0006\u0019B%Z9%KF$S-\u001d\u0013fqR,gn]5p]R!!q\u001bBn)\u0011\t)H!7\t\r\u0005ud\u00041\u0001u\u0011\u0019\u0011iI\ba\u0001i\u0006)B%Z9%E\u0006tw\rJ3rI\u0015DH/\u001a8tS>tG\u0003\u0002Bq\u0005K$B!!\u001e\u0003d\"1\u0011QP\u0010A\u0002QDaA!$ \u0001\u0004!\u0018A\u0005\u0013mKN\u001cH%Z9%Kb$XM\\:j_:$BAa;\u0003pR!\u0011Q\u000fBw\u0011\u0019\ti\b\ta\u0001i\"1!Q\u0012\u0011A\u0002Q\fq\u0002\n7fgN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0005k\u0014I\u0010\u0006\u0003\u0002v\t]\bBBA?C\u0001\u0007A\u000f\u0003\u0004\u0003\u000e\u0006\u0002\r\u0001^\u0001\u0016I\u001d\u0014X-\u0019;fe\u0012*\u0017\u000fJ3yi\u0016t7/[8o)\u0011\u0011ypa\u0001\u0015\t\u0005U4\u0011\u0001\u0005\u0007\u0003{\u0012\u0003\u0019\u0001;\t\r\t5%\u00051\u0001uQ\r\u0011\u0013QU\u0001\u0013I\u001d\u0014X-\u0019;fe\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004\f\r=A\u0003BA;\u0007\u001bAa!! $\u0001\u0004!\bB\u0002BGG\u0001\u0007A\u000fK\u0002$\u0003K\u000ba#\u001e8bef|F%\\5okN$S\r\u001f;f]NLwN\u001c\u000b\u0004i\u000e]\u0001B\u0002BGI\u0001\u0007A/A\b%a2,8\u000fJ3yi\u0016t7/[8o)\u0011\u0019ib!\t\u0015\u0007Q\u001cy\u0002\u0003\u0004\u0002~\u0015\u0002\r\u0001\u001e\u0005\u0007\u0005\u001b+\u0003\u0019\u0001;\u0002!\u0011j\u0017N\\;tI\u0015DH/\u001a8tS>tG\u0003BB\u0014\u0007W!2\u0001^B\u0015\u0011\u0019\tiH\na\u0001i\"1!Q\u0012\u0014A\u0002Q\f\u0001\u0003\n;j[\u0016\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\rE2Q\u0007\u000b\u0004i\u000eM\u0002BBA?O\u0001\u0007A\u000f\u0003\u0004\u0003\u000e\u001e\u0002\r\u0001^\u0001\u000fI\u0011Lg\u000fJ3yi\u0016t7/[8o)\u0011\u0019Yda\u0010\u0015\u0007Q\u001ci\u0004\u0003\u0004\u0002~!\u0002\r\u0001\u001e\u0005\u0007\u0005\u001bC\u0003\u0019\u0001;\u0002%\u0011\u0002XM]2f]R$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007\u000b\u001aI\u0005F\u0002u\u0007\u000fBa!! *\u0001\u0004!\bB\u0002BGS\u0001\u0007A/\u0001\f%I&4H\u0005]3sG\u0016tG\u000fJ3yi\u0016t7/[8o)\u0011\u0019yea\u0015\u0015\t\u0005e7\u0011\u000b\u0005\u0007\u0003{R\u0003\u0019\u0001;\t\r\t5%\u00061\u0001u\u0003Y)h.\u0019:z?\u0012\"\u0018\u000e\u001c3fI\u0015DH/\u001a8tS>tGc\u0001;\u0004Z!1!QR\u0016A\u0002Q\fA\u0003\n7fgN$C.Z:tI\u0015DH/\u001a8tS>tG\u0003BB0\u0007G\"2\u0001^B1\u0011\u001d\tI\u000f\fa\u0001\u0003KAaA!$-\u0001\u0004!\u0018A\u0007\u0013he\u0016\fG/\u001a:%OJ,\u0017\r^3sI\u0015DH/\u001a8tS>tG\u0003BB5\u0007[\"2\u0001^B6\u0011\u001d\tI/\fa\u0001\u0003KAaA!$.\u0001\u0004!\u0018A\t\u0013he\u0016\fG/\u001a:%OJ,\u0017\r^3sI\u001d\u0014X-\u0019;fe\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004t\r]Dc\u0001;\u0004v!9\u0011\u0011\u001e\u0018A\u0002\u0005\u0015\u0002B\u0002BG]\u0001\u0007A/\u0001\b%C6\u0004H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\ru4\u0011\u0011\u000b\u0004i\u000e}\u0004BBA?_\u0001\u0007A\u000f\u0003\u0004\u0003\u000e>\u0002\r\u0001^\u0001\u000fI\t\f'\u000fJ3yi\u0016t7/[8o)\u0011\u00199ia#\u0015\u0007Q\u001cI\t\u0003\u0004\u0002~A\u0002\r\u0001\u001e\u0005\u0007\u0005\u001b\u0003\u0004\u0019\u0001;\u0002\u001b\u0011*\b\u000fJ3yi\u0016t7/[8o)\u0011\u0019\tj!&\u0015\u0007Q\u001c\u0019\n\u0003\u0004\u0002~E\u0002\r\u0001\u001e\u0005\u0007\u0005\u001b\u000b\u0004\u0019\u0001;\u0002-\u0011\"\u0018.\\3tIQLW.Z:%Kb$XM\\:j_:$Baa'\u0004 R\u0019Ao!(\t\r\u0005u$\u00071\u0001u\u0011\u0019\u0011iI\ra\u0001i\u0006iqm\u00193%Kb$XM\\:j_:$Ba!*\u0004*R\u0019Aoa*\t\r\u0005u4\u00071\u0001u\u0011\u0019\u0011ii\ra\u0001i\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\u00119ba,\t\r\t5E\u00071\u0001u\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u00046\u000eeF\u0003BA;\u0007oC\u0011Ba\b6\u0003\u0003\u0005\rA!\t\t\r\t5U\u00071\u0001u\u0001"
)
public final class ULong {
   private final long signed;

   public static boolean equals$extension(final long $this, final Object x$1) {
      return ULong$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final long $this) {
      return ULong$.MODULE$.hashCode$extension($this);
   }

   public static long gcd$extension(final long $this, final long that) {
      return ULong$.MODULE$.gcd$extension($this, that);
   }

   public static long $times$times$extension(final long $this, final long that) {
      return ULong$.MODULE$.$times$times$extension($this, that);
   }

   public static long $up$extension(final long $this, final long that) {
      return ULong$.MODULE$.$up$extension($this, that);
   }

   public static long $bar$extension(final long $this, final long that) {
      return ULong$.MODULE$.$bar$extension($this, that);
   }

   public static long $amp$extension(final long $this, final long that) {
      return ULong$.MODULE$.$amp$extension($this, that);
   }

   public static long $greater$greater$greater$extension(final long $this, final int shift) {
      return ULong$.MODULE$.$greater$greater$greater$extension($this, shift);
   }

   public static long $greater$greater$extension(final long $this, final int shift) {
      return ULong$.MODULE$.$greater$greater$extension($this, shift);
   }

   public static long $less$less$extension(final long $this, final int shift) {
      return ULong$.MODULE$.$less$less$extension($this, shift);
   }

   public static long unary_$tilde$extension(final long $this) {
      return ULong$.MODULE$.unary_$tilde$extension($this);
   }

   public static Tuple2 $div$percent$extension(final long $this, final long that) {
      return ULong$.MODULE$.$div$percent$extension($this, that);
   }

   public static long $percent$extension(final long $this, final long that) {
      return ULong$.MODULE$.$percent$extension($this, that);
   }

   public static long $div$extension(final long $this, final long that) {
      return ULong$.MODULE$.$div$extension($this, that);
   }

   public static long $times$extension(final long $this, final long that) {
      return ULong$.MODULE$.$times$extension($this, that);
   }

   public static long $minus$extension(final long $this, final long that) {
      return ULong$.MODULE$.$minus$extension($this, that);
   }

   public static long $plus$extension(final long $this, final long that) {
      return ULong$.MODULE$.$plus$extension($this, that);
   }

   public static long unary_$minus$extension(final long $this) {
      return ULong$.MODULE$.unary_$minus$extension($this);
   }

   public static boolean $greater$extension(final long $this, final long that) {
      return ULong$.MODULE$.$greater$extension($this, that);
   }

   public static boolean $greater$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$greater$eq$extension($this, that);
   }

   public static boolean $less$extension(final long $this, final long that) {
      return ULong$.MODULE$.$less$extension($this, that);
   }

   public static boolean $less$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$less$eq$extension($this, that);
   }

   public static boolean $eq$bang$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$eq$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$eq$eq$eq$extension($this, that);
   }

   public static boolean $bang$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$bang$eq$extension($this, that);
   }

   public static boolean $eq$eq$extension(final long $this, final long that) {
      return ULong$.MODULE$.$eq$eq$extension($this, that);
   }

   public static String toString$extension(final long $this) {
      return ULong$.MODULE$.toString$extension($this);
   }

   public static BigInt toBigInt$extension(final long $this) {
      return ULong$.MODULE$.toBigInt$extension($this);
   }

   public static double toDouble$extension(final long $this) {
      return ULong$.MODULE$.toDouble$extension($this);
   }

   public static float toFloat$extension(final long $this) {
      return ULong$.MODULE$.toFloat$extension($this);
   }

   public static long toLong$extension(final long $this) {
      return ULong$.MODULE$.toLong$extension($this);
   }

   public static int toInt$extension(final long $this) {
      return ULong$.MODULE$.toInt$extension($this);
   }

   public static short toShort$extension(final long $this) {
      return ULong$.MODULE$.toShort$extension($this);
   }

   public static char toChar$extension(final long $this) {
      return ULong$.MODULE$.toChar$extension($this);
   }

   public static byte toByte$extension(final long $this) {
      return ULong$.MODULE$.toByte$extension($this);
   }

   public static long MaxValue() {
      return ULong$.MODULE$.MaxValue();
   }

   public static long MinValue() {
      return ULong$.MODULE$.MinValue();
   }

   public static BigInt ulongToBigInt(final long n) {
      return ULong$.MODULE$.ulongToBigInt(n);
   }

   public static long fromBigInt(final BigInt n) {
      return ULong$.MODULE$.fromBigInt(n);
   }

   public static long fromLong(final long n) {
      return ULong$.MODULE$.fromLong(n);
   }

   public static long fromInt(final int n) {
      return ULong$.MODULE$.fromInt(n);
   }

   public static long apply(final String s) {
      return ULong$.MODULE$.apply(s);
   }

   public static long apply(final long n) {
      return ULong$.MODULE$.apply(n);
   }

   public static NumberTag ULongTag() {
      return ULong$.MODULE$.ULongTag();
   }

   public static BitString ULongBitString() {
      return ULong$.MODULE$.ULongBitString();
   }

   public static CommutativeRig ULongAlgebra() {
      return ULong$.MODULE$.ULongAlgebra();
   }

   public long signed() {
      return this.signed;
   }

   public final byte toByte() {
      return ULong$.MODULE$.toByte$extension(this.signed());
   }

   public final char toChar() {
      return ULong$.MODULE$.toChar$extension(this.signed());
   }

   public final short toShort() {
      return ULong$.MODULE$.toShort$extension(this.signed());
   }

   public final int toInt() {
      return ULong$.MODULE$.toInt$extension(this.signed());
   }

   public final long toLong() {
      return ULong$.MODULE$.toLong$extension(this.signed());
   }

   public final float toFloat() {
      return ULong$.MODULE$.toFloat$extension(this.signed());
   }

   public final double toDouble() {
      return ULong$.MODULE$.toDouble$extension(this.signed());
   }

   public final BigInt toBigInt() {
      return ULong$.MODULE$.toBigInt$extension(this.signed());
   }

   public final String toString() {
      return ULong$.MODULE$.toString$extension(this.signed());
   }

   public final boolean $eq$eq(final long that) {
      return ULong$.MODULE$.$eq$eq$extension(this.signed(), that);
   }

   public final boolean $bang$eq(final long that) {
      return ULong$.MODULE$.$bang$eq$extension(this.signed(), that);
   }

   public final boolean $eq$eq$eq(final long that) {
      return ULong$.MODULE$.$eq$eq$eq$extension(this.signed(), that);
   }

   public final boolean $eq$bang$eq(final long that) {
      return ULong$.MODULE$.$eq$bang$eq$extension(this.signed(), that);
   }

   public final boolean $less$eq(final long that) {
      return ULong$.MODULE$.$less$eq$extension(this.signed(), that);
   }

   public final boolean $less(final long that) {
      return ULong$.MODULE$.$less$extension(this.signed(), that);
   }

   public final boolean $greater$eq(final long that) {
      return ULong$.MODULE$.$greater$eq$extension(this.signed(), that);
   }

   public final boolean $greater(final long that) {
      return ULong$.MODULE$.$greater$extension(this.signed(), that);
   }

   public final long unary_$minus() {
      return ULong$.MODULE$.unary_$minus$extension(this.signed());
   }

   public final long $plus(final long that) {
      return ULong$.MODULE$.$plus$extension(this.signed(), that);
   }

   public final long $minus(final long that) {
      return ULong$.MODULE$.$minus$extension(this.signed(), that);
   }

   public final long $times(final long that) {
      return ULong$.MODULE$.$times$extension(this.signed(), that);
   }

   public final long $div(final long that) {
      return ULong$.MODULE$.$div$extension(this.signed(), that);
   }

   public final long $percent(final long that) {
      return ULong$.MODULE$.$percent$extension(this.signed(), that);
   }

   public final Tuple2 $div$percent(final long that) {
      return ULong$.MODULE$.$div$percent$extension(this.signed(), that);
   }

   public final long unary_$tilde() {
      return ULong$.MODULE$.unary_$tilde$extension(this.signed());
   }

   public final long $less$less(final int shift) {
      return ULong$.MODULE$.$less$less$extension(this.signed(), shift);
   }

   public final long $greater$greater(final int shift) {
      return ULong$.MODULE$.$greater$greater$extension(this.signed(), shift);
   }

   public final long $greater$greater$greater(final int shift) {
      return ULong$.MODULE$.$greater$greater$greater$extension(this.signed(), shift);
   }

   public final long $amp(final long that) {
      return ULong$.MODULE$.$amp$extension(this.signed(), that);
   }

   public final long $bar(final long that) {
      return ULong$.MODULE$.$bar$extension(this.signed(), that);
   }

   public final long $up(final long that) {
      return ULong$.MODULE$.$up$extension(this.signed(), that);
   }

   public final long $times$times(final long that) {
      return ULong$.MODULE$.$times$times$extension(this.signed(), that);
   }

   public final long gcd(final long that) {
      return ULong$.MODULE$.gcd$extension(this.signed(), that);
   }

   public int hashCode() {
      return ULong$.MODULE$.hashCode$extension(this.signed());
   }

   public boolean equals(final Object x$1) {
      return ULong$.MODULE$.equals$extension(this.signed(), x$1);
   }

   public ULong(final long signed) {
      this.signed = signed;
   }
}
