package org.apache.ivy.plugins.resolver.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.repository.Repository;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.util.Message;

public final class ResolverHelper {
   private ResolverHelper() {
   }

   public static String[] listTokenValues(Repository rep, String pattern, String token) {
      String fileSep = rep.getFileSeparator();
      pattern = rep.standardize(pattern);
      String tokenString = IvyPatternHelper.getTokenString(token);
      int index = pattern.indexOf(tokenString);
      if (index == -1) {
         Message.verbose("unable to list " + token + " in " + pattern + ": token not found in pattern");
         return null;
      } else if (pattern.length() > index + tokenString.length() && !fileSep.equals(pattern.substring(index + tokenString.length(), index + tokenString.length() + 1)) || index != 0 && !fileSep.equals(pattern.substring(index - 1, index))) {
         int slashIndex = pattern.substring(0, index).lastIndexOf(fileSep);
         String root = slashIndex == -1 ? "" : pattern.substring(0, slashIndex);

         try {
            Message.debug("\tusing " + rep + " to list all in " + root);
            String[] all = listAll(rep, root);
            if (all == null) {
               return null;
            } else {
               Message.debug("\t\tfound " + all.length + " urls");
               List<String> ret = new ArrayList(all.length);
               int endNameIndex = pattern.indexOf(fileSep, slashIndex + 1);
               String namePattern;
               if (endNameIndex != -1) {
                  namePattern = pattern.substring(slashIndex + 1, endNameIndex);
               } else {
                  namePattern = pattern.substring(slashIndex + 1);
               }

               namePattern = namePattern.replaceAll("\\.", "\\\\.");
               namePattern = IvyPatternHelper.substituteToken(namePattern, token, "(.+)");
               Pattern p = Pattern.compile(namePattern);

               for(String path : all) {
                  Matcher m = p.matcher(path);
                  if (m.matches()) {
                     String value = m.group(1);
                     ret.add(value);
                  }
               }

               Message.debug("\t\t" + ret.size() + " matched " + pattern);
               return (String[])ret.toArray(new String[ret.size()]);
            }
         } catch (Exception e) {
            Message.warn("problem while listing resources in " + root + " with " + rep, e);
            return null;
         }
      } else {
         String root = pattern.substring(0, index);
         return listAll(rep, root);
      }
   }

   public static String[] listAll(Repository rep, String parent) {
      try {
         String fileSep = rep.getFileSeparator();
         Message.debug("\tusing " + rep + " to list all in " + parent);
         List<String> all = rep.list(parent);
         if (all == null) {
            Message.debug("\t\tno resources found");
            return null;
         } else {
            Message.debug("\t\tfound " + all.size() + " resources");
            List<String> names = new ArrayList(all.size());

            for(String path : all) {
               if (path.endsWith(fileSep)) {
                  path = path.substring(0, path.length() - 1);
               }

               int slashIndex = path.lastIndexOf(fileSep);
               names.add(path.substring(slashIndex + 1));
            }

            return (String[])names.toArray(new String[names.size()]);
         }
      } catch (IOException e) {
         Message.verbose("problem while listing resources in " + parent + " with " + rep, e);
         return null;
      } catch (Exception e) {
         Message.warn("problem while listing resources in " + parent + " with " + rep, e);
         return null;
      }
   }

   public static ResolvedResource[] findAll(Repository rep, ModuleRevisionId mrid, String pattern, Artifact artifact) {
      String partiallyResolvedPattern = IvyPatternHelper.substitute(pattern, ModuleRevisionId.newInstance(mrid, IvyPatternHelper.getTokenString("revision")), artifact);
      Message.debug("\tlisting all in " + partiallyResolvedPattern);
      String[] revs = listTokenValues(rep, partiallyResolvedPattern, "revision");
      if (revs == null) {
         if (!partiallyResolvedPattern.contains("[revision]")) {
            try {
               Resource res = rep.getResource(partiallyResolvedPattern);
               if (res.exists()) {
                  Message.debug("\tonly one resource found without real listing: using and defining it as working@" + rep.getName() + " revision: " + res.getName());
                  return new ResolvedResource[]{new ResolvedResource(res, "working@" + rep.getName())};
               }
            } catch (IOException e) {
               Message.debug("\timpossible to get resource from name listed by repository: " + partiallyResolvedPattern, e);
            }

            Message.debug("\tno revision found");
         }

         return null;
      } else {
         Message.debug("\tfound revs: " + Arrays.asList(revs));
         List<ResolvedResource> ret = new ArrayList(revs.length);

         for(String rev : revs) {
            String rres = IvyPatternHelper.substituteToken(partiallyResolvedPattern, "revision", rev);

            try {
               Resource res = rep.getResource(rres);
               if (res != null) {
                  ret.add(new ResolvedResource(res, rev));
               }
            } catch (IOException e) {
               Message.warn("impossible to get resource from name listed by repository: " + rres, e);
            }
         }

         if (revs.length != ret.size()) {
            Message.debug("\tfound resolved res: " + ret);
         }

         return (ResolvedResource[])ret.toArray(new ResolvedResource[ret.size()]);
      }
   }

   public static String[] listTokenValues(URLLister lister, String pattern, String token) {
      pattern = standardize(pattern);
      if (lister.accept(pattern)) {
         String tokenString = IvyPatternHelper.getTokenString(token);
         int index = pattern.indexOf(tokenString);
         if (index == -1) {
            Message.verbose("unable to list " + token + " in " + pattern + ": token not found in pattern");
            return null;
         } else if ((pattern.length() <= index + tokenString.length() || "/".equals(pattern.substring(index + tokenString.length(), index + tokenString.length() + 1))) && (index == 0 || "/".equals(pattern.substring(index - 1, index)))) {
            String root = pattern.substring(0, index);

            try {
               return listAll(lister, new URL(root));
            } catch (MalformedURLException e) {
               Message.warn("malformed url from pattern root: " + root + ": " + e.getMessage());
               return null;
            }
         } else {
            int slashIndex = pattern.substring(0, index).lastIndexOf(47);
            String root = slashIndex == -1 ? "" : pattern.substring(0, slashIndex);

            try {
               Message.debug("\tusing " + lister + " to list all in " + root);
               List<URL> all = lister.listAll(new URL(root));
               Message.debug("\t\tfound " + all.size() + " urls");
               List<String> ret = new ArrayList(all.size());
               int endNameIndex = pattern.indexOf(47, slashIndex + 1);
               String namePattern;
               if (endNameIndex != -1) {
                  namePattern = pattern.substring(slashIndex + 1, endNameIndex);
               } else {
                  namePattern = pattern.substring(slashIndex + 1);
               }

               String acceptNamePattern = ".*" + IvyPatternHelper.substituteToken(namePattern, token, "([^/]+)") + ".*";
               Pattern p = Pattern.compile(acceptNamePattern);

               for(URL url : all) {
                  String path = standardize(url.getPath());
                  Matcher m = p.matcher(path);
                  if (m.matches()) {
                     String value = m.group(1);
                     ret.add(value);
                  }
               }

               Message.debug("\t\t" + ret.size() + " matched " + pattern);
               return (String[])ret.toArray(new String[ret.size()]);
            } catch (Exception e) {
               Message.warn("problem while listing files in " + root, e);
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static String standardize(String path) {
      return path.replace('\\', '/');
   }

   public static String[] listAll(URLLister lister, URL root) {
      try {
         if (lister.accept(root.toExternalForm())) {
            Message.debug("\tusing " + lister + " to list all in " + root);
            List<URL> all = lister.listAll(root);
            Message.debug("\t\tfound " + all.size() + " urls");
            List<String> names = new ArrayList(all.size());

            for(URL dir : all) {
               String path = dir.getPath();
               if (path.endsWith("/")) {
                  path = path.substring(0, path.length() - 1);
               }

               int slashIndex = path.lastIndexOf(47);
               names.add(path.substring(slashIndex + 1));
            }

            return (String[])names.toArray(new String[names.size()]);
         } else {
            return null;
         }
      } catch (Exception e) {
         Message.warn("problem while listing directories in " + root, e);
         return null;
      }
   }
}
