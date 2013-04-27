/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyanTestingTools;

import java.io.*;
import java.util.*;

/** See the pdf  "Ferramentas e Programas de Teste para a Linguagem Cyan".
 *
 * creates a xml file of a Cyan program. It should be called as
 *     java CP projDir author options mainPackage mainObject
 *
 *
 * @author José
 *
 */
public class CP {
    private static String error;
    /**
     * @param args
     */
    public static String main(String[] args) {

        int size = args.length;
        String projDirName = "",
                author = "",
                options = "",
                mainPackage = "main",
                mainObject = "Program";

        if (size == 1) {
            projDirName = args[0];
        } else if (size == 5) {
            projDirName = args[0];
            author = args[1];
            options = args[2];
            mainPackage = args[3];
            mainObject = args[4];
        } else {
            error("Usage: \n"
                    + "cp projDir author options mainPackage mainObject\n"
                    + "Use \"\" for empty parameters\n"
                    + "This program creates a xml file describing a Cyan project");
        }

        fileSeparator = System.getProperty("file.separator");
        File projDir = new File(projDirName);

        String canPathOfTheProject = null;
        try {
            canPathOfTheProject = projDir.getCanonicalPath();
            String projFilePath = canPathOfTheProject + fileSeparator + projDir.getName() + ".xml";
            PrintWriter out = new PrintWriter(projFilePath);
            out.println("<?xml version=\"1.0\"?>\n"
                    + "<project"
                    + (options.length() == 0 ? "" : " options = \"" + options + "\"") + ">\n"
                    + (author.length() == 0 ? "" : "<author> " + author + " </author>\n")
                    + "<mainPackage> " + mainPackage + " </mainPackage>\n"
                    + "<mainObject> " + mainObject + " </mainObject>\n");

            ArrayList<String> projPath = new ArrayList<String>();
            projPath.add(canPathOfTheProject);
            ArrayList<String> projCyanName = new ArrayList<String>();
            projCyanName.add("");
            getAllProjects(projPath, projCyanName, 0);
            generatePackageSourceList(projPath, projCyanName, out);
            out.println("</project>");
            /*for(int i = 0; i < projPath.size(); i++) {
             System.out.println("project: " + projPath.get(i));
             System.out.println("name: " + projCyanName.get(i));
             }
             System.exit(1);
             */

            out.close();
        } catch (IOException e) {
            error("CanŽt write to file " + canPathOfTheProject);
        }
        return error;
    }
    private static String fileSeparator;

    /**
     * projPath contains all projects of
     *
     * @param projPath
     * @param projCyanName
     */
    private static void getAllProjects(ArrayList<String> projPath,
            ArrayList<String> projCyanName, int start) {

        int size = projPath.size();
        int i;
        for (i = start; i < size; i++) {
            String s = projPath.get(i);
            String projectName = projCyanName.get(i);
            File f = new File(s);
            if (f.isDirectory()) {
                String[] subDirList = f.list();
                for (String p : subDirList) {
                    File g = new File(s + fileSeparator + p);
                    if (g.isDirectory()) {
                        try {
                            projPath.add(g.getCanonicalPath());
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            error("error in handling file " + p);
                        }
                        if (projectName.length() == 0) {
                            projCyanName.add(p);
                        } else {
                            projCyanName.add(projectName + "." + p);
                        }
                    }
                }
            }
        }
        if (projPath.size() > size) {
            getAllProjects(projPath, projCyanName, size);
        }
    }

    /**
     * prints in the xml file referenced by out the data of each project such as
     * <project>
     * <name> projectName </name>
     * <sourcefile> A.cyan </sourcefile>
     * <sourcefile> B.cyan </sourcefile>
     *
     * @param projPath, a list of packages
     * @param projCyanName, a list of names of the packages of projPath
     * @param out, the xml file of the project
     */
    private static void generatePackageSourceList(
            ArrayList<String> projPath,
            ArrayList<String> projCyanName, PrintWriter out) {

        out.println("<packageList>");

        for (int i = 1; i < projPath.size(); i++) {
            File projFile = new File(projPath.get(i));
            String canProjFileName = null;
            try {
                canProjFileName = projFile.getCanonicalPath();
            } catch (IOException e) {
                error("error handling file " + projFile.getName());
            }
            int numCyanSourceFilesFoundInPackage = 0;
            if (!projFile.exists()) {
                error("File " + projPath.get(i) + " does not exist");
            }
            String cyanSource[] = projFile.list();
            for (String source : cyanSource) {
                String canCyanSource = canProjFileName + fileSeparator
                        + source;
                File f = new File(canCyanSource);
                if (source.endsWith(".cyan") && !f.isDirectory()) {
                    ++numCyanSourceFilesFoundInPackage;
                    if (numCyanSourceFilesFoundInPackage == 1) {
                        out.println("<package dir = \"" + canProjFileName + "\">");
                        out.println("<name> " + projCyanName.get(i) + " </name>");
                    }
                    out.println("<sourcefile> " + source + " </sourcefile>");
                }
            }
            if (numCyanSourceFilesFoundInPackage > 0) {
                out.println("</package>");
            }

        }
        out.println("</packageList>");
    }

    static private void error(String errorMessage) {
        System.out.println(errorMessage);
        error += errorMessage;
    }
}
