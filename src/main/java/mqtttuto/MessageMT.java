package mqtttuto;

import java.io.Serializable;

public class MessageMT implements Serializable{

    private final long id;
    private final String content;

    public MessageMT(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
