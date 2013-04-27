/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyanTestingTools;

import java.io.*;


/**
 * creates an empty Cyan program in a given directory, including
 * subdirectories but not including the project file (use option -p to
 * create the projetct file) The arguments to this
 * program are Dir Proj [-t] P1 O1 P2 O2 ... Pn On
 * It is created a directory Proj in directory Dir with
 * subdirectories P1, P2, ... Pn (Pi may be equal to Pj for i != j) and empty
 * object declarations for O2 ... On. If P1 O1 ... are absent, P1 is
 * considered to be "main" and O1 to be "Program"
 * Object O1 is declared as
 *
 * package P1
 * object O1
 * public proc run :args Array<String> [ ] end
 *
 * if option -t is not present or
 *
 * package P1 / * this starts a comment #$description:
 *
 * #$end #$type: #$category: * / // end of the comment object O1 public proc run
 * :args Array<String> [ ] end
 *
 * if option -t was used
 *
 *  *
 * @author José
 *
 */
public class CPC {
    private static String error = "";
    /**
     * @param stringArray
     */
    public static String main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(args.length);
        for (String s: args) {
            System.out.println(s);
        }

        boolean isTest = false, createProject = false;
        int size = args.length;
        int start = 2;
        String stringArray[] = args;

        if (stringArray.length >= 3) {
            if (stringArray[2].compareTo("-t") == 0) {
                isTest = true;
                start = 3;
            }
            if (stringArray[2].compareTo("-p") == 0) {
                createProject = true;
                start = 3;
            }


        }
        if (size <= 3) {
            if (size == 2 || (size == 3 && isTest)) {
                stringArray = new String[4];
                stringArray[0] = args[0];
                stringArray[1] = args[1];
                stringArray[2] = "main";
                stringArray[3] = "Program";
                start = 2;
                size = 4;
            } else {
                error("The arguments to this program are\n"
                        + "    Dir Proj [-t] P1 O1 P2 O2 ... Pn On\n"
                        + "It is created in directory Dir a directory Proj with subdirectories P1, P2, ... Pn \n"
                        + "(Pi may be equal to Pj for i != j) and \n"
                        + "empty object declarations for O2 ... On.\n"
                        + "if -t is present, a header of a test file is created.");
            }
        }
        String curDir = stringArray[0]; // System.getProperty("user.dir") + "\\";
        String projectDir = curDir + File.separator + stringArray[1];
        createDir(projectDir);

        /*if (size%2 != 1)
         error("The number of arguments to this program should be odd"); */

        int i = start;
        while (i < size) {
            String newDirPath = projectDir + File.separator + stringArray[i];

            createDir(newDirPath);
            String objectName = stringArray[i + 1];
            int index = objectName.lastIndexOf(".cyan");
            if (index >= 0) {
                objectName = objectName.substring(0, index);
            }
            if (objectName.indexOf('.') >= 0) {
                error("object name '" + objectName + "' should not have a .");
            }
            String newObjectFileName = newDirPath + File.separator + objectName + ".cyan";
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(
                        newObjectFileName));
                out.write("package " + stringArray[i] + "\n\n"
                        + (isTest
                        ? "/*\n"
                        + "    #$description:\n"
                        + "      \n"
                        + "    #$end\n"
                        + "    #$type: \n"
                        + "    #$category: \n"
                        + "*/\n\n"
                        : "")
                        + "object "
                        + objectName + "\n"
                        + ((i == start) ? "    public fun run: (:args Array<String>) [\n    ]\n" : "\n")
                        + "end\n");
                out.close();
            } catch (IOException e) {
                error("CanŽt write to file " + newObjectFileName);
            }
            i = i + 2;
        }
        if (createProject) {
            CP.main(new String[]{projectDir, "", "", "", ""});
        }
        return error;
    }

    static private boolean createDir(String path) {
        boolean success = false;
        File f = new File(path);
        if (!f.exists()) {
            success = f.mkdirs();
            if (!success) {
                error("CanŽt create directory " + path);
            }
        }
        return success;
    }

    static private void error(String errorMessage) {
        System.out.println(errorMessage);
        error = errorMessage;
    }
}
