package mod.acgaming.universaltweaks.tweaks.performance.autosave;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import net.minecraft.launchwrapper.Launch;

import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.config.UTConfig;
import mod.acgaming.universaltweaks.core.UTLoadingPlugin;

public class UTAutoSaveOFCompat
{
    public static File rootFolder = Launch.minecraftHome == null ? new File(".") : Launch.minecraftHome;

    public static void updateOFConfig()
    {
        if (!UTLoadingPlugin.isClient) return;
        try
        {
            Class.forName("optifine.OptiFineTweaker");
            UniversalTweaks.LOGGER.info("OptiFine detected, updating config file...");

            Path ofConfigPath = Paths.get(rootFolder + File.separator + "optionsof.txt");
            List<String> lines = Files.readAllLines(ofConfigPath, StandardCharsets.UTF_8);
            lines.set(31, "ofAutoSaveTicks:" + UTConfig.TWEAKS_PERFORMANCE.utAutoSaveInterval);
            Files.write(ofConfigPath, lines, StandardCharsets.UTF_8);

            UniversalTweaks.LOGGER.info("OptiFine auto save interval updated to " + UTConfig.TWEAKS_PERFORMANCE.utAutoSaveInterval);
        }
        catch (Exception ignored) {}
    }
}