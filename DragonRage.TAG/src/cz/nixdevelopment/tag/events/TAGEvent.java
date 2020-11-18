package cz.nixdevelopment.tag.events;

public class TAGEvent {

    private String identifier;
    private String tag;
    
    public TAGEvent(String identifier, String tag) {
        this.identifier = identifier;
        this.tag = tag;
    }
    
    public String GetIdentifier() {
        return this.identifier;
    }
    
    public String GetTag() {
        return this.tag;
    }
    
    public void SetTag(String tag) {
        this.tag = tag;
    }
    
}
