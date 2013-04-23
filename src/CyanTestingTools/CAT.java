/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyanTestingTools;


/**
 * @author José
 *
 */
public class CAT {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String stringArray[] = args;
        if (stringArray.length < 4) {
            error("The arguments to this program are\n"
                    + "     Dir s1 s2 ... sn start end\n"
                    + "\n"
                    + "This program creates, in directory Dir, projects s1-s2-...sn-start to s1-s2-...sn-end\n"
                    + "in which start and end are numbers.");
        }
        int size = stringArray.length;
        int startNum = 0;
        int endNum = 0;
        try {
            startNum = Integer.parseInt(args[size - 2]);
            endNum = Integer.parseInt(args[size - 1]);
        } catch (NumberFormatException e) {
            error("The last two parameters should be numbers");
        }
        if (startNum > endNum) {
            error("the end number (" + endNum
                    + ") should be greater than the start number ("
                    + startNum + ")");
        }
        if (endNum > 9999 || startNum > 9999) {
            error("Use numbers <= 9999");
        }
        String dir = args[0];
        String partialProjName = args[1];
        for (int i = 2; i < size - 2; i++) {
            partialProjName = partialProjName + "-" + args[i];
        }
        for (int i = startNum; i <= endNum; i++) {
            String num;
            String projName;
            if (i < 10) {
                num = "000" + i;
            } else if (i < 100) {
                num = "00" + i;
            } else if (i < 1000) {
                num = "0" + i;
            } else {
                num = "" + i;
            }
            projName = partialProjName + "-" + num;
            CPC.main(new String[]{dir, projName, "-t", "main", "Program"});
// 			System.out.println(projName);
        }
    }

    static private void error(String errorMessage) {
        System.out.println(errorMessage);
        System.exit(1);
    }
}
