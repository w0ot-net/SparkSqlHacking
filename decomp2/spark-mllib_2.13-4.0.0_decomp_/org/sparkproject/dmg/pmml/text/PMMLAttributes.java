package org.sparkproject.dmg.pmml.text;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field TEXTDOCUMENT_ID = ReflectionUtil.getField(TextDocument.class, "id");
   Field TEXTDOCUMENT_NAME = ReflectionUtil.getField(TextDocument.class, "name");
   Field TEXTDOCUMENT_LENGTH = ReflectionUtil.getField(TextDocument.class, "length");
   Field TEXTDOCUMENT_FILE = ReflectionUtil.getField(TextDocument.class, "file");
   Field TEXTMODEL_MODELNAME = ReflectionUtil.getField(TextModel.class, "modelName");
   Field TEXTMODEL_MININGFUNCTION = ReflectionUtil.getField(TextModel.class, "miningFunction");
   Field TEXTMODEL_ALGORITHMNAME = ReflectionUtil.getField(TextModel.class, "algorithmName");
   Field TEXTMODEL_NUMBEROFTERMS = ReflectionUtil.getField(TextModel.class, "numberOfTerms");
   Field TEXTMODEL_NUMBEROFDOCUMENTS = ReflectionUtil.getField(TextModel.class, "numberOfDocuments");
   Field TEXTMODEL_SCORABLE = ReflectionUtil.getField(TextModel.class, "scorable");
   Field TEXTMODEL_MATHCONTEXT = ReflectionUtil.getField(TextModel.class, "mathContext");
   Field TEXTMODELNORMALIZATION_LOCALTERMWEIGHTS = ReflectionUtil.getField(TextModelNormalization.class, "localTermWeights");
   Field TEXTMODELNORMALIZATION_GLOBALTERMWEIGHTS = ReflectionUtil.getField(TextModelNormalization.class, "globalTermWeights");
   Field TEXTMODELNORMALIZATION_DOCUMENTNORMALIZATION = ReflectionUtil.getField(TextModelNormalization.class, "documentNormalization");
   Field TEXTMODELSIMILIARITY_SIMILARITYTYPE = ReflectionUtil.getField(TextModelSimiliarity.class, "similarityType");
}
