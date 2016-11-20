package softuni.parser;

public interface JSONParser {
    <T> void writeJSON(T object, String file);
    <T> T[] readFromJSON(Class<T[]> classes, String file);
}
