/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyanTestingTools;

import java.io.File;
import java.io.IOException;


/** calls cp using all the directories of a given directory. Calling
 *        java CPA dir author options mainPackage mainObject
 *  this program calls
 *        java cp projDir author options mainPackage mainObject
 *  for each directory "projDir" of the directory "dir".
 *
 *  That is, all xml project files of all Cyan programs that are in subdirectories of
 *  the directory "dir" are created.
 *
 * @author José
 *
 */
public class CPA {
    private static String error = "";
    /**
     * @param args
     */
    public static String main(String[] args) {

        int size = args.length;
        String parentDirName = "",
                author = "",
                options = "",
                mainPackage = "main",
                mainObject = "Program";


        if (size == 1) {
            parentDirName = args[0];
        } else if (size == 5) {
            parentDirName = args[0];
        } else {
            error("Usage: \n"
                    + "cpa parentDir author options mainPackage mainObject\n"
                    + "Use \"\" for empty parameters\n"
                    + "This program creates a xml file describing a Cyan project for each"
                    + "directory of 'parentDir'");
        }

        String fileSeparator = System.getProperty("file.separator");
        File parentDir = new File(parentDirName);
        if (!parentDir.exists() || !parentDir.isDirectory()) {
            error("Directory " + parentDirName + " does not exist or it is not a directory");
        }
        String canParentDirName = "";
        try {
            canParentDirName = parentDir.getCanonicalPath();
        } catch (IOException e) {
            error("error handling file " + parentDirName);
        }
        String[] subDirList = parentDir.list();
        for (String subDir : subDirList) {
            String canSubDirName = null;
            File f = new File(canParentDirName + fileSeparator + subDir);
            try {
                canSubDirName = f.getCanonicalPath();
            } catch (IOException e) {
                error("error handling file " + subDir);
            }
            if (f.exists() && f.isDirectory()) {
                CP.main(new String[]{canSubDirName, author, options, mainPackage,
                    mainObject});
            }
        }
        return error;

    }

    static private void error(String errorMessage) {
        System.out.println(errorMessage);
        error = errorMessage;
    }
}
