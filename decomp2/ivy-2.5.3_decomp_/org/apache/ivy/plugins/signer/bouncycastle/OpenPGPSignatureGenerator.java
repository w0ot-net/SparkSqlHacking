package org.apache.ivy.plugins.signer.bouncycastle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Iterator;
import org.apache.ivy.plugins.signer.SignatureGenerator;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;

public class OpenPGPSignatureGenerator implements SignatureGenerator {
   private static final long MASK = 4294967295L;
   private String name;
   private String secring;
   private String password;
   private String keyId;
   private PGPSecretKey pgpSec;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getExtension() {
      return "asc";
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setSecring(String secring) {
      this.secring = secring;
   }

   public void setKeyId(String keyId) {
      if (!"auto".equals(keyId)) {
         this.keyId = keyId;
      }

   }

   public void sign(File src, File dest) throws IOException {
      OutputStream out = null;
      InputStream in = null;
      InputStream keyIn = null;

      try {
         if (this.secring == null) {
            this.secring = System.getProperty("user.home") + "/.gnupg/secring.gpg";
         }

         if (this.pgpSec == null) {
            keyIn = new FileInputStream(this.secring);
            this.pgpSec = this.readSecretKey(keyIn);
         }

         PBESecretKeyDecryptor decryptor = (new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider())).build(this.password.toCharArray());
         PGPPrivateKey pgpPrivKey = this.pgpSec.extractPrivateKey(decryptor);
         PGPSignatureGenerator sGen = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(this.pgpSec.getPublicKey().getAlgorithm(), 2));
         sGen.init(0, pgpPrivKey);
         in = new FileInputStream(src);
         out = new BCPGOutputStream(new ArmoredOutputStream(new FileOutputStream(dest)));
         int ch = 0;

         while((ch = in.read()) >= 0) {
            sGen.update((byte)ch);
         }

         sGen.generate().encode(out);
      } catch (PGPException e) {
         throw new IOException(e);
      } finally {
         if (out != null) {
            try {
               out.close();
            } catch (IOException var22) {
            }
         }

         if (in != null) {
            try {
               in.close();
            } catch (IOException var21) {
            }
         }

         if (keyIn != null) {
            try {
               keyIn.close();
            } catch (IOException var20) {
            }
         }

      }

   }

   private PGPSecretKey readSecretKey(InputStream in) throws IOException, PGPException {
      in = PGPUtil.getDecoderStream(in);
      PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(in, new BcKeyFingerprintCalculator());
      PGPSecretKey key = null;
      Iterator<PGPSecretKeyRing> it = pgpSec.getKeyRings();

      while(key == null && it.hasNext()) {
         PGPSecretKeyRing kRing = (PGPSecretKeyRing)it.next();
         Iterator<PGPSecretKey> it2 = kRing.getSecretKeys();

         while(key == null && it2.hasNext()) {
            PGPSecretKey k = (PGPSecretKey)it2.next();
            if (this.keyId == null && k.isSigningKey()) {
               key = k;
            }

            if (this.keyId != null && Long.valueOf(this.keyId, 16) == (k.getKeyID() & 4294967295L)) {
               key = k;
            }
         }
      }

      if (key == null) {
         throw new IllegalArgumentException("Can't find encryption key" + (this.keyId != null ? " '" + this.keyId + "' " : " ") + "in key ring.");
      } else {
         return key;
      }
   }

   static {
      Security.addProvider(new BouncyCastleProvider());
   }
}
