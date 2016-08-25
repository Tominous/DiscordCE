package com.github.duke605.discordce.gui;

import com.github.duke605.discordce.lib.Config;
import com.github.duke605.discordce.lib.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Arrays;

public class GuiDiscordConfig extends GuiConfig
{
    public GuiDiscordConfig(GuiScreen parentScreen)
    {
        super(parentScreen
                , Arrays.asList(
                        new ConfigElement(Config.instance.getCategory(Config.CATEGORY_COLOUR)),
                        new ConfigElement(Config.instance.getCategory(Config.CATEGORY_DISPLAY)),
                        new ConfigElement(Config.instance.getCategory(Configuration.CATEGORY_GENERAL))
                )
                , Reference.MODID
                , false
                , false
                , "DiscordCE Configuration");
    }
}
