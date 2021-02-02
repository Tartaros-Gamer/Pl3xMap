package net.pl3x.map.plugin.configuration;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Config extends AbstractConfig {
    private Config() {
        super("config.yml");
    }

    static Config config;
    static int version;

    public static void reload() {
        config = new Config();

        version = config.getInt("config-version", 1);
        config.set("config-version", 1);

        config.readConfig(Config.class, null);

        WorldConfig.reload();
    }

    public static String LANGUAGE_FILE = "lang-en.yml";
    public static boolean DEBUG_MODE = false;

    private static void baseSettings() {
        LANGUAGE_FILE = config.getString("settings.language-file", LANGUAGE_FILE);
        DEBUG_MODE = config.getBoolean("settings.debug-mode", DEBUG_MODE);
    }

    public static String WEB_DIR = "web";
    public static boolean UPDATE_WEB_DIR = true;

    private static void webDirSettings() {
        WEB_DIR = config.getString("settings.web-directory.path", WEB_DIR);
        UPDATE_WEB_DIR = config.getBoolean("settings.web-directory.auto-update", UPDATE_WEB_DIR);
    }

    public static boolean COMPRESS_IMAGES = false;
    public static float COMPRESSION_RATIO = 0.0F;

    private static void imageQualitySettings() {
        COMPRESS_IMAGES = config.getBoolean("settings.image-quality.compress-images", COMPRESS_IMAGES);
        COMPRESSION_RATIO = (float) config.getDouble("settings.image-quality.compress-images", COMPRESSION_RATIO);
    }

    public static boolean HTTPD_ENABLED = true;
    public static String HTTPD_BIND = "0.0.0.0";
    public static int HTTPD_PORT = 8080;

    private static void internalWebServerSettings() {
        HTTPD_ENABLED = config.getBoolean("settings.internal-webserver.enabled", HTTPD_ENABLED);
        HTTPD_BIND = config.getString("settings.internal-webserver.bind", HTTPD_BIND);
        HTTPD_PORT = config.getInt("settings.internal-webserver.port", HTTPD_PORT);
    }

    public static String UI_TITLE = "Pl3xMap - {world}";
    public static boolean UI_COORDINATES = true;
    public static boolean UI_LINK = true;
    public static String UI_SIDEBAR = "pinned";

    private static void uiSettings() {
        UI_TITLE = config.getString("settings.ui.title", UI_TITLE);
        UI_COORDINATES = config.getBoolean("settings.ui.coordinates", UI_COORDINATES);
        UI_LINK = config.getBoolean("settings.ui.link", UI_LINK);
        UI_SIDEBAR = config.getString("settings.ui.sidebar", UI_SIDEBAR);
    }

    public static String MAIN_COMMAND_LABEL = "pl3xmap";
    public static final List<String> MAIN_COMMAND_ALIASES = new ArrayList<>();

    private static void commandSettings() {
        MAIN_COMMAND_LABEL = config.getString("settings.commands.main-command-label", MAIN_COMMAND_LABEL);
        MAIN_COMMAND_ALIASES.clear();
        config.getList("settings.commands.main-command-aliases", List.of(
                "map"
        )).forEach(entry -> MAIN_COMMAND_ALIASES.add(entry.toString()));
    }

}
