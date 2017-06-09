package FileIO.Impl;

import FileIO.Interfaces.IFileManager;

import java.io.*;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class FileManager implements IFileManager {


    @Override
    public boolean writeStateToFile() {
        return false;
    }

    @Override
    public boolean restoreStateFromFile() {
        return false;
    }

    @Override
    public boolean refreshFile() {
        return false;
    }

    @Override
    public boolean writeStringToFile(String output, String filename) {
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
           out.println(output);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(String filename) {
        File f = new File(filename);
        return f.delete();
    }
}
