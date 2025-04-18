import java.io.*;
import java.util.*;

class Controller {
    public static final ArrayList<Writer> OLD_WRITERS = new ArrayList<>();
    public static final ArrayList<Writer> MID_WRITERS = new ArrayList<>();
    public static final ArrayList<Writer> NEW_WRITERS = new ArrayList<>();

    public static void handleFiles(File outputFilesDir, File... files) {
        String sortType = inputSortType();
        for (File file : files) {
            try {
                readFile(file);
            } catch (FileNotFoundException fnfe) {
                System.out.println("File not found: " + file.getPath());
            } catch (IOException ioe) {
                System.out.println("Exception: " + ioe.getMessage());
            }
        }
        sortWriters(sortType);
        writeFiles(outputFilesDir);
    }

    private static String inputSortType() {
        System.out.println("Select sort type:\n1 - by Author, Birth, Country " +
                "\n2 - by Birth, Author, Country \n3 - by Country, Birth, Author  \nEnter: ");
        Scanner input = new Scanner(System.in);
        String str = "";
        try {
            int type = input.nextInt();
            switch (type) {
                case 1 -> str = "author";
                case 2 -> str = "birth";
                case 3 -> str = "birth_country";
                default -> str = "default";
            }
        } catch (Exception e) {
            str = "default";
        }
        System.out.println("Sorting by: " + str);
        return str;
    }

    private static void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\s+");
            if (data.length < 3) continue;
            Writer writer = new Writer(data[1], Integer.parseInt(data[2]), data[3]);
            separateWriter(writer);
        }
        br.close();
    }

    private static void separateWriter(Writer writer) {
        int birthYear = writer.getBirthYear();
        if (birthYear <= 1850) {
            OLD_WRITERS.add(writer);
        } else if (birthYear <= 1950) {
            MID_WRITERS.add(writer);
        } else {
            NEW_WRITERS.add(writer);
        }
    }

    private static void writeFiles(File outputFilesDir) {
        try {
            writeFile(new File(outputFilesDir, "oldWriters.txt"), OLD_WRITERS);
            writeFile(new File(outputFilesDir, "midWriters.txt"), MID_WRITERS);
            writeFile(new File(outputFilesDir, "newWriters.txt"), NEW_WRITERS);
        } catch (FileNotFoundException fnfe) {
            System.out.println("File error: " + fnfe.getMessage());
        }
    }

    private static void writeFile(File file, ArrayList<Writer> writers) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < writers.size(); i++) {
            pw.printf("%-3d %s%n", (i + 1), writers.get(i));

        }
        pw.flush();
        pw.close();
    }

    private static void sortWriters(String key) {
        Comparator<Writer> comparator;
        switch (key) {
            case "author" -> {
                comparator = Comparator.comparing(Writer::getAuthor)
                        .thenComparing(Writer::getBirthYear)
                        .thenComparing(Writer::getCountry);
            }
            case "birth" -> {
                comparator = Comparator.comparingInt(Writer::getBirthYear)
                        .thenComparing(Writer::getAuthor)
                        .thenComparing(Writer::getCountry);
            }
            case "birth_country" -> {
                comparator = Comparator.comparingInt(Writer::getBirthYear)
                        .thenComparing(Writer::getCountry)
                        .thenComparing(Writer::getAuthor);
            }
            default -> {
                comparator = Comparator.naturalOrder();
            }
        }
        OLD_WRITERS.sort(comparator);
        MID_WRITERS.sort(comparator);
        NEW_WRITERS.sort(comparator);
    }
}
