package FileIO.Interfaces;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IFileManager {

    public boolean writeStateToFile();

    public boolean restoreStateFromFile();

    public boolean refreshFile();

    /**
     * Method to output a string to a file
     * @param output the output string
     * @param filename the file to be written to/created
     * @return
     */
    public boolean writeStringToFile(String output, String filename);

    /**
     * Method to delete a file from the file system
     * @param filename the path of the file to be deleted
     * @return a boolean indicating success or failure
     */
    public boolean deleteFile(String filename);
}
