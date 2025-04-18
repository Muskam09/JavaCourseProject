Writers File Processor

A Java-based application for processing text files containing writer data in the format N Author Birth Country. The program reads input files, filters writers by birth year (before 1850, 1851–1950, after 1950), sorts them by user-selected criteria (author, birth year, or birth year and country), and writes results to output files.

Table of Contents





Features



Installation



Usage



Project Structure



License

Features





File Reading: Processes text files with writer data, ignoring invalid lines.



Filtering: Categorizes writers into three groups based on birth year.



Sorting: Supports sorting by author, birth year, or birth year and country.



File Writing: Outputs sorted data to separate files (oldWriters.txt, midWriters.txt, newWriters.txt).



Error Handling: Robustly handles file I/O errors and invalid data.



Performance: Processes up to 1000 records in ~0.1–0.2 seconds.