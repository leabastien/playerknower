package lea.playerknower.util;

import net.minecraft.nbt.NbtCompound;

public class DescData {
    public static String readDesc(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getString("desc");
    }

    public static void setDesc(IEntityDataSaver player, String NewDesc) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("desc", NewDesc);
    }

    public static String readShortDesc(IEntityDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getString("short_desc");
    }

    public static void setShortDesc(IEntityDataSaver player, String NewDesc) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putString("short_desc", NewDesc);
    }
}
