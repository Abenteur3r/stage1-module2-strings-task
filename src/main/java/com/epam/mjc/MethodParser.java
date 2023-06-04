package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String modifier = null;
        String returnType;
        String methodName;

        if(signatureString.contains("private")){
            modifier = "private";
        } else if (signatureString.contains("protected")) {
            modifier = "protected";
        } else if (signatureString.contains("public")) {
            modifier = "public";
        }

        if (signatureString.contains("private") || signatureString.contains("public") || signatureString.contains("protected")){
            int index;
            index = (signatureString.substring(0, signatureString.indexOf(" "))).length();
            String string = signatureString.substring(index+1);
            returnType = string.substring(0,string.indexOf(" "));
            string = string.substring(returnType.length()+1);
            methodName = string.substring(0,string.indexOf("("));
        }
        else {
            returnType = signatureString.substring(0,signatureString.indexOf(" "));
            String string = signatureString.substring(returnType.length()+1);
            methodName = string.substring(0,string.indexOf("("));
        }

        List<MethodSignature.Argument> allArgs = new ArrayList<>();
        String inBrackets = signatureString.substring(signatureString.indexOf("(")+1,signatureString.lastIndexOf(")"));
        if(inBrackets.length() > 0){
        String[] arguments = inBrackets.split(", ");
        for (String s : arguments){
            MethodSignature.Argument foo = new MethodSignature.Argument(null,null);
            String type,name;
            if (s.contains(" ")){
                String[] typeNameData = s.split(" ");
                type = typeNameData[0];
                name = typeNameData[1];
                foo.setType(type);
                foo.setName(name);
            }

            allArgs.add(foo);
        }
        }
        MethodSignature obj = new MethodSignature(methodName,allArgs);
        obj.setMethodName(methodName);
        obj.setReturnType(returnType);
        obj.setAccessModifier(modifier);

        return obj;
    }
}

