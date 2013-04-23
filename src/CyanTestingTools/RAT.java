/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CyanTestingTools;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Vitor Casadei
 * @Email  vitor.casadei@gmail.com
 */

public class RAT {

    /* String que conterá os agrupamentos de tipos de nomes de testes */
    private String filesName[];

    /* O Método selectDirectory faz a seleção do diretório que será utilizado */
    public static String selectDirectory() {
        String path = "";
        JFileChooser fc = new JFileChooser();
        //Exibe somente os Diretórios
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = fc.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File directory = fc.getSelectedFile();
            path = directory.getAbsolutePath();
        } else {
            return null;
        }
        return path;
    }

    /* O Método renSubFiles renomeia os arquivos xml que estão dentro das pastas de projeto */
    private static int renSubFiles(File newFile, String filePath, String newPath, int j) {
        //Lista todos os arquivos e diretórios do diretório corrente
        File parentPath = new File(newFile.getPath());
        File files[] = parentPath.listFiles();
        int k;
        //Percorre todos os arquivos
        for (k = 0; k < files.length; k++) {
            //Se for um arquivo e não um diretório
            if (files[k].isFile()) {
                //Seleciona a parte do nome de arquivo até a numeração
                String parentName[] = newFile.getName().split("-\\d+");
                String childrenName[] = files[k].getName().split("-\\d+");
                //Separa a extensão do arquivo do nome
                String extension[] = files[k].getName().split("\\.");
                //Se o nome do pai for o mesmo do nome do filho -- Acho que não precisa
                if (childrenName[0].equals(parentName[0])) {
                    //Se a extensão do arquivo dor xml
                    if (extension[1].equals("xml")) {
                        //Prepara o novo nome do arquivo
                        filePath = files[k].getPath();
                        newPath = filePath.replace(files[k].getName(), newFile.getName() + ".xml");
                        newFile = new File(newPath);
                        //Renomeia arquivo e testa
                        boolean suc = files[k].renameTo(newFile);
                        if (!suc) {
                            JOptionPane.showMessageDialog(null, "ERROR: " + (k + 1));
                        }
                    }
                }
            }
        }

        return j;
    }

    /* O Método renFiles renomeia as pastas do diretório corrente */
    private static int renFiles(File f, String fileName, int j) {
        //Recebe o nome do diretório, separando o nome da numeração
        String directoryName[] = f.getName().split("-\\d+");
        //Se o nome do arquivo xml for igual a pasta, sem a numeração
        if (directoryName[0].equals(fileName)) {
            //Incrementa o contador desta nomenclatura de arquivos
            j++;
            //Recebe o Caminho do arquivo e o modifica
            String filePath = f.getPath();
            String newPath = filePath.replace(f.getName(), directoryName[0]);
            File newFile = new File("");
            //Prepara a escrita do novo nome do arquivo de acordo com a numetação
            if (j < 10) {
                newFile = new File(newPath + "-000" + j);
            } else {
                if (j < 100) {
                    newFile = new File(newPath + "-00" + j);
                } else {
                    if (j < 1000) {
                        newFile = new File(newPath + "-0" + j);
                    } else {
                        newFile = new File(newPath + "-" + j);
                    }
                }
            }
            //Se ocorreu erro ao renomear
            boolean suc = f.renameTo(newFile);
            if (!suc) {
                JOptionPane.showMessageDialog(null, "ERROR: " + j);
            }

            //Renomeia os arquivos dentro de cada pasta
            j = renSubFiles(newFile, filePath, newPath, j);

        }
        return j;
    }

    /* Função Auxiliar que Verifica se existe um determinado indice em uma String [] */
    private static boolean findIndex(String[] names, String str) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                if (names[i].equals(str)) {
                    return true;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int i, j = 0;
        //Criação de novo objeto RAT
        RAT obj = new RAT();
        //Escolhe a Pasta que deseja procurar
        File mainDirectory;
        String firstArg = "";
        if (args.length > 0) {
            firstArg = args[0];
        }
        
        mainDirectory = new File(firstArg);
        
        //Lista todos os arquivos do diretório
        File fList[] = mainDirectory.listFiles();
        //Ordena arquivos do diretório
        Arrays.sort(fList);
        //Inicialização da Lista de Strings filesName com o número de arquivos no diretório
        obj.filesName = new String[fList.length];
        //Variáveis que guardam os  index de teste Erro ou Ok
        int indexEr = -1, indexOk = -1;

        //Inicialização do vetor de strings filesName com os agrupamentos de testes existentes
        for (int o = 0; o < fList.length; o++) {
            //Verifica se o teste é tipo erro
            indexEr = fList[o].getName().indexOf("er-");
            if (indexEr != -1) {
                //Split para verificar somente o nome do teste, sem numeração
                String name[] = fList[o].getName().split("-\\d+");
                //Se o tipo de teste ainda não foi catalogado
                if (!findIndex(obj.filesName, name[0])) {
                    //Adiciona-se na lista
                    obj.filesName[o] = name[0];
                }
            }
            //Verifica se o teste é tipo Ok
            indexOk = fList[o].getName().indexOf("ok-");
            if (indexOk != -1) {
                //Split para verificar somente o nome do teste, sem numeração
                String name[] = fList[o].getName().split("-\\d+");
                //Se o tipo de teste ainda não foi catalogado
                if (!findIndex(obj.filesName, name[0])) {
                    //Adiciona-se na lista
                    obj.filesName[o] = name[0];
                }
            }
        }

        //Vetor de elementos com os contadores de cada numeração
        int[] elemCont = new int[obj.filesName.length];

        //Inicialização do vetor de contagem de elementos
        for (i = 0; i < obj.filesName.length; i++) {
            elemCont[i] = 0;
        }

        //Percorre todos os arquivos do diretório
        for (i = 0; i < fList.length; i++) {
            //Utiliza o método renFiles para renomear os arquivos e pastas
            for (j = 0; j < obj.filesName.length; j++) {
                if (obj.filesName[j] != null) {
                    elemCont[j] = renFiles(fList[i], obj.filesName[j], elemCont[j]);
                }
            }
        }
    }
}
