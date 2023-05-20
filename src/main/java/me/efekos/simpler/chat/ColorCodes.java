package me.efekos.simpler.chat;

public class ColorCodes {
    public static String translate(String message){
        String[] messageSplit = message.split(String.format("((?<=%1$s)|(?=%1$s))",'&'));

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < messageSplit.length; i++) {
            if (messageSplit[i].equalsIgnoreCase("&")){
                //get the next string
                i++;
                if (messageSplit[i].charAt(0) == '#'){
                    builder.append(net.md_5.bungee.api.ChatColor.of(messageSplit[i].substring(0, 7))).append(messageSplit[i].substring(7));
                }else{
                    builder.append(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', "&" + messageSplit[i]));
                }
            }else{
                builder.append(messageSplit[i]);
            }
        }

        return builder.toString();
    }
}
