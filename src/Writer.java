import java.util.Objects;

class Writer implements Comparable<Writer> {
    private String author;
    private int birthYear;
    private String country;

    Writer(String author, int birthYear, String country) {
        this.author = author;
        this.birthYear = birthYear;
        this.country = country;
    }

    public String getAuthor() {
        return author;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.format("%-15s %4d    %-10s", author, birthYear, country);
    }


    @Override
    public int compareTo(Writer writer) {
        int result = Integer.compare(this.birthYear, writer.birthYear);
        if (result != 0) return result;
        result = this.author.compareTo(writer.author);
        if (result != 0) return result;
        return this.country.compareTo(writer.country);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Writer)) return false;
        Writer writer = (Writer) obj;
        return birthYear == writer.birthYear &&
                Objects.equals(author, writer.author) &&
                Objects.equals(country, writer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, birthYear, country);
    }
}
