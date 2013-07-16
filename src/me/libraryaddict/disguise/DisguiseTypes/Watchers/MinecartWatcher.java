package me.libraryaddict.disguise.DisguiseTypes.Watchers;

import me.libraryaddict.disguise.DisguiseTypes.FlagWatcher;

public class MinecartWatcher extends FlagWatcher {

    public MinecartWatcher(int entityId) {
        super(entityId);
    }

    public float getDamage() {
        if (getValue(19, 0F) != null)
            return (Float) getValue(19, 0F);
        return 0F;
    }

    public void setDamage(float damage) {
        setValue(19, damage);
    }

}