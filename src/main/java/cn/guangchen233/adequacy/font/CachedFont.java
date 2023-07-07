package cn.guangchen233.adequacy.font;

import org.lwjgl.opengl.GL11;

public class CachedFont {
    private final int displayList;
    private long lastUsage;
    private boolean deleted;

    public CachedFont(final int displayList, final long lastUsage, final boolean deleted) {
        this.displayList = displayList;
        this.lastUsage = lastUsage;
        this.deleted = deleted;
    }

    public CachedFont(final int displayList, final long lastUsage) {
        this(displayList, lastUsage, false);
    }

    @Override
    protected void finalize() {
        if (!this.deleted) {
            GL11.glDeleteLists(this.displayList, 1);
        }
    }

    public int getDisplayList() {
        return this.displayList;
    }

    public long getLastUsage() {
        return this.lastUsage;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setLastUsage(final long lastUsage) {
        this.lastUsage = lastUsage;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }
}