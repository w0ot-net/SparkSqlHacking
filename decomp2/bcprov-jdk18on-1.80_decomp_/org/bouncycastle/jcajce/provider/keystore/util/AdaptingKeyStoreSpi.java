package org.bouncycastle.jcajce.provider.keystore.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Enumeration;
import org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Properties;

public class AdaptingKeyStoreSpi extends KeyStoreSpi {
   public static final String COMPAT_OVERRIDE = "keystore.type.compat";
   private final JKSKeyStoreSpi jksStore;
   private final KeyStoreSpi primaryStore;
   private KeyStoreSpi keyStoreSpi;

   public AdaptingKeyStoreSpi(JcaJceHelper var1, KeyStoreSpi var2) {
      this.jksStore = new JKSKeyStoreSpi(var1);
      this.primaryStore = var2;
      this.keyStoreSpi = var2;
   }

   public boolean engineProbe(InputStream var1) throws IOException {
      return this.keyStoreSpi instanceof PKCS12KeyStoreSpi ? ((PKCS12KeyStoreSpi)this.keyStoreSpi).engineProbe(var1) : false;
   }

   public Key engineGetKey(String var1, char[] var2) throws NoSuchAlgorithmException, UnrecoverableKeyException {
      return this.keyStoreSpi.engineGetKey(var1, var2);
   }

   public Certificate[] engineGetCertificateChain(String var1) {
      return this.keyStoreSpi.engineGetCertificateChain(var1);
   }

   public Certificate engineGetCertificate(String var1) {
      return this.keyStoreSpi.engineGetCertificate(var1);
   }

   public Date engineGetCreationDate(String var1) {
      return this.keyStoreSpi.engineGetCreationDate(var1);
   }

   public void engineSetKeyEntry(String var1, Key var2, char[] var3, Certificate[] var4) throws KeyStoreException {
      this.keyStoreSpi.engineSetKeyEntry(var1, var2, var3, var4);
   }

   public void engineSetKeyEntry(String var1, byte[] var2, Certificate[] var3) throws KeyStoreException {
      this.keyStoreSpi.engineSetKeyEntry(var1, var2, var3);
   }

   public void engineSetCertificateEntry(String var1, Certificate var2) throws KeyStoreException {
      this.keyStoreSpi.engineSetCertificateEntry(var1, var2);
   }

   public void engineDeleteEntry(String var1) throws KeyStoreException {
      this.keyStoreSpi.engineDeleteEntry(var1);
   }

   public Enumeration engineAliases() {
      return this.keyStoreSpi.engineAliases();
   }

   public boolean engineContainsAlias(String var1) {
      return this.keyStoreSpi.engineContainsAlias(var1);
   }

   public int engineSize() {
      return this.keyStoreSpi.engineSize();
   }

   public boolean engineIsKeyEntry(String var1) {
      return this.keyStoreSpi.engineIsKeyEntry(var1);
   }

   public boolean engineIsCertificateEntry(String var1) {
      return this.keyStoreSpi.engineIsCertificateEntry(var1);
   }

   public String engineGetCertificateAlias(Certificate var1) {
      return this.keyStoreSpi.engineGetCertificateAlias(var1);
   }

   public void engineStore(OutputStream var1, char[] var2) throws IOException, NoSuchAlgorithmException, CertificateException {
      this.keyStoreSpi.engineStore(var1, var2);
   }

   public void engineStore(KeyStore.LoadStoreParameter var1) throws IOException, NoSuchAlgorithmException, CertificateException {
      this.keyStoreSpi.engineStore(var1);
   }

   public void engineLoad(InputStream var1, char[] var2) throws IOException, NoSuchAlgorithmException, CertificateException {
      if (var1 == null) {
         this.keyStoreSpi = this.primaryStore;
         this.keyStoreSpi.engineLoad((InputStream)null, var2);
      } else {
         if (!Properties.isOverrideSet("keystore.type.compat") && this.primaryStore instanceof PKCS12KeyStoreSpi) {
            this.keyStoreSpi = this.primaryStore;
         } else {
            if (!((InputStream)var1).markSupported()) {
               var1 = new BufferedInputStream((InputStream)var1);
            }

            ((InputStream)var1).mark(8);
            if (this.jksStore.engineProbe((InputStream)var1)) {
               this.keyStoreSpi = this.jksStore;
            } else {
               this.keyStoreSpi = this.primaryStore;
            }

            ((InputStream)var1).reset();
         }

         this.keyStoreSpi.engineLoad((InputStream)var1, var2);
      }

   }

   public void engineLoad(KeyStore.LoadStoreParameter var1) throws IOException, NoSuchAlgorithmException, CertificateException {
      this.keyStoreSpi.engineLoad(var1);
   }
}
