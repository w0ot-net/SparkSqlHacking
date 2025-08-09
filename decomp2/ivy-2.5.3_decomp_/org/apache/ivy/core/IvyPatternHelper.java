package org.apache.ivy.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.settings.IvyVariableContainer;
import org.apache.ivy.core.settings.IvyVariableContainerImpl;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public final class IvyPatternHelper {
   public static final String CONF_KEY = "conf";
   public static final String TYPE_KEY = "type";
   public static final String EXT_KEY = "ext";
   public static final String ARTIFACT_KEY = "artifact";
   public static final String BRANCH_KEY = "branch";
   public static final String REVISION_KEY = "revision";
   public static final String MODULE_KEY = "module";
   public static final String ORGANISATION_KEY = "organisation";
   public static final String ORGANISATION_KEY2 = "organization";
   public static final String ORGANISATION_PATH_KEY = "orgPath";
   public static final String ORIGINAL_ARTIFACTNAME_KEY = "originalname";
   private static final Pattern PARAM_PATTERN = Pattern.compile("\\@\\{(.*?)\\}");
   private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");

   private IvyPatternHelper() {
   }

   public static String substitute(String pattern, ModuleRevisionId moduleRevision) {
      return substitute(pattern, moduleRevision.getOrganisation(), moduleRevision.getName(), moduleRevision.getBranch(), moduleRevision.getRevision(), "ivy", "ivy", "xml", (String)null, (ArtifactOrigin)null, moduleRevision.getQualifiedExtraAttributes(), (Map)null);
   }

   public static String substitute(String pattern, ModuleRevisionId moduleRevision, String artifact, String type, String ext) {
      return substitute(pattern, (ModuleRevisionId)moduleRevision, (Artifact)(new DefaultArtifact(moduleRevision, (Date)null, artifact, type, ext)));
   }

   public static String substitute(String pattern, Artifact artifact) {
      return substitute(pattern, artifact, (String)null);
   }

   public static String substitute(String pattern, Artifact artifact, ArtifactOrigin origin) {
      return substitute(pattern, artifact.getModuleRevisionId(), (Artifact)artifact, (String)null, (ArtifactOrigin)origin);
   }

   public static String substitute(String pattern, Artifact artifact, String conf) {
      return substitute(pattern, artifact.getModuleRevisionId(), (Artifact)artifact, conf, (ArtifactOrigin)null);
   }

   public static String substitute(String pattern, ModuleRevisionId mrid, Artifact artifact) {
      return substitute(pattern, mrid, (Artifact)artifact, (String)null, (ArtifactOrigin)null);
   }

   public static String substitute(String pattern, ModuleRevisionId mrid, Artifact artifact, String conf, ArtifactOrigin origin) {
      return substitute(pattern, mrid.getOrganisation(), mrid.getName(), mrid.getBranch(), mrid.getRevision(), artifact.getName(), artifact.getType(), artifact.getExt(), conf, origin, mrid.getQualifiedExtraAttributes(), artifact.getQualifiedExtraAttributes());
   }

   public static String substitute(String pattern, String org, String module, String revision, String artifact, String type, String ext) {
      return substitute(pattern, org, module, (String)null, revision, artifact, type, ext, (String)null, (ArtifactOrigin)null, (Map)null, (Map)null);
   }

   public static String substitute(String pattern, String org, String module, String revision, String artifact, String type, String ext, String conf) {
      return substitute(pattern, org, module, (String)null, revision, artifact, type, ext, conf, (ArtifactOrigin)null, (Map)null, (Map)null);
   }

   public static String substitute(String pattern, String org, String module, String revision, String artifact, String type, String ext, String conf, Map extraModuleAttributes, Map extraArtifactAttributes) {
      return substitute(pattern, org, module, (String)null, revision, artifact, type, ext, conf, (ArtifactOrigin)null, extraModuleAttributes, extraArtifactAttributes);
   }

   public static String substitute(String pattern, String org, String module, String branch, String revision, String artifact, String type, String ext, String conf, ArtifactOrigin origin, Map extraModuleAttributes, Map extraArtifactAttributes) {
      Map<String, Object> tokens = new HashMap();
      if (extraModuleAttributes != null) {
         for(Map.Entry entry : extraModuleAttributes.entrySet()) {
            String token = (String)entry.getKey();
            if (token.indexOf(58) > 0) {
               token = token.substring(token.indexOf(58) + 1);
            }

            tokens.put(token, new Validated(token, (String)entry.getValue()));
         }
      }

      if (extraArtifactAttributes != null) {
         for(Map.Entry entry : extraArtifactAttributes.entrySet()) {
            String token = (String)entry.getKey();
            if (token.indexOf(58) > 0) {
               token = token.substring(token.indexOf(58) + 1);
            }

            tokens.put(token, new Validated(token, (String)entry.getValue()));
         }
      }

      tokens.put("organisation", org == null ? "" : new Validated("organisation", org));
      tokens.put("organization", org == null ? "" : new Validated("organization", org));
      tokens.put("orgPath", org == null ? "" : org.replace('.', '/'));
      tokens.put("module", module == null ? "" : new Validated("module", module));
      tokens.put("branch", branch == null ? "" : new Validated("branch", branch));
      tokens.put("revision", revision == null ? "" : new Validated("revision", revision));
      tokens.put("artifact", new Validated("artifact", artifact == null ? module : artifact));
      tokens.put("type", type == null ? "jar" : new Validated("type", type));
      tokens.put("ext", ext == null ? "jar" : new Validated("ext", ext));
      tokens.put("conf", conf == null ? "default" : new Validated("conf", conf));
      if (origin == null) {
         tokens.put("originalname", new OriginalArtifactNameValue(org, module, branch, revision, artifact, type, ext, extraModuleAttributes, extraArtifactAttributes));
      } else {
         tokens.put("originalname", new OriginalArtifactNameValue(origin));
      }

      return substituteTokens(pattern, tokens, false);
   }

   public static String substituteVariables(String pattern, Map variables) {
      return substituteVariables(pattern, new IvyVariableContainerImpl(variables), new Stack());
   }

   public static String substituteVariables(String pattern, IvyVariableContainer variables) {
      return substituteVariables(pattern, variables, new Stack());
   }

   private static String substituteVariables(String pattern, IvyVariableContainer variables, Stack substituting) {
      if (pattern == null) {
         return null;
      } else {
         Matcher m = VAR_PATTERN.matcher(pattern);
         boolean useVariables = false;

         StringBuffer sb;
         String val;
         for(sb = null; m.find(); m.appendReplacement(sb, val.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$"))) {
            if (!useVariables) {
               useVariables = true;
               sb = new StringBuffer();
            }

            String var = m.group(1);
            val = variables.getVariable(var);
            if (val != null) {
               int index = substituting.indexOf(var);
               if (index != -1) {
                  List<String> cycle = new ArrayList(substituting.subList(index, substituting.size()));
                  cycle.add(var);
                  throw new IllegalArgumentException("cyclic variable definition: cycle = " + cycle);
               }

               substituting.push(var);
               val = substituteVariables(val, variables, substituting);
               substituting.pop();
            } else {
               val = m.group();
            }
         }

         if (useVariables) {
            m.appendTail(sb);
            return sb.toString();
         } else {
            return pattern;
         }
      }
   }

   public static String substituteTokens(String pattern, Map tokens) {
      Map<String, Object> tokensCopy = new HashMap();
      tokensCopy.putAll(tokens);
      return substituteTokens(pattern, tokensCopy, true);
   }

   private static String substituteTokens(String pattern, Map tokens, boolean external) {
      Map<String, Object> tokensCopy = (Map<String, Object>)(external ? tokens : new HashMap(tokens));
      if (tokensCopy.containsKey("organisation") && !tokensCopy.containsKey("organization")) {
         tokensCopy.put("organization", tokensCopy.get("organisation"));
      }

      if (tokensCopy.containsKey("organisation") && !tokensCopy.containsKey("orgPath")) {
         String org = (String)tokensCopy.get("organisation");
         tokensCopy.put("orgPath", org == null ? "" : org.replace('.', '/'));
      }

      StringBuilder buffer = new StringBuilder();
      StringBuffer optionalPart = null;
      StringBuffer tokenBuffer = null;
      boolean insideOptionalPart = false;
      boolean insideToken = false;
      boolean tokenSeen = false;
      boolean tokenHadValue = false;

      for(char ch : pattern.toCharArray()) {
         int i = pattern.indexOf(ch);
         switch (ch) {
            case '(':
               if (insideOptionalPart) {
                  throw new IllegalArgumentException("invalid start of optional part at position " + i + " in pattern " + pattern);
               }

               optionalPart = new StringBuffer();
               insideOptionalPart = true;
               tokenSeen = false;
               tokenHadValue = false;
               break;
            case ')':
               if (!insideOptionalPart || insideToken) {
                  throw new IllegalArgumentException("invalid end of optional part at position " + i + " in pattern " + pattern);
               }

               if (tokenHadValue) {
                  buffer.append(optionalPart.toString());
               } else if (!tokenSeen) {
                  buffer.append('(').append(optionalPart.toString()).append(')');
               }

               insideOptionalPart = false;
               break;
            case '[':
               if (insideToken) {
                  throw new IllegalArgumentException("invalid start of token at position " + i + " in pattern " + pattern);
               }

               tokenBuffer = new StringBuffer();
               insideToken = true;
               break;
            case ']':
               if (!insideToken) {
                  throw new IllegalArgumentException("invalid end of token at position " + i + " in pattern " + pattern);
               }

               String token = tokenBuffer.toString();
               Object tokenValue = tokensCopy.get(token);
               String value = tokenValue == null ? null : tokenValue.toString();
               if (insideOptionalPart) {
                  tokenHadValue = !StringUtils.isNullOrEmpty(value);
                  optionalPart.append(value);
               } else {
                  if (value == null) {
                     value = "[" + token + "]";
                  }

                  buffer.append(value);
               }

               insideToken = false;
               tokenSeen = true;
               break;
            default:
               if (insideToken) {
                  tokenBuffer.append(ch);
               } else if (insideOptionalPart) {
                  optionalPart.append(ch);
               } else {
                  buffer.append(ch);
               }
         }
      }

      if (insideToken) {
         throw new IllegalArgumentException("last token hasn't been closed in pattern " + pattern);
      } else if (insideOptionalPart) {
         throw new IllegalArgumentException("optional part hasn't been closed in pattern " + pattern);
      } else {
         String afterTokenSubstitution = buffer.toString();
         checkAgainstPathTraversal(pattern, afterTokenSubstitution);
         return afterTokenSubstitution;
      }
   }

   public static String substituteVariable(String pattern, String variable, String value) {
      StringBuffer buf = new StringBuffer(pattern);
      substituteVariable(buf, variable, value);
      return buf.toString();
   }

   public static void substituteVariable(StringBuffer buf, String variable, String value) {
      String from = "${" + variable + "}";
      int fromLength = from.length();

      for(int index = buf.indexOf(from); index != -1; index = buf.indexOf(from, index)) {
         buf.replace(index, index + fromLength, value);
      }

   }

   public static String substituteToken(String pattern, String token, String value) {
      StringBuffer buf = new StringBuffer(pattern);
      substituteToken(buf, token, value);
      return buf.toString();
   }

   public static void substituteToken(StringBuffer buf, String token, String value) {
      String from = getTokenString(token);
      int fromLength = from.length();

      for(int index = buf.indexOf(from); index != -1; index = buf.indexOf(from, index)) {
         buf.replace(index, index + fromLength, value);
      }

   }

   public static String getTokenString(String token) {
      return "[" + token + "]";
   }

   public static String substituteParams(String pattern, Map params) {
      return substituteParams(pattern, new IvyVariableContainerImpl(params), new Stack());
   }

   private static String substituteParams(String pattern, IvyVariableContainer params, Stack substituting) {
      if (pattern == null) {
         return null;
      } else {
         Matcher m = PARAM_PATTERN.matcher(pattern);

         StringBuffer sb;
         String val;
         for(sb = new StringBuffer(); m.find(); m.appendReplacement(sb, val.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\@", "\\\\\\@"))) {
            String var = m.group(1);
            val = params.getVariable(var);
            if (val != null) {
               int index = substituting.indexOf(var);
               if (index != -1) {
                  List<String> cycle = new ArrayList(substituting.subList(index, substituting.size()));
                  cycle.add(var);
                  throw new IllegalArgumentException("cyclic param definition: cycle = " + cycle);
               }

               substituting.push(var);
               val = substituteVariables(val, params, substituting);
               substituting.pop();
            } else {
               val = m.group();
            }
         }

         m.appendTail(sb);
         return sb.toString();
      }
   }

   public static String getTokenRoot(String pattern) {
      int index = pattern.indexOf(91);
      if (index == -1) {
         return pattern;
      } else {
         int optionalIndex = pattern.indexOf(40);
         if (optionalIndex >= 0) {
            index = Math.min(index, optionalIndex);
         }

         return pattern.substring(0, index);
      }
   }

   public static String getFirstToken(String pattern) {
      if (pattern == null) {
         return null;
      } else {
         int startIndex = pattern.indexOf(91);
         if (startIndex == -1) {
            return null;
         } else {
            int endIndex = pattern.indexOf(93, startIndex);
            return endIndex == -1 ? null : pattern.substring(startIndex + 1, endIndex);
         }
      }
   }

   private static void checkAgainstPathTraversal(String pattern, String afterTokenSubstitution) {
      String root = getTokenRoot(pattern);
      int rootLen = root.length();
      if (root.endsWith("/") || root.endsWith("\\")) {
         --rootLen;
      }

      String patternedPartWithNormalizedSlashes = afterTokenSubstitution.substring(rootLen).replace("\\", "/");
      if (patternedPartWithNormalizedSlashes.endsWith("/..") || patternedPartWithNormalizedSlashes.indexOf("/../") >= 0) {
         throw new IllegalArgumentException("path after token expansion contains an illegal sequence");
      }
   }

   private static class OriginalArtifactNameValue {
      private String org;
      private String moduleName;
      private String branch;
      private String revision;
      private Map extraModuleAttributes;
      private String artifactName;
      private String artifactType;
      private String artifactExt;
      private Map extraArtifactAttributes;
      private ArtifactOrigin origin;

      public OriginalArtifactNameValue(String org, String moduleName, String branch, String revision, String artifactName, String artifactType, String artifactExt, Map extraModuleAttributes, Map extraArtifactAttributes) {
         this.org = org;
         this.moduleName = moduleName;
         this.branch = branch;
         this.revision = revision;
         this.artifactName = artifactName;
         this.artifactType = artifactType;
         this.artifactExt = artifactExt;
         this.extraModuleAttributes = extraModuleAttributes;
         this.extraArtifactAttributes = extraArtifactAttributes;
      }

      public OriginalArtifactNameValue(ArtifactOrigin origin) {
         this.origin = origin;
      }

      public String toString() {
         if (this.origin == null) {
            ModuleRevisionId revId = ModuleRevisionId.newInstance(this.org, this.moduleName, this.branch, this.revision, this.extraModuleAttributes);
            Artifact artifact = new DefaultArtifact(revId, (Date)null, this.artifactName, this.artifactType, this.artifactExt, this.extraArtifactAttributes);
            RepositoryCacheManager cacheManager = IvyContext.getContext().getSettings().getResolver(revId).getRepositoryCacheManager();
            this.origin = cacheManager.getSavedArtifactOrigin(artifact);
            if (ArtifactOrigin.isUnknown(this.origin)) {
               Message.debug("no artifact origin found for " + artifact + " in " + cacheManager);
               return null;
            }
         }

         if (ArtifactOrigin.isUnknown(this.origin)) {
            return null;
         } else {
            String location = this.origin.getLocation();
            int lastPathIndex = location.lastIndexOf(47);
            if (lastPathIndex == -1) {
               lastPathIndex = location.lastIndexOf(92);
            }

            int lastColonIndex = location.lastIndexOf(46);
            return location.substring(lastPathIndex + 1, lastColonIndex);
         }
      }
   }

   private static class Validated {
      private final String tokenName;
      private final String tokenValue;

      private Validated(String tokenName, String tokenValue) {
         this.tokenName = tokenName;
         this.tokenValue = tokenValue;
      }

      public String toString() {
         if (this.tokenValue != null && !this.tokenValue.isEmpty()) {
            StringTokenizer tok = new StringTokenizer(this.tokenValue.replace("\\", "/"), "/");

            while(tok.hasMoreTokens()) {
               if ("..".equals(tok.nextToken())) {
                  throw new IllegalArgumentException("'" + this.tokenName + "' value " + this.tokenValue + " contains an illegal path sequence");
               }
            }
         }

         return this.tokenValue;
      }
   }
}
