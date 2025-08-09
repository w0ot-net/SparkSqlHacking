package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.jcajce.PKIXCertStore;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathBuilderSpi extends CertPathBuilderSpi {
   private final boolean isForCRLCheck;
   private Exception certPathException;

   public PKIXCertPathBuilderSpi() {
      this(false);
   }

   PKIXCertPathBuilderSpi(boolean var1) {
      this.isForCRLCheck = var1;
   }

   public CertPathBuilderResult engineBuild(CertPathParameters var1) throws CertPathBuilderException, InvalidAlgorithmParameterException {
      PKIXExtendedBuilderParameters var2;
      if (var1 instanceof PKIXBuilderParameters) {
         PKIXExtendedParameters.Builder var3 = new PKIXExtendedParameters.Builder((PKIXBuilderParameters)var1);
         PKIXExtendedBuilderParameters.Builder var4;
         if (!(var1 instanceof ExtendedPKIXParameters)) {
            var4 = new PKIXExtendedBuilderParameters.Builder((PKIXBuilderParameters)var1);
         } else {
            ExtendedPKIXBuilderParameters var5 = (ExtendedPKIXBuilderParameters)var1;
            Iterator var6 = var5.getAdditionalStores().iterator();

            while(var6.hasNext()) {
               var3.addCertificateStore((PKIXCertStore)var6.next());
            }

            var4 = new PKIXExtendedBuilderParameters.Builder(var3.build());
            var4.addExcludedCerts(var5.getExcludedCerts());
            var4.setMaxPathLength(var5.getMaxPathLength());
         }

         var2 = var4.build();
      } else {
         if (!(var1 instanceof PKIXExtendedBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
         }

         var2 = (PKIXExtendedBuilderParameters)var1;
      }

      ArrayList var10 = new ArrayList();
      Collection var8 = CertPathValidatorUtilities.findTargets(var2);
      CertPathBuilderResult var7 = null;

      X509Certificate var11;
      for(Iterator var9 = var8.iterator(); var9.hasNext() && var7 == null; var7 = this.build(var11, var2, var10)) {
         var11 = (X509Certificate)var9.next();
      }

      if (var7 == null && this.certPathException != null) {
         if (this.certPathException instanceof AnnotatedException) {
            throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
         } else {
            throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
         }
      } else if (var7 == null && this.certPathException == null) {
         throw new CertPathBuilderException("Unable to find certificate chain.");
      } else {
         return var7;
      }
   }

   protected CertPathBuilderResult build(X509Certificate var1, PKIXExtendedBuilderParameters var2, List var3) {
      if (var3.contains(var1)) {
         return null;
      } else if (var2.getExcludedCerts().contains(var1)) {
         return null;
      } else if (var2.getMaxPathLength() != -1 && var3.size() - 1 > var2.getMaxPathLength()) {
         return null;
      } else {
         var3.add(var1);
         CertPathBuilderResult var6 = null;

         CertificateFactory var4;
         PKIXCertPathValidatorSpi var5;
         try {
            var4 = new CertificateFactory();
            var5 = new PKIXCertPathValidatorSpi(this.isForCRLCheck);
         } catch (Exception var15) {
            throw new RuntimeException("Exception creating support classes.");
         }

         try {
            if (CertPathValidatorUtilities.isIssuerTrustAnchor(var1, var2.getBaseParameters().getTrustAnchors(), var2.getBaseParameters().getSigProvider())) {
               Object var17 = null;
               Object var19 = null;

               try {
                  var18 = var4.engineGenerateCertPath(var3);
               } catch (Exception var12) {
                  throw new AnnotatedException("Certification path could not be constructed from certificate list.", var12);
               }

               try {
                  var20 = (PKIXCertPathValidatorResult)var5.engineValidate(var18, var2);
               } catch (Exception var11) {
                  throw new AnnotatedException("Certification path could not be validated.", var11);
               }

               return new PKIXCertPathBuilderResult(var18, var20.getTrustAnchor(), var20.getPolicyTree(), var20.getPublicKey());
            }

            ArrayList var7 = new ArrayList();
            var7.addAll(var2.getBaseParameters().getCertificateStores());

            try {
               var7.addAll(CertPathValidatorUtilities.getAdditionalStoresFromAltNames(var1.getExtensionValue(Extension.issuerAlternativeName.getId()), var2.getBaseParameters().getNamedCertificateStoreMap()));
            } catch (CertificateParsingException var14) {
               throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", var14);
            }

            HashSet var8 = new HashSet();

            try {
               var8.addAll(CertPathValidatorUtilities.findIssuerCerts(var1, var2.getBaseParameters().getCertStores(), var7));
            } catch (AnnotatedException var13) {
               throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", var13);
            }

            if (var8.isEmpty()) {
               throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
            }

            X509Certificate var10;
            for(Iterator var9 = var8.iterator(); var9.hasNext() && var6 == null; var6 = this.build(var10, var2, var3)) {
               var10 = (X509Certificate)var9.next();
            }
         } catch (AnnotatedException var16) {
            this.certPathException = var16;
         }

         if (var6 == null) {
            var3.remove(var1);
         }

         return var6;
      }
   }
}
