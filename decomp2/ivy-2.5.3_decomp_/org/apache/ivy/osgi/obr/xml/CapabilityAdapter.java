package org.apache.ivy.osgi.obr.xml;

import java.text.ParseException;
import org.apache.ivy.osgi.core.BundleCapability;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.ExportPackage;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public class CapabilityAdapter {
   public static void adapt(BundleInfo bundleInfo, Capability capability) throws ParseException {
      switch (capability.getName()) {
         case "package":
            bundleInfo.addCapability(getExportPackage(bundleInfo, capability));
         case "bundle":
            break;
         case "service":
            bundleInfo.addCapability(getOSGiService(bundleInfo, capability));
            break;
         default:
            Message.warn("Unsupported capability '" + name + "' on the bundle '" + bundleInfo.getSymbolicName() + "'");
      }

   }

   private static ExportPackage getExportPackage(BundleInfo bundleInfo, Capability capability) throws ParseException {
      String pkgName = null;
      Version version = null;
      String uses = null;

      for(CapabilityProperty property : capability.getProperties()) {
         switch (property.getName()) {
            case "package":
               pkgName = property.getValue();
               break;
            case "uses":
               uses = property.getValue();
               break;
            case "version":
               version = new Version(property.getValue());
               break;
            default:
               Message.warn("Unsupported property '" + propName + "' on the 'package' capability of the bundle '" + bundleInfo.getSymbolicName() + "'");
         }
      }

      if (pkgName == null) {
         throw new ParseException("No package name for the capability", 0);
      } else {
         ExportPackage exportPackage = new ExportPackage(pkgName, version);
         if (uses != null) {
            for(String use : StringUtils.splitToArray(uses)) {
               exportPackage.addUse(use);
            }
         }

         return exportPackage;
      }
   }

   private static BundleCapability getOSGiService(BundleInfo bundleInfo, Capability capability) throws ParseException {
      String name = null;
      Version version = null;

      for(CapabilityProperty property : capability.getProperties()) {
         switch (property.getName()) {
            case "service":
               name = property.getValue();
               break;
            case "version":
               version = new Version(property.getValue());
               break;
            default:
               Message.warn("Unsupported property '" + propName + "' on the 'package' capability of the bundle '" + bundleInfo.getSymbolicName() + "'");
         }
      }

      if (name == null) {
         throw new ParseException("No service name for the capability", 0);
      } else {
         return new BundleCapability("service", name, version);
      }
   }
}
