package FileIO.Interfaces;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IConfigurationManager {

    public boolean loadConfigFiles();

    public boolean saveConfiguration();

    public boolean restoreConfigFiles();

}
