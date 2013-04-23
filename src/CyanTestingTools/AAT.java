package CyanTestingTools;

import java.io.*;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * @author Vitor Casadei
 * @Email  vitor.casadei@gmail.com
 */

public class AAT {
    //Atributo que deve conter o caminho para o diretório que conterá testdata

    private String testDataPath;
    //Atributo da Classe que guarda o nome do projeto
    private String projectName;
    //Atributo da Classe que guarda a descrição do projeto
    private String description;
    //Atributo da Classe que guarda o tipo do projeto
    private String type;
    //Atributo da Classe que guarda a categoria do projeto
    private String category;
    //Atributo da Classe que guarda a linha de erro do projeto
    private String errorLine;
    //Atributo booleano da Classe que marca a existência de erro
    private boolean error;

    /* O Método selectDirectory faz a seleção do diretório que será utilizado */
    /**
     *
     * @return
     */
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

    /* Método da Classe que realiza criação dos arquivos testdata.xml de cada Teste, utilizando DOM Parser */
    private static void writeXML(File path, String strProjectName, String strDescription, String strType, String strCategory, String strErrorLine, boolean error) {
        //Bloco Try/Catch
        try {
            //Criação de uma nova instância de documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Elemento Root do XML
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("CyanTest");
            doc.appendChild(rootElement);

            //Elemento do Projeto
            Element project = doc.createElement("Project");
            rootElement.appendChild(project);

            //Atributo Path do Projeto
            Attr attr = doc.createAttribute("Path");
            attr.setValue(path.getPath());
            project.setAttributeNode(attr);

            //Elemento ProjectName
            if (strProjectName.isEmpty()) {
                strProjectName = " ";
            }
            Element projectName = doc.createElement("ProjectName");
            projectName.appendChild(doc.createTextNode(strProjectName));
            project.appendChild(projectName);

            //Elemento Description
            if (strDescription.isEmpty()) {
                strDescription = " ";
            }
            Element description = doc.createElement("Description");
            description.appendChild(doc.createTextNode(strDescription));
            project.appendChild(description);

            //Elemento Type
            if (strType.isEmpty()) {
                strType = " ";
            }
            Element type = doc.createElement("Type");
            type.appendChild(doc.createTextNode(strType));
            project.appendChild(type);

            //Elemento Category
            if (strCategory.isEmpty()) {
                strCategory = " ";
            }
            Element category = doc.createElement("Category");
            category.appendChild(doc.createTextNode(strCategory));
            project.appendChild(category);

            //Se for teste de erro
            if (error) {
                //Elemento ErrorLine
                Element errorLine = doc.createElement("ErrorLine");
                errorLine.appendChild(doc.createTextNode(strErrorLine));
                project.appendChild(errorLine);
            }


            //Escrita no arquivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path.getParent() + "/testdata.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    /* Este método, que recebe duas strings, faz o processamento de uma linha de um arquivo */
    private static int processLine(String str, String directoryPath) {
        int category, type, description, select, error, projectName;
        category = type = description = select = error = projectName = -1;
        //Verificação de qual marcador existe na linha (se existir)
        description = str.indexOf("#$description:");
        type = str.indexOf("#$type:");
        category = str.indexOf("#$category:");
        error = str.indexOf("//#");
        projectName = str.indexOf("object ");

        //Seleção do marcador existente
        if (projectName != -1) {
            select = 0;
        } else {
            if (description != -1) {
                select = 1;
            } else {
                if (type != -1) {
                    select = 2;
                } else {
                    if (category != -1) {
                        select = 3;
                    } else {
                        if (error != -1) {
                            select = 4;
                        }
                    }
                }
            }
        }
        return select;
    }

    /* Este método faz o processamento de um arquivo .cyan específico, procurando pelos marcadores. Recebe um File, um inteiro marcador e o caminho da pasta  */
    private static boolean processFile(File newFile, String directoryPath) {
        int select = 0, i = 0, j = 1, error = 0, lineError = 0, projectNameIndex;
        String str, strDescription, split[], stringSave;
        AAT obj = new AAT();
        //Bloco Try/Catch para leitura do arquivo
        try {
            BufferedReader in = new BufferedReader(new FileReader(newFile));
            while (in.ready()) {
                //Leitura de uma linha do arquivo
                str = in.readLine();
                error = newFile.getPath().indexOf("er-");
                //Processamento desta linha
                select = processLine(str, directoryPath);


                if (select == 0) {
                    //Caso seja o nome do Projeto
                    projectNameIndex = str.indexOf("object ");
                    stringSave = str.substring(projectNameIndex + 7);
                    obj.projectName = stringSave;
                } else {
                    if (select == 1) {
                        //Caso o marcador seja um Description
                        strDescription = in.readLine();
                        split = strDescription.split(" +");
                        stringSave = "Description: ";
                        //São salvas as informações no arquivo testdata.xml
                        String mont = "";
                        if (split.length > 1) {
                            for (i = 1; i < split.length; i++) {
                                if (i + 1 == split.length) {
                                    mont = mont + split[i];
                                } else {
                                    mont = mont + split[i] + " ";
                                }
                            }
                        }
                        obj.description = mont;
                    } else {
                        //Caso a linha contenha um marcador Type
                        if (select == 2) {
                            //Type
                            split = str.split(": ");
                            if (split.length == 1) {
                                split = str.split(":");
                            }
                            if (split.length > 1) {
                                obj.type = split[1];
                            }
                        } else {
                            //Caso a linha contenha um marcador Type
                            if (select == 3) {
                                //Category
                                split = str.split(": ");
                                if (split.length == 1) {
                                    split = str.split(":");
                                }
                                if (split.length > 1) {
                                    obj.category = split[1];
                                }
                            } else {
                                //Caso a linha contenha um marcador de Erro
                                if (select == 4) {
                                    //Achou erro na linha j
                                    lineError = 1;
                                    obj.error = true;
                                    obj.errorLine = "" + (j + 1) + "";
                                }
                            }
                        }
                    }
                }
                j++;

            }
            in.close();
            if (obj.description != null && obj.type != null && obj.category != null) {
                writeXML(newFile, obj.projectName, obj.description, obj.type, obj.category, obj.errorLine, obj.error);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: No Marker Found --> " + newFile.getPath());
            }



            //Case encontre linha com erro, mas o arquivo não é teste de erro
            if (lineError == 1 && error == -1) {
                JOptionPane.showMessageDialog(null, "ERROR: Error Line found in an non-Error test --> " + newFile.getPath());
            } else {
                //Caso não encontre linha com erro, mas o arquivo não é teste de erro
                if (lineError == 0 && error != -1) {
                    JOptionPane.showMessageDialog(null, "ERROR: Error Line not found in an Error test --> " + newFile.getPath());
                }
            }

            if (obj.description != null || obj.type != null || obj.category != null) {
                return true;
            }
            return false;

        } catch (IOException e) {
            return false;
        }
    }

    /* Este método recebe um arquivo, um inteiro que representa os marcadores e a String contendo o caminho da pasta */
    private static void readSubFolder(File newFile, String directoryPath) {
        File parentFile = new File(newFile.getPath());
        File files[] = parentFile.listFiles();
        //A lista de arquivos é ordenada
        Arrays.sort(files);
        boolean hasMarker = false;
        boolean getReturn;
        for (int i = 0; i < (files.length); i++) {
            if (files[i].isFile()) {
                int cyanIndex = -1;
                int cyanOpenIndex = -1;
                cyanIndex = files[i].getName().indexOf(".cyan");
                cyanOpenIndex = files[i].getName().indexOf(".cyan~");
                if (cyanIndex != -1 && cyanOpenIndex == -1) {
                    //Processamento de um arquivo
                    getReturn = processFile(files[i], directoryPath);
                    //Verifica se existem mais de um arquivo .cyan em um projeto com marcadores
                    if (hasMarker) {
                        if (getReturn) {
                            JOptionPane.showMessageDialog(null, "ERROR: More than one file with Markers in --> " + newFile.getPath());
                        }
                    }

                    if (getReturn) {
                        hasMarker = true;
                    }
                }
            } else {
                //Processamento recursivo de subpastas
                readSubFolder(files[i], directoryPath);
            }


        }
    }

    public static void main(String[] args) {
        //Escolhe a Pasta que deseja procurar
        File mainDirectory;
        String firstArg = "";
        if (args.length > 0) {
            firstArg = args[0];
        }
        
        mainDirectory = new File(firstArg);
        //Lista todos os arquivos d	o diretório
        File fList[] = mainDirectory.listFiles();
        //Ordena arquivos do diretório
        Arrays.sort(fList);
        //Cria Objeto AAT
        AAT objAAT = new AAT();
        //Obtém a pasta raiz que sejá analisada
        objAAT.testDataPath = mainDirectory.getPath();

        //O atributo markerFound guarda a verificação se existe marcadores em algum arquivo Program.cyan
        if (mainDirectory.isDirectory()) {
            //Se estivermos em um diretório, lemos seus subdiretórios
            readSubFolder(mainDirectory, objAAT.testDataPath);
        }
    }
}
