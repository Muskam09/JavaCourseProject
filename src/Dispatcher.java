import java.io.File;

public class Dispatcher {
    public static void main(String[] args) {
        File inputFiles = new File("D:\\university\\java\\course_work\\src\\inputFiles");
        File outputFiles = new File("D:\\university\\java\\course_work\\src\\outputFiles");
        inputFiles.mkdir();
        outputFiles.mkdir();

        File f1 = new File(inputFiles, "oldWriters.txt");
        File f2 = new File(inputFiles, "midWriters.txt");
        File f3 = new File(inputFiles, "newWriters.txt");

        Controller.handleFiles(outputFiles, f1, f2, f3);

        System.out.println("Processing completed.");
        System.out.println("Old Writers: " + Controller.OLD_WRITERS);
        System.out.println("Mid Writers: " + Controller.MID_WRITERS);
        System.out.println("New Writers: " + Controller.NEW_WRITERS);
    }
}
