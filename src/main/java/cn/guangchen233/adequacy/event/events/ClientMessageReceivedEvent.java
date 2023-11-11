package cn.guangchen233.adequacy.event.events;

import cn.guangchen233.adequacy.event.bases.BaseEvent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class ClientMessageReceivedEvent extends BaseEvent {
    private ITextComponent message;
    private final ChatType type;

    public ClientMessageReceivedEvent(ChatType type, ITextComponent message) {
        this.type = type;
        this.setMessage(message);
    }

    public ITextComponent getMessage() {
        return message;
    }

    public void setMessage(ITextComponent message) {
        this.message = message;
    }

    public ChatType getType() {
        return type;
    }
}
