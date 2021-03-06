package ru.epatko.clientSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         02.01.17.
 */
public class ClientSettings {

    /**
     * Connection address.
     */
    private String address;
    /**
     * Connection port number.
     */
    private int port;
    /**
     * Properties.
     */
    private final Properties properties = new Properties();

    /**
     * Load settings from app.properties file.
     */
    public ClientSettings() {
        File file = new File("app.properties");

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            this.properties.load(fileInputStream);
            this.address = this.properties.getProperty("address");
            this.port = Integer.parseInt(this.properties.getProperty("port"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Getter.
     * @return - connection address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter.
     * @return - connection port number.
     */
    public int getPort() {
        return this.port;
    }
}
