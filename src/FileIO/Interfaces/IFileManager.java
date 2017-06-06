package FileIO.Interfaces;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IFileManager {

    public boolean writeStateToFile();

    public boolean restoreStateFromFile();

    public boolean refreshFile();

    public boolean writeStringToFile(String output, String filename);

    public boolean deleteFile(String filename);
}
